CREATE TABLE CrawlRecord (
	id BIGINT NOT NULL AUTO_INCREMENT,
	beingScraped TINYINT(1) DEFAULT 0,
	context VARCHAR(255),
	dateScraped DATETIME,
	status VARCHAR(255),
	url VARCHAR(250),
	PRIMARY KEY (id)
);
SET autocommit = 0;
INSERT INTO CrawlRecord (url, status, beingScraped) VALUES 
('https://aip.fipapatients.org/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/biosamples/', 'UNTRIED', 0) 
, ('http://www.cathdb.info/', 'UNTRIED', 0) 
, ('https://molgenis51.gcc.rug.nl/', 'UNTRIED', 0) 
, ('https://deb-central.org/', 'UNTRIED', 0) 
, ('https://ega-archive.org/', 'UNTRIED', 0) 
, ('https://www.ensembl.org/index.html', 'UNTRIED', 0) 
, ('https://fairsharing.org/', 'UNTRIED', 0) 
, ('http://gigadb.org/site/index', 'UNTRIED', 0) 
, ('https://hamap.expasy.org/archaea.html', 'UNTRIED', 0) 
, ('https://www.proteinatlas.org/', 'UNTRIED', 0) 
, ('http://www.guidetopharmacology.org/', 'UNTRIED', 0) 
, ('http://identifiers.org/', 'UNTRIED', 0) 
, ('http://mvid-central.org/', 'UNTRIED', 0) 
, ('http://mobidb.bio.unipd.it/', 'UNTRIED', 0) 
, ('https://modelarchive.org/', 'UNTRIED', 0) 
, ('https://swissmodel.expasy.org/repository', 'UNTRIED', 0) 
, ('https://mint.bio.uniroma2.it/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/', 'UNTRIED', 0) 
, ('https://datamed.org/display-item.php?repository=0006&id=59139ef65152c62a9fc18ff7&query=', 'UNTRIED', 0) 
, ('https://ega-archive.org/datasets/EGAD00000000001', 'UNTRIED', 0) 
, ('https://enzyme.expasy.org/EC/1.1.3.-', 'UNTRIED', 0) 
, ('https://hamap.expasy.org/cgi-bin/unirule/unirule_browse.cgi', 'UNTRIED', 0) 
, ('http://www.omicsdi.org/#/dataset/arrayexpress-repository/E-MTAB-6848', 'UNTRIED', 0) 
, ('https://prosite.expasy.org/cgi-bin/prosite/prosite-list.pl', 'UNTRIED', 0) 
, ('https://prosite.expasy.org/cgi-bin/prosite/prosite_browse.cgi', 'UNTRIED', 0) 
, ('https://reactome.org/content/detail/R-HSA-449147', 'UNTRIED', 0) 
, ('http://scientificdata.isa-explorer.org/', 'UNTRIED', 0) 
, ('https://www.uniprot.org/uniprot/Q62226', 'UNTRIED', 0) 
, ('https://www.uniprot.org/uniprot/P04426', 'UNTRIED', 0) 
, ('https://www.uniprot.org/uniprot/Q9WU20', 'UNTRIED', 0) 
, ('https://www.uniprot.org/uniprot/P46736', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6939/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL22/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6941/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6942/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6944/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6945/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6946/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6947/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL1163143/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6948/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6950/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6952/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6954/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6957/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6960/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL1163144/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6961/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6962/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6963/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6968/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6970/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6971/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6973/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6975/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6976/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6977/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6981/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6982/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6983/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6984/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL6985/', 'UNTRIED', 0) 
, ('https://www.ebi.ac.uk/chembl/compound_report_card/CHEMBL1163147/', 'UNTRIED', 0) 
; COMMIT;