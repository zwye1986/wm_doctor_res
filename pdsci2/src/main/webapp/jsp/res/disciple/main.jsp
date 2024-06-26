<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	.divItems{
		width:214px;
		min-width:214px;
		height: 178px;
		min-height: 178px;
		border: 1px solid #e3e3e3;
		float: left;
		margin-left: 150px;
		text-align: center;
		margin-top: 44px;
		cursor:pointer;
	}
	.zhezhao{
		background-color: #9f9f9f;
		z-index:1001;
		-moz-opacity: 0.7;
		opacity:.70;  filter: alpha(opacity=70);
		text-align: center;
	}
	.divMain{
		width:100%;
		min-width: 100%;
		margin-top: 44px;
		height: 178px;
		min-height: 178px;
		border: 0px solid grey;
		text-align: center;
	 }
	/*.div110{*/
		/*cursor:pointer;*/
	/*}*/
	.divImg110{
		width:110px;
		min-width:110px;
		height: 110px;
		min-height: 110px;
		margin-top: 36px;
	}
	.divImg126{
		width:126px;
		min-width:126px;
		height: 126px;
		min-height: 126px;
		margin-top: 26px;
	}
	.div126{
		display: none;
	}
	.span{
		position: relative;
		top: -80px;
		font-size: 18px;
		color: #fff;
		display: none;
	}
	.span1{
		font-size: 14px;
		color: #000;
	}
