
<script type="text/javascript">
	$(document).ready(function(){
		initCheck();
	});
</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<tr>
			<th rowspan="2">培训基地</th>
			<th rowspan="2">年级</th>
			<th rowspan="2">姓名</th>
			<th rowspan="2">登录名</th>
			<th rowspan="2">证件号码</th>
			<th rowspan="2">培养年限</th>
			<th rowspan="2">APP登录权限
				<br>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="appLogin"
					   id="appLogin" onclick="choose('appLogin','appLogins','jsres_doctor_app_login_');"/>
			</th>
            <th colspan="3">付费功能
                <%--<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="all" onclick="chooseAll('all','alls','jsres_doctor_all');"/>--%>
            </th>
			<th rowspan="2">数据审核区间
			</th>
			<th rowspan="2">出科考试权限
				<br>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="outProcessExam" onclick="choose('outProcessExam','outProcessExams','jsres_doctor_exam_');"/>
			</th>
			<th rowspan="2">结业理论模拟考核
				<br>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="outGraduationExam" onclick="choose('outGraduationExam','outGraduationExams','jsres_doctor_graduation_exam_');"/>
			</th>
			<th rowspan="2">培训手册
				<br>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="schManual" onclick="choose('schManual','schManuals','jsres_doctor_manual_');"/>
			</th>
			<th rowspan="2">是否提交
				<br>
				<input type="checkbox" name="isSubmitName"/>
			</th>
			<th rowspan="2">审核状态</th>
			<th rowspan="2">开通时间</th>
			<th rowspan="2">审核时间</th>
		</tr>
		<tr>
            <th>教学活动
                <input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="activity" onclick="choose('activity','activitys','jsres_doctor_activity_');"/>
            </th>
            <th>考勤功能
                <input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="attendance" onclick="choose('attendance','attendances','jsres_doctor_attendance_');"/>
            </th>
			<th>其他付费功能
				<br>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="appMenu" onclick="choose('appMenu','appMenus','jsres_doctor_app_menu_');"/>
			</th>
		</tr>
		<c:forEach items="${list }" var="userExt">
		<tr>
			<td>${userExt.orgName }</td>
			<td>${userExt.sessionNumber }</td>
			<td>${userExt.userName }</td>
			<td>${userExt.userCode}</td>
			<td>${userExt.idNo }</td>
			<td>
				<c:choose>
					<c:when test="${userExt.trainingYears eq 'OneYear'}">
						一年
					</c:when>
					<c:when test="${userExt.trainingYears eq 'TwoYear'}">
						两年
					</c:when>
					<c:when test="${userExt.trainingYears eq 'ThreeYear'}">
						三年
					</c:when>
				</c:choose>
			</td>
			<td>
				<c:set var="key" value="jsres_doctor_app_login_${userExt.userFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="jsres_doctor_app_login_${userExt.userFlow}"
					   name="appLogins" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key) == GlobalConstant.FLAG_Y?'checked':''}
					   onchange="oper(this,'${userExt.userFlow }','jsres_doctor_app_login_');"/>
				<c:if test="${pdfn:jsresPowerCfgMap(key) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key)}">
					<input type="checkbox" name = "jsres_doctor_app_login_" value="${userExt.userFlow }" style="display: none">
				</c:if>
			</td>
            <%-- 活动 --%>
            <td>
                <c:set var="key1" value="jsres_doctor_activity_${userExt.userFlow}"/>
                <input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="jsres_doctor_activity_${userExt.userFlow}"
                       name="activitys" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key) != GlobalConstant.FLAG_Y?'disabled':''}  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''}
                       onchange="oper(this,'${userExt.userFlow }','jsres_doctor_activity_');"/>
                <c:if test="${pdfn:jsresPowerCfgMap(key1) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key1)}">
                    <input type="checkbox" name = "jsres_doctor_activity_" value="${userExt.userFlow}" style="display: none">
                </c:if>
            </td>
            <%-- 考勤功能--%>
			<td>
				<c:set var="key1" value="jsres_doctor_attendance_${userExt.userFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="jsres_doctor_attendance_${userExt.userFlow}"
					   name="attendances" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key) != GlobalConstant.FLAG_Y?'disabled':''}  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''}
					   onchange="oper(this,'${userExt.userFlow }','jsres_doctor_attendance_');"/>
				<c:if test="${pdfn:jsresPowerCfgMap(key1) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key1)}">
					<input type="checkbox" name = "jsres_doctor_attendance_" value="${userExt.userFlow }" style="display: none">
				</c:if>
			</td>
            <%-- 其他付费功能--%>
			<td>
				<c:set var="key1" value="jsres_doctor_app_menu_${userExt.userFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="jsres_doctor_app_menu_${userExt.userFlow}"
					   name="appMenus" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key) != GlobalConstant.FLAG_Y?'disabled':''}  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''}
					   onchange="oper(this,'${userExt.userFlow }','jsres_doctor_app_menu_');"/>
				<c:if test="${pdfn:jsresPowerCfgMap(key1) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key1)}">
					<input type="checkbox" name = "jsres_doctor_app_menu_" value="${userExt.userFlow }" style="display: none">
				</c:if>
			</td>
			<td   <c:if test="${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y and empty param.isQuery}">onclick="changeTime('${userExt.userFlow}','jsres_doctor_data_time_${userExt.userFlow}')"</c:if>  id="jsres_doctor_data_time_${userExt.userFlow}">
				<c:set var="cfg" value="${cfgMap[userExt.userFlow]}"/>
				${cfg.powerStartTime}<c:if test="${!((empty cfg.powerStartTime) and (empty cfg.powerEndTime))}">至</c:if>${cfg.powerEndTime}
			</td>
			<td>
				<c:set var="key2" value="jsres_doctor_exam_${userExt.userFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="jsres_doctor_exam_${userExt.userFlow}"
					   name="outProcessExams" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y?'checked':''}
					   onchange="oper(this,'${userExt.userFlow }','jsres_doctor_exam_');"/>
				<c:if test="${pdfn:jsresPowerCfgMap(key2) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key2)}">
					<input type="checkbox" name = "jsres_doctor_exam_" value="${userExt.userFlow }" style="display: none">
				</c:if>
			</td>
			<td>
				<c:set var="key2" value="jsres_doctor_graduation_exam_${userExt.userFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="jsres_doctor_graduation_exam_${userExt.userFlow}"
					   name="outGraduationExams" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y?'checked':''}
					   onchange="oper(this,'${userExt.userFlow }','jsres_doctor_graduation_exam_');"/>
				<c:if test="${pdfn:jsresPowerCfgMap(key2) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key2)}">
					<input type="checkbox" name = "jsres_doctor_graduation_exam_" value="${userExt.userFlow }" style="display: none">
				</c:if>
			</td>
			<td>
				<c:set var="key3" value="jsres_doctor_manual_${userExt.userFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="jsres_doctor_manual_${userExt.userFlow}"
					   name="schManuals" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key3)==GlobalConstant.FLAG_Y?'checked':''}
					   onchange="oper(this,'${userExt.userFlow }','jsres_doctor_manual_');"/>
				<c:if test="${pdfn:jsresPowerCfgMap(key3) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key3)}">
					<input type="checkbox" name = "jsres_doctor_manual_" value="${userExt.userFlow }" style="display: none">
				</c:if>
			</td>
			<td>
				<c:if test="${userExt.isSubmitId eq 'NotSubmit' or empty userExt.isSubmitId }">
					<c:set var="key" value="jsres_doctor_app_login_${userExt.userFlow}"/>
					<c:set var="key1" value="jsres_doctor_app_menu_${userExt.userFlow}"/>
					<c:set var="cfg" value="${cfgMap[userExt.userFlow]}"/>
					<c:set var="key2" value="jsres_doctor_exam_${userExt.userFlow}"/>
					<c:set var="key2" value="jsres_doctor_graduation_exam_${userExt.userFlow}"/>
					<c:set var="key3" value="jsres_doctor_manual_${userExt.userFlow}"/>

					<c:if test="${pdfn:jsresPowerCfgMap(key) == GlobalConstant.FLAG_Y or pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y
						or !((empty cfg.powerStartTime) and (empty cfg.powerEndTime)) or pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y
						or pdfn:jsresPowerCfgMap(key3)==GlobalConstant.FLAG_Y}">
						<input type="button" value="提交" class="" onclick="updateSubmitOne('${userExt.userFlow}')"/>
						<input type="checkbox" id="isSubmitId" name="isSubmitId" value="${userExt.userFlow}" style="display: none"/>
					</c:if>

				</c:if>
				<c:if test="${userExt.isSubmitId eq 'Submit' and userExt.checkStatusId ne 'UnPassed'}">
					已提交
				</c:if>
				<c:if test="${userExt.isSubmitId eq 'Submit' and userExt.checkStatusId eq 'UnPassed'}">
					<input type="button" value="重新提交" class="" onclick="updateSubmitOne('${userExt.userFlow}')"/>
				</c:if>
			</td>
			<td>${userExt.checkStatusName }</td>
			<td>${userExt.submitTime }</td>
			<td>${userExt.checkTime }</td>
		</tr>
		</c:forEach>
		<c:if test="${empty list}">
		<tr>
			<td colspan="20" style="border:none;">暂无记录！</td>
		</tr>
		</c:if>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>