<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	$(function(){
		var li = $("#${param.id}");
		if(li.length>0){
			$(".tab_select").toggleClass("tab");
			$(".tab_select").toggleClass("tab_select");
			$(li).toggleClass("tab_select");
			$(li).toggleClass("tab");
		}
	});
	function getSpeList(catSpeId,obj){
		$(".tab_select").removeClass("tab_select");
		$(obj).addClass("tab_select");
		$(".spediv").hide();
		$("#"+catSpeId).show();
	}
</script>
      <div class="main_hd">
        <h2>培训专业</h2>
        <div class="title_tab" id="toptab">
          <ul>
            <li class="tab_select" onclick="getSpeList('${speCatEnumChineseMedicine.id}',this);"><a>${speCatEnumChineseMedicine.name}</a></li>
            <li class="" onclick="getSpeList('${speCatEnumTCMGeneral.id}',this);"><a>${speCatEnumTCMGeneral.name}</a></li>
            <li class="" onclick="getSpeList('${speCatEnumTCMAssiGeneral.id}',this);"><a>${speCatEnumTCMAssiGeneral.name}</a></li>
          </ul>
        </div>
      </div>
      <div class="main_bd" id="div_table_0" >
        <div class="div_table spediv" id="${speCatEnumChineseMedicine.id}">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th  style="text-align: left;padding-left: 40px;">对应专业代码</th>
                    <th  style="text-align: left;padding-left: 40px;">对应专业名称</th>
                </tr>
                <c:forEach items="${dictTypeEnumZyCoSpeList}" var="dict">
                    <tr>
                        <td style="text-align: left;padding-left: 40px;">${dict.dictId}</td>
                        <td style="text-align: left;padding-left: 40px;">${dict.dictName}</td>
                    </tr>
                </c:forEach>
            </table>

            <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
              <th  style="text-align: left;padding-left: 40px;">二级专业代码</th>
              <th  style="text-align: left;padding-left: 40px;">二级专业名称</th>
            </tr>
            <c:forEach items="${dictTypeEnumZySpeList}" var="dict">
            <tr>
              <td style="text-align: left;padding-left: 40px;">${dict.dictId}</td>
              <td style="text-align: left;padding-left: 40px;">${dict.dictName}</td>
            </tr>
            </c:forEach>
          </table>
        </div>
      <div class="div_table spediv" id="${speCatEnumTCMGeneral.id}" style="display: none">
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
              <th  style="text-align: left;padding-left: 40px;">对应专业代码</th>
              <th  style="text-align: left;padding-left: 40px;">对应专业名称</th>
            </tr>
            <c:forEach items="${ dictTypeEnumZyqkCoSpeList}" var="dict">
            <tr>
              <td style="text-align: left;padding-left: 40px;">${dict.dictId}</td>
              <td style="text-align: left;padding-left: 40px;">${dict.dictName}</td>
            </tr>
            </c:forEach>
          </table>
      </div>
      </div>
      
