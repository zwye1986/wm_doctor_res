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
<style>
    .basic td {
        text-align: center;
        padding-left: 0px;
    }
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
      id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step9_1"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;"></font>
    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="4" style="text-align: left;padding-left: 15px;">
                十、依托科室条件
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('supportedDeptList');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('supportedDeptList');"/>
						</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <td style="text-align: center;" width="5%">选择</td>
            </c:if>
            <td style="text-align: center;" width="5%">序号</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>时间</td>
            <td style="text-align: center;" width="20%"><font color="red">*</font>名称</td>
        </tr>
        <tbody class="supportedDeptList">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="supportedDeptList" items="${resultMap.supportedDeptList}"
                       varStatus="supportedDeptListStatus">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${supportedDeptListStatus.count}</td>
                    <td>
                        <input type="text" class="inputText validate[required,maxSize[100]]" name="supportedDeptList_time"
                               value="${supportedDeptList.objMap.supportedDeptList_time}" style="width: 90%;"/>
                    </td>
                    <td>
                        <select name="supportedDeptList_name" class="inputText validate[required]" style="width: 90%;">
                            <option value="">请选择</option>
                            <option value="国家级临床重点专科" <c:if test="${supportedDeptList.objMap.supportedDeptList_name eq '国家级临床重点专科'}">selected="selected"</c:if>>国家级临床重点专科</option>
                            <option value="国家级临床重点学科（实验室）"<c:if test="${supportedDeptList.objMap.supportedDeptList_name eq '国家级临床重点学科（实验室）'}">selected="selected"</c:if>>国家级临床重点学科（实验室）</option>
                            <option value="省级临床重点专科"<c:if test="${supportedDeptList.objMap.supportedDeptList_name eq '省级临床重点专科'}">selected="selected"</c:if>>省级临床重点专科</option>
                            <option value="省级临床重点学科（实验室）"<c:if test="${supportedDeptList.objMap.supportedDeptList_name eq '省级临床重点学科（实验室）'}">selected="selected"</c:if>>省级临床重点学科（实验室）</option>
                            <option value="省级妇幼保健重点学科"<c:if test="${supportedDeptList.objMap.supportedDeptList_name eq '省级妇幼保健重点学科'}">selected="selected"</c:if>>省级妇幼保健重点学科</option>
                            <option value="全军医学研究所"<c:if test="${supportedDeptList.objMap.supportedDeptList_name eq '全军医学研究所'}">selected="selected"</c:if>>全军医学研究所</option>
                            <option value="全军医学专病中心"<c:if test="${supportedDeptList.objMap.supportedDeptList_name eq '全军医学专病中心'}">selected="selected"</c:if>>全军医学专病中心</option>
                            <option value="其他"<c:if test="${supportedDeptList.objMap.supportedDeptList_name eq '其他'}">selected="selected"</c:if>>其他</option>
                        </select>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
            <c:forEach var="supportedDeptList" items="${resultMap.supportedDeptList}"
                       varStatus="supportedDept_nameStatus">
                <tr>
                    <td>${supportedDept_nameStatus.count}</td>
                    <td>
                            ${supportedDeptList.objMap.supportedDeptList_time}
                    </td>
                    <td>
                            ${supportedDeptList.objMap.supportedDeptList_name}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step9_0')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step10')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>

<table id="template" style="display: none">
    <tr id="supportedDeptList">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" class="inputText validate[required,maxSize[100]]" name="supportedDeptList_time" style="width: 90%;"/>
        </td>
        <td>
            <select name="supportedDeptList_name" class="inputText validate[required]" style="width: 90%;">
                <option value="">请选择</option>
                <option value="国家级临床重点专科">国家级临床重点专科</option>
                <option value="国家级临床重点学科（实验室）">国家级临床重点学科（实验室）</option>
                <option value="省级临床重点专科">省级临床重点专科</option>
                <option value="省级临床重点学科（实验室）">省级临床重点学科（实验室）</option>
                <option value="省级妇幼保健重点学科">省级妇幼保健重点学科</option>
                <option value="全军医学研究所">全军医学研究所</option>
                <option value="全军医学专病中心">全军医学专病中心</option>
                <option value="其他">其他</option>
            </select>
        </td>
    </tr>
</table>
