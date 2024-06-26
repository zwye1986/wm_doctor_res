<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function nextOpt(step){
            if(false==$("#projForm").validationEngine("validate")){
                return false;
            }
            var form = $('#projForm');
            form.append("<input name='nextPageName' value='"+step+"' type='hidden'/>");
            $('#nxt').attr({"disabled":"disabled"});
            $('#prev').attr({"disabled":"disabled"});
            jboxStartLoading();
            form.submit();
        }
        $(document).ready(function(){
            var ue1 = initUEditer("prescribedWork");
            var ue1 = initUEditer("research");
            var ue1 = initUEditer("implementation");
            var ue1 = initUEditer("evaluation");
            var ue1 = initUEditer("fundsUsed");
            var ue1 = initUEditer("jobIdea");
            var ue1 = initUEditer("question");
            var ue1 = initUEditer("suggestion");
        });
    </script>
</c:if>
<style type="text/css">
    .borderNone{border-bottom-style: none;}
</style>
</head>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step2" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
    <c:if test="${not empty projRec.pageGroupName}">
    <input type="hidden"  name="pageGroupName" value="${projRec.pageGroupName}"/>
    </c:if>

    <%--<font style="font-size: 14px; font-weight:bold;color: #333; ">一、基本信息</font>--%>
  <table class="basic" style="width: 100%; margin-top: 10px;">
         <tr>
             <th colspan="6" style="text-align: left;padding-left: 20px;">本年度科研项目合同书中规定完成的工作：</th>
         </tr>
         <tr>
            <td colspan="6">
                <%--<textarea style="height: 120px;width: 100%" name="prescribedWork">${resultMap.prescribedWork}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.prescribedWork}</c:when>
                    <c:otherwise>
                        <script id="prescribedWork" type="text/plain" name="prescribedWork" style="width:100%;height:200px;margin-right: 10px;">${resultMap.prescribedWork}</script>
                    </c:otherwise>
                </c:choose>
            </td>
         </tr>
        <tr>
            <th colspan="6" style="text-align: left;padding-left: 20px;">实际完成的科研工作及取得的成果（应详细填写研究的结果、发表论文等）</th>
        </tr>
        <tr>
            <td colspan="6">
                <%--<textarea style="height: 120px;width: 100%" name="research">${resultMap.research}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.research}</c:when>
                    <c:otherwise>
                        <script id="research" type="text/plain" name="research" style="width:100%;height:200px;margin-right: 10px;">${resultMap.research}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <th colspan="6" style="text-align: left;padding-left: 20px;">研究方案的执行情况，有无重大调整，有调整请说明原因（主要针对研究方法学）；项目组成员有无调整（如有请填附表）：</th>
        </tr>
          <tr>
              <td colspan="6">
                  <%--<textarea style="height: 120px;width: 100%" name="implementation">${resultMap.implementation}</textarea>--%>
                  <c:choose>
                      <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.mainViewAndContent}</c:when>
                      <c:otherwise>
                          <script id="implementation" type="text/plain" name="implementation" style="width:100%;height:200px;margin-right: 10px;">${resultMap.implementation}</script>
                      </c:otherwise>
                  </c:choose>
              </td>
          </tr>
  </table>

  <table class="basic" style="width: 100%">
        <tr>
            <th colspan="6" style="text-align: left;padding-left: 20px;">对本阶段研究工作的评价（详细填写其学术上和应用上的意义，与国内外先进水平的比较）：</th>
        </tr>
        <tr>
            <td colspan="6">
                <%--<textarea style="height: 120px;width: 100%" name="evaluation">${resultMap.evaluation}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.evaluation}</c:when>
                    <c:otherwise>
                        <script id="evaluation" type="text/plain" name="evaluation" style="width:100%;height:200px;margin-right: 10px;">${resultMap.evaluation}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <th colspan="6" style="text-align: left;padding-left: 20px;">本阶段项目经费使用情况：</th>
        </tr>
        <tr>
            <td colspan="6">
                <%--<textarea style="height: 120px;width: 100%" name="fundsUsed">${resultMap.fundsUsed}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.fundsUsed}</c:when>
                    <c:otherwise>
                        <script id="fundsUsed" type="text/plain" name="fundsUsed" style="width:100%;height:200px;margin-right: 10px;">${resultMap.fundsUsed}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <th colspan="6" style="text-align: left;padding-left: 20px;">对下阶段研究工作的设想（具体填写方法、指标和进度安排）：</th>
        </tr>
        <tr>
            <td colspan="6">
                <%--<textarea style="height: 120px;width: 100%" name="jobIdea">${resultMap.jobIdea}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.jobIdea}</c:when>
                    <c:otherwise>
                        <script id="jobIdea" type="text/plain" name="jobIdea" style="width:100%;height:200px;margin-right: 10px;">${resultMap.jobIdea}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <th colspan="6" style="text-align: left;padding-left: 20px;">存在问题（主要指学术、技术方面）：</th>
        </tr>
        <tr>
            <td colspan="6">
                <%--<textarea style="height: 120px;width: 100%" name="question">${resultMap.question}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.question}</c:when>
                    <c:otherwise>
                        <script id="question" type="text/plain" name="question" style="width:100%;height:200px;margin-right: 10px;">${resultMap.question}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <th colspan="6" style="text-align: left;padding-left: 20px;" >科技处审查意见：</th>
        </tr>
        <tr>
            <td colspan="6">
                <%--<textarea style="height: 120px;width: 100%" name="suggestion">${resultMap.suggestion}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.suggestion}</c:when>
                    <c:otherwise>
                        <script id="suggestion" type="text/plain" name="suggestion" style="width:100%;height:200px;margin-right: 10px;">${resultMap.suggestion}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <%--<tr>
            <td style="text-align: right" width="20%">项目负责人签字：</td>
            <td width="30%" colspan="2"><input type="text" name="applySign" value="${resultMap.applySign}" class="inputText borderNone" style="width: 80%;"/></td>
            <td style="text-align: right" width="20%">填报时间：</td>
            <td width="30%" colspan="2"><input name="applyTime"  type="text" value="${resultMap.applyTime}" class="inputText ctime" style="width: 160px;"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
        </tr>--%>
    </table>
<div class="button" style="width: 100%;
<c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
    <input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
    <input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="保&#12288;存"/>
</div>
