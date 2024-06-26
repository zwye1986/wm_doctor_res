
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>

<script type="text/javascript">
	function choose(roleFlow,id) {
		var url = "<s:url value='/res/doc/chooseTeacherOrHead'/>?roleFlow="+roleFlow+"&resultFlow=${param.resultFlow}&id="+id;
		jboxOpen(url,"选择用户",900,window.screen.height*0.7);
	}
	function save(){
		var $form = $("#saveForm");
		if($form.validationEngine("validate")){
			$('#saveBtn').hide();
			var url = "<s:url value='/res/doc/saveChoose'/>";
			var requestData = $form.serialize();
			jboxPost(url,requestData,function(resp){
				if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
					<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
						window.parent.frames["mainIframe"].changeDept('${param.schDeptFlow}');
						return top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
					</c:if>
					window.parent.frames["mainIframe"].window.location.href="<s:url value='/res/doc/rotationDetail'/>?resultFlow=${param.resultFlow}&rotationFlow=${param.rotationFlow}&schDeptFlow=${param.schDeptFlow}&roleFlag=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}";
				}
			},null,true);
		}
	}

	function getTeacherType(userFlow) {
        //document.getElementById('teacherTypeId').value = $("#teacherType").val();
		//console.log(userFlow);
        var url = '<s:url value="/res/doc/getUserType"/>';
        jboxPost(url,{userFlow:userFlow},function(resp){
            if(resp){
                console.log(resp);
                document.getElementById('teacherTypeId').value = resp.teacherTypeName;
			}
		},null,false);
    }
</script>
</head>
<body>	
   <div class="mainright">
      <div class="content">
        <div class="title1 clearfix">
		<form id="saveForm">
   		<table class="basic" width="100%">
      		<tr>
             	<td width="100px;">教学主任：</td>
                <td>
                	<select name="headUserFlow"  class="validate[required]" style="width: 80%;height: 27px;">
                		<option value="">请选择</option>
                		<c:forEach items="${headList}" var="user">
                			<option value="${user.userFlow}" <c:if test="${param.headUserFlow eq user.userFlow}">selected</c:if>>${user.userName}</option>
                		</c:forEach>
                	</select>
				</td>
             </tr>
      		<tr>
             	<td width="100px;">带教老师：</td>
                <td>
                	<select id="teacherUserFlow" name="teacherUserFlow" class="validate[required]" style="width: 80%;height: 27px;" onchange="getTeacherType(this.value);">
						<option value="">请选择</option>
                		<c:forEach items="${teacherList}" var="user">
                			<option value="${user.userFlow}">${user.userName}</option>
                		</c:forEach>
                	</select>
				</td>
             </tr>
			<tr>
				<td width="100px;">带教类型：</td>
				<td>
					<input type="text" id="teacherTypeId" name="teacherTypeId" style="width: 195px;height: 23px;" readonly="readonly">
				</td>
			</tr>
			<c:if test="${sysCfgMap['Chinese_Western'] eq GlobalConstant.Chinese}">
			<tr>
				<td width="100px;">跟师时间：</td>
				<td>
					<input type="text" name="discipleStartDate" id="discipleStartDate" value="" readonly="readonly"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 40%;height: 23px;"> ~
					<input type="text" name="discipleEndDate" id="discipleEndDate" value="" readonly="readonly"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 40%;height: 23px;">
				</td>
			</tr>
			</c:if>
         </table>
			<p align="center">
				<input type="hidden" name="resultFlow" value="${param.resultFlow}" /> 
				<input type="hidden" name="preResultFlow" value="${param.preResultFlow}" /> 
				<input id="saveBtn" class="search" type="button" value="保&#12288;存"  onclick="save();" />
			</p>
		</form>
         </div>
        
     </div> 	
   </div>	
</body>
</html>