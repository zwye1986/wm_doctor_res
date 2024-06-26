<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	.label td{
		width:120px;height:35px;text-align:center;border:1px solid #E3E3E3;cursor:pointer;
	}
	.label td.on{background-color:#4195C5;color:#fff;}
</style>
<script type="text/javascript">
	$(function(){
		$(".label td").bind('click',function(){
			$(this).attr("class","on");
			$(this).siblings("td").removeAttr("class");
			var index = $(this).index();
			$(".labelDiv").each(function(i){
				if(i == index){
					$(this).show();
					$(this).siblings(".labelDiv").hide();
				}
			});
			if(index == 0){
				toPage1(1);
			}else if(index == 2) {
				toPage2(1);
			}else if(index == 1) {
				searchRoom();
			}else if(index == 3){
				toPage3(1);
			}else if(index == 4){
				toPage4(1);
			}else if(index == 5){
				toPage5(1);
			}
		});
	});
	/*******************************预约学员信息***********************************/
	function toPage1(page){
		$("#currentPage1").val(page);
		jboxPostLoad("appointDiv","<s:url value="/osca/base/checkInfoManage"/>",$("#appointForm").serialize(),true);
	}
	function checkAll(){
		if($("#checkAll").attr("checked")){
			$(".check").attr("checked",true);
		}else{
			$(".check").attr("checked",false);
		}
	}
	function checkSingel(obj){
		if(!$(obj).attr("checked")){
			$("#checkAll").attr("checked",false);
		}else{
			var checkAllLen = $("input[type='checkbox'][class='check']").length;
			var checkLen = $("input[type='checkbox'][class='check']:checked").length;
			if(checkAllLen == checkLen){
				$("#checkAll").attr("checked",true);
			}
		}
	}
	function auditOpt(){
		var checkLen = $(":checkbox[class='check']:checked").length;
		if(checkLen == 0){
			jboxTip("请勾选预约学员信息！");
			return;
		}else{
			var len = 0;
			$(":checkbox[class='check']:checked").each(function(){
				if($(this).attr("statusId") != "Passing"){
					len ++;
				}
			});
			if(len > 0){
				jboxTip("只能审核待审核状态的记录！");
				return;
			}
		}
		var recordLst = [];
		$(":checkbox[class='check']:checked").each(function(){
			recordLst.push(this.value);
		})
		jboxButtonConfirm("所勾选学员预约信息是否通过？","通过","不通过", function(){//通过
			var json = {"recordLst":recordLst,"auditStatusId":"Passed"};
			var url = "<s:url value='/osca/base/auditAppoint'/>";
			jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
				setTimeout(function(){
					jboxPostLoad("appointDiv","<s:url value="/osca/base/checkInfoManage"/>",$("#appointForm").serialize(),true);
				},1000);
			}, null, true);
		},function(){//不通过
			var json = {"recordLst":recordLst,"auditStatusId":"UnPassed"};
			jboxOpen("<s:url value='/jsp/osca/base/reason.jsp'/>?jsonData="+encodeURI(JSON.stringify(json)),"",250,150,false);
		},300);
	}
	function expAppoint(){
		var url = "<s:url value='/osca/base/expAppoint'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#appointForm"), url, null, null, false);
		jboxEndLoading();
	}
	function importExl(){
		jboxOpen("<s:url value='/osca/base/toImportStudents?clinicalFlow=${param.clinicalFlow}'/>", "导入",600,200);
	}

    function expDocTickets(){
        var checkLen = $(":checkbox[class='check']:checked").length;
        if(checkLen == 0){
            jboxTip("请勾选学员！");
            return;
        }else{
            var len = 0;
            $(":checkbox[class='check']:checked").each(function(){
                if($(this).attr("statusId") != "Passed"){
                    len ++;
                }
            });
            if(len > 0){
                jboxTip("只能勾选审核状态通过的记录！");
                return;
            }
        }
        var recordFlows = "";
        $(":checkbox[class='check']:checked").each(function(i){
            if(i == 0){
                recordFlows += $(this).val();
            }else {
                recordFlows += "&recordFlows=" + $(this).val();
            }
        });
        var url = "<s:url value='/osca/base/downTicket'/>?clinicalFlow=${param.clinicalFlow}"+"&recordFlows="+recordFlows;
        jboxTip("导出中…………");
        jboxSubmit($("#appointForm"), url, null, null, false);
        jboxEndLoading();
    }
	function backOpt(){
		var checkLen = $(":checkbox[class='check']:checked").length;
		if(checkLen == 0){
			jboxTip("请勾选预约学员信息！");
			return;
		}else{
			var len = 0;
			$(":checkbox[class='check']:checked").each(function(){
				if($(this).attr("signStatusId") == "SignIn"){
					len ++;
				}
			});
			if(len > 0){
				jboxTip("无法撤销已签到的记录！");
				return;
			}
		}
		var recordLst = [];
		$(":checkbox[class='check']:checked").each(function(){
			recordLst.push(this.value);
		})
		jboxConfirm("确认撤销成待审核状态？",function() {//通过
			var json = {"recordLst": recordLst, "auditStatusId": "Passing"};
			var url = "<s:url value='/osca/base/auditAppoint'/>";
			jboxPost(url, "jsonData=" + JSON.stringify(json), function (resp) {
				setTimeout(function(){
					jboxPostLoad("appointDiv","<s:url value="/osca/base/checkInfoManage"/>",$("#appointForm").serialize(),true);
				},1000);
			}, null, true);
		});
	}
	/*******************************签到管理***********************************/
	function toPage2(page){
		$("#currentPage2").val(page);
		jboxPostLoad("signDiv","<s:url value="/osca/base/signManage"/>",$("#signForm").serialize(),true);
	}
	function signOpt(recordFlow,signStatusId){
		var statusId;
		var statusName;
		if(signStatusId == "NoSignIn"){
			statusId = "SignIn";
			statusName = "已签到";
		}else if(signStatusId == "SignIn"){
			statusId = "NoSignIn";
			statusName = "未签到";
		}
		jboxConfirm("是否更改状态“"+statusName+"”？",function(){
			var url = "<s:url value='/osca/base/changeSign?recordFlow='/>"+recordFlow+"&signStatusId="+statusId;
			jboxPost(url, null, function (resp) {
				<%--jboxPostLoad("signDiv","<s:url value="/osca/base/signManage"/>",$("#signForm").serialize(),true);--%>
				toPage2($("#currentPage2").val());
			}, null, true);
		});
	}
	function expSign(){
		var url = "<s:url value='/osca/base/expSign'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#signForm"), url, null, null, false);
		jboxEndLoading();
	}
	/*******************************考场安排***********************************/
	function searchRoom(){
		jboxPostLoad("roomDiv","<s:url value="/osca/base/roomManage"/>",$("#roomForm").serialize(),true);
	}
	function addRoom(recordFlow){
		var title = recordFlow == ""?"新增":"编辑";
		var url = "<s:url value='/osca/base/addRoom?clinicalFlow=${param.clinicalFlow}&subjectFlow=${param.subjectFlow}&recordFlow='/>"+recordFlow+"&clinicalName="+encodeURI(encodeURI("${clinicalName}"));
		jboxOpen(url,title,400,400,false);
	}
	function uploadFile(clinicalFlow,stationFlow){
		var url='<s:url value="/osca/provincial/uploadClinicalStationFiles"/>?clinicalFlow='+clinicalFlow+"&stationFlow="+stationFlow;
		jboxOpen(url,"上传考核试卷信息",500,550);
	}
	function addRoomNew(stationFlow){
		var title ="新增";
		var url = "<s:url value='/osca/base/addRoom?clinicalFlow=${param.clinicalFlow}&subjectFlow=${param.subjectFlow}&stationFlow='/>"+stationFlow+"&clinicalName="+encodeURI(encodeURI("${clinicalName}"))+"&flag=Y";
		jboxOpen(url,title,400,400,false);
	}
	function delRoom(recordFlow){
		var tip = recordFlow == ""?"清空考场":"删除此考场";
		jboxConfirm("是否确认"+tip+"？",function(){
			var url = "<s:url value='/osca/base/delRoom?clinicalFlow=${param.clinicalFlow}&recordFlow='/>"+recordFlow;
			jboxPost(url, null, function (resp) {
				searchRoom();
			}, null, true);
		});
	}
	/*******************************考核进度***********************************/
	function toPage3(page){
		$("#currentPage3").val(page);
		jboxPostLoad("scheduleDiv","<s:url value="/osca/base/scheduleManage"/>",$("#scheduleForm").serialize(),true);
	}
	/*******************************候考管理***********************************/
	function toPage5(page){
		jboxPostLoad("houkaoDiv","<s:url value="/osca/base/houkaoManage"/>",$("#houkaoForm").serialize(),true);
	}
	/*******************************成绩管理***********************************/
	function toPage4(page){
		$("#currentPage4").val(page);
		jboxPostLoad("gradeDiv","<s:url value="/osca/base/gradeManage"/>",$("#gradeForm").serialize(),true);
	}
	function isShowOpt(value,flag,obj){
		if(value=="school"){
			jboxConfirm("上报后不能修改，请确认",function(){
				var url = "<s:url value='/osca/base/isShowOpt?clinicalFlow=${param.clinicalFlow}&isShow='/>"+value+"&flag="+flag;
				jboxPost(url, null, function (resp) {
					$("input[name='isGradeReleased']").val("S");
					jboxPostLoad("gradeDiv","<s:url value="/osca/base/gradeManage"/>",$("#gradeForm").serialize(),true);
					if(resp=="操作成功！")jboxTip("操作成功！");
				}, null, false);
			})
		} else {
			var url = "<s:url value='/osca/base/isShowOpt?clinicalFlow=${param.clinicalFlow}&isShow='/>" + value + "&flag=" + flag;
			jboxPost(url, null, function (resp) {
//			$("input[name='isGradeReleased']").val("S");
				jboxPostLoad("gradeDiv", "<s:url value="/osca/base/gradeManage"/>", $("#gradeForm").serialize(), true);
				if(resp=="操作成功！")jboxTip("操作成功！");
			}, null, false);
		}
	}
	function gradeImport(){
		jboxOpen("<s:url value='/jsp/osca/base/importGrade.jsp?clinicalFlow=${param.clinicalFlow}'/>", "成绩导入",600,200);
	}
	function gradeExport(){
		var url = "<s:url value='/osca/base/exportGradeExcel'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#gradeForm"), url, null, null, false);
		jboxEndLoading();
	}
	function editGradeOpt(doctorFlow){
		jboxOpen("<s:url value='/osca/base/editGradeOpt?clinicalFlow=${param.clinicalFlow}&subjectFlow=${param.subjectFlow}&doctorFlow='/>"+doctorFlow+"&currentPage="+$("#currentPage4").val(), "成绩编辑",480,530);
	}
	function searchScoreForm(doctorFlow,stationFlow){
		jboxOpen("<s:url value='/osca/base/searchScoreForm?clinicalFlow=${param.clinicalFlow}&doctorFlow='/>"+doctorFlow+"&stationFlow="+stationFlow, "评分表",600,600);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div style="margin:20px 0px 10px;">
			<table class="label">
				<tr>
					<td class="on">预约学员信息</td>
					<td>考场安排</td>
					<td>签到管理</td>
					<td>考核进度</td>
					<td>成绩管理</td>
					<td>候考管理</td>
				</tr>
			</table>
		</div>
		<div style="font:16px bold;" id="speDiv">考核名称：${clinicalName}<span style='padding-left:25px'></span>考核专业：${speName}</div>
		<div id="appointDiv" class="labelDiv">
			<form id="appointForm" action="<s:url value="/osca/base/checkInfoManage"/>" method="post">
				<input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
				<input type="hidden" name="clinicalName" value="${clinicalName}"/>
				<input type="hidden" name="speName" value="${speName}"/>
				<input type="hidden" name="year" value="${year}"/>
				<input type="hidden" name="isLocal" value="${isLocal}"/>
				<input id="currentPage1" type="hidden" name="currentPage1" value="1"/>
				<table class="basic" style="width:100%;border:0px;margin:10px 0px;">
					<tr>
						<td style="border:0px;">
							<span style=""></span>审核状态：
							<select name="auditStatusId" style="width:137px;" class="select">
								<option value="">全部</option>
								<c:forEach items="${auditStatusEnumList}" var="status">
									<option value="${status.id}" ${param.auditStatusId eq status.id ?'selected':''}>${status.name}</option>
								</c:forEach>
							</select>
							<span style="padding-left:10px;"></span>姓名：
							<input type="text" name="appointDoctorName" value="${param.appointDoctorName}">
							<c:if test="${isLocal eq 'N'}">
							<span style="padding-left:10px;"></span>届别：
							<select name="sessionId" style="width:137px;" class="select">
								<option value="">全部</option>
								<option value="1" ${param.sessionId eq "1"?'selected':''}>应届</option>
								<option value="0" ${param.sessionId eq "0"?'selected':''}>往届</option>
							</select>
							</c:if>
							<span style="padding-left:20px;"></span>
							<input id="search001" type="button" class="search" value="查&#12288;询" onclick="toPage1(1)"/>
							<input type="button" class="search" value="审&#12288;核" onclick="auditOpt()"/>
							<input type="button" class="search" value="撤&#12288;销" onclick="backOpt()"/>
							<input type="button" class="search" value="导&#12288;出" onclick="expAppoint()"/>
							<input type="button" class="search" value="导&#12288;入" onclick="importExl()"/>
							<input type="button" class="search" value="导出准考证" onclick="expDocTickets()"/>
						</td>
					</tr>
				</table>
			</form>
			<table class="xllist" style="width:100%;">
				<tr>
					<th><input type="checkbox" id="checkAll" onclick="checkAll()">&nbsp;序号</th>
					<th>姓名</th>
					<th>证件号码</th>
					<th>性别</th>
					<th>培训届别</th>
					<th>培训基地</th>
					<th>考核专业</th>
					<th>联系方式</th>
					<th>学员预约时间</th>
					<th>状态</th>
				</tr>
				<c:forEach items="${appointList}" var="info" varStatus="i">
					<tr>
						<td><input type="checkbox" class="check" value="${info.recordFlow}" statusId="${info.auditStatusId}" signStatusId="${info.siginStatusId}" onclick="checkSingel(this)">&nbsp;${i.index + 1}</td>
						<td>${info.doctorName}</td>
						<td>${info.sysUser.idNo}</td>
						<td>${info.sysUser.sexName}</td>
						<td>${info.doctor.sessionNumber}</td>
						<td>${info.doctor.orgName}</td>
						<td>${info.skillAssess.speName}</td>
						<td>${info.sysUser.userPhone}</td>
						<td>${info.appointTime}</td>
						<td>${info.auditStatusName}</td>
					</tr>
				</c:forEach>
			</table>
			<div id="detail"></div>
			<div style="float:right;margin-top:100px;">
				<c:set var="pageView" value="${pdfn:getPageView(appointList)}" scope="request"/>
				<pd:pagination toPage="toPage1"/>
			</div>
		</div>
		<div id="roomDiv" class="labelDiv" style="display:none;">
			<form id="roomForm" method="post">
				<input type="hidden" name="clinicalName" value="${clinicalName}"/>
				<input type="hidden" name="speName" value="${speName}"/>
				<input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
				<input type="hidden" name="subjectFlow" value="${param.subjectFlow}"/>
				<table class="basic" style="width:100%;border:0px;margin: 10px 0px;">
					<tr>
						<td style="border:0px;">
							<input type="button" class="search" value="新增考场" onclick="addRoom('')"/>
							<input type="button" class="search" value="清空考场" onclick="delRoom('')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="signDiv" class="labelDiv" style="display:none;">
			<form id="signForm" method="post">
				<input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
				<input type="hidden" name="clinicalName" value="${clinicalName}"/>
				<input type="hidden" name="speName" value="${speName}"/>
				<input id="currentPage2" type="hidden" name="currentPage2" value="${currentPage2}"/>
				<table class="basic" style="width:100%;border:0px;margin: 10px 0px;">
					<tr>
						<td style="border:0px;">
							<span style=""></span>是否签到：
							<select name="siginStatusId" style="width:137px;" class="select">
								<option value="">全部</option>
								<c:forEach items="${signStatusEnumList}" var="status">
									<option value="${status.id}" ${param.siginStatusId eq status.id ?'selected':''}>${status.name}</option>
								</c:forEach>
							</select>
							<span style="padding-left:10px;"></span>姓名：
							<input type="text" name="signDoctorName" value="${param.signDoctorName}">
							<span style="padding-left:20px;"></span>
							<input type="button" class="search" value="查&#12288;询" onclick="toPage2(1)"/>
							<input type="button" class="search" value="导&#12288;出" onclick="expSign()"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="scheduleDiv" class="labelDiv" style="display:none;">
			<form id="scheduleForm" method="post">
				<input type="hidden" name="clinicalName" value="${clinicalName}"/>
				<input type="hidden" name="speName" value="${speName}"/>
				<input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
				<input type="hidden" name="subjectFlow" value="${param.subjectFlow}"/>
				<input type="hidden" name="currentPage3" value="1"/>
				<table class="basic" style="width:100%;border:0px;margin: 10px 0px;">
					<tr>
						<td style="border:0px;">
							<span style=""></span>考核进度：
							<select name="complete" style="width:137px;" class="select">
								<option value="">全部</option>
								<option value="Y" ${param.complete eq 'Y'?'selected':''}>完成</option>
								<option value="N" ${param.complete eq 'N'?'selected':''}>未完成</option>
							</select>
							<span style="padding-left:10px;"></span>考试阶段：
							<select name="categoryId" style="width:137px;" class="select">
								<option value="">全部</option>
								<c:forEach items="${trainCategoryEnumList}" var="cate">
									<option value="${cate.id}" ${param.categoryId eq cate.id?'selected':''}>${cate.name}</option>
								</c:forEach>
							</select>
							<span style="padding-left:20px;"></span>
							<input type="button" class="search" value="查&#12288;询" onclick="toPage3(1)"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="gradeDiv" class="labelDiv" style="display:none;">
			<form id="gradeForm" method="post">
				<input type="hidden" name="clinicalName" value="${clinicalName}"/>
				<input type="hidden" name="speName" value="${speName}"/>
				<input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
				<input type="hidden" name="subjectFlow" value="${param.subjectFlow}"/>
				<input type="hidden" name="currentPage4" value="1"/>
				<input type="hidden" name="isLocal" value="${isLocal}"/>
				<input type="hidden" name="isGradeReleased" value="${isGradeReleased}"/>
				<table class="basic" style="width:100%;border:0px;margin: 10px 0px;">
					<tr>
						<td style="border:0px;">
							<span style=""></span>准考证号：
							<input type="text" name="ticketNumber" value="${param.ticketNumber}">
							<span style="padding-left:5px;"></span>姓名：
							<input type="text" name="gradeDoctorName" value="${param.gradeDoctorName}">
							<span style="padding-left:5px;"></span>考试阶段：
							<select name="trainCategoryId" style="width:137px;" class="select">
								<option value="">全部</option>
								<c:forEach items="${trainCategoryEnumList}" var="cate">
									<option value="${cate.id}" ${param.trainCategoryId eq cate.id?'selected':''}>${cate.name}</option>
								</c:forEach>
							</select>
							<span style="padding-left:5px;"></span>考核结果：
							<select name="resultId" style="width:137px;" class="select">
								<option value="">全部</option>
								<c:forEach items="${doctorScoreEnumList}" var="rlt">
									<option value="${rlt.id}" ${param.resultId eq rlt.id?'selected':''}>${rlt.name}</option>
								</c:forEach>
							</select>
							<span style="padding-left:5px;"></span>成绩排序：
							<input type="radio" name="order" value="ASC" ${param.order eq 'ASC'?'checked':''}>升序&nbsp;
							<input type="radio" name="order" value="DESC" ${param.order eq 'DESC'?'checked':''}>降序
							<span style="padding-left:10px;"></span>
							<input type="button" class="search" value="查询" onclick="toPage4(1)"/>
							<br/>
							<span style="color:red;display:inline-block;margin-top:20px;">通过率${percent}%（说明：该通过率为查询结果通过率=考试合格人员/参加考试人员）</span>
				<span style="float:right;margin:20px 34px 0px;">
					<c:if test="${param.isLocal eq 'Y'}">学员能否查看考核成绩：
					<input type="radio" name="isShow" value="N" ${osa.isShow eq 'N'?'checked':''} onchange="isShowOpt(this.value)">否&#12288;
					<input type="radio" name="isShow" value="Y" ${osa.isShow eq 'Y'?'checked':''} onchange="isShowOpt(this.value)">是
					</c:if>
					<span style="padding-left:10px;"></span>
					<input type="button" class="search" value="成绩导入" onclick="gradeImport()"/>
					<input type="button" class="search" value="成绩导出" onclick="gradeExport()"/>
					<c:if test="${isLocal eq 'Y'}">
						<input type="button" class="search" value="成绩发布" onclick="isShowOpt('grade')"/>
					</c:if>
					<c:if test="${isLocal eq 'N'}">
						<c:if test="${isGradeReleased eq 'N'}">
							<input type="button" class="search" value="成绩上报" onclick="isShowOpt('school','',this)"/>
						</c:if>
						<c:if test="${isGradeReleased ne 'N'}">
							<input type="button" class="search" value=" 已 上 报 " style="cursor: auto;"/>
						</c:if>
					</c:if>
				</span>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="houkaoDiv" class="labelDiv" style="display:none;">
			<form id="houkaoForm" method="post">
				<input type="hidden" name="clinicalName" value="${clinicalName}"/>
				<input type="hidden" name="speName" value="${speName}"/>
				<input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
				<input type="hidden" name="subjectFlow" value="${param.subjectFlow}"/>
			</form>
		</div>
	</div>
</div>
</body>	
</html>