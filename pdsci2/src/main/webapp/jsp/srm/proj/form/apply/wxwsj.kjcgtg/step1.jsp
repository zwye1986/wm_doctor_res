<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
            $('#nxt').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }

        function doBack() {
            window.location.href = "<s:url value='/srm/proj/mine/process?projFlow='/>" + $("#projFlow").val();
        }
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" name="pageName" value="step1"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>


    <table class="bs_tb" style="width: 100%">
        <tr>
            <th colspan="4" class="theader"></th>
        </tr>
        <tr>
            <th style="text-align: center">项目名称：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="inputText" name="projName"
                       value="<c:if test='${empty resultMap.projName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${!empty resultMap.projName}'>${resultMap.projName}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <th width="200px;" style="text-align: center">&#12288;申请人：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="validate[required] inputText" name='applicant'
                           value="<c:if test='${empty resultMap.applicant and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.applicant}</c:if><c:if test='${! empty resultMap.applicant}'>${resultMap.applicant}</c:if>"
                           style="width: 46%"/>
                    <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <th style="text-align: center">单位名称：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="validate[required] inputText" name="company_name"
                           value="<c:if test='${empty resultMap.company_name and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyOrgName}</c:if><c:if test='${!empty resultMap.company_name}'>${resultMap.company_name}</c:if>"
                           style="width: 46%"/>
                    <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <th style="text-align: center">单位地址：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="validate[required] inputText" name="company_address"
                           value="<c:if test='${empty resultMap.company_address and param.view!=GlobalConstant.FLAG_Y}'>${org.orgAddress}</c:if><c:if test='${!empty resultMap.company_address}'>${resultMap.company_address}</c:if>"
                           style="width: 46%"/>
                    <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <th style="text-align: center">联系部门：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="validate[required] inputText" name="company_section"
                           value="<c:if test='${empty resultMap.company_section and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.company_section}</c:if><c:if test='${!empty resultMap.company_section}'>${resultMap.company_section}</c:if>"
                           style="width: 46%"/>
                    <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <th style="text-align: center">&#12288;联系人：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="inputText validate[required]" name="contacts"
                           value="<c:if test='${empty resultMap.contacts and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.contacts}</c:if><c:if test='${! empty resultMap.contacts}'>${resultMap.contacts}</c:if>"
                           style="width: 46%"/>
                    <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <th style="text-align: center">联系电话：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="inputText validate[required,custom[phone]]" name="phoneNum"
                           value="<c:if test='${empty resultMap.phoneNum and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.phoneNum}</c:if><c:if test='${! empty resultMap.phoneNum}'>${resultMap.phoneNum}</c:if>"
                           style="width: 46%"/>
                    <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <th style="text-align: center">申报日期：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="inputText validate[required]" name="applyTime"
                       value="<c:if test='${empty resultMap.applyTime and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.applyTime}</c:if><c:if test='${! empty resultMap.applyTime}'>${resultMap.applyTime}</c:if>"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
    </table>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
        <input id="nxt" type="button" onclick="nextOpt('step2')" class="search" value="下一步"/>
    </div>
</c:if>