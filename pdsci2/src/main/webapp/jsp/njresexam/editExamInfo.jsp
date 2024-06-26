<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>住院医师规范化培训结业考核管理平台</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
    </jsp:include>

    <script type="text/javascript">
    function saveDocInfo(){
        var url = "<s:url value='/res/njExam/saveExamInfo'/>";
        var data=$('#examInfo').serialize();
        jboxPost(url,data, function(resp){
            if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                window.parent.search();
                jboxTip("操作成功！");
                jboxClose();

            }else{
                jboxTip(resp==""?"操作失败！":resp);
            }
        },null,false);
    }
    </script>
</head>

<style type="text/css">
    .base_info th{
        text-align: center;
    }
    .base_info td{
        text-align: left;
    }
</style>


<body style="margin:0 10px;">
<div style="overflow:auto;" id="indexBody">

    <div class="doctorContent">
        <form class="search_table" id="examInfo" action="<s:url value="/res/njExam/saveExamInfo"/>" method="post">
            <table table border="0" cellpadding="0" cellspacing="0" class="base_info">
                <tr>
                    <th>培训专业</th>
                    <td>
                        <select name="speName" id="speName" class="select" style="width: 106px;">
                            <option value="">请选择</option>
                            <option <c:if test="${examInfo.speName eq '全科转岗'}">selected="selected"</c:if> value="全科转岗">全科转岗</option>
                            <option <c:if test="${examInfo.speName eq '助理全科'}">selected="selected"</c:if> value="助理全科">助理全科</option>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option <c:if test="${examInfo.speName eq dict.dictName}">selected="selected"</c:if> value="${dict.dictName}">${dict.dictName}</option>
                                </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>考试时间</th>
                    <td >
                        <textarea name="examTime" style="text-indent:0px;">${examInfo.examTime}</textarea>
                        <input type="hidden" name="recordFlow" value="${examInfo.recordFlow}">
                        <input type="hidden" name="userFlow" value="${examInfo.userFlow}">
                        <input type="hidden" name="cityCode" value="${examInfo.cityCode}">
                    </td>
                    <th>考试地点</th>
                    <td>
                        <textarea name="examAddress" style="text-indent:0px;">${examInfo.examAddress}</textarea>
                    </td>
                </tr>
                <tr>
                    <th>考点联系电话</th>
                    <td><input type="text"  class="input" name="sitePhone" value="${examInfo.sitePhone}"></td>
                    <th >准考证标题</th>
                    <td>
                        <input type="text"   class="input" name="title" value="${examInfo.title}">
                    </td>
                </tr>
                <tr>
                    <th>注意事项</th>
                    <td  colspan="3" >
                        <textarea name="remark" style="text-indent:0px;">${examInfo.remark}</textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" id="func" style="text-align: center">
                        <input class="btn_green" style="margin-left: 0px;" type="button" value="保&#12288;存"
                               onclick="saveDocInfo();"/>&nbsp;
                        <input class="btn_green" style="margin-left: 0px;" type="button" value="关&#12288;闭"
                               onclick="jboxClose();"/>&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
