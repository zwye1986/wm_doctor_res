<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
        .basic td,tr{border: 0}
    </style>
    <script type="text/javascript">
        window.onload = function () {
            if('${param.oneLevelSubjectId}' != ''){
                $('#twoLevelSubjectId option').hide();
                $('#twoLevelSubjectId option.${dictTypeEnumXjSubjectDeclaration.id}\\.${param.oneLevelSubjectId}').show();
            }
        }
        function toPage(page){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            $("#currentPage").val(page);
            search();
        }
        function search(){
            var orgFlow=$("select[name='orgFlow']").val();
            if('${adminFlag}'=="Y"){
                if(orgFlow==""){
                    jboxTip("请选择培养单位！");
                    return;
                }
            }
            var form = $("#recSearchForm");
            jboxStartLoading();
            form.submit();
        }
        function doctorInfo(doctorFlow){
            var url = "<s:url value='/xjgl/tutor/doctorInfo?doctorFlow='/>"+doctorFlow;
            jboxOpen(url,"导师信息",900,600,true);
        }
        function linkageSubject(dictId){
            $('#twoLevelSubjectId').val("");//清空上次展现数据
            $('#twoLevelSubjectId option').hide();
            $('#twoLevelSubjectId option[value=""]').show();
            $('#twoLevelSubjectId option.${dictTypeEnumXjSubjectDeclaration.id}\\.'+dictId).show();
        }
    </script>
    <style type="text/css">
        .table tr td, .table tr th{border-bottom: 0px; }
        .table1 td{border: none;}
        .table1{border: none;}
        .basicData{border:1px solid #bbbbbb;}
        .basicData th,.basicData td { border-right:1px solid #bbbbbb; border-bottom:1px solid #bbbbbb; height:35px;}
        .basicData tbody th { background:#f9f9f9; color:#333; height:35px; text-align:center;}
        .basicData td { text-align:center; line-height:35px;}
    </style>
</head>
<body>
<div class="header">
    <div class="top">
        <p class="tleft"><img src="<s:url value='/css/skin/${skinPath}/images/${applicationScope.sysCfgMap["sys_login_img"]}_head.png'/>" /></p>
</div>
</div>
<div class="mainright">
    <div class="content">
        <form id="recSearchForm" method="post" action="<s:url value='/xjgl/tutor/searchDoctorList'/>">
            <table style="width: 100%;margin: 10px 0px 5px -10px;border: none;">
                <tr>
                    <td style="border: none;">
                        <table class="basic table1" style="width: 980px">
                            <input id="currentPage" type="hidden" name="currentPage"/>
                            <tr>
                                <td>按分委会筛选：<select style="width: 141px;" name="fwhOrgFlow">
                                        <option></option>
                                        <c:forEach items="${deptList }" var="dept">
                                            <option value="${dept.deptFlow}" <c:if test="${param.fwhOrgFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                                        </c:forEach>
                                    </select>
                                    &#12288;培养单位：<select style="width: 141px;" name="pydwOrgFlow">
                                        <option></option>
                                        <c:forEach items="${orgList}" var="org">
                                            <option value="${org.orgFlow}" <c:if test="${param.pydwOrgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
                                        </c:forEach>
                                    </select>
                                    &#12288;一级学科：<select style="width: 141px;" name="oneLevelSubjectId" onchange="linkageSubject(this.value)">
                                        <option></option>
                                        <c:forEach items="${dictTypeEnumXjSubjectDeclarationList}" var="dict">
                                            <option value="${dict.dictId}" <c:if test="${dict.dictId eq param.oneLevelSubjectId}">selected</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                    &#12288;二级学科：<select style="width: 141px;" id="twoLevelSubjectId" name="twoLevelSubjectId">
                                        <option></option>
                                        <c:forEach items="${dictTypeEnumXjSubjectDeclarationList}" var="dict">
                                            <c:set var="dictKey" value="dictTypeEnumXjSubjectDeclaration.${dict.dictId}List"/>
                                            <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                                <option class="${scope.dictTypeId}" value="${scope.dictId}" <c:if test="${scope.dictId eq param.twoLevelSubjectId}">selected</c:if>>${scope.dictName}</option>
                                            </c:forEach>
                                        </c:forEach>
                                    </select><br/>
                                    <%--&#12288;按专业筛选：<select style="width: 141px;" name="recruitSpeId">--%>
                                        <%--<option/>--%>
                                        <%--<c:forEach items="${dictTypeEnumMajorList}" var="major">--%>
                                            <%--<option value="${major.dictId}" ${param.recruitSpeId eq major.dictId?'selected':''}>[${major.dictId}]${major.dictName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                    &#12288;按姓名检索：<input type="text" style="width: 137px;" name="doctorName" value="${param.doctorName }">
                                    &#12288;导师类别：<select style="width: 141px;" name="doctorTypeId">
                                        <option/>
                                        <option value="xsxbd" <c:if test="${param.doctorTypeId eq 'xsxbd'}">selected</c:if>>学术型博导</option>
                                        <option value="xsxsd" <c:if test="${param.doctorTypeId eq 'xsxsd'}">selected</c:if>>学术型硕导</option>
                                        <option value="zyxbd" <c:if test="${param.doctorTypeId eq 'zyxbd'}">selected</c:if>>专业型博导</option>
                                        <option value="zyxsd" <c:if test="${param.doctorTypeId eq 'zyxsd'}">selected</c:if>>专业型硕导</option>
                                    </select>
                                    <input type="button" name="" class="search" onclick="search();" value="查&#12288;询"/><br/>
                                    (注：<font style="color: red;">红色导师类别</font>为博士生导师，<font style="color: blue;">蓝色导师类别</font>为硕士生导师)
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
        <form>
            <table class="basicData" style="width: 100%;">
                <tr style="font-weight: bold;">
                    <td style="width: 10%;">导师姓名</td>
                    <td style="width: 25%;">分委会名称</td>
                    <td style="width: 19%;">培养单位</td>
                    <td style="width: 13%;">一级学科</td>
                    <td style="width: 13%;">二级学科</td>
                    <%--<td style="width: 10%;">专业名称</td>--%>
                    <td style="width: 10%;">导师类别</td>
                </tr>
                <c:forEach items="${doctorList}" var="record">
                    <tr style="cursor: pointer;" onclick="doctorInfo('${record.doctorFlow}');">
                        <td>${record.doctorName}</td>
                        <td>${record.fwhOrgName}</td>
                        <td>${record.pydwOrgName}</td>
                        <td>${record.oneLevelSubjectName}</td>
                        <td>${record.twoLevelSubjectName}</td>
                        <%--<td>${record.recruitSpeName}</td>--%>
                        <td>
                            <c:if test="${record.doctorTypeId eq 'xsxbd' or record.doctorTypeId eq 'zyxbd'}">
                                <font style="color: red;">${record.doctorTypeName}</font>
                            </c:if>
                            <c:if test="${record.doctorTypeId eq 'xsxsd' or record.doctorTypeId eq 'zyxsd'}">
                                <font style="color: blue;">${record.doctorTypeName}</font>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty doctorList}">
                    <tr>
                        <td colspan="99" >无记录！</td>
                    </tr>
                </c:if>
            </table>
        </form>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
