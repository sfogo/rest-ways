# Explore LOINC measurement and observation codes with Camel CXF + JSON
## Foreword
* [Routing](src/main/java/com/vnet/camelcxf/Routes.java) was lifted from Camel CXF [Samples](https://github.com/apache/camel/blob/master/examples/camel-example-cxf/src/main/java/org/apache/camel/example/cxf/jaxrs/CamelRouterBuilder.java).

## Run
* See [parent](https://github.com/sfogo/rest-ways) project for [LOINC](https://loinc.org) database setup.
* Set the following environment variables:
  * `DEMO_DB_URL`
  * `DEMO_DB_USERNAME`
  * `DEMO_DB_PASSWORD`
  * Optional `DEMO_DB_DRIVER_CLASSNAME` (defaults to MySQL with `com.mysql.jdbc.Driver`). See `pom.xl` for `mysql` artifact.
  * Optional `DEMO_REST_PORT` (defaults to 8080)
* If you installed the [parent](https://github.com/sfogo/rest-ways), directly run with `mvn exec:java` (otherwise run with `mvn compile exec:java`)
* Examples with `DEMO_REST_PORT=7272`
  * `$ curl http://localhost:7272/loinc/codes/56789-1`
`{"loincNum":"56789-1","component":"Insulin^32H post XXX challenge","property":"ACnc","timeAspect":"Pt","system":"Ser/Plas","scaleType":"Qn","methodType":"","codeClass":"CHAL","source":"BiTAC","dateLastChanged":1250233200000,"changeType":"MIN","comments":"","status":"ACTIVE","consumerName":"","molarMass":"","classType":1,"formula":"","species":"","exampleAnswers":"","acsSym":"","baseName":"","naaccrId":"","codeTable":"","surveyQuestText":"","surveyQuestSrc":"","unitsRequired":"N","submittedUnits":"","relatedNames2":"32h p chal; After; Arbitrary concentration; CHEMISTRY.CHALLENGE TESTING; HUM; Humulin; IH7; Insul; Lente; NPH; p chal; Pl; Plasma; Plsm; Point in time; PST; QNT; Quan; Quant; Quantitative; Random; Semilente; SerP; SerPl; SerPlas; Serum; Sliding; SR; Ultralente","shortName":"Insulin 32h p chal SerPl-aCnc","orderObs":"Observation","cdiscCommonTests":"","hl7FieldSubFieldId":"","externalCopyrightNotice":"","exampleUnits":"mU/mL;mcU/mL","longCommonName":"Insulin [Units/volume] in Serum or Plasma --32 hours post XXX challenge","hl7V2DataType":"","hl7V3DataType":"","curatedRangeAndUnits":"","documentSection":"","exampleUcumUnits":"u[IU]/mL","exampleSiUcumUnits":"","statusReason":"","statusText":"","changeReasonPublic":"","commonTestRank":0,"commonOrderRank":0,"commonSiTestRank":0,"hl7AttachmentStructure":"","externalCopyrightLink":""}`
  * Get all codes that contains `12345`  
`http://localhost:7272/loinc/codes?q=12345`
  * Get codes whose name contains `cannabi`  
`http://localhost:7272/loinc/codes?q=cannabi&type=name`

