package com.vnet.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Loinc {

    public Loinc() {}

    private String loincNum;
    private String component;
    private String property;
    private String timeAspect;
    private String system;
    private String scaleType;
    private String methodType;
    private String codeClass;
    private String source;
    private Date dateLastChanged;
    private String changeType;
    private String comments;
    private String status;
    private String consumerName;
    private String molarMass;
    private int classType;
    private String formula;
    private String species;
    private String exampleAnswers;
    private String acsSym;
    private String baseName;
    private String naaccrId;
    private String codeTable;
    private String surveyQuestText;
    private String surveyQuestSrc;
    private String unitsRequired;
    private String submittedUnits;
    private String relatedNames2;
    private String shortName;
    private String orderObs;
    private String cdiscCommonTests;
    private String hl7FieldSubFieldId;
    private String externalCopyrightNotice;
    private String exampleUnits;
    private String longCommonName;
    private String hl7V2DataType;
    private String hl7V3DataType;
    private String curatedRangeAndUnits;
    private String documentSection;
    private String exampleUcumUnits;
    private String exampleSiUcumUnits;
    private String statusReason;
    private String statusText;
    private String changeReasonPublic;
    private int commonTestRank;
    private int commonOrderRank;
    private int commonSiTestRank;
    private String hl7AttachmentStructure;
    private String ExternalCopyrightLink;

    public String getLoincNum() {
        return loincNum;
    }

    public void setLoincNum(String loincNum) {
        this.loincNum = loincNum;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getTimeAspect() {
        return timeAspect;
    }

    public void setTimeAspect(String timeAspect) {
        this.timeAspect = timeAspect;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getScaleType() {
        return scaleType;
    }

    public void setScaleType(String scaleType) {
        this.scaleType = scaleType;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getCodeClass() {
        return codeClass;
    }

    public void setCodeClass(String codeClass) {
        this.codeClass = codeClass;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDateLastChanged() {
        return dateLastChanged;
    }

    public void setDateLastChanged(Date dateLastChanged) {
        this.dateLastChanged = dateLastChanged;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getMolarMass() {
        return molarMass;
    }

    public void setMolarMass(String molarMass) {
        this.molarMass = molarMass;
    }

    public int getClassType() {
        return classType;
    }

    public void setClassType(int classType) {
        this.classType = classType;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getExampleAnswers() {
        return exampleAnswers;
    }

    public void setExampleAnswers(String exampleAnswers) {
        this.exampleAnswers = exampleAnswers;
    }

    public String getAcsSym() {
        return acsSym;
    }

    public void setAcsSym(String acsSym) {
        this.acsSym = acsSym;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getNaaccrId() {
        return naaccrId;
    }

    public void setNaaccrId(String naaccrId) {
        this.naaccrId = naaccrId;
    }

    public String getCodeTable() {
        return codeTable;
    }

    public void setCodeTable(String codeTable) {
        this.codeTable = codeTable;
    }

    public String getSurveyQuestText() {
        return surveyQuestText;
    }

    public void setSurveyQuestText(String surveyQuestText) {
        this.surveyQuestText = surveyQuestText;
    }

    public String getSurveyQuestSrc() {
        return surveyQuestSrc;
    }

    public void setSurveyQuestSrc(String surveyQuestSrc) {
        this.surveyQuestSrc = surveyQuestSrc;
    }

    public String getUnitsRequired() {
        return unitsRequired;
    }

    public void setUnitsRequired(String unitsRequired) {
        this.unitsRequired = unitsRequired;
    }

    public String getSubmittedUnits() {
        return submittedUnits;
    }

    public void setSubmittedUnits(String submittedUnits) {
        this.submittedUnits = submittedUnits;
    }

    public String getRelatedNames2() {
        return relatedNames2;
    }

    public void setRelatedNames2(String relatedNames2) {
        this.relatedNames2 = relatedNames2;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getOrderObs() {
        return orderObs;
    }

    public void setOrderObs(String orderObs) {
        this.orderObs = orderObs;
    }

    public String getCdiscCommonTests() {
        return cdiscCommonTests;
    }

    public void setCdiscCommonTests(String cdiscCommonTests) {
        this.cdiscCommonTests = cdiscCommonTests;
    }

    public String getHl7FieldSubFieldId() {
        return hl7FieldSubFieldId;
    }

    public void setHl7FieldSubFieldId(String hl7FieldSubFieldId) {
        this.hl7FieldSubFieldId = hl7FieldSubFieldId;
    }

    public String getExternalCopyrightNotice() {
        return externalCopyrightNotice;
    }

    public void setExternalCopyrightNotice(String externalCopyrightNotice) {
        this.externalCopyrightNotice = externalCopyrightNotice;
    }

    public String getExampleUnits() {
        return exampleUnits;
    }

    public void setExampleUnits(String exampleUnits) {
        this.exampleUnits = exampleUnits;
    }

    public String getLongCommonName() {
        return longCommonName;
    }

    public void setLongCommonName(String longCommonName) {
        this.longCommonName = longCommonName;
    }

    public String getHl7V2DataType() {
        return hl7V2DataType;
    }

    public void setHl7V2DataType(String hl7V2DataType) {
        this.hl7V2DataType = hl7V2DataType;
    }

    public String getHl7V3DataType() {
        return hl7V3DataType;
    }

    public void setHl7V3DataType(String hl7V3DataType) {
        this.hl7V3DataType = hl7V3DataType;
    }

    public String getCuratedRangeAndUnits() {
        return curatedRangeAndUnits;
    }

    public void setCuratedRangeAndUnits(String curatedRangeAndUnits) {
        this.curatedRangeAndUnits = curatedRangeAndUnits;
    }

    public String getDocumentSection() {
        return documentSection;
    }

    public void setDocumentSection(String documentSection) {
        this.documentSection = documentSection;
    }

    public String getExampleUcumUnits() {
        return exampleUcumUnits;
    }

    public void setExampleUcumUnits(String exampleUcumUnits) {
        this.exampleUcumUnits = exampleUcumUnits;
    }

    public String getExampleSiUcumUnits() {
        return exampleSiUcumUnits;
    }

    public void setExampleSiUcumUnits(String exampleSiUcumUnits) {
        this.exampleSiUcumUnits = exampleSiUcumUnits;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getChangeReasonPublic() {
        return changeReasonPublic;
    }

    public void setChangeReasonPublic(String changeReasonPublic) {
        this.changeReasonPublic = changeReasonPublic;
    }

    public int getCommonTestRank() {
        return commonTestRank;
    }

    public void setCommonTestRank(int commonTestRank) {
        this.commonTestRank = commonTestRank;
    }

    public int getCommonOrderRank() {
        return commonOrderRank;
    }

    public void setCommonOrderRank(int commonOrderRank) {
        this.commonOrderRank = commonOrderRank;
    }

    public int getCommonSiTestRank() {
        return commonSiTestRank;
    }

    public void setCommonSiTestRank(int commonSiTestRank) {
        this.commonSiTestRank = commonSiTestRank;
    }

    public String getHl7AttachmentStructure() {
        return hl7AttachmentStructure;
    }

    public void setHl7AttachmentStructure(String hl7AttachmentStructure) {
        this.hl7AttachmentStructure = hl7AttachmentStructure;
    }

    public String getExternalCopyrightLink() {
        return ExternalCopyrightLink;
    }

    public void setExternalCopyrightLink(String externalCopyrightLink) {
        ExternalCopyrightLink = externalCopyrightLink;
    }


}
