<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="false"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>

    <script type="text/javascript">
        function doPrint(){
            jboxTip("打印中...请稍等");
            setTimeout(function(){
                var headstr = "<html><head><title></title></head><body>";
                var footstr = "</body>";
                var newstr = $(".printDiv").html();
                var oldstr = document.body.innerHTML;
                document.body.innerHTML = headstr+newstr+footstr;
                window.print();
                document.body.innerHTML = oldstr;
                return false;
            },3000);
        }
    </script>
    <style type="text/css">
        .printDiv{
            margin-top: 60px;
            text-align: center;
            width: 100%;
        }
        .printTable{
            width: 100%;
            border-collapse:collapse;
            border:none;
        }
        .printTable td, .printTable th {
            height: 45px;
            border:solid #000 1px;
            text-align: left;
            padding-left: 15px;
        }

        span {
            width: 100%;
            text-align: center;
            color: #0b0b0b;
            font-family: 黑体;
            font-size: 14px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div id="main">
    <div style="text-align: center;float: right">
        <input class='search' onclick="doPrint()"
               type='button' value='打&nbsp;印&nbsp;报&nbsp;销&nbsp;单'/>&#12288;
    </div>
    <div class="printDiv">
        <div style="width: 90%;margin: 0 auto;margin-top: 30px;">
            <div style="text-align: center;">
                <h1 style="font-size: xx-large; font-weight: bold; font-family: 仿宋">支 出 审 批 单</h1>
            </div>

            <table class="xllist" style="text-align: center;">
                <tr>
                    <td width="10%">年度</td>
                    <td width="10%">姓名</td>
                    <td width="20%">事由</td>
                    <td width="12%">科教科</td>
                    <td width="12%">科教科科长</td>
                    <td width="12%">分管院长</td>
                    <td width="12%">财务科长</td>
                    <td width="12%">院长</td>
                </tr>
                <tr>
                    <td>${fn:substring(fundForm.createTime, 0, 4)}</td>
                    <td>${fundForm.fundOperator}</td>
                    <td>报销</td>
                    <td>${sciName}</td>
                    <td>${chiefName}</td>
                    <td>${deanName}</td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>
    </div>

</div>
</body>
</html>