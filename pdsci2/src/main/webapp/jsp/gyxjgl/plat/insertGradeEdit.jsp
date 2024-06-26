<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function bindOpt(obj){
            if($(obj).val()==""){
                $(obj).next().val("");
            }else{
                $(obj).next().val($(obj).find("option:selected").text());
            }
        }
        function addScore(){
            var inx = $("#gradeTB tr").length;
            var htl = "<tr><td style='width:32%;padding-right:10px;'><input type='hidden' name='gradeList["+inx+"].recordFlow'><input type='text' style='width:98%;' name='gradeList["+inx+"].courseName' class='validate[required]'></td>" +
                    "<td style='width:35%;padding-right:10px;'><input type='text' style='width:98%;' name='gradeList["+inx+"].courseNameEn'></td><td style='width:10%;'><input type='text' style='width:40px;' name='gradeList["+inx+"].courseHour'></td>" +
                    "<td style='width:10%;'><input type='text' style='width:40px;' name='gradeList["+inx+"].courseCredit'></td><td style='width:13%;'><input type='text' style='width:40px;' name='gradeList["+inx+"].gradeScore' class='validate[required,custom[number]]'>" +
                    "&nbsp;<img style='cursor:pointer;padding-right:10px;' src=\"<s:url value='/jsp/inx/lcjn/images/reduce.png'/>\" onclick='delScore(this)' title='删除' /></td></tr>";
            $("#gradeTB").append(htl);
        }
        function delScore(obj){
            $(obj).parents("tr").siblings("tr").each(function(i){
                $(this).find("input").each(function(){
                    var name = $(this).attr("name");
                    var index = name.substring(name.indexOf("[")+1,name.indexOf("]"));
                    $(this).attr("name",name.replace(index,i));
                })
            });
            var recordFlow = $(obj).parents("tr").attr("class");
            if(recordFlow != "" && recordFlow != undefined && recordFlow != "undefined"){
                if($("#delFlow").val()==""){
                    $("#delFlow").val(recordFlow);
                }else{
                    $("#delFlow").val($("#delFlow").val()+","+recordFlow);
                }
            }
            $(obj).parents("tr").remove();
        }
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            if($("#gradeTB tr").length == 0){
                jboxTip("成绩单列表不能为空！");
                return;
            }
            jboxPost("<s:url value='/gyxjgl/user/saveInsertGrade'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
        function printFlag(){
            var url = '<s:url value="/gyxjgl/user/printInsertGrade"/>?stuNo=${baseInfo.stuNo}';
            jboxStartLoading();
            jboxPost(url, null, function(data) {
                $("#printDiv").html(data);
                setTimeout(function(){
                    var newstr = $("#printDiv").html();
                    var oldstr = document.body.innerHTML;
                    document.body.innerHTML =newstr;
                    window.print();
                    document.body.innerHTML = oldstr;
                    jboxEndLoading();
                    return false;
                },3000);
            },null,false);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm" method="post">
            <table class="basic" style="width:100%;">
                <tr>
                    <td style="width:25%">学&#12288;&#12288;号：
                        <input type="text" style="width:90px;" name="stuNo" class="validate[required]" ${!empty param.stuNo?"readonly='readonly'":""} value="${baseInfo.stuNo}">
                    </td>
                    <td style="width:25%">性&#12288;&#12288;别：
                        <select style="width:95px" name="sexEn" class="inputText" onchange="bindOpt(this)">
                            <option/>
                            <option value="Male" ${baseInfo.sexEn eq 'Male'?'selected':''}>男</option>
                            <option value="Female" ${baseInfo.sexEn eq 'Female'?'selected':''}>女</option>
                        </select>
                        <input type="hidden" name="sex" value="${baseInfo.sex}"/>
                    </td>
                    <td style="width:25%">中文姓名：
                        <input type="text" style="width:90px;" name="userName" value="${baseInfo.userName}">
                    </td>
                    <td style="width:25%">英文姓名：
                        <input type="text" style="width:90px;" name="userNameEn" value="${baseInfo.userNameEn}">
                    </td>
                </tr>
                <tr>
                    <td style="width:25%">培养类型：
                        <select style="width:95px" name="trainTypeEn" class="inputText" onchange="bindOpt(this)">
                            <option/>
                            <option value="Academic Degree" ${baseInfo.trainTypeEn eq 'Academic Degree'?'selected':''}>学术型</option>
                            <option value="Professional Degree" ${baseInfo.trainTypeEn eq 'Professional Degree'?'selected':''}>专业型</option>
                        </select>
                        <input type="hidden" name="trainType" value="${baseInfo.trainType}"/>
                    </td>
                    <td style="width:25%">学位类别：
                        <select style="width:95px" name="degreeTypeEn" class="inputText" onchange="bindOpt(this)">
                            <option/>
                            <option value="Master" ${baseInfo.degreeTypeEn eq 'Master'?'selected':''}>硕士学位</option>
                            <option value="Doctor" ${baseInfo.degreeTypeEn eq 'Doctor'?'selected':''}>博士学位</option>
                        </select>
                        <input type="hidden" name="degreeType" value="${baseInfo.degreeType}"/>
                    </td>
                    <td style="width:25%">学习形式：
                        <select style="width:95px" name="studyForm" class="inputText">
                            <option/>
                            <option value="tdxl" ${baseInfo.studyForm eq 'tdxl'?'selected':''}>同等学力</option>
                            <option value="other" ${baseInfo.studyForm eq 'other'?'selected':''}>其他</option>
                        </select>
                    </td>
                    <td style="width:25%">专&#12288;&#12288;业：
                        <input type="text" style="width:90px;" name="major" value="${baseInfo.major}">
                    </td>
                </tr>
                <tr>
                    <td colspan="4">英文专业：
                        <input type="text" style="width:280px;" name="majorEn" value="${baseInfo.userNameEn}">
                    </td>
                </tr>
            </table>
            <table class="basic" style="width:100%;">
                <tr>
                    <th colspan="5" style="text-align:right;">成绩单详情：<img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addScore();" title="添加" /></th>
                </tr>
                <tr>
                    <th style="text-align:center;">课程名称</th>
                    <th style="text-align:center;">英文名称</th>
                    <th style="text-align:center;">学时</th>
                    <th style="text-align:center;">学分</th>
                    <th style="text-align:center;">成绩
                    <c:if test="${!empty param.stuNo}"><input type="hidden" name="delFlow" id="delFlow"></c:if>
                    </th>
                </tr>
                <tbody id="gradeTB">
                    <c:if test="${empty gradeList}">
                        <tr>
                            <td style="width:32%;padding-right:10px;">
                                <input type="hidden" name="gradeList[0].recordFlow">
                                <input type="text" style="width:98%;" class="validate[required]" name="gradeList[0].courseName">
                            </td>
                            <td style="width:35%;padding-right:10px;">
                                <input type="text" style="width:98%;" name="gradeList[0].courseNameEn">
                            </td>
                            <td style="width:10%;">
                                <input type="text" style="width:40px;" name="gradeList[0].courseHour">
                            </td>
                            <td style="width:10%;">
                                <input type="text" style="width:40px;" name="gradeList[0].courseCredit">
                            </td>
                            <td style="width:13%;">
                                <input type="text" style="width:40px;" class="validate[required,custom[number]]" name="gradeList[0].gradeScore">
                                <img style="cursor:pointer;padding-right:10px;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delScore(this)" title="删除" />
                            </td>
                        </tr>
                    </c:if>
                    <c:forEach items="${gradeList}" var="mat" varStatus="i">
                        <tr class="${mat.recordFlow}">
                            <td style="width:32%;padding-right:10px;">
                                <input type="hidden" name="gradeList[${i.index}].recordFlow" value="${mat.recordFlow}">
                                <input type="text" style="width:98%;" name="gradeList[${i.index}].courseName" class="validate[required]" value="${mat.courseName}">
                            </td>
                            <td style="width:35%;padding-right:10px;">
                                <input type="text" style="width:98%;" name="gradeList[${i.index}].courseNameEn" value="${mat.courseNameEn}">
                            </td>
                            <td style="width:10%;">
                                <input type="text" style="width:40px;" name="gradeList[${i.index}].courseHour" value="${mat.courseHour}">
                            </td>
                            <td style="width:10%;">
                                <input type="text" style="width:40px;" name="gradeList[${i.index}].courseCredit" value="${mat.courseCredit}">
                            </td>
                            <td style="width:13%;">
                                <input type="text" style="width:40px;" name="gradeList[${i.index}].gradeScore" class="validate[required,custom[number]]" value="${mat.gradeScore}">
                                <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delScore(this)" title="删除" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
        <div style="text-align:center;padding-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存">
            <c:if test="${!empty baseInfo.stuNo}">
                <input type="button" class="search" onclick="printFlag('${baseInfo.stuNo}');" value="打&#12288;印">
            </c:if>
        </div>
    </div>
</div>
<div id="printDiv" style="display: none;"></div>
</body>
</html>