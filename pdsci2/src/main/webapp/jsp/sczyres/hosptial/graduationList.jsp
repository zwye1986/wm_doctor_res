<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	function toPage(page) {
		if(page!=undefined){
			$("#currentPage").val(page);			
		}
		audit('${statusId}',$("#key").val() , $("#currentPage").val());
	} 
	function getInfo(userFlow){
		jboxOpen("<s:url value='/sczyres/doctor/getsingupinfofordialog'/>?userFlow="+userFlow,"用户信息",1000,550);
	}
	function auditInfo(userFlow){
		jboxOpen("<s:url value='/sczyres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow,"用户信息",1000,550);
	}
//	$(document).ready(function(){
//		$("li").click(function(){
//			$(".tab_select").addClass("tab");
//			$(".tab_select").removeClass("tab_select");
//			$(this).removeClass("tab");
//			$(this).addClass("tab_select");
//			audit($(this).attr("statusId"),$("#key").val(),null);
//		});
//	});
	function search(){
        jboxPostLoad("content", "<s:url value='/sczyres/hosptial/graduationList'/>",$("#searchForm").serialize(), true);
	}
	function returnInfo(userFlow){
		jboxConfirm("确认退回?", function(){
		jboxPost("<s:url value='/sczyres/hosptial/returnInfo'/>",{"userFlow":userFlow}, function(resp){
			setTimeout(function(){
				searchPerson();
			},1000);
		} , null , true);
		});
	}
    function detail(doctorFlow,recordFlow,role,level){
        jboxOpen("<s:url value='/sczyres/hosptial/detail'/>?doctorFlow="+doctorFlow+"&recordFlow="+recordFlow+"&role="+role+"&level="+level,"学员详情",1000,550);
    }
    function audit66(flag,recordFlow,level){
        jboxConfirm("确认通过？",function(){
            jboxPost("<s:url value='/sczyres/hosptial/auditApply'/>?flag="+flag+"&recordFlow="+recordFlow+"&level="+level,
                    null,function(resp){
                if(resp=='1'){
                    jboxTip("操作成功");
                    search();
                }
            },null,false);
        })
    }

    function noPass(recordFlow,level){
        jboxOpen("<s:url value='/sczyres/hosptial/noPass'/>?recordFlow="+recordFlow+"&role=${param.role}&level="+level,"审核意见",500,300,true);
    }

    function chargeAudit(flag,recordFlow){
        jboxConfirm("确认"+(flag=='1'?'通过？':'退回？'),function(){
            jboxPost("<s:url value='/sczyres/manage/auditApply'/>?flag="+flag+"&recordFlow="+recordFlow,null,function(resp){
                if(resp=='1'){
                    jboxTip("操作成功");
                    search();
                }
            },null,false);
        })
    }

    function exportExl(role){
        var url = "<s:url value='/sczyres/manage/exportExl'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"),url);
    }

    function exportExl2(role){
        var url = "<s:url value='/sczyres/manage/exportExl2'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"),url);
    }
