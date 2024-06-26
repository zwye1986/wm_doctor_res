<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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

    //		$(document).ready(function(){
    //			if($("#mainUserFinRwardProListTb tr").length<=0){
    //				add('mainUserFinRwardProList');
    //			}
    //			if($("#mainUserAuthorProListTb tr").length<=0){
    //				add('mainUserAuthorProList');
    //			}
    //			if($("#mainUserFinDevProListTb tr").length<=0){
    //				add('mainUserFinDevProList');
    //			}
    //		});

    function add(tb) {
        var length = $("#" + tb + "Tb").children().length;
        if (length > 14 && "mainUserAuthorProList" != tb) {
            jboxTip("限填15项！");
            return false;
        } else if (length > 19 && "mainUserAuthorProList" == tb) {
            jboxTip("限填20项！");
            return false;
        }
        //计算行数
        $("#" + tb + "Num").val(length + 1);
        //$("#total"+tb).val(length+1);

        $("#" + tb + "Tb").append($("#" + tb + "Template tr:eq(0)").clone());
        var length = $("#" + tb + "Tb").children().length;
        //序号
//			$("#"+tb+"Tb").children("tr").last().children("td").eq(1).text(length);
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
//					$(this).text(serial);
                $(this).children("input").val(serial);
            });
            //计算行数
            var length = $("#" + tb + "Tb").children().length;
            $("#" + tb + "Num").val(length);
            //$("#total"+tb).val(length);
        });
    }

</script>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;" id="projForm">
    <input type="hidden" id="pageName" name="pageName" value="step7"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333; ">&nbsp;</font>
    <table class="bs_tb" style="width: 100%;">
        <tr>
            <th colspan="7" class="theader">八、近五年为第一完成人的市局级及以上科技进步奖共<input type="text"
                                                                           name="totalmainUserFinRwardProList"
                                                                           id="totalmainUserFinRwardProList"
                                                                           value="${resultMap.totalmainUserFinRwardProList}"
                                                                           class="inputText" style="width: 70px;"/>项。（限填15项代表作）
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="add('mainUserFinRwardProList')"/>&#12288;
									<img title="删除" style="cursor: pointer;"
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick="delTr('mainUserFinRwardProList');"/></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <td width="50px"></td>
            <td width="50px">序号</td>
            <td>获奖时间</td>
            <td>名称</td>
            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                <td>完成人</td>
            </c:if>
            <td>奖励部门</td>
            <td>获奖等级</td>
        </tr>
        <tbody id="mainUserFinRwardProListTb">
        <c:if test="${not empty resultMap.mainUserFinRwardProList}">
            <c:forEach var="mainUserFinRwardProList" items="${resultMap.mainUserFinRwardProList}" varStatus="status">
                <tr>
                    <td width="50px" style="text-align: center;"><input name="mainUserFinRwardProListIds"
                                                                        type="checkbox"/></td>
                    <td width="50px" style="text-align: center;" class="mainUserFinRwardProListSerial"><input
                            name="mainUserFinRwardProListSerialNum" type="text"
                            value="${mainUserFinRwardProList.objMap.mainUserFinRwardProListSerialNum}"
                            style="width:50px;border:0px; text-align: center;" readonly="readonly"/></td>
                    <td><input type="text" name="mainUserFinRwardProList_date"
                               value="${mainUserFinRwardProList.objMap.mainUserFinRwardProList_date}"
                               class="inputText ctime invalidate[required]"
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 90%"/>
                    </td>
                    <td><input type="text" name="mainUserFinRwardProList_name"
                               value="${mainUserFinRwardProList.objMap.mainUserFinRwardProList_name}"
                               class="inputText invalidate[required]" style="width: 90%"/></td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                    <td><input type="text" name="mainUserFinRwardProList_finUser"
                               value="${mainUserFinRwardProList.objMap.mainUserFinRwardProList_finUser}"
                               class="inputText invalidate[required]" style="width: 90%"/></td>
                    </c:if>
                    <td><input type="text" name="mainUserFinRwardProList_dept"
                               value="${mainUserFinRwardProList.objMap.mainUserFinRwardProList_dept}"
                               class="inputText invalidate[required]" style="width: 90%"/></td>
                    <td>
                        <select name="mainUserFinRwardProList_level" class="inputText validate[required]"
                                style="width:90%;">
                            <option value="">请选择</option>
                            <option value="一等"
                                    <c:if test="${mainUserFinRwardProList.objMap.mainUserFinRwardProList_level eq '一等'}">selected="selected"</c:if>>
                                一等
                            </option>
                            <option value="二等"
                                    <c:if test="${mainUserFinRwardProList.objMap.mainUserFinRwardProList_level eq '二等'}">selected="selected"</c:if>>
                                二等
                            </option>
                            <option value="三等"
                                    <c:if test="${mainUserFinRwardProList.objMap.mainUserFinRwardProList_level eq '三等'}">selected="selected"</c:if>>
                                三等
                            </option>
                            <option value="无"
                                    <c:if test="${mainUserFinRwardProList.objMap.mainUserFinRwardProList_level eq '无'}">selected="selected"</c:if>>
                                无
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
            <th colspan="7" class="theader">九、近五年为第一或者通讯作者发表的SCI收录及中华级论文共<input type="text"
                                                                                name="totalmainUserAuthorProList"
                                                                                id="totalmainUserAuthorProList"
                                                                                value="${resultMap.totalmainUserAuthorProList}"
                                                                                class="inputText" style="width: 70px;"/>篇。（限填20项代表作,中华级论文影响因子统一填写0.5）
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="add('mainUserAuthorProList')"/>&#12288;
									<img title="删除" style="cursor: pointer;"
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick="delTr('mainUserAuthorProList');"/></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <td width="50px"></td>
            <td width="50px">序号</td>
            <td>发表时间</td>
            <td>论文题目</td>
            <td>发表期刊</td>
