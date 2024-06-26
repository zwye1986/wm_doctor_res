<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	function addLine() {
		var tr = $('.exampleTable tr').eq(0).clone();
        $('.mainTable').append(tr);
    }

    function save() {
        if(!$("#sysDictForm").validationEngine("validate")){
            return ;
        }
		var data = [];
		$('.mainTable tr').each(function () {
			var recordFlow = $(this).find('input[name="recordFlow"]').val();
			var detailOrder = $(this).find('input[name="detailOrder"]').val();
			var detailKey = $(this).find('input[name="detailKey"]').val();
			var inputType = $(this).find('[name="inputType"] option:selected').val();
			var single ={
                recordFlow:recordFlow,
                detailOrder:detailOrder,
                detailKey:detailKey,
                inputType:inputType
			}
			data.push(single);
        })
        var url = '<s:url value="/sys/dict/editForm"/>';
        jboxPost(url ,"data="+JSON.stringify(data)+"&dictFlow=${param.dictFlow}" , function(resp){
            if(resp==1){
                jboxTip("保存成功");
                setTimeout(function (){
                    jboxClose()
                }, 500)
            }
        } , null , false);
    }

    function delLine(obj) {
	    jboxConfirm("确认删除?",function () {
            var recordFlow = $(obj).closest('tr').find('input[name="recordFlow"]').val();
            var url = '<s:url value="/sys/dict/delForm"/>?recordFlow='+recordFlow;
            jboxPost(url , null, function(resp){
                if(resp==1){
                    jboxTip("操作成功");
                    $(obj).closest('tr').remove();
                }else if(resp==-1){
                    jboxTip("已有数据，不可删除");
				}
            } , null , false);
        })
    }
</script>
</head>
<body style="overflow: auto;">
<form id="sysDictForm" style="height: 100px;" >
<div class="content">
	<div style="text-align: right;margin-bottom: 5px;">
		<a onclick="addLine()" style="color: #0aa1ca;cursor: pointer">[添加]</a>
	</div>
		<table style="width: 100%" cellpadding="0" cellspacing="0" class="basic mainTable">
			<c:if test="${not empty dictForms}">
			<c:forEach items="${dictForms}" var="dictForm">
				<tr>
					<input type="hidden" name="recordFlow" value="${dictForm.recordFlow}">
					<th>排序码：</th>
					<td>
						<input class="validate[required] xltext" name="detailOrder" type="text" value="${dictForm.detailOrder}" style="width: 50px;"/>
					</td>
					<th>类型：</th>
					<td>
						<select class="validate[required] xlselect" name="inputType" style="width: 50px;">
							<option value="0" ${dictForm.inputType eq '0'?"selected":""}>小框</option>
							<option value="1" ${dictForm.inputType eq '1'?"selected":""}>大框</option>
						</select>
					</td>
					<th>名称：</th>
					<td>
						<input class="validate[required] xltext" name="detailKey" type="text" value="${dictForm.detailKey}" style="width: 400px;"/>
						<a onclick="delLine(this)" style="color: #0aa1ca;cursor: pointer">[删除]</a>
					</td>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${empty dictForms}">
				<tr>
					<th>排序码：</th>
					<td>
						<input class="validate[required] xltext" name="detailOrder" type="text" value="" style="width: 50px;"/>
					</td>
					<th>类型：</th>
					<td>
						<select class="validate[required] xlselect" name="inputType" style="width: 50px;">
							<option value="0">小框</option>
							<option value="1">大框</option>
						</select>
					</td>
					<th>内容：</th>
					<td>
						<input class="validate[required] xltext" name="detailKey" type="text" value="" style="width: 400px;"/>
						<a onclick="delLine(this)" style="color: #0aa1ca;cursor: pointer">[删除]</a>
					</td>
				</tr>
			</c:if>
		</table>
		<div class="button" style="width: 100%">
			<input class="search" type="button" value="保&#12288;存" onclick="save();" />
			<input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
		</div>
</div>
</form>
	<table class="exampleTable basic" style="display: none">
		<tr>
			<th>排序码：</th>
			<td>
				<input class="validate[required] xltext" name="detailOrder" type="text" value="" style="width: 50px;"/>
			</td>
			<th>类型：</th>
			<td>
				<select class="validate[required] xlselect" name="inputType" style="width: 50px;">
					<option value="0">小框</option>
					<option value="1">大框</option>
				</select>
			</td>
			<th>内容：</th>
			<td>
				<input class="validate[required] xltext" name="detailKey" type="text" value="" style="width: 400px;"/>
				<a onclick="delLine(this)" style="color: #0aa1ca;cursor: pointer">[删除]</a>
			</td>
		</tr>
	</table>
</body>
</html>