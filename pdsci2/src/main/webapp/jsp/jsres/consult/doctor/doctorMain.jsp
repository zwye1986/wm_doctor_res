<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="consult" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function(){
            $("li").click(function(){
                $(".tab_select").addClass("tab");
                $(".tab_select").removeClass("tab_select");
                $(this).removeClass("tab");
                $(this).addClass("tab_select");
            });
            if ("${param.liId}" != "") {
                $("#${param.liId}").addClass("tab_select");
            } else {
                $('li').first().addClass("tab_select");
            }
            searchArea()
        });

        function searchArea() {
            $("li.tab_select").click();
        }

        function enquireArea(){
            var orgCityName = $("#orgCityName").val();
            var consultQuestion = $("#consultQuestion").val();
            var orderBy = $("#orderBy").attr("value");
            var url = "<s:url value='/jsres/consult/enquireArea?orgCityName='/>"+orgCityName+"&consultQuestion="+consultQuestion+"&orderBy="+orderBy
            jboxPostLoad("div_table_0", url, true);
        }

        function policyArea(){
            var orgCityName = $("#orgCityName").val();
            var consultQuestion = $("#consultQuestion").val();
            var orderBy = $("#orderBy").attr("value");
            var url = "<s:url value='/jsres/consult/policyArea?orgCityName='/>"+orgCityName+"&consultQuestion="+consultQuestion+"&orderBy="+orderBy
            jboxPostLoad("div_table_0", url, true);
        }

        function myQuestionForm(currentPage) {
            if (${empty sessionScope.currUser}){
                jboxTip("当前未登录!");
                var url = "<s:url value='/inx/jsres'/>";
                <c:if test="${sysCfgMap['sys_index_url']=='/inx/tjres'}">
                   /*天津与江苏西医合并后导致问题：增加判断是否天津西医*/
                    url = "<s:url value='/inx/tjres'/>";
                </c:if>
                setTimeout(function(){window.open(url,"_self")},1000);
            }else {
                currentPage=currentPage||1;
                var url = "<s:url value='/jsres/consult/myQuestionForm?currentPage='/>"+currentPage;
                jboxLoad("myQues", url, false);
            }
        }
    </script>
</head>
<body>
<div class="bd_bg" style="overflow: auto">
    <div class="head">
        <div class="head_inner">
            <h1 class="logo">
                <a href="/jsres/manage/global">${sysCfgMap['sys_title_name']}</a>
            </h1>
        </div>
    </div>
    <div class="container" style="min-width: 1200px;max-width: 1200px;min-height: 700px;margin-left: auto;margin-right: auto;">
        <div id="myQues">
        <div style="margin: 32px">
            <h1>咨询专区</h1>
        </div>
        <form id="searchForm">
            <div class="flex  bordbox" style="margin-top: 5px">
                <div class="flex just-b poli-serchbox ">
                    <div class="flex">
                        <div class="poli-serch bordbox">
                            <input type="text" value="${consultQuestion}" name="consultQuestion" id="consultQuestion" placeholder="请输入问题" onfocus="this.placeholder=''" onblur="this.placeholder='请输入问题'" />
                        </div>
                        <div class="poli-select-box bordbox">
                            <select name="orgCityName" id="orgCityName" style="width: 100%;height: 100%;border: none;outline: none; padding-right: 5px;">
                                <option value="">全部地市</option>
                                <c:forEach items="${orgCityNameList}" var="orgCityNameParam">
                                    <option value="${orgCityNameParam}" <c:if test="${orgCityName eq orgCityNameParam}">selected="selected"</c:if>>${orgCityNameParam}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="serch-icon">
                            <i onclick="searchArea()"></i>
                        </div>
                    </div>
                    <div class="flex align-c">
                        <div class="ques-icon">
                        </div>
                        <a href="javascript:void(0);" onclick="myQuestionForm()" class="fs14" style="color: #54B2E5;">我的问题</a>
                    </div>
                </div>

            </div>
            <div class="title_tab flex just-b" style="padding-right: 30px">
                <ul>
                    <li class="tab_select" id="enquireArea" name="enquireArea" onclick="enquireArea()"><a>问答专区</a></li>
                    <li class="tab" name="policyArea" onclick="policyArea()"><a>政策专区</a></li>
                </ul>
            </div>
        </form>
        <div class="main_bd" id="div_table_0" >

        </div>
    </div>
    </div>
    <div class="foot">
        <div class="foot_inner">
            主管单位：${sysCfgMap['the_competent_unit']} | 技术支持：南京品德网络信息技术有限公司   |  <a href="https://beian.miit.gov.cn/" target="_blank" style="color:#FFFFFF;">工信部备案号：苏ICP备14054231号-1</a>
        </div>
    </div>
</div>
</body>
</html>



