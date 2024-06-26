<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<jsp:param name="jquery_ztree" value="true"/>
</jsp:include>
<script type="text/javascript">
		var setting = {
			view: {
				dblClickExpand: false,
				showIcon: false,
				showTitle:false,
				selectedMulti:false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};

		function beforeClick(treeId, treeNode) {
			var check = (treeNode.id!=0);
			if (!check){
				jboxTip('不能选择根节点');
				return false;
			}
		}
		
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			id = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				id += nodes[i].id + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			if (id.length > 0 ) id = id.substring(0, id.length-1);
			var cityObj = $("#columnSel");
			$("#selectColumnId").val(id);
			//alert(id);
			cityObj.attr("value", v);
		}

		function showMenu() {
			var cityObj = $("#columnSel");
			var cityOffset = $("#columnSel").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		
		$(document).ready(function(){
			var url = "<s:url value='/portal/manage/column/getAllDataJsonByUser'/>";
			    jboxPostJson(url,null,function(data){
			    	if(data){
						zNodes = $.parseJSON(data);
						$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					}
			    },null,false);
		});
	</script>
<style type="text/css">
.line {border: none;}
</style>

<script type="text/javascript">

function view(infoFlow,flag){
	var title = "审核资讯";
	if(flag=='show'){
		title = "查看资讯";
	}
	var w = $('#mainright').width();
	var url ="<s:url value='/portal/manage/info/showEdit?infoFlow='/>"+infoFlow+"&flag="+flag+"&role=${param.role}";
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='500px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,title,w,500);
}
function updateOneStatus(infoFlow,infoStatusId){
    var msg = "";
    if(infoStatusId=='${infoStatusEnumFailure.id}'){
        msg = "确认删除？";
    }else if(infoStatusId=='${infoStatusEnumPassing.id}'){
        msg = "确认提交审核？"
    }else if(infoStatusId=='${infoStatusEnumEdit.id}'){
		msg = "确认撤回？"
	}
	jboxConfirm(msg, function () {
		var stringData = "flows="+infoFlow+"&infoStatusId="+infoStatusId;
		var url = "<s:url value='/portal/manage/info/updateStatus'/>";
		jboxPost(url , stringData , function(resp){
			if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
				search();
			}
		} , null , true);
	});
}
function edit(infoFlow){
	jboxOpen("<s:url value='/portal/manage/info/showEdit?infoFlow='/>"+infoFlow,"编辑资讯", 900, 600);
}
function add(){
	jboxOpen("<s:url value='/portal/manage/info/add'/>?role=${param.role}","新增资讯", 900, 600);
}
function updateStatus(infoStatusId){
	var selectedItems  = new Array();
	selectedItems = $("input:checkbox[name='checkItem']:checked");
	if(selectedItems.length==0){
		jboxTip("请勾选要操作的记录！");
		return false;
	}
	var stringData ="";
	for(i=0;i<selectedItems.length;i++){
		stringData += "flows="+selectedItems[i].value+"&";
	}
	stringData += "infoStatusId="+infoStatusId;
	var msg = "";
	 if(infoStatusId=='${infoStatusEnumFailure.id}'){
		msg = "删除";
	}else if(infoStatusId=='${infoStatusEnumPassing.id}'){
		msg = "提交审核"
	} 
	var url = "<s:url value='/portal/manage/info/updateStatus'/>";
	jboxConfirm("确认将选中的记录"+msg+"吗？",function () {
		jboxPost(url , stringData , function(resp){
			if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
				search();
			}
		} , null , true);		
	});
} 

function search(){
	jboxStartLoading();
	$("#searchForm").submit();
}
function selectAll(){
	var selectedItems  = new Array();
	selectedItems = $("input:checkbox[name='checkItem']");
	var selectedAllObj = $("input:checkbox[name='checkAll']");
	for(i=0;i<selectedItems.length;i++){
		selectedItems[i].checked=selectedAllObj[0].checked;
	}
}
function toPage(page){
	var form = $("#searchForm");
	$("#currentPage").val(page);
	form.submit();
}
function updateRecordStatus(infoFlow,recordStatus){
	var stringData = "infoFlow="+infoFlow+"&recordStatus="+recordStatus;
	var url = "<s:url value='/portal/manage/info/updateRecordStatus'/>";
	jboxPost(url , stringData , function(resp){
		if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
			search();
		}
	} , null , true);
}
function updateIsTop(infoFlow, isTop){
	var data = "infoFlow="+infoFlow+"&isTop="+isTop;
	var url = "<s:url value='/portal/manage/info/modifyInxInfo'/>";
	jboxPost(url , data , function(resp){
		if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
			search();
		}
	} , null , true);
}
</script>

