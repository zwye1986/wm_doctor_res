<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
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
<script type="text/javascript">
    function audit(operstatusid,funddetailflow,money,rejectreason){
        jboxConfirm("确认"+(('${achStatusEnumSecondAudit.id}'==operstatusid)?"通过":"驳回")+"?" , function(){
            var fundFlow = $("input[name='fundFlow']").val();
            var data = {
                "fundDetailFlow": funddetailflow,
                "operStatusId":operstatusid,
                "content":rejectreason,
                "fundFlow":fundFlow,
                "money":money
            };
            var url="<s:url value='/srm/fund/auditFundDetail'/>";
            jboxPost(url,data,function(resp){
                if(resp=='${GlobalConstant.OPERATE_SUCCESSED}'){
                    jboxContentClose();
                    window.parent.frames['mainIframe'].window.search();
                    jboxCloseMessager();
                }
            },null,false);

        });


    }

    function saveDetails(){

        if(!$("#detailsForm").validationEngine("validate")){
    		return false;
    	}
    	jboxConfirm("确认提交?" , function(){
		var authTab = $('#test');//query选择器:id为test的元素
		var trs = authTab.children();//.children()方法获取authTab的子元素
		var datas = [];//js数组对象   datas[0]
		var fundFlow = $("input[name='fundFlow']").val();//jquery选择器:选择标签名为input,属性name值为fundFlow的所有元素,.val()取得元素值
		var isDataExist = false;
            $.each(trs , function(i , n){//jquery迭代器,用于循环数组和json  i表示索引   n表示当前循环的元素
			var fundSourceId =  $(n).find("select[name='fundSourceId']").val();//$(n)  $(js对象)   将js对象转换成jquery对象
			var realityTypeId =  $(n).find("input[name='realityTypeId']").val();
			var realityTypeName =  $(n).find("input[name='realityTypeName']").val();
			var money= $(n).find("input[name='money']").val();
			var provideDateTime= $(n).find("input[name='provideDateTime']").val();
			var provideOrg= $(n).find("input[name='provideOrg']").val();
            var operStatusId = "";
            <c:if test="${sessionScope.projListScope=='finance'}">
                operStatusId = "${achStatusEnumSecondAudit.id}";
            </c:if>
			var data = {//json对象   {key:value}
						"fundFlow":fundFlow,
						"fundSourceId":fundSourceId,
						"realityTypeId":realityTypeId,
						"realityTypeName":realityTypeName,
						"money":money/10000,
						"provideDateTime":provideDateTime,
						"provideOrg":provideOrg,
                        "fundTypeId":'${projFundTypeEnumIncome.id}',
                        "operStatusId":operStatusId
					};
			datas.push(data);//数组的push方法
                if(money != null && realityTypeId != null && provideDateTime != null && provideOrg != null){
                    isDataExist = true;
                }
		});
		var requestData = JSON.stringify(datas);//将数组对象转换成json字符串
		var url="<s:url value='/srm/fund/saveDetail'/>";
            if(isDataExist){
                jboxStartLoading();
                jboxPostJson(url,requestData,function(resp){
                    if(resp=='${GlobalConstant.SAVE_SUCCESSED}'){
                        window.parent.frames['mainIframe'].window.search();
                        jboxCloseMessager();
                    }
                },null,true);
            }else{
                jboxTip("没有修改数据");
            }
    	});

    }
    
    function doClose() {
    	jboxClose();
	}
    function check(obj){
       if(obj.checked==true){
    	   obj.value=1;  
       }	
       else{
    	   obj.value=0;   
       }	
    }
    function addItem(){
    	$('#test').append($("#moban tr:eq(0)").clone());
    }
    function delItemTr(obj){
    	var tr=obj.parentNode.parentNode;
        tr.remove();
    }  
    function delFundDetail(detailFlow) {
        jboxConfirm("确认删除该记录?", function () {
            jboxStartLoading();
            var url = "<s:url value='/srm/fund/delDetail?fundDetailFlow='/>" + detailFlow;
            jboxGet(url, null, function () {
                $("#searchForm", window.parent.frames['mainIframe'].document).submit();
                jboxCloseMessager();
            }, null, true);
        });

    }


    function rejectReason(operStatusId,detailFlow){
        jboxOpenContent(
                '<table class="bs_tb" width="100%" cellspacing="0" cellpadding="0">' +
                '<tr><th style="text-align:left">驳回理由</th></tr>' +
                '<tr><td><textarea id="rejectReason" rows="6" cols="70"></textarea></td></tr>' +
                ' <tr></table><div class="button"><input type="button" class="search" value="确定" onclick="top.frames[\'jbox-message-iframe\'].audit(\''+operStatusId+'\',\''+detailFlow+'\',null,$(\'#rejectReason\').val())" /> ' +
                '<input type="button" class="search" value="取消" onclick="jboxContentClose();" /></div></tr>' +
                '','驳回理由',500,200,true);
    }

    function getRealityTypeId(obj){
        var realityTypeName = $(obj).find("option:selected").attr("realityTypeName");
        if(realityTypeName == '下拨'){
            $("#realityTypeId").val("Allocate");
            $("#realityTypeName").val("下拨");
        }else if(realityTypeName == '配套'){
            $("#realityTypeId").val("Matching");
            $("#realityTypeName").val("配套");
        }
    }
