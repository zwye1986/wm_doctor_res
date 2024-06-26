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
<script type="text/javascript">
	function search(){
		jboxStartLoading();
		$("#searchForm").submit();
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
			/* clickActive:function(flow){
			 $("#studyUserFlow").val(flow).change();
			 } */
		});
		$("li").eq(0).click();
	});
	function selTag(tag,formFlow){
		$('.selectTag').removeClass('selectTag');
		$(tag).addClass('selectTag');
		var url = '<s:url value="/res/ProfessionalBase/evalDetail"/>?formFlow='+formFlow+"&role=${param.role}&viewFlag=Y";
		jboxLoad("content2",url,false);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
	<form id="searchForm" action="<s:url value="/res/ProfessionalBase/evalList" />" method="post">
		<div style="max-width: 880px;min-width: 880px;">
			<div class="inputDiv">
				<label class="qlable">专业基地：</label>
				<input id="orgSel" class="toggleView qtext" type="text" name="resTrainingSpeName" value="${empty param.resTrainingSpeName?resTrainingSpeName:param.resTrainingSpeName}" autocomplete="off"/>
				<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:100px;text-align: left;">
					<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 155px;border-top: none;position: relative;display: none;">
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
							<p class="item" flow="${spe.dictId}" value="${spe.dictName}" style="height: 25px;padding-left: 5px;">${spe.dictName}</p>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="lastDiv">
				<input type="button" class="search" onclick="search();" value="查&#12288;询">&#12288;
			</div>
		</div>
	</form>
	</div>

		<ul id="tags" style="margin-top:20px;margin-left: 20px;">
			<c:forEach items="${baseevalFormCfgList}" var="item">
				<li id="${item.formFlow}" onclick="selTag(this,'${item.formFlow}');"><a>${item.formName}</a></li>
			</c:forEach>
		</ul>
			<div style="clear:both;"></div>
		<c:if test="${empty baseevalFormCfgList}">
			<font color="red" style="margin-left: 25px;margin-top: -20px;">该专业基地暂未配置评价表单</font>
		</c:if>
		<div id="content2"></div>

	</div>
</body>
</body>
</html>