package com.pinde.sci.biz.pub;

import com.pinde.sci.model.mo.PubPatientWindow;

import java.util.List;


public interface IPubPatientWindowBiz {

    int savePatientWindow(PubPatientWindow patientWindow);

    List<PubPatientWindow> searchPatientWindowList(PubPatientWindow window);

    PubPatientWindow searchPatientWindow(String patientFlow, String visitFlow);

//	PubPatientWindow readPatientWindow(String recordFlow);

    List<PubPatientWindow> searchPatientWindowByPatientFlows(String projFlow,
                                                             List<String> patientFlows);

    int savePatientWindow(String projFlow, String patientFlow, String visitFlow);

    PubPatientWindow readPatientWindow(String patientFlow, String visitFlow);

}  
  