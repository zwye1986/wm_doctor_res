package com.pinde.sci.ctrl.jsres;

import com.pinde.sci.common.GeneralController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsres/completionReport")
public class CompletionReportController extends GeneralController {

    @RequestMapping(value="/main")
    public String main(Model model){
        return "jsres/global/completionReport/main";
    }
}