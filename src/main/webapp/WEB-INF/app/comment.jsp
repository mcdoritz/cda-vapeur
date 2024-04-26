<c:if test="${errorMsg != null}">
	<p style="color: var(--bs-warning); text-align:center">
		<c:out value="${errorMsg }" />
	</p>
</c:if>
<c:if test="${infoMsg != null}">
	<p style="color: var(--theme-color); text-align:center">
		<c:out value="${infoMsg }" />
	</p>
</c:if>
<c:if test="${comment != null }">
	<%@ include file="forms/commentForm.jsp"%>
</c:if>
