<script>
	function toPage(page){
		var regYear = $("[name='sessionNumber']").val();
		if(!regYear){
			regYear='';
		}
		if(page){
			registerStatistics(regYear,page);
		}
	}
</script>
<div class="main_hd">
    <form id="recruitResultForm">
		<h2>住培注册学员统计表&#12288;&#12288;
			基地：
			<c:choose>
			<c:when test="${GlobalConstant.USER_LIST_LOCAL eq param.source }">
			 	${sessionScope.currUser.orgName}
			</c:when>
			<c:otherwise>
				<select class="select" name="orgFlow" onchange="registerStatistics('','');" >
				    <option value="">请选择</option>
					    <c:forEach var="org" items="${orgList}">
							<option value="${org.orgFlow}" <c:if test='${org.orgFlow == param.orgFlow}'>selected="selected"</c:if>>${org.orgName}</option>
						</c:forEach>
				</select>
				<span style="margin-left:20px;"><a href="<s:url value='/hbres/singup/hospital/exportRecruit'/>?charge=charge&regYear=${param.regYear}" class="btn_green">录取学员导出</a></span>
			</c:otherwise>
			</c:choose>
		</h2>
		<div class="underline">
			&#12288;&#12288;&#12288;年&#12288;&#12288;份：
			<select name="sessionNumber" class="select" style="width: 126px;" onchange="registerStatistics(this.value,'')">
				<option value="">全部</option>
				<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
					<option value="${dict.dictName}" ${param.regYear eq dict.dictName?'selected':''}>${dict.dictName}</option>
				</c:forEach>
			</select>
		</div>
	</form>
</div>
<div class="main_bd" id="div_table_0" >
    <div class="div_table">
		<div style="float: right;font-size: 16px;padding-bottom: 10px;">
			招收人数合计：${totalNum}人
		</div>
        <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%">
            <tr>
                <th>序号</th>
                <th>专业代码</th>
                <th>基地专业名称</th>
                <th>招收人数</th>
            </tr>
            <c:forEach var="dict" items="${doctorTrainingSpeList}" varStatus="s">
            <tr>
                <td>${s.index+1}</td>
                <td>${dict.dictId}</td>
                <td>${dict.dictName}</td>
                <td>${doctorRecruitFormMap[dict.dictId]==null?'0':doctorRecruitFormMap[dict.dictId]}</td>
            </tr>
        	</c:forEach>
        </table>
    </div>
	<div class="page" style="text-align: center;">
		<input id="currentPage" type="hidden" name="currentPage" value=""/>
		<c:set var="pageView" value="${pdfn:getPageView(doctorTrainingSpeList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</div>
      
