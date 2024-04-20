<section class="overflow-hidden space-top space-extra2-bottom"
	style="padding-top: 1.5em">
	<div class="container">
		<div class="counter-card">
			<c:if test="${errorMsg == null && infoMsg == null}">
				<div class="media-body" style="text-align: center">
					<h4 class="box-number">
						<span class="counter-number"><c:out value="${totalGames }" /></span>
					</h4>
					<p class="box-text">
						jeux
						<c:choose>
							<c:when test="${genres == null}">
							trouv&#233;(s) pour la recherche : " <c:out value="${search}" /> "
						</c:when>
							<c:otherwise>
								<c:out value="en stock !" />
							</c:otherwise>
						</c:choose>
					</p>
				</div>
			</c:if>
		</div>
		<h3 style="color: white; text-align: center"></h3>
		<c:if test="${errorMsg != null}">
			<p style="color: var(--bs-warning)">
				<c:out value="${errorMsg }" />
			</p>
		</c:if>
		<c:if test="${infoMsg != null}">
			<p style="color: var(--theme-color)">
				<c:out value="${infoMsg }" />
			</p>
		</c:if>
		<c:if test="${genres != null }">
			<%@ include file="forms/filtersForm.jsp"%>
		</c:if>
		<c:if test="${genres == null }">
			<%@ include file="forms/inPageSearch.jsp"%>
		</c:if>
		<div class="row gy-4">
			<c:forEach var="game" items="${gamesList }">
				<div class="col-lg-4 col-md-6">
					<div class="game-card style2">
						<div class="game-card-img">
							<a href="game?id=${game.id }"> <img
								src="assets/img/game/2-1.png" alt="${game.title }">
							</a>
							<div class="game-logo">
								<a href="game?id=${game.id }"> <img
									src="assets/img/game/logo2-1.png" alt="game logo">
								</a>
							</div>
							<div
								style="font-weight: bold; font-style: italic; margin: -4.5em 0 1em 0; padding-left: 6em">
								<c:out value="${game.platform.name}" />
							</div>
						</div>
						<div class="game-card-details">
							<div class="media-left">
								<h3 class="box-title">
									<a href="game?id=${game.id }"><c:out value="${game.title }" /></a>
								</h3>
								<p class="game-content">
									Prix:<span class="text-theme"><c:out
											value="${game.price } ${euro }" /></span>
								</p>
							</div>
							<div class="media-body">
								<span class="game-rating"><i class="fas fa-star"></i> <c:out
										value=" ${game.usersAvgScore }" /></span> <span class="review-count">(<c:out
										value="${game.totalReviews }" /> &#233;val.)
								</span>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="pt-60 text-center">
			<div class="th-pagination ">
				<ul>

					<c:if test="${page > 1 }">
						<c:set var="previousPage" value="${page - 1}" />
						<li><a href="store?page=${previousPage }"><span
								class="btn-border"></span><i class="far fa-arrow-left"></i></a></li>
					</c:if>
					<c:if test="${gamesInPage > 11 }">
						<c:set var="nextPage" value="${page == 0 ? 2 : page + 1}" />
						<li><a href="store?page=${nextPage }"><span
								class="btn-border"></span><i class="far fa-arrow-right"></i></a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</section>