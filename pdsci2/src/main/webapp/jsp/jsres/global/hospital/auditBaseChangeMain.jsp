<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style type="text/css">
    .itemDiv {
        line-height: 20px;
    }
</style>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $.checkYear("sessionNumber","",null);
        if ("${baseFlag}" == "0") {
            $("#baseFlag").hide();
        }
        if("${baseFlag}"!="0"){
            $("#baseFlag").show();
        }
        <c:forEach items="${jsResDocTypeEnumList}" var="type">
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
    function getChangeOrgDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }

    function auditOrgChange(recordFlow, auditFlag, doctorFlow) {
        var title = "不通过";
        var changeStatusId = "${jsResChangeApplyStatusEnumGlobalApplyUnPass.id}";//省厅审核不通过
        if ("${GlobalConstant.FLAG_Y}" == auditFlag) {
            title = "通过";
            changeStatusId = "${jsResChangeApplyStatusEnumGlobalApplyPass.id}";
        }
        if (title == "不通过") {
            var url = "<s:url value='/jsres/manage/auditInfoGlobal'/>?recordFlow=" + recordFlow + "&changeStatusId=" + changeStatusId + "&doctorFlow=" + doctorFlow;
            jboxOpen(url, "审核意见", 680, 400);
        } else {
            <%--var url = "<s:url value='/jsres/manage/info'/>?recordFlow=" + recordFlow + "&changeStatusId=" + changeStatusId + "&doctorFlow=" + doctorFlow;--%>
            <%--jboxOpen(url, "相关信息", 600, 280);--%>
            var url = "<s:url value='/jsres/manage/turnOutOrgGlobal'/>?recordFlow="+recordFlow +"&changeStatusId="+changeStatusId+"&doctorFlow="+doctorFlow;
            jboxConfirm("确认通过该条记录?",  function(){
                jboxPost(url, null, function(resp){
                    if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
                        window.parent.search();
                        top.jboxTip("操作成功！");
                        jboxClose();
                    }else{
                        top.jboxTip("操作失败！");
                        jboxClose();
                    }
                }, null, false);
            });
        }
    }

    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        search();
    }
    function search() {
        <%--var url = "<s:url value='/jsres/manage/auditBaseChange'/>";--%>
        var url = "<s:url value='/jsres/manage/auditBaseChangeList'/>";
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        jboxPostLoad("div_table2", url, $("#inForm").serialize(), true);
    }
</script>
<div class="main_bd">
    <div class="div_search">
        <form id="inForm">
            <input type="hidden" name="currentPage" id="currentPage"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">地&#12288;&#12288;市：</td>
                    <td>
                        <select id="cityId2" name="cityId" class="select" onchange="changeOrg(this)" ></select>
                    </td>
                    <td class="td_left">培训基地：</td>
                    <td>
                        <input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 134px"  />
                        <input type="hidden" name="orgFlow" id="orgFlow" value="${param.orgFlow}">
                    </td>
                    <td class="td_left">姓&#12288;&#12288;名：</td>
                    <td>
                        <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                    </td>
                    <td class="td_left">届&#12288;&#12288;别：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                    </td>
                </tr>
                <tr>
                    <td class="td_left">学员状态：</td>
                    <td>
                        <select name="doctorStatusId" class="select" style="width: 130px">
                            <option value="">全部</option>
                            <option value="20" ${param.doctorStatusId eq '20'?'selected':''}>在培</option>
                            <option value="22" ${param.doctorStatusId eq '22'?'selected':''}>已考核待结业</option>
                            <option value="21" ${param.doctorStatusId eq '21'?'selected':''}>结业</option>
                        </select>
                    </td>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td id='jointOrg' colspan="2">
                        <label style="cursor: pointer;"><input type="checkbox" id="jointOrgFlag"
                                                               <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                                               name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
                    </td>
                </tr>
                <tr>
                    <td colspan="8">
                        <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table" id="div_table2">
    </div>
</div>
