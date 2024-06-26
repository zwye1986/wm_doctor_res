<style>
    th{
        text-align: center;
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
<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $('#currentPage').val(page);
        }
        jboxPostLoad("content","<s:url value='/gzzyjxres/hospital/furtherStudyQuery'/>",$("#searchForm").serialize(),true);
    }
    //编辑
    function edit(flag,recordFlow){
        jboxOpen("<s:url value='/gzzyjxres/hospital/showEdit'/>?recordFlow="+recordFlow, flag,800,600,true);
    }
    //删除
    function del(recordFlow){
        jboxConfirm("确定刪除吗？",function(){
            jboxPost("<s:url value='/gzzyjxres/hospital/delStudyRegist'/>",{"recordFlow":recordFlow},function(resp){
                furtherStudyQuery();
            },null,true);
        },null);
    }
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
            var xParent = $("#"+resp).position().top;
            var hParent = $("#"+resp).position().height;
            var yParent = $("#"+resp).position().left;
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
                suggestDiv.css({"position":"absolute", "top":xParent+hParent+2, "left":yParent+5});
                $(".selectP").bind("mouseover",function(){
                    $(this).css("background-color","#f1eae2");
                });
                $(".selectP").bind("mouseout",function(){
                    $(this).css("background-color","#fff");
                });
                $(".selectP").bind("click",function(e){
                    $("#"+resp).val($(this).text());
                    if (e.stopPropagation)
                        e.stopPropagation();
                    else
                        e.cancelBubble = true;
                    suggestDiv.hide();
                });
                $(document).bind('click',function(){
                    suggestDiv.hide();
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
    <h2 class="underline">外出进修查询
        <div class="fr"></div>
    </h2>
</div>
<div class="div_search" style="float:left;margin-left:40px;">
    <form id="searchForm" style="position: relative;">
        <input type="hidden" name="currentPage" id="currentPage" value="1" />
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td class="td_left">科室：</td>
                <td>
                    <input id="deptName" name="deptName" class="validate[required] input" value="${param.deptName}"
                           onclick="stopPropagation(event) " onkeyup="searchSuggest('deptName');" onfocus="searchSuggest('deptName');"
                           autocomplete="off" style="width: 185px;text-align: center;"/>
                    <div class="suggest"  style="width:185px;overflow: auto;max-height: 150px;"></div>
                </td>
                <td class="td_left">&#12288;姓名：</td>
                <td>
                    <input id="userName" name="userName" class="validate[required] input" value="${param.userName}"
                           onclick="stopPropagation(event) " onkeyup="searchSuggest('userName');" onfocus="searchSuggest('userName');"
                           autocomplete="off" style="width: 185px;text-align: center;"/>
                    <div class="suggest"  style="width:185px;overflow: auto;max-height: 150px;"></div>
                </td>
                <td>&#12288;
                    <input type="button" class="btn_green" onclick="toPage(1);" value="查&#12288;询" />
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="search_table">
        <table class="grid" width="100%;">
            <tr>
                <th>科室</th>
                <th>姓名</th>
                <th>进修单位</th>
                <th>进修专业1</th>
                <th>进修专业2</th>
                <th>进修时间</th>
                <th>进修时长</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${registList}" var="regist">
                <tr>
                    <td>${regist.deptName}</td>
                    <td>${regist.userName}</td>
                    <td>${regist.studyUnit}</td>
                    <td>${regist.spe1Name}</td>
                    <td>${regist.spe2Name}</td>
                    <td>${regist.stuStartTime} ~ ${regist.stuEndTime}</td>
                    <td>${pdfn:signDaysBetweenTowDate(regist.stuEndTime,regist.stuStartTime)+1}天</td>
                    <td style="text-align: center;">
                        <a onclick="edit('编辑','${regist.recordFlow}')">[编辑]</a>
                        <a onclick="del('${regist.recordFlow}')">[删除]</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty registList}">
                <tr>
                    <td colspan="8">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(registList)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
