<div class="div_search">
<div style="overflow: auto;">
	<table  style="width: 100%;margin-bottom: 5px;margin-top: 0px;border: 1px solid #e3e3e3;">
		<tr>
			<td style="border-bottom: 1px solid #e3e3e3;" id="f">
				<c:forEach items="${attachmentList}" var="list" varStatus="status">
					<div class="imageOper" style="border: 1px solid #e3e3e3; margin-left: 5px; margin-top: 5px;margin-bottom:5px;  width: 160px;height: 150px;float: left;text-align: center;">

						<a target="_blank" href="${list.imgPath}"><img class="imgc" src="${list.imgPath}" width="100%" height="100%"/></a>

					</div>
				</c:forEach>
				<c:if test="${empty attachmentList && !(empty param.orgFlow)}">
						<center style="font-size: 20px;color: rgba(0,0,0,0.5);">
							<label>暂无数据！</label>
						</center>
				</c:if>
				<c:if test="${empty param.orgFlow}">
						<center style="font-size: 20px;color: rgba(0,0,0,0.5);">
							<label>请选择基地</label>
						</center>
				</c:if>
			</td>
		</tr>
	</table>
</div>
</div>