<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount":100,
	"datas":[
		{
			"dataFlow":"123",
			"dataType":"mr",
			"title":"大病历",
			"content":[
				{
					"label":"病人姓名",
					"value":"张三"
				},
				{
					"label":"病历号",
					"value":"123456"
				}
				,
				{
					"label":"疾病名称",
					"value":"高血压"
				}
			]
		},
		{
			"dataFlow":"123456",
			"dataType":"skill",
			"title":"操作技能",
			"content":[
				{
					"label":"操作日期",
					"value":"2015-12-21"
				},
				{
					"label":"操作名称",
					"value":"静脉注射"
				}
				,
				{
					"label":"成功",
					"value":"是"
				}
			]
		}
	]
  </c:if>
}