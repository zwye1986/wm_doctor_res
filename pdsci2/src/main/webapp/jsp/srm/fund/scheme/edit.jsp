
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
  function save(){
	  if($("#newScheme").validationEngine("validate")){
		  var url = "<s:url value='/srm/fund/scheme/saveScheme'/>";
		  jboxStartLoading();
	      jboxPost(url , $('#newScheme').serialize() , function(){
		  $("#schemeForm",window.parent.frames['mainIframe'].document).submit(); jboxClose(); } , null , true);
	  }
  }
  function searchProjCategory(){
      var dictFlow = $("select[name='projFirstSourceId'] option:selected").attr("dictFlow");
      var dictTypeId = $("#projFirstSourceId").val();
      attrOptionText('projFirstSourceName',$("#projFirstSourceId"));
      var data = {
          dictFlow : dictFlow,
          dictTypeId : dictTypeId
      };
      var url = "<s:url value='/sys/dict/getCategoryDictByDeclarer'/>";
      jboxPost(url , data , function(data){
          //清空原类别！
          $("select[name=projSecondSourceId] option[value != '']").remove();
          var dataObj = data;
          for(var i =0;i<dataObj.length;i++){
              var cId =dataObj[i].dictId;
              var cName =dataObj[i].dictName;
              $option =$("<option></option>");
              $option.attr("value",cId);
              $option.text(cName);
              if("${srmFundScheme.projSecondSourceId}" == cId){
                  $option.attr("selected",true);
              }
              $("select[name='projSecondSourceId']").append($option);
          }
      } , null , false);
  }
$(document).ready(function(){
    searchProjCategory();
});
  function attrOptionText(name,obj){
      var text = $(obj).find("option:selected").text();
//            alert(text);
      if(text) {
          $("input[name='" + name + "']").val(text);

      }
  }
</script>
</head>
<body>
    <div class="mainright">
        <div class="content">
        <div class="title1 clearfix">
            <form id="newScheme" action="<s:url value="/srm/fund/scheme/saveScheme"/>" method="post">
                <input type="hidden" value="jsszyy.kyxm" />
                <table class="xllist">
                    <tr>
                        <th>预算方案名称：</th>
                        <td>
                            <input type="text" name="schemeName" style="width: 90%;" value="${srmFundScheme.schemeName}" class="xltext validate[required]">
                            <input type="hidden" name="schemeFlow" style="width: 90%;" value="${srmFundScheme.schemeFlow}" class="xltext validate[required]">
                        </td>
                    </tr>
                    <c:choose>
                    <c:when test="${(sysCfgMap['srm_for_use'] eq 'local') and (sysCfgMap['srm_local_type'] eq 'Y')}">
                    <tr>
                        <th>项目类型：</th>
                        <td>
                            <select name="projTypeId" style="width: 90%;" class="xlselect validate[required]">
                                <option value="">请选择</option>
                                    <c:forEach items="${existProjTypeIds}" var="dict">
                                        <option value="${dict.dictId }">${dict.dictName }</option>
                                    </c:forEach>
                            </select>
                        </td>
                    </tr>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                    </c:choose>
                </table>
            </form>
            </div>
            <div class="button" style="width: 400px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                <input type="button" onclick="jboxClose();" class="search" value="关&#12288;闭"/>
            </div>
       </div>
   </div>
</body>