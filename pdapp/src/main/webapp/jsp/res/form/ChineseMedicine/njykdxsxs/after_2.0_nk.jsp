<script>
    function showNext(obj)
    {
        var nextTr=$(obj).parent("tr").next("tr");
        if($(nextTr).is(":hidden")){
            $(nextTr).show();
        }else{
            $(nextTr).hide();
        }
    }
</script>
<table width="100%">
    <tr>
        <th colspan="4"  onclick="showNext(this);" style="font-color:blue;">内科临床技能相关评分标准</th>
    </tr>
    <tr hidden>
        <td colspan="4">
            <div id="msg"><p style=";margin-left:8px;text-autospace:none;text-align:left;line-height:114%"><span
                    style=";font-size:14px">1.&nbsp;&nbsp;A.&nbsp;&nbsp;检查住院病历、B.&nbsp;&nbsp;报告病史、回答问题（总分&nbsp;100&nbsp;分）&nbsp;</span>
            </p>
                <p style=";margin-left:8px;text-autospace:none;text-align:left;line-height:114%"><span
                        style=";font-size:14px">A.&nbsp;&nbsp;检查住院病历（85&nbsp;分）</span></p>
                <p style=";margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑴.&nbsp;&nbsp;主诉：5&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①主要症状及发病时间有错误……3&nbsp;（……为应扣分数，下类同）</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②主要症状及发病时间有遗漏……2&nbsp;</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑵.&nbsp;&nbsp;现病史：20&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①起病情况叙述不清……1～3</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②发病经过顺序不清，条理性差或有遗漏……1～3</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">③主要症状特点不清……1～3</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">④伴随症状不清……1～3</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑤有关鉴别的症状或重要的阴性症状不清。……1～3</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑥治疗经过叙述不全面。……1～3</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑦一般症状未叙述……1～2</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑶.&nbsp;&nbsp;其他病史：5&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①项目有遗漏者每项……1～2</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②有关阴性病史未提及……1～2</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">③顺序错误……1</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑷.&nbsp;&nbsp;体检：10&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①项目有遗漏……1～3</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②重要阳性或阴性体征有遗漏……1～3</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">③结果不符……1～3</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">④顺序错误……1</span></p>
                <p style="margin-top:3px;margin-left:48px;text-autospace:none;text-align:left;line-height:114%"><span
                        style=";font-size:14px">⑸.&nbsp;&nbsp;辅助检查：10&nbsp;分&nbsp;指临床常用的重要化验项目：</span>
                </p>
                <p style=";margin-left:48px;text-autospace:none;text-align:left;line-height:114%"><span
                        style=";font-size:14px">如血、尿、粪便、脑脊液常规、血生化、X光片、心电图等的结果，不能熟练掌握运用者每项扣&nbsp;0.5～1.5&nbsp;分</span>
                </p>
                <p style=";margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑹.&nbsp;&nbsp;诊断：10&nbsp;分</span></p>
                <p style=";margin-left:8px;text-indent:30px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①主要诊断错误或有重要遗漏……5</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②次要诊断错误或有遗漏……3</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">③顺序错误……2</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑺.&nbsp;&nbsp;诊断分析：10&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①主要及次要诊断不全面……1～4</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②未作必要的鉴别诊断者……3</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">③仅罗列书本内容，缺少对本病人实际情况的具体分析……1～3</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑻.&nbsp;&nbsp;治疗计划：5&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①有错误……1～3</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②有遗漏……1～2</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑼.&nbsp;&nbsp;病程记录：5&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①治疗经过有遗漏……2</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②病情变化有遗漏……3</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑽.&nbsp;&nbsp;规格文字：5&nbsp;分（检查住院病历）</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①规格不符……1</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②不符合医学术语……1</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">③字迹潦草……1</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">④有涂改……1</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑤错别字……1</span></p>
                <p style="margin-top:3px;margin-right:7px;margin-left:40px;text-autospace:none;text-align:left;line-height:114%">
                    <span style=";font-size:14px">B.报告病历、回答问题（15&nbsp;分）&nbsp;</span></p>
                <p style="margin-top:3px;margin-right:7px;margin-left:40px;text-autospace:none;text-align:left;line-height:114%">
                    <span style=";font-size:14px">报告病历要求40分钟完成。</span></p>
                <p style=";margin-left:8px;text-indent:32px;text-autospace:none;text-align:left;line-height:114%"><span
                        style=";font-size:14px">提问与本病相关的诊断、鉴别诊断、治疗原则及基础理论，共5个问题，每题&nbsp;3&nbsp;分。</span>
                </p>
                <p style=";margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">&nbsp;</span></p>
                <p style=";margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">2.体格检查（总分&nbsp;100&nbsp;分）</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑴.&nbsp;&nbsp;速度：10&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①要求40分钟内完成全面系统检查</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②超过&nbsp;5&nbsp;分钟……5&nbsp;分，超过&nbsp;10&nbsp;分钟……10&nbsp;分</span>
                </p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑵.&nbsp;&nbsp;方法：30&nbsp;分</span> <span
                        style=";font-size:14px">检查方法不正确扣分如下：</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">一般情况……1</span> <span
                        style=";font-size:14px">咽部……1</span> <span
                        style=";font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;胸部及肺&nbsp;&nbsp;</span>
                    <span style=";font-size:14px">视诊……1</span></p>
                <p style="margin-top:3px;text-indent:7px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">皮&nbsp;&nbsp;&nbsp;&nbsp;肤……1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;颈部……1</span>
                    <span style=";font-size:14px">触诊……1</span></p>
                <p style="margin-top:1px;text-indent:7px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">淋&nbsp;巴&nbsp;结……1</span> <span
                        style=";font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <span style=";font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;叩诊……2</span>
                </p>
                <p style="margin-top:1px;text-indent:7px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">头&nbsp;&nbsp;&nbsp;&nbsp;部……1</span> &nbsp; &nbsp;<span
                        style=";font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;听诊……1</span>
                </p>
                <p style="margin-top:1px;text-indent:7px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">眼&nbsp;&nbsp;&nbsp;&nbsp;部……1</span> <span
                        style=";font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;心脏</span> <span
                        style=";font-size:14px">视诊……1&nbsp;&nbsp;&nbsp;&nbsp;腹部</span> <span
                        style=";font-size:14px">&nbsp;&nbsp;视诊……1</span></p>
                <p style="margin-top:1px;text-indent:7px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">耳&nbsp;&nbsp;&nbsp;&nbsp;部……1</span> &nbsp;<span
                        style=";font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;触诊……1</span>
                    &nbsp;<span
                            style=";font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;触诊……2</span>
                </p>
                <p style="margin-top:1px;text-indent:7px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">鼻&nbsp;&nbsp;&nbsp;&nbsp;部……1</span> &nbsp;<span
                        style=";font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;叩诊……1</span>
                    &nbsp;<span
                            style=";font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;叩诊……1</span>
                </p>
                <p style="margin-top:1px;text-indent:7px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">口&nbsp;&nbsp;&nbsp;&nbsp;部……1</span> &nbsp;<span
                        style=";font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;听诊……2</span>
                    &nbsp;<span
                            style=";font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;听诊……1</span>
                </p>
                <p style=";text-indent:7px;text-autospace:none;text-align:left;line-height:18px"><span
                        style=";font-size:14px">神经系统……2&nbsp;&nbsp;包括颈强直、克氏、布氏、巴氏征、腹壁反射、膝腱反射（神经系统疾病例外）</span>
                </p>
                <p style="margin-top:3px;text-indent:7px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">四肢关节……1</span> <span
                        style=";font-size:14px">脊柱……1</span> <span
                        style=";font-size:14px">肛门及外生殖器……1</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑶.&nbsp;&nbsp;准确性：60&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①与主要诊断有关的阳性体征遗漏……30</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②与次要诊断有关的阳性体征遗漏……10</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">③其他部位阳性体征遗漏……5～10</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">④实为阴性体征查出阳性结果……5～10</span></p>
                <p style=";text-autospace:none;text-align:left;line-height:8px"><span
                        style=";font-size:14px">&nbsp;</span></p>
                <p style=";margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">3.&nbsp;处理门诊或住院病人（总分&nbsp;100&nbsp;分）</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑴.&nbsp;&nbsp;问诊：20&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①主诉不确切……4</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②起病情况叙述不清……4</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">③有关鉴别诊断的阳性症状及阴性症状遗漏……4</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">④治疗经过叙述不清……4</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑤与本病有关的既往史及接触史遗漏……4</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑵.&nbsp;&nbsp;查体：15&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①重要阳性体征遗漏……5</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②有关阴性体征遗漏……5</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">③结果不符……5</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑶.&nbsp;&nbsp;诊断：20&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:8px;text-indent:31px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①主要诊断错误……5～10</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②主要诊断有遗漏……5</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">③次要诊断有错误……2</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">④次要诊断有遗漏……2</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑤未提出必要的辅助检查……1</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑷.&nbsp;&nbsp;处理：20&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①治疗原则错误……10～13</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②主要药物用错……4</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">③主要药物剂量错误……2</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">④药名写错……1</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑸.&nbsp;&nbsp;交代病情：6&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①交待诊断不清……2</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②交待注意事项不清……2</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">③传染病的隔离、检疫有遗漏……2</span></p>
                <p style="margin-top:3px;margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">⑹.&nbsp;&nbsp;处方书写：4&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①字迹不清有错别字或涂改……2</span></p>
                <p style="margin-top:3px;margin-left:40px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②不签全名……2</span></p>
                <p style="margin-top:3px;margin-right:60px;margin-left:51px;text-autospace:none;text-align:left;line-height:114%">
                    <span style="font-family:楷体_GB2312;font-size:14px">⑺.&nbsp;</span><span
                        style=";font-size:14px">回答问题：15&nbsp;分&nbsp;</span></p>
                <p style="margin-top:3px;margin-left:8px;text-indent:28px;text-autospace:none;text-align:left;line-height:114%">
                    <span style=";font-size:14px">教师提问与本病诊断、鉴别诊断、治疗原则相关的问题5个，&nbsp;每题&nbsp;3&nbsp;分。</span>
                </p>
                <p style=";margin-left:56px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">要求&nbsp;20～30&nbsp;分钟完成。</span></p>
                <p style=";text-autospace:none;text-align:left;line-height:13px"><span
                        style=";font-size:14px">&nbsp;</span></p>
                <p style=";margin-left:8px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">4.&nbsp;&nbsp;其他：（100&nbsp;分）</span></p>
                <p style="margin-top:3px;margin-left:32px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">①心电图：50&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:56px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">5&nbsp;个典型心电图（各&nbsp;10&nbsp;分）</span></p>
                <p style="margin-top:3px;margin-left:32px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">②X&nbsp;光片：50&nbsp;分</span></p>
                <p style="margin-top:3px;margin-left:56px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">10&nbsp;张典型&nbsp;X&nbsp;光片（每张&nbsp;5&nbsp;分）</span>
                </p>
                <p style="margin-top:3px;margin-left:64px;text-autospace:none;text-align:left"><span
                        style=";font-size:14px">诊断依据占&nbsp;40%，诊断&nbsp;60%，分别在&nbsp;1&nbsp;小时内完成。</span>
                </p>
                <p><br></p></div>
        </td>
    </tr>
</table>