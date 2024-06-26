<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
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
        //	if($("#listoneTb tr").length<=0){
        //		add('listone');
        //	}
        //	if($("#listtwoTb tr").length<=0){
        //		add('listtwo');
        //	}
        //	if($("#listthreeTb tr").length<=0){
        //		add('listthree');
        //	}
        //	if($("#listfourTb tr").length<=0){
        //		add('listfour');
        //	}
        //	if($("#listfiveTb tr").length<=0){
        //		add('listfive');
        //	}
        //});

        function add(tb) {
            var length = $("#" + tb + "Tb").children().length;
            if (length > 4) {
                jboxTip("限填5项！");
                return false;
            }
            //计算行数
            $("#" + tb + "Num").val(length + 1);
            //$("#total"+tb.substring(4)).val(length+1);

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
                //$("#total"+tb.substring(4)).val(length);
            });
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
    <input type="hidden" name="pageName" value="step4"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;display: block;margin-top:10px;">四、临床医学中心带头人主要业绩</font>
    <table class="bs_tb" style="width: 100%; margin-top: 10px;">
        <tr>
            <th colspan="6" class="theader">1.近五年为第一完成人的市局级及以上科研成果（含专利、新药证书）共<input type="text" name="totalone"
                                                                                    id="totalone"
                                                                                    value="${resultMap.totalone}"
                                                                                    class="inputText"
                                                                                    style="width: 70px;"/>项，限填5项代表作。
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                         style="cursor: pointer;" onclick="add('listone')"/>&#12288;
					<img title="删除" style="cursor: pointer;"
                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('listone');"/>
				</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <td width="50px"></td>
            <td width="50px">序号</td>
            <td>授予时间</td>
            <td>成果名称</td>
            <td>授予部门</td>
            <td>等级</td>
        </tr>
        <tbody id="listoneTb">
        <c:if test="${not empty resultMap.listone}">
            <c:forEach var="listone" items="${resultMap.listone}" varStatus="status">
                <tr>
                    <td width="50px" style="text-align: center;"><input name="listoneIds" type="checkbox"/></td>
                    <td width="50px" style="text-align: center;" class="listoneSerial"><input name="listoneSerialNum"
                                                                                              type="text"
                                                                                              value="${listone.objMap.listoneSerialNum}"
                                                                                              style="width:50px;border:0px; text-align: center;"
                                                                                              readonly="readonly"/></td>
                    <td><input type="text" name="listone_date" value="${listone.objMap.listone_date}"
                               class="inputText ctime validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" style="width: 90%;"/></td>
                    <td>
                        <select name="listone_name" class="inputText validate[required]" style="width: 90%;">
                            <option value="">请选择</option>
                            <c:forEach var="dict" items="${dictTypeEnumCgResultNameList}">
                                <option value="${dict.dictName }"
                                        <c:if test="${listone.objMap.listone_name eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="text" name="listone_post" value="${listone.objMap.listone_post}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    <td>
                        <select name="listone_level" class="inputText validate[required]" style="width:90%;">
                            <option value="">请选择</option>
                            <option value="一等"
                                    <c:if test="${listone.objMap.listone_level eq '一等'}">selected="selected"</c:if>>一等
                            </option>
                            <option value="二等"
                                    <c:if test="${listone.objMap.listone_level eq '二等'}">selected="selected"</c:if>>二等
                            </option>
                            <option value="三等"
                                    <c:if test="${listone.objMap.listone_level eq '三等'}">selected="selected"</c:if>>三等
                            </option>
                            <option value="无"
                                    <c:if test="${listone.objMap.listone_level eq '无'}">selected="selected"</c:if>>无
                            </option>
                        </select>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="bs_tb" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="7" class="theader">2、近五年为第一负责人的市局级及以上科研课题共<input type="text" name="totaltwo" id="totaltwo"
                                                                          value="${resultMap.totaltwo}"
                                                                          class="inputText" style="width: 70px;"/>项，限填5项代表作。
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="add('listtwo')"/>&#12288;
									<img title="删除" style="cursor: pointer;"
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick="delTr('listtwo');"/></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <td width="50px"></td>
            <td width="50px">序号</td>
            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                <td>课题编号</td>
            </c:if>
            <td>课题名称</td>
            <td>课题来源</td>
            <td>经费(万元)</td>
            <td>起止时间</td>
        </tr>
        <tbody id="listtwoTb">
        <c:if test="${not empty resultMap.listtwo}">
            <c:forEach var="listtwo" items="${resultMap.listtwo}" varStatus="status">
                <tr>
                    <td width="50px" style="text-align: center;"><input name="listtwoIds" type="checkbox"/></td>
                    <td width="50px" style="text-align: center;" class="listtwoSerial"><input name="listtwoSerialNum"
                                                                                              type="text"
                                                                                              value="${listtwo.objMap.listtwoSerialNum}"
                                                                                              style="width:50px;border:0px; text-align: center;"
                                                                                              readonly="readonly"/></td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                        <td><input type="text" name="listtwo_code" value="${listtwo.objMap.listtwo_code}"
                                   class="inputText validate[required]" style="width: 90%"/></td>
                    </c:if>
                    <td><input type="text" name="listtwo_name" value="${listtwo.objMap.listtwo_name}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    <td>
                        <select name="listtwo_chp" class="inputText validate[required]" style="width: 90%;">
                            <option value="">请选择</option>
                            <c:forEach var="dict" items="${dictTypeEnumSubjectSourceList}">
                                <option value="${dict.dictName }"
                                        <c:if test="${listtwo.objMap.listtwo_chp eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="text" name="listtwo_amount" value="${listtwo.objMap.listtwo_amount}"
                               class="inputText validate[required,number]" style="width: 90%"/></td>
                    <td nowrap="nowrap"><input type="text" name="listtwo_bdate" value="${listtwo.objMap.listtwo_bdate}"
                                               class="inputText ctime validate[required]"
                                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"
                                               style="width: 110px;" onchange="checkBDDate(this)"/> ~ <input type="text"
                                                                                                             name="listtwo_edate"
                                                                                                             value="${listtwo.objMap.listtwo_edate}"
                                                                                                             class="inputText ctime"
                                                                                                             onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                                                                                             readonly="readonly"
                                                                                                             style="width: 110px;"
                                                                                                             onchange="checkBDDate(this)"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="bs_tb" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="6" class="theader">3、近五年为第一或者通讯作者发表的SCI和中华级学术论文共
                <input type="text" name="totalthree" id="totalthree" value="${resultMap.totalthree}" class="inputText"
                       style="width: 70px;"/>篇，限填5项代表作。中华级论文影响因子统一填写0.5
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="add('listthree')"/>&#12288;
									<img title="删除" style="cursor: pointer;"
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick="delTr('listthree');"/></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <td width="50px"></td>
            <td width="50px">序号</td>
            <td>发表时间</td>
            <td>论文名称</td>
            <td>刊物名称</td>
            <td>影响因子</td>
        </tr>
        <tbody id="listthreeTb">
        <c:if test="${not empty resultMap.listthree}">
            <c:forEach var="listthree" items="${resultMap.listthree}" varStatus="status">
                <tr>
                    <td width="50px" style="text-align: center;"><input name="listthreeIds" type="checkbox"/></td>
                    <td width="50px" style="text-align: center;" class="listthreeSerial"><input
                            name="listthreeSerialNum" type="text" value="${listthree.objMap.listthreeSerialNum}"
                            style="width:50px;border:0px; text-align: center;" readonly="readonly"/></td>
                    <td><input type="text" name="listthree_date" value="${listthree.objMap.listthree_date}"
                               class="inputText ctime validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly" style="width: 90%;"/></td>
                    <td><input type="text" name="listthree_name" value="${listthree.objMap.listthree_name}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    <td><input type="text" name="listthree_kname" value="${listthree.objMap.listthree_kname}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                    <td><input type="text" name="listthree_reason" value="${listthree.objMap.listthree_reason}"
                               class="inputText validate[required]" style="width: 90%"/></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="bs_tb" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="5" class="theader">4、近五年学术杂志任职情况，共<input type="text" name="totalfour" id="totalfour"
                                                                  value="${resultMap.totalfour}" class="inputText"
                                                                  style="width: 70px;"/>份杂志，限填5份代表杂志。
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="add('listfour')"/>&#12288;
									<img title="删除" style="cursor: pointer;"
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick="delTr('listfour');"/></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <td width="50px"></td>
            <td width="50px">序号</td>
            <td>任期年限</td>
            <td>学术杂志名称</td>
            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                <td>职位</td>
            </c:if>
        </tr>
        <tbody id="listfourTb">
        <c:if test="${not empty resultMap.listfour}">
            <c:forEach var="listfour" items="${resultMap.listfour}" varStatus="status">
                <>
                <td width="50px" style="text-align: center;"><input name="listfourIds" type="checkbox"/></td>
                <td width="50px" style="text-align: center;" class="listfourSerial"><input name="listfourSerialNum"
                                                                                           type="text"
                                                                                           value="${listfour.objMap.listfourSerialNum}"
                                                                                           style="width:50px;border:0px; text-align: center;"
                                                                                           readonly="readonly"/>
                </td>
                <td><input type="text" name="listfour_year" value="${listfour.objMap.listfour_year}"
                           class="inputText validate[required]" style="width: 90%"/></td>
                <td><input type="text" name="listfour_name" value="${listfour.objMap.listfour_name}"
                           class="inputText validate[required]" style="width: 90%"/></td>
                <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                    <td>
                        <select name="listfour_title" class="inputText validate[required]" style="width: 90%;">
                            <option value="">请选择</option>
                            <option value="主编"
                                    <c:if test="${listfour.objMap.listfour_title eq '主编'}">selected="selected"</c:if>>主编
                            </option>
                            <option value="副主编"
                                    <c:if test="${listfour.objMap.listfour_title eq '副主编'}">selected="selected"</c:if>>
                                副主编
                            </option>
                            <option value="编委"
                                    <c:if test="${listfour.objMap.listfour_title eq '编委'}">selected="selected"</c:if>>编委
                            </option>
                        </select>
                    </td>
                </c:if>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="bs_tb" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="5" class="theader">5、近五年学会任职情况，共<input type="text" name="totalfive" id="totalfive"
                                                                value="${resultMap.totalfive}" class="inputText"
                                                                style="width: 70px;"/>个职务，限填5项最高职务。
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="add('listfive')"/>&#12288;
									<img title="删除" style="cursor: pointer;"
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick="delTr('listfive');"/></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <td width="50px"></td>
            <td width="50px">序号</td>
            <td>任期年限</td>
            <td>学会（分会、学组）名称</td>
            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                <td>职位</td>
            </c:if>
        </tr>
        <tbody id="listfiveTb">
        <c:if test="${not empty resultMap.listfive}">
            <c:forEach var="listfive" items="${resultMap.listfive}" varStatus="status">
                <td width="50px" style="text-align: center;"><input name="listfiveIds" type="checkbox"/></td>
                <td width="50px" style="text-align: center;" class="listfiveSerial"><input name="listfiveSerialNum"
                                                                                           type="text"
                                                                                           value="${listfive.objMap.listfiveSerialNum}"
                                                                                           style="width:50px;border:0px; text-align: center;"
                                                                                           readonly="readonly"/>
                </td>
                <td><input type="text" name="listfive_year" value="${listfive.objMap.listfive_year}"
                           class="inputText validate[required]" style="width: 90%"/></td>
                <td><input type="text" name="listfive_name" value="${listfive.objMap.listfive_name}"
                           class="inputText validate[required]" style="width: 90%"/></td>
                <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                    <td>
                        <select name="listfive_title" class="inputText validate[required]" style="width: 90%;">
                            <option value="">请选择</option>
                            <option value="主委"
                                    <c:if test="${listfive.objMap.listfive_title eq '主委'}">selected="selected"</c:if>>主委
                            </option>
                            <option value="副主委"
                                    <c:if test="${listfive.objMap.listfive_title eq '副主委'}">selected="selected"</c:if>>
                                副主委
                            </option>
                            <option value="组长"
                                    <c:if test="${listfive.objMap.listfive_title eq '组长'}">selected="selected"</c:if>>组长
                            </option>
                            <option value="常委"
                                    <c:if test="${listfive.objMap.listfive_title eq '常委'}">selected="selected"</c:if>>常委
                            </option>
                            <option value="委员"
                                    <c:if test="${listfive.objMap.listfive_title eq '委员'}">selected="selected"</c:if>>委员
                            </option>
                        </select>
                    </td>
                </c:if>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</form>


