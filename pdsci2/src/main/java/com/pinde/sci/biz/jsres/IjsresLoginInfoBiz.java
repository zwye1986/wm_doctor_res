package com.pinde.sci.biz.jsres;


import com.pinde.sci.model.mo.JsresLoginInfo;

import java.util.List;

public interface IjsresLoginInfoBiz {


    List<JsresLoginInfo> search(JsresLoginInfo jsresLoginInfo);
}
