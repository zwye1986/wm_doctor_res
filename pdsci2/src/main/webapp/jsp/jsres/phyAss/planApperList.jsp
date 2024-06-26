<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>

<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript"
        src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    table.grid th, table.grid td {
        padding: 0;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $("#currentPage").val("${param.currentPage}");
    });

    function selectAll() {
        if($("#checkAll").is(':checked')){
            $(".check").prop("checked",true);
        }else{
            $(".check").prop("checked",false);
        }
    }

    function closeFlash() {
        window.parent.toPage(1);
        top.jboxCloseMessager();
    }

    function operateUser(type) {
        var recordFlows = new Array();
        var i = 0;
        if($(".main_bd input[type='checkbox']").length<=0) {
            jboxTip("请选择需要操作的数据！");
            return;
        }
        $(".main_bd input[type='checkbox']:checked").each(function(){
            var aa = $(this).attr("recordFlow");
            if(aa != "" && aa != undefined) {
                recordFlows[i] = $(this).attr("recordFlow");
                i++;
            }
        });
        var recordFlowsStr = recordFlows.join(",");
        if(recordFlowsStr.length==0){
            jboxTip("您还没有勾选！");
            return;
        }

        var title='是否删除？';
        var url = "<s:url value='/jsres/phyAss/operateUser?recordFlowsStr='/>" + recordFlowsStr+"&type="+type;
        if (type=='affirm'){
            title='是否确认？';
             url = "<s:url value='/jsres/phyAss/operateUser?recordFlowsStr='/>" + recordFlowsStr+"&type="+type;
        }
        debugger;
        var userStatus = $("#userStatus1").val();
        var planFlow = $("#planFlow1").val();
        var type = $("#type1").val();
        jboxConfirm(title , function() {
            jboxPost(url,null,function(resp){
                jboxTip(resp);
                if(resp=='${GlobalConstant.OPERATE_SUCCESSED}'){
                    loadInfo(planFlow,type,userStatus);
                }
            }, null, false);
        });
    }
</script>

<div class="search_table" style="width: 95%;padding: 0 20px">
    <input type="hidden" id="userStatus1" value="${userStatus}">
    <input type="hidden" id="planFlow1" value="${planFlow}">
    <input type="hidden" id="type1" value="${type}">
    <div style="margin: 10px 0px">
        <span style="font-size: 16px;font-weight: bold">培训计划：</span> <span style="font-size: 14px">${plan.planContent}</span>
        <span style="font-size: 16px;font-weight: bold;margin-left: 20%">上报人数：</span> <span style="font-size: 14px">${apperNum}</span>
        <div style="margin-top: -24px;text-align: right">
            <c:if test="${type ne 'show'}">
                <input class="btn_green" type="button" value="删&#12288;除" onclick="operateUser('del');"/>
                <input class="btn_green" type="button" value="确&#12288;认" onclick="operateUser('affirm');"/>
            </c:if>

            <input class="btn_green" type="button" value="关&#12288;闭" onclick="closeFlash();"/>
        </div>
    </div>
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <c:if test="${type eq 'affirm' and userStatus eq 'toAffirm'}">
               <th style="border: 1px solid #e3e3e3;" width="5%">
                   <input type="checkbox" id="checkAll" onchange="selectAll()"/>
               </th>
           </c:if>
            <th style="border: 1px solid #e3e3e3;" width="">登录名</th>
            <th style="border: 1px solid #e3e3e3;" width="9%">姓名</th>
            <th style="border: 1px solid #e3e3e3;" width="9%">性别</th>
            <th style="border: 1px solid #e3e3e3;" width="14%">基地</th>
            <th style="border: 1px solid #e3e3e3;" width="9%">培训专业</th>
            <th style="border: 1px solid #e3e3e3;" width="9%">科室</th>
            <th style="border: 1px solid #e3e3e3;" width="9%">角色</th>
            <th style="border: 1px solid #e3e3e3;" width="9%">手机号</th>
            <th style="border: 1px solid #e3e3e3;" width="9%">身份证号</th>
            <th style="border: 1px solid #e3e3e3;" width="9%">电子邮箱</th>
        </tr>
        <c:if test="${empty list}">
            <tr>
                <td colspan="11">无记录！</td>
            </tr>
        </c:if>
        <c:if test="${not empty list}">
            <c:forEach items="${list}" var="s">
                <tr>
                    <c:if test="${type eq 'affirm' and userStatus eq 'toAffirm'}">
                        <td style="border: 1px solid #e3e3e3;">
                            <input recordFlow="${s.recordFlow}" type="checkbox" class="check"/>
                        </td>
                    </c:if>
                    <td style="border: 1px solid #e3e3e3;">${s.doctorCode}</td>
                    <td style="border: 1px solid #e3e3e3;">${s.doctorName}</td>
                    <td style="border: 1px solid #e3e3e3;">${s.sexName}</td>
                    <td style="border: 1px solid #e3e3e3;">${s.orgName}</td>
                    <td title="${s.speName}" style="border: 1px solid #e3e3e3;">${pdfn:cutString(s.speName,6,true,3)}</td>
                    <td title="${s.deptName}" style="border: 1px solid #e3e3e3;">${pdfn:cutString(s.deptName,6,true,3)}</td>
                    <td style="border: 1px solid #e3e3e3;">${s.doctorRoleName}</td>
                    <td style="border: 1px solid #e3e3e3;">${s.userPhone}</td>
                    <td style="border: 1px solid #e3e3e3;">${s.idNo}</td>
                    <td style="border: 1px solid #e3e3e3;">${s.userEmail}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</div>
<div class="page" style="text-align: center">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
</div>
