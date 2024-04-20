<c:if test="${errorMsg != null}">
	<p style="color: var(--bs-warning); text-align:center">
		<c:out value="${errorMsg }" />
	</p>
</c:if>

<%@ include file="forms/loginForm.jsp"%>