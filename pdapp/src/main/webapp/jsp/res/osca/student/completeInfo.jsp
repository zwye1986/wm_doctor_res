<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,

        <c:if test="${empty user.userHeadImg}">
            <c:set var="userHeadImg" value=""/>
        </c:if>
        <c:if test="${not empty user.userHeadImg}">
            <c:set var="userHeadImg" value="${uploadBaseUrl}/${user.userHeadImg}"/>
        </c:if>
        <c:set var="isRead" value="${regist.statusId eq 'Passing'or regist.statusId eq 'Passed'}"></c:set>
    "data":{
             "userFlow":${pdfn:toJsonString(user.userFlow)},
             "userName":${pdfn:toJsonString(user.userName)},
             "sexId":${pdfn:toJsonString(user.sexId)},
             "sexName":${pdfn:toJsonString(user.sexName)},
             "headImg":${pdfn:toJsonString(userHeadImg)},
             "statusId":${pdfn:toJsonString(regist.statusId)},
             "statusName":${pdfn:toJsonString(regist.statusName)},
             "statusRemark":${pdfn:toJsonString(regist.statusRemark)},
             "cretTypeId":${pdfn:toJsonString(user.cretTypeId)},
             "cretTypeName":${pdfn:toJsonString(user.cretTypeName)},
             "idNo":${pdfn:toJsonString(user.idNo)},
             "userEmail":${pdfn:toJsonString(user.userEmail)},
             "userPhone":${pdfn:toJsonString(user.userPhone)},
             "trainingTypeId":${pdfn:toJsonString(doctor.trainingTypeId)},
             "trainingTypeName":${pdfn:toJsonString(doctor.trainingTypeName)},
             "trainingSpeId":${pdfn:toJsonString(doctor.trainingSpeId)},
             "trainingSpeName":${pdfn:toJsonString(doctor.trainingSpeName)},
             "sessionNumber":${pdfn:toJsonString(doctor.sessionNumber)},
             "trainingYears":${pdfn:toJsonString(doctor.trainingYears)},
             "trainingYearsName":${pdfn:toJsonString(trainingYears)},
             "graduationYear":${pdfn:toJsonString(doctor.graduationYear)},
             "workOrgName":${pdfn:toJsonString(doctor.workOrgName)},
             "orgFlow":${pdfn:toJsonString(user.orgFlow)},
             "orgName":${pdfn:toJsonString(user.orgName)},
             "readonly":${isRead},
            <c:if test="${!isRead}">
                "action":"save"
            </c:if>
            <c:if test="${isRead}">
                "action":""
            </c:if>
        },
        "sex":[

            {
                "optionId": "Man",
                "optionDesc": "男"
            },
            {
                "optionId": "Woman",
                "optionDesc": "女"
            }
        ],
        "cretTypeId": [
            <c:forEach items="${types}" var="data" varStatus="s">
                {
                    "optionId": ${pdfn:toJsonString(data.id)},
                    "optionDesc": ${pdfn:toJsonString(data.name)}
                }
                <c:if test="${not s.last }">
                    ,
                </c:if>
            </c:forEach>
        ],
        "trainingTypeId": [
                <c:forEach items="${dicts}" var="data" varStatus="s">
                       {
                           "optionId": ${pdfn:toJsonString(data.dictId)},
                           "optionDesc": ${pdfn:toJsonString(data.dictName)},
                           <c:set var="key" value="${data.dictId}"></c:set>
                           <c:set var="dicts2" value="${dictsMap[key]}"></c:set>
                           "items":[
                               <c:forEach items="${dicts2}" var="data2" varStatus="s2">
                                   {
                                       "optionId": ${pdfn:toJsonString(data2.dictId)},
                                       "optionDesc": ${pdfn:toJsonString(data2.dictName)}
                                   }
                                   <c:if test="${not s2.last }">
                                       ,
                                   </c:if>
                               </c:forEach>
                             ]
                       }
                       <c:if test="${not s.last }">
                           ,
                       </c:if>
                </c:forEach>
        ],
        <c:set var="key" value="${doctor.trainingTypeId}"></c:set>
        <c:set var="dicts2" value="${dictsMap[key]}"></c:set>
        "trainingSpeId": [
                <c:forEach items="${dicts2}" var="data" varStatus="s">
                        {
                            "optionId": ${pdfn:toJsonString(data.dictId)},
                            "optionDesc": ${pdfn:toJsonString(data.dictName)}
                        }
                        <c:if test="${not s.last }">
                            ,
                        </c:if>
                </c:forEach>
        ],
        "orgs": [
            <c:forEach items="${orgList}" var="data" varStatus="s">
                {
                    "optionId": ${pdfn:toJsonString(data.orgFlow)},
                    "optionDesc": ${pdfn:toJsonString(data.orgName)}
                }
                <c:if test="${not s.last }">
                    ,
                </c:if>
            </c:forEach>
        ],
        "years": [
            <c:forEach items="${years}" var="data" varStatus="s">
                {
                    "optionId": ${pdfn:toJsonString(data.dictId)},
                    "optionDesc": ${pdfn:toJsonString(data.dictName)}
                }
                <c:if test="${not s.last }">
                    ,
                </c:if>
            </c:forEach>
        ]
    </c:if>
}
