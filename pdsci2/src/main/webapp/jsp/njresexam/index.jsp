<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="login" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script>

        function printExamCard(userId,flag){
            var msg = "打印准考证";
            if('Y' == flag){
                msg = "新冠肺炎疫情防控告知书";
            }
            var url = "<s:url value='/res/njExam/printCard'/>?userId=" + userId+"&flag="+flag;
            jboxStartLoading();
            jboxOpen(url, msg, '20cm', 1000);
            jboxEndLoading();
        }
        function shouye() {
            var url = "<s:url value='/res/njExam/index'/>";
            window.location.href = url;
        }

        $(function () {
            $("#indexBody").css("height", window.innerHeight + "px");
        });

    </script>

    <style>
        body {
            overflow: hidden;
        }
        .base_info {
            width: 18cm;
        }
        .base_info th{
            text-align: center;
        }
        .base_info td{
            text-align: left;
        }
        .noprint {
            visibility: hidden
        }
    </style>
</head>

<body>
<div style="overflow:auto;" id="indexBody">
    <div class="bd_bg">
        <div class="yw">
            <div class="head">
                <div class="head_inner">
                    <h1 class="logo">
                        <a onclick="shouye();">江苏省结业技能考试准考证打印平台</a>
                    </h1>
                    <div class="account">
                        <h2 style="text-align: right;">您好，<span
                                id="topUserName">${sessionScope.currUser.userName }</span></h2>
                        <div class="head_right">
                            <a onclick="shouye();">首页</a>&#12288;
                            <a href="<s:url value='/inx/njresexam/logout'/>">退出</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <nav>
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_management"></i>结业考核管理
                                </dt>
                                <dd class="menu_item"><a href="#baseInfo">准考证打印</a></dd>
                            </dl>
                        </nav>
                    </div>
                    <div class="col_main" id="content">
                        <div class="main_hd">
                            <h2>准考证信息&#12288;&#12288;
                                <input class="btn_green" type="button" id="addRecruitBtn"
                                       onclick="printExamCard('${docinfo.userId}');" value="打印准考证">&#12288;
                                <c:if test="${docinfo.createTime =='20210524092829'}">
                                    <input class="btn_green" type="button" id="addRecruitBtn2"
                                           onclick="printExamCard('${docinfo.userId}','Y');" value="新冠肺炎疫情防控告知书">
                                </c:if>

                            </h2>
                            <div class="doctorContent">
                                <p class="ticket_title" style="color: red;margin:10px; font-weight:bold; ">${docinfo.title}</p>
                                <div class="search_table" id="baseInfo">
                                    <table border="0" cellpadding="0" cellspacing="0" class="base_info">
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
                                                    <img src="${sysCfgMap['upload_base_url']}/${docinfo.phonePath}"
                                                         onerror="this.src='<s:url value="/jsp/njresexam/moren.png"/>'" width="100%" height="100%"/>
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
                                                <textarea  readonly="readonly" style="height: 250px;color: #686868;margin: 0px;border-left: 0px; text-indent:0px;" >${docinfo.remark}</textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th colspan="6">考生须知</th>
                                        </tr>
                                        <tr>
                                            <td colspan="6">
                                                1、考生须同时持准考证和有效身份证，提前到达考试地点集中点名；<br>
                                                2、请考生自带工作服、听诊器、帽子、口罩、签字笔；贵重物品请自行妥善保管；<br>
                                                3、禁止携带各种通讯工具或电子设备等与考试无关的物品进入考场，一经发现视同作弊；<br>
                                                4、考生入场后应严格服从监考人员管理。考试结束待监考人员许可后离场；<br>
                                                5、本准考证需由<b>工作单位或培训单位加盖单位（管理部门）公章</b>方为有效。<br>
                                            </td>
                                        </tr>
                                        <%--<tr>
                                            <th colspan="6">新冠肺炎疫情防控告知书</th>
                                        </tr>
                                        <tr>
                                            <td colspan="6">
                                                <h1 style="text-align:center">南京市2020年住院医师规范化培训结业考试（考核）</h1>
                                                <h1 style="text-align:center"> 新冠肺炎疫情防控告知书 </h1>
                                                <h4 >
                                                    &#12288;&#12288;根据江苏省卫生健康委员会文件《关于全省卫生健康系统做好各类大型考试新冠肺炎疫情防控工作的通知》（苏卫疾控202048号）文件要求，现将南京市住院医师规范化培训结业考试疫情防控要求告知如下：<br>
                                                    &#12288;&#12288;1. 考生在考试当天进入考点时出示实时“苏康码”并配合检测体温。<b>“苏康码”为绿码，</b>且经现场<b>测量体温低于37.3℃、无干咳等异常症状</b>的人员方可进入考点参加考试。<br>
                                                    &#12288;&#12288;2. 对6月16日起，“苏康码”为“绿码”的来自<b>北京</b>的考生应主动出示<b>有效的7天内新冠病毒核酸检测为阴性</b>的报告，方可进入考点参加考试。<br>
                                                    &#12288;&#12288;3. 自<b>湖北</b>来宁返宁的考生应主动出示<b>有效的7天内新冠病毒核酸检测为阴性</b>的报告，其中<b>武汉</b>来宁返宁人员要增加<b>抗体检测（IgG和IgM）</b>，并出具相应报告，方可进入考点参加考试。<br>
                                                    &#12288;&#12288;4. 对5月30日以来，凡有<b>国务院联防联控机制发布的高中风险地区（包括北京丰台区、石景区、太兴区等）</b>旅居史，或到过<b>北京新发地批发市场、京深海鲜市场、海淀区玉泉东商品交易市场、西城区广外天陶红莲菜市场</b>等风险场所的考生，应主动出示<b>解除隔离证明</b>和<b>有效的7天内新冠病毒核酸检测为阴性</b>的报告方可进入考点参加考试。<br>
                                                    &#12288;&#12288;5.来自<b>国（境）外</b>的考生，应主动出示<b>解除隔离证明</b>和<b>有效的7天内新冠病毒核酸检测为阴性</b>的报告方可进入考点参加考试。<br>
                                                    &#12288;&#12288;6. 考生应自备<b>一次性医用口罩或无呼吸阀的N95口罩</b>，除身份确认环节需摘除口罩以外全程佩戴，做好个人防护。<br>
                                                    &#12288;&#12288;7. 因国内外疫情发展呈动态变化，疫情防控标准以国务院联防联控机制发布的疫情风险等级为准。<br>
                                                    &#12288;&#12288;8. 考生应认真阅读本文件，知悉<b>告知事项、证明义务和防疫要求</b>，凡隐瞒或谎报旅居史、接触史、健康状况等疫情防控重点信息，或不配合防疫检测、询问、排查、送诊等造成严重后果的，取消其相应资格，并按有关规定进行处理，构成违法的将依法追究其法律责任。
                                                </h4>
                                            </td>
                                        </tr>--%>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="foot">
        <div class="foot_inner">
            主管单位：江苏省卫生健康委员会科教处 | 技术支持：南京品德网络信息技术有限公司
        </div>
    </div>

</div>

</body>
</html>
