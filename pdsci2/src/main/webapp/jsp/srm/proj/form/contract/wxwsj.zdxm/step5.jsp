
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
<script type="text/javascript">
	function nextOpt(step){
		if(false==$("#projForm").validationEngine("validate")){
			return false;
		}
		var form = $('#projForm');
		var action = form.attr('action');
		action+="?nextPageName="+step;
		form.attr("action" , action);
		form.submit();
	}
</script>
</c:if>

	<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
		id="projForm">
		<input type="hidden" id="pageName" name="pageName" value="step5" /> 
		<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
		<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
		<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

		<font style="font-size: 14px; font-weight:bold;color: #333; ">四、项目保障条件</font><br/>
		<table class="basic" style="width: 100%; margin-top: 10px;" >
			<tr>
				<th style="text-align: left;padding-left: 20px;">（项目单位已具备的工作基础和项目进行过程中须提供的相关保障条件）</th>
			</tr>
			<tr>
				<td style="text-align:left;">
		     		<textarea placeholder="" class="xltxtarea"  name="ensureCondition">${resultMap.ensureCondition}</textarea>
		     	</td>
			</tr>
		 </table>
        <font style="font-size: 14px; font-weight:bold;color: #333; ">五、项目目标与成果</font><br/>
		 <table class="basic" style="width: 100%;margin-top: 10px;">
			<tr>
				<th style="text-align: left;padding-left: 20px;">(预期的项目目标、考核指标、取得成果及提供成果的形式，包括主要技术指标、形成的专利、标准、新技术、新产品、新装置、论文专著等数量、指标及其水平等，考核指标应当明确具体、量化可考核）</th>
			</tr>          
			<tr>
				<td style="text-align:left;">
		     		<textarea placeholder="" class="xltxtarea" style="height: 300px;" name="targetAndFruit">${resultMap.targetAndFruit}</textarea>
		     	</td>
			</tr>
		 </table>

	</form>

   	<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
		<div align="center" style="margin-top: 10px">
		    <input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
		    <input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="下一步"/>
       	</div>
    </c:if>

		