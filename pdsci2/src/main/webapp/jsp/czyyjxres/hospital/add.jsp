
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
    var num;
    var flag=false;


	function save() {
		if(false==$("#arrangeForm").validationEngine("validate")){
			return ;
		}
        //赋值 科室秘书
        var secretaryName = $("#secretaryId").find('option:selected').text();
        $("input[name='secretaryName']").val(secretaryName);


		var s = $("input[name='schStartDate']").val();
		var e = $("input[name='schEndDate']").val();
		if(s>e){
			jboxTip("开始时间不能大于结束时间！");
			return;
		}
        var jsonData = $('#arrangeForm').serialize();

        var url = "<s:url value='/czyyjxres/hospital/getArrangedNum'/>";
        jboxGetAsync(url,jsonData,function(resp){//同步加载
            num = resp;
        },null,false);

        jboxConfirm("当前时间段，该科室已经有"+num+"人，是否继续保存？",function(){
            var url = "<s:url value='/czyyjxres/hospital/save'/>";
            jboxPost(url, jsonData, function(data) {
                if('${GlobalConstant.SAVE_SUCCESSED}'==data){
                    $(parent.window.frames["mainIframe"].document).find(".selDoc").click();
                    jboxClose();
                }
            });
        },null);

	}
	//根据科室查询科秘
	function showKm(){
		var deptFlow = $("#deptFlow").val();
        var deptName = $("#deptFlow").find('option:selected').text();
        $("#deptName").val(deptName);

        if(deptFlow==null || deptFlow==''){
            $("#secretaryId").empty();
//            $("#deptFlow").append('<option value="">'+'请选择'+'</option>');
//            jboxTip("请先选择科室！");
            return;
        }
        //首先要刷新一下科秘下拉框
        $("#secretaryId").empty();
        var paramSecretaryFlow = '${arrange.secretaryFlow}';
        var secretary = $("#secretaryId");

		var url = "<s:url value='/czyyjxres/hospital/loadKm'/>?deptFlow=" + deptFlow;
        jboxGet(url, null, function (data) {
            for (var i=0;i<data.length;i++) {
                var sel = "";
                if (data[i].userFlow == paramSecretaryFlow) {
                    sel = "selected";
                }
                secretary.append('<option value="' + data[i].userFlow + '" ' + sel + '>' + data[i].userName + '</option>');

            }
        }, null, false);
	}

    $(document).ready(function(){
        showKm();
    });

</script>
</head>
<body>

<form id="arrangeForm" style="padding-left: 30px;height: 100px;" >
	<input type="hidden" name="doctorFlow" value="${doctorFlow}">
	<input type="hidden" name="doctorName" value="${doctorName}">
	<input type="hidden" name="arrangeFlow" value="${arrange.arrangeFlow}">
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<table width="800" cellpadding="0" cellspacing="0" class="basic">
					<tr>
						<th>进修专业：</th>
						<td>
							<select id="deptFlow" name="deptFlow" class="validate[required] xlselect" onchange="showKm()">
									<option value="">请选择</option>
								<c:forEach items="${deptList}" var="dept">
									<option value="${dept.deptFlow }" ${arrange.deptFlow eq dept.deptFlow?'selected':''}>${dept.deptName }</option>
								</c:forEach>
							</select>
							<input type="hidden" name="deptName" id="deptName" value="${arrange.deptName}">
						</td>
						<th width="20%">科室秘书：</th>
						<td width="30%">
							<%--<input type="hidden" value="${arrange.secretaryFlow}" name="secretaryFlow" id="secretaryId">--%>
							<%--<input type="text"   value="${arrange.secretaryName}" name="secretaryName" id="secretary" class="xltext"  readonly="readonly">--%>
                            <select id="secretaryId" name="secretaryFlow" class="xlselect validate[required]">

                            </select>
                            <input type="hidden" name="secretaryName" value="${arrange.secretaryName}">
                        </td>
					</tr>			
					<tr>
						<th>开始时间：</th>
						<td>
							<input class="validate[required] xltext" name="schStartDate" type="text" value="${arrange.schStartDate}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								   readonly="readonly" >
						</td>
						<th>结束时间：</th>
						<td>
							<input class="validate[required] xltext" name="schEndDate" type="text" value="${arrange.schEndDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								   readonly="readonly" >
						</td>
					</tr>
					<tr>
						<th>备注：</th>
						<td colspan="3">
							<textarea name="memo"  style="width: 600px; height:100px;margin: 3px 0;">${arrange.memo}</textarea>
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