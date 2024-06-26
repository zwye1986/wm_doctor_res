<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>${sysCfgMap['sys_title_name']}</title>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_cxselect" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
		});


		function validate(){
			var doctorFlow=$("#doctorFlow").val();

			top.jboxPost("<s:url value='/jsres/doctorRecruit/validate'/>?doctorFlow="+
					doctorFlow , null , function(resp){
				if("${GlobalConstant.FLAG_Y}"==resp){
					saveRecruit();
				}else{
					top.jboxTip("已添加过报名，无法继续添加！");
					return false;
				}
			} , null , false);
		}
		/**
		 * 保存
		 */
		function saveRecruit(){
			if(false==$("#editForm").validationEngine("validate")){
				return false;
			}
			// 第二志愿 选择基地后必须选择专业
			var orgSelValue = $("#orgSel").val();
			if(orgSelValue){
				var speIdTwoValue = $("#speIdTwo").val();
				if(!speIdTwoValue){
					top.jboxTip("请选择第二志愿专业！");
					return false;
				}
			}

			var inJointOrgTrain = $("#inJointOrgTrain").val();
			if (inJointOrgTrain=='Y'){
				var jointOrgName = $("#jointOrgName").val();
				if (null==jointOrgName || jointOrgName=='' || jointOrgName==undefined){
					top.jboxTip("请选择协同基地！");
					return false;
				}
			}

			top.jboxStartLoading();
			var url = "<s:url value='/jsres/message/saveResDoctorRecruit'/>";
			var data = $("#editForm").serialize();
			$("#saveBtn").attr("disabled",true);
			var year=$("#sessionNumber").val();
			var msg="确认报名？";
			top.jboxConfirm(msg,function(){
				top.jboxPost(url, data, function(resp){
					top.jboxEndLoading();
					if("${GlobalConstant.SAVE_FAIL}" != resp){
						// window.parent.main(resp);
						if("${GlobalConstant.OPRE_FAIL}" == resp){
                            top.jboxTip("已提交三次报名，暂无法报名！");
						}else{
							top.jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
							window.parent.jboxCloseMessager();
							window.parent.getPlanList('',1);
							setTimeout(function(){
								top.jboxClose();
							}, 1000);
						}
					}else{
                        top.jboxTip("${GlobalConstant.SAVE_FAIL}");
					}
				}, null, false);
			},function(){
				top.jboxEndLoading();
				$("#saveBtn").removeAttr("disabled");
			});

		}

		/**
		 * 界别
		 */
		function setSessionNumber(obj){
			var limitDate='${sysCfgMap["res_reg_date"]}';
			if(limitDate!=""){
				var yearFlag="";
				var trainYear=$(obj).val().substring(0,4);
				var trainDate=$(obj).val().substring(5,$(obj).val().length);
				var limitArray=limitDate.split("-");
				var trainArray=trainDate.split("-");
				if(trainArray[0]<limitArray[0]){
					yearFlag=false;
				}else if(trainArray[0]==limitArray[0]){
					if(trainArray[1]<=limitArray[1]){
						yearFlag=false;
					}else{
						yearFlag=true;
					}
				}else{
					yearFlag=true;
				}
				if(yearFlag){
					$("#sessionNumber").val(parseInt(trainYear)+1);
					$("#sessionNumberTd").text(parseInt(trainYear)+1);
				}else{
					$("#sessionNumber").val(trainYear);
					$("#sessionNumberTd").text(trainYear);
				}
			}
		}

		$(function(){
			$("#orgSel").likeSearchInit({
				clickActive:function(flow){
					$("#orgFlowTwo").val(flow).change();
				}
			});
		});

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

		function removeName(orgName){
			debugger
			var $contains = $("p.item:contains('"+orgName+"')");
			var length = $contains.length;
			if(length != 1){
				$("#orgFlowTwo").val("").change();
			}else{
				if($contains.text() == orgName){
					var orgFlow = $contains.attr("flow");
					$("#orgFlowTwo").val(orgFlow).change();
				}else{
					$("#orgFlowTwo").val("").change();
				}
			}
		}

		function searchSpeList(){
			debugger
			var orgFlow = $("#orgFlowTwo").val();
			/* var oldValue = $("#orgFlow").attr("oldValue");
            if(orgFlow == oldValue){
                return false;
            } */
			var url ="<s:url value='/jsres/message/searchSpeList'/>?orgFlow="+orgFlow;
			jboxPost(url,null,function(resp){
				$("#speIdTwo").empty();
				var options = '<option value="">' + '请选择专业' + '</option>';
				var resBaseList = resp['resBaseList'];
				$.each(resBaseList, function (i, item) {
					options += "<option value=" + item.speId + ">" + item.speName + "</option>";
				});
				$("#speIdTwo").append(options);
			},null,false);
		}

		function setspeNameTwo() {
			$("#speNameTwo").val($('#speIdTwo option:selected').text());
		}
	</script>
