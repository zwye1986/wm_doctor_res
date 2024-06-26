<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
 <html>
 <head>
     <%@include file="/jsp/common/html.jsp" %>
   <title>2016年住院医师规范化培训评估指标——外科专业基地</title>
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
	<h1>2016年住院医师规范化培训评估指标——外科专业基地</h1>
  </div>
  <div data-role="main" class="ui-content">
    <table data-role="table" data-mode=""  class="ui-responsive table-stroke">
       <colgroup>
			<col style="width:10%"/>
			<col style="width:10%"/>
			<col style="width:12%"/>
			<col style="text-align: left;width:32%"/>
			<col style="width:14%"/>
			<col style="width:5%"/>
			<col style="width:5%"/>
			<col style="width:12%"/>
      </colgroup>
      <thead>
        <tr class="th_group">
          <th colspan="3">评估项目</th>
          <th rowspan="2">评估内容</th>
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
          <th rowspan="13">1.基本条件（20分）</th>
          <td rowspan="10">1.1专业基地所在医院条件</td>
          <td class="left">1.1.1总床位数</td>
          <td class="left">三级甲等综合医院外科床位总数≥200张</td>
          <td class="left">符合标准，得满分<br/>不达标准，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][0]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.2年收治病人数</td>
          <td class="left">≥3400人次</td>
          <td class="left">符合标准，得1分<br/>不达标准，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][1]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.3年门诊量</td>
          <td class="left">≥95000人次</td>
          <td class="left">符合标准，得1分<br/>不达标准，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][2]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.4年急诊量</td>
          <td class="left">≥9500人次</td>
          <td class="left">符合标准，得1分<br/>不达标准，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][3]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.5科室和实验室</td>
          <td class="left">必备科室:普通外科、骨科、泌尿外科、胸心外科、麻醉科、外科重症监护治疗室(SICU)、神经外科、医院门诊、急诊科、内科、中心手术室<br/>辅助科室：放射科、超声科、核医学科、病理科、检验医学科、输血科，外科实验室</td>
          <td class="left">必备科室缺1个科室，不得分<br/>辅助科室缺2个科室，不得分  </td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][4]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.6轮转科室</td>
          <td class="left">普通外科(包括门、急诊)、骨科(包括门、急诊)、泌尿外科、胸心外科、 神经外科、 麻醉科、外科重症监护室</td>
          <td class="left">科室齐全，得满分<br/>缺1个科室，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][5]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.7疾病种类及数量★</td>
          <td class="left" rowspan="2">符合《住院医师规范化培训基地认定标准（试行）》和《住院医师规范化培训内容与标准（试行）》外科专业细则要求，详见附表1-1至1-6    </td>
          <td class="left">符合要求（含协同单位），得满分<br/>疾病种类及数量≥规定数的90%，得2分<br/>疾病种类及数量≥规定数的85%，得1分<br/>疾病种类及数量＜规定数的85%，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][6]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.8临床技能操作种类及数量★</td>
          <td class="left">符合要求（含协同单位），得满分<br/>技能操作种类及数量≥规定数的90%，得2分<br/>技能操作种类及数量≥规定数的85%，得1分<br/>技能操作种类及数量＜规定数的85%，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][7]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.9医院设备</td>
		  <td class="left">大型X射线机、 彩色超声仪、 CT、  MRI、 骨密度测定仪、核素扫描仪、肌电图、脑电图仪、动态心电图仪、临床技能模拟培训系统</td>
		  <td class="left">缺1项，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][8]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.10专业基地设备</td>
		  <td class="left">1.纤维胃镜、结肠镜、肝脏介入治疗设备、腹腔镜、胆道镜等<br/>
