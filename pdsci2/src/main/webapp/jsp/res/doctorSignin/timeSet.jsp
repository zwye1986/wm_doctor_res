<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
	</jsp:include>
	<script type="text/javascript">
	function save(){
		if(false==$("#orgAddressForm").validationEngine("validate")){
			return false;
		}
		var bean={};
		bean.orgFlow=$("input[name='orgFlow']").val()||"";
		var cfgs=[];
		$(".basic").each(function(){
			var cfg={};
			cfg.doctorTypeId=$(this).attr("id")||"";
			cfg.lessOrGreater="All";
			cfg.allDays=$("input[name='"+cfg.doctorTypeId+"allDays']").val()||"";
			cfg.intervalDays=$("input[name='"+cfg.doctorTypeId+"intervalDays']").val()||"";
            cfg.intervalDays2=$("input[name='"+cfg.doctorTypeId+"intervalDays2']").val()||"";
			cfgs.push(cfg);
			if(!cfg.allDays)
			{
				jboxTip("请输入请假总天数！");
				return ;
			}
			if(!cfg.intervalDays)
			{
				jboxTip("请输入请假阀值1！");
				return ;
			}
            if(!cfg.intervalDays2)
            {
                jboxTip("请输入请假阀值2！");
                return ;
            }
			if(parseInt(cfg.intervalDays)>parseInt(cfg.allDays))
			{
				jboxTip("请假阀值1不得大于请假总天数！");
                throw 'error';
			}
            if(parseInt(cfg.intervalDays2)>parseInt(cfg.allDays))
            {
                jboxTip("请假阀值2不得大于请假总天数！");
                throw 'error';
            }
            if(parseInt(cfg.intervalDays)>parseInt(cfg.intervalDays2))
            {
                jboxTip("请假阀值1不得大于请假阀值2！");
                throw 'error';
            }
			cfg={};
			cfg.doctorTypeId=$(this).attr("id")||"";
			cfg.lessOrGreater="Greater";
			$(":checkbox[name='"+cfg.doctorTypeId+"Greater']:checked").each(function(){
				var flag=$(this).attr("flag")||"";
				if(flag!="")
				{
					cfg[flag]="Y";
				}
			});
			$(":checkbox[name='"+cfg.doctorTypeId+"Greater']:not(:checked)").each(function(){
				var flag=$(this).attr("flag")||"";
				if(flag!="")
				{
					cfg[flag]="N";
				}
			});
			cfgs.push(cfg);
			cfg={};
			cfg.doctorTypeId=$(this).attr("id")||"";
			cfg.lessOrGreater="Less";
			$(":checkbox[name='"+cfg.doctorTypeId+"Less']:checked").each(function(){
				var flag=$(this).attr("flag")||"";
				if(flag!="")
				{
					cfg[flag]="Y";
				}
			});
			$(":checkbox[name='"+cfg.doctorTypeId+"Less']:not(:checked)").each(function(){
				var flag=$(this).attr("flag")||"";
				if(flag!="")
				{
					cfg[flag]="N";
				}
			});
			cfgs.push(cfg);
            cfg={};
            cfg.doctorTypeId=$(this).attr("id")||"";
            cfg.lessOrGreater="Midd";
            $(":checkbox[name='"+cfg.doctorTypeId+"Midd']:checked").each(function(){
                var flag=$(this).attr("flag")||"";
                if(flag!="")
                {
                    cfg[flag]="Y";
                }
            });
            $(":checkbox[name='"+cfg.doctorTypeId+"Midd']:not(:checked)").each(function(){
                var flag=$(this).attr("flag")||"";
                if(flag!="")
                {
                    cfg[flag]="N";
                }
            });
            cfgs.push(cfg);
		});
		bean.cfgs=cfgs;
		console.log(JSON.stringify(bean));

		var url = "<s:url value='/res/doctorSignin/saveTimeSet'/>";
		jboxPostJson(url, JSON.stringify(bean),
				function(resp){
					if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
						window.parent.frames['mainIframe'].window.location.reload(true);
						jboxClose();
					}
				}, null, true);
	}


