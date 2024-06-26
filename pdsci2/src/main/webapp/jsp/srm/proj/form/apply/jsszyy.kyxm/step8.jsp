<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script type="text/javascript">
    function nextOpt(step){
        if(false==$("#projForm").validationEngine("validate")){
            return false;
        }
        var form = $('#projForm');
        form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
        $('#nxt').attr({"disabled":"disabled"});
        $('#prev').attr({"disabled":"disabled"});
        jboxStartLoading();
        form.submit();
    }

    function add(templateId,total) {
        if (templateId) {
            if ($('.' + templateId + ' .toDel').length < total) {
                $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
             //   reSeq(templateId);
            } else {
                jboxTip('该项最多新增'+total+'条！');
            }
        }
    }

    function del(templateId) {
        if (templateId) {
            if (!$('.' + templateId + ' .toDel:checked').length) {
                return jboxTip('请选择需要删除的项目！');
            }
            jboxConfirm('确认删除？', function () {
                $('.' + templateId + ' .toDel:checked').closest('tr').remove();
              //  reSeq(templateId);
            }, null);
        }
    }

    function reSeq(templateId) {
        $('.' + templateId + ' .seq').each(function (i, n) {
            $(n).text(i + 1);
        });
    }
    $(function () {
        $('#template tr').each(function(){
            var id = this.id;
            if(id){
                if(!$('.'+id+' .toDel').length){
                    add(id,1);
                }
            }
        });
    });
</script>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;" id="projForm">
    <input type="hidden" id="pageName" name="pageName" value="step8" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333; ">七、实施计划、考核指标</font>
    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="5" style="text-align: left">
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('startingPlan',99)"/>&#12288;
									<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('startingPlan');"/></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <th style="text-align: center" width="50px"></td>
            <th style="text-align: center" width="20%">时间安排</th>
            <th style="text-align: center" width="30%">研究内容(分期目标)</th>
            <th style="text-align: center" width="30%">考核指标</th>
            <%--<th style="text-align: center" width="12%">经费预算</th>--%>
        </tr>

        <tbody class="startingPlan">
        <c:if test="${not empty resultMap.startingPlan}">
            <c:forEach var="startingPlan" items="${resultMap.startingPlan}" varStatus="status">
                <tr>
                    <td style="text-align: center"><input type="checkbox" class="toDel"></td>
                    <td>
                        <input type="text" name="startingPlan_time" value="${startingPlan.objMap.startingPlan_time}" class="inputText" style="width: 90%"/>
                    </td>
                    <td>
                        <textarea class="xltxtarea" style="height: 50px;" name="startingPlan_content">${startingPlan.objMap.startingPlan_content}</textarea>
                    </td>
                    <td>
                        <textarea  class="xltxtarea" style="height: 50px;" name="startingPlan_index">${startingPlan.objMap.startingPlan_index}</textarea>
                    </td>
                    <%--<td><input type="text" name="startingPlan_budget" value="${startingPlan.objMap.startingPlan_budget}" class="inputText" style="width: 90%"/></td>--%>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
        <tr>
            <td></td>
            <td style="text-align: center">
                其它
            </td>
            <td>
                <textarea class="xltxtarea" style="height: 50px;" name="otherPlan_content">${resultMap.otherPlan_content}</textarea>
            </td>
            <td>
                <textarea  class="xltxtarea" style="height: 50px;" name="otherPlan_index">${resultMap.otherPlan_index}</textarea>
            </td>
            <%--<td><input type="text" name="otherPlan_budget" value="${resultMap.otherPlan_budget}" class="inputText" style="width: 90%"/></td>--%>
        </tr>
    </table>
    <div class="button" style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
        <input id="prev" type="button" onclick="nextOpt('step7')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step9')" class="search" value="保&#12288;存"/>
    </div>
</form>

<div style="display: none;">
    <table id="template" style="display: none">
        <tr id="startingPlan">
            <td style="text-align: center"><input type="checkbox" class="toDel"></td>
            <td>
                <input type="text" name="startingPlan_time" class="inputText" style="width: 90%"/>
            </td>
            <td>
                <textarea class="xltxtarea" style="height: 50px;" name="startingPlan_content"></textarea>
            </td>
            <td>
                <textarea  class="xltxtarea" style="height: 50px;" name="startingPlan_index"></textarea>
            </td>
            <%--<td><input type="text" name="startingPlan_budget" class="inputText" style="width: 90%"/></td>--%>
        </tr>
    </table>
</div>

