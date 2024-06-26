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
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
	.table {
		border: 1px solid #e3e3e3;
	}
	.table tr:nth-child(2n) {
		background-color: #fcfcfc;
		transition: all 0.125s ease-in-out 0s;
	}
	.table tr:hover {
		background: #fbf8e9 none repeat scroll 0 0;
	}
	.table th, .table td {
		border-bottom: 1px solid #e3e3e3;
		border-right: 1px solid #e3e3e3;
		text-align: center;
	}
	.table th {
		background: rgba(0, 0, 0, 0) url("<s:url value='/jsp/res/disciple/images/table.png'/>") repeat-x scroll 0 0;
		color: #585858;
		height: 30px;
	}
	.table td {
		height: 30px;
		line-height: 25px;
		text-align: center;
		word-break: break-all;
	}
</style>
<c:set var="isNew" value="${empty teacherInfo}"></c:set>
<script type="text/javascript">
	$(function(){
		<c:if test="${!isNew}">
			$(".readonly").attr("disabled","disabled");
		</c:if>
		if("${role}" == "teacher" | "${role}" == "admin"){
			$(".table").find("input:text").hide();
			$(".table").find("select").hide();
			$(".table").find("textarea").hide();
		}else {
			$(".table").find("lable").hide();
		}
        loadTeacherJson();
        // $("#teacherFlow").bind("input",function(event){
        //     alert($("#teacherFlow").val())
        // });
	});
    //查询师承老师
    function loadTeacherJson() {
        var url = "<s:url value='/res/disciple/searchTeachers'/>";
        var courseArray = [];
        jboxGetAsync(url, null, function (data) {
            if (data) {
                for (var i = 0; i < data.length; i++) {
                    var userName = data[i].userName;
                    var userFlow = data[i].userFlow;
                    if (data[i].userName == null) {
                        userName = "";
                    }
                    courseArray[i] = [userFlow, userName];
                    if ($("#userFlow").val() == userFlow) {
                        $("#teacherName").val(userName);
                    }
                }
                jboxStartLoading();
                $("#teacherName").suggest(courseArray, {
                    attachObject: '#suggest_teacher',
                    dataContainer: '#teacherFlow',
                    triggerFunc: function (userFlow) {
                        jboxPost("<s:url value='/res/disciple/searchSingleTeacher'/>?userFlow="+userFlow,null,function (resp){
                            if(resp){
                                var sexId = resp.sexId;
                                if(sexId){
                                    $("#sexId option[value='"+sexId+"']").attr("selected","selected");
                                }
								var userBirthday = resp.userBirthday;
								if(userBirthday){
                                    $("#birthdate").val(userBirthday);
								}
								var titleName = resp.titleName;
								if(titleName){
								    $("#teacherTitleName").val(titleName);
								}
                                var educationName = resp.educationName;
                                if(educationName){
                                    $("#educationId").val(educationName);
                                }
                                var userPhone = resp.userPhone;
                                if(userPhone){
                                    $("#phone").val(userPhone);
                                }
							}
						},null,false);
                    },
                    enterFunc: function (userName) {
                    }
                });
                jboxEndLoading();
            }
        }, null, false);
    }
	function showTeacherInfo(){
		var src="<s:url value="/res/disciple/discipleTeacherIndex"></s:url>";
		window.location.href= src;
	}
	function save()
	{
		jboxConfirm("确认保存?",function(){
			jboxPost("<s:url value='/res/disciple/saveDiscipleTeacherInfo'/>",$('#saveForm').serialize(),function(resp){
				if(resp=="${GlobalConstant.SAVE_SUCCESSED}") {
					showTeacherInfo();
				}
			},null,true);
		});

	}
	function showSave(obj)
	{
		$(obj).hide();
		$("#save").show();
		$(".readonly").removeAttr("disabled");
	}
    function adjustResults() {
        $("#suggest_teacher").css("left", $("#teacherName").offset().left);
        $("#suggest_teacher").css("top", $("#teacherName").offset().top + $("#teacherName").outerHeight());
    }

