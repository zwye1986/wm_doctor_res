<%@include file="/jsp/common/doctype.jsp" %>
<!-- 必要参数START -->
<!-- 非必要 noHead 是否加载头文件 Y:不加载,!Y:加载 -->
<!-- 必要 roleFlag 当前角色标识 -->
<!-- 必要 d 学员角色标识 -->
<!-- 必要 t 老师角色标识 -->
<!-- 必要 h 科主任角色标识 -->
<!-- 必要 m 基地角色标识 -->
<!-- 必要 a 医院管理员角色标识 -->
<!-- 必要 recTypeId 表单类型 -->
<!-- 非必要 recFlow 记录标识 -->
<!-- 必要 processFlow 轮转信息标识 -->
<!-- 必要 operUserFlow 表单所属用户 -->
<!-- 必要 formPath 表单地址 -->
<!-- 非必要 roleMark 角色留痕表单(会以角色为标识单独生成xml) -->
<!-- 必要参数END -->
<html>
<head>
	<!-- noHead : Y/!Y -->
	<!-- 是否导入头文件,如果主页面已存在头文件则不导入 -->
	<!-- 重复导入头文件会极大的影响页面加载速度 -->
	<c:if test="${!(noHead eq GlobalConstant.FLAG_Y)}">
		<jsp:include page="/jsp/common/htmlhead.jsp">
			<jsp:param name="basic" value="true"/>
			<jsp:param name="jbox" value="true"/>
			<jsp:param name="jquery_form" value="true"/>
			<jsp:param name="jquery_ui_tooltip" value="true"/>
			<jsp:param name="jquery_validation" value="true"/>
			<jsp:param name="jquery_datePicker" value="true"/>
			<jsp:param name="jquery_placeholder" value="true"/>
		</jsp:include>
		<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	</c:if>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<style type="text/css">
		.errorInfo {color: red;}
		.operBtnDiv {margin-top: 10px;text-align: center;}
		a {color: blue;cursor:pointer;}
		.fileBody {height: 20px;font-size: 13px;line-height: 20px;}
	</style>
	
	<script type="text/javascript">
		//常量
		var FLAG_Y = 'Y';
		var FLAG_N = 'N';
		var DEFAULT_SET_UP = '101';
		var SET_UP = '${setup}' || DEFAULT_SET_UP;
		var SET_UP_DATA = [//配置对应的值
					[false,function(){//是否加载关闭按钮 0:取消,1:加载
						BTN_CFG.CLOSE_BTN = BTN_CFG.TEMP_BTN.clone().val('关    闭').on('click',closeTheForm);//关闭按钮
					}]
					,
					[false,function(){//表单是否只读 0:按角色自动,1:所有属性只读
						setOperStatus();
						setOperStatus=function(){
							console.log('该页面被设置为只读!');
						};
					}]
					,
					[false,function(){//加载审核按钮方式 0:不加载审核按钮,1:默认当前角色审核通过,2:只加载审核通过,3:加载通过和不通过
						loadAuditBtn(1);
					},function(){
						loadAuditBtn(2);
					},function(){
						loadAuditBtn(3);
					}]
		                   ];
		
		var BTN_CFG = {
				TEMP_BTN : $('<input class="search" type="button"/>'),//按钮模板
				SAVE_BTN : null,
				CLOSE_BTN : null,
				AUDIT_Y_BTN : null,
				AUDIT_N_BTN : null,
				BTN_GROUP : function(){
					return [
							BTN_CFG.SAVE_BTN,
							BTN_CFG.AUDIT_Y_BTN,
							BTN_CFG.AUDIT_N_BTN,
							BTN_CFG.CLOSE_BTN
					        ];
				}
		};
		BTN_CFG.SAVE_BTN = BTN_CFG.TEMP_BTN.clone().val('保    存').on('click',saveTheForm);//保存按钮
		
		var SAVE_BEFORE;//保存前动作,必须返回true/false
		var SAVE_AFTER;//保存后动作,无需返回值
		var AUDIT_BEFORE;//同保存前
		var AUDIT_AFTER;//同保存后
		var CUSTOM_CLOSE;//自定义关闭方法
		
		//为某区域或某个控件定义属性
		var CTR_SELTOR = '.ctr,:text,:checkbox,:radio,textarea,select';//ctr类表示控件,默认以input(text,checkbox,radio,password),textarea,select为展示的控件,如自定义控件请加上ctr类
		var LAB_SELTOR = '.lab,label';//lab类表示文本,默认以label为文本展示标签,如有自定义标签请加上lab类,文本展示必须由标签包裹
		
		var CURR_ROLE = '${roleFlag}';//当前角色
		var CURR_REC_STATUS = loadRoleStatus();
		
		//加载角色状态
		function loadRoleStatus(){
			if(CURR_ROLE=='${d}'){
				return {
					'status':'${rec.auditStatusId}${rec.headAuditStatusId}${rec.managerAuditStatusId}${rec.adminAuditStatusId}',
					'auditAttrName':'statusId',
					'auditAttrValue_Y':'${recStatusEnumSubmit.id}',
					'auditAttrValue_N':'',
				};
			}else if(CURR_ROLE=='${t}'){
				return {
					'status':'${rec.auditStatusId}',
					'auditAttrName':'auditStatusId',
					'auditAttrValue_Y':'${recStatusEnumTeacherAuditY.id}',
					'auditAttrValue_N':'${recStatusEnumTeacherAuditN.id}',
				};
			}else if(CURR_ROLE=='${h}'){
				return {
					'status':'${rec.headAuditStatusId}',
					'auditAttrName':'headAuditStatusId',
					'auditAttrValue_Y':'${recStatusEnumHeadAuditY.id}',
					'auditAttrValue_N':'${recStatusEnumHeadAuditN.id}',
				};
			}else if(CURR_ROLE=='${m}'){
				return {
					'status':'${rec.managerAuditStatusId}',
					'auditAttrName':'managerAuditStatusId',
					'auditAttrValue_Y':'${recStatusEnumManagerAuditY.id}',
					'auditAttrValue_N':'${recStatusEnumManagerAuditN.id}',
				};
			}else if(CURR_ROLE=='${a}'){
				return {
					'status':'${rec.adminAuditStatusId}',
					'auditAttrName':'adminAuditStatusId',
					'auditAttrValue_Y':'${recStatusEnumAdminAuditY.id}',
					'auditAttrValue_N':'${recStatusEnumAdminAuditN.id}',
				};
			}else {
				return {
					'status':'',
					'auditAttrName':'',
					'auditAttrValue_Y':'',
					'auditAttrValue_N':'',
				};
			}
		}
		
		//加载审核按钮
		function loadAuditBtn(index){
			if(index>=1){
				$('#AUDIT_STATUS').attr({
					'name':CURR_REC_STATUS.auditAttrName,
					'value':CURR_REC_STATUS.auditAttrValue_Y,
					'disabled':false
				});
			}
			
			if(index==1){
				return;
			}
			
			BTN_CFG.SAVE_BTN = null;
			
			if(index>=2){
				BTN_CFG.AUDIT_Y_BTN = BTN_CFG.TEMP_BTN.clone().val('审核通过').on('click',function(){
					auditTheForm(CURR_REC_STATUS.auditAttrValue_Y);
				});
				
			}
			
			if(index==3){
				BTN_CFG.AUDIT_N_BTN = BTN_CFG.TEMP_BTN.clone().val('审核不通过').on('click',function(){
					auditTheForm(CURR_REC_STATUS.auditAttrValue_N);
				});
			}
		}
		
		//加载配置
		function toLoadSetUp(){
			SET_UP += DEFAULT_SET_UP.substring(SET_UP.length);//为未定义的配置赋默认值
			for(var index in SET_UP){
				var val = SET_UP[index];
				var action = SET_UP_DATA[index][val];
				if(action){
					action();
				}
			}
		}
		
		//为页面加载按钮
		function loadTheBtns(){
			$.each(BTN_CFG.BTN_GROUP(),function(){
				$('.operBtnDiv').append(this);
			});
		}
		
		//保存表单
		function saveTheForm(){
			if(SAVE_BEFORE){
				if(!(SAVE_BEFORE())){
					return;
				}
			}
			if($("#REG_REC_FORM").validationEngine("validate")){
				jboxPost(
						'<s:url value="/res/rec/saveRegistryForm"/>',
						$('#REG_REC_FORM').serialize(),
						function(resp){
							if(SAVE_AFTER){
								SAVE_AFTER();
							}
							closeTheForm();
						},
						null,
						false
					);
			}
		}
		
		//审核表单
		function auditTheForm(statusId){
			$('#AUDIT_STATUS').val(statusId);
			if(AUDIT_BEFORE){
				if(!(AUDIT_BEFORE())){
					return;
				}
			}
			jboxPost(
					'<s:url value="/res/rec/auditTheForm"/>',
					$('#REG_REC_FORM').serialize(),
					function(resp){
						if(AUDIT_AFTER){
							AUDIT_AFTER();
						}
					},
					null,
					false
				);
		}
		
		//关闭
		function closeTheForm(){
			if(CUSTOM_CLOSE){
				CUSTOM_CLOSE();
			}else if(window == top){
				window.close();
			}else{
				jboxClose();
			}
		}
		
		//控件操作状态控制
		function setOperStatus(roleFlag){
			//控件和文本只会展示一个并且都必须存在于其角色对应的标签下
			//存在角色按角色控制
			//否则全部只读
			$(CTR_SELTOR).hide();//全部只读
			if(roleFlag){//未考虑审核不通过的情况
				var operFlag = !!CURR_REC_STATUS.status;
			
				$(CTR_SELTOR,'.'+roleFlag).toggle(!operFlag);
				$(LAB_SELTOR,'.'+roleFlag).toggle(operFlag);
				
				if(operFlag){
					BTN_CFG.SAVE_BTN = null;
				}
			}else{
				BTN_CFG.SAVE_BTN = null;
			}
		}
		
		//为日期控件自动设置样式,绑定事件
		//日期控件类为 dateInput
		//属性 fmt 控制格式,默认yyyy-MM-dd
		function onDateInput(){
			$('.dateInput').on('click',function(){
				var fmt = $(this).attr('fmt') || 'yyyy-MM-dd';
				WdatePicker({dateFmt:fmt});
			}).attr('readonly',true);
		}
		
		//加载多文件上传控件需为某一空元素添加 fu,fu 属性.值 s:单文件上传,m:多文件上传
		//为该控件自定义 name,fileName,value 属性
		//该控件无需定义展示节点,即label
		//在itemgroup下上传控件需遵循一下规则生成
		function initFileUpload(){
			$('[fu]').each(function(){
				autoBuildFileInput(this);
			});
		}
		
		function autoBuildFileInput(theInput){
			manualBuildFileInput(theInput,null,function(data){
				getFileElement(data.name,data.contentId,data.fileName,data.fileUrl);
			});
		}
		
		function manualBuildFileInput(theInput,contentId,callback){
			var $this = $(theInput);//将当前这个容器转换为jquery对象
			var type = $this.attr('fu');//提取属性值
			var name = $this.attr('name');//同上
			var fileName = $this.attr('fileName');//同上
			var value = $this.attr('value');//同上
			
			contentId = contentId || name;//容器的id
			
			var fileContent = $('<div/>').attr({//已上传文件的存放容器
				'id':contentId,
				'type':type
			});
			var btnContentContent = $('<span/>').attr('id',contentId+'upBtn').css('lineHeight','20px');
			var btnContent = $('<span/>').addClass('ctr');
			var uploadBtn = $('<a/>').text('上传').on('click',function(){//上传按钮
				$('#FILE_UPLOAD_INPUT').on('change',function(){
					toUploadTheFile(function(data){
						if(callback){
							data.name = name;
							data.contentId = contentId;
							callback(data);
						}
					});
				}).click();
			});
			$this.append(fileContent).append(btnContentContent.append(btnContent.append(['[',uploadBtn,']'])));
			
			if(value){
				values = value.split(',');
				fileNames = fileName.split(',');
				for(var index in values){
					var data = {
							'name':name,
							'contentId':contentId,
							'fileName':fileNames[index],
							'fileUrl':values[index]
					};
					if(callback){
						callback(data);
					}
				}
				if(type=='s'){
					btnContentContent.hide();
				}
			}
		}
		
		//生成文件上传后实体
		function getFileElement(inputName,contentId,fileName,fileUrl){
			if(fileName && fileUrl){
				var type = $('#'+contentId).attr('type');
				
				var fileId = UUID();//创建该文件的唯一标识
				
				var fileBody = $('<div/>').attr('id',fileId).addClass('fileBody');//文件实体容器
				var fileInput = $('<input/>').attr({//文件路径上传控件
					'name':inputName,
					'value':fileUrl,
					'type':'hidden'
				});
				var fileView = $('<a/>').text(fileName).attr({//已上传文件查看按钮
					'target':'_blank',
					'href':"${sysCfgMap['upload_base_url']}"+fileUrl
				});
				var btnContent = $('<span/>').addClass('ctr');
				var delBtn = $('<a/>').text('删除').on('click',function(){//删除按钮
					$('#'+fileId).remove();
					if(type=='s'){
						$('#'+contentId+'upBtn').show();
					}
				});
				btnContent.append(['[',delBtn,']']);
				fileBody.append([fileView,' ',btnContent,fileInput]);
				$('#'+contentId).append(fileBody);
				if(type=='s'){
					$('#'+contentId+'upBtn').hide();
				}
			}
		}
		
		//文件开始上传
		function toUploadTheFile(callback){
			$.ajaxFileUpload({
				url:"<s:url value='/res/rec/formFileUpload'/>?date="+new Date(),
				secureuri:false,
				fileElementId:'FILE_UPLOAD_INPUT',
				dataType: 'json',
				success: function (data){
					data = JSON.parse(data);
					if(data){
						if(data.status == '${GlobalConstant.OPRE_SUCCESSED_FLAG}'){
							if(callback){
								callback(data);
							}
						}else{
							jboxTip(data.error);
						}
					}else{
						jboxTip('${GlobalConstant.UPLOAD_FAIL}');
					}
				},
				error: function (data, status, e){
					jboxTip('${GlobalConstant.UPLOAD_FAIL}');
				},
				complete:function(){
				}
			});
		}
		
		//唯一标识生成器
		function UUID(){
			var result = '';
			var hexDigits = "0123456789abcdef";
			for (var i = 0; i < 33; i++) {
				result += hexDigits[Math.floor(Math.random() * 0x10)];
			}
			return result;
		}
		
		//表单各功能初始化
		function formInit(){
			//1.加载日期控件
			console.log('加载日期控件');
			onDateInput();
			
			//2.初始化多文件上传控件
			console.log('初始化多文件上传控件');
			initFileUpload();
			
			//3.开始加载配置
			console.log('开始加载配置');
			toLoadSetUp();
			
			//4.根据角色控制读写权限
			console.log('根据角色控制读写权限');
			setOperStatus(CURR_ROLE);
			
			//5.加载按钮
			console.log('加载按钮');
			loadTheBtns();
		}
		
		//初始化
		$(function(){
			//页面初始化
			formInit();
		});
	</script>
