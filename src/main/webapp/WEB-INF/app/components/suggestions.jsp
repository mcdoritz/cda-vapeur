<c:forEach items="${suggestions }" var="suggestion">
	<div class="marquee-item swiper-slide" style="margin-right: 50px;"
		role="group" aria-label="4 / 8" data-swiper-slide-index="3">
		<div class="marquee_icon">
			<img src="assets/img/normal/star.png" alt="Icon">
		</div>

		<h3 class="marquee-title">
			<a href="game?id=${suggestion.id }"><c:out
					value="${suggestion.title }" /></a>
		</h3>
		<div class="marquee_icon">
			<img src="assets/img/normal/star.png" alt="Icon">
		</div>
	</div>
	<br>
</c:forEach>