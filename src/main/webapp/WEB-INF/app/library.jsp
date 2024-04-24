<c:if test="${sessionScope.user == null }">
	<div class="container" style="margin-top: 1em">
		<div class="title-area text-center" style="margin-bottom: 0">
			<h2 class="sec-title text-white" style="margin-bottom: 0">Quand
				on est connecté, on a une bibliothèque !</h2>
			<p style="margin-bottom: 0">Et quand on n'est pas connecté... on
				se connecte.</p>
		</div>
	</div>
	<%@ include file="forms/loginForm.jsp"%>
</c:if>
<c:if test="${sessionScope.user != null }">
	<c:if test="${errorMsg != null}" >
		<p style="color: var(--bs-warning); text-align:center">
			<c:out value="${errorMsg }" />
		</p>
	</c:if>
	<c:if test="${gamesList.size() == 0 }" >
		<p style="color: var(--theme-color); text-align:center">
			Aucun jeu trouv&#233; !
		</p>
	</c:if>
		<div class="container">
            <div class="title-area text-center custom-anim-top wow  animated" data-wow-duration="1.5s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1.5s; animation-delay: 0.2s; animation-name: custom-anim-top;">
                <span class="sub-title style2"># Tous mes jeux</span>
            </div>
			<c:if test="${errorMsg == null && gamesList.size() > 0}">
	            <div class="table-responsive">
	                <table class="table tournament-table">
	                    <thead>
	                        <tr>
	                            <th scope="col">#</th>
	                            <th scope="col">Jeux</th>
	                            <th scope="col">Plateforme</th>
	                            <th scope="col">Ma note</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    	<c:forEach var="game" items="${gamesList }" varStatus="i">
		                        <tr>
		                            <th scope="row"><c:out value="${i.count }"/></th>
		                            <td><a href="game?id=${game.id }"><img src="assets/img/tournament/1-1.png" alt="img"><c:out value="${game.title }"/></a></td>
		                            <td><c:out value="${game.platform.name }"/></td>
		                            <td>
		                            	<c:if test="${game.comment.score > 0 }">
		                            		<a href="comment?game_id=${game.id }"><c:out value="${game.comment.score }"/></a>
	                            		</c:if>
	                            		<c:if test="${game.comment.score < 0 }">
		                            		<a href="comment?game_id=${game.id }">NOTER !</a>
	                            		</c:if>
		                            </td>
		                        </tr>
	                        </c:forEach>
	                    </tbody>
	                </table>
	            </div>
            </c:if>
        </div>
	
	<c:if test="${infoMsg != null}">
		<p style="color: var(--theme-color); text-align:center">
			<c:out value="${infoMsg }" />
		</p>
	</c:if>
	
		
</c:if>