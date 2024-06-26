<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script>
        function saveForm(canEditAppendix) {
            if ($("#editform").validationEngine("validate")) {
                autoValue($("#editform"), "autoValue");
                var url="<s:url value='/res/rec/saveRegistryForm?canEditAppendix='/>"+canEditAppendix;
                jboxSubmit($("#editform"), url, function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        parentRefresh();
                        jboxClose();
                    }
                }, function (resp) {
                    jboxTip("${GlobalConstant.SAVE_FAIL}");
                }, true);
            }
        }

        function parentRefresh() {
            window.parent.frames['mainIframe'].window.loadProcess();
        }
        function moveTr(obj) {
            jboxConfirm("确认删除？", function () {
                var flowinput = $(obj).parents("td").find("input[name='visits_accessoriesFlow']");
                var nameinput = $(obj).parents("td").find("input[name='visits_accessoriesName']");
                var fileinput = $(obj).parents("td").find("input[name='visits_accessories']");
                flowinput.attr("value", "");
                nameinput.attr("value", "");
                fileinput.remove();
                var tr = obj.parentNode;
                $("#fileIsRe").val("Y");
                $(tr).after("<li><input type='file' name='visits_accessories'/></li>");
                tr.remove();
                var trParent = obj.parentNode.parentNode;

            });
        }
        function addMember() {
            $("#memberTb").append($("#memberTemplate tr:eq(0)").clone());
        }
        function delMember(tb) {
            var checkboxs = $("input[name='memberIds']:checked");
            if (checkboxs.length == 0) {
                jboxTip("请勾选要删除的！");
                return false;
            }

            var trs = $("#memberTable").find("tr");
            if (checkboxs.length == trs.length - 2) {
                jboxTip("至少填写一组数据！");
                return false;
            }
            jboxConfirm("确认删除?", function () {
                var trs = $('#memberTb').find(':checkbox:checked');
                $.each(trs, function (i, n) {
                    $(n).parent('td').parent("tr").remove();
                });
            });
        }
        function addFollow() {
            $("#followTb").append($("#followTemplate tr:eq(0)").clone());
        }
        function delFollow() {
            var checkboxs = $("input[name='followIds']:checked");
            if (checkboxs.length == 0) {
                jboxTip("请勾选要删除的！");
                return false;
            }

            var trs = $("#followTable").find("tr");
            if (checkboxs.length == trs.length - 2) {
                jboxTip("至少填写一组数据！");
                return false;
            }
            jboxConfirm("确认删除?", function () {
                var trs = $('#followTb').find(':checkbox:checked');
                $.each(trs, function (i, n) {
                    $(n).parent('td').parent("tr").remove();
                });
            });
        }
        $(function(){
            <c:if test="${rec.auditStatusId eq recStatusEnumTeacherAuditY.id || !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR) || param.isRead}">
            $("#editform").find(":text,textarea").each(function(){
                $(this).attr("readonly","readonly");
            });
            $("#editform select").each(function(){
                var text = $(this).find(":selected").text().trim();
                var name =  $(this).attr("name");
                var value =  $(this).attr("value");
                $(this).replaceWith($('<input readonly  value="'+text+'"/><input readonly hidden name="'+name+'" value="'+value+'"/>'));
            });
            $("#editform").find(":checkbox,:radio").attr("disabled",true);
            $("#editform").find("input[name='visits_time']").attr("onclick","");
            </c:if>
        });
    </script>
