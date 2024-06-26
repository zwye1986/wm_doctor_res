<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省中医住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
function editTrainSpe(speId){
	$(".show").show();
	$(".input").hide();
	$("#"+speId+"Span").hide();
	$("#"+speId).show();
}
</script>
</head>
<body>
<div class="main_hd">
    <h2 class="underline">专业管理</h2> 
</div>
<div class="main_bd">
	<div class="div_search">
		
	</div>
	<div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
        		<col style="text-align: left;padding-left: 40px;"/>
        		<col style="text-align: left;padding-left: 40px;"/>
        		<col style="text-align: left;padding-left: 40px;"/>
        	</colgroup>
        	<tr>
                <th>培训类别</th>
                <th>专业名称</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${jszyTrainCategoryEnumList }" var="trainCategory" varStatus="status">
				<c:if test="${trainCategory.typeId eq jszyTrainCategoryTypeEnumAfter2014.id }">
				<c:set var="dictName" value="dictTypeEnum${trainCategory.id }List" />
		            <c:forEach items="${applicationScope[dictName] }" var="dict">
		            	<tr>
			                <td>
			                	${trainCategory.name }
			                </td>
			                <td>
			                	<span id="${dict.dictId}Span" class="show">${dict.dictName }</span>
			                	<input type="text" class="input" id="${dict.dictId}" value="${dict.dictName }" style="display: none;"/>
			                </td>
			                <td><a class="btn" onclick="editTrainSpe('${dict.dictId}');">编辑</a></td>
			            </tr>
					</c:forEach>
				</c:if>
			</c:forEach>
        </table>
	</div>
</div>
</body>
</html>