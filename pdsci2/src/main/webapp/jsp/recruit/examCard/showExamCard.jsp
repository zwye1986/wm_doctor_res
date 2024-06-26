<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
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
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>

    <script type="text/javascript">


        function jboxPrint(id) {
            jboxTip("正在准备打印…")
            setTimeout(function(){
                $("#title").show();
                var newstr = $("#"+id).html();
                var oldstr = document.body.innerHTML;
                document.body.innerHTML = newstr;
                window.print();
                document.body.innerHTML = oldstr;
                $("#title").hide();
                jboxEndLoading();
                return false;
            },2000);
        }
 
    </script>
</head>
<body>
<div class="mainright" >
    <div class="content" id="printDiv" style="padding-bottom: 0px;">
        <div class="title1 clearfix" style="overflow: auto;min-height:300px">
                <table width="100%" class="basic" >
                    <tr>
                        <td  style="text-align: center;padding-right: 10px" colspan="3">
                            准&#12288;&#12288;考&#12288;&#12288;证
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;padding-right: 10px">姓&#12288;&#12288;名</td>
                        <td>
                            ${recruitInfoExt.sysUser.userName}
                        </td>
                        <td style="text-align: left;;width:150px;padding-left: 0px;" rowspan="3">
                            <div style="width: 110px;height: 130px;margin: 5px auto 5px;">
                                <img src="${sysCfgMap['upload_base_url']}/${recruitInfoExt.sysUser.userHeadImg}" id="showImg" width="100%"
                                     height="100%" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;padding-right: 10px">准考证号</td>
                        <td>
                            ${recruitInfoExt.ticketNumber}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;padding-right: 10px">身份证号</td>
                        <td>
                            ${recruitInfoExt.sysUser.idNo}
                        </td>
                    </tr>
                    <tr>
                        <td  style="text-align: center;padding-right: 10px" colspan="3">
                            考&#12288;试&#12288;安&#12288;排
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;padding-right: 10px">笔试时间</td>
                        <td colspan="2">
                            ${recruitInfoExt.recruitExamInfo.examStartTime}~${recruitInfoExt.recruitExamInfo.examEndTime}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;padding-right: 10px">笔试地点</td>
                        <td colspan="2">
                            ${recruitInfoExt.recruitExamRoomInfo.examAddress}
                        </td>
                    </tr>
                    <c:if test="${recruitInfoExt.interviewFlag eq 'Y'}">
                        <tr>
                            <td style="text-align: center;padding-right: 10px">面试时间</td>
                            <td colspan="2">
                                    ${interviewInfo.interviewStartTime}~${interviewInfo.interviewEndTime}
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;padding-right: 10px">面试地点</td>
                            <td colspan="2">
                                    ${interviewInfo.interviewAddress}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td style="text-align: center;padding-right: 10px">注意事项</td>
                        <td colspan="2">
                            请仔细阅读考试须知，携带2B铅笔
                        </td>
                    </tr>
                </table>
        </div>
    </div>
    <div style="text-align: center;margin-top: 15px;">
        <input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>
    </div>
</div>
</body>
</html>