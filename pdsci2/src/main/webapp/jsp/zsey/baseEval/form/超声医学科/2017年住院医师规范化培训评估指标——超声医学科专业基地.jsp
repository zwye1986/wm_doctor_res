
 <html>
 <head>

<style type="text/css">
	.td_group .left{
		text-align: left;
	}
	.td_group .right{
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
    .td_group textarea:not(.problems) {
      height: 25px;
      line-height: 25px;
      padding: 0 3px;
      border: 1px solid #bdbebe;
      background: white;
      font-family: "Microsoft Yahei", Helvetica, sans-serif;
      width: 142px;
    }
    .td_group input[name='score']:not(#signDate){
      height: 25px;
      line-height: 25px;
      padding: 0 3px;
      border: 1px solid #bdbebe;
      background: white;
      font-family: "Microsoft Yahei", Helvetica, sans-serif;
      width: 30px;
    }
   </style>
   <script>
     $(function(){
//       $(".td_group textarea").css("height","25px").css("background","white");
//       $(".td_group input[name='score']:not(#signDate)").css("width","30px").css("background","white");
       $(".td_group textarea").css("height","25px")
       $(".problems").css("height","");
       $("input[name='score']:not(#signDate,#inputSumScore)").each(function(){
         $(this).attr("onblur","checkPoint(this)");
       });
       if('${param.viewFlag}'=='Y'){
         $(".td_group textarea").attr("disabled",true);
         $(".td_group input").attr("disabled",true);
         $(".td_group").each(function(){
           $(this).find("a").last().hide().prev().hide();
         })
       }
     });
     function sumScore()
     {
       var allScore=0;
       $("input[name='score']").each(function(){
         if($(this).attr("id")!='signDate' && $(this).attr("id")!='inputSumScore'){
           var score=$(this).val();
           if(!isNaN(score)&&score!="")
           {
             allScore+=parseFloat(score);
           }else{
             allScore+=0;
           }
         }
       });
       $("#inputSumScore").val(allScore);
     }
     function checkPoint(obj){
       var maxPonit = $(obj).parent().prev().prev().text();
       if(parseFloat(maxPonit)<parseFloat($(obj).val())){
         $(obj).val("");
         jboxTip("分数不能超过最大值");
         setTimeout(function(){
           $(obj).focus();
         },100);
       }else if(isNaN($(obj).val())){
         $(obj).val("");
         jboxTip("请输入数字");
         setTimeout(function(){
           $(obj).focus();
         },100);
       }else {
         sumScore();
       }
     }
   </script>
 </head>
 <body >
 <input type="hidden" id="formFlow" name="formFlow" value="${formFlow}">
 <input type="hidden" id="recordFlow" name="recordFlow" value="${baseeval.recordFlow}">
<div data-role="page" id="pageone">
  <div data-role="header">
	<h1 style="text-align: center;font-size: 25px;">2017年住院医师规范化培训评估指标——超声医学科专业基地</h1>
    1、一级指标分为基本条件、师资条件、过程管理、培训质量等4个方面；二级指标10项；三级指标34项，其中核心指标14项，共100分。<br/>
    2、基本条件和师资条件中核心指标6项，其中有一项不达标，限期整改或者减少招收人数（下一年不得招收学员）；过程管理和培训质量三部分核心指标共10项，其中3项不达标限期整改，5项及以上不达标取消专业基地资格。<br/>
    3、现场评估详细填写存在的问题和扣分原因。
  </div>
  <div data-role="main" class="ui-content">
    <table data-role="table" data-mode=""  class="ui-responsive table-stroke">
       <colgroup>
         <col style="width:7%"/>
         <col style="width:7%"/>
         <col style="width:7%"/>
         <col style="text-align: left;width:17%"/>
         <col style="width:17%"/>
         <col style="width:20%"/>
         <col style="width:5%"/>
         <col style="width:5%"/>
         <col style="width:7%"/>
         <col style="width:10%;"/>
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
          <th rowspan="2">附件材料</th>
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
          <th rowspan="11">1.基本条件（20分）</th>
          <td rowspan="7">1.1医院和超声科条件</td>
          <td class="left">1.1.1医院日门诊量</td>
          <td class="left">≥2000人次</td>
          <td class="left" rowspan="3">检查相关统计报表复印件，需加盖医院公章</td>
          <td class="left">符合标准，得1分<br/>不达标准，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.1.1"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks">${marksMap["1.1.1"]}</textarea></td>
          <td>
            <span id="1.1.1Span" style="display:${empty fileMap["1.1.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.1Up" href="javascript:uploadFile('1.1.1');" style="color:blue">${empty fileMap["1.1.1"]?'':'重新'}上传</a>
            <a id="1.1.1Del" href="javascript:delFile('1.1.1');"  style="color:blue;${empty fileMap["1.1.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.1Value" name="filePath" value="${fileMap["1.1.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.2医院日急诊量</td>
          <td class="left">≥100人次</td>
          <td class="left">符合标准，得1分<br/>不达标准，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.1.2"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.1.2"]}</textarea></td>
          <td>
            <span id="1.1.2Span" style="display:${empty fileMap["1.1.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.2Up" href="javascript:uploadFile('1.1.2');" style="color:blue">${empty fileMap["1.1.2"]?'':'重新'}上传</a>
            <a id="1.1.2Del" href="javascript:delFile('1.1.2');"  style="color:blue;${empty fileMap["1.1.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.2Value" name="filePath" value="${fileMap["1.1.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.3科室日检查例数</td>
          <td class="left">腹部≥70人次，心脏≥50人次，妇产≥60人次，血管≥30人次，浅表器官≥40人次，介入≥2人次（日均），其他（床旁、急症、胸部、术中、院内）≥50人次  </td>
          <td class="left">符合标准，得2分<br/>不达标准，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="1.1.3"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.1.3"]}</textarea></td>
          <td>
            <span id="1.1.3Span" style="display:${empty fileMap["1.1.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.3Up" href="javascript:uploadFile('1.1.3');" style="color:blue">${empty fileMap["1.1.3"]?'':'重新'}上传</a>
            <a id="1.1.3Del" href="javascript:delFile('1.1.3');"  style="color:blue;${empty fileMap["1.1.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.3Value" name="filePath" value="${fileMap["1.1.3"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.4超声科亚专业设置★</td>
          <td class="left">腹部、浅表器官、血管、妇产、心脏、介入等6个超声亚专业</td>
          <td class="left">1.查看PACS和/或His系统或其他登记系统<br/>2.实地检查<br/>3.访谈学员（回避制）</td>
          <td class="left">6个亚专业，得满分<br/>
            5个亚专业，得2分<br/>
            4个亚专业，得1分<br/>
            ＜4个亚专业，不得分</td>
          <td>5</td>
          <input type="hidden" name="orderNumber" value="1.1.4"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.4"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.1.4"]}</textarea></td>
          <td>
            <span id="1.1.4Span" style="display:${empty fileMap["1.1.4"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.4"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.4Up" href="javascript:uploadFile('1.1.4');" style="color:blue">${empty fileMap["1.1.4"]?'':'重新'}上传</a>
            <a id="1.1.4Del" href="javascript:delFile('1.1.4');"  style="color:blue;${empty fileMap["1.1.4"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.4Value" name="filePath" value="${fileMap["1.1.4"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.5疾病种类及数量★</td>
          <td class="left" rowspan="2">符合《住院医师规范化培训基地认定标准（试行）》和《住院医师规范化培训内容与标准（试行）》中有关超声专业细则的要求（详见附表1或2014年版两细则）</td>
          <td class="left">核对上1年度超声科各亚专业收治疾病种类和数量统计报表（以PACS和His为准）</td>
          <td class="left">符合要求（含协同单位），得满分<br/>
            疾病种类及数量≥规定数的90%，得3分<br/>
            疾病种类及数量≥规定数的85%，得1分<br/>
            疾病种类及数量＜规定数的85%，不得分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="1.1.5"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.5"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.1.5"]}</textarea></td>
          <td>
            <span id="1.1.5Span" style="display:${empty fileMap["1.1.5"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.5"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.5Up" href="javascript:uploadFile('1.1.5');" style="color:blue">${empty fileMap["1.1.5"]?'':'重新'}上传</a>
            <a id="1.1.5Del" href="javascript:delFile('1.1.5');"  style="color:blue;${empty fileMap["1.1.5"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.5Value" name="filePath" value="${fileMap["1.1.5"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.6技能（上机）操作种类及数量★</td>
          <td class="left">核对上1年度超声科各亚专业技能（上机）操作种类和数量的统计报表（以PACS和His为准） </td>
          <td class="left">符合要求（含协同单位），得满分<br/>
            技能（上机）操作种类及数量≥规定数的90%，得3分<br/>
            技能（上机）操作种类及数量≥规定数的85%，得1分<br/>
            技能（上机）操作种类及数量＜规定数85%，不得分   </td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="1.1.6"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.6"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.1.6"]}</textarea></td>
          <td>
            <span id="1.1.6Span" style="display:${empty fileMap["1.1.6"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.6"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.6Up" href="javascript:uploadFile('1.1.6');" style="color:blue">${empty fileMap["1.1.6"]?'':'重新'}上传</a>
            <a id="1.1.6Del" href="javascript:delFile('1.1.6');"  style="color:blue;${empty fileMap["1.1.6"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.6Value" name="filePath" value="${fileMap["1.1.6"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.1.7专业基地设备</td>
          <td class="left">彩色多普勒超声诊断仪≥8台</td>
          <td class="left">检查设备清单复印件，需加盖医院公章，实地考查</td>
          <td class="left">符合标准，得1分；不达标准，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.1.7"/>
          <td><input type="text" name="score" value="${scoreMap["1.1.7"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.1.7"]}</textarea></td>
          <td>
            <span id="1.1.7Span" style="display:${empty fileMap["1.1.7"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.1.7"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.1.7Up" href="javascript:uploadFile('1.1.7');" style="color:blue">${empty fileMap["1.1.7"]?'':'重新'}上传</a>
            <a id="1.1.7Del" href="javascript:delFile('1.1.7');"  style="color:blue;${empty fileMap["1.1.7"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.1.7Value" name="filePath" value="${fileMap["1.1.7"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td  rowspan="2">1.2协同单位</td>
          <td class="left">1.2.1协同数</td>
          <td class="left">≤3个（暂定）</td>
          <td class="left" rowspan="2">查看原始资料，核实相关信息（以PACS和His为准） </td>
          <td class="left">满足要求，得1分（无协同单位的专业基地，此处不失分）</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.2.1"/>
          <td><input type="text" name="score" value="${scoreMap["1.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.2.1"]}</textarea></td>
          <td>
            <span id="1.2.1Span" style="display:${empty fileMap["1.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.2.1Up" href="javascript:uploadFile('1.2.1');" style="color:blue">${empty fileMap["1.2.1"]?'':'重新'}上传</a>
            <a id="1.2.1Del" href="javascript:delFile('1.2.1');"  style="color:blue;${empty fileMap["1.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.2.1Value" name="filePath" value="${fileMap["1.2.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.2.2轮转时间</td>
          <td class="left">在协同亚专业（专科）轮转时间不超过3个月</td>
          <td class="left">满足要求，得1分（无协同单位的专业基地，此处不失分）</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.2.2"/>
          <td><input type="text" name="score" value="${scoreMap["1.2.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.2.2"]}</textarea></td>
          <td>
            <span id="1.2.2Span" style="display:${empty fileMap["1.2.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.2.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.2.2Up" href="javascript:uploadFile('1.2.2');" style="color:blue">${empty fileMap["1.2.2"]?'':'重新'}上传</a>
            <a id="1.2.2Del" href="javascript:delFile('1.2.2');"  style="color:blue;${empty fileMap["1.2.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.2.2Value" name="filePath" value="${fileMap["1.2.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td rowspan="2">1.3政策支持</td>
          <td class="left">1.3.1执业地点变更</td>
          <td class="left">医院管理部门协助办理医师执业地点变更手续</td>
          <td class="left">查看原始材料，核实相关信息</td>
          <td class="left">满足要求，得1分；不达标准，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.3.1"/>
          <td><input type="text" name="score" value="${scoreMap["1.3.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.3.1"]}</textarea></td>
          <td>
            <span id="1.3.1Span" style="display:${empty fileMap["1.3.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.3.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.3.1Up" href="javascript:uploadFile('1.3.1');" style="color:blue">${empty fileMap["1.3.1"]?'':'重新'}上传</a>
            <a id="1.3.1Del" href="javascript:delFile('1.3.1');"  style="color:blue;${empty fileMap["1.3.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.3.1Value" name="filePath" value="${fileMap["1.3.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">1.3.2“同工同酬”★</td>
          <td class="left">符合《住院医师规范化培训管理办法（试行）》等相关文件规定，制定规章制度，落实住培薪酬待遇</td>
          <td class="left">查看原始材料，核实相关信息，访谈（回避制）</td>
          <td class="left">满足要求，得1分；不达标准，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.3.2"/>
          <td><input type="text" name="score" value="${scoreMap["1.3.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.3.2"]}</textarea></td>
          <td>
            <span id="1.3.2Span" style="display:${empty fileMap["1.3.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.3.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.3.2Up" href="javascript:uploadFile('1.3.2');" style="color:blue">${empty fileMap["1.3.2"]?'':'重新'}上传</a>
            <a id="1.3.2Del" href="javascript:delFile('1.3.2');"  style="color:blue;${empty fileMap["1.3.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.3.2Value" name="filePath" value="${fileMap["1.3.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <th rowspan="7">2.师资条件（15分）</th>
          <td rowspan="4">2.1师资情况</td>
          <td class="left">2.1.1带教医师与学员比例★</td>
          <td class="left">带教老师与学员（包括并轨研究生）比例为1:1～2</td>
          <td class="left">查看原始资料，访谈学员</td>
          <td class="left">达标准，得2分；不达标准，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="2.1.1"/>
          <td><input type="text" name="score" value="${scoreMap["2.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.1.1"]}</textarea></td>
          <td>
            <span id="2.1.1Span" style="display:${empty fileMap["2.1.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.1Up" href="javascript:uploadFile('2.1.1');" style="color:blue">${empty fileMap["2.1.1"]?'':'重新'}上传</a>
            <a id="2.1.1Del" href="javascript:delFile('2.1.1');"  style="color:blue;${empty fileMap["2.1.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.1Value" name="filePath" value="${fileMap["2.1.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.2带教医师条件★</td>
          <td class="left">均须有“医学影像与放射治疗学”的执业资格，包括主任和教学副主任等；医学本科及以上学历,超声专业主治医师专业技术职称3年（含）以上，从事本专业临床诊疗工作5年（含）以上；介入超声带教医师应经过正规培训，并从事介入超声工作3年（含）以上</td>
          <td class="left" rowspan="3">查看人事部门提供的师资状，统计表，包括姓名、年龄、毕业时间、毕业学校、学历、学位、专业技术职称及任职时间、工作年限，需加盖人事部门公章</td>
          <td class="left">其中1名带教医师不符合要求，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.1.2"/>
          <td><input type="text" name="score" value="${scoreMap["2.1.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.1.2"]}</textarea></td>
          <td>
            <span id="2.1.2Span" style="display:${empty fileMap["2.1.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.2Up" href="javascript:uploadFile('2.1.2');" style="color:blue">${empty fileMap["2.1.2"]?'':'重新'}上传</a>
            <a id="2.1.2Del" href="javascript:delFile('2.1.2');"  style="color:blue;${empty fileMap["2.1.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.2Value" name="filePath" value="${fileMap["2.1.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.3带教医师组成</td>
          <td class="left">各超声亚专业（组）有主任医师或副主任医师≥1人，主治医师≥2人</td>
          <td class="left">1个亚专业不达标，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.1.3"/>
          <td><input type="text" name="score" value="${scoreMap["2.1.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.1.3"]}</textarea></td>
          <td>
            <span id="2.1.3Span" style="display:${empty fileMap["2.1.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.3Up" href="javascript:uploadFile('2.1.3');" style="color:blue">${empty fileMap["2.1.3"]?'':'重新'}上传</a>
            <a id="2.1.3Del" href="javascript:delFile('2.1.3');"  style="color:blue;${empty fileMap["2.1.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.3Value" name="filePath" value="${fileMap["2.1.3"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">2.1.4专业基地主任/负责人条件★</td>
          <td class="left">1.附属医院者，具有医学硕士研究生学历，主任医师专业技术职称；教学医院者，医学本科及以上学历，副主任医师以上专业技术职称；2.从事本专业医教研工作≥15年</td>
          <td class="left">1项不符合条件，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="2.1.4"/>
          <td><input type="text" name="score" value="${scoreMap["2.1.4"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.1.4"]}</textarea></td>
          <td>
            <span id="2.1.4Span" style="display:${empty fileMap["2.1.4"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.4"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.4Up" href="javascript:uploadFile('2.1.4');" style="color:blue">${empty fileMap["2.1.4"]?'':'重新'}上传</a>
            <a id="2.1.4Del" href="javascript:delFile('2.1.4');"  style="color:blue;${empty fileMap["2.1.4"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.4Value" name="filePath" value="${fileMap["2.1.4"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td rowspan="3">2.2师资建设</td>
          <td class="left">2.2.1师资培训★</td>
          <td class="left">近一年，带教医师60%参加过省/直辖市级师资培训活动，20%参加国家级师资培训活动</td>
          <td class="left">查看培训证书</td>
          <td class="left">两级别培训均满足，得满分；仅有一项，得2分；都不满足，不得分</td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="2.2.1"/>
          <td><input type="text" name="score" value="${scoreMap["2.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.2.1"]}</textarea></td>
          <td>
            <span id="2.2.1Span" style="display:${empty fileMap["2.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.1Up" href="javascript:uploadFile('2.2.1');" style="color:blue">${empty fileMap["2.2.1"]?'':'重新'}上传</a>
            <a id="2.2.1Del" href="javascript:delFile('2.2.1');"  style="color:blue;${empty fileMap["2.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.1Value" name="filePath" value="${fileMap["2.2.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">2.2.2师资评价★</td>
          <td class="left">每年度至少组织1次对带教医师教学工作进行评价</td>
          <td class="left">查看原始资料（评价或鉴定表等），访谈带教医师和学员（回避制）</td>
          <td class="left">原始记录详实，得满分；无记录，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.2.2"/>
          <td><input type="text" name="score" value="${scoreMap["2.2.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.2.2"]}</textarea></td>
          <td>
            <span id="2.2.2Span" style="display:${empty fileMap["2.2.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.2Up" href="javascript:uploadFile('2.2.2');" style="color:blue">${empty fileMap["2.2.2"]?'':'重新'}上传</a>
            <a id="2.2.2Del" href="javascript:delFile('2.2.2');"  style="color:blue;${empty fileMap["2.2.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.2Value" name="filePath" value="${fileMap["2.2.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">2.2.3激励制度★</td>
          <td class="left">建立带教医师激励机制，将教学工作与绩效考评、奖金、评优等挂钩</td>
          <td class="left">查看相关材料，访谈带教医师（回避制）</td>
          <td class="left">有激励机制，与奖金、评优挂钩，得满分；无激励机制，不得分</td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="2.2.3"/>
          <td><input type="text" name="score" value="${scoreMap["2.2.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.2.3"]}</textarea></td>
          <td>
            <span id="2.2.3Span" style="display:${empty fileMap["2.2.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.3Up" href="javascript:uploadFile('2.2.3');" style="color:blue">${empty fileMap["2.2.3"]?'':'重新'}上传</a>
            <a id="2.2.3Del" href="javascript:delFile('2.2.3');"  style="color:blue;${empty fileMap["2.2.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.3Value" name="filePath" value="${fileMap["2.2.3"]}">
          </td>
        </tr>

        <tr class="td_group">
          <th rowspan="11">3.过程管理（30分）</th>
          <td rowspan="7">3.1培训制度与落实</td>
          <td class="left">3.1.1主任职责</td>
          <td class="left">实行专业基地负责人负责制，并切实落实</td>
          <td class="left" rowspan="3">查看相关文件与证明，访谈各类人员（回避制）</td>
          <td class="left">职责明确，履职认真，得1分；无岗位职责或履职不认真，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="3.1.1"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.1.1"]}</textarea></td>
          <td>
            <span id="3.1.1Span" style="display:${empty fileMap["3.1.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.1Up" href="javascript:uploadFile('3.1.1');" style="color:blue">${empty fileMap["3.1.1"]?'':'重新'}上传</a>
            <a id="3.1.1Del" href="javascript:delFile('3.1.1');"  style="color:blue;${empty fileMap["3.1.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.1Value" name="filePath" value="${fileMap["3.1.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.2教学主任或相应负责人★</td>
          <td class="left">与科主任或基地主任非同一人，副高职称及以上，专门具体负责本专业基地教学培训工作的组织与实施</td>
          <td class="left">职责明确，履职认真，得3分；无岗位职责，或履职不认真，不得分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="3.1.2"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.1.2"]}</textarea></td>
          <td>
            <span id="3.1.2Span" style="display:${empty fileMap["3.1.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.2Up" href="javascript:uploadFile('3.1.2');" style="color:blue">${empty fileMap["3.1.2"]?'':'重新'}上传</a>
            <a id="3.1.2Del" href="javascript:delFile('3.1.2');"  style="color:blue;${empty fileMap["3.1.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.2Value" name="filePath" value="${fileMap["3.1.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.3教学秘书</td>
          <td class="left">主治医师及以上职称，落实本专业基地教学工作</td>
          <td class="left">有教学秘书，履职认真，得1分；无或履职不认真，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="3.1.3"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.1.3"]}</textarea></td>
          <td>
            <span id="3.1.3Span" style="display:${empty fileMap["3.1.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.3Up" href="javascript:uploadFile('3.1.3');" style="color:blue">${empty fileMap["3.1.3"]?'':'重新'}上传</a>
            <a id="3.1.3Del" href="javascript:delFile('3.1.3');"  style="color:blue;${empty fileMap["3.1.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.3Value" name="filePath" value="${fileMap["3.1.3"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.4教学小组</td>
          <td class="left">成立教学小组，明确小组职责，定期组织教学工作</td>
          <td class="left">查看教学小组名单、职责和教学工作记录</td>
          <td class="left">有教学小组，履职认真，得2分；无或履职不认真，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="3.1.4"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.4"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.1.4"]}</textarea></td>
          <td>
            <span id="3.1.4Span" style="display:${empty fileMap["3.1.4"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.4"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.4Up" href="javascript:uploadFile('3.1.4');" style="color:blue">${empty fileMap["3.1.4"]?'':'重新'}上传</a>
            <a id="3.1.4Del" href="javascript:delFile('3.1.4');"  style="color:blue;${empty fileMap["3.1.4"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.4Value" name="filePath" value="${fileMap["3.1.4"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.5教学计划</td>
          <td class="left">按规定制定并落实教学计划</td>
          <td class="left">查看原始材料，核实相关信息，演示，访谈（回避制）</td>
          <td class="left">有，且严格落实，得2分；未严格落实，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="3.1.5"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.5"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.1.5"]}</textarea></td>
          <td>
            <span id="3.1.5Span" style="display:${empty fileMap["3.1.5"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.5"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.5Up" href="javascript:uploadFile('3.1.5');" style="color:blue">${empty fileMap["3.1.5"]?'':'重新'}上传</a>
            <a id="3.1.5Del" href="javascript:delFile('3.1.5');"  style="color:blue;${empty fileMap["3.1.5"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.5Value" name="filePath" value="${fileMap["3.1.5"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.6轮转管理</td>
          <td class="left">按细则落实轮转计划和要求</td>
          <td class="left">查看2～3名学员轮转手册等原始资料，访谈学员（回避制）</td>
          <td class="left">有，且严格落实，得4分；未严格落实，不得分</td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="3.1.6"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.6"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.1.6"]}</textarea></td>
          <td>
            <span id="3.1.6Span" style="display:${empty fileMap["3.1.6"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.6"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.6Up" href="javascript:uploadFile('3.1.6');" style="color:blue">${empty fileMap["3.1.6"]?'':'重新'}上传</a>
            <a id="3.1.6Del" href="javascript:delFile('3.1.6');"  style="color:blue;${empty fileMap["3.1.6"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.6Value" name="filePath" value="${fileMap["3.1.6"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.1.7考勤制度</td>
          <td class="left">有考勤规章制度，有专人负责，并严格执行</td>
          <td class="left">查看考勤规章制度，并抽查2～3名学员考勤记录原始资料</td>
          <td class="left">有，且严格落实，得2分；未严格落实，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="3.1.7"/>
          <td><input type="text" name="score" value="${scoreMap["3.1.7"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.1.7"]}</textarea></td>
          <td>
            <span id="3.1.7Span" style="display:${empty fileMap["3.1.7"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.7"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.7Up" href="javascript:uploadFile('3.1.7');" style="color:blue">${empty fileMap["3.1.7"]?'':'重新'}上传</a>
            <a id="3.1.7Del" href="javascript:delFile('3.1.7');"  style="color:blue;${empty fileMap["3.1.7"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.7Value" name="filePath" value="${fileMap["3.1.7"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td rowspan="3">3.2培训活动</td>
          <td class="left">3.2.1入科教育</td>
          <td class="left">规范实施包括科室情况、科室纪律、培养计划与要求、医德医风、医患沟通等入科教育，并有专人组织实施</td>
          <td class="left">提供本年度入科教育原始资料</td>
          <td class="left">有，且严格落实，得2分；未严格落实，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="3.2.1"/>
          <td><input type="text" name="score" value="${scoreMap["3.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.2.1"]}</textarea></td>
          <td>
            <span id="3.2.1Span" style="display:${empty fileMap["3.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.2.1Up" href="javascript:uploadFile('3.2.1');" style="color:blue">${empty fileMap["3.2.1"]?'':'重新'}上传</a>
            <a id="3.2.1Del" href="javascript:delFile('3.2.1');"  style="color:blue;${empty fileMap["3.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.2.1Value" name="filePath" value="${fileMap["3.2.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.2.2小讲课</td>
          <td class="left">开展规范的小讲课活动，至少2周1次</td>
          <td class="left" rowspan="2">提供本年度原始资料，访谈学员（回避制），核实落实情况</td>
          <td class="left">开展次数达标，且认真规范，得4分；未达标或不规范，不得分</td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="3.2.2"/>
          <td><input type="text" name="score" value="${scoreMap["3.2.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.2.2"]}</textarea></td>
          <td>
            <span id="3.2.2Span" style="display:${empty fileMap["3.2.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.2.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.2.2Up" href="javascript:uploadFile('3.2.2');" style="color:blue">${empty fileMap["3.2.2"]?'':'重新'}上传</a>
            <a id="3.2.2Del" href="javascript:delFile('3.2.2');"  style="color:blue;${empty fileMap["3.2.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.2.2Value" name="filePath" value="${fileMap["3.2.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">3.2.3疑难病例讨论</td>
          <td class="left">开展规范的疑难病例讨论，至少2周1次</td>
          <td class="left">开展次数达标，且认真规范，得3分；未达标或不规范，不得分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="3.2.3"/>
          <td><input type="text" name="score" value="${scoreMap["3.2.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.2.3"]}</textarea></td>
          <td>
            <span id="3.2.3Span" style="display:${empty fileMap["3.2.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.2.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.2.3Up" href="javascript:uploadFile('3.2.3');" style="color:blue">${empty fileMap["3.2.3"]?'':'重新'}上传</a>
            <a id="3.2.3Del" href="javascript:delFile('3.2.3');"  style="color:blue;${empty fileMap["3.2.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.2.3Value" name="filePath" value="${fileMap["3.2.3"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td>3.3过程考核</td>
          <td class="left">3.3.1出科考核★</td>
          <td class="left">理论考核、技能操作考核评分标准、学员测评结果等原始资料齐全，真实规范</td>
          <td class="left">随机抽查、访谈委培、社会人、本院学员2～3名（回避制），检查近1年原始资料</td>
          <td class="left">考核项目全面，且认真规范，得6分；仅有技能操作考核，得5分；理论考试，得3分；无考核，不得分</td>
          <td>6</td>
          <input type="hidden" name="orderNumber" value="3.3.1"/>
          <td><input type="text" name="score" value="${scoreMap["3.3.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.3.1"]}</textarea></td>
          <td>
            <span id="3.3.1Span" style="display:${empty fileMap["3.3.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.3.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.3.1Up" href="javascript:uploadFile('3.3.1');" style="color:blue">${empty fileMap["3.3.1"]?'':'重新'}上传</a>
            <a id="3.3.1Del" href="javascript:delFile('3.3.1');"  style="color:blue;${empty fileMap["3.3.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.3.1Value" name="filePath" value="${fileMap["3.3.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <th rowspan="6">4.质量控制（35分）</th>
          <td rowspan="2">4.1带教医师教学质量</td>
          <td class="left">4.1.1带教老师教学活动★</td>
          <td class="left">带教医师针对学员开展规范的临床教学指导（演示、阅片会）</td>
          <td class="left">随机抽查2～3名带教医师的临床教学情况</td>
          <td class="left" rowspan="2">临床教学评分表见附表2
            ≥90分得10分，＜90～80分得8分，＜80～70分得6分，＜70～60分得4分，＜60分不得分  </td>
          <td rowspan="2">10</td>
          <input type="hidden" name="orderNumber" value="4.1.1"/>
          <td rowspan="2"><input type="text" name="score" value="${scoreMap["4.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.1.1"]}</textarea></td>
          <td>
            <span id="4.1.1Span" style="display:${empty fileMap["4.1.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.1.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.1.1Up" href="javascript:uploadFile('4.1.1');" style="color:blue">${empty fileMap["4.1.1"]?'':'重新'}上传</a>
            <a id="4.1.1Del" href="javascript:delFile('4.1.1');"  style="color:blue;${empty fileMap["4.1.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.1.1Value" name="filePath" value="${fileMap["4.1.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">4.1.2技能操作带教情况★</td>
          <td class="left">带教医师指导学员完成技能操作，操作前、操作中及时的指导，结束后提问、讲解、指导书写报告等</td>
          <td class="left">随机抽查2～3名带教医师指导学员（规二及以上）进行技能操作等</td>
          <input type="hidden" name="orderNumber" value="4.1.2"/>
          <%--<td><input type="text" name="score" value="${scoreMap["4.1.2"]}"/></td>--%>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.1.2"]}</textarea></td>
          <td>
            <span id="4.1.2Span" style="display:${empty fileMap["4.1.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.1.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.1.2Up" href="javascript:uploadFile('4.1.2');" style="color:blue">${empty fileMap["4.1.2"]?'':'重新'}上传</a>
            <a id="4.1.2Del" href="javascript:delFile('4.1.2');"  style="color:blue;${empty fileMap["4.1.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.1.2Value" name="filePath" value="${fileMap["4.1.2"]}">
          </td>
        </tr>
        <%--<tr class="td_group">--%>
          <%--<td class="left">4.1.3技能操作带教情况★</td>--%>
          <%--<td class="left">带教医师协助并指导培训对象完成技能操作，带教严格规范</td>--%>
          <%--<td class="left">1.培训对象操作前是否与患者交流、沟通情况 1分<br/>2.培训对象操作中存在问题及时进行指导  1分<br/>3.培训对象操作结束后是否进行提问   1分<br/>4.对培训对象的操作的总体评价（优、缺点点评） 2分</td>--%>
          <%--<td>5<input type="hidden" name="baseScore" value="5"/></td>--%>
          <%--<td><input type="text" name="score" value=""/></td>--%>
          <%--<td><textarea name="reason">${formDataMap['scores'][28]['reason']}</textarea></td>--%>
        <%--</tr>--%>
        <tr class="td_group">
          <td rowspan="4">4.2学员学习效果</td>
          <td class="left">4.2.1报告书写★</td>
          <td class="left">学员诊断报告书写规范、准确</td>
          <td class="left">随机抽查2～3名学员书写的诊断报告，结合报告提问（回避制）</td>
          <td class="left">诊断报告书写评分表见附表3       ≥90分得5分，＜90～80分得4分，＜80～70分得2分，＜70～60分得1分，＜60分不得分 </td>
          <td>5</td>
          <input type="hidden" name="orderNumber" value="4.2.1"/>
          <td><input type="text" name="score" value="${scoreMap["4.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.2.1"]}</textarea></td>
          <td>
            <span id="4.2.1Span" style="display:${empty fileMap["4.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.2.1Up" href="javascript:uploadFile('4.2.1');" style="color:blue">${empty fileMap["4.2.1"]?'':'重新'}上传</a>
            <a id="4.2.1Del" href="javascript:delFile('4.2.1');"  style="color:blue;${empty fileMap["4.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.2.1Value" name="filePath" value="${fileMap["4.2.1"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">4.2.2上机操作★</td>
          <td class="left">学员技能操作情况</td>
          <td class="left">随机抽查2～3名二年级及以上学员进行技能操作，查看其掌握超声检查的情况</td>
          <td class="left">技能操作评分表见附表4
            ≥90分得8分，＜90～80分得6分，＜80～70分得4分，＜70～60分得2分，＜60分不得分</td>
          <td>8</td>
          <input type="hidden" name="orderNumber" value="4.2.2"/>
          <td><input type="text" name="score" value="${scoreMap["4.2.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.2.2"]}</textarea></td>
          <td>
            <span id="4.2.2Span" style="display:${empty fileMap["4.2.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.2.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.2.2Up" href="javascript:uploadFile('4.2.2');" style="color:blue">${empty fileMap["4.2.2"]?'':'重新'}上传</a>
            <a id="4.2.2Del" href="javascript:delFile('4.2.2');"  style="color:blue;${empty fileMap["4.2.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.2.2Value" name="filePath" value="${fileMap["4.2.2"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">4.2.3完成培训内容与要求</td>
          <td class="left">按照本专业《住院医师规范化培训内容与标准（试行）》细则，核实培训内容的完成情况</td>
          <td class="left">随机抽查、访谈学员2～3名（回避制），查看轮转登记手册、出科考核等原始资料</td>
          <td class="left">资料完整得4分<br/>
            资料部分完整得2分<br/>
            无完整资料不得分</td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="4.2.3"/>
          <td><input type="text" name="score" value="${scoreMap["4.2.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.2.3"]}</textarea></td>
          <td>
            <span id="4.2.3Span" style="display:${empty fileMap["4.2.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.2.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.2.3Up" href="javascript:uploadFile('4.2.3');" style="color:blue">${empty fileMap["4.2.3"]?'':'重新'}上传</a>
            <a id="4.2.3Del" href="javascript:delFile('4.2.3');"  style="color:blue;${empty fileMap["4.2.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.2.3Value" name="filePath" value="${fileMap["4.2.3"]}">
          </td>
        </tr>
        <tr class="td_group">
          <td class="left">4.2.4近三年理论、技能考核成绩★</td>
          <td class="left">学员理论与技能考核情况</td>
          <td class="left">查看原始材料，核实相关信息</td>
          <td class="left">学员理论及技能考核全部合格，得8分；1名学员考核理论或技能不合格，得4分；2名学员考核理论或技能不合格，不得分</td>
          <td>8</td>
          <input type="hidden" name="orderNumber" value="4.2.4"/>
          <td><input type="text" name="score" value="${scoreMap["4.2.4"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.2.4"]}</textarea></td>
          <td>
            <span id="4.2.4Span" style="display:${empty fileMap["4.2.4"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.2.4"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.2.4Up" href="javascript:uploadFile('4.2.4');" style="color:blue">${empty fileMap["4.2.4"]?'':'重新'}上传</a>
            <a id="4.2.4Del" href="javascript:delFile('4.2.4');"  style="color:blue;${empty fileMap["4.2.4"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.2.4Value" name="filePath" value="${fileMap["4.2.4"]}">
          </td>
        </tr>
      </tbody>
      <tbody>
      <tr class="td_group">
        <td colspan="6" class="right">合计</td>
        <td>100</td>
        <input type="hidden" name="orderNumber" value="sumScore"/>
        <td><input id="inputSumScore" readonly name="score" value="${scoreMap["sumScore"]}"/></td>
        <td></td>
        <td></td>
      </tr>
      <tr class="td_group">
        <td class="right">存在问题</td>
        <td colspan="8" >
          <input type="hidden" name="orderNumber" value="problems"/>
          <textarea name="deductMarks" class="problems" rows="4" placeholder="" style="width:99%;resize:none;margin:3px; " >
            ${marksMap["problems"]}</textarea>
          <td><span id="problemsSpan" style="display:${empty fileMap["problems"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["problems"]}" target="_blank" style="color:blue">查看</a>
			</span>
          <a id="problemsUp" href="javascript:uploadFile('problems');" style="color:blue">${empty fileMap["problems"]?'':'重新'}上传</a>
          <a id="problemsDel" href="javascript:delFile('problems');"  style="color:blue;${empty fileMap["problems"]?'display:none':''}">删除</a>
          <input type="hidden" id="problemsValue" name="filePath" value="${fileMap["problems"]}"></td>
        </td>
      </tr>
      <tr class="td_group">
        <td colspan="10">
          专家姓名：
          <input type="hidden" name="orderNumber" value="sign"/>
          <input type="text" class="qtext" name="deductMarks" value="${marksMap["sign"]}"/>
          &#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
          评估日期：
          <input type="text" class="qtext" name="score" value="${scoreMap["sign"]}" id="signDate"
                 onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
        </td>
      </tr>
      </tbody>
    </table>
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