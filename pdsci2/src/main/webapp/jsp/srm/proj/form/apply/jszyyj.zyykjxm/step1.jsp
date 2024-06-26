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
            var mainTop = $("#main").offset().top+$("#main").outerHeight();
            var top=cityOffset.top + cityObj.outerHeight();
//            console.log(mainTop);
//            console.log(top);
            if(mainTop-top<250)
            {
                top=top-220- cityObj.outerHeight();
            }
//            console.log(top);
            $("#menuContent").css({"margin-left":cityOffset.left-20 + "px",
                top:top + "px"}).slideDown("fast");

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


    <table class="bs_tb" style="width: 100%">
        <tr>
            <th colspan="4" class="theader"><input type="text" class="validate[required] inputText" name="projYear"
                                                   value="<c:if test='${empty resultMap.projYear and param.view!=GlobalConstant.FLAG_Y}'>${proj.projYear}</c:if><c:if test='${!empty resultMap.projYear}'>${resultMap.projYear}</c:if>"
                                                   style="width:100px;margin-left: 20%"/>年度江苏省中医药科技项目申报书
            </th>
        </tr>
        <tr>
            <th >课题名称：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="validate[required] inputText" name="subjectName"
                       value="<c:if test='${empty resultMap.subjectName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${!empty resultMap.subjectName}'>${resultMap.subjectName}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <th>&#12288;申请人：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="validate[required] inputText" name='applName'
                       value="<c:if test='${empty resultMap.applName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${! empty resultMap.applName}'>${resultMap.applName}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <th >所在单位：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="validate[required] inputText" name="applyOrgName"
                       value="<c:if test='${empty resultMap.applyOrgName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyOrgName}</c:if><c:if test='${!empty resultMap.applyOrgName}'>${resultMap.applyOrgName}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <th >地&#12288;&#12288;址：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="validate[required] inputText" name="jcrcAddress"
                       value="<c:if test='${empty resultMap.jcrcAddress and param.view!=GlobalConstant.FLAG_Y}'>${org.orgAddress}</c:if><c:if test='${!empty resultMap.jcrcAddress}'>${resultMap.jcrcAddress}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <th >邮政编码：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="inputText validate[required]" name="orgPostcode"
                       value="<c:if test='${empty resultMap.orgPostcode and param.view!=GlobalConstant.FLAG_Y}'>${org.orgZip}</c:if><c:if test='${! empty resultMap.orgPostcode}'>${resultMap.orgPostcode}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <th >移动电话：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="inputText validate[required,custom[phone]]" name="userPhone"
                       value="<c:if test='${empty resultMap.userPhone and param.view!=GlobalConstant.FLAG_Y}'>${user.userPhone}</c:if><c:if test='${! empty resultMap.userPhone}'>${resultMap.userPhone}</c:if>"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <th >传&#12288;&#12288;真：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="inputText" name="userFax"
                       value="${resultMap.userFax}"
                       style="width: 46%"/>
            </td>
        </tr>
        <tr>
            <th >电子信箱：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input type="text" class="inputText validate[required,custom[email]]" name="userMail"
                       value="${resultMap.userMail}"
                       style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <th >申请日期：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <input class="inputText validate[required]" type="text" name="applyDate"
                       value="${resultMap.applyDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                       readonly="readonly" style="width: 46%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <th >受理编号：</th>
            <td style="text-align: left;padding-left: 10px;">
                <input type="text" readonly="readonly" class="inputText" placeholder="由中医药局审核时录入" name="acceptNum"
                       value="${proj.acceptNumber}" style="width: 90%"/>
            </td>
            <th >申报学科：</th>
            <td style="text-align: left;padding-left: 10px;">
                <%--<input type="hidden" id="selectProjId" name='selectProjId' value='${projInfoMap.subjectCode}'>--%>
                <input id="proSelect" name="applSubject" class="inputText" value="<c:if test="${empty resultMap.applSubject}">${projInfoMap.subjectName}</c:if>${resultMap.applSubject}"
                       readonly="readonly" class="" style="width: 180px;text-align: left"
                       onclick="showMenu(); return false;"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <th>项目类型：</th>
            <td style="text-align: left;padding-left: 10px;" colspan="3">
                <select name="proType" class="inputText validate[required]" style="width: 46%;">
                    <option value="">请选择</option>
                    <option value="重点项目"
                            <c:if test="${resultMap.proType eq '重点项目'}"> selected="selected"</c:if>
                            <c:if test="${(empty resultMap.proType) and (proj.projTypeId eq 'jszyyj.zdxm')}"> selected="selected"</c:if>>
                        重点项目</option>
                    <option value="一般项目"
                            <c:if test="${resultMap.proType eq '一般项目'}"> selected="selected"</c:if>
                            <c:if test="${(empty resultMap.proType) and (proj.projTypeId eq 'jszyyj.ybxm')}"> selected="selected"</c:if>>
                        一般项目
                    </option>
                </select>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
    </table>
</form>
<div id="menuContent" class="menuContent" style="display:none;position: absolute;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:190px;height: 200px"></ul>
</div>
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
        <input id="nxt" type="button" onclick="nextOpt('step2')" class="search" value="下一步"/>
    </div>
</c:if>