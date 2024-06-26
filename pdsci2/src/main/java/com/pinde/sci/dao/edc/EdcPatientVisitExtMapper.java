package com.pinde.sci.dao.edc;

import com.pinde.sci.model.mo.EdcPatientVisit;

import java.util.List;


public interface EdcPatientVisitExtMapper {
    List<EdcPatientVisit> searchEdcPatientVisitList(String param1,
                                                    String param2);

    String searchSdvStatus(String param1,
                           String param2, String param3);

    String searchEdcPatientVistMap(String param1, String param2, String param3);
}