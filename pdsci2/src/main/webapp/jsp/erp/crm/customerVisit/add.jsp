<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        .xllist th {
            text-align: right;
            height: 40px;
        }

        .xllist td {
            text-align: left;
            height: 40px;
        }
    </style>
    <script type="text/javascript">
        function saveDateForm() {
            var $form = $("#dateForm");
            if ($form.validationEngine("validate")) {
                var url = "<s:url value='/erp/crm/customerVisit/save'/>";
                jboxConfirm("确定保存回访信息吗？" , function(){
                    jboxPost(url, $form.serialize(), function (resp) {
                        if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                            jboxTip("保存成功");

                            var visitSubject=$("#visitSubject").val();
                            var visitContent=$("#visitContent").val();
                            var infoBody = document.getElementById("infoBody").innerHTML;
                            var line = '<tr><td style="text-align: center;">${pdfn:getCurrDate()}</td>'+
                                    '<td style="text-align: left;">'+visitSubject+'</td>'+
                                    '<td>'+visitContent+'</td>'+
                                    '<td style="text-align: center;">${sessionScope.currUser.userName}</td>'+
                                    '</tr>';
                            document.getElementById("infoBody").innerHTML = line + infoBody;

                            $("#visitSubject").val("");
                            $("#visitContent").val("");
                            $("#noneInfo").css("display", "none");

    //                        setTimeout(function () {
    //                            window.parent.frames['mainIframe'].window.location.reload();
    //                            jboxClose();
    //                        }, 2000);
                        } else {
                            var msg = "保存失败！";
                            jboxTip(msg);
                        }
                    }, null, false);
                });
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <form id="dateForm"  name="dateForm">
        <input type="hidden" name="visitType" id="visitType" value="1"/>
        <input type="hidden" name="visitRefFlow" id="visitRefFlow" value="${param.customerFlow}"/>
        <div class="content">
            <div class="title1 clearfix">
                <table class="xllist">
                    <tr>
                        <th width="140px;">回访主题：</th>
                        <td>&nbsp;&nbsp;<input type="text" name="visitSubject" id="visitSubject"
                                               class="validate[required]" maxlength="50"/></td>
                        <th width="140px;">回访人：</th>
                        <td>&nbsp;&nbsp;${sessionScope.currUser.userName}
                            <input type="hidden" name="visitUserFlow" id="visitUserFlow"
                                   value="${sessionScope.currUser.userFlow}"/>
                            <input type="hidden" name="visitUserName" id="visitUserName"
                                   value="${sessionScope.currUser.userName}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>&#12288;回访内容：</th>
                        <td colspan="3">&nbsp;&nbsp;<textarea name="visitContent" id="visitContent"
                                                              class="validate[required]" maxlength="1000"
                                                              style="margin-top: 5px;margin-bottom: 5px;width:609px;height:58px;overflow: auto; overflow-x: scroll"></textarea>
                        </td>
                    </tr>
                </table>
                <div>
                    <p style="text-align: center; margin-bottom:20px;"><input type="button" class="search" value="保&#12288;存" onclick="saveDateForm();"><input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"></p>
                </div>

                <div style="overflow-x: auto; overflow-y: auto; height: 205px;border:1px solid #eee;padding:2px;">
                    <table cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;">
                        <tr>
                            <th style="text-align: left;padding-left: 10px">客户回访历史</th>
                        </tr>
                    </table>
                    <table cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;">
                        <colgroup>
                            <col width="12%"/>
                            <col width="18%"/>
                            <col width="60%"/>
                            <col width="10%"/>
                        </colgroup>
                        <tr>
                            <th style="text-align: center;">回访时间</th>
                            <th style="text-align: left;">&nbsp;回访主题</th>
                            <th style="text-align: left;">&nbsp;回访内容</th>
                            <th style="text-align: center;">回访人</th>
                        </tr>
                        <tbody id="infoBody">
                            <c:forEach items="${visitList}" var="visit" varStatus="status">
                                <tr>
                                    <td style="text-align: center;" title="${pdfn:transDateTime(visit.visitTime)}">${pdfn:transDate(visit.visitTime)}</td>
                                    <td style="text-align: left;">${visit.visitSubject}</td>
                                    <td style="text-align: left;">${visit.visitContent}</td>
                                    <td style="text-align: center;">${visit.visitUserName}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <c:if test="${empty visitList}">
                            <tr id="noneInfo">
                                <td colspan="4" style="text-align: center;">无记录</td>
                            </tr>
                        </c:if>
                    </table>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>