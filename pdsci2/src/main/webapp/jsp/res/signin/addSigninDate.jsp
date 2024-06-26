<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
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
<style type="text/css">
	#excelForm tr th{
		text-align: right;
	}
	#excelForm tr td{
		text-align: left;
		padding-left: 100px;
	}
</style>

<script type="text/javascript">
function save(){
	if(false==$("#excelForm").validationEngine("validate")){
		return ;
	}
	var url = "<s:url value='/res/signin/saveSigninDate'/>";
	jboxPost(url,$("#excelForm").serialize(),function(resp){
		if(resp="${GlobalConstant.SAVE_SUCCESSED}"){
			window.parent.frames['mainIframe'].location.reload(true);
		}
	},null,true);
}

</script>
</head>
<body>

<div class="mainright">
   <div class="content">
   <div class="title1 clearfix"></div>
	
	<form id="excelForm"   method="post" enctype="multipart/form-data">

		<table width="100%" class="bs_tb">
			<tr>
				<th width="30%">日期：</th>
				<td>
					<input name="signinDate" type="text" class="xltext ctime validate[required]" style="width: 159px;"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
				</td>
			</tr>
		</table>
		<br/>
		<p style="text-align: center;">
			<input type="button" onclick="save();" value="保存" class="search"/>
		</p>
	</form>
</div>
</div>
</body>
</html>