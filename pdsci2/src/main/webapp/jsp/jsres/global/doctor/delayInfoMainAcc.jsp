<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<script type="text/javascript">

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
    $(document).ready(function () {
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

    function getChangeOrgDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }
    function search() {
        // if ($("#orgFlow").val() == "") {
        //     $("#trainOrg").val("");
        // }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/jsres/doctor/delayListAcc'/>";
        jboxPostLoad("div_table2", url, $("#submitForm").serialize(), true);
    }
    function toPage(page) {
        var currentPage;
        if (page != undefined) {
            currentPage = page;
        }
        $("#currentPage").val(currentPage);
        search();
    }

    function exportDelayList() {
        var url = "<s:url value='/jsres/doctor/exportDelayListAcc'/>";
        jboxTip("导出中…………");
        // jboxSubmit($("#submitForm"), url, null, null, false);
        jboxExp($("#submitForm"),url);
        // jboxEndLoading();
    }
</script>
<c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
    <div class="main_hd">
        <h2 class="underline">延期学员查询</h2>
    </div>
</c:if>
<div>

    <div class="div_search">
        <form id="submitForm">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">

                <table class="searchTable">
                    <tr>
                        <td class="td_left">地&#12288;&#12288;市：</td>
                        <td>
                            <select id="cityId2" name="cityId" class="select" onchange="changeOrg(this)" style="width: 130px"></select>
                        </td>
                        <td class="td_left">&#12288;培训基地：</td>
                        <td>
                            <input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off" style="width: 124px;margin: 0px 0px;"  />
                            <input type="hidden" name="orgFlow" id="orgFlow" value="${param.orgFlow}">
                        </td>
                        <td class="td_left">&#12288;学员姓名：</td>
                        <td>
                            <input type="text"  name="doctorName" value="${param.doctorName}" class="input"   />&#12288;
                        </td>
                        <td id='jointOrg' colspan="2">
                            &nbsp;<label style="cursor: pointer;"><input type="checkbox" id="jointOrgFlag"
                                                                         <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                                                         name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
                        </td>

                    </tr>
                    <tr>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas"  />${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                        <td colspan="4">
                            <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                            &#12288;<input type="button" class="btn_green" onclick="exportDelayList()" value="导&#12288;出">
                        </td>
                    </tr>

                </table>
            </c:if>
        </form>
    </div>
    <div class="search_table" id="div_table2">

    </div>
</div>
