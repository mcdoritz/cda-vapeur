<div class="row gy-4">
	<c:forEach var="game" items="${gamesList }">
		<div class="col-lg-4 col-md-6">
			<div class="game-card style2">
				<div class="game-card-img">
					<a href="game?id=${game.id }"> <img
						src="${url }/VapeurBackOffice/assets/images/games/${game.id }/logo/logo.jpg" alt="${game.title }">
					</a>
					<div class="game-logo" style="overflow:hidden">
						<a href="store?platforms=${game.platform.id }"><img
							src="assets/img/platform/logo${game.platform.id }.png" alt="platform logo" style="height:100%; width:100%;">
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