</head>
<body>
<div class="infoAudit" style="height: 450px;">
	<div class="div_table">
		<h4>报考信息(<font color="red">培训专业慎重选择，一经审核不可更改</font>)</h4>
		<c:set var="auditNotPassed" value="${jsResDoctorAuditStatusEnumNotPassed.id eq doctorRecruit.auditStatusId}"/>
		<form id="editForm" style="position: relative;" method="post">
			<input type="hidden" name="doctorFlow"  id="doctorFlow"value="${sessionScope.currUser.userFlow}"/>
			<input type="hidden" id="orgName" name="orgName" value="${orgName}"/>
			<input type="hidden" id="catSpeName" name="catSpeName" value="${catSpeName}"/>
			<input type="hidden" id="speName" name="speName" value="${speName}"/>
			<!--协同基地-->
			<input type="hidden" id="jointOrgFlow" name="jointOrgFlow" value="${jointOrgFlow}"/>
			<input type="hidden" id="jointOrgName" name="jointOrgName" value="${jointOrgName}"/>
			<!--报名年份-->
			<input type="hidden" id="recruitYear" name="recruitYear" value="${assignYear}"/>
			<input type="hidden" id="sessionNumber" name="sessionNumber" value="${assignYear}"/>

			<%-- 所在地区:市、地区 --%>
			<input type="hidden" id="placeId" name="placeId" value="${placeId}"/>
			<input type="hidden" id="placeName" name="placeName" value="${placeName}"/>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="17%"/>
					<col width="34%"/>
					<col width="20%"/>
					<col width="34%"/>
				</colgroup>
				<tbody>
				<tr>
					<th>报考基地：</th>
					<td>
						<select id="orgFlow" name="orgFlow" class="validate[required] select" style="width: 160px;">
							<option value="${orgFlow}">${orgName}</option>
						</select>
					</td>
					<th>是否在协同基地培训：</th>

					<td>
						<select id="inJointOrgTrain" name="inJointOrgTrain" class="validate[required] select" style="width: 160px;">
							<option value="N">否</option>
							<option value="Y">是</option>
						</select>
					</td>

					<%--<c:if test="${inJointOrgTrain eq 'N'}">
						<td>
							<select id="inJointOrgTrain" name="inJointOrgTrain" class="validate[required] select" style="width: 160px;">
								<option value="${inJointOrgTrain}">否</option>
							</select>
						</td>
					</c:if>
					<c:if test="${inJointOrgTrain eq 'Y'}">
						<td>
							<select id="inJointOrgTrain" name="inJointOrgTrain" class="validate[required] select" style="width: 160px;">
								<option value="${inJointOrgTrain}">是</option>
							</select>
						</td>
					</c:if>--%>
				</tr>
				<tr>
					<c:if test="${jointOrgFlow eq ''}">
						<th class="trainSpe">报考专业：</th>
						<td colspan="3">
							<select name="speId" class="validate[required] select" style="width: 160px;">
								<option value="${speId}">${speName}</option>
							</select>
						</td>
					</c:if>
					<c:if test="${jointOrgFlow ne ''}">
						<th class="trainSpe">报考专业：</th>
						<td>
							<select name="speId" class="validate[required] select" style="width: 160px;">
								<option value="${speId}">${speName}</option>
							</select>
						</td>
						<th>协同基地：</th>
						<td>
							<select id="jointOrgName" name="jointOrgName2" class="select" style="width: 160px;">
								<option value="${jointOrgName}">${jointOrgName}</option>
							</select>
						</td>
					</c:if>

				</tr>
				</tbody>
			</table>
		</form>

		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<c:if test="${empty doctorRecruit.auditStatusId or jsResDoctorAuditStatusEnumNotSubmit.id eq  doctorRecruit.auditStatusId or jsResDoctorAuditStatusEnumNotPassed.id eq  doctorRecruit.auditStatusId}">
				<input type="button" id="saveBtn" class="btn_green" onclick="saveRecruit();" value="保存"/>&nbsp;
			</c:if>
			<c:if test="${param.openType eq 'open'}">
				<input type="button" class="btn_green" onclick="top.jboxClose();" value="关闭"/>&nbsp;
			</c:if>
		</div>

		<!-- 培训类别类型 -->
		<c:forEach items="${trainCategoryTypeEnumList }" var="trainCategoryType">
			<span id="clone_${trainCategoryType.id }" style="display: none;">
			<c:forEach items="${trainCategoryEnumList }" var="trainCategory">
				<c:if test="${trainCategory.typeId eq trainCategoryType.id }">
					<label class="label_${trainCategory.id}"><input type="radio" style="vertical-align: middle;" name="catSpeId" class="validate[required]" value="${trainCategory.id}" onchange="searchResOrgSpeList();"/><span>${trainCategory.name}</span>&nbsp;</label>
				</c:if>
			</c:forEach>
			</span>
		</c:forEach>

		<c:forEach items="${trainCategoryEnumList }" var="trainCategory">
			<span id="cloneSpe_${trainCategory.id }" style="display:none ;">
		    <select class="select" name="trainSpe" style="width: 160px;">
				<option value="">请选择</option>
				<c:set var="dictName" value="dictTypeEnum${trainCategory.id}List" />
				<c:forEach items="${applicationScope[dictName]}" var="trainSpe">
					<option value="${trainSpe.dictId }">${trainSpe.dictName }</option>
				</c:forEach>
			</select>
			</span>
		</c:forEach>
	</div>
</div>
</body>
</html>