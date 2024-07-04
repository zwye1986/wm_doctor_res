<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"files":[
		 	<c:forEach var="f" items="${files}" varStatus="s">
				{
					"fileFlow":"${f.fileFlow}",
					"fileName":"${f.fileName}",
					<c:set value="${uploadBaseUrl}${f.filePath}" var="filePath"></c:set>
					"filePath":${pdfn:toJsonString(filePath)}
				}
			</c:forEach>
		],
	"logs":[
			{
				"isShow":"Y",
				"statusId":"${kq.auditStatusId eq 'Revoke'? 'Revoke':'Start'}",
				"statusName":"${kq.auditStatusId eq 'Revoke'? '已撤销':'发起审核'}",
				"operUserName":"${kq.doctorName}",
				"operTime":"${pdfn:transDateByPattern(kq.createTime,'yyyy-MM-dd HH:mm')}",
				"auditInfo":""
			}
			<c:if test="${kq.kqTypeId eq 'LeaveType' and kq.auditStatusId ne 'Revoke'}">
				,
				{
					"isShow":"${kq.teacherName eq '-'?'N':'Y'}",
					"statusId":"${ empty kq.teacherAgreeFlag ?'Auditing': kq.teacherAgreeFlag eq 'Y'?'Passed':'UnPassed'}",
					"statusName":"${ empty kq.teacherAgreeFlag ?'待审核': kq.teacherAgreeFlag eq 'Y'?'审核通过':'审核不通过'}",
					"operUserName":"${kq.teacherName}",
					"operTime":"${kq.teacherAuditTime}",
					"auditInfo":${pdfn:toJsonString(kq.teacherAuditInfo)}
				}

				<c:set var="audit" value="false"></c:set>
				<c:if test="${kq.headName ne '-'}">
					<c:if test="${not empty kq.headAgreeFlag}">
						<c:set var="audit" value="true"></c:set>
					</c:if>
					<c:if test="${empty kq.headAgreeFlag}">
						<c:if test="${kq.teacherName eq '-'}">
							<c:set var="audit" value="true"></c:set>
						</c:if>
						<c:if test="${kq.teacherName ne '-'}">
							<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
							<c:if test="${empty kq.teacherAgreeFlag}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
						</c:if>

					</c:if>
				</c:if>
				<c:if test="${audit}">
					,
					{
						"isShow":"${kq.headName eq '-'?'N':'Y'}",
						"statusId":"${ empty kq.headAgreeFlag ?'Auditing': kq.headAgreeFlag eq 'Y'?'Passed':'UnPassed'}",
						"statusName":"${ empty kq.headAgreeFlag ?'待审核': kq.headAgreeFlag eq 'Y'?'审核通过':'审核不通过'}",
						"operUserName":"${kq.headName}",
						"operTime":"${kq.headAuditTime}",
						"auditInfo":${pdfn:toJsonString(kq.headAuditInfo)}
					}
				</c:if>

				<c:set var="audit" value="false"></c:set>
				<c:if test="${kq.tutorName ne '-'}">
					<c:if test="${not empty kq.tutorAgreeFlag}">
						<c:set var="audit" value="true"></c:set>
					</c:if>
					<c:if test="${empty kq.tutorAgreeFlag}">
						<c:if test="${kq.teacherName eq '-' and kq.headName eq '-'  }">
							<c:set var="audit" value="true"></c:set>
						</c:if>
						<c:if test="${kq.teacherName ne '-' and kq.headName eq '-' }">
							<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
							<c:if test="${empty kq.teacherAgreeFlag}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
						</c:if>
						<c:if test="${kq.teacherName eq '-' and kq.headName ne '-' }">
							<c:if test="${not empty kq.headAgreeFlag and kq.headAgreeFlag eq 'Y'}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
							<c:if test="${empty kq.headAgreeFlag}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
						</c:if>
						<c:if test="${kq.teacherName ne '-' and kq.headName ne '-'  }">
							<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
								<c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
									<c:set var="audit" value="true"></c:set>
								</c:if>
								<c:if test="${empty kq.headAgreeFlag}">
									<c:set var="audit" value="true"></c:set>
								</c:if>
							</c:if>
							<c:if test="${empty kq.teacherAgreeFlag}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
						</c:if>
					</c:if>
				</c:if>
				<c:if test="${audit}">
					,
					{
						"isShow":"${kq.tutorName eq '-'?'N':'Y'}",
						"statusId":"${ empty kq.tutorAgreeFlag ?'Auditing': kq.tutorAgreeFlag eq 'Y'?'Passed':'UnPassed'}",
						"statusName":"${ empty kq.tutorAgreeFlag ?'待审核': kq.tutorAgreeFlag eq 'Y'?'审核通过':'审核不通过'}",
						"operUserName":"${kq.tutorName}",
						"operTime":"${kq.tutorAuditTime}",
						"auditInfo":${pdfn:toJsonString(kq.tutorAuditInfo)}
					}
				</c:if>

				<c:set var="audit" value="false"></c:set>
				<c:if test="${kq.managerName ne '-'}">
					<c:if test="${not empty kq.managerAgreeFlag}">
						<c:set var="audit" value="true"></c:set>
					</c:if>
					<c:if test="${empty kq.managerAgreeFlag}">
						<c:if test="${kq.teacherName eq '-' and kq.headName eq '-' and kq.tutorName eq '-'  }">
							<c:set var="audit" value="true"></c:set>
						</c:if>
						<c:if test="${kq.teacherName ne '-' and kq.headName eq '-' and kq.tutorName eq '-'}">
							<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
							<c:if test="${empty kq.teacherAgreeFlag}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
						</c:if>
						<c:if test="${kq.teacherName eq '-' and kq.headName ne '-'  and kq.tutorName eq '-'}">
							<c:if test="${not empty kq.headAgreeFlag and kq.headAgreeFlag eq 'Y'}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
							<c:if test="${empty kq.headAgreeFlag}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
						</c:if>
						<c:if test="${kq.teacherName eq '-' and kq.headName eq '-'  and kq.tutorName ne '-'}">
							<c:if test="${not empty kq.tutorAgreeFlag and kq.tutorAgreeFlag eq 'Y'}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
							<c:if test="${empty kq.tutorAgreeFlag}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
						</c:if>
						<c:if test="${kq.teacherName ne '-' and kq.headName ne '-' and kq.tutorName ne '-'  }">
							<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
								<c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
									<c:if test="${not empty kq.tutorAgreeFlag  and kq.tutorAgreeFlag eq 'Y'}">
										<c:set var="audit" value="true"></c:set>
									</c:if>
									<c:if test="${empty kq.tutorAgreeFlag}">
										<c:set var="audit" value="true"></c:set>
									</c:if>
								</c:if>
								<c:if test="${empty kq.headAgreeFlag}">
									<c:set var="audit" value="true"></c:set>
								</c:if>
							</c:if>
							<c:if test="${empty kq.teacherAgreeFlag}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
						</c:if>
						<c:if test="${kq.teacherName ne '-' and kq.headName ne '-' and kq.tutorName eq '-'  }">
							<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
								<c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
									<c:set var="audit" value="true"></c:set>
								</c:if>
								<c:if test="${empty kq.headAgreeFlag}">
									<c:set var="audit" value="true"></c:set>
								</c:if>
							</c:if>
							<c:if test="${empty kq.teacherAgreeFlag}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
						</c:if>
						<c:if test="${kq.teacherName ne '-' and kq.headName eq '-' and kq.tutorName ne '-'  }">
							<c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
								<c:if test="${not empty kq.tutorAgreeFlag  and kq.tutorAgreeFlag eq 'Y'}">
									<c:set var="audit" value="true"></c:set>
								</c:if>
								<c:if test="${empty kq.tutorAgreeFlag}">
									<c:set var="audit" value="true"></c:set>
								</c:if>
							</c:if>
							<c:if test="${empty kq.teacherAgreeFlag}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
						</c:if>
						<c:if test="${kq.teacherName eq '-' and kq.headName ne '-' and kq.tutorName ne '-'  }">
							<c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
								<c:if test="${not empty kq.tutorAgreeFlag  and kq.tutorAgreeFlag eq 'Y'}">
									<c:set var="audit" value="true"></c:set>
								</c:if>
								<c:if test="${empty kq.tutorAgreeFlag}">
									<c:set var="audit" value="true"></c:set>
								</c:if>
							</c:if>
							<c:if test="${empty kq.headAgreeFlag}">
								<c:set var="audit" value="true"></c:set>
							</c:if>
						</c:if>
					</c:if>
				</c:if>
				<c:if test="${audit}">
					,
					{
						"isShow":"${kq.managerName eq '-'?'N':'Y'}",
						"statusId":"${ empty kq.managerAgreeFlag ?'Auditing': kq.managerAgreeFlag eq 'Y'?'Passed':'UnPassed'}",
						"statusName":"${ empty kq.managerAgreeFlag ?'待审核': kq.managerAgreeFlag eq 'Y'?'审核通过':'审核不通过'}",
						"operUserName":"${kq.managerName}",
						"operTime":"${kq.managerAuditTime}",
						"auditInfo":${pdfn:toJsonString(kq.managerAuditInfo)}
					}
				</c:if>
			</c:if>
			<c:if test="${kq.kqTypeId eq 'AppealType' and kq.auditStatusId ne 'Revoke'}">
				,
				{
					"isShow":"Y",
					"statusId":"${ empty kq.managerAgreeFlag ?'Auditing': kq.managerAgreeFlag eq 'Y'?'Passed':'UnPassed'}",
					"statusName":"${ empty kq.managerAgreeFlag ?'待审核': kq.managerAgreeFlag eq 'Y'?'审核通过':'审核不通过'}",
					"operUserName":"${kq.managerName}",
					"operTime":"${kq.managerAuditTime}",
					"auditInfo":${pdfn:toJsonString(kq.managerAuditInfo)}
				}
			</c:if>
		],
    "data": {
				"recordFlow":"${kq.recordFlow}",
				"subName":"${fn:substring(kq.doctorName , fn:length(kq.doctorName)-2 , fn:length(kq.doctorName))}",
				"doctorName":"${kq.doctorName}",
				"kqTypeId":"${kq.kqTypeId}",
				"kqTypeName":"${kq.kqTypeName}",
				"typeId":"${kq.typeId}",
				"typeName":"${kq.typeName}",
				"startDate":"${kq.startDate}",
				"endDate":"${kq.endDate}",
				"intervalDays":"${kq.intervalDays}",
				"doctorRemarks":"${kq.doctorRemarks}",
				"schDeptName":"${kq.schDeptName}",
				"processFlow":"${kq.processFlow}",
				"submitTime":"${pdfn:transDateTime(kq.createTime)}",
				"auditStatusId":"${kq.auditStatusId}",
				"auditStatusName":"${kq.auditStatusName}"
			}
    </c:if>
}