</script>
</head>
<body>
<div id="main">
   <div class="mainright">
      <div class="content">
          <p style="font-weight: bold;font-size: 14px;"> 项目名称：${proj.projName} &nbsp;&nbsp;&nbsp;&nbsp;项目编号：${proj.projNo }&nbsp;&nbsp;&nbsp;&nbsp;项目类型：${proj.projTypeName}</p>     
          <br/>
        <div class="title1 clearfix">
        <form id="detailsForm">
         <table class="bs_tb" width="100%" cellspacing="0" cellpadding="0">
             <c:if test="${param.view ne 'Y'}">
           <tr>
               <th colspan="8" class="bs_name">到账编辑<a href="javascript:void(0)">
                   <img
                       src="<s:url value='/'/>css/skin/${skinPath}/images/add.png" onclick="addItem();"
                       title="添加"/></a>

               </th>
		   </tr>
             </c:if>
		   <tr>
             <th style="text-align: center; width:15%">到账类型</th>
             <th style="text-align: center; width:15%;">到账金额(元)</th>
             <th style="text-align: center; width:18%;">到账时间</th>
             <th style="text-align: center;">拨款单位</th>
               <c:if test="${fn:split(proj.projTypeId,'.')[0] ne 'jsszyy'}">
             <th style="text-align: center;">审核状态</th>
               </c:if>
               <c:if test="${param.view ne 'Y'}">
             <th style="text-align: center;width: 10%;">操作</th>
               </c:if>
           </tr>
            <c:forEach items="${details}" var="detail">
            <tr>
             <td>
            	${detail.fundSourceName}
             </td>
             <td>
                ${pdfn:transMultiply(detail.money,10000)}
             </td>
             <td>
                ${pdfn:transDateTime(detail.provideDateTime)}
             </td>
             <td>
                ${detail.provideOrg}
             </td>
             <c:if test="${sessionScope.projListScope=='finance' and (fn:split(proj.projTypeId,'.')[0] ne 'jsszyy')}">
                <td>
                    <c:if test="${!(detail.operStatusId eq achStatusEnumSecondAudit.id) and !(detail.operStatusId eq achStatusEnumSecondBack.id) and (param.view ne 'Y')}">
                        <a href="javascript:void(0);" onclick="audit('${achStatusEnumSecondAudit.id}','${detail.fundDetailFlow}','${detail.money}')" style="color: green;">同意</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" onclick="rejectReason('${achStatusEnumSecondBack.id}','${detail.fundDetailFlow}')" style="color: red;">驳回</a>
                    </c:if>
                    <c:if test="${!(!(detail.operStatusId eq achStatusEnumSecondAudit.id) && !(detail.operStatusId eq achStatusEnumSecondBack.id))}">
                    ${detail.operStatusName}&nbsp;
                    </c:if>
                </td>
             </c:if>
             <c:if test="${sessionScope.projListScope=='local'}" >
                <td>
                    ${detail.operStatusName}
                </td>
             </c:if>
                <c:if test="${param.view ne 'Y'}">
             <td>
                 <a href="javascript:void(0);" onclick="delFundDetail('${detail.fundDetailFlow}')">删除</a>
             </td>
                </c:if>
           </tr>
           </c:forEach> 
           <tbody id="test">
           </tbody>
         </table>
       <div class="button" >
       		<input type="hidden" name="fundFlow" value="${param.fundFlow}" />
       		<input id="jsondata" type="hidden" name="jsondata" value=""/>
           <input type="button" value="提&#12288;交" onclick="saveDetails();" class="search"/>&#12288;<input type="button" value="关&#12288;闭" onclick="jboxCloseMessager();" class="search"/>
       </div>
       </form>
       
     <table id="moban" style="display: none">
        <tr>
             <td>
              <select name="fundSourceId" class="validate[required]" style="width: 90%;" onchange="getRealityTypeId(this)">
                <option value="">请选择</option>
                <%--<c:forEach var="fundAccountsType" items="${projFundAccountsTypeEnumList}">
                   <option value="${fundAccountsType.id}" >${fundAccountsType.name}</option>
                </c:forEach>--%>
                  <c:forEach items="${dictTypeEnumProjFundAccountsTypeList}" var="dict" varStatus="status">
                      <option value="${dict.dictId}" realityTypeName="${dict.dictDesc}" >${dict.dictName}</option>
                  </c:forEach>
              </select>
                 <input type="hidden" id="realityTypeId" name="realityTypeId" />
                 <input type="hidden" id="realityTypeName" name="realityTypeName"  />
             </td>
             <td>
                <input style="width: 90%; text-align: center;" type="text" name="money" class="validate[required,custom[number],min[0]]" />
             </td>
             <td>
                 <c:choose>
                     <c:when test="${sysCfgMap['srm_local_type'] eq 'Y'}">
                         <input type="text" name="provideDateTime" class="validate[required] ctime" style="width:90%" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                     </c:when>
                     <c:otherwise>
                         <input type="text" name="provideDateTime" class="validate[required] ctime" style="width:90%" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${pdfn:getCurrDateTime('yyyy-MM-dd HH:mm:ss')}" />
                     </c:otherwise>
                 </c:choose>
             </td>
             <td>
                 <input class="validate[required]" style="width:96%" type="text" name="provideOrg"/>
             </td>
            <c:if test="${fn:split(proj.projTypeId,'.')[0] ne 'jsszyy'}">
             <td>

             </td>
            </c:if>
             <td> 
            	 <a href="javascript:void(0);" onclick="delItemTr(this)">删除</a>
             </td>
           </tr>
           </table>

         </div>
     </div> 	
   </div>
</div>
</body>
</html>