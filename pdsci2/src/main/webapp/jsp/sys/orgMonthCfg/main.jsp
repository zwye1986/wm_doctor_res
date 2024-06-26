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
	#boxHome .item:HOVER{background-color: #eee;}
	.selSysDept span{color: red;}
</style>

<script type="text/javascript">

	$(document).ready(function(){
		toPage(1);
	});
	function toPage(page) {
		if(page== undefined){
			page=1;
		}
		$("#currentPage").val(page);
		search();
	}
	function search(){
		jboxPostLoad("spePracticeDiv" ,"<s:url value='/sys/orgMonthCfg/list'/>?isQuery=${param.isQuery}" ,$("#doctorSearchForm").serialize() , true);
	}

	function operPerm(obj, orgFlow, operType){
		var cfgValue = "${GlobalConstant.FLAG_N}";
		if($(obj).attr("checked")=="checked") {
			cfgValue = "${GlobalConstant.FLAG_Y}";
		}
		$("#cfgCode").val("jswjw_"+orgFlow+operType);
		$("#cfgValue").val(cfgValue);
		if(operType=="_M001"){
			$("#desc").val("是否显示月度考核表");
		}
		save(orgFlow, operType);
	}
	function save(orgFlow, operType){
		var url = "<s:url value='/sys/cfg/saveOne'/>";
		jboxPost(url, $($('#sysCfgForm').html().htmlFormart("jswjw_"+orgFlow+operType)).serialize(), function(resp){
			if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
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
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form  id="doctorSearchForm" method="post" onkeydown="if(event.keyCode==13)return false;">
			<table class="basic" style="width:100%;margin-top: 10px;">
				<tr>
						<th style="width: 10%">基地名称：</th>
						<td style="width: 10%">
							<input id="orgSel" class="toggleView xltext" type="text" name="orgName" autocomplete="off"/>
							<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;top:0px;">
								<div id="boxHome" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
									<c:forEach items="${allSysOrgList}" var="org">
										<p class="item" flow="${org.orgFlow}" value="${org.orgName}"style="line-height: 20px; padding:10px 0;cursor: default; ">${org.orgName}</p>
									</c:forEach>
								</div>
							</div>
					   </td>
						<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>

					<td colspan="6" style="text-align: left">
						<input type="button" class="search" onclick="toPage(1);" value="查&#12288;询">
					</td>
				</tr>
			</table>
		</form>
		<form id="sysCfgForm" >
			<input type="hidden" id="cfgCode" name="cfgCode"/>
			<input type="hidden" id="cfgValue" name="{0}"/>
			<input type="hidden" id="wsId" value="${GlobalConstant.RES_WS_ID }" name="{0}_ws_id"/>
			<input type="hidden" id="desc" name="{0}_desc">
		</form>
		<div id="spePracticeDiv">
			
		</div>
	</div>
	<div style="display: none;">
		<select id="WMFirst_select">
			<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:forEach>
		</select>
		<select id="WMSecond_select">
			<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:forEach>
		</select>
		<select id="AssiGeneral_select">
			<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:forEach>
		</select>
		<select id="DoctorTrainingSpe_select">
			<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:forEach>
		</select>
	</div>
</div>
</body>
</html>