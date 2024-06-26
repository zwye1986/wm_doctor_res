<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style>
	.editResultByStandardDept_toSelStdDept{display: none;}
	.editResultByStandardDept_toSelSchDept{display: none;}
	.isSelected{background-color: pink;}
	table{font-size: 14px}
</style>
<c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}" var="unitKey"/>
<script>
	//输出错误信息
	/*if("${error}"){
		console.error("${error}");
	}*/
	
	//本页select数据
	var selDatas = {
			'headSeller':'${process.headUserFlow}',
			'teacherSeller':'${process.teacherUserFlow}'
	};
	
	//弹出选择界面
	function toSel(contentClass,title){
		var content = $('.'+contentClass);
		jboxOpenContent2(content.html(),title,700,350);
	}
	
	//赋值
	function setVal(data){
		if(data){
			for(var key in data){
				var input = $('[name="'+key+'"]');
				if(input.length){
					var tagName = input[0].tagName.toLowerCase();
					if(tagName){
						if(tagName == 'input' || tagName == 'textarea'){
							input.val(data[key]);
							if(input.is('[onchange]')){
								input.change();
							}
						}else{
							$('[name="'+key+'"]').text(data[key]);
						}
					}
				}
			}
		}
	}
	
	//选择一个科室
	function toSelDept(data,selClass){
		var thisDept = $('.'+selClass);
		var parent = thisDept.closest('div');
		$('.isSelected',parent).removeClass('isSelected');
		thisDept.addClass('isSelected');
		setVal(data);
		jboxContentClose2();
	}
	function selDept(obj,name,data,selClass){
		if($(obj).attr('checked')){ 
			$(':checkbox[name='+name+']').removeAttr('checked'); 
			$(obj).attr('checked','checked'); 
		} 
		$(".standardDept").hide();
		$(".schDept").hide();
		$(".other").show();
		if($(".lzks").is(":hidden")){
			$("#standardDept").removeAttr("colspan");
			$(".lzks").show();
		}
		if($(".bzks").is(":hidden")){
			$("#schDept").removeAttr("colspan");
			$(".bzks").show();
		}
		setVal(data);
		jboxContentClose2();
	}
	//加载带教和主任
	function loadTeacherAndHead(schDeptFlow){
		if(schDeptFlow){
			var url = '<s:url value="/res/doc/loadTeacherAndHead"/>';
			jboxPost(url,{schDeptFlow:schDeptFlow},function(resp){
				if(resp){
					var option = $('<option/>');
					for(var key in resp){
						var list = resp[key];
						if(list){
							var select = $('.'+key).empty();
							if(select.length){
								select.empty().append(option.clone());
								for(var index in list){
									var data = list[index];
									var val = data.userFlow;
									var selData = selDatas[key];
									select.append(option.clone().val(val).text(data.userName).attr('selected',val==selData));
								}
							}
						}
					}
				}
			},null,false);
		}
	}
	
	//保存表单
	function saveTheForm(endCallback){
		var standardGroupFlow = $('[name="standardGroupFlow"]').val();
		var standardDeptId = $('[name="standardDeptId"]').val();
		var headUserFlow = $('select[name=headUserFlow]').val();
		var teacherUserFlow = $('select[name=teacherUserFlow]').val();
		if(headUserFlow==""){
			return jboxTip("教学主任不能为空！");
		}
		if(teacherUserFlow==""){
			return jboxTip("带教老师不能为空！");
		}
		if(!standardGroupFlow || !standardDeptId){
			return jboxTip("请选择标准科室！");
		}
		
		var schDeptFlow = $('[name="schDeptFlow"]').val();
		if(!schDeptFlow){
			return jboxTip("请选择轮转科室！");
		}
		
		if($("#editResultByStandardDept_form").validationEngine("validate")){
			var startDate = $('[name="schStartDate"]').val();
			var endDate = $('[name="schEndDate"]').val();
			if(startDate>endDate){

				return jboxTip("开始时间不能大于结束时间！");
			}
			if(startDate==""){
				return jboxTip("开始时间为空！");
			}
			if(endDate==""){
				return jboxTip("结束时间为空！");
			}
			$('#editResultByStandardDept_toSave_btn').hide();
			//验证时间是否与其他科室重叠
			var checkUrl = '<s:url value="/res/doc/checkTheResultDate"/>';
			jboxPost(checkUrl,
					{
				doctorFlow:'${param.doctorFlow}',
				resultFlow:'${result.resultFlow}',
				startDate:startDate,
				endDate:endDate
			},
			function(resp){
				if("${GlobalConstant.FLAG_Y}" == resp){
					toSave(endCallback);
				}else if("${GlobalConstant.FLAG_N}" == resp){
					$('#editResultByStandardDept_toSave_btn').show();
					return jboxTip("该轮转时间与其他科室轮转时间重叠,请重新选择！");
				}else{
					console.error(resp);
				}
			},null,false);
		}
	}
	
	//开始保存
	function toSave(endCallback){
		var url = '<s:url value="/res/doc/saveResultAndProcess"/>';
		jboxPost(url,$('#editResultByStandardDept_form').serialize(),function(resp){
			if("${GlobalConstant.OPRE_SUCCESSED_FLAG}"==resp){
				top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
				setTimeout(function(){
					endCallback();
				},2000);
			}
			if("${GlobalConstant.OPRE_SUCCESSED_FLAG}"!=resp) {
				$('#editResultByStandardDept_toSave_btn').hide();
			}
		},null,false);
	}
	
	//刷新当前页面
	function reloadCurrPage(){
		location.reload();
	}
	
	//初始化
	$(function(){
// 		$('.createResultByStandardDept_toSave').on('click',function(){
// 			saveTheForm(reloadCurrPage);
// 		});
		$('[name="schDeptFlow"]').change();
	});
	function save(){
		if(!$(".schDept").is(":hidden")){

			var schDeptFlow = $('[name="schDeptFlow"]').val();
			if(!schDeptFlow){
				return jboxTip("请选择轮转科室！");
			}

			$(".other").show();
			$(".schDept").hide();
			$(".bzks").show();
			$(".standardDept").hide();
			$("#schDept").removeAttr("colspan");

			return ;
		}
		saveTheForm(function(resp){
			top.document.mainIframe.location.reload();
			jboxClose();
		});
	}
	function edit(data){
		var value="";
		if(data == 'standardDept'){
			if($(".standardDept").is(":hidden")){
				$(".other").hide();
				$(".schDept").hide();
				$(".lzks").hide();
				$(".standardDept").show();
				$("#standardDept").attr("colspan",3);
			}else{
				$(".other").show();
				$(".schDept").hide();
				$(".lzks").show();
				$(".standardDept").hide();
				$("#standardDept").removeAttr("colspan");
			}
			value=$("input[name=standardDeptId]").val();
		}
		if(data == 'schDept'){
			if($(".schDept").is(":hidden")){
				$(".other").hide();
				$(".schDept").show();
				$(".bzks").hide();
				$(".standardDept").hide();
				$("#schDept").attr("colspan",3);
			}else{
				$(".other").show();
				$(".schDept").hide();
				$(".bzks").show();
				$(".standardDept").hide();
				$("#schDept").removeAttr("colspan");
			}
			value=$("input[name=schDeptFlow]").val();
		}
		$(':checkbox[name='+data+']').each(function(){
			if($(this).val()==value){
				$(this).attr("checked",true);
			}
		});
	}
