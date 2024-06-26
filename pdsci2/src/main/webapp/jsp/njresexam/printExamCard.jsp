<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>住院医师规范化培训结业考核管理平台</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
    </jsp:include>

    <script type="text/javascript">
        function pt() {
            window.print();
        }

        function downPdf() {
            var url = "<s:url value='downPdfExamCard'/>";
            window.location.href = url;
        }
        $(function () {
            //$("#indexBody").css("height", window.innerHeight + "px");
        });
    </script>
</head>
<style media=print type="text/css">
    .noprint {
        visibility: hidden
    }
    .base_info th{
        text-align: center;
    }
    .base_info td{
        text-align: left;
    }
</style>
<style type="text/css">
    .base_info th{
        text-align: center;
    }
    .base_info td{
        border: none!important;
        text-align: left;
    }
</style>


<body style="margin:0 10px;overflow: auto;">
<div style="overflow:auto;height:100%" id="indexBody">
    <div style="width: 100%">
        <p class="noprint" style="text-align: right; margin-right: 30px;">
            <a onclick="pt();" class="btn_blue"  target="_self">
                <c:if test="${'Y' ne flag}">打印准考证</c:if>
                <c:if test="${'Y' eq flag}">打印新冠肺炎疫情防控告知书</c:if>
            </a>
        </p>
    </div>
    <div class="doctorContent" >
        <c:if test="${'Y' ne flag}">
            <div class="search_table" id="baseInfo">

                <p class="ticket_title" style="margin:10px; font-weight:bold; ">${docinfo.title}</p>
                <table table border="0" cellpadding="0" cellspacing="0" class="base_info">
                    <colgroup>
                        <col width="20%"/>
                        <col width="20%"/>
                        <col width="20%"/>
                        <col width="20%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                    </colgroup>
                    <tr>
                        <th>姓名</th>
                        <td>${docinfo.userName}</td>
                        <th>考试科目</th>
                        <td>${docinfo.speName}</td>
                        <td colspan="2" rowspan="4">
                            <div style="width: 40mm;height: 60mm">
                                <img  src="${sysCfgMap['upload_base_url']}/${docinfo.phonePath}"
                                     onerror="this.src='<s:url value="/jsp/njresexam/moren.png"/>'" width="98%" height="98%"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>准考证号</th>
                        <td colspan="3">${docinfo.ticketNum}</td>
                    </tr>
                    <tr>
                        <th>有效身份证号</th>
                        <td colspan="3">${docinfo.idNo}</td>
                    </tr>
                    <tr>
                        <th>考试时间</th>
                        <td colspan="3">
                            <textarea  readonly style="height: 110px;color: #686868;margin: 0px;border-left: 0px;text-indent:0px;">${docinfo.examtime}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>考试地点</th>
                        <td colspan="5">
                            <textarea  readonly style="height: 120px;color: #686868;margin: 0px;border-left: 0px;text-indent:0px;">${docinfo.address}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>学员联系电话</th>
                        <td colspan="5">${docinfo.userPhone}</td>
                    </tr>
                    <tr>
                        <th>考点联系电话</th>
                        <td colspan="5">${docinfo.sitephone}</td>
                    </tr>
                    <tr>
                        <th>注意事项</th>
                        <td  colspan="5" >
                            <textarea  readonly style="height: 250px;color: #686868;margin: 0px;border-left: 0px;text-indent:0px;">${docinfo.remark}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="6">考生须知</th>
                    </tr>
                    <tr style="padding-bottom: 20px">
                        <td colspan="6">
                            1、考生须同时持准考证和有效身份证，提前到达考试地点集中点名；<br>
                            2、请考生自带工作服、听诊器、帽子、口罩、签字笔；贵重物品请自行妥善保管；<br>
                            3、禁止携带各种通讯工具或电子设备等与考试无关的物品进入考场，一经发现视同作弊；<br>
                            4、考生入场后应严格服从监考人员管理。考试结束待监考人员许可后离场；<br>
                            5、本准考证需由<b>工作单位或培训单位加盖单位（管理部门）公章</b>方为有效。
                        </td>
                    </tr>
                </table>
<%--                <br><br><br><br><br><br><br><br><br><br><br><br><br><br>--%>
            </div>
        </c:if>
            <%-- 禅道需求编号：446  修改承诺书模版 修改时间：2020年7月9日 产品：徐开宏--%>
