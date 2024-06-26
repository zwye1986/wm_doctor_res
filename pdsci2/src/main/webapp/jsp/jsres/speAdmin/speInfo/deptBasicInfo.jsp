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
    .base_info th {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info td {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
    }
</style>
<script type="text/javascript"
        src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<script type="text/javascript">
    function showTable(obj) {
        var oDiv = document.getElementById(obj);
        var aDisplay = oDiv.style.display;
        if ("none" == aDisplay) {
            oDiv.style.display = "";

            var imgDivUp = document.getElementById(obj+"up");
            var imgDivDown = document.getElementById(obj+"down");
            imgDivUp.style.display = "";
            imgDivDown.style.display = "none";
        } else {
            oDiv.style.display = "none";
            var imgDivUp = document.getElementById(obj+"up");
            var imgDivDown = document.getElementById(obj+"down");
            imgDivUp.style.display = "none";
            imgDivDown.style.display = "";
        }
    }
</script>

<input type="hidden" id="resBase" name="resBase" value="${baseSpeDept}"/>
<form id='BaseInfoForm' style="position:relative;">
    <input type="hidden" name="resBaseSpeDept.orgFlow"
           value="${empty baseSpeDept?sessionScope.currUser.orgFlow:baseSpeDept.orgFlow}"/>
    <input type="hidden" name="resBaseSpeDept.speFlow" value="${speFlow}"/>
    <input type="hidden" name="flag" value="${GlobalConstant.DEPT_BASIC_INFO}"/>
    <div class="main_bd" <c:if test="${isJoin eq 'Y' or isglobal eq 'Y'}"> style="position: relative;overflow-y: auto;" </c:if> >
        <div class="div_table">
            <h4>基本信息</h4>
            <img id="jbxxdown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('jbxx');"  title="展开"
                 style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
            <img id="jbxxup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('jbxx');"  title="收缩"
                 style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
            <c:if test="${ishos ne 'Y'}">
                <img src="<s:url value='/jsp/res/images/test.png'/>" onclick="editInfo('DeptBasicInfo','${speFlow}');"
                     style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
            </c:if>
            <table id="jbxx" border="0" cellspacing="0" cellpadding="0" class="base_info">
                <colgroup>
                    <col width="17%"/>
                    <col width="13%"/>
                    <col width="17%"/>
                    <col width="14%"/>
                    <col width="14%"/>
                    <col width="15%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th>专业基地名称：</th>
                    <td>${speName}</td>
                    <th>专业基地编码：</th>
                    <td colspan="3">${speFlow}</td>
                </tr>
                <tr>
                    <th>专业基地负责人姓名：</th>
                    <td>${deptBasicInfoForm.speRespName}</td>
                    <th>负责人联系电话：</th>
                    <td>${deptBasicInfoForm.speRespPhone}</td>
                    <th>负责人邮箱：</th>
                    <td>${deptBasicInfoForm.speRespEmail}</td>
                </tr>
                <tr>
                    <th>教学主任姓名：</th>
                    <td>${deptBasicInfoForm.speDirName}</td>
                    <th>教学主任联系电话：</th>
                    <td>${deptBasicInfoForm.speDirPhone}</td>
                    <th>教学主任邮箱：</th>
                    <td>${deptBasicInfoForm.speDirEmail}</td>
                </tr>
                <tr>
                    <th>教学秘书姓名：</th>
                    <td>${deptBasicInfoForm.speSceName}</td>
                    <th>教学秘书联系电话：</th>
                    <td>${deptBasicInfoForm.speScePhone}</td>
                    <th>教学秘书邮箱：</th>
                    <td>${deptBasicInfoForm.speSceEmail}</td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="div_table">
            <h4>基本条件</h4>
            <img id="jbtjdown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('jbtj');"  title="展开"
                 style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
            <img id="jbtjup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('jbtj');"  title="收缩"
                 style="display:none ;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
            <c:if test="${ishos ne 'Y'}">
                <img src="<s:url value='/jsp/res/images/test.png'/>" onclick="editInfo('DeptBasicInfo','${speFlow}');"
                     style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
            </c:if>
            <table id="jbtj" style="display: none" border="0" cellspacing="0" cellpadding="0" class="base_info">
                <colgroup>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                </colgroup>
                <tbody>
                <%--<tr>
                    <th>医院类别：</th>
                    <td colspan="3">
                        <c:if test="${deptBasicInfoForm.hostType == GlobalConstant.FLAG_Y }">综合医院</c:if>
                        <c:if test="${deptBasicInfoForm.hostType == GlobalConstant.FLAG_N }">专科医院</c:if>
                    </td>
                </tr>--%>
                <tr>
                    <th>本年编制总床位数：</th>
                    <td>${deptBasicInfoForm.bzzcws}张</td>
                    <th>
                        本年实有总床位数：
                    </th>
                    <td>
                        ${deptBasicInfoForm.syzcws}张
                    </td>
                </tr>
                <tr>
                    <th>本年收治住院病人数：</th>
                    <td>${deptBasicInfoForm.nszzybrs}人次</td>
                    <th>
                        本年病床使用率：
                    </th>
                    <td>${deptBasicInfoForm.bcsyl}%</td>
                </tr>
                <tr>
                    <th>本年门诊量：</th>
                    <td>${deptBasicInfoForm.nmzl}人次</td>
                    <th>
                        本年急诊量：
                    </th>
                    <td>${deptBasicInfoForm.njzl}人次</td>
                </tr>
                <tr>
                    <th>本年病床周转次数：</th>
                    <td>${deptBasicInfoForm.bczzcs}次</td>
                    <th>
                        本年平均住院日：
                    </th>
                    <td>${deptBasicInfoForm.pjzyr}天</td>
                </tr>
                <tr>
                    <th>
                        本年出院病人数：</th>
                    <td>${deptBasicInfoForm.ncybrs}人次</td>
                    <th>
                        本年急诊手术例数：
                    </th>
                    <td>${deptBasicInfoForm.njzscls}例次</td>
                </tr>
                <c:if test="${speFlow eq '1600'}">
                    <tr>
                        <th>
                            产科本年分娩量：</th>
                        <td>${deptBasicInfoForm.cknfml}人次</td>
                        <th>
                            妇科本年收治病人数：
                        </th>
                        <td>${deptBasicInfoForm.fknszbrs}人次</td>
                    </tr>
                    <tr>
                        <th>产科本年收治病人数：</th>
                        <td colspan="3">${deptBasicInfoForm.cknszbrs}人次</td>
                    </tr>
                </c:if>
                <c:if test="${speFlow eq '1900'}">
                    <tr>
                        <th>本年麻醉总数：</th>
                        <td>${deptBasicInfoForm.nmzzszmk}人次</td>
                        <th>
                            本年麻醉恢复室病人数：
                        </th>
                        <td>${deptBasicInfoForm.mzhfsbrs}人次</td>
                    </tr>
                    <tr>
                        <th>本年疼痛门诊病人数：</th>
                        <td>${deptBasicInfoForm.ttmzbrs}人次</td>
                        <th>
                            本年重症监护室收治病人数：
                        </th>
                        <td>${deptBasicInfoForm.zzjhsszbrs}人次</td>
                    </tr>
                </c:if>
                <c:if test="${speFlow eq '2000'}">
                    <tr>
                        <th>本年活检标本病例数：</th>
                        <td>${deptBasicInfoForm.nhjbbbls}人次</td>
                        <th>
                            本年尸体解剖病例数：
                        </th>
                        <td>${deptBasicInfoForm.nstjpbls}人次</td>
                    </tr>
                    <tr>
                        <th>本年冰冻快速诊断量：</th>
                        <td>${deptBasicInfoForm.nbdkszdl}人次</td>
                        <th>
                            本年细胞学检查病例数：
                        </th>
                        <td>${deptBasicInfoForm.nxbxjcbls}人次</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>

        <div class="div_table">
            <h4>承担教学任务（近三年总数）</h4>
            <img id="cddown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('cd');"  title="展开"
                 style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
            <img id="cdup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('cd');"  title="收缩"
                 style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
            <c:if test="${ishos ne 'Y'}">
                <img src="<s:url value='/jsp/res/images/test.png'/>" onclick="editInfo('DeptBasicInfo','${speFlow}');"
                     style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
            </c:if>
            <table id="cd" style="display: none" border="0" cellspacing="0" cellpadding="0" class="base_info">
                <colgroup>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th>本科生：</th>
                    <td>
                        <c:if test="${deptBasicInfoForm.bksyw ==GlobalConstant.FLAG_Y }">有</c:if>
                        <c:if test="${deptBasicInfoForm.bksyw ==GlobalConstant.FLAG_N }">无</c:if> &#12288;&#12288;&#12288;&#12288;
                        <c:if test="${deptBasicInfoForm.bksyw ==GlobalConstant.FLAG_Y }">${deptBasicInfoForm.bksrs}人次</c:if>
                    </td>
                    <th>研究生（硕、博）：</th>
                    <td>
                        <c:if test="${deptBasicInfoForm.yjsyw ==GlobalConstant.FLAG_Y }">有</c:if>
                        <c:if test="${deptBasicInfoForm.yjsyw ==GlobalConstant.FLAG_N }">无</c:if> &#12288;&#12288;&#12288;&#12288;
                        <c:if test="${deptBasicInfoForm.yjsyw ==GlobalConstant.FLAG_Y }">${deptBasicInfoForm.yjsrs}人次</c:if>
                    </td>
                </tr>
                <tr>
                    <th>住院医师：</th>
                    <td>
                        <c:if test="${deptBasicInfoForm.zyysyw ==GlobalConstant.FLAG_Y }">有</c:if>
                        <c:if test="${deptBasicInfoForm.zyysyw ==GlobalConstant.FLAG_N }">无</c:if> &#12288;&#12288;&#12288;&#12288;
                        <c:if test="${deptBasicInfoForm.zyysyw ==GlobalConstant.FLAG_Y }">${deptBasicInfoForm.zyysrs}人次</c:if>
                    </td>
                    <th>进修医师：</th>
                    <td>
                        <c:if test="${deptBasicInfoForm.jxysyw ==GlobalConstant.FLAG_Y }">有</c:if>
                        <c:if test="${deptBasicInfoForm.jxysyw ==GlobalConstant.FLAG_N }">无</c:if> &#12288;&#12288;&#12288;&#12288;
                        <c:if test="${deptBasicInfoForm.jxysyw ==GlobalConstant.FLAG_Y }">${deptBasicInfoForm.jxysrs}人次</c:if>
                    </td>
                </tr>
                <tr>
                    <th>承担继续医学教育情况：</th>
                    <td colspan="3"><textarea readonly
                                              name="deptBasicInfoForm.cdjjyx">${deptBasicInfoForm.cdjjyx}</textarea>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="div_table">
            <h4>承担科研项目（省部级以上的国家发明奖和科技进步奖）</h4>
            <img id="kydown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('ky');"  title="展开"
                 style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
            <img id="kyup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('ky');"  title="收缩"
                 style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
            <c:if test="${ishos ne 'Y'}">

                <img src="<s:url value='/jsp/res/images/test.png'/>" onclick="editInfo('DeptBasicInfo','${speFlow}');"
                     style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
            </c:if>
            <table id="ky" style="display: none" border="0" cellspacing="0" cellpadding="0" class="base_info">
                <colgroup>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th>特等：</th>
                    <td>${deptBasicInfoForm.td}项</td>
                    <th>一等：</th>
                    <td>${deptBasicInfoForm.yd}项</td>
                </tr>
                <tr>
                    <th>二等：</th>
                    <td>${deptBasicInfoForm.ed}项</td>
                    <th>三等：</th>
                    <td>${deptBasicInfoForm.sd}项</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="div_table">
            <h4>其他</h4>
            <img id="qtdown" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('qt');"  title="展开"
                 style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
            <img id="qtup" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('qt');"  title="收缩"
                 style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
            <c:if test="${ishos ne 'Y'}">
                <img src="<s:url value='/jsp/res/images/test.png'/>" onclick="editInfo('DeptBasicInfo','${speFlow}');"
                     style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
            </c:if>
            <table id="qt" style="display: none" border="0" cellspacing="0" cellpadding="0" class="base_info">
                <colgroup>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th>国家临床重点专科：</th>
                    <td>
                        <c:if test="${deptBasicInfoForm.gjlczdzk == GlobalConstant.FLAG_Y }">是</c:if>
                        <c:if test="${deptBasicInfoForm.gjlczdzk == GlobalConstant.FLAG_N }">否</c:if>
                    </td>
                    <th>省重点建设学科：</th>
                    <td>
                        <c:if test="${deptBasicInfoForm.sszdjsxk == GlobalConstant.FLAG_Y }">是</c:if>
                        <c:if test="${deptBasicInfoForm.sszdjsxk == GlobalConstant.FLAG_N }">否</c:if>
                    </td>
                </tr>
                <tr>
                    <th>市重点建设学科：</th>
                    <td>
                        <c:if test="${deptBasicInfoForm.cityZdjsxk == GlobalConstant.FLAG_Y }">是</c:if>
                        <c:if test="${deptBasicInfoForm.cityZdjsxk == GlobalConstant.FLAG_N }">否</c:if>
                    </td>
                    <th>
                        学位培养点：
                    </th>
                    <td>
                        <c:if test="${deptBasicInfoForm.xwpyd ==GlobalConstant.FLAG_Y }">是</c:if>
                        <c:if test="${deptBasicInfoForm.xwpyd ==GlobalConstant.FLAG_N }">否</c:if>
                    </td>
                </tr>
                <c:if test="${deptBasicInfoForm.masterxw eq 'Y'}">
                    <c:if test="${not empty deptBasicInfoForm.masterSchlAndSpe[0].school}">
                        <tr>
                            <td style="text-align: right">
                                硕士
                            </td>
                            <td>学校：${deptBasicInfoForm.masterSchlAndSpe[0].school}</td>
                            <td colspan="2">专业：${deptBasicInfoForm.masterSchlAndSpe[0].spe}</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty deptBasicInfoForm.masterSchlAndSpe[1].school}">
                        <tr>
                            <td></td>
                            <td>学校：${deptBasicInfoForm.masterSchlAndSpe[1].school}</td>
                            <td colspan="2">专业：${deptBasicInfoForm.masterSchlAndSpe[1].spe}</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty deptBasicInfoForm.masterSchlAndSpe[2].school}">
                        <tr>
                            <td></td>
                            <td>学校：${deptBasicInfoForm.masterSchlAndSpe[2].school}</td>
                            <td colspan="2">专业：${deptBasicInfoForm.masterSchlAndSpe[2].spe}</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty deptBasicInfoForm.masterSchlAndSpe[3].school}">
                        <tr>
                            <td></td>
                            <td>学校：${deptBasicInfoForm.masterSchlAndSpe[3].school}</td>
                            <td colspan="2">专业：${deptBasicInfoForm.masterSchlAndSpe[3].spe}</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty deptBasicInfoForm.masterSchlAndSpe[4].school}">
                        <tr>
                            <td></td>
                            <td>学校：${deptBasicInfoForm.masterSchlAndSpe[4].school}</td>
                            <td colspan="2">专业：${deptBasicInfoForm.masterSchlAndSpe[4].spe}</td>
                        </tr>
                    </c:if>
                </c:if>
                <c:if test="${deptBasicInfoForm.drxw eq 'Y'}">
                    <c:if test="${not empty deptBasicInfoForm.docSchlAndSpe[0].school}">
                        <tr>
                            <td style="text-align: right">
                                博士
                            </td>
                            <td>学校：${deptBasicInfoForm.docSchlAndSpe[0].school}</td>
                            <td colspan="2">专业：${deptBasicInfoForm.docSchlAndSpe[0].school}</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty deptBasicInfoForm.docSchlAndSpe[1].school}">
                        <tr>
                            <td></td>
                            <td>学校：${deptBasicInfoForm.docSchlAndSpe[1].school}</td>
                            <td colspan="2">专业：${deptBasicInfoForm.docSchlAndSpe[1].school}</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty deptBasicInfoForm.docSchlAndSpe[2].school}">
                        <tr>
                            <td></td>
                            <td>学校：${deptBasicInfoForm.docSchlAndSpe[2].school}</td>
                            <td colspan="2">专业：${deptBasicInfoForm.docSchlAndSpe[2].school}</td>
                        </tr>
                    </c:if>
                </c:if>

                <tr>
                    <th>开展住院医师规范化培训工作年限：</th>
                    <td>${deptBasicInfoForm.kzzyys}年</td>
                    <th>
                        累计结业人本科生数：
                    </th>
                    <td>${deptBasicInfoForm.ljjyrs}人</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</form>