2.手术显微镜、关节镜、 C形臂<br/>
3.支气管镜、胸腔镜、多导监护仪<br/>
4.膀胱镜、电切镜、输尿管镜、经皮肾镜<br/>
5.脑电图仪、层流手术间、神经外科手术用显微镜<br/>
6.血液、生化、免疫、尿液检验设备<br/>
7.氧饱和度监测仪、肺功能仪、呼吸机、指测血糖仪、输液泵、微量泵<br/>
8.体外循环机<br/>
9.麻醉机</td>
		  <td class="left">缺1项，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][9]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td  rowspan="3">1.2协同单位</td>
          <td class="left">1.2.1协同数</td>
          <td class="left">协同数量不应超过3个</td>
          <td class="left">满足要求，得1分（无协同单位的专业基地，此处不失分）</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][10]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.2.2协同床位数</td>
          <td class="left">各亚专业(专科)床位数(参照《住院医师规范化培训基地认证标准（试行）》康复科细则要求)</td>
          <td class="left">满足要求，得1分（无协同单位的专业基地，此处不失分）</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][11]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.2.3轮转时间</td>
          <td class="left">在协同亚专业(专科)轮转时间不超过3个月</td>
          <td class="left">满足要求，得1分（无协同单位的专业基地，此处不失分）</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][12]['reason']}</textarea></td>
        </tr>
		
        <tr class="td_group">
          <th rowspan="7">2.师资条件（15分）</th>
          <td rowspan="4">2.1师资情况</td>
          <td class="left">2.1.1带教医师与培训对象比例★</td>
          <td class="left">每名带教医师同时带教本专业培训对象不超过3名 </td>
          <td class="left">不达标准，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][13]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.2带教医师条件</td>
          <td class="left">医学本科及以上学历,主治医师专业技术职务3年以上，从事外科专业工作5年以上</td>
          <td class="left">其中1名带教医师不符合要求，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][14]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.3带教医师组成</td>
          <td class="left">各亚专业（专科）有主任医师≥1人，副主任医师≥1人，主治医师≥2人</td>
          <td class="left">1个亚专业（专科）不达标，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][15]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.4专业基地负责人条件</td>
          <td class="left">医学本科及以上学历，主任医师专业技术职务，从事外科专业的医疗、科研和教学工作超过15年</td>
          <td class="left">1项不符合条件，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][16]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="3">2.2师资建设</td>
          <td class="left">2.2.1师资培训★</td>
          <td class="left">带教医师均参加过院级师资培训<br/>各亚专业（专科）至少1名带教医师参加过省级及以上师资培训</td>
          <td class="left">2项培训均满足，得满分<br/>1项满足，得1分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][17]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.2.2师资评价★</td>
          <td class="left">每年度至少组织1次对带教医师教学工作进行评价</td>
          <td class="left">有评价方案，原始记录详实，得满分<br/>有评价记录无方案，得2分<br/>有方案无记录，得1分<br/>无，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][18]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.2.3激励制度★</td>
          <td class="left">建立带教医师激励机制，将教学工作与绩效考评、奖金、评优等挂钩</td>
          <td class="left">有激励机制，并与奖金、评优挂钩，得满分<br/>有激励机制，未与奖金、评优挂钩，得1分<br/>无，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][19]['reason']}</textarea></td>
        </tr>
		
		
        <tr class="td_group">
          <th rowspan="13">3.过程管理（30分）</th>
          <td rowspan="6">3.1培训制度与落实</td>
          <td class="left">3.1.1主任职责</td>
          <td class="left">实行专业基地负责人负责制，并切实落实</td>
          <td class="left">职责明确，履职认真，得1分<br/>无岗位职责，或履职不认真，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][20]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.2教学主任</td>
          <td class="left">设置专职教学主任岗位，专门负责本专业基地教学工作的组织实施</td>
          <td class="left">职责明确，履职认真，得1分<br/>无岗位职责，或履职不认真，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][21]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.3教学秘书</td>
          <td class="left">设置专职教学秘书岗位，落实本专业基地教学工作</td>
          <td class="left">有教学秘书，履职认真，得1分<br/>无，或履职不认真，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][22]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.4教学小组</td>
          <td class="left">成立教学小组，明确小组职责，定期组织研究教学工作</td>
          <td class="left">有教学小组，履职认真，得1分<br/>无，或履职不认真，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][23]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.5轮转计划★</td>
          <td class="left">按规定落实轮转计划和要求</td>
          <td class="left">有，且严格落实，得满分<br/>未严格落实，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][24]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.6考勤制度</td>
          <td class="left">有考勤规章制度，有专人负责，并严格执行</td>
          <td class="left">有，且严格落实，得满分<br/>未严格落实，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][25]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="4">3.2培训活动</td>
          <td class="left">3.2.1入科教育</td>
          <td class="left">规范实施，包括科室情况、科室纪律、培养计划与要求、医德医风、医患沟通等入科教育，并有专人组织实施</td>
          <td class="left">有，且严格落实，得满分<br/>未严格落实，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][26]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.2.2教学查房</td>
          <td class="left">开展规范的教学查房，至少每周1次</td>
          <td class="left">开展次数达标，且认真规范，得满分<br/>未达标或不规范，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][27]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.2.3小讲课</td>
          <td class="left">开展规范的小讲课活动，至少每周1次</td>
          <td class="left">开展次数达标，且认真规范，得满分<br/>未达标或不规范，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][28]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.2.4疑难病例讨论</td>
          <td class="left">开展规范的疑难病例讨论，至少2周1次</td>
          <td class="left">开展病例讨论，得满分<br/>未达标或不规范，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][29]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>3.3过程考核</td>
          <td class="left">3.3.1出科考核★</td>
          <td class="left">理论考核(如临床病例分析)试题、技能操作考核评分标准、培训对象测评结果、考勤记录等原始资料齐全，真实规范</td>
          <td class="left">考核项目全面，且认真规范，得满分<br/>仅有技能操作考核，得4分<br/>仅有理论考试，得2分<br/>仅有测评结果和考勤记录，得1分<br/>无学员，不得分</td>
          <td>7<input type="hidden" name="baseScore" value="7"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][30]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="2">3.4培训强度</td>
          <td class="left">3.4.1管理病床数★</td>
          <td class="left">病房：日管床数≥5张（在带教医师指导下独立于其他培训对象管理床位）</td>
          <td class="left">管床数达到要求，得满分<br/>管床数≥3张，得2分<br/>管床数≥2张，得1分<br/>管床数＜2张，或未安排，不得分<br/>无学员，不得分</td>
          <td>4<input type="hidden" name="baseScore" value="4"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][31]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.4.2门急诊工作量</td>
          <td class="left">门诊：日诊治数≥20人次<br/>急诊：日急诊数≥10人次</td>
          <td class="left">门急诊工作量达到要求，得满分<br/>
