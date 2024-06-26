<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
            $('#nxt').attr({"disabled": "disabled"});
            $('#prev').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }

        //$(document).ready(function(){
        //	if($("#mainUserFinishProListTb tr").length<=0){
        //		add('mainUserFinishProList');
        //	}
        //	if($("#mainUserProingListTb tr").length<=0){
        //		add('mainUserProingList');
        //	}
        //});

        function add(tb) {
            var length = $("#" + tb + "Tb").children().length;
            if (length > 9) {
                jboxTip("限填10项！");
                return false;
            }
            //计算行数
            $("#" + tb + "Num").val(length + 1);
            //$("#total"+tb).val(length+1);

            $("#" + tb + "Tb").append($("#" + tb + "Template tr:eq(0)").clone());
            var length = $("#" + tb + "Tb").children().length;
            //序号
            $("#" + tb + "Tb").children("tr").last().children("td").eq(1).children("input").val(length);
        }

        function delTr(tb) {
            //alert("input[name="+tb+"Ids]:checked");
            var checkboxs = $("input[name='" + tb + "Ids']:checked");
            if (checkboxs.length == 0) {
                jboxTip("请勾选要删除的！");
                return false;
            }
            jboxConfirm("确认删除?", function () {
                var trs = $('#' + tb + 'Tb').find(':checkbox:checked');
                $.each(trs, function (i, n) {
                    $(n).parent('td').parent("tr").remove();
                });
                //删除后序号
                var serial = 0;
                $("." + tb + "Serial").each(function () {
                    serial += 1;
                    $(this).children("input").val(serial);
                });
                //计算行数
                var length = $("#" + tb + "Tb").children().length;
                $("#" + tb + "Num").val(length);
                //$("#total"+tb).val(length);
                theFromAmountTotal();
            });
        }

        function theFromAmountTotal() {
            $("#proAmountTotal input").each(function () {
                $(this).val("0");
            });
            setInputValue("mainUserFinishProListTb", "mainUserFinishProList_from");
            setInputValue("mainUserProingListTb", "mainUserProingList_from");
        }
        function setInputValue(tb, name) {
            $("#" + tb + " select[name=" + name + "]").each(function () {
                var fromStr = this.value;
                var amountStr = $(this).parent().next().next().children().val();
                if (isNaN(amountStr)) {
                    amountStr = 0;
                } else {
                    amountStr = amountStr * 1;
                }
                //alert(fromStr+"="+amountStr);

                // pro amount
                var tit = getIdByName(fromStr);
                if (tit != "" && amountStr != "") {
                    var befAmount = parseFloat($("input[name=amount" + tit + "]").val());
                    if (isNaN(befAmount)) {
                        befAmount = 0;
                    }
                    var befPro = parseFloat($("input[name=pro" + tit + "]").val());
                    if (isNaN(befPro)) {
                        befPro = 0;
                    }
                    $("input[name=pro" + tit + "]").val(befPro + 1);
                    $("input[name=amount" + tit + "]").val(befAmount + amountStr);
                }
            });
        }
        function getIdByName(name) {
//		863项目	973项目	国家科技支撑项目	国家重大科技专项	国家杰出青年科学基金项目  	国家自然科学基金面上项目	国家自然科学基金重大/重点项目
// 		国家自然科学基金青年科学基金项目	国家卫计委行业专项	江苏省科技厅临床重大专项	江苏省卫计委科研项目	国家自然基金重点国际（地区）合作研究项目	军队重大项目	军队一般项目	其他
//		863 973 1 2 3 4 5 6
            if (name == "863项目") {
                return "_863";
            } else if (name == "973项目") {
                return "_973";
            } else if (name == "国家科技支撑项目") {
                return "_1";
            } else if (name == "国家重大科技专项") {
                return "_2";
            } else if (name == "国家杰出青年科学基金项目") {
                return "_2_2";
            } else if (name == "国家自然科学基金面上项目") {
                return "_3";
            } else if (name == "国家自然科学基金重大/重点项目") {
                return "_3_2";
            } else if (name == "国家自然科学基金青年科学基金项目") {
                return "_3_3";
            } else if (name == "国家卫计委行业专项") {
                return "_4";
            } else if (name == "江苏省科技厅临床重大专项") {
                return "_5";
            } else if (name == "江苏省卫计委科研项目") {
                return "_6";
            } else if (name == "国家自然基金重点国际（地区）合作研究项目") {
                return "_7";
            } else if (name == "国家自然基金重大研究计划培养项目") {
                return "_8";
            } else if (name == "其他") {
                return "_9";
            } else if (name == "软科学研究项目（面上项目）（省科技厅）") {
                return "_10";
            } else if (name == "产学研合作项目（省科技厅）") {
                return "_11";
            } else if (name == "国际科技合作项目（省科技厅）") {
                return "_12";
            } else if (name == "省杰出青年基金项目（省科技厅）"){
                return "_13";
            } else if (name == "省优秀青年基金项目（省科技厅）") {
                return "_14";
            } else if (name == "省青年基金项目（省科技厅）") {
                return "_15";
            } else if (name == "省自然科学基金面上项目（省科技厅）") {
                return "_16";
            } else if (name == "省创新能力建设计划（科技设施）项目（省科技厅）") {
                return "_17";
            } else if (name == "省重点研发计划项目（社会发展）（省科技厅）") {
                return "_18";
            } else if (name == "临床医学科技专项（省科技厅）") {
                return "_19";
            } else if (name == "333高层次人才培养工程项目（第一层次）") {
                return "_20";
            } else if (name == "333高层次人才培养工程项目（第二层次）") {
                return "_21";
            } else if (name == "333高层次人才培养工程项目（第三层次）") {
                return "_22";
            } else if (name == "六大人才高峰项目（A类）") {
                return "_23";
            } else if (name == "六大人才高峰项目（B类）") {
                return "_24";
            } else if (name == "六大人才高峰项目（C类）") {
                return "_25";
            } else if (name == "六大人才高峰项目（D类）") {
                return "_26";
            } else if (name == "军队重大项目") {
                return "_27";
            } else if (name == "军队一般项目") {
                return "_28";
            }
        }

        function checkBDDate(dt) {
            var dates = $(':text', $(dt).closest("td"));
            if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                jboxTip("开始时间不能大于结束时间！");
                dt.value = "";
            }

        }
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" name="pageName" value="step7"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333; ">&nbsp;</font>
    <table class="bs_tb" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="8" class="theader">六、近五年为第一负责人已完成的省部级及以上科研项目共<input type="text"
                                                                             name="totalmainUserFinishProList"
                                                                             id="totalmainUserFinishProList"
                                                                             value="${resultMap.totalmainUserFinishProList}"
                                                                             class="inputText" style="width: 70px;"/>项。（限填10项代表作）
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="add('mainUserFinishProList')"/>&#12288;
									<img title="删除" style="cursor: pointer;"
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick="delTr('mainUserFinishProList');"/></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <td width="50px"></td>
            <td width="50px">序号</td>
