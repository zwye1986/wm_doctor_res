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
	</jsp:include>

	<style type="text/css">
		.selSysDept span{color: red;}
		.searchTable{
			border: 0px;
		}
		.searchTable tbody td{
			border: 0px;
			background-color: white;
			color: #575656;
		}
		.searchTable tbody th{
			border: 0px;
			background: white;
			color: #575656;
		}
	</style>

	<script type="text/javascript">
		$(document).ready(function(){
			toPage(1);
		});

		function search(){
			jboxPostLoad("sendSchoolList" ,"<s:url value='/jsres/sendSchoolCfg/sendSchoolList'/>?isQuery=${param.isQuery}" ,$("#doctorSearchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
		function initCheck()
		{
			$("input[name='guoCheng']:checked").parents("tr").find("a").attr("style","cursor:pointer;color: blue;")
					.bind("click", function(){
						openMenuPermission($(this).attr("dictId"));
					});;
		}
		function operPerm(obj, dictFlow,dictId, operType){
			var cfgValue = "${GlobalConstant.FLAG_N}";
			if($(obj).attr("checked")=="checked") {
				cfgValue = "${GlobalConstant.FLAG_Y}";
			}
			var $P001 = $("#"+operType+dictId);
			var $_MENU = $("#"+dictId+"_MENU");
			if($P001.attr("checked")=="checked") {
				$_MENU.attr("onclick","openMenuPermission('"+dictId+"')");
				$_MENU.attr("style","cursor:pointer;color: blue;");
			}else{
				$_MENU.attr("onclick","");
				$_MENU.attr("style","cursor:default;color: grey;");
			}
			$("#cfgCode").val(operType+dictId);
			$("#cfgValue").val(cfgValue);
			if(operType == "jsres_sendSchool_gc_"){
				$("#desc").val("是否开放过程管理");
			}
			save(dictFlow,dictId, operType);
		}

		function save(dictFlow,dictId,cfg){
			var url = "<s:url value='/jsres/sendSchoolCfg/saveOne'/>?dictFlow="+dictFlow+"&dictId="+dictId;
			jboxPost(url, $($('#sysCfgForm').html().htmlFormart(cfg+dictId)).serialize(), function(resp){
				if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
				    search();
					jboxTip("操作成功！");
				}else{
					jboxTip("操作失败！");
				}initCheck();
			}, null, false);
		}

		String.prototype.htmlFormart = function(){
			var html = this;
			for(var index in arguments){
				var reg = new RegExp('\\{'+index+'\\}','g');
				html = html.replace(reg,arguments[index]);
			}
			return html;
		};
		function openMenuPermission(dictId){
			jboxOpen("<s:url value='/jsres/sendSchoolCfg/getMenu'/>?isQuery=${param.isQuery}&dictId="+dictId,"功能列表", 800, 400);
		}

        function updateSchoolSubmit() {
            $("input[name='isSubmitId']").prop("checked",true);
            var dictFlows="";
            var count = 0;
            $("input[name='isSubmitId']").each(function(i){
                if(i == 0){
                    dictFlows += $(this).val();
                    count += 1;
                }else {
                    dictFlows += "&dictFlows=" + $(this).val();
                    count += 1;
                }
            });
            var url = "<s:url value='/jsres/sendSchoolCfg/updateSchoolSubmit'/>?dictFlows="+dictFlows;
            jboxConfirm("共"+count+"条数据，确认提交？", function () {
                jboxPost(url, null, function (resp) {
                    if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                        search();
                        jboxTip("操作成功！");
                    }else{
                        jboxTip("操作失败！");
                    }
                }, null, true);
            });
        }

        function updateSchoolSubmitOne(dictFlow) {
            var dictFlows = dictFlow;
            var url = "<s:url value='/jsres/sendSchoolCfg/updateSchoolSubmit'/>?dictFlows="+dictFlows;
            jboxConfirm("确认提交？", function () {
                jboxPost(url, null, function (resp) {
                    if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                        search();
                        jboxTip("操作成功！");
                    }else{
                        jboxTip("操作失败！");
                    }
                }, null, true);
            });
        }

	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="sysCfgForm" >
			<input type="hidden" id="cfgCode" name="cfgCode"/>
			<input type="hidden" id="cfgValue" name="{0}"/>
			<input type="hidden" id="wsId" value="${GlobalConstant.RES_WS_ID }" name="{0}_ws_id"/>
			<input type="hidden" id="desc" name="{0}_desc">
		</form>
		<form  id="doctorSearchForm" method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<table class="basic searchTable" style="width:100%;margin-top: 10px;">
				<tr>
					<td>
						派送学校名称：
						<input class="xltext" name="dictName" type="text" value="${param.dictName}"/>
						审核状态：
						<select name="checkStatusId" class="xltext" style="width: 158px;">
							<option value="">请选择</option>
							<option value="NotSubmit" ${param.checkStatusId eq 'NotSubmit'?'selected':''}>未提交</option>
							<option value="Passing" ${param.checkStatusId eq 'Passing'?'selected':''}>待审核</option>
							<option value="Passed" ${param.checkStatusId eq 'Passed'?'selected':''}>审核通过</option>
							<option value="UnPassed" ${param.checkStatusId eq 'UnPassed'?'selected':''}>审核不通过</option>
						</select>
						&#12288;
						<input type="button" value="查&#12288;询" class="search" onclick="toPage(1);"/>
						<input type="button" value="一 键 提 交" class="search" onclick="updateSchoolSubmit();"/>
					</td>
				</tr>
			</table>
		</form>
		<div id="sendSchoolList">

		</div>
	</div>
</div>
</body>
</html>