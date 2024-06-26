<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            if(false==$("#orgAddressForm").validationEngine("validate")){
                return false;
            }
            var bean={};
            bean.orgFlow= "${sessionScope.currUser.orgFlow}";
            var cfgs=[];
            $(".base_info").each(function(){
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
            var url = "<s:url value='/jsres/attendanceNew/saveTimeSet'/>";
            jboxPostJson(url,  JSON.stringify(bean),
                function(resp) {
                    if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
                        window.parent.frames['mainIframe'].window.location.reload(true);
                        jboxClose();
                    }
            }, null, true);
        }

    </script>
</head>
<body>
<div class="main_hd">
    <h2 class="underline">请假参数配置</h2>
</div>
<div class="mainright">
        <form id="orgAddressForm">
            <input name="orgFlow" hidden value="${sessionScope.currUser.orgFlow}">
            <c:forEach items="${recDocCategoryEnumList}" var="category">
                <c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
                <c:if test="${'Doctor'eq category.id}">
                    <c:set var="key1" value="${category.id}All"></c:set>
                    <c:set var="key2" value="${category.id}Greater"></c:set>
                    <c:set var="key3" value="${category.id}Midd"></c:set>
                    <c:set var="key4" value="${category.id}Less"></c:set>
                    <c:set var="cfg1" value="${cfgMap[key1]}"></c:set>
                    <c:set var="cfg2" value="${cfgMap[key2]}"></c:set>
                    <c:set var="cfg3" value="${cfgMap[key3]}"></c:set>
                    <c:set var="cfg4" value="${cfgMap[key4]}"></c:set>
                    <fieldset style="margin : 30px">
                        <legend>考勤时间设置<font color="red">（一天默认8小时）</font></legend>
                        <div style="height: 200px;width: 100%">
                            <table border="0" cellpadding="0" cellspacing="0" class="base_info" width="100%" id="${category.id }">
                                <tr>
                                    <td width="200px;">请假总天数：</td>
                                    <td><input name="${category.id }allDays" type="text" class="input validate[required,custom[integer],min[1]]" value="${cfg1.allDays}"style="width: 80px;">天</td>
                                    <td width="100px;">请假阀值1：</td>
                                    <td><input name="${category.id }intervalDays" type="text" class="input validate[required,custom[integer],min[1]]" value="${cfg1.intervalDays}"style="width: 80px;">天</td>
                                    <td width="100px;">请假阀值2：</td>
                                    <td><input name="${category.id }intervalDays2" type="text" class="input validate[required,custom[integer],min[1]]" value="${cfg1.intervalDays2}"style="width: 80px;">天</td>
                                </tr>
                                <tr class="Greater">
                                    <td>大于阀值2流程：</td>
                                    <td colspan="5">
                                        <input type="checkbox" class="validate[required]" name="${category.id }Greater" flag="teacherFlag" ${(cfg2.teacherFlag eq 'Y')?'checked':''} id="${category.id }greaterteacherFlag"><label for="${category.id }greaterteacherFlag">带教</label>------>
                                        <input type="checkbox" class="validate[required]" name="${category.id }Greater"flag="headFlag" ${(cfg2.headFlag eq 'Y')?'checked':''} id="${category.id }greaterheadFlag"><label for="${category.id }greaterheadFlag">主任</label>
                                            ------>
                                            <c:if test="${category.id eq 'Graduate'}">
                                                <input type="checkbox" class="validate[required]" name="${category.id }Greater" flag="tutorFlag" ${(cfg2.tutorFlag eq 'Y')?'checked':''} id="${category.id }greatertutorFlag"><label for="${category.id }greatertutorFlag">导师</label>------>
                                            </c:if>
                                            <input type="checkbox" class="validate[required]" name="${category.id }Greater" flag="managerFlag" ${(cfg2.managerFlag eq 'Y')?'checked':''} id="${category.id }greatermanagerFlag"><label for="${category.id }greatermanagerFlag">管理员</label>
                                    </td>
                                </tr>
                                <tr class="Midd">
                                    <td>大于阀值1小于等于阀值2流程：</td>
                                    <td colspan="5">
                                        <input type="checkbox" class="validate[required]" name="${category.id }Midd" flag="teacherFlag" ${(cfg3.teacherFlag eq 'Y')?'checked':''} id="${category.id }middteacherFlag"><label for="${category.id }middteacherFlag">带教</label>------>
                                        <input type="checkbox" class="validate[required]" name="${category.id }Midd"flag="headFlag" ${(cfg3.headFlag eq 'Y')?'checked':''} id="${category.id }middheadFlag"><label for="${category.id }middheadFlag">主任</label>
                                        ------>
                                        <c:if test="${category.id eq 'Graduate'}">
                                            <input type="checkbox" class="validate[required]" name="${category.id }Midd" flag="tutorFlag" ${(cfg3.tutorFlag eq 'Y')?'checked':''} id="${category.id }middtutorFlag"><label for="${category.id }middtutorFlag">导师</label>------>
                                        </c:if>
                                        <input type="checkbox" class="validate[required]" name="${category.id }Midd" flag="managerFlag" ${(cfg3.managerFlag eq 'Y')?'checked':''} id="${category.id }middmanagerFlag"><label for="${category.id }middmanagerFlag">管理员</label>
                                    </td>
                                </tr>
                                <tr class="Less">
                                    <td>小于等于阀值1流程：</td>
                                    <td colspan="5">
                                        <input type="checkbox" class="validate[required]" name="${category.id }Less" flag="teacherFlag" ${(cfg4.teacherFlag eq 'Y')?'checked':''} id="${category.id }lessteacherFlag"><label for="${category.id }lessteacherFlag">带教</label>------>
                                        <input type="checkbox" class="validate[required]" name="${category.id }Less" flag="headFlag" ${(cfg4.headFlag eq 'Y')?'checked':''} id="${category.id }lessheadFlag"><label for="${category.id }lessheadFlag">主任</label>
                                            ------>
                                            <c:if test="${category.id eq 'Graduate'}">
                                                <input type="checkbox" class="validate[required]" name="${category.id }Less" flag="tutorFlag" ${(cfg4.tutorFlag eq 'Y')?'checked':''} id="${category.id }lesstutorFlag"><label for="${category.id }lesstutorFlag">导师</label>------>
                                            </c:if>
                                            <input type="checkbox" class="validate[required]" name="${category.id }Less" flag="managerFlag" ${(cfg4.managerFlag eq 'Y')?'checked':''} id="${category.id }lessmanagerFlag"><label for="${category.id }lessmanagerFlag">管理员</label>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </fieldset>
                </c:if>
            </c:forEach>
        </form>
        <div style="width: 100%">
            <div class="button">
                <input class="btn_green"  onclick="save()" type="button" value="保&#12288;存"/>
            </div>
        </div>
</div>
</body>
</html>