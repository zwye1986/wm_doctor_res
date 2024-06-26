
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
	$(document).ready(function(){
		var obj = $("#schDeptFlow");
		var deptFlow= '${deptFlow}';
		loadTeacherAndHead(obj,deptFlow);
	});

	function save(){
		var schStartDate = $("#schStartDate").val();
		var schEndDate = $("#schEndDate").val();

		if(schStartDate>schEndDate){
			jboxTip("开始时间不能大于结束时间！");
			return;
		}

		//验证时间是否与其他科室重叠
		var checkUrl = '<s:url value="/res/doc/checkTheResultDate"/>';
		jboxPost(checkUrl,
				{
					doctorFlow:'${doctorFlow}',
					resultFlow:'${resultFlow}',
					startDate:schStartDate,
					endDate:schEndDate
				},
				function(resp){
					if("${GlobalConstant.FLAG_Y}" == resp){
						toSave();
					}else if("${GlobalConstant.FLAG_N}" == resp){
						return jboxTip("该轮转时间与其他科室轮转时间重叠,请重新选择！");
					}else{
						console.error(resp);
					}
				},null,false);

	}

	/**
	 * 保存
	 */
	function toSave() {
		if(false==$("#arrangeForm").validationEngine("validate")){
			return ;
		}


		var url = "<s:url value='/res/head/save'/>";
		var getdata = $('#arrangeForm').serialize();
		jboxPost(url, getdata, function(data) {
			if('${GlobalConstant.SAVE_SUCCESSED}'==data){
				parent.window.frames["mainIframe"].window.location.reload(true);
				jboxClose();
			}
		});
	}

	//本页select数据
	var selDatas = {
		'headSeller':'${process.headUserFlow}',
		'teacherSeller':'${process.teacherUserFlow}'
	};
	//根据科室查询带教、科主任
	function loadTeacherAndHead(obj,deptFlow){
		if($(obj).val()==null || $(obj).val()==''){
			$("#teacherUserFlow").empty();
			$("#teacherUserFlow").append('<option value="">请选择</option>');
			return;
		}

		$("#schDeptName").val($(obj).find('option:selected').text());

		var url = '<s:url value="/res/head/loadTeacherAndHead"/>';
		jboxPost(url,{deptFlow:deptFlow},function(resp){
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

	$(document).ready(function(){

		var flag = '${flag}';
		if(flag=='Y'){  //入科
			$("#edit1").hide();
			$("#edit2").hide();
			$("#ruke").show();
		}else if(flag=='edit'){//编辑
			$("#edit1").show();
			$("#edit2").show();
			$("#ruke").show();
		}else{//新增
			$("#edit1").show();
			$("#edit2").show();
			$("#ruke").hide();
		}
	});
</script>
</head>
<body>

<form id="arrangeForm" style="padding-left: 30px;height: 100px;" >
	<input type="hidden" name="doctorFlow" value="${doctorFlow}">
	<input type="hidden" name="doctorName" value="${doctorName}">
	<input type="hidden" name="deptFlow" value="${deptFlow}">
	<input type="hidden" name="deptName" value="${deptName}">
	<input type="hidden" name="resultFlow" value="${resultFlow}">

<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">

				<table width="800" cellpadding="0" cellspacing="0" class="basic">
					<tr id="edit1" style="display: none">
						<th>轮转科室：</th>
						<td colspan="3">
							<select name="schDeptFlow" id="schDeptFlow" class="validate[required] xlselect" onchange="loadTeacherAndHead(this,'${deptFlow}')">
									<option value="">请选择</option>
								<c:forEach items="${schDeptList}" var="schDept">
									<option value="${schDept.schDeptFlow }" ${schDept.schDeptFlow eq process.schDeptFlow?'selected':''}>${process.schDeptName?process.schDeptName:schDept.schDeptName }</option>
								</c:forEach>
							</select>
							<input type="hidden" name="schDeptName" id="schDeptName">
						</td>
					</tr>


					<tr id="ruke" style="display: none">
						<th>带教老师：</th>
						<td>
							<select name="teacherUserFlow" id="teacherUserFlow" class="teacherSeller validate[required] xlselect">
								<option value="">请选择</option>
							</select>
							<input type="hidden" name="teacherUserName" id="teacherUserName" value="${process.teacherUserName}">
						</td>
						<th>科主任：</th>
						<td>
							<select name="headUserFlow" id="headUserFlow" class="headSeller validate[required] xlselect">
								<option value="">请选择</option>
							</select>
							<input type="hidden" name="headUserName" id="headUserName" value="${process.headUserName}">
						</td>
					</tr>

					<tr id="edit2" style="display: none">
						<th>开始时间：</th>
						<td>
							<input class="validate[required] xltext" name="schStartDate" id="schStartDate" type="text" value="${process.schStartDate}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${schStartDate}',maxDate:'${schEndDate}'})"
								   readonly="readonly" >
						</td>
						<th>结束时间：</th>
						<td>
							<input class="validate[required] xltext" name="schEndDate" id="schEndDate" type="text" value="${process.schEndDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${schStartDate}',maxDate:'${schEndDate}'})"
								   readonly="readonly" >
						</td>
					</tr>

				</table>

				<div class="button" style="width: 800px;">
					<input class="search" type="button" value="保&#12288;存" onclick="save();" />
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose()">
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>