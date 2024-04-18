<form id="gameFilters" method="post"
	class="contact-form pb-xl-0 space-bottom" style="margin-bottom: 1em">
	<div class="row" style="text-align: center">
		<div class="main-menu d-none d-lg-inline-block">
			<ul>
				<li class="menu-item-has-children"><a href="#" style="${not empty genresSelected ? 'color:var(--theme-color)' : '' }">Genres</a>
					<ul class="sub-menu">
						<c:forEach var="genre" items="${genres}">
							<c:set var="isChecked" value="false" />
							<c:forEach var="selectedId" items="${genresSelected}">
								<c:if test="${selectedId == genre.id}">
									<c:set var="isChecked" value="true" />
								</c:if>
							</c:forEach>

							<li><input type="checkbox" id="genre-${genre.id}"
								name="genresForm" value="${genre.id}"
								${isChecked ? 'checked=true' : ''}> <label
								for="genre-${genre.id}" class="option-label"> <i
									class="fa-solid fa-bolt"></i>&nbsp;<c:out value="${genre.name}" />
							</label></li>
						</c:forEach>
					</ul></li>
				<li class="menu-item-has-children"><a href="#" style="${not empty modesSelected ? 'color:var(--theme-color)' : '' }">Modes de jeu</a>
					<ul class="sub-menu">
						<c:forEach var="mode" items="${modes}">
							<c:set var="isChecked" value="false" />
							<c:forEach var="selectedId" items="${modesSelected}">
								<c:if test="${selectedId == mode.id}">
									<c:set var="isChecked" value="true" />
								</c:if>
							</c:forEach>

							<li><input type="checkbox" id="mode-${mode.id}"
								name="modesForm" value="${mode.id}"
								${isChecked ? 'checked=true' : ''}> <label
								for="mode-${mode.id}" class="option-label"> <i
									class="fa-solid fa-rotate"></i>&nbsp;<c:out
										value="${mode.name}" />
							</label></li>
						</c:forEach>
					</ul></li>
				<li class="menu-item-has-children"><a href="#" style="${not empty languagesSelected ? 'color:var(--theme-color)' : '' }">Langues
						dispo</a>
					<ul class="sub-menu">
						<c:forEach var="language" items="${languages}">
							<c:set var="isChecked" value="false" />
							<c:forEach var="selectedId" items="${languagesSelected}">
								<c:if test="${selectedId == language.id}">
									<c:set var="isChecked" value="true" />
								</c:if>
							</c:forEach>

							<li><input type="checkbox" id="language-${language.id}"
								name="languagesForm" value="${language.id}"
								${isChecked ? 'checked=true' : ''}> <label
								for="language-${language.id}" class="option-label"> <i
									class="fa-solid fa-language"></i>&nbsp;<c:out
										value="${language.language}" />
							</label></li>
						</c:forEach>
					</ul></li>
				<li class="menu-item-has-children"><a href="#" style="${not empty platformsSelected ? 'color:var(--theme-color)' : '' }">Plateformes</a>
					<ul class="sub-menu">
						<c:forEach var="platform" items="${platforms}">
							<c:set var="isChecked" value="false" />
							<c:forEach var="selectedId" items="${platformsSelected}">
								<c:if test="${selectedId == platform.id}">
									<c:set var="isChecked" value="true" />
								</c:if>
							</c:forEach>

							<li><input type="checkbox" id="platform-${platform.id}"
								name="platformsForm" value="${platform.id}"
								${isChecked ? 'checked=true' : ''}> <label
								for="platform-${platform.id}" class="option-label"> <i
									class="fa-solid fa-gamepad"></i>&nbsp;<c:out
										value="${platform.name}" />
							</label></li>
						</c:forEach>
					</ul></li>
				<li class="menu-item-has-children"><a href="#" style="${not empty developersSelected ? 'color:var(--theme-color)' : '' }">Développeurs</a>
					<ul class="sub-menu">
						<c:forEach var="developer" items="${developers}">
							<c:set var="isChecked" value="false" />
							<c:forEach var="selectedId" items="${developersSelected}">
								<c:if test="${selectedId == developer.id}">
									<c:set var="isChecked" value="true" />
								</c:if>
							</c:forEach>

							<li><input type="checkbox" id="developer-${developer.id}"
								name="developersForm" value="${developer.id}"
								${isChecked ? 'checked=true' : ''}> <label
								for="developer-${developer.id}" class="option-label"> <i
									class="fa-solid fa-code"></i>&nbsp;<c:out
										value="${developer.name}" />
							</label></li>
						</c:forEach>
					</ul></li>
			</ul>
		</div>
	</div>
	<div class="row" style="text-align: center">
		<div class="form-btn col-12">
			<button type="submit" class="th-btn" form="gameFilters"
				value="Submit">
				Filtrer <i class="fa-solid fa-arrow-right ms-2"></i>
			</button>
			<a href="store" class="th-btn style-border"> <span
				class="btn-border"> Réinitialiser <i
					class="fa-solid fa-arrow-right ms-2"></i>
			</span>
			</a>
		</div>
	</div>
</form>



