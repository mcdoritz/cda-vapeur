<section class="overflow-hidden space-top space-extra2-bottom">
	<div class="container">
		<div class="row gy-4">
			<c:forEach var="game" items="${gamesList }">
				<div class="col-lg-4 col-md-6">
					<div class="game-card style2">
						<div class="game-card-img">
							<a href="tournament-details.html"> <img
								src="assets/img/game/2-1.png" alt="${game.title }">
							</a>
							<div class="game-logo">
								<img src="assets/img/game/logo2-1.png" alt="game logo">
							</div>
						</div>
						<div class="game-card-details">
							<div class="media-left">
								<h3 class="box-title">
									<a href="game?id=${game.id }"><c:out value="${game.title }"/></a>
								</h3>
								<p class="game-content">
									Prix:<span class="text-theme"><c:out value="${game.price } ${euro }"/></span>
								</p>
							</div>
							<div class="media-body">
								<span class="game-rating"><i class="fas fa-star"></i><c:out value=" ${game.usersAvgScore }"/></span>
								<span class="review-count">(<c:out value="${game.totalReviews }"/> éval.)</span>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="pt-60 text-center">
			<div class="th-pagination ">
				<ul>
					<li><a href="blog.html"><span class="btn-border"></span> 1</a></li>
					<li><a href="blog.html"><span class="btn-border"></span> 2</a></li>
					<li><a href="blog.html"><span class="btn-border"></span> 3</a></li>
					<li><a href="blog.html"><span class="btn-border"></span><i
							class="far fa-arrow-right"></i></a></li>
				</ul>
			</div>
		</div>
	</div>
</section>