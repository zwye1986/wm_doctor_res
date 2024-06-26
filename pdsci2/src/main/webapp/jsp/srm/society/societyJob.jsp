<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_form" value="false"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="false"/>
    <jsp:param name="jquery_scrollTo" value="true"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
    <jsp:param name="jquery_ztree" value="true"/>
</jsp:include>
<style>
    .basic td {
        text-align: center;
        padding-left: 0px;
    }
</style>
<script type="text/javascript">
    function saveList() {

        if (!$("#projForm").validationEngine("validate")) {
            return false;
        }
        jboxConfirm("确认提交?", function () {
            var authTab = $('#test');//query选择器:id为test的元素
            var trs = authTab.children();//.children()方法获取authTab的子元素
            var datas = [];//js数组对象   datas[0]
            var userFlow = $("input[name='userFlow']").val();
            var userName = $("input[name='userName']").val();
            var orgFlow = $("input[name='orgFlow']").val();
            var orgName = $("input[name='orgName']").val();
            var isDataExist = false;
            $.each(trs, function (i, n) {//jquery迭代器,用于循环数组和json  i表示索引   n表示当前循环的元素
                var deptFlow = $(n).find("select[name='deptFlow']").val();//$(n)  $(js对象)   将js对象转换成jquery对象
                var deptName = $(n).find("input[name='deptName']").val();
                var societyName = $(n).find("input[name='societyName']").val();
                var societyDetailed = $(n).find("input[name='societyDetailed']").val();
                var societyPosition = $(n).find("select[name='societyPosition']").val();
                var startYear = $(n).find("input[name='startYear']").val();
                var endYear = $(n).find("input[name='endYear']").val();
                var societyFlow = $(n).find("input[name='societyFlow']").val();
                var data = {//json对象   {key:value}
                    "userFlow": userFlow,
                    "userName": userName,
                    "orgFlow": orgFlow,
                    "orgName": orgName,
                    "deptFlow": deptFlow,
                    "deptName": deptName,
                    "societyName": societyName,
                    "societyDetailed": societyDetailed,
                    "societyPosition": societyPosition,
                    "startYear": startYear,
                    "endYear": endYear,
                    "societyFlow": societyFlow
                };
                datas.push(data);//数组的push方法
//                if (userFlow != null && userName != null && deptFlow != null && deptName != null && societyName != null && societyDetailed != null && societyPosition != null && startYear != null && endYear != null) {
//                    isDataExist = true;
//                }
            });
            var requestData = JSON.stringify(datas);//将数组对象转换成json字符串
            //console.log(requestData);
            var url = "<s:url value='/srm/learning/sociery/saveSociety'/>";
//            if (isDataExist) {
            jboxStartLoading();
            jboxPostJson(url, requestData, function (resp) {
                if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                 //   window.search();
                    jboxClose();
                }
            }, null, true);
//            } else {
//                jboxTip("没有修改数据");
//            }
        });

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
            jboxConfirm('删除后将无法恢复，确认删除？', function () {
                var delInfo = $('.' + templateId + ' .toDel:checked');
                var datas = [];
                $.each(delInfo, function (i, n) {
                    var str = $(n).parent().parent().find("input[name = 'societyFlow']").val();
                    datas.push(str);
                });
                var requestData = JSON.stringify(datas);
                $('.' + templateId + ' .toDel:checked').closest('tr').remove();
                var url = "<s:url value='/srm/learning/sociery/deleteSociety'/>";
                    jboxStartLoading();
                    jboxPostJson(url, requestData, function (resp) {
                      //  window.parent.frames['mainIframe'].location.reload(true);
                      //  doClose();
                    }, null, true);

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
        $('#template tr').each(function () {
            var id = this.id;
            if (id) {
                if (!$('.' + id + ' .toDel').length) {
                    add(id);
                }
            }
        });
    });

    function checkBDDate(obj) {
        var start = $(obj).parent().parent().find("input[name = 'startYear']").val();
        var end = $(obj).parent().parent().find("input[name = 'endYear']").val();
        //alert(start + "```"+ end);
        if (start && end && start > end) {
            jboxTip("开始时间不能大于结束时间！");
            $(obj).parent().parent().find("input[name = 'endYear']").val("");
        }
    }
    function getDeptName(obj) {
        $(obj).next().val($(obj).find("option:selected").text());
    }