<c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
            <td>课题编号</td>
    </c:if>
            <td>项目名称</td>
            <td>项目来源</td>
            <td>项目起迄时间</td>
            <td>项目经费(万元)</td>
            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                <td>第一完成人</td>
            </c:if>
        </tr>
        <tbody id="mainUserFinishProListTb">
        <c:if test="${not empty resultMap.mainUserFinishProList}">
            <c:forEach var="mainUserFinishProList" items="${resultMap.mainUserFinishProList}" varStatus="status">
                <tr>
                    <td width="50px" style="text-align: center;"><input name="mainUserFinishProListIds"
                                                                        type="checkbox"/></td>
                    <td width="50px" style="text-align: center;" class="mainUserFinishProListSerial"><input
                            name="mainUserFinishProListSerialNum" type="text"
                            value="${mainUserFinishProList.objMap.mainUserFinishProListSerialNum}"
                            style="width:50px;border:0px; text-align: center;" readonly="readonly"/></td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                    <td><input type="text" name="mainUserFinishProList_code"
                               value="${mainUserFinishProList.objMap.mainUserFinishProList_code}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    </c:if>
                    <td><input type="text" name="mainUserFinishProList_name"
                               value="${mainUserFinishProList.objMap.mainUserFinishProList_name}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    <td>
                        <select name="mainUserFinishProList_from" class="inputText validate[required]"
                                style="width: 90%;" onchange="theFromAmountTotal()">
                            <option value="">请选择</option>
                            <option value="863项目"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '863项目'}">selected="selected"</c:if>>
                                863项目
                            </option>
                            <option value="973项目"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '973项目'}">selected="selected"</c:if>>
                                973项目
                            </option>
                            <option value="国家科技支撑项目"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '国家科技支撑项目'}">selected="selected"</c:if>>
                                国家科技支撑项目
                            </option>
                            <option value="国家重大科技专项"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '国家重大科技专项'}">selected="selected"</c:if>>
                                国家重大科技专项
                            </option>
                            <option value="国家杰出青年科学基金项目"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '国家杰出青年科学基金项目'}">selected="selected"</c:if>>
                                国家杰出青年科学基金项目
                            </option>
                            <option value="国家自然科学基金面上项目"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '国家自然科学基金面上项目'}">selected="selected"</c:if>>
                                国家自然科学基金面上项目
                            </option>
                            <option value="国家自然科学基金重大/重点项目"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '国家自然科学基金重大/重点项目'}">selected="selected"</c:if>>
                                国家自然科学基金重大/重点项目
                            </option>
                            <option value="国家自然科学基金青年科学基金项目"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '国家自然科学基金青年科学基金项目'}">selected="selected"</c:if>>
                                国家自然科学基金青年科学基金项目
                            </option>
                            <option value="国家卫计委行业专项"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '国家卫计委行业专项'}">selected="selected"</c:if>>
                                国家卫计委行业专项
                            </option>
                            <option value="江苏省科技厅临床重大专项"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '江苏省科技厅临床重大专项'}">selected="selected"</c:if>>
                                江苏省科技厅临床重大专项
                            </option>
                            <option value="江苏省卫计委科研项目"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '江苏省卫计委科研项目'}">selected="selected"</c:if>>
                                江苏省卫计委科研项目
                            </option>
                            <option value="国家自然基金重点国际（地区）合作研究项目"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '国家自然基金重点国际（地区）合作研究项目'}">selected="selected"</c:if>>
                                国家自然基金重点国际（地区）合作研究项目
                            </option>
                            <option value="国家自然基金重大研究计划培养项目"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '国家自然基金重大研究计划培养项目'}">selected="selected"</c:if>>
                                国家自然基金重大研究计划培养项目
                            </option>

                            <option value="软科学研究项目（面上项目）（省科技厅）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '软科学研究项目（面上项目）（省科技厅）'}">selected="selected"</c:if>>
                                软科学研究项目（面上项目）（省科技厅）
                            </option>
                            <option value="产学研合作项目（省科技厅）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '产学研合作项目（省科技厅）'}">selected="selected"</c:if>>
                                产学研合作项目（省科技厅）
                            </option>
                            <option value="国际科技合作项目（省科技厅）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '国际科技合作项目（省科技厅）'}">selected="selected"</c:if>>
                                国际科技合作项目（省科技厅）
                            </option>
                            <option value="省杰出青年基金项目（省科技厅）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '省杰出青年基金项目（省科技厅）'}">selected="selected"</c:if>>
                                省杰出青年基金项目（省科技厅）
                            </option>
                            <option value="省优秀青年基金项目（省科技厅）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '省优秀青年基金项目（省科技厅）'}">selected="selected"</c:if>>
                                省优秀青年基金项目（省科技厅）
                            </option>
                            <option value="省青年基金项目（省科技厅）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '省青年基金项目（省科技厅）'}">selected="selected"</c:if>>
                                省青年基金项目（省科技厅）
                            </option>
                            <option value="省自然科学基金面上项目（省科技厅）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '省自然科学基金面上项目（省科技厅）'}">selected="selected"</c:if>>
                                省自然科学基金面上项目（省科技厅）
                            </option>
                            <option value="省创新能力建设计划（科技设施）项目（省科技厅）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '省创新能力建设计划（科技设施）项目（省科技厅）'}">selected="selected"</c:if>>
                                省创新能力建设计划（科技设施）项目（省科技厅）
                            </option>
                            <option value="省重点研发计划项目（社会发展）（省科技厅）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '省重点研发计划项目（社会发展）（省科技厅）'}">selected="selected"</c:if>>
                                省重点研发计划项目（社会发展）（省科技厅）
                            </option>
                            <option value="临床医学科技专项（省科技厅）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '临床医学科技专项（省科技厅）'}">selected="selected"</c:if>>
                                临床医学科技专项（省科技厅）
                            </option>
                            <option value="333高层次人才培养工程项目（第一层次）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '333高层次人才培养工程项目（第一层次）'}">selected="selected"</c:if>>
                                333高层次人才培养工程项目（第一层次）
                            </option>
                            <option value="333高层次人才培养工程项目（第二层次）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '333高层次人才培养工程项目（第二层次）'}">selected="selected"</c:if>>
                                333高层次人才培养工程项目（第二层次）
                            </option>
                            <option value="333高层次人才培养工程项目（第三层次）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '333高层次人才培养工程项目（第三层次）'}">selected="selected"</c:if>>
                                333高层次人才培养工程项目（第三层次）
                            </option>
                            <option value="六大人才高峰项目（A类）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '六大人才高峰项目（A类）'}">selected="selected"</c:if>>
                                六大人才高峰项目（A类）
                            </option>
                            <option value="六大人才高峰项目（B类）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '六大人才高峰项目（B类）'}">selected="selected"</c:if>>
                                六大人才高峰项目（B类）
                            </option>
                            <option value="六大人才高峰项目（C类）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '六大人才高峰项目（C类）'}">selected="selected"</c:if>>
                                六大人才高峰项目（C类）
                            </option>
                            <option value="六大人才高峰项目（D类）"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '六大人才高峰项目（D类）'}">selected="selected"</c:if>>
                                六大人才高峰项目（D类）
                            </option>
                            <option value="军队重大项目"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '军队重大项目'}">selected="selected"</c:if>>
                                军队重大项目
                            </option>
                            <option value="军队一般项目"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '军队一般项目'}">selected="selected"</c:if>>
                                军队一般项目
                            </option>
                            <option value="其他"
                                    <c:if test="${mainUserFinishProList.objMap.mainUserFinishProList_from eq '其他'}">selected="selected"</c:if>>
                                其他
                            </option>
                        </select>
                    </td>
                    <td nowrap="nowrap"><input type="text" name="mainUserFinishProList_bdate"
                                               value="${mainUserFinishProList.objMap.mainUserFinishProList_bdate}"
                                               class="inputText ctime validate[required]"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"
                                               style="width: 110px;" onchange="checkBDDate(this)"/> ~ <input type="text"
                                                                                                             name="mainUserFinishProList_edate"
                                                                                                             value="${mainUserFinishProList.objMap.mainUserFinishProList_edate}"
                                                                                                             class="inputText ctime validate[required]"
                                                                                                             onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                                                                                             readonly="readonly"
                                                                                                             style="width: 110px;"
                                                                                                             onchange="checkBDDate(this)"/>
                    </td>
                    <td><input type="text" name="mainUserFinishProList_amount"
                               value="${mainUserFinishProList.objMap.mainUserFinishProList_amount}"
                               class="inputText validate[required,custom[number]]" onchange="theFromAmountTotal()"
                               style="width: 90%"/></td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                    <td><input type="text" name="mainUserFinishProList_firstuser"
                               value="${mainUserFinishProList.objMap.mainUserFinishProList_firstuser}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="bs_tb" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="8" class="theader">七、近五年为第一负责人在研的省部级及以上科技项目共<input type="text" name="totalmainUserProingList"
                                                                            id="totalmainUserProingList"
                                                                            value="${resultMap.totalmainUserProingList}"
                                                                            class="inputText" style="width: 70px;"/>项。（限填10项代表作）
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="add('mainUserProingList')"/>&#12288;
									<img title="删除" style="cursor: pointer;"
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick="delTr('mainUserProingList');"/></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <td width="50px"></td>
            <td width="50px">序号</td>
