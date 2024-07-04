<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)},
    <c:if test="${resultId eq '200' }">
	    ,
		"tab":[
	        {
	            "id":"deptStudents",
	            "name":"科室学员"
	        }
			<c:if test="${not empty getCycleStudents}">
			,
	        {
	            "id":"getCycleStudents",
	            "name":"轮转学员"
	        }
			</c:if>
			,
	        {
	            "id":"deptTeachers",
	            "name":"带教师资"
	        }
			,
	        {
	            "id":"speUsers",
	            "name":"专业基地主任"
	        }
			,
	        {
	            "id":"deptSecretarys",
	            "name":"教学秘书"
	        }
			<c:if test="${not empty deptHeads}">
			,
	        {
	            "id":"deptHeads",
	            "name":"教学主任"
	        }
			</c:if>
	    ],
		"datas":[

				{
					"id":"deptStudents",
					"values":[
						<c:forEach items="${deptStudents}" var="u" varStatus="status">

						<c:if test="${empty u.userHeadImg}">
							<c:set var="userHeadImg" value=""/>
						</c:if>
						<c:if test="${not empty u.userHeadImg}">
							<c:set var="userHeadImg" value="${uploadBaseUrl}/${u.userHeadImg}"/>
						</c:if>
						{
							"userFlow":${pdfn:toJsonString(u.userFlow)},
							"userName":${pdfn:toJsonString(u.userName)},
							"userHeadImg":${pdfn:toJsonString(userHeadImg)},
							"sessionNumber":${pdfn:toJsonString(u.sessionNumber)},
							"trainingSpeName":${pdfn:toJsonString(u.trainingSpeName)},
							"ringId":${pdfn:toJsonString(u.ringId)},
							"ringName":${pdfn:toJsonString(u.ringName)},
							"userPhone":${pdfn:toJsonString(u.userPhone)}
						}
						<c:if test="${!status.last}">
						,
						</c:if>
						</c:forEach>
					]
				}
				<c:if test="${not empty getCycleStudents}">
				,
				{
					"id":"getCycleStudents",
					"values":[
						<c:forEach items="${getCycleStudents}" var="u" varStatus="status">

						<c:if test="${empty u.userHeadImg}">
							<c:set var="userHeadImg" value=""/>
						</c:if>
						<c:if test="${not empty u.userHeadImg}">
							<c:set var="userHeadImg" value="${uploadBaseUrl}/${u.userHeadImg}"/>
						</c:if>
						{
							"userFlow":${pdfn:toJsonString(u.userFlow)},
							"userName":${pdfn:toJsonString(u.userName)},
							"userHeadImg":${pdfn:toJsonString(userHeadImg)},
							"sessionNumber":${pdfn:toJsonString(u.sessionNumber)},
							"trainingSpeName":${pdfn:toJsonString(u.trainingSpeName)},
							"ringId":${pdfn:toJsonString(u.ringId)},
							"ringName":${pdfn:toJsonString(u.ringName)},
							"userPhone":${pdfn:toJsonString(u.userPhone)}
						}
						<c:if test="${!status.last}">
						,
						</c:if>
						</c:forEach>
					]
				}
				</c:if>
				,
				{
					"id":"deptTeachers",
					"values":[
						<c:forEach items="${deptTeachers}" var="u" varStatus="status">

						<c:if test="${empty u.userHeadImg}">
							<c:set var="userHeadImg" value=""/>
						</c:if>
						<c:if test="${not empty u.userHeadImg}">
							<c:set var="userHeadImg" value="${uploadBaseUrl}/${u.userHeadImg}"/>
						</c:if>
						{
							"userFlow":${pdfn:toJsonString(u.userFlow)},
							"userName":${pdfn:toJsonString(u.userName)},
							"userHeadImg":${pdfn:toJsonString(userHeadImg)},
							"ringId":${pdfn:toJsonString(u.ringId)},
							"ringName":${pdfn:toJsonString(u.ringName)},
							"userPhone":${pdfn:toJsonString(u.userPhone)}
						}
						<c:if test="${!status.last}">
						,
						</c:if>
						</c:forEach>
					]
				}
				,
				{
					"id":"deptSecretarys",
					"values":[
						<c:forEach items="${deptSecretarys}" var="u" varStatus="status">

						<c:if test="${empty u.userHeadImg}">
							<c:set var="userHeadImg" value=""/>
						</c:if>
						<c:if test="${not empty u.userHeadImg}">
							<c:set var="userHeadImg" value="${uploadBaseUrl}/${u.userHeadImg}"/>
						</c:if>
						{
							"userFlow":${pdfn:toJsonString(u.userFlow)},
							"userName":${pdfn:toJsonString(u.userName)},
							"userHeadImg":${pdfn:toJsonString(userHeadImg)},
							"ringId":${pdfn:toJsonString(u.ringId)},
							"ringName":${pdfn:toJsonString(u.ringName)},
							"userPhone":${pdfn:toJsonString(u.userPhone)}
						}
						<c:if test="${!status.last}">
						,
						</c:if>
						</c:forEach>
					]
				}
				,
				{
					"id":"speUsers",
					"values":[
						<c:forEach items="${speUsers}" var="u" varStatus="status">

						<c:if test="${empty u.userHeadImg}">
							<c:set var="userHeadImg" value=""/>
						</c:if>
						<c:if test="${not empty u.userHeadImg}">
							<c:set var="userHeadImg" value="${uploadBaseUrl}/${u.userHeadImg}"/>
						</c:if>
						{
							"userFlow":${pdfn:toJsonString(u.userFlow)},
							"userName":${pdfn:toJsonString(u.userName)},
							"userHeadImg":${pdfn:toJsonString(userHeadImg)},
							"ringId":${pdfn:toJsonString(u.ringId)},
							"ringName":${pdfn:toJsonString(u.ringName)},
							"userPhone":${pdfn:toJsonString(u.userPhone)}
						}
						<c:if test="${!status.last}">
						,
						</c:if>
						</c:forEach>
					]
				}
				<c:if test="${not empty deptHeads}">
				,
				{
					"id":"deptHeads",
					"values":[
						<c:forEach items="${deptHeads}" var="u" varStatus="status">

						<c:if test="${empty u.userHeadImg}">
							<c:set var="userHeadImg" value=""/>
						</c:if>
						<c:if test="${not empty u.userHeadImg}">
							<c:set var="userHeadImg" value="${uploadBaseUrl}/${u.userHeadImg}"/>
						</c:if>
						{
							"userFlow":${pdfn:toJsonString(u.userFlow)},
							"userName":${pdfn:toJsonString(u.userName)},
							"userHeadImg":${pdfn:toJsonString(userHeadImg)},
							"ringId":${pdfn:toJsonString(u.ringId)},
							"ringName":${pdfn:toJsonString(u.ringName)},
							"userPhone":${pdfn:toJsonString(u.userPhone)}
						}
						<c:if test="${!status.last}">
						,
						</c:if>
						</c:forEach>
					]
				}
				</c:if>
		]
    </c:if>
}