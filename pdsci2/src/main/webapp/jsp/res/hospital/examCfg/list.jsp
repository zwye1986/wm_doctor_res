<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<table class="xllist">
	<colgroup>
		<col width="20%"/>
		<col width="18%"/>
		<col width="18%"/>
		<col width="19%"/>
		<col width="25%"/>
	</colgroup>
	<tr>
		<th>培训专业</th>
		<th>年级</th>
		<th>年度</th>
		<th>考试模式</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${list}" var="b">
		<tr>
			<td>${b.trainingSpeName}</td>
			<td>${b.sessionNumber}</td>
			<td>${b.assessmentYear}</td>
			<td>
				<c:if test="${b.exampaperType eq '1'}">
					随机试卷
				</c:if>
				<c:if test="${b.exampaperType eq '2'}">
					固定试卷
				</c:if>
			</td>
			<td>
				<c:if test="${b.isOpen eq 'Y'}">
					[<a href="javascript:openInfo('${b.arrangeFlow}','N');">关闭</a>]
				</c:if>
				<c:if test="${b.isOpen eq 'N'}">
					[<a href="javascript:openInfo('${b.arrangeFlow}','Y');">开放</a>]
				</c:if>
				[<a href="javascript:edit('${b.arrangeFlow}');">编辑</a>]
				[<a href="javascript:del('${b.arrangeFlow}');">删除</a>]
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty list}">
	<tr>
		<td colspan="6" >无记录！</td>
	</tr>
	</c:if>
</table>
<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
<pd:pagination toPage="toPage"/>

      