</script>
<div class="mainright">
<form id="editResultByStandardDept_form" style="position: relative;">
	<input type="hidden" name="resultFlow" value="${result.resultFlow}">
	<input type="hidden" name="doctorFlow" value="${param.doctorFlow}">
	<input type="hidden" name="schDeptFlow" value="${result.schDeptFlow}" 
	onchange="loadTeacherAndHead(this.value);">
	<c:if test="${not empty result.standardGroupFlow }">
		<input type="hidden" name="standardGroupFlow" value="${result.standardGroupFlow}">
	</c:if>
	<c:if test="${ empty result.standardGroupFlow }">
		<input type="hidden" name="standardGroupFlow" value="${param.standardGroupFlow}">
	</c:if>
	<c:if test="${not empty result.standardDeptId }">
		<input type="hidden" name="standardDeptId" value="${result.standardDeptId}">
	</c:if>
	<c:if test="${empty result.standardDeptId}">
		<input type="hidden" name="standardDeptId" value="${param.standardDeptId}">
	</c:if>
	<table class="basic" style="width: 100%;">
			<colgroup>
				<col width="20%"/>
				<col width="30%"/>
				<col width="20%"/>
				<col width="30%"/>
			</colgroup>
		<tr>
			<th colspan="4" style="text-align: left;padding-left: 10px;">
				编辑轮转计划
			</th>
		</tr>
		<tr>
			<th class="bzks" ><font color="red">*</font>标准科室：</th>
			<td id="standardDept" class="bzks" >
			<c:if test="${param.addFlag != GlobalConstant.FLAG_Y  }">
				<a name="standardDeptName" onclick="edit('standardDept');" style="cursor: pointer;">