<c:if test="${'Y' eq flag}">
            <div class="search_table" id="baseInfo2">
                <table table border="0" cellpadding="0" cellspacing="0" class="base_info"  style="padding-top: 10px;border: 0">
                    <tr style="padding-top: 20px">
                        <td colspan="6">
                            <h1 style="text-align:center;padding-top: 20px">南京市2021年住院医师规范化培训结业考试（考核</h1>
                            <h1 style="text-align:center">新冠肺炎疫情防控告知暨考生承诺书</h1>
                            <font style="line-height: 35px">
                                &#12288;&#12288;_____________学员:<br>
                                &#12288;&#12288;根据江苏省卫生健康委员会文件《关于全省卫生健康系统做好各类大型考试新冠肺炎疫情防控工作的通知》文件要求，现将南京市住院医师规范化培训结业考试疫情防控要求告知如下：<br>
                                &#12288;&#12288;一：考生在考试当天进入考点时应主动向工作人员出示“苏康码”、“行程码”并配合检测体温。“苏康码”、“行程码”为绿码，且经现场测量体温低于37.3℃、无干咳等异常症状的人员方可进入考点参加考试。来自安徽、辽宁等中风险地区的考生还应主动出示有效的7天内新冠病毒核酸检测为阴性的报告。参加考试的考生应自备口罩，除身份确认环节需摘除口罩以外全程佩戴，做好个人防护。<br>
                                &#12288;&#12288;二：按当前疫情防控有关要求，考试当天持“苏康码”、“行程码”非绿码的考生不得进入考点参与考试，并配合安排至指定地点进行集中隔离医学观察。考试前14天内有国内疫情中高风险地区或国（境）外旅居史或有新冠肺炎确诊病例、疑似病例、无症状感染者密切接触史的考生，应主动报告，并配合安排至指定地点进行集中隔离医学观察。凡有国务院联防联控机制发布的高中风险地区旅居史的考生（包括安徽六安市、合肥市，辽宁营口市、沈阳市等风险场所），应主动出示解除隔离证明和7天内有效的新冠病毒核酸检测阴性报告，方能进入考点参加考试；国（境）外的考生，应主动出示解除隔离证明和7天内有效的新冠病毒核酸检测阴性报告，方能进入考点参加考试。<br>
                                &#12288;&#12288;三：国内外疫情发展呈动态变化，疫情防控标准以国务院联防联控机制发布的疫情风险等级为参照。凡隐瞒或谎报旅居史、接触史、健康状况等疫情防控重点信息，或不配合工作人员进行防疫检测、询问、排查、送诊等造成严重后果的，取消其相应资格，并按有关规定进行处理，构成违法的将依法追究其法律责任。<br>
                                &#12288;&#12288;四：考生应认真阅读本文件，知悉告知事项、证明义务和防疫要求。考生在本页签字，即代表作出以下承诺：“本人已认真阅读《南京市2021年住院医师规范化培训结业考试（考核）新冠肺炎疫情防控告知暨考生承诺书》，知悉告知事项、证明义务和防疫要求。在此郑重承诺：本人填报、提交和现场出示的所有信息（证明）均真实、准确、完整、有效，并保证配合做好疫情防控相关工作。如有违反，本人自愿承担相关责任、接受相应处理。”<br>
                            </font>
                        </td>
                    </tr>
                    <tr>
                        <td ></td>
                        <td colspan="2">
<%--                            &#12288;&#12288;准考证号：______________________--%>
                        </td>
                        <td colspan="2">
                            本人签字：________________
                        </td>
                        <td ></td>
                    </tr>
                    <tr style="text-align:right;">
                        <td ></td><td ></td><td ></td>
                        <td colspan="4">
                            _______年_______月_______日
                        </td>
                    </tr>
                </table>
            </div>
</c:if>
        <%--<div class="search_table" id="baseInfo2">
            <table table border="0" cellpadding="0" cellspacing="0" class="base_info" style="padding-top: 10px">
                <tr style="padding-top: 20px">
                    <td colspan="6">
                        <h1 style="text-align:center;padding-top: 20px">南京市2020年住院医师规范化培训结业考试（考核）</h1>
                        <h1 style="text-align:center"> 新冠肺炎疫情防控告知书 </h1>
                        <font style="line-height: 35px">
                            &#12288;&#12288;根据江苏省卫生健康委员会文件《关于全省卫生健康系统做好各类大型考试新冠肺炎疫情防控工作的通知》（苏卫疾控202048号）文件要求，现将南京市住院医师规范化培训结业考试疫情防控要求告知如下：<br>
                            &#12288;&#12288;1. 考生在考试当天进入考点时出示实时“苏康码”并配合检测体温。<b>“苏康码”为绿码，</b>且经现场<b>测量体温低于37.3℃、无干咳等异常症状</b>的人员方可进入考点参加考试。<br>
                            &#12288;&#12288;2. 对6月16日起，“苏康码”为“绿码”的来自<b>北京</b>的考生应主动出示<b>有效的7天内新冠病毒核酸检测为阴性</b>的报告，方可进入考点参加考试。<br>
                            &#12288;&#12288;3. 自<b>湖北</b>来宁返宁的考生应主动出示<b>有效的7天内新冠病毒核酸检测为阴性</b>的报告，其中<b>武汉</b>来宁返宁人员要增加<b>抗体检测（IgG和IgM）</b>，并出具相应报告，方可进入考点参加考试。<br>
                            &#12288;&#12288;4. 对5月30日以来，凡有<b>国务院联防联控机制发布的高中风险地区（包括北京丰台区、石景山区、大兴区等）</b>旅居史，或到过<b>北京新发地批发市场、京深海鲜市场、海淀区玉泉东商品交易市场、西城区广外天陶红莲菜市场</b>等风险场所的考生，应主动出示<b>解除隔离证明</b>和<b>有效的7天内新冠病毒核酸检测为阴性</b>的报告方可进入考点参加考试。<br>
                            &#12288;&#12288;5.来自<b>国（境）外</b>的考生，应主动出示<b>解除隔离证明</b>和<b>有效的7天内新冠病毒核酸检测为阴性</b>的报告方可进入考点参加考试。<br>
                            &#12288;&#12288;6. 考生应自备<b>一次性医用口罩或无呼吸阀的N95口罩</b>，除身份确认环节需摘除口罩以外全程佩戴，做好个人防护。<br>
                            &#12288;&#12288;7. 因国内外疫情发展呈动态变化，疫情防控标准以国务院联防联控机制发布的疫情风险等级为准。<br>
                            &#12288;&#12288;8. 考生应认真阅读本文件，知悉<b>告知事项、证明义务和防疫要求</b>，凡隐瞒或谎报旅居史、接触史、健康状况等疫情防控重点信息，或不配合防疫检测、询问、排查、送诊等造成严重后果的，取消其相应资格，并按有关规定进行处理，构成违法的将依法追究其法律责任。
                        </font>
                    </td>
                </tr>
            </table>
        </div>--%>
    </div>
</div>
</body>
</html>
