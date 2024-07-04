<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"recTypeList":[
				<c:if test="${empty param.dataType}">
				{
					"typeName":"大病历",
					"typeId":"mr"
				},
				{
					"typeName":"病种",
					"typeId":"disease"
				},
				{
					"typeName":"操作技能",
					"typeId":"skill"
				},
				{
					"typeName":"手术",
					"typeId":"operation"
				},
				{
					"typeName":"参加活动",
					"typeId":"activity"
				}
					,
					{
						"typeName":"临床操作技能评估量化表",
						"typeId":"DOPS"
					},
					{
						"typeName":"迷你临床演练评估量化表",
						"typeId":"MINI-CEX"
					},
					{
						"typeName":"出科考核表",
						"typeId":"AfterEvaluation"
					},
					{
						"typeName":"月度考评表",
						"typeId":"MonthEval"
					}
				</c:if>
				<c:if test="${ param.dataType eq 'fiveData'}">
					{
					"typeName":"大病历",
					"typeId":"mr"
					},
					{
					"typeName":"病种",
					"typeId":"disease"
					},
					{
					"typeName":"操作技能",
					"typeId":"skill"
					},
					{
					"typeName":"手术",
					"typeId":"operation"
					},
					{
					"typeName":"参加活动",
					"typeId":"activity"
					}
				</c:if>
				<c:if test="${ param.dataType eq 'dops'}">
					{
						"typeName":"临床操作技能评估量化表",
						"typeId":"DOPS"
					}
				</c:if>
				<c:if test="${ param.dataType eq 'miniCex'}">
					{
						"typeName":"迷你临床演练评估量化表",
						"typeId":"MINI-CEX"
					}
				</c:if>
				<c:if test="${ param.dataType eq 'after'}">
					{
						"typeName":"出科考核表",
						"typeId":"AfterEvaluation"
					}
				</c:if>
				<c:if test="${ param.dataType eq 'docEval'}">
					{
						"typeName":"月度考评表",
						"typeId":"MonthEval"
					}
				</c:if>
		],
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="bean" varStatus="s">
	     	{
				<c:set value="${bean.processFlow}DOPS" var="dopsKey"/>
				<c:set value="${bean.processFlow}Mini_CEX" var="miniKey"/>
				<c:set value="${bean.processFlow}schScore" var="scoreKey"/>
				<c:set var="currDate" value="${pdfn:getCurrDate()}"/>
				<c:set var="headImg" value="${uploadBaseUrl}/${empty bean.userHeadImg?'default.png':bean.userHeadImg}"/>
				"userName":"${bean.userName}",
				"schStartDate":"${bean.schStartDate}",
				"schEndDate":"${bean.schEndDate}",
				"userHeadImg":"${headImg}",
				"schScore": <c:if test="${empty schScoreMap[scoreKey] }">"暂无"</c:if><c:if test="${not empty  schScoreMap[scoreKey]}">"${ schScoreMap[scoreKey]}"</c:if>,
				"docFlow":"${bean.userFlow}",
				"schResultFlow":"${bean.schResultFlow}",
				"processFlow":"${bean.processFlow}",
				"orgFlow":"${bean.orgFlow}",
				"orgName":"${bean.orgName}",
				"deptFlow":"${bean.deptFlow}",
				"deptName":"${bean.deptName}",
				"schDeptFlow":"${bean.schDeptFlow}",
				"schDeptName":"${bean.schDeptName}",
				"afterRecFlow":"${resRecMap[bean.processFlow].recFlow}",
				"afterStatusId":"<c:if test="${empty resRecMap[bean.processFlow]}">notAudit</c:if><c:if test="${not empty resRecMap[bean.processFlow]}">isAudit</c:if>",
				"afterStatusName":"<c:if test="${empty resRecMap[bean.processFlow]}">未审核</c:if><c:if test="${not empty resRecMap[bean.processFlow]}">已审核</c:if>",
				"dopsRecFlow":"${resRecMap[dopsKey].recFlow}",
				"dopsStatusId":"<c:if test="${empty resRecMap[dopsKey]}">notAudit</c:if><c:if test="${not empty resRecMap[dopsKey]}">isAudit</c:if>",
				"dopsStatusName":"<c:if test="${empty resRecMap[dopsKey]}">未审核</c:if><c:if test="${not empty resRecMap[dopsKey]}">已审核</c:if>",
				"miniCexRecFlow":"${resRecMap[miniKey].recFlow}",
				"miniCexStatusId":"<c:if test="${empty resRecMap[miniKey]}">notAudit</c:if><c:if test="${not empty resRecMap[miniKey]}">isAudit</c:if>",
				"miniCexStatusName":"<c:if test="${empty resRecMap[miniKey]}">未审核</c:if><c:if test="${not empty resRecMap[miniKey]}">已审核</c:if>",
				"recordFlow":"${schRotationDeptMap[bean.processFlow].recordFlow}",
				"currDate":"${currDate}",
				"per": ${pdfn:toJsonString(empty bean.per?0:bean.per)},
				<c:if test="${not empty biaoJi}">
					<c:set value="${bean.processFlow}CaseRegistry" var="preTrainMapKey"/>
					<c:set value="${preTrainMapKey}notAudit" var="notAuditKey"/>
					"mrNotAudit":"${resRecCountMap[notAuditKey]}",
					<c:set value="${bean.processFlow}DiseaseRegistry" var="preTrainMapKey"/>
					<c:set value="${preTrainMapKey}notAudit" var="notAuditKey"/>
					"diseaseNotAudit":"${resRecCountMap[notAuditKey]}",
					<c:set value="${bean.processFlow}SkillRegistry" var="preTrainMapKey"/>
					<c:set value="${preTrainMapKey}notAudit" var="notAuditKey"/>
					"skillNotAudit":"${resRecCountMap[notAuditKey]}",
					<c:set value="${bean.processFlow}OperationRegistry" var="preTrainMapKey"/>
					<c:set value="${preTrainMapKey}notAudit" var="notAuditKey"/>
					"operationNotAudit":"${resRecCountMap[notAuditKey]}",
					<c:set value="${bean.processFlow}CampaignRegistry" var="preTrainMapKey"/>
					<c:set value="${preTrainMapKey}notAudit" var="notAuditKey"/>
					"activityNotAudit":"${resRecCountMap[notAuditKey]}",
				</c:if>
				"schType":
						<c:choose>
							<c:when test="${bean.schStartDate<=currDate && !(not empty resRecMap[bean.processFlow] and (empty resRecMap[bean.processFlow].managerAuditUserFlow
							or not empty resRecMap[bean.processFlow].managerAuditUserFlow and not empty resRecMap[bean.processFlow].headAuditStatusId )) }">
								"isCurrent"
							</c:when>
							<c:when test="${not empty resRecMap[bean.processFlow] and (empty resRecMap[bean.processFlow].managerAuditUserFlow
							or not empty resRecMap[bean.processFlow].managerAuditUserFlow and not empty resRecMap[bean.processFlow].headAuditStatusId ) }">
								"isSch"
							</c:when>
							<c:when test="${currDate<bean.schStartDate && (empty resRecMap[bean.processFlow])}">
								"notCurrent"
							</c:when>
						</c:choose>,
				"schStatus":
					<c:choose>
						<c:when test="${bean.schStartDate<=currDate && !(not empty resRecMap[bean.processFlow] and (empty resRecMap[bean.processFlow].managerAuditUserFlow
							or not empty resRecMap[bean.processFlow].managerAuditUserFlow and not empty resRecMap[bean.processFlow].headAuditStatusId )) }">
							"轮转中"
						</c:when>
						<c:when test="${not empty resRecMap[bean.processFlow] and (empty resRecMap[bean.processFlow].managerAuditUserFlow
							or not empty resRecMap[bean.processFlow].managerAuditUserFlow and not empty resRecMap[bean.processFlow].headAuditStatusId ) }">
							"已出科"
						</c:when>
						<c:when test="${currDate<bean.schStartDate && (empty resRecMap[bean.processFlow])}">
							"未入科"
						</c:when>
					</c:choose>

			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}