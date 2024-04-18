<c:if test="${sessionScope.user == null }">
<div class="container" style="margin-top:1em">
            <div class="title-area text-center" style="margin-bottom:0">
                <h2 class="sec-title text-white" style="margin-bottom:0">Quel est votre profil ?</h2>
                <p style="margin-bottom:0">Pour le savoir, connectez-vous.</p>
            </div>
        </div>
	<%@ include file="forms/loginForm.jsp"%>
</c:if>
<c:if test="${sessionScope.user != null }">
	<c:out value="user loggué"/>
</c:if>