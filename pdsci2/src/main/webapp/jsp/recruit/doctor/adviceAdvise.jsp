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
    <style>
        i{
            font-style: normal;
        }
    </style>
</head>
<body>
<div class="mainright" >
    <div class="content" id="printDiv" style="padding-bottom: 0px;">
        <div class="title1 clearfix" style="overflow: auto;min-height:300px">
            <table width="100%" class="basic"  border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td  style="font-size: 180%;text-align: center;padding-right: 10px" colspan="2">
                        <strong>录&#12288;取&#12288;通&#12288;知&#12288;书</strong>
                    </td>
                </tr>
                <tr>
                    <td  style="padding-right: 10px" colspan="2">
                        <p style="font-size: 180%;letter-spacing: 4px">亲爱的<i style="text-decoration:underline;">${user.userName}</i>(先生/女士):</p>
                        <p style="text-indent:50px;font-size: 140%;letter-spacing: 2px;line-height:40px">证件号为:<i style="text-decoration:underline;letter-spacing: normal">${user.idNo}</i>,很高兴通知您,恭喜通过${orgName}的面试考核,请于准备好<i style="text-decoration:underline;">${recruitAdmitInfo.admitDemo}</i>等相关证件,于<i style="text-decoration:underline;letter-spacing: normal">${admitDate.year}</i>年<i style="text-decoration:underline;letter-spacing: normal">${admitDate.month}</i>月<i style="text-decoration:underline;letter-spacing: normal;">${admitDate.day}</i>日<i style="text-decoration:underline;letter-spacing: normal;">${admitDate.hour}</i>时<i style="text-decoration:underline;letter-spacing: normal;">${admitDate.minute}</i>分至<i style="text-decoration:underline;">${recruitAdmitInfo.admitAddress}</i>报道。</p>
                        <div style="margin-top: 30px;font-size: 160%">
                            <div style="text-align: right"><p>${orgName}</p></div>
                            <div style="text-align: right"><p>${createDate.year}年${createDate.month}月${createDate.day}日</p></div>
                        </div>
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