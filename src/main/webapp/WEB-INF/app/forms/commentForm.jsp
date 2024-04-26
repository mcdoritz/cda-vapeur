<form action="comment?id=${game.id }" method="POST">
	<div class="row">

		<div class="col-md-6 form-group style-border">
			<input type="text" class="form-control" disabled
				value="${sessionScope.user.nickname }"> <i
				class="far fa-user"></i>
		</div>
		<div class="slidecontainer">
			<input type="range" min="0" max="5" value="2" class="slider"
				id="score" name="score" oninput="rangeValue.innerText = this.value">
		</div>
		<div class="counter-card">

			<div class="media-body" style="text-align: center">
				<h4 class="box-number">
					<span id="rangeValue" class="counter-number">2</span>
				</h4>
				<p class="box-text">/5</p>
			</div>

		</div>
	</div>
	<div class="row">
		<div class="col-12 form-group style-border">
			<textarea placeholder="Ecrivez votre évaluation" class="form-control" name="content"></textarea>
			<i class="far fa-pencil"></i>
		</div>
		<div class="col-12 form-group mb-0">
			<button class="th-btn" type="submit">
				NOTER <i class="far fa-arrow-right ms-2"></i>
			</button>
		</div>

	</div>
</form>