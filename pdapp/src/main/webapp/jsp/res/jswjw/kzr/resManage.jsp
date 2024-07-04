<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"datas":[
				{
					"a":"1",
					"typeName":"本月轮转学员",
					"typeId":"monthCurrent",
                    "qty":"${monthCurrent+0}"
				},
				{
					"a":"2",
					"typeName":"本月计划出科学员",
					"typeId":"monthSch",
                    "qty":"${monthSch+0}"
				},
				{
					"a":"3",
					"typeName":"计划入科学员",
					"typeId":"waitSch",
                    "qty":"${waitSch+0}"
				},
				{
					"a":"4",
					"typeName":"逾期未出科学员",
					"typeId":"errorSch",
                    "qty":"${errorSch+0}"
				}
		]
    </c:if>
}