</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="orgAddressForm">
				<input name="orgFlow" hidden value="${sessionScope.currUser.orgFlow }">
			<c:forEach items="${recDocCategoryEnumList}" var="category">
				<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
				<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
					<c:set var="key1" value="${category.id}All"></c:set>
					<c:set var="key2" value="${category.id}Greater"></c:set>
					<c:set var="key3" value="${category.id}Midd"></c:set>
					<c:set var="key4" value="${category.id}Less"></c:set>
					<c:set var="cfg1" value="${cfgMap[key1]}"></c:set>
					<c:set var="cfg2" value="${cfgMap[key2]}"></c:set>
					<c:set var="cfg3" value="${cfgMap[key3]}"></c:set>
					<c:set var="cfg4" value="${cfgMap[key4]}"></c:set>
					<fieldset>
						<legend>${category.name}考勤时间设置</legend>
						<div style="height: 150px;width: 100%">
								<table class="basic" width="100%" id="${category.id }">
									<input name="orgFlow" hidden value="${sessionScope.currUser.orgFlow }">
									<tr>
										<td width="200px;">请假总天数：</td>
										<td><input name="${category.id }allDays" type="text" class="input validate[required,custom[integer],min[1]]" value="${cfg1.allDays}"style="width: 80px;"></td>
										<td width="200px;">请假阀值1：</td>
										<td><input name="${category.id }intervalDays" type="text" class="input validate[required,custom[integer],min[1]]" value="${cfg1.intervalDays}"style="width: 80px;">天</td>
										<td width="200px;">请假阀值2：</td>
										<td><input name="${category.id }intervalDays2" type="text" class="input validate[required,custom[integer],min[1]]" value="${cfg1.intervalDays2}"style="width: 80px;">天</td>
									</tr>
									<tr class="Greater">
										<td>大于阀值2流程：</td>
										<td colspan="5">
											<input type="checkbox" class="validate[required]" name="${category.id }Greater" flag="teacherFlag" ${(cfg2.teacherFlag eq 'Y')?'checked':''} id="${category.id }greaterteacherFlag"><label for="${category.id }greaterteacherFlag">带教</label>------>
											<input type="checkbox" class="validate[required]" name="${category.id }Greater"flag="headFlag" ${(cfg2.headFlag eq 'Y')?'checked':''} id="${category.id }greaterheadFlag"><label for="${category.id }greaterheadFlag">主任</label>

											<c:if test="${sysCfgMap['sys_index_url'] ne '/inx/shangHaiRuiJing'}">
												------>
												<c:if test="${category.id eq 'Graduate'}">
													<input type="checkbox" class="validate[required]" name="${category.id }Greater" flag="tutorFlag" ${(cfg2.tutorFlag eq 'Y')?'checked':''} id="${category.id }greatertutorFlag"><label for="${category.id }greatertutorFlag">导师</label>------>
												</c:if>
												<input type="checkbox" class="validate[required]" name="${category.id }Greater" flag="managerFlag" ${(cfg2.managerFlag eq 'Y')?'checked':''} id="${category.id }greatermanagerFlag"><label for="${category.id }greatermanagerFlag">管理员</label>
											</c:if>
										</td>
									</tr>
									<tr class="Midd">
										<td>大于阀值1小于等于阀值2流程：</td>
										<td colspan="5">
											<input type="checkbox" class="validate[required]" name="${category.id }Midd" flag="teacherFlag" ${(cfg3.teacherFlag eq 'Y')?'checked':''} id="${category.id }middteacherFlag"><label for="${category.id }middteacherFlag">带教</label>------>
											<input type="checkbox" class="validate[required]" name="${category.id }Midd"flag="headFlag" ${(cfg3.headFlag eq 'Y')?'checked':''} id="${category.id }middheadFlag"><label for="${category.id }middheadFlag">主任</label>

											<c:if test="${sysCfgMap['sys_index_url'] ne '/inx/shangHaiRuiJing'}">
												------>
												<c:if test="${category.id eq 'Graduate'}">
													<input type="checkbox" class="validate[required]" name="${category.id }Midd" flag="tutorFlag" ${(cfg3.tutorFlag eq 'Y')?'checked':''} id="${category.id }middtutorFlag"><label for="${category.id }middtutorFlag">导师</label>------>
												</c:if>
												<input type="checkbox" class="validate[required]" name="${category.id }Midd" flag="managerFlag" ${(cfg3.managerFlag eq 'Y')?'checked':''} id="${category.id }middmanagerFlag"><label for="${category.id }middmanagerFlag">管理员</label>
											</c:if>
										</td>
									</tr>
									<tr class="Less">
										<td>小于等于阀值1流程：</td>
										<td colspan="5">
											<input type="checkbox" class="validate[required]" name="${category.id }Less" flag="teacherFlag" ${(cfg4.teacherFlag eq 'Y')?'checked':''} id="${category.id }lessteacherFlag"><label for="${category.id }lessteacherFlag">带教</label>------>
											<input type="checkbox" class="validate[required]" name="${category.id }Less" flag="headFlag" ${(cfg4.headFlag eq 'Y')?'checked':''} id="${category.id }lessheadFlag"><label for="${category.id }lessheadFlag">主任</label>
											<c:if test="${sysCfgMap['sys_index_url'] ne '/inx/shangHaiRuiJing'}">
												------>
												<c:if test="${category.id eq 'Graduate'}">
													<input type="checkbox" class="validate[required]" name="${category.id }Less" flag="tutorFlag" ${(cfg4.tutorFlag eq 'Y')?'checked':''} id="${category.id }lesstutorFlag"><label for="${category.id }lesstutorFlag">导师</label>------>
												</c:if>
												<input type="checkbox" class="validate[required]" name="${category.id }Less" flag="managerFlag" ${(cfg4.managerFlag eq 'Y')?'checked':''} id="${category.id }lessmanagerFlag"><label for="${category.id }lessmanagerFlag">管理员</label>
											</c:if>
										</td>
									</tr>
								</table>
						</div>
					</fieldset>
				</c:if>
			</c:forEach>

			</form>
			<fieldset>
				<div class="button">
					<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
				</div>
			</fieldset>
		</div>
	</div>
</div>
</body>
</html>