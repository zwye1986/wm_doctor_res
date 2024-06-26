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
			jboxPostLoad("hospitalList" ,"<s:url value='/jsres/hospitalCfg/autoList'/>?isQuery=${param.isQuery}" ,$("#doctorSearchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}

        function choose(type,types,cfg) {
            var length = $("input[name='" + type + "']:checked").length;
            var f = true;
            if(f){
                var recordStatus='Y';
                if(length>0){
                    $("input[name='"+types+"']").attr("checked",true);
                    // if(type=="appLogin") {
                    //     $("input[name='appMenus']").removeAttr("disabled");
                    // }
                    recordStatus='Y';
                }else{
                    $("input[name='"+types+"']").attr("checked",false);
                    // if(type=="appLogin") {
                    //     $("input[name='appMenus']").attr("disabled",true);
                    // }
                    recordStatus='N';
                }
                var orgFlows="";
                var count = 0;
                var title = "确认开放权限？";
                if(recordStatus == "N"){
                    title = "确认取消权限？";
                    $("input[name='"+types+"']").each(function(i){
                        orgFlows +="&orgFlows="+$(this).val();
                        count += 1;
                    });
                }else{
                    $("input[name='"+cfg+"']").each(function(i){
                        orgFlows +="&orgFlows="+$(this).val();
                        count += 1;
                    });
                }
                var url = "<s:url value='/jsres/hospitalCfg/saveNew'/>?recordStatus="+recordStatus+"&cfg="+cfg+orgFlows;
                jboxButtonConfirm("共"+count+"条数据，"+title,"确认","取消", function () {
                    jboxPost(url, null, function(resp){
                        if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                            search();
                            jboxTip("操作成功！");
                        }else{
                            jboxTip("操作失败！");
                        }
                    }, null, false);
                },function () {
                    search();
                },300)
            }
        }

        function oper(obj, orgFlow,cfg){
            var cfgValue = "${GlobalConstant.FLAG_N}";
            var login = $("#jsres_org_app_login_"+orgFlow);
            var menu = $("#jsres_org_app_menu_"+orgFlow);
            if($(obj).attr("checked")=="checked") {
                cfgValue = "${GlobalConstant.FLAG_Y}";
            }

            // if(login.attr("checked")=="checked") {
            //     menu.removeAttr("disabled");
            // }else{
            //     if(menu.attr("checked")=="checked") {
            //         jboxInfo("请先取消app付费功能菜单权限！");
            //         login.attr("checked", true);
            //         return false;
            //     }
            //     menu.attr("disabled", true);
            // }
            $("#cfgCode").val(cfg+orgFlow);
            $("#cfgValue").val(cfgValue);
            if(cfg=="jsres_org_app_login_"){
                $("#desc").val("是否开放基地app登录权限");
            }else if(cfg=="jsres_org_app_menu_"){
                $("#desc").val("是否开放基地付费菜单");
            }else if(cfg=="jsres_org_exam_"){
                $("#desc").val("是否开放基地出科考核表");
            }else if(cfg=="jsres_org_manual_"){
                $("#desc").val("是否开放基地手册");
            }else if(cfg=="jsres_org_graduation_exam_"){
                $("#desc").val("是否开放基地结业理论模拟考核");
            }else if(cfg=="jsres_org_activity_"){
                $("#desc").val("是否开放基地教学活动功能");
            }else if(cfg=="jsres_org_attendance_"){
                $("#desc").val("是否开放基地考勤功能");
            }
            saveOne(orgFlow,cfg,cfgValue);
        }

		function saveOne(orgFlow,cfg){
			var url = "<s:url value='/jsres/hospitalCfg/saveOne'/>?orgFlow="+orgFlow;
			jboxPost(url, $($('#sysCfgForm').html().htmlFormart(cfg+orgFlow)).serialize(), function(resp){
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
        function initCheck() {
            var cl=$("input[name='appLogins']:checked").length;
            var al=$("input[name='appLogins']").length;
            if(cl!= 0&&cl==al)
            {
                $("input[name='appLogin']").attr("checked",true);
            }else{
                $("input[name='appLogin']").attr("checked",false);
            }
            var cl=$("input[name='appMenus']:checked").length;
            var al=$("input[name='appMenus']").length;
            if(cl!= 0&&cl==al)
            {
                $("input[name='appMenu']").attr("checked",true);
            }else{
                $("input[name='appMenu']").attr("checked",false);
            }
            var cl=$("input[name='attendances']:checked").length;
            var al=$("input[name='attendances']").length;
            if(cl!= 0&&cl==al)
            {
                $("input[name='attendance']").attr("checked",true);
            }else{
                $("input[name='attendance']").attr("checked",false);
            }
            var cl=$("input[name='activitys']:checked").length;
            var al=$("input[name='activitys']").length;
            if(cl!= 0&&cl==al)
            {
                $("input[name='activity']").attr("checked",true);
            }else{
                $("input[name='activity']").attr("checked",false);
            }
            var cl=$("input[name='outProcessExams']:checked").length;
            var al=$("input[name='outProcessExams']").length;
            if(cl!= 0&&cl==al)
            {
                $("input[name='outProcessExam']").attr("checked",true);
            }else{
                $("input[name='outProcessExam']").attr("checked",false);
            }
            var cl=$("input[name='outGraduationExams']:checked").length;
            var al=$("input[name='outGraduationExams']").length;
            if(cl!= 0&&cl==al)
            {
                $("input[name='outGraduationExam']").attr("checked",true);
            }else{
                $("input[name='outGraduationExam']").attr("checked",false);
            }
            var cl=$("input[name='schManuals']:checked").length;
            var al=$("input[name='schManuals']").length;
            if(cl!= 0&&cl==al)
            {
                $("input[name='schManual']").attr("checked",true);
            }else{
                $("input[name='schManual']").attr("checked",false);
            }
            if ($("input[name='activity']").is(':checked') && $("input[name='attendance']").is(':checked') && $("input[name='appMenu']").is(':checked')) {
                $("input[name='all']").attr("checked",true);
            } else {
                $("input[name='all']").attr("checked",false);
            }
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
						<%--审核状态：--%>
						<%--<select name="checkStatusId" class="qselect">--%>
							<%--<option value="">请选择</option>--%>
							<%--<option value="NotSubmit" ${param.checkStatusId eq 'NotSubmit'?'selected':''}>未提交</option>--%>
							<%--<option value="Passing" ${param.checkStatusId eq 'Passing'?'selected':''}>待审核</option>--%>
							<%--<option value="Passed" ${param.checkStatusId eq 'Passed'?'selected':''}>审核通过</option>--%>
							<%--<option value="UnPassed" ${param.checkStatusId eq 'UnPassed'?'selected':''}>审核不通过</option>--%>
						<%--</select>--%>
						&#12288;
						<input type="button" value="查&#12288;询" class="search" onclick="toPage(1);"/>
						<%--&#12288;--%>
						<%--<input type="button" value="一 键 提 交" class="search" onclick="updateOrgSubmit();"/>--%>
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