<c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
            <td>课题编号</td>
    </c:if>
            <td>项目名称</td>
            <td>项目来源</td>
            <td>项目起迄时间</td>
            <td>项目经费(万元)</td>
            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                <td>第一负责人</td>
            </c:if>
        </tr>
        <tbody id="mainUserProingListTb">
        <c:if test="${not empty resultMap.mainUserProingList}">
            <c:forEach var="mainUserProingList" items="${resultMap.mainUserProingList}" varStatus="status">
                <tr>
                    <td width="50px" style="text-align: center;"><input name="mainUserProingListIds" type="checkbox"/>
                    </td>
                    <td width="50px" style="text-align: center;" class="mainUserProingListSerial"><input
                            name="mainUserProingListSerialNum" type="text"
                            value="${mainUserProingList.objMap.mainUserProingListSerialNum}"
                            style="width:50px;border:0px; text-align: center;" readonly="readonly"/></td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                    <td><input type="text" name="mainUserProingList_code"
                               value="${mainUserProingList.objMap.mainUserProingList_code}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    </c:if>
                    <td><input type="text" name="mainUserProingList_name"
                               value="${mainUserProingList.objMap.mainUserProingList_name}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    <td>
                        <select name="mainUserProingList_from" class="inputText validate[required]" style="width: 90%;"
                                onchange="theFromAmountTotal()">
                            <option value="">请选择</option>
                            <option value="863项目"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '863项目'}">selected="selected"</c:if>>
                                863项目
                            </option>
                            <option value="973项目"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '973项目'}">selected="selected"</c:if>>
                                973项目
                            </option>
                            <option value="国家科技支撑项目"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '国家科技支撑项目'}">selected="selected"</c:if>>
                                国家科技支撑项目
                            </option>
                            <option value="国家重大科技专项"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '国家重大科技专项'}">selected="selected"</c:if>>
                                国家重大科技专项
                            </option>
                            <option value="国家杰出青年科学基金项目"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '国家杰出青年科学基金项目'}">selected="selected"</c:if>>
                                国家杰出青年科学基金项目
                            </option>
                            <option value="国家自然科学基金面上项目"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '国家自然科学基金面上项目'}">selected="selected"</c:if>>
                                国家自然科学基金面上项目
                            </option>
                            <option value="国家自然科学基金重大/重点项目"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '国家自然科学基金重大/重点项目'}">selected="selected"</c:if>>
                                国家自然科学基金重大/重点项目
                            </option>
                            <option value="国家自然科学基金青年科学基金项目"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '国家自然科学基金青年科学基金项目'}">selected="selected"</c:if>>
                                国家自然科学基金青年科学基金项目
                            </option>
                            <option value="国家卫计委行业专项"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '国家卫计委行业专项'}">selected="selected"</c:if>>
                                国家卫计委行业专项
                            </option>
                            <option value="江苏省科技厅临床重大专项"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '江苏省科技厅临床重大专项'}">selected="selected"</c:if>>
                                江苏省科技厅临床重大专项
                            </option>
                            <option value="江苏省卫计委科研项目"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '江苏省卫计委科研项目'}">selected="selected"</c:if>>
                                江苏省卫计委科研项目
                            </option>
                            <option value="国家自然基金重点国际（地区）合作研究项目"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '国家自然基金重点国际（地区）合作研究项目'}">selected="selected"</c:if>>
                                国家自然基金重点国际（地区）合作研究项目
                            </option>
                            <option value="国家自然基金重大研究计划培养项目"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '国家自然基金重大研究计划培养项目'}">selected="selected"</c:if>>
                                国家自然基金重大研究计划培养项目
                            </option>
                            <option value="软科学研究项目（面上项目）（省科技厅）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '软科学研究项目（面上项目）（省科技厅）'}">selected="selected"</c:if>>
                                软科学研究项目（面上项目）（省科技厅）
                            </option>
                            <option value="产学研合作项目（省科技厅）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '产学研合作项目（省科技厅）'}">selected="selected"</c:if>>
                                产学研合作项目（省科技厅）
                            </option>
                            <option value="国际科技合作项目（省科技厅）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '国际科技合作项目（省科技厅）'}">selected="selected"</c:if>>
                                国际科技合作项目（省科技厅）
                            </option>
                            <option value="省杰出青年基金项目（省科技厅）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '省杰出青年基金项目（省科技厅）'}">selected="selected"</c:if>>
                                省杰出青年基金项目（省科技厅）
                            </option>
                            <option value="省优秀青年基金项目（省科技厅）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '省优秀青年基金项目（省科技厅）'}">selected="selected"</c:if>>
                                省优秀青年基金项目（省科技厅）
                            </option>
                            <option value="省青年基金项目（省科技厅）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '省青年基金项目（省科技厅）'}">selected="selected"</c:if>>
                                省青年基金项目（省科技厅）
                            </option>
                            <option value="省自然科学基金面上项目（省科技厅）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '省自然科学基金面上项目（省科技厅）'}">selected="selected"</c:if>>
                                省自然科学基金面上项目（省科技厅）
                            </option>
                            <option value="省创新能力建设计划（科技设施）项目（省科技厅）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '省创新能力建设计划（科技设施）项目（省科技厅）'}">selected="selected"</c:if>>
                                省创新能力建设计划（科技设施）项目（省科技厅）
                            </option>
                            <option value="省重点研发计划项目（社会发展）（省科技厅）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '省重点研发计划项目（社会发展）（省科技厅）'}">selected="selected"</c:if>>
                                省重点研发计划项目（社会发展）（省科技厅）
                            </option>
                            <option value="临床医学科技专项（省科技厅）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '临床医学科技专项（省科技厅）'}">selected="selected"</c:if>>
                                临床医学科技专项（省科技厅）
                            </option>
                            <option value="333高层次人才培养工程项目（第一层次）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '333高层次人才培养工程项目（第一层次）'}">selected="selected"</c:if>>
                                333高层次人才培养工程项目（第一层次）
                            </option>
                            <option value="333高层次人才培养工程项目（第二层次）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '333高层次人才培养工程项目（第二层次）'}">selected="selected"</c:if>>
                                333高层次人才培养工程项目（第二层次）
                            </option>
                            <option value="333高层次人才培养工程项目（第三层次）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '333高层次人才培养工程项目（第三层次）'}">selected="selected"</c:if>>
                                333高层次人才培养工程项目（第三层次）
                            </option>
                            <option value="六大人才高峰项目（A类）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '六大人才高峰项目（A类）'}">selected="selected"</c:if>>
                                六大人才高峰项目（A类）
                            </option>
                            <option value="六大人才高峰项目（B类）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '六大人才高峰项目（B类）'}">selected="selected"</c:if>>
                                六大人才高峰项目（B类）
                            </option>
                                <option value="六大人才高峰项目（C类）"
                                        <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '六大人才高峰项目（C类）'}">selected="selected"</c:if>>
                                六大人才高峰项目（C类）
                            </option>
                            <option value="六大人才高峰项目（D类）"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '六大人才高峰项目（D类）'}">selected="selected"</c:if>>
                                六大人才高峰项目（D类）
                            </option>
                            <option value="军队重大项目"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '军队重大项目'}">selected="selected"</c:if>>
                                军队重大项目
                            </option>
                            <option value="军队一般项目"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '军队一般项目'}">selected="selected"</c:if>>
                                军队一般项目
                            </option>
                            <option value="其他"
                                    <c:if test="${mainUserProingList.objMap.mainUserProingList_from eq '其他'}">selected="selected"</c:if>>
                                其他
                            </option>
                        </select>
                    </td>
                    <td nowrap="nowrap"><input type="text" name="mainUserProingList_bdate"
                                               value="${mainUserProingList.objMap.mainUserProingList_bdate}"
                                               class="inputText ctime validate[required]"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"
                                               style="width:110px;" onchange="checkBDDate(this)"/> ~ <input type="text"
                                                                                                            name="mainUserProingList_edate"
                                                                                                            value="${mainUserProingList.objMap.mainUserProingList_edate}"
                                                                                                            class="inputText ctime validate[required]"
                                                                                                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                                                                                            readonly="readonly"
                                                                                                            style="width: 110px;"
                                                                                                            onchange="checkBDDate(this)"/>
                    </td>
                    <td><input type="text" name="mainUserProingList_amount"
                               value="${mainUserProingList.objMap.mainUserProingList_amount}"
                               class="inputText validate[required,custom[number]]" onchange="theFromAmountTotal()"
                               style="width: 90%"/></td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                        <td><input type="text" name="mainUserProingList_masteruser"
                               value="${mainUserProingList.objMap.mainUserProingList_masteruser}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table id="proAmountTotal" class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <th colspan="9" class="theader" style="text-align:left;padding-left:10px;">按项目、课题来源统计</th>
        </tr>
        <tr>
            <th style="text-align: center;padding-left: 20px;">类型</th>
            <th style="text-align: center;">863项目</th>
            <th style="text-align: center;">973项目</th>
            <th style="text-align: center;">国家科技支撑项目</th>
            <th style="text-align: center;">国家重大科技专项</th>
            <th style="text-align: center;">国家杰出青年科学基金项目</th>
            <th style="text-align: center;">国家自然科学基金面上项目</th>
            <th style="text-align: center;">国家自然科学基金重大/重点项目</th>
            <th style="text-align: center;">国家自然科学基金青年科学基金项目</th>
            <th style="text-align: center;">国家卫计委行业专项</th>
            <th style="text-align: center;">江苏省科技厅临床重大专项</th>
            <th style="text-align: center;">江苏省卫计委科研项目</th>
        </tr>
        <tr>
            <td style="text-align: center;" width="120px;">项目(个)</td>
            <td><input type="text" name="pro_863" value="${resultMap.pro_863}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="pro_973" value="${resultMap.pro_973}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="pro_1" value="${resultMap.pro_1}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_2" value="${resultMap.pro_2}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_2_2" value="${resultMap.pro_2_2}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="pro_3" value="${resultMap.pro_3}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_3_2" value="${resultMap.pro_3_2}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="pro_3_3" value="${resultMap.pro_3_3}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="pro_4" value="${resultMap.pro_4}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_5" value="${resultMap.pro_5}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_6" value="${resultMap.pro_6}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
        </tr>
        <tr>
            <td style="text-align: center;" width="120px;">经费(万元)</td>
            <td><input type="text" name="amount_863" value="${resultMap.amount_863}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_973" value="${resultMap.amount_973}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_1" value="${resultMap.amount_1}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_2" value="${resultMap.amount_2}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_2_2" value="${resultMap.amount_2_2}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_3" value="${resultMap.amount_3}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_3_2" value="${resultMap.amount_3_2}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_3_3" value="${resultMap.amount_3_3}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_4" value="${resultMap.amount_4}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_5" value="${resultMap.amount_5}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_6" value="${resultMap.amount_6}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>

        </tr>

        <tr>
            <th style="text-align: center;padding-left: 20px;">类型</th>
            <th style="text-align: center;">国家自然基金重点国际（地区）合作研究项目</th>
            <th style="text-align: center;">国家自然基金重大研究计划培养项目</th>
            <th style="text-align: center;">软科学研究项目（面上项目）（省科技厅）</th>
            <th style="text-align: center;">产学研合作项目（省科技厅）</th>
            <th style="text-align: center;">国际科技合作项目（省科技厅）</th>
            <th style="text-align: center;">省杰出青年基金项目（省科技厅）</th>
            <th style="text-align: center;">省优秀青年基金项目（省科技厅）</th>
            <th style="text-align: center;">省青年基金项目（省科技厅）</th>
            <th style="text-align: center;">省自然科学基金面上项目（省科技厅）</th>
            <th style="text-align: center;">省创新能力建设计划（科技设施）项目（省科技厅）</th>
            <th style="text-align: center;">省重点研发计划项目（社会发展）（省科技厅）</th>
        </tr>
        <tr>
            <td style="text-align: center;" width="120px;">项目(个)</td>
            <td><input type="text" name="pro_7" value="${resultMap.pro_7}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_8" value="${resultMap.pro_8}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_10" value="${resultMap.pro_10}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_11" value="${resultMap.pro_11}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_12" value="${resultMap.pro_12}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_13" value="${resultMap.pro_13}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_14" value="${resultMap.pro_14}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_15" value="${resultMap.pro_15}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_16" value="${resultMap.pro_16}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_17" value="${resultMap.pro_17}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_18" value="${resultMap.pro_18}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
        </tr>
        <tr>
            <td style="text-align: center;" width="120px;">经费(万元)</td>
            <td><input type="text" name="amount_7" value="${resultMap.amount_7}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_8" value="${resultMap.amount_8}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_10" value="${resultMap.amount_10}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_11" value="${resultMap.amount_11}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_12" value="${resultMap.amount_12}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_13" value="${resultMap.amount_13}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_14" value="${resultMap.amount_14}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_15" value="${resultMap.amount_15}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_16" value="${resultMap.amount_16}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_17" value="${resultMap.amount_17}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_18" value="${resultMap.amount_18}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
        </tr>

        <tr>
            <th style="text-align: center;padding-left: 20px;">类型</th>
            <th style="text-align: center;">临床医学科技专项（省科技厅）</th>
            <th style="text-align: center;">333高层次人才培养工程项目（第一层次）</th>
            <th style="text-align: center;">333高层次人才培养工程项目（第二层次）</th>
            <th style="text-align: center;">333高层次人才培养工程项目（第三层次）</th>
            <th style="text-align: center;">六大人才高峰项目（A类）</th>
            <th style="text-align: center;">六大人才高峰项目（B类）</th>
            <th style="text-align: center;">六大人才高峰项目（C类）</th>
            <th style="text-align: center;">六大人才高峰项目（D类）</th>
            <th style="text-align: center;">军队重大项目</th>
            <th style="text-align: center;">军队一般项目</th>
            <th style="text-align: center;">其他</th>
        </tr>
        <tr>
            <td style="text-align: center;" width="120px;">项目(个)</td>
            <td><input type="text" name="pro_19" value="${resultMap.pro_19}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_20" value="${resultMap.pro_20}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_21" value="${resultMap.pro_21}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_22" value="${resultMap.pro_22}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_23" value="${resultMap.pro_23}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_24" value="${resultMap.pro_24}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_25" value="${resultMap.pro_25}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_26" value="${resultMap.pro_26}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_27" value="${resultMap.pro_27}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_28" value="${resultMap.pro_28}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
            <td><input type="text" name="pro_9" value="${resultMap.pro_9}" class="inputText validate[custom[number]]"
                       readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
        </tr>
        <tr>
            <td style="text-align: center;" width="120px;">经费(万元)</td>
            <td><input type="text" name="amount_19" value="${resultMap.amount_19}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_20" value="${resultMap.amount_20}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_21" value="${resultMap.amount_21}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_22" value="${resultMap.amount_22}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_23" value="${resultMap.amount_23}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_24" value="${resultMap.amount_24}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_25" value="${resultMap.amount_25}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_26" value="${resultMap.amount_26}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_27" value="${resultMap.amount_27}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_28" value="${resultMap.amount_28}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
            <td><input type="text" name="amount_9" value="${resultMap.amount_9}"
                       class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改"
                       style="width: 80%;"/></td>
        </tr>

    </table>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step8')" class="search" value="下一步"/>
    </div>
