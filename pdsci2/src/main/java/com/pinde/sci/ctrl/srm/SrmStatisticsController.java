package com.pinde.sci.ctrl.srm;

import com.pinde.sci.common.GeneralController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 报表统计
 */
@Controller
@RequestMapping("/srm/statistics")
public class SrmStatisticsController extends GeneralController {

    /**
     * 到账经费一览表
     * @return
     */
    @RequestMapping("/fundArrivalList")
    public String fundArrivalList(){

        return "/srm/statistics/fundArrivalList";
    }

    /**
     * 奖项信息一览表
     * @return
     */
    @RequestMapping("/achSatList")
    public String achSatList(){

        return "/srm/statistics/achSatList";
    }

    /**
     * 经费到账个人排行榜
     * @return
     */
    @RequestMapping("/fundArrivalRanking")
    public String fundArrivalRanking(){

        return "/srm/statistics/fundArrivalRanking";
    }

    /**
     * 经费到账科室排行榜
     * @return
     */
    @RequestMapping("/fundArrivalDeptRanking")
    public String fundArrivalDeptRanking(){

        return "/srm/statistics/fundArrivalDeptRanking";
    }

    /**
     * 课题信息一览表
     * @return
     */
    @RequestMapping("/achTopicList")
    public String achTopicList(){

        return "/srm/statistics/achTopicList";
    }

    /**
     * 论文信息一览表
     * @return
     */
    @RequestMapping("/ackThesisList")
    public String ackThesisList(){

        return "/srm/statistics/ackThesisList";
    }

    /**
     * 人员结构分析表
     * @return
     */
    @RequestMapping("/personFabric")
    public String personFabric(){

        return "/srm/statistics/personFabric";
    }

    /**
     * 专利信息一览表
     * @return
     */
    @RequestMapping("/achPatentList")
    public String achPatentList(){

        return "/srm/statistics/achPatentList";
    }
}
