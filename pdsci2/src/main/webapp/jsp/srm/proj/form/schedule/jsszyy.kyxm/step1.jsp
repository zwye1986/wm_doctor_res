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

        $(document).ready(function () {
            initDept();
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

        function doBack() {
            <c:if test="${sessionScope.projListScope ne GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            window.location.href = "<s:url value='/srm/proj/mine/process?projFlow='/>" + $("#projFlow").val();
            </c:if>
            <c:if test="${sessionScope.projListScope eq GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            window.location.href = "<s:url value='/srm/proj/search/recList?projFlow='/>" + $("#projFlow").val();
            </c:if>
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
    .basic td{
        text-align: left;
    }
</style>
</head>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data" <%--style="position: relative;"--%>>
    <input type="hidden" id="pageName" name="pageName" value="step1" />
    <c:if test="${not empty projPageGroupName}">
    <input type="hidden"  name="pageGroupName" value="${projPageGroupName}"/>
    </c:if>
    <c:if test="${empty projPageGroupName}">
        <input type="hidden"  name="pageGroupName" value="${projRec.pageGroupName}"/>
    </c:if>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <%--<font style="font-size: 14px; font-weight:bold;color: #333; ">一、基本信息</font>--%>
    <table class="basic" style="width: 100%; margin-top: 10px;">
        <colgroup>
            <col width="14%">
            <col width="18%">
            <col width="14%">
            <col width="22%">
            <col width="14%">
            <col width="18%">
        </colgroup>
        <tr>
            <th >项目名称：</th>
            <td colspan="5"><input type="text" name="projName" value="${empty resultMap.projName?proj.projName:resultMap.projName}" class="inputText" style="width: 80%;text-align: left" readonly="readonly"/></td>
        </tr>
        <tr>
            <th >项目来源：</th>
            <td colspan="2"><input type="text" name="projDeclarer" value="${empty resultMap.projDeclarer?proj.projSecondSourceName:resultMap.projDeclarer}" class="inputText borderNone" style="width: 80%;text-align: left" readonly="readonly"/></td>
            <th >项目合同号：</th>
            <td colspan="2"><input type="text" name="projContractNo" value="${resultMap.projContractNo}" class="inputText" style="width: 80%;text-align: left"/></td>
        </tr>
        <tr>
            <th  >立项年度：</th>
            <td ><input type="text" name="projYear" value="${empty resultMap.projYear?proj.projYear:resultMap.projYear}" class="inputText" style="width: 80%;text-align: left" onClick="WdatePicker({dateFmt:'yyyy'})"/></td>
            <th  >承担科室：</th>
            <td >

                <input type="text" id="trainDept" class="inputText borderNone" name="applyOrgName"  value="${empty resultMap.applyOrgName?proj.applyOrgName:resultMap.applyOrgName}" autocomplete="off" style="width: 80%;text-align: left"/>
                <%--<input type="hidden" id="deptFlow" name="deptFlow" class="input" value="${resultMap.applyOrgFlow}" />--%>
                <%--<input type="text" name="applyOrgName" value="${resultMap.applyOrgName}" class="inputText borderNone" style="width: 80%;"/>--%>

            </td>
            <th  >负责人：</th>
            <td ><input type="text" name="applyUserName" value="${empty resultMap.applyUserName?proj.applyUserName:resultMap.applyUserName}" class="inputText" style="width: 80%;text-align: left"/></td>
        </tr>
        <tr>
            <th >能否按时完成预期目标：</th>
            <td colspan="2">
                <label>能<input type="checkbox" name="isComplete" value="能" <c:if test="${resultMap.isComplete eq '能'}">checked="checked"</c:if> onchange="single(this)">&#12288;</label>
                <label>否<input type="checkbox" name="isComplete" value="否" <c:if test="${resultMap.isComplete eq '否'}">checked="checked"</c:if> onchange="single(this)"></label>
            </td>
            <th >涉及人体的研究是否通过伦理审查：</th>
            <td colspan="2">
                <label>是<input type="checkbox" name="isPass" value="是" <c:if test="${resultMap.isPass eq '是'}">checked="checked"</c:if> onchange="single(this)">&#12288;</label>
                <label>否<input type="checkbox" name="isPass" value="否" <c:if test="${resultMap.isPass eq '否'}">checked="checked"</c:if> onchange="single(this)">&#12288;</label>
                <label>不涉及<input type="checkbox" name="isPass" value="不涉及" <c:if test="${resultMap.isPass eq '不涉及'}">checked="checked"</c:if> onchange="single(this)">&#12288;</label>
            </td>
        </tr>
    </table>
</form>
<div class="button" style="width: 100%;
<c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
    <input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
    <input onclick="nextOpt('step2');" id="nxt" class="search" type="button" value="保&#12288;存"/>
</div>