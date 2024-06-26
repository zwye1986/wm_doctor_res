<head>
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

            function reCheck(obj, id) {
                $(obj).hide();
                $("#" + id).hide();
                $(obj).parent().prev().find("input[name=" + id + "]").show();
            }
        </script>
    </c:if>
    <style type="text/css">
        .borderNone {
            border-bottom-style: none;
        }
    </style>
</head>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data"
      style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step3"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333; ">二、项目技术报告</font>

    <table class="basic" style="width: 100%">
        <c:if test="${param.view != GlobalConstant.FLAG_Y}">
            <tr>
                <th style="text-align: left" colspan="3">临床/实验研究总结报告（附件格式：WORD标准A4纸）<span style="float: right"><a
                        href="<s:url value="/jsp/srm/proj/form/complete/jsszyy.kyxm/总结报告结构与内容规范.docx" />">【总结报告结构与内容规范模板】&#12288;&#12288;</a></span>
                </th>
            </tr>
        </c:if>
        <tr>
            <th width="25%">临床研究总结报告</th>
            <td>
                <input type="hidden" name="clinical_file" value="${resultMap.clinical_file}">
                <input type="hidden" name="clinical_name" value="${resultMap.clinical_name}">
                <c:if test="${not empty resultMap.clinical_file}">
                    <a id="clinicalFile"
                       href='<s:url value="/pub/file/down?fileFlow=${resultMap.clinical_file}"/>'>${resultMap.clinical_name}</a>
                    <input type="file" name="clinicalFile" style="display: none"/>
                </c:if>
                <c:if test="${empty resultMap.clinical_file}">
                    <input type="file" name="clinicalFile"/>
                </c:if>
            </td>
            <c:if test="${param.view != GlobalConstant.FLAG_Y}">
                <td>
                    <c:if test="${not empty resultMap.clinical_file}">
                        <a onclick="reCheck(this,'clinicalFile')">[重新选择]</a>
                    </c:if>
                </td>
            </c:if>
        </tr>
        <tr>
            <th>实验研究总结报告</th>
            <td>
                <input type="hidden" name="test_file" value="${resultMap.test_file}">
                <input type="hidden" name="test_name" value="${resultMap.test_name}">
                <c:if test="${not empty resultMap.test_file}">
                    <a id="testFile"
                       href='<s:url value="/pub/file/down?fileFlow=${resultMap.test_file}"/>'>${resultMap.test_name}</a>
                    <input type="file" name="testFile" style="display: none"/>
                </c:if>
                <c:if test="${empty resultMap.test_file}">
                    <input type="file" name="testFile"/>
                </c:if>
            </td>
            <c:if test="${param.view != GlobalConstant.FLAG_Y}">
                <td>
                    <c:if test="${not empty resultMap.test_file}">
                        <a onclick="reCheck(this,'testFile')">[重新选择]</a>
                    </c:if>
                </td>
            </c:if>
        </tr>
    </table>
    <div class="button" style="width: 100%;
    <c:if test="${param.view eq GlobalConstant.FLAG_Y}">display:none</c:if>">
        <input id="prev" type="button" onclick="nextOpt('step2')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step4')" class="search" value="保&#12288;存"/>
    </div>

</form>
<table style="display:none">
    <tr id="feePayList">
        <td style="text-align: center"><input type="checkbox" class="toDel"></td>
        <td colspan="3"><input type="text" id="feePayList_content" name="feePayList_content" value="" class="inputText "
                               style="width: 100%;"/></td>
        <td><input type="text" name="feePayList_money" value="" class="inputText " style="width: 100%;"
                   onchange="countFee()"/></td>
    </tr>
</table>
