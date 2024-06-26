<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="basic" value="true"/>
</jsp:include>
<script type="text/javascript">
    function searchTea(){
        if(!$("#orgFlow").val()){
            $("#orgSel").val("");
        }
        var orgName = $("#orgSel").val();
        if(!orgName){
            $("#orgFlow").val("");
        }
        jboxPostLoad("contentList" ,"<s:url value='/jsres/businessTwo/teachChangeList'/>" ,$("#searchForm").serialize() , true);
    }

    function clearOrgFlow(){
        $("#orgFlow").val("");
        var html = '<option value="">--请选择--</option>';
        $("#deptFlow").html(html);
    }

    function selectOrg(){
        var url='<s:url value="/jsres/teacherCfg/queryDeptListJson"/>?orgFlow='+$("#orgFlow").val();
        jboxPost(url,null,function(resp){
            if(resp){
                var html = '<option value="">--请选择--</option>';
                $.each(resp,function(index,obj){
                    html += '<option value="'+obj.deptFlow+'">'+obj.deptName+'</option>';
                });
                $("#deptFlow").html(html);
            }
        },null,false);
    }

    function toPage(page) {
        if(page){
            $("#currentPage").val(page);
        }
        searchTea();
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
    //======================================
    //页面加载完成时调用
    $(function(){
        $("#orgSel").likeSearchInit({
            clickActive:function(flow){
                $("#orgFlow").val(flow);
                selectOrg();
            }
        });
    });

    $(document).ready(function(){
        jboxStartLoading();
        searchTea();
    });
</script>

<div class="main_hd">
    <h2>带教权限配置</h2>
    <div class="main_bd" id="div_table_0" >
        <div class="div_search">
            <form id="sysCfgForm" >
                <input type="hidden" id="cfgCode" name="cfgCode"/>
                <input type="hidden" id="cfgValue" name="{0}"/>
                <input type="hidden" id="wsId" value="${GlobalConstant.RES_WS_ID }" name="{0}_ws_id"/>
                <input type="hidden" id="desc" name="{0}_desc">
            </form>
            <form id="searchForm" method="post">
                <input id="currentPage" type="hidden" name="currentPage"/>
                <table class="searchTable">
                    <tr>
                        <td>
                            培训基地：
                            <input id="orgSel" onchange="clearOrgFlow();" class="input"  type="text" name="orgName"
                                   value="${param.orgName}" autocomplete="off" style="width: 144px;"/>
                            <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top: 36px; left:74px;">
                                <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;
							  vertical-align:middle; background-color: white;min-width: 150px;border-top: none;position: relative;display: none;">
                                    <c:forEach items="${applicationScope.sysOrgList}" var="org">
                                        <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="line-height: 20px; padding:10px 0;cursor: default; text-align: left;">${org.orgName}</p>
                                    </c:forEach>
                                </div>
                            </div>
                            <input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
                        </td>
                        <td>
                            科&#12288;&#12288;室：
                            <select name="deptFlow"class="select" id="deptFlow" style="width: 150px;">
                                <option value="">--请选择--</option>
                            </select>
                        </td>
                        <td>
                            姓&#12288;&#12288;名：
                            <input type="text" name="userName" class="input"  style="width: 150px;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            用 户 名：
                            <input type="text" name="userCode" class="input" style="width:150px;">
                        </td>
                        <td>
                            <input type="button" value="查&#12288;询" class="btn_green" onclick="searchTea();"/>

                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>


<div id="contentList">

</div>