</script>
      <div class="main_hd">
        <h2>报名审核</h2>
        <%--<div class="title_tab" id="toptab">--%>
          <%--<ul>--%>
            <%--<li class="${statusId==regStatusEnumPassing.id?'tab_select':'tab' }" statusId="${regStatusEnumPassing.id }"><a>待审核</a></li>--%>
            <%--<li class="${statusId==regStatusEnumPassed.id?'tab_select':'tab' }" statusId="${regStatusEnumPassed.id }"><a>审核通过</a></li>--%>
            <%--<li class="${statusId==regStatusEnumUnPassed.id?'tab_select':'tab' }" statusId="${regStatusEnumUnPassed.id }"><a>审核不通过</a></li>--%>
          <%--</ul>--%>
        <%--</div>--%>
      </div>
      <div class="main_bd" id="div_table_0" >
         <form id="searchForm">
             <input name="role" value="${param.role}" type="hidden">
      	<div class="div_search">
      	  <%--<c:if test="${statusId==regStatusEnumPassed.id}">--%>
      	 <%--<a href="<s:url value='/sczyres/hosptial/exportpasseddoctor'/>?orgFlow=${sessionScope.currUser.orgFlow}" class="btn_green" style="float: right;margin-top: 5px;height:28px;">导出</a>--%>
      	<%--</c:if>--%>
      	<%--<input type="text" class="fr input" id="key" name="key" value="${param.key}" placeholder="姓名/手机号/邮件/身份证" onblur="searchPerson();" style="width:200px; height:25px;"/>--%>
              姓&#12288;&#12288;名：<input type="text" class="input" name="doctorName" value="${param.doctorName}" style="width: 136px;">&#12288;
              <c:if test="${param.role eq 'charge'}">
              基&#12288;&#12288;地：
              <select name="orgFlow" class="select" style="width: 136px;margin: 0 5px;" >
                  <option value="" >请选择</option>
                  <c:forEach items="${hospitals}" var="hos">
                      <option value="${hos.orgFlow}" <c:if test='${param.orgFlow eq hos.orgFlow}'>selected="selected"</c:if>>${hos.orgName}</option>
                  </c:forEach>
              </select>&#12288;
               </c:if>
              培训专业：
              <select id="catSpeId" name="catSpeId" class="select"  style="width: 136px;margin: 0 5px;">
                  <option value="">全部</option>
                  <option value="1" ${param.catSpeId eq '1'?'selected':''}>中医</option>
                  <option value="2" ${param.catSpeId eq '2'?'selected':''}>中医全科</option>
                  <option value="3" ${param.catSpeId eq '3'?'selected':''}>中医助理全科</option>
              </select>
                <c:if test="${param.role eq 'charge'}">
                    <br>
                </c:if>
              是否补考：
              <select   name="isMakeUp" class="select"  style="width: 136px;margin: 0 5px;">
                  <option value="">全部</option>
                  <option value="Y" ${param.isMakeUp eq 'Y'?'selected':''}>是</option>
                  <option value="N" ${param.isMakeUp eq 'N'?'selected':''}>否</option>
              </select>
              <c:if test="${param.role eq 'charge'}">
                  &#12288;审核状态：
              <select name="chargeStatusId" class="select" style="width: 136px;margin: 0 5px;">
                  <option value="">全部</option>
                  <option value="-1" ${param.chargeStatusId eq '-1'?'selected':''}>未审核</option>
                  <option value="1" ${param.chargeStatusId eq '1'?'selected':''}>已通过</option>
              </select>
              &#12288;
              </c:if>
              <c:if test="${(param.role eq 'hospital')&&(isJoint ne 'Y')}">
                  审核状态：
                  <select name="statusId" class="select" style="width: 136px;margin: 0 5px;">
                      <option value="">全部</option>
                      <option value="1" ${param.statusId eq '1'?'selected':''}>协同基地审核通过</option>
                      <option value="2" ${param.statusId eq '2'?'selected':''}>培训基地审核通过</option>
                      <option value="3" ${param.statusId eq '3'?'selected':''}>中管局审核通过</option>
                  </select>
                  &#12288;
              </c:if>
            <input type="button" value="查&#12288;询" class="btn_blue" onclick="search();">
            &#12288;
            <input type="button" value="导&#12288;出" class="btn_blue" onclick="exportExl();">
              &#12288;
            <input type="button" value="导入模板" class="btn_blue" onclick="exportExl2();">
        </div>
         </form>
         <div class="div_table">
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
              <th style="width: 30px;">序号</th>
              <th style="width: 60px;">姓名</th>
              <th style="width: 150px;">身份证</th>
              <th style="width: 160px;">培训基地</th>
              <th style="width: 100px;">学历</th>
              <th style="width: 100px;">专业</th>
              <c:if test="${param.role eq 'charge'}">
                  <th style="width: 160px;">基地审核人</th>
              </c:if>
              <th style="width: 150px;">操作</th>
            </tr>
            <c:forEach items="${graduationApplies}" var="apply" varStatus="s">
            <tr>
              <td>${s.index+1}</td>
              <td>${apply.doctorName}</td>
              <td>${apply.idNo}</td>
              <td>${apply.orgName}</td>
              <td>${apply.educationName}</td>
              <td>${apply.trainingSpeName}</td>
                <c:if test="${param.role eq 'charge'}">
                    <td>${apply.orgAuditorName}</td>
                </c:if>
              <td>
                  <a onclick="detail('${apply.doctorFlow}','${apply.recordFlow}','${param.role}','${level}')">详情</a>
                  <c:if test="${param.role eq 'hospital' && level eq 'Main'}">
                      <c:if test="${apply.xtOrgStatusId eq '-1'}">
                          <a onclick="audit66('1','${apply.recordFlow}','Main')">通过</a>
                          <a onclick="noPass('${apply.recordFlow}','Main')">退回</a>
                      </c:if>
                      <c:if test="${apply.xtOrgStatusId eq '1'}">
                          <c:if test="${apply.chargeStatusId ne '1'}">
                              基地已通过
                          </c:if>
                          <c:if test="${apply.chargeStatusId eq '1'}">
                              中管局已通过
                          </c:if>
                      </c:if>
                  </c:if>
                  <c:if test="${param.role eq 'hospital' && level eq 'Joint'}">
                      <c:if test="${apply.orgStatusId eq '-1'}">
                          <a onclick="audit66('1','${apply.recordFlow}','Joint')">通过</a>
                          <a onclick="noPass('${apply.recordFlow}','Joint')">退回</a>
                      </c:if>
                      <c:if test="${apply.orgStatusId eq '1'}">
                          <c:if test="${apply.xtOrgStatusId ne '1'}">
                              协同基地已通过
                          </c:if>
                          <c:if test="${apply.xtOrgStatusId eq '1'}">
                              主基地已通过
                          </c:if>
                          <c:if test="${apply.chargeStatusId eq '1'}">
                              中管局已通过
                          </c:if>
                      </c:if>
                  </c:if>
                  <c:if test="${param.role eq 'charge'}">
                      <c:if test="${apply.chargeStatusId eq '-1'}">
                          <a onclick="chargeAudit('1','${apply.recordFlow}')">通过</a>
                          <a onclick="noPass('${apply.recordFlow}')">退回</a>
                      </c:if>
                      <c:if test="${apply.chargeStatusId eq '1'}">
                          已通过
                      </c:if>
                  </c:if>
                  </td>
              </td>
            </tr>
            </c:forEach>
            <c:if test="${empty graduationApplies}">
              <tr>
                  <td colspan="20">暂无记录</td>
              </tr>
            </c:if>
          </table>
          <%--<c:if test="${statusId==regStatusEnumUnPassed.id }">--%>
          <%--<table border="0" cellpadding="0" cellspacing="0" class="grid">--%>
            <%--<tr>--%>
              <%--<th  style="text-align: left;padding-left: 40px;width: 100px;">姓名</th>--%>
              <%--<th  style="text-align: left;padding-left: 40px;">未通过原因</th>--%>
              <%--<th width="120px;">报名信息</th>--%>
<%--<!--               <th width="120px;">操作</th> -->--%>
            <%--</tr>--%>
            <%--<c:forEach items="${userList}" var="user">--%>
            <%--<tr>--%>
              <%--<td style="text-align: left;padding-left: 40px;">${user.sysUser.userName}</td>--%>
              <%--<td style="text-align: left;padding-left: 40px;">${user.disactiveReason}</td>--%>
              <%--<td ><a class="btn" onclick="getInfo('${user.sysUser.userFlow}');">报名信息</a></td>--%>
<%--<!--               <td></td> -->--%>
            <%--</tr>--%>
            <%--</c:forEach>--%>
          <%--</table>--%>
          <%--</c:if>--%>
        </div>
        <%--<div class="page" style="padding-right: 40px;">--%>
       	 <%--<input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>--%>
           <%--<c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"></c:set>--%>
	  		 <%--<pd:pagination-sczyres toPage="toPage"/>	 --%>
        <%--</div>--%>
      </div>
      
