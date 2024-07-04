<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "action":{
        "url":"saveDayEval",
        "save":"保存"
    },
    "datas": [

	         {
	            "id":"t1",
	            "name":"日常评价表",
	            "type":"title"
	         },
			{
				"id":"TrueName",
				"name":"姓名",
				"type":"text",
				"readonly":true,
			    "value":${pdfn:toJsonString(baseInfo.TrueName)}
			},
			{
				"id":"username",
				"name":"${baseInfo.UserType eq '1'?'学号':'工号'}",
				"type":"text",
				"readonly":true,
			    "value":${pdfn:toJsonString(baseInfo.username)}
			},
			{
				"id":"HosSecName",
				"name":"轮转科室",
				"type":"text",
				"readonly":true,
			    "value":${pdfn:toJsonString(baseInfo.HosSecName)}
			},
			{
				"id":"RStartTime",
				"name":"培训开始时间",
				"type":"text",
				"readonly":true,
			    "value":${pdfn:toJsonString(baseInfo.RStartTime)}
			},
			{
				"id":"REndTime",
				"name":"培训结束时间",
				"type":"text",
				"readonly":true,
			    "value":${pdfn:toJsonString(baseInfo.REndTime)}
			},
             {
	            "id":"t10",
	            "name":"考勤",
	            "type":"title"
	         },
			{
				"id":"shijia",
				"name":"事假",
				"type":"text",
				"readonly":true,
				"value":${pdfn:toJsonString(empty shijia ? '0':shijia)}
			},
			{
				"id":"bingjia",
				"name":"病假",
				"type":"text",
				"readonly":true,
				"value":${pdfn:toJsonString(empty bingjia ? '0':bingjia)}
			},
			{
				"id":"queqing",
				"name":"缺勤",
				"type":"text",
				"readonly":true,
				"value":${pdfn:toJsonString(empty queqing ? '0':queqing)}
			},
             {
	            "id":"t11",
	            "name":"医德医风",
	            "type":"title"
	         },
			 {
				 "id":"Score1_title",
				 "name":"沟通技巧，处理医患关系的能力(5分)",
				 "tip":"沟通技巧，处理医患关系的能力(5分)",
				 "type":"title"
			 },
			{
				"id":"Score1",
				"name":"分数",
				"type":"text",
				"isSum":true,
				"required":true,
				"placeholder": "0~5",
				"verify": {
					"max": "5",
				    "type": "int"
				},
				"value":${pdfn:toJsonString(dailyInfo.Score1)}
			},
			 {
				 "id":"Score2_title",
				 "name":"医疗作风端正，廉洁行医(5分)",
				 "tip":"医疗作风端正，廉洁行医(5分)",
				 "type":"title"
			 },
			{
				"id":"Score2",
				"name":"分数",
				"type":"text",
				"isSum":true,
				"required":true,
				"placeholder": "0~5",
				"verify": {
					"max": "5",
				    "type": "int"
				},
				"value":${pdfn:toJsonString(dailyInfo.Score2)}
			},
             {
	            "id":"t12",
	            "name":"工作态度",
	            "type":"title"
	         },
			 {
				 "id":"Score3_title",
				 "name":"团结协作，工作认真，责任心强，无医疗差错(5分)",
				 "tip":"团结协作，工作认真，责任心强，无医疗差错(5分)",
				 "type":"title"
			 },
			{
				"id":"Score3",
				"name":"分数",
				"type":"text",
				"isSum":true,
				"required":true,
				"placeholder": "0~5",
				"verify": {
					"max": "5",
				    "type": "int"
				},
				"value":${pdfn:toJsonString(dailyInfo.Score3)}
			},
             {
	            "id":"t13",
	            "name":"医疗业务工作",
	            "type":"title"
	         },
			 {
				 "id":"Score4_title",
				 "name":"医疗文书-抽查住院病历、医嘱、处方等(15分)",
				 "tip":"医疗文书-抽查住院病历、医嘱、处方等(15分)",
				 "type":"title"
			 },
			{
				"id":"Score4",
				"name":"分数",
				"type":"text",
				"isSum":true,
				"required":true,
				"placeholder": "0~15",
				"verify": {
					"max": "15",
				    "type": "int"
				},
				"value":${pdfn:toJsonString(dailyInfo.Score4)}
			},
			 {
				 "id":"Score5_title",
				 "name":"体格检查-体格检查正确规范、及时发现阳性体征(10分)",
				 "tip":"体格检查-体格检查正确规范、及时发现阳性体征(10分)",
				 "type":"title"
			 },
			{
				"id":"Score5",
				"name":"分数",
				"type":"text",
				"isSum":true,
				"required":true,
				"placeholder": "0~10",
				"verify": {
					"max": "10",
				    "type": "int"
				},
				"value":${pdfn:toJsonString(dailyInfo.Score5)}
			},
			 {
				 "id":"Score6_title",
				 "name":"操作技术-临床技能操作正确、规范、熟练与否(15分)",
				 "tip":"操作技术-临床技能操作正确、规范、熟练与否(15分)",
				 "type":"title"
			 },
			{
				"id":"Score6",
				"name":"分数",
				"type":"text",
				"isSum":true,
				"required":true,
				"placeholder": "0~15",
				"verify": {
					"max": "15",
				    "type": "int"
				},
				"value":${pdfn:toJsonString(dailyInfo.Score6)}
			},
			 {
				 "id":"Score7_title",
				 "name":"诊治技能及思维-常见病诊断及鉴别诊断能力；结合病情分析检查报告的能力；辨证施治能力(15分)",
				 "tip":"诊治技能及思维-常见病诊断及鉴别诊断能力；结合病情分析检查报告的能力；辨证施治能力(15分)",
				 "type":"title"
			 },
			{
				"id":"Score7",
				"name":"分数",
				"type":"text",
				"isSum":true,
				"required":true,
				"placeholder": "0~15",
				"verify": {
					"max": "15",
				    "type": "int"
				},
				"value":${pdfn:toJsonString(dailyInfo.Score7)}
			},
			 {
				 "id":"Score8_title",
				 "name":"查房-询问病情，检查病人，汇报病情及疑难问题，归纳上级医师的意见(10分)",
				 "tip":"查房-询问病情，检查病人，汇报病情及疑难问题，归纳上级医师的意见(10分)",
				 "type":"title"
			 },
			{
				"id":"Score8",
				"name":"分数",
				"type":"text",
				"isSum":true,
				"required":true,
				"placeholder": "0~10",
				"verify": {
					"max": "10",
				    "type": "int"
				},
				"value":${pdfn:toJsonString(dailyInfo.Score8)}
			},
			 {
				 "id":"Score9_title",
				 "name":"病房管理-管理病人，处理急、危、重症及突发事件的能力(10分)",
				 "tip":"病房管理-管理病人，处理急、危、重症及突发事件的能力(10分)",
				 "type":"title"
			 },
			{
				"id":"Score9",
				"name":"分数",
				"type":"text",
				"isSum":true,
				"required":true,
				"placeholder": "0~10",
				"verify": {
					"max": "10",
				    "type": "int"
				},
				"value":${pdfn:toJsonString(dailyInfo.Score9)}
			},
             {
	            "id":"t14",
	            "name":"临床教学活动参与情况",
	            "type":"title"
	         },
			 {
				 "id":"Score10_title",
				 "name":"小讲课/教学查房/病例讨论等科室组织的临床教学活动参与情况(10分)",
				 "tip":"小讲课/教学查房/病例讨论等科室组织的临床教学活动参与情况(10分)",
				 "type":"title"
			 },
			{
				"id":"Score10",
				"name":"分数",
				"type":"text",
				"isSum":true,
				"required":true,
				"placeholder": "0~10",
				"verify": {
					"max": "10",
				    "type": "int"
				},
				"value":${pdfn:toJsonString(dailyInfo.Score10)}
			},
	         {
                 "id":"TotalScore",
                 "name":"总分",
                 "type":"text",
                 "readonly":true,
                 "required":true,
				 "verify": {
				 	"max": "100",
				    "type": "int"
				},
				"value":${pdfn:toJsonString(dailyInfo.TotalScore)}
             },
             {
                 "id":"Status",
                 "name":"轮转考核结果",
                 "type":"option",
                 "required":true,
                 "options": [
                     {
                         "optionId": "1",
                         "optionDesc": "通过"
                     },
                     {
                         "optionId": "0",
                         "optionDesc": "不通过"
                     }
                 ],
				"value":${pdfn:toJsonString(dailyInfo.Status)}
             },
			 {
                 "id":"Teacher",
                 "name":"带教签名",
                 "type":"text",
                 "readonly":true,
                 "required":true,
				"value":${pdfn:toJsonString(baseInfo.TecName)}
			 },
			 {
                 "id":"Professer",
                 "name":"科主任签名",
                 "type":"text",
                 "readonly":true,
                 "required":true,
				"value":${pdfn:toJsonString(Professer)}
			 },
             <c:set var="currDate" value="${pdfn:getCurrDate()}"/>
			 {
                 "id":"CheckDate",
                 "name":"考核时间",
                 "type":"text",
                 "readonly":true,
                 "required":true,
				"value":${pdfn:toJsonString(empty dailyInfo.CheckDate ? currDate:dailyInfo.CheckDate)}
			 }
    ]
	</c:if>
}
