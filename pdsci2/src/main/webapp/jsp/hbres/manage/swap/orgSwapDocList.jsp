<script>
    function toPage(page){
        if(page){
            $('#currentPage2').val(page);
        }
        getSwapContent();
    }
</script>
<div style="height: 100%;background: white;">
    <div class="main_hd">
        <h2 class="underline">
            缺额调剂
            <div style="float: right;margin-right: 10px;">
                <input class="btn_green" style="margin-top: 25px;" onclick="exportHbSwapInfo();" type="button" value="导&#12288;出">
                <input class="btn_green" style="margin-top: 25px;" onclick="closeTheSlide();" type="button" value="关&#12288;闭">
            </div>
        </h2>
    </div>
    <div style="height: 40px;margin-top:10px;">
        <form id="swapDocForm">
            <input type="hidden" name="orgFlow" value="${param.orgFlow}"/>
            <input type="hidden" name="speId" value="${param.speId}"/>
            <input id="currentPage2" type="hidden" name="currentPage" value=""/>
            <%--<div style="float: left;margin-left: 10px;">--%>
                <%--基地：--%>
                <%--<select name="orgFlow" class="select" onchange="getSwapContent();">--%>
                    <%--<c:forEach items="${orgList}" var="org">--%>
                        <%--<option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</div>--%>
            <%--<c:if test="${!empty param.orgFlow}">--%>
                <%--<div style="float: left;margin-left: 10px;">--%>
                    <%--专业：--%>
                    <%--<select name="speId" class="select" onchange="getSwapContent();">--%>
                        <%--<option/>--%>
                        <%--<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="speDict">--%>
                            <%--<c:set var="k" value="${param.orgFlow}${speDict.dictId}"/>--%>
                            <%--<c:set var="assignNum" value="${speAssignMap[k]}"/>--%>
                            <%--<c:if test="${!empty assignNum}">--%>
                                <%--<option value="${speDict.dictId}" <c:if test="${param.speId eq speDict.dictId}">selected</c:if>>--%>
                                    <%--${speDict.dictName}(${assignNum-recruitDocMap[k]})--%>
                                <%--</option>--%>
                            <%--</c:if>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</c:if>--%>
            <%--<div style="clear: left;height: 5px;"></div>--%>
            <div style="float: left;margin-left: 10px;">
                姓名：<input type="text" name="userName" value="${param.userName}" onchange="getSwapContent();" class="input" style="width: 80px;"/>
            </div>
            <div style="float: left;margin-left: 10px;">
                手机：<input type="text" name="userPhone" value="${param.userPhone}" onchange="getSwapContent();" class="input" style="width: 100px;"/>
            </div>
            <div style="float: left;margin-left: 10px;">
                邮箱：<input type="text" name="userEmail" value="${param.userEmail}" onchange="getSwapContent();" class="input" />
            </div>
            <div style="float: left;margin-left: 10px;">
                身份证：<input type="text" name="idNo" value="${param.idNo}" onchange="getSwapContent();" class="input" />
            </div>
            <div style="float: left;margin-left: 10px;padding-top: 5px;height: 32px;">
                <label>
                    <input type="checkbox" name="isSwap" value="${GlobalConstant.FLAG_Y}"
                           <c:if test="${param.isSwap eq GlobalConstant.FLAG_Y}">checked</c:if>
                           onchange="getSwapContent();"
                           style="float: left;"
                    />已被调剂
                </label>
            </div>
            <div style="float: left;margin-left: 10px;padding-top: 5px;height: 32px;">
                <label>
                    <input type="checkbox" name="isNotRecruitDoc" value="${GlobalConstant.FLAG_Y}"
                           <c:if test="${param.isNotRecruitDoc eq GlobalConstant.FLAG_Y || isNotRecruitDoc eq GlobalConstant.FLAG_Y}">checked</c:if>
                           onchange="getSwapContent();"
                           style="float: left;"
                    />未被录取学员
                </label>
            </div>
        </form>
    </div>
    <div style="margin-top:5px;margin-left:8px;">
        <h2>待调剂学员总人数：${notSwapNum}人</h2>
    </div>
    <c:set var="isFree" value="${pdfn:isFreeGlobal()}"></c:set>
    <div style="padding: 0 10px;overflow: auto;height: 75%;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid orgTab">
            <tr>
                <th style="width: 8%;">姓名</th>
                <th style="width: 5%;">笔试<br/>成绩</th>
                <th style="width: 8%;">人员类型</th>
                <th style="width: 15%;">工作单位</th>
                <th style="width: 10%;">执业医师<br/>资格证号</th>
                <th style="width: 24%;">调剂<br/>培训基地</th>
                <th style="width: 10%;">调剂专业</th>
                <th style="width: 5%;">人员<br/>信息</th>
                <c:if test="${!isFree}">
                    <th style="width: 5%;">操作</th>
                </c:if>
            </tr>
            <c:forEach items="${adminSwapDocList}" var="doc">
                <tr>
                    <td>${doc.userName}</td>
                    <td>${doc.examResult}</td>
                    <td>${doc.doctorTypeName}</td>
                    <td>${doc.workOrgName}</td>
                    <td title="${doc.qualifiedNo}"><label style="color: blue;">${pdfn:cutString(doc.qualifiedNo,6,true,3)}</label></td>
                    <td>${doc.swapOrgName}</td>
                    <td>${doc.swapSpeName}</td>
                    <td>
                        <a onclick="getInfo('${doc.doctorFlow}','${doc.recruitFlow}');">详情</a>
                    </td>
                    <c:if test="${!isFree}">
                    <td>
                        <c:if test="${!(GlobalConstant.FLAG_Y eq doc.adminSwapFlag) || doc.recruitFlag eq GlobalConstant.FLAG_N}">
                            <a onclick="toSwap('${doc.doctorFlow}','${param.orgFlow}','${param.speId}','${doc.examResult}')">调剂</a>
                        </c:if>
                        <c:if test="${GlobalConstant.FLAG_Y eq doc.adminSwapFlag && doc.recruitFlag ne GlobalConstant.FLAG_N}">
                            <a onclick="delSwap('${doc.doctorFlow}','${doc.recruitFlow}','${doc.swapOrgName}','${doc.swapSpeName}');">撤销</a>
                        </c:if>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty adminSwapDocList}">
                <tr>
                    <td colspan="10">
                        <a title="关闭" onclick="closeTheSlide();">暂无记录</a>
                    </td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="text-align: center">
        <c:set var="pageView" value="${pdfn:getPageView(adminSwapDocList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>

