<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="false"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>

    <style type="text/css">
        caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;}
    </style>

    <script type="text/javascript">
        function search(){
            toPage(1);
        }

        function toPage(page)
        {
            jboxStartLoading();
            page=page||1;
            $("#currentPage").val(page);
            $("#admitInfoForm").submit();
        }

        function sendAdmitForm(recruitFlow) {
            jboxOpen("<s:url value='/recruit/admitInfoManage/sendAdmitForm'/>?recruitFlow="+recruitFlow, "录取通知", 650, 360 ,true);
        }
        function showAdmitForm(recruitFlow) {
            jboxOpen("<s:url value='/recruit/admitInfoManage/showAdmitForm'/>?recruitFlow="+recruitFlow, "查看录取通知", 650, 360 ,true);
        }

        function chk() {
            var all = document.getElementById("all");
            var mychk = document.getElementsByName("mychk");
            if (all.checked == true) {
                if (mychk.length) {
                    for (var i = 0; i < mychk.length; i++) {
                        mychk[i].checked = true;
                    }
                }
                mychk.chcked = true;
            } else {
                if (mychk.length) {
                    for (var i = 0; i < mychk.length; i++) {
                        mychk[i].checked = false;
                    }
                }
            }
        }



        function sendAll() {
            var num = $(".check:checked").length;
            if(num==0){
                jboxTip("请至少选择一个学员");
                return;
            }
            jboxConfirm("向所选考生发放录取通知吗？", function () {
                var recruitFlows="";
                $.each($('input:checkbox[name=mychk]'),function(){
                    if(this.checked){
                        recruitFlows+="&recruitFlows="+$(this).val();
                    }
                });
                if(recruitFlows.length>0)
                {
                    recruitFlows=recruitFlows.substr(1);
                }
                jboxOpen("<s:url value='/recruit/admitInfoManage/sendAdmitAllForm'/>?"+recruitFlows, "批量发送录取通知", 650, 360 ,true);
            });
        }

        function auditAdmit(recruitFlow) {
            var url = "<s:url value='/recruit/admitInfoManage/auditAdmit'/>?recordStatus=Y";
            jboxConfirm("确认学员已报到？" , function(){
                requestData = "recruitFlow="+recruitFlow;
                jboxPost(url,requestData,function(resp){
                    if(resp == '${GlobalConstant.OPRE_SUCCESSED}'){
                        window.location.reload();
                    }
                },null,true);
            });
        }

        function notAuditAdmit(recruitFlow) {
            var url = "<s:url value='/recruit/admitInfoManage/auditAdmit'/>?recordStatus=N";
            jboxConfirm("确认学员未报到？" , function(){
                requestData = "recruitFlow="+recruitFlow;
                jboxPost(url,requestData,function(resp){
                    if(resp == '${GlobalConstant.OPRE_SUCCESSED}'){
                        window.location.reload();
                    }
                },null,true);
            });
        }

        function insertAll() {
            var url = "<s:url value='/recruit/admitInfoManage/importAdmitResult'/>";
            jboxOpen(url, "导入报到学员", 390, 180);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 ">
            <form id="admitInfoForm" action="<s:url value='/recruit/admitInfoManage/main'/>" method="post">
                <input type="hidden" id="currentPage" name="currentPage"/>
                <div class="div_search" style="padding: 10px">
                    <div style="display:inline;margin-left: 50px">
                        <p1 class="qlable">姓名：</p1>
                        <input id="userName" value="${param.userName}" name="userName" type="text" class="qtext"/>
                    </div>
                    <div style="display:inline;margin-left: 50px">
                        <p1 class="qlable">证件号码：</p1>
                        <input id="idNo" value="${param.idNo}" name="idNo" type="text" class="qtext"/>
                    </div>
                    <div style="display:inline;margin-left: 50px">
                        <p1 class="qlable">年份：</p1>
                        <input id="recruitYear" value="${param.recruitYear}" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" name="recruitYear" type="text" class="qtext"/>
                    </div>
                    <div style="display:inline;margin-left: 50px">
                        <input style="margin-left: 40px" type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
                        <input style="margin-left: 40px" type="button" value="导&#12288;入" class="searchInput" onclick="insertAll();"/>
                        <input style="margin-left: 40px" type="button" value="一键发送" class="searchInput" onclick="sendAll();"/>
                    </div>
                </div>
            </form>
        </div>
        <table class="xllist" style="width: 100%;">
            <tr>
                <th width="24px"><input id="all" type="checkbox" name="all" onclick="chk()"/></th>
                <th width="24px" style="text-align: center">年份</th>
                <th width="24px" style="text-align: center">姓名</th>
                <th width="24px" style="text-align: center">性别</th>
                <th width="24px" style="text-align: center">证件类型</th>
                <th width="24px" style="text-align: center">证件号码</th>
                <th width="24px" style="text-align: center">说明</th>
                <th width="24px" style="text-align: center">是否录取</th>
                <th width="24px" style="text-align: center">报到结果</th>
                <th width="24px" style="text-align: center">操作</th>
            </tr>
            <tbody>
            <c:forEach items="${recruitInfoExts}" var="recruitInfoExt" varStatus="seq">
                <%--<tr class="body _${examRoom.speId}">--%>
                <tr>
                    <td>
                        <c:if test="${recruitInfoExt.admitFlag eq 'N'}">
                            <input type="checkbox" class="check" name="mychk" value="${recruitInfoExt.recruitFlow}"/>
                        </c:if>
                    </td>
                    <td>${recruitInfoExt.recruitYear}</td>
                    <td>${recruitInfoExt.sysUser.userName}</td>
                    <td>${recruitInfoExt.sysUser.sexName}</td>
                    <td>${recruitInfoExt.sysUser.cretTypeName}</td>
                    <td>${recruitInfoExt.sysUser.idNo}</td>

                    <td>

                        <c:set var="admitInfo" value="${admitInfoMap[recruitInfoExt.recruitFlow]}"></c:set>
                        <c:if test="${not empty admitInfo}">
                            <c:if test="${not empty admitInfo.admitDemo}">
                                <a href="javascript:showAdmitForm('${recruitInfoExt.recruitFlow}')" style="color: blue">${admitInfo.admitDemo}</a>
                            </c:if>
                            <c:if test="${empty admitInfo.admitDemo}">
                                <a href="javascript:showAdmitForm('${recruitInfoExt.recruitFlow}')" style="color: blue">查看通知</a>
                            </c:if>
                        </c:if>
                    </td>
                        <td>

                            <c:if test="${recruitInfoExt.admitFlag eq 'Y'}">
                                是
                            </c:if>
                            <c:if test="${recruitInfoExt.admitFlag eq 'N'}">
                                否
                            </c:if>
                        </td>
                        <td>

                            <c:if test="${recruitInfoExt.admitFlag eq 'Y'}">
                                <c:if test="${recruitInfoExt.admitIsPass eq 'Y'}">
                                    <font color="black">已报到</font>
                                </c:if>
                                <c:if test="${recruitInfoExt.admitIsPass eq 'N'}">
                                    <font color="black">未报到</font>
                                </c:if>
                            </c:if>
                        </td>
                    <td>
                        <c:if test="${recruitInfoExt.admitFlag eq 'N'}">
                            <a href="javascript:sendAdmitForm('${recruitInfoExt.recruitFlow}')" style="color: blue">发送录取通知</a>
                        </c:if>
                        <c:if test="${recruitInfoExt.admitFlag eq 'Y'}">
                            <c:if test="${empty recruitInfoExt.admitIsPass  }">
                                <a href="javascript:auditAdmit('${recruitInfoExt.recruitFlow}')" style="color: blue">已报到</a>
                                |
                                <a href="javascript:notAuditAdmit('${recruitInfoExt.recruitFlow}')" style="color: blue">未报到</a>
                            </c:if>
                            <c:if test="${recruitInfoExt.admitIsPass  eq 'Y'}">
                                <a href="javascript:notAuditAdmit('${recruitInfoExt.recruitFlow}')" style="color: blue">未报到</a>
                            </c:if>
                            <c:if test="${recruitInfoExt.admitIsPass  eq 'N'}">
                                <a href="javascript:auditAdmit('${recruitInfoExt.recruitFlow}')" style="color: blue">已报到</a>
                            </c:if>
                        </c:if>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty recruitInfoExts}">
                <tr><td align="center" colspan="99">无记录</td></tr>
            </c:if>
        </table>
    </div>
    <div class="resultDiv">
        <c:set var="pageView" value="${pdfn:getPageView(recruitInfoExts)}" scope="request"/>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>