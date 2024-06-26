<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>住院医师规范化培训结业考核管理平台</title>
    <jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
    </jsp:include>

    <script type="text/javascript">
        function pt() {
            window.print();
        }

        function down() {
            var url = "<s:url value='print'/>?watermarkFlag=${GlobalConstant.FLAG_N}&recTypeId=ExamCard";
            window.location.href = url;
        }

        function downPdf() {
            /* var url ="
            <s:url value='print'/>?watermarkFlag=
            ${GlobalConstant.FLAG_N}&recTypeId=ExamCard&printType=pdf"; */
            var url = "<s:url value='downPdfExamCard'/>";
            window.location.href = url;
        }
        $(function () {
            $("#indexBody").css("height", window.innerHeight + "px");
        });
    </script>
</head>
<style media=print type="text/css">
    .noprint {
        visibility: hidden
    }
    .tabstl th{
        font-weight: bold;
        font-size: 15px;
        text-align: center;
    }
    .tabstl td{
        font-weight: 510;
        text-align: left;
    }
</style>

<body style="margin:0 10px;">
<div style="overflow:auto;" id="indexBody">
    <div style="width: 100%">
        <p class="noprint" style="text-align: right; margin-right: 30px;">
            <a onclick="pt();" class="btn_blue" id="pt" target="_self">打印准考证</a>
        </p>
    </div>
    <div class="user-contain" style="position:relative;width: 46%;float: left;"><%--background: url('<s:url value="/jsp/tjresexam/1.png"/>')--%>
        <img style="position:absolute;z-index:-999;" src="<s:url value="/jsp/tjresexam/beijing2.png"/>">
        <p class="ticket_title" style="color: #334C9C;margin-top:30px;">天津市2019年度住院医师</p>
        <p class="ticket_title" style="color: #334C9C;margin-top:10px;">规范化培训结业考核</p>
        <h5 style="color: #FC0403;">准考证</h5>
        <div class="user-bd">
            <table class="tabstl" cellpadding="0" cellspacing="0" border="0" style="width:100%;margin:0 auto;">
                <colgroup>
                    <col width="28%"/>
                    <col width="30%"/>
                    <col width="42%"/>
                </colgroup>
                <tr>
                    <th>姓&#12288;&#12288;名:</th>
                    <td>${docinfo.userName}</td>
                    <td rowspan="4">
                        <div style="width: 35mm;height: 57mm;margin-left:-5px;margin-top:20px;">
                            <img src="<s:url value="${sysCfgMap['upload_base_url']}/${docinfo.phonePath}"/>"
                                 width="100%" height="100%" onerror="this.src='<s:url value="/jsp/tjresexam/moren.png"/>'"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>性&#12288;&#12288;别：</th>
                    <td>${docinfo.userSex}</td>
                </tr>
                <tr>
                    <th>专&#12288;&#12288;业：</th>
                    <td>${docinfo.speName}</td>
                </tr>
                <tr>
                    <th>准考证号：</th>
                    <td>${docinfo.ticketNum}</td>
                </tr>
            </table>
        </div>
    </div>
    <div class="user-contain" style="position:relative;width: 46%;float: left;margin-left:6%">
        <div class="user-bd">
            <div>
                <p style="line-height: 30px;text-align: center;">
                    <b style="font-size: 18px">考生须知</b>
                    <br/>
                </p>
                <p style="line-height: 20px; font-size: 14px">
                    <br/>
                    &#12288;&#12288;携带身份证、准考证提前20分钟进入考场指定等待区域<br/>
                    &#12288;&#12288;考生自备白大衣、听诊器、水笔和记录本。<br/>
                    <%--&#12288;&#12288;具体考试时间请登录天津市医师规范化培训网络管理平台公示公告栏查看<br/>--%>
                    &#12288;&#12288;考生需听从指挥和统一安排，严格遵守考场规则和纪律。<br/><br/>
                </p>
                <p style="line-height: 30px;text-align: center;">
                    <b style="font-size: 18px">编号规则</b>
                </p>
                <p style="line-height: 20px;font-size: 14px">
                    <br/>&#12288;&#12288;准考证编号：<br/>
                    &#12288;&#12288;共11位数字，前4位位年份，第五位为医师类别（西医1、中医2），第6-7位分别为二\三站考点编号，第8-10位为大排行顺序编号。<br/>
                    &#12288;&#12288;考点编号：<br/>
                    &#12288;&#12288;1、总医院&#12288;2、武警&#12288;3、人民&#12288;4、三中心&#12288;5、儿童&#12288;6、安定&#12288;7、医口&#12288;8、市口&#12288;9、眼院&#12288;0、一中心<br/>
                    &#12288;&#12288;A、中一附&#12288;B、中二附&#12288;C、中研院<br/>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
