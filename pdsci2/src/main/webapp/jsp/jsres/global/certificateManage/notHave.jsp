<c:if test="${roleFlag eq 'doctor'}">
    <img src="<s:url value='/jsp/jsres/images/notHave1.png'/>">
</c:if>
<c:if test="${roleFlag ne 'doctor'}">
    <img src="<s:url value='/jsp/jsres/images/notHave.png'/>">
</c:if>