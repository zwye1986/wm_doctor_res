<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
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
		/**
		 *模糊查询加载
		 */
		(function($){
			$.fn.likeSearchInit = function(option){
				option.clickActive = option.clickActive || null;

				var searchInput = this;
				searchInput.on("keyup focus",function(){
					$("#boxHome").show();
					if($.trim(this.value)){
						$("#boxHome .item").hide();
						var items = $("#boxHome .item[value*='"+this.value+"']").show();
						if(!items){
							$("#boxHome").hide();
						}
					}else{
						$("#boxHome .item").show();
					}
				});
				searchInput.on("blur",function(){
					if(!$("#boxHome.on").length){
						$("#boxHome").hide();
					}
				});
				$("#boxHome").on("mouseenter mouseleave",function(){
					$(this).toggleClass("on");
				});
				$("#boxHome .item").click(function(){
					$("#boxHome").hide();
					var value = $(this).attr("value");
					$("#itemName").val(value);
					searchInput.val(value);
					if(option.clickActive){
						option['clickActive']($(this).attr("flow"));
					}
				});
			};
		})(jQuery);
		//======================================
		//页面加载完成时调用
		$(function(){
			$("#orgSel").likeSearchInit({
				/* clickActive:function(flow){

				 } */
			});
		});
		$(document).ready(function(){
			toPage(1);
		});

		function search(){
			jboxPostLoad("hospitalList" ,"<s:url value='/jsres/hospitalCfg/hospitalList'/>?isQuery=${param.isQuery}" ,$("#doctorSearchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}

		function save(userFlow,cfg){
			var url = "<s:url value='/jsres/hospitalCfg/save'/>?orgFlow="+userFlow;
			jboxPost(url, $($('#sysCfgForm').html().htmlFormart("jsres_"+userFlow+cfg)).serialize(), function(resp){
				if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
				    search();
					jboxTip("操作成功！");
				}else{
					jboxTip("操作失败！");
				}
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
		function initCheck()
		{
			$("input[name='guoCheng']:checked").parents("tr").find("a").attr("style","cursor:pointer;color: blue;")
					.bind("click", function(){
						openMenuPermission($(this).attr("orgflow"));
					});;
		}
		function operPerm(obj, orgFlow, operType){
			var cfgValue = "${GlobalConstant.FLAG_N}";
			if($(obj).attr("checked")=="checked") {
				cfgValue = "${GlobalConstant.FLAG_Y}";
			}
			var $P001 = $("#"+orgFlow+"_guocheng");
			var $P002 = $("#"+orgFlow+"_downExamFile");
			var $_MENU = $("#"+orgFlow+"_MENU");
			if($P001.attr("checked")=="checked") {
				$_MENU.bind("click", function(){
					openMenuPermission(orgFlow);
				});
				$_MENU.attr("style","cursor:pointer;color: blue;");
			}else{
				$_MENU.prop("onclick",null).off("click");
				$_MENU.attr("style","cursor:default;color: grey;");
			}
			$("#cfgCode").val("jsres_"+orgFlow+operType);
			$("#cfgValue").val(cfgValue);
			if(operType == "_P001"){
				$("#desc").val("是否开放过程管理");
			}
			if(operType == "_downExamFile"){
				$("#desc").val("是否开放出科考试卷下载");
			}
			save(orgFlow, operType);
		}

		function openMenuPermission(orgFlow){
			jboxOpen("<s:url value='/jsres/hospitalCfg/getMenu'/>?isQuery=${param.isQuery}&orgFlow="+orgFlow,"功能列表", 600, 300);
		}

        function updateOrgSubmit() {
            $("input[name='isSubmitId']").prop("checked",true);
            var orgFlows="";
            var count = 0;
            $("input[name='isSubmitId']").each(function(i){
                if(i == 0){
                    orgFlows += $(this).val();
                    count += 1;
                }else {
                    orgFlows += "&orgFlows=" + $(this).val();
                    count += 1;
                }
            });
            var url = "<s:url value='/jsres/hospitalCfg/updateOrgSubmit'/>?orgFlows="+orgFlows;
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

        function updateOrgSubmitOne(orgFlow) {
            var orgFlows = orgFlow;
            var url = "<s:url value='/jsres/hospitalCfg/updateOrgSubmit'/>?orgFlows="+orgFlows;
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

		function getOrgDate(orgFlow) {
			jboxOpen("<s:url value='/jsres/hospitalCfg/getOrgDate'/>?isQuery=${param.isQuery}&orgFlow="+orgFlow,"时效设置", 400, 200);
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
						培训基地：
						<input id="orgSel" class="toggleView xltext" type="text" name="orgName" value="${param.orgName}" autocomplete="off"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;left: 69px;">
							<div id="boxHome" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${allSysOrgList}" var="org">
									<p class="item" flow="${org.orgFlow}" value="${org.orgName}"style="line-height: 20px; padding:10px 0;cursor: default; ">${org.orgName}</p>
								</c:forEach>
							</div>
						</div>
						审核状态：
						<select name="checkStatusId" class="qselect">
							<option value="">请选择</option>
							<option value="NotSubmit" ${param.checkStatusId eq 'NotSubmit'?'selected':''}>未提交</option>
							<option value="Passing" ${param.checkStatusId eq 'Passing'?'selected':''}>待审核</option>
							<option value="Passed" ${param.checkStatusId eq 'Passed'?'selected':''}>审核通过</option>
							<option value="UnPassed" ${param.checkStatusId eq 'UnPassed'?'selected':''}>审核不通过</option>
						</select>
						&#12288;
						<input type="button" value="查&#12288;询" class="search" onclick="toPage(1);"/>
						&#12288;
						<input type="button" value="一 键 提 交" class="search" onclick="updateOrgSubmit();"/>
					</td>
				</tr>
			</table>
		</form>
		<div id="hospitalList">

		</div>
	</div>
</div>
</body>
</html>