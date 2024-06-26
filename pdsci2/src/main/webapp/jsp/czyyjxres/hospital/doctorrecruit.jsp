<style type="text/css">
        #boxHome .item:HOVER{background-color: #eee;}
        .td_left{
            border: 0px;
            text-align: left;
        }
        .td_right{
            border: 0px;
            text-align: right;
        }
    </style>

    <script type="text/javascript">

        //分页
        function toPage(page) {
            if(page!=undefined){
                $("#currentPage").val(page);
            }
            searchDoctor();
        }
        //切换录取状态
        function searchDoctorForStatus(obj){
            var status = $(obj).attr('id');
            $("#status").val(status);
            $("#currentPage").val(1);
            searchDoctor();
        }
        //查询
        function searchDoctor(){
            jboxPostLoad("content","<s:url value='/czyyjxres/hospital/recruitDoctor'/>?isQuery=Y",$("#searchForm").serialize() ,true);
        }
        //打印入科通知
        function printSpeNotice(resumeFlow){
            jboxTip("打印中,请稍等...");
            var url = '<s:url value="/czyyjxres/hospital/printSpeNotice?resumeFlow="/>'+resumeFlow;
            window.location.href = url;
        }
        //人员信息
        function getInfo(userFlow,batchId,flag,isForeign){

            if(isForeign =='Y'){
                jboxOpen("<s:url value='/czyyjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&flag="+flag+"&isForeign="+isForeign,"User Infomations",1000,550);
            }
            jboxOpen("<s:url value='/czyyjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&flag="+flag+"&isForeign="+isForeign,"人员信息",1000,550);
        }
        //是否报到操作
        function admitOper(resumeFlow , admitFlag){
            var tip = admitFlag == "Y"?"确认报到？":"确认未报到？";

//            var dateForm = $("<form></form>");
//            var dateInput = $("<input type='text' class='input validate[required]' onclick='WdatePicker({dateFmt:'yyyy-MM-dd'})' />");
//            dateForm.append(dateInput);
            if(admitFlag=='Y'){
                jboxOpen("<s:url value='/czyyjxres/hospital/admitTime?resumeFlow='/>"+resumeFlow+"&statusId="+admitFlag, tip,500,300,true);
            }

            if(admitFlag=='N'){
                jboxConfirm(tip, function(){
                    jboxPost('<s:url value="/czyyjxres/hospital/notrecruit"/>' , {"resumeFlow":resumeFlow,"statusId":admitFlag} , function(resp){
                        if(resp=="1"){
                            jboxTip("操作成功");
                        }
                        searchDoctor();
                    } , null , false);
                });
            }

        }
        //登记老师
        function registerTeacher(resumeFlow,deptFlow){
            jboxOpen("<s:url value='/czyyjxres/hospital/registerTeacher?resumeFlow='/>"+resumeFlow+"&deptFlow="+deptFlow, "登记老师",800,500,true);
        }
        //结业
        function graduation(resumeFlow){
            jboxOpen("<s:url value='/czyyjxres/hospital/graduation?resumeFlow='/>"+resumeFlow, "结业",1000,500,true);
        }
        function  exportInfo()
        {
            var url = "<s:url value='/czyyjxres/hospital/exportAdmitPassed'/>";
            jboxExp($("#searchForm"),url);
        }
        function stuInfoExport(){
            //先选择 学员类型
            if($("select[name='isForeign']").val()=='all'){
                return jboxTip("请先选择学员类型！");
            }
            jboxExp($('#searchForm'),"<s:url value='/czyyjxres/hospital/stuChestCardExport'/>");
        }

        /**
         *模糊查询加载
         */
        (function($){

            $.fn.likeSearchInit = function(option){
                option.clickActive = option.clickActive || null;
                var searchInput = this;
                searchInput.on("keyup focus",function(){
                    $("#boxHome").show();
                    if($.trim(this.value)){
                        $("#boxHome .item").hide();
                        var items = $("#boxHome .item[value*='"+this.value+"']").show();

                        if(!items){
                            $("#boxHome").hide();
                        }
                    }else{
                        $("#boxHome .item").show();

                    }
                });
                searchInput.on("blur",function(){
                    if(!$("#boxHome.on").length){
                        $("#boxHome").hide();
                    }
                });
                $("#boxHome").unbind("mouseenter mouseleave"); //先进行解绑，否则失效
                $("#boxHome").on("mouseenter mouseleave",function(){
                    $(this).toggleClass("on");
                });

                $("#boxHome .item").click(function(){
                    $("#boxHome").hide();
                    var value = $(this).attr("value");
                    $("#speId").val($(this).attr("flow"));
                    $("#itemName").val(value);
                    searchInput.val(value);
                    if(option.clickActive){
                        option['clickActive']($(this).attr("flow"));

                    }
                });
            };


        })(jQuery);
        /**
         *显示不同的查询专业
         */
        function showSpe(flag){
            $("#boxHome").html("");
            $("#speSel").val("");
            if(flag =='Y'){
                $("#boxHome").append($(".abroad").clone());
            }else if(flag =='N'){
                $("#boxHome").append($(".domestic").clone());
            }

            $("#speSel").likeSearchInit({

            });
        }


        //页面加载完成时调用
        $(function(){
            $("#speSel").likeSearchInit({

            });
        });
    </script>


