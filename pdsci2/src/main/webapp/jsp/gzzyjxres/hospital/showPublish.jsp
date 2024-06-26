<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/gzzyjxres/htmlhead-gzzyjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function publish(){
            jboxConfirm("确认发布录取通知书?", function(){
                jboxPost("<s:url value='/gzzyjxres/hospital/publish'/>",{"resumeFlow":${param.resumeFlow}}, function(resp){
                    if(resp=="发布成功")
                    {
                        window.parent.search();
                    }
                    jboxTip(resp);
                } , null , true);
            });
        }
    </script>
</head>
<body>
<div class="div_table">
    <div style="width: 100%;height: 450px;overflow: auto;">
    <p>
        <br/>
    </p>
    <p style="text-align:center">
        <span style="font-size:35px;font-family:华文中宋">入学通知书</span>
    </p>
    <p style="text-indent:37px">
        <strong><span style="text-decoration:underline;"><span style="font-size:19px;font-family:宋体;color:red">${userName}</span></span></strong><strong><span style="font-size:20px;font-family: 宋体">：</span></strong>
    </p>
    <p style="text-indent:33px;line-height:35px">
        <span style="font-family:楷体_GB2312"></span>
    </p>
    <p style="margin-left:350px;line-height:35px">
        <span style="font-size:19px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span style="font-size:19px;font-family:宋体">成都中医药大学附属医院继续教育部<span style="color:red">录取日期：</span></span><strong><span style="text-decoration:underline;"><span style="font-size:19px;font-family:宋体;color:red">${recruitTime}</span></span></strong>
    </p>
    <p>
        <span style="font-size:16px">---------------------------------------------------------------------------------------------------------------------------------&nbsp; </span>
    </p>
    <p style="text-align:center">
        <span style="font-size:21px;font-family:华文中宋">进修学员入学报到须知</span>
    </p>
    <p>
        <span style="font-size:16px">&nbsp;</span>
    </p>
    <p style="text-indent:32px;line-height:31px">
        <span style="font-size:16px;font-family: 宋体">一、学员报到时须带本人有效证件，以及医（护、药）师资格证、执业证的原件及复印件，有效的进修学习担保书。凡手续和证照不全、不实者，一律不予报到注册。</span>
    </p>
    <p style="text-indent:32px;line-height:31px">
        <span style="font-size:16px;font-family: 宋体">二、报到时交进修培养费</span><strong><span style="text-decoration:underline;"><span style="font-size:19px;font-family:宋体;color:red">${trainFee}</span></span></strong><span style="font-size:16px;font-family:宋体">元，服装费、办证费共计</span><strong><em><span style="font-size:16px">200</span></em></strong><span style="font-size:16px;font-family:宋体">元，学员须一次性交清上述各项费用（<strong><span style="text-decoration:underline;">一律交现金</span></strong>，概不办理汇单、汇票）才能安排进修学习。护理的进修人员自备工作服，不交服装费。</span>
    </p>
    <p style="text-indent:32px;line-height:31px">
        <span style="font-size:16px;font-family: 宋体">三、学员逾期不到校，取消入学资格。因故中断学习概不退费，也不换人；进修学员须身心健康（妊娠期妇女不安排进修学习）。</span>
    </p>
    <p style="text-indent:32px;line-height:31px">
        <span style="font-size:16px;font-family: 宋体">四、报到时须交近期</span><strong><span style="text-decoration:underline;"><span style="font-size:16px">1</span></span></strong><strong><span style="text-decoration:underline;"><span style="font-size:16px;font-family:宋体">寸标准免冠半身照片</span></span></strong><strong><span style="text-decoration:underline;"><span style="font-size:16px">3</span></span></strong><strong><span style="text-decoration:underline;"><span style="font-size:16px;font-family:宋体">张</span></span></strong><span style="font-size: 16px;font-family:宋体">，供办证使用，学员在进修学习期间需自备个人生活用品和听诊器。</span>
    </p>
    <p style="text-indent:32px;line-height:31px">
        <span style="font-size:16px;font-family: 宋体">五、临床进修不放寒暑假，进修原则上服从科室安排。学员在进修学习期间不参加原单位的会议活动，所进修的专业中途不得要求转科，也不延长或缩短进修期限。如需进修延期原则上须提前</span><span style="font-size:16px">3</span><span style="font-size:16px;font-family:宋体">个月提出申请。</span>
    </p>
    <p style="text-indent:32px;line-height:31px">
        <span style="font-size:16px;font-family: 宋体">六、学员在进修学习期间须遵纪守法、遵守医院的各项规章制度。事假须本人申请，单位证明；病假需有医院病情证明。凡病事假累计超过规定期限（即：一年病事假累计超过</span><span style="font-size:16px">4</span><span style="font-size:16px;font-family:宋体">周，半年超过</span><span style="font-size:16px">2</span><span style="font-size:16px;font-family:宋体">周），停止进修，不发结业证，不假离院按自动退学处理。</span>
    </p>
    <p>
        <span style="font-size:16px;font-family: 黑体"><br style="page-break-before:always" clear="all"/> </span>
    </p>
    <p style="text-align:center;line-height:29px">
        <strong><span style="font-size:20px;font-family:宋体">成都中医药大学附属医院</span></strong>
    </p>
    <p style="text-align:center;line-height:29px">
        <strong><span style="font-size:20px;font-family:宋体;letter-spacing:5px">四川省中医</span></strong><strong><span style="font-size:20px;font-family:宋体;letter-spacing:0">院</span></strong>
    </p>
    <p>
        <br/>
    </p>
    <table cellspacing="0" cellpadding="0">
        <tbody>
        <tr class="firstRow">
            <td style="word-break: break-all;" width="46" height="2"></td>
            <td style="word-break: break-all;" width="46" height="2"></td>
        </tr>
        <tr>
            <td style="word-break: break-all;"></td>
            <td></td>
        </tr>
        </tbody>
    </table>
    <p>
        <span style="font-size:16px">&nbsp;</span>
    </p>
    <p style="text-indent:32px">
        <span style="font-size:16px">&nbsp;</span>
    </p>
    <p style="text-indent:0">
        <span style="font-size:16px">&nbsp;</span>
    </p>
    <p>
        <br clear="ALL"/>
    </p>
    <p style="text-indent:47px">
        <span style="font-family:宋体">为了便于</span><span style="font-family:宋体">规范</span><span style="font-family:宋体">管理，防止医、技、药、护进修生到成都中医药大学附属医院进修学习期间发生医疗事故、差错，根据成都中医药大学附属医院进修教育管理规定，进修生选送单位向进修生接收单位郑重承诺：督促本单位选送到成都中医药大学附属医院进修学习的职工（进修生）在其进修学习期间，严格遵守医院和进修学习单位的各项规章制度，并与进修生本人一道，共同承担因其处理不当、操作失误等原因而发生的医疗事故、差错的责任赔偿，以及因此造成精密、贵重仪器损坏的赔偿。</span>
    </p>
    <p style="text-indent:37px">
        <span style="font-size:19px;font-family:宋体">同时，进修生本人也郑重承诺：在院进修学习期间，严格遵守国家法规和院纪院规，保持良好的医德医风，服从科室的安排，努力学习，积极参与社会公益活动。</span>
    </p>
    <p style="text-indent:37px">
        <span style="font-size:19px;font-family:宋体">进修生接收单位向完成上述承诺，履行了各项入学报到手续的进修生提供专业进修学习机会，按进修学习计划为进修生选送单位培养实用人才。</span>
    </p>
    <p style="line-height:27px">
        <span style="font-size:19px">&nbsp;</span>
    </p>
    <p style="line-height:33px">
        <span style="font-size:19px;font-family:宋体">选送单位负责人（签字）</span><span style="font-size:19px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span style="font-size:19px;font-family:宋体">进修学员（签字）</span>
    </p>
    <p style="line-height:33px">
        <span style="font-size:19px">&nbsp;</span>
    </p>
    <p style="line-height:33px">
        <span style="font-size:19px;font-family:宋体">选送单位</span><span style="font-size:19px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span style="font-size:19px;font-family:宋体">（盖章）</span>
    </p>
    <p style="text-indent:392px;line-height:33px">
        <span style="font-size:19px;font-family:宋体">年</span><span style="font-size:19px">&nbsp;&nbsp;&nbsp; </span><span style="font-size:19px;font-family:宋体">月</span><span style="font-size:19px">&nbsp;&nbsp;&nbsp; </span><span style="font-size:19px;font-family:宋体">日</span>
    </p
    <p>
        <br clear="ALL"/>
    </p>
    <p style="text-indent:32px">
        <span style="font-size:16px;font-family:宋体">注：本担保书经进修生本人及其单位领导签字盖章，报到时交验无误生效，请学员自留复印件。</span>
    </p>
    <p>
        <br/>
    </p>
</div>
</div>
<div align="center" style="margin-top: 20px; margin-bottom:20px;">
    <input class="btn_green" style="width:60px;" onclick="publish();" value="发布" readonly="readonly" />
    <input class="btn_grey" style="width:60px;" onclick="jboxClose();" value="关闭" readonly="readonly" />
</div>
</body>
</html>