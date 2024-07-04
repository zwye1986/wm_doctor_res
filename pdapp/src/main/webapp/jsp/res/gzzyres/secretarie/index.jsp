<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "inProcess":[
        {
            "id":"Trainee4",
            "name":"实习生",
            "value":${pdfn:toJsonString(empty inProcessCount['4']?0:inProcessCount['4'])}
        },
        {
            "id":"Trainee2",
            "name":"住院医师",
            "value":${pdfn:toJsonString(empty inProcessCount['2']?0:inProcessCount['2'])}
        },
        {
            "id":"Trainee1",
            "name":"专科医生",
            "value":${pdfn:toJsonString(empty inProcessCount['1']?0:inProcessCount['1'])}
        },
        {
            "id":"Trainee5",
            "name":"八年制",
            "value":${pdfn:toJsonString(empty inProcessCount['5']?0:inProcessCount['5'])}
        }
    ] ,
    "outProcess":[
        {
            "id":"Trainee4",
            "name":"实习生",
            "value":${pdfn:toJsonString(empty outProcessCount['4']?0:outProcessCount['4'])}
        },
        {
            "id":"Trainee2",
            "name":"住院医师",
            "value":${pdfn:toJsonString(empty outProcessCount['2']?0:outProcessCount['2'])}
        },
        {
            "id":"Trainee1",
            "name":"专科医生",
            "value":${pdfn:toJsonString(empty outProcessCount['1']?0:outProcessCount['1'])}
        },
        {
            "id":"Trainee5",
            "name":"八年制",
            "value":${pdfn:toJsonString(empty outProcessCount['5']?0:outProcessCount['5'])}
        }
    ],
    "teachPlan":{

    },
    "teachAction":{

    }
    </c:if>
}
