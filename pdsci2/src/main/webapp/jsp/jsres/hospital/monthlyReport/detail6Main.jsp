<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER{background-color: #eee;}
    .cur{color:red;}
    html,body{
        height: 100%;
        overflow: auto;
    }
</style>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script>
    $(document).ready(function(){
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode:2,
            format:'yyyy'
        });
        toPage(1);
        getDepts('${currentOrgFlow}');
    })
    function toPage(page) {
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
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("detail6","<s:url value='/jsres/monthlyReport/detail6'/>",$("#searchForm").serialize(),false);
    }
    function exportExl(){
        var url = "<s:url value='/jsres/monthlyReport/exportDetail6'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"),url);
    }
    function getDepts(orgFlow){
        jboxPost("<s:url value='/jsres/monthlyReport/getDepts'/>?orgFlow="+orgFlow,null,function(resp){
            $("#schDeptFlow").empty();
            $("#schDeptFlow").append("<option value=''>全部</option>");
            for(k in resp){
                $("#schDeptFlow").append("<option value='"+k+"'>"+resp[k]+"</option>");
            }
        },null,false)
    }
</script>
<div class="main_bd" id="div_table_0" >
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage" value=""/>
            <input type="hidden" name="monthDate" value="${monthDate}"/>

            <table class="searchTable">
                <tr>
                    <td class="td_left">培训基地：</td>
                    <td>
                        <select name="orgFlow" class="select" onchange="getDepts(this.value)">
                            <option value="all">全部</option>
                            <c:forEach items="${orgMap}" var="org">
                                <option value="${org.key}" <c:if test="${org.key eq currentOrgFlow}">selected="selected"</c:if>>${org.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">科室名称：</td>
                    <td>
                        <select name="schDeptFlow" id="schDeptFlow" class="select">
                            <option value="">全部</option>
                            <%--<c:forEach items="${deptMap}" var="dept">--%>
                                <%--<option value="${dept.key}">${dept.value}</option>--%>
                            <%--</c:forEach>--%>
                        </select>
                    </td>
                    <td  class="td_left">年&#12288;&#12288;级：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                               class="input" readonly="readonly"/>
                    </td>
                    <td class="td_left">培训专业：</td>
                    <td>
                        <select name="trainingSpeId" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${speMap}" var="spe">
                                <option value="${spe.key}">${spe.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}" name="doctorTypeIds" checked="checked"/>${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td colspan="4">
                        <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                        <input class="btn_green" type="button" value="导&#12288;出" onclick="exportExl();"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="detail6">
    </div>
</div>
