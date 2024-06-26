<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            if('${flag}'=='Y'){
                $("input").attr("disabled","disabled");
                $("textarea").attr("disabled","disabled");
                $("select").attr("disabled","disabled");
                $("#applyBtn").hide();
            }
        });

        function editInfo(recordFlow){
            jboxOpen("<s:url value='/xjgl/abroadApply/editAbroadApply'/>?recordFlow="+recordFlow+"&roleFlag=${roleFlag}","编辑",960,600);
        }
        function showEditSheet(recordFlow,formType){
            jboxOpen("<s:url value='/xjgl/abroadApply/showEditSheet'/>?recordFlow="+recordFlow+"&roleFlag=${roleFlag}&formType="+formType,"编辑",960,600);
        }
        function showUploadFile(recordFlow){
            jboxOpen("<s:url value='/xjgl/abroadApply/showUploadFile'/>?recordFlow="+recordFlow+"&roleFlag=${roleFlag}","编辑",500,180);
        }
        function editTrip(recordFlow){
            var url ="<s:url value='/xjgl/abroadApply/showTripInfo?recordFlow='/>"+recordFlow+"&roleFlag=${param.roleFlag}";
            jboxOpen(url,"编辑",960,600);
        }
        function editCost(recordFlow){
            jboxOpen("<s:url value='/xjgl/abroadApply/showEditSheet'/>?recordFlow="+recordFlow+"&roleFlag=${param.roleFlag}&formType=cost","编辑",900,500);
        }
        function editProj(recordFlow){
            jboxOpen("<s:url value='/xjgl/abroadApply/showUploadFile'/>?recordFlow="+recordFlow+"&roleFlag=${roleFlag}&type=proj","编辑",500,180);
        }

        function saveApply(){
            if(!$("#searchForm").validationEngine("validate")){
                return;
            }
            var start = $("#leaveTimeStart").val();
            var end = $("#leaveTimeEnd").val();
            $("#leaveTimeInput").val(start+"/"+end);
            var url ="<s:url value='/xjgl/leave/saveApply'/>";
            jboxPost(url, $("#searchForm").serialize() , function(resp){
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}'){
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            } , null , true);
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
    </script>
</head>
<body>

<div class="mainright">
    <div class="content">
        <%--<form id="searchForm" method="post" action="<s:url value='/xjgl/leave/leaveInfosByStu'/>">--%>
        <%--<input type="hidden" name="recordFlow" value="${leaveMain.recordFlow}"/>--%>
        <table class="basic" style="width: 100%;margin: 0px 0px 10px 0px;">
            <tr>
                <td style="text-align: center;">
                    <span style="font-size: 16px;">${abroadApply.userName}</span>
                </td>
            </tr>
            <tr>
                <td>
                    <a onclick="editInfo('${recordFlow}');" style="cursor:pointer;color:blue;">研究生出国（境）申请登记表</a>
                </td>
            </tr>
            <tr>
                <td>
                    <a onclick="showEditSheet('${recordFlow}','recordSheet');" style="cursor:pointer;color:blue;">研究生出国人员备案登记表</a>
                </td>
            </tr>
            <tr>
                <td>
                    <a onclick="showUploadFile('${recordFlow}');" style="cursor:pointer;color:blue;">研究生出国邀请信与中文译文附件</a>
                </td>
            </tr>
            <tr>
                <td>
                    <a onclick="editTrip('${recordFlow}');" style="cursor:pointer;color:blue;">研究生出访行程路线登记表</a>
                </td>
            </tr>
            <tr>
                <td>
                    <a onclick="editCost('${recordFlow}');" style="cursor:pointer;color:blue;">研究生出国费用预算与费用说明登记表</a>
                </td>
            </tr>
            <tr>
                <td>
                    <a onclick="editProj('${recordFlow}');" style="cursor:pointer;color:blue;">研究生项目相关附件</a>
                </td>
            </tr>
        </table>
        <%--</form>--%>
    </div>
    <div style="text-align: center;margin-top:20px;">
        <input type="button" class="search" value="关&#12288;闭" onclick="jboxCloseMessager();"/>
    </div>
</div>
</body>
</html>
