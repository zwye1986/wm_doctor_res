
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
<script type="text/javascript">
    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
        });

        <c:if test="${flag eq 'N'}">
        $("#Y").click();
        </c:if>
        <c:if test="${flag eq 'Y'}">
        $("#N").click();
        </c:if>
        <c:if test="${flag eq 'N2'}">
        $("#Y2").click();
        </c:if>
        <c:if test="${flag eq 'Y2'}">
        $("#N2").click();
        </c:if>
    });
    function selTag(tag,inx,data,flag){
 //       if(flag == 'Y'){
 //           var url = '<s:url value="/jsres/lecture/randomEvaList?lectureFlow="/>' + data;
 //           jboxLoad("content2", url, true);
 //       } else {
            var url = '<s:url value="/jsres/lecture/"/>' + inx + "?lectureFlow=" + data+"&flag="+flag;
            jboxLoad("content2", url, true);
        // }
    }
    function exportInfo(inx,flag){
        var url = '<s:url value="/jsres/lecture/exportInfo2"/>'+"?lectureFlow=${lectureFlow}&pageType="+inx+"&flag="+flag;
        jboxExp(null,url);
    }

    /*function exportInfo(inx){
        var url = '<s:url value="/jsres/lecture/exportInfo2"/>'+"?lectureFlow=${lectureFlow}&pageType="+inx;
        jboxExp(null,url);
    }*/
</script>
</head>
<body>
<div class="main_hd">
    <div class="title_tab" style="margin-top: 5px;">
            <ul>
                <li class="tab_select"  id="Y" onclick="selTag(this,'evaList','${lectureFlow}','${lectureInfo.randomSignIn}');" style="cursor: pointer;"><a id="evaList">已扫码学员（${fn:length(scans)+0}）</a></li>
                <li class="tab_select"   id="N" onclick="selTag(this,'noRegist','${lectureFlow}',null);"style="cursor: pointer;"><a id="noRegist">已报名学员（${fn:length(regists)+0}）</a></li>
                <li class="tab_select"  id="Y2" onclick="selTag(this,'evaList2','${lectureFlow}',null);" style="cursor: pointer;"><a id="evaList2">已扫码师资人员（${fn:length(scans2)+0}）</a></li>
                <li class="tab_select"   id="N2" onclick="selTag(this,'noRegist2','${lectureFlow}',null);"style="cursor: pointer;"><a id="noRegist2">已报名师资人员（${fn:length(regists2)+0}）</a></li>
            </ul>
    </div>
</div>
    <div id="content2" style="">
    </div>
</body>
</html>