<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript"
            src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        function createPhyAssGrade(recordFlow) {
            var gradeId = $("#gradeId").val();
            var gradeName = $("#gradeId").find("option:selected").text();
            if (gradeId==''){
                jboxTip("请选择师资等级！");
                return;
            }
            var url = "<s:url value='/jsres/phyAss/createPhyAssGrade'/>?recordFlows=" + recordFlow+"&gradeId="+gradeId+"&gradeName="+gradeName;
            jboxGet(url , null , function(resp){
                if (resp=='${GlobalConstant.OPERATE_SUCCESSED}'){
                    jboxTip(resp);
                    window.parent.toPage(1);
                    top.jboxClose();
                }else {
                    jboxTip(resp);
                }
            },null , false);

        }

        function showImg() {
            jboxOpen("<s:url value='/jsres/phyAss/showGrade?recordFlow=${recordFlow}'/>&type=show","证书预览",1800,1000);
        }


        $(function (){
            $("#aler2").css("position", "absolute");
            $("#aler2").css("left", "40px"); //距离左边距
            $("#aler2").css("top", "82px"); //距离上边距
            $("#aaa").mouseenter(function () {
                $("#aler2").show();
            });
            $("#aaa").mouseleave(function () {
                $("#aler2").hide();
            });
        })

    </script>
</head>

<body>
<div class="infoAudit">
    <div class="div_table">
        <form id="editForm" style="position: relative;height: 115px" method="post">
            <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;border: none;font-size: 14px">
                <colgroup>
                    <col width="35%"/>
                    <col width="45%"/>
                    <col width="20%"/>
                </colgroup>
                <tbody>
                <tr style="border: none">
                    <td style="border: none;text-align: right"><span style="color:red;">*&nbsp;</span>师资等级：</td>
                    <td style="border: none;text-align: left">
                        <select name="gradeId" id="gradeId" class="select" style="width: 200px;">
                            <option value="">请选择</option>
                            <option value="ordinary">普通师资</option>
                            <option value="commonly">一般师资</option>
                            <option value="other">其他师资</option>
                        </select>

                    </td>
                    <td style="border: none">
                        <img width="20px" id="aaa" style="margin-left: 15px" src="<s:url value="/img/u222.png" />"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <c:if test="${type ne 'all'}">
            <div>
                <a style="margin-right: 50px" id="viewImgLink" href="<s:url value='/jsres/phyAss/showGrade?recordFlow=${recordFlow}'/>&type=show" target="_blank">
                    <img width="35px" style="margin-left: 80%" src="<s:url value="/img/u221.png" />"/>
                </a>
            </div>
        </c:if>
        <c:if test="${type eq 'all'}">
        <div style="height: 35px">
        </div>
        </c:if>
        <div class="button" style="margin-top: -30px">
            <input type="button" class="btn_green" style="margin-right: 20px" onclick="top.jboxCloseMessager();" value="取&#12288;消"/>
            <input type="button" class="btn_green" style="margin-left: 20px" onclick="createPhyAssGrade('${recordFlow}');" value="生成证书"/>
        </div>

        <div id="aler2" style="display:none;" >
                1.一般师资：指取得主治医师专业技术职称3年及以上，能胜任普通师资职责。 <br>
                2.骨干师资：指取得普通师资资格3年以上，能胜任骨干师资职责。有住院医师规范化培训带教经历，副高以上专业技术职称。 <br>
                3.其他师资：指除普通师资、骨干师资以外的师资
        </div>
    </div>
</div>
</body>
</html>