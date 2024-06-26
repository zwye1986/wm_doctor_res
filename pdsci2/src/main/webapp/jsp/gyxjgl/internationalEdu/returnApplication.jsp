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
        <jsp:param name="jquery_cxselect" value="true"/>
    </jsp:include>
    <style type="text/css">
        input[type='text']{width:100px;border-width:0px;}
        input[type='text']{border-bottom:1px solid #e3e3e3;}
    </style>
    <script type="text/javascript" src="<s:url value='/js/jquery.jqprint-0.3.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        //获取字符串长度（汉字算两个字符，字母数字算一个）
        function getByteLen(val) {
            var len = 0;
            if(val!=null&&val!=undefined&&val!='undefined'){
                for (var i = 0; i < val.length; i++) {
                    var a = val.charAt(i);
                    if (a.match(/[^\x00-\xff]/ig) != null) {
                        len += 2;
                    }
                    else {
                        len += 1;
                    }
                }
            }
            return len;
        }
        function search(){
            $("#searchForm").submit();
        }
        function save(){
            if (!$("#saveForm").validationEngine("validate")) {
                return;
            }
//            if(getByteLen($("input[name='researchDirection']").val())>1000){
//                jboxTip("研究方向不能超过500字！");
//                return;
//            }
            if(getByteLen($("textarea[name='cultureSummary']").val())>1000){
                jboxTip("在外联合培养小结不能超过500字！");
                return;
            }
            if(getByteLen($("textarea[name='backTutorAdvice']").val())>200){
                jboxTip("导师意见不能超过100字！");
                return;
            }
            jboxPost("<s:url value='/gyxjgl/abroadApply/saveAbroadApply'/>", $("#saveForm").serialize(), function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames["mainIframe"].window.toPage(1);
                    jboxClose();
                }
            });
        }
        function printOpt(){
            jboxTip("正在准备打印…");
            $("#printDivIframe").removeAttr("hidden");
            setTimeout(function(){
                $("#printDivIframe").jqprint({
                    debug: false,
                    importCSS: true,
                    printContainer: true,
                    operaSupport: false
                });
                $("#printDivIframe").attr("hidden","hidden");
                jboxEndLoading();
                return false;
            },2000);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gyxjgl/abroadApply/saveAbroadApply"/>" method="post">
            <input type="hidden" name="roleFlag" value="${roleFlag}">
            <div style="margin:20px 0px 10px 20px;">
                <c:if test="${roleFlag eq 'student'||roleFlag eq 'tutor'}">
                    <input type="button" class="search" onclick="save();" value="保&#12288;存" />
                </c:if>
                <input type="button" class="search" onclick="printOpt();" value="打&#12288;印" />
            </div>
        </form>
        <form id="saveForm">
            <input type="hidden" name="roleFlag" value="${roleFlag}">
            <input type="hidden" name="formType" value="${formType}">
            <input type="hidden" name="recordFlow" value="${abroadApply.recordFlow}"/>
            <div style="text-align:center;font-size:30px;font-weight:500;">
                南方医科大学联合培养研究生回国（境）登记表
            </div>
            <div style="min-width:850px;">
                <table class="basic" style="width:100%;">
                    <tr>
                        <th style="min-width:60px;">姓名</th>
                        <td style="min-width:120px;">${abroadApply.userName}</td>
                        <th style="min-width:60px;">学号</th>
                        <td style="min-width:115px;">${abroadApply.stuNo}</td>
                        <th style="min-width:60px;">性别</th>
                        <td style="min-width:80px;">${abroadApply.sexName}</td>
                        <th style="min-width:80px;">出生年月</th>
                        <td style="min-width:115px;">${abroadApply.birthDate}</td>
                    </tr>
                    <tr>
                        <th>二级机构</th>
                        <td>${abroadApply.pydwOrgName}</td>
                        <th>导师</th>
                        <td>${abroadApply.tutorName}</td>
                        <th>婚否</th>
                        <td>${abroadApply.maritalName}</td>
                        <th>CSC资助<br/>证书号</th>
                        <td><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="cscCertificateNo" value="${abroadApply.cscCertificateNo}" ${roleFlag eq 'student'?'':'readonly'}/></td>
                    </tr>
                    <tr>
                        <th>出国类别</th>
                        <td colspan="7">
                            <c:forEach items="${abroadCategoryEnumList}" var="category">
                                <input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="goAbroadId" value="${category.id}" <c:if test="${abroadApply.goAbroadId eq category.id}"> checked="checked"</c:if> <c:if test="${roleFlag ne 'student'}">disabled="disabled"</c:if>>${category.name}&#12288;
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th>学生层次</th>
                        <td colspan="3">
                            <input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="stuLevelId" value="master" <c:if test="${abroadApply.stuLevelId eq 'master' || pdfn:contains(eduUser.trainTypeName,'硕士')}"> checked="checked"</c:if>  disabled="disabled">硕士研究生
                            &#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="stuLevelId" value="doctor" <c:if test="${abroadApply.stuLevelId eq 'doctor' || pdfn:contains(eduUser.trainTypeName,'博士')}"> checked="checked"</c:if> disabled="disabled">博士研究生
                        </td>
                        <th colspan="2">入学时间</th>
                        <td colspan="2">${abroadApply.inSchoolDate}</td>
                    </tr>
                    <tr>
                        <th>联系电话</th>
                        <td colspan="3">${abroadApply.telphone}</td>
                        <th colspan="2">E-MAIL</th>
                        <td colspan="2">${abroadApply.email}</td>
                    </tr>
                    <tr>
                        <th colspan="2">出国起止时间</th>
                        <td colspan="6">
                            <span><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'} inputText ctime" readonly="readonly"   <c:if test="${roleFlag eq 'student'}">onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"</c:if> style="width: 100px;text-align:left;" name="beginDate" value="${abroadApply.beginDate}" onchange="checkTime(this)"/>至
                            <input type="text" class="${roleFlag eq 'global'?'':'validate[required]'} inputText ctime" readonly="readonly"   <c:if test="${roleFlag eq 'student'}">onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"</c:if> style="width: 100px;text-align:left;" name="endDate" value="${abroadApply.endDate}" onchange="checkTime(this)"/></span>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">留学国家（地区）及城市名称</th>
                        <td colspan="6"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="destinationName" value="${abroadApply.destinationName}" style="width:300px;" ${roleFlag eq 'student'?'':'readonly'}/></td>
                    </tr>
                    <tr>
                        <th>在外联合<br/>培养小结</th>
                        <td colspan="7">
                            <textarea class="validate[required]" name="cultureSummary" style="width:800px;height:100px;" placeholder="说明出国（境）留学国家（地区）与大学名称、国（境）外导师姓名及联系方式，在外联合培养期间的主要任务与已经取得的成绩、留学体会等。【可附页】" ${roleFlag eq 'student'?'':'readonly'}>${abroadApply.cultureSummary}</textarea>
                            <%--<br/><span style="padding-left: 500px;"/>签名：&#12288;&#12288;&#12288;&#12288;日期：&#12288;&#12288;&#12288;&#12288;--%>
                        </td>
                    </tr>
                    <tr>
                        <th>导师意见</th>
                        <td colspan="7">
                            <textarea class="${roleFlag eq 'tutor'?'validate[required]':''}" name="backTutorAdvice" style="width: 800px;height: 80px;margin-top: 3px;" <c:if test="${roleFlag ne 'tutor'}">readonly="readonly"</c:if>>${abroadApply.backTutorAdvice}</textarea>
                            <br/><span style="padding-left: 550px;">签名：${abroadApply.tutorName}&emsp;日期：${abroadApply.tutorBackAuditDate}</span>
                        </td>
                    </tr>
                    <tr>
                        <th>学院意见</th>
                        <td colspan="7">
                            <br/><br/><br/>
                            <span style="padding-left: 550px;"/>签名（公章）：&#12288;&#12288;&#12288;&#12288;日期：&#12288;&#12288;&#12288;&#12288;
                        </td>
                    </tr>
                    <tr>
                        <th>研究生院<br/>意见</th>
                        <td colspan="7">
                            <br/><br/><br/>
                            <span style="padding-left: 550px;"/>签名（公章）：&#12288;&#12288;&#12288;&#12288;日期：&#12288;&#12288;&#12288;&#12288;
                        </td>
                    </tr>
                </table>
                <font style="font-weight:bold;">注：此表正反面打印，一式四份，由研究生本人、导师、二级机构及研究生院各留存一份。</font>
            </div>
        </form>
    </div>
</div>
<div hidden="hidden" id="printDivIframe" name="printDivIframe">
    <div style="text-align:center;font-size:20px;font-weight:500;margin-bottom: 10px;">
        南方医科大学联合培养研究生回国（境）登记表
    </div>
    <div>
        <table class="basic" style="width:100%;font-size: 11px;">
            <tr>
                <th style="width:60px;">姓名</th>
                <td style="width:100px;">${abroadApply.userName}</td>
                <th style="width:60px;">学号</th>
                <td style="width:80px;">${abroadApply.stuNo}</td>
                <th style="width:50px;">性别</th>
                <td style="width:50px;">${abroadApply.sexName}</td>
                <th style="width:60px;">出生年月</th>
                <td style="width:80px;">${abroadApply.birthDate}</td>
            </tr>
            <tr>
                <th>二级机构</th>
                <td>${abroadApply.pydwOrgName}</td>
                <th>导师</th>
                <td>${abroadApply.tutorName}</td>
                <th>婚否</th>
                <td>${abroadApply.maritalName}</td>
                <th>CSC资助<br/>证书号</th>
                <td>${abroadApply.cscCertificateNo}</td>
            </tr>
            <tr>
                <th>出国类别</th>
                <td colspan="7">
                    <c:forEach items="${abroadCategoryEnumList}" var="category">
                        <input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="goAbroadId" value="${category.id}" <c:if test="${abroadApply.goAbroadId eq category.id}"> checked="checked"</c:if> <c:if test="${roleFlag ne 'student'}">disabled="disabled"</c:if>>${category.name}&#12288;
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th>学生层次</th>
                <td colspan="3">
                    <input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="stuLevelId" value="master" <c:if test="${abroadApply.stuLevelId eq 'master'}"> checked="checked"</c:if>  <c:if test="${roleFlag ne 'student'}">disabled="disabled"</c:if>>硕士研究生
                    &#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="stuLevelId" value="doctor" <c:if test="${abroadApply.stuLevelId eq 'doctor'}"> checked="checked"</c:if>  <c:if test="${roleFlag ne 'student'}">disabled="disabled"</c:if>>博士研究生
                </td>
                <th colspan="2">入学时间</th>
                <td colspan="2">${abroadApply.inSchoolDate}</td>
            </tr>
            <tr>
                <th>联系电话</th>
                <td colspan="3">${abroadApply.telphone}</td>
                <th colspan="2">E-MAIL</th>
                <td colspan="2">${abroadApply.email}</td>
            </tr>
            <tr>
                <th colspan="2">出国起止时间</th>
                <td colspan="6">&emsp;${abroadApply.beginDate}&emsp;至&emsp;${abroadApply.endDate}</td>
            </tr>
            <tr>
                <th colspan="2">留学国家（地区）及城市名称</th>
                <td colspan="6">${abroadApply.destinationName}</td>
            </tr>
            <tr>
                <th>在外联合<br/>培养小结</th>
                <td colspan="7">
                    ${abroadApply.cultureSummary}<br/>
                    <%--<br/><span style="padding-left: 500px;"/>签名：&#12288;&#12288;&#12288;&#12288;日期：&#12288;&#12288;&#12288;&#12288;--%>
                </td>
            </tr>
            <tr>
                <th>导师意见</th>
                <td colspan="7">
                    ${abroadApply.backTutorAdvice}
                    <br/><span style="padding-left: 60%;">签名：${abroadApply.tutorName}&emsp;日期：${abroadApply.tutorBackAuditDate}</span>
                </td>
            </tr>
            <tr>
                <th>学院意见</th>
                <td colspan="7">
                    <br/>
                    <span style="padding-left: 60%;"/>签名（公章）：&#12288;&#12288;&#12288;日期：&#12288;&#12288;&#12288;
                </td>
            </tr>
            <tr>
                <th>研究生院<br/>意见</th>
                <td colspan="7">
                    <br/>
                    <span style="padding-left: 60%;"/>签名（公章）：&#12288;&#12288;&#12288;日期：&#12288;&#12288;&#12288;
                </td>
            </tr>
        </table>
        <font style="font-weight:bold;">注：此表正反面打印，一式四份，由研究生本人、导师、二级机构及研究生院各留存一份。</font>
    </div>
</div>
</body>
</html>