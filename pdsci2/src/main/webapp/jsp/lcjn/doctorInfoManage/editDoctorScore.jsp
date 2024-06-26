<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
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
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <script>

        function saveLcjnScore(recordFlow){
            var examScore=$("input[name='examScore']").val();
            var regex1=/^[\u4E00-\u9FA5]+$/;
            var regex2=/^(0|([1-9]\d*))(\.\d+)?$/;
            var regex3 =/^\d+(\.\d)?$/;;
            var page='${currentPage3}';
            if(regex1.test(examScore) || regex3.test(examScore)){
                var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/editDoctorScore?recordFlow='/>" + recordFlow+"&examScore="+encodeURI(encodeURI(examScore));
                jboxPost(url,null,function(resp){
                    jboxTip(resp);
                    window.parent.frames['mainIframe'].window.toPage3(page);
                    jboxClose();
                });
            }else {
                jboxTip("成绩只能输入文字或者最多一位小数的数字！");
                return false;
            }

        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="appointForm" action="<s:url value="/lcjn/lcjnDoctorTrainInfo/doctorScoreList"/>" method="post">
            <table  class="xllist" style="width:100%; position:relative;">
                <tbody>
                <tr>
                    <th  style="text-align: right;">姓&#12288;&#12288;名：</th>
                    <td  style="text-align: left;"><label style="margin-left: 8px;" name="userName" value="${doctorScore.USER_NAME}">${doctorScore.USER_NAME}</label></td>
                </tr>
                <tr>
                    <th  style="text-align: right;">培训专业：</th>
                    <td  style="text-align: left;"><label style="margin-left: 8px;" name="trainingSpeName" value="${doctorScore.LCJN_SPE_NAME}">${doctorScore.LCJN_SPE_NAME}</label></td>
                </tr>
                <tr>
                    <th  style="text-align: right;">工作单位：</th>
                    <td  style="text-align: left;"><label style="margin-left: 8px;" name="workOrgName" value="${doctorScore.ORG_NAME}">${doctorScore.ORG_NAME}</label></td>
                </tr>
                <tr>
                    <th  style="text-align: right;">所在科室：</th>
                    <td  style="text-align: left;"><label style="margin-left: 8px;" name="deptName" value="${doctorScore.DEPT_NAME}">${doctorScore.DEPT_NAME}</label></td>
                </tr>
                <tr>
                    <th  style="text-align: right;">成&#12288;&#12288;绩：</th>
                    <td  style="text-align: left;"><input class="input" type="text" style="margin-left: 8px;" name="examScore" value="${doctorScore.EXAM_SCORE}"/></td>
                </tr>
                </tbody>
            </table>
            <br/>
            <div  style="text-align: center;" colspan="2" style="height:100px;">
                <input class="search" type="button" value='保&#12288;存' onclick="saveLcjnScore('${doctorScore.RECORD_FLOW}')" />
            </div>
        </form>
    </div>
</div>

</body>
</html>