</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div width="100%" height="100%" style="margin-top: 20px">
			<table class="table" width="100%">
				<tr style="height: 58px">
					<th style="text-align: center; "><span style="font-size: 25px;">师承指导老师简况表</span></th>
				</tr>
			</table>
			<br>
			<table class="table" width="100%">
				<form id="saveForm">
					<input type="text" class=" inputText" value="${teacherInfo.recordFlow}" name="recordFlow" hidden style="margin-left: 20px;">
					<input type="text" class=" inputText" value="${( empty teacherInfo.doctorName)?user.userName:teacherInfo.doctorName}" name="doctorName" hidden style="margin-left: 20px;">
					<input type="text" class=" inputText" value="${( empty teacherInfo.doctorFlow)?user.userFlow:teacherInfo.doctorFlow}" name="doctorFlow" hidden style="margin-left: 20px;">
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">姓&#12288;&#12288;名：</span></td>
					<td style="text-align: left;">
						<input type="text" class="readonly inputText" <c:if test="${not empty param.role}">readonly</c:if> value="${teacherInfo.teacherName}"
							   name="teacherName" style="text-align:left;font-size: 16px;margin-left: 20px;height: 24px;width: 150px;"
							   id="teacherName" onkeydown="adjustResults();" onfocus="adjustResults();">
						<div id="suggest_teacher" class="teacher_results"
							 style="margin:0;position: fixed; z-index: 100;width: 150px;"></div>
						<input type="hidden" id="teacherFlow" value=""/>
						<lable style="text-align:left;font-size: 16px;margin-left: 20px;height: 24px;width: 150px;" class=" inputText">${teacherInfo.teacherName}</lable>
					</td>
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">性&#12288;&#12288;别：</span></td>
					<td style="text-align: left;">
						<select class="readonly inputText" id="sexId" name="sexId" <c:if test="${not empty param.role}">readonly</c:if>
								style="font-size: 16px;margin-left: 20px;height: 24px;width: 150px;">
							<option value=""></option>
							<c:forEach var="dict" items="${userSexEnumList}">
								<c:if test="${dict.id != userSexEnumUnknown.id}">
									<option value="${dict.id}" <c:if test="${teacherInfo.sexId eq dict.id}">selected</c:if> >${dict.name}</option>
								</c:if>
							</c:forEach>
						</select>
						<lable style="text-align:left;font-size: 16px;margin-left: 20px;height: 24px;width: 150px;" class=" inputText">${teacherInfo.sexName}</lable>
					</td>
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">出生年月：</span></td>
					<td style="text-align: left;">
						<input type="text" class="readonly inputText" id="birthdate" <c:if test="${not empty param.role}">readonly</c:if> name="birthdate" value="${teacherInfo.birthdate}" style="text-align:left;font-size: 16px;width: 150px;;margin-left: 20px;height: 24px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" >
						<lable style="text-align:left;font-size: 16px;margin-left: 20px;height: 24px;width: 150px;" class=" inputText">${teacherInfo.birthdate}</lable>
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">从事专业：</span></td>
					<td style="text-align: left;">
						<input type="text" class="readonly inputText" <c:if test="${not empty param.role}">readonly</c:if> value="${teacherInfo.workSpeName}" name="workSpeName" style="text-align:left;font-size: 16px;margin-left: 20px;height: 24px;width: 150px;">
						<lable style="text-align:left;font-size: 16px;margin-left: 20px;height: 24px;width: 150px;" class=" inputText">${teacherInfo.workSpeName}</lable>
					</td>
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">职&#12288;&#12288;称：</span></td>
					<td style="text-align: left;">
						<input type="text" class="readonly inputText" <c:if test="${not empty param.role}">readonly</c:if> value="${teacherInfo.teacherTitleName}" id="teacherTitleName" name="teacherTitleName" style="text-align:left;font-size: 16px;margin-left: 20px;height: 24px;width: 150px;">
						<lable style="text-align:left;font-size: 16px;margin-left: 20px;height: 24px;width: 150px;" class=" inputText">${teacherInfo.teacherTitleName}</lable>
					</td>
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">学&#12288;&#12288;历：</span></td>
					<td style="text-align: left;">
						<input type="text" class="readonly inputText" <c:if test="${not empty param.role}">readonly</c:if> id="educationId" name="educationId" value="${teacherInfo.educationId}" style="text-align:left;font-size: 16px;width: 100px;margin-left: 20px;height: 24px;width: 150px;" >
						<lable style="text-align:left;font-size: 16px;margin-left: 20px;height: 24px;width: 150px;" class=" inputText">${teacherInfo.educationId}</lable>
					</td>
				</tr>
				<tr style="height: 54px">
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">通讯地址：</span></td>
					<td style="text-align: left;" colspan="3">
						<input class="readonly inputText" <c:if test="${not empty param.role}">readonly</c:if> value="${teacherInfo.address}" maxlength="250" name="address"
								  style="text-align:left;font-size: 16px;margin-left: 20px;height: 24px;width: 500px;" >
						<lable style="text-align:left;font-size: 16px;margin-left: 20px;height: 24px;width: 150px;" class=" inputText">${teacherInfo.address}</lable>
						</input>
					</td>
					<td style="text-align: right; "><span style="font-size: 16px;margin-right: 34px">电&#12288;&#12288;话：</span></td>
					<td style="text-align: left;">
						<input type="text" class="readonly inputText" <c:if test="${not empty param.role}">readonly</c:if> id="phone" name="phone" value="${teacherInfo.phone}" maxlength="15" style="text-align:left;font-size: 16px;width: 150px;margin-left: 20px;height: 24px;" >
						<lable style="text-align:left;font-size: 16px;margin-left: 20px;height: 24px;width: 150px;" class=" inputText">${teacherInfo.phone}</lable>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;width: 100%; " colspan="6">
						<textarea class="readonly" <c:if test="${not empty param.role}">readonly</c:if> style="font-size: 16px;width: 80%;height: 450px;margin-top: 10px;" name="content" placeholder="主要学术经验、专长及成就（内容包括基本学术思想、临床技能、科研教学、论文论著等情况）">${teacherInfo.content}</textarea>
						<lable style="font-size: 16px;width: auto;height: 450px;margin-top: 10px;margin-left:20px;margin-right:20px;display: block;text-indent:50px;text-align: left;" class=" inputText">${teacherInfo.content}</lable>
					</td>
				</tr>
				</form>
				<tr style="height: 54px">
					<td colspan="6">

						<c:if test="${empty param.role}">
							<input type="button"  <c:if test="${!isNew}">hidden</c:if> value="保&#12288;存" id="save" class="search" style="width: 70px"  onclick="save();" />
							<input type="button"  <c:if test="${isNew}">hidden</c:if>  value="编&#12288;辑" class="search" style="width: 70px"  onclick="showSave(this);" />
						</c:if>
						<c:if test="${not empty param.role}"><input type="button"  value="关&#12288;闭" class="search" style="width: 70px"  onclick="jboxClose();" /></c:if>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</div>
</body>
</html>