<c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
            <td>作者</td>
    </c:if>
            <td>影响因子</td>
        </tr>
        <tbody id="mainUserAuthorProListTb">
        <c:if test="${not empty resultMap.mainUserAuthorProList}">
            <c:forEach var="mainUserAuthorProList" items="${resultMap.mainUserAuthorProList}" varStatus="status">
                <tr>
                    <td width="50px" style="text-align: center;"><input name="mainUserAuthorProListIds"
                                                                        type="checkbox"/></td>
                    <td width="50px" style="text-align: center;" class="mainUserAuthorProListSerial"><input
                            name="mainUserAuthorProListSerialNum" type="text"
                            value="${mainUserAuthorProList.objMap.mainUserAuthorProListSerialNum}"
                            style="width:50px;border:0px; text-align: center;" readonly="readonly"/></td>
                    <td><input type="text" name="mainUserAuthorProList_date"
                               value="${mainUserAuthorProList.objMap.mainUserAuthorProList_date}"
                               class="inputText ctime invalidate[required]"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 90%"/>
                    </td>
                    <td><input type="text" name="mainUserAuthorProList_title"
                               value="${mainUserAuthorProList.objMap.mainUserAuthorProList_title}"
                               class="inputText invalidate[required]" style="width: 90%"/></td>
                    <td><input type="text" name="mainUserAuthorProList_post"
                               value="${mainUserAuthorProList.objMap.mainUserAuthorProList_post}"
                               class="inputText invalidate[required]" style="width: 90%"/></td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                    <td><input type="text" name="mainUserAuthorProList_author"
                               value="${mainUserAuthorProList.objMap.mainUserAuthorProList_author}"
                               class="inputText invalidate[required]" style="width: 90%"/></td>
                    </c:if>
                    <td><input type="text" name="mainUserAuthorProList_reason"
                               value="${mainUserAuthorProList.objMap.mainUserAuthorProList_reason}"
                               class="inputText invalidate[required]" style="width: 90%"/></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="bs_tb" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="7" class="theader">十、近五年为第一完成人的发明专利、新药证书共<input type="text" name="totalmainUserFinDevProList"
                                                                         id="totalmainUserFinDevProList"
                                                                         value="${resultMap.totalmainUserFinDevProList}"
                                                                         class="inputText" style="width: 70px;"/>张。（限填15项代表作）
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="add('mainUserFinDevProList')"/>&#12288;
									<img title="删除" style="cursor: pointer;"
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick="delTr('mainUserFinDevProList');"/></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <td width="50px"></td>
            <td width="50px">序号</td>
            <td>颁发时间</td>
            <td>名称</td>
