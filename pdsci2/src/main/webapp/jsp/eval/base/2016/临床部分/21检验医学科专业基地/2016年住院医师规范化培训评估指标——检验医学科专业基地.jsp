<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
 <html>
 <head>
     <%@include file="/jsp/common/html.jsp" %>
   <title>检验医学科专业基地</title>
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
  <h1>2016年住院医师规范化培训评估指标——检验医学科专业基地</h1>
  
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
          <th rowspan="7">1.基本条件（20分）</th>
          <td rowspan="7">1.1专业基地所在医院条件</td>
          <td class="left">1.1.1日标本数</td>
          <td class="left">日检验标本总数≥1000例</td>
          <td class="left">符合标准，得满分<br/>不达标准，不得分</td>
          <td>4<input type="hidden" name="baseScore" value="4"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][0]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.2专业设置</td>
          <td class="left">临床体液、血液学专业<br/>临床微生物学专业 <br/>临床化学专业 <br/>临床免疫学专业 <br/>临床分子生物学专业</td>
          <td class="left">专业设置齐全，得满分<br/>缺1个专业，扣1分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][1]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.3轮转科室</td>
          <td class="left">临床科室：心血管内科、肾内科、呼吸内科、消化内科、内分泌科、血液科<br/>专业科室：临床体液血液检验、临床免疫学检验、临床化学检验、临床微生物学检验、临床分子生物学检验</td>
          <td class="left">科室齐全，得满分<br/>缺1个科室，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][2]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.4疾病种类及数量★</td>
          <td class="left" rowspan="2">符合《住院医师规范化培训基地认定标准（试行）》和《住院医师规范化培训内容与标准（试行）》检验医学科专业细则要求，详见附表1 </td>
          <td class="left">符合要求（含协同单位），得满分<br/>疾病种类及数量≥规定数的90%，得2分<br/>疾病种类及数量≥规定数的85%，得1分<br/>疾病种类及数量＜规定数的85%，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][3]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.5技能操作种类及数量★</td>
          <td class="left">符合要求（含协同单位），得满分<br/>技能操作种类及数量≥规定数的90%，得2分<br/>技能操作种类及数量≥规定数的85%，得1分<br/>技能操作种类及数量＜规定数的85%，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][4]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.6实验室信息管理系统</td>
          <td class="left">有完善的实验室信息管理系统，患者、分析系统和标本检测、质量控制、结果报告等信息进行统一管理；LIS和HIS联网</td>
          <td class="left">有管理系统，并日常规范使用，得1分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][5]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.7专业基地设备</td>
          <td class="left">1.共用设备：离心机、冰箱、低温冰箱、生物安全柜、带摄像头光学生物显微镜<br/>2.临床体液、血液检验：全自动血细胞分析仪（五分类）、尿液干化学分析仪、尿液形态学分析仪、全自动凝血分析仪<br/>3.临床微生物学检验：血培养仪、细菌药敏试验及鉴定仪、二氧化碳培养箱、厌氧培养装置<br/>4.临床化学检验：全自动生化分析（含电解质测定）仪、蛋白电泳仪<br/>5.临床免疫检验：酶标仪、洗板机、发光免疫分析仪、荧光显微镜<br/>6.临床分子生物学检验：PCR仪、质谱仪、流式细胞仪</td>
          <td class="left">缺1个，扣0.5分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][6]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <th rowspan="7">2.师资条件（15分）</th>
          <td rowspan="4">2.1师资情况</td>
          <td class="left">2.1.1带教医师与培训对象比例★</td>
          <td class="left">每名带教医师同时带教本专业培训对象不超过2名</td>
          <td class="left">不达标准，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][7]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.2带教医师条件</td>
          <td class="left">具有主治医师、副主任医师或主任医师专业技术职务，从事检验医学科工作5年以上</td>
          <td class="left">其中1名带教医师不符合要求，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][8]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.3带教医师组成</td>
          <td class="left">各亚专业（专科）至少有1名检验医学专业副高及以上专业技术职务带教医师</td>
          <td class="left">1个亚专业（专科）不达标，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][9]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.4专业基地负责人条件</td>
          <td class="left">具有检验医学专业主任医师或教授专业技术职务, 从事检验医学科工作10年以上</td>
          <td class="left">1项不符合条件，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][10]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="3">2.2师资建设</td>
          <td class="left">2.2.1师资培训★</td>
          <td class="left">带教医师均参加过院级师资培训<br/>各亚专业（专科）至少1名带教医师参加过省级及以上师资培训</td>
          <td class="left">3项培训均满足，得满分<br/>1项满足，得1分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][11]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.2.2师资评价★</td>
          <td class="left">每年至少组织1次对带教医师教学工作进行评价</td>
          <td class="left">有评价方案，原始记录详实，得满分<br/>有评价记录无方案，得2分<br/>有方案无记录，得1分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][12]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">2.2.3激励制度★</td>
          <td class="left">建立带教医师激励机制，将教学工作与绩效考评、奖金、评优等挂钩</td>
          <td class="left">有激励机制，并与奖金、评优挂钩，得满分<br/>有激励机制，未与奖金、评优挂钩，得1分<br/>无，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][13]['reason']}</textarea></td>
        </tr>
    
    
        <tr class="td_group">
          <th rowspan="12">3.过程管理（30分）</th>
          <td rowspan="6">3.1培训制度与落实</td>
          <td class="left">3.1.1主任职责</td>
          <td class="left">实行专业基地负责人负责制，并切实落实</td>
          <td class="left">职责明确，履职认真，得1分<br/>无岗位职责，或履职不认真，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][14]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.2教学主任</td>
          <td class="left">设置专职教学主任岗位，专门负责本专业基地教学工作的组织实施</td>
          <td class="left">职责明确，履职认真，得1分<br/>无岗位职责，或履职不认真，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][15]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.3教学秘书</td>
          <td class="left">设置专职教学秘书岗位，落实本专业基地教学工作</td>
          <td class="left">有教学秘书，履职认真，得1分<br/>无，或履职不认真，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][16]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.4教学小组</td>
          <td class="left">成立教学小组，明确小组职责，定期组织研究教学工作</td>
          <td class="left">有教学小组，履职认真，得1分<br/>无，或履职不认真，不得分</td>
          <td>1<input type="hidden" name="baseScore" value="1"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][17]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.5轮转计划★</td>
          <td class="left">按规定落实轮转计划和要求</td>
          <td class="left">有，且严格落实，得满分<br/>未严格落实，不得分</td>
          <td>3<input type="hidden" name="baseScore" value="3"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][18]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.6考勤制度</td>
          <td class="left">有考勤规章制度，有专人负责，并严格执行</td>
          <td class="left">有，且严格落实，得满分<br/>未严格落实，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][19]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="4">3.2培训活动</td>
          <td class="left">3.2.1入科教育</td>
          <td class="left">规范实施，包括科室情况、科室纪律、培养计划与要求、医德医风、医患沟通等入科教育，并有专人组织实施</td>
          <td class="left">有，且严格落实，得满分<br/>未严格落实，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][20]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.2.2教学查房或临床巡诊</td>
          <td class="left">开展规范的教学查房或临床巡诊和临床讲座，至少每周1次</td>
          <td class="left">开展次数达标，得满分<br/>未开展或不达标，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][21]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.2.3小讲课</td>
          <td class="left">开展规范的小讲课活动，至少每周1次</td>
          <td class="left">开展次数达标，得满分<br/>未开展或不达标，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][22]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">3.2.4疑难病例讨论</td>
          <td class="left">开展规范的疑难病例讨论，至少2周1次</td>
          <td class="left">开展次数达标，得满分<br/>未开展或不达标，不得分</td>
          <td>2<input type="hidden" name="baseScore" value="2"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][23]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>3.3过程考核</td>
          <td class="left">3.3.1出科考核★</td>
          <td class="left">理论考试试题、技能操作考核评分标准、培训对象测评结果、考勤记录等原始资料齐全，真实规范施</td>
          <td class="left">考核项目全面，且认真规范，得满分<br/>仅有技能操作考核，得4分<br/>仅有理论考试，得2分<br/>仅有测评结果和考勤记录，得1分</td>
          <td>7<input type="hidden" name="baseScore" value="7"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][24]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td>3.4培训强度</td>
          <td class="left">3.4.1培训工作量★</td>
          <td class="left">按照专业基地培训对象临床操作能够达到《住院医师规范化培训内容与标准（试行）》检验医学科专业细则的要求</td>
          <td class="left">管床数或完成检查项目达要求，得满分<br/>管床数或完成检查项目≥规定数的80%，得3分<br/>管床数或完成检查项目≥规定数的60%，得1分<br/>未安排管床或未独立完成检查，不得分</td>
          <td>6<input type="hidden" name="baseScore" value="6"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][25]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <th rowspan="6">4.质量控制（35分）</th>
          <td rowspan="3">4.1带教医师教学质量</td>
          <td class="left">4.1.1查房或临床巡诊质量★</td>
          <td class="left">主任或带教医师组织开展规范的教学查房或临床巡诊，悉心指导培训对象</td>
          <td class="left">现场评价满意，得满分<br/>现场评价较好，得5分<br/>现场评价一般，得3分<br/>现场评价较差，不得分</td>
          <td>7<input type="hidden" name="baseScore" value="7"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][26]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">4.1.2技能操作安排情况★</td>
          <td class="left">每个轮转科室均按照《住院医师规范化培训内容与标准（试行）》本专业培训细则要求执行，为每名培训对象安排并完成规定的技能操作</td>
          <td class="left">满意率≥90%，得满分<br/>满意率≥80%，得2分<br/>满意率＜80%，不得分</td>
          <td>4<input type="hidden" name="baseScore" value="4"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][27]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">4.1.3技能操作带教情况★</td>
          <td class="left">带教医师协助并指导培训对象完成技能操作，带教严格规范</td>
          <td class="left">1.培训对象操作前是否与患者交流、沟通情况 1分<br/>2.培训对象操作中存在问题及时进行指导  1分<br/>3.培训对象操作结束后是否进行提问   1分<br/>4.对培训对象的操作的总体评价（优、缺点点评） 2分</td>
          <td>4<input type="hidden" name="baseScore" value="4"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][28]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td rowspan="3">4.2培训对象学习效果</td>
          <td class="left">4.2.1病历书写或分析报告★</td>
          <td class="left">培训对象病历或分析报告书写规范</td>
          <td class="left">病历书写评分表见附表2 <br/>≥90分得满分，≥80分得4分，≥70分得2分，≥60分得1分，＜60分不得分</td>
          <td>6<input type="hidden" name="baseScore" value="6"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][29]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">4.2.2技能操作★</td>
          <td class="left">培训对象技能操作情况</td>
          <td>技能操作评分表见附表3<br/>≥90分得满分，≥80分得4分，≥70分得2分，≥60分得1分，＜60分不得分</td>
          <td>6<input type="hidden" name="baseScore" value="6"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][30]['reason']}</textarea></td>
        </tr>
        <tr class="td_group">
          <td class="left">4.2.3完成培训内容与要求★</td>
          <td class="left">按照本专业《住院医师规范化培训内容与标准（试行）》细则，核实培训内容的完成情况</td>
          <td class="left">完成率≥90%，得满分<br/>完成率≥85%，得5分<br/>完成率≥80%，得3分<br/>完成率＜80%，不得分</td>
          <td>8<input type="hidden" name="baseScore" value="8"/></td>
          <td><input type="text" name="score" value=""/></td>
          <td><textarea name="reason">${formDataMap['scores'][31]['reason']}</textarea></td>
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
      1.一级指标4项，二级指标10项，三级指标37项。三级指标中，核心指标15项、计67分，一般指标22项、计33分，共100分。<br/>
      2.随机抽查对象优先选择委托培训对象和面向社会招收的培训对象，如果没有，可考虑本基地培训对象。<br/>
      3.现场评估时详细填写存在的问题和扣分原因。
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