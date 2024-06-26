<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        function selTag(typeId,obj){
            $(".selectTag").removeClass("selectTag");
            $(obj).parent().addClass('selectTag');
            $("#assessTypeId").val(typeId);
            toPage(1);
        }
        function search(){
            $("#searchForm").submit();
        }
        function detail(cfgCodeId) {
            jboxOpen("<s:url value='/res/evaluation/evaluationCfg'/>?cfgCodeId=" + cfgCodeId, '双向评价表单信息', 1000, 500,false);
        }
        function showDetail(cfgCodeId) {
            jboxOpen("<s:url value='/res/evaluation/evaluationCfg'/>?cfgCodeId=" + cfgCodeId, '双向评价表单信息', 1000, 500,false);
        }
        function del(cfgFlow){
            jboxConfirm("确认删除?",function(){
                jboxPost("<s:url value='/res/evaluation/delEvaluation'/>?cfgFlow="+cfgFlow,null,function(resp){
                    jboxEndLoading();
                    if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
                        jboxTip("删除成功！");
                        search();
                    }
                },null,false);
            });
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }

        function evaluationDept(cfgFlow){
            jboxOpen("<s:url value='/res/evaluation/evaluationDept'/>?cfgFlow=" + cfgFlow, '评价表单关联科室', 1000, 400);
        }
    </script>
</head>
<%--<body>
<div class="mainright">
    <div class="content">
         <div class="title1 clearfix">
                <form id="searchForm" action="<s:url value='/res/evaluation/evaluationView'/>" method="post">
                    <input value="" name="currentPage" type="hidden" id="currentPage">
                    <input type="hidden" id="assessTypeId" value="${evaluationTypeEnumDoctorEval.id}" name="assessTypeId"/>
                    <div class="inputDiv">
                        <label class="qlable">评价表单名称：</label>
                        <input type="text" name="cfgName" value="${param.cfgName}" class="qtext">
                    </div>
                    <div class="lastDiv" style="min-width: 200px;max-width: 200px;">
                        &#12288;
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
                        <input type="button" class="searchInput" value="新&#12288;增" onclick="detail('','')">
                    </div>
                </form>
            </div>
         <table class="xllist">
            <colgroup>
                <col width="15%"/>
                <col width="75%"/>
                <col width="10%"/>
            </colgroup>
            <tr>
                <th >评价表单名称</th>
                <th >科室</th>
                <th >操作</th>
            </tr>
            <c:forEach items="${evaluationCfgs}" var="evaluation">
                <tr>
                    <td>${evaluation.cfgCodeName}</td>
                    <td  style="cursor: pointer" onclick="evaluationDept('${evaluation.cfgFlow}')"> ${deptMap[evaluation.cfgFlow]} </td>
                    <td>
                        <a onclick="detail('${evaluation.cfgCodeId}','')" style="cursor: pointer">[编辑]</a>
                        <a onclick="del('${evaluation.cfgFlow}')" style="cursor: pointer">[删除]</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty evaluationCfgs}">
                <tr>
                    <td colspan="8">无记录</td>
                </tr>
            </c:if>
        </table>

         <div  class="page"  style="padding-right: 40px;">
                <c:set var="pageView" value="${pdfn:getPageView(evaluationCfgs)}" scope="request"></c:set>
                <pd:pagination toPage="toPage"/>
            </div>
    </div>
</div>
</body>
</html>--%>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value='/res/evaluation/evaluationView'/>" method="post">
                <input value="" name="currentPage" type="hidden" id="currentPage">
                <input type="hidden" id="assessTypeId" value="${evaluationTypeEnumDoctorEval.id}" name="assessTypeId"/>
                <div class="inputDiv">
                    <label class="qlable">评价表单名称：</label>
                    <input type="text" name="cfgName" value="${param.cfgName}" class="qtext">
                </div>
                <div class="lastDiv" style="min-width: 200px;max-width: 200px;">
                    &#12288;
                    <input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
                    <input type="button" class="searchInput" value="新&#12288;增" onclick="detail('','')">
                </div>
            </form>
        </div>
        <table class="xllist">
            <colgroup>
                <col width="15%"/>
                <col width="75%"/>
                <col width="10%"/>
            </colgroup>
            <tr>
                <th >评价表单名称</th>
                <th >科室</th>
                <th >操作</th>
            </tr>
            <c:forEach items="${evaluationCfgs}" var="evaluation">
                <tr>
                    <td>${evaluation.cfgCodeName}</td>
                   <%-- <td  style="cursor: pointer" <c:if test="${flagMap[evaluation.cfgCodeId] eq 'N'}">onclick="evaluationDept('${evaluation.cfgFlow}')"</c:if>>--%>
                    <td  style="cursor: pointer" onclick="evaluationDept('${evaluation.cfgFlow}')">
                        ${deptMap[evaluation.cfgFlow]}&nbsp;&nbsp;&nbsp;

                       <%-- <c:if test="${flagMap[evaluation.cfgCodeId] ne 'N'}">
                          <span style="color: red;font-size: 16px;font-weight: bold"> [ 学员已评分，无法进行更改！ ] </span>
                        </c:if>--%>
                    </td>
                    <td>
                        <c:if test="${flagMap[evaluation.cfgCodeId] eq 'N'}">
                            <a onclick="detail('${evaluation.cfgCodeId}','')" style="cursor: pointer">[编辑]</a>
                        </c:if>
                        <c:if test="${flagMap[evaluation.cfgCodeId] ne 'N'}">
                            <a onclick="showDetail('${evaluation.cfgCodeId}','')" style="cursor: pointer">[查看]</a>
                        </c:if>
                        <c:if test="${flagMap[evaluation.cfgCodeId] eq 'N'}">
                            <a onclick="del('${evaluation.cfgFlow}')" style="cursor: pointer">[删除]</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty evaluationCfgs}">
                <tr>
                    <td colspan="8">无记录</td>
                </tr>
            </c:if>
        </table>

        <div  class="page"  style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(evaluationCfgs)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
