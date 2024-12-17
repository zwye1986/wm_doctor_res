package com.pinde.res.biz.hbres;

import com.pinde.core.model.PubFile;

import java.util.List;

public interface IFileBiz {
	List<PubFile> searchByProductFlow(String productFlow);
	void addFile(PubFile file);

	PubFile readFile(String fileFlow);

	void editFile(PubFile pubFile);


	PubFile searchByProductFlowAndType(String userFlow, String examTeaSigin);

	List<PubFile> findFileByTypeFlow(String resDoctorKqFile, String recordFlow);

    PubFile readProductFile(String resultFlow, String fileType);
}
 