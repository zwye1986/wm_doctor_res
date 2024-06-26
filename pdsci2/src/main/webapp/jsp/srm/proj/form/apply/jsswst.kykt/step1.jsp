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
        var setting = {
            view: {
                dblClickExpand: false,
                showIcon: false,
                showTitle:false,
                selectedMulti:false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick: onClick
            }
        };

        function beforeClick(treeId, treeNode) {
            var check = (treeNode.id!=0);
            if (!check) jboxTip('不能选择根节点');
            return check;
        }

        function onClick(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = zTree.getSelectedNodes(),
                    v = "";
            id = "";
            nodes.sort(function compare(a,b){return a.id-b.id;});
            for (var i=0, l=nodes.length; i<l; i++) {
                v += nodes[i].name + ",";
                id += nodes[i].id + ",";
            }
            if (v.length > 0 ) v = v.substring(0, v.length-1);
            if (id.length > 0 ) id = id.substring(0, id.length-1);
            var cityObj = $("#proSelect");
            $("#selectProjId").val(id);
            cityObj.attr("value", v);
        }
        function showMenu() {
            var cityObj = $("#proSelect");
            var cityOffset = $("#proSelect").offset();
            $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

            $("body").bind("mousedown", onBodyDown);
        }
        function hideMenu() {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                hideMenu();
            }
        }
        $(document).ready(function(){
            var url = "<s:url value='/sys/subject/getAllDataJson'/>";
            jboxPostJson(url,null,function(data){
                if(data){
                    zNodes = $.parseJSON(data);
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                }
            },null,false);
        });
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" name="pageName" value="step1"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

<div>
    <table class="basic" style="width: 100%">
        <tr>
            <th colspan="4" class="theader"></th>
        </tr>
        <tr>
            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>课题名称：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="inputText" name="topics"
                       value="<c:if test='${empty resultMap.topics and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${!empty resultMap.topics}'>${resultMap.topics}</c:if>"
                       style="width: 46%"/>
            </td>
        </tr>
        <tr>
            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>课题负责人：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="validate[required] inputText" name='applicant'
                           value="<c:if test='${empty resultMap.applicant and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${! empty resultMap.applicant}'>${resultMap.applicant}</c:if>"
                           style="width: 46%"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>电&#12288;&#12288;话：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="validate[required,custom[phone]] inputText" name='phoneNum'
                           value="<c:if test='${empty resultMap.applicant and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.phoneNum}</c:if><c:if test='${! empty resultMap.phoneNum}'>${resultMap.phoneNum}</c:if>"
                           style="width: 46%"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>电子邮箱：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="validate[required,custom[email]] inputText" name='emails'
                           value="${resultMap.emails}" style="width: 46%"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>依托单位：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="validate[required] inputText" name="support_org"
                           value="<c:if test='${empty resultMap.support_org and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyOrgName}</c:if><c:if test='${!empty resultMap.support_org}'>${resultMap.support_org}</c:if>"
                           style="width: 46%"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>地&#12288;&#12288;址：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="validate[required] inputText" name="address"
                           value="<c:if test='${empty resultMap.address and param.view!=GlobalConstant.FLAG_Y}'>${org.orgAddress}</c:if><c:if test='${!empty resultMap.address}'>${resultMap.address}</c:if>"
                           style="width: 46%"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>邮政编码：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="validate[required,custom[chinaZip]] inputText" name="postCode"
                           value="${resultMap.postCode}"
                           style="width: 46%"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>主管部门：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                    <input type="text" class="inputText validate[required]" name="chargeOrg"
                           value="<c:if test='${! empty resultMap.chargeOrg}'>${resultMap.chargeOrg}</c:if>"
                           style="width: 46%"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>填报日期：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="inputText validate[required] ctime" name="reportTime"
                       value="<c:if test='${empty resultMap.reportTime and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.reportTime}</c:if><c:if test='${! empty resultMap.reportTime}'>${resultMap.reportTime}</c:if>"
                       onclick="WdatePicker({dateFmt:'yyyy年MM月dd日'})" readonly="readonly" style="width: 46%"/>
            </td>
        </tr>
        <tr>
            <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>研究期限：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <%--<span class="inputText" >2017年1月1日——2018年12月31日</span>--%>
                <input class="inputText validate[required] ctime" type="text" name="projStartTime" value="${empty resultMap.projStartTime?proj.projStartTime:resultMap.projStartTime}" onClick="WdatePicker({dateFmt:'yyyy年MM月dd日'})" readonly="readonly" style="width: 22%"/>
                ~ <input class="inputText validate[required] ctime" type="text" name="projEndTime" value="${empty resultMap.projEndTime?proj.projEndTime:resultMap.projEndTime}" onClick="WdatePicker({dateFmt:'yyyy年MM月dd日'})" readonly="readonly" style="width: 22%"/>
            </td>
        </tr>
        <tr>
            <th >申报学科：</th>
            <td style="text-align: left;padding-left: 10px;">
                <%--<input type="hidden" id="selectProjId" name='selectProjId' value='${projInfoMap.subjectCode}'>--%>
                <input id="proSelect" name="subjectType" class="inputText" value="${proj.subjName}"
                       readonly="readonly" class="" style="width: 46%"/>
            </td>
        </tr>
        <tr>
            <th >受理编号：</th>
            <td style="text-align: left;padding-left: 10px;">
                <input type="text" readonly="readonly" class="inputText" placeholder="由卫计委审核时录入" name="acceptNum"
                       value="${proj.acceptNumber}" style="width: 46%"/>
            </td>
        </tr>
        <tr>
            <th >申报计划类别：</th>
            <td style="text-align: left;padding-left: 10px;">
                <input type="text" readonly="readonly" class="inputText" name="applyPlanType"
                       value="${proj.projTypeName}" style="width: 46%"/>
            </td>
        </tr>
    </table>
</div>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
        <input id="nxt" type="button" onclick="nextOpt('step2')" class="search" value="下一步"/>
    </div>
</c:if>