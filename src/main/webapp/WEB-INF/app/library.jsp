<c:if test="${sessionScope.user == null }">
<div class="container" style="margin-top:1em">
            <div class="title-area text-center" style="margin-bottom:0">
                <h2 class="sec-title text-white" style="margin-bottom:0">Quand on est connect�, on a une biblioth�que !</h2>
                <p style="margin-bottom:0">Et quand on n'est pas connect�... on se connecte.</p>
            </div>
        </div>
	<%@ include file="forms/loginForm.jsp"%>
</c:if>
<c:if test="${sessionScope.user != null }">
	<c:out value="user loggu�"/>
</c:if>