<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>

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

    function addSign(signFlow,orgFlow,orgName) {
        var url = "<s:url value='/jsres/hospital/addSign'/>?orgFlow="+orgFlow+"&orgName="+orgName+"&signFlow="+signFlow ;
        jboxOpen(url, "上传签名图片", 600, 350);
    }

    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchIn();
    }
    function searchIn() {
        var url = "<s:url value='/jsres/hospital/signSetUpList'/>";
        jboxPostLoad("doctorContent", url, $("#inForm").serialize(), true);
    }
    
    function changeUseStatus(signFlow,useStatusId) {
        var msg = "";
        if("Y" == useStatusId){
            msg = "启用";
        }else{
            msg = "停用";
        }
        jboxConfirm("确认" + msg + "该院长签名吗？",function () {
            var url = "<s:url value='/jsres/hospital/changeUseStatus?signFlow='/>" + signFlow+ "&useStatusId=" + useStatusId;
            jboxStartLoading();
            jboxPost(url, null, function (resp) {
                if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                    setTimeout(function () {
                        searchIn();
                        jboxEndLoading();
                    },800);
                }else{
                    jboxTip(resp);
                    jboxEndLoading();
                }
            }, null, true);
        });
    }

</script>

<div id="doctorContent">
<%--<div class="main_hd">--%>
    <%--<h2 class="underline">签名图片维护</h2>--%>
<%--</div>--%>
<div class="main_bd" >
    <div class="div_search">
        <form id="inForm">
            <input type="hidden" name="orgFlow" value="${orgFlow}"/>
            <input type="hidden" name="orgName" value="${orgName}"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">审核状态：</td>
                    <td>
                        <select name="auditStatusId" id="auditStatusId" class="select" style="width: 134px;">
                            <option value="">请选择</option>
                            <option value="NotUse" <c:if test="${param.auditStatusId=='NotUse'}">selected="selected"</c:if>>未启用</option>
                            <option value="Auditing" <c:if test="${param.auditStatusId=='Auditing'}">selected="selected"</c:if>>待审核</option>
                            <option value="Passed" <c:if test="${param.auditStatusId=='Passed'}">selected="selected"</c:if>>审核通过</option>
                            <option value="UnPassed" <c:if test="${param.auditStatusId=='UnPassed'}">selected="selected"</c:if>>审核不通过</option>
                        </select>
                    </td>
                    <td colspan="2">
                        <input type="button" class="btn_green" onclick="addSign('','${orgFlow}','${orgName}')" value="新&#12288;增">&nbsp;
                        <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <thead>
                <tr>
                    <th style="width: 30px">序号</th>
                    <th style="width: 120px">基地名称</th>
                    <th style="width: 65px">院长姓名</th>
                    <th style="width: 65px">审核状态</th>
                    <th style="width: 65px">使用状态</th>
                    <th style="width: 80px">上传时间</th>
                    <th style="width: 80px">启用时间</th>
                    <th style="width: 80px">停用时间</th>
                    <th style="width: 60px">年份</th>
                    <th style="width: 80px">签名图片</th>
                    <th style="width: 80px">操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${signList}" var="sign" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${sign.orgName}</td>
                        <td>${sign.presidentName}</td>
                        <td>${sign.auditStatusName}</td>
                        <td>${sign.useStatusName}</td>
                        <td>${pdfn:transDate(sign.createTime)}</td>
                        <td>${pdfn:transDate(sign.useTime)}</td>
                        <td>${pdfn:transDate(sign.stopTime)}</td>
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
                            <c:if test="${sign.useStatusId eq 'Y'}">
                                <c:if test="${sign.auditStatusId eq 'Passed'}">
                                    <a class="btn" href="javascript:void(0);" onclick="changeUseStatus('${sign.signFlow}','N');">停用</a>
                                </c:if>
                                <c:if test="${sign.auditStatusId eq 'Auditing'}">
                                    审核中
                                </c:if>
                            </c:if>
                            <c:if test="${sign.useStatusId eq 'N' }">
                                <a class="btn" href="javascript:void(0);" onclick="changeUseStatus('${sign.signFlow}','Y');">启用</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty signList }">
                    <tr>
                        <td colspan="9">无记录</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(signList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
</div>