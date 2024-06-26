
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
   </style>
   <script>
     $(function(){
       $(".td_group textarea").css("height","25px").css("background","white");
       $(".td_group input[name='score']:not(#signDate)").css("width","30px").css("background","white");
       $(".problems").css("height","");
       $("input[name='score']:not(#signDate,#inputSumScore1,#inputSumScore2,#inputSumScore3)").each(function(){
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
     function sumScore(){
       var sum1=0;
       $(".sum1").each(function(){
         var score=$(this).val();
         if(!isNaN(score)&&score!="")
         {
           sum1+=parseFloat(score);
         }else{
           sum1+=0;
         }
       });
       $("#inputSumScore1").val(sum1);
       var sum2=0;
       $(".sum2").each(function(){
         var score=$(this).val();
         if(!isNaN(score)&&score!="")
         {
           sum2+=parseFloat(score);
         }else{
           sum2+=0;
         }
       });
       $("#inputSumScore2").val(sum2);
       $("#inputSumScore3").val(sum1+sum2);
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
       }else{
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
	<h1 style="text-align: center;font-size: 25px;">2016年住院医师规范化培训基地评估指标</h1>
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
          <th rowspan="9">1.基本条件（20分）</th>
          <td>1.1医院情况</td>
          <td class="left">1.1.1医院科室设置</td>
          <td class="left">医院等级、科室设置、床位数等符合培训基地认定标准有关要求，其中：<br/>综合医院临床科室至少设有急诊科、内科、外科、妇产科、儿科、全科、中医科、耳鼻喉科、口腔科、眼科、皮肤科、麻醉科、康复医学科、预防保健科；医技科室至少设有药剂科、检验科、放射科、手术室、病理科、输血科、核医学科、消毒供应室、病案室、营养部和相应的临床功能检查室；<br/>承担口腔、儿科、妇产、精神、放射肿瘤等专业培训任务的专科医院，科室设置要符合相关培训基地认定标准的要求。</td>
          <td class="left">查看医院相关材料</td>
          <td class="left">均符合标准，得满分<br/>缺1个科室，扣1分，直至扣完</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="1.1.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["1.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.1.1"]}</textarea></td>
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
          <td rowspan="2">1.2图书馆及信息检索系统</td>
          <td class="left">1.2.1图书馆藏书数量</td>
          <td class="left">藏书量（包括电子图书）不低于3000册/百名卫技人员，并对培训对象开放；</td>
          <td class="left" rowspan="2">现场考查，查看相关资料</td>
          <td class="left">符合标准，得1分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.2.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["1.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.2.1"]}</textarea></td>
          <td>
            <span id="1.2.1Span" style="display:${empty fileMap["1.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.2.1Up" href="javascript:uploadFile('1.2.1');" style="color:blue">${empty fileMap["1.2.1"]?'':'重新'}上传</a>
            <a id="1.2.1Del" href="javascript:delFile('1.2.1');"  style="color:blue;${empty fileMap["1.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.2.1Value" name="filePath" value="${fileMap["1.2.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">1.2.2信息检索系统</td>
          <td class="left">有信息检索系统，并提供培训对象使用</td>
          <td class="left">符合标准，得1分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.2.2"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["1.2.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.2.2"]}</textarea></td>
          <td>
            <span id="1.2.2Span" style="display:${empty fileMap["1.2.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.2.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.2.2Up" href="javascript:uploadFile('1.2.2');" style="color:blue">${empty fileMap["1.2.2"]?'':'重新'}上传</a>
            <a id="1.2.2Del" href="javascript:delFile('1.2.2');"  style="color:blue;${empty fileMap["1.2.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.2.2Value" name="filePath" value="${fileMap["1.2.2"]}">
        </tr>
        <tr class="td_group">
          <td rowspan="3">1.3临床能力训练中心</td>
          <td class="left">1.3.1场地设备</td>
          <td class="left">建筑面积不低于600平米（专科医院符合相关培训基地认定标准的要求），训练仪器设备满足培训需求，面向培训对象开放</td>
          <td class="left" rowspan="3">1.现场考查，查看原始记录<br/>2.访谈2～3名培训对象</td>
          <td class="left">符合标准，满足培训需求，得2分；不符合标准或未满足需求，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="1.3.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["1.3.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.3.1"]}</textarea></td>
          <td>
            <span id="1.3.1Span" style="display:${empty fileMap["1.3.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.3.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.3.1Up" href="javascript:uploadFile('1.3.1');" style="color:blue">${empty fileMap["1.3.1"]?'':'重新'}上传</a>
            <a id="1.3.1Del" href="javascript:delFile('1.3.1');"  style="color:blue;${empty fileMap["1.3.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.3.1Value" name="filePath" value="${fileMap["1.3.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">1.3.2专职人员管理</td>
          <td class="left">配备专职人员管理</td>
          <td class="left">有专人管理，得1分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.3.2"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["1.3.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.3.2"]}</textarea></td>
          <td>
            <span id="1.3.2Span" style="display:${empty fileMap["1.3.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.3.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.3.2Up" href="javascript:uploadFile('1.3.2');" style="color:blue">${empty fileMap["1.3.2"]?'':'重新'}上传</a>
            <a id="1.3.2Del" href="javascript:delFile('1.3.2');"  style="color:blue;${empty fileMap["1.3.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.3.2Value" name="filePath" value="${fileMap["1.3.2"]}">
        </tr>
        <tr class="td_group">
          <td class="left">1.3.3课程训练计划</td>
          <td class="left">制定技能操作训练计划，根据课程设计实施训练</td>
          <td class="left">有计划并实施，得满分；有计划，部分落实，得1分</td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="1.3.3"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["1.3.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.3.3"]}</textarea></td>
          <td>
            <span id="1.3.3Span" style="display:${empty fileMap["1.3.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.3.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.3.3Up" href="javascript:uploadFile('1.3.3');" style="color:blue">${empty fileMap["1.3.3"]?'':'重新'}上传</a>
            <a id="1.3.3Del" href="javascript:delFile('1.3.3');"  style="color:blue;${empty fileMap["1.3.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.3.3Value" name="filePath" value="${fileMap["1.3.3"]}">
        </tr>
        <tr class="td_group">
          <td>1.4全科医学科</td>
          <td class="left">1.4.1建立全科医学科★</td>
          <td class="left">作为培训基地的综合医院独立设置全科医学科，牵头承担全科住培，与相关临床轮转科室密切协同，指导帮助基层实践基地加强带教能力建设，共同完成好培训任务。</td>
          <td class="left">现场考查，查看相关资料</td>
          <td class="left">全部落实，得满分<br/>
            有科室，未落实培训任务（无全科专业基地除外），得2分<br/>
            未设置，不得分</td>
          <td>5</td>
          <input type="hidden" name="orderNumber" value="1.4.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["1.4.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.4.1"]}</textarea></td>
          <td>
            <span id="1.4.1Span" style="display:${empty fileMap["1.4.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.4.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.4.1Up" href="javascript:uploadFile('1.4.1');" style="color:blue">${empty fileMap["1.4.1"]?'':'重新'}上传</a>
            <a id="1.4.1Del" href="javascript:delFile('1.4.1');"  style="color:blue;${empty fileMap["1.4.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.4.1Value" name="filePath" value="${fileMap["1.4.1"]}">
        </tr>
        <tr class="td_group">
          <td rowspan="2">1.5协同管理</td>
          <td class="left">1.5.1协同单位</td>
          <td class="left">协同单位应为二级甲等及以上</td>
          <td class="left">现场考查，查看相关资料</td>
          <td class="left">符合标准，得1分（如无协同单位，此处不失分）</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="1.5.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["1.5.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.5.1"]}</textarea></td>
          <td>
            <span id="1.5.1Span" style="display:${empty fileMap["1.5.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.5.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.5.1Up" href="javascript:uploadFile('1.5.1');" style="color:blue">${empty fileMap["1.5.1"]?'':'重新'}上传</a>
            <a id="1.5.1Del" href="javascript:delFile('1.5.1');"  style="color:blue;${empty fileMap["1.5.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.5.1Value" name="filePath" value="${fileMap["1.5.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">1.5.2协同管理责任</td>
          <td class="left">制定协同单位管理办法，明确培训基地与协同单位分工协作责任，规范培训实施与管理</td>
          <td class="left">1.查看原始资料<br/>
            2.核实培训基地、协同单位住培管理人员<br/>
            3.访谈带教医师和培训对象<br/>
          </td>
          <td class="left">有文件，并认真落实，得满分<br/>
            有文件，部分落实，得1分<br/>
            无文件，或未落实，不得分<br/>
            （如无协同单位，此处不失分）</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="1.5.2"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["1.5.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["1.5.2"]}</textarea></td>
          <td>
            <span id="1.5.2Span" style="display:${empty fileMap["1.5.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["1.5.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="1.5.2Up" href="javascript:uploadFile('1.5.2');" style="color:blue">${empty fileMap["1.5.2"]?'':'重新'}上传</a>
            <a id="1.5.2Del" href="javascript:delFile('1.5.2');"  style="color:blue;${empty fileMap["1.5.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="1.5.2Value" name="filePath" value="${fileMap["1.5.2"]}">
        </tr>
        <tr class="td_group">
          <th rowspan="21">2.组织管理（35分）</th>
          <td rowspan="5">2.1组织领导</td>
          <td class="left">2.1.1院长职责★</td>
          <td class="left">实行院长（一把手）负责制，并切实落实</td>
          <td class="left" rowspan="3">查看原始资料</td>
          <td class="left">认真落实，得满分<br/>
            未落实，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="2.1.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.1.1"]}</textarea></td>
          <td>
            <span id="2.1.1Span" style="display:${empty fileMap["2.1.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.1Up" href="javascript:uploadFile('2.1.1');" style="color:blue">${empty fileMap["2.1.1"]?'':'重新'}上传</a>
            <a id="2.1.1Del" href="javascript:delFile('2.1.1');"  style="color:blue;${empty fileMap["2.1.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.1Value" name="filePath" value="${fileMap["2.1.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.1.2分管院长职责</td>
          <td class="left">明确分管院级领导，并履行分管职责</td>
          <td class="left">有，并履行职责，得1分<br/>
            未履行职责，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.1.2"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.1.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.1.2"]}</textarea></td>
          <td>
            <span id="2.1.2Span" style="display:${empty fileMap["2.1.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.2Up" href="javascript:uploadFile('2.1.2');" style="color:blue">${empty fileMap["2.1.2"]?'':'重新'}上传</a>
            <a id="2.1.2Del" href="javascript:delFile('2.1.2');"  style="color:blue;${empty fileMap["2.1.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.2Value" name="filePath" value="${fileMap["2.1.2"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.1.3主管部门</td>
          <td class="left">有专门负责住培的职能部门和负责人，与其他相关职能部门密切协同，共同落实好住培管理责任</td>
          <td class="left">有，并落实，得1分<br/>
            无，或未落实，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.1.3"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.1.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.1.3"]}</textarea></td>
          <td>
            <span id="2.1.3Span" style="display:${empty fileMap["2.1.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.3Up" href="javascript:uploadFile('2.1.3');" style="color:blue">${empty fileMap["2.1.3"]?'':'重新'}上传</a>
            <a id="2.1.3Del" href="javascript:delFile('2.1.3');"  style="color:blue;${empty fileMap["2.1.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.3Value" name="filePath" value="${fileMap["2.1.3"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.1.4专人管理</td>
          <td class="left">有专职管理人员履职，专职管理人员与培训对象比例≥1:100</td>
          <td class="left">查看原始资料，访谈2～3名培训对象</td>
          <td class="left">有，并落实，得1分<br/>
            无，或未落实，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.1.4"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.1.4"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.1.4"]}</textarea></td>
          <td>
            <span id="2.1.4Span" style="display:${empty fileMap["2.1.4"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.4"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.4Up" href="javascript:uploadFile('2.1.4');" style="color:blue">${empty fileMap["2.1.4"]?'':'重新'}上传</a>
            <a id="2.1.4Del" href="javascript:delFile('2.1.4');"  style="color:blue;${empty fileMap["2.1.4"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.4Value" name="filePath" value="${fileMap["2.1.4"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.1.5教学主任★</td>
          <td class="left">专业基地设置专职教学主任岗位，专门负责本专业基地带教工作的组织实施</td>
          <td class="left">查看原始资料，访谈带教医师和培训对象</td>
          <td class="left">有，并落实，得满分<br/>
            无，或未落实，不得分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="2.1.5"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.1.5"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.1.5"]}</textarea></td>
          <td>
            <span id="2.1.5Span" style="display:${empty fileMap["2.1.5"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.1.5"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.1.5Up" href="javascript:uploadFile('2.1.5');" style="color:blue">${empty fileMap["2.1.5"]?'':'重新'}上传</a>
            <a id="2.1.5Del" href="javascript:delFile('2.1.5');"  style="color:blue;${empty fileMap["2.1.5"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.1.5Value" name="filePath" value="${fileMap["2.1.5"]}">
        </tr>
        <tr class="td_group">
          <td rowspan="8">2.2制度与落实</td>
          <td class="left">2.2.1招收等管理规定</td>
          <td class="left">制定本基地住培招收等管理办法，不得限制本科毕业生参培，向紧缺专业倾斜，并认真落实</td>
          <td class="left" rowspan="3">查看文件及相关资料，访谈管理人员、带教医师和培训对象</td>
          <td class="left">有，并落实，得1分<br/>无，或未落实，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.2.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.2.1"]}</textarea></td>
          <td>
            <span id="2.2.1Span" style="display:${empty fileMap["2.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.1Up" href="javascript:uploadFile('2.2.1');" style="color:blue">${empty fileMap["2.2.1"]?'':'重新'}上传</a>
            <a id="2.2.1Del" href="javascript:delFile('2.2.1');"  style="color:blue;${empty fileMap["2.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.1Value" name="filePath" value="${fileMap["2.2.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.2.2培训计划★</td>
          <td class="left">参照《住院医师规范化培训内容与标准（试行）》的要求，制定实施系统规范的培训轮转计划，年度安排体现循序渐进要求；落实入院教育</td>
          <td class="left">均有，并落实，得满分<br/>无，或未落实，不得分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="2.2.2"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.2.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.2.2"]}</textarea></td>
          <td>
            <span id="2.2.2Span" style="display:${empty fileMap["2.2.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.2Up" href="javascript:uploadFile('2.2.2');" style="color:blue">${empty fileMap["2.2.2"]?'':'重新'}上传</a>
            <a id="2.2.2Del" href="javascript:delFile('2.2.2');"  style="color:blue;${empty fileMap["2.2.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.2Value" name="filePath" value="${fileMap["2.2.2"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.2.3考核办法</td>
          <td class="left">制定本基地出科考核、年度考核等形成性考核管理办法，并认真落实</td>
          <td class="left">有，并落实，得1分<br/>无，或未落实，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.2.3"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.2.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.2.3"]}</textarea></td>
          <td>
            <span id="2.2.3Span" style="display:${empty fileMap["2.2.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.3Up" href="javascript:uploadFile('2.2.3');" style="color:blue">${empty fileMap["2.2.3"]?'':'重新'}上传</a>
            <a id="2.2.3Del" href="javascript:delFile('2.2.3');"  style="color:blue;${empty fileMap["2.2.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.3Value" name="filePath" value="${fileMap["2.2.3"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.2.4经费使用管理办法</td>
          <td class="left">制定本基地住培经费使用管理办法，并规范实施</td>
          <td class="left">查看文件及相关资料，访谈相关管理人员</td>
          <td class="left">有，并落实，得1分<br/>无，或未落实，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.2.4"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.2.4"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.2.4"]}</textarea></td>
          <td>
            <span id="2.2.4Span" style="display:${empty fileMap["2.2.4"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.4"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.4Up" href="javascript:uploadFile('2.2.4');" style="color:blue">${empty fileMap["2.2.4"]?'':'重新'}上传</a>
            <a id="2.2.4Del" href="javascript:delFile('2.2.4');"  style="color:blue;${empty fileMap["2.2.4"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.4Value" name="filePath" value="${fileMap["2.2.4"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.2.5人员管理制度</td>
          <td class="left">有住培人员管理规定，并认真落实</td>
          <td class="left">查看相关文件，访谈培训对象和相关管理人员</td>
          <td class="left">有，并落实，得1分<br/>无，或未落实，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.2.5"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.2.5"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.2.5"]}</textarea></td>
          <td>
            <span id="2.2.5Span" style="display:${empty fileMap["2.2.5"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.5"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.5Up" href="javascript:uploadFile('2.2.5');" style="color:blue">${empty fileMap["2.2.5"]?'':'重新'}上传</a>
            <a id="2.2.5Del" href="javascript:delFile('2.2.5');"  style="color:blue;${empty fileMap["2.2.5"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.5Value" name="filePath" value="${fileMap["2.2.5"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.2.6师资管理办法</td>
          <td class="left">制定本基地住培师资管理办法，规范师资遴选、培训、考核和激励工作</td>
          <td class="left">查看文件及相关资料，访谈带教医师</td>
          <td class="left">有，并落实，得1分<br/>无，或未落实，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.2.6"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.2.6"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.2.6"]}</textarea></td>
          <td>
            <span id="2.2.6Span" style="display:${empty fileMap["2.2.6"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.6"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.6Up" href="javascript:uploadFile('2.2.6');" style="color:blue">${empty fileMap["2.2.6"]?'':'重新'}上传</a>
            <a id="2.2.6Del" href="javascript:delFile('2.2.6');"  style="color:blue;${empty fileMap["2.2.6"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.6Value" name="filePath" value="${fileMap["2.2.6"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.2.7沟通反馈机制</td>
          <td class="left">建立顺畅的沟通反馈机制，及时了解培训对象和带教医师的意见建议</td>
          <td class="left">查看原始资料，访谈培训对象和带教医师</td>
          <td class="left">有，并落实，得1分<br/>无，或沟通不畅，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.2.7"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.2.7"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.2.7"]}</textarea></td>
          <td>
            <span id="2.2.7Span" style="display:${empty fileMap["2.2.7"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.7"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.7Up" href="javascript:uploadFile('2.2.7');" style="color:blue">${empty fileMap["2.2.7"]?'':'重新'}上传</a>
            <a id="2.2.7Del" href="javascript:delFile('2.2.7');"  style="color:blue;${empty fileMap["2.2.7"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.7Value" name="filePath" value="${fileMap["2.2.7"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.2.8院级督导★</td>
          <td class="left">建立院级培训督导机制，并认真落实</td>
          <td class="left">查看原始资料，核查带教医师和培训对象</td>
          <td class="left">有且落实，得满分<br/>无，或未落实，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="2.2.8"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.2.8"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.2.8"]}</textarea></td>
          <td>
            <span id="2.2.8Span" style="display:${empty fileMap["2.2.8"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.2.8"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.2.8Up" href="javascript:uploadFile('2.2.8');" style="color:blue">${empty fileMap["2.2.8"]?'':'重新'}上传</a>
            <a id="2.2.8Del" href="javascript:delFile('2.2.8');"  style="color:blue;${empty fileMap["2.2.8"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.2.8Value" name="filePath" value="${fileMap["2.2.8"]}">
        </tr>
        <tr class="td_group">
          <td rowspan="5">2.3激励机制</td>
          <td class="left">2.3.1院长目标考核</td>
          <td class="left">院长目标考核与住培工作紧密挂钩</td>
          <td class="left" rowspan="2">查看本年度绩效考核记录、文件等原始资料</td>
          <td class="left">有，并落实，得2分<br/>无，或未落实，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="2.3.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.3.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.3.1"]}</textarea></td>
          <td>
            <span id="2.3.1Span" style="display:${empty fileMap["2.3.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.3.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.3.1Up" href="javascript:uploadFile('2.3.1');" style="color:blue">${empty fileMap["2.3.1"]?'':'重新'}上传</a>
            <a id="2.3.1Del" href="javascript:delFile('2.3.1');"  style="color:blue;${empty fileMap["2.3.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.3.1Value" name="filePath" value="${fileMap["2.3.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.3.2分管院长目标考核</td>
          <td class="left">分管院长目标考核与住培工作紧密挂钩</td>
          <td class="left">有，并落实，得1分<br/>无，或未落实，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.3.2"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.3.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.3.2"]}</textarea></td>
          <td>
            <span id="2.3.2Span" style="display:${empty fileMap["2.3.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.3.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.3.2Up" href="javascript:uploadFile('2.3.2');" style="color:blue">${empty fileMap["2.3.2"]?'':'重新'}上传</a>
            <a id="2.3.2Del" href="javascript:delFile('2.3.2');"  style="color:blue;${empty fileMap["2.3.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.3.2Value" name="filePath" value="${fileMap["2.3.2"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.3.3专业基地绩效考核</td>
          <td class="left">将住培过程考核和结业考核结果与科室绩效考核紧密挂钩</td>
          <td class="left" rowspan="3">查阅原始资料，抽查2～3名带教医师座谈与访谈</td>
          <td class="left">有，并落实，得1分<br/>无，或未落实，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.3.3"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.3.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.3.3"]}</textarea></td>
          <td>
            <span id="2.3.3Span" style="display:${empty fileMap["2.3.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.3.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.3.3Up" href="javascript:uploadFile('2.3.3');" style="color:blue">${empty fileMap["2.3.3"]?'':'重新'}上传</a>
            <a id="2.3.3Del" href="javascript:delFile('2.3.3');"  style="color:blue;${empty fileMap["2.3.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.3.3Value" name="filePath" value="${fileMap["2.3.3"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.3.4带教活动考核★</td>
          <td class="left">将专业基地负责人、教学主任、教学秘书和带教医师的带教活动纳入个人绩效考核</td>
          <td class="left">均落实，得满分<br/>有1项未落实，扣1分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="2.3.4"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.3.4"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.3.4"]}</textarea></td>
          <td>
            <span id="2.3.4Span" style="display:${empty fileMap["2.3.4"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.3.4"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.3.4Up" href="javascript:uploadFile('2.3.4');" style="color:blue">${empty fileMap["2.3.4"]?'':'重新'}上传</a>
            <a id="2.3.4Del" href="javascript:delFile('2.3.4');"  style="color:blue;${empty fileMap["2.3.4"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.3.4Value" name="filePath" value="${fileMap["2.3.4"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.3.5晋升激励</td>
          <td class="left">将住培工作与专业技术职务晋升挂钩</td>
          <td class="left">落实，得满分<br/>未落实，不得分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="2.3.5"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.3.5"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.3.5"]}</textarea></td>
          <td>
            <span id="2.3.5Span" style="display:${empty fileMap["2.3.5"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.3.5"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.3.5Up" href="javascript:uploadFile('2.3.5');" style="color:blue">${empty fileMap["2.3.5"]?'':'重新'}上传</a>
            <a id="2.3.5Del" href="javascript:delFile('2.3.5');"  style="color:blue;${empty fileMap["2.3.5"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.3.5Value" name="filePath" value="${fileMap["2.3.5"]}">
        </tr>
        <tr class="td_group">
          <td rowspan="3">2.4培训招收</td>
          <td class="left">2.4.1招收与容量</td>
          <td class="left">各专业基地实际招收人数不突破核定的培训容量，严格控制非紧缺专业招收规模</td>
          <td class="left">核实近1年各专业基地实际招生情况</td>
          <td class="left">符合要求，得1分<br/>
            非紧缺专业每1个专业招收人数<br/>
            超过计划数，从总分扣1分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.4.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.4.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.4.1"]}</textarea></td>
          <td>
            <span id="2.4.1Span" style="display:${empty fileMap["2.4.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.4.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.4.1Up" href="javascript:uploadFile('2.4.1');" style="color:blue">${empty fileMap["2.4.1"]?'':'重新'}上传</a>
            <a id="2.4.1Del" href="javascript:delFile('2.4.1');"  style="color:blue;${empty fileMap["2.4.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.4.1Value" name="filePath" value="${fileMap["2.4.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.4.2紧缺专业★</td>
          <td class="left">采取有力措施，积极完成当地行政主管部门下达的全科、儿科、精神科等紧缺专业招收计划</td>
          <td class="left">根据招收计划，核实上年度的紧缺专业实际招收人数，查看培训对象花名册</td>
          <td class="left">完成招收任务，得满分<br/>每1个专业未完成，扣1分<br/>（综合医院没有紧缺专业基地的此处不得分）</td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="2.4.2"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.4.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.4.2"]}</textarea></td>
          <td>
            <span id="2.4.2Span" style="display:${empty fileMap["2.4.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.4.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.4.2Up" href="javascript:uploadFile('2.4.2');" style="color:blue">${empty fileMap["2.4.2"]?'':'重新'}上传</a>
            <a id="2.4.2Del" href="javascript:delFile('2.4.2');"  style="color:blue;${empty fileMap["2.4.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.4.2Value" name="filePath" value="${fileMap["2.4.2"]}">
        </tr>
        <tr class="td_group">
          <td class="left">2.4.3委培和社会化招收★</td>
          <td class="left">不得仅培训本基地医院的住院医师，在培人员中应有相应数量的外单位委培对象或社会化培训对象，并逐年增加</td>
          <td class="left">查看上年度培训基地实际招收对象名单</td>
          <td class="left">均有招收，各得1分<br/>未招收，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="2.4.3"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["2.4.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["2.4.3"]}</textarea></td>
          <td>
            <span id="2.4.3Span" style="display:${empty fileMap["2.4.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["2.4.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="2.4.3Up" href="javascript:uploadFile('2.4.3');" style="color:blue">${empty fileMap["2.4.3"]?'':'重新'}上传</a>
            <a id="2.4.3Del" href="javascript:delFile('2.4.3');"  style="color:blue;${empty fileMap["2.4.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="2.4.3Value" name="filePath" value="${fileMap["2.4.3"]}">
        </tr>
        <tr class="td_group">
          <td rowspan="6">3.培训质量<br/>（20分）</td>
          <td rowspan="2">3.1师资培训</td>
          <td class="left">3.1.1院级培训</td>
          <td class="left">制定实施师资培训制度，所有带教医师均须培训上岗</td>
          <td class="left">查看培训资料、培训名单和证书</td>
          <td class="left">培训率为100%，得满分<br/>培训率≥90%，得1分<br/>培训率＜90%，不得分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="3.1.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["3.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.1.1"]}</textarea></td>
          <td>
            <span id="3.1.1Span" style="display:${empty fileMap["3.1.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.1Up" href="javascript:uploadFile('3.1.1');" style="color:blue">${empty fileMap["3.1.1"]?'':'重新'}上传</a>
            <a id="3.1.1Del" href="javascript:delFile('3.1.1');"  style="color:blue;${empty fileMap["3.1.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.1Value" name="filePath" value="${fileMap["3.1.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">3.1.2省级及以上培训</td>
          <td class="left">每个培训科室均有1名以上带教医师经过省级及以上师资培训</td>
          <td class="left">查看培训名单、证书，核查专业覆盖率</td>
          <td class="left">符合要求，得2分<br/>超过10%科室未参加，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="3.1.2"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["3.1.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.1.2"]}</textarea></td>
          <td>
            <span id="3.1.2Span" style="display:${empty fileMap["3.1.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.1.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.1.2Up" href="javascript:uploadFile('3.1.2');" style="color:blue">${empty fileMap["3.1.2"]?'':'重新'}上传</a>
            <a id="3.1.2Del" href="javascript:delFile('3.1.2');"  style="color:blue;${empty fileMap["3.1.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.1.2Value" name="filePath" value="${fileMap["3.1.2"]}">
        </tr>
        <tr class="td_group">
          <td rowspan="2">3.2师资评价</td>
          <td class="left">3.2.1带教医师评价★</td>
          <td class="left">建立培训对象对带教医师测评机制，并将测评结果纳入带教医师总体评价</td>
          <td class="left">查阅测评原始资料，现场组织带教医师、护士、管理人员和培训对象进行测评</td>
          <td class="left">有机制，且得分率≥80%，得满分<br/>
            有机制，且得分率≥60%，得1分<br/>
            无机制，或得分率＜60%，不得分
          </td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="3.2.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["3.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.2.1"]}</textarea></td>
          <td>
            <span id="3.2.1Span" style="display:${empty fileMap["3.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.2.1Up" href="javascript:uploadFile('3.2.1');" style="color:blue">${empty fileMap["3.2.1"]?'':'重新'}上传</a>
            <a id="3.2.1Del" href="javascript:delFile('3.2.1');"  style="color:blue;${empty fileMap["3.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.2.1Value" name="filePath" value="${fileMap["3.2.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">3.2.2同等施教★</td>
          <td class="left">带教医师对外单位委派培训对象、面向社会招收的培训对象与本院培训对象一视同仁，使其享受同等教学资源和培训机会</td>
          <td class="left">组织三类人员进行现场访谈</td>
          <td class="left">同等施教，得满分<br/>未同等施教，不得分</td>
          <td>4</td>
          <input type="hidden" name="orderNumber" value="3.2.2"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["3.2.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.2.2"]}</textarea></td>
          <td>
            <span id="3.2.2Span" style="display:${empty fileMap["3.2.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.2.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.2.2Up" href="javascript:uploadFile('3.2.2');" style="color:blue">${empty fileMap["3.2.2"]?'':'重新'}上传</a>
            <a id="3.2.2Del" href="javascript:delFile('3.2.2');"  style="color:blue;${empty fileMap["3.2.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.2.2Value" name="filePath" value="${fileMap["3.2.2"]}">
        </tr>
		    <tr class="td_group">
          <td rowspan="2">3.3培训对象评价</td>
          <td class="left">3.3.1综合评价</td>
          <td class="left">带教医师、科室护士、患者、其他有关专业人员和管理人员对培训对象实施综合评价，有针对性地改进培训带教和有关管理工作</td>
          <td class="left">查阅原始资料，现场组织部分带教医师、护士、患者和管理人员对培训对象进行评价</td>
          <td class="left">合格率≥90%，得满分<br/>
            合格率≥80%，得2分<br/>
            合格率＜70%，不得分</td>
          <td>3</td>
              <input type="hidden" name="orderNumber" value="3.3.1"/>
              <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["3.3.1"]}"/></td>
              <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.3.1"]}</textarea></td>
              <td>
            <span id="3.3.1Span" style="display:${empty fileMap["3.3.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.3.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
                <a id="3.3.1Up" href="javascript:uploadFile('3.3.1');" style="color:blue">${empty fileMap["3.3.1"]?'':'重新'}上传</a>
                <a id="3.3.1Del" href="javascript:delFile('3.3.1');"  style="color:blue;${empty fileMap["3.3.1"]?'display:none':''}">删除</a>
                <input type="hidden" id="3.3.1Value" name="filePath" value="${fileMap["3.3.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">3.3.2过程考核★</td>
          <td class="left">出科考核、年度考核等严格规范，结果客观公正</td>
          <td class="left">查看过程考核相关原始记录</td>
          <td class="left">记录齐全，组织严格规范，得满分；有记录，但不太规范，得2分；无记录或不规范，不得分</td>
          <td>5</td>
          <input type="hidden" name="orderNumber" value="3.3.2"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["3.3.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["3.3.2"]}</textarea></td>
          <td>
            <span id="3.3.2Span" style="display:${empty fileMap["3.3.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["3.3.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="3.3.2Up" href="javascript:uploadFile('3.3.2');" style="color:blue">${empty fileMap["3.3.2"]?'':'重新'}上传</a>
            <a id="3.3.2Del" href="javascript:delFile('3.3.2');"  style="color:blue;${empty fileMap["3.3.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="3.3.2Value" name="filePath" value="${fileMap["3.3.2"]}">
        </tr>
        <tr class="td_group">
          <th rowspan="8">4.保障措施（25分）</th>
          <td>4.1专项经费</td>
          <td class="left">4.1.1专账管理</td>
          <td class="left">建立住培经费专项账户，规范使用中央、地方财政补助经费，包括中央财政的基地能力建设500万元、年人均3万元经常性补助以及地方财政补助</td>
          <td class="left">查看本年度财务报表等相关资料</td>
          <td class="left">符合要求，得满分<br/>有1项不符合要求，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="4.1.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["4.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.1.1"]}</textarea></td>
          <td>
            <span id="4.1.1Span" style="display:${empty fileMap["4.1.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.1.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.1.1Up" href="javascript:uploadFile('4.1.1');" style="color:blue">${empty fileMap["4.1.1"]?'':'重新'}上传</a>
            <a id="4.1.1Del" href="javascript:delFile('4.1.1');"  style="color:blue;${empty fileMap["4.1.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.1.1Value" name="filePath" value="${fileMap["4.1.1"]}">
        </tr>
        <tr class="td_group">
          <td>4.2教学经费</td>
          <td class="left">4.2.1教学补助</td>
          <td class="left">落实上级财政专项补助经费用于培训基地教学实践活动，包括带教费、小讲课费等</td>
          <td class="left">查看本年度财务报表，访谈有关人员</td>
          <td class="left">符合要求，专款专用，得满分<br/>不符合要求，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="4.2.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["4.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.2.1"]}</textarea></td>
          <td>
            <span id="4.2.1Span" style="display:${empty fileMap["4.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.2.1Up" href="javascript:uploadFile('4.2.1');" style="color:blue">${empty fileMap["4.2.1"]?'':'重新'}上传</a>
            <a id="4.2.1Del" href="javascript:delFile('4.2.1');"  style="color:blue;${empty fileMap["4.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.2.1Value" name="filePath" value="${fileMap["4.2.1"]}">
        </tr>
        <tr class="td_group">
          <td rowspan="2">4.3培训对象补助</td>
          <td class="left">4.3.1社会招收对象补助★</td>
          <td class="left">面向社会招收的培训对象生活补助标准参照培训基地同等条件住院医师工资水平确定，由培训基地依考核发放</td>
          <td class="left">查看财务部门提供本年度待遇发放流水单,抽查3～5名培训对象收入情况</td>
          <td class="left">政策执行到位，得满分<br/>未按政策执行，不得分</td>
          <td>6</td>
          <input type="hidden" name="orderNumber" value="4.3.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["4.3.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.3.1"]}</textarea></td>
          <td>
            <span id="4.3.1Span" style="display:${empty fileMap["4.3.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.3.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.3.1Up" href="javascript:uploadFile('4.3.1');" style="color:blue">${empty fileMap["4.3.1"]?'':'重新'}上传</a>
            <a id="4.3.1Del" href="javascript:delFile('4.3.1');"  style="color:blue;${empty fileMap["4.3.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.3.1Value" name="filePath" value="${fileMap["4.3.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">4.3.2委派对象补助★</td>
          <td class="left">委派单位发放的工资低于培训基地同等条件住院医师工资水平的部分，由培训基地按照本院同等条件住院医师工资水平依考核发放</td>
          <td class="left">查看财务部门提供本年度工资发放流水单,抽查3～5名培训对象收入情况</td>
          <td class="left">政策执行到位，得满分<br/>未按政策执行，不得分</td>
          <td>6</td>
          <input type="hidden" name="orderNumber" value="4.3.2"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["4.3.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.3.2"]}</textarea></td>
          <td>
            <span id="4.3.2Span" style="display:${empty fileMap["4.3.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.3.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.3.2Up" href="javascript:uploadFile('4.3.2');" style="color:blue">${empty fileMap["4.3.2"]?'':'重新'}上传</a>
            <a id="4.3.2Del" href="javascript:delFile('4.3.2');"  style="color:blue;${empty fileMap["4.3.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.3.2Value" name="filePath" value="${fileMap["4.3.2"]}">
        </tr>
        <tr class="td_group">
          <td>4.4住宿条件</td>
          <td class="left">4.4.1住宿或住宿补贴</td>
          <td class="left">为培训对象提供免费或低收费住宿，或提供住宿补贴</td>
          <td class="left">现场考查，访谈培训对象</td>
          <td class="left">提供免费或低收费住宿，满足基本住宿条件，或提供适当住宿补贴，得满分<br/>
            无，不得分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="4.4.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["4.4.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.4.1"]}</textarea></td>
          <td>
            <span id="4.4.1Span" style="display:${empty fileMap["4.4.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.4.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.4.1Up" href="javascript:uploadFile('4.4.1');" style="color:blue">${empty fileMap["4.4.1"]?'':'重新'}上传</a>
            <a id="4.4.1Del" href="javascript:delFile('4.4.1');"  style="color:blue;${empty fileMap["4.4.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.4.1Value" name="filePath" value="${fileMap["4.4.1"]}">
        </tr>
        <tr class="td_group">
          <td rowspan="3">4.5相关措施</td>
          <td class="left">4.5.1签订培训协议</td>
          <td class="left">招收的培训对象与培训基地按规定签订培训协议，约定有关事项。培训基地不得聘用培训中和服务期内的非基地单位委派培训对象</td>
          <td class="left">查看相关资料、协议原件，访谈培训对象</td>
          <td class="left">签订协议，并严格落实，得2分<br/>
            无，或未落实，不得分</td>
          <td>2</td>
          <input type="hidden" name="orderNumber" value="4.5.1"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["4.5.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.5.1"]}</textarea></td>
          <td>
            <span id="4.5.1Span" style="display:${empty fileMap["4.5.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.5.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.5.1Up" href="javascript:uploadFile('4.5.1');" style="color:blue">${empty fileMap["4.5.1"]?'':'重新'}上传</a>
            <a id="4.5.1Del" href="javascript:delFile('4.5.1');"  style="color:blue;${empty fileMap["4.5.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.5.1Value" name="filePath" value="${fileMap["4.5.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">4.5.2资助参加社会保障</td>
          <td class="left">培训基地资助面向社会招收的培训对象参加社会保险</td>
          <td class="left">查看社会保障卡号进行核查，访谈培训对象</td>
          <td class="left">有，得满分<br/>无，不得分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="4.5.2"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["4.5.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.5.2"]}</textarea></td>
          <td>
            <span id="4.5.2Span" style="display:${empty fileMap["4.5.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.5.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.5.2Up" href="javascript:uploadFile('4.5.2');" style="color:blue">${empty fileMap["4.5.2"]?'':'重新'}上传</a>
            <a id="4.5.2Del" href="javascript:delFile('4.5.2');"  style="color:blue;${empty fileMap["4.5.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.5.2Value" name="filePath" value="${fileMap["4.5.2"]}">
        </tr>
        <tr class="td_group">
          <td class="left">4.5.3医师资格考试报名和执业注册</td>
          <td class="left">协助培训对象报名参加医师资格考试、办理有关执业注册事宜</td>
          <td class="left">查看名单，访谈培训对象</td>
          <td class="left">有，得1分<br/>无，不得分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="4.5.3"/>
          <td><input type="text" name="score" class="qtext sum1" value="${scoreMap["4.5.3"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["4.5.3"]}</textarea></td>
          <td>
            <span id="4.5.3Span" style="display:${empty fileMap["4.5.3"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["4.5.3"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="4.5.3Up" href="javascript:uploadFile('4.5.3');" style="color:blue">${empty fileMap["4.5.3"]?'':'重新'}上传</a>
            <a id="4.5.3Del" href="javascript:delFile('4.5.3');"  style="color:blue;${empty fileMap["4.5.3"]?'display:none':''}">删除</a>
            <input type="hidden" id="4.5.3Value" name="filePath" value="${fileMap["4.5.3"]}">
        </tr>
        <tr class="td_group">
          <td colspan="6" class="right">合计</td>
          <td>100</td>
          <input type="hidden" name="orderNumber" value="sumScore1"/>
          <td><input id="inputSumScore1" readonly name="score" class="qtext" value="${scoreMap["sumScore1"]}"/></td>
          <td></td>
        </tr>
        <tr class="td_group">
          <th rowspan="8">5.加分项目<br/>（10分）</th>
          <td>5.1培训合格率</td>
          <td class="left">5.1.1结业考核通过率</td>
          <td class="left">培训对象第1次参加结业考核的通过率<br/>（通过率=当年第1次参加结业考核通过的人数/当年结业总人数）</td>
          <td class="left">查看结业考核成绩记录</td>
          <td class="left">通过率≥本省（区、市）平均水平，得1分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="5.1.1"/>
          <td><input type="text" name="score" class="qtext sum2" value="${scoreMap["5.1.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["5.1.1"]}</textarea></td>
          <td>
            <span id="5.1.1Span" style="display:${empty fileMap["5.1.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["5.1.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="5.1.1Up" href="javascript:uploadFile('5.1.1');" style="color:blue">${empty fileMap["5.1.1"]?'':'重新'}上传</a>
            <a id="5.1.1Del" href="javascript:delFile('5.1.1');"  style="color:blue;${empty fileMap["5.1.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="5.1.1Value" name="filePath" value="${fileMap["5.1.1"]}">
        </tr>
        <tr class="td_group">
          <td>5.2师资培训</td>
          <td class="left">5.2.1师资培训</td>
          <td class="left">举办省级及以上骨干师资培训班</td>
          <td class="left">查看相关资料（培训资料、培训名单和照片等）</td>
          <td class="left">有，得1分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="5.2.1"/>
          <td><input type="text" name="score" class="qtext sum2" value="${scoreMap["5.2.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["5.2.1"]}</textarea></td>
          <td>
            <span id="5.2.1Span" style="display:${empty fileMap["5.2.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["5.2.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="5.2.1Up" href="javascript:uploadFile('5.2.1');" style="color:blue">${empty fileMap["5.2.1"]?'':'重新'}上传</a>
            <a id="5.2.1Del" href="javascript:delFile('5.2.1');"  style="color:blue;${empty fileMap["5.2.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="5.2.1Value" name="filePath" value="${fileMap["5.2.1"]}">
        </tr>
        <tr class="td_group">
          <td>5.3教学激励</td>
          <td class="left">5.3.1奖励专项</td>
          <td class="left">设置奖励专项，奖励优秀带教医师和参培住院医师</td>
          <td class="left" rowspan="2">查看相关资料，访谈带教医师和培训对象</td>
          <td class="left">有，得1分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="5.3.1"/>
          <td><input type="text" name="score" class="qtext sum2" value="${scoreMap["5.3.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["5.3.1"]}</textarea></td>
          <td>
            <span id="5.3.1Span" style="display:${empty fileMap["5.3.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["5.3.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="5.3.1Up" href="javascript:uploadFile('5.3.1');" style="color:blue">${empty fileMap["5.3.1"]?'':'重新'}上传</a>
            <a id="5.3.1Del" href="javascript:delFile('5.3.1');"  style="color:blue;${empty fileMap["5.3.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="5.3.1Value" name="filePath" value="${fileMap["5.3.1"]}">
        </tr>
        <tr class="td_group">
          <td>5.4学员导师</td>
          <td class="left">5.4.1建立导师制</td>
          <td class="left">为每位培训对象配置1名相对固定的带教医师作为导师，负责培训期间的全程指导</td>
          <td class="left">有，得1分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="5.4.1"/>
          <td><input type="text" name="score" class="qtext sum2" value="${scoreMap["5.4.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["5.4.1"]}</textarea></td>
          <td>
            <span id="5.4.1Span" style="display:${empty fileMap["5.4.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["5.4.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="5.4.1Up" href="javascript:uploadFile('5.4.1');" style="color:blue">${empty fileMap["5.4.1"]?'':'重新'}上传</a>
            <a id="5.4.1Del" href="javascript:delFile('5.4.1');"  style="color:blue;${empty fileMap["5.4.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="5.4.1Value" name="filePath" value="${fileMap["5.4.1"]}">
        </tr>
        <tr class="td_group">
          <td>5.5教学研究</td>
          <td class="left">5.5.1课题研究</td>
          <td class="left">开展住院医师规范化培训相关课题研究</td>
          <td class="left">查看相关论文、课题研究报告等</td>
          <td class="left">有，得1分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="5.5.1"/>
          <td><input type="text" name="score" class="qtext sum2" value="${scoreMap["5.5.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["5.5.1"]}</textarea></td>
          <td>
            <span id="5.5.1Span" style="display:${empty fileMap["5.5.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["5.5.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="5.5.1Up" href="javascript:uploadFile('5.5.1');" style="color:blue">${empty fileMap["5.5.1"]?'':'重新'}上传</a>
            <a id="5.5.1Del" href="javascript:delFile('5.5.1');"  style="color:blue;${empty fileMap["5.5.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="5.5.1Value" name="filePath" value="${fileMap["5.5.1"]}">
        </tr>
        <tr class="td_group">
          <td rowspan="2">5.6对口支援</td>
          <td class="left">5.6.1省域内</td>
          <td class="left">开展对口支援工作，接收贫困县等欠发达地区培训对象</td>
          <td class="left" rowspan="2">查看相关记录和接收的培训对象名单及协议</td>
          <td class="left">有，得1分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="5.6.1"/>
          <td><input type="text" name="score" class="qtext sum2" value="${scoreMap["5.6.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["5.6.1"]}</textarea></td>
          <td>
            <span id="5.6.1Span" style="display:${empty fileMap["5.6.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["5.6.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="5.6.1Up" href="javascript:uploadFile('5.6.1');" style="color:blue">${empty fileMap["5.6.1"]?'':'重新'}上传</a>
            <a id="5.6.1Del" href="javascript:delFile('5.6.1');"  style="color:blue;${empty fileMap["5.6.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="5.6.1Value" name="filePath" value="${fileMap["5.6.1"]}">
        </tr>
        <tr class="td_group">
          <td class="left">5.6.2省域间</td>
          <td class="left">开展对口支援，派出带教医师，接收中西部特别是西部边疆民族地区培训对象</td>
          <td class="left">有，得1分</td>
          <td>1</td>
          <input type="hidden" name="orderNumber" value="5.6.2"/>
          <td><input type="text" name="score" class="qtext sum2" value="${scoreMap["5.6.2"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["5.6.2"]}</textarea></td>
          <td>
            <span id="5.6.2Span" style="display:${empty fileMap["5.6.2"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["5.6.2"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="5.6.2Up" href="javascript:uploadFile('5.6.2');" style="color:blue">${empty fileMap["5.6.2"]?'':'重新'}上传</a>
            <a id="5.6.2Del" href="javascript:delFile('5.6.2');"  style="color:blue;${empty fileMap["5.6.2"]?'display:none':''}">删除</a>
            <input type="hidden" id="5.6.2Value" name="filePath" value="${fileMap["5.6.2"]}">
        </tr>
        <tr class="td_group">
          <td>5.7招收工作</td>
          <td class="left">5.7.1紧缺专业招收</td>
          <td class="left">创新工作办法，采取有力措施，超额完成全科、儿科、精神科专业招收计划</td>
          <td class="left">查看培训对象花名册</td>
          <td class="left">3个专业均超额完成，得3分<br/>
            2个专业超额完成，得2分<br/>
            1个专业超额完成，得1分</td>
          <td>3</td>
          <input type="hidden" name="orderNumber" value="5.7.1"/>
          <td><input type="text" name="score" class="qtext sum2" value="${scoreMap["5.7.1"]}"/></td>
          <td><textarea rows='1' name="deductMarks" class="qtext">${marksMap["5.7.1"]}</textarea></td>
          <td>
            <span id="5.7.1Span" style="display:${empty fileMap["5.7.1"]?'none':''} ">
			  <a href="${sysCfgMap['upload_base_url']}${fileMap["5.7.1"]}" target="_blank" style="color:blue">查看</a>
			</span>
            <a id="5.7.1Up" href="javascript:uploadFile('5.7.1');" style="color:blue">${empty fileMap["5.7.1"]?'':'重新'}上传</a>
            <a id="5.7.1Del" href="javascript:delFile('5.7.1');"  style="color:blue;${empty fileMap["5.7.1"]?'display:none':''}">删除</a>
            <input type="hidden" id="5.7.1Value" name="filePath" value="${fileMap["5.7.1"]}">
        </tr>
      </tbody>
      <tbody>
        <tr class="td_group">
          <td colspan="6" class="right">合计</td>
          <td>10</td>
          <input type="hidden" name="orderNumber" value="sumScore2"/>
          <td><input id="inputSumScore2" readonly name="score" class="qtext" value="${scoreMap["sumScore2"]}"/></td>
          <td></td>
        </tr>
        <tr class="td_group">
          <td colspan="6" class="right">总计</td>
          <td>110</td>
          <input type="hidden" name="orderNumber" value="sumScore3"/>
          <td><input id="inputSumScore3" readonly name="score" class="qtext" value="${scoreMap["sumScore3"]}"/></td>
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
          <td colspan="10" style="text-align: left">
            备注：<br/>
            1.一级指标4项；二级指标17项；三级指标44项，其中核心指标13项，100分。另设加分项，10分，合计110分。<br/>
            2.指标中所有规章制度，专指住院医师规范化培训相关制度。<br/>
            3.随机抽查对象优先选择委托培训对象和面向社会招收的培训对象，如果没有，可考虑本基地培训对象。<br/>
            4.现场评估时详细填写存在的问题和扣分原因。
          </td>
        </tr>
        <tr class="td_group">
          <td colspan="10">
            专家姓名：
            <input type="hidden" name="orderNumber" value="sign"/>
            <input type="text" name="deductMarks" class="qtext" value="${marksMap["sign"]}"/>
            &#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
            评估日期：
            <input type="text" name="score" class="qtext" value="${scoreMap["sign"]}" id="signDate"
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