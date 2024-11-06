<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="impromptu" value="true"/>
</jsp:include>
<style type="text/css">
    .searchTable td:nth-child(1),.searchTable td:nth-child(3){
        width: 5em;
    }
    .searchTable td:nth-child(5){
        width: 7em;
    }
</style>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
    $(document).ready(function(){
        search();

    });

    function toPage(page) {
        $("#currentPage").val(page);
        search();
    }

    function search(){
        var url = "<s:url value='/jsres/dept/queryBaseDept'/>";
        jboxPostLoad("searchContent", url, $("#searchForm").serialize(), false);
    }

    function editDept(deptFlow){
        debugger;
        var target = window.event.target;
        var curTr = $(target).parents('tr');
        var deptFlow = curTr.attr('dataflow');
        var ordinal = curTr.attr('dataordinal');
        var orgFlow = curTr.attr('orgFlow');
        var deptCode = curTr.find('.deptCode').attr('datacode');
        var deptName = curTr.find('.deptName').attr('dataname');

        var settings = {
            title: '编辑科室信息',
            operType: 'edit',
            deptFlow: deptFlow,
            orgFlow: orgFlow,
            deptCode: deptCode,
            deptName: deptName,
            ordinal: ordinal
        }

        promptShow(settings);
    }

    function deptAdd() {
        var settings = {
            title: '新增科室信息',
            operType: 'add',
        };

        promptShow(settings);
    }

    function relStdDept(deptFlow){
        var width = window.innerWidth * 0.85;
        var height = window.innerHeight * 0.85;
        var url = "<s:url value='/jsres/dept/configBaseDept' />" + "?deptFlow=" + deptFlow + "&height=" + (height - 120);
        jboxOpen(url, "关联标准科室", width, height, true);
    }

    function searchReset() {
        $("#deptCode,#deptNameFuzzy,#relStdDeptFlag").val("");
        search();
    }

    function promptShow(settings) {
        var html = $("#baseDeptEditTemplate").html();
        html = html.replace("\{operType}", settings.operType);
        html = html.replace("\{deptFlow}", settings.deptFlow || '');
        html = html.replace("\{orgFlow}", settings.orgFlow || '');
        html = html.replace("\{ordinal}", settings.ordinal || '');
        html = html.replaceAll("\{deptCode}", settings.deptCode || '');
        html = html.replaceAll("\{deptName}", settings.deptName || '');
        $.prompt({
            state0: {
                title: settings.title,
                html: html,
                buttons: {},
                submit:function(e,v,m,f){

                },
                close: function (t, e, i, n) {

                }
            }
        });
    }

    function closeEditDialog() { // 找不到插件关闭弹窗入口，只能模拟点击一下
        $(".jqiclose").trigger('click');
    }

    function editSave(operType, otherData) {
        debugger;
        var url = '<s:url value="/jsres/dept/saveBaseDept" />';
        var data = {
            operType: operType
        }
        if(operType == 'edit') {
            data.deptCode = $("input[name='baseDeptCodeEdit']").last().val(),
            data.deptName = $("input[name='baseDeptNameEdit']").last().val(),
            data.ordinal = $("input[name='baseOrdinalEdit']").last().val(),
            data.deptFlow = $(".deptFlowEdit").last().val();
            data.orgFlow = $("#orgFlow").val(),
            data.orgName = $("#orgName").val()

            if(!data.deptCode || !data.deptName || !data.ordinal) {
                jboxTip("请求入参不完整，请检查！");
                return;
            }
        }
        if(operType == 'add') {
            data.deptCode = $("input[name='baseDeptCodeEdit']").last().val(),
            data.deptName = $("input[name='baseDeptNameEdit']").last().val(),
            data.ordinal = $("input[name='baseOrdinalEdit']").last().val(),
            data.orgFlow = $("#orgFlow").val(),
            data.orgName = $("#orgName").val()

            if(!data.deptCode || !data.deptName || !data.ordinal || !data.orgFlow) {
                jboxTip("请求入参不完整，请检查！");
                return;
            }
        }
        if(operType == 'toggleStatus') {
            var target = window.event.target;
            var curTr = $(target).parents('tr');
            data.deptFlow = curTr.attr('dataflow');
            data.recordStatus = otherData.recordStatus;
        }

        jboxPostJson(url, JSON.stringify(data), function (resp) {
            jboxTip(resp);
            if(resp == '保存成功！') {
                closeEditDialog();
                search();
            }
        }, function(msg) {
           jboxTip("系统错误，请联系管理员！");
        });
    }

    function baseDeptDelete() {
        var target = window.event.target;
        var curTr = $(target).parents('tr');
        var deptFlow = curTr.attr('dataflow');
        jboxConfirm("是否确认删除？", function () {
            var url = '<s:url value="/jsres/dept/baseDeptDelete" />';
            var data = {
                deptFlow: deptFlow
            }

            jboxPostJson(url, JSON.stringify(data), function (resp) {
                jboxTip(resp);
                if(resp == '保存成功！') {
                    search();
                }
            }, function(msg) {
                jboxTip("系统错误，请联系管理员！");
            });
        });
    }

    function showDept(deptFlow, schDeptName, isJoin, speFlow) {
        var url = "<s:url value ='/jsres/base/showDeptInfo'/>?viewFlag=N&deptFlow=" + deptFlow + "&orgFlow=${sessionScope.currUser.orgFlow}&isJoin=Y&speFlow=" + speFlow+"&isglobal=N";
        jboxOpen(url, '科室信息（' + schDeptName + '）', 1200, 700);
    }