<!-- 				<a name="standardDeptName" onclick="toSel('editResultByStandardDept_toSelSchDept','选择轮转科室');"> -->
				<label style="cursor: pointer;">${empty result?'请选择标准科室':result.standardDeptName}</label>
				</a>
			</c:if>
			<c:if test="${param.addFlag eq GlobalConstant.FLAG_Y  }">
				<c:forEach items="${rotationDeptList}" var="rotationDept">
					<c:if test="${(rotationDept.standardDeptId eq param.standardDeptId)and (rotationDept.groupFlow eq param.standardGroupFlow) }">
						${rotationDept.standardDeptName }
					</c:if>
				</c:forEach>
			</c:if>
			</td>
			<th style="width: 20%;" class="lzks"><font color="red">*</font>轮转科室：</th>
			<td style="width: 30%;" class="lzks" id="schDept">
				<a name="schDeptName" onclick="edit('schDept');"style="cursor: pointer;">
					<label style="cursor: pointer;color: blue">${empty result?'请选择轮转科室':result.schDeptName}</label>
				</a>
			</td>
		</tr>
		<tr class="other">
			<th style="width: 20%;"><font color="red">*</font>开始时间：</th>
			<td style="width: 30%;">
				<input 
				type="text" 
				name="schStartDate" 
				value="${result.schStartDate}" 
				readonly="readonly" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
				style="width: 100px;"
				class="validate[required]"/>
			</td>
			<th style="width: 20%;"><font color="red">*</font>结束时间：</th>
			<td style="width: 30%;">
				<input 
				type="text" 
				name="schEndDate" 
				value="${result.schEndDate}" 
				readonly="readonly" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
				style="width: 100px;"
				class="validate[required]"/>
			</td>
		</tr>
		<tr class="other">
			<th style="width: 20%;"><font color="red">*</font>教学主任：</th>
			<td style="width: 30%;">
				<select class="headSeller validate[required]" name="headUserFlow" style="width: 100px;">
					<option/>
				</select>
			</td>
			<th style="width: 20%;"><font color="red">*</font>带教老师：</th>
			<td style="width: 30%;">
				<select class="teacherSeller validate[required]" name="teacherUserFlow" style="width: 100px;">
					<option/>
				</select>
			</td>
		</tr>
		<tr style="display: none;" class="standardDept">
			<td colspan="4" style="overflow: auto;">
			<div style="height: 300px">
				<font color="red">Tip:再次点击或者选中按钮关闭这里</font><br/>
				<c:forEach items="${rotationDeptList}" var="rotationDept">
						<span><label style="cursor: pointer;display: inline-block;"><input type="checkbox" value="${rotationDept.standardDeptId }" name="standardDept" onclick="
							selDept(this,'standardDept',
								{
									standardDeptId:'${rotationDept.standardDeptId}',
									standardGroupFlow:'${rotationDept.groupFlow}',
									standardDeptName:'${rotationDept.standardDeptName}'
								},
								'editResultByStandardDept_toSelStdDept_${rotationDept.recordFlow}'
							);"style="cursor: pointer;"/>[${groupMap[rotationDept.groupFlow].schStageName}${groupMap[rotationDept.groupFlow].groupName}]${rotationDept.standardDeptName}(${rotationDept.schMonth}${applicationScope[unitKey].name})&#12288;
<%-- 					<td>${rotationDept.deptNote}</td> --%>
						</label>
						</span>
				</c:forEach>
			</div>
			</td>
			</tr>
			<tr class="schDept"  style="display: none;">
				<td colspan="4"  style="overflow: auto;">
				<div style="height: 200px">
					<font color="red">Tip:再次点击或者选中按钮关闭这里</font><br/>
						<c:forEach items="${schDeptList}" var="schDept">
						<div style="width: 24.9%;float: left;text-align: left;"><label style="cursor: pointer;display: inline-block;"><input type="checkbox" name="schDept" value="${schDept.schDeptFlow }"  onclick="
								selDept(this,'schDept',
									{
										schDeptFlow:'${schDept.schDeptFlow}',
										schDeptName:'${schDept.schDeptName}'
									},
									'editResultByStandardDept_toSelSchDept_${schDept.schDeptFlow}'
								);
							" />${schDept.schDeptName}</label>&#12288;</div>
						</c:forEach>
					</div>
				</td>
			</tr>
	</table>

	<div style="margin-top: 10px;margin-left: 400px">
		<%--<a id="editResultByStandardDept_toSave_btn" style="color: blue;cursor: pointer;" onclick="save();" >保存</a>&#12288;--%>
		<%--<a style="color: blue;cursor: pointer;" onclick="jboxClose();">取消</a>--%>
		<button class="search" id="editResultByStandardDept_toSave_btn" type="button" onclick="save();">保&#12288;存</button>
		<button class="search" onclick="jboxClose();">取&#12288;消</button>
	</div>
</form>
</div>