</c:if>

<div style="display: none;">
    <!-- 1.已完成的省部级及以上科研项目模板 -->
    <table class="basic" id="mainUserFinishProListTemplate" style="width: 100%">
        <tr>
            <td width="50px" style="text-align: center;"><input name="mainUserFinishProListIds" type="checkbox"/></td>
            <td width="50px" style="text-align: center;" class="mainUserFinishProListSerial"><input
                    name="mainUserFinishProListSerialNum" type="text" style="width:50px;border:0px; text-align: center;"
                    readonly="readonly"/></td>
            <td><input type="text" name="mainUserFinishProList_code" class="inputText validate[required]"
                       style="width: 90%"/></td>
            <td><input type="text" name="mainUserFinishProList_name" class="inputText validate[required]"
                       style="width: 90%"/></td>
            <td>
                <select name="mainUserFinishProList_from" class="inputText validate[required]" style="width: 90%;"
                        onchange="theFromAmountTotal()">
                    <option value="">请选择</option>
                    <option value="863项目">863项目</option>
                    <option value="973项目">973项目</option>
                    <option value="国家科技支撑项目">国家科技支撑项目</option>
                    <option value="国家重大科技专项">国家重大科技专项</option>
                    <option value="国家杰出青年科学基金项目">国家杰出青年科学基金项目</option>
                    <option value="国家自然科学基金面上项目">国家自然科学基金面上项目</option>
                    <option value="国家自然科学基金重大/重点项目">国家自然科学基金重大/重点项目</option>
                    <option value="国家自然科学基金青年科学基金项目">国家自然科学基金青年科学基金项目</option>
                    <option value="国家卫计委行业专项">国家卫计委行业专项</option>
                    <option value="江苏省科技厅临床重大专项">江苏省科技厅临床重大专项</option>
                    <option value="江苏省卫计委科研项目">江苏省卫计委科研项目</option>
                    <option value="国家自然基金重点国际（地区）合作研究项目">国家自然基金重点国际（地区）合作研究项目</option>
                    <option value="国家自然基金重大研究计划培养项目">国家自然基金重大研究计划培养项目</option>
                    <option value="软科学研究项目（面上项目）（省科技厅）">软科学研究项目（面上项目）（省科技厅）</option>
                    <option value="产学研合作项目（省科技厅）">产学研合作项目（省科技厅）</option>
                    <option value="国际科技合作项目（省科技厅）">国际科技合作项目（省科技厅）</option>
                    <option value="省杰出青年基金项目（省科技厅）">省杰出青年基金项目（省科技厅）</option>
                    <option value="省优秀青年基金项目（省科技厅）">省优秀青年基金项目（省科技厅）</option>
                    <option value="省青年基金项目（省科技厅）">省青年基金项目（省科技厅）</option>
                    <option value="省自然科学基金面上项目（省科技厅）">省自然科学基金面上项目（省科技厅）</option>
                    <option value="省创新能力建设计划（科技设施）项目（省科技厅）">省创新能力建设计划（科技设施）项目（省科技厅）</option>
                    <option value="省重点研发计划项目（社会发展）（省科技厅）">省重点研发计划项目（社会发展）（省科技厅） </option>
                    <option value="临床医学科技专项（省科技厅）">临床医学科技专项（省科技厅）</option>
                    <option value="333高层次人才培养工程项目（第一层次）">333高层次人才培养工程项目（第一层次）</option>
                    <option value="333高层次人才培养工程项目（第二层次）">333高层次人才培养工程项目（第二层次）</option>
                    <option value="333高层次人才培养工程项目（第三层次）">333高层次人才培养工程项目（第三层次） </option>
                    <option value="六大人才高峰项目（A类）">六大人才高峰项目（A类） </option>
                    <option value="六大人才高峰项目（B类）">六大人才高峰项目（B类）</option>
                    <option value="六大人才高峰项目（C类）">六大人才高峰项目（C类） </option>
                    <option value="六大人才高峰项目（D类）">六大人才高峰项目（D类）</option>
                    <option value="军队重大项目">军队重大项目</option>
                    <option value="军队一般项目">军队一般项目</option>
                    <option value="其他">其他</option>
                </select>
            </td>
            <td nowrap="nowrap"><input type="text" name="mainUserFinishProList_bdate"
                                       class="inputText ctime validate[required]"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"
                                       style="width: 110px;" onchange="checkBDDate(this)"/> ~ <input type="text"
                                                                                                     name="mainUserFinishProList_edate"
                                                                                                     class="inputText ctime validate[required]"
                                                                                                     onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                                                                                     readonly="readonly"
                                                                                                     style="width: 110px;"
                                                                                                     onchange="checkBDDate(this)"/>
            </td>
            <td><input type="text" name="mainUserFinishProList_amount"
                       class="inputText validate[required,custom[number]]" onchange="theFromAmountTotal()"
                       style="width: 90%"/></td>
            <td><input type="text" name="mainUserFinishProList_firstuser" class="inputText validate[required]"
                       style="width: 90%"/></td>
        </tr>
    </table>
    <!-- 2.在研的省部级及以上科技项目模板 -->
    <table class="basic" id="mainUserProingListTemplate" style="width: 100%">
        <tr>
            <td width="50px" style="text-align: center;"><input name="mainUserProingListIds" type="checkbox"/></td>
            <td width="50px" style="text-align: center;" class="mainUserProingListSerial"><input
                    name="mainUserProingListSerialNum" type="text" style="width:50px;border:0px; text-align: center;"
                    readonly="readonly"/></td>
            <td><input type="text" name="mainUserProingList_code" class="inputText validate[required]"
                       style="width: 90%"/></td>
            <td><input type="text" name="mainUserProingList_name" class="inputText validate[required]"
                       style="width: 90%"/></td>
            <td>
                <select name="mainUserProingList_from" class="inputText validate[required]" style="width: 90%;"
                        onchange="theFromAmountTotal()">
                    <option value="">请选择</option>
                    <option value="863项目">863项目</option>
                    <option value="973项目">973项目</option>
                    <option value="国家科技支撑项目">国家科技支撑项目</option>
                    <option value="国家重大科技专项">国家重大科技专项</option>
                    <option value="国家杰出青年科学基金项目">国家杰出青年科学基金项目</option>
                    <option value="国家自然科学基金面上项目">国家自然科学基金面上项目</option>
                    <option value="国家自然科学基金重大/重点项目">国家自然科学基金重大/重点项目</option>
                    <option value="国家自然科学基金青年科学基金项目">国家自然科学基金青年科学基金项目</option>
                    <option value="国家卫计委行业专项">国家卫计委行业专项</option>
                    <option value="江苏省科技厅临床重大专项">江苏省科技厅临床重大专项</option>
                    <option value="江苏省卫计委科研项目">江苏省卫计委科研项目</option>
                    <option value="国家自然基金重点国际（地区）合作研究项目">国家自然基金重点国际（地区）合作研究项目</option>
                    <option value="国家自然基金重大研究计划培养项目">国家自然基金重大研究计划培养项目</option>
                    <option value="软科学研究项目（面上项目）（省科技厅）">软科学研究项目（面上项目）（省科技厅）</option>
                    <option value="产学研合作项目（省科技厅）">产学研合作项目（省科技厅）</option>
                    <option value="国际科技合作项目（省科技厅）">国际科技合作项目（省科技厅）</option>
                    <option value="省杰出青年基金项目（省科技厅）">省杰出青年基金项目（省科技厅）</option>
                    <option value="省优秀青年基金项目（省科技厅）">省优秀青年基金项目（省科技厅）</option>
                    <option value="省青年基金项目（省科技厅）">省青年基金项目（省科技厅）</option>
                    <option value="省自然科学基金面上项目（省科技厅）">省自然科学基金面上项目（省科技厅）</option>
                    <option value="省创新能力建设计划（科技设施）项目（省科技厅）">省创新能力建设计划（科技设施）项目（省科技厅）</option>
                    <option value="省重点研发计划项目（社会发展）（省科技厅）">省重点研发计划项目（社会发展）（省科技厅） </option>
                    <option value="临床医学科技专项（省科技厅）">临床医学科技专项（省科技厅）</option>
                    <option value="333高层次人才培养工程项目（第一层次）">333高层次人才培养工程项目（第一层次）</option>
                    <option value="333高层次人才培养工程项目（第二层次）">333高层次人才培养工程项目（第二层次）</option>
                    <option value="333高层次人才培养工程项目（第三层次）">333高层次人才培养工程项目（第三层次） </option>
                    <option value="六大人才高峰项目（A类）">六大人才高峰项目（A类） </option>
                    <option value="六大人才高峰项目（B类）">六大人才高峰项目（B类）</option>
                    <option value="六大人才高峰项目（C类）">六大人才高峰项目（C类） </option>
                    <option value="六大人才高峰项目（D类）">六大人才高峰项目（D类）</option>
                    <option value="军队重大项目">军队重大项目</option>
                    <option value="军队一般项目">军队一般项目</option>
                    <option value="其他">其他</option>
                </select>
            </td>
            <td nowrap="nowrap"><input type="text" name="mainUserProingList_bdate"
                                       class="inputText ctime validate[required]"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"
                                       style="width:110px;" onchange="checkBDDate(this)"/> ~ <input type="text"
                                                                                                    name="mainUserProingList_edate"
                                                                                                    class="inputText ctime validate[required]"
                                                                                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                                                                                    readonly="readonly"
                                                                                                    style="width: 110px;"
                                                                                                    onchange="checkBDDate(this)"/>
            </td>
            <td><input type="text" name="mainUserProingList_amount" class="inputText validate[required]"
                       onchange="theFromAmountTotal()" style="width: 90%"/></td>
            <td><input type="text" name="mainUserProingList_masteruser" class="inputText validate[required]"
                       style="width: 90%"/></td>
        </tr>
    </table>
</div>
