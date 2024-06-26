<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>系统出错啦！</title>
    <script type="text/javascript">
    </script>
    <STYLE type=text/css>
        A:link {
            COLOR: #555555;
            TEXT-DECORATION: none
        }

        A:visited {
            COLOR: #555555;
            TEXT-DECORATION: none
        }

        A:active {
            COLOR: #555555;
            TEXT-DECORATION: none
        }

        A:hover {
            COLOR: #6f9822;
            TEXT-DECORATION: none
        }

        .text {
            FONT-SIZE: 12px;
            COLOR: #555555;
            FONT-FAMILY: "";
            TEXT-DECORATION: none
        }

        .STYLE1 {
            font-size: 13px
        }

        .STYLE2 {
            font-size: 12px
        }

        .STYLE3 {
            font-size: 11px
        }
    </STYLE>
</head>

<body>
　
<table style="width: 100%;" align="center" cellspacing="0" cellpadding="0" border="0">

    <tbody>
    <tr>
        <td valign="center" align="center">
            <table style="min-width: 500px;max-width: 500px;" align="center" cellspacing="0" cellpadding="0" border="0">
                <tbody>
                <tr>
                    <td  style="max-width: 17px;min-width: 17px;max-height: 17px;min-height: 17px;"><img src="<s:url value='/jsp/inx/gzykdx/error/404/co_01.gif'/>"  style="max-width: 17px;min-width: 17px;max-height: 17px;min-height: 17px;"></td>
                    <td  style="max-width: 466px;min-width: 466px;max-height: 17px;min-height: 17px;" background="<s:url value='/jsp/inx/gzykdx/error/404/bg01.gif'/>"></td>
                    <td  style="max-width: 17px;min-width: 17px;max-height: 17px;min-height: 17px;"><img src="<s:url value='/jsp/inx/gzykdx/error/404/co_02.gif'/>"  style="max-width: 17px;min-width: 17px;max-height: 17px;min-height: 17px;"></td>
                </tr>
                <tr>
                    <td  style="max-width: 17px;min-width: 17px;max-height: 466px;min-height: 466px;" background="<s:url value='/jsp/inx/gzykdx/error/404/bg02.gif'/>"style="background-repeat:repeat-y; "></td>
                    <td  style="max-width: 466px;min-width: 466px;max-height: 466px;min-height: 466px;">
                        <table cellSpacing=0 cellPadding=10 width="100%" height="100%" align=center border=0>
                            <tr>
                                <td style="text-align: center">
                                    <strong><font style="font-size: 25px;">系统出错！！</font></strong>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table class=text cellSpacing=0 cellPadding=0 width="100%" border=0>
                                       <tr>
                                           <td>
                                               <strong><font style="font-size: 14px;color: red;">出错原因：</font></strong>
                                           </td>
                                       </tr>
                                       <tr>
                                           <td>
                                               <strong>${loginErrorMessage}</strong>
                                           </td>
                                       </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td  style="max-width: 17px;min-width: 17px;max-height: 466px;min-height: 466px;" background="<s:url value='/jsp/inx/gzykdx/error/404/bg03.gif'/>"></td>
                </tr>
                <tr>
                    <td  style="max-width: 17px;min-width: 17px;max-height: 17px;min-height: 17px;"><img src="<s:url value='/jsp/inx/gzykdx/error/404/co_03.gif'/>"  style="max-width: 17px;min-width: 17px;max-height: 17px;min-height: 17px;"></td>
                    <td  style="max-width: 466px;min-width: 466px;max-height: 17px;min-height: 17px;" background="<s:url value='/jsp/inx/gzykdx/error/404/bg04.gif'/>"></td>
                    <td  style="max-width: 17px;min-width: 17px;max-height: 17px;min-height: 17px;"><img src="<s:url value='/jsp/inx/gzykdx/error/404/co_04.gif'/>"  style="max-width: 17px;min-width: 17px;max-height: 17px;min-height: 17px;"></td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
