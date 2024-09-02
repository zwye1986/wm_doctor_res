<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style type="text/css">
	.label td{
		width:120px;height:35px;text-align:center;border:1px solid #E3E3E3;cursor:pointer;
	}
	.label td.on{background-color:#54B2E5;color:#fff;}
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
		jboxPostLoad("appointDiv","<s:url value="/jsres/skillTimeConfig/checkInfoManage"/>",$("#appointForm").serialize(),true);
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
					jboxPostLoad("appointDiv","<s:url value="/jsres/skillTimeConfig/checkInfoManage"/>",$("#appointForm").serialize(),true);
				},1000);
			}, null, true);
		},function(){//不通过
			var json = {"recordLst":recordLst,"auditStatusId":"UnPassed"};
			jboxOpen("<s:url value='/jsp/osca/base/reason.jsp'/>?jsonData="+encodeURI(JSON.stringify(json)),"",250,150,false);
		},300);
	}
	function exportAppoint(){
		var url = "<s:url value='/osca/base/expAppoint'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#appointForm"), url, null, null, false);
		jboxEndLoading();
	}
	function importExcel(){
		jboxOpen("<s:url value='/jsres/skillTimeConfig/toImportStudents?clinicalFlow=${param.clinicalFlow}'/>", "导入",600,200);
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
        var url = "<s:url value='/jsres/skillTimeConfig/downTicket'/>?clinicalFlow=${param.clinicalFlow}"+"&recordFlows="+recordFlows;
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
					jboxPostLoad("appointDiv","<s:url value="/jsres/skillTimeConfig/checkInfoManage"/>",$("#appointForm").serialize(),true);
				},1000);
			}, null, true);
		});
	}

	/*******************************考场安排***********************************/
	function searchRoom(){
		jboxPostLoad("roomDiv","<s:url value="/jsres/skillTimeConfig/roomManage"/>",$("#roomForm").serialize(),true);
	}
	function addRoom(recordFlow){
		var title = recordFlow == ""?"新增":"编辑";
		var url = "<s:url value='/jsres/skillTimeConfig/addRoom?clinicalFlow=${param.clinicalFlow}&subjectFlow=${param.subjectFlow}&recordFlow='/>"+recordFlow+"&clinicalName="+encodeURI(encodeURI("${clinicalName}"));
		// jboxOpen(url,title,400,400,true);
        var url ="<s:url value='/jsres/skillTimeConfig/addRoom?clinicalFlow=${param.clinicalFlow}&subjectFlow=${param.subjectFlow}&recordFlow='/>"+recordFlow+"&clinicalName="+encodeURI(encodeURI("${clinicalName}"));
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' src='"+url+"'></iframe>";
        jboxMessager(iframe,title,400,400,true);
	}
	function uploadFile(clinicalFlow,stationFlow){
		var url='<s:url value="/osca/provincial/uploadClinicalStationFiles"/>?clinicalFlow='+clinicalFlow+"&stationFlow="+stationFlow;
		jboxOpen(url,"上传考核试卷信息",500,550);
	}
	function addRoomNew(stationFlow){
		var title ="新增";
		var url = "<s:url value='/jsres/skillTimeConfig/addRoom?clinicalFlow=${param.clinicalFlow}&subjectFlow=${param.subjectFlow}&stationFlow='/>"+stationFlow+"&clinicalName="+encodeURI(encodeURI("${clinicalName}"))+"&flag=Y";
		// jboxOpen(url,title,400,400,false);
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' src='"+url+"'></iframe>";
        jboxMessager(iframe,title,400,400,true);
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
</script>
</head>
<body>
<div class="main_bd" style="margin-left: 10px;">
	<div style="margin:20px 0px 10px;">
		<table class="label">
			<tr>
				<td class="on">预约学员信息</td>
				<td>考场安排</td>
			</tr>
		</table>
	</div>
	<div style="font:16px bold;" id="speDiv">考核名称：${clinicalName}<span style='padding-left:25px'></span>考核专业：${speName}</div>
	<div id="appointDiv" class="labelDiv">
		<div class="div_search">
			<form id="appointForm" action="<s:url value="/jsres/skillTimeConfig/checkInfoManage"/>" method="post">
				<input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
				<input type="hidden" name="clinicalName" value="${clinicalName}"/>
				<input type="hidden" name="speName" value="${speName}"/>
				<input type="hidden" name="year" value="${year}"/>
				<input type="hidden" name="isLocal" value="${isLocal}"/>
				<input id="currentPage1" type="hidden" name="currentPage1" value="1"/>
				<table class="searchTable">
					<tr>
						<td style="border:0px;">
							<span style=""></span>审核状态：
							<select name="auditStatusId" style="width:137px;" class="select">
								<option value="">全部</option>
								<c:forEach items="${auditStatusEnumList}" var="status">
									<option value="${status.id}" ${param.auditStatusId eq status.id ?'selected':''}>${status.name}</option>
								</c:forEach>
							</select>&#12288;&#12288;
							<span style="padding-left:10px;"></span>姓名：
							<input type="text" class="input" name="appointDoctorName" value="${param.appointDoctorName}">
							<%--<span style="padding-left:20px;"></span>--%>
							<br/>
							<input style="margin-top: 5px" id="search001" type="button" class="btn_green" value="查&#12288;询" onclick="toPage1(1)"/>
							<input style="margin-top: 5px" type="button" class="btn_green" value="审&#12288;核" onclick="auditOpt()"/>
							<input style="margin-top: 5px" type="button" class="btn_green" value="撤&#12288;销" onclick="backOpt()"/>
							<input style="margin-top: 5px" type="button" class="btn_green" value="导&#12288;出" onclick="exportAppoint()"/>
							<input style="margin-top: 5px" type="button" class="btn_green" value="导&#12288;入" onclick="importExcel()"/>
							<input style="margin-top: 5px" type="button" class="btn_green" value="导出准考证" onclick="expDocTickets()"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="padding: 0px 40px;">
		<div class="main_bd clearfix" style="margin-top:20px;">
			<table border="0" cellpadding="0" cellspacing="0" class="grid">
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
				<c:if test="${empty appointList}">
					<tr>
						<td colspan="10">无记录！</td>
					</tr>
				</c:if>
				<c:if test="${not empty appointList}">
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
				</c:if>
			</table>
		</div>
		<%--<div id="detail"></div>--%>
		<div class="page" style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(appointList)}" scope="request"/>
			<pd:pagination-jsres toPage="toPage1"/>
		</div>
	</div>
	</div>
	<div id="roomDiv" class="labelDiv">
		<div class="div_search" style="display:none;">
			<form id="roomForm" method="post">
				<input type="hidden" name="clinicalName" value="${clinicalName}"/>
				<input type="hidden" name="speName" value="${speName}"/>
				<input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
				<input type="hidden" name="subjectFlow" value="${param.subjectFlow}"/>
				<table class="searchTable">
					<tr>
						<td style="border:0px;">
							<input type="button" class="btn_green" value="新增考场" onclick="addRoom('')"/>
							<input type="button" class="btn_green" value="清空考场" onclick="delRoom('')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
</body>	
</html>