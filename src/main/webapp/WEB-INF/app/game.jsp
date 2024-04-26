<section class="overflow-hidden space-top space-extra2-bottom">
	<div class="container">
		<div class="row gx-40">
			<div class="col-xxl-8 col-lg-7">
				<c:if test="${errorMsg != null}">
					<p style="color: var(--bs-warning); text-align: center">
						<c:out value="${errorMsg }" />
					</p>
				</c:if>
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
				<div class="widget widget_tag_cloud  ">
					<h3 class="widget_title">Langues disponibles</h3>
					<table class="table tournament-table" style="min-width: unset">
						<thead>
							<tr>
								<th scope="col"></th>
								<th scope="col" style="text-align: center">Interface</th>
								<th scope="col" style="text-align: center">Audio</th>
								<th scope="col" style="text-align: center; padding-right: 0">Sous-titres</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="langue" items="${game.languages }">
								<tr>
									<td><c:out value="${langue.language }" /></td>
									<td style="text-align: center"><c:if
											test="${langue.interfaceSupport == true }">
											<i class="fa-regular fa-circle-check"></i>
										</c:if></td>
									<td style="text-align: center"><c:if
											test="${langue.fullAudioSupport == true }">
											<i class="fa-regular fa-circle-check"></i>
										</c:if></td>
									<td style="text-align: center"><c:if
											test="${langue.subtitles == true }">
											<i class="fa-regular fa-circle-check"></i>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="widget widget_tag_cloud  ">
					<h3 class="widget_title">
						Développeur :
						<c:out value="${game.developer.name }" />
					</h3>
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
			<div class="col-xxl-4 col-lg-5">
				<aside class="sidebar-area">
					<div class="widget  ">
						<div class="widget-game-info">
							<div class="player-logo">
								<img src="assets/img/tournament/1-1.png" alt="img">
							</div>
							<h2 class="sub-title">
								<a href="cart?add=${game.id }" class="panier">Au panier !<br>
									<i class="fa-solid fa-cart-shopping"></i></a>
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
										<a href="store?platforms=${game.platform.id }" class="panier"><c:out
												value="${game.platform.name }" /></a>
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
		</div>
		<div class="widget widget_tag_cloud  ">
			<div class="th-comment-form ">
				<div class="form-title">
					<h3 class="blog-inner-title mb-2">
						<i class="fa-solid fa-reply"></i> Noter ce jeu !
					</h3>
					<p class="form-text">Ici, c'est la communauté de la Vapeur.</p>
				</div>
				<c:choose>
					<c:when test="${sessionScope.user != null}">
						<c:choose>
						    <c:when test="${gameInUserLibrary}">
						    	<c:choose>
								    <c:when test="${userHasCommented}">
								        <%@ include file="forms/commentForm.jsp"%>
								    </c:when>
								    <c:otherwise>
								        <c:out value="Vous avez déjà commenté ce jeu !" />
								    </c:otherwise>
								</c:choose>
						        
						    </c:when>
						    <c:otherwise>
						        <c:out value="Vous devez avoir commandé le jeu pour commenter et noter !" />
						    </c:otherwise>
						</c:choose>
						
					</c:when>
					<c:otherwise>
						<c:out value="Vous devez être connecté et avoir commandé le jeu pour commenter !" />
					</c:otherwise>
				</c:choose>

			</div>
			<div class="th-comments-wrap ">
				<h3 class="blog-inner-title">
					<i class="far fa-comments"></i>
					<c:out value="${game.comments.size() }" />
					commentaires de nos vapeurGameurs (nombre de notes :
					<c:out value="${totalNotes }" />
					)
				</h3>
				<c:if test="${game.comments.size() == 0 }">
					Ce jeu n'a pas été commenté !
				
				</c:if>
				<c:if test="${game.comments.size() > 0 }">
					<ul class="comment-list">
						<c:forEach var="comment" items="${game.comments }">
							<c:if test="${ not empty comment.content }">
								<li class="th-comment-item">
									<div class="th-post-comment">
										<div class="comment-avater">
											<img src="assets/img/blog/comment-author-1.jpg"
												alt="Comment Author">
										</div>
										<div class="comment-content">
											<h3 class="name">
												<span style="color: var(--theme-color)"><c:out
														value="${comment.userNickname }" /></span> a donné <span
													class="color-<fmt:formatNumber value="${comment.score}" pattern="###" />"><fmt:formatNumber
														value="${comment.score }" pattern="###" />/5</span> et a dit :
											</h3>
											<span class="commented-on"> <fmt:formatDate
													value="${comment.uploaded }"
													pattern="EEEE dd MMMM yyyy HH:mm" />
											</span>
											<p class="text">
												<c:out value="${comment.content }" />
											</p>
										</div>
									</div>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<c:if test="${sessionScope.user != null }">
				<div class="game-title-wrap">
					<h2 class="page-title text-white mb-0">Ca va vous plaire
						également :</h2>
					<p>Suggestions basées sur vos achats précédents</p>
				</div>
				<%@ include file="components/carroutext.jsp"%>

			</c:if>
		</div>
	</div>

</section>