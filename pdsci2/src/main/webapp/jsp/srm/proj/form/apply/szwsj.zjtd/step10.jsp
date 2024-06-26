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



        $(document).ready(function(){
            if($(".teamMember tr").length<=0){
                add('teamMember');
            }
        });
        function add(templateId) {
            if (templateId) {
//                if ($('.' + templateId + ' .toDel').length < 10) {
                    $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                    reSeq(templateId);
                    //projCount(templateId);
                /*} else {
                    jboxTip('该项最多新增10条！');
                }*/
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
                    //projCount(templateId);
                }, null);
            }
        }

        function reSeq(templateId) {
            $('.' + templateId + ' .seq').each(function (i, n) {
                $(n).text(i + 1);
            });
        }

    </script>
</c:if>
<style type="text/css">
    .basic tbody td{
        text-align: center;
    }
</style>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" name="pageName" value="step10"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
    <font style="font-size: 14px; font-weight: bold; color: #333;"></font>
    <table class="basic" style="width: 100%;margin-top: 20px;">
        <tr>
            <th style="text-align: left" colspan="6">
                苏州市引进临床医学专家团队成员基本情况表
                <span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('teamMember')"/>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('teamMember');"/>
				</span>
            </th>
        </tr>
        <tr>
            <td colspan="3">申报单位（盖章）</td>
            <td colspan="2"><input type="text" class="validate inputText" name="teamApplyOrg" value="${resultMap.teamApplyOrg}" style="width: 90%;" /></td>
            <td>&#12288;&#12288;团队类别:<input type="text" class="validate inputText" name="teamType" value="${resultMap.teamType}" style="width: 50%;" /></td>
        </tr>
        <tr>
            <td colspan="3">团队输出单位</td>
            <td colspan="2"><input type="text" class="validate inputText" name="teamOutputOrg" value="${resultMap.teamOutputOrg}" style="width: 90%;" /></td>
            <td>合作项目名称：<input type="text" class="validate inputText" name="jointProjName" value="${resultMap.jointProjName}" style="width: 50%;" /></td>
        </tr>
        <tr>
            <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
            <td width="5%"></td>
            </c:if>
            <td <c:if test="${param.view eq GlobalConstant.FLAG_Y }">colspan="2"</c:if> width="5%">序号</td>
            <td width="15%">类别</td>
            <td width="20%">姓名</td>
            <td width="25%">承担的主要工作</td>
            <td width="30%">承诺每年在苏工作时间（单位：月）</td>
        </tr>
        <tbody class="teamMember">
        <c:forEach var="teamMember" items="${resultMap.teamMember}" varStatus="status">
            <tr>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                    <td style="text-align: center"><input class="toDel" type="checkbox" /></td>
                </c:if>
                <td <c:if test="${param.view eq GlobalConstant.FLAG_Y }">colspan="2"</c:if> style="text-align: center;" class="seq">${status.count}</td>
                <td><input type="text" class="validate inputText" name="teamMember_type" value="${teamMember.objMap.teamMember_type}" style="width: 90%;" /></td>
                <td><input type="text" class="validate inputText" name="teamMember_name" value="${teamMember.objMap.teamMember_name}" style="width: 90%;" /></td>
                <td><input type="text" class="validate inputText" name="teamMember_content" value="${teamMember.objMap.teamMember_content}" style="width: 90%;" /></td>
                <td><input type="text" class="validate inputText" name="teamMember_workTime" value="${teamMember.objMap.teamMember_workTime}" style="width: 90%;" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>

<div class="button" style="width:100%;
<c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none;</c:if>">
    <input id="prev" type="button" onclick="nextOpt('step9')" class="search" value="上一步"/>
    <input id="nxt" type="button" onclick="nextOpt('step11')" class="search" value="下一步"/>
</div>
<div style="display: none;">
    <!-- 模板 -->
    <table >
        <tr id="teamMember">
            <td style="text-align: center"><input class="toDel" type="checkbox" /></td>
            <td style="text-align: center;" class="seq">${status.count}</td>
            <td><input type="text" class="inputText" name="teamMember_type" style="width: 90%" /></td>
            <td><input type="text" class="inputText" name="teamMember_name" style="width: 90%" /></td>
            <td><input type="text" class="inputText" name="teamMember_content" style="width: 90%" /></td>
            <td><input type="text" class="inputText" name="teamMember_workTime" style="width: 90%" /></td>
        </tr>
    </table>
</div>