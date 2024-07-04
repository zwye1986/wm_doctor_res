<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
	"codeType": ${pdfn:toJsonString(codeType)},
	"activityCodeTime":15,
	"activityType":[
			{
				"activityTypeId":"1",
				"activityTypeName":"教学查房",
				"qty":"${activityTypeMap['1']+0}"
			},
			{
				"activityTypeId":"11",
				"activityTypeName":"教学病例讨论",
				"qty":"${activityTypeMap['11']+0}"
			},
			{
				"activityTypeId":"2",
				"activityTypeName":"疑难病例讨论",
				"qty":"${activityTypeMap['2']+0}"
			},
			{
				"activityTypeId":"3",
				"activityTypeName":"危重病例讨论",
				"qty":"${activityTypeMap['3']+0}"
			},
			{
				"activityTypeId":"5",
				"activityTypeName":"死亡病例讨论",
				"qty":"${activityTypeMap['5']+0}"
			},
<%--			{--%>
<%--				"activityTypeId":"3",--%>
<%--				"activityTypeName":"危重病例讨论",--%>
<%--				"qty":"${activityTypeMap['3']+0}"--%>
<%--			},--%>
			{
				"activityTypeId":"4",
				"activityTypeName":"临床小讲课",
				"qty":"${activityTypeMap['4']+0}"
			},
<%--			{--%>
<%--				"activityTypeId":"5",--%>
<%--				"activityTypeName":"死亡病例讨论",--%>
<%--				"qty":"${activityTypeMap['5']+0}"--%>
<%--			},--%>
			{
				"activityTypeId":"6",
				"activityTypeName":"入轮转科室教育",
				"qty":"${activityTypeMap['6']+0}"
			},
			{
				"activityTypeId":"7",
				"activityTypeName":"出科考核",
				"qty":"${activityTypeMap['7']+0}"
			},
			{
				"activityTypeId":"8",
				"activityTypeName":"技能培训",
				"qty":"${activityTypeMap['8']+0}"
			},
			{
				"activityTypeId":"9",
				"activityTypeName":"教学阅片",
				"qty":"${activityTypeMap['9']+0}"
			},
			{
				"activityTypeId":"10",
				"activityTypeName":"门诊教学",
				"qty":"${activityTypeMap['10']+0}"
			},
<%--			{--%>
<%--				"activityTypeId":"11",--%>
<%--				"activityTypeName":"教学病例讨论",--%>
<%--				"qty":"${activityTypeMap['11']+0}"--%>
<%--			},--%>
			{
				"activityTypeId":"12",
				"activityTypeName":"临床操作技能床旁教学",
				"qty":"${activityTypeMap['12']+0}"
			},
			{
				"activityTypeId":"13",
				"activityTypeName":"住院病历书写指导教学",
				"qty":"${activityTypeMap['13']+0}"
			},
			{
				"activityTypeId":"14",
				"activityTypeName":"手术操作指导教学",
				"qty":"${activityTypeMap['14']+0}"
			},
			{
				"activityTypeId":"15",
				"activityTypeName":"影像诊断报告书写指导教学",
				"qty":"${activityTypeMap['15']+0}"
			},
			{
				"activityTypeId":"16",
				"activityTypeName":"临床文献研读会",
				"qty":"${activityTypeMap['16']+0}"
			},
			{
				"activityTypeId":"17",
				"activityTypeName":"入院教育",
				"qty":"${activityTypeMap['17']+0}"
			},
			{
				"activityTypeId":"18",
				"activityTypeName":"入专业基地教育",
				"qty":"${activityTypeMap['18']+0}"
			} ,
			{
				"activityTypeId":"19",
				"activityTypeName":"晨间报告",
				"qty":"${activityTypeMap['19']+0}"
			}
			,
			{
				"activityTypeId":"20",
				"activityTypeName":"报告单分析",
				"qty":"${activityTypeMap['20']+0}"
			}
			,
			{
				"activityTypeId":"21",
				"activityTypeName":"教学上机",
				"qty":"${activityTypeMap['21']+0}"
			}
			,
			{
				"activityTypeId":"22",
				"activityTypeName":"上机演示",
				"qty":"${activityTypeMap['22']+0}"
			}
		],
    "datas": [
		<c:forEach items="${list}" var="b" varStatus="s">
	     	{
				"activityFlow":"${b.activityFlow}",
				"speakerFlow":"${b.speakerFlow}",
				"activityName":"${b.activityName}",
				"activityTypeName":"${b.activityTypeName}",
				"activityAddress":"${b.activityAddress}",
				"activityRemark":"${b.activityRemark}",
				"activityStatus":"${b.activityStatus}",
				"audit":"${b.audit}",
				"opinion":"${b.opinion}",
				"resultFlow":"${b.resultFlow}",
				"evalScore":"${b.evalScore+0}",
				"userName":"${b.userName}",
				"deptName":"${b.deptName}",
				"startTime":"${b.startTime}",
				"endTime":"${b.endTime}",
				"fileFlow":"${b.fileFlow}",
				"fileName":"${b.fileName}",
				"isRegiest":"${b.isRegiest}",
				"isScan":"${b.isScan}",
				"isScan2":"${b.isScan2}",
				<c:set var="qrCode1" value="func://funcFlow=activitySigin&activityFlow=${b.activityFlow}"></c:set>
				<c:set var="qrCode2" value="func://funcFlow=activityOutSigin&activityFlow=${b.activityFlow}"></c:set>
				"qrCode1":${pdfn:toJsonString(qrCode1)},
				"qrCode2":${pdfn:toJsonString(qrCode2)},
				"action":[
			        <c:if test="${(fn:length(resultMap[b.activityFlow])+0)<=0}">
						{
							"id":"del",
							"name":"删除"
						}
					</c:if>
				]

			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
