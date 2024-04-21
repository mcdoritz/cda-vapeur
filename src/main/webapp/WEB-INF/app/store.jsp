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
		<%@ include file="components/gamesList.jsp"%>
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