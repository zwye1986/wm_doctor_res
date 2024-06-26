<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
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

        function add(templateId,total) {
            if (templateId) {
                if ($('.' + templateId + ' .toDel').length < total) {
                    $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                    reSeq(templateId);
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
                    reSeq(templateId);
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
        $(document).ready(function(){
            var ue1 = initUEditer("jobSummary");
        });
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" name="pageName" value="step7"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333; ">六、研究基础与工作条件</font>
    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="7" style="text-align: left">
                <p style="line-height: 25px">1．与本课题相关的以往研究工作摘要（只需列明题目、发表论文出处、第几完成单位、研究内容论点和创新点摘要。对应的详细资料需按要求纳入申请书附件中）</p>
            </th>
        </tr>
        <tr>
            <td colspan="7" style="text-align:left;">
                <%--<textarea placeholder=""  class="xltxtarea" style="height: 150px;" name="jobSummary">${resultMap.jobSummary}</textarea>--%>
                <c:choose>
                    <c:when test="${param.view==GlobalConstant.FLAG_Y}">${resultMap.specialAndResult}</c:when>
                    <c:otherwise>
                        <script id="jobSummary" type="text/plain" name="jobSummary" style="width:100%;height:200px;margin-right: 10px;">${resultMap.jobSummary}</script>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <th colspan="7" style="text-align: left">
                2．本课题将使用的主要科研设备、仪器、试剂、实验动物等条件
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('topicCondition',99)"/>&#12288;
									<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('topicCondition');"/></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <th style="text-align: center" width="50px"></th>
            <th style="text-align: center" width="50px">序号</th>
            <th style="text-align: center" width="20%">名称</th>
            <th style="text-align: center" width="10%">规格</th>
            <th style="text-align: center" width="20%">产地\生产商</th>
            <th style="text-align: center" width="15%">操作部门</th>
            <th style="text-align: center" width="25%">备注</th>
        </tr>

        <tbody class="topicCondition">
        <c:if test="${not empty resultMap.topicCondition}">
            <c:forEach var="topicCondition" items="${resultMap.topicCondition}" varStatus="status">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${status.count}</td>
                    <td><input type="text" name="topicCondition_name" value="${topicCondition.objMap.topicCondition_name}" class="inputText" style="width: 90%"/></td>
                    <td><input type="text" name="topicCondition_type" value="${topicCondition.objMap.topicCondition_type}" class="inputText" style="width: 90%"/></td>
                    <td><input type="text" name="topicCondition_area" value="${topicCondition.objMap.topicCondition_area}" class="inputText" style="width: 90%"/></td>
                    <td><input type="text" name="topicCondition_dept" value="${topicCondition.objMap.topicCondition_dept}" class="inputText" style="width: 90%"/></td>
                    <td><input type="text" name="topicCondition_remark" value="${topicCondition.objMap.topicCondition_remark}" class="inputText" style="width: 90%"/></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step8')" class="search" value="保&#12288;存"/>
    </div>
</c:if>

<div style="display: none;">
    <table id="template" style="display: none">
        <tr id="topicCondition">
            <td><input type="checkbox" class="toDel"></td>
            <td class="seq"></td>
            <td><input type="text" name="topicCondition_name" class="inputText" style="width: 90%"/></td>
            <td><input type="text" name="topicCondition_type"  class="inputText" style="width: 90%"/></td>
            <td><input type="text" name="topicCondition_area"  class="inputText" style="width: 90%"/></td>
            <td><input type="text" name="topicCondition_dept"  class="inputText" style="width: 90%"/></td>
            <td><input type="text" name="topicCondition_remark"  class="inputText" style="width: 90%"/></td>
        </tr>
    </table>
</div>
