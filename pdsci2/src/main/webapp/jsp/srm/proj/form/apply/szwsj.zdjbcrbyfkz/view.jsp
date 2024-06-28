<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>

<script type="text/javascript">
    $(function () {
        $(".redspan").hide();
        $('input').attr("readonly", "readonly");
        $('textarea').attr("readonly", "readonly");
        $("select").attr("disabled", "disabled");
        $(":checkbox").attr("disabled", "disabled");
        $(":radio").attr("disabled", "disabled");
        $(".ctime").removeAttr("onclick");
        $("input").removeAttr("onclick");
    });
    function back() {
        history.back();
    }
</script>

<div id="main">
    <div class="mainright">
        <div class="content">
            <div class="title1 clearfix">
                <div style="overflow:hidden;">
                    <ul id="tags">
                        <li class="selectTag"><a onclick="selectTag('tagContent0',this)"
                                                 href="javascript:void(0)">基本信息</a></li>
                        <li><a onclick="selectTag('tagContent1',this)" href="javascript:void(0)">立项依据</a></li>
                        <li><a onclick="selectTag('tagContent2',this)"
                               href="javascript:void(0)">研究内容、研究试验方法、技术路线以及工艺流程</a></li>
                        <li><a onclick="selectTag('tagContent3',this)" href="javascript:void(0)">工作基础和条件</a></li>
                        <li><a onclick="selectTag('tagContent4',this)" href="javascript:void(0)">项目研究预期成果及效益</a></li>
                        <li><a onclick="selectTag('tagContent5',this)" href="javascript:void(0)">计划进度安排与考核指标</a></li>
                        <li><a onclick="selectTag('tagContent6',this)" href="javascript:void(0)">附件清单</a></li>
                    </ul>
                </div>
                <div id="tagContent" style="margin-top: 10px;border:none;">
                    <div class="tagContent selectTag" id="tagContent0" style="padding-top: 0px;">
                        <jsp:include page="step1.jsp" flush="true">
                            <jsp:param name="view" value="${GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent1" style="padding-top: 0px;">
                        <jsp:include page="step2.jsp" flush="true">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent2" style="padding-top: 0px;">
                        <jsp:include page="step3.jsp" flush="true">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent3" style="padding-top: 0px;">
                        <jsp:include page="step4.jsp" flush="true">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent4" style="padding-top: 0px;">
                        <jsp:include page="step5.jsp" flush="true">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent5" style="padding-top: 0px;">
                        <jsp:include page="step6.jsp" flush="true">
                            <jsp:param name="view" value="${ GlobalConstant.FLAG_Y}"/>
                        </jsp:include>
                    </div>

                    <div class="tagContent" id="tagContent6" style="padding-top: 0px;">
                        <jsp:include page="step7.jsp" flush="true">
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
