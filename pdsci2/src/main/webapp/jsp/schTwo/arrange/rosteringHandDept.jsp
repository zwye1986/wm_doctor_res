<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style type="text/css">
        table.basic th, table.basic td {
            text-align: center;
            padding: 0;
        }

        .highLight {
            background: rgba(255, 0, 0, 0.8);
        }

        .lastOper {
            background: pink;
        }

        .moveColor {
            background: rgba(225, 238, 245, 0.95);
        }
    </style>
    <c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}"
           var="unitKey"/>
    <script type="text/javascript">
        var oldFirst;//排序前第一个元素
        //初始化操作
        <c:if test="${series eq 'N'}">
        $(function(){
            $(".search").hide();
            $(".schMonthInput").attr("readonly","readonly");
            $(".start,.end").removeAttr("onclick");
            $(".aTd a").hide();
        })
        </c:if>
        <c:if test="${series ne 'N'}">//如果存在多个不连续已出科，则不允许更改
        $(function () {
            saveFirstResult();
            <%--<c:if test="${rotation.isCrisscrossFlag eq GlobalConstant.FLAG_Y}">--%>
            var oldData = "";
            $("#sortResult").sortable({
                helper: function (e, ui) {
                    ui.children().each(function () {
                        $(this).width($(this).width());
                    });
                    return ui;
                },
                create: function (e, ui) {
                    var oldSortedIds = $("#sortResult").sortable("toArray");
                    $.each(oldSortedIds, function (i, sortedId) {
                        oldData = oldData + "&recordFlow=" + sortedId;
                    });
                },
                start: function (e, ui) {
                    $(".lastOper").removeClass("lastOper");
                    ui.helper.addClass("moveColor");
                    saveFirstResult($(".resultHome:first"));
                    return ui;
                },
                stop: function (event, ui) {
                    $(".moveColor").removeClass("moveColor");
                    ui.item.addClass("lastOper");

                    var operEndIndex = $(".resultHome").index(ui.item);
                    var startId = ui.item[0].id;
                    lastOperResultFlow = startId;
                    if (operEndIndex > 0) {
                        startId = $(".resultHome:eq(" + (operEndIndex - 1) + ")")[0].id;
                    }
                    getCurrEleIndex(startId);
// 		    		ui.item.css({"backgroundColor":"#fff"});
                    var sortedIds = $("#sortResult").sortable("toArray");
                    var postdata = "";
                    $.each(sortedIds, function (i, sortedId) {
                        postdata = postdata + "&resultFlow=" + sortedId;
                    });
                    if (oldData == postdata) {
                        return;
                    }
                    jboxPost("<s:url value='/res/doc/sortAndCalculateTwo'/>", postdata + "&resultNum=${resultList.size()}", function () {
                        jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
                    }, null, false);
                    oldData = postdata;
                }
            });
            <%--</c:if>--%>

            <%--<c:if test="${!(rotation.isCrisscrossFlag eq GlobalConstant.FLAG_Y)}">--%>
            <%--$("#containerFree").append($(".sort"));--%>

            <%--var oldDataFree = "";--%>
            <%--$("#containerFree").sortable({--%>
                <%--helper: function (e, ui) {--%>
                    <%--ui.children().each(function () {--%>
                        <%--$(this).width($(this).width());--%>
                    <%--});--%>
                    <%--return ui;--%>
                <%--},--%>
                <%--create: function (e, ui) {--%>
                    <%--var oldSortedIds = $("#containerFree").sortable("toArray");--%>
                    <%--$.each(oldSortedIds, function (i, sortedId) {--%>
                        <%--oldDataFree = oldDataFree + "&recordFlow=" + sortedId;--%>
                    <%--});--%>
                <%--},--%>
                <%--start: function (e, ui) {--%>
                    <%--$(".lastOper").removeClass("lastOper");--%>
                    <%--ui.helper.addClass("moveColor");--%>
                    <%--saveFirstResult($(".resultHome:first"));--%>
                    <%--return ui;--%>
                <%--},--%>
                <%--stop: function (event, ui) {--%>
                    <%--$(".moveColor").removeClass("moveColor");--%>
                    <%--ui.item.addClass("lastOper");--%>
                    <%--var operEndIndex = $(".resultHome").index(ui.item);--%>
                    <%--var startId = ui.item[0].id;--%>
                    <%--lastOperResultFlow = startId;--%>
                    <%--if (operEndIndex > 0) {--%>
                        <%--startId = $(".resultHome:eq(" + (operEndIndex - 1) + ")")[0].id;--%>
                    <%--}--%>
                    <%--getCurrEleIndex(startId);--%>
                    <%--var sortedIds = $("#containerFree").sortable("toArray");--%>
                    <%--var postdata = "";--%>
                    <%--$.each(sortedIds, function (i, sortedId) {--%>
                        <%--postdata = postdata + "&resultFlow=" + sortedId;--%>
                    <%--});--%>
                    <%--if (oldDataFree == postdata) {--%>
                        <%--return;--%>
                    <%--}--%>
                    <%--postdata = "";--%>
                    <%--$(".resultHome").each(function () {--%>
                        <%--postdata += ("&resultFlow=" + this.id);--%>
                    <%--});--%>
                    <%--jboxPost("<s:url value='/res/doc/sortAndCalculateTwo'/>", postdata + "&resultNum=${resultList.size()}", function () {--%>
                        <%--jboxTip("${GlobalConstant.OPRE_SUCCESSED}");--%>
                    <%--}, null, false);--%>
                    <%--oldDataFree = postdata;--%>
                <%--}--%>
            <%--});--%>

            <%--<c:forEach items="${schStageEnumList}" var="stage">--%>
            <%--$("#container${stage.id}").append($(".sort${stage.id}"));--%>

            <%--var oldData${stage.id} = "";--%>
            <%--$("#container${stage.id}").sortable({--%>
                <%--helper: function (e, ui) {--%>
                    <%--ui.children().each(function () {--%>
                        <%--$(this).width($(this).width());--%>
                    <%--});--%>
                    <%--return ui;--%>
                <%--},--%>
                <%--create: function (e, ui) {--%>
                    <%--var oldSortedIds = $("#container${stage.id}").sortable("toArray");--%>
                    <%--$.each(oldSortedIds, function (i, sortedId) {--%>
                        <%--oldData${stage.id} = oldData${stage.id} + "&recordFlow=" + sortedId;--%>
                    <%--});--%>
                <%--},--%>
                <%--start: function (e, ui) {--%>
                    <%--$(".lastOper").removeClass("lastOper");--%>
                    <%--ui.helper.addClass("moveColor");--%>
                    <%--saveFirstResult($(".resultHome:first"));--%>
                    <%--return ui;--%>
                <%--},--%>
                <%--stop: function (event, ui) {--%>
                    <%--$(".moveColor").removeClass("moveColor");--%>
                    <%--ui.item.addClass("lastOper");--%>
                    <%--var operEndIndex = $(".resultHome").index(ui.item);--%>
                    <%--var startId = ui.item[0].id;--%>
                    <%--lastOperResultFlow = startId;--%>
                    <%--if (operEndIndex > 0) {--%>
                        <%--startId = $(".resultHome:eq(" + (operEndIndex - 1) + ")")[0].id;--%>
                    <%--}--%>
                    <%--getCurrEleIndex(startId);--%>
<%--// 			    		ui.item.css({"backgroundColor":"#fff"});--%>
                    <%--var sortedIds = $("#container${stage.id}").sortable("toArray");--%>
                    <%--var postdata = "";--%>
                    <%--$.each(sortedIds, function (i, sortedId) {--%>
                        <%--postdata = postdata + "&resultFlow=" + sortedId;--%>
                    <%--});--%>
                    <%--if (oldData${stage.id} == postdata) {--%>
                        <%--return;--%>
                    <%--}--%>
                    <%--postdata = "";--%>
                    <%--$(".resultHome").each(function () {--%>
                        <%--postdata += ("&resultFlow=" + this.id);--%>
                    <%--});--%>
                    <%--jboxPost("<s:url value='/res/doc/sortAndCalculateTwo'/>", postdata + "&resultNum=${resultList.size()}", function (resp) {--%>
                        <%--jboxTip("${GlobalConstant.OPRE_SUCCESSED}");--%>
                    <%--}, null, false);--%>
                    <%--oldData${stage.id} = postdata;--%>
                <%--}--%>
            <%--});--%>
            <%--</c:forEach>--%>
            <%--</c:if>--%>

            $(".isAfter").each(function () {
                $(this).removeClass("resultHome");
                $(".start,.end", this).each(function () {
                    var value = this.value;
                    $(this).hide().after('<label>' + value + '</label>');
                });
            });
            $("#afterResult").append($(".isAfter"));

            $(".isRemove").each(function () {
                $(this).removeClass("resultHome");
                $(".start,.end", this).each(function () {
                    var value = this.value;
                    $(this).hide().after('<label>' + value + '</label>');
                });
            });
            $("#removeResult").append($(".isRemove"));

            $(".isExternal").each(function () {
                $(".start,.end", this).each(function () {
                    var value = this.value;
                    $(this).hide().after('<label>' + value + '</label>');
                });
            });
            if (lastOperResultFlow) {
                $("#" + lastOperResultFlow).addClass("lastOper");
            }
            var afterResultTrs = $("#afterResult").find("tr");
            if (afterResultTrs.length > 0) {
                $("#autoCount").hide();
            }
        });
        </c:if>
        //记录下第一条
        function saveFirstResult(first) {
            if (first) {
                oldFirst = $(".resultHome:first");
            }
            return oldFirst;
        }
        function checkExternal() {
            var b = true;
            $(".isExternal").each(function () {
                var startDate = getDate($("#startDate").val());
                var externalResultFlow = $(this).attr("id");
                var externalStartDate = getDate($(this).attr("startDate"));
                var sumSchMonth = 0;
                $("input[name=resultFlows]").each(function () {
                    var resultFlow = $(this).val();
                    if(externalResultFlow == resultFlow){
                        return false;
                    }
                    sumSchMonth = parseInt(sumSchMonth) + parseInt($("#" + resultFlow).attr("schMonth"));
                });
                startDate.setMonth(startDate.getMonth() + sumSchMonth);
                if(startDate.getTime() != externalStartDate.getTime()){
                    b = false;
                    return false;
                }
            });
            return b;
        }
        //字符串转日期格式，strDate要转为日期格式的字符串
        function getDate(strDate){
            var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
                            function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
            return date;
        }
        //弹出增加轮转科室页面
        function editResult() {
            var url = "<s:url value='/schTwo/arrange/showEditResult'/>?doctorFlow=${doctor.doctorFlow}";
            jboxOpen(url, "选择要轮转的科室", 700, 400, true);
        }
        //自动排班
        function autoCount(startDate) {
            var startDate = $("#startDate").val() || startDate;
            if (!startDate)
                return jboxTip("请选择开始日期！");


            if(!checkExternal()){
                jboxTip("有外院轮转的科室不满足条件，请修改排班数据或调整开始时间！");
                return false;
            }
            var startDate = $("#startDate").val() || startDate;

            var postdata = createResultsByAuto();

            postdata += ("&startDate=" + startDate);
            var url = "<s:url value='/res/doc/sortAndCalculateTwo'/>";
            jboxPost(url, postdata + "&resultNum=${resultList.size()}", function () {
                $(".selDoc").click();
            }, null, false);
        }
        //包装排班列表参数
        function createResults(index) {
            var selector = "";
            if (index || index == 0) {
                selector = ":gt(" + index + ")";
            }
            var postdata = "";
            $(".resultHome:not(.isAfter):not(.isRemove):not(.isExternal)" + selector).each(function () {
                postdata += ("&resultFlow=" + (this.id));
            });
            return postdata;
        }
        //包装排班列表参数（用于自动排班）
        function createResultsByAuto(index) {
            var selector = "";
            if (index || index == 0) {
                selector = ":gt(" + index + ")";
            }
            var postdata = "";
            $(".resultHome:not(.isAfter):not(.isRemove)" + selector).each(function () {
                postdata += ("&resultFlow=" + (this.id));
            });
            return postdata;
        }
        //移除、恢复轮转记录
        function editSchResult(resultFlow,recordStatus){
            var url = "<s:url value='/res/doc/saveDiyDate4Two'/>";
            var data = {};
            data.resultFlow = resultFlow;
            data.recordStatus = recordStatus;
            if('${GlobalConstant.RECORD_STATUS_D}' == recordStatus){
                jboxConfirm("学员将无法查看该轮转科室信息!", function(){
                    jboxPost(url, data, function (resp) {
                        if (resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}") {
                            refreshResult();
                        }
                    }, null, false);
                });
            }else {
                jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}") {
                        refreshResult();
                    }
                }, null, false);
            }
        }
        //保存轮转时长
        function saveSchMonth(resultFlow, schMonth, groupFlow, groupMonth, groupName) {
            if (isNaN(schMonth) || schMonth == "") {
                return jboxTip("请输入数字！");
            }


            var data = {};
            data.resultFlow = resultFlow;
            schMonth = parseFloat(schMonth).toFixed(1);

            if (schMonth > 99 || schMonth < 0.5) {
                return jboxTip("请输入合法数字(0.5~99)！");
            }

            data.schMonth = schMonth;
            var url = "<s:url value='/res/doc/saveDiyDate'/>";
            jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}") {
                    $("#" + resultFlow).attr("schMonth", schMonth);
                    refreshResult();
                }
            }, null, false);
        }
        //保存自定义时间
        function diyDate(data) {
            var resultFlow = data.resultFlow;
            var start = $("#" + resultFlow).find(".start").val();
            var end = $("#" + resultFlow).find(".end").val();
            if (start && end && (start >= end)) {
                return jboxTip("结束时间必须大于开始时间！");
            }

            var result = true;
            $(".resultHome").each(function () {
                var currStart = $(this).find(".start").val();
                var currEnd = $(this).find(".end").val();
                if ((start || end) && currStart && currEnd && resultFlow != this.id) {
                    if (start)
                        result = (start < currStart) || (start > currEnd);
                    else if (end)
                        result = (end < currStart) || (end > currEnd);
                    else if (start && end)
                        result = (start < currStart && end < currStart) || (start > currEnd && end > currEnd);
                    if (!result)
                        return result;
                }
            });
            if (!result) {
                if (data.schStartDate) {
                    $("#" + resultFlow + " .start").val("");
                } else if (data.schEndDate) {
                    $("#" + resultFlow + " .end").val("");
                }
                return jboxTip("该日期与其他日期产生重叠！");
            }
            if (data.schStartDate) {
                data.schMonth = $("#" + resultFlow).attr("schMonth") || "";
            }
            var url = "<s:url value='/res/doc/saveDiyDate'/>";
            jboxPost(url, data, function (resp) {
                if (data.schEndDate) {
                    getCurrEleIndex(resultFlow);
                }
            }, null, false);
        }
        //获取当前这个元素的位置
        function getCurrEleIndex(id) {
            var startResultIndex = $(".resultHome").index($("#" + id));
            if ($(".resultHome").length - 1 != startResultIndex) {
                var startDate = $("#" + id + " .end").val();
                var currStart = $("#" + id + " .start").val();

                var fStart = $(".start", saveFirstResult()).val();
                if (startResultIndex == 0 && currStart != fStart) {
                    return autoCount(fStart);
                }

                if (startDate) {
                    var postdata = createResults(startResultIndex);
                    postdata += ("&startDate=" + startDate);
                    var mustdate = "";
                    if ($("#mustDate").length) {
                        mustdate = "&mustDate=" + (($("#mustDate")[0].checked) ? "${GlobalConstant.FLAG_Y}" : "${GlobalConstant.FLAG_N}");
                    }
                    var url = "<s:url value='/res/doc/sortAndCalculateTwo'/>";
                    jboxPost(url, postdata + "&resultNum=${resultList.size()}&addOneDay=true" + mustdate, function (resp) {
                        $(".selDoc").click();
                    }, null, false);
                }
            }
        }
        //清空排班
        function clearCount() {
            jboxConfirm("确认清空排班数据？", function () {
                var postdata = createResults();

                postdata += ("&clear=true");
                var url = "<s:url value='/res/doc/sortAndCalculateTwo'/>";
                jboxPost(url, postdata + "&resultNum=${resultList.size()}", function () {
                    $(".selDoc").click();
                }, null, false);
            }, null);
        }
    </script>
