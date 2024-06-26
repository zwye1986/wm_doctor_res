
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
	function save(){
		var teacherUserName = $("#teacher option:selected").text();
        var url = "<s:url value='/res/teacher/saveChooseTea'/>?teacherUserName="+teacherUserName;
        jboxPost(url,$('#saveForm').serialize(),function(resp){
            if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                //window.parent.frames['mainIframe'].location.reload(true);
                jboxClose();
            }
        },null,true);
	}
</script>
</head>
<body>	
   <div>
      <div class="content">
        <div class="title1 clearfix">
		<form id="saveForm">
   		<table class="basic" width="100%">
      		<tr>
             	<td width="100px;">带教老师：</td>
                <td>
                	<select name="teacherUserFlow" class="validate" id="teacher" style="width: 80%;height: 27px;">
						<option value="">请选择</option>
                		<c:forEach items="${teacherList}" var="user">
                			<option value="${user.userFlow}" <c:if test="${process.teacherUserFlow eq user.userFlow}">selected</c:if>>${user.userName}</option>
                		</c:forEach>
                	</select>
				</td>
             </tr>
         </table>
			<p align="center">
				<input type="hidden" name="resultFlow" value="${param.resultFlow}" />
				<input id="saveBtn" class="search" type="button" value="保&#12288;存"  onclick="save();" />
			</p>
		</form>
         </div>
     </div> 	
   </div>	
</body>
</html>