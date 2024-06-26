<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
    </jsp:include>
    <style type="text/css">
        input[type='text']{width:100px;border-width:0px;}
        input[type='text']{border-bottom:1px solid #e3e3e3;}
    </style>
    <script type="text/javascript" src="<s:url value='/js/jquery.jqprint-0.3.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        //获取字符串长度（汉字算两个字符，字母数字算一个）
        function getByteLen(val) {
            var len = 0;
            if(val!=null&&val!=undefined&&val!='undefined'){
                for (var i = 0; i < val.length; i++) {
                    var a = val.charAt(i);
                    if (a.match(/[^\x00-\xff]/ig) != null) {
                        len += 2;
                    }
                    else {
                        len += 1;
                    }
                }
            }
            return len;
        }
        function search(){
            $("#searchForm").submit();
        }
        function save(){
            if (!$("#saveForm").validationEngine("validate")) {
                return;
            }
            if(getByteLen($("textarea[name='abroadApply.goAbroadDesc']").val())>500){
                jboxTip("出国任务栏描述不能超过250字！");
                return;
            }
            count = 0;
            $(".famName").each(function(i){
                if($.trim($(this).val()) != "" && $.trim($(".famAge").val()) != "" && $.trim($(".famRelation").val()) != "" && $.trim($(".famPolitics").val()) != ""
                        && $.trim($(".famUnit").val()) != ""){
                    count ++;
                }
            });
            if(count == 0){
                jboxTip("家庭主要成员情况至少填写一条完整信息");
                return;
            }
            jboxPost("<s:url value='/gyxjgl/abroadApply/saveSheet'/>", $("#saveForm").serialize(), function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames["mainIframe"].window.toPage(1);
                    jboxClose();
                }
            });
        }
        function uploadImage() {
            $.ajaxFileUpload({
                url: "<s:url value='/gyxjgl/user/uploadImage'/>",
                secureuri: false,
                fileElementId: 'imageFile',
                dataType: 'json',
                success: function (data, status) {
                    if (data.indexOf("success") == -1) {
                        jboxTip(data);
                    } else {
                        jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
                        var arr = [];
                        arr = data.split(":");
                        var url = "${sysCfgMap['upload_base_url']}/" + arr[1];
                        $("#showImg").attr("src", url);
                        $("#img").val(arr[1]);
                    }
                },
                error: function (data, status, e) {
                    jboxTip('${GlobalConstant.UPLOAD_FAIL}');
                },
                complete: function () {
                    $("#imageFile").val("");
                }
            });
        }
        function printOpt(){
            jboxTip("正在准备打印…");
            $("#printDivIframe").removeAttr("hidden");
            setTimeout(function(){
                $("#printDivIframe").jqprint({
                    debug: false,
                    importCSS: true,
                    printContainer: true,
                    operaSupport: false
                });
                $("#printDivIframe").attr("hidden","hidden");
                jboxEndLoading();
                return false;
            },2000);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm">
            <div style="margin:20px 0px 10px 20px;">
                <c:if test="${roleFlag eq 'student'}">
                    <input type="button" class="search" onclick="save();" value="保&#12288;存" />
                </c:if>
                <input type="button" class="search" onclick="printOpt();" value="打&#12288;印" />
            </div>
        </form>
        <form id="saveForm">
            <input type="hidden" name="roleFlag" value="${roleFlag}">
            <input type="hidden" name="formType" value="${formType}"/>
            <c:if test="${roleFlag eq 'student'}">
                <input type="hidden" name="abroadApply.recordFlow" value="${abroadApply.recordFlow}">
            </c:if>
            <div style="text-align:center;font-size:30px;font-weight:500;">
                出国人员备案登记
            </div>
            <div style="min-width:850px;">
                <table class="basic" style="width:100%;">
                    <tr>
                        <th style="min-width:60px;">姓名</th>
                        <td style="min-width:120px;">${abroadApply.userName}</td>
                        <th style="min-width:60px;">性别</th>
                        <td style="min-width:80px;">${abroadApply.sexName}</td>
                        <th style="min-width:80px;">出生年月</th>
                        <td style="min-width:100px;">${abroadApply.birthDate}</td>
                        <th style="min-width:80px;">政治面貌</th>
                        <td style="min-width:100px;"><input type="text" name="abroadApply.politicalStatus" value="${abroadApply.politicalStatus}"/></td>
                    </tr>
                    <tr>
                        <th colspan="2">工作单位及职务、是否<br/>为涉密人员及涉密等级</th>
                        <td colspan="4">
                            <input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="abroadApply.postDesc" value="${abroadApply.postDesc}" placeholder="南方医科大学XXXX单位XXX职务，非涉密/重要核密/核心涉密人员" style="width: 90%;" maxlength="100"/>
                        </td>
                        <th>健康状况</th>
                        <td>
                            <input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="abroadApply.healthySituation" value="${abroadApply.healthySituation}" maxlength="25"/>
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="6">家庭主要<br/>成员情况</th>
                        <th style="text-align:center;">称谓</th>
                        <th style="text-align:center;">姓名</th>
                        <th style="text-align:center;">年龄</th>
                        <th style="text-align:center;">政治面貌</th>
                        <th style="text-align:center;" colspan="3">工作单位、职务及居住地（是否取得<br/>外国国籍、境外长期或永久居留权）</th>
                    </tr>
                    <c:set var="familyCount" value="0"/>
                    <c:forEach items="${familyFormList}" var="fam" varStatus="i">
                        <c:set var="familyCount" value="${familyCount+1}" />
                        <tr>
                            <td><input type="text" class="famRelation" name="familyFormList[${i.index}].relation" value="${fam.relation}"/></td>
                            <td><input type="text" class="famName" name="familyFormList[${i.index}].name" value="${fam.name}"/></td>
                            <td><input type="text" class="famAge" name="familyFormList[${i.index}].age" value="${fam.age}"/></td>
                            <td><input type="text" class="famPolitics" name="familyFormList[${i.index}].politics" value="${fam.politics}"/></td>
                            <td colspan="3"><input type="text" class="famUnit" name="familyFormList[${i.index}].workUnit" style="width:300px;" value="${fam.workUnit}" placeholder="XXX单位XX职务，居住XX城市，是/未取得外国国籍、境外长期或永久居留权"/></td>
                        </tr>
                    </c:forEach>
                    <c:forEach begin="${familyCount}" end="4" var="index">
                        <tr>
                            <td><input type="text" class="famRelation" name="familyFormList[${index}].relation"/></td>
                            <td><input type="text" class="famName" name="familyFormList[${index}].name"/></td>
                            <td><input type="text" class="famAge" name="familyFormList[${index}].age"/></td>
                            <td><input type="text" class="famPolitics" name="familyFormList[${index}].politics"/></td>
                            <td colspan="3"><input type="text" class="famUnit" name="familyFormList[${index}].workUnit" style="width:300px;"/></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="2">出国任务、所赴国家<br/>（地区）及停留时间</th>
                        <td colspan="6">
                            <textarea class="${roleFlag eq 'global'?'':'validate[required]'}" name="abroadApply.goAbroadDesc" style="width: 600px;height: 55px;" ${roleFlag eq 'student'?'':'readonly'} placeholder="最多250字">${abroadApply.goAbroadDesc}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>二级单位<br/>意见</th>
                        <td colspan="7">
                            <br/><br/>
                            <span style="padding-left: 550px;">负责人签名：&emsp;&emsp;&emsp;&emsp;&emsp;单位盖章：&emsp;&emsp;&emsp;&emsp;</span><br/>
                            <span style="padding-left: 550px;">年&emsp;&emsp;月&emsp;&emsp;日&emsp;&emsp;&emsp;&emsp;年&emsp;&emsp;月&emsp;&emsp;日&emsp;</span>
                        </td>
                    </tr>
                    <tr>
                        <th>研究生院<br/>意见</th>
                        <td colspan="7">
                            <br/><br/>
                            <span style="padding-left: 550px;">负责人签名：&emsp;&emsp;&emsp;&emsp;&emsp;单位盖章：&emsp;&emsp;&emsp;&emsp;</span><br/>
                            <span style="padding-left: 550px;">年&emsp;&emsp;月&emsp;&emsp;日&emsp;&emsp;&emsp;&emsp;年&emsp;&emsp;月&emsp;&emsp;日&emsp;</span>
                        </td>
                    </tr>
                </table>
                <font style="font-weight:bold;">注：家庭成员最多可填写五名。</font>
            </div>
        </form>
    </div>
</div>
<div hidden="hidden" id="printDivIframe" name="printDivIframe">
    <div style="text-align:center;font-size:20px;font-weight:500;">
        出国人员备案登记
    </div>
    <div>
        <table class="basic" style="width:100%;font-size: 11px;">
            <tr>
                <th style="width:50px;">姓名</th>
                <td style="width:80px;">${abroadApply.userName}</td>
                <th style="width:50px;">性别</th>
                <td style="width:50px;">${abroadApply.sexName}</td>
                <th style="width:50px;">出生年月</th>
                <td style="width:60px;">${abroadApply.birthDate}</td>
                <th style="width:50px;">政治面貌</th>
                <td style="width:50px;">${abroadApply.politicalStatus}</td>
            </tr>
            <tr>
                <th colspan="2">工作单位及职务、是否<br/>为涉密人员及涉密等级</th>
                <td colspan="4">${abroadApply.postDesc}</td>
                <th>健康状况</th>
                <td>${abroadApply.healthySituation}</td>
            </tr>
            <tr>
                <th rowspan="6">家庭主要<br/>成员情况</th>
                <th style="text-align:center;">称谓</th>
                <th style="text-align:center;">姓名</th>
                <th style="text-align:center;">年龄</th>
                <th style="text-align:center;">政治面貌</th>
                <th style="text-align:center;" colspan="3">工作单位、职务及居住地（是否取得<br/>外国国籍、境外长期或永久居留权）</th>
            </tr>
            <c:set var="familyCount" value="0"/>
            <c:forEach items="${familyFormList}" var="fam" varStatus="i">
                <c:set var="familyCount" value="${familyCount+1}" />
                <tr>
                    <td>${fam.relation}</td>
                    <td>${fam.name}</td>
                    <td>${fam.age}</td>
                    <td>${fam.politics}</td>
                    <td colspan="3">${fam.workUnit}</td>
                </tr>
            </c:forEach>
            <c:forEach begin="${familyCount}" end="4" var="index">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td colspan="3"></td>
                </tr>
            </c:forEach>
            <tr>
                <th colspan="2">出国任务、所赴国家<br/>（地区）及停留时间</th>
                <td colspan="6">
                    ${abroadApply.goAbroadDesc}<br/><br/>
                </td>
            </tr>
            <tr>
                <th>二级单位<br/>意见</th>
                <td colspan="7">
                    <br/><br/>
                    <span style="padding-left: 60%;">负责人签名：&emsp;&emsp;&emsp;&emsp;单位盖章：&emsp;&emsp;&emsp;</span><br/>
                    <span style="padding-left: 60%;">年&emsp;&emsp;月&emsp;&emsp;日&emsp;&emsp;&emsp;年&emsp;&emsp;月&emsp;&emsp;日</span>
                </td>
            </tr>
            <tr>
                <th>研究生院<br/>意见</th>
                <td colspan="7">
                    <br/><br/>
                    <span style="padding-left: 60%;">负责人签名：&emsp;&emsp;&emsp;&emsp;单位盖章：&emsp;&emsp;&emsp;</span><br/>
                    <span style="padding-left: 60%;">年&emsp;&emsp;月&emsp;&emsp;日&emsp;&emsp;&emsp;年&emsp;&emsp;月&emsp;&emsp;日</span>
                </td>
            </tr>
        </table>
        <font style="font-weight:bold;">注：家庭成员最多可填写五名。</font>
    </div>
</div>
</body>
</html>