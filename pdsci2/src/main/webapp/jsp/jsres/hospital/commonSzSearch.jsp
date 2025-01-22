<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="bootstrapSelect" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css"/>
<%--<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect.js'/>"></script>--%>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    /*.input{*/
    /*    margin: 0 12px 0 4px;*/
    /*    width: 161px;*/
    /*}*/

    .btn-group.bootstrap-select {
        margin: 0 12px 0 4px !important;
        width: 182px !important;
        height: 30px;
    }

    .text{
        margin-left: 0;
        width: auto;
        height: auto;
        line-height: inherit;
        color: black;
    }
    .selected a{
        padding: 0;
        background: #459ae9;
    }
    .btn{
        height: 30px !important;
        border: 1px solid #e7e7eb
    }
    .body{
        width: 90%;
        margin-left: auto;
        margin-right: auto;
        padding: 0 0 88px;
    }
    .container{
        padding-left: 0;
        padding-right: 0;
    }
    .btn-default{
        background-color: #fff;
    }
    .div_search span{
        font-weight: inherit;
        float: none;
    }
    .div_search{
        line-height: 35px;
    }
    .clearfix {
        clear: both;
        height: 0;
    }
    .bootstrap-select>.dropdown-toggle {
        width: 161px;
        padding-right: 25px;
        /*border: 1px;*/
        border: 1px solid #e7e7eb;
    }
    .btn-group.bootstrap-select {
        margin: 0 !IMPORTANT;
        width: 182px !important;
        height: 30px;
    }


</style>
<script type="text/javascript">
    (function ($) {
        $.fn.likeSearchInit = function (option) {
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus", function () {
                var boxHome = $("#" + spaceId + "Sel");
                boxHome.show();
                if ($.trim(this.value)) {
                    $("p." + spaceId + ".item", boxHome).hide();
                    var items = boxHome.find("p." + spaceId + ".item[value*='" + this.value + "']").show();
                    if (!items) {
                        boxHome.hide();
                    }
                } else {
                    $("p." + spaceId + ".item", boxHome).show();
                }
            });
            searchInput.on("blur", function () {
                var boxHome = $("#" + spaceId + "Sel");
                if (!$("." + spaceId + ".boxHome.on").length) {
                    boxHome.hide();
                }
            });
            $("." + spaceId + ".boxHome").on("mouseenter mouseleave", function () {
                $(this).toggleClass("on");
            });

            $("." + spaceId + ".boxHome .item").click(function () {
                var boxHome = $("." + spaceId + ".boxHome");
                boxHome.hide();
                var value = $(this).attr("value");
                var flow = $(this).attr("flow");
                var input = $("#" + spaceId);
                var inputFlow = $("#" + spaceId+"Flow");
                input.val(value);
                inputFlow.val(flow);
                if (option.clickActive) {
                    option.clickActive($(this).attr("flow"));
                }
// 			var content = $("#clone").html().replace("title",value);
// 			$("#"+input.attr("id")+"Div").append(content);
            });
        };
    })(jQuery);

    $(document).ready(function () {
        search();
        $("#ksmc").likeSearchInit({
            clickActive: function (flow) {
                $("." + flow).show();
            }
        });

        $('#trainingYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
    });

    function toPage(page) {
        $("#currentPage").val(page);
        search();
    }

    function search() {
        if($("input[name='deptName']").val()==''){
            $("input[name='deptFlow']").val('');
        }
        var url = "<s:url value='/jsres/manage/commonSzList'/>?orgFlow=${sessionScope.currUser.orgFlow}";
        jboxPostLoad("searchContent", url, $("#searchForm").serialize(), true);
    }

    function editCommonSzInfo(recordFlow, flag) {
        var url = "<s:url value='/jsres/manage/editCommonSzInfo?'/>" + "recordFlow=" + recordFlow + "&flag=" + flag;
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='800px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        if (flag == "view") {
            jboxMessager(iframe, "查看师资信息", 1400, 800);
        } else {
            jboxMessager(iframe, "编辑师资信息", 1400, 800);
        }
    }

    function deleteCommonSz(recordFlow){
        jboxConfirm("相关师资信息将会清空，是否确认取消师资？", function () {
            var url = "<s:url value='/jsres/manage/deleteCommonSzInfo?'/>" + "recordFlow=" + recordFlow;
            jboxPost(url, null, function(data) {
                if('${GlobalConstant.OPRE_SUCCESSED_FLAG}' == data){
                    search();
                }else{
                    jboxTip("删除失败");
                }
            },null,false);
        });
    }

    function exportUser() {
        var url = "<s:url value='/jsres/manage/exportSzList?orgFlow=${sessionScope.currUser.orgFlow}'/>";
        jboxExp($("#searchForm"), url);
    }

    function viewAttachment(recordFlow,recType,title){
        var url = "<s:url value='/jsres/manage/attachment'/>?recFlow="+recordFlow + "&recType=" + recType+ "&readonly=Y";
        jboxOpen(url, title,700,550);
    }

    function importTeacher() {
        var url = "<s:url value='/jsres/manage/importTeacher'/>";
        jboxOpen(url, "师资导入", 700, 250);
    }

</script>


