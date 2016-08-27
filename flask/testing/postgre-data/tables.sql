--
-- Table structure for table 'loinc'
--

DROP TABLE IF EXISTS loinc;
CREATE TABLE IF NOT EXISTS loinc (
  loinc_num varchar(10) NOT NULL,
  component varchar(255) DEFAULT NULL,
  property varchar(30) DEFAULT NULL,
  time_aspct varchar(15) DEFAULT NULL,
  system varchar(100) DEFAULT NULL,
  scale_typ varchar(30) DEFAULT NULL,
  method_typ varchar(50) DEFAULT NULL,
  class varchar(20) DEFAULT NULL,
  source varchar(8) DEFAULT NULL,
  date_last_changed timestamp DEFAULT NULL,
  chng_type varchar(3) DEFAULT NULL,
  comments text,
  status varchar(11) DEFAULT NULL,
  consumer_name varchar(255) DEFAULT NULL,
  molar_mass varchar(13) DEFAULT NULL,
  classtype int DEFAULT NULL,
  formula varchar(255) DEFAULT NULL,
  species varchar(20) DEFAULT NULL,
  exmpl_answers text,
  acssym text,
  base_name varchar(50) DEFAULT NULL,
  naaccr_id varchar(20) DEFAULT NULL,
  code_table varchar(10) DEFAULT NULL,
  survey_quest_text text,
  survey_quest_src varchar(50) DEFAULT NULL,
  unitsrequired varchar(1) DEFAULT NULL,
  submitted_units varchar(30) DEFAULT NULL,
  relatednames2 text,
  shortname varchar(40) DEFAULT NULL,
  order_obs varchar(15) DEFAULT NULL,
  cdisc_common_tests varchar(1) DEFAULT NULL,
  hl7_field_subfield_id varchar(50) DEFAULT NULL,
  external_copyright_notice text,
  example_units varchar(255) DEFAULT NULL,
  long_common_name varchar(255) DEFAULT NULL,
  hl7_v2_datatype varchar(255) DEFAULT NULL,
  hl7_v3_datatype varchar(255) DEFAULT NULL,
  curated_range_and_units text,
  document_section varchar(255) DEFAULT NULL,
  example_ucum_units varchar(255) DEFAULT NULL,
  example_si_ucum_units varchar(255) DEFAULT NULL,
  status_reason varchar(9) DEFAULT NULL,
  status_text text,
  change_reason_public text,
  common_test_rank int DEFAULT NULL,
  common_order_rank int DEFAULT NULL,
  common_si_test_rank int DEFAULT NULL,
  hl7_attachment_structure varchar(15) DEFAULT NULL,
  ExternalCopyrightLink varchar(255) DEFAULT NULL,
  PRIMARY KEY (loinc_num)
);

--
-- Table structure for table 'measurement'
--

DROP TABLE IF EXISTS measurement;
CREATE TABLE IF NOT EXISTS measurement (
  id varchar(36) NOT NULL,
  patientId varchar(36) NOT NULL,
  deviceId varchar(36) NOT NULL,
  code varchar(10) NOT NULL,
  unit varchar(20) NOT NULL,
  value decimal NOT NULL,
  manual smallint NOT NULL,
  captureTimestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  creationTimestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  note varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);