</script>
<!-- <div class="main_hd">
<h2 class="underline">科室维护</h2>
</div> -->
<div class="main_bd" id="div_table_0" >
    <div class="div_search">
        <form id="searchForm">
            <input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>
            <input id="orgFlow" type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow}"/>
            <input id="orgName" type="hidden" name="orgName" value="${sessionScope.currUser.orgName}"/>


            <div class="form_search">
                <div class="form_item">
                    <div class="form_label">科室代码：</div>
                    <div class="form_content">
                        <input type="text" name="deptCode" id="deptCode" value="" class="input"/>
                    </div>
                </div>
                <div class="form_item">
                    <div class="form_label">科室名称：</div>
                    <div class="form_content">
                        <input type="text" name="deptNameFuzzy" id="deptNameFuzzy" value="" class="input"/>
                    </div>
                </div>
                <div class="form_item" style="margin-left: 10px">
                    <div class="form_label">关联标准科室：</div>
                    <div class="form_content">
                        <select name="relStdDeptFlag" class="select" style="width: 161px;" id="relStdDeptFlag">
                        <option value="">全部</option>
                        <option value="Y">已关联</option>
                        <option value="N">未关联</option>
                    </select>
                    </div>
                </div>
            </div>

            <div style="margin-top: 15px;margin-bottom: 15px">
                <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                <input type="button" class="btn_grey" onclick="searchReset()" value="重&#12288;置">
                <input type="button" class="btn_green" onclick="deptAdd()" value="新&#12288;增">
            </div>

        </form>
    </div>

    <div id="searchContent">

    </div>

</div>
<div id="baseDeptEditTemplate" style="display: none">
    <div class="div_table" style="padding:0 10px 10px">
        <!-- <h4>编辑科室信息</h4> -->
        <form id="editForm" style="position: relative;" method="post">
            <input type="hidden" value="{orgFlow}">
            <input type="hidden" value="{deptFlow}" class="deptFlowEdit">
            <input type="hidden" value="{deptCode}" class="deptCodeEdit">
            <input type="hidden" value="{deptName}" class="deptNameEdit">
            <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
                <colgroup>
                    <col style="width: 40%" />
                    <col style="width: 60%" />
                </colgroup>
                <tbody>
                    <tr>
                        <th>科室名称：</th>
                        <td><input class="validate[required,minSize[1],maxSize[25]] input" name="baseDeptNameEdit" type="text" value="{deptName}" /></td>
                    </tr>
                    <tr>
                        <th>科室代码：</th>
                        <td><input class="validate[required,custom[integer],maxSize[50]] input" name="baseDeptCodeEdit" type="text" value="{deptCode}" /></td>
                    </tr>
                    <tr>
                        <th>排序码：</th>
                        <td><input class="validate[required,custom[integer],maxSize[50]] input" name="baseOrdinalEdit" type="text" value="{ordinal}" /></td>
                    </tr>
                </tbody>
            </table>
        </form>

        <div class="button">
            <input type="button" class="btn_green" onclick="editSave('{operType}')" value="保存"/>
            <input type="button" class="btn_green" onclick="closeEditDialog()" value="关闭"/>
        </div>
    </div>
</div>