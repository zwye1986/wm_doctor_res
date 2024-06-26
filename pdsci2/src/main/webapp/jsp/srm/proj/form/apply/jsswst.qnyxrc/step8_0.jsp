<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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

        function add(templateId) {
            if (templateId) {
                $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                reSeq(templateId);
            }
        }

        function del(templateId) {
            if (templateId) {
                if (!$('.' + templateId + ' .toDel:checked').length) {
                    return jboxTip('请选择需要删除的项目！');
                }
                jboxConfirm('确认删除？', function () {
                    $('.' + templateId + ' .toDel:checked').closest('tr').remove();
                    reSeq(templateId);
                    reasonCount();
                }, null);
            }
        }

        function reSeq(templateId) {
            $('.' + templateId + ' .seq').each(function (i, n) {
                $(n).text(i + 1);
            });
        }
        function reasonCount() {
            var count = 0;
            var re = /^[0-9]+.?[0-9]*$/;
            var res = /^[0-9]+.?[0-9]{0,3}$/;
            var reasonNums = $(".learningPapersCensus").find("input[name = 'learningPapersCensus_IF']");
            $.each(reasonNums, function (index, element) {
                var reasonNum = $(element).val();
                if (re.test(reasonNum)) {
                    if (!res.test(reasonNum)) {//如果小数点后超过3位则四舍五入保留3位
                        reasonNum = parseFloat(reasonNum).toFixed(3);
                        $(element).val(reasonNum);
                    }
                    count = count + parseFloat(reasonNum);
                }
                // alert(count);
            });
            if (!res.test(count)) {
                count = count.toFixed(3);
            }
            $("#learningPaperReasonCount").val(count);
        }
    </script>
</c:if>
<style>
    .basic td {
        text-align: center;
        padding-left: 0px;
    }
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
      id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step8_0"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">八、影响因子统计</font>
    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="8" style="text-align: left;padding-left: 15px;">
                近5年作为第一或通讯作者发表SCI和中华级学术论文统计表（中华级论文IF统一填写0.5）,影响因子合计
                <input type="text" id="learningPaperReasonCount" name="learningPaperReasonCount"
                       class="inputText" value="${resultMap.learningPaperReasonCount}"
                       style="width: 50px;" readonly="readonly"/>。
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('learningPapersCensus');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('learningPapersCensus');"/>
						</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <td style="text-align: center;" width="5%">选择</td>
            </c:if>
            <td style="text-align: center;" width="5%">序号</td>
            <td style="text-align: center;" width="25%"><font color="red">*</font>论文名称</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>发表时间</td>
            <td style="text-align: center;" width="25%"><font color="red">*</font>刊物名称</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>卷-期-页</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>本人排名</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>IF</td>
        </tr>
        <tbody class="learningPapersCensus">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="learningPapersCensus" items="${resultMap.learningPapersCensus}"
                       varStatus="learningPapersCensusStatus">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${learningPapersCensusStatus.count}</td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="learningPapersCensus_name"
                               value="${learningPapersCensus.objMap.learningPapersCensus_name}" style="width: 90%;"/>
                    </td>
                    <td>
                        <input type="text" class="inputText ctime validate[required]" name="learningPapersCensus_publishDate"
                               value="${learningPapersCensus.objMap.learningPapersCensus_publishDate}"
                               onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" style="width: 90%;"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]"
                               name="learningPapersCensus_publicactionName"
                               value="${learningPapersCensus.objMap.learningPapersCensus_publicactionName}"
                               style="width: 90%;"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="learningPapersCensus_place"
                               value="${learningPapersCensus.objMap.learningPapersCensus_place}" style="width: 90%;"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="learningPapersCensus_rank"
                               value="${learningPapersCensus.objMap.learningPapersCensus_rank}" style="width: 90%;"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required,custom[positiveNum]]"
                               name="learningPapersCensus_IF"
                               value="${learningPapersCensus.objMap.learningPapersCensus_IF}" style="width: 90%;"
                               onchange="reasonCount()"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
            <c:forEach var="learningPapersCensus" items="${resultMap.learningPapersCensus}"
                       varStatus="supportedDept_nameStatus">
                <tr>
                    <td style="text-align: center;">${supportedDept_nameStatus.count}</td>
                    <td style="text-align: center;">
                            ${learningPapersCensus.objMap.learningPapersCensus_name}
                    </td>
                    <td style="text-align: center;">
                            ${learningPapersCensus.objMap.learningPapersCensus_publishDate}
                    </td>
                    <td style="text-align: center;">
                            ${learningPapersCensus.objMap.learningPapersCensus_publicactionName}
                    </td>
                    <td style="text-align: center;">
                            ${learningPapersCensus.objMap.learningPapersCensus_place}
                    </td>
                    <td style="text-align: center;">
                            ${learningPapersCensus.objMap.learningPapersCensus_rank}
                    </td>
                    <td style="text-align: center;">
                            ${learningPapersCensus.objMap.learningPapersCensus_IF}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step8')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step8_1')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>

<table id="template" style="display: none">
    <tr id="learningPapersCensus">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" class="inputText validate[required]" name="learningPapersCensus_name"
                   style="width: 90%;"/>
        </td>
        <td>
            <input type="text" class="inputText ctime validate[required]" name="learningPapersCensus_publishDate"
                   onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" style="width: 90%;"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="learningPapersCensus_publicactionName"
                   style="width: 90%;"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="learningPapersCensus_place"
                   style="width: 90%;"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="learningPapersCensus_rank"
                   style="width: 90%;"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required,custom[positiveNum]]" name="learningPapersCensus_IF"
                   style="width: 90%;" onchange="reasonCount()"/>
        </td>
    </tr>
</table>
