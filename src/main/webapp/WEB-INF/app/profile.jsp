<c:if test="${sessionScope.user == null }">
	<div class="container" style="margin-top: 1em">
		<div class="title-area text-center" style="margin-bottom: 0">
			<h2 class="sec-title text-white" style="margin-bottom: 0">Quel
				est votre profil ?</h2>
			<p style="margin-bottom: 0">Pour le savoir, connectez-vous.</p>
		</div>
	</div>
	<%@ include file="forms/loginForm.jsp"%>
</c:if>
<c:if test="${sessionScope.user != null }">
	<section class="space">
		<div class="container">
			<div class="row gy-80">
				<div class="col-xl-6">
					<div class="widget  ">
						<div class="widget-game-info">
							<div class="player-logo">
								<img src="assets/img/tournament/1-1.png" alt="img">
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-6">
					<div class="team-about-card">
						<div class="title-area mb-0">
							<span class="sub-title"># Information</span>
							<h4 class="sec-title">
								<c:if
									test="${sessionScope.user.firstname != null && sessionScope.user.lastname != null }">
									<c:out
										value="${sessionScope.user.firstname } ${sessionScope.user.lastname } ( ${sessionScope.user.nickname } )" />
								</c:if>
								<c:if
									test="${sessionScope.user.firstname == null || sessionScope.user.lastname == null }">
									<c:out value="${sessionScope.user.nickname }" />
								</c:if>

							</h4>
							<br>
						</div>
						<div class="team-info-list">
							<ul>
								<li>Adresse de livraison: <span> <c:if
											test="${sessionScope.user.shippingAddress == null }">
											Pas d'adresse renseignÃ©e
										</c:if> <c:if test="${sessionScope.user.shippingAddress != null }">
											<c:out value="${sessionScope.user.shippingAddress }" />
										</c:if>

								</span></li>
							</ul>
						</div>

					</div>
					<br>
					<div style="text-align: center">
						<a href="profil?edit" class="th-btn style-border"> <span
							class="btn-border"> Editer mon profil <i
								class="fa-solid fa-edit"></i>
						</span>
						</a>
					</div>
				</div>
			</div>

		</div>

		<div class="container">
			<div class="title-area text-center custom-anim-top wow  animated"
				data-wow-duration="1.5s" data-wow-delay="0.2s"
				style="visibility: visible; animation-duration: 1.5s; animation-delay: 0.2s; animation-name: custom-anim-top;">
				<span class="sub-title style2"># Historique de commandes</span>
			</div>
			<c:if test="${errorMsg != null}">
				<p style="color: var(--bs-warning); text-align: center">
					<c:out value="${errorMsg }" />
				</p>
			</c:if>
			<c:if test="${ordersList.size() == 0}">
				<p style="color: var(--theme-color);text-align:center">
				Aucune commande trouv&#233;e !
			</p>
			
			</c:if>
			<c:if test="${errorMsg == null && ordersList.size() > 0}">
				<div class="table-responsive">
					<table class="table tournament-table">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Date</th>
								<th scope="col">Jeux</th>
								<th scope="col">Nb.jeux</th>
								<th scope="col">Montant</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="order" items="${ordersList }" varStatus="i">
								<tr>
									<th scope="row"><c:out value="${order.id }" /></th>
									<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${order.date }" /></td>
									<td><c:out value="${order.name }" /></td>
									<td><c:out value="${order.totalQuantity }" /></td>
									<td><c:out value="${order.amount } " />&#8364;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>

	</section>
</c:if>