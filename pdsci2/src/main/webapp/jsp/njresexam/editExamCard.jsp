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
        var url = "<s:url value='/res/njExam/saveDocInfo'/>";
        var data=$('#docInfo').serialize();
        jboxPost(url,data, function(resp){
            if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                window.parent.toPage(1);
                jboxTip("操作成功！");

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
        <form class="search_table" id="docInfo" action="<s:url value="/res/njExam/saveDocInfo"/>" method="post">
            <table table border="0" cellpadding="0" cellspacing="0" class="base_info">
                <colgroup>
                    <col width="15%"/>
                    <col width="20%"/>
                    <col width="15%"/>
                    <col width="20%"/>
                    <col width="15%"/>
                    <col width="15%"/>
                </colgroup>
                <tr>
                    <th>姓名</th>
                    <input type="text" name="userFlow" hidden value="${docinfo.userFlow}">
                    <td><input type="text" name="userName"  class="input" value="${docinfo.userName}" ></td>
                    <th>考试科目</th>
                    <td><input type="text" name="speName" class="input" value="${docinfo.speName}"></td>
                </tr>
                <tr>
                    <th>准考证号</th>
                    <td ><input type="text"  class="input" name="ticketNum" value="${docinfo.ticketNum}"></td>
                    <th>有效身份证号</th>
                    <td ><input type="text"  class="input" name="idNo" value="${docinfo.idNo}"></td>
                </tr>
                <tr>
                    <th>考生联系电话</th>
                    <td><input type="text"  class="input" name="userPhone" value="${docinfo.userPhone}"></td>
                </tr>
                <tr>
                    <th>考试时间</th>
                    <td >
                        <textarea name="examtime" style="text-indent:0px;">${docinfo.examtime}</textarea>
                    </td>
                    <th>考试地点</th>
                    <td>
                        <textarea name="address" style="text-indent:0px;">${docinfo.address}</textarea>
                    </td>
                </tr>
                <tr>
                    <th>考点联系电话</th>
                    <td><input type="text"  class="input" name="sitephone" value="${docinfo.sitephone}"></td>
                    <th >准考证标题</th>
                    <td>
                        <input type="text"   class="input" name="title" value="${docinfo.title}">
                    </td>
                </tr>
                <tr>
                    <th>注意事项</th>
                    <td  colspan="3" >
                        <textarea name="remark" style="text-indent:0px;">${docinfo.remark}</textarea>
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
