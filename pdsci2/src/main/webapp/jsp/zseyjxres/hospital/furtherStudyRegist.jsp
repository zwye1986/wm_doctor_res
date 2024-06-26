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
<style>
    th{
        text-align: right;
    }
    .td_left{
        text-align: left;
    }
    select {
        margin: 0 5px;
        width: 191px;
        text-align: center;
    }
    .suggest{
        position:absolute;
        background-color:#FFFFFF;
        text-align: left;
        border: 1px solid lightgrey;
        display: none;
    }
    .selectP{

    }
</style>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('#admissionTime').datepicker();
        $('#stuStartTime').datepicker();
        $('#stuEndTime').datepicker();
    });
    //提交
    function subStudy(){
        var titleName = $("#titleId").find("option:selected").text();
        $("#titleName").val(titleName);
        var stationPropertyName = $("input[type='radio']:checked").next().text();
        $("#stationPropertyName").val(stationPropertyName);
        var stuStartTime = $("#stuStartTime").val();
        var stuEndTime = $("#stuEndTime").val();
        if(stuStartTime>stuEndTime){
            jboxTip("开始日期不能大于结束日期");
            return false;
        }
        if(false == $("#editForm").validationEngine("validate")){
            return false;
        }
        jboxConfirm("确认保存?" ,  function(){
            jboxPost("<s:url value='/zseyjxres/hospital/editStudyRegist'/>",$("#editForm").serialize(),function(resp){
                if(resp == '${GlobalConstant.SAVE_SUCCESSED}'){
                    jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
                    furtherStudyRegist();
                }
            },null,true);
        });
    }

    <%--function changeDept(exp){--%>
        <%--var deptId = $(exp).find("option:selected").val();--%>
        <%--jboxPost("<s:url value='/gzzyjxres/hospital/changeDept'/>",{"deptId":deptId},function(data){--%>
            <%--var list=eval(data);--%>
            <%--if(list!=null)--%>
            <%--{--%>
                <%--var selectUser = $("[name='userFlow']").html("");--%>
                <%--var html="<option value=''>------请选择------</option>"--%>
                <%--for(var i=0;i<list.length;i++)--%>
                <%--{--%>
                    <%--html+="<option value='"+list[i].userFlow+"'>"+list[i].userName+"</option>";--%>
                <%--}--%>
                <%--selectUser = $("[name='userFlow']").html(html);--%>
            <%--}--%>
            <%--if(list!=null & list.length < 1){--%>
                <%--jboxTip("该科室下无可选人员！");--%>
            <%--}--%>
        <%--},null,false);--%>
    <%--}--%>

    //重置
    function reSet(){
        jboxConfirm("重置后会将填写数据置空？",function(){
            furtherStudyRegist();
        },null);
    }
    function searchSuggest(resp){
        var url = "<s:url value='/zseyjxres/hospital/searchSuggest'/>";
        var searchData = "";
        if(resp == "deptName"){
            var deptName = $("#"+resp).val();
            searchData = {"deptName":deptName};
        }
        if(resp == "userName"){
            var deptName = $("#deptName").val();
            var userName = $("#"+resp).val();
            searchData = {"deptName":deptName,"userName":userName};
        }
        jboxPost(url,searchData,function(data){
            var list = eval(data);
            var xParent = $("#"+resp).offset().top;
            var hParent = $("#"+resp).offset().height;
            var yParent = $("#"+resp).offset().left;
            if(list != null)
            {
                var suggestDiv = $("#"+resp).next().html("");
                var html = "";
                for(var i=0;i<list.length;i++)
                {
                    if(resp == "deptName"){
                        html+="<p class='selectP' deptId='"+list[i].dictId+"'>"+list[i].dictName+"</p>";
                    }
                    if(resp == "userName"){
                        html+="<p class='selectP' userFlow='"+list[i].userFlow+"'>"+list[i].userName+"</p>";
                    }
                }
                suggestDiv.html(html);
//                suggestDiv.css({"position":"absolute", "top":xParent+hParent+2, "left":yParent-332});
                $(".selectP").bind("mouseover",function(){
                    $(this).css("background-color","#f1eae2");
                });
                $(".selectP").bind("mouseout",function(){
                    $(this).css("background-color","#fff");
                });
                $(".selectP").bind("click",function(e){
                    $("#"+resp).val($(this).text());
                    if(resp == "deptName"){
                        var deptId = $(this).attr("deptId");
                        $("#deptId").val(deptId);
                        $("#deptId").attr("deptName",$(this).text());
                        $("#userName").val("");
                        $("#userFlow").val("");
                    }
                    if(resp == "userName"){
                        var userFlow = $(this).attr("userFlow");
                        $("#userFlow").val(userFlow);
                        $("#userFlow").attr("userName",$(this).text());
                    }
                    if (e.stopPropagation)
                        e.stopPropagation();
                    else
                        e.cancelBubble = true;
                    suggestDiv.hide();
                });
                $(document).bind('click',function(){
                    suggestDiv.hide();
                    if(!$("#userFlow").val()){
                        $("#userName").val("");
                    }
                    if(!$("#deptId").val()){
                        $("#deptName").val("");
                    }
                    var userName = $("#userFlow").attr("userName");
                    var deptName = $("#deptId").attr("deptName");
                    if(userName != $("#userName").val()){
                        $("#userName").val("");
                    }
                    if(deptName != $("#deptName").val()){
                        $("#deptName").val("");
                    }
                });
                suggestDiv.show();
            }
        },null,false);
    }
    function stopPropagation(e) {
        if (e.stopPropagation)
            e.stopPropagation();
        else
            e.cancelBubble = true;
    }
