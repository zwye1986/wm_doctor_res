<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<%--<style type="text/css">--%>
<%--    .boxHome .item:HOVER {--%>
<%--        background-color: #eee;--%>
<%--    }--%>

<%--    .cur {--%>
<%--        color: red--%>
<%--    }--%>
<%--</style>--%>
<script type="text/javascript">
    $(document).ready(function () {
        $.checkYear("sessionNumber","",null);
        <c:forEach items="${resDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if("${data}"=="${type.id}"){
            $("#"+"${data}").attr("checked","checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#"+"${type.id}").attr("checked","checked");
        </c:if>
        </c:forEach>

        getCityArea();
        initOrg();
        toPage(1);
    });

    function getCityArea(){
        var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
        var provIds = "320000";
        jboxGet(url,null, function(json) {
            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
            var newJsonData=new Array();
            var j=0;
            var html ="<option value=''></option>";
            for(var i=0;i<json.length;i++){
                if(provIds==json[i].v){
                    var citys=json[i].s;
                    for(var k=0;k<citys.length;k++){
                        var city=citys[k];
                        html+="<option value='"+city.v+"'>"+city.n+"</option>";
                    }
                }
            }
            $("#cityId2").html(html);
        },null,false);
    }

    var allOrgs=[];
    function initOrg() {
        var datas=[];
        <c:forEach items="${orgs}" var="org">
        <c:if test="${sessionScope.currUser.orgFlow!=org.orgFlow }">
        var d={};
        d.id="${org.orgFlow}";
        d.text="${org.orgName}";
        d.cityId="${org.orgCityId}";
        datas.push(d);
        allOrgs.push(d);
        </c:if>
        </c:forEach>
        var itemSelectFuntion = function(){
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);

    }

    function changeOrg(obj) {
        var cityId=$(obj).val();
        var datas=[];
        for(var i=0;i<allOrgs.length;i++)
        {
            var org=allOrgs[i];
            if(org.cityId==cityId||cityId=="")
            {
                datas.push(org);
            }
        }
        $("#orgFlow").val("");
        var itemSelectFuntion = function(){
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
    }

    function checkAll(obj){
        var f=false;
        if($(obj).attr("checked")=="checked")
        {
            f=true;
        }
        $(".docType").each(function(){
            if(f)
            {
                $(this).attr("checked","checked");
            }else {
                $(this).removeAttr("checked");
            }

        });
    }
    function changeAllBox(){
        if($(".docType:checked").length==$(".docType").length)
        {
            $("#all").attr("checked","checked");
        }else{
            $("#all").removeAttr("checked");
        }
    }

    function toPage(page) {
        var currentPage = "";
        if (!page || page != undefined) {
            currentPage = page;
        }
        if (page == undefined || page == "") {
            currentPage = 1;
        }
        $("#currentPage").val(currentPage);
        searchRecInfo();
    }
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }
    function searchRecInfo() {
        if ($("#orgFlow").val() == "") {
            $("#trainOrg").val("");
        }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/jsres/doctor/backTrainInfoList'/>";
        jboxPostLoad("div_table2", url, $("#searchForm").serialize(), true);
    }
    function exportExcel() {
        <%--var url = "<s:url value='/jsres/doctor/exportForBack'/>";--%>
        var url = "<s:url value='/jsres/doctor/exportForBackNewAcc'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
    function change() {
        $("#trainOrg").val("");
    }
    //上传退培附件
    function uploadFile(recordFlow) {
        if (${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}) {
            jboxTip("退培附件由基地上传！");
            return;
        }
        var url = "<s:url value='/jsres/doctor/showUpload?recordFlow='/>" + recordFlow;
        typeName = "上传退培附件";
        jboxOpen(url, typeName, 500, 260);
    }
    function doctorPassedList(doctorFlow,recruitFlow){
        var hideApprove="hideApprove";
        var url = "<s:url value='/jsres/manage/province/doctor/doctorPassedList'/>?isRetrunShow=Y&info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
        jboxOpen(url,"学员信息",1050,600);
    }
</script>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage" value=""/>
            <input type="hidden" name="roleFlag" id="roleFlag" value="charge"/>
            <c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y }">
                <div>
                    <table>
                        <tr>