门急诊量≥规定数的80%，得2分<br/>
门急诊量≥规定数的60%，得1分 <br/>
门急诊量＜规定数60%，或未安排，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][32]['reason']}</textarea></td>
        </tr>
		
		
        <tr class="td_group">
          <th rowspan="6">4.质量控制（35分）</th>
          <td rowspan="3">4.1带教医师教学质量</td>
          <td class="left">4.1.1查房质量★</td>
          <td class="left">主任或带教医师组织规范的教学查房，悉心指导培训对象</td>
          <td class="left">教学查房评分表见附表2<br/>≥90分得满分，≥80分得4分，≥70分得2分，≥60分得1分，＜60分不得分 </td>
          <td>6<input type="hidden" name="baseScore" value="6"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][33]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">4.1.2技能操作安排情况★</td>
          <td class="left">每个轮转科室均能按照《住院医师规范化培训内容与标准（试行）》本专业细则要求执行，为每名培训对象安排并完成规定的技能操作种类和数量</td>
          <td class="left">满意率≥90%，得满分<br/>满意率≥80%，得2分<br/>满意率＜80%，不得分</td>
          <td>4<input type="hidden" name="baseScore" value="4"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][34]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">4.1.3技能操作带教情况★</td>
          <td class="left">带教医师协助并指导培训对象完成技能操作，带教严格规范</td>
          <td class="left">1.培训对象操作前是否与患者交流、沟通情况 1分<br/>
