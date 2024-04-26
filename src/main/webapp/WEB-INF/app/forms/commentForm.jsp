<div
	class="contact-sec-1 space bg-repeat overflow-hidden background-image"
	style="background-image: url(&quot;assets/img/bg/jiji-bg2.png&quot;);">
	<div class="container">
		<div class="row align-items-center justify-content-between">
			<div class="col-xl-6 pe-xxl-5">
				<div class="title-area">
					<span class="sub-title style2"># Editer mon commentaire</span>
					<h2 class="sec-title text-white">
						Commentaire <span class="text-theme">!</span>
					</h2>
				</div>

				<form action="comment?game_id=${game.id != 0 ? game.id : comment.gameId }" method="POST">
					<div class="row">

						<div class="col-md-6 form-group style-border">
							<input type="text" class="form-control" disabled
								value="${sessionScope.user.nickname }"> <i
								class="far fa-user"></i>
						</div>
						<div class="slidecontainer">
							<input type="range" min="0" max="5" value="${comment.score != null ? comment.score : 2 }" class="slider"
								id="score" name="score"
								oninput="rangeValue.innerText = this.value">
						</div>
						<div class="counter-card">

							<div class="media-body" style="text-align: center">
								<p class="box-text"><span id="rangeValue"><fmt:formatNumber value="${comment.score != null ? comment.score : 2 }" pattern="###" /></span>/5</p>
							</div>

						</div>
					</div>
					<div class="row">
						<div class="col-12 form-group style-border">
							<textarea placeholder="Ecrivez votre évaluation"
								class="form-control" name="content"><c:out value="${comment.content != null ? comment.content : '' }"/></textarea>
							<i class="far fa-pencil"></i>
						</div>
						<div class="col-12 form-group mb-0">
							<button class="th-btn" type="submit">
								MODIFIER <i class="far fa-arrow-right ms-2"></i>
							</button>
						</div>

					</div>
				</form>
			</div>
			<div class="col-xl-4 ps-xxl-5">
			<h2 class="sec-title text-white"><c:out value="${game.title }"/></h2>
				<img
					src="https://i.pinimg.com/originals/88/44/eb/8844eb46c5719be6fa2944c2b9a84af5.png"
					alt="mario" style="max-height: 280px">
			</div>
		</div>
	</div>
</div>