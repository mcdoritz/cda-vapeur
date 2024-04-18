<section class="overflow-hidden space-top space-extra2-bottom">
	<div class="container">
		<div class="row gx-40">
			<div class="col-xxl-8 col-lg-7">
				<div class="page-single game-details-wrap">
					<div class="page-img mb-50">
						<img class="w-100" src="assets/img/game/game-s-1-1.png" alt="img">
					</div>
					<div class="page-content">
						<div class="game-title-wrap">
							<h2 class="page-title text-white mb-0">
								<c:out value="${game.title }" />
							</h2>
							<div class="game-meta">
								Prix: <span class="text-theme"><c:out
										value="${game.price } ${euro }" /></span>
							</div>
						</div>
						<p class="mb-30">
							<c:out value="${game.description }" />
						</p>
					</div>
				</div>
			</div>
			<div class="col-xxl-4 col-lg-5">
				<aside class="sidebar-area">
					<div class="widget  ">
						<div class="widget-game-info">
							<div class="player-logo">
								<img src="assets/img/tournament/1-1.png" alt="img">
							</div>
							<h2 class="sub-title">
								<a href="cart?add=${game.id }" class="panier">Au panier !<br> <i
									class="fa-solid fa-cart-shopping"></i></a>
							</h2>
							<div class="game-rating-info">
								<div class="rating-wrap">
									<span class="game-rating"><i class="fas fa-star"></i> <c:out
											value=" ${game.usersAvgScore }" /></span> <span
										class="review-count">(<c:out
											value="${game.totalReviews }" /> éval.)
									</span>
								</div>
								<div class="download-wrap">
									<c:if test="${game.stock > 0 }">
										<h5 class="download-wrap-title">
											<c:if test="${game.stock > 0 }">
												<c:out value=" ${game.stock > 100 ? '100+' : game.stock }" />
											</c:if>
										</h5>
										<span class="download-wrap-text">en stock</span>
									</c:if>
									<c:if test="${game.stock <= 0 }">
										<h5 class="download-wrap-title">RUPTURE</h5>
										<span class="download-wrap-text">Y a pu</span>
									</c:if>
								</div>
							</div>
							<div></div>
							<div class="counter-card">
								<div class="media-body">
									<h2 class="box-text" style="font-size: 2em">
										<c:out value="${game.platform.name }" />
									</h2>
								</div>
							</div>
						</div>
					</div>
					<div class="widget widget_tag_cloud  ">
						<h3 class="widget_title">Tags</h3>
						<div class="tagcloud">
							<c:forEach var="tag" items="${game.tags }">
								<a href="store?filter="><span><c:out value="${tag }" /></span></a>
							</c:forEach>
						</div>
					</div>
					<div class="widget widget_tag_cloud  ">
						<h3 class="widget_title">Genres</h3>
						<div class="tagcloud">
							<c:forEach var="genre" items="${game.genres }">
								<a href="store?genres=${genre.id }"><span><c:out
											value="${genre.name }" /></span></a>
							</c:forEach>
						</div>
					</div>
					<div class="widget widget_tag_cloud  ">
						<h3 class="widget_title">Modes de jeu</h3>
						<div class="tagcloud">
							<c:forEach var="mode" items="${game.modes }">
								<a href="store?modes=${mode.id }"><span><c:out
											value="${mode.name }" /></span></a>
							</c:forEach>
						</div>
					</div>
					<div class="widget  ">
							<div class="widget-game-info">
								<h2 class="sub-title">
									<a href="cart?add=${game.id }" class="panier">Au panier !<br>
									<i class="fa-solid fa-cart-shopping"></i></a>
								</h2>
							</div>
						</div>
				</aside>
			</div>
			<div class="col-xxl-8 col-lg-7">
				<div class="team-about-card">
					<div class="title-area mb-0">
						<span class="sub-title">Développement</span>
						<h2 class="sec-title">
							<c:out value="${game.developer.name }" />
						</h2>
					</div>
					<p class="about-card_text mt-30 mb-25"></p>
					<div class="team-info-list">
						<ul>
							<li>Fondé en <span><fmt:formatDate pattern="yyyy"
										value="${game.developer.creationDate}" /></span></li>
							<li>Pays: <span><c:out
										value="${game.developer.country }" /></span></li>
						</ul>
					</div>
					<div class="team-social mt-25">
						<h5 class="fw-semibold text-white">
							Suivre <span class="text-theme"><c:out
									value="${game.developer.name }" /></span>
						</h5>
						<div class="th-social style-mask">
							<c:if test="${game.developer.urlInstagram != null }">
								<a class="instagram" href="${game.developer.urlInstagram }">
									<i class="fab fa-instagram"></i>
								</a>
							</c:if>
							<c:if test="${game.developer.urlX != null }">
								<a class="twitter" href="${game.developer.urlX }"> <i
									class="fab fa-twitter"></i>
								</a>
							</c:if>
							<c:if test="${game.developer.urlFacebook != null }">
								<a class="facebook" href="${game.developer.urlFacebook }"> <i
									class="fab fa-facebook-f"></i>
								</a>
							</c:if>
							<c:if test="${game.developer.urlWebsite != null }">
								<a class="google-play" href="${game.developer.urlWebsite }">
									<img src="assets/img/icon/google-playstore-icon.svg" alt="icon">
								</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>