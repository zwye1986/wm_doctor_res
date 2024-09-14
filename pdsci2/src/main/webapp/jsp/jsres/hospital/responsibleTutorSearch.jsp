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
    });

    function toPage(page) {
        $("#currentPage").val(page);
        search();
    }

    function search() {
        if($("input[name='deptName']").val()==''){
            $("input[name='deptFlow']").val('');
        }
        var url = "<s:url value='/jsres/manage/commonSzList'/>?orgFlow=${sessionScope.currUser.orgFlow}" + "&isQueryTutor=Y";
        jboxPostLoad("searchContent", url, $("#searchForm").serialize(), true);
    }

    function editCommonSzInfo(recordFlow,teacherLevelId) {
        var url = "<s:url value='/jsres/manage/editCommonSzInfo?'/>" + "recordFlow=" + recordFlow;
        if(teacherLevelId == 'GeneralFaculty'){
            jboxOpen(url, "编辑一般师资信息", 550, 540);
        }
        if('KeyFaculty' == teacherLevelId){
            jboxOpen(url, "编辑骨干师资信息", 550, 540);
        }
    }

    function deleteCommonSz(recordFlow){
        jboxConfirm("确定删除导师信息，不删除用户信息？", function () {
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
        var url = "<s:url value='/jsres/manage/exportSzList?orgFlow=${sessionScope.currUser.orgFlow}'/>" + "&isQueryTutor=Y";
        jboxExp($("#searchForm"), url);
    }

    function viewAttachment(recordFlow,recType,title){
        var url = "<s:url value='/jsres/manage/attachment'/>?recFlow="+recordFlow + "&recType=" + recType+ "&readonly=Y";
        jboxOpen(url, title,700,550);
    }

</script>


<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm" action="<s:url value="/jsres/manage/teacherList" />" method="post">
            <input type="hidden" name="currentPage" id="currentPage" value="${param.currentPage}">
            <input type="hidden" name="isResponsibleTutor" id="isResponsibleTutor" value="Y">

             <div class="form_search">
                 <div class="form_item">
                     <div class="form_label">姓名：</div>
                     <div class="form_content">
                         <input type="text" name="doctorName" value="${param.doctorName}" class="input" />
                     </div>
                 </div>

                 <div class="form_item">
                     <div class="form_label">手机号码：</div>
                     <div class="form_content">
                         <input type="text" name="userPhone" value="${param.userPhone}"  class="input"  />
                     </div>
                 </div>

                 <div class="form_item">
                     <div class="form_label">专业：</div>
                     <div class="form_content">
                         <select name="speId"  id="speId" class="select">
                             <option value="" >全部</option>
                             <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                 <option <c:if test="${teacher.speId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
                             </c:forEach>
                         </select>
                     </div>
                 </div>

                 <div class="form_item">
                    <div class="form_label">科室名称：</div>
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
                    <div class="form_label">证书编号：</div>
                    <div class="form_content">
                        <input type="text" name="certificateNo" value="${param.certificateNo}"  class="input" />
                    </div>
                </div>

                 <div class="form_item">
                     <div class="form_label">技术职称：</div>
                     <div class="form_content">
                         <input type="text" name="technicalTitle" value="${param.technicalTitle}"  class="input" />
                     </div>
                 </div>

                 <div class="form_item">
                     <div class="form_label">培训年份：</div>
                     <div class="form_content">
                         <input type="text" name="trainingYear" value="${param.trainingYear}"  class="input" />
                     </div>
                 </div>

                 <div class="form_item">
                     <div class="form_label">师资级别：</div>
                     <div class="form_content">
                         <select name="teacherLevelId" class="select" style="width: 160px;">
                             <option value="ALL">全部</option>
                             <c:forEach items="${jsResTeacherLevelEnumList}" var="type">
                                 <option value="${type.id}" <c:if test="${param.teacherLevelId eq type.id}">selected="selected"</c:if>>${type.name}</option>
                             </c:forEach>
                             <option value="">无</option>
                         </select>
                     </div>
                 </div>

             </div>


            <div style="margin-top: 15px;margin-bottom: 15px">
                <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                <input type="button" class="btn_green" onclick="exportUser()" value="导&#12288;出">
            </div>

        </form>
    </div>

    <div id="searchContent">
    </div>
</div>