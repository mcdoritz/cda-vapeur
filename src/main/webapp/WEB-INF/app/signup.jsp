<c:if test="${errorMsg != null}">
	<p style="color: var(--bs-warning); text-align:center">
		<c:out value="${errorMsg }" />
	</p>
</c:if>

<c:if test="${!signupDone }">
	<%@ include file="forms/signupForm.jsp"%>
</c:if>

<c:if test="${signupDone }">
	<p>Bienvenue !</p>
	<a href="profile" class="th-btn style-border"> <span
		class="btn-border"> Compléter mon profil ! <i
			class="fa-solid fa-user-plus"></i>
	</span>
	</a>
</c:if>
