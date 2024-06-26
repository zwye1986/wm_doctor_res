<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>
    .basic td {
        text-align: center;
        padding-left: 0px;
    }
</style>
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

        $(function () {
		$('#template tr').each(function(){
			var id = this.id;
			if(id){
				if(!$('.'+id+' .toDel').length){
					add(id);
				}
			}
		});
        });
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
      id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step6"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

    <table class="basic" style="width: 100%">
        <tr>
            <th style="text-align: left;padding-left: 15px;" colspan="9">
                六、课题组成员名单
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('memberList');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('memberList');"/>
						</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <td style="text-align: center;" width="5%">选择</td>
            </c:if>
            <td style="text-align: center;" width="5%">序号</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>姓名</td>
            <td style="text-align: center;" width="5%"><font color="red">*</font>年龄</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>学历</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>专业</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>技术职称</td>
            <td style="text-align: center;" width="20%"><font color="red">*</font>所在单位及科室</td>
            <td style="text-align: center;" width="20%"><font color="red">*</font>承担主要任务</td>
        </tr>
        <tbody class="memberList">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="memberList" items="${resultMap.memberList}" varStatus="memberListStatus">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${memberListStatus.count}</td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="memberList_name"
                               value="${memberList.objMap.memberList_name}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required,custom[integer]]" name="memberList_age"
                               value="${memberList.objMap.memberList_age}" style="width: 80%"/>
                    </td>
                    <td>
                        <select name="memberList_education" class="inputText validate[required]" style="width: 80%;">
                            <option value="">请选择</option>
                            <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                <option value="${dict.dictName }"
                                        <c:if test="${memberList.objMap.memberList_education eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                            </c:forEach>
                        </select>
                        <%--<input type="text" class="inputText validate[required]" name="memberList_education"--%>
                               <%--value="${memberList.objMap.memberList_education}" style="width: 80%"/>--%>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="memberList_spe"
                               value="${memberList.objMap.memberList_spe}" style="width: 80%"/>
                    </td>
                    <td>
                        <select name="memberList_title" class="inputText validate[required]" style="width: 80%;">
                            <option value="">请选择</option>
                            <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                <option value="${dict.dictName }"
                                        <c:if test="${memberList.objMap.memberList_title eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                            </c:forEach>
                        </select>
                        <%--<input type="text" class="inputText validate[required]" name="memberList_title"--%>
                               <%--value="${memberList.objMap.memberList_title}" style="width: 80%"/>--%>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="memberList_dept"
                               value="${memberList.objMap.memberList_dept}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="memberList_bearResponsibility"
                               value="${memberList.objMap.memberList_bearResponsibility}" style="width: 80%"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
            <c:forEach var="memberList" items="${resultMap.memberList}" varStatus="memberListStatus">
                <tr>
                    <td>${memberListStatus.count}</td>
                    <td>
                            ${memberList.objMap.memberList_name}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_age}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_education}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_spe}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_title}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_dept}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_bearResponsibility}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step6_1')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>

<table id="template" style="display: none">
    <tr id="memberList">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_name" value="" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required,custom[integer]]" name="memberList_age" value=""
                   style="width: 80%"/>
        </td>
        <td>
            <select name="memberList_education" class="inputText validate[required]" style="width: 80%;">
                <option value="">请选择</option>
                <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                    <option value="${dict.dictName }">${dict.dictName }</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_spe" value="" style="width: 80%"/>
        </td>
        <td>
            <select name="memberList_title" class="inputText validate[required]" style="width: 80%;">
                <option value="">请选择</option>
                <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                    <option value="${dict.dictName }">${dict.dictName }</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_dept" value=""
                   style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_bearResponsibility" value=""
                   style="width: 80%"/>
        </td>
    </tr>
</table>


