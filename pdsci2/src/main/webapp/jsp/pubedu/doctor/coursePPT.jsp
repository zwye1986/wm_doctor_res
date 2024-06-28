<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/pubedu/htmlhead-pubedu.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function doPage() {
            var index = $("#pageId").val();
            if (isNaN(index + 0)) {
                index = 1;
            }
            if (index < 1) {
                index = 1;
            }
            if (index > parseInt("${courseDetail.pagerNumber}")) {//此处数字为最大图片数
                index = "${courseDetail.pagerNumber}";
            }
            $("#pageId").val(index);
          <%--$("#images").prop("src", "${sysCfgMap["upload_base_url"]}/${courseDetail.detailUrl}/幻灯片" + index + ".jpg");--%>
            $("#images").prop("src", "${sysCfgMap["upload_base_url"]}/${pdfn:encodeString2(courseDetail.detailUrl)}/幻灯片" + index + ".jpg");
            $("#bigImages").prop("src", "${sysCfgMap["upload_base_url"]}/${pdfn:encodeString2(courseDetail.detailUrl)}/幻灯片" + index + ".jpg");
        }

        function nextPage(obj) {
            var index = $("#pageId").val();
            index++;
            if (isNaN(index)) {
                index = 1;
            }
            if (index > parseInt("${courseDetail.pagerNumber}")) {//此处数字为最大图片数
                index = "${courseDetail.pagerNumber}";
                jboxTip("已经是最后一页了！");
            }
            $("#pageId").val(index);
            doPage();
        }
        function upPage(obj) {
            var index = $("#pageId").val();
            index--;
            if (isNaN(index)) {
                index = 1;
            }
            if (index < 1) {
                index = 1;
                jboxTip("当前为第一页！");
            }
            $("#pageId").val(index);
            doPage();
        }
        $(document).ready(function () {
            $("#bigImages,.bg").bind("click", function () {
                $("#popup").hide();
            });
            $("#images").bind("click", function () {
                $("#bigImages").attr("src", $("#images")[0].src);
                $("#popup").show();
            });
        });

    </script>

</head>
<style>
    .mainDiv {
        width: 700px;
        margin: 0 auto;
    }

    .mainDiv img {

        width: 100%;
        margin-right: 5%;
        margin-top: 100px;
    }

    #popup {
        position: fixed;
        left: 0px;
        top: 0px;
        width: 100%;
        height: 100%;
        text-align: center;
        display: none;
    }

    #popup .bg {
        background-color: rgba(0, 0, 0, 0.5);
        width: 100%;
        height: 100%;
    }

    @media \0screen\,screen\9 {
        #popup .bg {
            background-color: #000000;
            filter: Alpha(opacity=50);
            position: static;
        }

        #popup .bg img {
            position: relative;
        }
    }

    #popup img {
        max-width: 100%;
        max-height: 100%;
    }

    #popup .previous,
    #popup .next {
        z-index: 99992;
        position: absolute;
        top: 50%;
        overflow: hidden;
        display: block;
        width: 49px;
        height: 49px;
        margin-top: -25px;
    }

    #popup .previous {
        left: 0;
        background: url('<s:url value='/jsp/pubedu/images/arrows.png'/>') no-repeat 0 0;
    }

    #popup .next {
        right: 0;
        background: url('<s:url value='/jsp/pubedu/images/arrows.png'/>') no-repeat 100% 0;
    }

    #popup .previous:hover,
    #popup .next:hover {
        background-color: #0088cc;
    }

</style>
<body>
<jsp:include page="doctorHead.jsp" flush="true"/>
<div class="body">
    <div class="container">
        <div id="mainDiv" class="mainDiv">
            <img style="cursor: zoom-in" id="images"
                 src="${sysCfgMap["upload_base_url"]}/${pdfn:encodeString2(courseDetail.detailUrl)}/幻灯片1.jpg" alt="图片未找到"/>
            <div style="margin-top:25px;width: 100%;text-align: center">
                <a href="javascript:void(0)" onclick="upPage(this)">◀◁</a>&#12288;
                <input value="1" type="text" id="pageId" onblur="doPage()"
                       style="width:28px; padding:2px;text-align: center"/>&nbsp;/${courseDetail.pagerNumber}&#12288;
                <a href="javascript:void(0)" onclick="nextPage(this)">▷▶</a>
            </div>
        </div>
        <div id="popup">
            <a class="previous" onclick="upPage(this)"></a>
            <div class="bg" >
                <img style="min-width: 50px;cursor: zoom-out;" id="bigImages" src="" alt="图片未找到"/>
            </div>
            <a class="next" onclick="nextPage(this)"></a>
        </div>
    </div>
</div>
<div class="foot">
    <div class="foot_inner">
        <span>技术支持：南京品德网络信息技术有限公司&nbsp;&nbsp;服务电话：025-69815356&nbsp;69815357</span>
    </div>
</div>
</body>
</html>
