<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
   <%-- <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
    </jsp:include>--%>

 <%--   <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>app</title>--%>
   <%-- <link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.bootcss.com/jquery-treegrid/0.2.0/css/jquery.treegrid.min.css">--%>
    <%--<link rel="stylesheet" href="<s:url value='/css/bootstrap-table.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
    <link rel="stylesheet" href="<s:url value='/css/jquery.treegrid.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
    &lt;%&ndash;<link rel="stylesheet" href="<s:url value='/jsp/jsres/hospital/monthlyReportNew/css/common.css'/>">&ndash;%&gt;
    <link rel="stylesheet" href="<s:url value='/css/cssPro.css'/>"> 

    <script type="text/javascript" src="<s:url value='/js/bootstrap-table.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/bootstrap-table-treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/jquery.treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/common.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/jsp/srm/statistics/js/echarts.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>

    <style type="text/css">
       /* #table thead{
            background: rgba(225, 228, 229, 1);
        }
        #body-tab li a.current{
            color: #E8D5FF;
            background: #2f8cef;
        }*/
    </style>
    <script>
        $(function () {
            //初始化清空sessionstage勾选框信息
            var sessionStorageObj = window.sessionStorage;
            for(var key in sessionStorageObj){
                if(key.startsWith("gouxuan")){
                    sessionStorage.removeItem(key)
                }
            }
          /*  var date =  new Date();
            var y =date.getFullYear();
            $("[name='sessionNumber']").val(y);

            var m =date.getMonth()+1;
            if(m<10){
                m='0'+m;
            }
                $("[name='monthDate']").val(y+'-'+m);
                $("#yearMonth").text(y+'年'+m+'月');*/
            if(${error==null}){
                toPage("1");
            }else{
                jboxTip("${error}");
            }
        });
        function  toPage(page,search) {
            debugger
            $("#arrayIds").val("");
            if(search=='search'){
                var sessionStorageObj = window.sessionStorage;
                for(var key in sessionStorageObj){
                    if(key.startsWith("gouxuan")){
                        sessionStorage.removeItem(key)
                    }
                }
            }
            var data="";
            <c:forEach items="${jsResDocTypeEnumList}" var="type">
            if($("#"+"${type.id}").attr("checked")){
                data+="&datas="+$("#"+"${type.id}").val();
            }
            </c:forEach>
            if(data==""){
                jboxTip("请选择人员类型！");
                return false;
            }
            var doctorIds="";
            <c:forEach items="${doctorIds}" var="mm">
                doctorIds+="&doctorIds=${mm}";
            </c:forEach>
            $("#currentPage").val(page);
            jboxStartLoading();
            jboxPostLoad("doctorListZi","<s:url value='/jsres/manage/localDoctorExceptionUseList'/>",$("#myOrgForm").serialize()/*+"&name=el_name"+doctorIds+"&monthDate=el_monthDate"*/,false);
        }
        function exportOutCheck() {
            $("#arrayIds").val("");
            var data="";
            <c:forEach items="${jsResDocTypeEnumList}" var="type">
            if($("#"+"${type.id}").attr("checked")){
                data+="&datas="+$("#"+"${type.id}").val();
            }
            </c:forEach>
            if(data==""){
                jboxTip("请选择人员类型！");
                return;
            }
            var arrayIds="";
            var sessionStorageObj = window.sessionStorage;
            if(sessionStorageObj==null){
                jboxTip("请至少勾选一条记录！");
                return;
            }else{
                for(var key in sessionStorageObj){
                    if(key.startsWith("gouxuan")){
                       var val =sessionStorage.getItem(key);
                        arrayIds+="&arrayId="+val;
                    }
                }
                if(arrayIds==""){
                    jboxTip("请至少勾选一条记录！");
                    return;
                }
            }

            $("#arrayIds").val(arrayIds);
            var url = "<s:url value='/jsres/manage/exportExcelLocalDoctorException'/>";
            jboxTip("导出中…………");
            jboxExp($("#myOrgForm"), url);
            jboxEndLoading();
        }
        function exportOut() {
            $("#arrayIds").val("");
            var data="";
            <c:forEach items="${jsResDocTypeEnumList}" var="type">
            if($("#"+"${type.id}").attr("checked")){
                data+="&datas="+$("#"+"${type.id}").val();
            }
            </c:forEach>
            if(data==""){
                jboxTip("请选择人员类型！");
                return;
            }
            var url = "<s:url value='/jsres/manage/exportExcelLocalDoctorException'/>";
            jboxTip("导出中…………");
            jboxExp($("#myOrgForm"), url);
            jboxEndLoading();
        }
        function datechange(obj) {
            var y= obj.value.split("-")[0];
            var m=  obj.value.split("-")[1];
            $("#yearMonth").text(y+"年"+m+"月");
        }

        function single(index,value) {
            if($("input[name=gouxuan"+index+"]").is(':checked')){
                sessionStorage.setItem('gouxuan'+value,value);
            }else{
                sessionStorage.removeItem('gouxuan'+value);
            }
        }
        /*返回第一层页面*/
        function returnBack() {
            $("#content2").hide();
            $("#content1").show();
        }
    </script>