</script>
<div class="main_hd">
    <h2 class="underline">外出进修登记
        <div class="fr"></div>
    </h2>
</div>
<div class="div_search">
</div>
<div class="search_table">
    <form id="editForm" style="position: relative;">
        <input type="hidden" id="userFlow" name="userFlow" value="" />
        <input type="hidden" id="deptId" name="deptId" value="" />
        <input type="hidden" id="titleName" name="titleName" value="" />
        <input type="hidden" id="stationPropertyName" name="stationPropertyName" value="" />
        <table class="grid" width="100%;">
            <colgroup>
                <col width="25%" />
                <col width="25%" />
                <col width="25%" />
                <col width="25%" />
            </colgroup>
            <tr>
                <th>所在科室（部门）：<span class="red">*</span></th>
                <td class="td_left">
                    <input id="deptName" name="deptName" class="validate[required] input"
                           onclick="stopPropagation(event) " onkeyup="searchSuggest('deptName');" onfocus="searchSuggest('deptName');"
                           autocomplete="off" style="width: 185px;text-align: center;"/>
                    <div class="suggest"  style="width:189px;overflow: auto;max-height: 150px;margin-left:5px;"></div>

                </td>
                <th>姓名：<span class="red">*</span></th>
                <td  class="td_left">
                    <input id="userName" name="userName" class="validate[required] input"
                           onclick="stopPropagation(event) " onkeyup="searchSuggest('userName');" onfocus="searchSuggest('userName');"
                           autocomplete="off" style="width: 185px;text-align: center;"/>
                    <div class="suggest"  style="width:189px;overflow: auto;max-height: 150px;margin-left:5px;"></div>
                </td>
            </tr>
            <tr>
                <th>年龄：<span class="red">*</span></th>
                <td  class="td_left">
                    <input name="userAge" class="validate[required] validate[custom[integer],min[0],max[150]] input" style="width: 185px;;text-align: center;"/>&nbsp;
                </td>
                <th>从事专业：<span class="red">*</span></th>
                <td  class="td_left">
                    <input name="engagedProfession" class="validate[required] input" style="width: 185px;;text-align: center;"/>&nbsp;
                </td>
            </tr>
            <tr>
                <th>入院时间（年限）：<span class="red">*</span></th>
                <td  class="td_left">
                    <input id="admissionTime" type="text" name="admissionTime" class="validate[required] input" readonly="readonly" style="width: 185px;;text-align: center;"/>&nbsp;
                </td>
                <th>职称：<span class="red">*</span></th>
                <td  class="td_left">
                    <select id="titleId" name="titleId" class="select validate[required]"  >
                        <option value="">-----------请选择-----------</option>
                        <c:forEach items="${dictTypeEnumTitleGenreList}" var="title">
                            <option value="${title.dictId}">${title.dictName}</option>
                        </c:forEach>
                    </select>&nbsp;
                </td>
            </tr>
            <tr>
                <th>岗位性质：<span class="red">*</span></th>
                <td  class="td_left">
                    <c:forEach items="${stationPropertyEnumList}" var="property">
                        &nbsp;<input id="${property.id}" <c:if test="${property.id eq stationPropertyEnumOther.id}">checked="checked"</c:if> type="radio" name="stationPropertyId" value="${property.id}">&nbsp;
                        <label for="${property.id}">${property.name}</label>
                    </c:forEach>&nbsp;
                </td>
                <th>进修专业1：<span class="red">*</span></th>
                <td  class="td_left">
                    <input name="spe1Name" class="validate[required] input" style="width: 185px;;text-align: center;"/>&nbsp;
                </td>
            </tr>
            <tr>
                <th>进修专业2：</th>
                <td  class="td_left">
                    <input name="spe2Name" class="input" style="width: 185px;;text-align: center;"/>
                </td>
                <th>进修单位：<span class="red">*</span></th>
                <td  class="td_left">
                    <input name="studyUnit" class="validate[required] input" style="width:185px;;text-align: center;"/>&nbsp;
                </td>
            </tr>
            <tr>
                <th>培训开始时间：<span class="red">*</span></th>
                <td  class="td_left">
                    <input id="stuStartTime" type="text" name="stuStartTime" class="validate[required] input" readonly="readonly" style="width: 185px;;text-align: center;"/>&nbsp;
                </td>
                <th>培训结束时间：<span class="red">*</span></th>
                <td  class="td_left">
                    <input id="stuEndTime" type="text" name="stuEndTime" class="validate[required] input" readonly="readonly" style="width: 185px;;text-align: center;"/>&nbsp;
                </td>
            </tr>
        </table>
    </form>
    <div style="text-align: center;padding-top: 20px;padding-bottom: 50px;">
        <input type="button" class="btn_green" onclick="subStudy();" value="保&#12288;存"/>
        &#12288;&#12288;
        <input type="button" class="btn_green" onclick="reSet();" value="重&#12288;置"/>
    </div>
</div>
