
<script type="text/javascript">
    $(document).ready(function(){
        initCheck();
    });
</script>
<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
	<tr>
		<th rowspan="2">培训基地</th>
		<th colspan="3">付费功能</th>
		<th rowspan="2">数据审核区间</th>
		<th rowspan="2">出科考试权限
			<br>
			<input type="checkbox" name="outProcessExam" onclick="choose('outProcessExam','outProcessExams','jsres_org_exam_');"/>
		</th>
		<th rowspan="2">结业理论模拟考核
			<br>
			<input type="checkbox" name="outGraduationExam" onclick="choose('outGraduationExam','outGraduationExams','jsres_org_graduation_exam_');"/>
		</th>
		<th rowspan="2">培训手册
			<br>
			<input type="checkbox" name="schManual" onclick="choose('schManual','schManuals','jsres_org_manual_');"/>
		</th>
	</tr>
	<tr>
		<th>教学活动
			<input type="checkbox" name="activity" onclick="choose('activity','activitys','jsres_org_activity_');"/>
		</th>
		<th>考勤功能
			<input type="checkbox" name="attendance" onclick="choose('attendance','attendances','jsres_org_attendance_');"/>
		</th>
		<th>其他付费功能
			<br>
			<input type="checkbox" name="appMenu" onclick="choose('appMenu','appMenus','jsres_org_app_menu_');"/>
		</th>
	</tr>
	<c:forEach items="${sysOrgList }" var="org">
		<tr>
			<td>${org.orgName }</td>
			<td>
				<c:set var="key1" value="jsres_org_activity_${org.orgFlow}"/>
				<input type="checkbox" id="jsres_org_activity_${org.orgFlow}"
					   name="activitys" value="${org.orgFlow}"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''}
					   onchange="oper(this,'${org.orgFlow}','jsres_org_activity_');"/>
				<c:if test="${pdfn:jsresPowerCfgMap(key1) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key1)}">
					<input type="checkbox" name = "jsres_org_activity_" value="${org.orgFlow}" style="display: none">
				</c:if>
			</td>
				<%-- 考勤功能--%>
			<td>
				<c:set var="key1" value="jsres_org_attendance_${org.orgFlow}"/>
				<input type="checkbox" id="jsres_org_attendance_${org.orgFlow}"
					   name="attendances" value="${org.orgFlow}"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''}
					   onchange="oper(this,'${org.orgFlow}','jsres_org_attendance_');"/>
				<c:if test="${pdfn:jsresPowerCfgMap(key1) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key1)}">
					<input type="checkbox" name = "jsres_org_attendance_" value="${org.orgFlow}" style="display: none">
				</c:if>
			</td>
				<%-- 其他付费功能--%>
			<td>
				<c:set var="key1" value="jsres_org_app_menu_${org.orgFlow}"/>
				<input type="checkbox"  id="jsres_org_app_menu_${org.orgFlow}"
					   name="appMenus" value="${org.orgFlow}"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''}
					   onchange="oper(this,'${org.orgFlow}','jsres_org_app_menu_');"/>
				<c:if test="${pdfn:jsresPowerCfgMap(key1) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key1)}">
					<input type="checkbox" name = "jsres_org_app_menu_" value="${org.orgFlow}" style="display: none">
				</c:if>
			</td>
			<td   <c:if test="${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y and empty param.isQuery}">onclick="changeTime('${org.orgFlow}','jsres_org_data_time_${org.orgFlow}')"</c:if>  id="jsres_org_data_time_${org.orgFlow}">
				<c:set var="cfg" value="${cfgMap[org.orgFlow]}"/>
					${cfg.powerStartTime}<c:if test="${!((empty cfg.powerStartTime) and (empty cfg.powerEndTime))}">至</c:if>${cfg.powerEndTime}
			</td>
			<td>
				<c:set var="key2" value="jsres_org_exam_${org.orgFlow}"/>
				<input type="checkbox" id="jsres_org_exam_${org.orgFlow}"
					   name="outProcessExams" value="${org.orgFlow}" ${pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y?'checked':''}
					   onchange="oper(this,'${org.orgFlow}','jsres_org_exam_');"/>
				<c:if test="${pdfn:jsresPowerCfgMap(key2) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key2)}">
					<input type="checkbox" name = "jsres_org_exam_" value="${org.orgFlow}" style="display: none">
				</c:if>
			</td>
			<td>
				<c:set var="key2" value="jsres_org_graduation_exam_${org.orgFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="jsres_org_graduation_exam_${org.orgFlow}"
					   name="outGraduationExams" value="${org.orgFlow}" ${pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y?'checked':''}
					   onchange="oper(this,'${org.orgFlow}','jsres_org_graduation_exam_');"/>
				<c:if test="${pdfn:jsresPowerCfgMap(key2) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key2)}">
					<input type="checkbox" name = "jsres_org_graduation_exam_" value="${org.orgFlow}" style="display: none">
				</c:if>
			</td>
			<td>
				<c:set var="key3" value="jsres_org_manual_${org.orgFlow}"/>
				<input type="checkbox" id="jsres_org_manual_${org.orgFlow}"
					   name="schManuals" value="${org.orgFlow}" ${pdfn:jsresPowerCfgMap(key3)==GlobalConstant.FLAG_Y?'checked':''}
					   onchange="oper(this,'${org.orgFlow}','jsres_org_manual_');"/>
				<c:if test="${pdfn:jsresPowerCfgMap(key3) == GlobalConstant.FLAG_N or empty pdfn:jsresPowerCfgMap(key3)}">
					<input type="checkbox" name = "jsres_org_manual_" value="${org.orgFlow}" style="display: none">
				</c:if>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty sysOrgList}">
		<tr>
			<td colspan="20" style="border:none;">暂无记录！</td>
		</tr>
	</c:if>
</table>
<div>
	<c:set var="pageView" value="${pdfn:getPageView(sysOrgList)}" scope="request"/>
	<pd:pagination toPage="toPage"/>
</div>