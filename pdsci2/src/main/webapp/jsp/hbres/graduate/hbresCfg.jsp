<script type="text/javascript">
    //异步
    function jboxPost(posturl,postdata,funcOk,funcErr,showResp){
        $.ajax({
            type : "post",
            url : posturl,
            data : postdata,
            cache : false,
            beforeSend : function(){
                jboxStartLoading();
            },
            success : function(resp) {
                jboxEndLoading();
                if(showResp==false){

                }else{
                    jboxTip(resp);
                }
                if(funcOk!=null){
                    funcOk(resp);
                }
            },
            error : function() {
                jboxEndLoading();
                jboxTip("操作失败,请刷新页面后重试");
                if(funcErr!=null){
                    funcErr();
                }
            },
            complete : function(){
                jboxEndLoading();
            }
        });
    }
    $(document).ready(function () {
        <c:if test="${'graduationScoreCfg'==param.tagId }">
            $('#cfgYear').datepicker({
                startView: 2,
                maxViewMode: 2,
                minViewMode:2,
                format:'yyyy'
            });
            toPage();
        </c:if>
    });

    function toPage(page){
        if(page != undefined){
            $("#currentPage").val(page);
        }
        searchCfgInfo();
    }

    function searchCfgInfo(){
        var url =" <s:url value='/hbres/singup/loadScoreConf'/>";
        jboxPostLoad("contentDiv", url, $("#searchForm").serialize(), true);
    }
    var width  = 400;
    var height =200;
    //添加
    function setScoreConf(){
        var url="<s:url value='/hbres/singup/setScoreConf'/>";
        jboxOpen(url,"添加配置信息",width,height);
    }
    //编辑
    function editCfgInfo(cfgYear){
        var url="<s:url value='/hbres/singup/setScoreConf?cfgYear='/>"+cfgYear;
        jboxOpen(url,"编辑配置信息",width,height);
    }
    //删除
    function delCfgInfo(cfgYear){
        jboxConfirm("确认删除此条记录?", function(){
            var url="<s:url value='/hbres/singup/delScoreConf?cfgYear='/>"+cfgYear;
            jboxPost(url, null, function(resp){
                if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                    searchCfgInfo();
                }
            }, null, false);
        });
    }
</script>
<body>

<div class="main_bd">
    <c:if test="${'graduationScoreCfg'==param.tagId }">
        <script type="text/javascript">
        </script>
        <div class="div_search">
            <form id="searchForm">
                <input type="hidden" name="currentPage" id="currentPage"/>
                年份：<input type="text" class="input" id="cfgYear" name="cfgYear" value="${param.cfgYear}" style="width:100px;"/>&#12288;
                <input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/>&#12288;
                <input class="btn_green" type="button" value="添&#12288;加" onclick="setScoreConf()"/>
            </form>
        </div>

        <div class="search_table" id="contentDiv">

        </div>
    </c:if>

</div>
</body>