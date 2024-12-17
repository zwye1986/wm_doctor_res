package com.pinde.sci.biz.jsres;


import com.pinde.core.model.JsresLoginInfo;

import java.util.List;

public interface IjsresLoginInfoBiz {


    List<JsresLoginInfo> search(JsresLoginInfo jsresLoginInfo);
}