</head>
<c:set var="isWeek" value="${sysCfgMap['res_rotation_unit'] eq schUnitEnumWeek.id}"/>
<body>
<table class="basic" style="margin-left: 10px;width: 98%;">
    <tr>
        <td style="text-align: left;padding-left: 10px;">
            <div>
                姓名：${doctor.doctorName}
                &#12288;
                轮转方案：
                <c:if test="${empty rotation}">
                    无
                </c:if>
                <c:if test="${!empty rotation}">
                    <a style="color: blue;cursor: pointer;"
                       onclick="openDetail('${rotation.rotationName}','${rotation.rotationFlow}');">${rotation.rotationName}</a>
                </c:if>
                &#12288;
                轮转年限：<c:out value="${rotation.rotationYear}" default="无"/>
                <c:if test="${!empty doctor.secondRotationFlow}">
                    &#12288;
                    二级专业轮转方案：
                    <a style="color: blue;cursor: pointer;"
                       onclick="openDetail('${doctor.secondRotationName}','${doctor.secondRotationFlow}');">${doctor.secondRotationName}</a>
                </c:if>
                &#12288;
                <font color="red">如果存在多个不连续的已出科科室，不允许调整学员排班</font>
            </div>
            <div>
                <c:if test="${sysCfgMap['res_diy_sch_dept'] eq GlobalConstant.FLAG_Y}">
                    <input type="button" value="增加轮转科室" class="search"
                           style="float: right;margin-right: 10px;margin-top: 4px;"
                           onclick="editResult();"/>
                </c:if>
                <input type="button" value="清空排班" class="search"
                       style="float: right;margin-right: 10px;margin-top: 4px;"
                       onclick="clearCount();">
                <input type="button" value="自动排班" class="search" id="autoCount"
                       style="float: right;margin-right: 10px;margin-top: 4px;"
                       onclick="autoCount();">

                <input
                        class="mustpicker"
                        type="text"
                        style="float: right;margin-right: 10px;margin-top: 7px;cursor: pointer;width: 100px;"
                        id="startDate"
                        name="startDate"
                        readonly="readonly"
                        onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
                        value="${param.startDate}"
                />
                <span style="float: right;margin-right: 10px;margin-top: 0px;">开始日期：</span>
                <c:if test="${not empty isSecondRotation}">
                    <span class="red" style="float: right;margin-right: 10px;margin-top: 0px;">
                        Tip：标准科室为蓝色表示二级专业轮转方案！
                    </span>
                </c:if>
            </div>
        </td>
    </tr>