<%--                            <td class="td_left">地&#12288;&#12288;市：</td>--%>
<%--                            <td>--%>
<%--                                <select id="cityId2" name="cityId" class="select" onchange="changeOrg(this)" style="width: 130px"></select>--%>
<%--                            </td>--%>
                            <td class="td_left">&#12288;培训基地：</td>
                            <td>
                                <input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off" style="width: 124px;margin: 0px 0px;"  />
                                <input type="hidden" name="orgFlow" id="orgFlow" value="${param.orgFlow}">
                            </td>
                            <td>&#12288;学&nbsp;&nbsp;员&nbsp;&nbsp;姓&nbsp;&nbsp;名：</td>
                            <td>
                                <input type="text" value="${param.doctorName}" class="input" name="doctorName"
                                       style="width: 124px;margin: 0 0px;"/>
                            </td>
                            <td>&#12288;培训届别：</td>
                            <td>
                                <input type="text" id="sessionNumber" name="sessionNumber"
                                       value="${param.sessionNumber}" class="input" readonly="readonly"
                                       style="width: 124px;margin-left: 0px"/>
                            </td>
                            <td>培训专业：</td>
                            <td>
                                <input type="text" value="${param.trainingSpeName}" class="input" name="trainingSpeName"
                                       style="width: 124px;margin: 0 0px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td>&#12288;退培类型：</td>
                            <td>
                                <select class="select" id="policyId" name="policyId" style="width: 130px;">
                                    <option value="">请选择</option>
                                    <option value="1" <c:if test="${param.policyId eq 1}">selected="selected"</c:if>>
                                        协议退培
                                    </option>
                                    <option value="2" <c:if test="${param.policyId eq 2}">selected="selected"</c:if>>
                                        违约退培
                                    </option>
                                </select>
                            </td>
                            <td>&#12288;退培主要原因：</td>
                            <td>
                                <select class="select" id="reasonId" name="reasonId" style="width: 130px;">
                                    <option value="">请选择</option>
                                    <option value="1" <c:if test="${param.reasonId eq 1}">selected="selected"</c:if>>
                                        辞职
                                    </option>
                                    <option value="2" <c:if test="${param.reasonId eq 2}">selected="selected"</c:if>>
                                        考研
                                    </option>
                                    <option value="3" <c:if test="${param.reasonId eq 3}">selected="selected"</c:if>>
                                        其他
                                    </option>
                                </select>
                            </td>
                            <td>&#12288;审核结果：</td>
                            <td>
                                <select class="select" id="auditStatusId" name="auditStatusId" style="width: 130px;">
                                    <option value="">请选择</option>
                                    <option value="0"
                                            <c:if test="${param.auditStatusId eq '0'}">selected="selected"</c:if>>不通过
                                    </option>
                                    <option value="1"
                                            <c:if test="${param.auditStatusId eq '1'}">selected="selected"</c:if>>通过
                                    </option>
                                </select>
                            </td>
                            <td id='jointOrg' colspan="2">
                                &nbsp;<label style="cursor: pointer;"><input type="checkbox" id="jointOrgFlag"
                                                                             <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                                                             name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
                            </td>
                        </tr>
                        <tr>
                            <td >&#12288;人员类型：</td>
                            <td colspan="3">
                                <c:forEach items="${resDocTypeEnumList}" var="type">
                                    <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas"  />${type.name}&nbsp;</label>
                                </c:forEach>
                            </td>
                            <td colspan="2">&#12288;
                                <input class="btn_green" type="button" onclick="searchRecInfo()" value="查&#12288;询"/>
                                &#12288;
                                <input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>
        </form>
    </div>
    <div class="search_table" id="div_table2"></div>
</div>
