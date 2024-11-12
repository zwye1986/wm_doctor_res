<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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
	.boxHome .item:HOVER{background-color: #eee;}
	.cur{color:red;}
	.searchTable{
		width: auto;
	}
	.searchTable td{
		width: auto;
		height: auto;
		line-height: auto;
		text-align: left;
		max-width: 150px;;
	}
	.searchTable .td_left{
		word-wrap:break-word;
		width:5em;
		height: auto;
		line-height: auto;
		text-align: right;
	}
</style>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#sessionNumber').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
		$('#applyYear').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
		$('#graduationYear').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		})
		getCityArea();
		initOrg();
		toPage();
	});

	var allOrgs=[];
	function initOrg()
	{
		var datas=[];
		<c:forEach items="${orgs}" var="org">
		<c:if test="${sessionScope.currUser.orgFlow!=org.orgFlow }">
		var d={};
		d.id="${org.orgFlow}";
		d.text="${org.orgName}";
		d.cityId="${org.orgCityId}";
		datas.push(d);
		allOrgs.push(d);
		</c:if>
		</c:forEach>
		var itemSelectFuntion = function(){
			$("#orgFlow").val(this.id);
		};
		$.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
	}
	function changeOrg(obj)
	{
		var cityId=$(obj).val();
		var datas=[];
		for(var i=0;i<allOrgs.length;i++)
		{
			var org=allOrgs[i];
			if(org.cityId==cityId||cityId=="")
			{
				datas.push(org);
			}
		}
		$("#orgFlow").val("");
		var itemSelectFuntion = function(){
			$("#orgFlow").val(this.id);
		};
		$.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
	}
	function toPage(page) {
		var data="";
		$("input[class='docType']:checked").each(function(){
			data+="&datas="+$(this).val();
		});
		if(!$("#applyYear").val()){
			jboxTip("请选申请年份！");
			return false;
		}
		if(data==""){
			jboxTip("请选择人员类型！");
			return false;
		}
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/hbres/asseGlobal/AuditList'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),false);
	}
	function exportInfo()
	{
		if(!$("#applyYear").val()){
			jboxTip("请选申请年份！");
			return false;
		}
		var url = "<s:url value='/hbres/asseGlobal/exportList'/>";
		jboxExp($("#searchForm"),url);
	}
	function changeTrainSpes(t){
		var trainCategoryid=$("#trainingTypeId").val();
		if(trainCategoryid==""){
			$("select[name=trainingSpeId] option[value != '']").remove();
			return false;
		}
		$("select[name=trainingSpeId] option[value != '']").remove();
		$("#"+trainCategoryid+"_select").find("option").each(function(){
			$(this).clone().appendTo($("#trainingSpeId"));
		});
		return false;
	}
	function getCityArea(){
		var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
		var provIds = "420000";
		jboxGet(url,null, function(json) {
			// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
			var newJsonData=new Array();
			var j=0;
			var html ="<option value=''></option>";
			for(var i=0;i<json.length;i++){
				if(provIds==json[i].v){
					var citys=json[i].s;
					for(var k=0;k<citys.length;k++){
						var city=citys[k];
						html+="<option value='"+city.v+"'>"+city.n+"</option>";
					}
				}
			}
			$("#cityId2").html(html);
		},null,false);
	}
</script>
<div class="div_search" style="width: 95%;line-height:normal;">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
		<%--省厅市局查询条件--%>
		<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="td_left">地&#12288;&#12288;区：</td>
				<td>
					<select id="cityId2" name="cityId" class="select" onchange="changeOrg(this)" style="width:107px;"></select>
				</td>
				<td class="td_left">培训基地：</td>
				<td>
					<input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 100px"  />
					<input type="hidden" name="orgFlow" id="orgFlow">
				</td>
				<td class="td_left">培训专业：</td>
				<td>
					<select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 106px;">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
							<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<td class="td_left">年&#12288;&#12288;级：</td>
				<td>
					<input type="text" id="sessionNumber" name="sessionNumber"  class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">姓&#12288;&#12288;名：</td>
				<td>
					<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;margin-left: 0px;"/>
				</td>
				<td class="td_left">结业考核&#12288;年&#12288;&#12288;份：</td>
				<td>
					<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
				</td>
				<td class="td_left">报考资格&#12288;材&#12288;&#12288;料：</td>
				<td>
					<select id="qualificationMaterialId" name="qualificationMaterialId" class="select" style="width: 106px;">
						<option value="">请选择</option>
						<%--<option value="176">医师执业证书</option>--%>
						<option value="177">医师资格证书</option>
						<option value="暂无">暂无</option>
					</select>
				</td>
				<td class="td_left">申请年份：</td>
				<td>
					<input type="text" id="applyYear" name="applyYear" class="input" readonly="readonly" value="${pdfn:getCurrYear()}" style="width: 100px;margin-left: 0px"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">证&nbsp;件&nbsp;号&nbsp;：</td>
				<td>
					<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 100px;margin-left: 0px;"/>
				</td>
				<%--<td class="td_left">数据完成&#12288;比例低于：</td>--%>
				<%--<td>--%>
					<%--<input type="text" name="completeBi"  class="input" style="width: 100px;margin-left: 0px;"/>--%>
				<%--</td>--%>
				<%--<td class="td_left">数据审核&#12288;比例低于：</td>--%>
				<%--<td>--%>
					<%--<input type="text" name="auditBi"  class="input" style="width: 100px;margin-left: 0px;"/>--%>
				<%--</td>--%>
				<td class="td_left">审核状态：</td>
				<td>
					<select name="auditStatusId" id="auditStatusId" class="select"  style="width:107px;">
						<option value="">请选择</option>
						<c:forEach items="${jsResAsseAuditListEnumList}" var="type">
							<option value="${type.id}">${type.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>

				<td class="td_left">人员类型：</td>
				<td colspan="3">

					<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
						<label><input name="datas" class="docType" type="checkbox" checked  value="${type.dictId}"/>${type.dictName}&nbsp;</label>
					</c:forEach>
				</td>
				<td colspan="2">
					<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;
					<input class="btn_green" type="button" value="导&#12288;出" onclick="exportInfo();"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="doctorListZi" style="width: 95%">
</div>