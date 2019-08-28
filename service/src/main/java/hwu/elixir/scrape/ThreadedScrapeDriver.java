package hwu.elixir.scrape;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hwu.elixir.scrape.db.crawl.CrawlRecord;
import hwu.elixir.scrape.db.crawl.DBAccess;
import hwu.elixir.scrape.scraper.ScrapeState;
import hwu.elixir.scrape.scraper.ScrapeThread;
import hwu.elixir.scrape.scraper.ServiceScraper;
import hwu.elixir.utils.Helpers;


/** 
 * Runs the scrape. Connects to DBMS to collect a list of URLs (in the forms of CrawlRecords) to scrape. 
 * Scrapes them in turn (using 8 threads), writes the (bio)schema markup extracted to a file (1 file per URL)
 * and adds provenance to the CrawlRecord. Once all URLs extracted, the CrawlRecords are synced to the DBMS and 
 * another batch are fetched.
 * 
 * @author kcm
 *
 */
public class ThreadedScrapeDriver {

	private static final String propertiesFile = "application.properties";

	private static int waitTime = 1;
	private static int numberOfPagesToCrawlInALoop;
	private static int totalNumberOfPagesToCrawlInASession;
	private static String outputFolder;
	private static int pagesCounter = 0;

	private DBAccess dba;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

	private static Logger logger = LoggerFactory.getLogger(System.class.getName());


	/**
	 * Runs the scrape process
	 * 
	 * @param args Not used
	 */
	public static void main(String[] args) {
		ThreadedScrapeDriver driver = new ThreadedScrapeDriver();

		Date date = new Date(System.currentTimeMillis());
		logger.info("STARTING CRAWL: " + formatter.format(date));
		driver.processProperties();
		driver.runScrape();
	}
	
	/** 
	 * Fires off threads and organises update of DBMS at end before starting another loop
	 * 
	 */
	private void runScrape() {
		while (pagesCounter <= totalNumberOfPagesToCrawlInASession) {
			System.out.println(pagesCounter + " is less than " + totalNumberOfPagesToCrawlInASession);
			List<CrawlRecord> pagesToPull = generatePagesToPull();
			if (pagesToPull.isEmpty()) {
				break;
			}
			ScrapeState scrapeState = new ScrapeState(pagesToPull);

			ScrapeThread scrape1 = new ScrapeThread(new ServiceScraper(), scrapeState, waitTime, outputFolder);
			scrape1.setName("S1");

			ScrapeThread scrape2 = new ScrapeThread(new ServiceScraper(), scrapeState, waitTime, outputFolder);
			scrape2.setName("S2");

			ScrapeThread scrape3 = new ScrapeThread(new ServiceScraper(), scrapeState, waitTime, outputFolder);
			scrape3.setName("S3");

			ScrapeThread scrape4 = new ScrapeThread(new ServiceScraper(), scrapeState, waitTime, outputFolder);
			scrape4.setName("S4");

			ScrapeThread scrape5 = new ScrapeThread(new ServiceScraper(), scrapeState, waitTime, outputFolder);
			scrape5.setName("S5");

			ScrapeThread scrape6 = new ScrapeThread(new ServiceScraper(), scrapeState, waitTime, outputFolder);
			scrape6.setName("S6");

			ScrapeThread scrape7 = new ScrapeThread(new ServiceScraper(), scrapeState, waitTime, outputFolder);
			scrape7.setName("S7");

			ScrapeThread scrape8 = new ScrapeThread(new ServiceScraper(), scrapeState, waitTime, outputFolder);
			scrape8.setName("S8");

			scrape1.start();
			scrape2.start();
			scrape3.start();
			scrape4.start();
			scrape5.start();
			scrape6.start();
			scrape7.start();
			scrape8.start();
			long startTime = System.nanoTime();
			
			try {
				scrape1.join();
				scrape2.join();
				scrape3.join();
				scrape4.join();
				scrape5.join();
				scrape6.join();
				scrape7.join();
				scrape8.join();
			} catch (InterruptedException e) {
				System.out.println("Unexpected interruption of thread when trying to join");
				e.printStackTrace();
			}

			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			System.out.println("Time in s to complete: " + timeElapsed / 1e+9);
			logger.info("Time in s to complete: " + timeElapsed / 1e+9);

			updateDatabase(scrapeState);
			pagesCounter += numberOfPagesToCrawlInALoop;
			System.out.println("ENDED loop");
		}
		dba.shutdown();
		Date date = new Date(System.currentTimeMillis());
		logger.info("ENDING CRAWL: " + formatter.format(date));		
		System.out.println("CRAWL OVER!");
	}

	/**
	 * Updates the DBMS by syncing CrawlRecords at end
	 * 
	 * @param scrapeState State of scrape at end
	 * @return true if success / false otherwise
	 * @see ScrapeState
	 * @see CrawlRecord
	 */
	private boolean updateDatabase(ScrapeState scrapeState) {
		boolean result = false;
		try {
			if(dba == null)
				dba = new DBAccess();
			result = dba.updateAllCrawlRecords(scrapeState.getPagesProcessed());
		} catch (Exception e) {
			System.out.println("Exception thrown by updated database");
			e.printStackTrace();
			return false;
		} finally {
			dba.close();
		}
		return result;
	}

	/**
	 * Get a list of URLs (in the form of CrawlRecords) that need to be scraped 
	 * from DBMS.
	 * 
	 * @return List of URLs to be scraped
	 * @see CrawlRecord
	 */
	private List<CrawlRecord> generatePagesToPull() {	
		try {
			if(dba == null)
				dba = new DBAccess();
			return dba.getSomeCrawlRecords(numberOfPagesToCrawlInALoop);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			dba.close();
		}
		return null;
	}

	/**
	 * Updates properties based on properties file in src > main > resources
	 * 
	 */
	private void processProperties() {
		ClassLoader classLoader = ThreadedScrapeDriver.class.getClassLoader();

		InputStream is = classLoader.getResourceAsStream(propertiesFile);
		if(is == null) {
			logger.error("     Cannot find " + propertiesFile + " file");
			throw new IllegalArgumentException(propertiesFile + "file is not found!");
		}

		Properties prop = new Properties();

		try {
			prop.load(is);
		} catch (IOException e) {
			logger.error("     Cannot load application.properties", e);
			System.exit(0);
		}

		waitTime = Integer.parseInt(prop.getProperty("waitTime").trim());
		logger.info("     waitTime: " + waitTime);
		outputFolder = prop.getProperty("outputFolder").trim();
		outputFolder += Helpers.getDateForName()+"/";
		logger.info("     outputFolder: " + outputFolder);		
		numberOfPagesToCrawlInALoop = Integer.parseInt(prop.getProperty("numberOfPagesToCrawlInALoop").trim());
		logger.info("     numberOfPagesToCrawl: " + numberOfPagesToCrawlInALoop);
		totalNumberOfPagesToCrawlInASession = Integer.parseInt(prop.getProperty("totalNumberOfPagesToCrawlInASession").trim());
		logger.info("     totalNumberOfPagesToCrawlInASession: " + totalNumberOfPagesToCrawlInASession);
		logger.info("\n\n\n");
	}

}