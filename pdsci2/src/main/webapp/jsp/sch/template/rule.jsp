
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="true"/>
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
		table.basic th,table.basic td{padding: 0;text-align: center;}
		caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;margin-bottom: 10px;}
		table.none td{padding-right:5px;border: 0px;border-bottom-width: 0;border-left-width: 0;border-right-width: 0x;border-top-width: 0;}
		.noFinished{background:pink;}
		.warringRule{background:orange;}
	</style>
	<c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}" var="unitKey"/>
	<script type="text/javascript">
		String.prototype.cptcp = function(){
			var reg = /\./g;
			return this.replace(reg,"\\.");
		};

		function doBack(){
			var target ;
			<c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag)}">
			target = "<s:url value='/sch/template/localList'/>?roleFlag=${param.roleFlag}";
			</c:if>
			<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
			target = "<s:url value='/res/platform/rotation'/>?roleFlag=${param.roleFlag}";
			</c:if>
			<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.currRoleFlag}">
			target = "<s:url value='/sch/template/localList'/>?roleFlag=${param.roleFlag}&orgFlow=${param.orgFlow}&currRoleFlag=${param.currRoleFlag}";
			</c:if>
			location.href = target;
		}

		function addRotationDept(){
			jboxOpen("<s:url value='/sch/template/deptcfg_edit'/>?rotationFlow=${param.rotationFlow}&isAdd=${GlobalConstant.FLAG_Y}&roleFlag=${param.roleFlag}","科室管理",$('.mainright').width(),600);
		}

		function editRotationDept(recordFlow,groupFlow){
			jboxOpen("<s:url value='/sch/template/deptcfg_edit'/>?rotationFlow=${param.rotationFlow}&roleFlag=${param.roleFlag}&groupFlow="+groupFlow,"科室管理",$('.mainright').width(),600);
		}

		function delRotationDept(recordFlow,groupFlow,rotationFlow){
			jboxConfirm("确认删除?",function () {
				var url = "<s:url value='/sch/template/delRotationDept'/>?recordFlow="+recordFlow+"&groupFlow="+groupFlow+"&rotationFlow="+rotationFlow;
				jboxPost(url,null,function(resp){
					if(resp == '${GlobalConstant.DELETE_SUCCESSED}'){
						refresh('${param.rotationFlow}');
					}
				},null,true);
			});
		}

		//查看编辑要求标准
		function rotationStandard(recordFLow){
			jboxOpen("<s:url value='/sch/template/rotationStandard'/>?roleFlag=${param.roleFlag}&recordFlow="+recordFLow,"轮转规范",1000,500);
		}

		//配置方案
		function configuration(recordFLow,rotationFlow,inputId,groupFLow){
			var typeId = $("input[name='"+inputId+groupFLow+"practice']:checked").val();
			jboxOpen("<s:url value='/sch/template/configuration'/>?roleFlag=${param.roleFlag}&recordFlow="+recordFLow+"&rotationFlow="+rotationFlow+"&typeId="+typeId,"数据类型表单",1000,500);
		}

		//编辑要求
		// 	function rotationRequire(recordFLow){
		// 		location.href = "<s:url value='/sch/template/rotationRequire'/>?roleFlag=${param.roleFlag}&speId=${param.speId}&sessionNumber=${param.sessionNumber}&rotationFlow=${param.rotationFlow}&recordFlow="+recordFLow;
		// 	}
		$(function(){
			//侧滑初始化
			$("#reqCfg").slideInit({
				width:800,
				speed:500,
				outClose:true,
				haveZZ:true
			});

			<c:forEach items="${schStageEnumList}" var="stage">
			$("#${stage.id}").append($(".ord${stage.id}"));
			</c:forEach>

			checkLengthToSort();
		});
		//加载后滑出
		function rotationRequire(recordFLow,inputId,groupFLow){
			var typeId = $("input[name='"+inputId+groupFLow+"practice']:checked").val();
			jboxGet("<s:url value='/sch/template/rotationRequire'/>?roleFlag=${param.roleFlag}&rotationFlow=${param.rotationFlow}&recordFlow="+recordFLow+"&typeId="+typeId,null,function(resp){
				$("#reqCfg").html(resp).rightSlideOpen();
				jboxEndLoading();
			},function(){jboxEndLoading();},false);
		}
		//加载页面
		function reqReload(url){
			jboxLoad("reqCfg",url,false);
		}

		//刷新页面
		function refresh(rotationFlow){
			window.location.href="<s:url value='/sch/template/rule'/>?roleFlag=${param.roleFlag}&rotationFlow="+rotationFlow;
		}

		//移动科室
		function moveDept(type,bodyId,ele){
			bodyId = bodyId.cptcp();
			var bodyTr = $(ele).closest(".body");
			var trIndex = $("."+bodyId+" .body").index(bodyTr);
			$("."+bodyId+" .body:first td:last").hide();
			if("up" == type){
				if(0==trIndex){
					$("."+bodyId+" .body:first td:last").show();
					return jboxTip("不可上移！");
				}
				var replaceEle = $("."+bodyId+" .body:eq("+(trIndex-1)+")");
				replaceEle.before(bodyTr);
				$("."+bodyId+" .body:first td:last").show();
			}else if("down" == type){
				if($("."+bodyId+" .body").length-1 == trIndex){
					$("."+bodyId+" .body:first td:last").show();
					return jboxTip("不可下移！");
				}
				var replaceEle = $("."+bodyId+" .body:eq("+(trIndex+1)+")");
				replaceEle.after(bodyTr);
				$("."+bodyId+" .body:first td:last").show();
			}
			var recordFlows = "";
			$(".record").each(function(){
				recordFlows+=("&recordFlows="+this.value);
			});
			jboxPost("<s:url value='/sch/template/sortRotationDept'/>",recordFlows,null,null,false);
		}

		//轮转方案说明
		function openDetail(rotationName,rotationFlow){
			var url = "<s:url value='/sch/template/editRotation'/>?roleFlag=${param.roleFlag}&viewFlag=${GlobalConstant.FLAG_Y}&rotationFlow="+rotationFlow;
			jboxOpen(url, rotationName, 800, 500);
		}

		//科室关联配置加载
		function relCfg(deptBody){
			deptBody = deptBody.cptcp();
			$("#selDeptList").attr("bodyId",deptBody);
			$(".relOption").hide();
			$("."+deptBody+" .relOption").toggle().html($("#selDeptList"));
			var standardDeptId = $("."+deptBody).attr("standardId") || "";
			jboxPost("<s:url value='/sch/template/relSchDept'/>?orgFlow=${param.orgFlow}&standardDeptId="+standardDeptId,null,function(resp){
				if(resp){
					$("#selDeptList :checkbox").attr({
						checked:false,
						recordFlow:""
					});
					for(var index in resp){
						$("#"+resp[index].schDeptFlow).attr({
							recordFlow:resp[index].recordFlow,
							checked:true
						});
					}
				}
			},null,false);
		}
		//基层实践或理论学习
		function setPractic(recordFlow, typeId) {
			var url = "<s:url value='/sch/template/setPractict'/>?recordFlow=" + recordFlow + "&typeId=" + typeId
			jboxPost(url, null, function (resp) {
				jboxTip(resp);
			}, null, false);
		}

		//选择或取消关联
		function relSchDept(schDeptFlow){
			if($("#selDeptList :checked").length<1){
				$("#"+schDeptFlow)[0].checked = true;
				return jboxTip("必须保留一条关系！");
			}
			var bodyId = $("#selDeptList").attr("bodyId");
			var standardDeptId = $("."+bodyId).attr("standardId");
			var schDept = $("#"+schDeptFlow)[0];
			var recordFlow = $(schDept).attr("recordFlow") || "";
			var recordStatus = schDept.checked?"${GlobalConstant.RECORD_STATUS_Y}":"${GlobalConstant.RECORD_STATUS_N}";
			var data = {};
			data.recordFlow = recordFlow;
			if(schDept.checked){
				data.schDeptFlow = schDeptFlow;
				data.standardDeptId = standardDeptId;
			}
			if($("."+bodyId+" .schDeptFlow option[value='"+schDeptFlow+"']").length){
				$("."+bodyId+" .schDeptFlow option[value='"+schDeptFlow+"']").remove();
			}else{
				$("."+bodyId+" .schDeptFlow").append('<option value="'+schDeptFlow+'">'+$("#"+schDeptFlow).attr("schDeptName")+'</option>');
			}
			data.recordStatus = recordStatus;
			data.orgFlow = "${param.orgFlow}" || "";
// 		jboxStartLoading();
			jboxPost("<s:url value='/sch/cfg/saveDeptRel'/>",data,function(resp){
				if(resp=="${GlobalConstant.FLAG_N}"){
					var standarDeptName = $("."+bodyId+" .standarDeptName").text();
					$("#"+schDeptFlow)[0].checked = false;
					return jboxTip("标准科室<font color='blue'>"+standarDeptName+"</font>未维护！");
				}
				if(resp){
					$(schDept).attr("recordFlow",resp);
					var text = "";
					$("#selDeptList :checked").each(function(){
						text+=(","+$(this).attr("schDeptName"));
					});
					text = text.substr(1) || "未关联";
					var standardDeptIdClass = standardDeptId.cptcp();
					$(".relRoom."+standardDeptIdClass).text(text);

					$("."+bodyId).attr("maxsel",$("#selDeptList :checked").length);
				}
				var bodyData = $("."+bodyId).data("data") || {};
				jboxPost("<s:url value='/sch/template/reUpdateRotationDept'/>",{
					rotationFlow:"${rotation.rotationFlow}",
					standardDeptId:standardDeptId,
					groupFlow:bodyData.groupFlow || "",
					orgFlow:"${param.orgFlow}" || "",
				},function(resp){
					$("."+bodyId+" .body").replaceWith($(resp));
// 				jboxEndLoading();
				},null,false);
			},null,false);
		}

		//轮转科室微调
		function ruleCfg(bodyId){
			bodyId = bodyId.cptcp();
			$("."+bodyId).data("oldBody",$("."+bodyId+" .body").clone(true));
			$("."+bodyId).data("removeList",[]);

			$("."+bodyId+" .data").toggle();
		}

		//取消调整
		function cancelCfg(bodyId){
			bodyId = bodyId.cptcp();
			//jboxConfirm("确认取消设置？",function(){
			$("."+bodyId+" .body").replaceWith($("."+bodyId).data("oldBody"));
			//},null);
		}

		//添加调整
		function addSchDept(addTr){
			var body = $(addTr).closest("tbody");
			if(body.find(".body").length < body.attr("maxsel")){
				var newTr = $(addTr).closest(".body").clone(true);
				newTr.find("td:last").hide();
				newTr.find(".record,.schDeptFlow,.schMonth").val("");
				$(addTr).closest("tbody").append(newTr);
			}else{
				jboxTip("轮转科室不可多于关联轮转科室数！");
			}
			checkLengthToSort();
		}

		//移除调整
		function removeCfg(bodyId,trId){
			bodyId = bodyId.cptcp();
			if($("."+bodyId+" .body").length>1){
				var removeTr = $(trId).closest(".body");
				var record = removeTr.find(".record").val();
				if(record){
					$("."+bodyId).data("removeList").push(record);
				}
				//removeTr.next().find("td:last").show();
				removeTr.remove();
				$("."+bodyId+" .body td:last").hide();
				$("."+bodyId+" .body:first td:last").show();
			}else{
				jboxTip("必须保留一个轮转科室！");
			}
			checkLengthToSort();
		}

		//检测重复选取
		function checkResel(bodyId,select){
			bodyId = bodyId.cptcp();
			if($("."+bodyId+" [value='"+select.value+"']:selected").length>1){
				select.value="";
				jboxTip("不可添加重复轮转科室！");
			}
		}

		//保存微调
		function saveCfg(bodyId,standardDeptId,groupFlow,isRequired){
			bodyId = bodyId.cptcp();
			var rotationDeptList = [];
			var currFirst = 0;
			var currSecond = 0;
			var currThird = 0;
			var canSave = true;

			$("."+bodyId+" .body").each(function(){
				if(!$(".schDeptFlow",this).val()){
					jboxTip("轮转科室不能为空！");
					return canSave = false;
				}
				var schMonth = $(":text.schMonth",this).val();
				if(!schMonth || isNaN(schMonth)){
					jboxTip("请输入数字！");
					return canSave = false;
				}
				var first = parseFloat(schMonth);
				currFirst+=first;

				var rotationDept = {};
				rotationDept.recordFlow = $(".record",this).val() || "";
				rotationDept.schMonth = $(".schMonth",this).val() || "";
				rotationDept.standardDeptId = standardDeptId;
				rotationDept.schDeptFlow = $(".schDeptFlow",this).val() || "";
				rotationDept.rotationFlow = "${rotation.rotationFlow}";
				rotationDept.schMonth = first;
				rotationDept.isRequired = isRequired || "";
				rotationDept.groupFlow = groupFlow || "";

				rotationDeptList.push(rotationDept);
			});

			if(!canSave){
				return ;
			}

			var removeList = $("."+bodyId).data("removeList");
			for(var index in removeList){

				var rotationDept = {};
				rotationDept.recordFlow = removeList[index];
				rotationDept.recordStatus = "${GlobalConstant.RECORD_STATUS_N}";

				rotationDeptList.push(rotationDept);
			}

			var data = $("."+bodyId).data("data");
			var selTypeId = data.selTypeId || "";
			if(selTypeId!="${schSelTypeEnumFree.id}"){
				if(currFirst!=data.schMonth){
					jboxTip("轮转时间必须等于标准方案轮转时间！");
					return ;
				}
			}

			var addNum = 0;
			var deptNum = data.deptNum || 0;
			var maxDeptNum = data.maxDeptNum || 0;
			if(groupFlow){
				var groupTab = $("."+bodyId).closest(".xllist");
				groupTab.find(".mainBody").each(function(){
					addNum+=($(this).find(".body").length-1);
				});
				if(maxDeptNum){
					maxDeptNum+=addNum;
				}else{
					deptNum+=addNum;
				}
			}
			jboxConfirm("确认保存修改?",function(){
				jboxPostJson("<s:url value='/sch/template/saveCfg'/>?orgFlow=${param.orgFlow}&groupFlow="+groupFlow+"&deptNum="+deptNum+"&maxDeptNum="+maxDeptNum,JSON.stringify(rotationDeptList),function(resp){
					if(resp=='${GlobalConstant.SAVE_SUCCESSED}'){
						location.reload(true);
					}
				},null,false);
			},null);
		}

		<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.currRoleFlag}">
		function reloadRule(orgFlow){
			window.location.href="<s:url value='/sch/template/rule'/>?rotationFlow=${rotation.rotationFlow}&roleFlag=${param.roleFlag}&currRoleFlag=${param.currRoleFlag}&orgFlow="+orgFlow;
		}
		</c:if>

		$(function(){
			<c:if test="${useCount>0}">
// 			$(".countEditFlag").toggle();
			</c:if>
		});

		function checkLengthToSort(){
			$(".mainBody").each(function(){
				$(".body .sortbtn",this).toggle($(".body",this).length!=1);
			});
		}
	</script>
	<!--
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
                                    修改此页面某些功能时 /sch/template/ruleCfg.jsp 页面需要同步修改
                                    修改此页面某些功能时 /sch/template/ruleCfg.jsp 页面需要同步修改
                                    修改此页面某些功能时 /sch/template/ruleCfg.jsp 页面需要同步修改
                                    修改此页面某些功能时 /sch/template/ruleCfg.jsp 页面需要同步修改
                                    修改此页面某些功能时 /sch/template/ruleCfg.jsp 页面需要同步修改
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
    ************************************************************************************************************
     -->
