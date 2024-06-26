<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	
	function getInfo(doctorFlow){
		jboxOpen("<s:url value='/sczyres/doctor/getsingupinfofordialog'/>?userFlow="+doctorFlow,"人员信息",1000,550);
	}
	
	function openSwapPage(doctorFlow , recruitFlow){
		jboxOpen("<s:url value='/sczyres/manage/openSwapPage'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitFlow,"调剂",1100,550);
	}
	function searchSwapDoctor(){
		var data = $("#searchForm").serialize();
		jboxPostLoad("content","<s:url value='/sczyres/manage/swap'/>",data ,true);
	}
	function changeSwapStatus(obj){
        var tds = $(".cell");
        $(tds).each(function(){
            if('1'==$(obj).val()){
                if($(this).find('a').text()=='调剂'){
                    $(this).closest('tr').hide();
                }else{
                    $(this).closest('tr').show();
                }
            }else if('0'==$(obj).val()){
                if($(this).find('a').text()=='调剂'){
                    $(this).closest('tr').show();
                }else{
                    $(this).closest('tr').hide();
                }
            }else {
                $(this).closest('tr').show();
            }
        })
    }
</script>
<div class="main_bd" id="div_table_0" > 
   <div class="main_hd">
       <h2 class="underline">学员调剂</h2>
	   <div class="div_search" style="text-align:right;">
	       <form id="searchForm">
            调剂状态：<select name="swapStatus" class="select" onchange="changeSwapStatus(this);">
               <option value="">全部</option>
               <option value="1">已调剂</option>
               <option value="0">未调剂</option>
               </select>
			<input type="text" id="key" style="width: 200px;" name="key" value="${param.key}" class="input" placeholder="姓名/手机号/邮件/身份证" 
			onchange="searchSwapDoctor();"/>
		    </form>
		</div>
   </div>

   <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
              <th style="width:10%;">姓名</th>
              <th style="width:20%;">身份证号</th>
              <th style="width:25%;">填报志愿</th>
              <th style="width:25%;">调剂志愿</th>
              <th style="width:10%;">人员信息</th>
              <c:if test="${empty param.isLocal}">
              <th style="width:10%;">操作</th>
              </c:if>
            </tr>
            <c:forEach items="${doctorRecruitExts}" var="noRecruit">
                <%--<c:if test="${!(empty swapRecruit) && (swapRecruit.recruitFlag eq 'N')}">--%>
                <tr>
                    <td>${noRecruit.sysUser.userName}</td>
                    <td>${noRecruit.sysUser.idNo}</td>
                    <td>${noRecruit.orgName}<br/>${noRecruit.catSpeName}&#12288;${noRecruit.speName}&#12288;${noRecruit.secondSpeName}</td>
                    <td>
                        <c:set var="swapRecruit" value='${swapRecruitMap[noRecruit.doctorFlow]}'></c:set>
                        <c:if test='${empty swapRecruit}'>
                            <span>暂无调剂</span>
                        </c:if>
                        <c:if test='${!empty swapRecruit}'>
                            <span>${swapRecruit.orgName}<br/>${swapRecruit.catSpeName}&#12288;${swapRecruit.speName}&#12288;${swapRecruit.secondSpeName}</span>
                        </c:if>
                    </td>
                    <td><a class="btn" onclick="getInfo('${noRecruit.doctorFlow}');">详情</a></td>
                    <c:if test="${empty param.isLocal}">
                    <td class="cell">
                        <c:if test='${empty swapRecruit}'>
                            <a class="btn" onclick="openSwapPage('${noRecruit.doctorFlow}' , '${noRecruit.recruitFlow}');">调剂</a>
                        </c:if>
                        <c:if test='${!empty swapRecruit}'>
                                <c:if test="${empty swapRecruit.recruitFlag}">
                                    基地未确认
                                </c:if>
                                <c:if test="${swapRecruit.recruitFlag eq 'Y'}">
                                    <c:if test="${ empty swapRecruit.confirmFlag}">学员未确认</c:if>
                                    <c:if test="${ 'Y' eq swapRecruit.confirmFlag}">学员已确认</c:if>
                                    <c:if test="${ 'N' eq swapRecruit.confirmFlag}">
                                        学员已放弃
                                        <%--<a class="btn" onclick="openSwapPage('${noRecruit.doctorFlow}' , '${noRecruit.recruitFlow}');">调剂</a>--%>
                                    </c:if>
                                </c:if>
                                <c:if test="${swapRecruit.recruitFlag eq 'N'}">
                                    <a class="btn" onclick="openSwapPage('${noRecruit.doctorFlow}' , '${noRecruit.recruitFlow}');">调剂</a>
                                </c:if>
                        </c:if>
                        </td>
                    </c:if>
                </tr>
                <%--</c:if>--%>
            </c:forEach>
          </table>
    </div>
</div>
