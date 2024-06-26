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
        function save(){
            var form = $("#sendInterviewForm");
            if(false==form.validationEngine("validate")){
                return ;
            }
            var url = "<s:url value='/recruit/interviewInfoManage/sendInterview'/>";
            requestData = $('#sendInterviewForm').serialize();
            jboxPost(url,requestData,function(resp){
                if(resp == '${GlobalConstant.SEND_SUCCESSED}'){
                    window.parent.frames['mainIframe'].window.search();
                    jboxClose();
                }
            },null,true);
        }
    </script>

</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="sendInterviewForm"name="sendInterviewForm" action="<s:url value='/recruit/interviewInfoManage/sendInterview'/>" method="post" style="position: relative;">
                <input hidden value="${recruitInfo.recruitFlow}" id="recruitFlow" name="recruitFlow"/>
                <input hidden value="${recruitInfo.doctorFlow}" id="doctorFlow" name="doctorFlow"/>
                <input hidden value="${recruitInfo.orgFlow}" id="orgFlow" name="orgFlow"/>
                <table width="100%" class="basic" >
                    <tr>
                        <th>年份：</th>
                        <td>
                            <input type="text" class="xltext" value="${recruitInfo.recruitYear}" style="margin-right: 0px" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <th>考生姓名：</th>
                        <td>
                            <input type="text" class="xltext" value="${recruitInfo.sysUser.userName}" style="margin-right: 0px" readonly="readonly"/>

                        </td>
                    </tr>
                    <tr>
                        <th>身份证号：</th>
                        <td>
                            <input type="text" class="xltext" value="${recruitInfo.sysUser.idNo}" style="margin-right: 0px" readonly="readonly"/>

                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>面试开始时间：</th>
                        <td>
                            <input type="text" class="validate[required] xltext" id="interviewStartTime" name="interviewStartTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'${recruitInfo.recruitExamInfo.examEndTime}'})" value="${recruitInfo.recruitExamInfo.interviewStartTime}" style="margin-right: 0px"  readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>面试结束时间：</th>
                        <td>
                            <input type="text" class="validate[required] xltext" id="interviewEndTime" name="interviewEndTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'${recruitInfo.recruitExamInfo.examEndTime}'})" value="${recruitInfo.recruitExamInfo.interviewEndTime}" style="margin-right: 0px"  readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>面试地点：</th>
                        <td>
                            <input type="text" class="validate[required] xltext" id="interviewAddress" name="interviewAddress"
                                   value="${recruitInfo.recruitExamInfo.interviewAddress}" style="margin-right: 0px" placeholder="地址"/>
                        </td>
                    </tr>
                    <tr>
                        <th>备注说明：</th>
                        <td>
                            <input type="text" id="interviewDemo" name="interviewDemo"
                                   value="${recruitInfo.recruitExamInfo.interviewDemo}" style="margin-right: 0px" placeholder="备注"/>

                        </td>
                    </tr>
                </table>
                <div style="text-align: center;margin-top: 15px;">
                    <input type="button" onclick="save()" class="search" value="发&#12288;送"/>
                    <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>


