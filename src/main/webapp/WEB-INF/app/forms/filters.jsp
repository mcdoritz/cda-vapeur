<form id="gameFilters" method="post"
	class="contact-form pb-xl-0 space-bottom"
	style="margin-bottom:1em">
	<div class="row" style="text-align:center">
		<div class="main-menu d-none d-lg-inline-block">
			<ul>
				<li class="menu-item-has-children"><a href="#">Genres</a>
					<ul class="sub-menu">
						<c:forEach var="genre" items="${genres }" varStatus="i">
							<li><input type="checkbox" id="genre-${genre.id }" name="genresForm"
							value="${genre.id }"><label for="genre-${genre.id }" class="option-label">
								<i class="fa-solid fa-bolt"></i>&nbsp<c:out value="${genre.name }"/></label>
							</li>
						
						</c:forEach>
					</ul>
				</li>
				<li class="menu-item-has-children"><a href="#">Modes de
						jeu</a>
					<ul class="sub-menu">
						<c:forEach var="mode" items="${modes }" varStatus="i">
							<li><input type="checkbox" id="mode-${mode.id }" name="modesForm"
							value="${mode.id }"><label for="mode-${mode.id }" class="option-label">
								<i class="fa-solid fa-rotate"></i>&nbsp<c:out value="${mode.name }"/></label>
							</li>
						
						</c:forEach>
					</ul>
				</li>
				<li class="menu-item-has-children"><a href="#">Langues
						dispo</a>
					<ul class="sub-menu">
						<c:forEach var="language" items="${languages }" varStatus="i">
							<li><input type="checkbox" id="language-${language.id }" name="languagesForm"
							value="${language.id }"><label for="language-${language.id }" class="option-label">
								<i class="fa-solid fa-language"></i>&nbsp<c:out value="${language.language }"/></label>
							</li>
						
						</c:forEach>
					</ul>
				</li>
				<li class="menu-item-has-children"><a href="#">Plateformes</a>
					<ul class="sub-menu">
						<c:forEach var="platform" items="${platforms }" varStatus="i">
							<li><input type="checkbox" id="platform-${platform.id }" name="platformsForm"
							value="${platform.id }"><label for="platform-${platform.id }" class="option-label">
								<i class="fa-solid fa-gamepad"></i>&nbsp<c:out value="${platform.name }"/></label>
							</li>
						
						</c:forEach>
					</ul>
				</li>
				<li class="menu-item-has-children"><a href="#">Développeurs</a>
					<ul class="sub-menu">
						<c:forEach var="developer" items="${developers }" varStatus="i">
							<li><input type="checkbox" id="developer-${developer.id }" name="developersForm"
							value="${developer.id }"><label for="developer-${developer.id }" class="option-label">
								<i class="fa-solid fa-code"></i>&nbsp<c:out value="${developer.name }"/></label>
							</li>
						
						</c:forEach>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	<div class="row" style="text-align:center">
		<div class="form-btn col-12">
			<button type="submit" class="th-btn" form="gameFilters" value="Submit">
				Filtrer <i class="fa-solid fa-arrow-right ms-2"></i>
			</button>
			<a href="store?reset=1">Réinitialiser</a>
		</div>
	</div>
</form>



