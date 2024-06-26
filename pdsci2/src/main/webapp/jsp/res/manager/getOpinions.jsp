<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
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
    <style type="text/css">
        td{padding-left: 10px;padding-right: 10px; }
    </style>
    <script type="text/javascript">
        function search(){
            $("#searchForm").submit();
        }
        function reply(trainingOpinionFlow){
            jboxOpen("<s:url value='/res/liveTraining/replyOpinions'/>?trainingOpinionFlow="+trainingOpinionFlow,"回复反馈",550,350);
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value='/res/liveTraining/getOpinions'/>" method="post">
                <input id="currentPage" type="hidden" name="currentPage" value=""/>
                <div class="queryDiv">
                    <div class="inputDiv">
                        <label class="qlable">反馈内容：</label>
                        <input name="opinionUserContent" value="${param.opinionUserContent}" type="text" class="qtext"/>
                    </div>
                    <div class="inputDiv" style="min-width: 160px;max-width: 160px;">
                        <label><input name="replayStatus" value="Y" type="checkbox"
                                      <c:if test="${'Y' eq param.replayStatus}">checked</c:if>>
                            &nbsp;仅显示未回复的反馈</label>
                    </div>
                    <div class="lastDiv">
                        <input type="button" class="searchInput" value="查&#12288;询" onclick="search();">
                    </div>
                </div>
            </form>
        </div>

        <table class="xllist" style="border-left: none;border-right: none;border-bottom: none;">
            <tr>
                <th colspan="3" style="text-align: center;border-left: 1px solid #D0E3F2;border-right: 1px solid #D0E3F2;">
                    <span style="font-size: 18px">住培意见反馈</span>
                    <span style="font-size: 12px">(反馈意见仅住培管理员可查看)</span>
                </th>
            </tr>
            <c:forEach items="${trainingOpinions}" var="trainingOpinion" varStatus="status">
                <tr>
                    <td colspan="3" style="text-align: left;border-left: 1px solid #D0E3F2;border-right: 1px solid #D0E3F2;">&nbsp;<u><b>${status.count}.</b></u>${trainingOpinion.opinionUserContent}</td>
                </tr>
                <c:if test="${!empty trainingOpinion.opinionReplyContent}">
                <tr>
                    <td style="text-align: left;width: 10%;border-left: 1px solid #D0E3F2;border-right: 1px solid #D0E3F2;">&nbsp;您的回复：</td>
                    <td colspan="2" style="text-align: left">&nbsp;${trainingOpinion.opinionReplyContent}</td>
                </tr>
                </c:if>
                <tr>
                    <%--<td style="text-align: left;width: 10%">&nbsp;提出人:${trainingOpinion.opinionUserName}</td>--%>
                    <script>
                        $(function(){
                            var date = "${trainingOpinion.evaTime}";
                            var a = date.substr(0,4);
                            var b = date.substr(4,2);
                            var c = date.substr(6,2);
                            var result ="提出时间："+a+"-"+b+"-"+c;
                            $(".ass").eq(${status.count-1}).text(result);
                        });
                    </script>
                    <%--<td  style="text-align: left;width:10%"></td>--%>
                    <td  colspan="3" style="text-align: left;border-left: 1px solid #D0E3F2;">
                        <div>
                            提出人：${trainingOpinion.opinionUserName}&#12288;
                            <span class="ass"></span> &#12288;
                            <c:if test="${empty trainingOpinion.opinionReplyContent}">
                               <%--<a onclick="reply('${trainingOpinion.trainingOpinionFlow}')" style="cursor: pointer;background-color: #f3e97a;color:green">[回复]</a>--%>
                               <a href="javascript:void(0);" style="float: right;color: blue;" onclick="reply('${trainingOpinion.trainingOpinionFlow}')">回复</a>
                            </c:if>
                        </div>
                    </td>
                </tr>
                <tr style="height: 10px;border-bottom: none;">
                    <td colspan="20" style="height: 10px;border-left: none;border-right: none;
                    <c:if test="${status.count eq trainingOpinions.size()}">border-bottom: none;</c:if>
                    "></td>
                </tr>
            </c:forEach>
            <c:if test="${empty trainingOpinions}">
                <tr>
                    <td colspan="3">无记录!</td>
                </tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(trainingOpinions)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
