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
        function add(templateId) {
            if (templateId) {
                $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
               // reSeq(templateId);
            }
        }

        function del(templateId) {
            if (templateId) {
                if (!$('.' + templateId + ' .toDel:checked').length) {
                    return jboxTip('请选择需要删除的项目！');
                }
                jboxConfirm('确认删除？', function () {
                    $('.' + templateId + ' .toDel:checked').closest('tr').remove();
                   // reSeq(templateId);
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
<style>
    .basic .inputText {
        text-align: left;
    }
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step9"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;margin-top: 20px">八、主要研究人员</font>
    <table class="basic" style="width: 100%">
        <tr>
            <th style="text-align: left;padding-left: 15px;" colspan="9">
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
                <td  width="5%">选&#12288;择</td>
            </c:if>
            <td  width="10%"><font color="red">*</font>单&#12288;位</td>
            <td  width="5%"><font color="red">*</font>姓&#12288;名</td>
            <td  width="10%"><font color="red">*</font>性&#12288;别</td>
            <td  width="10%"><font color="red">*</font>年&#12288;龄</td>
            <td  width="10%"><font color="red">*</font>专&#12288;业</td>
            <td  width="20%"><font color="red">*</font>职&#12288;务</td>
            <td  width="20%"><font color="red">*</font>在本项目中的主要工作</td>
        </tr>
        <tbody class="memberList">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="memberList" items="${resultMap.memberList}" varStatus="memberListStatus">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="memberList_org"
                               value="${memberList.objMap.memberList_org}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="memberList_name"
                               value="${memberList.objMap.memberList_name}" style="width: 80%"/>
                    </td>
                    <td>
                        <select name="memberList_sex" class="inputText validate[required]" style="width: 80%;">
                            <option value="">请选择</option>
                                <option value="男" <c:if test="${memberList.objMap.memberList_sex eq '男'}">selected="selected"</c:if>>男</option>
                                <option value="女" <c:if test="${memberList.objMap.memberList_sex eq '女'}">selected="selected"</c:if>>女</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required,custom[integer]]" name="memberList_age"
                               value="${memberList.objMap.memberList_age}" style="width: 80%"/>
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
                    <td>
                            ${memberList.objMap.memberList_org}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_name}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_sex}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_age}
                    </td>

                    <td>
                            ${memberList.objMap.memberList_spe}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_title}
                    </td>

                    <td>
                            ${memberList.objMap.memberList_bearResponsibility}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step8')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step10')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>
<table id="template" style="display: none">
    <tr id="memberList">
        <td><input type="checkbox" class="toDel"></td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_org" value=""
                   style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_name" value="" style="width: 80%"/>
        </td>
        <td>
            <select name="memberList_sex" class="inputText validate[required]" style="width: 80%;">
                <option value="">请选择</option>
                <option value="男" <c:if test="${memberList.objMap.memberList_sex eq '男'}">selected="selected"</c:if>>男</option>
                <option value="女" <c:if test="${memberList.objMap.memberList_sex eq '女'}">selected="selected"</c:if>>女</option>
            </select>
        </td>
        <td>
            <input type="text" class="inputText validate[required,custom[integer]]" name="memberList_age" value=""
                   style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_spe" value="" style="width: 80%"/>
        </td>
        <td>
            <select name="memberList_title" class="inputText validate[required]" style="width: 80%;">
                <option value="">请选择</option>
                <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                    <option value="${dict.dictName }"
                            <c:if test="${memberList.objMap.memberList_title eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_bearResponsibility" value=""
                   style="width: 80%"/>
        </td>
    </tr>
</table>