<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="basic" value="true"/>
</jsp:include>
<script type="text/javascript">
    function searchSchool(){
        if(!$("#orgFlow").val()){
            $("#orgSel").val("");
        }
        var orgName = $("#orgSel").val();
        if(!orgName){
            $("#orgFlow").val("");
        }
        jboxPostLoad("contentInfo" ,"<s:url value='/jsres/businessTwo/schoolChangeList'/>" ,$("#searchForm").serialize() , true);
    }

    function toPage(page) {
        if(page){
            $("#currentPage").val(page);
        }
        searchSchool();
    }

    $(document).ready(function(){
        jboxStartLoading();
        searchSchool();
    });

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

</script>

<div class="main_hd">
    <h2>高校权限配置</h2>
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
                            派送学校名称：
                            <input type="text" name="dictName" class="input" style="width:150px;">
                        </td>
                        <td>
                            <input type="button" value="查&#12288;询" class="btn_green" onclick="searchSchool();"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="contentInfo">

        </div>
    </div>
</div>

<div id="contentList">

</div>

