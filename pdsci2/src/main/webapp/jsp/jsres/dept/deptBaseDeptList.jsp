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
            <tr dataflow="${dept.deptFlow}" dataordinal="${dept.ordinal}" orgFlow="${dept.orgFlow}">
                <td class="deptCode" datacode="${dept.deptCode}">${dept.deptCode}</td>
                <td class="deptName" dataname="${dept.deptName}">${dept.deptName}</td>
                <td>${dept.standardDeptName}</td>
                <td>
                    <c:if test="${dept.recordStatus == GlobalConstant.RECORD_STATUS_Y }">
                        <c:if test="${empty dept.standardDeptFlow}"><a onclick="relStdDept('${dept.deptFlow}');" >未关联</a></c:if>
                        <c:if test="${not empty dept.standardDeptFlow}"><a onclick="relStdDept('${dept.deptFlow}');">已关联</a></c:if>
                    </c:if>
                    <c:if test="${dept.recordStatus == GlobalConstant.RECORD_STATUS_N }">
                        <c:if test="${empty dept.standardDeptFlow}">未关联</c:if>
                        <c:if test="${not empty dept.standardDeptFlow}">已关联</c:if>
                    </c:if>
                </td>
                <td>
                    <a href="javascript:void(0)" onclick="showDept('${dept.deptFlow}','${dept.deptName}','','');" class="btn" style="padding: 0 2px">查看</a>
                    <c:if test="${dept.recordStatus == GlobalConstant.RECORD_STATUS_Y }">
                        <a onclick="editDept('${dept.deptFlow}');"  style="margin: 10px">编辑</a>
                        <a onclick="editSave('toggleStatus', {recordStatus: 'N'});"  style="margin: 10px">停用</a>
                    </c:if>
                    <c:if test="${dept.recordStatus == GlobalConstant.RECORD_STATUS_N }">
                        <a onclick="editSave('toggleStatus', {recordStatus: 'Y'});"  style="margin: 10px">启用</a>
                    </c:if>
                    <a onclick="baseDeptDelete();"  style="margin: 10px">删除</a>
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
