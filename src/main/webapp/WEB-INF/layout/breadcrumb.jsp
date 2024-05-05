<div class="breadcumb-wrapper " style="margin-top:4em; background-color:transparent;  padding-bottom: 0;">
	<div class="container">
		<div class="breadcumb-content">
			<h1 class="breadcumb-title"><c:out value="${pageTitle != null ? pageTitle : 'Vapeur Store Inc.' }" /></h1>
		</div>
		<c:if test="${sessionScope.user != null }">
			<c:if test="${pageTitle == 'Profil' || pageTitle == 'Bibliothèque' }">
				<h3 style="color:white"><c:out value="de ${sessionScope.user.nickname } !"/></h3>
			</c:if>
		</c:if>
	</div>
</div>