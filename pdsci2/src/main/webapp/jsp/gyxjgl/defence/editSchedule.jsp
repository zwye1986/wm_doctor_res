<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function(){
            if(${not empty defence.defencePlace}){
                var defencePlace='${defence.defencePlace}';
                var prov=defencePlace.substring(0,defencePlace.indexOf('省'));
                var city=defencePlace.substring(defencePlace.indexOf('省')+1,defencePlace.indexOf('市'));
                var unit=defencePlace.substring(defencePlace.indexOf('市')+1,defencePlace.indexOf('单'));
                var building=defencePlace.substring(defencePlace.indexOf('位')+1,defencePlace.indexOf('楼'));
                var floor=defencePlace.substring(defencePlace.indexOf('楼')+1,defencePlace.indexOf('层'));
                var room=defencePlace.substring(defencePlace.indexOf('层')+1,defencePlace.indexOf('房间'));
                $("input[name='prov']").val(prov);
                $("input[name='city']").val(city);
                $("input[name='unit']").val(unit);
                $("input[name='building']").val(building);
                $("input[name='floor']").val(floor);
                $("input[name='room']").val(room);
            }
        });
        function save(statusId){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var defencePlace=$("input[name='prov']").val()+"省"+$("input[name='city']").val()+"市"+$("input[name='unit']").val()+"单位"+$("input[name='building']").val()+"楼"+$("input[name='floor']").val()+"层"+$("input[name='room']").val()+"房间";
            $(".defencePlace").val(defencePlace);
            var memberList = [];
            $(".memberTbody tr").not("[class*='orgTr']").each(function(i){
                var memberOrg="";
                $(".memberOrg"+i).each(function(j){
                    memberOrg+=$(this).val();
                    if(j < $(".memberOrg"+i).length-1){
                        memberOrg+=",";
                    }
                });
               var obj = {
                   "recordFlow" : $(this).find(".recordFlow").val(),
                   "defenceFlow" : '${defence.recordFlow}',
                   "memberName" : $(this).find(".memberName").val(),
                   "memberPost" : $(this).find(".memberPost").val(),
                   "memberOrg" : memberOrg
               };
                memberList.push(obj);
            });
            var chairmanOrg="";
            $(".chairmanOrg").each(function(i){
                chairmanOrg+=$(this).val();
                if(i < $(".chairmanOrg").length-1){
                    chairmanOrg+=",";
                }
            });
            jboxPostJson("<s:url value='/gyxjgl/paper/saveSchedule?recordFlow=${defence.recordFlow}&scheduleFlag='/>"+statusId+"&defenceBeginTime="+$(".defenceBeginTime").val()+"&defenceEndTime="+$(".defenceEndTime").val()
                    +"&defencePlace="+encodeURI(encodeURI($(".defencePlace").val()))+"&chairmanName="+encodeURI(encodeURI($(".chairmanName").val()))+"&chairmanPost="+encodeURI(encodeURI($(".chairmanPost").val()))+"&chairmanOrg="+encodeURI(encodeURI(chairmanOrg)), JSON.stringify(memberList), function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
        function addMember(){
            var i = $(".memberTbody tr").not("[class*='orgTr']").length;
            var html = "<tr><th style='text-align:right;padding:0px;'>委员姓名：</th><td><input type='hidden' class='recordFlow'><input type='text' class='validate[required] memberName'></td><th style='text-align:right;padding:0px;'>职称：</th><td><input type='text' class='validate[required] memberPost'></td>" +
                    "<th style='text-align:right;padding:0px;'>单位：</th><td><input type='text' class='validate[required] memberOrg"+i+"'><span style='margin-left:10px;'></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='addMemberOrg(this,"+i+")' title='添加'></tr>";
            $(".memberTbody").append(html);
        }
        function delTr(obj){
            $(obj).parents("tr").remove();
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("td"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function addChairmanOrg(){
            var html = "<tr><th colspan='5' style='text-align:right;padding:0px;'>单位：</th><td><input type='text' class='validate[required] chairmanOrg'><span style='margin-left:10px;'></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this)' title='删除'></td></tr>";
            $(".chairmanOrgTbody").append(html);
        }
        function addMemberOrg(obj,inx){
            var i = $(".orgTr"+inx).length+1;
            var html = "<tr class='orgTr"+inx+"'><th colspan='5' style='text-align:right;padding:0px;'>单位：</th><td><input type='text' class='validate[required] memberOrg"+inx+"'><span style='margin-left:10px;'></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this)' title='删除'></td></tr>";
            if($("tr[class*='orgTr"+inx+"']").length > 0){
                $("tr[class*='orgTr"+inx+"']:last").after(html);
            }else{
                $(obj).parents("tr").after(html);
            }
        }
        function delTr(obj) {
            $(obj).parents("tr").remove();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <table class="basic" style="width: 100%;">
                <tr>
                    <th style="text-align:right;padding:0px;">答辩起止时间：</th>
                    <td colspan="5">
                        <c:if test="${param.viewFlag ne 'view'}">
                            <input type="text" style="width:120px;" class="validate[required] defenceBeginTime" value="${defence.defenceBeginTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" onchange="checkTime(this)" readonly="readonly"> — <input type="text" style="width:120px;" class="validate[required] defenceEndTime" value="${defence.defenceEndTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" onchange="checkTime(this)" readonly="readonly">
                        </c:if>
                        <c:if test="${param.viewFlag eq 'view'}">${defence.defenceBeginTime} — ${defence.defenceEndTime}</c:if>
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;padding:0px;">答辩地点：</th>
                    <td colspan="5">
                        <c:if test="${param.viewFlag ne 'view'}">
                            <input type="text" class="validate[required]" name="prov" style="width: 60px;">省
                            <input type="text" class="validate[required]" name="city" style="width: 60px;">市
                            <input type="text" class="validate[required]" name="unit" style="width: 70px;">单位
                            <input type="text" class="validate[required]" name="building" style="width: 50px;">楼
                            <input type="text" class="validate[required]" name="floor" style="width: 50px;">层
                            <input type="text" class="validate[required]" name="room" style="width: 45px;">房间

                            <input type="hidden" class="defencePlace" value="${defence.defencePlace}">
                        </c:if>
                        <c:if test="${param.viewFlag eq 'view'}">${defence.defencePlace}</c:if>
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right;padding:0px;">委员会主席姓名：</th>
                    <td>
                        <c:if test="${param.viewFlag ne 'view'}">
                            <input type="text" class="validate[required] chairmanName" value="${defence.chairmanName}">
                        </c:if>
                        <c:if test="${param.viewFlag eq 'view'}">${defence.chairmanName}</c:if>
                    </td>
                    <th style="text-align:right;padding:0px;">职称：</th>
                    <td>
                        <c:if test="${param.viewFlag ne 'view'}">
                            <input type="text" class="validate[required] chairmanPost" value="${defence.chairmanPost}">
                        </c:if>
                        <c:if test="${param.viewFlag eq 'view'}">${defence.chairmanPost}</c:if>
                    </td>
                    <th style="text-align:right;padding:0px;">单位：</th>
                    <td>
                        <c:if test="${param.viewFlag ne 'view'}">
                            <input type="text" class="validate[required] chairmanOrg" value="${pdfn:split(defence.chairmanOrg,',')[0]}"><span style="margin-left:10px;"></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='addChairmanOrg()' title='添加'>
                        </c:if>
                        <c:if test="${param.viewFlag eq 'view'}">${pdfn:split(defence.chairmanOrg,',')[0]}</c:if>
                    </td>
                </tr>
                <tbody class="chairmanOrgTbody">
                    <c:forEach items="${pdfn:split(defence.chairmanOrg,',')}" var="org" varStatus="i">
                        <c:if test="${!i.first}">
                            <tr>
                                <th colspan="5" style="text-align:right;padding:0px;">单位：</th>
                                <td>
                                    <c:if test="${param.viewFlag ne 'view'}">
                                        <input type="text" class="validate[required] chairmanOrg" value="${org}"><span style="margin-left:10px;"></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this)' title='删除'>
                                    </c:if>
                                    <c:if test="${param.viewFlag eq 'view'}">${org}</c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
                <tr style="display:${param.viewFlag ne 'view'?'':'none'}">
                    <th colspan="6" style="text-align:left;padding-left:15px;">添加委员信息<span style="margin-left:10px;"></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='addMember()' title='添加'></th>
                </tr>
                <tbody class="memberTbody">
                    <c:if test="${empty memberList}">
                        <tr>
                            <th style="text-align:right;padding:0px;">委员姓名：</th>
                            <td><input type="hidden" name="recordFlow"><input type="text" class="validate[required] memberName"></td>
                            <th style="text-align:right;padding:0px;">职称：</th>
                            <td><input type="text" class="validate[required] memberPost"></td>
                            <th style="text-align:right;padding:0px;">单位：</th>
                            <td><input type="text" class="validate[required] memberOrg0"><span style="margin-left:10px;"></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick="addMemberOrg(this,'0')" title='添加'></td>
                        </tr>
                    </c:if>
                    <c:forEach items="${memberList}" var="mem" varStatus="i">
                        <tr>
                            <th style="text-align:right;padding:0px;">委员姓名：</th>
                            <td>
                                <c:if test="${param.viewFlag ne 'view'}">
                                    <input type="hidden" class="recordFlow" value="${mem.recordFlow}">
                                    <input type="text" class="validate[required] memberName" value="${mem.memberName}">
                                </c:if>
                                <c:if test="${param.viewFlag eq 'view'}">${mem.memberName}</c:if>
                            </td>
                            <th style="text-align:right;padding:0px;">职称：</th>
                            <td>
                                <c:if test="${param.viewFlag ne 'view'}">
                                    <input type="text" class="validate[required] memberPost" value="${mem.memberPost}">
                                </c:if>
                                <c:if test="${param.viewFlag eq 'view'}">${mem.memberPost}</c:if>
                            </td>
                            <th style="text-align:right;padding:0px;">单位：</th>
                            <td>
                                <c:if test="${param.viewFlag ne 'view'}">
                                    <input type="text" class="validate[required] memberOrg${i.index}" value="${pdfn:split(mem.memberOrg,',')[0]}"><span style="margin-left:10px;"></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick="addMemberOrg(this,'${i.index}')" title='添加'>
                                </c:if>
                                <c:if test="${param.viewFlag eq 'view'}">${pdfn:split(mem.memberOrg,',')[0]}</c:if>
                            </td>
                        </tr>
                        <c:forEach items="${pdfn:split(mem.memberOrg,',')}" var="org" varStatus="j">
                            <c:if test="${!j.first}">
                                <c:if test="${param.viewFlag ne 'view'}">
                                    <tr class="orgTr${i.index}"><th colspan='5' style='text-align:right;padding:0px;'>单位：</th><td><input type='text' class='validate[required] memberOrg${i.index}' value="${org}"><span style='margin-left:10px;'></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this)' title='删除'></td></tr>
                                </c:if>
                                <c:if test="${param.viewFlag eq 'view'}"><tr><th colspan='5' style='text-align:right;padding:0px;'>单位：</th><td>${org}</td></tr></c:if>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <c:if test="${param.viewFlag ne 'view'}">
                    <input type="button" class="search" onclick="save('Save');" value="保&#12288;存"/>
                    <input type="button" class="search" onclick="save('Submit');" value="提&#12288;交"/>
                </c:if>
                <c:if test="${param.viewFlag eq 'view'}">
                    <input type="button" class="search" onclick="jboxClose()" value="关&#12288;闭"/>
                </c:if>
            </div>
        </form>
    </div>
</div>
</body>
</html>