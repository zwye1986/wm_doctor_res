<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
 <html>
 <head>
     <%@include file="/jsp/common/html.jsp" %>
   <title>基地评估指标</title>
<style type="text/css">
	.th_group .left{
		text-align: left;
	}
	.th_group .right{
		text-align: right;
	}
   .th_group th {
		background-color: rgba(0,0,0,0.07);
		border: 1px solid rgba(0,0,0,0.07);
		text-align: center;
		vertical-align:middle;
	}
	.td_group td {
		border: 1px solid rgba(0,0,0,0.07);
		text-align: center;
		vertical-align:middle;
	}
	.td_group th {
		border: 1px solid rgba(0,0,0,0.07);
		text-align: center;
		vertical-align:middle;
	}
	body {
		width:100%;
		overflow:auto;
	}
   </style>
 </head>
 <body >
<div data-role="page" id="pageone">
  <div data-role="header">
	<h1>2016年住院医师规范化培训基地评估指标</h1>
  </div>
  <div data-role="main" class="ui-content">
    <table data-role="table" data-mode=""  class="ui-responsive table-stroke">
       <colgroup>
			<col style="width:8%"/>
			<col style="width:8%"/>
			<col style="width:10%"/>
			<col style="text-align: left;width:30%"/>
			<col style="width:8%"/>
            <col style="width:14%"/>
			<col style="width:4%"/>
			<col style="width:6%"/>
			<col style="width:12%"/>
      </colgroup>
      <thead>
        <tr class="th_group">
          <th colspan="3">评估项目</th>
          <th rowspan="2">评估内容</th>
          <th rowspan="2">现场评估方式</th>
          <th rowspan="2">评分标准</th>
          <th rowspan="2">分值</th>
          <th rowspan="2">得分</th>
          <th rowspan="2">扣分原因</th>
        </tr>
        <tr class="th_group">
          <th>一级指标</th>
          <th>二级指标</th>
          <th>三级指标<br/>★为核心指标
		  </th>
        </tr>
      </thead>
      <tbody  id="dataTable">
        <tr class="td_group">
          <th rowspan="7">1.基本条件（20分）</th>
          <td>1.1医院情况</td>
          <td class="left">1.1.1医院科室设置</td>
          <td class="left">医院等级、科室设置、床位数等符合培训基地认定标准有关要求，其中：<br/>综合医院临床科室至少设有急诊科、内科、外科、妇产科、儿科、全科、中医科、耳鼻喉科、口腔科、眼科、皮肤科、麻醉科、康复医学科、预防保健科；医技科室至少设有药剂科、检验科、放射科、手术室、病理科、输血科、核医学科、消毒供应室、病案室、营养部和相应的临床功能检查室；<br/>承担口腔、儿科、妇产、精神、放射肿瘤等专业培训任务的专科医院，科室设置要符合相关培训基地认定标准的要求。</td>
          <td class="left">查看医院相关材料</td>
          <td class="left">均符合标准，得满分<br/>缺1个科室，扣1分，直至扣完</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][0]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>1.2图书馆及信息检索系统</td>
          <td class="left">1.2.1面向培训对象开放情况</td>
          <td class="left">藏书量（包括电子图书）不低于3000册/百名卫技人员，并对培训对象开放；<br/>信息检索系统，并提供培训对象使用。</td>
          <td class="left">现场考查，查看相关资料</td>
          <td class="left">符合标准，各得1分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][1]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="3">1.3临床能力训练中心</td>
          <td class="left">1.3.1中心面积场地设备★</td>
          <td class="left">建筑面积不低于1200平米（专科医院不低于500平米），训练仪器设备满足培训需求，面向培训对象开放。</td>
          <td class="left" rowspan="3">1.现场考查，查看原始记录<br/>2.访谈2～3名培训对象</td>
          <td class="left" rowspan="2">符合标准，满足培训需求，得满分；不符合标准或未满足需求，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][2]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.3.2中心专职管理人员★</td>
          <td class="left">配备专职人员管理，专职人员数/培训对象数不低于1/200。</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][3]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.3.3课程训练计划</td>
          <td class="left">制定技能操作训练计划，根据课程设计实施训练。</td>
          <td class="left">有计划并实施，得满分；有计划，部分落实，得1分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][4]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>1.4全科医学科<br/>（无全科专业基地除外）</td>
          <td class="left">1.4.1建立全科医学科（门诊、病房、床位数）★</td>
          <td class="left">作为培训基地的综合医院独立设置全科医学科，牵头承担全科住培，与相关临床轮转科室密切协同，指导帮助基层实践基地加强带教能力建设，共同完成好培训任务。</td>
          <td class="left">现场考查，查看相关资料</td>
          <td class="left">全科医学科独立设置、有全科诊疗科目、承担全科培训任务、与相关科室和基层基地密切配合、指导基层基地各占1分</td>
          <td>5<input type="hidden" name="baseScore" value="5"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][5]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>1.5协同医院管理（无协同除外）</td>
          <td class="left">1.5.2协同管理责任</td>
          <td class="left">制定协同单位管理办法，协同医院应为二级甲等及以上；<br/>与协同医院签署协议、明确分工协作责任；<br/>指导帮助协同医院、规范培训实施与管理。</td>
          <td class="left">查看原始资料；访谈有关人员</td>
          <td class="left">1项1分，完成即得分，未完成不得分；</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][6]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <th rowspan="14">2.组织管理（35分）</th>
          <td rowspan="2">2.1组织领导</td>
          <td class="left">2.1.1领导职责、主管部门（是否独立设置）、专人管理（占比）★</td>
          <td class="left">实行院长（一把手）负责制，并切实落实；<br/>明确分管院级领导，并履行分管职责。<br/>独立设置负责住培的职能部门，明确负责人，能与其他相关职能部门密切协同，共同落实好住培管理责任；<br/>有专职管理人员，专职管理人员与培训对象比例≥1:100。</td>
          <td class="left" rowspan="2">查看原始资料，访谈带教医师和培训对象</td>
          <td class="left">有，并落实，得满分<br/>专职人员占比不达标，扣1分</td>
          <td>4<input type="hidden" name="baseScore" value="4"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][7]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.2教学主任★</td>
          <td class="left">专业基地设置专职教学主任和教学秘书岗位，专门负责本专业基地带教工作的组织实施。</td>
          <td class="left">有，并落实，得满分<br/>无，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][8]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="5">2.2制度与落实</td>
          <td class="left">2.2.1招收规定</td>
          <td class="left">制定本基地住培招收等管理办法，向紧缺专业倾斜并认真落实；</td>
          <td class="left" rowspan="3">查看文件及相关资料，访谈有关人员</td>
          <td class="left">有，并落实，得满分<br/>一项未落实，扣1分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][9]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.2.2培训计划★</td>
          <td class="left">参照《住院医师规范化培训内容与标准（试行）》的要求，制定实施系统规范的培训轮转计划，年度安排体现循序渐进要求；落实入院教育；所有缩减培训时间的培训对象都有科学且个性化的培训轮转方案；所有在培学员均填写手机app软件。</td>
          <td class="left">均有，并落实，得满分<br/>无或未落实，不得分</td>
          <td>4<input type="hidden" name="baseScore" value="4"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][10]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.2.3相关制度建设</td>
          <td class="left">有出科考核、年度考核等形成性考核管理办法，并认真落实。有住培经费使用管理办法，并规范实施；<br/>有住培人员管理规定，并认真落实；<br/>有住培师资管理办法，规范师资遴选、培训、考核和激励；</td>
          <td class="left">共4项，1项1分<br/>落实，得1分<br/>未落实无，不得分</td>
          <td>4<input type="hidden" name="baseScore" value="4"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][11]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.2.7沟通反馈机制</td>
          <td class="left">建立顺畅的沟通反馈机制，及时了解培训对象和带教医师的意见建议</td>
          <td class="left" rowspan="2">查看原始资料，访谈培训对象和带教医师</td>
          <td class="left">有，并落实，得1分<br/>无，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][12]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.2.8院级督导★</td>
          <td class="left">建立院级培训督导机制，并认真落实</td>
          <td class="left">有且落实，得满分<br/>未落实，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][13]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="4">2.3激励机制</td>
          <td class="left">2.3.2领导目标考核</td>
          <td class="left">定期召开办公会议，研究讨论住院医师培训工作。</td>
          <td class="left" rowspan="2">查看本年度绩效考核记录、文件等原始资料</td>
          <td class="left">有，并落实，得1分<br/>无，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][14]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.3.3专业基地绩效考核</td>
          <td class="left">将住培过程考核和结业考核结果与科室绩效考核紧密挂钩</td>
          <td class="left">有，并落实，得1分<br/>无，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][15]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.3.4带教活动考核★</td>
          <td class="left">将专业基地负责人、教学主任、教学秘书和带教医师的带教活动纳入个人绩效考核</td>
          <td class="left" rowspan="2">查阅原始资料，访谈带教医师座谈与</td>
          <td class="left">均落实，得满分<br/>有1项未落实，扣1分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][16]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.3.5晋升激励</td>
          <td class="left">将住培工作与专业技术职务晋升、聘用挂钩</td>
          <td class="left">落实，得满分<br/>未落实，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][17]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="3">2.4培训招收</td>
          <td class="left">2.4.1招收与容量（学员和床位数占比）★</td>
          <td class="left">各专业基地实际招收人数不突破核定的培训容量，严格控制非紧缺专业招收规模；<br/>所有在培学员数/医院床位数不超过1/4</td>
          <td class="left">核实各专业基地实际招生情况</td>
          <td class="left">符合要求，得1分，不符合不得分；非紧缺专业每1个专业招收人数超过计划数，从总分扣1分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][18]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.4.2紧缺专业★</td>
          <td class="left">采取有力措施，积极完成当地行政主管部门下达的全科、儿科、精神科等紧缺专业招收计划</td>
          <td class="left">根据招收计划，查看培训对象花名册</td>
          <td class="left">完成招收任务，得满分<br/>每1个专业未完成，扣1分<br/>（综合医院没有紧缺专业基地的此处不得分）</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][19]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.4.3委培和社会化招收★</td>
          <td class="left">不得仅培训本基地医院的住院医师，在培人员中应有相应数量的外单位委培对象或社会化培训对象，并逐年增加；<br/>组织实施社会化学员招录工作。</td>
          <td class="left">查看上年度实际招收对象名单</td>
          <td class="left">均有招收，各得1分<br/>未招收，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][20]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="6">3.培训质量<br/>（20分）</td>
          <td rowspan="2">3.1师资培训</td>
          <td class="left">3.1.1院级培训</td>
          <td class="left">制定实施师资培训制度，所有带教医师均须培训上岗</td>
          <td class="left">查看培训资料、培训名单和证书</td>
          <td class="left">培训率为100%，得满分<br/>培训率≥90%，得1分<br/>培训率＜90%，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][21]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.2省级及以上培训</td>
          <td class="left">每个培训科室均有2名以上带教医师经过省级及以上师资培训</td>
          <td class="left">查看培训名单、证书，核查覆盖率</td>
          <td class="left">符合要求，得2分<br/>超过10%科室未参加，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][22]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="2">3.2师资评价</td>
          <td class="left">3.2.1带教医师评价★</td>
          <td class="left">建立培训对象对带教医师测评机制，并将测评结果纳入带教医师总体评价</td>
          <td class="left">查阅测评原始资料</td>
          <td class="left">有测评机制、合理运用测评结果各1分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][23]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.2.2同等施教★</td>
          <td class="left">带教医师对外单位委派培训对象、面向社会招收的培训对象与本院培训对象一视同仁，使其享受同等教学资源和培训机会</td>
          <td class="left">组织三类人员进行现场访谈</td>
          <td class="left">同等施教，得满分<br/>未同等施教，不得分</td>
          <td>5<input type="hidden" name="baseScore" value="5"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][24]['reason']}</textarea></td>
        </tr>
		    <tr class="td_group">
          <td rowspan="2">3.3培训对象评价</td>
          <td class="left">3.3.1综合评价</td>
          <td class="left">带教医师、科室护士、患者、其他有关专业人员和管理人员对培训对象实施综合评价，有针对性地改进培训带教和有关管理工作</td>
          <td class="left">查阅原始资料</td>
          <td class="left">综合评价方案、组织实施各1分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][25]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.3.2过程考核★</td>
          <td class="left">出科考核、年度考核等严格规范，结果客观公正，考核结果与培训对象奖励性绩效挂钩</td>
          <td class="left">查看过程考核相关原始记录</td>
          <td class="left">记录齐全，组织严格规范，得满分；有记录，但不太规范，得2分；无记录或不规范，不得分</td>
          <td>5<input type="hidden" name="baseScore" value="5"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][26]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <th rowspan="7">4.保障措施（25分）</th>
          <td>4.1专项经费</td>
          <td class="left">4.1.1专账管理（上级资金拨付进度）</td>
          <td class="left">建立住培经费专项账户，规范使用中央、地方财政补助经费，包括中央财政的基地能力建设500万元、中央财政年人均3万元经常性补助以及省级财政补助资金</td>
          <td class="left">查看本年度财务报表等相关资料</td>
          <td class="left">符合要求，得满分<br/>有1项不符合要求，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][27]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>4.2教学经费</td>
          <td class="left">4.2.1教学补助</td>
          <td class="left">落实中央和省级财政专项补助经费用于培训基地教学实践活动，包括带教费、小讲课费等</td>
          <td class="left">查看本年度财务报表，访谈有关人员</td>
          <td class="left">符合要求，专款专用，得满分<br/>不符合要求，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][28]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>4.3培训对象补助</td>
          <td class="left">4.3.1社会招收对象和委派对象补助★（15级学员奖励性绩效平均值）</td>
          <td class="left">积极落实《江苏省住院医师规范化培训学员人事管理若干意见（试行）》有关规定，按照要求考核发放面向社会招收的培训对象和就业单位委派对象工资待遇</td>
          <td class="left">查看财务明细,抽查培训对象收入</td>
          <td class="left">政策执行到位，得满分<br/>未按政策执行，不得分</td>
          <td>8<input type="hidden" name="baseScore" value="8"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][29]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>4.4住宿条件</td>
          <td class="left">4.4.1住宿或住宿补贴</td>
          <td class="left">为培训对象提供免费或低收费住宿，或提供住宿补贴</td>
          <td class="left">现场考查，访谈培训对象</td>
          <td class="left">提供免费或低收费住宿，或提供适当住宿补贴，得满分；无，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][30]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="3">4.5相关措施</td>
          <td class="left">4.5.1签订培训协议</td>
          <td class="left">招收的培训对象与培训基地按规定签订《江苏省住院医师规范化培训学员协议范本》，约定有关事项。培训基地不得聘用培训中和服务期内的非基地单位委派培训对象</td>
          <td class="left">查看相关资料、协议原件，访谈培训对象</td>
          <td class="left">签订协议，并严格落实，得2分<br/>无，或未落实，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][31]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">4.5.2资助参加社会保障</td>
          <td class="left">培训基地资助面向社会招收的培训对象参加社会保险，统一代扣代缴。</td>
          <td class="left">查看社会保障卡号进行核查，访谈培训对象</td>
          <td class="left">有，得满分<br/>无，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][32]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">4.5.3医师资格考试报名和执业注册★</td>
          <td class="left">协助培训对象报名参加医师资格考试；<br/>将取得执业资格的培训对象注册到培训基地。</td>
          <td class="left">查看名单，访谈培训对象</td>
          <td class="left">有，得3分<br/>无，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][33]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td colspan="6" class="right">合计</td>
          <td>100</td>
          <td><span  id="sumScore1">${formDataMap['evalScore1']}</span><input type="hidden" id="inputSumScore1" readonly name="sumScore1" value="${formDataMap['evalScore1']}"/></td>
          <td></td>
        </tr>
        <tr class="td_group">
          <th rowspan="8">5.加分项目<br/>（10分）</th>
          <td>5.1培训合格率</td>
          <td class="left">5.1.1结业考核通过率</td>
          <td class="left">培训对象第1次参加结业考核的通过率<br/>（通过率=当年第1次参加结业考核通过的人数/当年结业总人数）</td>
          <td class="left">查看结业考核成绩记录</td>
          <td class="left">通过率居于各培训基地排名前5位，得1分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][35]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>5.2教学激励</td>
          <td class="left">5.2.1奖励专项（15年奖励额度）</td>
          <td class="left">设置奖励专项，奖励优秀带教医师和参培住院医师</td>
          <td class="left" rowspan="2">查看相关资料，访谈带教医师和培训对象</td>
          <td class="left">有，得1分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][36]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>5.3学员导师</td>
          <td class="left">5.3.1建立导师制</td>
          <td class="left">为每位培训对象配置1名相对固定的带教医师作为导师，负责培训期间的全程指导</td>
          <td class="left">有，得1分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][37]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>5.4教学研究</td>
          <td class="left">5.4.1课题研究</td>
          <td class="left">开展住院医师规范化培训相关课题研究（2016年省卫生计生委课题除外）</td>
          <td class="left">查看相关论文、课题研究报告等</td>
          <td class="left">有，得1分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][38]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>5.5对口支援</td>
          <td class="left">5.5.1省域间</td>
          <td class="left">开展对口支援，派出带教医师，接收新疆西藏地区培训对象</td>
          <td class="left"></td>
          <td class="left">有，得1分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][39]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>5.6招收工作</td>
          <td class="left">5.6.1紧缺专业招收</td>
          <td class="left">创新工作办法，采取有力措施，超额完成全科、儿科、精神科专业招收计划</td>
          <td class="left">查看培训对象花名册</td>
          <td class="left">有，得1分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][40]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>5.7信息化建设</td>
          <td class="left">5.7.1培训管理系统</td>
          <td class="left">有培训基地管理系统，能支撑出科考核、师资学员评价等功能；<br/>技能中心有预约管理系统，统计分析学员培训使用情况。</td>
          <td class="left">现场演示</td>
          <td class="left">有，各得1分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][41]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>5.8双向接轨学位政策</td>
          <td class="left">5.8.1在培学员申请学位政策</td>
          <td class="left">在培学员可以向有关高校申请专业硕士研究生学位。</td>
          <td class="left">查看文件</td>
          <td class="left">有文件，得1分<br/>有学员已获得学位，得1分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][42]['reason']}</textarea></td>
        </tr>
      </tbody>
      <tbody>
        <tr class="td_group">
          <td colspan="6" class="right">合计</td>
          <td>10</td>
          <td><span  id="sumScore2">${formDataMap['evalScore2']}</span><input type="hidden" id="inputSumScore2" readonly name="sumScore2" value="${formDataMap['evalScore2']}"/></td>
          <td></td>
        </tr>
        <tr class="td_group">
          <td colspan="6" class="right">总计</td>
          <td>110</td>
          <td><span  id="sumScore">${formDataMap['evalScore']}</span><input type="hidden" id="inputSumScore" readonly name="sumScore" value="${formDataMap['evalScore']}"/></td>
          <td></td>
        </tr>
      </tbody>
    </table>
  <script>
  
    $(document).ready(function(){
      $(".left").css("text-align","left");
      $(".right").css("text-align","right");
      $("input").attr("readonly","readonly");
      $("textarea").attr("disabled","disabled");
     // $("textarea").css("height","200px");
            <c:if test="${not empty formDataMap['scores']}">
                $("#dataTable tr").each(function(i){
                    <c:forEach items="${formDataMap['scores']}" var="score">
                    if("${score['ordinal']}"==(i+1))
                    {
                        //$(this).find("textarea[name='reason']").val("${score['reason']}");
                        $(this).find("input[name='score']").val("${score['score']}");
                    }
                    </c:forEach>
                });
            </c:if>
            $("#dataTable tr").each(function(i){
                if($(this).find("textarea[name='reason']").val()) {
                    //autoTextarea($(this).find("textarea[name='reason']")[0]);
                }
            });
    });
  </script>
 
  </div>
    <div data-role="popup" id="myPopup" class="ui-content">
       <p id="errorMsg"></p>
    </div>
  
 <div data-role="footer">
 <h1></h1>
 </div>
</div> 
 </body>
 </html>