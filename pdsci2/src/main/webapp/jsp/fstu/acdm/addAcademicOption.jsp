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
    <style type="text/css">
        #myForm div{margin-top:5px;}
        input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        $(function(){
            <c:if test="${empty param.academicFlow}"><%-- 新增 --%>
                $('#applyTime').val(new Date().format("yyyy-MM-dd HH:mm:ss"));
                $('#applyYear').val(new Date().format("yyyy"));
                $(".food").each(function(){
                    $(this).closest("div").find("input").eq(0).attr("checked",true);
                });
            </c:if>
            $(".budget").bind("change",function(){
                var budget = 0;
                $(".budget").each(function(){
                    budget += Number($(this).val());
                });
                $('#sumBudget').val(budget)
            });
        });
        function checkYear(obj){
            var dates = $(':text',$(obj).closest("tr"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var academicTypeText = $("#academicTypeId option:selected").text();
            $("#academicTypeName").val(academicTypeText);
            var provinceText = $("#placeProvinceId option:selected").text();
            $("#placeProvince").val(provinceText);
            var cityText = $("#placeCityId option:selected").text();
            $("#placeCity").val(cityText);

            var academic = $("#myForm").serializeJson();
            var fileTr = $("#projFileTb").children();
            var fileDatas = [];
            $.each(fileTr, function (i, n) {
                var fileFlow = $(n).find("input[name='fileFlow']").val();
                var fileRemark = $(n).find("input[name='fileRemark']").val();
                var pubFlie = {
                    "fileFlow": fileFlow,
                    "fileRemark": fileRemark
                };
                fileDatas.push(pubFlie);
            });
            var requestData = {'fileList': fileDatas, 'academicActivity': academic};
            $("#jsondata").val(JSON.stringify(requestData));
            var url = "<s:url value='/fstu/academic/saveAcademicAndFile'/>";
            jboxSubmit($("#myForm"), url, function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
        function selectFood(obj){
            var feeInp = $(obj).closest("div").find(".food").eq(0);
            if($(obj).val() == "Y"){
                $(feeInp).attr("disabled",false);
            }else{
                var fee = Number($('#sumBudget').val())-Number($(feeInp).val())
                $('#sumBudget').val(fee);
                $(feeInp).val("");
                $(feeInp).attr("disabled",true);
            }
        }
        //上传出差凭证
        function checkFile(obj){
            var array = new Array('gif', 'jpeg', 'png', 'jpg', 'pdf');  //可以上传的文件类型
            if (obj.value == '') {
                jboxTip("让选择要上传的文件！");
                return false;
            } else {
                var fileContentType = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用
                var isExists = false;
                for (var i in array) {
                    if (fileContentType.toLowerCase() == array[i].toLowerCase()) {
                        isExists = true;
                        break;
                    }
                }
                if (isExists == false) {
                    obj.value = null;
                    jboxTip("上传文件类型不正确！");
                    return false;
                }
            }
        }
        function reCheck(obj) {
            $(obj).hide();
            $("#down").hide();
            $("#file").show();
        }
        Date.prototype.format = function(pattern) {
            /*初始化返回值字符串*/
            var returnValue = pattern;
            /*正则式pattern类型对象定义*/
            var format = {
                "y+": this.getFullYear(),
                "M+": this.getMonth()+1,
                "d+": this.getDate(),
                "H+": this.getHours(),
                "m+": this.getMinutes(),
                "s+": this.getSeconds(),
                "S": this.getMilliseconds(),
                "h+": (this.getHours()%12),
                "a": (this.getHours()/12) <= 1? "AM":"PM"
            };
            /*遍历正则式pattern类型对象构建returnValue对象*/
            for(var key in format) {
                var regExp = new RegExp("("+key+")");
                if(regExp.test(returnValue)) {
                    var zero = "";
                    for(var i = 0; i < RegExp.$1.length; i++) { zero += "0"; }
                    var replacement = RegExp.$1.length == 1? format[key]:(zero+format[key]).substring(((""+format[key]).length));
                    returnValue = returnValue.replace(RegExp.$1, replacement);
                }
            }
            return returnValue;
        };

        function addFile(tb) {
            if ($("#projFileTb tr").length > 9) {
                jboxTip("附件总数不能超过10个！");
                return false;
            }

            var html = '<tr>' +
                    '<td><input type="file" id="file" onchange="checkFile(this)" name="files" class="validate[required]"/></td>' +
                    '<td><input class="validate[required,maxSize[100]] xltext" style="width: 97%;" name="fileRemark" type="text"/></td>' +
                    '<td><a class="reCheck" href="javascript:void(0);" onclick="delTr(this);">[删除]</a></td>' +
                    '</tr>';
            $('#' + tb).append(html);
        }

        function delTr(tb) {
            jboxConfirm("确认删除？", function () {
                $(tb).parent('td').parent("tr").remove();
            });
        }

        function delFile(obj, fileFlow) {
            var url = '<s:url value="/pub/file/delFileByFlow?fileFlow="/>' + fileFlow;
            jboxConfirm("确认删除？", function () {
                jboxGet(url, null, function () {
                    var tr = $(obj).parent("td").parent("tr");
                    tr.remove();
                }, null, true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
        <form id="myForm">
            <input id="jsondata" type="hidden" name="jsondata" value=""/>
            <table class="basic" style="width: 100%;">
                <tr class="bs_name" style="height: 26px">
                    <td style="text-align:center" colspan="4">学术申请表</td>
                </tr>
                <tr class="bs_name" style="height: 26px">
                    <td style="text-align:left" colspan="4">基本信息</td>
                </tr>
                <tr>
                    <th style="width: 20%;">申请人：</th>
                    <td >
                        <input type="text" name="applyUserName" value="${user.userName}" readonly="readonly"/>
                        <input type="hidden" name="academicFlow" value="${acActivity.academicFlow}"/>
                        <input type="hidden" name="applyUserFlow" value="${user.userFlow}"/>
                    </td>
                    <th style="width: 20%;">申请时间：</th>
                    <td >
                        <input type="text" id="applyTime" name="applyTime" value="${acActivity.applyTime}" readonly="readonly"/>
                        <input type="hidden" id="applyYear" name="applyYear"/>
                    </td>
                </tr>
               <tr>
                   <th style="width: 20%;">申请人部门：</th>
                   <td >
                       <input type="text" name="applyDeptName" value="${user.deptName}" readonly="readonly"/>
                       <input type="hidden" name="applyDeptFlow" value="${user.deptFlow}"/>
                       <input type="hidden" name="applyOrgFlow" value="${user.orgFlow}"/>
                       <input type="hidden" name="applyOrgName" value="${user.orgName}"/>
                   </td>
                   <th style="width: 20%;"><span style="color:red;">*</span>学术名称：</th>
                   <td >
                       <input type="text" value="${acActivity.academicName}" name="academicName" class="validate[required]">
                   </td>
               </tr>

                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>学术类型：</th>
                    <td >
                        <select id="academicTypeId" name="academicTypeId" style="width:137px;" class="validate[required]">
                            <option/>
                            <c:forEach items="${dictTypeEnumAcademicTypeList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${dict.dictId eq acActivity.academicTypeId}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="academicTypeName" name="academicTypeName">
                    </td>
                    <th style="width: 20%;"><span style="color:red;padding-left:20px;">*</span>学术天数：</th>
                    <td >
                        <input type="text" class="validate[required,custom[number]]" name="takeDay" value="${acActivity.takeDay}">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color: red;padding-left:21px;">*</span>学术地点：</th>
                    <td colspan="3">
                        <div id="native">
                            <select id="placeProvinceId" name="placeProvinceId" style="width:137px;" class="province validate[required,custom[number]]" data-value="${acActivity.placeProvinceId}" data-first-title="选择省"></select>
                            <select id="placeCityId" name="placeCityId" style="width:137px;" class="city validate[required]" data-value="${acActivity.placeCityId}" data-first-title="选择市"></select>
                            <input type="text" name="placeArea" value="${acActivity.placeArea}" placeholder="具体地点" class="validate[required]">

                            <input id="placeProvince" name="placeProvince" type="hidden">
                            <input id="placeCity" name="placeCity" type="hidden">
                            <script type="text/javascript">
                                // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                                $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                                $.cxSelect.defaults.nodata = "none";
                                $("#native").cxSelect({
                                    selects: ["province", "city"],
                                    nodata: "none",
                                    firstValue: ""
                                });
                            </script>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color: red;padding-left:21px;">*</span>学术开始时间：</th>
                    <td >
                        <input type="text" readonly="readonly" class="validate[required]" name="beginTime" value="${acActivity.beginTime}" onchange="checkYear(this)" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                    </td>
                    <th style="width: 20%;"><span style="color: red;padding-left:21px;">*</span>学术结束时间：</th>
                    <td >
                        <input type="text" readonly="readonly" class="validate[required]" name="endTime" value="${acActivity.endTime}" onchange="checkYear(this)" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color: red;padding-left:21px;">*</span>学术内容：</th>
                    <td colspan="3">
                        <textarea type="text" name="academicContent" style="width:98%;height:80px;" placeholder="50-300字符" class="validate[required,minSize[50],maxSize[300]]">${acActivity.academicContent}</textarea>
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">主办单位：</th>
                    <td colspan="3">
                        <input type="text" name="holdUnit" maxlength="50" value="${acActivity.holdUnit}">
                    </td>
                </tr>
            </table>
                <table class="basic" style="width:100%;">

                    <tr class="bs_name" style="height: 26px">
                        <td style="text-align:left" colspan="4">备注信息：</td>
                    </tr>
                    <tr>
                        <th width="20%">备注信息：</th>
                        <td colspan="3">
                            <textarea type="text" name="remark" style="width:98%;height:80px;" placeholder="支持250字符" class="validate[maxSize[250]]">${acActivity.remark}</textarea>
                        </td>
                    </tr>

                </table>

            <table class="basic" style="width:100%;">
                <thead>
                <tr>
                    <th colspan="4" class="bs_name">附件上传：上传出差申请凭证，仅能上传图片格式或pdf，需要有主任或部门负责人签字
                        <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img
                                            title="新增" src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                            style="cursor: pointer;" onclick="addFile('projFileTb')"/></a></span>
                        </c:if>
                    </th>
                </tr>
                <tr>
                    <td width="30%" style="font-weight:bold;">附件名称</td>
                    <td width="40%" style="font-weight:bold;">附件内容</td>
                    <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                        <td width="20%" style="font-weight:bold;">附件操作</td>
                    </c:if>
                </tr>
                </thead>
                <tbody id="projFileTb">
                <c:forEach var="file" items="${fileList}" varStatus="status">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${not empty file.fileFlow}">
                                    <a id="down"
                                       href='<s:url value="/fstu/book/fileDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                    <input type="file" id="file" name="files" style="display: none;" onchange="checkFile(this)"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="file" id="file" name="files" onchange="checkFile(this)"/>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                        </td>
                        <td>
                            <input class="validate[required,maxSize[100]] xltext" style="width: 97%;"
                                   name="fileRemark" type="text" value="${file.fileRemark}"/>
                        </td>
                        <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                            <td>
                                <a class="reCheck" href="javascript:void(0);" onclick="reCheck(this);">[重新选择文件]</a>&#12288;
                                <a class="reCheck" href="javascript:void(0);" onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

                <table class="basic" style="width:100%;">

                    <tr class="bs_name" style="height: 26px">
                        <td style="text-align:left" colspan="4">预计费用：</td>
                    </tr>
                    <tr>
                        <th width="20%">会议费：</th>
                        <td >
                            <input type="text" class="budget validate[custom[number]]" name="meetingBudget" value="${acActivity.meetingBudget}">
                        </td>
                        <th width="20%">资料费：</th>
                        <td >
                            <input type="text" class="budget validate[custom[number]]" name="materialBudget" value="${acActivity.materialBudget}">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%">交通费：</th>
                        <td >
                            <input type="text" class="budget validate[custom[number]]" name="trafficBudget" value="${acActivity.trafficBudget}">
                        </td>
                        <th width="20%">服装费：</th>
                        <td >
                            <input type="text" class="budget validate[custom[number]]" name="costumeBudget" value="${acActivity.costumeBudget}">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%">是否含食宿：</th>
                        <td >
                            <input type="radio" name="foodBudgetWhether" value="Y" <c:if test="${acActivity.foodBudgetWhether eq 'Y'}">checked="checked"</c:if> onclick="selectFood(this)">含&#12288;&#12288;&#12288;&#12288;&#12288;
                            <input type="radio" name="foodBudgetWhether" value="N" <c:if test="${acActivity.foodBudgetWhether eq 'N'}">checked="checked"</c:if> onclick="selectFood(this)">不含
                        </td>
                        <th width="20%">食宿费：</th>
                        <td >
                            <input type="text" class="food budget validate[custom[number]]" name="foodBudget" value="${acActivity.foodBudget}">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%"> 补贴费：</th>
                        <td >
                            <input type="text" class="budget validate[custom[number]]" name="subsidyBudget" value="${acActivity.subsidyBudget}">
                        </td>
                        <th width="20%"> 其他费：</th>
                        <td >
                            <input type="text" class="budget validate[custom[number]]" name="otherBudget" value="${acActivity.otherBudget}">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%">  预计费用合计：</th>
                        <td colspan="3">
                            <input type="text" id="sumBudget" name="sumBudget" value="${acActivity.sumBudget}" readonly="readonly">
                        </td>
                    </tr>
                </table>

                <table class="basic" style="width:100%;">
                    <c:if test="${not empty param.academicFlow && acActivity.auditStatusId ne 'Add'}"><%-- 学术申请送审后 可见 --%>
                    <tr>
                        <th width="20%"> 科室主任审核意见：</th>
                        <td colspan="3">
                            <input type="text" style="width:98%" name="headerAdvice" value="${acActivity.headerAdvice}" size="100" readonly="readonly">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%">继教部审核意见：</th>
                        <td colspan="3">
                            <input type="text"style="width:98%" name="adminAdvice" value="${acActivity.adminAdvice}" size="100" readonly="readonly">
                        </td>
                    </tr>
                    </c:if>
                </table>


            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>

        </div>
    </div>
</div>
</body>
</html>