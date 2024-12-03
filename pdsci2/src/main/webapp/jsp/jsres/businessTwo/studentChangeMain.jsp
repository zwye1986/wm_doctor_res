<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="false"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="false"/>
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
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">

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
    //======================================
    //页面加载完成时调用
    $(function(){
        $("#orgSel").likeSearchInit({
            clickActive:function(flow){
                $("#orgFlow").val(flow);
            }
        });
    });


    function search() {
        top.jboxStartLoading();
        if(!$("#orgFlow").val()){
            $("#orgSel").val("");
        }
        if($("#ifOpen").find("option:selected ").val()){
            if($("input[name='powerTypeId']:checked").size()<1){
                jboxTip("请选择权限类型！");
                return false;
            }
        }
        if($("input[name='powerTypeId']:checked").size()>=1){
            if(!$("#ifOpen").find("option:selected ").val()){
                jboxTip("请选择权限筛选！");
                return false;
            }
        }
        var orgName = $("#orgSel").val();
        if(!orgName){
            $("#orgFlow").val("");
        }
        var url = "<s:url value='/jsres/businessTwo/studentChangeList' />";
        jboxPostLoad("contentList", url, $("#searchForm").serialize(), true);
    }

    function toPage(page) {
        if (page) {
            $("#currentPage").val(page);
        }
        search();
    }


    $(document).ready(function(){
       search();
    });

</script>

<div class="main_hd">
    <h2>学员权限配置</h2>

    <div class="main_bd" id="div_table_0" >
        <div class="div_search">
            <form id="searchForm" method="post">
                <input id="currentPage" type="hidden" name="currentPage"/>
                <table class="searchTable">
                    <tr>
                        <td>
                            培训基地：
                            <input id="orgSel" onchange="clearOrgFlow();" class="input"  type="text" name="orgName"
                                   value="${param.orgName}" autocomplete="off" style="width: 120px;"/>
                            <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top: 36px; left:74px;">
                                <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;
							  vertical-align:middle; background-color: white;min-width: 126px;border-top: none;position: relative;display: none;">
                                    <c:forEach items="${applicationScope.sysOrgList}" var="org">
                                        <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="line-height: 20px; padding:10px 0;cursor: default; text-align: left;">${org.orgName}</p>
                                    </c:forEach>
                                </div>
                            </div>
                            <input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
                        </td>
                        <td>
                            姓&#12288;&#12288;名：
                            <input type="text" name="userName" value="${param.userName}" class="input"
                                   style="width: 120px;"/>
                        </td>
                        <td>
                            证件号码：
                            <input type="text" name="idNo" class="input"
                                   style="width: 120px;"/>
                        </td>
                        <td class="Graduate">
                            派送学校：
                            <select name="workOrgId" id="workOrgId"  class="select" style="width: 126px;">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
                                    <option value="${dict.dictId}" >${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            审核区间：
                            <input  id="startTime" name="startTime" class="input" type="text" value="${param.startTime}" readonly="readonly"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" style="width: 120px;"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input  id="endTime" name="endTime" class="input" type="text" value="${param.endTime}" readonly="readonly"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" style="width: 120px;"/>
                        </td>
                        <td colspan="2">
                            人员类型：
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}" onclick="showSendSchool(this);" class="docType" name="datas" />${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="1">
                            权限筛选：
                            <select name="ifOpen" id="ifOpen" class="select" style="width: 126px;margin-left: 5px">
                                <option value="">全部</option>
                                <option value="Y" ${param.ifOpen eq 'Y'?'selected':''}>已开通</option>
                                <option value="N" ${param.ifOpen eq 'N'?'selected':''}>未开通</option>
                            </select>
                        </td>
                        <td style="" id="powerTypes" colspan="2">
                            权限类型：
                            <input name="powerTypeId"  type="checkbox" value="APP" ${APP eq 'Y'?'checked':''}/>APP&nbsp;
                            <input name="powerTypeId"  type="checkbox" value="menu" ${menu eq 'Y'?'checked':''}/>付费功能 &nbsp;
                            <input name="powerTypeId"  type="checkbox" value="ckks" ${ckks eq 'Y'?'checked':''}/>出科考试&nbsp;
                            <input name="powerTypeId"  type="checkbox" value="pxsc" ${pxsc eq 'Y'?'checked':''}/>培训手册&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" class="btn_green" value="查&#12288;询" id="searchDoctor" onclick="search()">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    <div id="contentList">

    </div>
    </div>
</div>



