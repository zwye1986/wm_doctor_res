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
            <td width="200px;" style="text-align: center">申请者：</td>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="validate[required] inputText" name='applicant'
                       value="<c:if test='${empty resultMap.applicant and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.applicant}</c:if><c:if test='${! empty resultMap.applicant}'>${resultMap.applicant}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">从事专业：</td>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="validate[required] inputText" name="major"
                       value="<c:if test='${empty resultMap.major and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.major}</c:if><c:if test='${!empty resultMap.major}'>${resultMap.major}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">课题名称：</td>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="inputText" name="topics"
                       value="<c:if test='${empty resultMap.topics and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.topics}</c:if><c:if test='${!empty resultMap.topics}'>${resultMap.topics}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">单位名称：</td>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="validate[required] inputText" name="company_name"
                       value="<c:if test='${empty resultMap.company_name and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.company_name}</c:if><c:if test='${!empty resultMap.company_name}'>${resultMap.company_name}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">单位地址：</td>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="validate[required] inputText" name="company_address"
                       value="<c:if test='${empty resultMap.company_address and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.company_address}</c:if><c:if test='${!empty resultMap.company_address}'>${resultMap.company_address}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">单位邮编：</td>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                    <input type="text" class="inputText validate[required]" name="company_postCode"
                       value="<c:if test='${empty resultMap.company_postCode and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.company_postCode}</c:if><c:if test='${! empty resultMap.company_postCode}'>${resultMap.company_postCode}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">联系人：</td>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="inputText validate[required]" name="contacts"
                       value="<c:if test='${empty resultMap.contacts and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.contacts}</c:if><c:if test='${! empty resultMap.contacts}'>${resultMap.contacts}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">联系电话：</td>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="inputText validate[required,custom[phone]]" name="phoneNum"
                       value="<c:if test='${empty resultMap.phoneNum and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.phoneNum}</c:if><c:if test='${! empty resultMap.phoneNum}'>${resultMap.phoneNum}</c:if>"
                       style="width: 46%"/>
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