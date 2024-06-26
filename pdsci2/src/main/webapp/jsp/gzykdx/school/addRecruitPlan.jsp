<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            $("#allNum").val(accAdd($("#academicNum").val(),$("#specializedNum").val()));
            //发布后，修改总指标验证是否小于已分配指标数
            <c:if test="${!empty param.rargetFlow && recTarget.isReport eq 'Y'}">
                //同步
                var boolean = true;
                jboxPostAsync("<s:url value='/gzykdx/school/searchUseTargetSum'/>", $("#myForm").serialize(), function (resp) {
                    var academicNum = resp["ACADEMICNUM"];
                    var specializedNum = resp["SPECIALIZEDNUM"];
                    if(!isNaN(specializedNum) && !isNaN(academicNum)){
                        if(Number($("#academicNum").val().trim()) < Number(academicNum)){
                            boolean = false;
                            jboxTip("学术学位招生数已使用"+academicNum);
                        }
                        if(Number($("#specializedNum").val().trim()) < Number(specializedNum)){
                            boolean = false;
                            jboxTip("专业学位招生数已使用"+specializedNum);
                        }
                    }
                }, null, false);
                if(!boolean){
                    return;
                }
            </c:if>
            realSaveOpt();
        }
        function realSaveOpt(){
            jboxPost("<s:url value='/gzykdx/school/saveRecruitPlan'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            },null,true);
        }
        function accAdd(arg1,arg2){
            var r1,r2,m;
            try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
            try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
            m=Math.pow(10,Math.max(r1,r2));
            return (arg1*m+arg2*m)/m
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="rargetFlow" value="${param.rargetFlow}">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;width:40%;">年份：</td>
                    <td style="width:60%;">
                        <input type="text" class="validate[required]" ${!empty param.rargetFlow?"disabled":"readonly"} onClick="WdatePicker({dateFmt:'yyyy'})" name="recruitYear" value="${recTarget.recruitYear}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">学术学位招生数：</td>
                    <td>
                        <input type="text" class="validate[required,custom[number]]" id="academicNum" name="academicNum" value="${recTarget.academicNum}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">专业学位招生数：</td>
                    <td>
                        <input type="text" class="validate[required,custom[number]]" id="specializedNum" name="specializedNum" value="${recTarget.specializedNum}"/>
                        <input type="hidden" name="allNum" id="allNum">
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <c:if test="${param.flag ne 'view'}"><input type="button" class="search" onclick="save();" value="保&#12288;存"/></c:if>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>