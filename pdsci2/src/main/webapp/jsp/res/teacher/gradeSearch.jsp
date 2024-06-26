<%@include file="/jsp/common/doctype.jsp" %>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="jquery_ui_combobox" value="true"/>
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_ztree" value="true"/>
	<jsp:param name="slideRight" value="true"/>
</jsp:include>
	<style type="text/css">
		.module-tabs{ height:49px; line-height:49px; font-size:15px; color:6e6e6e; border-bottom:1px solid #dadada}
		.module-tabs li{ float:left;  cursor:pointer;display:block;}
		.module-tabs .type li{padding:0 15px;}
		.module-tabs li.on{ height:48px; border-bottom:2px solid blue; color:blue;}
		.module-tabs li a{padding:8px 15px;}
		.module-tabs li a.hove1{ height:48px; color:#fff; padding:8px 15px;  background:blue;}
		.module-tabs li a:hover{color:#000;text-decoration:none;}
		.fl{ float:left;}
		.module-content{ margin-top:10px;}
		.module-content ul li{ display:none;}
		.module-content ul li dl{ margin-left:20px; width:368px;}
		.module-content ul li{background:#fff; overflow:hidden; border:1px solid #dfdfdf; position:relative; margin-bottom:10px;}
		.module-content ul li dt{ line-height:40px; color:#000; font-size:15px; margin-bottom:12px;}
		.module-content li dd p{ color:#9e9e9e; line-height:25px;}
		.module-content dd div{ line-height:30px;}
		.module-content dd div img{ vertical-align:middle;}
		.module-content .btn{ position:absolute; right:10px; bottom:10px; height:30px; padding:0 24px;}
		.module-content dt .fr{margin-right:10px;}
		.module-content .lessonimg{border:4px solid #fff;cursor:default;}
		.module-content .graph{width:142px;background:#dadada;vertical-align:middle;height:11px;border-radius:5px;display:inline-block;}
		table.basic th,table.basic td{text-align: center;padding: 0;}
	</style>
<script type="text/javascript">
	$(document).ready(function(){
		if(${param.gradeRole eq 'head'}){
			$(".selectTag").removeClass("selectTag");
			$('#head').addClass('selectTag');
		}else if(${param.gradeRole eq 'teacher'}){
			$(".selectTag").removeClass("selectTag");
			$('#teacher').addClass('selectTag');
		}
	})
	function selTag(gradeRole,obj){
		$(".selectTag").removeClass("selectTag");
		$(obj).parent().addClass('selectTag');
		$("#gradeRole").val(gradeRole);
		var startDate = $("#operStartDate").val();
		var endDate = $("#operEndDate").val();
		if(startDate && endDate && startDate>endDate){
			return jboxTip("开始时间必须小于等于结束时间！");
		}
		gradeSearch();
	}

	function gradeSearch(page) {
		if (page != undefined) {
			$("#currentPage").val(page);
		}
		$('#gradeSearchForm').submit();
	}

	function toPageByOrg(page) {
		$("select[name='deptFlow']").val("");
		if (page != undefined) {
			$("#currentPage").val(page);
		}
		$('#gradeSearchForm').submit();
	}

	function detailShow(span,clazz){
//		$("font",span).toggle();
		$("."+clazz+"Show").fadeToggle(100);
	}
	function operDetail(name,keyCode,date){
		var startDate = $("#operStartDate").val() || "";
		var endDate = $("#operEndDate").val() || "";
		date = date || "";
		var url = "<s:url value='/res/head/gradeSearchDoc'/>?gradeRole=${param.gradeRole}&keyCode="+keyCode+"&operStartDate="+startDate+"&operEndDate="+endDate+"&date="+date+"&role=${role}";
		jboxOpen(url,name+"评分详情",800,500);
	}
	function exportEval() {
		if (${empty datas}) {
			jboxTip("当前无记录!");
			return;
		}
		var gradeRole = $("#gradeRole").val();
		var role = $("#role").val();
		var url = "<s:url value='/res/head/exportEval?gradeRole='/>" + gradeRole + "&role=" + role;
		jboxTip("导出中...");
		jboxSubmit($("#gradeSearchForm"), url, null, null, false);
		jboxEndLoading();
	}
	function loadDept(obj){
		var orgFlow=$(obj).val();
		console.log(orgFlow);
		jboxPost("<s:url value='/res/head/loadDept'/>?orgFlow="+orgFlow,null,function(resp){
			jboxEndLoading();
			if(""!= resp){
				$("select[name=deptFlow] option[value != '']").remove();
				var dataObj = resp;
				for(var i = 0; i<dataObj.length; i++){
					var deptFlow = dataObj[i].deptFlow;
					var deptName = dataObj[i].deptName;
					$option =$("<option></option>");
					$option.attr("value",deptFlow);
					$option.text(deptName);
					$("select[name=deptFlow]").append($option);
				}
			}
		},null,true);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="top-tab">
			<div class="clearfix" style="padding-top: 10px;">
				<ul id="tags">
					<li id="teacher">
						<a onclick="selTag('teacher',this);" href="javascript:void(0)">带教老师</a>
					</li>
					<li id="head">
						<a onclick="selTag('head',this);" href="javascript:void(0)">科室</a>
					</li>
				</ul>
				<div id="tagContent">
					<div class="content">
						<div class="module-content" style="width: 100%;">
							<form id="gradeSearchForm" action="<s:url value="/res/head/gradeSearch"/>" method="post">
								<input type="hidden" id="currentPage" name="currentPage">
								<input type="hidden" id="gradeRole" name="gradeRole" value="${param.gradeRole}">
								<input type="hidden" id="role" name="role" value="${role}">
								<div class="queryDiv" style="min-width: 960px;max-width: 960px;">
									<div class="inputDiv">
										<label class="qlable">培训基地：</label>
										<select name="orgFlow"  id="orgFlow" class="qselect" <c:if test="${param.gradeRole eq 'head'}">onchange="toPageByOrg();"</c:if>>
											<c:forEach items="${orgList}" var="org">
												<option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
											</c:forEach>
										</select>
									</div>
									<c:if test="${param.gradeRole eq 'head'}">
										<div class="inputDiv">
											科&#12288;&#12288;室：
											<select name="deptFlow" class="qselect">
												<option value=""></option>
												<c:forEach items="${depts}" var="dept">
													<option value="${dept.deptFlow}" <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
												</c:forEach>
											</select>
										</div>
									</c:if>
									<c:if test="${param.gradeRole eq 'teacher'}">
										<div class="inputDiv">
											姓&#12288;&#12288;名：
											<input type="text" name="userName" value="${param.userName}" class="qtext"/>
										</div>
										<div class="inputDiv">
											科室名称：
											<input type="text" name="deptName" value="${param.deptName}" class="qtext"/>
										</div>
									</c:if>
									<div class="inputDiv">
									年&#12288;&#12288;级：
									<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="qtext"/>
								</div>
									<div class="inputDiv" style="min-width: 275px;max-width: 275px;">
										评分时间：
										<input type="text" id="operStartDate" name="operStartDate" value="${param.operStartDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"readonly="readonly" class="qtext" style="width: 75px;"/>~<input type="text" id="operEndDate" name="operEndDate" value="${param.operEndDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="qtext" style="width: 75px;"/>
									</div>
									<div class="lastDiv" style="min-width: 182px;max-width: 182px;">
										<input type="button" class="searchInput" value="查&#12288;询" onclick="gradeSearch()">
										<input type="button" class="searchInput" value="导&#12288;出" onclick="exportEval();"/>

									</div>
								</div>
							</form>
							<table class="basic list" width="100%" style="margin-top: 10px;">
								<colgroup>
									<c:if test="${param.gradeRole eq 'teacher'}">
										<c:if test="${empty param.sessionNumber}">
											<c:set value="7" var="col"/>
											<col width="35%"/>
											<col width="15%"/>
											<col width="10%"/>
											<col width="10%"/>
											<col width="10%"/>
											<col width="10%"/>
											<col width="10%"/>
										</c:if>

										<c:if test="${not empty param.sessionNumber}">
											<c:set value="5" var="col"/>
											<col width="40%"/>
											<col width="15%"/>
											<col width="15%"/>
											<col width="15%"/>
											<col width="15%"/>
										</c:if>
									</c:if>
									<c:if test="${param.gradeRole eq 'head'}">
										<c:if test="${empty param.sessionNumber}">
											<c:set value="6" var="col"/>
											<col width="50%"/>
											<col width="10%"/>
											<col width="10%"/>
											<col width="10%"/>
											<col width="10%"/>
											<col width="10%"/>
										</c:if>
										<c:if test="${not empty param.sessionNumber}">
											<c:set value="4" var="col"/>
											<col width="55%"/>
											<col width="15%"/>
											<col width="15%"/>
											<col width="15%"/>
										</c:if>
									</c:if>
								</colgroup>

								<tr>
									<th style="text-align: left;padding-left: 10px;">
										<c:if test="${param.gradeRole eq 'teacher'}">
											老师姓名
										</c:if>
										<c:if test="${param.gradeRole eq 'head'}">
											科室名称
										</c:if>
									</th>
									<c:if test="${param.gradeRole eq 'teacher'}">
										<th >科室信息</th>
									</c:if>
									<c:if test="${empty param.sessionNumber}">
										<th >${FirstTwoYearsDate}级总均分</th>
										<th >${PreviouYearDate}级总均分</th>
										<th >${currDate}级总均分</th>
									</c:if>
									<c:if test="${not empty param.sessionNumber}">
										<th>${param.sessionNumber}级总均分</th>
									</c:if>
									<th >标准分</th>
									<th >总均分</th>
								</tr>

								<c:forEach items="${datas}" var="data">
									<c:if test="${param.gradeRole eq 'teacher'}">
										<c:set var="key" value="${data.userFlow}"/>
										<c:set var="name" value="${data.userName}"/>
									</c:if>

									<c:if test="${param.gradeRole eq 'head'}">
										<c:set var="key" value="${data.deptFlow}"/>
										<c:set var="name" value="${data.deptName}"/>
									</c:if>

									<c:set var="avgScoreKey" value="${key}_Total"/>

									<tr>
										<th style="text-align: left;padding-left: 10px;">
											<span style="cursor: pointer;color: blue;font-size: 1em;line-height: 5px;" onclick="operDetail('${name}','${key}',null);">${name}</span>
										</th>
										<c:if test="${param.gradeRole eq 'teacher'}">
											<th>${data.deptName}</th>
										</c:if>
										<c:if test="${empty param.sessionNumber}">
											<th>
												<a style="cursor: pointer;color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}','${FirstTwoYearsDate}');">${recFirstTwoYearsAvgMap[avgScoreKey]}</a>
											</th>
											<th>
												<a style="cursor: pointer;color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}','${PreviouYearDate}');">${recpreviouYearAvgMap[avgScoreKey]}</a>
											</th>
											<th>
												<a style="cursor: pointer;color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}','${currDate}');">${recCurrDateAvgMap[avgScoreKey]}</a>
											</th>
										</c:if>

										<c:if test="${not empty param.sessionNumber}">
											<th>
												<a style="color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}','${param.sessionNumber}');">${recDateAvgMap[avgScoreKey]}</a>
											</th>
										</c:if>
										<th style="font-weight:normal;">${scoreMap[key]}</th>
										<th>
											<a style="cursor: pointer;color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}',null);">${avgMap[avgScoreKey]}</a>
										</th>
									</tr>







































                                    <%--
                                    <div>

                                    </div>

                                    <c:forEach items="${assessCfgList}" var="title">
                                        <tr>
                                            <c:if test="${param.gradeRole eq 'head'}">
                                                <c:if test="${empty param.sessionNumber}">
                                                    <td class="${key}Show" colspan="5" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
                                                            ${title.name}
                                                    </td>
                                                </c:if>
                                                <c:if test="${not empty param.sessionNumber}">
                                                    <td class="${key}Show" colspan="3" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
                                                            ${title.name}
                                                    </td>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${param.gradeRole eq 'teacher'}">
                                                <c:if test="${empty param.sessionNumber}">
                                                    <td class="${key}Show" colspan="6" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
                                                            ${title.name}
                                                    </td>
                                                </c:if>
                                                <c:if test="${not empty param.sessionNumber}">
                                                    <td class="${key}Show" colspan="4" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
                                                            ${title.name}
                                                    </td>
                                                </c:if>
                                            </c:if>
                                            <td class="${key}Show"  style="font-weight: bold;display: none;padding-right: 20px;">
                                            </td>
                                        </tr>
                                        <c:forEach items="${title.itemList}" var="item">
                                            <c:set var="scoreKey" value="${key}_${item.id}"/>
                                            <tr>
                                                <c:if test="${param.gradeRole eq 'head'}">
                                                    <td class="${key}Show"  style="text-align: left;padding-left: 10px;display: none;">${item.name}</td>
                                                </c:if>
                                                <c:if test="${param.gradeRole eq 'teacher'}">
                                                    <td class="${key}Show" colspan="2" style="text-align: left;padding-left: 10px;display: none;">${item.name}</td>
                                                </c:if>
                                                <c:if test="${empty param.sessionNumber}">
                                                    <td class="${key}Show" style="display: none;padding-right: 0px;">${recFirstTwoYearsAvgMap[scoreKey]}</td>
                                                    <td class="${key}Show" style="display: none;padding-right: 0px;">${recpreviouYearAvgMap[scoreKey]}</td>
                                                    <td class="${key}Show" style="display: none;padding-right: 0px;">${recCurrDateAvgMap[scoreKey]}</td>
                                                </c:if>
                                                <c:if test="${not empty param.sessionNumber}">
                                                    <td class="${key}Show" style="display: none;padding-right: 0px;">${recDateAvgMap[scoreKey]}</td>
                                                </c:if>
                                                <td class="${key}Show" style="display: none;padding-right: 0px;text-align: center;">${item.score}</td>
                                                <td class="${key}Show" style="display: none;text-align: center;padding-right: 0px;">${avgMap[scoreKey]}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:forEach>







--%>







								</c:forEach>
								<c:if test="${empty datas}">
									<tr>
										<td colspan="${col}">
											<c:if test="${param.role eq GlobalConstant.USER_LIST_GLOBAL || param.role eq GlobalConstant.USER_LIST_CHARGE}">
												<c:if test="${empty param.deptFlow}">
													请选择科室
												</c:if>
												<c:if test="${not empty param.deptFlow}">
													无记录
												</c:if>
											</c:if>
											<c:if test="${!(param.role eq GlobalConstant.USER_LIST_GLOBAL || param.role eq GlobalConstant.USER_LIST_CHARGE)}">
												无记录！
											</c:if>
										</td>
									</tr>
								</c:if>
							</table>

							<div class="page" style="padding-right: 40px;">
								<c:set var="pageView" value="${pdfn:getPageView(datas)}" scope="request"></c:set>
								<pd:pagination toPage="gradeSearch"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>