
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="bootstrapSelect" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
.cur{color:red;}
.title_tab{
	margin-top: 0;
}

 .text{
	 margin-left: 0;
	 width: auto;
	 height: auto;
	 line-height: inherit;
	 color: black;
 }
.selected a{
	padding: 0;
	background: none;
}
.dropdown-menu > .active > a,.dropdown-menu > .active > a:hover{
	background-color: inherit;
	color: inherit;
}
.btn{
	/*height: 28px !important;*/
	border: 1px solid #e7e7eb !important;
	padding: 0px;
}
.body{
	width: 90%;
	margin-left: auto;
	margin-right: auto;
	padding: 0 0 88px;
}
.container{
	padding-left: 0;
	padding-right: 0;
}
.btn-default{
	background-color: #fff;
}
.form-control,.input{
	height: 28px;
	padding: 0;
}
.bootstrap-select{
	width: 182px !important;
}
.search-div{
	float: left;
	margin-bottom: 18px;
	margin-right: 8px;
}
.clearfix {
	clear: both;
	height: 0;
}
.bootstrap-select>.dropdown-toggle{
	width: 160px !important;
}
</style>
<script type="text/javascript">
	/*(function($){
		$.fn.likeSearchInit = function(option){
			option.clickActive = option.clickActive || null;

			var searchInput = this;
			var spaceId = this[0].id;
			searchInput.on("keyup focus",function(){
				var boxHome = $("#"+spaceId+"Sel");
				boxHome.show();
				if($.trim(this.value)){
					$("p."+spaceId+".item",boxHome).hide();
					var items = boxHome.find("p."+spaceId+".item[value*='"+this.value+"']").show();
					if(!items){
						boxHome.hide();
					}
				}else{
					$("p."+spaceId+".item",boxHome).show();
				}
			});
			searchInput.on("blur",function(){
				var boxHome = $("#"+ spaceId+"Sel");
				if(!$("."+spaceId+".boxHome.on").length){
					boxHome.hide();
				}
			});
			$("."+spaceId+".boxHome").on("mouseenter mouseleave",function(){
				$(this).toggleClass("on");
			});

			$("."+spaceId+".boxHome .item").click(function(){
				var boxHome = $("."+spaceId+".boxHome");
				boxHome.hide();
				var value = $(this).attr("value");
				var input = $("#"+spaceId);
				input.val(value);
				if(option.clickActive){
					option.clickActive($(this).attr("flow"));
				}
// 			var content = $("#clone").html().replace("title",value);
// 			$("#"+input.attr("id")+"Div").append(content);
			});
		};
	})(jQuery);*/
$(document).ready(function(){
	toPage(1);
	/*$("#ksmc").likeSearchInit({
		clickActive:function(flow){
			$("."+flow).show();
		}
	});*/

	$("#activityTypeId,#deptName").selectpicker({
		deselectAllText: "全不选",
		selectAllText: "全选",
		noneResultsText: "没有匹配{0}的选项",
		noneSelectedText: "未选中",
	});
});
function toPage(page) {
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jsres/activityQuery/list'/>?roleFlag=${param.roleFlag}",$("#searchForm").serialize(),false);
}
function editActivity(activityFlow,role,action,scanNum,currentPage){
    if('audit' == action){
        jboxOpen("<s:url value='/jsres/activityQuery/editActivity'/>?activityFlow=" + activityFlow+"&role="+role+"&action="+action+"&currentPage="+currentPage,'审核活动',800,500);
	}
    else{
		if(activityFlow)
			jboxOpen("<s:url value='/jsres/activityQuery/editActivity'/>?activityFlow=" + activityFlow+"&role="+role+"&scanNum="+scanNum+"&currentPage="+currentPage,'编辑活动',800,500);
		else
			jboxOpen("<s:url value='/jsres/activityQuery/editActivity'/>?activityFlow=" + activityFlow+"&role="+role+"&currentPage="+currentPage,'新增活动',800,500);
    }
}
function exportExcel(){
	var url = "<s:url value='/jsres/activityQuery/exportList'/>?roleFlag=${param.roleFlag}";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}

function upload(activityFlow,isUpload){
	var url = "<s:url value='/jsres/activityQuery/upload'/>?activityFlow="+activityFlow+"&isUpload="+isUpload;
	if(isUpload=="Y")
	{
		jboxOpen(url, "上传活动图片",700,550);
	}else{
		jboxOpen(url, "查看活动图片",700,550);
	}
}

