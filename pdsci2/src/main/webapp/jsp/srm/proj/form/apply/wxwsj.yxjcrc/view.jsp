<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
    </jsp:include>
    <style type="text/css">
        #fund td {
            text-align: center;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".redspan").hide();
            $('input').attr("readonly", "readonly");
            $('textarea').attr("readonly", "readonly");
            $("select").attr("disabled", "disabled");
            $(":checkbox").attr("disabled", "disabled");
            $(":radio").attr("disabled", "disabled");
            $(".ctime").removeAttr("onclick");
        });
        function back() {
            history.back();
        }
    </script>
    <style type="text/css">
        .line {
            border: none;
        }
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div class="title1 clearfix">
                <div style="overflow:hidden;">
                <ul id="tags">
                    <li class="selectTag"><a onclick="selectTag('tagContent0',this)" href="javascript:void(0)">基本信息</a></li>
                    <li><a onclick="selectTag('tagContent1',this)" href="javascript:void(0)">医学杰出人才个人概况</a></li>
                    <li><a onclick="selectTag('tagContent2',this)" href="javascript:void(0)">医学杰出人才简历</a></li>
                    <li><a onclick="selectTag('tagContent3',this)" href="javascript:void(0)">医学杰出人才主要业绩</a></li>
                    <li><a onclick="selectTag('tagContent4',this)" href="javascript:void(0)">依托课题立题依据</a></li>
                    <li><a onclick="selectTag('tagContent5',this)" href="javascript:void(0)">依托课题研究内容</a></li>
                    <li><a onclick="selectTag('tagContent6',this)" href="javascript:void(0)">课题组成员名单</a></li>
                    <li><a onclick="selectTag('tagContent7',this)" href="javascript:void(0)">项目规划</a></li>
                    <li><a onclick="selectTag('tagContent8',this)" href="javascript:void(0)">经费预算</a></li>
                    <li><a onclick="selectTag('tagContent9',this)" href="javascript:void(0)">审核意见</a></li>
                    <li><a onclick="selectTag('tagContent10',this)" href="javascript:void(0)">附件信息</a></li>
                </ul>
                </div>
                <div id="tagContent">
                    <div class="tagContent selectTag" id="tagContent0" style="padding-top: 0px;">
                        <jsp:include page="step0.jsp">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent1" style="padding-top: 0px;">
                        <jsp:include page="step1.jsp">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent2" style="padding-top: 0px;">
                        <jsp:include page="step2.jsp">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent3" style="padding-top: 0px;">
                        <jsp:include page="step3.jsp">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent4" style="padding-top: 0px;">
                        <jsp:include page="step4.jsp">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent5" style="padding-top: 0px;">
                        <jsp:include page="step5.jsp">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent6" style="padding-top: 0px;">
                        <jsp:include page="step6.jsp">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent7" style="padding-top: 0px;">
                        <jsp:include page="step6_1.jsp">
                            <jsp:param  name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>
                    <div class="tagContent" id="tagContent8" style="padding-top: 0px;">
                        <jsp:include page="step7.jsp">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent9" style="padding-top: 0px;">
                        <jsp:include page="step8.jsp">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>
                    <div class="tagContent" id="tagContent10" style="padding-top: 0px;">
                        <jsp:include page="step9.jsp">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>
                </div>
            </div>
            <p>
                <script type="text/javascript">
                    function selectTag(showContent, selfObj) {
                        // 操作标签
                        var tag = document.getElementById("tags").getElementsByTagName("li");
                        var taglength = tag.length;
                        for (i = 0; i < taglength; i++) {
                            tag[i].className = "";
                        }
                        selfObj.parentNode.className = "selectTag";
                        // 操作内容
                        for (i = 0; j = document.getElementById("tagContent" + i); i++) {
                            j.style.display = "none";
                        }
                        document.getElementById(showContent).style.display = "block";
                    }
                </script>
            </p>
        </div>
    </div>
</div>
</body>
</html>