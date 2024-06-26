<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
    <script type="text/javascript">
        function initDept() {
            var datas = [];
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            jboxPost(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    var d = {};
                    d.id = n.deptFlow;
                    d.text = n.deptName;
                    datas.push(d);
                });
            }, null, false);
            var itemSelectFuntion = function () {
                $("#deptFlow").val(this.id);
            };
            $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
        }

        $(document).ready(function () {
            initDept();
        });

        function searchProjCategory(){
            var dictFlow = $("select[name='projDeclarerFlow'] option:selected").attr("dictFlow");
            var dictTypeId = $("#projDeclarerFlow").val();
            var data = {
                dictFlow : dictFlow,
                dictTypeId : dictTypeId
            };
            var url = "<s:url value='/sys/dict/getCategoryDictByDeclarer'/>";
            jboxPost(url , data , function(data){
                //清空原类别！
                $("select[name=projSecondSourceId] option[value != '']").remove();
                var dataObj = data;
                for(var i =0;i<dataObj.length;i++){
                    var cId =dataObj[i].dictId;
                    var cName =dataObj[i].dictName;
                    $option =$("<option></option>");
                    $option.attr("value",cId);
                    $option.text(cName);
                    if("${param.projSecondSourceId}" == cId){
                        $option.attr("selected",true);
                    }
                    $("select[name='projSecondSourceId']").append($option);
                }
            } , null , false);
        }
        $(function(){
            searchProjCategory()
        })
        function audit(fundFlow) {
            var w = $('#mainright').width();
            jboxOpen("<s:url value='/srm/fund/scheme/audit?fundFlow='/>" + fundFlow, "审核", w, 600);
        }
        function viewDetail(projFlow) {
            var w = $('#mainright').width();
            var url = '<s:url value="/srm/fund/scheme/viewDetailBudget?projFlow="/>' + projFlow;
            var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='460px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
            jboxMessager(iframe, '预算项查看', w, 500);
        }
        function toPage(page) {
            if(page!=undefined){
                $("#currentPage").val(page);
            }
            search();
        }
        function search() {
            jboxStartLoading();
            $("#budgetList").submit();
        }
        function view(projFlow) {
            var w = $('#mainright').width();
            var url = '<s:url value="/srm/fund/scheme/applyEdit?projFlow="/>' + projFlow + "&look=look";
            var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='330px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
            jboxMessager(iframe, '预算项查看', w, 400);
        }
        function showProcess(fundFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value="/srm/payment/showPaymentAudit?fundFlow="/>" + fundFlow, "操作信息", 800, 600);
        }
    </script>