</head>
<body>
<div class="mainright">
	<div class="content">
		<table style="width:100%;margin: 10px 0;" class="basic" >
			<tr>
				<td style="text-align: left;padding-left:10px;font-weight: bolder;">
					<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.currRoleFlag}">
						选择机构：
						<select onchange="reloadRule(this.value);">
							<c:forEach items="${applicationScope.sysOrgList}" var="org">
								<option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
							</c:forEach>
						</select>
						&#12288;&#12288;
					</c:if>
					<font style="font-weight: bolder;">培训方案名称：</font><font title="点击查看方案说明" style="font-weight: bold;color:#3d91d5;cursor: pointer;"  onclick="openDetail('${rotation.rotationName}','${rotation.rotationFlow}');">${rotation.rotationName}</font>
					&#12288;&#12288;
					<font style="font-weight: bolder;">人员类型：</font>${rotation.doctorCategoryName}
					&#12288;&#12288;
					<font style="font-weight: bolder;">培训专业：</font>${rotation.speName}
					&#12288;&#12288;
					<font style="font-weight: bolder;">年限：</font>${rotation.rotationYear}年
					&#12288;&#12288;
					<input type="button" value="返&#12288;回" class="search" onclick="doBack();"/>
					<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
						<input type="button" value="新&#12288;增" class="search countEditFlag" onclick="addRotationDept();"/>
						<font class="countEditFlag" style="display: none;color: blue;margin-right: 10px;float: right;">Tip：已有<font color="red">${useCount}</font>名医师在使用本方案,不可修改！</font>
					</c:if>

					<c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag)}">
						<div>
							Tip：<span style="color: orange;">黄色</span>表示关联已取消的科室，
							<span style="color: red;">红色</span>表示未关联或无轮转时间科室
						</div>
					</c:if>
				</td>
			</tr>
		</table>
		<div>

			<c:forEach items="${schStageEnumList}" var="stage">
				<div id="${stage.id}">
				</div>
			</c:forEach>

			<c:forEach items="${standardRotationGroupList}" var="rotationGroup">
				<table class="xllist ord${rotationGroup.schStageId}">
					<caption>
					<span style="color: rgb(56, 145, 239);">
						<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
							${rotationGroup.schStageName}：
						</c:if>
						${rotationGroup.groupName}
					</span>

						<font style="float: right;margin-right: 10px;font-weight: normal;color: black;">
								<%-- 						总时间(${applicationScope[unitKey].name}数)：${rotationGroup.schMonth}${applicationScope[unitKey].name} --%>
							<!-- 						&#12288; -->
							<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
								<font class="countEditFlag">
									<a href="javascript:editRotationDept('','${rotationGroup.groupFlow}')" style="color: blue;cursor: pointer;">编辑</a>
									&nbsp;|&nbsp;
									<a href="javascript:delRotationDept('','${rotationGroup.groupFlow}','${rotationGroup.rotationFlow}')" style="color: blue;cursor: pointer;">删除</a>
								</font>
							</c:if>
						</font>
					</caption>
					<tr>
						<th width="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag?'20':'20'}%">
							标准科室
						</th>
						<c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag)}">
							<th width="17%">轮转科室</th>
						</c:if>
						<th width="12%">轮转最小时间(${applicationScope[unitKey].name})</th>
						<th width="12%">轮转最大时间(${applicationScope[unitKey].name})</th>
						<th width="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag?'20':'10'}%">备注</th>
						<th width="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag?'20':'15'}%">操作</th>
						<th width="20%">基层实践或理论学习</th>
					</tr>
					<c:forEach items="${standardGroupDeptMap[rotationGroup.groupFlow]}" var="rotationDept">
						<tbody standardId="${rotationDept.standardDeptId}" maxsel="${deptRelMap[rotationDept.standardDeptId].size()}" class="${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId} mainBody">
						<tr class="head">
							<td rowspan="100">
								<table style="width: 100%;" class="none">
									<tr>
										<td class="standarDeptName" width="50%;" style="text-align: center;">
												${rotationDept.standardDeptName}
										</td>
										<c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag)}">
											<td width="5px;" >|</td>
											<td style="text-align: left">
												轮转时间：
												<c:out value="${rotationDept.schMonth}" default="0"/>月
												<c:set var="sf" value="${sf+rotationDept.schMonth}"/>
												<script>
													$(function(){
														$("."+"${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}".cptcp()).data("data",{
															schMonth:<c:out value="${rotationDept.schMonth}" default="0"/>,
															deptNum:<c:out value="${rotationGroup.deptNum}" default="0"/>,
															maxDeptNum:<c:out value="${rotationGroup.maxDeptNum}" default="0"/>,
															groupFlow:"${localGroupMap[rotationGroup.groupFlow].groupFlow}",
															selTypeId:"${rotationGroup.selTypeId}"
														});
													});
												</script>
												<div style="color: #3d91d5;font-size: 8px;" onclick="relCfg('${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}');">
													关联科室：
													<font class="relRoom ${rotationDept.standardDeptId}" title="配置关联科室" style="cursor: pointer;">
														<c:forEach items="${deptRelMap[rotationDept.standardDeptId]}" var="relMap" varStatus="status">
															<c:if test="${!status.first}">
																,
															</c:if>
															${relMap.value.schDeptName}
														</c:forEach>
														<c:if test="${empty deptRelMap[rotationDept.standardDeptId]}">未关联</c:if>
													</font>
												</div>
												<div relNum="${deptRelMap[rotationDept.standardDeptId].size()}" class="relOption" style="display: none;height: 0px;overflow: visible;width: 0px;"></div>
											</td>
										</c:if>
									</tr>
								</table>
							</td>
						</tr>

						<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
							<tr>
								<td>
									<c:out value="${rotationDept.schMonth}" default="0"/>
									<c:set var="sf" value="${sf+rotationDept.schMonth}"/>
								</td>
								<td>
									<c:out value="${rotationDept.schMaxMonth}" default="0"/>
									<c:set var="maxsf" value="${maxsf+rotationDept.schMaxMonth}"/>
								</td>
								<td style="text-align: left;padding-left: 10px;">
										${rotationDept.deptNote}
								</td>
								<td>
									<a onclick="rotationRequire('${rotationDept.recordFlow}','${rotationDept.standardDeptId}','${rotationGroup.groupFlow}');" style="color: blue;cursor: pointer;">轮转要求</a>
									&nbsp;|&nbsp;
									<a onclick="rotationStandard('${rotationDept.recordFlow}');" style="color: blue;cursor: pointer;">轮转规范</a>
									&nbsp;|&nbsp;
									<a onclick="configuration('${rotationDept.recordFlow}','${rotationDept.rotationFlow}','${rotationDept.standardDeptId}','${rotationGroup.groupFlow}');" style="color: blue;cursor: pointer;">配置表单</a>

									<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag and rotation.publishFlag ne 'Y'}">
										<font class="countEditFlag">
											&nbsp;|&nbsp;
											<a onclick="delRotationDept('${rotationDept.recordFlow}','','${rotationDept.rotationFlow}')" style="color: blue;cursor: pointer;">删除</a>
										</font>
									</c:if>
								</td>
								<td rowspan="100">
									<div>
										<input type="radio"
											   <c:if test="${rotationDept.practicOrTheory eq jszyTCMPracticEnumN.id}">checked="checked"</c:if>
											   name="${rotationDept.standardDeptId}${rotationGroup.groupFlow}practice"
											   value="${jszyTCMPracticEnumN.id}"
											   onclick="setPractic('${rotationDept.recordFlow}','${jszyTCMPracticEnumN.id}');"/>
										无
										&nbsp;|&nbsp;
										<input type="radio"
											   <c:if test="${rotationDept.practicOrTheory eq jszyTCMPracticEnumBasicPractice.id}">checked="checked"</c:if>
											   name="${rotationDept.standardDeptId}${rotationGroup.groupFlow}practice"
											   value="${jszyTCMPracticEnumBasicPractice.id}"
											   onclick="setPractic('${rotationDept.recordFlow}','${jszyTCMPracticEnumBasicPractice.id}');"/>
										基层实践
										&nbsp;|&nbsp;
										<input type="radio"
											   <c:if test="${rotationDept.practicOrTheory eq jszyTCMPracticEnumTheoreticalStudy.id}">checked = "checked"</c:if>
											   name="${rotationDept.standardDeptId}${rotationGroup.groupFlow}practice"
											   value="${jszyTCMPracticEnumTheoreticalStudy.id}"
											   onclick="setPractic('${rotationDept.recordFlow}','${jszyTCMPracticEnumTheoreticalStudy.id}');"/>
										理论学习
									</div>
								</td>
							</tr>
						</c:if>

						<c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag)}">
							<c:set var="localKey" value="${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}"/>
							<c:forEach items="${localRotationDeptListMap[localKey]}" var="groupDept" varStatus="statusFlag">
								<c:set var="isNotFinishedRel" value="${empty groupDept.schDeptFlow}"/>
								<c:set var="isNotFinishedMonth" value="${!(localGroupMap[rotationGroup.groupFlow].selTypeId eq schSelTypeEnumFree.id) && (empty groupDept.schMonth || groupDept.schMonth eq '0')}"/>
								<c:set var="isWarringRule" value="${!isNotFinishedRel && empty deptRelMap[rotationDept.standardDeptId][groupDept.schDeptFlow]}"/>
								<tr class="body" style="min-height: 30px;">
									<input class="record" type="hidden" value="${groupDept.recordFlow}"/>
									<td class="<c:if test="${isNotFinishedRel}">noFinished</c:if>  _s_  <c:if test="${isWarringRule}">warringRule</c:if>"><!--  style="<c:if test="${isNotFinishedRel}">cursor:pointer;</c:if>" <c:if test="${isNotFinishedRel}">onclick="$('.setOperBtn',$(this).closest('.body')).click();"</c:if> -->
										<span style="float: left;margin-left: 10px;">
									<img class="sortbtn" title="上移" src="<s:url value='/css/skin/${skinPath}/images/up.png'/>" onclick="moveDept('up','${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}',this);" style="cursor: pointer;"/>
									<img class="sortbtn" title="下移" src="<s:url value='/css/skin/${skinPath}/images/down.png'/>" onclick="moveDept('down','${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}',this);" style="cursor: pointer;"/>
								</span>
										<font class="data">${groupDept.schDeptName}</font>
										<div class="data"  style="display: none;">
											<img title="移除" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="removeCfg('${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}',this);"  style="display: none;cursor: pointer;margin-right: 10px;" class="data"/>
											<select class="schDeptFlow" style="width: 100px;" onchange="checkResel('${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}',this);">
												<option>
													<c:forEach items="${deptRelMap[rotationDept.standardDeptId]}" var="relMap">
												<option value="${relMap.value.schDeptFlow}"
														<c:if test="${groupDept.schDeptFlow eq relMap.value.schDeptFlow}">selected</c:if>
												>${relMap.value.schDeptName}</option>
												</c:forEach>
											</select>
										</div>
									</td>
									<td class="<c:if test="${isNotFinishedMonth}">noFinished</c:if>"><!--  style="<c:if test="${isNotFinishedMonth}">cursor:pointer;</c:if>" <c:if test="${isNotFinishedMonth}">onclick="$('.setOperBtn',$(this).closest('.body')).click();"</c:if> -->
										<font class="schMonth data">
											<c:out value="${groupDept.schMonth}" default="0"/>
											<c:set var="lf" value="${lf+groupDept.schMonth}"/>
										</font>
										<input type="text" value="${groupDept.schMonth}" class="schMonth data"  style="display: none;width: 20px;"/>
									</td>
									<td style="text-align: left;padding-left: 10px;">
											${rotationDept.deptNote}
									</td>
									<td rowspan="100" <c:if test="${!statusFlag.first}">style="display: none;"</c:if>>
										<div class="data">
											<c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag)}">
												<a class="setOperBtn" style="cursor: pointer;color: blue;" onclick="ruleCfg('${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}');">调整</a>
												&nbsp;|&nbsp;
											</c:if>
											<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
												<a onclick="rotationRequire('${groupDept.recordFlow}');" style="color: blue;cursor: pointer;">轮转要求</a>
												&nbsp;|&nbsp;
											</c:if>
											<a onclick="rotationStandard('${rotationDept.recordFlow}');" style="color: blue;cursor: pointer;">轮转规范</a>
										</div>
										<div class="data" style="display: none;">
											<a style="cursor: pointer;color: blue;" onclick="addSchDept(this);">添加</a>
											<!-- 									&nbsp;|&nbsp; -->
												<%-- 									<a style="cursor: pointer;color: blue;" onclick="removeCfg('${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}',this);">移除</a> --%>
											&nbsp;|&nbsp;
											<a style="cursor: pointer;color: blue;" onclick="saveCfg('${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}','${rotationDept.standardDeptId}','${localGroupMap[rotationGroup.groupFlow].groupFlow}','${localGroupMap[rotationGroup.groupFlow].isRequired}');">确认</a>
											&nbsp;|&nbsp;
											<a style="cursor: pointer;color: blue;" onclick="cancelCfg('${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}');">取消</a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						</tbody>
					</c:forEach>
					<tr>
						<th>备注</th>
						<td colspan="4" style="padding-left: 10px;text-align: left;">
								${rotationGroup.groupNote}
						</td>
					</tr>
					<tr>
						<th>合计</th>
						<td colspan="4" style="text-align: left;padding-left: 10px;">
							<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
								轮转时间：${rotationGroup.schMonth}${applicationScope[unitKey].name}~${rotationGroup.schMaxMonth}${applicationScope[unitKey].name}
								<c:if test="${rotationGroup.isRequired eq GlobalConstant.FLAG_N}">
									&#12288;${rotationGroup.selTypeName}：${rotationGroup.deptNum}<c:if test="${rotationGroup.selTypeId eq schSelTypeEnumFree.id}">~${rotationGroup.maxDeptNum}</c:if>
								</c:if>
							</c:if>
							<c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag)}">
								轮转时间：${localGroupMap[rotationGroup.groupFlow].schMonth}${applicationScope[unitKey].name}
								<c:if test="${localGroupMap[rotationGroup.groupFlow].isRequired eq GlobalConstant.FLAG_N}">
									&#12288;${localGroupMap[rotationGroup.groupFlow].selTypeName}：${localGroupMap[rotationGroup.groupFlow].deptNum}<c:if test="${localGroupMap[rotationGroup.groupFlow].selTypeId eq schSelTypeEnumFree.id}">~${localGroupMap[rotationGroup.groupFlow].maxDeptNum}</c:if>
								</c:if>
							</c:if>
						</td>
					</tr>
				</table>
			</c:forEach>
		</div>
	</div>
</div>
<div id="openContentHome" style="display: none;">
	<div id="selDeptList" bodyId="" class="choose-wrapper">
		<p style="text-align: left;padding-left: 10px;width: 100%;line-height: 30px;">
			<c:forEach items="${deptList}" var="dept" varStatus="status">
			<font title="${dept.externalOrgName}" style="display: inline-block;text-align: left;min-width: 110px;"> <label><input id="${dept.schDeptFlow}" type="checkbox" onclick="relSchDept('${dept.schDeptFlow}');" schDeptName="${dept.schDeptName}"/> ${dept.schDeptName}<c:if test="${GlobalConstant.FLAG_Y eq dept.isExternal}"><font color="red">*</font></c:if></label></font>
			<c:if test="${status.count%3==0}">
		</p><p style="text-align: left;padding-left: 10px;width: 100%;">
		</c:if>
		</c:forEach>
	</p>
		<div style="margin:10px auto;"><a class="search" style="padding: 3px 12px;"  onclick="$('#selDeptList').parent().toggle();">关&nbsp;闭</a>&#12288;Tip：<font color="red"> * </font>表示外院科室！</div>
	</div>
</div>
<div id="reqCfg"></div>
</body>
</html>