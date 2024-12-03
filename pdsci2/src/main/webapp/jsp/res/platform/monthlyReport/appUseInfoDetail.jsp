<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<%--    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
    <script type="text/javascript" src="<s:url value='/js/echarts/echarts.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>

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
            var date =  new Date();
            var y =date.getFullYear();
            $("[name='sessionNumber']").val(y);

          /*  var m =date.getMonth()+1;
            if(m<10){
                m='0'+m;
            }*/
          //  if(!$("[name='monthDate']").val()){
                $("[name='monthDate']").val($("#ym").val());
                $("#yearMonth").text($("#ym").val().split("-")[0]+'年'+$("#ym").val().split("-")[1]+'月');
                /*$("[name='monthDate']").val('2019-02');*/
           // }

            if(${error==null}){
                toPage("1");
            }else{
                jboxTip("${error}");
            }
        });
        var dateTime = $("[name='sessionNumber']").val();
        function  toPage(page) {
            var b =$("[name='sessionNumber']").val();
            console.log("查询时间:"+dateTime+",查询年份:"+b);
            if(b>dateTime){
                jboxTip("年级不能大于当前查询时间,请重新选择！");
                return;
            }

            var data="";
            <c:forEach items="${resDocTypeEnumList}" var="type">
            if($("#"+"${type.id}").attr("checked")){
                data+="&datas="+$("#"+"${type.id}").val();
            }
            </c:forEach>
            if(data==""){
                jboxTip("请选择人员类型！");
                return false;
            }
            $("#currentPage").val(page);
            jboxStartLoading();
            jboxPostLoad("doctorListZi","<s:url value='/res/monthlyReportGlobal/appNotUseList'/>",$("#myOrgForm").serialize(),false);
        }
        function exportOut() {
            var data="";
            <c:forEach items="${resDocTypeEnumList}" var="type">
            if($("#"+"${type.id}").attr("checked")){
                data+="&datas="+$("#"+"${type.id}").val();
            }
            </c:forEach>
            if(data==""){
                jboxTip("请选择人员类型！");
                return;
            }
            var url = "<s:url value='/res/monthlyReportGlobal/exportExcel'/>";
            jboxTip("导出中…………");
            jboxExp($("#myOrgForm"), url);
            jboxEndLoading();
        }
        function datechange(obj) {
            var y= obj.value.split("-")[0];
            var m=  obj.value.split("-")[1];
            $("#yearMonth").text(y+"年"+m+"月");
            dateTime = y+"-"+m;
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
                    <table style="width: 100%;">
                        <tr>
                            <td style="text-align: left;width: 7%">培训基地：</td>
                            <td style="text-align: left;width: 26%">
                                <select name="orgFlow" class="select" style="width: 63%;">
                                    <option value="">全部</option>
                                    <c:forEach items="${orgs}" var="org">
                                        <option value="${org.orgFlow}"> ${org.orgName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td style="text-align: left;width: 21%">时间：
                                <input type="text" name="monthDate" class="input" id="ym1" onchange="datechange(this)"  value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym1').blur();}})" readonly="readonly"/>
                            </td>
                           <%-- <td style="text-align: left">
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
                                <c:forEach items="${resDocTypeEnumList}" var="type">
                                    <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas"  checked/>${type.name}</label>
                                </c:forEach>
                            </td>
                            <td style="text-align: left;width: 20%">培训专业：
                                <select name="trainingSpeId" class="select" style="width: 163px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                        <option  value="${dict.dictId}">${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                          <%--  <td style="text-align: left">
                                <select name="trainingSpeId" class="select" style="width: 63%;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                        <option  value="${dict.dictId}">${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>--%>
                            <td style="width: 29%;    text-align: left;" colspan="4">
                                <input type="button" value="查&#12288;询" class="btn_green" onclick="toPage(1);"/>
                                <input type="button" value="导&#12288;出" class="btn_green" onclick="exportOut();"/>
                            </td>
                        </tr>
                    </table>
                  <%--  <div class="queryDiv">
                        <div class="inputDiv">
                            <label class="qlable">培训基地：</label>
                            <select name="orgFlow" class="qselect" style="width: 63%;">
                                <option value="">全部</option>
                                <c:forEach items="${orgs}" var="org">
                                    <option value="${org.orgFlow}"> ${org.orgName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">时间：</label>
                            <input type="text" name="monthDate" id="ym1" onchange="datechange(this)"  value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym1').blur();}})" readonly="readonly"/>
                        </div>
                        <div class="inputDiv"style="/*min-width: 160px;max-width: 160px;*/margin-left: -3%">
                            <label class="qlable">年级：</label>
                            <input type="text" name="sessionNumber"   value="${param.sessionNumber}" onClick="WdatePicker({dateFmt:'yyyy'/*isShowClear:false,*//*onpicked:function(dp){$dp.$('ym').blur();}*/})" readonly="readonly"/>
                        </div>
                        <div class="inputDiv" style="/*min-width: 160px;max-width: 160px;*/">
                            <label class="qlable">学员类型：</label>
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas"  checked/>${type.name}</label>
                            </c:forEach>
                        </div>
                        <div class="inputDiv" style="/*min-width: 160px;max-width: 160px;*/">
                            <label class="qlable">培训专业：</label>
                            <select name="trainingSpeId" class="qselect" style="width: 63%;">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option  value="${dict.dictId}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="lastDiv"></div>
                        <div class="lastDiv">
                            <input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
                        </div>
                        <div class="lastDiv">
                            <input type="button" value="导&#12288;出" class="searchInput" onclick="exportOut();"/>
                        </div>
                    </div>--%>
                </form>
                <div id="doctorListZi">

                </div>
       <%-- </div>--%>
    </div>
</div>
</body>
</html>
