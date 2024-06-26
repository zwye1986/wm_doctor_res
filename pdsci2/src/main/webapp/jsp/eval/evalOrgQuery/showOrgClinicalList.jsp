<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script type="text/javascript">
	function showOrgClinicalDetail(speId)
	{
		var url="<s:url value='/eval/evalOrgQuery/checkClinicalDetail'/>?orgFlow=${param.orgFlow}&evalYear=${param.evalYear}&speId="+speId;
		jboxPost(url,null,function(resp){
			if(resp=="1")
			{
				var w = $('.mainright').width();
				var h = $('.mainright').height();
				var url = "<s:url value='/eval/evalOrgQuery/showClinicalDetail'/>?orgFlow=${param.orgFlow}&evalYear=${param.evalYear}&speId="+speId;
				var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='500'" +
						" marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
				jboxMessager(iframe,'专业基地评估详情',1100,500,null,true);
			}else{
				jboxTip(resp);
			}
		},null,false);
	}
</script>
<table class="xllist"  id="treeTable"  style="width:100%;margin-top: 10px;text-align: center">
	<colgroup>
		<col width="30%"/>
		<col width="10%"/>
		<col width="30%"/>
		<col width="30%"/>
	</colgroup>
	<thead>
	<tr>
		<th>专业基地</th>
		<th>评估分数</th>
		<th>排名</th>
		<th>全部</th>
	</tr>
	</thead>
	<tbody id="tbody">
	<c:forEach items="${list}" var="bean">
		<tr>
			<td>${bean.speName}</td>
			<td>${bean.score}</td>
			<td>
				<c:if test="${bean.score ne '-'}">
					${bean.orderNum}
				</c:if>
				<c:if test="${bean.score eq '-'}">
					-
				</c:if>
			</td>
			<td>
				<a style="color:#449bcd;" href="javascript:showOrgClinicalDetail('${bean.speId}');" >查看</a>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty param.evalYear or empty param.orgFlow}">
		<tr>
			<td colspan="14">请选择评估年份及培训基地</td>
		</tr>
	</c:if>
	</tbody>
</table>