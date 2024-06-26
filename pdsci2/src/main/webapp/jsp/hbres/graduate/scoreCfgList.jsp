<table border="0" cellpadding="0" cellspacing="0" class="grid">
    <colgroup>
        <col width="28%"/>
        <col width="28%"/>
        <col width="44%"/>
    </colgroup>
    <tbody id="tbody">
    <tr>
        <th>年份</th>
        <th>分数</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${cfgList}" var="scoreCfg">
        <tr>
            <td>${scoreCfg.cfgYear}</td>
            <td>${scoreCfg.cfgPassScore}</td>
            <td><a class="btn" onclick="editCfgInfo($(this).parent().parent().children().eq(0).text())">编辑</a>&#12288;<a class="btn" onclick="delCfgInfo($(this).parent().parent().children().eq(0).text())">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
    <c:if test="${empty cfgList}">
        <tr>
            <td colspan="3">无记录</td>
        </tr>
    </c:if>
</table>

<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(cfgList)}" scope="request"></c:set>
    <pd:pagination-jszy toPage="toPage"/>
</div>
