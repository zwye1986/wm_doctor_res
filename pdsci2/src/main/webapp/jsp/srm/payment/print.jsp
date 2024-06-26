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
                    <div style="float:left; height: 20px"><span>用款部门：</span>${detailExt.proj.applyDeptName}</div>
                    <div style="float:right; margin-right: 30px;height: 20px"><span>填制日期：</span>${pdfn:transDate(detailExt.provideDateTime)}</div>
                    <table class="printTable">
                        <tr>
                            <td><span>开 支 理 由</span></td>
                            <td colspan="5">${detailExt.content}</td>
                        </tr>
                        <tr>
                            <td><span>申请支出金额</span></td>
                            <td colspan="4">${CNmoney}</td>

                            <td >￥${detailExt.money}（万元）<br/>${detailExt.realityTypeName}</td>
                        </tr>
                       <tr>
                            <td><span>审核实付金额</span></td>
                            <td colspan="4">${CNbudgetMoney}</td>
                            <td>￥${detailExt.realmoney}（万元）</td>
                        </tr>
                        <tr>
                            <td width="17%"><span>财务审核人</span></td>
                            <td width="20%"></td>
                            <td width="14%"><span>科室/课题负责人</span></td>
                            <td width="17%">${detailExt.proj.applyUserName}</td>
                            <td width="16%"><span>经办人</span></td>
                            <td width="16%">${detailExt.fundOperator}</td>
                        </tr>
                        <tr>
                            <td><span>项 目 明 细</span></td>
                            <td>${detailExt.schemeDetail.itemName}</td>
                            <td><span>预算金额</span></td>
                            <td>￥${budgetDetail.money}（万元）</td>
                            <td><span>已支出金额</span><br/>(不包含本次报销金额)</td>
                            <td>￥${reimburseMoney-detailExt.realmoney}（万元）</td>
                        </tr>
                        <tr>
                            <td><span>领 导 审 批</span></td>
                            <td colspan="5"></td>
                        </tr>
                        <tr>
                            <td rowspan="2"><span>备&#12288;&#12288;&#12288;注</span></td>
                            <td colspan="3" style="border-bottom: none;border-right: none">
                                <span>课题名称：</span>${detailExt.proj.projName}
                            </td>
                            <td colspan="2" style="border-bottom: none;border-left: none"><span>编码：</span>${detailExt.proj.projNo}</td>
                        </tr>
                        <tr>
                            <td colspan="3" style="border-top: none;border-right: none">
                                <span>科教审核人：</span>${localUserName}
                            </td>
                            <td colspan="2" style="border-top: none; border-left: none"><span>财务审核人：</span>${financeUserName}</td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>
</body>
</html>