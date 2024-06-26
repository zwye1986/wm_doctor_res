<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer2.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/viewer/viewer.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(function () {
        <c:forEach items="${signList}" var="sign" varStatus="status">
            var id = "ratateImg${status.index+1}"
            var viewer = new Viewer(document.getElementById(id), {
                url: 'data-original'
            });
        </c:forEach>
    });
</script>
<div class="search_table">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <thead>
            <tr>
                <th style="width: 50px">序号</th>
                <th style="width: 120px">基地名称</th>
                <th style="width: 65px">院长姓名</th>
                <th style="width: 65px">审核状态</th>
                <th style="width: 65px">使用状态</th>
                <th style="width: 80px">上传时间</th>
                <th style="width: 100px">修改时间</th>
                <th style="width: 100px">年份</th>
                <th style="width: 80px">签名图片</th>
                <th style="width: 80px">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${signList }" var="sign" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${sign.orgName}</td>
                <td>${sign.presidentName}</td>
                <td>${sign.auditStatusName}</td>
                <td>${sign.useStatusName}</td>
                <td>${pdfn:transDate(sign.createTime)}</td>
                <td>${sign.stopTime}</td>
                <td>${sign.sessionNumber}</td>
                <td  style="height: 100px;">
                    <div>
                        <ul >
                            <li id="ratateImg${status.index+1}">
                                <img src="${sysCfgMap['upload_base_url']}/${sign.signUrl}" style="width: 80px;height: 80px;" >
                            </li>
                        </ul>
                    </div>
                </td>
                <td>
                    <c:if test="${sign.auditStatusId eq 'Auditing'}">
                        <a class="btn" href="javascript:void(0);" onclick="signAudit('${sign.signFlow}');">审核</a>
                    </c:if>
                    <c:if test="${sign.auditStatusId ne 'Auditing'}">
                        ${sign.useStatusName}
                    </c:if>
                </td>
            </tr>
        </c:forEach>

        <c:if test="${empty signList }">
            <tr>
                <td colspan="10">无记录</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(signList)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
