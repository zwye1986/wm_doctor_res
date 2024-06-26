<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>

<script type="text/javascript">
    $(function(){
        $(".qualifiedNo").hide();
        $(".isAssistance").hide();
    })
    function showId2(obj){
        if($(obj).val() == "Y"){
            $(".qualifiedNo").show();
        }else{
            $(".qualifiedNo").hide();
            $(".qualifiedNo input,.qualifiedNo select").each(function(){
                $(this).val("");
            });
        }
    }
    function showId3(obj){
        if($(obj).val() == "Y"){
            $(".isAssistance").show();
        }else{
            $(".isAssistance").hide();
            $(".isAssistance input,.isAssistance select").each(function(){
                $(this).val("");
            });
        }
    }
    function submit(){
        jboxConfirm("提交后不能更改，确认提交？",function(){
            $("#infoForm").submit();
            jboxClose();
        });
    }

</script>
</head>

<body>
  <div class="main_wrap">
    <div class="user-contain">
      <div class="user-bd">
        <h2 style="color: #459ae9">个人信息</h2>
        <form id="submitForm" method="post"  action="<s:url value='/hbres/singup/doctor/completeNewInfo'/>">
        <input type="hidden" name="doctorFlow" value="${param.doctorFlow}">
        <table cellpadding="0" cellspacing="0" border="0" class="form_tab" style="width: 600px;">
          <colgroup>
            <col width="25%" />
            <col width="25%" />
          </colgroup>
            <tr class="even">
                <th><font color="red">*</font>是否取得执业证：</th>
                <td>
                    <label><input type="radio" name="practPhysicianFlag" value="Y"  class="validate[required]" onchange="showId2(this);"/>是</label>&#12288;
                    <label><input type="radio" name="practPhysicianFlag"  value="N"  class="validate[required]" onchange="showId2(this);"/>否</label>
                </td>
            </tr>
            <tr class="odd">
                <th class="qualifiedNo"><font color="red">*</font>执业医师证号：</th>
                <td class="qualifiedNo">
                    <input type="text" name="practPhysicianCertificateNo" class="validate[required] select" >
                </td>
            </tr>
            <tr class="even">
                <th><font color="red">*</font>人员属性：</th>
                <td>
                    <select id="personnelAttributeId" name="personnelAttributeId" class="select validate[required]">
                        <option value="">请选择</option>
                        <c:forEach items="${personnelAttributeEnumList}" var="attr">
                            <option value="${attr.id}">${attr.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr class="odd">
                <th><font color="red">*</font>是否为援疆援藏住院医师：</th>
                <td>
                    <label><input type="radio" name="isAssistance"  value="Y"  class="validate[required]" onchange="showId3(this);"/>是</label>&#12288;
                    <label><input type="radio" name="isAssistance"  value="N"  class="validate[required]" onchange="showId3(this);"/>否</label>
                </td>
            </tr>
            <tr class="even isAssistance">
                <th><font color="red">*</font>援疆援藏住院医师送出省份：</th>
                <td><input type="text" name="assistanceProvince"  class="validate[required] select" ></td>
            </tr>
            <tr class="odd isAssistance">
                <th style="line-height: 25.5px;">援疆援藏住院医师送出单位统一社会信用代码/组织机构代码：</th>
                <td><input type="text" name="assistanceCode" class="select"></td>
            </tr>
        </table>
            <p style="text-align: center;width: 600px">
                <input type="button" class="btn_blue" onclick="submit()" value="提&#12288;交">
            </p>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