<c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
            <td>完成人</td>
    </c:if>
            <td>发明专利或新药证书</td>
            <td>等级</td>
        </tr>
        <tbody id="mainUserFinDevProListTb">
        <c:if test="${not empty resultMap.mainUserFinDevProList}">
            <c:forEach var="mainUserFinDevProList" items="${resultMap.mainUserFinDevProList}" varStatus="status">
                <tr>
                    <td width="50px" style="text-align: center;"><input name="mainUserFinDevProListIds"
                                                                        type="checkbox"/></td>
                    <td width="50px" style="text-align: center;" class="mainUserFinDevProListSerial"><input
                            name="mainUserFinDevProListSerialNum" type="text"
                            value="${mainUserFinDevProList.objMap.mainUserFinDevProListSerialNum}"
                            style="width:50px;border:0px; text-align: center;" readonly="readonly"/></td>
                    <td><input type="text" name="mainUserFinDevProList_date"
                               value="${mainUserFinDevProList.objMap.mainUserFinDevProList_date}"
                               class="inputText ctime invalidate[required]"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 90%"/>
                    </td>
                    <td><input type="text" name="mainUserFinDevProList_name"
                               value="${mainUserFinDevProList.objMap.mainUserFinDevProList_name}"
                               class="inputText invalidate[required]" style="width: 90%"/></td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                    <td><input type="text" name="mainUserFinDevProList_finUser"
                               value="${mainUserFinDevProList.objMap.mainUserFinDevProList_finUser}"
                               class="inputText invalidate[required]" style="width: 90%"/></td>
                    </c:if>
                    <td><input type="text" name="mainUserFinDevProList_dev"
                               value="${mainUserFinDevProList.objMap.mainUserFinDevProList_dev}"
                               class="inputText invalidate[required]" style="width: 90%"/></td>
                    <td>
                        <select name="mainUserFinDevProList_level" class="inputText validate[required]"
                                style="width:90%;">
                            <option value="">请选择</option>
                            <option value="一等"
                                    <c:if test="${mainUserFinDevProList.objMap.mainUserFinDevProList_level eq '一等'}">selected="selected"</c:if>>
                                一等
                            </option>
                            <option value="二等"
                                    <c:if test="${mainUserFinDevProList.objMap.mainUserFinDevProList_level eq '二等'}">selected="selected"</c:if>>
                                二等
                            </option>
                            <option value="三等"
                                    <c:if test="${mainUserFinDevProList.objMap.mainUserFinDevProList_level eq '三等'}">selected="selected"</c:if>>
                                三等
                            </option>
                            <option value="无"
                                    <c:if test="${mainUserFinDevProList.objMap.mainUserFinDevProList_level eq '无'}">selected="selected"</c:if>>
                                无
                            </option>
                        </select>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <div class="button" style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
        <input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step8')" class="search" value="下一步"/>
    </div>
</form>

