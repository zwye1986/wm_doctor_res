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
        function save(){
            if(!$("#myForm").validationEngine("validate")){
                return ;
            }
            jboxPost("<s:url value='/gyxjgl/paper/saveTitle'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
        $(function(){
            chooseCoAuthor('${extInfo.isCoAuthor}');
            chooseCoMessAuthor('${extInfo.isCoMessAuthor}');
            chooseJournal('${extInfo.journalType}');
        });
        function chooseCoAuthor(value){
            if(value == 'Y'){
                $(".coY").show();
                $(".coN").hide();
            }else if(value == 'N'){
                $(".coY").hide();
                $(".coN").show();
            }else{
                $(".coY").hide();
                $(".coN").hide();
            }
        }
        function chooseCoMessAuthor(value){
            if(value == 'Y'){
                $(".coMessY").show();
                $(".coMessN").hide();
            }else if(value == 'N'){
                $(".coMessY").hide();
                $(".coMessN").show();
            }else{
                $(".coMessY").hide();
                $(".coMessN").hide();
            }
        }
        function chooseJournal(value){
            if(value == 'sci'){
                $(".journal").show();
                $(".article").attr("colspan","2")
            }else{
                $(".journal").hide();
                $(".article").attr("colspan","4")
            }
        }
        function bindOwnerOrg(){
            var i = $(".ownerOrg tr").length;
            var html = "<tr><td colspan='2'></td><td colspan='2'>本人作者单位：<input type='text' class='validate[required]' name='ownerOrgList["+i+"].ownerOrg'><span style='margin-left:10px;'></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this,1)' title='删除'></td></tr>";
            $(".ownerOrg").append(html);
        }
        function bindFirstOrg(){
            var i = $(".firstOrg tr").length+1;
            var html = "<tr><td colspan='2'></td><td colspan='2'>所在单位：<input type='text' class='validate[required]' name='authorOrgList["+i+"].authorOrg'><span style='margin-left:10px;'></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this,2)' title='删除'></td></tr>";
            $(".firstOrg").append(html);
        }
        function bindMessOrg(){
            var i = $(".messOrg tr").length+1;
            var html = "<tr><td colspan='2'></td><td colspan='2'>通讯作者单位：<input type='text' class='validate[required]' name='messAuthorOrgList["+i+"].messAuOrg'><span style='margin-left:10px;'></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this,2)' title='删除'></td></tr>";
            $(".messOrg").append(html);
        }
        function bindCoAuthor(value){
            if(value == ""){
                $(".coAuthor").html("");
            }else{
                var html = "";
                var arry = value.split("、");
                for(var i=0;i<arry.length;i++){
                    html += "<tr><td colspan='2'>共同第一作者中文姓名：<input type='text' name='coAuthorList["+i+"].coAuthorName' value='"+arry[i]+"'></td><td colspan='2'>所在单位：<input type='text' name='coAuthorList["+i+"].coAuthorOrgList[0].coAuthorOrg'><span style='margin-left:10px;'></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='bindCoAuthorOrg(this,"+i+")' title='添加'></td></tr>"
                }
                $(".coAuthor").html(html)
            }
        }
        function bindCoAuthorOrg(obj,inx){
            var i = $(".coTr"+inx).length+1;
            var html = "<tr class='coTr"+inx+"'><td colspan='2'></td><td colspan='2'>所在单位：<input type='text' class='validate[required]' name='coAuthorList["+inx+"].coAuthorOrgList["+i+"].coAuthorOrg'><span style='margin-left:10px;'></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this,\"coTr"+inx+"\")' title='删除'></td></tr>";
            if($("tr[class*='coTr"+inx+"']").length > 0){
                $("tr[class*='coTr"+inx+"']:last").after(html);
            }else{
                $(obj).parents("tr").after(html);
            }
        }
        function bindMessAuthor(value){
            if(value == ""){
                $(".messAuthor").html("");
            }else{
                var html = "";
                var arry = value.split("、");
                for(var i=0;i<arry.length;i++){
                    html += "<tr><td colspan='2'>共同通讯作者中文姓名：<input type='text' name='messAuthorList["+i+"].messAuthorName' value='"+arry[i]+"'></td><td colspan='2'>所在单位：<input type='text' name='messAuthorList["+i+"].messAuthorOrgList[0].messAuthorOrg'><span style='margin-left:10px;'></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='bindMessAuthorOrg(this,"+i+")' title='添加'></td></tr>"
                }
                $(".messAuthor").html(html)
            }
        }
        function bindMessAuthorOrg(obj,inx){
            var i = $(".messTr"+inx).length+1;
            var html = "<tr class='messTr"+inx+"'><td colspan='2'></td><td colspan='2'>所在单位：<input type='text' class='validate[required]' name='messAuthorList["+inx+"].messAuthorOrgList["+i+"].messAuthorOrg'><span style='margin-left:10px;'></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this,\"coTr"+inx+"\")' title='删除'></td></tr>";
            if($("tr[class*='messTr"+inx+"']").length > 0){
                $("tr[class*='messTr"+inx+"']:last").after(html);
            }else{
                $(obj).parents("tr").after(html);
            }
        }
        function delTr(obj,type){
            if(type == "1"){
                $(obj).parents("tr").siblings("tr").each(function(i){
                    $(this).find("input").each(function(){
                        var name = $(this).attr("name");
                        var index = name.substring(name.indexOf("[")+1,name.indexOf("]"));
                        $(this).attr("name",name.replace(index,i));
                    })
                });
                $(obj).parents("tr").remove();
            }else if(type == "2"){
                $(obj).parents("tr").siblings("tr").each(function(i){
                    $(this).find("input").each(function(){
                        var name = $(this).attr("name");
                        var index = name.substring(name.indexOf("[")+1,name.indexOf("]"));
                        $(this).attr("name",name.replace(index,i+1));
                    })
                });
                $(obj).parents("tr").remove();
            }else{
                $(obj).parents("tr").remove();
                $("tr[class='"+type+"']").each(function(i){
                    $(this).find("input").each(function(){
                        var name = $(this).attr("name");
                        var index = name.substring(name.lastIndexOf("[")+1,name.lastIndexOf("]"));
                        $(this).attr("name",name.replace(index,i+1));
                    })
                });
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <fieldset>
                <legend>基本信息</legend>
                <table class="basic" style="width: 100%;">
                    <input type="hidden" name="recordFlow" value="${title.recordFlow}"/>
                    <input type="hidden" name="defenceFlow" value="${defence.recordFlow}"/>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;">学号：</th>
                        <td style="text-align:left;padding:0px;border:0px;min-width:80px">${defence.stuNo}</td>
                        <th style="text-align:right;padding:0px;border:0px;">姓名：</th>
                        <td style="text-align:left;padding:0px;border:0px;min-width:80px">${defence.userName}</td>
                        <th style="text-align:right;padding:0px;border:0px;">性别：</th>
                        <td style="text-align:left;padding:0px;border:0px;min-width:80px">${defence.sexName}</td>
                        <th style="text-align:right;padding:0px;border:0px;">专业：</th>
                        <td style="text-align:left;padding:0px;border:0px;min-width:80px">${defence.majorName}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;">培养层次：</th>
                        <td style="text-align:left;padding:0px;border:0px;">${defence.trainGradationName}</td>
                        <th style="text-align:right;padding:0px;border:0px;">培养类型：</th>
                        <td style="text-align:left;padding:0px;border:0px;">${defence.trainCategoryName}</td>
                        <th style="text-align:right;padding:0px;border:0px;">导师：</th>
                        <td style="text-align:left;padding:0px;border:0px;">
                            <c:if test="${!empty defence.tutorName && !empty defence.twoTutorName}"><span style="line-height:20px;">${defence.tutorName}<br>${defence.twoTutorName}</span></c:if>
                            <c:if test="${empty defence.tutorName || empty defence.twoTutorName}">${defence.tutorName}${defence.twoTutorName}</c:if>
                        </td>
                        <th style="text-align:right;padding:0px;border:0px;">导师电话：</th>
                        <td style="text-align:left;padding:0px;border:0px;">
                            <c:if test="${!empty defence.tutorName && !empty defence.twoTutorName}"><span style="line-height:20px;">${defence.tutorPhone}<br>${defence.twoTutorPhone}</span></c:if>
                            <c:if test="${empty defence.tutorName || empty defence.twoTutorName}">${defence.tutorPhone}${defence.twoTutorPhone}</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;">分委员会：</th>
                        <td colspan="3" style="text-align:left;padding:0px;border:0px;">${defence.fwhOrgName}</td>
                        <th style="text-align:right;padding:0px;border:0px;">培养单位：</th>
                        <td colspan="3" style="text-align:left;padding:0px;border:0px;">${defence.pydwOrgName}</td>
                    </tr>
                </table>
            </fieldset>
            <fieldset>
                <legend><c:if test="${isReplenish ne 'Y'}">答辩申请信息</c:if><c:if test="${isReplenish eq 'Y'}">学位补授申请信息</c:if></legend>
                <table class="basic" style="width: 100%;">
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;width:20%;"><c:if test="${isReplenish ne 'Y'}">答辩时间</c:if><c:if test="${isReplenish eq 'Y'}">学位补授时间</c:if>：</th>
                        <td style="text-align:left;padding:0px;border:0px;width:30%;line-height: 130%;">${isReplenish eq 'Y'?defence.replenishTime:defence.defenceTime}</td>
                        <th style="text-align:right;padding:0px;border:0px;width:20%;">评审论文命名：</th>
                        <td style="text-align:left;padding:0px;border:0px;width:30%;line-height: 130%;">${defence.paperAuditName}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;">中文论文题目：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">${defence.paperChiTitle}</td>
                        <th style="text-align:right;padding:0px;border:0px;">英文论文题目：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">${defence.paperEngTitle}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;">关键词：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">${defence.keyWord}</td>
                        <th style="text-align:right;padding:0px;border:0px;">研究方向：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">${defence.researchDirection}</td>
                    </tr>
                </table>
            </fieldset>
            <fieldset>
                <legend>期刊论文题目</legend>
                <table class="basic" style="width: 100%;">
                    <tr>
                        <td colspan="4">发表期刊论文题目：<input type="text" class="validate[required]" name="paperTitle" value="${title.paperTitle}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                    </tr>
                    <tr>
                        <td colspan="2">全部作者中文名单：<input type="text" class="validate[required]" placeholder="按实际出现次序填写" name="authorChiName" value="${extInfo.authorChiName}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                        <td colspan="2">全部作者英文名单：<input type="text" class="validate[required]" placeholder="按实际出现次序填写" name="authorEngName" value="${extInfo.authorEngName}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                    </tr>
                    <tr>
                        <td colspan="2">本人在全部作者中的排名：<input type="text" class="validate[required]" name="ownerRank" value="${extInfo.ownerRank}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                        <td colspan="2">本人作者单位（按实际顺序填写）<span style="margin-left:10px;"></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' <c:if test="${param.viewFlag ne 'view'}">onclick='bindOwnerOrg()'</c:if> title='添加'></td>
                    </tr>
                    <tbody class="ownerOrg">
                        <c:forEach items="${extInfo.ownerOrgList}" var="org" varStatus="i">
                            <tr>
                                <td colspan="2"></td><td colspan="2">本人作者单位：<input type="text" class="validate[required]" name="ownerOrgList[${i.index}].ownerOrg" value="${org.ownerOrg}" ${param.viewFlag eq 'view'?'readonly':''}><c:if test="${param.viewFlag ne 'view'}"><span style="margin-left:10px;"></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick="delTr(this,'1')" title='删除'></c:if></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tr>
                        <td colspan="4">有无共同一作者：<select class="select validate[required]" name="isCoAuthor" style="width:156px;" onchange="chooseCoAuthor(this.value)" ${param.viewFlag eq 'view'?'disabled':''}>
                            <option/>
                            <option value="Y" <c:if test="${extInfo.isCoAuthor eq 'Y'}">selected</c:if>>有</option>
                            <option value="N" <c:if test="${extInfo.isCoAuthor eq 'N'}">selected</c:if>>无</option>
                        </select></td>
                    </tr>
                    <tr class="coY">
                        <td colspan="4">共同第一作者中文名单：<input type="text" class="validate[required]" placeholder="按实际出现次序填写" name="firstCoAuthor" value="${extInfo.firstCoAuthor}" onchange="bindCoAuthor(this.value)" ${param.viewFlag eq 'view'?'readonly':''}><span style="color:red;">&#12288;姓名以“、”分割</span></td>
                    </tr>

                    <tbody class="coY coAuthor">
                        <c:forEach items="${extInfo.coAuthorList}" var="coAut" varStatus="i">
                            <tr>
                                <td colspan="2">共同第一作者中文姓名：<input type="text" class="validate[required]" name="coAuthorList[${i.index}].coAuthorName" value="${coAut.coAuthorName}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                                <td colspan="2">所在单位：<input type="text" class="validate[required]" name="coAuthorList[${i.index}].coAuthorOrgList[0].coAuthorOrg" value="${coAut.coAuthorOrgList[0].coAuthorOrg}" ${param.viewFlag eq 'view'?'readonly':''}><c:if test="${param.viewFlag ne 'view'}"><span style="margin-left:10px;"></span><img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="bindCoAuthorOrg(this,'${i.index}')" title="添加"></c:if></td>
                            </tr>
                            <c:forEach items="${coAut.coAuthorOrgList}" var="org" varStatus="j">
                                <c:if test="${!j.first}">
                                    <tr class="coTr${i.index}">
                                        <td colspan="2"></td>
                                        <td colspan="2">所在单位：<input type="text" class="validate[required]" name="coAuthorList[${i.index}].coAuthorOrgList[${j.index}].coAuthorOrg" value="${org.coAuthorOrg}" ${param.viewFlag eq 'view'?'readonly':''}><c:if test="${param.viewFlag ne 'view'}"><span style="margin-left:10px;"></span><img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delTr(this,'coTr${i.index}')" title="删除"></c:if></td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </tbody>

                    <tr class="coY">
                        <td colspan="4">本人在共同一作中的排名：<input type="text" placeholder="非第一作者，无需填写" name="ownerCoRank" value="${extInfo.ownerCoRank}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                    </tr>
                    <tr class="coN">
                        <td colspan="2">第一作者中文姓名：<input type="text" class="validate[required]" name="firstAuthor" value="${extInfo.firstAuthor}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                        <td colspan="2">所在单位：<input type="text" class="validate[required]" name="authorOrgList[0].authorOrg" value="${extInfo.authorOrgList[0].authorOrg}" ${param.viewFlag eq 'view'?'readonly':''}><c:if test="${param.viewFlag ne 'view'}"><span style="margin-left:10px;"></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='bindFirstOrg()' title='添加'></c:if></td>
                    </tr>
                    <tbody class="firstOrg">
                        <c:forEach items="${extInfo.authorOrgList}" var="org" varStatus="i">
                            <c:if test="${!i.first}">
                                <tr>
                                    <td colspan="2"></td><td colspan="2">所在单位：<input type="text" class="validate[required]" name="authorOrgList[${i.index}].authorOrg" value="${org.authorOrg}" ${param.viewFlag eq 'view'?'readonly':''}><c:if test="${param.viewFlag ne 'view'}"><span style="margin-left:10px;"></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick="delTr(this,'2')" title='删除'></c:if></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                    <tr>
                        <td colspan="4">有无共同通讯者：<select class="select validate[required]" name="isCoMessAuthor" style="width:156px;" onchange="chooseCoMessAuthor(this.value)" ${param.viewFlag eq 'view'?'disabled':''}>
                            <option/>
                            <option value="Y" <c:if test="${extInfo.isCoMessAuthor eq 'Y'}">selected</c:if>>有</option>
                            <option value="N" <c:if test="${extInfo.isCoMessAuthor eq 'N'}">selected</c:if>>无</option>
                        </select></td>
                    </tr>
                    <tr class="coMessY">
                        <td colspan="4">共同通讯作者中文名单：<input type="text" class="validate[required]" placeholder="按实际出现次序填写" name="messCoAuthor" onchange="bindMessAuthor(this.value)" value="${extInfo.messCoAuthor}" ${param.viewFlag eq 'view'?'readonly':''}><span style="color:red;">&#12288;姓名以“、”分割</span></td>
                    </tr>

                    <tbody class="coMessY messAuthor">
                        <c:forEach items="${extInfo.messAuthorList}" var="messAut" varStatus="i">
                            <tr>
                                <td colspan="2">共同通讯作者中文姓名：<input type="text" class="validate[required]" name="messAuthorList[${i.index}].messAuthorName" value="${messAut.messAuthorName}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                                <td colspan="2">所在单位：<input type="text" class="validate[required]" name="messAuthorList[${i.index}].messAuthorOrgList[0].messAuthorOrg" value="${messAut.messAuthorOrgList[0].messAuthorOrg}" ${param.viewFlag eq 'view'?'readonly':''}><c:if test="${param.viewFlag ne 'view'}"><span style="margin-left:10px;"></span><img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="bindMessAuthorOrg(this,'${i.index}')" title="添加"></c:if></td>
                            </tr>
                            <c:forEach items="${messAut.messAuthorOrgList}" var="org" varStatus="j">
                                <c:if test="${!j.first}">
                                    <tr class="messTr${j.index}">
                                        <td colspan="2"></td>
                                        <td colspan="2">所在单位：<input type="text" class="validate[required]" name="messAuthorList[${i.index}].messAuthorOrgList[${j.index}].messAuthorOrg" value="${org.messAuthorOrg}" ${param.viewFlag eq 'view'?'readonly':''}><c:if test="${param.viewFlag ne 'view'}"><span style="margin-left:10px;"></span><img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delTr(this,'2')" title="删除"></c:if></td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </tbody>

                    <tr class="coMessN">
                        <td colspan="2">通讯作者姓名：<input type="text" class="validate[required]" name="messAuthor" value="${extInfo.messAuthor}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                        <td colspan="2">通讯作者单位：<input type="text" class="validate[required]" name="messAuthorOrgList[0].messAuOrg" value="${extInfo.messAuthorOrgList[0].messAuOrg}" ${param.viewFlag eq 'view'?'readonly':''}><c:if test="${param.viewFlag ne 'view'}"><span style="margin-left:10px;"></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='bindMessOrg()' title='添加'></c:if></td>
                    </tr>
                    <tbody class="messOrg">
                        <c:forEach items="${extInfo.messAuthorOrgList}" var="org" varStatus="i">
                            <c:if test="${!i.first}">
                                <tr>
                                    <td colspan="2"></td><td colspan="2">通讯作者单位：<input type="text" class="validate[required]" name="messAuthorOrgList[${i.index}].messAuOrg" value="${org.messAuOrg}" ${param.viewFlag eq 'view'?'readonly':''}><c:if test="${param.viewFlag ne 'view'}"><span style="margin-left:10px;"></span><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick="delTr(this,'2')" title='删除'></c:if></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                    <tr>
                        <td colspan="2">导师是否通讯作者：<select class="select validate[required]" name="isMessAuthor" style="width:156px;" ${param.viewFlag eq 'view'?'disabled':''}>
                            <option/>
                            <option value="Y" <c:if test="${extInfo.isMessAuthor eq 'Y'}">selected</c:if>>是</option>
                            <option value="N" <c:if test="${extInfo.isMessAuthor eq 'N'}">selected</c:if>>否</option>
                        </select></td>
                        <td colspan="2">发表论文刊物名称：<input type="text" class="validate[required]" name="journalName" value="${title.journalName}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                    </tr>
                    <tr>
                        <td colspan="2">发表年份：<input type="text" class="validate[required]" name="publishYear" value="${title.publishYear}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                        <td colspan="2">卷&#12288;&#12288;期：<input type="text" class="validate[required]" name="volume" value="${title.volume}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                    </tr>
                    <tr>
                        <td colspan="2">页面范围：<input type="text" class="validate[required]" name="pageNumber" value="${title.pageNumber}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                        <td colspan="2">刊物类型：<select class="select validate[required]" name="journalType" style="width:156px;" onchange="chooseJournal(this.value)" ${param.viewFlag eq 'view'?'disabled':''}>
                            <option/>
                            <option value="sci" <c:if test="${title.journalType eq 'sci'}">selected</c:if>>SCI</option>
                            <option value="ei" <c:if test="${title.journalType eq 'ei'}">selected</c:if>>EI</option>
                            <option value="zwhx" <c:if test="${title.journalType eq 'zwhx'}">selected</c:if>>中文核心</option>
                            <option value="zwtjy" <c:if test="${title.journalType eq 'zwtjy'}">selected</c:if>>中文统计源</option>
                            <option value="qt" <c:if test="${title.journalType eq 'qt'}">selected</c:if>>其他</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="journal">SCI大类分区：<select class="select validate[required]" name="sciBigArea" style="width:156px;" ${param.viewFlag eq 'view'?'disabled':''}>
                            <option/>
                            <option value="1" <c:if test="${extInfo.sciBigArea eq '1'}">selected</c:if>>一区</option>
                            <option value="2" <c:if test="${extInfo.sciBigArea eq '2'}">selected</c:if>>二区</option>
                            <option value="3" <c:if test="${extInfo.sciBigArea eq '3'}">selected</c:if>>三区</option>
                            <option value="4" <c:if test="${extInfo.sciBigArea eq '4'}">selected</c:if>>四区</option>
                        </select></td>
                        <td colspan="2" class="journal">SCI小类分区：<select class="select validate[required]" name="sciSmalArea" style="width:156px;" ${param.viewFlag eq 'view'?'disabled':''}>
                            <option/>
                            <option value="1" <c:if test="${extInfo.sciSmalArea eq '1'}">selected</c:if>>一区</option>
                            <option value="2" <c:if test="${extInfo.sciSmalArea eq '2'}">selected</c:if>>二区</option>
                            <option value="3" <c:if test="${extInfo.sciSmalArea eq '3'}">selected</c:if>>三区</option>
                            <option value="4" <c:if test="${extInfo.sciSmalArea eq '4'}">selected</c:if>>四区</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="journal">SCI影响因子：<input type="text" class="validate[required]" placeholder="小数点前后各3位" name="scifactor" value="${extInfo.scifactor}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                        <td colspan="4" class="article">文章类型：<select class="select validate[required]" name="articleType" style="width:156px;" ${param.viewFlag eq 'view'?'disabled':''}>
                            <option/>
                            <option value="lz" <c:if test="${extInfo.articleType eq 'lz'}">selected</c:if>>论著</option>
                            <option value="zs" <c:if test="${extInfo.articleType eq 'zs'}">selected</c:if>>综述</option>
                            <option value="blbd" <c:if test="${extInfo.articleType eq 'blbd'}">selected</c:if>>病例报道</option>
                            <option value="meta" <c:if test="${extInfo.articleType eq 'meta'}">selected</c:if>>META分析</option>
                            <option value="qt" <c:if test="${extInfo.articleType eq 'qt'}">selected</c:if>>其他</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td colspan="2">文章发表状态：<select class="select validate[required]" name="articleStatus" style="width:156px;" ${param.viewFlag eq 'view'?'disabled':''}>
                            <option/>
                            <option value="fb" <c:if test="${title.articleStatus eq 'fb'}">selected</c:if>>发表</option>
                            <option value="ly" <c:if test="${title.articleStatus eq 'ly'}">selected</c:if>>录用</option>
                            <option value="qt" <c:if test="${title.articleStatus eq 'qt'}">selected</c:if>>投稿</option>
                        </select></td>
                        <td colspan="2">备注：<input type="text" class="validate[required]" name="memo" value="${extInfo.memo}" ${param.viewFlag eq 'view'?'readonly':''}></td>
                    </tr>
                </table>
            </fieldset>
            <div style="text-align: center;margin-top:20px;">
                <c:if test="${param.viewFlag ne 'view'}">
                    <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                </c:if>
                <c:if test="${param.viewFlag eq 'view'}">
                    <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭"/>
                </c:if>
            </div>
        </form>
    </div>
</div>
</body>
</html>