<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    <c:set var="isRead" value="${regist.statusId eq 'Passing' or empty regist}"></c:set>
    "action":{
          <c:if test="${!isRead}">
               "save":"保存"
          </c:if>
    },
    "audit":{
          "statusId":${pdfn:toJsonString(regist.statusId)},
          "statusName":${pdfn:toJsonString(regist.statusName)}
    },
    "inputs": [
          {
                "inputId": "userFlow",
                "label": "用户标识符",
                "required": true,
                "inputType": "text",
                "isHidden": true,
                "readonly":${isRead},
                "value":${pdfn:toJsonString(user.userFlow)}
          },
          <c:if test="${empty user.userHeadImg}">
                <c:set var="userHeadImg" value=""/>
          </c:if>
          <c:if test="${not empty user.userHeadImg}">
                <c:set var="userHeadImg" value="${uploadBaseUrl}/${user.userHeadImg}"/>
          </c:if>
          {
                "inputId": "headImg",
                "label": "头&#12288;&#12288;像：",
                "required": true,
                "inputType": "img",
                "isHidden": false,
                "readonly":${isRead},
                "value":${pdfn:toJsonString(userHeadImg)}
          },
          {
                "inputId": "userName",
                "label": "姓&#12288;&#12288;名：",
                "required": true,
                "inputType": "text",
                "isHidden": false,
                "value":${pdfn:toJsonString(user.userName)}
          },
          {
                "inputId": "sexId",
                "label": "性&#12288;&#12288;别：",
                "required": true,
                "inputType": "radio",
                "isHidden": false,
                "options": [
                      {
                            "optionId": "Man",
                            "optionDesc": "男"
                      },
                      {
                            "optionId": "Woman",
                            "optionDesc": "女"
                      }
                ],
                "readonly":${isRead},
                "value":${pdfn:toJsonString(user.sexId)}
          },
          {
                "inputId": "cretTypeId",
                "label": "证件类型：",
                "required": true,
                "inputType": "select",
                "isHidden": false,
                "options": [
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
                "readonly":${isRead},
                "value":${pdfn:toJsonString(user.cretTypeId)}
          },
          {
                "inputId": "idNo",
                "label": "证件号码：",
                "required": true,
                "inputType": "text",
                "isHidden": false,
                "readonly":${isRead},
                "value":${pdfn:toJsonString(user.idNo)}
          },
          {
                "inputId": "userEmail",
                "label": "注册邮箱：",
                "required": true,
                "inputType": "text",
                "isHidden": false,
                "readonly":${isRead},
                "value":${pdfn:toJsonString(user.userEmail)}
          },
          {
                "inputId": "userPhone",
                "label": "手机号码：",
                "required": true,
                "inputType": "text",
                "isHidden": false,
                "value":${pdfn:toJsonString(user.userPhone)}
          },
          {
                "inputId": "trainingTypeId",
                "label": "培训类型：",
                "required": true,
                "inputType": "select",
                "isHidden": false,
                "options": [
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
                "readonly":${isRead},
                "value":${pdfn:toJsonString(doctor.trainingTypeId)}
          },
          <c:set var="key" value="${doctor.trainingTypeId}"></c:set>
          <c:set var="dicts2" value="${dictsMap[key]}"></c:set>
          {
                "inputId": "trainingSpeId",
                "label": "培训专业：",
                "required": true,
                "inputType": "select",
                "isHidden": false,
                "options": [
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
                "readonly":${isRead},
                "value":${pdfn:toJsonString(doctor.trainingSpeId)}
          },
          {
              "inputId":"sessionNumber",
              "label":"年&#12288;&#12288;级：",
              "required":true,
              "inputType":"date",
              "isHidden": false,
              "verify": {
                  "dateFmt": "yyyy",
                  "type": "date"
              },
              "value":${pdfn:toJsonString(doctor.sessionNumber)}
          },
          {
                "inputId": "orgFlow",
                "label": "培训基地：",
                "required": true,
                "inputType": "select",
                "isHidden": false,
                "options": [
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
                "readonly":${isRead},
                "value":${pdfn:toJsonString(user.orgFlow)}
          },
          {
                "inputId": "trainingYears",
                "label": "培养年限：",
                "required": true,
                "inputType": "select",
                "isHidden": false,
                "options": [
                    <c:forEach items="${years}" var="data" varStatus="s">
                        {
                            "optionId": ${pdfn:toJsonString(data.dictId)},
                            "optionDesc": ${pdfn:toJsonString(data.dictName)}
                        }
                        <c:if test="${not s.last }">
                            ,
                        </c:if>
                    </c:forEach>
                ],
                "readonly":${isRead},
                "value":${pdfn:toJsonString(doctor.trainingYears)}
          },
          {
                "inputId": "graduationYear",
                "label": "结业年份：",
                "required": true,
                "readonly": true,
                "inputType": "text",
                "isHidden": false,
                "value":${pdfn:toJsonString(doctor.graduationYear)}
          },
          {
                "inputId": "workOrgName",
                "label": "所在单位：",
                "required": false,
                "inputType": "text",
                "isHidden": false,
                "readonly":${isRead},
                "value":${pdfn:toJsonString(doctor.workOrgName)}
          }
    ]
    </c:if>
}
