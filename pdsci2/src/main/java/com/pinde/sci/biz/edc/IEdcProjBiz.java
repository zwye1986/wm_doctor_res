package com.pinde.sci.biz.edc;

import com.pinde.sci.form.pub.ProjFileForm;
import com.pinde.sci.model.mo.EdcProjParam;
import com.pinde.sci.model.mo.PubProj;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEdcProjBiz {
	
	public PubProj read(String projFlow);
	
	public void save(PubProj proj);
	
	public void add(PubProj proj);
	
	public void mod(PubProj proj);
	
	public void del(PubProj proj);
	
	public List<PubProj> search(PubProj proj);
	
	public List<PubProj> searchForUser(PubProj proj,String userFlow);

	public EdcProjParam readProjParam(String projFlow);
 
	public void addProjParam(EdcProjParam param);

	public void modify(EdcProjParam param);

	List<ProjFileForm> searchProjFiles(String projFlow) throws Exception;

	int savePubFile(MultipartFile[] files, String projFlow) throws Exception;

	int modPubFile(String fileFlow, String isShared, String recordStatus, String projFlow)
			throws Exception; 
	
}
