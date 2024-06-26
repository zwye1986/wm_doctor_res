package com.pinde.sci.biz.jsres;

import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.form.jsres.JykhInfoForm;
import com.pinde.sci.model.jsres.DoctorSignup;
import com.pinde.sci.model.jsres.JsDoctorInfoExt;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface IJsResDoctorSignupBiz {

    /**
     * 保存
     *
     * @param doctorSignup
     * @return
     */
    int saveDoctorSignup(DoctorSignup doctorSignup);


}
