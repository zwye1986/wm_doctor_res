<c:if test="${param.view != GlobalConstant.FLAG_Y}">
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

        function doBack() {
            <c:if test="${sessionScope.projListScope ne GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            window.location.href = "<s:url value='/srm/proj/mine/process?projFlow='/>" + $("#projFlow").val();
            </c:if>
            <c:if test="${sessionScope.projListScope eq GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            window.location.href = "<s:url value='/srm/proj/search/recList?projFlow='/>" + $("#projFlow").val();
            </c:if>
        }
        //单选
        function single(box){
            var curr=box.checked;
            if(curr){
                var name=box.name;
                $(":checkbox[name='"+name+"']").attr("checked",false);
            }
            box.checked = curr;
        }
    </script>
</c:if>

<style type="text/css">
    .borderNone{border-bottom-style: none;}
</style>
</head>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step1" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <%--<font style="font-size: 14px; font-weight:bold;color: #333; ">一、基本信息</font>--%>
    <table class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <td colspan="4" style="font-size: 14px; font-weight:bold; text-align: center">
                <span>课题中止情况说明</span>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <p><input type="text" style="width: 15%" class="inputText" name="orgName" value="${empty resultMap.orgName?proj.applyDeptName:resultMap.orgName}"></input>科室
                    <input type="text" style="width: 15%" class="inputText" name="username" value="${empty resultMap.username?proj.applyUserName:resultMap.username}">主持的
                    <input type="text" style="width: 25%" class="inputText" name="origin" value="${empty resultMap.origin?proj.projSecondSourceName:resultMap.origin}">来源的
                    <input type="text" style="width: 30%" class="inputText" name="topic" value="${empty resultMap.topic?proj.projName:resultMap.topic}">课题，
                    因<input type="text" style="width: 30%" class="inputText" name="reason" value="${resultMap.reason}">，目前已超过研究期限1年尚未完成研究任务。已被告知并了解《江苏省中医院科研项目管理暂行办法》（省中科[2009]第97号）关于课题中止相关规定，在以后的科研工作中避免再发生此类事件。
                </p>
            </td>
        </tr>
    </table>
</form>
<div class="button" style="width: 100%;
<c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
    <input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
    <c:if test="${sessionScope.projListScope ne GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
        <input id="nxt" type="button" onclick="nextOpt('finish')" class="search" value="完&#12288;成"/>
    </c:if>
    <c:if test="${sessionScope.projListScope eq GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
        <%--医院管理员编辑完成标识符更改--%>
        <input id="nxt" type="button" onclick="nextOpt('localFinish')" class="search" value="完&#12288;成"/>
    </c:if>
</div>