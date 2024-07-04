<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "inProcess":{
        "Trainee4":${pdfn:toJsonString(empty inProcessCount['4']?0:inProcessCount['4'])},
        "Trainee2":${pdfn:toJsonString(empty inProcessCount['2']?0:inProcessCount['2'])},
        "Trainee1":${pdfn:toJsonString(empty inProcessCount['1']?0:inProcessCount['1'])},
        "Trainee5":${pdfn:toJsonString(empty inProcessCount['5']?0:inProcessCount['5'])}
    } ,
    "outProcess":{
        "Trainee4":${pdfn:toJsonString(empty outProcessCount['4']?0:outProcessCount['4'])},
        "Trainee2":${pdfn:toJsonString(empty outProcessCount['2']?0:outProcessCount['2'])},
        "Trainee1":${pdfn:toJsonString(empty outProcessCount['1']?0:outProcessCount['1'])},
        "Trainee5":${pdfn:toJsonString(empty outProcessCount['5']?0:outProcessCount['5'])}
    },
    "teachPlan":{

    },
    "teachAction":{

    }
    </c:if>
}