</script>
<form action="<s:url value=''/>" method="post"
      id="projForm" style="position: relative;">
    <input type="hidden" name="userFlow" value="${sysUser.userFlow}"/>
    <input type="hidden" name="userName" value="${sysUser.userName}"/>
    <input type="hidden" name="orgFlow" value="${sysUser.orgFlow}"/>
    <input type="hidden" name="orgName" value="${sysUser.orgName}"/>
    <div class="content" style="height: 450px;overflow: auto;">
        <div class="title1 clearfix">
            <div id="tagContent">
                <table class="basic" style="width: 100%">
                    <tr>
                        <th style="text-align: left;padding-left: 15px;" colspan="9">
                            登记姓名：<span>${sysUser.userName}</span>
                            <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                    <span style="float: right;padding-right: 10px">
                        <img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                             style="cursor: pointer;" onclick="add('learningSocietyList');"/>
                        <img title="删除" style="cursor: pointer;"
                             src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                             onclick="del('learningSocietyList');"/>
                    </span>
                            </c:if>
                        </th>
                    </tr>
                    <tr>
                        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                            <td style="text-align: center;" width="5%">选择</td>
                        </c:if>
                        <td style="text-align: center;" width="5%">序号</td>
                        <%--<td style="text-align: center;" width="10%">姓名</td>--%>
                        <td style="text-align: center;" width="10%">科室</td>
                        <td style="text-align: center;" width="15%">学会名称</td>
                        <td style="text-align: center;" width="20%">具体专业委员会名称</td>
                        <td style="text-align: center;" width="10%">职位</td>
                        <td style="text-align: center;" width="15%">任职时间</td>
                        <td style="text-align: center;" width="15%">结束时间</td>
                    </tr>
                    <tbody class="learningSocietyList" id="test">
                    <c:forEach var="learningSociety" items="${learningSocietyList}" varStatus="learningSocietyList">
                        <tr>
                            <td><input type="checkbox" class="toDel"></td>
                            <td class="seq">${learningSocietyList.count}</td>
                                <%--<td>
                                    <input type="hidden" name="userFlow" value="${sysUser.userFlow}"/>
                                    <input type="text" class="inputText validate[required]" name="userName"
                                           value="<c:if test='${empty learningSociety.userName}'>${sysUser.userName}</c:if> ${learningSociety.userName}" style="width: 80%"/>
                                </td>--%>
                            <td>
                                <select name="deptFlow" class="inputText validate[required]" style="width: 80%;"
                                        onchange="getDeptName(this);">
                                    <option value="">请选择</option>
                                    <c:choose>
                                        <c:when test="${empty learningSociety.deptFlow}">
                                            <c:forEach var="dept" items="${sysDeptList}">
                                                <option value="${dept.deptFlow }"
                                                        <c:if test="${dept.deptFlow eq sysUser.deptFlow}">selected="selected"</c:if>>${dept.deptName }</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="dept" items="${sysDeptList}">
                                                <option value="${dept.deptFlow }"
                                                        <c:if test="${dept.deptFlow eq learningSociety.deptFlow}">selected="selected"</c:if>>${dept.deptName }</option>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                                <input type="hidden" name="deptName" value="<c:if test="${empty learningSociety.deptName}">${sysUser.deptName}</c:if>${learningSociety.deptName}"/>
                            </td>
                            <td>
                                <input type="hidden" name="societyFlow" value="${learningSociety.societyFlow}"/>
                                <input type="text" class="inputText validate[required]" name="societyName"
                                       value="${learningSociety.societyName}" style="width: 80%"/>
                            </td>
                            <td>
                                <input type="text" class="inputText validate[required]" name="societyDetailed"
                                       value="${learningSociety.societyDetailed}"
                                       style="width: 80%"/>
                            </td>
                            <td>
                                <select name="societyPosition" class="inputText validate[required]"
                                        style="width: 80%;">
                                    <option value="">请选择</option>
                                    <option value="主委"
                                            <c:if test="${learningSociety.societyPosition eq '主委'}">selected="selected"</c:if>>
                                        主委
                                    </option>
                                    <option value="副主委"
                                            <c:if test="${learningSociety.societyPosition eq '副主委'}">selected="selected"</c:if>>
                                        副主委
                                    </option>
                                    <option value="委员"
                                            <c:if test="${learningSociety.societyPosition eq '委员'}">selected="selected"</c:if>>
                                        委员
                                    </option>
                                </select>
                            </td>
                            <td>
                                <input class="inputText ctime validate[required]" type="text" name="startYear"
                                       value="${learningSociety.startYear}" onClick="WdatePicker({dateFmt:'yyyy-MM'})"
                                       readonly="readonly" style="width: 80%" onchange="checkBDDate(this)"/>
                            </td>
                            <td>
                                <input class="inputText ctime validate[required]" type="text" name="endYear"
                                       value="${learningSociety.endYear}" onClick="WdatePicker({dateFmt:'yyyy-MM'})"
                                       readonly="readonly" style="width: 80%" onchange="checkBDDate(this)"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input type="button" onclick="saveList();" class="search" value="保&#12288;存"/>
            <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
        </div>
    </c:if>
</form>
<table id="template" style="display: none">
    <tr id="learningSocietyList">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <%--<td>
            <input type="hidden" name="userFlow" value="${sysUser.userFlow}"/>
            <input type="text" class="inputText validate[required]" name="userName" value="${sysUser.userName}" style="width: 80%"/>
        </td>--%>
        <td>
            <select name="deptFlow" class="inputText validate[required]" style="width: 80%;"
                    onchange="getDeptName(this);">
                <option value="">请选择</option>
                <c:forEach var="dept" items="${sysDeptList}">
                    <option value="${dept.deptFlow }"
                            <c:if test="${dept.deptFlow eq sysUser.deptFlow}">selected="selected"</c:if>>${dept.deptName }</option>
                </c:forEach>
            </select>
            <input id="deptName" type="hidden" name="deptName" value="${sysUser.deptName}"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="societyName" value="" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="societyDetailed" value=""
                   style="width: 80%"/>
        </td>
        <td>
            <select name="societyPosition" class="inputText validate[required]" style="width: 80%;">
                <option value="">请选择</option>
                <option value="主委">主委</option>
                <option value="副主委">副主委</option>
                <option value="委员">委员</option>
            </select>
        </td>
        <td>
            <input class="inputText ctime validate[required]" type="text" name="startYear"
                   value="${learningSociety.startYear}" onClick="WdatePicker({dateFmt:'yyyy-MM'})"
                   readonly="readonly" style="width: 80%" onchange="checkBDDate(this)"/>
        </td>
        <td>
            <input class="inputText ctime validate[required]" type="text" name="endYear"
                   value="${learningSociety.endYear}" onClick="WdatePicker({dateFmt:'yyyy-MM'})"
                   readonly="readonly" style="width: 80%" onchange="checkBDDate(this)"/>
        </td>
    </tr>
</table>


