package com.vnet.service;

import com.vnet.Utils;
import com.vnet.db.SqlService;
import com.vnet.model.Loinc;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class LoincService {

    private SqlService sqlService;
    public void setSqlService(SqlService sqlService) {this.sqlService = sqlService;}

    public LoincService() {}
    public LoincService(SqlService sqlService) {this.sqlService = sqlService;}

    public Loinc getCode(String id) throws ServiceException {
        final Collection<Loinc> list = query("select * from loinc where loinc_num = '" + id + "'");
        if (list.size() == 0)
            throw new ServiceException(ServiceException.Code.NOT_FOUND, id);

        return list.iterator().next();
    }

    public Collection<Loinc> getCodes(String q, String type) throws ServiceException {
        if (Utils.notSet(q))
            throw new ServiceException(ServiceException.Code.MQP, "q");

        if (Utils.notSet(type) || "code".equals(type))
            return query("select * from loinc where loinc_num like '%" + q + "%'");

        if ("name".equals(type))
            return query("select * from loinc where lower(long_common_name) like '%" + q.toLowerCase() + "%'");

        throw new ServiceException(ServiceException.Code.IST, type);
    }

    private Collection<Loinc> query(String sql) throws ServiceException {
        try {
            final List<Properties> items = sqlService.query(sql);
            final Collection<Loinc> codes = new LinkedList<>();
            codes.addAll(items.stream().map(LoincService::transform).collect(Collectors.toList()));
            return codes;
        } catch (SQLException e) {
            throw new ServiceException(ServiceException.Code.SQL, sql, e.getMessage());
        }
    }

    static private Loinc transform(Properties item) {
        final Loinc c = new Loinc();
        c.setLoincNum(item.getProperty("loinc_num"));
        c.setComponent(item.getProperty("component"));
        c.setProperty(item.getProperty("property"));
        c.setTimeAspect(item.getProperty("time_aspct"));
        c.setSystem(item.getProperty("system"));
        c.setScaleType(item.getProperty("scale_typ"));
        c.setMethodType(item.getProperty("method_typ"));
        c.setCodeClass(item.getProperty("class"));
        c.setSource(item.getProperty("source"));
        c.setDateLastChanged(Utils.parse(item.getProperty("date_last_changed")));
        c.setChangeType(item.getProperty("chng_type"));
        c.setComments(item.getProperty("comments"));
        c.setStatus(item.getProperty("status"));
        c.setConsumerName(item.getProperty("consumer_name"));
        c.setMolarMass(item.getProperty("molar_mass"));
        c.setClassType(Integer.parseInt(item.getProperty("classtype")));
        c.setFormula(item.getProperty("formula"));
        c.setSpecies(item.getProperty("species"));
        c.setExampleAnswers(item.getProperty("exmpl_answers"));
        c.setAcsSym(item.getProperty("acssym"));
        c.setBaseName(item.getProperty("base_name"));
        c.setNaaccrId(item.getProperty("naaccr_id"));
        c.setCodeTable(item.getProperty("code_table"));
        c.setSurveyQuestText(item.getProperty("survey_quest_text"));
        c.setSurveyQuestSrc(item.getProperty("survey_quest_src"));
        c.setUnitsRequired(item.getProperty("unitsrequired"));
        c.setSubmittedUnits(item.getProperty("submitted_units"));
        c.setRelatedNames2(item.getProperty("relatednames2"));
        c.setShortName(item.getProperty("shortname"));
        c.setOrderObs(item.getProperty("order_obs"));
        c.setCdiscCommonTests(item.getProperty("cdisc_common_tests"));
        c.setHl7FieldSubFieldId(item.getProperty("hl7_field_subfield_id"));
        c.setExternalCopyrightNotice(item.getProperty("external_copyright_notice"));
        c.setExampleUnits(item.getProperty("example_units"));
        c.setLongCommonName(item.getProperty("long_common_name"));
        c.setHl7V2DataType(item.getProperty("hl7_v2_datatype"));
        c.setHl7V3DataType(item.getProperty("hl7_v3_datatype"));
        c.setCuratedRangeAndUnits(item.getProperty("curated_range_and_units"));
        c.setDocumentSection(item.getProperty("document_section"));
        c.setExampleUcumUnits(item.getProperty("example_ucum_units"));
        c.setExampleSiUcumUnits(item.getProperty("example_si_ucum_units"));
        c.setStatusReason(item.getProperty("status_reason"));
        c.setStatusText(item.getProperty("status_text"));
        c.setChangeReasonPublic(item.getProperty("change_reason_public"));
        c.setCommonTestRank(Integer.parseInt(item.getProperty("common_test_rank")));
        c.setCommonOrderRank(Integer.parseInt(item.getProperty("common_order_rank")));
        c.setCommonSiTestRank(Integer.parseInt(item.getProperty("common_si_test_rank")));
        c.setHl7AttachmentStructure(item.getProperty("hl7_attachment_structure"));
        c.setExternalCopyrightLink(item.getProperty("ExternalCopyrightLink"));
        return c;
    }
}
