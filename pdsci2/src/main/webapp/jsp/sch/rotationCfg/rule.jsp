<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <style type="text/css">
        table.basic th, table.basic td {
            padding: 0;
            text-align: center;
        }

        caption {
            line-height: 40px;
            text-align: left;
            font-weight: bold;
            padding-left: 10px;
            border-bottom: 1px solid #ddd;
            color: #f60;
            margin-bottom: 10px;
        }

        table.none td {
            padding-right: 5px;
            border: 0px;
            border-bottom-width: 0;
            border-left-width: 0;
            border-right-width: 0x;
            border-top-width: 0;
        }

    </style>
    <c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}"
           var="unitKey"/>
    <script type="text/javascript">
        String.prototype.cptcp = function () {
            var reg = /\./g;
            return this.replace(reg, "\\.");
        };

        function doBack() {
            var target = "<s:url value='/sch/rotationCfg/list'/>?roleFlag=${param.roleFlag}";
            location.href = target;
        }


        $(function () {
            //侧滑初始化
            $("#reqCfg").slideInit({
                width: 800,
                speed: 500,
                outClose: true,
                haveZZ: true
            });

            <c:forEach items="${schStageEnumList}" var="stage">
            $("#${stage.id}").append($(".ord${stage.id}"));
            </c:forEach>
        });
        //刷新页面
        function refresh(rotationFlow) {
            window.location.href = "<s:url value='/sch/rotationCfg/rule'/>?roleFlag=${param.roleFlag}&rotationFlow=" + rotationFlow;
        }

        //轮转方案说明
        function openDetail(rotationName, rotationFlow) {
            var url = "<s:url value='/schTwo/template/editRotation'/>?roleFlag=${param.roleFlag}&viewFlag=${GlobalConstant.FLAG_Y}&rotationFlow=" + rotationFlow;
            jboxOpen(url, rotationName, 800, 500);
        }
        //
        function removeRotationDept(recordFlow,flag)
        {
            var tbody=$("tbody[orgDeptRecordFlow='"+recordFlow+"']");
            console.log(tbody);
            if(flag=="N")
            {
                $(tbody).find("a[class='remove']").hide();
                $(tbody).find("a[class='recovery']").show();
                $(tbody).attr("isRemove","true");
                $(tbody).find("img[class='delBtn']").hide();
                $(tbody).find("img[class='addBtn']").hide();
                $(tbody).find("select").attr("disabled","disabled");
                $(tbody).find("input").attr("readonly","readonly");
                $(tbody).find("select").removeClass("validate[required]");
                $(tbody).find("input").removeClass("validate[required,custom[number]]");
            }else{
                $(tbody).attr("isRemove","false");
                $(tbody).find("a[class='remove']").show();
                $(tbody).find("a[class='recovery']").hide();
                $(tbody).find("img[class='delBtn']").show();
                $(tbody).find("img[class='addBtn']").show();
                $(tbody).find("select").removeAttr("disabled");
                $(tbody).find("input").removeAttr("readonly");
                $(tbody).find("select").addClass("validate[required]");
                $(tbody).find("input").addClass("validate[required,custom[number]]");
            }
        }
        //添加调整
        function addSchDept(tbodyId,obj,groupFlow,standardDeptId,recordFlow,isRequired) {
            var tbody=$("#" + tbodyId + "body");
            var isRemove=$(tbody).attr("isRemove");
            if(isRemove=='false') {
                var newTr = $("#templete").clone().attr("id", "");
                $(newTr).find("select[class*='schDeptFlow']").bind('change', function () {
                    checkResel(groupFlow + standardDeptId, this);
                });
                $(tbody).append(newTr);
            }
        }

        //移除调整
        function removeCfg(obj) {
            var tbody=$(obj).parent().parent().parent().parent();
            var isRequired=$(tbody).attr("isRequired");
            var isRemove=$(tbody).attr("isRemove");
            if(isRemove=='false') {
                jboxConfirm("确认删除？", function () {
                    if ($(tbody).find("tr[class*='body']").length > 1) {
                        $(obj).parent().parent().parent().remove();
                    } else {
                        jboxTip("必轮科室必须有一个轮转科室！");
                    }
                }, null);
            }
        }

        //检测重复选取
        function checkResel(bodyId, select) {
            bodyId = bodyId.cptcp();
            if ($("." + bodyId + " [value='" + select.value + "']:selected").length > 1) {
                select.value = "";
                jboxTip("不可添加重复轮转科室！");
            }
        }

        function Map() {
            var struct = function(key, value) {
                this.key = key;
                this.value = value;
            }

            var put = function(key, value){
                for (var i = 0; i < this.arr.length; i++) {
                    if ( this.arr[i].key === key ) {
                        this.arr[i].value = value;
                        return;
                    }
                }
                this.arr[this.arr.length] = new struct(key, value);
            }

            var get = function(key) {
                for (var i = 0; i < this.arr.length; i++) {
                    if ( this.arr[i].key === key ) {
                        return this.arr[i].value;
                    }
                }
                return null;
            }

            var remove = function(key) {
                var v;
                for (var i = 0; i < this.arr.length; i++) {
                    v = this.arr.pop();
                    if ( v.key === key ) {
                        continue;
                    }
                    this.arr.unshift(v);
                }
            }

            var size = function() {
                return this.arr.length;
            }

            var isEmpty = function() {
                return this.arr.length <= 0;
            }
            this.arr = new Array();
            this.get = get;
            this.put = put;
            this.remove = remove;
            this.size = size;
            this.isEmpty = isEmpty;
        };
        var  Y1all= {};
        //保存微调
        function save() {
            $(".msg").each(function(){
                $(this).html("");
            });
            if(!$("#saveForm").validationEngine("validate")){
                return ;
            }
            var cycleTypeId=$("input[name='cycleTypeId']:checked").val();
            if(!cycleTypeId)
            {
                jboxTip("请先选择排班方式");
                return ;
            }
            var rotationOrgGroupList = [];
            var removeCount=0;
            var count=0;
            var groupSchMonthMap=new Map();
            var groupOneSchMonthMap=new Map();
            var groupTwoSchMonthMap=new Map();
            var groupThreeSchMonthMap=new Map();
            <c:forEach items="${standardRotationGroupList}" var="rotationGroup">
            <c:set var="localGroup" value="${localGroupMap[rotationGroup.groupFlow]}"></c:set>
            count++;
            var groupFlow="${localGroup.groupFlow}";
            var groupTable=$("#"+groupFlow);
            var group ={};
            group.groupFlow=groupFlow;
            group.selTypeId=$(groupTable).attr("selTypeId");
            group.isRequired=$(groupTable).attr("isRequired");
            group.schMonth=parseFloat($(groupTable).find(".groupSchMonth").val())||0;
            group.oneSchMonth=parseFloat($(groupTable).find(".groupDeptOneMonth").val())||0;
            group.twoSchMonth=parseFloat($(groupTable).find(".groupDeptTwoMonth").val())||0;
            group.threeSchMonth=parseFloat($(groupTable).find(".groupDeptThreeMonth").val())||0;
            var v1=new Array();
            var v2=new Array();
            var v3=new Array();
            var v4=new Array();
            if("${selectYear}"=="One"&&cycleTypeId=="OneYear")
            {
                group.schMonth=group.oneSchMonth;
            }
            var isRemove="N";
            //标准科室 是否是移除的
            var orgStandardDeptList=[];
            var c=0;
            $(groupTable).find(".mainBody").each(function(){
                var osd={};
                var recordStatus=$(this).attr("isRemove")=="true"?"N":"Y";
                osd.recordFlow=$(this).attr("orgDeptRecordFlow");
                osd.recordStatus=recordStatus;
                if("N"==recordStatus)
                {
                    c++;
                }
                var standardDeptId=$(this).attr("standardId");
                var standardDeptName=$(this).attr("standardName");
                var isRequired=$(this).attr("isRequired");
                var orgSchDeptList=[];
                $(this).find(".body").each(function(){
                    var schDept = {};
                    schDept.recordFlow = $(".record", this).val() || "";
                    schDept.schMonth = parseFloat($(".schMonth", this).val()) || 0;
                    schDept.oneSchMonth = parseFloat($(".oneSchMonth", this).val()) || 0;
                    schDept.twoSchMonth = parseFloat($(".twoSchMonth", this).val()) || 0;
                    schDept.threeSchMonth = parseFloat($(".threeSchMonth", this).val()) || 0;
                    if("${selectYear}"=="One"&&cycleTypeId=="OneYear")
                    {
                        schDept.schMonth=schDept.oneSchMonth;
                    }
                    if(recordStatus=="Y") {
                        v1.push(schDept.schMonth);
                        v2.push(schDept.oneSchMonth);
                        v3.push(schDept.twoSchMonth);
                        v4.push(schDept.threeSchMonth);
                    }
                    schDept.standardDeptId = standardDeptId;
                    schDept.standardDeptName = standardDeptName;
                    schDept.schDeptFlow = $(".schDeptFlow", this).val() || "";
                    schDept.schDeptName = $("#"+schDept.schDeptFlow).val();
                    schDept.rotationFlow = "${rotation.rotationFlow}";
                    schDept.isRequired = isRequired || "";
                    schDept.groupFlow = groupFlow || "";
                    orgSchDeptList.push(schDept);
                });
                osd.orgSchDeptList=orgSchDeptList;
                orgStandardDeptList.push(osd);
            });
            if(c==$(groupTable).find(".mainBody").length)
            {
                isRemove="Y";
                removeCount++;
            }
            group.isRemove=isRemove;
            group.orgStandardDeptList=orgStandardDeptList;
            rotationOrgGroupList.push(group);
            groupSchMonthMap.put(groupFlow,v1);
            groupOneSchMonthMap.put(groupFlow,v2);
            groupTwoSchMonthMap.put(groupFlow,v3);
            groupThreeSchMonthMap.put(groupFlow,v4);
            </c:forEach>
            if(count==removeCount)
            {
                jboxTip("请至少保留一个轮转科室");
                return;
            }
            var sessionNumber = $("#sessionNumber").val();
            var selectYear = $("#selectYear").val();
            var schMonthAll=0;
            var oneSchMonthAll=0;
            var twoSchMonthAll=0;
            var threeSchMonthAll=0;
            for(var i=0;i<rotationOrgGroupList.length;i++)
            {
                var group =rotationOrgGroupList[i];
                var groupFlow=group.groupFlow;
                var isRemove=group.isRemove;
                var isRequired=group.isRequired;
                var selTypeId=group.selTypeId;
                //级别下所有标准科室没有被全部移除
                if(isRemove=="N")
                {
                    schMonthAll+=group.schMonth;
                    oneSchMonthAll+=group.oneSchMonth;
                    twoSchMonthAll+=group.twoSchMonth;
                    threeSchMonthAll+=group.threeSchMonth;
                    if(cycleTypeId=="${schCycleTypeEnumOneYear.id}") {
                        var groupSchMonth=0;
                        if (selectYear == "One") {
                            groupSchMonth += group.oneSchMonth;
                            if (groupSchMonth != group.schMonth) {
                                jboxTip("Y1与轮转时间必须相等");
                                $("#" + groupFlow + "msg").html("Y1与轮转时间必须相等");
                                return;
                            }
                        }
                        if (selectYear == "Two") {
                            groupSchMonth += group.oneSchMonth;
                            groupSchMonth += group.twoSchMonth;
                            if (groupSchMonth != group.schMonth) {
                                jboxTip("(Y1+Y2)与轮转时间必须相等");
                                $("#" + groupFlow + "msg").html("(Y1+Y2)与轮转时间必须相等");
                                return;
                            }
                        }
                        if (selectYear == "Three") {
                            groupSchMonth += group.oneSchMonth;
                            groupSchMonth += group.twoSchMonth;
                            groupSchMonth += group.threeSchMonth;
                            if (groupSchMonth != group.schMonth) {
                                jboxTip("(Y1+Y2+Y3)与轮转时间必须相等");
                                $("#" + groupFlow + "msg").html("(Y1+Y2+Y3)与轮转时间必须相等");
                                return;
                            }
                        }
                    }

                    var groupSchMonthAll=0;
                    var groupOneSchMonthAll=0;
                    var groupTwoSchMonthAll=0;
                    var groupThreeSchMonthAll=0;
                    //所有标准科室List
                    var orgStandardDeptList=group.orgStandardDeptList;
                    for(var j=0;j<orgStandardDeptList.length;j++)
                    {
                        var osd=orgStandardDeptList[j];
                        if(osd.recordStatus=="Y")
                        {
                            var orgSchDeptList=osd.orgSchDeptList;

                            for(var k=0;k<orgSchDeptList.length;k++)
                            {
                                var orgSchDept=orgSchDeptList[k];
                                groupSchMonthAll+=orgSchDept.schMonth;
                                groupOneSchMonthAll+=orgSchDept.oneSchMonth;
                                groupTwoSchMonthAll+=orgSchDept.twoSchMonth;
                                groupThreeSchMonthAll+=orgSchDept.threeSchMonth;

                                if(cycleTypeId=="${schCycleTypeEnumOneYear.id}") {
                                    var standardDeptName = orgSchDept.standardDeptName;
                                    var schDeptFlow = orgSchDept.schDeptFlow;
                                    var schDeptName = orgSchDept.schDeptName;
                                    var deptSchMonth = 0;

                                    if (0== orgSchDept.schMonth) {
                                        jboxTip("标准科室【" + standardDeptName + "】下轮转科室【" + schDeptName + "】的轮转时间必须大于0");
                                        $("#" + groupFlow + "msg").html("标准科室【" + standardDeptName + "】下轮转科室【" + schDeptName + "】的轮转时间必须大于0");
                                        return;
                                    }
                                    if (selectYear == "One") {
                                        deptSchMonth += orgSchDept.oneSchMonth;
                                        if (deptSchMonth != orgSchDept.schMonth) {
                                            jboxTip("标准科室【" + standardDeptName + "】下轮转科室【" + schDeptName + "】的Y1与轮转时间必须相等");
                                            $("#" + groupFlow + "msg").html("标准科室【" + standardDeptName + "】下轮转科室【" + schDeptName + "】的Y1与轮转时间必须相等");
                                            return;
                                        }
                                    }
                                    if (selectYear == "Two") {
                                        deptSchMonth += orgSchDept.oneSchMonth;
                                        deptSchMonth += orgSchDept.twoSchMonth;
                                        if (deptSchMonth != orgSchDept.schMonth) {
                                            jboxTip("标准科室【" + standardDeptName + "】下轮转科室【" + schDeptName + "】的(Y1+Y2)与轮转时间必须相等");
                                            $("#" + groupFlow + "msg").html("标准科室【" + standardDeptName + "】下轮转科室【" + schDeptName + "】的(Y1+Y2)与轮转时间必须相等");
                                            return;
                                        }
                                    }
                                    if (selectYear == "Three") {
                                        deptSchMonth += orgSchDept.oneSchMonth;
                                        deptSchMonth += orgSchDept.twoSchMonth;
                                        deptSchMonth += orgSchDept.threeSchMonth;
                                        if (deptSchMonth != orgSchDept.schMonth) {
                                            jboxTip("标准科室【" + standardDeptName + "】下轮转科室【" + schDeptName + "】的(Y1+Y2+Y3)与轮转时间必须相等");
                                            $("#" + groupFlow + "msg").html("标准科室【" + standardDeptName + "】下轮转科室【" + schDeptName + "】的(Y1+Y2+Y3)与轮转时间必须相等");
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //必轮
                    if(isRequired=="Y") {
                        if(cycleTypeId=="${schCycleTypeEnumOneYear.id}") {
                            if (selectYear == "One") {
                                if (groupOneSchMonthAll != group.oneSchMonth) {
                                    jboxTip("转轮科室的Y1总和与组的Y1轮转时间必须相等");
                                    $("#" + groupFlow + "msg").html("转轮科室的Y1总和与组的Y1轮转时间必须相等");
                                    return;
                                }
                            }
                            if (selectYear == "Two") {
                                if (groupSchMonthAll != group.schMonth) {
                                    jboxTip("转轮科室的轮转时间总和与组的轮转时间必须相等");
                                    $("#" + groupFlow + "msg").html("转轮科室的轮转时间总和与组的轮转时间必须相等");
                                    return;
                                }
                                if (groupOneSchMonthAll != group.oneSchMonth) {
                                    jboxTip("转轮科室的Y1总和与组的Y1轮转时间必须相等");
                                    $("#" + groupFlow + "msg").html("转轮科室的Y1总和与组的Y1轮转时间必须相等");
                                    return;
                                }
                                if (groupTwoSchMonthAll != group.twoSchMonth) {
                                    jboxTip("转轮科室的Y2总和与组的Y2轮转时间必须相等");
                                    $("#" + groupFlow + "msg").html("转轮科室的Y2总和与组的Y2轮转时间必须相等");
                                    return;
                                }
                            }
                            if (selectYear == "Three") {
                                if (groupSchMonthAll != group.schMonth) {
                                    jboxTip("转轮科室的轮转时间总和与组的轮转时间必须相等");
                                    $("#" + groupFlow + "msg").html("转轮科室的轮转时间总和与组的轮转时间必须相等");
                                    return;
                                }
                                if (groupOneSchMonthAll != group.oneSchMonth) {
                                    jboxTip("转轮科室的Y1总和与组的Y1轮转时间必须相等");
                                    $("#" + groupFlow + "msg").html("转轮科室的Y1总和与组的Y1轮转时间必须相等");
                                    return;
                                }
                                if (groupTwoSchMonthAll != group.twoSchMonth) {
                                    jboxTip("转轮科室的Y2总和与组的Y2轮转时间必须相等");
                                    $("#" + groupFlow + "msg").html("转轮科室的Y2总和与组的Y2轮转时间必须相等");
                                    return;
                                }
                                if (groupThreeSchMonthAll != group.threeSchMonth) {
                                    jboxTip("转轮科室的Y3总和与组的Y3轮转时间必须相等");
                                    $("#" + groupFlow + "msg").html("转轮科室的Y3总和与组的Y3轮转时间必须相等");
                                    return;
                                }
                            }
                        }else{
                            if (groupSchMonthAll != group.schMonth) {
                                jboxTip("转轮科室的轮转时间总和与组的轮转时间必须相等");
                                $("#" + groupFlow + "msg").html("转轮科室的轮转时间总和与组的轮转时间必须相等");
                                return;
                            }
                        }
                    }else{//非必轮
                        if(cycleTypeId=="${schCycleTypeEnumOneYear.id}") {
                            if (selectYear == "One") {
                                if (groupOneSchMonthAll < group.oneSchMonth) {
                                    jboxTip("转轮科室的Y1总和必须大于或等于组的Y1轮转时间");
                                    $("#" + groupFlow + "msg").html("转轮科室的Y1总和必须大于或等于组的Y1轮转时间");
                                    return;
                                } else {
                                    if (groupOneSchMonthAll > 0 && group.oneSchMonth == 0) {
                                        jboxTip("转轮科室的Y1总和大于0时，组的Y1轮转时间不得等于0");
                                        $("#" + groupFlow + "msg").html("转轮科室的Y1总和大于0时，组的Y1轮转时间不得等于0");
                                        return;
                                    }
                                }
                            }
                            if (selectYear == "Two") {
                                if (groupSchMonthAll < group.schMonth) {
                                    jboxTip("转轮科室的轮转时间总和必须大于或等于组的轮转时间");
                                    $("#" + groupFlow + "msg").html("转轮科室的轮转时间总和必须大于或等于组的轮转时间");
                                    return;
                                } else {
                                    if (groupSchMonthAll > 0 && group.schMonth == 0) {
                                        jboxTip("转轮科室的轮转时间总和大于0时，组的轮转时间不得等于0");
                                        $("#" + groupFlow + "msg").html("转轮科室的轮转时间总和大于0时，组的轮转时间不得等于0");
                                        return;
                                    }
                                }
                                if (groupOneSchMonthAll < group.oneSchMonth) {
                                    jboxTip("转轮科室的Y1总和必须大于或等于组的Y1轮转时间");
                                    $("#" + groupFlow + "msg").html("转轮科室的Y1总和必须大于或等于组的Y1轮转时间");
                                    return;
                                } else {
                                    if (groupOneSchMonthAll > 0 && group.oneSchMonth == 0) {
                                        jboxTip("转轮科室的Y1总和大于0时，组的Y1轮转时间不得等于0");
                                        $("#" + groupFlow + "msg").html("转轮科室的Y1总和大于0时，组的Y1轮转时间不得等于0");
                                        return;
                                    }
                                }
                                if (groupTwoSchMonthAll < group.twoSchMonth) {
                                    jboxTip("转轮科室的Y2总和必须大于或等于组的Y2轮转时间");
                                    $("#" + groupFlow + "msg").html("转轮科室的Y2总和必须大于或等于组的Y2轮转时间");
                                    return;
                                } else {
                                    if (groupTwoSchMonthAll > 0 && group.twoSchMonth == 0) {
                                        jboxTip("转轮科室的Y2总和大于0时，组的Y2轮转时间不得等于0");
                                        $("#" + groupFlow + "msg").html("转轮科室的Y2总和大于0时，组的Y2轮转时间不得等于0");
                                        return;
                                    }
                                }
                            }
                            if (selectYear == "Three") {
                                if (groupSchMonthAll < group.schMonth) {
                                    jboxTip("转轮科室的轮转时间总和必须大于或等于组的轮转时间");
                                    $("#" + groupFlow + "msg").html("转轮科室的轮转时间总和必须大于或等于组的轮转时间");
                                    return;
                                } else {
                                    if (groupSchMonthAll > 0 && group.schMonth == 0) {
                                        jboxTip("转轮科室的轮转时间总和大于0时，组的轮转时间不得等于0");
                                        $("#" + groupFlow + "msg").html("转轮科室的轮转时间总和大于0时，组的轮转时间不得等于0");
                                        return;
                                    }
                                }
                                if (groupOneSchMonthAll < group.oneSchMonth) {
                                    jboxTip("转轮科室的Y1总和必须大于或等于组的Y1轮转时间");
                                    $("#" + groupFlow + "msg").html("转轮科室的Y1总和必须大于或等于组的Y1轮转时间");
                                    return;
                                } else {
                                    if (groupOneSchMonthAll > 0 && group.oneSchMonth == 0) {
                                        jboxTip("转轮科室的Y1总和大于0时，组的Y1轮转时间不得等于0");
                                        $("#" + groupFlow + "msg").html("转轮科室的Y1总和大于0时，组的Y1轮转时间不得等于0");
                                        return;
                                    }
                                }
                                if (groupTwoSchMonthAll < group.twoSchMonth) {
                                    jboxTip("转轮科室的Y2总和必须大于或等于组的Y2轮转时间");
                                    $("#" + groupFlow + "msg").html("转轮科室的Y2总和必须大于或等于组的Y2轮转时间");
                                    return;
                                } else {
                                    if (groupTwoSchMonthAll > 0 && group.twoSchMonth == 0) {
                                        jboxTip("转轮科室的Y2总和大于0时，组的Y2轮转时间不得等于0");
                                        $("#" + groupFlow + "msg").html("转轮科室的Y2总和大于0时，组的Y2轮转时间不得等于0");
                                        return;
                                    }
                                }
                                if (groupThreeSchMonthAll < group.threeSchMonth) {
                                    jboxTip("转轮科室的Y3总和必须大于或等于组的Y3轮转时间");
                                    $("#" + groupFlow + "msg").html("转轮科室的Y3总和必须大于或等于组的Y3轮转时间");
                                    return;
                                } else {
                                    if (groupThreeSchMonthAll > 0 && group.threeSchMonth == 0) {
                                        jboxTip("转轮科室的Y3总和大于0时，组的Y3轮转时间不得等于0");
                                        $("#" + groupFlow + "msg").html("转轮科室的Y3总和大于0时，组的Y3轮转时间不得等于0");
                                        return;
                                    }
                                }
                            }
                        }else{

                            if (groupSchMonthAll < group.schMonth) {
                                jboxTip("转轮科室的轮转时间总和必须大于或等于组的轮转时间");
                                $("#" + groupFlow + "msg").html("转轮科室的轮转时间总和必须大于或等于组的轮转时间");
                                return;
                            } else {
                                if (groupSchMonthAll > 0 && group.schMonth == 0) {
                                    jboxTip("转轮科室的轮转时间总和大于0时，组的轮转时间不得等于0");
                                    $("#" + groupFlow + "msg").html("转轮科室的轮转时间总和大于0时，组的轮转时间不得等于0");
                                    return;
                                }
                            }
                        }

                        //非必轮科室 需要校验组别的轮转科室所有轮转时间组合相加是否能与要求相等
                        if(cycleTypeId=="${schCycleTypeEnumOneYear.id}") {
                            if (selectYear == "One"||selectYear == "Two"||selectYear == "Three") {
                                if (selectYear == "One"){
                                    var v=groupSchMonthMap.get(groupFlow);
                                    var month=group.schMonth;
                                    if(parseFloat(month)>0)
                                    {
                                        var check=checkMonth(month,v);
                                        if(check==0)
                                        {
                                            jboxTip("轮转科室的时间组合总和至少需要满足一组！");
                                            $("#" + groupFlow + "msg").html("轮转科室的时间组合总和至少需要满足一组！");
                                            return ;
                                        }
                                    }
                                }
                                var v=groupOneSchMonthMap.get(groupFlow);
                                var month=group.oneSchMonth;
                                if(parseFloat(month)>0)
                                {
                                    var check=checkMonth(month,v);
                                    if(check==0)
                                    {
                                        jboxTip("Y1的轮转科室的时间组合总和至少需要满足一组！");
                                        $("#" + groupFlow + "msg").html("Y1的轮转科室的时间组合总和至少需要满足一组！");
                                        return ;
                                    }
                                }
                            }
                            if (selectYear == "Two"||selectYear == "Three") {
                                var v=groupTwoSchMonthMap.get(groupFlow);
                                var month=group.twoSchMonth;
                                if(parseFloat(month)>0)
                                {
                                    var check=checkMonth(month,v);
                                    if(check==0)
                                    {
                                        jboxTip("Y2的轮转科室的时间组合总和至少需要满足一组！");
                                        $("#" + groupFlow + "msg").html("Y2的轮转科室的时间组合总和至少需要满足一组！");
                                        return ;
                                    }
                                }
                            }
                            if (selectYear == "Three") {
                                var v=groupThreeSchMonthMap.get(groupFlow);
                                var month=group.threeSchMonth;
                                if(parseFloat(month)>0)
                                {
                                    var check=checkMonth(month,v);
                                    if(check==0)
                                    {
                                        jboxTip("Y2的轮转科室的时间组合总和至少需要满足一组！");
                                        $("#" + groupFlow + "msg").html("Y2的轮转科室的时间组合总和至少需要满足一组！");
                                        return ;
                                    }
                                }
                            }
                        }else{//全年排
                            var v=groupSchMonthMap.get(groupFlow);
                            var month=group.schMonth;
                            if(parseFloat(month)>0)
                            {
                                var check=checkMonth(month,v);
                                if(check==0)
                                {
                                    jboxTip("轮转科室的时间组合总和至少需要满足一组！");
                                    $("#" + groupFlow + "msg").html("轮转科室的时间组合总和至少需要满足一组！");
                                    return ;
                                }
                            }
                        }

                    }
                }
            }
            console.log(JSON.stringify(rotationOrgGroupList));
            console.log(schMonthAll);
            if(selectYear=="One")
            {
                if(schMonthAll!=12)
                {
                    jboxTip("方案轮转总时间必须为12个月");
                    return;
                }
            }
            if(selectYear=="Two")
            {

                if(schMonthAll!=24)
                {
                    jboxTip("方案轮转总时间必须为24个月");
                    return;
                }
                if(cycleTypeId=="${schCycleTypeEnumOneYear.id}")
                {
                    if(oneSchMonthAll!=12)
                    {
                        jboxTip("方案第1年轮转总时间必须为12个月");
                        return;
                    }
                    if(twoSchMonthAll!=12)
                    {
                        jboxTip("方案第2年轮转总时间必须为12个月");
                        return;
                    }
                }
            }
            if(selectYear=="Three")
            {

                if(schMonthAll<33)
                {
                    jboxTip("方案轮转总时间必须大于等于33个月");
                    return;
                }
                if(cycleTypeId=="${schCycleTypeEnumOneYear.id}")
                {
                    if(oneSchMonthAll!=12)
                    {
                        jboxTip("方案第1年轮转总时间必须为12个月");
                        return;
                    }
                    if(twoSchMonthAll!=12)
                    {
                        jboxTip("方案第2年轮转总时间必须为12个月");
                        return;
                    }
                    if(threeSchMonthAll<9)
                    {
                        jboxTip("方案第3年轮转总时间必须大于等于9个月");
                        return;
                    }
                }
            }

            jboxPost("<s:url value='/sch/rotationCfg/checkRotationCfg'/>?rotationFlow=${rotation.rotationFlow}&orgFlow=${param.orgFlow}&sessionNumber=" + sessionNumber +"&selectYear=" + selectYear
                ,null,function(resp){
                    var msg="确认保存修改?";
                    if (resp != 0) {
                        msg="存在已选科未排班的学员信息，保存后将删除这些学员的选科信息，确认保存修改?";
                    }
                    jboxConfirm(msg, function () {
                        jboxPostJson("<s:url value='/sch/rotationCfg/saveRotationCfg'/>?rotationFlow=${rotation.rotationFlow}&orgFlow=${param.orgFlow}&sessionNumber=" + sessionNumber +"&selectYear=" + selectYear, JSON.stringify(rotationOrgGroupList), function (resp) {
                            if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                                location.reload(true);
                            }
                            jboxTip(resp);
                        }, null, false);
                    }, null);
                },null,false);
        }
        function checkMonth(month,arr)
        {
            var l=arr.length;
            if(l>0)
            {
                var blen =1 << l;
                for(var i=0;i<blen;i++)
                {
                    //取数组
                    var newArr=getComb(arr, i,blen,l);
                    var sum=0;
                    for(var j=0;j<newArr.length;j++)
                    {
                        sum+=parseFloat(newArr[j]);
                    }
                    if(parseFloat(month)==sum)
                    {
                        return 1;
                    }
                }
            }
            return 0;
        }
        function getComb(arr,index,blen,len)
        {
            var list = new Array();
            for (var i = 0; i < len; i++) {    //逐个遍历为禁止的位
                var tmp = index << i;
                if ((tmp & blen>>1) != 0) {    //遇到1就将数据加入组合中
                    list.push(arr[i]);
                }
            }
            return list;
        }
        function checkLengthToSort() {
            $(".mainBody").each(function () {
                $(".body .sortbtn", this).toggle($(".body", this).length != 1);
            });
        }

        function checkLengthToDel() {
            $(".mainBody").each(function () {
                $(".body .delBtn", this).toggle($(".body", this).length != 1);
            });
        }

        function changeYear(){
            var sessionNumber = $("#sessionNumber").val();
            var selectYear = $("#selectYear").val();
            window.location.href = "<s:url value='/sch/rotationCfg/rule'/>?rotationFlow=${rotation.rotationFlow}&roleFlag=${param.roleFlag}&currRoleFlag=${param.currRoleFlag}&orgFlow=${param.orgFlow}" + "&sessionNumber=" + sessionNumber+ "&selectYear=" + selectYear;
        }
        //查看编辑要求标准
        function rotationStandard(recordFLow){
            jboxOpen("<s:url value='/sch/template/rotationStandard'/>?roleFlag=${param.roleFlag}&recordFlow="+recordFLow,"轮转规范",1000,500);
        }
        var oldCycleTypeId="${cfg.cycleTypeId}";
        function selectTypeId(obj)
        {

            var sessionNumber = $("#sessionNumber").val();
            var selectYear = $("#selectYear").val();
            var v=$(obj).val();
            if(oldCycleTypeId=="")
            {
                var back=false;
                jboxPostJson("<s:url value='/sch/rotationCfg/saveCycleCfg'/>?rotationFlow=${rotation.rotationFlow}&orgFlow=${param.orgFlow}&sessionNumber=" + sessionNumber +"&selectYear=" + selectYear+"&cycleTypeId=" + v, null, function (resp) {
                    if (resp != '${GlobalConstant.SAVE_SUCCESSED}') {
                        back=true;
                        jboxTip("保存失败！");
                    }else{
                        oldCycleTypeId=v;
                        jboxTip("保存成功！");
                        $("#cycleTypeMsg").html("");
                        showCycleTd();
                    }
                }, null, false);
                if(back) {
                    $("input[name='cycleTypeId']").removeAttr("checked");
                }
            }else{
                if(oldCycleTypeId!=v)
                {
                    //校验是否有排班和选科
                    jboxPost("<s:url value='/sch/rotationCfg/checkHaveSch'/>?rotationFlow=${rotation.rotationFlow}&orgFlow=${param.orgFlow}&sessionNumber=" + sessionNumber +"&selectYear=" + selectYear
                        ,null,function(resp){
                            var sch=resp.sch;
                            var sel=resp.sel;
                            var back=false;
                            if(sch>0)
                            {
                                jboxTip("有学员排班信息，请删除排班后，再次调整！");
                                back=true;
                                return ;
                            }else
                            {
                                if(sel>0)
                                {
                                    jboxConfirm("有学员选科信息，保存修改将删除学员选科信息，确认修改吗", function () {
                                        jboxPostJson("<s:url value='/sch/rotationCfg/saveCycleCfg'/>?rotationFlow=${rotation.rotationFlow}&orgFlow=${param.orgFlow}&sessionNumber=" + sessionNumber +"&selectYear=" + selectYear+"&cycleTypeId=" + v, null, function (resp) {
                                            if (resp != '${GlobalConstant.SAVE_SUCCESSED}') {
                                                back=true;
                                                jboxTip("保存失败！");
                                            }else{
                                                oldCycleTypeId=v;
                                                jboxTip("保存成功！");
                                                $("#cycleTypeMsg").html("");
                                                showCycleTd();
                                            }
                                        }, null, false);
                                    }, null);
                                }else{
                                    jboxPostJson("<s:url value='/sch/rotationCfg/saveCycleCfg'/>?rotationFlow=${rotation.rotationFlow}&orgFlow=${param.orgFlow}&sessionNumber=" + sessionNumber +"&selectYear=" + selectYear+"&cycleTypeId=" + v, null, function (resp) {
                                        if (resp != '${GlobalConstant.SAVE_SUCCESSED}') {
                                            back=true;
                                            jboxTip("保存失败！");
                                        }else{
                                            oldCycleTypeId=v;
                                            jboxTip("保存成功！");
                                            $("#cycleTypeMsg").html("");
                                            showCycleTd();
                                        }
                                    }, null, false);
                                }
                            }
                            if(back) {
                                $("input[name='cycleTypeId']").removeAttr("checked");
                                $("#" + oldCycleTypeId).attr("checked", "checked");
                            }
                        },null,false);
                }
                showCycleTd();
            }
        }
        function removeRotationDept(recordFlow){
            var url = "<s:url value='/sch/template/removeDept'/>?recordFlow="+recordFlow;
            jboxPost(url,null,function(resp){
                if(resp == '${GlobalConstant.DELETE_SUCCESSED}'){
                    refresh('${param.rotationFlow}');
                }
            },null,true);

        }
        function showCycleTd()
        {
            if(oldCycleTypeId !=""){
                if(oldCycleTypeId=="${schCycleTypeEnumOneYear.id}")
                {
                    $(".AllYear").hide();
                    $(".OneYear").show();

                    var selectYear = $("#selectYear").val();
                    if(selectYear=="One")
                    {
                        $(".AllYear").hide();
                    }
                }
                if(oldCycleTypeId=="${schCycleTypeEnumAllYear.id}")
                {
                    $(".OneYear").hide();
                    $(".AllYear").show();
                }
            }else{
                $(".AllYear").hide();
                $(".OneYear").hide();
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table style="width:100%;margin: 10px 0;" class="basic">
            <tr>
                <td style="text-align: left;padding-left:10px;font-weight: bolder">
                    <font style="font-weight: bolder;">培训方案名称：</font>
                    <font title="点击查看方案说明" style="font-weight: bold;color:#3d91d5;cursor: pointer;"
                          onclick="openDetail('${rotation.rotationName}','${rotation.rotationFlow}');">${rotation.rotationName}</font>
                    &#12288;&#12288;
                    <font style="font-weight: bolder;">人员类型：</font>${rotation.doctorCategoryName}
                    &#12288;&#12288;
                    <font style="font-weight: bolder;">培训专业：</font>${rotation.speName}
                    &#12288;&#12288;
                    <font style="font-weight: bolder;">年限：</font>${rotation.rotationYear}年
                    &#12288;&#12288;
                    年级：
                    <select style="width: 88px" id="sessionNumber" name="sessionNumber"  onchange="changeYear();">
                        <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
                            <option value="${dict.dictName}" ${sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                    &#12288;&#12288;
                    培训年限：
                    <select style="width: 88px" id="selectYear" name="selectYear"  onchange="changeYear();">
                        <c:forEach items="${schSelYearEnumList}" var="dict" varStatus="num">
                            <option value="${dict.id}" ${selectYear eq dict.id?'selected':''}>${dict.name}</option>
                        </c:forEach>
                    </select>
                    &#12288;&#12288;
                    <input type="button" value="返&#12288;回" class="search" onclick="doBack();"/>
                    <input type="button" name="search" name="search" value="刷&#12288;新" class="search" onclick="location.reload(true);"/>
                </td>
            </tr>
        </table>
        <form id="saveForm">
            <div>
                <div>
                    <table style="width: 100%;">
                        <caption>
                        <span style="color: rgb(56, 145, 239);">
                            注：标准科室对应医院轮转科室时，必须添加一个轮转科室。如不需要学员轮转某个标准科室，请使用移除功能。
                        </span>
                            <br/>
                            <span style="color: rgb(56, 145, 239);">
                            培训年限为一年的，轮转总时间至少12个月，两年的至少24个月，三年的至少33个月。
                            其中Y1为第一年轮转时间，Y2为第二年轮转时间，Y3为第三年轮转时间。
                        </span>
                            <br/>
                            <span style="color: red;">
                            重新配置配置轮转方案信息后，如果存在已选科，并未排班的学员，将会清除学员选科信息
                        </span>
                        </caption>
                    </table>
                </div>
                <div>
                    <table style="width: 100%;">
                        <caption>
                        <span style="color: rgb(56, 145, 239);">
                            培训方案【${rotation.rotationName}】${sessionNumber}级学员排班方式：
                            <c:forEach items="${schCycleTypeEnumList}" var="e">
                                <input id="${e.id }" class="validate[required]" onclick="selectTypeId(this)" <c:if test="${ cfg.cycleTypeId eq e.id}">checked</c:if> type="radio" name="cycleTypeId"  value="${e.id }"/>
                                <label>${e.name}</label>&#12288;
                            </c:forEach>；
                            <c:if test="${empty cfg.cycleTypeId}">
                                <font id="cycleTypeMsg" style="color:red;">请选择排班方式!</font>
                            </c:if>
                        </span>
                        </caption>
                    </table>
                </div>
                <c:forEach items="${schStageEnumList}" var="stage">
                    <div id="${stage.id}">
                    </div>
                </c:forEach>

                <c:forEach items="${standardRotationGroupList}" var="rotationGroup">
                    <table class="xllist ord${rotationGroup.schStageId}">
                        <caption>
					<span style="color: rgb(56, 145, 239);">
						<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
                            ${rotationGroup.schStageName}：
                        </c:if>
						${rotationGroup.groupName}
					</span>
                        </caption>
                        <tr>
                            <th width="2%">
                                移除
                            </th>
                            <th width="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag?'20':'20'}%">
                                轮转科室
                            </th>
                            <th width="8%">轮转时间(${applicationScope[unitKey].name})</th>
                            <th width="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag?'25':'25'}%">备注</th>
                        </tr>
                        <c:forEach items="${standardGroupDeptMap[rotationGroup.groupFlow]}" var="rotationDept">
                            <tbody standardId="${rotationDept.standardDeptId}" maxsel="${deptRelMap[rotationDept.standardDeptId].size()}" class="${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId} mainBody">
                            <tr class="head">
                                <td rowspan="100">
                                    <table style="width: 100%;" class="none">
                                        <tr>
                                            <td>
                                                <a onclick="removeRotationDept('${rotationDept.recordFlow}');" class="remove" style="color: blue;">移除</a>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr class="head">
                                <td rowspan="100">
                                    <table style="width: 100%;" class="none">
                                        <tr>
                                            <td class="standarDeptName" width="25%;" style="text-align: center;">
                                                    ${rotationDept.standardDeptName}
                                            </td>
                                        </tr>



                                            <%--                                        <c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag)}">--%>
                                            <%--                                            <td width="5px;" >|</td>--%>
                                            <%--                                            <td style="text-align: left">--%>
                                            <%--                                                轮转时间：--%>
                                            <%--                                                <c:out value="${rotationDept.schMonth}" default="0"/>月--%>
                                            <%--                                                <c:set var="sf" value="${sf+rotationDept.schMonth}"/>--%>
                                            <%--                                                <script>--%>
                                            <%--                                                    $(function(){--%>
                                            <%--                                                        $("."+"${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}".cptcp()).data("data",{--%>
                                            <%--                                                            schMonth:<c:out value="${rotationDept.schMonth}" default="0"/>,--%>
                                            <%--                                                            deptNum:<c:out value="${rotationGroup.deptNum}" default="0"/>,--%>
                                            <%--                                                            maxDeptNum:<c:out value="${rotationGroup.maxDeptNum}" default="0"/>,--%>
                                            <%--                                                            groupFlow:"${localGroupMap[rotationGroup.groupFlow].groupFlow}",--%>
                                            <%--                                                            selTypeId:"${rotationGroup.selTypeId}"--%>
                                            <%--                                                        });--%>
                                            <%--                                                    });--%>
                                            <%--                                                </script>--%>
                                            <%--&lt;%&ndash;                                                <div style="color: #3d91d5;font-size: 8px;" onclick="relCfg('${localGroupMap[rotationGroup.groupFlow].groupFlow}${rotationDept.standardDeptId}');">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;                                                    关联科室：&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;                                                    <font class="relRoom ${rotationDept.standardDeptId}" title="配置关联科室" style="cursor: pointer;">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;                                                        <c:forEach items="${deptRelMap[rotationDept.standardDeptId]}" var="relMap" varStatus="status">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;                                                            <c:if test="${!status.first}">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;                                                                ,&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;                                                            </c:if>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;                                                            ${relMap.value.schDeptName}&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;                                                        </c:forEach>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;                                                        <c:if test="${empty deptRelMap[rotationDept.standardDeptId]}">未关联</c:if>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;                                                    </font>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;                                                </div>&ndash;%&gt;--%>
                                            <%--                                                <div relNum="${deptRelMap[rotationDept.standardDeptId].size()}" class="relOption" style="display: none;height: 0px;overflow: visible;width: 0px;"></div>--%>
                                            <%--                                            </td>--%>
                                            <%--                                        </c:if>--%>
                                            <%--                                    </tr>--%>
                                    </table>
                                </td>
                            </tr>
                            <tr class="head">
                                <td rowspan="100">
                                    <table style="width: 100%;" class="none">
                                        <tr>
                                            <td class="schMonth" width="30%;" style="text-align: center;">
                                                    ${rotationDept.schMonth}
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr class="head">
                                <td rowspan="100">
                                    <table style="width: 100%;" class="none">
                                        <tr>
                                            <td class="deptNote" width="40%;" style="text-align: center;">
                                                    ${rotationDept.deptNote}
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            </tbody>
                        </c:forEach>
                        <tr>
                            <th>合计</th>
                            <td colspan="4" style="text-align: left;padding-left: 10px;">
                                <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
                                    轮转时间：${rotationGroup.schMonth}${applicationScope[unitKey].name}
                                    <c:if test="${rotationGroup.isRequired eq GlobalConstant.FLAG_N}">
                                        &#12288;${rotationGroup.selTypeName}：${rotationGroup.deptNum}<c:if test="${rotationGroup.selTypeId eq schSelTypeEnumFree.id}">~${rotationGroup.maxDeptNum}</c:if>
                                    </c:if>
                                </c:if>
                                <c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag)}">
                                    轮转时间：${localGroupMap[rotationGroup.groupFlow].schMonth}${applicationScope[unitKey].name}
                                    <c:if test="${localGroupMap[rotationGroup.groupFlow].isRequired eq GlobalConstant.FLAG_N}">
                                        &#12288;${localGroupMap[rotationGroup.groupFlow].selTypeName}：${localGroupMap[rotationGroup.groupFlow].deptNum}<c:if test="${localGroupMap[rotationGroup.groupFlow].selTypeId eq schSelTypeEnumFree.id}">~${localGroupMap[rotationGroup.groupFlow].maxDeptNum}</c:if>
                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </c:forEach>
            </div>
        </form>
    </div>
</div>
<div id="reqCfg"></div>
<div style="display: none">

    <c:forEach items="${deptList}" var="schDept">
        <input id="${schDept.schDeptFlow}" value="${schDept.schDeptName}"></input>
    </c:forEach>
    <table>
        <tr class="body" id="templete" style="min-height: 30px;">
            <input class="record" name="orgDeptFlow" type="hidden" value=""/>
            <td>
                <div class="data">
                    <img title="移除" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                         onclick="removeCfg(this);"
                         style="cursor: pointer;margin-right: 10px;"
                         class="delBtn"/>
                    <select class="schDeptFlow validate[required]"  style="width: 100px;">
                        <option>
                            <c:forEach items="${deptList}" var="schDept">
                        <option value="${schDept.schDeptFlow}">${schDept.schDeptName}</option>
                        </c:forEach>
                    </select>
                </div>
            </td>
            <td class="OneYear AllYear" style="display: ${ selectYear eq 'One' ? 'none':''};">
                <input type="text" value="" class="schMonth data " style="width: 30px;"/>
            </td>
            <td class="OneYear">
                <input type="text" value="" class="oneSchMonth data " style="width: 30px;"/>
            </td>
            <c:if test="${selectYear eq 'Two' or selectYear eq 'Three'}">
                <td class="OneYear">
                    <input type="text" value="" class="twoSchMonth data "  style="width: 30px;"/>
                </td>
            </c:if>
            <c:if test="${ selectYear eq 'Three'}">
                <td class="OneYear">
                    <input type="text" value="" class="threeSchMonth data "  style="width: 30px;"/>
                </td>
            </c:if>
        </tr>
        <tr class="body empty" id="emptyTemplete" style="min-height: 30px;">
            <td>
            </td>
            <td>
            </td>
            <td>
            </td>
            <c:if test="${selectYear eq 'Two' or selectYear eq 'Three'}">
                <td>
                </td>
            </c:if>
            <c:if test="${ selectYear eq 'Three'}">
                <td>
                </td>
            </c:if>
        </tr>
    </table>
    <input id="checkIsSelectSave2" type="hidden" value="">
</div>
</body>
</html>