<body>
    <div class="main_hd">
        <h2>学员报到</h2>
        <div class="title_tab" id="toptab">
            <ul>
                <li class="${empty param.status?'tab_select':tab}" id="" onclick="searchDoctorForStatus(this);"><a>待报到</a></li>
                <li class="${param.status=='Y'?'tab_select':tab}" id="Y" onclick="searchDoctorForStatus(this);"><a>已报到</a></li>
                <li class="${param.status=='N'?'tab_select':tab}" id="N" onclick="searchDoctorForStatus(this);"><a>未报到</a></li>
            </ul>
        </div>
    </div>
    <div class="main_bd" id="div_table_0" >

        <div class="<%--div_table--%>" style="padding:20px 25px 0;margin-bottom: 10px;">
            <form id="searchForm">
                <input type="hidden" id="status" name="status" value="${param.status}" />
                <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
                <table>
                    <tr>
                        <td class="td_right">进修批次：</td>
                        <td class="td_left">
                            <select name="batchFlow" class="select" style="width:126px;margin-left: 4px">
                                <option value="">全部</option>
                                <c:forEach items="${batchLst}" var="dict">
                                    <option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="td_right">&#12288;进修专业：</td>
                        <td class="td_left">
                            <input id="speSel" style="width: 120px" class="toggleView input" type="text" name="speName2" value="${param.speName2}" autocomplete="off" title="${param.speName2}" onmouseover="this.title = this.value"/>
                            <input id="speId"  type="hidden" name="speId" value="${param.speId}"/>
                            <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;left:4px;">
                                <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 126px;border-top: none;position: relative;display: none;">
                                    <c:if test="${param.isForeign eq GlobalConstant.FLAG_N}">
                                        <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
                                            <p class="item" flow="${SysDept.deptFlow}" value="${SysDept.deptName}" style="height: 30px;padding-left: 10px;"}>${SysDept.deptName}</p>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${param.isForeign eq GlobalConstant.FLAG_Y}">
                                        <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                            <p class="item" flow="${dict.dictId}" value="${dict.dictDesc}" style="height: 40px;padding-left: 10px;">${dict.dictDesc}</p>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </td>
                        <td class="td_right" style="height: 50px">&#12288;姓&#12288;&#12288;名：</td>
                        <td class="td_left">
                            <input type="text" name="userName" value="${param.userName}" class="input" style="width: 120px"/>
                        </td>
                        <td class="td_left" colspan="2">
                            &#12288;<input type="button" class="btn_green" value="查&#12288;询" onclick="searchDoctor()" />
                            <c:if test="${param.status=='Y' }">
                                <a href="javascript:void(0);" onclick="exportInfo()" class="btn_green">导&#12288;出</a>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="div_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <colgroup>
                    <col width="10%"/>
                    <c:if test="${param.status eq 'Y'}">
                        <col width="10%"/>
                    </c:if>
                    <col width="15%"/>
                    <col width="15%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <c:if test="${param.status=='Y'}">
                    <col width="10%"/>
                    </c:if>
                    <col width="20%"/>
                </colgroup>
                <tr>
                    <th>姓名</th>
                    <c:if test="${param.status=='Y'}">
                        <th>工号</th>
                    </c:if>
                    <th>证件号码</th>
                    <th>毕业专业</th>
                    <th colspan="2">进修专业(进修时间)</th>
                    <c:if test="${param.status=='Y'}">
                    <th>报到时间</th>
                    </c:if>
                    <th>操作</th>
                </tr>
                <c:forEach items="${stuUserLst}" var="user">
                    <tr>
                        <td>${extInfoMap[user.resumeFlow].userName}</td>
                        <c:if test="${param.status=='Y'}">
                            <td>${extInfoMap[user.resumeFlow].userCode}</td>
                        </c:if>
                        <td>${user.sysUser.idNo}</td>
                        <td>${user.schoolSpeName}</td>
                        <td colspan="2">
                            <c:set var="unit" value="个月"></c:set>
                            <c:set var="unit2" value="天"></c:set>
                            <c:forEach items="${extInfoMap[user.resumeFlow].speFormList}" var="speForm">
                                ${speForm.speName}
                                (
                                ${not empty speForm.stuTimeId?speForm.stuTimeId:(pdfn:signDaysBetweenTowDate(speForm.endDate,speForm.beginDate)+1)}
                                <c:if test="${user.sysUser.isForeign eq 'Y'}">${unit2}</c:if>
                                <c:if test="${user.sysUser.isForeign eq 'N'}">${unit}</c:if>
                                )
                            </c:forEach>
                        </td>
                        <c:if test="${param.status=='Y'}">
                        <td>${user.reportDate}</td>
                        </c:if>
                        <td>
                            <a onclick="getInfo('${user.userFlow}','${user.stuBatId}','Y','${user.sysUser.isForeign}')">[人员信息]</a>
                            <c:if test="${user.stuStatusId eq stuStatusEnumRecruitted.id}">
                                <a onclick='admitOper("${user.resumeFlow}" , "Y")'>[报到]</a>
                                <a onclick='admitOper("${user.resumeFlow}" , "N")'>[未报到]</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(stuUserLst)}" scope="request"></c:set>
            <pd:pagination-dwjxres toPage="toPage"/>
        </div>
        <div  style="display:none;width: 0px;height: 0px;overflow: visible;float: left; position:relative; /*top:30px;*/left:4px;">
            <div  style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 350px;border-top: none;position: relative;display: none;">
                <%--<c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">--%>
                <%--<p class="domestic item" flow="${dict.dictId}" value="${dict.dictName}" style="height: 30px;padding-left: 10px;">${dict.dictName}</p>--%>
                <%--</c:forEach>--%>
                <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
                    <p class="domestic item" flow="${SysDept.deptFlow}" value="${SysDept.deptName}" style="height: 30px;padding-left: 10px;"}>${SysDept.deptName}</p>
                </c:forEach>
                <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                    <p class="abroad item" flow="${dict.dictId}" value="${dict.dictDesc}" style="height: 40px;padding-left: 10px;">${dict.dictDesc}</p>
                </c:forEach>
            </div>
        </div>

    </div>
</body>