</style>
<script type="text/javascript">
	$(function(){
		//鼠标的移入移出
		$(".divItems").mouseover(function (){
			$(this).find(".div100").hide();
			$(this).find(".div126").show();
			$(this).find(".span").show();
			$(this).addClass("zhezhao");
		}).mouseout(function (){
			$(this).find(".div100").show();
			$(this).find(".div126").hide();
			$(this).find(".span").hide();
			$(this).removeClass("zhezhao");
		});
	});
	function showIndex(){
		var src="<s:url value="/res/disciple/discipleIndex"></s:url>";
		window.location.href= src;
	}
	function refresh()
	{
		window.location.reload(true);
	}
	function showTeacherInfo(){
		var src="<s:url value="/res/disciple/discipleTeacherIndex"></s:url>";
		window.location.href= src;
	}
	function showTcmRecordInfo(){
		var src="<s:url value="/res/bookStudyRecord/list"></s:url>";
		window.location.href= src;
	}
	function showGraduationInfo(){
		var src="<s:url value="/res/graduation/main/student"></s:url>";
		window.location.href= src;
	}
	//跟师记录
	function showFollowTeacherRecord(){
		var url ="<s:url value="/res/folowTeacherRecord/showFollowTeacherRecord"></s:url> ";
		window.location.href= url;
	}
	//跟师医案
	function showTypicalCases(){
		var url ="<s:url value="/res/typicalCases/showTypicalCases"></s:url> ";
		window.location.href= url;
	}
    function showDiscipleNoteInfo(scope){
        var doctorFlow="${resDoctor.doctorFlow}";
        var discipleTeacherFlow="${resDoctor.discipleTeacherFlow}";
        var url = "<s:url value="/res/discipleNote/showDiscipleNoteInfo/doctor/"/>"+scope +"?doctorFlow="+doctorFlow+"&discipleTeacherFlow="+discipleTeacherFlow;
        window.location.href= url;
    }
	function showUploadFileTr(fileFlow)
	{
		var width=(window.screen.width)*0.5;
		var height=(window.screen.height)*0.5;
		var url = "<s:url value="/res/disciple/showUploadFile/"/>?fileFlow="+fileFlow;
		jboxOpen(url,"结业论文上传",600,250,true);
	}
    function initAnnual(){
        var doctorFlow="${resDoctor.doctorFlow}";
        var url = "<s:url value="/res/disciple/initAnnualAssessment/doctor?doctorFlow="/>"+doctorFlow;
        window.location.href= url;
    }
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<c:if test="${ !((not empty resDoctor) and (not empty resDoctor.discipleTeacherFlow))}">
			<div style="width: 100%;height: 100%;left: 100%;text-align: center">
				<img style="width: auto;height: auto;" src="<s:url value='/jsp/res/disciple/images/noneTea.png'/>"/>
				<%--<img style="float: left" src="<s:url value='/jsp/res/disciple/images/none.png'/>"/>--%>
			</div>
		</c:if>
		<c:if test="${(not empty resDoctor) and (not empty resDoctor.discipleTeacherFlow)}">
			<%--<div class="divMain">--%>
				<div class="divItems" onclick="showIndex()">
					<div class="div100">
						<img class="divImg110" src="<s:url value='/jsp/res/disciple/images/110/xxfm.png'/>"/>
						<br>
						<span class="span1">跟师学习管理学习手册封面</span>
					</div>
					<div class="div126">
						<img class="divImg126" src="<s:url value='/jsp/res/disciple/images/126/xxfm.png'/>"/>
					</div>
					<span class="span">学习手册封面</span>
				</div>
				<div class="divItems" onclick="showTeacherInfo()">
					<div class="div100">
						<img class="divImg110" src="<s:url value='/jsp/res/disciple/images/110/sczd.png'/>"/>
						<br>
						<span class="span1">师承指导老师简况</span>
					</div>
					<div class="div126">
						<img class="divImg126" src="<s:url value='/jsp/res/disciple/images/126/sczd.png'/>"/>
					</div>
					<span class="span">师承指导老师简况</span>
				</div>
				<div class="divItems" onclick="showFollowTeacherRecord()">
					<div class="div100">
						<img class="divImg110" src="<s:url value='/jsp/res/disciple/images/110/gsjl.png'/>"/>
						<br>
						<span class="span1">跟师记录</span>
					</div>
					<div class="div126">
						<img class="divImg126" src="<s:url value='/jsp/res/disciple/images/126/gsjl.png'/>"/>
					</div>
					<span class="span">跟师记录</span>
				</div>
				<div class="divItems" onclick="showDiscipleNoteInfo('Note')">
					<div class="div100">
						<img class="divImg110" src="<s:url value='/jsp/res/disciple/images/110/xxbj.png'/>"/>
						<br>
						<span class="span1">跟师学习笔记</span>
					</div>
					<div class="div126">
						<img class="divImg126" src="<s:url value='/jsp/res/disciple/images/126/xxbj.png'/>"/>
					</div>
					<span class="span">跟师学习笔记</span>
				</div>
			<%--</div>--%>
			<%--<div class="divMain">--%>
				<div class="divItems" onclick="showDiscipleNoteInfo('Experience')">
					<div class="div100">
						<img class="divImg110" src="<s:url value='/jsp/res/disciple/images/110/gsxd.png'/>"/>
						<br>
						<span class="span1">跟师心得</span>
					</div>
					<div class="div126">
						<img class="divImg126" src="<s:url value='/jsp/res/disciple/images/126/gsxd.png'/>"/>
					</div>
					<span class="span">跟师心得</span>
				</div>
				<div class="divItems" onclick="showTcmRecordInfo()">
					<div class="div100">
						<img class="divImg110" src="<s:url value='/jsp/res/disciple/images/110/zyjl.png'/>"/>
						<br>
						<span class="span1">中医经典书籍学习记录</span>
					</div>
					<div class="div126">
						<img class="divImg126" src="<s:url value='/jsp/res/disciple/images/126/zyjl.png'/>"/>
					</div>
					<span class="span">中医经典书籍学习记录</span>
				</div>
				<div class="divItems" onclick="showDiscipleNoteInfo('BookExperience')">
					<div class="div100">
						<img class="divImg110" src="<s:url value='/jsp/res/disciple/images/110/xxth.png'/>"/>
						<br>
						<span class="span1">经典医籍学习体会</span>
					</div>
					<div class="div126">
						<img class="divImg126" src="<s:url value='/jsp/res/disciple/images/126/xxth.png'/>"/>
					</div>
					<span class="span">经典医籍学习体会</span>
				</div>
				<div class="divItems" onclick="showTypicalCases();">
					<div class="div100">
						<img class="divImg110" src="<s:url value='/jsp/res/disciple/images/110/dxal.png'/>"/>
						<br>
						<span class="span1">跟师医案</span>
					</div>
					<div class="div126">
						<img class="divImg126" src="<s:url value='/jsp/res/disciple/images/126/dxal.png'/>"/>
					</div>
					<span class="span">跟师医案</span>
				</div>
			<%--</div>--%>
			<%--<div class="divMain">--%>
				<div class="divItems" onclick="initAnnual()">
					<div class="div100">
						<img class="divImg110" src="<s:url value='/jsp/res/disciple/images/110/khjl.png'/>"/>
						<br>
						<span class="span1">跟师学习年度考核情况记录表</span>
					</div>
					<div class="div126">
						<img class="divImg126" src="<s:url value='/jsp/res/disciple/images/126/khjl.png'/>"/>
					</div>
					<span class="span">年度考核情况记录表</span>
				</div>
				<div class="divItems" onclick="showGraduationInfo()">
					<div class="div100">
						<img class="divImg110" src="<s:url value='/jsp/res/disciple/images/110/jykh.png'/>"/>
						<br>
						<span class="span1">跟师学习结业考核情况记录表</span>
					</div>
					<div class="div126">
						<img class="divImg126" src="<s:url value='/jsp/res/disciple/images/126/jykh.png'/>"/>
					</div>
					<span class="span">结业考核情况记录表</span>
				</div>
				<%--管理员是否审核通过--%>
				<c:set var="isShowSelect" value="${ext.auditStatusId eq discipleStatusEnumAdminAudit.id}"></c:set>
				<div class="divItems" <c:if test="${!isShowSelect}"> onclick="showUploadFileTr('${file.fileFlow}')"</c:if> >
					<div class="div100">
						<img class="divImg110" src="<s:url value='/jsp/res/disciple/images/110/jysc.png'/>"/>
						<br>
						<c:if test="${(not empty file) and (not empty file.fileName)}">
							<span class="span1">${file.fileName}</span>
						</c:if>
						<c:if test="${!((not empty file) and (not empty file.fileName))}">
							<span class="span1">结业论文上传</span>
						</c:if>
					</div>
					<div class="div126">
						<img class="divImg126" src="<s:url value='/jsp/res/disciple/images/126/jysc.png'/>"/>
					</div>
					<c:if test="${(not empty file) and (not empty file.fileName)}">
						<span class="span">${file.fileName}</span>
					</c:if>
					<c:if test="${!((not empty file) and (not empty file.fileName))}">
						<span class="span">结业论文上传</span>
					</c:if>
				</div>
			<%--</div>--%>
		</c:if>
	</div>
	<div class="clearfix"></div>
	<div style="padding-bottom: 20px;"></div>
</div>
</div>
</body>
</html>