</head>
<body>
<div class="main_bd" id="div_table_0">
    <div class="div_search" style="width: 100%;padding:10px 0px">
       <%-- <div class="title1 clearfix">--%>
                <form id="myOrgForm" action="<%--<s:url value='/res/monthlyReportGlobal/appUseInfo'/>--%>" method="post">
                    <input type="hidden" id="currentPage" name="currentPage" value="" >
                    <input type="hidden" id="userListScope" name="userListScope" value="${userListScope}">
                    <input type="hidden" id="arrayIds" name="arrayIds" value="">
                    <input type="hidden" id="name" name="name" value="${name}">
                    <input type="hidden" id="monthDate" name="monthDate" value="${monthDate}">
                    <c:forEach items="${doctorIds}" var="mm">
                        <input type="hidden"  name="doctorIds" value="${mm}">
                    </c:forEach>
                    <input type="button" style="float:left;" value="返回${name}" class="btn_green" onclick="returnBack();"/>
                    <%--<a href="javascript:returnBack();" style="float:left;">返回${name}</a>--%>
                    <table style="width: 100%;">
                        <tr>
                            <td style="text-align: left;width: 7%">培训基地：</td>
                            <td style="text-align: left;width: 26%">
                                <select name="orgFlow" class="select" style="width: 63%;">
                                   <%-- <option value="">全部</option>--%>
                                    <c:forEach items="${orgs}" var="org">
                                        <option value="${org.orgFlow}"> ${org.orgName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <%--<td style="text-align: left;width: 21%">时间：
                                <input type="text" name="monthDate" id="ym1" onchange="datechange(this)"  value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym1').blur();}})" readonly="readonly"/>
                            </td>--%>
                            <td style="text-align: left;width: 4%">年级：</td>
                            <td style="text-align: left">
                                <input type="text" name="sessionNumber" class="input"  value="${param.sessionNumber}" onClick="WdatePicker({dateFmt:'yyyy'/*isShowClear:false,*//*onpicked:function(dp){$dp.$('ym').blur();}*/})" readonly="readonly"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: left;width: 7%">学员类型：</td>
                            <td style="text-align: left">
                                <c:choose>
                                    <c:when test="${fn:length(dataType)==2}">
                                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                            <label>
                                                <input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas"  checked onclick="return false;" />
                                                    ${type.name}</label>
                                        </c:forEach>
                                    </c:when><c:otherwise>
                                    <c:choose>
                                         <c:when test="${dataType[0]=='Graduate'}">
                                             <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                                 <label>
                                                     <input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" <c:if test="${type.id=='Graduate'}">checked</c:if>  onclick="return false;"/>
                                                         ${type.name}</label>
                                             </c:forEach>
                                         </c:when><c:otherwise>
                                            <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                                <label>
                                                    <input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" <c:if test="${type.id=='Company'or type.id=='CompanyEntrust' or type.id=='Social'}">checked</c:if> onclick="return false;" />
                                                        ${type.name}</label>
                                            </c:forEach>
                                         </c:otherwise>
                                    </c:choose>
                                   </c:otherwise>
                                </c:choose>
                            </td>
                            <%--<td style="text-align: left;width: 20%">培训专业：
                                <select name="trainingSpeId" class="select" style="width: 163px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                        <option  value="${dict.dictId}">${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>--%>
                            <td style="width: 29%;    text-align: left;" colspan="4">
                                <input type="button" value="查&#12288;询" class="btn_green" onclick="toPage(1,'search');"/>
                                <input type="button" value="勾选导出" class="btn_green" onclick="exportOutCheck();"/>
                                <input type="button" value="全部导出" class="btn_green" onclick="exportOut();"/>
                            </td>
                        </tr>
                    </table>
                </form>
                <div id="doctorListZi">

                </div>
       <%-- </div>--%>
    </div>
</div>
</body>
</html>