<div style="display: none">
    <!-- 8.近五年为第一完成人的省部级及以上科技进步奖模板 -->
    <table class="basic" id="mainUserFinRwardProListTemplate" style="width: 100%">
        <tr>
            <td width="50px" style="text-align: center;"><input name="mainUserFinRwardProListIds" type="checkbox"/></td>
            <td width="50px" style="text-align: center;" class="mainUserFinRwardProListSerial"><input
                    name="mainUserFinRwardProListSerialNum" type="text"
                    style="width:50px;border:0px; text-align: center;" readonly="readonly"/></td>
            <td><input type="text" name="mainUserFinRwardProList_date" class="inputText ctime invalidate[required]"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 90%"/></td>
            <td><input type="text" name="mainUserFinRwardProList_name" class="inputText invalidate[required]"
                       style="width: 90%"/></td>
            <td><input type="text" name="mainUserFinRwardProList_finUser" class="inputText invalidate[required]"
                       style="width: 90%"/></td>
            <td><input type="text" name="mainUserFinRwardProList_dept" class="inputText invalidate[required]"
                       style="width: 90%"/></td>
            <td>
                <select name="mainUserFinRwardProList_level" class="inputText validate[required]" style="width:90%;">
                    <option value="">请选择</option>
                    <option value="一等">一等</option>
                    <option value="二等">二等</option>
                    <option value="三等">三等</option>
                    <option value="无">无</option>
                </select>
            </td>
        </tr>
    </table>
    <!-- 9.近五年为第一作者发表的SCI收录及中华级论文模板 -->
    <table class="basic" id="mainUserAuthorProListTemplate" style="width: 100%">
        <tr>
            <td width="50px" style="text-align: center;"><input name="mainUserAuthorProListIds" type="checkbox"/></td>
            <td width="50px" style="text-align: center;" class="mainUserAuthorProListSerial"><input
                    name="mainUserAuthorProListSerialNum" type="text" style="width:50px;border:0px; text-align: center;"
                    readonly="readonly"/></td>
            <td><input type="text" name="mainUserAuthorProList_date" class="inputText ctime invalidate[required]"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 90%"/></td>
            <td><input type="text" name="mainUserAuthorProList_title" class="inputText invalidate[required]"
                       style="width: 90%"/></td>
            <td><input type="text" name="mainUserAuthorProList_post" class="inputText invalidate[required]"
                       style="width: 90%"/></td>
            <td><input type="text" name="mainUserAuthorProList_author" class="inputText invalidate[required]"
                       style="width: 90%"/></td>
            <td><input type="text" name="mainUserAuthorProList_reason" class="inputText invalidate[required]"
                       style="width: 90%"/></td>
        </tr>
    </table>
    <!-- 10.近五年为第一完成人的发明专利、新药证书模板 -->
    <table class="basic" id="mainUserFinDevProListTemplate" style="width: 100%">
        <tr>
            <td width="50px" style="text-align: center;"><input name="mainUserFinDevProListIds" type="checkbox"/></td>
            <td width="50px" style="text-align: center;" class="mainUserFinDevProListSerial"><input
                    name="mainUserFinDevProListSerialNum" type="text" style="width:50px;border:0px; text-align: center;"
                    readonly="readonly"/></td>
            <td><input type="text" name="mainUserFinDevProList_date" class="inputText ctime invalidate[required]"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 90%"/></td>
            <td><input type="text" name="mainUserFinDevProList_name" class="inputText invalidate[required]"
                       style="width: 90%"/></td>
            <td><input type="text" name="mainUserFinDevProList_finUser" class="inputText invalidate[required]"
                       style="width: 90%"/></td>
            <td><input type="text" name="mainUserFinDevProList_dev" class="inputText invalidate[required]"
                       style="width: 90%"/></td>
            <td>
                <select name="mainUserFinDevProList_level" class="inputText validate[required]" style="width:90%;">
                    <option value="">请选择</option>
                    <option value="一等">一等</option>
                    <option value="二等">二等</option>
                    <option value="三等">三等</option>
                    <option value="无">无</option>
                </select>
            </td>
        </tr>
    </table>
</div>

		