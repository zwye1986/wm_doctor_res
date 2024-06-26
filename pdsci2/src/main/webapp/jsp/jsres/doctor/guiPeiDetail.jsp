<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#sessionNumber').datepicker({
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
	});
	$(".base_info").css("text-align","center");
});
	function doclose()
	{
		top.jboxClose();
	}
</script>
<div class="search_table" style="text-align: left;">
	<div class="main_hd">
		<h2>
			<B>姓名：</B>${user.userName}&#12288;<B>
			培训专业：</B>${resDoctor.trainingSpeName }&#12288;
			<c:set var="allper" value="0"></c:set>
			<c:set var="count" value="1"></c:set>
			<c:forEach items="${list}" var="bean">
				 <c:set var="allper" value="${allper+(empty processPerMap[bean.flow]?0:processPerMap[bean.flow])}"></c:set>
				<c:set var="count" value="${count+1}"></c:set>
			</c:forEach>
			<c:set var="avg" value="0"/>
			<c:if test="${!(allper eq '0')&& !(count eq '1')}">
				<c:set var="count" value="${count-1}"></c:set>
				<c:set var="avg" value="${allper/count}"/>
			</c:if>
			<B style="float:right;">平均完成比例：${avg}%</B>
		</h2>
	</div>
	<div class="infoAudit" style="max-height: 430px;overflow:auto;">
	<div class="main_bd" id="div_table_0" >
		<table border="0" style=" width:100%;text-align: left;" class="base_info" >
			<colgroup>
				<col width="25%"/>
				<col width="50%"/>
				<col width="25%"/>
			</colgroup>
			<tbody>
			<tr style=" width:100%;text-align: center;">
				<th style="text-align: center;">标准科室名称</th>
				<th style="text-align: center;">出科考核表</th>
				<th style="text-align: center;">完成比例</th>
			</tr>
			<c:forEach items="${list}" var="bean">
				<c:set var="imagelist" value="${extScoreMap[bean.flow]}"></c:set>
					<tr>
						<td  style="text-align: center;">${bean.standardDeptName}</td>
						<td  style="text-align: center;">
							<div style="margin-top:10px; ">
							<c:forEach var="image" items="${imagelist}" varStatus="status">
								<a href="${image.imageUrl}" target="_blank"><img width="80px" height="100px"  src="${image.thumbUrl}"></a>&nbsp;
								<c:if test="${(status.count % 6) eq '0'}"><br/></c:if>
							</c:forEach>
							</div>
						</td>
						<td style="text-align: center">
							${empty processPerMap[bean.flow]?0:processPerMap[bean.flow]}%
						</td>
					</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr>
					<td colspan="3" style="text-align: center;">无记录！</td>
				</tr>
			</c:if>

			</tbody>
		</table>
	</div>
	</div>
	<div class="main_bd" id="div_table_1" >
		<div align="center" style="padding-top: 20px;">
			<input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关&#12288;闭"/>&nbsp;
		</div>
	</div>
</div>
