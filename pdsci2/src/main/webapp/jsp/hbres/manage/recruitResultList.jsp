<script type="text/javascript">
	function toPage(currentPage) {
		if(!currentPage){
			currentPage = 1;
		}
		recruitResultList(currentPage,'${param.regYear}');
	}
    function exportList(){
        <c:choose>
			<c:when test="${GlobalConstant.USER_LIST_LOCAL eq param.source }">
               var url="<s:url value='/hbres/grade/exportRecruitResultList'/>?regYear=${param.regYear}&orgFlow=${sessionScope.currUser.orgFlow}"
            </c:when>
			<c:otherwise>
                var orgFlow = $("[name='orgFlow']").val();
                var url="<s:url value='/hbres/grade/exportRecruitResultList'/>?regYear=${param.regYear}&orgFlow="+orgFlow;
			</c:otherwise>
			</c:choose>
        jboxTip("导出中…………");
        jboxExp("", url);
        jboxEndLoading();
    }
</script>
<style>
</style>
<div class="main_hd">
    <form id="recruitResultForm">
		<h2>录取考试成绩排序表&#12288;&#12288;
			基地：
			<c:choose>
			<c:when test="${GlobalConstant.USER_LIST_LOCAL eq param.source }">
			 	${sessionScope.currUser.orgName}
			</c:when>
			<c:otherwise>
				<select class="select" name="orgFlow" onchange="recruitResultList('','');">
				    <option value="">请选择</option>
					    <c:forEach var="org" items="${orgList}">
							<option value="${org.orgFlow}" <c:if test='${org.orgFlow == param.orgFlow}'>selected="selected"</c:if>>${org.orgName}</option>
						</c:forEach>
				</select>
			</c:otherwise>
			</c:choose>
		</h2>
        <div class="underline">
            &#12288;&#12288;&#12288;年&#12288;&#12288;份：
            <select name="sessionNumber" class="select" style="width: 126px;" onchange="recruitResultList('',this.value)">
                <option value="">全部</option>
                <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
                    <option value="${dict.dictName}" ${param.regYear eq dict.dictName?'selected':''}>${dict.dictName}</option>
                </c:forEach>
            </select>&#12288;
            <span><a onclick="exportList()" class="btn_green">导&#12288;出</a></span>
        </div>
	</form>
</div>
<div class="main_bd" id="div_table_0" >
    <div class="div_table">
        <div style="float: right;font-size: 16px;padding-bottom: 10px;">
            考试人数合计：${totalNum}人
        </div>
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th style="text-align: center;">名次</th>
                <th style="text-align: center;">年级</th>
                <th style="text-align: center;">姓名</th>
                <th style="text-align: center;">身份证号</th>
                <th style="text-align: center;">笔试</th>
                <th style="text-align: center;">技能考试</th>
                <th style="text-align: center;">面试</th>
                <th style="text-align: center;">总成绩</th>
            </tr>
            <c:forEach items="${doctorRecruitExtList}" var="doctorRecruitExt" varStatus="status">
            <tr>
                <td style="text-align: center;">
                	<c:set var="rank" value="${(param.currentPage-1)*pageSize}"/>
                	${rank + status.count}
                </td>
                <td style="text-align: center;">${doctorRecruitExt.recruitYear}</td>
                <td style="text-align: center;">${doctorRecruitExt.sysUser.userName}</td>
                <td style="text-align: center;">${doctorRecruitExt.sysUser.idNo}</td>
                <td style="text-align: center;">${doctorRecruitExt.examResult==null?'——':doctorRecruitExt.examResult}</td>
                <td style="text-align: center;">${doctorRecruitExt.operResult==null?'——':doctorRecruitExt.operResult}</td>
                <td style="text-align: center;">${doctorRecruitExt.auditionResult==null?'——':doctorRecruitExt.auditionResult}</td>
                <td style="text-align: center;">${doctorRecruitExt.totleResult==null?'——':doctorRecruitExt.totleResult}</td>
            </tr>
            </c:forEach>
            <c:if test="${empty doctorRecruitExtList}">
            <tr>
                <td colspan="7">无记录</td>
            </tr>
            </c:if>
        </table>
    </div>
   <div class="page" style="text-align: center;">
        <input id="currentPage" type="hidden" name="currentPage" value=""/>
        <c:set var="pageView" value="${pdfn:getPageView(doctorRecruitExtList)}" scope="request"></c:set>
	  	<pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
      
