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
            var ue1 = initUEditer("research");
            var ue1 = initUEditer("prescribedWork");
        });
    </script>
</c:if>
<style type="text/css">
    .borderNone{border-bottom-style: none;}
</style>
</head>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data">
    <input type="hidden" id="pageName" name="pageName" value="step2" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>


  <table class="basic" style="width: 100%">
        <tr>
            <th colspan="6" style="text-align: left;padding-left: 20px;">变更事由及项目已完成情况（变更项目负责人须写明新项目负责人的性别、出生时间、职称、工作单位、联系电话等情况，变更前后项目负责人均要签字。如填写不下请另加页)：</th>
        </tr>
        <tr>
            <td colspan="6">
                <%--<textarea style="height: 120px;width: 100%" name="prescribedWork">${resultMap.prescribedWork}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.prescribedWork}</c:when>
                    <c:otherwise>
                        <script id="prescribedWork" type="text/plain" name="prescribedWork" style="width:100%;height:400px;margin-right: 10px;">${resultMap.prescribedWork}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <th colspan="6" style="text-align: left;padding-left: 20px;">下阶段工作打算（工作内容及进度等）：</th>
        </tr>
        <tr>
            <td colspan="6">
                <%--<textarea style="height: 120px;width: 100%" name="research">${resultMap.research}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.research}</c:when>
                    <c:otherwise>
                        <script id="research" type="text/plain" name="research" style="width:100%;height:400px;margin-right: 10px;">${resultMap.research}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <th colspan="6" style="text-align: left;padding-left: 20px;" >科技处审查意见：</th>
        </tr>
        <tr>
            <td colspan="6">
                <textarea style="height: 120px;width: 100%" name="suggestion">${resultMap.suggestion}</textarea>
            </td>
        </tr>
    </table>
    </form>
<div class="button" style="width: 100%;
<c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
    <input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
    <input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="保&#12288;存"/>
</div>
