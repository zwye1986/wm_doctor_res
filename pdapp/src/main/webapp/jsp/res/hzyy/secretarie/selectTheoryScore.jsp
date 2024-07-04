<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
		<c:if test="${!read}">
			,
			"action":{
				"save":"保存"
			}
		</c:if>
        ,"datas":[

                {
                    "inputId":"TheoryScore",
                    "inputType":"text",
                    "label":"出科理论成绩",
                    "readonly":${read},
                    "required":true,
					"placeholder": "0~100",
					"verify": {
						"max": "100",
						"type": "float"
					},
                    "value":${pdfn:toJsonString(outSecBrief.TheoryScore )}
                }
        ]
	</c:if>
}