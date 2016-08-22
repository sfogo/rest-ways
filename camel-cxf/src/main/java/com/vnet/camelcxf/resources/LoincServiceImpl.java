/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vnet.camelcxf.resources;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.ws.rs.WebApplicationException;

public class LoincServiceImpl implements LoincService {
    
    private final DBService dbService = new DBService();

    public LoincServiceImpl() {
    }
    
    public Loinc getCode(String id) {
        final Collection<Loinc> list = query("select * from loinc where loinc_num = '" + id + "'");
        if (list.size() == 0)
            throw new WebApplicationException(Utils.makeResponse(404, id,"Cannot find Loinc Code"));

        return list.iterator().next();
    }

    public Collection<Loinc> getCodes(String q, String type) {
        if (Utils.notSet(q))
            throw new WebApplicationException(Utils.makeResponse(400, "q", "Missing query parameter q"));

        if (Utils.notSet(type) || "code".equals(type))
            return query("select * from loinc where loinc_num like '%" + q + "%'");

        if ("name".equals(type))
            return query("select * from loinc where lower(long_common_name) like '%" + q.toLowerCase() + "%'");

        throw new WebApplicationException(Utils.makeResponse(400, type, "Invalid search type"));
    }

    private Collection<Loinc> query(String sql) {
        try {
            final List<Properties> items = dbService.query(sql);
            final Collection<Loinc> codes = new LinkedList<>();
            codes.addAll(items.stream().map(LoincServiceImpl::transform).collect(Collectors.toList()));
            return codes;
        } catch (SQLException e) {
            throw new WebApplicationException(Utils.makeResponse(400, sql, e.getMessage()));
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
