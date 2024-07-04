package com.pinde.res.biz.mbgl;

import com.pinde.res.ctrl.jswjw.ActivityImageFileForm;
import com.pinde.res.ctrl.jswjw.ImageFileForm;
import com.pinde.res.model.jswjw.mo.FromTitle;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IMbglBiz {


	Map<String,Object> findByUserCode(String userCode);

	List<Map<String,Object>> readOtherUsers(String userCode);
}
  