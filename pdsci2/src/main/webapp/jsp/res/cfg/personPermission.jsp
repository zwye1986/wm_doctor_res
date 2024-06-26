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
		<script type="text/javascript">
			$(function(){
				getSwitchOpenCount();//获取本机构开放功能的人数
				if(!$('.batchOper').length){//批量操作内无内容则删除该模块
					$('#batchOperHome').remove();
				}
			});
			function search(page){
				$("#currentPage").val(page || 1);
				$('#docForm').submit();
			}
			function toPage(page) {
				search(page);
			}
			/**
			 * 获取开关启用数量
			 * 所有需统计开启数量的开关需按照  switchs该类元素的写法新增
			 */
			function getSwitchOpenCount(){
				var switchs = $('.switchs');
				if(switchs.length>0){
					var url = '<s:url value="/res/cfg/cfgSwitchUseCount"/>';
					var datas = '';
					switchs.each(function(){
						var dataStr = (datas?'&':'')+'cfgCodePrefix='+this.id;
						datas+=dataStr;
					});
					jboxPost(url,datas+'&orgFlow=${param.orgFlow}',function(resp){
						if(resp){
							for(var k in resp){
								setCurrNum(k,resp[k]);
							}
						}
					},null,false);
				}
			}
			//将获取或计算得到的数量放进展示区且绑定到元素内
			function setCurrNum(id,num){
				$('#'+id).text(num)[0].dataset.currNum = num;
			}
			//验证该数量是否有效并且可继续往下操作
			function operCurrNumById(id,num){
				if(id){
					var limitNumInfo = $('#'+id)[0];
					var limitNum = limitNumInfo.dataset.limitNum-0;
					if((limitNum+'') !=''){
						var currNum = limitNumInfo.dataset.currNum-0;
						currNum+=num;
						if(num<=0 || (limitNum)==0 || (limitNum)>=currNum){
							setCurrNum(id,currNum);
							return true;
						}else{
							jboxInfo('该功能可开通人数已满或超过总人数！');
						}
					}else{
						jboxInfo('请先配置该功能的开通人数！');
					}
				}
				return false;
			}
			/**
			 * 保存开关信息
			 * 开关定义方式为   自定义前缀_机构flow_医师/用户flow
			 * @param cbox
			 * @param prefix
             * @returns {boolean}
             */
			function saveCfg(cbox,prefix){
				var currStatus = cbox.checked;
				if(!operCurrNumById(prefix,currStatus?+1:-1)){
					cbox.checked = !currStatus;
					return false;
				}
				var code = cbox.value;
				var sData = {};
				sData['cfgCode'] = code;
				sData[code] = currStatus?'${GlobalConstant.FLAG_Y}':'${GlobalConstant.FLAG_N}';
				sData[code+'_ws_id'] = '${GlobalConstant.RES_WS_ID}';
				sData[code+'_desc'] = cbox.dataset.switchDesc;
				var url = '<s:url value="/sys/cfg/saveOne"/>';
				jboxPost(url,sData,function(resp){
					jboxTip('操作成功');
				},function(){
					operCurrNumById(prefix,currStatus?-1:+1);
				},false);
			}
			/**
			 * 全选或反选操作
			 * true:全选,false:反选
			 * @param status
             */
			function operSelect(status){
				$('.toSelBox').each(function(){
					this.checked = status || !this.checked;
				});
			}
			/**
			 * 批量操作开关
			 * //async:false//为同步参数,执行完之前浏览器被锁定
			 * @param id
             * @returns {*}
             */
			function batchOpenSwitch(id){
				var checkedBoxs = $('.toSelBox:checked');
				var selCount = checkedBoxs.length;
				if(!selCount){
					return jboxTip('请至少选择一个用户！');
				}
				var result = getOpenCount(id);
				if(operCurrNumById(id,result)){
					checkedBoxs.each(function(){
						var switchData = {};
						var cfgCode = id+'${param.orgFlow}_'+this.value;
						var currBox = $('[value="'+cfgCode+'"]')[0];
						var currBoxStatus = currBox.checked;
						switchData['cfgCode'] = cfgCode;
						switchData[cfgCode] = currBoxStatus?'${GlobalConstant.FLAG_N}':'${GlobalConstant.FLAG_Y}';
						switchData[cfgCode+'_ws_id'] = '${GlobalConstant.RES_WS_ID}';
						switchData[cfgCode+'_desc'] = currBox.dataset.switchDesc;
						var url = '<s:url value="/sys/cfg/saveOne"/>';
						$.ajax({
							url:url,
							type:'POST',
							data:switchData,
							async:false,
							success:function(){
								currBox.checked = !currBoxStatus;
							}
						});
					});
					jboxTip('操作成功');
				}
			}
			/**
			 * 一次批量的增量
			 * @param id
             * @returns {number}
             */
			function getOpenCount(id){
				var count = 0;
				$('.toSelBox:checked').each(function(){
					count+=($('[value="'+(id+'${param.orgFlow}_'+this.value)+'"]')[0].checked?-1:1);
				});
				return count;
			}
		</script>
	</head>

	<%-- 机构考试对接状态 --%>
	<c:set var="testInterfaceKey" value="jswjw_${param.orgFlow}_P004"/>
	<c:set var="testInterfaceStatus" value="${sysCfgMap[testInterfaceKey] eq GlobalConstant.FLAG_Y}"/>

	<body>
		<form id="docForm" method="POST" action="<s:url value='/res/cfg/personPermission'/>">
			<input type="hidden" name="orgFlow" value="${param.orgFlow}">
			<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
			<table class="basic" style="width: 99%;">
				<tr>
					<td>
						<div>
							<div style="float: left;margin-right: 10px;">
								姓名：
								<input type="text" name="doctorName" value="${param.doctorName}" onchange="search();" style="width: 100px;">
							</div>
							<div style="float: left;margin-right: 10px;">
								人员类型：
								<select name="doctorCategoryId" onchange="search();" style="width: 100px;">
									<option></option>
									<c:forEach items="${recDocCategoryEnumList}" var="category">
										<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id}"/>
										<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
											<option value="${category.id}" <c:if test="${param.doctorCategoryId eq category.id}">selected</c:if>>${category.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
							<div style="float: left;margin-right: 10px;">
								届别：
								<select name="sessionNumber" onchange="search();" style="width: 100px;">
									<option></option>
									<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
										<option value="${dict.dictName}" <c:if test="${param.sessionNumber eq dict.dictName}">selected</c:if>>${dict.dictName}</option>
									</c:forEach>
								</select>
							</div>
							<div style="float: left;margin-right: 10px;">
								专业：
								<select name="trainingSpeId" onchange="search();" style="width: 100px;">
									<option></option>
									<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
										<option value="${dict.dictId}" <c:if test="${param.trainingSpeId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="batchOperHome" style="clear: left;">
							<div style="float: left;">批量操作：</div>
							<c:if test="${testInterfaceStatus}">
								<div class="batchOper" style="float: left;">
									<c:set var="p004NumKey" value="P004_limit_num_${param.orgFlow}"/>
									<span>
										<a style="cursor:pointer;color: blue;" onclick="batchOpenSwitch('doc_test_switch_');">考试对接</a>
										(
										<label class="switchs" id="doc_test_switch_" data-limit-num="${sysCfgMap[p004NumKey]}" data-curr-num="0">

										</label>
										/
										<label>
											<c:out value="${sysCfgMap[p004NumKey]}" default="暂未设"/>
										</label>
										)
									</span>
								</div>
							</c:if>
						</div>
					</td>
				</tr>
			</table>
		</form>

		<div style="width: 99%;height: 343px;overflow: auto;margin-top: 10px;">
			<table class="xllist" style="width: 100%;">
				<tr>
					<th style="width: 5%;">
						<a style="cursor:pointer;color: blue;font-weight: normal;" onclick="operSelect(true);">全</a>/<a style="cursor:pointer;color: blue;font-weight: normal;" onclick="operSelect(false);">反</a>
					</th>
					<th style="width: 12%;">姓名</th>
					<th style="width: 15%;">类别</th>
					<th style="width: 6%;">性别</th>
					<th style="width: 7%;">届别</th>
					<th style="width: 20%;">专业</th>
					<th>权限配置</th>
				</tr>
				<c:forEach items="${doctorExtList}" var="docExt">
					<tr>
						<td>
							<input type="checkbox" class="toSelBox" value="${docExt.sysUser.userFlow}"/>
						</td>
						<td>${docExt.doctorName}</td>
						<td>${docExt.doctorCategoryName}</td>
						<td>${docExt.sysUser.sexName}</td>
						<td>${docExt.sessionNumber}</td>
						<td>${docExt.trainingSpeName}</td>
						<td>
							<c:if test="${testInterfaceStatus}">
								<label style="margin-left: 10px;float: left;">
									<c:set var="docTestSwitchKey" value="doc_test_switch_${docExt.orgFlow}_${docExt.doctorFlow}"/>
									<input
											<c:if test="${sysCfgMap[docTestSwitchKey] eq GlobalConstant.FLAG_Y}">checked</c:if>
											type="checkbox"
											data-switch-desc="单个学员考试系统对接开关"
											value="${docTestSwitchKey}"
											onchange="saveCfg(this,'doc_test_switch_');"
									/>
									考试对接
								</label>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div align="right">
			<c:set var="pageView" value="${pdfn:getPageView(doctorExtList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</div>

		<div align="center" style="margin-top: 10px;">
			<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
		</div>
	</body>
</html>