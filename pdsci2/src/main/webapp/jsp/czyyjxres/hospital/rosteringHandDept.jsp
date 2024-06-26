<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<style type="text/css">
	table.basic th,table.basic td{text-align: center;padding: 0;}
	.highLight{background: rgba(255,0,0,0.8);}
	.lastOper{background: pink;}
	.moveColor{background: rgba(225, 238, 245,0.95);}
</style>

<script type="text/javascript">
	$(document).ready(function(){

	});

	//新增/编辑
	function add(flag,arrangeFlow) {
		var doctorName = '${doctor.doctorName}';
		var doctorFlow = '${doctor.doctorFlow}';
		jboxOpen("<s:url value='/czyyjxres/hospital/add?doctorFlow='/>"+doctorFlow+"&doctorName="+encodeURI(encodeURI(doctorName))+"&arrangeFlow="+arrangeFlow,flag+"进修专业信息", 900, 300);
	}

	//删除
	function del(){
		if($(".arrangeList:checked").length>0){
			var flows ="";
			$(".arrangeList:checked").each(function(){
				flows+=$(this).val()+",";
			});
			flows = flows.substring(0,flows.length-1);
			jboxConfirm("确认删除此条记录?", function(){
				var url = "<s:url value='/czyyjxres/hospital/del?arrangeFlows='/>"+flows;
				jboxPost(url, null, function(resp){
					if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
						$(parent.window.frames["mainIframe"].document).find(".selDoc").click();
					}
				}, null, false);
			});
		}else{
			jboxTip("请选择要删除的项！");
			return;
		}
	}
</script>
</head>
<body>
	<div style="float: right;margin-bottom: 5px;">
		<input type="button" value="新&#12288;增" class="search" onclick="add('新增','')"/>
		<input type="button" value="删&#12288;除" class="search" onclick="del()"/>
	</div>
	<table class="basic" style="width: 98%;margin-top: 10px;margin-left: 10px;">
		<thead>
			<tr>
				<th width="5%">选择</th>
				<th width="18%">进修专业</th>
				<th width="15%">科室秘书</th>
				<th width="15%">开始时间</th>
				<th width="15%">结束时间</th>
				<th width="25%">备注</th>
				<th width="5%">操作</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${arrangeList}" var="arrange">
				<tr>
					<td><input type="checkbox"  class="arrangeList" value="${arrange.arrangeFlow}"></td>
					<td>${arrange.deptName}</td>
					<td>${arrange.secretaryName}</td>
					<td>${arrange.schStartDate}</td>
					<td>${arrange.schEndDate}</td>
					<td>${arrange.memo}</td>
					<td><a style="cursor: pointer;color: #0a8cd2" onclick="add('编辑','${arrange.arrangeFlow}')">编辑</a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty arrangeList}">
				<tr>
					<td colspan="7">无记录</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</body>
</html>