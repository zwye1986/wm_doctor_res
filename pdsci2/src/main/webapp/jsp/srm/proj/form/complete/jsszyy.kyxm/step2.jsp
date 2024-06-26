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
            var ue1 = initUEditer("projTask");
            var ue1 = initUEditer("proImplement");
            var ue1 = initUEditer("achievement");
            var ue1 = initUEditer("development");
            var ue1 = initUEditer("meaning");
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

    <font style="font-size: 14px; font-weight:bold;color: #333; ">一、项目工作报告</font>
  <table class="basic" style="width: 100%">
      <tr>
          <th colspan="6" style="text-align: left;padding-left: 20px;">（一）、项目任务书<span style="color: red">（按合同书或任务书填写）</span>：</th>
      </tr>
      <tr>
          <td colspan="6">
                <%--<textarea style="height: 120px;width: 100%" name="projTask">${resultMap.projTask}</textarea>--%>
              <c:choose>
                  <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.projTask}</c:when>
                  <c:otherwise>
                      <script id="projTask" type="text/plain" name="projTask" style="width:100%;height:400px;margin-right: 10px;">${resultMap.projTask}</script>
                  </c:otherwise>
              </c:choose>
          </td>
      </tr>
      <tr>
          <th colspan="6" style="text-align: left;padding-left: 20px;">（二）、项目实施情况<span style="color: red">（对照合同书或任务书中的考核指标列表逐条说明完成情况。如果项目实施过程中对原研究内容或任务有调整或修改应作出说明）</span>：</th>
      </tr>
      <tr>
          <td colspan="6">
              <%--<textarea style="height: 120px;width: 100%" name="proImplement">${resultMap.proImplement}</textarea>--%>
              <c:choose>
                  <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.proImplement}</c:when>
                  <c:otherwise>
                      <script id="proImplement" type="text/plain" name="proImplement" style="width:100%;height:400px;margin-right: 10px;">${resultMap.proImplement}</script>
                  </c:otherwise>
              </c:choose>
          </td>
      </tr>
      <tr>
          <th colspan="6" style="text-align: left;padding-left: 20px;">（三）、项目取得的成果<span style="color: red">（对照合同书或任务书预期的成果列出）</span>：</th>
      </tr>
      <tr>
          <td colspan="6">
              <%--<textarea style="height: 120px;width: 100%" name="achievement">${resultMap.achievement}</textarea>--%>
              <c:choose>
                  <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.achievement}</c:when>
                  <c:otherwise>
                      <script id="achievement" type="text/plain" name="achievement" style="width:100%;height:400px;margin-right: 10px;">${resultMap.achievement}</script>
                  </c:otherwise>
              </c:choose>
          </td>
      </tr>
      <tr>
          <th colspan="6" style="text-align: left;padding-left: 20px;">（四）、项目执行中的人才培养情况：</th>
      </tr>
      <tr>
          <td colspan="6">
              <%--<textarea style="height: 120px;width: 100%" name="development">${resultMap.development}</textarea>--%>
              <c:choose>
                  <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.development}</c:when>
                  <c:otherwise>
                      <script id="development" type="text/plain" name="development" style="width:100%;height:400px;margin-right: 10px;">${resultMap.development}</script>
                  </c:otherwise>
              </c:choose>
          </td>
      </tr>
      <tr>
          <th colspan="6" style="text-align: left;padding-left: 20px;">（五）、此项目研究的科学意义和应用前景（社会效益、经济效益）、存在问题和进一步申报上级项目的打算</th>
      </tr>
      <tr>
          <td colspan="6">
              <%--<textarea style="height: 120px;width: 100%" name="meaning">${resultMap.meaning}</textarea>--%>
              <c:choose>
                  <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.meaning}</c:when>
                  <c:otherwise>
                      <script id="meaning" type="text/plain" name="meaning" style="width:100%;height:400px;margin-right: 10px;">${resultMap.meaning}</script>
                  </c:otherwise>
              </c:choose>
          </td>
      </tr>
    </table>
<div class="button" style="width: 100%;
<c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
    <input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
    <input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="保&#12288;存"/>
</div>