</table>
<table class="basic" style="width: 98%;margin-top: 10px;margin-left: 10px;margin-bottom: 50px;">
    <tr>
        <c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
            <th width="150px">轮转阶段</th>
        </c:if>
        <th width="200px">标准科室</th>
        <th width="150px">轮转科室</th>
        <th width="100px">轮转时长</th>
        <th width="350px">轮转时间</th>
        <c:if test="${!empty processMap}">
            <th width="100px">轮转状态</th>
        </c:if>
        <th width="100px">操作</th>
    </tr>

    <tbody id="afterResult"></tbody>

    <c:set var="sumMonth" value="0"/>
    <tbody id="sortResult">
    <c:forEach items="${resultList}" var="result">
        <c:if test="${result.recordStatus ne GlobalConstant.RECORD_STATUS_D}">
            <c:set var="sumMonth" value="${sumMonth+result.schMonth }"/>
        </c:if>
        <input type="hidden" name="resultFlows" value="${result.resultFlow}">
        <tr id="${result.resultFlow}" schMonth="${result.schMonth}" startDate="${result.schStartDate}"
            class="resultHome <c:if test="${processMap[result.resultFlow].schFlag eq GlobalConstant.FLAG_Y}"> isAfter</c:if>
             sort${groupMap[result.groupFlow].schStageId} <c:if test="${result.recordStatus eq GlobalConstant.RECORD_STATUS_D}"> isRemove</c:if>
             <c:if test="${processMap[result.resultFlow].isExternal eq GlobalConstant.FLAG_Y}"> isExternal</c:if>"
            style="cursor: move;
            <c:if test="${result.recordStatus eq GlobalConstant.RECORD_STATUS_D}">color:grey;</c:if>">
            <c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
                <td>
                        ${groupMap[result.groupFlow].schStageName}
                </td>
            </c:if>
            <td style="
            <c:if test="${isSecondRotation[result.resultFlow] eq GlobalConstant.FLAG_Y}">color:blue;</c:if>">
                <c:if test="${!empty result.standardDeptId}">
                    ${result.standardDeptName}
                </c:if>
                <c:if test="${empty result.standardDeptId}">
                    <c:forEach items="${deptRelMap[result.schDeptFlow]}" var="deptRel" varStatus="relStatus">
                        <c:if test="${!relStatus.first}">
                            ,
                        </c:if>
                        ${deptRel.standardDeptName}
                    </c:forEach>
                </c:if>
            </td>
            <td>
                ${result.schDeptName}
            </td>
            <td>
                <c:if test="${result.recordStatus eq GlobalConstant.RECORD_STATUS_D}">
                    ${result.schMonth}
                </c:if>
                <c:if test="${processMap[result.resultFlow].isExternal eq GlobalConstant.FLAG_Y}">
                    ${result.schMonth}
                </c:if>
                <c:if test="${result.recordStatus ne GlobalConstant.RECORD_STATUS_D and processMap[result.resultFlow].isExternal ne GlobalConstant.FLAG_Y}">
                    <input class="schMonthInput" type="text" value="${result.schMonth}"
                           style="width: 55px;text-align: center;"
                           onchange="saveSchMonth('${result.resultFlow}',this.value,'${result.groupFlow}',${groupMap[result.groupFlow].schMonth+0},'${groupMap[result.groupFlow].groupName}');"/>
                </c:if>
                ${applicationScope[unitKey].name}
            </td>
            <td>
                <input class="start" type="text" value="${result.schStartDate}" style="width: 80px;cursor: pointer;"
                       readonly="readonly"
                        <c:if test="${result.recordStatus ne GlobalConstant.RECORD_STATUS_D}">
                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
                        </c:if>
                        <c:if test="${result.recordStatus eq GlobalConstant.RECORD_STATUS_D}">
                            disabled
                        </c:if>
                       onchange="diyDate({resultFlow:'${result.resultFlow}',schStartDate:this.value});"
                />
                ~
                <input class="end" type="text" value="${result.schEndDate}" style="width: 80px;cursor: pointer;"
                       readonly="readonly"
                        <c:if test="${result.recordStatus ne GlobalConstant.RECORD_STATUS_D}">
                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
                        </c:if>
                        <c:if test="${result.recordStatus eq GlobalConstant.RECORD_STATUS_D}">
                            disabled
                        </c:if>
                       onchange="diyDate({resultFlow:'${result.resultFlow}',schEndDate:this.value});"/>
            </td>
            <c:if test="${!empty processMap}">
                <td>
                    <c:if test="${processMap[result.resultFlow].schFlag eq GlobalConstant.FLAG_Y}">
                        已出科
                    </c:if>
                    <c:if test="${processMap[result.resultFlow].isCurrentFlag eq GlobalConstant.FLAG_Y}">
                        轮转中
                    </c:if>
                </td>
            </c:if>
            <td class="aTd">
                <c:if test="${processMap[result.resultFlow].schFlag ne GlobalConstant.FLAG_Y and result.isRotation eq GlobalConstant.FLAG_Y and result.recordStatus eq GlobalConstant.RECORD_STATUS_Y}">
                    <a style="cursor: pointer;color: blue;" href="javascript:void(0);" onclick="editSchResult('${result.resultFlow}','${GlobalConstant.RECORD_STATUS_D}');">停用</a>
                </c:if>
                <c:if test="${processMap[result.resultFlow].schFlag ne GlobalConstant.FLAG_Y and result.isRotation eq GlobalConstant.FLAG_Y and result.recordStatus eq GlobalConstant.RECORD_STATUS_D}">
                    <a style="cursor: pointer;color: blue;" href="javascript:void(0);" onclick="editSchResult('${result.resultFlow}','${GlobalConstant.RECORD_STATUS_Y}');">启用</a>
                </c:if>
                <c:if test="${processMap[result.resultFlow].schFlag ne GlobalConstant.FLAG_Y and result.isRotation eq GlobalConstant.FLAG_N
                and result.recordStatus eq GlobalConstant.RECORD_STATUS_Y and processMap[result.resultFlow].isExternal ne GlobalConstant.FLAG_Y}">
                    <a style="cursor: pointer;color: blue;" href="javascript:void(0);" onclick="editSchResult('${result.resultFlow}','${GlobalConstant.RECORD_STATUS_N}');">删除</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>

    <c:forEach items="${schStageEnumList}" var="stage">
        <tbody id="container${stage.id}"></tbody>
    </c:forEach>

    <tbody id="containerFree"></tbody>

    <tbody id="removeResult"></tbody>

    <c:if test="${empty resultList}">
        <tr>
            <td colspan="7">无记录</td>
        </tr>
    </c:if>
    <c:if test="${!empty resultList}">
        <tr>
            <c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
                <th width="150px" colspan="3" style="text-align: right">合计：&#12288;</th>
            </c:if>
            <c:if test="${!(rotation.isStage eq GlobalConstant.FLAG_Y)}">
                <th width="150px" colspan="2" style="text-align: right">合计：&#12288;</th>
            </c:if>
            <th width="220px">
                <fmt:formatNumber type="number" maxFractionDigits="1" value="${sumMonth}"/>
            </th>
            <c:if test="${!empty processMap}">
                <th width="100px" colspan="3"></th>
            </c:if>
            <c:if test="${empty processMap}">
                <th width="100px" colspan="2"></th>
            </c:if>
        </tr>
    </c:if>
</table>


</body>
</html>