</head>
<body>
	<!-- 错误信息 -->
	<div class="errorInfo">
		<c:if test="${empty roleFlag}">
			用户角色丢失！
		</c:if>
	</div>
	
	<!-- 表单部分 -->
	<div class="formDiv">
		<form id="REG_REC_FORM">
			<!-- 填表角色 -->
			<input type="hidden" name="roleFlag" value="${roleFlag}"/>
			<!-- 表单类型 -->
			<input type="hidden" name="recTypeId" value="${recTypeId}"/>
			<!-- 表单数据的flow -->
	        <input type="hidden" name="recFlow" value="${recFlow}"/>
	        <!-- 当前轮转信息的flow -->
	        <input type="hidden" name="processFlow" value="${processFlow}"/>
	        <!-- 表单所属 -->
	        <input type="hidden" name="operUserFlow" value="${operUserFlow}">
	        <!-- 审核状态 -->
	        <input type="hidden" name="" value="" id="AUDIT_STATUS" disabled="disabled">
	        
	        <!-- 引入表单正文 -->
			<jsp:include page="/jsp/${formPath}.jsp"></jsp:include>
		</form>
	</div>
	
	<!-- 基本操作按钮 -->
	<div class="operBtnDiv"></div>
	
	<!-- 影藏文件上传控件 -->
	<div id="FILE_UPLOAD_INPUT_CONTENT">
		<input id="FILE_UPLOAD_INPUT" type="file" name="formFile" style="display: none;">
	</div>
</body>
</html> 