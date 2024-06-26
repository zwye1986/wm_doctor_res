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
            $("#interviewInfoForm").submit();
        }

        function toPage(page)
        {
            jboxStartLoading();
            page=page||1;
            $("#currentPage").val(page);
            search();
        }

        function sendInterviewForm(recruitFlow) {
            jboxOpen("<s:url value='/recruit/interviewInfoManage/sendInterviewForm'/>?recruitFlow="+recruitFlow, "面试通知", 650, 360 ,true);
        }
        function showInterviewForm(recruitFlow) {
            jboxOpen("<s:url value='/recruit/interviewInfoManage/showInterviewForm'/>?recruitFlow="+recruitFlow, "查看面试通知", 650, 360 ,true);
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
            jboxConfirm("向所选考生发放面试邀请吗？", function () {
                var array = new Array();
                $.each($('input:checkbox[name=mychk]'),function(){
                    if(this.checked){
                        array.push($(this).val());
                    }
                });
                var url = "<s:url value='/recruit/interviewInfoManage/sendAll'/>";
                requestDate = {"chkList":JSON.stringify(array)};
                jboxPost(url,requestDate,function (resp) {
                    search();
                }, null, true);
            });
        }

        function auditInterview(recruitFlow) {
            var url = "<s:url value='/recruit/interviewInfoManage/auditInterview'/>?recordStatus=Y";
            jboxConfirm("确认面试通过？" , function(){
                requestData = "recruitFlow="+recruitFlow;
                jboxPost(url,requestData,function(resp){
                    if(resp == '${GlobalConstant.OPRE_SUCCESSED}'){
                        window.location.reload();
                    }else{
                        window.location.reload();
                    }
                },null,true);
            });
        }

        function notAuditInterview(recruitFlow) {
            var url = "<s:url value='/recruit/interviewInfoManage/auditInterview'/>?recordStatus=N";
            jboxConfirm("确认面试不通过？" , function(){
                requestData = "recruitFlow="+recruitFlow;
                jboxPost(url,requestData,function(resp){
                    if(resp == '${GlobalConstant.OPRE_SUCCESSED}'){
                        window.location.reload();
                    }else{
                        window.location.reload();
                    }
                },null,true);
            });
        }

        function insertAll() {
            var url = "<s:url value='/recruit/interviewInfoManage/importInterviewScore'/>";
            jboxOpen(url, "导入面试结果", 390, 180);
        }

        function changeInterviewScore(recruitFlow) {
            jboxOpen("<s:url value='/recruit/interviewInfoManage/changeInterviewScore'/>?recruitFlow="+recruitFlow, "修改面试成绩", 500,160);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 ">
            <form id="interviewInfoForm" action="<s:url value='/recruit/interviewInfoManage/main'/>" method="post">
                <input type="hidden" id="currentPage" name="currentPage"/>
                <div class="div_search" style="padding: 10px">
                    <div style="display:inline;margin-left: 50px">
                        <p1 class="qlable">姓&#12288;&#12288;名：</p1>
                        <input id="userName" value="${param.userName}" name="userName" type="text" class="qtext"/>
                    </div>
                    <div style="display:inline;margin-left: 50px">
                        <p1 class="qlable">证件号码：</p1>
                        <input id="idNo" value="${param.idNo}" name="idNo" type="text" class="qtext"/>
                    </div>
                    <div style="display:inline;margin-left: 50px">
                        <p1 class="qlable">年&#12288;&#12288;份：</p1>
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
                <th width="5%"><input id="all" type="checkbox" name="all" onclick="chk()"/></th>
                <th width="10%" style="text-align: center">年份</th>
                <th width="10%" style="text-align: center">姓名</th>
                <th width="10%" style="text-align: center">性别</th>
                <th width="10%" style="text-align: center">证件类型</th>
                <th width="15%" style="text-align: center">证件号码</th>
                <th width="15%" style="text-align: center">说明</th>
                <th width="10%" style="text-align: center">面试成绩</th>
                <th width="10%" style="text-align: center">面试结果</th>
                <th width="10%" style="text-align: center">操作</th>
            </tr>
            <tbody>
            <c:forEach items="${recruitInfoExts}" var="recruitInfoExt" varStatus="seq">
                <tr>
                    <td>
                        <c:if test="${recruitInfoExt.interviewFlag eq 'N'}">
                            <input type="checkbox" class="check" name="mychk" value="${recruitInfoExt.recruitFlow}"/>
                        </c:if>
                    </td>
                    <td>${recruitInfoExt.recruitYear}</td>
                    <td>${recruitInfoExt.sysUser.userName}</td>
                    <td>${recruitInfoExt.sysUser.sexName}</td>
                    <td>${recruitInfoExt.sysUser.cretTypeName}</td>
                    <td>${recruitInfoExt.sysUser.idNo}</td>
                    <td>
                        <c:set var="interviewInfo" value="${interviewInfoMap[recruitInfoExt.recruitFlow]}"></c:set>
                        <c:if test="${not empty interviewInfo}">
                            <c:if test="${not empty interviewInfo.interviewDemo}">
                                <a href="javascript:showInterviewForm('${recruitInfoExt.recruitFlow}')" style="color: blue">${interviewInfo.interviewDemo}</a>
                            </c:if>
                            <c:if test="${empty interviewInfo.interviewDemo}">
                                <a href="javascript:showInterviewForm('${recruitInfoExt.recruitFlow}')" style="color: blue">查看通知</a>
                            </c:if>
                        </c:if>
                    </td>
                    <td>${recruitInfoExt.interviewScore}</td>
                    <td>
                        <c:if test="${recruitInfoExt.interviewIsPass eq 'Y'}">
                            <font color="black">面试通过</font>
                        </c:if>
                        <c:if test="${recruitInfoExt.interviewIsPass eq 'N'}">
                            <font color="black">面试未通过</font>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${recruitInfoExt.interviewFlag eq 'N'}">
                            <a href="javascript:sendInterviewForm('${recruitInfoExt.recruitFlow}')" style="color: blue">发送面试邀请</a>
                        </c:if>

                        <c:if test="${recruitInfoExt.admitFlag ne 'Y'}">
                            <c:if test="${recruitInfoExt.interviewFlag eq 'Y'}">
                                <a href="javascript:changeInterviewScore('${recruitInfoExt.recruitFlow}')" style="color: blue">修改分数</a>
                                <c:if test="${empty recruitInfoExt.interviewIsPass}">
                                    |
                                    <a href="javascript:auditInterview('${recruitInfoExt.recruitFlow}')" style="color: blue">通过</a>
                                    |
                                    <a href="javascript:notAuditInterview('${recruitInfoExt.recruitFlow}')" style="color: blue">不通过</a>
                                </c:if>
                                <c:if test="${recruitInfoExt.interviewIsPass eq 'Y'}">
                                    |
                                    <a href="javascript:notAuditInterview('${recruitInfoExt.recruitFlow}')" style="color: blue">不通过</a>
                                </c:if>
                                <c:if test="${recruitInfoExt.interviewIsPass eq 'N'}">
                                    |
                                    <a href="javascript:auditInterview('${recruitInfoExt.recruitFlow}')" style="color: blue">通过</a>
                                </c:if>
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