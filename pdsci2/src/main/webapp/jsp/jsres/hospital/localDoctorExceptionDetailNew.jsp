<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

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
            $("input[class='docType']:checked").each(function () {
                data += "&datas=" + $(this).val();
            });
            if(data==""){
                jboxTip("请选择学员类型！");
                return false;
            }

            $("#currentPage").val(page);
            jboxStartLoading();
            jboxPostLoad("doctorListZi", "<s:url value='/jsres/manage/searchDocInfoDetail'/>", $("#myOrgForm").serialize(), true);
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
                jboxTip("请选择学员类型！");
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
            $("input[class='docType']:checked").each(function () {
                data += "&datas=" + $(this).val();
            });
            if(data==""){
                jboxTip("请选择学员类型！");
                return;
            }
            var doctorFlows = [];
            $("input[name='checkDoc']:checked").each(function () {
                doctorFlows += "&doctorFlows=" + $(this).val();
            });
            var url = "<s:url value='/jsres/manage/exportDocInfoDetail'/>?doctorFlow"+doctorFlows;
            jboxTip("导出中…………");
            jboxExp($("#myOrgForm"), url);
            jboxEndLoading();
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
        <form id="myOrgForm"  method="post">
            <input type="hidden" id="currentPage" name="currentPage" value="" >
            <input type="hidden" id="isContain" name="isContain" value="${isContain}">
            <input type="hidden" id="monthDate" name="monthDate" value="${monthDate}">
            <input type="hidden" id="orgFlow" name="orgFlow" value="${orgFlow}">
            <input type="hidden" id="speId" name="speId" value="${speId}">
            <input type="hidden" id="tabTag" name="tabTag" value="${tabTag}">
            <input type="hidden" id="arrayIds" name="arrayIds" value="">

            <input type="button" style="float:left;" value="返回" class="btn_green" onclick="returnBack();"/>
            <table style="width: 100%;">
                <tr>
                    <td style="text-align: left;">培训基地：</td>
                    <td style="text-align: left;">
                        <select name="orgFlow2" class="select" style="width: 180px;">
                            <c:if test="${isContain eq 'Y'}">
                                <option value="">全部</option>
                                <c:forEach items="${orgs}" var="org">
                                    <option value="${org.orgFlow}"> ${org.orgName}</option>
                                </c:forEach>
                            </c:if>
                            <c:if test="${isContain ne 'Y'}">
                                <c:forEach items="${orgs}" var="org">
                                    <c:if test="${org.orgFlow eq orgFlow}">
                                        <option value="${org.orgFlow}"> ${org.orgName}</option>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </select>
                    </td>
                    <td style="text-align: left;">年级：</td>
                    <td style="text-align: left">
                        <input type="text" name="sessionNumber" class="input"  value="${param.sessionNumber}" onClick="WdatePicker({dateFmt:'yyyy'/*isShowClear:false,*//*onpicked:function(dp){$dp.$('ym').blur();}*/})" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: left;">学员类型：</td>
                    <td style="text-align: left">
                        <c:choose>
                            <c:when test="${fn:length(dataType)==2}">
                                <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                    <label>
                                        <input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas"  checked />
                                            ${type.name}</label>
                                </c:forEach>
                            </c:when><c:otherwise>
                            <c:choose>
                                 <c:when test="${dataType[0]=='Graduate'}">
                                     <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                         <c:if test="${type.id eq 'Graduate'}">
                                             <label>
                                                 <input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" <c:if test="${type.id=='Graduate'}">checked</c:if> />
                                                     ${type.name}</label>
                                         </c:if>
                                     </c:forEach>
                                 </c:when>
                                <c:otherwise>
                                    <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                        <c:if test="${type.id ne 'Graduate'}">
                                            <label>
                                                <input type="checkbox" id="${type.id}"value="${type.id}"class="docType"
                                                       name="datas" <c:if test="${type.id=='Company'or type.id=='CompanyEntrust' or type.id=='Social'}">checked</c:if>  />
                                                ${type.name}
                                            </label>
                                        </c:if>
                                    </c:forEach>
                                 </c:otherwise>
                            </c:choose>
                           </c:otherwise>
                        </c:choose>
                    </td>
                    <td style="text-align: left;" colspan="4">
                        <input type="button" value="查&#12288;询" class="btn_green" onclick="toPage(1,'search');"/>
                        <input type="button" value="导&#12288;出" class="btn_green" onclick="exportOut();"/>
                    </td>
                </tr>
            </table>
        </form>
        <div id="doctorListZi" style="margin-top: 20px">

        </div>
    </div>
</div>
</body>
</html>
