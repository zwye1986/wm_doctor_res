<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    function search(){
        var data = $("#searchForm").serialize();
        jboxPostLoad("content","<s:url value='/zseyjxres/hospital/orgRecruitInfo'/>?isQuery=Y",data ,true);
    }
</script>
   <div class="main_hd">
       <h2>招录概况
         <div class="fr"></div>
       </h2>
	   <div class="div_search" style="text-align:right;">
	       <form id="searchForm">进修批次：
               <select name="batchFlow" class="select"style="width: 120px" onchange="search();">
                   <option value="">全部</option>
                   <c:forEach items="${batchLst}" var="dict">
                       <option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                   </c:forEach>
               </select>
		    </form>
		</div>
   </div>
     <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>科室</th>
                <th>报到人数</th>
                <th>待报到人数</th>
                <th>结业人数</th>
                <th>延期结业人数</th>
            </tr>
            <c:forEach items="${dictTypeEnumDwjxSpeList}"  var="doc">
                <tr>
                    <td>${doc.dictName}</td>
                    <td>${recruitCountMap[doc.dictId][0]}</td>
                    <td>${recruitCountMap[doc.dictId][1]}</td>
                    <td>${recruitCountMap[doc.dictId][2]}</td>
                    <td>${recruitCountMap[doc.dictId][3]}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty dictTypeEnumDwjxSpeList}">
	            <tr>
	                <td colspan="5">无记录</td>
	            </tr>
            </c:if>
          </table>
    </div>

