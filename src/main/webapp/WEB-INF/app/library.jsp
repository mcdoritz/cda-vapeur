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
	<c:if test="${gamesList != null}">
		<div class="container">
            <div class="title-area text-center custom-anim-top wow  animated" data-wow-duration="1.5s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1.5s; animation-delay: 0.2s; animation-name: custom-anim-top;">
                <span class="sub-title style2"># Tous mes jeux</span>
            </div>

            <div class="table-responsive">
                <table class="table tournament-table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Jeux</th>
                            <th scope="col">Plateforme</th>
                            <th scope="col">Evaluation</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td><a href="tournament.html"><img src="assets/img/tournament/1-1.png" alt="img">PRO Player</a></td>
                            <td>GS6</td>
                            <td>4</td>

                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td><a href="tournament.html"><img src="assets/img/tournament/1-2.png" alt="img">The Lion King</a></td>
                            <td>GS6</td>
                            <td>4</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td><a href="tournament.html"><img src="assets/img/tournament/1-3.png" alt="img">The Assassin King</a></td>
                            <td>GS6</td>
                            <td>4</td>

                        </tr>
                        <tr>
                            <th scope="row">4</th>
                            <td><a href="tournament.html"><img src="assets/img/tournament/1-4.png" alt="img">Cyberpunk</a></td>
                            <td>GS6</td>
                            <td>4</td>

                        </tr>
                        <tr>
                            <th scope="row">5</th>
                            <td><a href="tournament.html"><img src="assets/img/tournament/1-5.png" alt="img">Team Gorilla</a></td>
                            <td>GS6</td>
                            <td>4</td>

                        </tr>
                        <tr>
                            <th scope="row">6</th>
                            <td><a href="tournament.html"><img src="assets/img/tournament/1-6.png" alt="img">King Of Badgamer</a></td>
                            <td>GS6</td>
                            <td>4</td>

                        </tr>
                        <tr>
                            <th scope="row">7</th>
                            <td><a href="tournament.html"><img src="assets/img/tournament/1-7.png" alt="img">Team Ninja</a></td>
                            <td>GS6</td>
                            <td>4</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
	</c:if>
	<c:if test="${infoMsg != null}">
		<p style="color: var(--theme-color); text-align:center">
			<c:out value="${infoMsg }" />
		</p>
	</c:if>
	
		
</c:if>