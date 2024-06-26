<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <style type="text/css">
        .div_table table {
            border-collapse: collapse;
            border: 1px solid #D3D3D3;
        }

        .div_table table td {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
            padding-left: 4px;
            padding-right: 2px;
        }

        .div_table table th {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
        }

        .div_table table tr.lastrow td {
            border-bottom: 0;
        }

        .div_table table tr td.lastCol {
            border-right: 0;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            <c:forEach items="${signList}" var="sign" varStatus="status">
            var id = "ratateImg${status.index+1}"
            var viewer = new Viewer(document.getElementById(id), {
                url: 'data-original'
            });
            </c:forEach>
        });
        $(document).ready(function () {
            $(".showInfo").on("mouseenter mouseleave",
                function () {
                    $(".theInfo", this).toggle(100);
                }
            );
            $(".show").on("mouseenter mouseleave",
                function () {
                    $(".info", this).toggle(100);
                }
            );

        });

        function saveSelect(expl, itemId, itemName) {
            
            var url = "<s:url value='/jsres/supervisio/savScheduleHaveAndNo'/>";
            var data = {
                "itemId": itemId,
                "itemName": itemName,
                "score": expl.value,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}',
                "fileRoute": '${fileRoute}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    var itemName2 = "d" + itemName.substring(1);
                    var scoreInput = "";
                    if (expl.value == '有') {
                        scoreInput = "";
                    } else {
                        scoreInput = "√";
                    }
                    var inputItem = $("input[itemName=\"" + itemName2 + "\"]").val(scoreInput);
                    top.jboxTip(resp);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        };

        function subInfo() {
            // 判断选择框是否为空
            var selectList = $("select");
            for (var i = 0; i < selectList.length; i++) {
                if (selectList[i].value == "") {
                    $(selectList[i]).focus();
                    top.jboxTip("有选择框未选择数据，请选择数据！");
                    return;
                }
            }
            var itemIdList = $("input");
            var self1Score = 1;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "less") {
                    if (itemIdList[i].value == "√") {
                        self1Score = 0;
                    }
                }
            }
            
            var inputSelf1;
            if (${ roleFlag==("baseExpert")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao2");
            } else if (${roleFlag==("expertLeader") }) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao2Expert");
            }
            inputSelf1[0].value = self1Score;
            if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], self1Score);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], self1Score);
            }
            top.jboxClose();
        }

        //窗口加载后
        $(function () {
            //获取所有input标签
            var itemIdList = $("input");
            var selectList = $("select");
            if (${isRead eq GlobalConstant.RECORD_STATUS_Y} || ${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${editFlag eq GlobalConstant.FLAG_N}) {
                for (var i = 0; i < itemIdList.length; i++) {
                    itemIdList[i].readOnly = "true";
                }
                for (var i = 0; i < selectList.length; i++) {
                    selectList[i].disabled = "true";
                }
            }
            //为每个input赋值
            <c:forEach items="${scoreList}" var="item" varStatus="status" >
            //获得值
            for (var i = 0; i < itemIdList.length; i++) {
                if(null != itemIdList[i].getAttribute("itemName")){
                    if (itemIdList[i].getAttribute("itemName").substring(0,1) == "${item.itemName}".substring(0,1) && itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name").startsWith("s")) {
                        itemIdList[i].value = "${item.score}";
                        $(itemIdList[i]).attr("preValue", "${item.score}");
                    }
                    if (itemIdList[i].getAttribute("itemName").substring(0,1) == "${item.itemName}".substring(0,1) && itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name").startsWith("l")) {
                        itemIdList[i].value = "${item.score}";
                        if (itemIdList[i].value <= 0) {
                            itemIdList[i].value = "";
                        } else {
                            itemIdList[i].value = "√";
                        }
                        $(itemIdList[i]).attr("preValue", "${item.score}");
                    }
                }
            }
            for (var i = 0; i < selectList.length; i++) {
                if (selectList[i].getAttribute("itemName") == "${item.itemName}" && selectList[i].getAttribute("name").startsWith("s")) {
                    selectList[i].value = "${item.score}";
                    $(selectList[i]).attr("preValue", "${item.score}");
                }
            }
            </c:forEach>
        });

        function check(exp) {
            var reg1 = /^\d+(\.0)$/;
            if (reg1.test(exp)) {
                return parseInt(exp);
            } else {
                return exp;
            }
        }

        $(function () {
            if (${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${"expertLeader" eq roleFlag} || ${"expert" eq roleFlag}) {
                $("button[name='removeFileBtn']").attr({style: "display:none"});
                $("a[name='upLoadBtn']").attr({style: "display:none"});
            }
        });
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 600px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">内科医疗设备</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">医疗设备</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="35" style="width: 20%">专业基地专有设备</th>
            <th style="width: 30%">12导联心电图记录仪</th>
            <th style="width: 16%">有</th>
            <td style="width: 17%">
                <select onchange="saveSelect(this,'0100-09-1.1','k12导联心电图记录仪');" itemId="0100-09-1.1"
                        itemName="k12导联心电图记录仪"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td style="width: 17%">
                <input itemId="0100-09-1.1" itemName="d12导联心电图记录仪" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>动态血压仪</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.2','k动态血压仪');" itemId="0100-09-1.2" itemName="k动态血压仪"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.2" itemName="d动态血压仪" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>超声心动图(含普通经胸超声心动图和经食管超声心动图)仪</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.3','k超声心动图仪');" itemId="0100-09-1.3" itemName="k超声心动图仪"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.3" itemName="d超声心动图仪" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>临时心脏起搏器</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.4','k临时心脏起搏器');" itemId="0100-09-1.4" itemName="k临时心脏起搏器"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.4" itemName="d临时心脏起搏器" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心电监护仪</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.5','k心电监护仪');" itemId="0100-09-1.5" itemName="k心电监护仪"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.5" itemName="d心电监护仪" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>血流动力学监测仪</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.6','k血流动力学监测仪');" itemId="0100-09-1.6" itemName="k血流动力学监测仪"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.6" itemName="d血流动力学监测仪" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>除颤器</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.7','k除颤器');" itemId="0100-09-1.7" itemName="k除颤器"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.7" itemName="d除颤器" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>食管电极导管</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.8','k食管电极导管');" itemId="0100-09-1.8" itemName="k食管电极导管"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.8" itemName="d食管电极导管" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>电生理刺激仪</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.9','k电生理刺激仪');" itemId="0100-09-1.9" itemName="k电生理刺激仪"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.9" itemName="d电生理刺激仪" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>平板运动机</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.10','k平板运动机');" itemId="0100-09-1.10" itemName="k平板运动机"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.10" itemName="d平板运动机" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>氧饱和度监测仪</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.11','k氧饱和度监测仪');" itemId="0100-09-1.11" itemName="k氧饱和度监测仪"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.11" itemName="d氧饱和度监测仪" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺功能仪</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.12','k肺功能仪');" itemId="0100-09-1.12" itemName="k肺功能仪"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.12" itemName="d肺功能仪" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>呼吸机</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.13','k呼吸机');" itemId="0100-09-1.13" itemName="k呼吸机"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.13" itemName="d呼吸机" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>支气管镜</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.14','k支气管镜');" itemId="0100-09-1.14" itemName="k支气管镜"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.14" itemName="d支气管镜" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>多导睡眠呼吸分析仪(PSG)</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.15','k多导睡眠呼吸分析仪(PSG)');" itemId="0100-09-1.15"
                        itemName="k多导睡眠呼吸分析仪(PSG)"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.15" itemName="d多导睡眠呼吸分析仪(PSG)" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胃镜</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.16','k胃镜');" itemId="0100-09-1.16" itemName="k胃镜"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.16" itemName="d胃镜" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>结肠镜</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.17','k结肠镜');" itemId="0100-09-1.17" itemName="k胃镜"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.17" itemName="d结肠镜" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>十二指肠镜</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.18','k十二指肠镜');" itemId="0100-09-1.18" itemName="k十二指肠镜"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.18" itemName="d十二指肠镜" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>超声内镜</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.19','k超声内镜');" itemId="0100-09-1.19" itemName="k超声内镜"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.19" itemName="d超声内镜" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>腹腔镜</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.20','k腹腔镜');" itemId="0100-09-1.20" itemName="k腹腔镜"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.20" itemName="d腹腔镜" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>内镜下介入治疗</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.21','k内镜下介入治疗');" itemId="0100-09-1.21" itemName="k内镜下介入治疗"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.21" itemName="d内镜下介入治疗" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>超声下介入诊治设备</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.22','k超声下介入诊治设备');" itemId="0100-09-1.22"
                        itemName="k超声下介入诊治设备"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.22" itemName="d超声下介入诊治设备" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>显微镜</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.23','k显微镜');" itemId="0100-09-1.23" itemName="k显微镜"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.23" itemName="d显微镜" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>数码摄像头及成像电脑设备</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.24','k数码摄像头及成像电脑设备');" itemId="0100-09-1.24"
                        itemName="k数码摄像头及成像电脑设备"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.24" itemName="d数码摄像头及成像电脑设备" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>细胞遗传学检查设备</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.25','k细胞遗传学检查设备');" itemId="0100-09-1.25"
                        itemName="k细胞遗传学检查设备"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.25" itemName="d细胞遗传学检查设备" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>干细胞冷冻复苏设备</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.26','k干细胞冷冻复苏设备');" itemId="0100-09-1.26"
                        itemName="k干细胞冷冻复苏设备"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.26" itemName="d干细胞冷冻复苏设备" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>血液、生化、免疫、尿液检验设备</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.27','k血液、生化、免疫、尿液检验设备');" itemId="0100-09-1.27"
                        itemName="k血液、生化、免疫、尿液检验设备"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.27" itemName="d血液、生化、免疫、尿液检验设备" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>血液透析机</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.28','k血液透析机');" itemId="0100-09-1.28" itemName="k血液透析机"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.28" itemName="d血液透析机" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>超声引导下的经皮肾活检设备</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.29','k超声引导下的经皮肾活检设备');" itemId="0100-09-1.29"
                        itemName="k超声引导下的经皮肾活检设备"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.29" itemName="d超声引导下的经皮肾活检设备" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>持续性血液净化设备</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.30','k持续性血液净化设备');" itemId="0100-09-1.30"
                        itemName="k持续性血液净化设备"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.30" itemName="d持续性血液净化设备" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>血浆置换设备</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.31','k血浆置换设备');" itemId="0100-09-1.31" itemName="k血浆置换设备"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.31" itemName="d血浆置换设备" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>便携式血糖仪</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.32','k便携式血糖仪');" itemId="0100-09-1.32" itemName="k便携式血糖仪"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.32" itemName="d便携式血糖仪" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>血糖监测仪</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.33','k血糖监测仪');" itemId="0100-09-1.33" itemName="k血糖监测仪"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.33" itemName="d血糖监测仪" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胰岛素泵</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.34','k胰岛素泵');" itemId="0100-09-1.34" itemName="k胰岛素泵"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.34" itemName="d胰岛素泵" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>双能X射线骨密度测定仪</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-1.35','k双能X射线骨密度测定仪');" itemId="0100-09-1.35"
                        itemName="k双能X射线骨密度测定仪"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-1.35" itemName="d双能X射线骨密度测定仪" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">所在培训基地（医院）设备</th>
            <th>大型X射线机</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-2.1','k大型X射线机');" itemId="0100-09-2.1" itemName="k大型X射线机"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-2.1" itemName="d大型X射线机" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>数字血管造影设备(DSA)</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-2.2','k数字血管造影设备(DSA)');" itemId="0100-09-2.2"
                        itemName="k数字血管造影设备(DSA)"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-2.2" itemName="d数字血管造影设备(DSA)" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>CT</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-2.3','kCT');" itemId="0100-09-2.3" itemName="kCT"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-2.3" itemName="dCT" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>MRI</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-2.4','kMRI');" itemId="0100-09-2.4" itemName="kMRI"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-2.4" itemName="dMRI" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>ECT</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-2.5','kECT');" itemId="0100-09-2.5" itemName="kECT"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-2.5" itemName="dECT" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>放射治疗机</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-2.6','k放射治疗机');" itemId="0100-09-2.6" itemName="k放射治疗机"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-2.6" itemName="放射治疗机" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>彩色B超(带有Doppler探头)</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0100-09-2.7','k彩色B超(带有Doppler探头)');" itemId="0100-09-2.7"
                        itemName="k彩色B超(带有Doppler探头)"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-09-2.7" itemName="d彩色B超(带有Doppler探头)" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="4" style="text-align: right">合计：</th>
            <th></th>
        </tr>
        </tbody>

    </table>
</div>
<c:if test="${(GlobalConstant.USER_LIST_GLOBAL ne roleFlag) && (isRead ne GlobalConstant.RECORD_STATUS_Y) && (editFlag ne 'N')}">
    <div class="button">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
<input class="btn_green" type="button" id="zancun" value="暂&#12288存" onclick="zancun();"/>&#12288;
        <input class="btn_green" type="button" value="取&#12288;消" onclick="top.jboxClose();"/>
        <script type="text/javascript">
            function zancun() {
                top.jboxTip("暂存成功！");
                $('#zancun').hide();
            }
        </script>
    </div>
</c:if>
</body>
</html>