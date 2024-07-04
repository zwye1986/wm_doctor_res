<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount":${dataCount}
    <c:if test="${resultId eq '200' }">
    ,
        <c:if test='${param.roleId eq "Trainee"}'>
        "noticeList":[
        	<c:forEach items="${schPlansToDoIn}" var="schPlan" varStatus="status">
	     	{
	     		"schDeptFlow":"${schPlan.schDeptFlow}",
	     		"schDeptName":"${schPlan.schDeptName}",
	     		"schStatusId":"${schPlan.schStatusId}",
				"dataFlow":"",
				"dataName":"",
                "content":"需要阅读${schPlan.schDeptName}的入科教育文档。",
                "funcTypeId":"dataInput11",
                "funcFlow":"0001",
                "cataFlow":"0"
			}
			<c:if test="${not status.last}">
				,
			</c:if>
     	</c:forEach>
     	<c:if test="${schPlansToDoIn.size()>0 and schPlansToDoOut.size()>0}">
     	,
     	</c:if>
     	<c:forEach items="${schPlansToDoOut}" var="schPlan" varStatus="status">
	     	{
	     		"schDeptFlow":"${schPlan.schDeptFlow}",
	     		"schDeptName":"${schPlan.schDeptName}",
	     		"schStatusId":"${schPlan.schStatusId}",
				"dataFlow":"",
				"dataName":"",
                "content":"${schPlan.schDeptName}即将出科,请填写出科自我鉴定。",
                "funcTypeId":"dataInput11",
                <c:if test='${user.roleFlow == 4}'>
			    	"funcFlow":"0007",
				</c:if>
				<c:if test='${user.roleFlow != 4}'>
			    	"funcFlow":"D005",
				</c:if>
                "cataFlow":"0"
			}
			<c:if test="${not status.last}">
				,
			</c:if>
		</c:forEach>
		
		<c:if test="${schPlansToDoIn.size() + schPlansToDoOut.size()>0 and activitysNotEntered.size()>0}">
     	,
     	</c:if>
     	<c:forEach items="${activitysNotEntered}" var="activity" varStatus="status">
	     	{
	     		"schDeptFlow":"${activity.schDeptFlow}",
	     		"schDeptName":"${activity.schDeptName}",
				"dataFlow":"${activity.dataFlow}",
				"dataName":"${activity.name}",
                "content":"${activity.name}即将开始,是否需要参加。",
                "funcTypeId":"dataInput1N",
                "funcFlow":"0004",
                "cataFlow":"0"
			}
			<c:if test="${not status.last}">
				,
			</c:if>
		</c:forEach>
		
		<c:if test="${schPlansToDoIn.size()+ schPlansToDoOut.size()+ activitysNotEntered.size()>0 and activitysNotScore.size()>0}">
     	,
     	</c:if>
     	<c:forEach items="${activitysNotScore}" var="activity" varStatus="status">
	     	{
	     		"schDeptFlow":"${activity.schDeptFlow}",
	     		"schDeptName":"${activity.schDeptName}",
				"dataFlow":"${activity.dataFlow}",
				"dataName":"${activity.name}",
                "content":"${activity.name}活动结束,请及时填写评分。",
                "funcTypeId":"dataInput1N",
                "funcFlow":"0004",
                "cataFlow":"0"
			}
			<c:if test="${not status.last}">
				,
			</c:if>
		</c:forEach>
		<c:if test="${schPlansToDoIn.size()+ schPlansToDoOut.size()+ activitysNotEntered.size()+activitysNotScore.size()>0 and outDopsList.size()>0}">
     	,
     	</c:if>
     	<c:forEach items="${outDopsList}" var="outDops" varStatus="status">
	     	{
	     		"schDeptFlow":"${outDops.schDeptFlow}",
	     		"schDeptName":"${outDops.schDeptName}",
	     		"schStatusId":"${outDops.schStatusId}",
				"dataFlow":"${outDops.dataFlow}",
				"dataName":"${outDops.schDeptName}的DOPS评估表",
                "content":"${outDops.schDeptName}的DOPS评估表老师已打分,请及时填写评分。",
                "funcTypeId":"dataInput11",
                "funcFlow":"0008",
                "cataFlow":"0"
			}
			<c:if test="${not status.last}">
				,
			</c:if>
		</c:forEach>
		<c:if test="${schPlansToDoIn.size()+ schPlansToDoOut.size()+ activitysNotEntered.size()+activitysNotScore.size()+outDopsList.size()>0 and outMiniCexList.size()>0}">
     	,
     	</c:if>
     	<c:forEach items="${outMiniCexList}" var="outMiniCex" varStatus="status">
	     	{
	     		"schDeptFlow":"${outMiniCex.schDeptFlow}",
	     		"schDeptName":"${outMiniCex.schDeptName}",
	     		"schStatusId":"${outMiniCex.schStatusId}",
				"dataFlow":"${outMiniCex.dataFlow}",
				"dataName":"${outMiniCex.schDeptName}的Mini-CEX评估表",
                "content":"${outMiniCex.schDeptName}的Mini-CEX评估表老师已打分,请及时填写评分。",
                "funcTypeId":"dataInput11",
                "funcFlow":"0009",
                "cataFlow":"0"
			}
			<c:if test="${not status.last}">
				,
			</c:if>
		</c:forEach>
        ]
        </c:if>
        <c:if test='${param.roleId eq "Teacher"}'>
            "noticeList":[
	            <c:forEach items="${notEnteredDoctotList}" var="doctor" varStatus="status">
		     	{
		     		"doctorFlow":"${doctor.UCSID}",
		     		"doctorName":"${doctor.TrueName}",
		     		"studentFlow":"${doctor.DoctorFlow}",
					"dataFlow":"",
					"dataName":"",
	                "content":"${doctor.TrueName}学员需要进行入科确认操作。",
	                "funcTypeId":"dataInput11",
	                "funcFlow":"0001",
	                "cataFlow":"0"
				}
				<c:if test="${not status.last}">
					,
				</c:if>
	     	</c:forEach>
	     	<c:if test="${notEnteredDoctotList.size()>0 and waitEvalOutSecBriefList.size()>0}">
	     	,
	     	</c:if>
	     	<c:forEach items="${waitEvalOutSecBriefList}" var="outSecBrief" varStatus="status">
		     	{
		     		"doctorFlow":"${outSecBrief.UCSID}",
		     		"doctorName":"${outSecBrief.TrueName}",
					"studentFlow":"${outSecBrief.DoctorFlow}",
					"dataFlow":"",
					"dataName":"",
	                "content":"${outSecBrief.TrueName} 已完成自我鉴定,请及时出科审核",
	                "funcTypeId":"dataInput11",
	                "funcFlow":"0004",
	                "cataFlow":"0"
				}
				<c:if test="${not status.last}">
					,
				</c:if>
			</c:forEach>
			<c:if test="${notEnteredDoctotList.size() + waitEvalOutSecBriefList.size()>0 and waitArrangeActivityCount.arrangeCount>0}">
	     	,
	     	{
	     		"doctorFlow":"",
	     		"doctorName":"",
				"dataFlow":"",
				"dataName":"",
                "content":"${waitArrangeActivityCount.arrangeCount} 个教学活动需要安排，请到电脑上上传活动资料！",
                "funcTypeId":"dataInput11",
                "funcFlow":"0004",
                "cataFlow":"0"
			}
	     	</c:if>
            ]
        </c:if>
    </c:if>
}
