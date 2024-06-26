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
        function saveCredit(){
            if(false==$("#appointForm").validationEngine("validate")){
                return false;
            }
            var recordFlow=$("input[name='recordFlow']").val();
            var trainTypeId=$("select[name='trainTypeId']").val();
            var degreeCredit=$("input[name='degreeCredit']").val();
            var credit=$("input[name='credit']").val();
            var majorId=$("select[name='majorId']").val();
            var page='${currentPage}';
            var url="<s:url value='/xjgl/majorCourse/saveMajorCredit?recordFlow='/>" + recordFlow+"&trainTypeId="+trainTypeId+"&degreeCredit="+degreeCredit+"&credit="+credit+"&majorId="+majorId;
            jboxPost(url,null,function(resp){
                if(resp == '${GlobalConstant.NOT_NULL}'){
                    jboxTip("已经维护过该专业该培养层次下的学分！");
                }else{
                    jboxTip(resp);
                    window.parent.frames['mainIframe'].window.toPage(page);
                    jboxClose();
                }
            },null,false);
        }

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="appointForm" action="<s:url value="/xjgl/majorCourse/saveMajorCredit"/>" method="post">
            <input type="hidden" name="recordFlow" value="${majorCredit.recordFlow}"/>
            <table class="basic" style="width:100%;margin:10px 0px;">
                <tr>
                    <td style="text-align:right;">专业名称：</td>
                    <td>
                        <select name="majorId" style="width:151px;" class="select validate[required]">
                            <option></option>
                            <c:forEach items="${dictTypeEnumMajorList}" var="status">
                                <option value="${status.dictId}" ${majorCredit.majorId eq status.dictId ?'selected':''}>[${status.dictId}]${status.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">学位课程学分：</td>
                    <td>
                        <input type="text" name="degreeCredit" class="validate[required,custom[positiveNum]" value="${majorCredit.degreeCredit}" style="width: 147px;">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">总学分：</td>
                    <td>
                        <input type="text" name="credit" value="${majorCredit.credit}" class="validate[required,custom[positiveNum]" style="width: 147px;">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">培养层次：</td>
                    <td>
                        <select name="trainTypeId" style="width:151px;" class="select validate[required]">
                            <option/>
                            <c:forEach items="${dictTypeEnumTrainTypeList}" var="trainType">
                                <c:if test="${trainType.dictId eq '1' || trainType.dictId eq '2'}">
                                    <option value="${trainType.dictId}" ${majorCredit.trainTypeId eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="saveCredit();" value="保&#12288;存"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
