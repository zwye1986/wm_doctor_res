<div class="search_table">
    <table class="grid" >
        <thead>
        <tr>
            <th style="width: 10%;">基地科室代码</th>
            <th style="width: 25%;">基地科室名称</th>
            <th style="width: 25%;">标准科室名称</th>
            <th style="width: 20%;">关联标准科室</th>
            <th style="width: 20%;">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${baseDeptList}" var="dept">
            <tr dataflow="${dept.deptFlow}" dataordinal="${dept.ordinal}">
                <td class="deptCode" datacode="${dept.deptCode}">${dept.deptCode}</td>
                <td class="deptName" dataname="${dept.deptName}">${dept.deptName}</td>
                <td>${dept.standardDeptName}</td>
                <td>
                    <c:if test="${dept.recordStatus == GlobalConstant.RECORD_STATUS_Y }">
                        <c:if test="${empty dept.standardDeptFlow}"><a onclick="relStdDept('${dept.deptFlow}');" class="btn">未关联</a></c:if>
                        <c:if test="${not empty dept.standardDeptFlow}"><a onclick="relStdDept('${dept.deptFlow}');" class="btn">已关联</a></c:if>
                    </c:if>
                    <c:if test="${dept.recordStatus == GlobalConstant.RECORD_STATUS_N }">
                        <c:if test="${empty dept.standardDeptFlow}">未关联</c:if>
                        <c:if test="${not empty dept.standardDeptFlow}">已关联</c:if>
                    </c:if>
                </td>
                <td>
                    <c:if test="${dept.recordStatus == GlobalConstant.RECORD_STATUS_Y }">
                        <a onclick="editDept('${dept.deptFlow}');" class="btn" style="margin: 3px">编辑</a>
                        <a onclick="editSave('toggleStatus', {recordStatus: 'N'});" class="btn" style="margin: 3px">停用</a>
                    </c:if>
                    <c:if test="${dept.recordStatus == GlobalConstant.RECORD_STATUS_N }">
                        <a onclick="editSave('toggleStatus', {recordStatus: 'Y'});" class="btn" style="margin: 3px">启用</a>
                    </c:if>
                    <a onclick="baseDeptDelete();" class="btn" style="margin: 3px">删除</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty baseDeptList}">
            <tr>
                <td align="center" colspan="10">无记录</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(baseDeptList)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