<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm" action="<s:url value="/jsres/manage/teacherList" />" method="post">
            <input type="hidden" name="currentPage" id="currentPage" value="${param.currentPage}">
            <input type="hidden" name="teacherLevelId" id="teacherLevelId" value="${teacherLevelId}">

             <div class="form_search">
                 <div class="form_item">
                     <div class="form_label">姓&#12288;&#12288;名：</div>
                     <div class="form_content">
                         <input type="text" name="doctorName" value="${param.doctorName}" class="input" />
                     </div>
                 </div>

                 <div class="form_item">
                     <div class="form_label">科&#12288;&#12288;室：</div>
                     <div class="form_content">
                         <input type="text" id="ksmc" name="deptName" value="${param.deptName}" class=" input" autocomplete="off"/>
                         <input id="ksmcFlow" name="deptFlow" value="${param.deptFlow}" hidden="hidden"/>
                         <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; left:0px; top:30px;">
                             <div class="boxHome ksmc" id="ksmcSel"
                                  style="margin-left: -161px; max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 161px;border-top: none;position: relative;display:none;">
                                 <c:forEach items="${sysDeptList}" var="dept">
                                     <p class="item ksmc" flow="${dept.deptFlow}" value="${dept.deptName}"
                                        style="line-height: 25px; padding:0px 0;cursor: default;width: 100% ">${dept.deptName}</p>
                                 </c:forEach>
                             </div>
                         </div>
                     </div>
                 </div>

                 <div class="form_item">
                     <div class="form_label">技术职务：</div>
                     <div class="form_content">
                         <select name="technicalPositionId" class="select" style="width: 160px;">
                             <option value="" >请选择</option>
                             <option value="chiefPhysician" <c:if test="${param.technicalPositionId eq 'chiefPhysician'}">selected="selected"</c:if>>主任医师</option>
                             <option value="associateChiefPhysician" <c:if test="${param.technicalPositionId eq 'associateChiefPhysician'}">selected="selected"</c:if>>副主任医师</option>
                             <option value="physicianInCharge" <c:if test="${param.technicalPositionId eq 'physicianInCharge'}">selected="selected"</c:if>>主治医师</option>
                             <option value="seniorTechnologist" <c:if test="${param.technicalPositionId eq 'seniorTechnologist'}">selected="selected"</c:if>>主任技师</option>
                             <option value="deputyChiefTechnician" <c:if test="${param.technicalPositionId eq 'deputyChiefTechnician'}">selected="selected"</c:if>>副主任技师</option>
                             <option value="attendingTechnician" <c:if test="${param.technicalPositionId eq 'attendingTechnician'}">selected="selected"</c:if>>主管技师</option>
                         </select>
                     </div>
                 </div>

                 <div class="form_item">
                     <div class="form_label">培训年份：</div>
                     <div class="form_content">
                         <input type="text" id="trainingYear" name="trainingYear" value="${param.trainingYear}"  class="input" />
                     </div>
                 </div>

<%--                 <div class="form_item">--%>
<%--                     <div class="form_label">手机号码：</div>--%>
<%--                     <div class="form_content">--%>
<%--                         <input type="text" name="userPhone" value="${param.userPhone}"  class="input"  />--%>
<%--                     </div>--%>
<%--                 </div>--%>

                 <div class="form_item">
                     <div class="form_label">培训专业：</div>
                     <div class="form_content">
                         <select name="speId"  id="speId" class="select">
                             <option value="" >请选择</option>
                             <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                 <option <c:if test="${teacher.speId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
                             </c:forEach>
                         </select>
                     </div>
                 </div>

<%--                 <div class="form_item">--%>
<%--                     <div class="form_label">责任导师：</div>--%>
<%--                     <div class="form_content">--%>
<%--                         <select name="isResponsibleTutor" class="select" style="width: 160px;">--%>
<%--                             <option value="" >全部</option>--%>
<%--                             <option value="Y" <c:if test="${param.isResponsibleTutor eq 'Y'}">selected="selected"</c:if>>是</option>--%>
<%--                             <option value="N" <c:if test="${param.isResponsibleTutor eq 'N'}">selected="selected"</c:if>>否</option>--%>
<%--                         </select>--%>
<%--                     </div>--%>
<%--                 </div>--%>

             </div>

            <div class="form_search">
                <div class="form_item">
                    <div class="form_label">证书等级：</div>
                    <div class="form_content">
                        <select name="certificateLevelId" class="select" style="width: 160px;">
                            <option value="" >请选择</option>
                            <option value="1" <c:if test="${param.certificateLevelId eq '1'}">selected="selected"</c:if>>国家级</option>
                            <option value="2" <c:if test="${param.certificateLevelId eq '2'}">selected="selected"</c:if>>省级</option>
                            <option value="3" <c:if test="${param.certificateLevelId eq '3'}">selected="selected"</c:if>>市级</option>
                            <option value="4" <c:if test="${param.certificateLevelId eq '4'}">selected="selected"</c:if>>院级</option>
                        </select>
                    </div>
                </div>
            </div>


            <div style="margin-top: 15px;margin-bottom: 15px">
                <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                <input type="button" class="btn_green" onclick="exportUser()" value="导&#12288;出">
                <input type="button" class="btn_green" onclick="importTeacher()" value="师资导入">
            </div>

        </form>
    </div>

    <div id="searchContent">
    </div>
</div>