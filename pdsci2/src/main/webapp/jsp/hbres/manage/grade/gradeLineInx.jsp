<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
        $("#toptab li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
            if($(this).attr('id')=="charge"){//统一划线
                jboxStartLoading();
                jboxLoad("content2" , "<s:url value='/hbres/grade/gradeline'/>",true);
            }
            if($(this).attr("id")=="hospital"){//基地划线
                jboxStartLoading();
                jboxLoad("content2" , "<s:url value='/hbres/grade/gradelineOrg'/>", true);
            }
        });
        if('${param.firstFlag}'=='Y'){
            $("#charge").click();
        }
	});
</script>
      <div class="main_hd">
        <h2>分数划定</h2>
        <div class="title_tab" id="toptab">
          <ul>
            <li class="tab_select" id='charge'><a>统一划线</a></li>
            <li id='hospital'><a>基地划线</a></li>
          </ul>
        </div>
      </div>
<div id="content2">
</div>
