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
						<div class="checklist list-two-column">
							<ul>
								<li><i class="far fa-shield-check"></i> Thousands of levels
									across multiple worlds</li>
								<li><i class="far fa-shield-check"></i> Challenging
									gameplay to train your brain</li>
								<li><i class="far fa-shield-check"></i> Daily events to
									help you win more prizes</li>
								<li><i class="far fa-shield-check"></i> Free rewards every
									day</li>
								<li><i class="far fa-shield-check"></i> Club Tournaments
									and other exciting friends</li>
							</ul>
						</div>
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
							<h2 class="game-info-title"><a href="cart?add=${game.id }">Au panier !</a></h2>
							<div class="game-meta-list">
								<span>Mobile</span><span>Action RPG</span><span>PC</span>
							</div>
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
							<div class="btn-wrap">
								<a href="#"><img
									src="assets/img/widget/widget-apple-btn.png" alt="img"></a> <a
									href="#"><img
									src="assets/img/widget/widget-playstore-btn.png" alt="img"></a>
							</div>
						</div>
					</div>
				</aside>
			</div>
			<div class="col-xxl-8 col-lg-7">
				<div class="team-about-card">
					<div class="title-area mb-0">
						<span class="sub-title">Développement</span>
						<h2 class="sec-title"><c:out value="${game.developerId }"/></h2>
					</div>
					<p class="about-card_text mt-30 mb-25">Successful esports teams
						exhibit strong communication, strategic coordination, and
						individual player skills. Team chemistry, effective coaching, and
						adaptability to changing meats.</p>
					<div class="team-info-list">
						<ul>
							<li>Fondé en <span>2023</span></li>
							<li>Pays: <span>Turkisdétan</span></li>
						</ul>
					</div>
					<div class="team-social mt-25">
						<h5 class="fw-semibold text-white">
							Suivre <span class="text-theme"><c:out value="${game.developerId }"/></span>
						</h5>
						<div class="th-social style-mask">
							<a class="facebook" href="https://www.facebook.com/"><i
								class="fab fa-facebook-f"></i></a> <a class="twitter"
								href="https://www.twitter.com/"><i class="fab fa-twitter"></i></a>
							<a class="instagram" href="https://www.instagram.com/"> <i
								class="fab fa-instagram"></i>
							</a> <a class="linkedin" href="https://www.linkedin.com/"><i
								class="fab fa-linkedin"></i></a> <a class="google-play"
								href="https://www.google.com/"> <img
								src="assets/img/icon/google-playstore-icon.svg" alt="icon">
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>