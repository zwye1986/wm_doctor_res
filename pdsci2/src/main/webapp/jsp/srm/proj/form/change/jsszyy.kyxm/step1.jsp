<head>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function initDept() {
            var datas = [];
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            jboxPost(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    var d = {};
                    d.id = n.deptFlow;
                    d.text = n.deptName;
                    datas.push(d);
                });
            }, null, false);
            var itemSelectFuntion = function () {
                $("#deptFlow").val(this.id);
            };
            $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
        }
        function doBack() {
            <c:if test="${sessionScope.projListScope ne GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            window.location.href = "<s:url value='/srm/proj/mine/process?projFlow='/>" + $("#projFlow").val();
            </c:if>
            <c:if test="${sessionScope.projListScope eq GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            window.location.href = "<s:url value='/srm/proj/search/recList?projFlow='/>" + $("#projFlow").val();
            </c:if>
        }
        $(document).ready(function () {
            initDept();
            $('input[name="change_other"]').change(function () {
                if ($("#change_other").attr("checked")) {
                    $("#otherState").removeAttr("readonly");
                } else {
                    $("#otherState").val("");
                    $("#otherState").attr("readonly", "readonly");
                }
            });
        });

        function nextOpt(step){
            if(false==$("#projForm").validationEngine("validate")){
                return false;
            }
            var form = $('#projForm');
            form.append("<input name='nextPageName' value='"+step+"' type='hidden'/>");
            $('#nxt').attr({"disabled":"disabled"});
            $('#prev').attr({"disabled":"disabled"});
            jboxStartLoading();
            form.submit();
        }

        //单选
        function single(box){
            var curr=box.checked;
            if(curr){
                var name=box.name;
                $(":checkbox[name='"+name+"']").attr("checked",false);
            }
            box.checked = curr;
        }
    </script>
</c:if>
<style type="text/css">
    .borderNone{border-bottom-style: none;}
</style>
</head>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data" >
    <input type="hidden" id="pageName" name="pageName" value="step1" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <%--<font style="font-size: 14px; font-weight:bold;color: #333; ">一、基本信息</font>--%>
    <table class="basic" style="width: 100%; margin-top: 10px;">
       <%-- <tr>
            <th colspan="6" style="text-align: left;padding-left: 20px;">基本信息</th>
        </tr>--%>
        <tr>
            <td width="14%" style="text-align: right;">项目名称：</td>
            <td colspan="5" width="86%"><input type="text" name="projName" value="${empty resultMap.projName?proj.projName:resultMap.projName}" class="inputText" style="width: 80%" readonly="readonly"/></td>
        </tr>
        <tr>
            <td width="14%" style="text-align: right;">项目来源：</td>
            <td colspan="2" width="36%"><input type="text" name="projDeclarer" value="${empty resultMap.projDeclarer?proj.projSecondSourceName:resultMap.projDeclarer}" class="inputText" style="width: 80%;" readonly="readonly"/></td>
            <td width="14%" style="text-align: right;">项目合同号：</td>
            <td colspan="2" width="36%"><input type="text" name="projContractNo" value="${resultMap.projContractNo}" class="inputText" style="width: 80%;" /></td>
        </tr>
        <tr>
            <td width="14%" style="text-align: right;">立项年度：</td>
            <td width="18%"><input type="text" name="projYear" value="${empty resultMap.projYear?proj.projYear:resultMap.projYear}" class="inputText" style="width: 80%;" onClick="WdatePicker({dateFmt:'yyyy'})"/></td>
            <td width="14%" style="text-align: right;">承担科室：</td>
            <td width="22%">

                <input type="text" id="trainDept" class="inputText" name="applyOrgName"  value="${empty resultMap.applyOrgName?proj.applyOrgName:resultMap.applyOrgName}" autocomplete="off" style="width: 80%;"/>
                <%--<input type="hidden" id="deptFlow" name="deptFlow" class="input" value="${resultMap.applyOrgFlow}" />--%>
                <%--<input type="text" name="applyOrgName" value="${resultMap.applyOrgName}" class="inputText borderNone" style="width: 80%;"/>--%>

            </td>
            <td width="14%" style="text-align: right;">负责人：</td>
            <td width="18%"><input type="text" name="applyUserName" value="${empty resultMap.applyUserName?proj.applyUserName:resultMap.applyUserName}" class="inputText" style="width: 80%;"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">变更内容：</td>
            <td colspan="5">
                <label><input type="checkbox" name="change_conclusionTime" value="变更结题时间" <c:if test="${resultMap.change_conclusionTime eq '变更结题时间'}">checked="checked"</c:if> >变更结题时间&#12288;</label>
                <label><input type="checkbox" name="change_research" value="变更研究方案" <c:if test="${resultMap.change_research eq '变更研究方案'}">checked="checked"</c:if> >变更研究方案&#12288;</label>
                <label><input type="checkbox" name="change_applyUserName" value="变更项目负责人" <c:if test="${resultMap.change_applyUserName eq '变更项目负责人'}">checked="checked"</c:if> >变更项目负责人&#12288;</label>
                <label><input type="checkbox" name="change_member" value="变更课题组成员" <c:if test="${resultMap.change_member eq '变更课题组成员'}">checked="checked"</c:if> >变更课题组成员&#12288;</label>
                <label><input type="checkbox" name="change_other" id="change_other" value="其他" <c:if test="${resultMap.change_other eq '其他'}">checked="checked"</c:if>>其它&#12288;</label>
                    <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                        <input type="text" class="inputText" name="otherState" id="otherState"
                               value="${resultMap.otherState}" readonly="readonly">
                    </c:if>
                    <c:if test="${param.view eq GlobalConstant.FLAG_Y }">
                        <span class="inputText">${resultMap.otherState}</span>
                    </c:if>
            </td>
        </tr>
    </table>
</form>
<div class="button" style="width: 100%;
<c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
    <input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
    <input onclick="nextOpt('step2');" id="nxt" class="search" type="button" value="保&#12288;存"/>
</div>