</head>
<body>
<c:set var="canEditAppendix" value="${rec.auditStatusId eq recStatusEnumTeacherAuditY.id &&(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}"/>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="editform" enctype="multipart/form-data" method="post">
                <input type="hidden" name="formFileName" value="${formFileName}"/>
                <input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
                <input type="hidden" name="recFlow" value="${rec.recFlow}"/>
                <input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
                <input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
                <table class="basic" width="100%">
                    <tr>
                        <th colspan="4" style="text-align: left;">&#12288;家庭信息</th>
                    </tr>
                    <tr>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>户主姓名：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input class="validate[required]" name="householderName" type="text"
                                       value="${formDataMap['householderName']}" style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['householderName']}</label>
                            </c:if>
                        </td>
                        <td style="width: 120px;text-align: right;">家庭住址：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input name="address" type="text" value="${formDataMap['address']}"
                                       style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['address']}</label>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 120px;text-align: right;">邮编：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input name="zipCode" type="text" value="${formDataMap['zipCode']}"
                                       style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['zipCode']}</label>
                            </c:if>
                        </td>
                        <td style="width: 120px;text-align: right;">联系电话：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input name="telephoneNumber" type="text" value="${formDataMap['telephoneNumber']}"
                                       style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['telephoneNumber']}</label>
                            </c:if>
                        </td>
                    </tr>
                </table>
                <table id="memberTable" class="basic" width="100%" style="margin-top: 20px;">
                    <tr>
                        <th colspan="6" style="text-align: left;">&#12288;成员信息
                            <c:if test="${!empty doctor}">
                                <span style="float: right;margin-right: 10px;margin-bottom: 5px;">
                                    <img class='opBtn' title='删除' style='cursor: pointer;'
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick='delMember();'/>
                                    <img class="opBtn" title="新增"
                                         src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="addMember();"/>
                                </span>
                            </c:if>
                        </th>
                    </tr>
                    <tr>
                        <c:if test="${!empty doctor}">
                            <th width="4%"></th>
                        </c:if>
                        <th style="width: 60px;text-align: center;"><font color="red">*</font>姓名</th>
                        <th style="width: 60px;text-align: center;"><font color="red">*</font>性别</th>
                        <th style="width: 60px;text-align: center;"><font color="red">*</font>年龄</th>
                        <th style="width: 120px;text-align: center;"><font color="red">*</font>健康状况</th>
                        <th style="width: 120px;text-align: center;">备注</th>
                    </tr>
                    <tbody id="memberTb">
                    <c:forEach var="memberMap" items="${formDataMap['familyMemberInfo']}" varStatus="status">
                        <tr>
                            <c:if test="${!empty doctor}">
                                <td width="4%">
                                    <input type="checkbox" name="memberIds"/>
                                </td>
                            </c:if>
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input class="validate[required]" name="member_name" type="text"
                                           value="${memberMap['member_name']}" style="width: 60px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ memberMap['member_name']}</label>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${!empty doctor}">
                                    <select name="member_gender" style="width: 65px;"
                                            class="autoValue validate[required]">
                                        <option value="">请选择</option>
                                        <option value="Man"
                                                <c:if test="${'Man' eq memberMap['member_gender_id']}">selected</c:if>>
                                            男
                                        </option>
                                        <option value="Woman"
                                                <c:if test="${'Woman' eq memberMap['member_gender_id']}">selected</c:if>>
                                            女
                                        </option>
                                    </select>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ memberMap['member_gender']}</label>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input class="validate[required]" name="member_age" type="text"
                                           value="${memberMap['member_age']}" style="width: 60px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ memberMap['member_age']}</label>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${!empty doctor}">
                                    <textarea style="width: 92%;height: 90%;" class="validate[required]"
                                              name="member_health_statues">${memberMap['member_health_statues']}</textarea>
                                </c:if>
                                <c:if test="${empty doctor}">
                                        <span>
                                                ${ memberMap['member_health_statues']}
                                        </span>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${!empty doctor}">
                                    <textarea style="width: 92%;height: 90%;"
                                              name="member_remarks">${memberMap['member_remarks']}</textarea>
                                </c:if>
                                <c:if test="${empty doctor}">
                                        <span>
                                                ${ memberMap['member_remarks']}
                                        </span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty formDataMap['familyMemberInfo']}">
                        <tr>
                            <c:if test="${!empty doctor}">
                                <td width="4%">
                                    <input type="checkbox" name="memberIds"/>
                                </td>
                            </c:if>
                            <td>
                                <input class="validate[required]" name="member_name" type="text" value=""
                                       style="width: 60px;"/>
                            </td>
                            <td>
                                <select name="member_gender" style="width: 65px;"
                                        class="autoValue validate[required]">
                                    <option value="">请选择</option>
                                    <option value="Man">男</option>
                                    <option value="Woman">女</option>
                                </select>
                            </td>
                            <td>
                                <input name="member_age" class="validate[required]" type="text" value=""
                                       style="width: 60px;"/>
                            </td>
                            <td>
                                    <textarea style="width: 92%;height: 90%;" class="validate[required]"
                                              name="member_health_statues"></textarea>
                            </td>
                            <td>
                                <textarea style="width: 92%;height: 90%;" name="member_remarks"></textarea>
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>

                <table id="followTable" class="basic" width="100%" style="margin-top: 20px;">
                    <tr>
                        <th colspan="6" style="text-align: left;">&#12288;随访家访信息
                            <c:if test="${!empty doctor}">
                                <span style="float: right;margin-right: 10px;margin-bottom: 5px;">
                                    <img class='opBtn' title='删除' style='cursor: pointer;'
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick='delFollow();'/>
                                    <img class="opBtn" title="新增"
                                         src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="addFollow();"/>
                                </span>
                            </c:if>
                        </th>
                    </tr>
                    <tr>
                        <c:if test="${!empty doctor}">
                            <th width="4%"></th>
                        </c:if>
                        <th style="width: 80px;text-align: center;"><font color="red">*</font>时间</th>
                        <th style="width: 240px;text-align: center;"><font color="red">*</font>随访家访内容（包括家庭病床管理记录）</th>
                        <th style="width: 100px;text-align: center;">附件</th>
                    </tr>
                    <tbody id="followTb">
                    <c:forEach var="followMap" items="${formDataMap['homeVisitsInfo']}" varStatus="status">
                        <tr>
                            <c:if test="${!empty doctor}">
                                <td width="4%">
                                    <input type="checkbox" name="followIds"/>
                                </td>
                            </c:if>
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input class="validate[required]" name="visits_time" type="text"
                                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                           value="${followMap['visits_time']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <span>${ followMap['visits_time']}</span>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${!empty doctor}">
                                        <textarea style="width: 92%;height: 90%;" class="validate[required]"
                                                  name="visits_content">${followMap['visits_content']}</textarea>
                                </c:if>
                                <c:if test="${empty doctor}">
                                        <span>
                                                ${ followMap['visits_content']}
                                        </span>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${!empty doctor and not empty followMap['visits_accessories_Flow']}">
                                    <input type="text" id="fileIsRe" value="N" name="fileIsRe" style="display: none;"/>
                                    <input type="text" value="${followMap['visits_accessories_Flow']}"
                                           name="visits_accessoriesFlow" style="display: none;"/>
                                    <input type="text" value="${followMap['visits_accessories']}"
                                           name="visits_accessoriesName" style="display: none;"/>
                                    <input type="file" name="visits_accessories" style="display: none;"/>
                                    <ul>
                                        <li>
                                            <a href="<s:url value='/res/rec/fileDown'/>?fileFlow=${followMap['visits_accessories_Flow']}">【${followMap['visits_accessories']}】</a><img
                                                class='opBtn' title='删除' style='cursor: pointer;'
                                                src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                                onclick='moveTr(this);'/>
                                        </li>
                                    </ul>
                                </c:if>
                                <c:if test="${!empty doctor and empty followMap['visits_accessories_Flow']}">
                                    <input type="text" id="fileIsRe" value="" name="visits_accessoriesIsRe"
                                           style="display: none;"/>
                                    <input type="text" value="" name="visits_accessoriesFlow" style="display: none;"/>
                                    <input type="text" value="" name="visits_accessoriesName" style="display: none;"/>
                                    <input type="file" name="visits_accessories"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <ul>
                                        <li>
                                            <a href="<s:url value='/res/rec/fileDown'/>?fileFlow=${followMap['visits_accessories_Flow']}">【${followMap['visits_accessories']}】</a>
                                        </li>
                                    </ul>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty formDataMap['homeVisitsInfo']}">
                        <tr>
                            <c:if test="${!empty doctor}">
                                <td width="4%">
                                    <input type="checkbox" name="followIds"/>
                                </td>
                            </c:if>
                            <td>
                                <input class="validate[required]" name="visits_time" type="text"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                       style="width: 80px;"/>
                            </td>
                            <td>
                                    <textarea style="width: 92%;height: 90%;" class="validate[required]"
                                              name="visits_content"></textarea>
                            </td>
                            <td>
                                <input type="file" name="visits_accessories"/>
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>

                <p align="center">
                    <c:if test="${canEditAppendix}">
                        <input class="search" type="button" value="保&#12288;存"  onclick="saveForm('Y');"/>
                    </c:if>
                    <c:if test="${!canEditAppendix}">
                        <input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
                    </c:if>
                    <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
                </p>
            </form>
        </div>
        <div style="display: none">
            <!-- 模板 -->
            <table id="memberTemplate">
                <tr>
                    <td width="4%">
                        <input type="checkbox" name="memberIds"/>
                    </td>
                    <td>
                        <input class="validate[required]" name="member_name" type="text" style="width: 60px;"/>
                    </td>
                    <td>
                        <select name="member_gender" style="width: 65px;" class="autoValue validate[required]">
                            <option value="">请选择</option>
                            <option value="Man">男</option>
                            <option value="Woman">女</option>
                        </select>
                    </td>
                    <td>
                        <input class="validate[required]" name="member_age" type="text" style="width: 60px;"/>
                    </td>
                    <td>
                        <textarea style="width: 92%;height: 90%;" class="validate[required]"
                                  name="member_health_statues"></textarea>
                    </td>
                    <td>
                        <textarea style="width: 92%;height: 90%;" name="member_remarks"></textarea>
                    </td>
                </tr>
            </table>

            <table id="followTemplate">
                <tr>
                    <td width="4%">
                        <input type="checkbox" name="followIds"/>
                    </td>
                    <td>
                        <input class="validate[required]" name="visits_time" type="text" style="width: 80px;"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                    </td>
                    <td>
                        <textarea style="width: 92%;height: 90%;" class="validate[required]"
                                  name="visits_content"></textarea>
                    </td>
                    <td>
                        <input type="text" value="${followMap['visits_accessories_Flow']}" name="visits_accessoriesFlow"
                               style="display: none;"/>
                        <input type="text" value="${followMap['visits_accessories']}" name="visits_accessoriesName"
                               style="display: none;"/>
                        <input type="file" name="visits_accessories"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>