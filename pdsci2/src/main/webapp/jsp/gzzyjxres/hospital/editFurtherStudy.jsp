<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/gzzyjxres/htmlhead-gzzyjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style>
        select {
            margin: 0 5px;
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
    <script type="text/javascript">
        $(document).ready(function(){
            $('#admissionTime').datepicker();
            $('#stuStartTime').datepicker();
            $('#stuEndTime').datepicker();
        });
        function saveStaff(){
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
            jboxConfirm("确认修改?" ,  function(){
                jboxPost("<s:url value='/gzzyjxres/hospital/editStudyRegist'/>",$("#editForm").serialize(),function(resp){
                    if(resp == '${GlobalConstant.SAVE_SUCCESSED}'){
                        window.parent.jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
                        window.parent.furtherStudyQuery();
                        jboxClose();
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
                    <%--var selectUser = $("[name='userFlow']").html(html);--%>
                <%--}--%>
                <%--if(list!=null & list.length < 1){--%>
                    <%--jboxTip("该科室下无可选人员！");--%>
                <%--}--%>
            <%--},null,false);--%>
        <%--}--%>
        function searchSuggest(resp){
            var url = "<s:url value='/gzzyjxres/hospital/searchSuggest'/>";
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
                            html+="<p class='selectP' style='width: 100%;' deptId='"+list[i].dictId+"'>"+list[i].dictName+"</p>";
                        }
                        if(resp == "userName"){
                            html+="<p class='selectP' style='width: 100%;' userFlow='"+list[i].userFlow+"'>"+list[i].userName+"</p>";
                        }
                    }
                    suggestDiv.html(html);
                    suggestDiv.css({"position":"absolute", "top":xParent+hParent+2, "left":yParent});
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
</head>
<body>
    <div class="div_table">
        <form id="editForm">
            <input type="hidden" id="recordFlow" name="recordFlow" value="${studyRegist.recordFlow}" />
            <input type="hidden" id="userFlow" userName="${studyRegist.userName}" name="userFlow" value="${studyRegist.userFlow}" />
            <input type="hidden" id="deptId" deptName="${studyRegist.deptName}" name="deptId" value="${studyRegist.deptId}" />
            <input type="hidden" id="titleName" name="titleName" value="" />
            <input type="hidden" id="stationPropertyName" name="stationPropertyName" value="" />
            <table class="grid">
                <tr>
                    <th style="text-align: right;">所在科室（部门）：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <input id="deptName" name="deptName" class="validate[required] input" value="${studyRegist.deptName}" style="text-align: left"
                               onclick="stopPropagation(event) " onkeyup="searchSuggest('deptName');" onfocus="searchSuggest('deptName');"
                               autocomplete="off" style="width: 160px;text-align: center;"/>
                        <div class="suggest"  style="width:160px;overflow: auto;max-height: 150px;"></div>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">姓名：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <input id="userName" name="userName" class="validate[required] input" value="${studyRegist.userName}" style="text-align: left"
                               onclick="stopPropagation(event) " onkeyup="searchSuggest('userName');" onfocus="searchSuggest('userName');"
                               autocomplete="off" style="width: 160px;text-align: center;"/>
                        <div class="suggest"  style="width:160px;overflow: auto;max-height: 150px;"></div>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">年龄：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <input type="text" style="width:160px;" class="validate[required] validate[custom[integer],min[0],max[150]] input" name="userAge" value="${studyRegist.userAge}" />
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">从事专业：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <input type="text" style="width:160px;" class="input validate[required]"  name="engagedProfession" value="${studyRegist.engagedProfession}"/>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">入院时间：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <input id="admissionTime" type="text" style="width:160px;" class="input validate[required]" name="admissionTime" readonly="readonly" value="${studyRegist.admissionTime}"/>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">职称：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <select id="titleId" class="validate[required] select" name="titleId" style="width: 166px;">
                            <option value="">------请选择------</option>
                            <c:forEach items="${dictTypeEnumTitleGenreList}" var="title">
                                <option <c:if test="${title.dictId eq studyRegist.titleId}">selected="selected"</c:if> value="${title.dictId}">${title.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">岗位性质：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <c:forEach items="${stationPropertyEnumList}" var="property">
                            &nbsp;<input id="${property.id}" type="radio" <c:if test="${property.id eq studyRegist.stationPropertyId}">checked="checked"</c:if> name="stationPropertyId" value="${property.id}">&nbsp;
                            <label for="${property.id}">${property.name}</label>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">进修专业1：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <input type="text" style="width:160px;" class="input validate[required]" name="spe1Name" value="${studyRegist.spe1Name}"/>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">进修专业2：</th>
                    <td style="text-align: left;">
                        <input type="text" style="width:160px;" class="input" name="spe2Name" value="${studyRegist.spe2Name}"/>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">进修单位：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <input type="text" style="width:160px;" class="input" name="studyUnit" value="${studyRegist.studyUnit}"/>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">培训开始时间：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <input id="stuStartTime" type="text" style="width:160px;" class="input validate[required]" name="stuStartTime" readonly="readonly" value="${studyRegist.stuStartTime}"/>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">培训结束时间：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <input id="stuEndTime" type="text" style="width:160px;" class="input validate[required]" name="stuEndTime" readonly="readonly" value="${studyRegist.stuEndTime}"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input class="btn_green" style="width:60px;" onclick="saveStaff();" value="保&#12288;存" readonly="readonly" />&#12288;
        <input class="btn_green" style="width:60px;" onclick="jboxClose();" value="关&#12288;闭" readonly="readonly" />
    </div>
</body>
</html>