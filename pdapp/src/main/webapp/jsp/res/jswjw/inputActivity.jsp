<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
    	{
            "inputId": "activity_date",
            "label": "日期",
            "required":true,
            "inputType": "date"
        },
    	{
            "inputId": "activity_content",
            "label": "内容",
            "required":true,
            "inputType": "textarea"
        },
    	{
            "inputId": "activity_address",
            "label": "活动地点",
            "required":true,
            "inputType": "textarea"
        },
        {
            "inputId": "activity_way",
            "label": "活动形式",
            "required":true,
            "inputType": "select",
            "options": [
			        {
	                    "optionId": "17",
	                    "optionDesc": "入院教育"
	                },
<%--	                {--%>
<%--	                    "optionId": "2",--%>
<%--	                    "optionDesc": "疑难病例讨论"--%>
<%--	                },--%>
<%--	                {--%>
<%--	                    "optionId": "3",--%>
<%--	                    "optionDesc": "危重病例讨论"--%>
<%--	                },--%>
	                {
	                    "optionId": "1",
	                    "optionDesc": "教学查房"
	                },
<%--	                 {--%>
<%--	                    "optionId": "5",--%>
<%--	                    "optionDesc": "死亡病例讨论"--%>
<%--	                },--%>
	                {
	                    "optionId": "11",
	                    "optionDesc": "教学病例讨论"
	                },
                    {
                        "optionId": "2",
                        "optionDesc": "疑难病例讨论"
                    },
                    {
                        "optionId": "3",
                        "optionDesc": "危重病例讨论"
                    },
                    {
                        "optionId": "5",
                        "optionDesc": "死亡病例讨论"
                    },
	                 {
	                    "optionId": "4",
	                    "optionDesc": "临床小讲课"
	                },
                    {
                        "optionId": "12",
                        "optionDesc": "临床操作技能床旁教学"
                    },
                    {
                    "optionId":"13",
                    "optionDesc":"住院病历书写指导教学"
                    },
                    {
                    "optionId":"18",
                    "optionDesc":"入专业基地教育"
                    },
                    {
                    "optionId":"6",
                    "optionDesc":"入轮转科室教育"
                    },
                    {
                    "optionId":"14",
                    "optionDesc":"手术操作指导教学"
                    },
                    {
                    "optionId":"16",
                    "optionDesc":"临床文献研读会"
                    },
                    {
                    "optionId":"9",
                    "optionDesc":"教学阅片"
                    },
                    {
                    "optionId":"15",
                    "optionDesc":"影像诊断报告书写指导教学"
                    },
                    {
                    "optionId":"10",
                    "optionDesc":"门诊教学"
                    },
                    {
                    "optionId":"19",
                    "optionDesc":"晨间报告"
                    },
                    {
                    "optionId":"20",
                    "optionDesc":"报告单分析"
                    },
                    {
                    "optionId":"21",
                    "optionDesc":"教学上机"
                    },
                    {
                    "optionId":"22",
                    "optionDesc":"上级演示"
                    },
                    {
                    "optionId":"7",
                    "optionDesc":"出科考核"
                    },
                    {
                    "optionId":"8",
                    "optionDesc":"技能培训"
                    }

            ]
        },
        {
            "inputId": "activity_period",
            "label": "学时",
            "required":true,
            "inputType": "select",
            "options": [
                {
                    "optionId": "1",
                    "optionDesc": "1"
                },
                {
                    "optionId": "2",
                    "optionDesc": "2"
                },
                {
                    "optionId": "3",
                    "optionDesc": "3"
                },
                {
                    "optionId": "4",
                    "optionDesc": "4"
                },
                {
                    "optionId": "5",
                    "optionDesc": "5"
                }
            ]
        },
        {
            "inputId": "activity_speaker",
            "label": "主讲人",
            "required":true,
            "inputType": "text"
        }