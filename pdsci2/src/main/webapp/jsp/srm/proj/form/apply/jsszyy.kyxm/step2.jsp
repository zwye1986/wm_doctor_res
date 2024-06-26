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
        function checkBDDate(dt){
            var dates = $(':text',$(dt).closest("td"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                dt.value = "";
            }

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
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step2"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">一、项目基本情况</font>
    <table class="basic" style="width: 100%; margin-top: 10px;">
        <colgroup>
            <col width="18%"/>
            <col width="15%"/>
            <col width="15%"/>
            <col width="15%"/>
            <col width="15%"/>
            <col width="15%"/>
        </colgroup>
        <tr>
            <th colspan="6" style="text-align: center">研究课题</th>
        </tr>
        <tr>
            <th>名&#12288;称<font color="red">*</font>：</th>
            <td colspan="5">
                <input type="text" name="projectName" value="<c:if test="${empty resultMap.projectName}">${proj.projName}</c:if>${resultMap.projectName}"
                       class="validate[required] inputText" style="width: 80%;text-align: center;"/>
            </td>
        </tr>
        <tr>
            <th>课题总经费（万元）<font color="red">*</font>：</th>
            <td>
                <input type="text" name="projectFund" value="${resultMap.projectFund}"
                       class="validate[required] inputText" style="width: 80%"/>
            </td>
            <th>申请经费（万元）<font color="red">*</font>：</th>
            <td>
                <input type="text" name="applyFund" value="${resultMap.applyFund}"
                       class="validate[required] inputText" style="width: 80%"/>
            </td>
            <th>自筹经费（万元）<font color="red">*</font>：</th>
            <td>
                <input type="text" name="oneselfFund" value="${resultMap.oneselfFund}"
                       class="validate[required] inputText" style="width: 80%"/>
            </td>
        </tr>
        <tr>
            <th>申报科室<font color="red">*</font>：</th>
            <td colspan="2">
                    <input type="text" name="applyDeptName" value="${resultMap.applyDeptName}<c:if test="${empty resultMap.applyDeptName}">${proj.applyDeptName}</c:if>"
                           class="validate[required] inputText" style="width: 80%"/>
            </td>
            <th>研究工作起止年月：</th>
            <td colspan="2">
                <input class="inputText ctime" type="text" name="projStartYearMonth" onchange="checkBDDate(this)"
                       value="<c:if test="${empty resultMap.projStartYearMonth}">${pdfn:transDateTimeForPattern(proj.projStartTime,'yyyy-MM-dd','yyyy年MM月')}</c:if>${resultMap.projStartYearMonth}" onClick="WdatePicker({dateFmt:'yyyy年MM月'})" readonly="readonly"
                       style="width: 35%"/> ~
                <input class="inputText ctime" type="text" name="projEndYearMonth" onchange="checkBDDate(this)"
                       value="<c:if test="${empty resultMap.projEndYearMonth}">${pdfn:transDateTimeForPattern(proj.projEndTime,'yyyy-MM-dd','yyyy年MM月')}</c:if>${resultMap.projEndYearMonth}" onClick="WdatePicker({dateFmt:'yyyy年MM月'})" readonly="readonly"
                       style="width: 35%"/>
            </td>
        </tr>
        <tr>
            <th style="text-align: right;">项目负责人<font color="red">*</font>：</th>
            <td colspan="5">
                姓名：<input type="text" class="validate[required] inputText" name='projContacts'
                       value="<c:if test='${empty resultMap.projContacts and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${! empty resultMap.projContacts}'>${resultMap.projContacts}</c:if>"
                       style="width: 120px"/>
                &#12288;职称：<input type="text" class="validate[required] inputText" name='contactsTitle'
                          value="<c:if test='${empty resultMap.contactsTitle and param.view!=GlobalConstant.FLAG_Y}'>${user.titleName}</c:if><c:if test='${! empty resultMap.contactsTitle}'>${resultMap.contactsTitle}</c:if>"
                          style="width: 120px"/>
                &#12288;手机：<input type="text" class="validate[required,custom[mobile]] inputText" name='telePhone'
                          value="<c:if test='${empty resultMap.telePhone and param.view!=GlobalConstant.FLAG_Y}'>${user.userPhone}</c:if><c:if test='${! empty resultMap.telePhone}'>${resultMap.telePhone}</c:if>"
                          style="width: 120px"/>
                &#12288;邮箱：<input type="text" class="validate[required,custom[email]] inputText" name='email'
                          value="<c:if test='${empty resultMap.email and param.view!=GlobalConstant.FLAG_Y}'>${user.userEmail}</c:if><c:if test='${! empty resultMap.email}'>${resultMap.email}</c:if>"
                          style="width: 200px"/>
            </td>
        </tr>
        </table>
        <%--<tr>
            <th style="text-align: right;">申报指南代码：</th>
            <td colspan="5">
                <input type="text" name="guideCode" value="${resultMap.guideCode}" class="inputText" style="width: 80%"/>
            </td>
        </tr>--%>
        <table class="basic" style="width: 100%; margin-top: 10px;">
            <tr>
                <th style="text-align: left" colspan="5" >合作单位
                    <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('cooperation',20)"/>&#12288;
									<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('cooperation');"/></span>
                    </c:if>
                </th>
            </tr>
        <tr>
            <th></th>
            <th>序号</th>
            <th style="text-align: center">单位名称</th>
            <th style="text-align: center">通讯地址及邮政编码</th>
            <th style="text-align: center">单位性质</th>
        </tr>
            <tbody class="cooperation">
            <c:if test="${not empty resultMap.cooperation}">
                <c:forEach var="researcher" items="${resultMap.cooperation}" varStatus="status">
                    <tr>
                        <td><input type="checkbox" class="toDel"></td>
                        <td class="seq">${status.count}</td>
                        <td>
                            <input type="text" name="cooperation_Name" value="${researcher.objMap.cooperation_Name}" class="inputText validate[required]" style="width: 90%"/>
                        </td>
                        <td>
                            <input type="text" name="cooperation_Address" value="${researcher.objMap.cooperation_Address}" class="inputText validate[required]" style="width: 90%"/>
                        </td>
                        <td>
                            <input type="text" name="cooperation_Nature" value="${researcher.objMap.cooperation_Nature}" class="inputText validate[required]" style="width: 90%"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
    </table>
        <table class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <th width="30%">主要研究内容<font color="red">*</font>：</th>
            <td style="text-align:left;">
                <textarea class="validate[required] xltxtarea" style="height: 100px;"
                          name="mainResearchContent">${resultMap.mainResearchContent}</textarea>
            </td>
        </tr>
        <tr>
            <th>考核指标<font color="red">*</font>：</th>
            <td style="text-align:left;">
                <textarea class="validate[required] xltxtarea" style="height: 100px;"
                          name="examineIndex">${resultMap.examineIndex}</textarea>
            </td>
        </tr>
        <tr>
            <th >创新之处及关键技术(不超过 100 字)<font color="red">*</font>：</th>
            <td style="text-align:left;">
                <textarea class="validate[required,maxSize[100]] xltxtarea" style="height: 100px;"
                          name="keyTechnologies">${resultMap.keyTechnologies}</textarea>
            </td>
        </tr>
    </table>

    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="保&#12288;存"/>
        </div>
    </c:if>
</form>
<table id="template" style="display: none">
    <tr id="cooperation">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" name="cooperation_Name" class="inputText validate[required]" style="width: 90%"/>
        </td>
        <td>
            <input type="text" name="cooperation_Address" class="inputText validate[required]" style="width: 90%"/>
        </td>
        <td>
            <input type="text" name="cooperation_Nature" class="inputText validate[required]" style="width: 90%"/>
        </td>
    </tr>
    </table>