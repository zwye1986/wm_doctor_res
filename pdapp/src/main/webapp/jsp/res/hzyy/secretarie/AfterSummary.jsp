<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "datas": [
        {
            "id": "HosSecName",
            "name": "轮转科室：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(afterSummmaryData.HosSecName)}
        },
        <c:set var="nowDate" value="${pdfn:getCurrDate()}"></c:set>
        {
            "id": "OutDate",
            "name": "出科时间：",
            "type": "date",
            "required":true,
            "readonly":false,
            "value":${pdfn:toJsonString(empty afterSummmaryData.OutDate ? nowDate:afterSummmaryData.OutDate)}
        },
        {
            "id": "TrueName",
            "name": "姓        名：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(afterSummmaryData.TrueName)}
        },
        {
            "id": "StartYear",
            "name": "年        级：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(afterSummmaryData.StartYear)}
        },
        {
            "id": "SpName",
            "name": "专        业：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(afterSummmaryData.SpName)}
        },
        {
            "id": "CycleDate",
            "name": "轮转时间：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(afterSummmaryData.CycleDate)}
        },
        {
            "id": "HosName",
            "name": "所在医院：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(afterSummmaryData.HosName)}
        },
        {
            "id": "attendInfo",
            "name": "出勤情况：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":"出勤${afterSummmaryData.AttendInfo }天|病假${afterSummmaryData.SICK }天|事假${afterSummmaryData.LEAVE }天"
        },
        {
            "id": "RequireCycleMonth ",
            "name": "轮转时间要求：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":"${afterSummmaryData.RequireCycleMonth }个月"
        },
        {
            "id": "bltl",
            "name": "参加病例讨论：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":"共${empty activitsData['教学病例讨论'] ? 0: activitsData['教学病例讨论']}次"
        },
        {
            "id": "jxcf",
            "name": "参加教学查房：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":"共${empty activitsData['教学查房'] ? 0: activitsData['教学查房']}次"
        },
        {
            "id": "xjk",
            "name": "参加小讲课：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":"共${empty activitsData['小讲课'] ? 0: activitsData['小讲课']}次"
        },
        {
            "id": "jnjs",
            "name": "技能技术操作指导：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":"共${empty activitsData['技能技术操作指导'] ? 0: activitsData['技能技术操作指导']}次"
        },
        {
            "id": "sxbl",
            "name": "书写病例：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":"应完成${empty datas['BRequireNumber']?0:datas['BRequireNumber']}份，实际完成${empty datas['BFinish']?0:datas['BFinish']}份"
        },
        {
            "id": "wcbz",
            "name": "完成病种：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":"应完成${empty datas['CRequireNumber']?0:datas['CRequireNumber']}份，实际完成${empty datas['CFinish']?0:datas['CFinish']}份"
        },
        {
            "id": "ssyq",
            "name": "手术要求：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":"应完成${empty datas['SRequireNumber']?0:datas['SRequireNumber']}份，实际完成${empty datas['SFinish']?0:datas['CFinish']}份"
        },
        {
            "id": "jncz",
            "name": "技能操作：",
            "type": "text",
            "required":false,
            "readonly":true,
            "value":"应完成${empty datas['ORequireNumber']?0:datas['ORequireNumber']}份，实际完成${empty datas['OFinish']?0:datas['CFinish']}份"
        },
        {
            "id": "BriefRequrie",
            "name": "文献、综述、读书报告学习及撰写情况：",
            "type": "textarea",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(afterSummmaryData.BriefRequrie)}
        },
        {
            "id": "GainsDefect",
            "name": "本次轮转的收获与不足：",
            "type": "textarea",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(afterSummmaryData.GainsDefect)}
        }
    ]
	</c:if>
}