</head>
<body>
<div class="mainright" id="mainright">
    <div class="content">
        <form id="budgetList" action="<s:url value="/srm/fund/scheme/auditList/local/${sessionScope.projCateScope}"/>"
              method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <div class="title1 clearfix">
                <div class="searchDiv">
                    &#12288;项目名称：
                    <input type="text" name="projName" class="xltext" value="${param.projName}">
                </div>
                <div class="searchDiv">
                    &#12288;课题编号：
                    <input class="xltext" name="projNo" type="text"
                           value="${param.projNo}"/>
                </div>
                <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                <div class="searchDiv">
                    &#12288;课题账号：
                    <input class="xltext" name="accountNo" type="text"
                           value="${param.accountNo}"/>
                </div>
                <div class="searchDiv">
                    &#12288;一级来源：
                    <select id="projDeclarerFlow" name="projDeclarerFlow" class="xlselect"
                            onchange="searchProjCategory();">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                            <option dictFlow="${dict.dictFlow}" value="${dict.dictId}"
                                    <c:if test="${param.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;二级来源：
                    <select name="projSecondSourceId" class="xlselect">
                        <option value="">请选择</option>
                    </select>
                </div>
                </c:if>
                <div class="searchDiv">
                    项目负责人：
                    <input type="text" name="applyUserName" class="xltext" value="${param.applyUserName}">
                </div>
                <div class="searchDiv">
                    &#12288;审核状态：
                    <select name="budgetStatusId" class="xlselect" >
                        <option value="">请选择</option>
                        <option value="${achStatusEnumSubmit.id}"
                                <c:if test="${achStatusEnumSubmit.id==param.budgetStatusId}">selected="selected"</c:if> >${achStatusEnumSubmit.name }</option>
                        <option value="${achStatusEnumFirstAudit.id}"
                                <c:if test="${achStatusEnumFirstAudit.id==param.budgetStatusId}">selected="selected"</c:if> >${achStatusEnumFirstAudit.name }</option>
                        <%-- <option value="${achStatusEnumFirstBack.id}" <c:if test="${achStatusEnumFirstBack.id==param.budgetStatusId}">selected="selected"</c:if> >${achStatusEnumFirstBack.name }</option>
                          <option value="${achStatusEnumSecondAudit.id}" <c:if test="${achStatusEnumSecondAudit.id==param.budgetStatusId}">selected="selected"</c:if> >${achStatusEnumSecondAudit.name }</option>
                          <option value="${achStatusEnumSecondBack.id}" <c:if test="${achStatusEnumSecondBack.id==param.budgetStatusId}">selected="selected"</c:if> >${achStatusEnumSecondBack.name }</option>--%>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="applyDeptName" type="text"
                           value="${param.applyDeptName}" autocomplete="off"/>
                    <input id="deptFlow" name="applyDeptFlow" class="input" value="${param.applyDeptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                <div class="searchDiv">
                    <input type="button" class="search" onclick="search();" value="查&#12288;询">
                </div>
            </div>

            <table class="xllist" id="mubiao">
                <!--   <tr>
                     <th colspan="7" class="bs_name">项目列表</th>
                  </tr> -->
                <thead>
                <tr>
                    <th width="7%">课题编号</th>
                    <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                    <th width="8%">课题账号</th>
                    </c:if>
                    <c:if test="${sysCfgMap['srm_local_type'] eq 'Y'}">
                        <th style="width: 11%">项目类型</th>
                    </c:if>
                    <th style="width: 15%">项目名称</th>
                    <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                    <th style="width: 11%">一级来源</th>
                    <th style="width: 11%">二级来源</th>
                    </c:if>
                    <th style="width:10%">部门</th>
                    <th style="width:10%;">项目负责人</th>
                    <th style="width:8%;">预算总金额<br/>(元)</th>
                    <th style="width:10%;">审核状态</th>
                    <th style="width:10%;">操作</th>
                </tr>
                </thead>
                <c:forEach var="fundInfo" items="${fundInfoList}">
                    <tr>

                        <td>${fundInfo.projNo}</td>
                        <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                        <td>${fundInfo.accountNo }</td>
                        </c:if>
                        <c:if test="${sysCfgMap['srm_local_type'] eq 'Y'}">
                            <td>${fundInfo.projTypeName }</td>
                        </c:if>
                        <td>${fundInfo.projName}</td>
                        <c:if test="${sysCfgMap['srm_local_type'] ne 'Y'}">
                        <td>${fundInfo.projDeclarer }</td>
                        <td>${fundInfo.projSecondSourceName }</td>
                        </c:if>
                        <td>${fundInfo.user.deptName}</td>
                        <td>${fundInfo.applyUserName}</td>
                        <td>${pdfn:transMultiply(fundInfo.projFundInfo.budgetAmount,10000)} </td>
                        <td>
                                ${fundInfo.projFundInfo.budgetStatusName}<br/>[<a href="javascript:void(0);"
                                                                                  onclick="showProcess('${fundInfo.projFundInfo.fundFlow}')">查看过程</a>]
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${fundInfo.projFundInfo.budgetStatusId == achStatusEnumSubmit.id }">
                                    <a href="javascript:void(0);" onclick="audit('${fundInfo.projFundInfo.fundFlow}')">审核</a>
                                </c:when>
                                <c:when test="${fundInfo.projFundInfo.budgetStatusId == achStatusEnumFirstAudit.id }">
                                    <a href="javascript:void(0);" onclick="viewDetail('${fundInfo.projFlow}');">查看详细</a>
                                </c:when>
                                <c:when test="${fundInfo.projFundInfo.budgetStatusId == achStatusEnumApply.id }">
                                    <a href="javascript:void(0);" onclick="audit('${fundInfo.projFundInfo.fundFlow}');">添加预算</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:void(0);" onclick="view('${fundInfo.projFlow}');">查看详细</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </form>
        <c:set var="pageView" value="${pdfn:getPageView(fundInfoList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>