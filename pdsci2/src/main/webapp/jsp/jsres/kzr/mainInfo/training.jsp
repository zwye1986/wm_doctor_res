<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
    .div_table h4 {
        color: #000000;
        font: 15px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info th,.grid th {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info td,.grid td {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
    }
</style>
<script type="text/javascript"
        src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
<div
        <c:if test="${isglobal eq 'Y'}"> style="position: relative;overflow-y: auto;height: 600px" </c:if> >
    <form id='BaseInfoForm' style="position:relative;">
        <input type="hidden" name="resBaseSpeDept.orgFlow"
               value="${empty baseSpeDept?sessionScope.currUser.orgFlow:baseSpeDept.orgFlow}"/>
        <input type="hidden" name="resBaseSpeDept.deptFlow" value="${deptFlow}"/>
        <input type="hidden" name="flag" value="${GlobalConstant.EQUIPMENT_INSTRUMENTS}"/>
        <div class="main_bd"  <c:if test="${isJoin eq 'Y'}"> style="position: relative;overflow-y: auto;height: 625px" </c:if>  >
            <div class="div_table">
                <h4>培训对象医疗工作量</h4>
                <c:if test="${viewFlag ne 'Y'}">
                    <img src="<s:url value='/jsp/res/images/test.png'/>" onclick="editInfo('Training','${deptFlow}');"
                         style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
                </c:if>

                <table cellspacing="0" cellpadding="0" class="base_info">
                    <colgroup>
                        <col width="20%"/>
                        <col width="30%"/>
                        <col width="20%"/>
                        <col width="30%"/>
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>轮转管床数：</th>
                        <td>${trainingForm.lzcws}张</td>
                        <th>日门诊量：</th>
                        <td>${trainingForm.rmzl}人次</td>
                    </tr>
                    <tr>
                        <th>日急诊量：</th>
                        <td>${trainingForm.rjzl}人次</td>
                        <th>
                          轮转必选科室手写系统病历数：
                        </th>
                        <td>${trainingForm.lzbx}份/科</td>
                    </tr>
                    <tr>
                        <th>近三年招收人数：</th>
                        <td>${trainingForm.zrs}人</td>
                        <th>当前在培人数：</th>
                        <td>${trainingForm.zprs}人</td>
                    </tr>
                    <tr>
                        <th>
                           住院医师规范化培训登记手册：
                        </th>
                        <td>
                            <c:if test="${trainingForm.dj ==GlobalConstant.FLAG_Y }">有</c:if>
                            <c:if test="${trainingForm.dj ==GlobalConstant.FLAG_N }">无</c:if>
                        </td>
                        <th>
                            住院医师规范化培训考核手册：
                        </th>
                        <td>
                            <c:if test="${trainingForm.kh ==GlobalConstant.FLAG_Y }">有</c:if>
                            <c:if test="${trainingForm.kh ==GlobalConstant.FLAG_N }">无</c:if>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="main_bd"  <c:if test="${isJoin eq 'Y'}"> style="position: relative;overflow-y: auto;height: 625px" </c:if>  >
            <div class="div_table">
                <h4>科室各种培训活动记录（可另附表）</h4>
                <c:if test="${viewFlag ne 'Y'}">
                    <img src="<s:url value='/jsp/res/images/test.png'/>" onclick="editInfo('Training','${deptFlow}');"
                         style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
                </c:if>
                <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table6">
                    <colgroup>
                        <col width="10%"/>
                        <col width="45%"/>
                        <col width="30%"/>
                        <col width="15%"/>
                    </colgroup>
                    <tr>
                        <td colspan="4" style="text-align: left">近三年入科教育、轮转计划表、教学查房、疑难死亡病例讨论、小讲课、出科考核。</td>
                    </tr>
                    <tr>
                        <th style="background-color: #f4f5f9;">序号</th>
                        <th style="background-color: #f4f5f9;">培训活动记录名称</th>
                        <th style="background-color: #f4f5f9;">日期</th>
                        <th style="background-color: #f4f5f9;">上传附件</th>
                    </tr>
                    <tbody id="workTb">
                    <c:forEach var="s" items="${trainingForm.trainingActivityForms}"
                               varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${s.activityName }</td>
                        <td>

                        </td>
                        <td>
                            <c:set var="file" value="${fileMap[s.appendix]}"></c:set>
                            <c:if test="${empty file}">
                                未上传
                            </c:if>
                            <c:if test="${not empty file}">
                                <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                                   target="_blank">${file.fileName}</a>
                            </c:if>
                        </td>
                    </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    <%--<c:if test="${viewFlag ne 'Y'}">
        <div class="btn_info">
            <input type="button" class="btn_green" value="编&#12288;辑"
                   onclick="editInfo('Training','${deptFlow}');"></input>
        </div>
    </c:if>--%>
    </form>
</div>