2.培训对象操作中存在问题及时进行指导  1分<br/>
3.培训对象操作结束后是否进行提问   1分<br/>
4.对培训对象的操作的总体评价（优、缺点点评） 2分</td>
          <td>5<input type="hidden" name="baseScore" value="5"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][35]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="3">4.2培训对象学习效果</td>
          <td class="left">4.2.1病历书写★</td>
          <td class="left">培训对象病历书写规范</td>
          <td class="left">病历书写评分表见附表2<br/>≥90分得满分，≥80分得3分，≥70分得2分，≥60分得1分，＜60分不得分 </td>
          <td>5<input type="hidden" name="baseScore" value="5"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][36]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">4.2.2技能操作★</td>
          <td class="left">培训对象技能操作情况</td>
          <td class="left">技能操作评分表见附表3<br/>≥90分得满分，≥80分得5分，≥70分得2分，≥60分得1分，＜60分不得分 </td>
          <td>7<input type="hidden" name="baseScore" value="7"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][37]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">4.2.3完成培训内容与要求★</td>
          <td class="left">按照本专业《住院医师规范化培训内容与标准（试行）》细则，核实培训内容的完成情况</td>
          <td class="left">完成率≥90%，得满分<br/>完成率≥85%，得5分<br/>完成率≥80%，得3分<br/>完成率＜80%，不得分<br/>无学员，不得分</td>
          <td>8<input type="hidden" name="baseScore" value="8"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][38]['reason']}</textarea></td>
        </tr>
      </tbody>
      <tbody>
        <tr class="td_group">
          <td colspan="5" class="right">合计</td>
          <td>100</td>
          <td><span  id="sumScore">${formDataMap['evalScore']}</span><input type="hidden" id="inputSumScore" readonly name="sumScore" value="${formDataMap['evalScore']}"/></td>
          <td></td>
        </tr>
        <tr class="td_group">
          <td class="right" colspan="2">存在问题</td>
          <td colspan="7" >
		  <textarea name="problems" placeholder="" style="width:100%;height:100px;resize:none;" >${formDataMap['problems']}</textarea>
		  </td>
        </tr>
        <tr class="td_group">
          <td colspan="8" class="left">
		  备注：<br/>
			1.一级指标4项，二级指标10项，三级指标37项。三级指标中，核心指标15项、计69分，一般指标22项、计31分，共100分。<br/>
2.指标中所有规章制度，专指住院医师规范化培训相关制度。<br/>
3.随机抽查对象优先选择委托培训对象和面向社会招收的培训对象，如果没有，可考虑本基地培训对象。<br/>
4.现场评估时详细填写存在的问题和扣分原因。
		  </td>
        </tr>
        <tr class="td_group">
		  <td class="right" colspan="2">
			专家姓名：
		  </td>
		  <td>
			<input type="text" name="siginName" value="${formDataMap['siginName']}"/>
		  </td>
          <td>
			
		  </td>
		  <td class="right" colspan="2">
			评估日期：
		  </td>
          <td colspan="2">
			<input type="text" id="siginDate" name="siginDate" value="${formDataMap['siginDate']}" readonly/>
		  </td>
		</tr>
      </tbody>
    </table>
	<script>
	
		$(document).ready(function(){
		$(".left").css("text-align","left");
		$(".right").css("text-align","right");$("input").attr("readonly","readonly");$("textarea").attr("disabled","disabled");$("textarea[name!='reason']").css("height","200px");
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
                   // autoTextarea($(this).find("textarea[name='reason']")[0]);
                }
            });
		});
		function sumScore()
		{
			var allScore=0;
			$("input[name='score']").each(function(){
				var score=$(this).val();
				if(score!="")
				{
					allScore+=parseInt(score);
				}else{
					allScore+=0;
				}
			});
			$("#sumScore").html(allScore);
			$("#inputSumScore").val(allScore);
		}
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