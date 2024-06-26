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
            if($('#projStartTime').val()>$('#projEndTime').val()){
                jboxTip("开始时间不能大于结束时间");
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
</style>
</head>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data">
    <input type="hidden" id="pageName" name="pageName" value="step1" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333; ">基本信息</font>
    <table class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <td width="14%" style="text-align: right;">项目编号：</td>
            <td colspan="2" width="36%"><input type="text" name="projNo" value="${empty resultMap.projNo?proj.projYear:resultMap.projNo}" class="inputText" style="width: 80%" /></td>
            <td width="14%" style="text-align: right;">项目名称：</td>
            <td colspan="2" width="36%"><input type="text" name="projName" value="${empty resultMap.projName?proj.projName:resultMap.projName}" class="inputText" style="width: 80%" /></td>
        </tr>
        <tr>
            <td width="14%" style="text-align: right;">项目负责人：</td>
            <td width="36%" colspan="2"><input type="text" name="applyUserName" value="${empty resultMap.applyUserName?proj.applyUserName:resultMap.applyUserName}" class="inputText" style="width: 80%;"/></td>
            <td width="14%" style="text-align: right;">手机：</td>
            <td width="36%" colspan="2"><input type="text" name="mobile" value="${empty resultMap.mobile?proj.applyUserPhone:resultMap.mobile}" class="inputText validate[<%--required,--%>custom[phone]]" style="width: 80%;"/></td>
        </tr>
        <tr>
            <td width="14%" style="text-align: right;">项目承担科室：</td>
            <td width="36%" colspan="2"><input type="text" id="trainDept" class="inputText" name="applyOrgName"  value="${empty resultMap.applyOrgName?proj.applyOrgName:resultMap.applyOrgName}" autocomplete="off" style="width: 80%;"/></td>
            <td style="text-align: right" width="20%">计划研究开始时间：</td>
            <td width="30%" colspan="2"><input id="projStartTime" name="projStartTime"  type="text" value="${ empty resultMap.projStartTime?pdfn:transDateTimeForPattern(proj.projStartTime,'yyyy-MM-dd','yyyy年MM月'):resultMap.projStartTime}"
                                               class="inputText ctime validate[required]" style="width: 160px;"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy年MM月'})" /></td>
        </tr>
        <tr>
            <td style="text-align: right" width="20%">计划研究结束时间：</td>
            <td width="30%" colspan="2"><input id="projEndTime" name="projEndTime"  type="text" value="${empty resultMap.projEndTime?pdfn:transDateTimeForPattern(proj.projEndTime,'yyyy-MM-dd','yyyy年MM月'):resultMap.projEndTime}" class="inputText ctime validate[required]" style="width: 160px;"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy年MM月'})" /></td>
            <td style="text-align: right" width="20%">实际完成时间：</td>
            <td width="30%" colspan="2"><input name="compeleteTime"  type="text" value="${resultMap.compeleteTime}" class="inputText ctime validate[required]" style="width: 160px;"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy年MM月'})" /></td>
        </tr>
    </table>
</form>
<div class="button" style="width: 100%;
<c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
    <input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
    <input onclick="nextOpt('step2');" id="nxt" class="search" type="button" value="保&#12288;存"/>
</div>