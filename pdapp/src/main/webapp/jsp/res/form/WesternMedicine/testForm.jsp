   		<table class="basic" width="100%">
   			<tr>
                <th colspan="6" style="text-align: left;">&#12288;登记信息</th>
            </tr>
   			<tbody class="${d}">
	             <tr>
	             	<td style="width: 20%;"><font color="red">*</font>病人姓名：</td>
	                <td style="width: 30%;">
	                    <input class="validate[required] " name="patientName" type="text" value="${formDataMap['patientName']}" style="width: 150px;"/>
	                    <label>张三</label>
					</td>
					<td style="width: 20%;"><font color="red">*</font>住院号：</td>
	                <td style="width: 30%;">
	                    <input class="validate[required] " name="hospitalNumbers" type="text" value="${formDataMap['hospitalNumbers']}" style="width: 150px;"/>
	                    <label>010</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
						<div fu="s" name="filea" 
							value="a"
							fileName="a"
						></div>
	
					</td>
				</tr>
   			</tbody>
   			<tbody class="${t}">
				<tr>
					<td style="width: 100px;"><font color="red">*</font>疾病名称：</td>
					<td>
						<script type="text/javascript">
							$(function(){
								$('#123555').append($('#123555').html())
							})
						</script>
						<input class="validate[required]  dateInput"  name="diseaseName" type="text" value="${formDataMap['diseaseName']}" style="width: 150px;"/>
						<label>感冒</label>
					</td>
					<td style="width: 100px;"><font color="red">*</font>操作时间：</td>
					<td id="123555">
						<input style="width: 150px;" class="validate[required] dateInput" fmt="yyyy-MM" name="operTime" type="text" value="${formDataMap['operTime']}"/>
						<label>2016-02-26</label>
					</td>
				</tr>
   			</tbody>
   			<tbody class="${h}">
	             <tr>
					<td style="width: 100px;"><font color="red">*</font>诊断类型：</td>
	                <td >
	                    <select name="diagnoseType" style="width: 154px;">
		                    <option value="主要诊断" <c:if test="${formDataMap['diagnoseType']=='主要诊断'}">selected</c:if> >主要诊断</option>
		                    <option value="次要诊断" <c:if test="${formDataMap['diagnoseType']=='次要诊断'}">selected</c:if>>次要诊断</option>
		                    <option value="并行诊断" <c:if test="${formDataMap['diagnoseType']=='并行诊断'}">selected</c:if>>并行诊断</option>
	                    </select>
						<label>主要诊断</label>
					</td>
					<td style="width: 100px;"><font color="red">*</font>诊断：</td>
					<td>
						<input class="validate[required] " name="diagnose" type="text" value="${formDataMap['diagnose']}" style="width: 150px;"/>
						<label>ok</label>
					</td>
	             </tr>
   			</tbody>
   			<tbody class="${m}">
	             <tr>
	             	<td style="width: 100px;">抢救措施：</td>
					<td colspan="3">
						<div fu="m" name="fileb"
							value="a"
							fileName="a"
						></div>
					</td>
	             </tr>
	             <tr>
	             	<td style="width: 100px;">死亡原因分析：</td>
					<td colspan="3">
						<input name="dieReasonParse" type="text" value="${formDataMap['dieReasonParse']}" style="width: 493px;"/>
						<label>无</label>
					</td>
	             </tr>
	             <tr>
	             	<td style="width: 100px;">记录：</td>
					<td colspan="3">
						<textarea style="width:495px;	border:1px solid #bdbebe;	height:100px;	margin:5px 5px 5px 0px" name="caseRecord">${formDataMap['caseRecord']}</textarea>
						<label>感冒</label>
					</td>
	             </tr>
   			</tbody>
      </table>