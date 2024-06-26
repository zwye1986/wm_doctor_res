<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<%--<html>--%>
<%--<head>--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--<title>${applicationScope.sysCfgMap['study_platform'] eq 'szslyy'?'苏州市住院医师公共科目学习平台':'南京医科大学研究生在线课程学习平台'}</title>--%>
<%--<meta name="keywords" content=""/>--%>
<%--<meta name="description" content=""/>--%>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="courseDetail" value="true"/>
	<jsp:param name="teachCourses" value="true"/>
	<jsp:param name="findCourse" value="true"/>
</jsp:include>

<style type="text/css">
.f2{
	float: right;
}
</style>

<script type="text/javascript">
$(function(){
	toPage1(1);
});

function jboxSubmit(jqForm,posturl,funcOk,funcErr,showResp){
    if(showResp!=false){
        showResp = true;
    }
    var options = {
        url : posturl,
        type : "post",
        cache : false,
        beforeSend : function(){
            jboxStartLoading();
        },
        success : function(resp) {
            jboxEndLoading();
            if(showResp){
                jboxTip(resp);
            }
            if(funcOk!=null){
                if(showResp){
                    setTimeout(function(){
                        funcOk(resp);
                    },1000);
                }else{
                    funcOk(resp);
                }
            }
        },
        error : function() {
            jboxEndLoading();
            jboxTip("操作失败,请刷新页面后重试");
            if(funcErr!=null){
                funcErr();
            }
        },
        complete : function(){
            jboxEndLoading();
        },
        iframe : true
    };
    jqForm.ajaxSubmit(options);
}
function jboxButtonConfirm(msg,button1,button2,funcOk,funcCancel,width){
	dialog({
		fixed: true,
		width:width,
		title: '提示',
		cancelValue:'关闭',
		content: msg,
		backdropOpacity:0.1,
		button:[
			{
				value: button1,
				callback:funcOk,
				autofocus: true
			},
			{
				value: button2,
				callback:funcCancel
			}
		]
	}).showModal();
}
	/*******************************预约学员信息***********************************/
function toPage1(page){
	$("#currentPage1").val(page);
	jboxPostLoad("appointDiv","<s:url value="/njmuedu/user/detailList"/>?isAdmin="+"${isAdmin}",$("#appointForm").serialize(),true);
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
var sumbit = function (v,h,f) {
	if(v == true){
		jboxTip("通过",info);
	}else{
		jboxTip("不通过",info);
	}
	return true;
}

function auditOpt(){
	var checkLen = $(":checkbox[class='check']:checked").length;
	if(checkLen == 0){
		jboxTip("请勾选预约学员信息！");
		return;
	}else{
		var len = 0;
		$(":checkbox[class='check']:checked").each(function(){
			if($(this).attr("statusId") != "Passing" && $(this).attr("statusId") != "Passed"){
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
	});
	jboxButtonConfirm("所勾选学员预约信息是否通过？","通过","不通过",function(){//通过
		var url = "<s:url value='/njmuedu/user/auditPassed'/>?isAdmin="+"${isAdmin}";
		jboxPostJson(url,JSON.stringify(recordLst), function (resp) {
			setTimeout(function(){
				toPage1(1);
			},1000);
		}, null, true);
	},function(){//不通过
		var url = "<s:url value='/njmuedu/user/auditUnPassed'/>?isAdmin="+"${isAdmin}";
		jboxPostJson(url,JSON.stringify(recordLst), function (resp) {
			setTimeout(function(){
				toPage1(1);
			},1000);
		}, null, true);
	},300);
}

function expAppoint(){
	var url = "<s:url value='/njmuedu/user/exportDetailList'/>?isAdmin="+"${isAdmin}"
	jboxTip("导出中…………");
	//jboxSubmit($("#appointForm"), url, null, null, false);
	jboxExp($("#appointForm"),url);
//	jboxEndLoading();
}

function backOpt(){
	var checkLen = $(":checkbox[class='check']:checked").length;
	if(checkLen == 0){
		jboxTip("请勾选预约学员信息！");
		return;
	}
	var recordLst = [];
	var len = 0;
	$(":checkbox[class='check']:checked").each(function(){
		if($(this).attr("statusId") == 'Passing'){
			len ++;
		}
		recordLst.push(this.value);
	});
	if(len > 0){
		jboxTip("该状态无需撤销！");
		return;
	}
	jboxConfirm("确认撤销成待审核状态？",function() {//通过
		var url = "<s:url value='/njmuedu/user/auditBack'/>?isAdmin="+"${isAdmin}";
		jboxPostJson(url,JSON.stringify(recordLst), function (resp) {
			setTimeout(function(){
				toPage1(1);
			},1000);
		}, null, true);
	});
}
</script>
<%--</head>--%>
<body id="initCont">
<div class="registerMsg-m2 fl">
	<div class="registerMsg-m-inner registerBgw">
	<%--<div class="registerMsg-tabs">	--%>
	<div class="registerMsg-tabs">
		<div class="module-tabs">
				<ul class="fl type">
					<li class="on">学员预约信息(课程名称：${subject.subjectName})</li>
				</ul>
		</div>
		<div class="module-tabs">
			<form id="appointForm" >
				<input type="hidden" name="subjectFlow" value="${param.subjectFlow}"/>
				<input id="currentPage1" type="hidden" name="currentPage1" value="1"/>
				<%--<input type="hidden" id="isAdmin" name="isAdmin" value="${param.isAdmin}">--%>
					<tr >
						<td style="...">
							<span style=""></span>审核状态：
							<select name="auditStatusId" style="width:137px;" class="select">
								<option value="">全部</option>
								<c:forEach items="${njmuEduAuditStatusEnumList}" var="status">
									<option value="${status.id}" ${param.auditStatusId eq status.id ?'selected':''}>${status.name}</option>
								</c:forEach>
							</select>
							<span style="padding-left:10px;"></span>姓名：
							<input type="text" name="doctorName" style="border: 1px solid #ccc;background: #fff;height: 25px;box-sizing: border-box;" value="${param.doctorName}">
							<span style="padding-left:20px;"></span>
						</td>
					</tr>

					<div class="module-tabs f2">
						<ul>
							<li><a  href="javascript:void(0);" onclick="toPage1(1);" class="add"><img src="<s:url value='/jsp/njmuedu/css/images/search.png'/>" />查询</a></li>&#12288;
						</ul>
					</div>
					<div class="module-tabs f2">
						<ul>
							<li><a  href="javascript:void(0);" onclick="auditOpt()" class="add"><img src="<s:url value='/jsp/njmuedu/css/images/add.png'/>" />审核</a></li>&#12288;
						</ul>
					</div>
					<div class="module-tabs f2">
						<ul>
							<li><a  href="javascript:void(0);" onclick="backOpt()" class="add"><img src="<s:url value='/jsp/njmuedu/css/images/add.png'/>" />撤销</a></li>&#12288;
						</ul>
					</div>
					<div class="module-tabs f2">
						<ul>
							<li><a  href="javascript:void(0);" onclick="expAppoint()" class="add"><img src="<s:url value='/jsp/njmuedu/css/images/add.png'/>" />导出</a></li>&#12288;
						</ul>
					</div>
			</form>
		</div>

			<div  id="appointDiv">
				
			</div>
		</div>
	</div>
</div>
</body>	
</html>