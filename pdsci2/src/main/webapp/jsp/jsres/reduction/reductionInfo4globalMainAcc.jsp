<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
</style>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $.checkYear("sessionNumber","",null);
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
    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchIn();
    }
    function searchIn() {
        var url = "<s:url value='/jsres/reduction/reductionManageListAcc'/>";
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        jboxPostLoad("div_table2", url, $("#inForm").serialize(), true);
    }

    function auditDoctorList() {
        var ids = $('input:checkbox[name="reductionCheck"]:checked');
        if (ids == null || ids.length <= 0) {
            jboxTip("请勾选至少一条记录！");
            return;
        }
        var recordFlowList = [];
        for (var i = 0; i < ids.length; i++) {
            recordFlowList.push(ids[i].value)
        }
        var url = "<s:url value='/jsres/reduction/showCheckReductionInfoList'/>?roleId=${roleId}&recordFlowList=" + recordFlowList;
        jboxOpen(url, "批量审核", 500, 300);
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
        $("#trainOrg").val("");
        var itemSelectFuntion = function(){
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
    }

    //导出
    function exportList(){
        var url="<s:url value='/jsres/reduction/exportListAcc'/>";
        jboxTip("导出中…………");
        jboxExp($("#inForm"), url);
        jboxEndLoading();
    }

</script>

<div class="main_bd">
    <div class="div_search">
        <form id="inForm">
            <input type="hidden" name="currentPage" id="currentPage"/>
            <input type="hidden" name="operType" id="operType" value="${operType}"/>
            <input type="hidden" name="roleId" id="roleId" value="${roleId}"/>
            <c:if test="${operType eq 'isCheck'}">
                <table class="searchTable">
                    <tr>
                        <td class="td_left">地&#12288;&#12288;市：</td>
                        <td>
                            <select id="cityId2" name="cityId" class="select" onchange="changeOrg(this)" style="width: 134px"></select>
                        </td>
                        <td class="td_left">培训基地：</td>
                        <td>
                            <input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 128px"  />
                            <input type="hidden" name="orgFlow" id="orgFlow">
                        </td>
                        <td class="td_left">培训专业：</td>
                        <td>
                            <select name="trainingSpeId"  id="trainingSpeId" class="select" style="width: 134px">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="td_left">姓&#12288;&#12288;名：</td>
                        <td>
                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>&#12288;
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">证件号码：</td>
                        <td>
                            <input type="text" id="idNo" name="idNo" value="${param.idNo}" class="input" />
                        </td>
                        <td class="td_left">届&#12288;&#12288;别：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                        </td>
                        <td id='jointOrg' colspan="2">
                            <label style="cursor: pointer;"><input type="checkbox" id="jointOrgFlag"
                                                                   <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                                                   name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
                        </td>
                        <td colspan="4">
                            <input class="btn_green" type="button" onclick="searchIn()" value="查&#12288;询"/>
                            <c:if test="${maintenance ne 'Y'}"> <%--客服（运维角色）只能查看——--%>
                            <input class="btn_green" type="button" value="批量审核" onclick="auditDoctorList();"/>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${operType eq 'isQuery'}">
                <table class="searchTable">
                    <tr>
                        <td class="td_left">地&#12288;&#12288;市：</td>
                        <td>
                            <select id="cityId2" name="cityId" class="select" onchange="changeOrg(this)" style="width: 134px"></select>
                        </td>
                        <td class="td_left">培训基地：</td>
                        <td>
                            <input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 134px"  />
                            <input type="hidden" name="orgFlow" id="orgFlow">
                        </td>
                        <td class="td_left">培训专业：</td>
                        <td>
                            <select name="trainingSpeId"  id="trainingSpeId" class="select" style="width:134px;">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="td_left">姓&#12288;&#12288;名：</td>
                        <td>
                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>&#12288;
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">证件号码：</td>
                        <td>
                            <input type="text" id="idNo" name="idNo" value="${param.idNo}" class="input" />
                        </td>
                        <td class="td_left">届&#12288;&#12288;别：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" style="width: 134px"  readonly="readonly" />
                        </td>
                        <td class="td_left">审核状态：</td>
                        <td>
                            <select name="statusFlag" id="statusFlag" class="select" style="width: 134px;">
                                <option value="all">全部</option>
                                <option value="pass"
                                        <c:if test="${param.statusFlag=='pass'}">selected="selected"</c:if>>省厅审核通过
                                </option>
                                <option value="notPass"
                                        <c:if test="${param.statusFlag=='notPass'}">selected="selected"</c:if>>省厅审核不通过
                                </option>
                            </select>
                        </td>
                        <td id='jointOrg' colspan="2">
                            <label style="cursor: pointer;"><input type="checkbox" id="jointOrgFlag"
                                                                   <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                                                   name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="8">
                            <input class="btn_green" type="button" onclick="searchIn()" value="查&#12288;询"/>
                            <input class="btn_green" type="button" onclick="exportList()" value="导&#12288;出"/>
                        </td>
                    </tr>
                </table>
            </c:if>
        </form>
    </div>
    <div id="div_table2"></div>

</div>