function selTag(tag,type,flag){
    $(".selectTag").removeClass("selectTag");
    $(tag).addClass("selectTag");
   if(flag == 'activityManage'){
       toPage(1);
   }else {
       //var url = "<s:url value='/jsres/activityQuery/activityMain'/>?roleFlag=${param.roleFlag}";
       //jboxLoad("content", url, true);
       //jboxOpen(url, "教学活动一览",1000,800);
       var url ="<s:url value='/jsres/activityQuery/activityMain'/>?roleFlag=${param.roleFlag}";
       var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
       jboxMessager(iframe,'教学活动一览',1000,800);
   }

}
</script>
<div class="main_hd">
	<c:if test="${param.roleFlag eq 'teach'}">
		<h2 class="underline">教学活动维护</h2>
		<div class="title_tab">
			<ul id="reducationTab">
				<li  id="selectTab1"class="selectTag" onclick="selTag(this,'selectTab1','activityManage');"><a id="activityManage">教学活动维护</a></li>
				<li  id="tab1" onclick="selTag(this,'tab1','EventCalendar');"><a id="EventCalendar">教学活动一览</a></li>
			</ul>
		</div>
	</c:if>
	<c:if test="${ param.roleFlag eq 'head'}">
		<h2 class="underline">教学活动管理</h2>
		<div class="title_tab">
			<ul id="reducationTab2">
				<li  id="selectTab2" class="selectTag" onclick="selTag(this,'selectTab2','activityManage');"><a id="activityManage2">教学活动管理</a></li>
				<li  id="tab2" onclick="selTag(this,'tab2','EventCalendar');"><a id="EventCalendar2">教学活动一览</a></li>
			</ul>
		</div>
	</c:if>
	<c:if test="${!(param.roleFlag eq 'teach'or param.roleFlag eq 'head')}">
		<h2 class="underline">教学活动查询</h2>
		<div class="title_tab">
			<ul id="reducationTab3">
				<li  id="selectTab3" class="selectTag" onclick="selTag(this,'selectTab3','activityManage');"><a id="activityManage3">教学活动查询</a></li>
				<li  id="tab3" onclick="selTag(this,'tab3','EventCalendar');"><a id="EventCalendar3">教学活动一览</a></li>
			</ul>
		</div>
	</c:if>
</div>


<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<input type="hidden" id="orderByClo" name="orderByClo">
		<input type="hidden" id="orderByFall" name="orderByFall">
		

			<table class="searchTable">
				<tr>
					<td class="td_left">
						活动名称：
					</td>
					<td class="td_right">
						<input type="text" name="activityName" value="" class="input" />
					</td>
					<td class="td_left">
						主&nbsp;讲&nbsp;人：
					</td>
					<td class="td_right">
						<input type="text" name="userName" value="" class="input" />
					</td>
					<td class="td_left">
						活动形式：
					</td>
					<td class="td_right">
						<select name="activityTypeId" id="activityTypeId" class="show-menu-arrow" multiple title="请选择" data-live-search="true"
								data-live-search-placeholder="搜索" data-actions-box="true">
							<c:forEach items="${activityTypeEnumList}" var="a">
								<option value="${a.id}" >${a.name}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">
						科&#12288;&#12288;室：
					</td >
<%--					<td>--%>
<%--						<c:if test="${'university' eq roleFlag}">--%>
<%--							<input type="text" name="deptName" value="" class="input" />--%>
<%--						</c:if>--%>
<%--						<c:if test="${'university' ne roleFlag}">--%>
<%--							<select name="deptFlow" class="select">--%>
<%--								<option value="">全部</option>--%>
<%--								<c:forEach items="${depts}" var="dept">--%>
<%--									<option value="${dept.deptFlow}" >${dept.deptName}</option>--%>
<%--								</c:forEach>--%>
<%--							</select>--%>
<%--						</c:if>--%>
<%--					</td>--%>
					<td class="td_right">
						<select name="deptName" id="deptName" class="show-menu-arrow" multiple title="请选择" data-live-search="true"
								data-live-search-placeholder="搜索" data-actions-box="true">
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.deptName}" >${dept.deptName}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">
						开始时间：
					</td>
					<td class="td_right">
                        <input type="text" id="startDate" name="startTime" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 00:00:00'})">
					</td>
				</tr>
				<tr>
					<td class="td_left">
						结束时间：
					</td>
					<td class="td_right">
                        <input type="text" id="endDate" name="endTime" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 23:59:59'})">
					</td>
					<c:if test="${param.roleFlag eq 'local'}">
						<td class="td_left"> 是否有效： </td>
						<td class="td_right">
							<select name="isEffective" class="select">
								<option value="">全部</option>
								<option value="1">有效</option>
								<option value="0">无效</option>
							</select>
						</td>
					</c:if>
					<td class="td_left">
						审核状态：
					</td>
					<td class="td_right">
						<select name="activityStatus" class="select">
							<option value="">全部</option>
							<option value="audit">待审核</option>
							<option value="pass">已通过</option>
							<option value="unpass">未通过</option>
							<option value="over">已过期</option>
						</select>
					</td>
					<td>
						是否上传活动图片：
					</td>
					<td>
						<select name="isUploadImg" class="select">
							<option value="">全部</option>
							<option value="Y">是</option>
							<option value="N">否</option>
						</select>
					</td>
<%--					<td colspan="6">--%>
<%--						--%>
<%--					</td>--%>
				</tr>

				<tr>
<%--					<c:choose>--%>
<%--						<c:when test="${param.roleFlag eq 'teach'or param.roleFlag eq 'head'or param.roleFlag eq 'secretary'}">--%>
							<td id="func" colspan="6">
								<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage(1);"/>&nbsp;
								<c:if test="${addFlag eq 'Y'}">
									<input class="btn_green" style="margin-left: 0px;" type="button" value="新&#12288;增" onclick="editActivity('','${param.roleFlag}');"/>&nbsp;
								</c:if>
								<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportExcel();"/>&nbsp;
							</td>
<%--						</c:when>--%>
<%--						<c:otherwise>--%>
<%--							<td id="func" colspan="3">--%>
<%--								<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage(1);"/>&nbsp;--%>
<%--								<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportExcel();"/>&nbsp;--%>
<%--							</td>--%>
<%--						</c:otherwise>--%>
<%--					</c:choose>--%>
				</tr>
				<%--<c:if test="${param.roleFlag eq 'teach'or param.roleFlag eq 'head'}">
					<tr>
						<td id="func" colspan="3">
							<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportExcel();"/>&nbsp;
						</td>
					</tr>
				</c:if>--%>
			</table>
	</form>
    </div>
   <div id="doctorListZi">
    </div>
</div>
