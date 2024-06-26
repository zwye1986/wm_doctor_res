
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<c:if test="${!param.head}">
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
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
</c:if>
<style type="text/css">
	table td{text-align: left;}
</style>
<script type="text/javascript">
function apply(){
	jboxOpen("<s:url value='/jsp/gyxjgl/student/application.jsp'/>","异动申请",900,500);
}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table class="basic" style="width: 100%; margin-top: 10px;">
			<tr>
				<td style="color: red">
				<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
					学籍信息已锁定，当前时间范围内不可修改!
				</c:if>
					<div style="float: right;margin-top: 3px;">
						<input type="button" name="" class="search" value="异动申请" onclick="apply();"/>
					<span>
					<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
						<input type="button" name="" class="search" value="打&#12288;印" />
					</c:if>
					<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
						<input type="button" name="" class="search" value="保&#12288;存" />
					</c:if>
					</span>
				</div>
				</td>
			</tr>
		</table>
		
		<table class="basic" style="width:100%; margin-top: 10px; margin-bottom: 10px;">
			<tr><th colspan="10" style="text-align: left;padding-left: 20px;">学籍信息</th></tr>
			<tr>
				<th style="text-align: right;width: 24%;" ><span style="color: red;">*</span>&#12288;学号&#12288;</th>
				<td style="width: 15%;"><label >21400021</label>&#12288;</td>
				<td  rowspan="5" style="width: 12%; text-align:center;margin-top: 2px;" >
					<img style="width: 100px; height: 130px;" src="<s:url value='/jsp/gyxjgl/img/a.bmp'/>"/><br>
					<a>上传头像</a>
				</td>
				<th  style="width: 24%; text-align: right;">证件类型&#12288;</th>
				<td style="width: 25%;"><label>身份证</label></td>
			</tr>
			<tr>	
				<th style="text-align: right;"><span style="color: red;">*</span>&#12288;姓名&#12288;</th>
				<td><label>林伞</label>&#12288;</td>
				<th style="text-align: right;">证件号码&#12288;</th>
				<td><label>321322199407015689</label></td>
			</tr>
			<tr>	
				<th style="text-align: right;">考生姓名拼音&#12288;</th>
				<td><label>linsan</label></td>
				<th style="text-align: right;">民族码&#12288;</th>
				<td><label>1</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">考生编号&#12288;</th>
				<td><label>1</label>&#12288;</td>
				<th style="text-align: right;">民族&#12288;</th>
				<td><label>汉</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">入学年级&#12288;</th>
				<td><label>2008</label></td>
				<th style="text-align: right;">婚姻状况&#12288;</th>
				<td>
					<select style="width: 144px;" class="inputText">
						<option>未婚</option>
						<option>已婚</option>
						<option>丧偶</option>
						<option>离婚</option>
						<option>其他</option>
					</select>
				</td>
			</tr>
			<tr>
				<th style="text-align: right;">班级&#12288;</th>
				<td colspan="2"><label>7</label></td>
				<th style="text-align: right;">婚育情况&#12288;</th>
				<td><input type="text" name="" class="inputText" style="width: 140px;text-align: left;" value="未育"></td>
			</tr>
			<tr>
				<th style="text-align: right;">出生日期&#12288;</th>
				<td colspan="2"><label>1994-07-01</label></td>
				<th style="text-align: right;">政治面貌码&#12288;</th>
				<td><label>1</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">性别码&#12288;</th>
				<td colspan="2"><label>1</label></td>
				<th style="text-align: right;">政治面貌&#12288;</th>
				<td>
					<select style="width: 144px;" class="inputText">
						<option>中国共产党党员</option>
						<option>中国共产党预备党员</option>
						<option>中国共产主义青年团团员</option>
						<option>中国国民党革命委员会会员</option>
						<option>中国民主同盟盟员</option>
						<option>中国民主建国会会员</option>
						<option>中国民主促进会会员</option>
						<option>中国农工民主党党员</option>
						<option>中国致公党党员</option>
						<option>九三学社社员</option>
						<option>台湾民主自治同盟盟员</option>
						<option>无党派民主人士</option>
						<option>群众</option>
					</select>
				</td>
			</tr>
			<tr>
				<th style="text-align: right;"><span style="color: red;">*</span>&#12288;性别&#12288;</th>
				<td colspan="2"><label>女</label></td>
				<th style="text-align: right;">籍贯&#12288;</th>
				<td><label>江苏宿迁</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">户口所在地&#12288;</th>
				<td colspan="2"><label>宿迁</label>&#12288;</td>
				<th style="text-align: right;">户口所在详细地址&#12288;</th>
				<td><label>宿迁金地华园</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">档案所在地码&#12288;</th>
				<td colspan="2"><label>1</label>&#12288;</td>
				<th style="text-align: right;">原档案所在单位&#12288;</th>
				<td><label>希曼</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">原档案所在单位邮编&#12288;</th>
				<td colspan="2"><label>210012</label></td>
				<th style="text-align: right;">原学习或工作单位&#12288;</th>
				<td><label>南京西南</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">考生来源&#12288;</th>
				<td colspan="2"><label>地点</label></td>
				<th style="text-align: right;">录取类别&#12288;</th>
				<td><label>非定向</label></td>
			</tr>
			<tr>
				<th style="text-align: right;"><span style="color: red;">*</span>&#12288;培养层次&#12288;</th>
				<td colspan="2"><label>硕士研究生</label></td>
				<th style="text-align: right;" ><span style="color: red;">*</span>&#12288;培养单位&#12288;</th>
				<td><label>第三附属医院</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">院系部&#12288;</th>
				<td colspan="2"><label>一院</label></td>
				<th style="text-align: right;"><span style="color: red;">*</span>&#12288;专业名称&#12288;</th>
				<td><label>内科学（肾病）</label></td>
			</tr>
			<tr>
				<th style="text-align: right;"><span style="color: red;">*</span>&#12288;研究方向&#12288;</th>
				<td colspan="2"><input type="text" name="" class="inputText" style="width: 140px;text-align: left;" value="0102"></td>
				<th style="text-align: right;">是否5+3培养模式&#12288;</th>
				<td><label>是</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">导师一&#12288;</th>
				<td colspan="2"><label>张少华</label></td>
				<th style="text-align: right;">导师二&#12288;</th>
				<td><label>周建军</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">学习形式&#12288;</th>
				<td colspan="2"><label>一对一</label></td>
				<th style="text-align: right;">在校状态&#12288;</th>
				<td><label>毕业</label></td>
			</tr>
			<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
			<tr>
				<th style="text-align: right;"><span style="color: blue;">毕业时间</span>&#12288;</th>
				<td colspan="2"><input type="text" name="" style="color: blue;text-align: left;" class="inputText" value="2014-06-05"/></td>
				<th style="text-align: right;"><span style="color: blue;">毕业证书编号</span>&#12288;</th>
				<td><input type="text" name="" class="inputText" style="color: blue;text-align: left;" value="11254"/></td>
			</tr>
			<tr>
				<th style="text-align: right;"><span style="color: blue;">授予学位时间</span>&#12288;</th>
				<td colspan="2"><input type="text" class="inputText" name="" style="color: blue;text-align: left;" value="2014-06-04"/></td>
				<th style="text-align: right;"><span style="color: blue;">授予学位证书编号</span>&#12288;</th>
				<td><input type="text" name="" class="inputText" style="color: blue;text-align: left;" value="11245"/></td>
			</tr>
			<tr>
				<th style="text-align: right;"><span style="color: blue;"><span style="color: blue;">授予学位类别</span></span>&#12288;</th>
				<td colspan="4"><input type="text" class="inputText" name="" style="color: blue;text-align: left;" value="学位"/></td>
			</tr>
			</c:if>
			<tr>
				<th style="text-align: right;">学籍状态&#12288;</th>
				<td colspan="2"><label>考</label></td>
				<th style="text-align: right;">招生季节&#12288;</th>
				<td><label>春</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">缴费账号&#12288;</th>
				<td colspan="2"><label>12546</label></td>
				<th style="text-align: right;">培养类型&#12288;</th>
				<td><label>专业学位</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">外国语名称&#12288;</th>
				<td colspan="2"><label>英语一</label></td>
				<th style="text-align: right;">外国语成绩&#12288;</th>
				<td><label>88</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">政治理论&#12288;</th>
				<td colspan="2"><label>思想政治理论</label></td>
				<th style="text-align: right;">政治理论成绩&#12288;</th>
				<td><label>89</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">业务课一&#12288;</th>
				<td colspan="2"><label>西医综合</label></td>
				<th style="text-align: right;">业务课一成绩&#12288;</th>
				<td><label>69</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">业务课二&#12288;</th>
				<td colspan="2"><label>无</label></td>
				<th style="text-align: right;">业务课二成绩&#12288;</th>
				<td><label>0</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">加试科一&#12288;</th>
				<td colspan="2"><label>无</label></td>
				<th style="text-align: right;">加试科一成绩&#12288;</th>
				<td><label>0</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">加试科二&#12288;</th>
				<td colspan="2"><label>无</label></td>
				<th style="text-align: right;">加试科二成绩&#12288;</th>
				<td><label>0</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">复试成绩&#12288;</th>
				<td colspan="2"><label>0</label></td>
				<th style="text-align: right;">总分&#12288;</th>
				<td><label></label>222</td>
			</tr>
			<tr>
				<th style="text-align: right;">是否破格&#12288;</th>
				<td colspan="2"><label>否</label></td>
				<th style="text-align: right;">是否推免生&#12288;</th>
				<td><label>否</label></td>
			</tr>
