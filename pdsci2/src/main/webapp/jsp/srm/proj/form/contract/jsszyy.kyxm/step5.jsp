<c:if test="${param.view != GlobalConstant.FLAG_Y}">

    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            form.append("<input name='nextPageName' value='" + step + "' type='hidden'/>");
            $('#nxt').attr({"disabled": "disabled"});
            $('#prev').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }
        function checkBDDate(dt) {
            var dates = $(':text', $(dt).closest("td"));
            if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                jboxTip("开始时间不能大于结束时间！");
                dt.value = "";
            }
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
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step5"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">项目年度计划及考核指标（以半年为单位）</font>
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
        <c:if test="${empty resultMap.startingPlan}">
            <c:forEach var="startingPlan" items="${applyMap.startingPlan}" varStatus="status">
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
                <textarea class="xltxtarea" style="height: 50px;" name="otherPlan_content"><c:if test="${empty resultMap.otherPlan_content}">${applyMap.otherPlan_content}</c:if>${resultMap.otherPlan_content}</textarea>
            </td>
            <td>
                <textarea  class="xltxtarea" style="height: 50px;" name="otherPlan_index"><c:if test="${empty resultMap.otherPlan_index}">${applyMap.otherPlan_index}</c:if>${resultMap.otherPlan_index}</textarea>
            </td>
        </tr>
    </table>

    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="保&#12288;存"/>
        </div>
    </c:if>
</form>

<div style="display: none">
    <!-- 模板 -->
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