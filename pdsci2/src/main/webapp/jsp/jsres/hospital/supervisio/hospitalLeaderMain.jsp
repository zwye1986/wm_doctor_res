<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red;
    }

    .searchTable {
        width: auto;
    }

    .searchTable td {
        width: auto;
        height: auto;
        line-height: auto;
        text-align: left;
        max-width: 150px;;
    }

    .searchTable .td_left {
        word-wrap: break-word;
        width: 5em;
        height: auto;
        line-height: auto;
        text-align: right;
    }



</style>
<%--<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>"></script>
<link href="<s:url value='/css/UCFORM.css'/>" rel="stylesheet" type="text/css">
<script src="<s:url value='/js/jQuery.UCSelect.js'/>" type="text/javascript"></script>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
    $(document).ready(function () {
        // $('select[name=trainingSpeId]').UCFormSelect();
        $(".UCSelect").css("width","107px");
        $(".SelectVal").css("width","170px");
        $(".SelectBox").css("width","159%");
        toPage();
    });

    function toPage(page) {
        if ($("#orgSel").val()==null || $("#orgSel").val()==''){
            $("#deptFlow").val('');
        }
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/supervisio/hospitalLeaderList'/>", $("#searchForm").serialize(), false);
    }


    function editHospitalLeader(type, userFlow) {
        var title = "新增";
        if (type == 'edit') {
            title = "修改";
        }
        var url = "<s:url value ='/jsres/supervisio/editHospitalLeader'/>?userFlow=" + userFlow + "&type=" + type;
        jboxOpen(url, title, 800, 380);
    }


    function importExcelUser() {
        jboxOpen("<s:url value='/jsres/supervisio/importHospitalLeaderExcel'/>", "导入专家信息", 500, 200);
    }


    function exportSupervisioUser() {
        var url = "<s:url value='/jsres/supervisio/exportHospitalLeader'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }

    function toDeptFlow(deptFlow) {
        $("#deptFlow").val(deptFlow);
    }


    /**
     *模糊查询加载
     */
    (function($){
        $.fn.likeSearchInit = function(option){
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            searchInput.on("keyup focus",function(){
                $("#boxHome").show();
                if($.trim(this.value)){
                    $("#boxHome .item").hide();
                    var items = $("#boxHome .item[value*='"+this.value+"']").show();
                    if(!items){
                        $("#boxHome").hide();
                    }
                }else{
                    $("#boxHome .item").show();
                }
            });
            searchInput.on("blur",function(){
                if(!$("#boxHome.on").length){
                    $("#boxHome").hide();
                }
            });
            $("#boxHome").on("mouseenter mouseleave",function(){
                $(this).toggleClass("on");
            });
            $("#boxHome .item").click(function(){
                $("#boxHome").hide();
                var value = $(this).attr("value");
                $("#itemName").val(value);
                searchInput.val(value);
                if(option.clickActive){
                    option['clickActive']($(this).attr("flow"));
                }
            });
        };
    })(jQuery);


    //页面加载完成时调用
    $(function(){
        $("#orgSel").likeSearchInit({});
    });


    $(function () {
        var edit = $('#edit').val();
        if (edit=='edit'){
            var subjectAddFalg = $("#userLevelId").find("option:selected").val();
            var select;
            var info = $('#info').val();
            for(var i=0; i<select.options.length; i++){
                if(select.options[i].value == info){
                    select.options[i].selected = true;
                    break;
                }
            }
        }
    })
</script>
<div class="main_hd">
    <h2 class="underline">评审专家维护</h2>
</div>
<div class="div_search" style="width: 95%;line-height:normal;">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>


        <div class="form_search">

            <div class="form_item">
                <div class="form_label">专家姓名：</div>
                <div>
                    <input type="text" name="userName" value="${param.userName}" class="input" placeholder="请输入专家姓名"
                        />
                </div>
            </div>
            <div class="form_item">
                <div class="form_label">专业：</div>
                <div>
                    <select name="trainingSpeId" class="select" id="trainingSpeId" >
                        <option value="" >全部</option>
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                            <c:if test="${'3500' ne dict.dictId and '3700' ne dict.dictId}">
                                <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form_item">
                <div class="form_label">科室：</div>
                <div class="form_content">
                    <input type="hidden" id="deptFlow" name="deptFlow" value="${user.deptFlow}">
                    <input id="orgSel" class="toggleView input" type="text" style="margin-left: 5px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 179px -4px;" name="deptName"
                           value="${user.deptName}" autocomplete="off" title="${param.deptName}"  placeholder="请选择科室"
                           onmouseover="this.title = this.value"/>
                    <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:5px;">
                        <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 161px;border-top: none;position: relative;display: none;">
                            <c:forEach items="${deptList}" var="dept">
                                <p class="item" flow="${dept.deptFlow}" value="${dept.deptName}" onclick="toDeptFlow('${dept.deptFlow}');" style="height: 30px;padding-left: 10px;text-align: left;">${dept.deptName}</p>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form_item">
                <div class="form_label">手机号码：</div>
                <div class="form_content" >
                    <input type="text" name="userPhone" value="${param.userName}" class="input" placeholder="请输入手机号码"
                        />
                </div>
            </div>
        </div>

        <div style="margin-top: 15px;margin-bottom: 15px">
            <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
            <input class="btn_green" type="button" value="新&#12288;增" onclick="editHospitalLeader('add','');"/>
            <input class="btn_green" type="button" value="导&#12288;入" onclick="importExcelUser();"/>
            <input class="btn_green" type="button" value="导&#12288;出" onclick="exportSupervisioUser();"/>
        </div>

<%--        <div style="display: flex;justify-content: flex-start; column-gap: 56px;margin-top: 15px">--%>
<%--            <div>--%>
<%--                <label class="form_label">专家姓名：</label>--%>
<%--                <input type="text" name="userName" value="${param.userName}" class="input" placeholder="请输入专家姓名"--%>
<%--                       style="width: 161px;"/>--%>
<%--            </div>--%>
<%--            <div>--%>
<%--                <label class="form_label">专业：</label>--%>
<%--                <select name="trainingSpeId" class="select" id="trainingSpeId" style="width: 161px;" >--%>
<%--                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
<%--                        <c:if test="${'3500' ne dict.dictId and '3700' ne dict.dictId}">--%>
<%--                            <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>--%>
<%--                        </c:if>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--            <div>--%>
<%--                <label class="form_label">科室：</label>--%>
<%--                <input type="hidden" id="deptFlow" name="deptFlow" value="${user.deptFlow}">--%>
<%--                <input id="orgSel" class="toggleView input" type="text" style="width: 161px;margin-left: 5px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 179px -4px;" name="deptName"--%>
<%--                       value="${user.deptName}" autocomplete="off" title="${param.deptName}"  placeholder="请选择科室"--%>
<%--                       onmouseover="this.title = this.value"/>--%>
<%--                <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:5px;">--%>
<%--                    <div id="boxHome" style="max-height: 250px; margin-left: 45px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 169px;border-top: none;position: relative;display: none;">--%>
<%--                        <c:forEach items="${deptList}" var="dept">--%>
<%--                            <p class="item" flow="${dept.deptFlow}" value="${dept.deptName}" onclick="toDeptFlow('${dept.deptFlow}');" style="height: 30px;padding-left: 10px;text-align: left;">${dept.deptName}</p>--%>
<%--                        </c:forEach>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div>--%>
<%--                <label class="form_label">手机号码：</label>--%>
<%--                <input type="text" name="userPhone" value="${param.userName}" class="input" placeholder="请输入手机号码"--%>
<%--                       style="width: 161px;margin-left: 0px;"/>--%>
<%--            </div>--%>
<%--        </div>--%>




<%--        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">--%>
<%--            <tr>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr style="font-size: 14px">专家姓名：</nobr>--%>
<%--                </td>--%>
<%--                <td style="width: 150px;">--%>
<%--                    <input type="text" name="userName" value="${param.userName}" class="input" placeholder="请输入专家姓名"--%>
<%--                           style="width: 145px;margin-left: 0px;"/>--%>
<%--                </td>--%>
<%--                <td class="td_left" style="font-size: 14px">--%>
<%--                    <nobr style="margin-left: 85px;">专&#12288;&#12288;业：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <select name="trainingSpeId" class="select" id="trainingSpeId" style="width: 150px;" multiple="multiple">--%>
<%--                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
<%--                            <c:if test="${'3500' ne dict.dictId and '3700' ne dict.dictId}">--%>
<%--                                <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>--%>
<%--                            </c:if>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                </td>--%>

<%--                <td class="td_left">--%>
<%--                    <nobr style="margin-left: 90px;font-size: 14px">科&#12288;&#12288;室：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <input type="hidden" id="deptFlow" name="deptFlow" value="${user.deptFlow}">--%>
<%--                    <input id="orgSel" class="toggleView input" type="text" style="width: 211px;margin-left: 5px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 179px -4px;" name="deptName"--%>
<%--                           value="${user.deptName}" autocomplete="off" title="${param.deptName}"  placeholder="请选择科室"--%>
<%--                           onmouseover="this.title = this.value"/>--%>
<%--                    <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:5px;">--%>
<%--                        <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 220px;border-top: none;position: relative;display: none;">--%>
<%--                            <c:forEach items="${deptList}" var="dept">--%>
<%--                                <p class="item" flow="${dept.deptFlow}" value="${dept.deptName}" onclick="toDeptFlow('${dept.deptFlow}');" style="height: 30px;padding-left: 10px;text-align: left;">${dept.deptName}</p>--%>
<%--                            </c:forEach>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr style="font-size: 14px">手机号码：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <input type="text" name="userPhone" value="${param.userName}" class="input" placeholder="请输入手机号码"--%>
<%--                           style="width: 145px;margin-left: 0px;"/>--%>
<%--                </td>--%>

<%--                <td colspan="8" style="text-align: right">--%>
<%--                    <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>&#12288;--%>
<%--                    <input class="btn_green" type="button" value="新&#12288;增" onclick="editHospitalLeader('add','');"/>&#12288;--%>
<%--                    <input class="btn_green" type="button" value="导&#12288;入" onclick="importExcelUser();"/>&#12288;--%>
<%--                    <input class="btn_green" type="button" value="导&#12288;出" onclick="exportSupervisioUser();" style="margin-right: 20px;"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>

    </form>
</div>
<div id="doctorListZi">

</div>