<!-- 			////////////////////////// -->
			<tr> 
				<th style="text-align: right;">身高&#12288;</th>
				<td colspan="2"><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="160"></td>
				
				<th style="text-align: right;">加入年月日&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="2008-02-03"></td>
			</tr>
			<tr>
				<th style="text-align: right;">是否有医师资格证&#12288;</th>
				<td colspan="2"><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="是"></td>
				<th style="text-align: right;">血型&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="B"></td>
			</tr>
			<tr>
				<th style="text-align: right;">是否有职业医师执照&#12288;</th>
				<td colspan="2"><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="是"></td>
				<th style="text-align: right;">资格证编号&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="11444111"></td>
			</tr>
			<tr>	
				<th style="text-align: right;">党团关系是否转入&#12288;</th>
				<td colspan="2"><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="是"></td>
				<th style="text-align: right;">执照编号&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="1111144"></td>
			</tr>
			<tr>
				<th style="text-align: right;">邮政编码&#12288;</th>
				<td colspan="2"><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="210012"></td>
				<th style="text-align: right;">原户籍地&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="宿迁"></td>
			</tr>
			<tr>
				<th style="text-align: right;">是否住宿&#12288;</th>
				<td colspan="2"><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="是"></td>
				<th style="text-align: right;">固定电话&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="218025222144"></td>
			</tr>
			<tr>
				<th style="text-align: right;">电子邮箱&#12288;</th>
				<td colspan="2"><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="2546824@qq.com"></td>
				<th style="text-align: right;">手机号码&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="158246811452"></td>
			</tr>
			<tr>
				<th style="text-align: right;">微信号&#12288;</th>
				<td colspan="2"><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="2546824"></td>
				<th style="text-align: right;">QQ号&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="2546824"></td>
			</tr>
			<tr>
				<th style="text-align: right;">配偶身份证&#12288;</th>
				<td colspan="2"><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="无"></td>
				<th style="text-align: right;">配偶姓名&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="无"></td>
			</tr>
			<tr>
				<th style="text-align: right;">本科毕业单位名称&#12288;</th>
				<td colspan="2"><label>南方医科大学</label></td>
				<th style="text-align: right;">第一学历&#12288;</th>
				<td>
					<select style="width: 144px;" class="inputText">
						<option>研究生</option>
						<option>本科毕业</option>
						<option>本科结业</option>
						<option>高职高专</option>
					</select>
				</td>
			</tr>
			<tr>
				<th style="text-align: right;">获学士学位单位名称&#12288;</th>
				<td colspan="2"><label>南方医科大学</label></td>
				<th style="text-align: right;">本科毕业证编号&#12288;</th>
				<td><label>111222556322</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">学士学位证书编号&#12288;</th>
				<td colspan="2"><label>5451452445</label></td>
				<th style="text-align: right;">获学士学位专业名称&#12288;</th>
				<td><label>中西医临床医学</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">宿舍电话&#12288;</th>
				<td colspan="2"><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="154268452"></td>
				<th style="text-align: right;">最后学位证编号&#12288;</th>
				<td><label>52544455522</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">特长&#12288;</th>
				<td colspan="2"><input type="text" name="" class="inputText" style="width: 140px;text-align: left;" value="唱歌"></td>
				<th style="text-align: right;">普通话水平&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="一般"></td>
			</tr>
			<tr>
				<th style="text-align: right;">本科毕业年月&#12288;</th>
				<td colspan="2"><label>2014-06-08</label></td>
				<th style="text-align: right;">入学前奖惩情况&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="奖惩"></td>
			</tr>
			<tr>
				<th style="text-align: right;">获得学士学位年月&#12288;</th>
				<td colspan="2"><label>2014-07-08</label></td>
				<th style="text-align: right;">本科毕业专业名称&#12288;</th>
				<td><label>南方医科大学西医临床医学</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">宿舍号&#12288;</th>
				<td colspan="2"><input type="text" name="" class="inputText" style="width: 140px;text-align: left;" value="12"></td>
				<th style="text-align: right;">取得本科学历的学习形式&#12288;</th>
				<td><label>普通全日制</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">计算机水平&#12288;</th>
				<td colspan="2"><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="一般"></td>
				<th style="text-align: right;">外语水平&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="一般"></td>
			</tr>
			<tr>
				<th style="text-align: right;">硕士毕业年月&#12288;</th>
				<td colspan="2"><label>2014-06-09</label></td>
				<th style="text-align: right;">硕士毕业单位名称&#12288;</th>
				<td><label>南大</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">硕士毕业专业名称&#12288;</th>
				<td colspan="2"><label>硕士专业</label></td>
				<th style="text-align: right;">硕士毕业证编号&#12288;</th>
				<td><label>3652222222</label></td>
			</tr>
			<tr>
				<th style="text-align: right;">获得硕士学位单位名称&#12288;</th>
				<td colspan="2"><label>硕士学位</label></td>
				<th style="text-align: right;">硕士学位证编号&#12288;</th>
				<td><label>1482644741111</label></td>
			</tr>
				<tr>
				<th style="text-align: right;">获得硕士学位年月&#12288;</th>
				<td colspan="2"><label>2014-5-05-02</label></td>
				<th style="text-align: right;">获得硕士学位方式&#12288;</th>
				<td><input type="text" name="" style="width: 140px;text-align: left;" class="inputText" value="普通全日制"></td>
			</tr>
			<tr>
				<th style="text-align: right;">定向培养单位&#12288;</th>
				<td colspan="4"><label>定向培养</label></td>
			</tr>
			<tr>
				<th style="text-align: right;" >备注&#12288;</th>
				<td colspan="4"><label>since for workofart</label></td>
			</tr>
		</table>
		
	</div>
</div>
		
</body>
</html>