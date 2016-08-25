# Explore LOINC measurement and observation codes with Camel CXF
## Foreword
* See [parent](https://github.com/sfogo/rest-ways) project for [LOINC](https://loinc.org) database setup.
* [Routing](src/main/java/com/vnet/camelcxf/Routes.java) was lifted from Camel CXF [Samples](https://github.com/apache/camel/blob/master/examples/camel-example-cxf/src/main/java/org/apache/camel/example/cxf/jaxrs/CamelRouterBuilder.java).

## Run
* Export the following environment variables:
```
export DEMO_DB_URL=jdbc:mysql://<Host>/<DatabaseName>
export DEMO_DB_USERNAME=<username>
export DEMO_DB_PASSWORD=<password>
export DEMO_REST_PORT=7272
```
* If you installed the [parent](https://github.com/sfogo/rest-ways), directly run with `mvn exec:java` (otherwise run with `mvn compile exec:java`)
* Examples
  * `$ curl http://localhost:7272/loinc/codes/12345-5`  
`<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Loinc><acsSym></acsSym><baseName></baseName><cdiscCommonTests></cdiscCommonTests><changeReasonPublic>The Property has been changed from ACnc to reflect the new model for ordinal terms where results are based on a cut-off value.</changeReasonPublic><changeType>MIN</changeType><classType>1</classType><codeClass>DRUG/TOX</codeClass><codeTable></codeTable><comments></comments><commonOrderRank>0</commonOrderRank><commonSiTestRank>0</commonSiTestRank><commonTestRank>0</commonTestRank><component>Cyclothiazide</component><consumerName></consumerName><curatedRangeAndUnits></curatedRangeAndUnits><dateLastChanged>2013-05-23T00:00:00-07:00</dateLastChanged><documentSection></documentSection><exampleAnswers></exampleAnswers><exampleSiUcumUnits></exampleSiUcumUnits><exampleUcumUnits></exampleUcumUnits><exampleUnits></exampleUnits><externalCopyrightLink></externalCopyrightLink><externalCopyrightNotice></externalCopyrightNotice><formula></formula><hl7AttachmentStructure></hl7AttachmentStructure><hl7FieldSubFieldId></hl7FieldSubFieldId><hl7V2DataType></hl7V2DataType><hl7V3DataType></hl7V3DataType><loincNum>12345-5</loincNum><longCommonName>Cyclothiazide [Presence] in Urine</longCommonName><methodType></methodType><molarMass></molarMass><naaccrId></naaccrId><orderObs>Both</orderObs><property>Threshold</property><relatedNames2>DRUG/TOXICOLOGY; Drugs; Ordinal; Point in time; QL; Qual; Qualitative; Random; Screen; UA; UR; Urn</relatedNames2><scaleType>Ord</scaleType><shortName>Cyclothiazide Ur Ql</shortName><source>LCA</source><species></species><status>ACTIVE</status><statusReason></statusReason><statusText></statusText><submittedUnits></submittedUnits><surveyQuestSrc></surveyQuestSrc><surveyQuestText></surveyQuestText><system>Urine</system><timeAspect>Pt</timeAspect><unitsRequired></unitsRequired></Loinc>`
  * Get all codes that contains `12345`  
`http://localhost:7272/loinc/codes?q=12345`
  * Get codes whose name contains `cannabi`  
`http://localhost:7272/loinc/codes?q=cannabi&type=name`

