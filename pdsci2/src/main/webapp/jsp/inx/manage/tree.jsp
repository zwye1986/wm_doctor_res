<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<SCRIPT type="text/javascript">
		<!--
		var setting = {
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false,
				dblClickExpand: dblClickExpand,
				showIcon: false,
				showTitle:true
				
			},
			edit: {
				enable: true,
				editNameSelectAll: true,
				showRemoveBtn: showRemoveBtn,
				showRenameBtn: showRenameBtn,
				renameTitle:"修改",
				removeTitle:"删除"
			},
			data: {
				simpleData: {
					enable: true
				},
				key: {
					title:"t"
				}
			},
			callback: {
				beforeDrag: beforeDrag,
				beforeEditName: beforeEditName,
				beforeRemove: beforeRemove,
				beforeRename: beforeRename,
				onRemove: onRemove,
				onRename: onRename
			}
		};

		
		var log, className = "dark";
		var myTreeNode;
		function beforeDrag(treeId, treeNodes) {
			return false;
		}
		function beforeEditName(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			var id = treeNode.id;
			var requestData = {"columnId":id};
			var url = "<s:url value='/inx/manage/column/getColumnJson'/>";
			jboxPost(url,requestData,function(data){
				if(data){
					$("#name").val(data.columnName);
					$("#ordinal").val(data.ordinal);
					$("#flow").val(data.columnFlow);
					$("#par_id").val(data.parentColumnId);
					if(data.parentColumnId==null){
						$("#par_id").val("0");
					}
					myTreeNode = treeNode;
					$("#operation").html("一修改栏目");
					$("#editTable").show();
				}
			},null,false);
			return false;
		}
		function beforeRemove(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			jboxConfirm("确认删除 " + treeNode.name + " 吗？" , function(){
				var ids = [];
				if(treeNode.children){
					$.each(treeNode.children,function(i,obj){
						ids.push(obj.id);
					});
				}
				ids.push(treeNode.id);
				var requestData = JSON.stringify(ids);
				var url = "<s:url value='/inx/manage/column/deleteJson'/>";
				jboxPostJson(url , requestData , function(resp){
					if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						zTree.removeChildNodes(treeNode);
						zTree.removeNode(treeNode);
					}
				} , null , true);
			});
			return false;
		}
		function onRemove(e, treeId, treeNode) {
			showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		}
		function beforeRename(treeId, treeNode, newName, isCancel) {
			className = (className === "dark" ? "":"dark");
			showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
			if (newName.length == 0) {
				alert("节点名称不能为空.");
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				setTimeout(function(){zTree.editName(treeNode)}, 10);
				return false;
			}
			return true;
		}
		function onRename(e, treeId, treeNode, isCancel) {
			showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
		}
		function showRemoveBtn(treeId, treeNode) {
			return treeNode.id!=0;
		}
		function showRenameBtn(treeId, treeNode) {
			return treeNode.id!=0;
		}
		function showLog(str) {
			if (!log) log = $("#log");
			log.append("<li class='"+className+"'>"+str+"</li>");
			if(log.children("li").length > 8) {
				log.get(0).removeChild(log.children("li")[0]);
			}
		}
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}

		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='添加下级' style='padding-left:0px' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			myTreeNode = treeNode;
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				var editTable = $("#editTable");
				editTable.hide();
				//alert(treeNode.level);
				if(treeNode.level>=3){
					jboxTip("栏目最多允许添加3级！");
					return false;
				}
				var nameObj = $("#name");
				var ordinalObj = $("#ordinal");
				var parIdObj = $("#par_id");
				var flowObj = $("#flow");
				nameObj.val("");
				ordinalObj.val("");
				parIdObj.val(treeNode.id);
				flowObj.val("");
				$("#operation").html("一添加栏目");
				editTable.show();
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		function selectAll() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
		}
		
		function dblClickExpand(treeId, treeNode) {
			return treeNode.level > 0;
		}
		function hideObj(objName){
			$(objName).hide();
			$("#operation").html("");
		}
		function submitForm(){
			var editForm = $("#editForm");
			if(editForm.validationEngine("validate")){
				var name = $.trim($("#name").val());
				var ordinal =  $.trim($("#ordinal").val());
				var flow = $("#flow").val();
				var par_id = $("#par_id").val();
				var requestData ={"columnName":name,"columnFlow":flow,"ordinal":ordinal,"parentColumnId":par_id};
				var url = "<s:url value='/inx/manage/column/editJson'/>";
				jboxPost(url , requestData , function(resp){
					if(resp!=""&&resp!="0" ){
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						if(flow!=""){
							zTree.editName(myTreeNode);
							zTree.cancelEditName(name);
						}else{
							zTree.addNodes(myTreeNode, {id:resp, pId:par_id, name:name});
						}
						jboxTip('${GlobalConstant.OPRE_SUCCESSED}');
						hideObj('#editTable');
						window.location.href = '<s:url value="/inx/manage/column/list"/>';
					}else{
						jboxTip('${GlobalConstant.OPRE_FAIL}');
					}
				} , null , false);
			}
		}
		$(document).ready(function(){
			var url = "<s:url value='/inx/manage/column/getAllDataJson'/>"
				jboxPostJson(url,null,function(data){
			    	if(data){
						zNodes = $.parseJSON(data);
						$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					}
			    },null,false);
			$("#selectAll").bind("click", selectAll);
		});
		//-->
	</SCRIPT>
<style type="text/css">
	.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
	.ztree li ul.level0 {padding:0; background:none;}
	.line {border: none;}
	.ztree { padding: 10px 0 0 20px; }
	.ztree *{font-size: 13px; font-family: 微软雅黑;}
	#eleSortable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
 	#eleSortable li { margin: 0 3px 3px 3px; padding: 0.4em; padding-left: 1.5em; font-size: 1.4em; height: 18px; }
 	#eleSortable li tr td span { position: absolute; margin-left: -1.3em; }
</style>
</head>
<body>
	<div id="main">
		<div class="mainright">
			<div class="content">
				<div class="title1 clearfix">
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td valign="top" width="450">
								<table width="450" cellspacing="0" cellpadding="0" class="bs_tb" >
									<tr>
										<th class="bs_name">栏目维护</th>
									</tr>
									<tbody>
										<tr>
											<td>
												<div>
													<ul id="treeDemo" class="ztree"></ul>
												</div>	
											</td>
										</tr>			
									</tbody>													
								</table>
							</td>
							<td width="10">
							</td>
							<td valign="top">
								<table width="450" cellspacing="0"
									cellpadding="0" class="bs_tb">
									<tr>
										<th colspan="11" class="bs_name">相关操作<span id="operation"></span></th>
									</tr>
									<tr>
										<td>
											<form id="editForm">
											<input type="hidden" id="par_id">
											<input type="hidden" id="flow">
											<table class="basic" style="display: none;" id="editTable"> 
													<tr>
														<th>栏目名称：</th>
														<td><input type="text" id="name" class="validate[required] text-input xltext"></td>
													</tr>
													<tr>
														<th>排&#12288;&#12288;序：</th>
														<td><input type="text" id="ordinal" class="validate[required,custom[integer],min[1]] xltext"></td>
													</tr>
													<tr>
														<td colspan="2"><input type="button" class="search" value="提&#12288;交" onclick="submitForm()">&#12288;&#12288;<input type="button" value="取&#12288;消" class="search"  onclick="hideObj('#editTable')"></td>
													</tr>
											</table>
											</form>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>