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
	<style type="text/css">
		#boxHome .item:HOVER{background-color: #eee;}
		/*#boxHome .item{line-height: normal;  vertical-align:middle; }*/
	</style>

	<script type="text/javascript">
		$(document).ready(function(){
			toPage(1);
		});

		function search(){
			if(!$("#orgFlow").val()){
				$("#orgSel").val("");
			}
			var orgName = $("#orgSel").val();
			if(!orgName){
				$("#orgFlow").val("");
			}
			jboxPostLoad("userList" ,"<s:url value='/jsres/teacherCfg/userList'/>?isQuery=${param.isQuery}" ,$("#doctorSearchForm").serialize() , true);
		}

		function clearOrgFlow(){
			$("#orgFlow").val("");
			var html = '<option value="">--请选择--</option>';
			$("#deptFlow").html(html);
		}
		function selectOrg(){
			var url='<s:url value="/jsres/teacherCfg/queryDeptListJson"/>?orgFlow='+$("#orgFlow").val();
			jboxPost(url,null,function(resp){
				//alert(resp);
				if(resp){
					var html = '<option value="">--请选择--</option>';
					$.each(resp,function(index,obj){
						html += '<option value="'+obj.deptFlow+'">'+obj.deptName+'</option>';
					});
					$("#deptFlow").html(html);
				}
			},null,false);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
		function oper(obj, userFlow,cfg){
			debugger
			var cfgValue = "${GlobalConstant.FLAG_N}";
			if($(obj).attr("checked")=="checked") {
				cfgValue = "${GlobalConstant.FLAG_Y}";
			}
			$("#cfgCode").val(cfg+userFlow);
			$("#cfgValue").val(cfgValue);
			$("#desc").val("是否开放带教app登录权限");
			save(userFlow,cfg);
		}

		function save(userFlow,cfg){
			debugger
            var url = "<s:url value='/jsres/teacherCfg/saveOne'/>?userFlow="+userFlow;
            var title = "确认开放权限？";
            if(cfgValue == "N"){
                title = "确认取消权限？";
            }
            jboxButtonConfirm(title,"确认","取消", function () {
				jboxPost(url, $($('#sysCfgForm').html().htmlFormart(cfg+userFlow)).serialize(), function(resp){
					if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
						jboxTip("操作成功！");
						search();
					}else{
						jboxTip("操作失败！");
					}initCheck();
				}, null, false);
			},function(){
                search();
            },300);
		}

		String.prototype.htmlFormart = function(){
			var html = this;
			for(var index in arguments){
				var reg = new RegExp('\\{'+index+'\\}','g');
				html = html.replace(reg,arguments[index]);
			}
			return html;
		};
		function choose(type,types,cfg){
			var length=$("input[name='"+type+"']:checked").length;
				var recordStatus='Y';
				if(length>0){
					$("input[name='"+types+"']").attr("checked",true);
					recordStatus='Y';
				}else{
					$("input[name='"+types+"']").attr("checked",false);
					recordStatus='N';
				}
				var userFlows="";
				$("input[name='"+types+"']").each(function(i){
					userFlows +="&userFlows="+$(this).val();
				});
				var url = "<s:url value='/jsres/teacherCfg/save'/>?recordStatus="+recordStatus+"&cfg="+cfg+userFlows;
				jboxPost(url, null, function(resp){
					if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
					    search();
						jboxTip("操作成功！");
					}else{
						jboxTip("操作失败！");
					}
				}, null, false);
		}
		function initCheck()
		{
			var cl=$("input[name='appLogins']:checked").length;
			var al=$("input[name='appLogins']").length;
			if(cl!= 0&&cl==al)
			{
				$("input[name='appLogin']").attr("checked",true);
			}else{
				$("input[name='appLogin']").attr("checked",false);
			}
		}
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
				 clickActive:function(flow){
				 	$("#orgFlow").val(flow);
					 selectOrg();
				 }
			});
		});

        function updateTeaSubmit() {
            $("input[name='isSubmitId']").prop("checked",true);
            var userFlows="";
            var count = 0;
            $("input[name='isSubmitId']").each(function(i){
                if(i == 0){
                    userFlows += $(this).val();
                    count += 1;
                }else {
                    userFlows += "&userFlows=" + $(this).val();
                    count += 1;
                }
            });
            var url = "<s:url value='/jsres/powerCfg/updateTeaSubmit'/>?userFlows="+userFlows;
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

        function updateTeaSubmitOne(userFlow) {
            var userFlows = userFlow;
            var url = "<s:url value='/jsres/powerCfg/updateTeaSubmit'/>?userFlows="+userFlows;
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
						培训基地：
						<input id="orgSel" onchange="clearOrgFlow();" class="toggleView xltext" style="width: 150px;" type="text" name="orgName" value="${param.orgName}" autocomplete="off"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:69px">
							<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;
							  vertical-align:middle; background-color: white;min-width: 156px;border-top: none;position: relative;display: none;">
								<c:forEach items="${applicationScope.sysOrgList}" var="org">
									<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="line-height: 20px; padding:10px 0;cursor: default; ">${org.orgName}</p>
								</c:forEach>
							</div>
						</div>
						<input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
						科&#12288;&#12288;室：
						<select name="deptFlow"class="xlselect" id="deptFlow" style="width: 158px;">
							<option value="">--请选择--</option>
						</select>
						姓&#12288;&#12288;名：
						<input type="text" name="userName" class="xltext"  style="width: 150px;">
						用户名：
						<input type="text" name="userCode" class="xltext" style="width:150px;">
						<%--<input type="button" value="查&#12288;询" class="search" onclick="toPage(1);"/>--%>
						<%--<input type="button" value="一 键 提 交" class="search" onclick="updateTeaSubmit();"/>--%>
					</td>
				</tr>
				<tr>
					<td>
						<%--用户名：--%>
						<%--<input type="text" name="userCode" class="xltext" style="width:150px;">--%>
						审核状态：
						<select name="checkStatusId" class="xltext" style="width: 158px;">
							<option value="">请选择</option>
							<option value="NotSubmit" ${param.checkStatusId eq 'NotSubmit'?'selected':''}>未提交</option>
							<option value="Passing" ${param.checkStatusId eq 'Passing'?'selected':''}>待审核</option>
							<option value="Passed" ${param.checkStatusId eq 'Passed'?'selected':''}>审核通过</option>
							<option value="UnPassed" ${param.checkStatusId eq 'UnPassed'?'selected':''}>审核不通过</option>
						</select>
						<input type="button" value="查&#12288;询" class="search" onclick="toPage(1);"/>
						<input type="button" value="一 键 提 交" class="search" onclick="updateTeaSubmit();"/>
					</td>
				</tr>
			</table>
		</form>
		<div id="userList">

		</div>
	</div>
</div>
</body>
</html>