<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step5')" class="search" value="下一步"/>
    </div>
</c:if>

<div style="display: none;">
    <!-- 1.科研成果模板 -->
    <table class="basic" id="listoneTemplate" style="width: 100%">
        <tr>
            <td width="50px" style="text-align: center;"><input name="listoneIds" type="checkbox"/></td>
            <td width="50px" style="text-align: center;" class="listoneSerial"><input name="listoneSerialNum"
                                                                                      type="text"
                                                                                      style="width:50px;border:0px; text-align: center;"
                                                                                      readonly="readonly"/></td>
            <td><input class="inputText ctime validate[required]" type="text" name="listone_date"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 90%;"/></td>
            <td>
                <select name="listone_name" class="inputText validate[required]" style="width:90%;">
                    <option value="">请选择</option>
                    <c:forEach var="dict" items="${dictTypeEnumCgResultNameList}">
                        <option value="${dict.dictName}">${dict.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" name="listone_post" class="inputText validate[required]" style="width: 90%"/></td>
            <td>
                <select name="listone_level" class="inputText validate[required]" style="width:90%;">
                    <option value="">请选择</option>
                    <option value="一等">一等</option>
                    <option value="二等">二等</option>
                    <option value="三等">三等</option>
                    <option value="无">无</option>
                </select>
            </td>
        </tr>
    </table>

    <!-- 2.科研课题模板 -->
    <table class="basic" id="listtwoTemplate" style="width: 100%">
        <tr>
            <td width="50px" style="text-align: center;"><input name="listtwoIds" type="checkbox"/></td>
            <td width="50px" style="text-align: center;" class="listtwoSerial"><input name="listtwoSerialNum"
                                                                                      type="text"
                                                                                      style="width:50px;border:0px; text-align: center;"
                                                                                      readonly="readonly"/></td>
            <td><input type="text" name="listtwo_code" class="inputText validate[required]" style="width: 90%"/></td>
            <td><input type="text" name="listtwo_name" class="inputText validate[required]" style="width: 90%"/></td>
            <td>
                <select name="listtwo_chp" class="inputText validate[required]" style="width: 90%;">
                    <option value="">请选择</option>
                    <c:forEach var="dict" items="${dictTypeEnumSubjectSourceList}">
                        <option value="${dict.dictName }">${dict.dictName }</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" name="listtwo_amount" class="inputText validate[required]" style="width: 90%"/></td>
            <td><input type="text" name="listtwo_bdate" onchange="checkBDDate(this)"
                       class="inputText ctime validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                       readonly="readonly" style="width: 40%"/> ~ <input type="text" name="listtwo_edate"
                                                                         onchange="checkBDDate(this)"
                                                                         class="inputText ctime validate[required]"
                                                                         onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                                                         readonly="readonly" style="width: 40%"/></td>
        </tr>
    </table>

    <!-- 3.学术论文模板 -->
    <table class="basic" id="listthreeTemplate" style="width: 100%">
        <tr>
            <td width="50px" style="text-align: center;"><input name="listthreeIds" type="checkbox"/></td>
            <td width="50px" style="text-align: center;" class="listthreeSerial"><input name="listthreeSerialNum"
                                                                                        type="text"
                                                                                        style="width:50px;border:0px; text-align: center;"
                                                                                        readonly="readonly"/></td>
            <td><input type="text" name="listthree_date" class="inputText ctime validate[required]"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 90%"/></td>
            <td><input type="text" name="listthree_name" class="inputText validate[required]" style="width: 90%"/></td>
            <td><input type="text" name="listthree_kname" class="inputText validate[required]" style="width: 90%"/></td>
            <td><input type="text" name="listthree_reason" class="inputText validate[required]" style="width: 90%"/>
            </td>
        </tr>
    </table>
    <!-- 4.学术杂志任职情况模板 -->
    <table class="basic" id="listfourTemplate" style="width: 100%">
        <tr>
            <td width="50px" style="text-align: center;"><input name="listfourIds" type="checkbox"/></td>
            <td width="50px" style="text-align: center;" class="listfourSerial"><input name="listfourSerialNum"
                                                                                       type="text"
                                                                                       style="width:50px;border:0px; text-align: center;"
                                                                                       readonly="readonly"/></td>
            <td><input type="text" name="listfour_year" class="inputText validate[required]" style="width: 90%"/></td>
            <td><input type="text" name="listfour_name" class="inputText validate[required]" style="width: 90%"/></td>
            <td>
                <select name="listfour_title" class="inputText validate[required]" style="width: 90%;">
                    <option value="">请选择</option>
                    <option value="主编">主编</option>
                    <option value="副主编">副主编</option>
                    <option value="编委">编委</option>
                </select>
            </td>
        </tr>
    </table>
    <!-- 5.学会任职情况模板 -->
    <table class="basic" id="listfiveTemplate" style="width: 100%">
        <tr>
            <td width="50px" style="text-align: center;"><input name="listfiveIds" type="checkbox"/></td>
            <td width="50px" style="text-align: center;" class="listfiveSerial"><input name="listfiveSerialNum"
                                                                                       type="text"
                                                                                       style="width:50px;border:0px; text-align: center;"
                                                                                       readonly="readonly"/></td>
            <td><input type="text" name="listfive_year" class="inputText validate[required]" style="width: 90%"/></td>
            <td><input type="text" name="listfive_name" class="inputText validate[required]" style="width: 90%"/></td>
            <td>
                <select name="listfive_title" class="inputText validate[required]" style="width: 90%;">
                    <option value="">请选择</option>
                    <option value="主委">主委</option>
                    <option value="副主委">副主委</option>
                    <option value="组长">组长</option>
                    <option value="常委">常委</option>
                    <option value="委员">委员</option>
                </select>
            </td>
        </tr>
    </table>
</div>