</head>
<body>
<div class="mainright" id="mainright">
	<div class="content">
	<form id="searchForm" action="<s:url value="/portal/manage/info/viewList" />" method="post" >
		 <div class="queryDiv">
			 <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
			 <input type="hidden"  name="infoStatusId" value="${param.infoStatusId}">
			 <input type="hidden"  name="role" value="${param.role}">
			 <input type="hidden"  name="type" value="${param.type}">
		 	<div class="inputDiv">
		 		标&#12288;&#12288;题：<input type="text" name="infoTitle" value="${param.infoTitle}" class="qtext"/>
		 	</div>
			<div class="inputDiv">
				所属栏目：<input type="hidden" name="columnId" id="selectColumnId" value="${param.columnId}">
				<input class="qtext" id="columnSel" name="parentColumnName"   type="text" value="${param.parentColumnName}"  onclick="showMenu(); return false;" autocomplete="off"/>
			</div>
			<div class="inputDiv">
				 开始日期：<input  name="startDate" type="text" value="${param.startDate}" class="qtext ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
			</div>
			 <div class="inputDiv">
				结束日期：<input  name="endDate" type="text" value="${param.endDate}" class="qtext ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
			 </div>
			 <div class="lastDiv">
				 <input type="button" class="search" onclick="search();" value="查&#12288;询">&nbsp;
			 </div>
		</div>
		<c:if test="${param.infoStatusId!= infoStatusEnumPassing.id && param.infoStatusId!= infoStatusEnumPassed.id && param.infoStatusId!= infoStatusEnumCityPassed.id}">
			<div class="funcDiv">
				<input type="button" class="search" onclick="add();" value="新&#12288;增">
				<input type="button" class="search" onclick="updateStatus('${infoStatusEnumFailure.id}');" value="批量删除">
			</div>
		</c:if>
		</form>
	<div class="resultDiv">
		<table class="xllist" style="margin-top: 10px;">
			<thead>
			<tr>
				<c:if test="${param.infoStatusId!= infoStatusEnumPassing.id && param.infoStatusId!= infoStatusEnumCityPassed.id}"><th width="5%"><input type="checkbox" name="checkAll" onclick="selectAll()" /></th></c:if>
				<th width="40%">标题</th>
				<th width="10%">所属栏目</th>
				<th width="12%">资讯时间</th>
				<th width="10%" >状态</th>
				<th width="18%" >操作</th>
			</tr>
			</thead>
			<c:forEach items="${infoList}" var="info">
				<tr>
					<c:if test="${param.infoStatusId!= infoStatusEnumPassing.id && param.infoStatusId!= infoStatusEnumCityPassed.id}"><td><input type="checkbox"  name="checkItem" value="${info.infoFlow}" /></td></c:if>
					<td>${pdfn:cutString(info.infoTitle,30,true,3)}</td>
					<td>${info.columnName}</td>
					<td>${pdfn:transDateTime(info.infoTime)}</td>
					<td>
						<c:if test="${info.infoStatusId==infoStatusEnumEdit.id}">编辑</c:if>
						<c:if test="${info.infoStatusId==infoStatusEnumPassing.id}">待审核</c:if>
						<c:if test="${info.infoStatusId==infoStatusEnumCityPassed.id}">市局审核通过</c:if>
						<c:if test="${info.infoStatusId==infoStatusEnumPassed.id}">审核通过</c:if>
						<c:if test="${info.infoStatusId==infoStatusEnumNoPassed.id}">审核不通过</c:if>
					</td>
					<td>
                        <c:if test="${info.infoStatusId== infoStatusEnumPassed.id }">
                            [<a href="javascript:void(0)" onclick="view('${info.infoFlow}','show')" >查看</a>]
                            [<a href="javascript:void(0)" onclick="edit('${info.infoFlow}')" >修改</a>]
                            [<a href="javascript:void(0)" onclick="updateOneStatus('${info.infoFlow}','${infoStatusEnumFailure.id}')" >删除</a>]
                        </c:if>
                        <c:if test="${info.infoStatusId!= infoStatusEnumPassed.id }">
							<c:if test="${info.infoStatusId==infoStatusEnumPassing.id}">
								[<a href="javascript:void(0)" onclick="updateOneStatus('${info.infoFlow}','${infoStatusEnumEdit.id}')" >撤回</a>]
							</c:if>
                            [<a href="javascript:void(0)" onclick="view('${info.infoFlow}','show')" >查看</a>]
                            <c:if test="${info.infoStatusId==infoStatusEnumEdit.id || info.infoStatusId==infoStatusEnumNoPassed.id }">
                                [<a href="javascript:void(0)" onclick="edit('${info.infoFlow}')" >修改</a>]
                                [<a href="javascript:void(0)" onclick="updateOneStatus('${info.infoFlow}','${infoStatusEnumPassing.id}')" >提交审核</a>]
                                [<a href="javascript:void(0)" onclick="updateOneStatus('${info.infoFlow}','${infoStatusEnumFailure.id}')" >删除</a>]
                            </c:if>
                        </c:if>
                        <c:if test="${info.infoStatusId== infoStatusEnumPassing.id and param.role == 'city'}">
                            [<a href="javascript:void(0)" onclick="view('${info.infoFlow}','pass')" >审核</a>]
                        </c:if>
						<c:if test="${info.infoStatusId== infoStatusEnumCityPassed.id and param.role == 'admin'}">
							[<a href="javascript:void(0)" onclick="view('${info.infoFlow}','pass')" >审核</a>]
						</c:if>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty infoList}">
				<tr>
					<td colspan="7">无符合条件的资讯信息</td>
				</tr>
			</c:if>
		</table>
		<c:set var="pageView" value="${pdfn:getPageView(infoList)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</div>
	</div> 
</div>
<div id="menuContent" class="menuContent" style="display:none; position:absolute;">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>
</body>
</html>