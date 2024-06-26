<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>操作调剂</title>
    <jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script>
        var vacancyMap = {};
        <c:forEach items="${orgList}" var="org">
            <c:set var="orgFlow" value="${org.orgFlow}"/>
            vacancyMap['${org.orgFlow}'] = {};
            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="speDict">
                <c:set var="speId" value="${speDict.dictId}"/>
                <c:set var="k" value="${orgFlow}${speId}"/>
                <c:set var="assignNum" value="${speAssignMap[k]}"/>
                <c:set var="recruitNum" value="${recruitDocMap[k]}"/>
                vacancyMap['${orgFlow}']['${speId}'] = ${assignNum - recruitNum};
            </c:forEach>
        </c:forEach>

        function filterSpe(orgFlow){
            $('.speOption').hide();
            var vacancyNums = vacancyMap[orgFlow];
            for(var k in vacancyNums){
                var vacancyNum = vacancyNums[k];
                if(vacancyNum>0){
                    var opt = $('.speOption.'+k).show();
                    var text = opt.text();
                    var sIndex = text.indexOf('(');
                    var openTxt = text.substr(0,sIndex);
                    opt.text(openTxt+'('+vacancyNum+')')[0].dataset.vacancyNum = vacancyNum;
                }
            }
        }

        function setOrgVacancuSum(){
            for(var k in vacancyMap){
                var vacancayNums = vacancyMap[k];
                var sum = 0;
                for(var speid in vacancayNums){
                    var vacancayNum = vacancayNums[speid];
                    if(vacancayNum>0){
                        sum+=vacancayNum;
                    }
                }
                var org = $('.'+k+'Opt');
                var text = org.text();
                org.text(text+'('+sum+')')[0].dataset.vacancyNum = sum;
            }
        }

        $(function(){
            filterSpe('${param.orgFlow}');
            setOrgVacancuSum();
            var currSelSpe = $('.speOption:selected')[0];
            if(!currSelSpe.dataset.vacancyNum){
                currSelSpe.selected = false;
            }
        });

        function saveSwap(){
            var swapForm = $('#swapIndex');
            if(swapForm.validationEngine("validate")){
                top.jboxStartLoading();
                setMsg();
                jboxPost('<s:url value="/hbres/singup/saveSwap"/>',swapForm.serialize(),function(resp){
                    top.jboxEndLoading();
                    if(resp>0){
                        top.getSwapContent();
                        top.jboxTip('操作成功！');
                        jboxClose();
                    }
                },function(){
                    top.jboxEndLoading();
                },false);
            }
        }


        function setMsg(){
            var orgName = $('[name="orgFlow"] :selected').text() ||  '';
            var speName = $('.speOption:selected').text() ||  '';
            var sIndex = speName.indexOf('(');
            speName = $.trim(speName.substr(0,sIndex));
            var msg = top.getMsg(orgName,speName,false);
            $('#msg').val(msg);
        }
    </script>
</head>
<body>
    <form id="swapIndex" style="padding-left: 5%;padding-right: 5%;padding-top: 10px;">
        <input type="hidden" name="doctorFlow" value="${param.doctorFlow}"/>
        <input type="hidden" name="examResult" value="${param.examResult}"/>
        <input type="hidden" name="auditionResult" value="${recruit.auditionResult}"/>
        <input type="hidden" name="operResult" value="${recruit.operResult}"/>
        <input type="hidden" name="totleResult" value="${recruit.totleResult}"/>
        <input type="hidden" id="msg" name="msg" value=""/>

        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th colspan="2" style="font-weight: bold;text-align: left;padding-left: 10px;">调剂信息</th>
            </tr>
            <tr>
                <th style="width: 35%;text-align: right;padding-right: 10px;">
                    <span style="color: red;">*</span>培训基地：
                </th>
                <td style="text-align: left;padding-left: 10px;">
                    <select name="orgFlow" style="width: 60%;" class="select validate[required]" onchange="filterSpe(this.value);">
                        <option/>
                        <c:forEach items="${orgList}" var="org">
                            <option class="${org.orgFlow}Opt" value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th style="width: 35%;text-align: right;padding-right: 10px;">
                    <span style="color: red;">*</span>培训专业：
                </th>
                <td style="text-align: left;padding-left: 10px;">
                    <select name="speId" style="width: 60%;" class="select validate[required]">
                        <option/>
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="speDict">
                            <option class="${speDict.dictId} speOption" value="${speDict.dictId}" <c:if test="${param.speId eq speDict.dictId}">selected</c:if>>
                                ${speDict.dictName}(0)
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
    </form>
    <div align="center" style="margin-top: 10px;">
        <input class="btn_green" onclick="saveSwap();" type="button" value="保&#12288;存">
        &#12288;
        <input class="btn_green" onclick="jboxClose();" type="button" value="关&#12288;闭">
    </div>
</body>
</html>