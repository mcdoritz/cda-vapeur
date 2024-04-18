<div class="breadcumb-wrapper " data-bg-src="assets/img/bg/breadcumb-bg<c:out value="${pageTitle != null ? pageTitle : ''}"/>.gif">
	<div class="container">
		<div class="breadcumb-content">
			<h1 class="breadcumb-title"><c:out value="${pageTitle != null ? pageTitle : 'Vapeur Store Inc.' }" /></h1>
		</div>
		<c:if test="${sessionScope.user != null }">
			<c:if test="${pageTitle == 'Profil' || pageTitle == 'Bibliothèque' }">
				<h3 style="color:white"><c:out value="de Msieur'dame ${sessionScope.user.nickname } !"/></h3>
			</c:if>
		</c:if>
	</div>
</div>