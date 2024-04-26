<c:if test="${sessionScope.user == null }">
	<div class="container" style="margin-top: 1em">
		<div class="title-area text-center" style="margin-bottom: 0">
			<h2 class="sec-title text-white" style="margin-bottom: 0">Quel
				est votre profil ?</h2>
			<p style="margin-bottom: 0">Pour le savoir, connectez-vous.</p>
		</div>
	</div>
	<%@ include file="forms/loginForm.jsp"%>
</c:if>
<c:if test="${sessionScope.user != null }">
	<section class="space">
		<div class="title-area mb-0" style="text-align: center">
			<span class="sub-title"># Modifier</span>
			<h4 class="sec-title">
				<c:if
					test="${sessionScope.user.firstname != null && sessionScope.user.lastname != null }">
					<c:out
						value="${sessionScope.user.firstname } ${sessionScope.user.lastname } ( ${sessionScope.user.nickname } )" />
				</c:if>
				<c:if
					test="${sessionScope.user.firstname == null || sessionScope.user.lastname == null }">
					<c:out value="${sessionScope.user.nickname }" />
				</c:if>

			</h4>
			<br>
		</div>
		<div class="container">
		<!--
			<div class="row gy-80">
				<div class="col-xl-12">
					<div class="widget  ">
						<div class="widget-game-info">
							<div class="player-logo">
								<img src="assets/img/tournament/1-1.png" alt="img">
							</div>
						</div>
					</div>
				</div>
			</div>
			  -->
			<div class="row">
				<div class="col-xl-12">
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
					<!--
					<form action="editProfile" method="POST"
						enctype="multipart/form-data"
						class="contact-form pb-xl-0 space-bottom" id="avatar-form">
						<div class="row">
							<div class="widget  ">
								<div class="widget-game-info">
									<h5 style="color: white;">Changer d'avatar</h5>
									<input name="avatar" type="file" class="" id="avatar"
										placeholder="Avatar" accept="image/png, image/jpeg">
								</div>
							</div>
						</div>
						<div class="form-group col-md-6" style="text-align: center;">
							<button type="submit" form="avatar-form" class="th-btn">
								Changer d'avatar <i class="fa fa-edit" aria-hidden="true"></i>
							</button>
						</div>
					</form>
					-->
					<form action="editProfile" method="POST"
						class="contact-form pb-xl-0 space-bottom" id="edit-form">

						<div class="row">
							<div class="form-group style-border2 col-md-6">

								<input type="text" class="form-control" id="nickname" disabled
									value="${sessionScope.user.nickname }" /><i
									class="fal fa-user"></i>

							</div>
							<div class="form-group style-border2 col-md-6">
								<input type="email" class="form-control" name="email" id="email"
									placeholder="${sessionScope.user.email }" /><i
									class="fal fa-envelope"></i>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label for="firstname" style="color: white">Prénom</label> 
								<div class="form-group style-border2">
									<input
										type="text" class="form-control" name="firstname"
										id="firstname" placeholder="${sessionScope.user.firstname }" /><i
										class="fa-solid fa-align-left"></i>
								</div>
							</div>
							<div class="col-md-6">
								<label for="lastname" style="color: white">Nom</label> 
								<div class="form-group style-border2 ">
									<input
										type="text" class="form-control" name="lastname" id="lastname"
										placeholder="${sessionScope.user.lastname }" /><i
										class="fa-solid fa-align-left"></i>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group style-border2 col-md-6">
								<input type="password" class="form-control" name="new-password"
									id="new-password" placeholder="Nouveau mot de passe" /> <i
									class="fal fa-key"></i>

							</div>
							<div class="form-group style-border2 col-md-6">
								<input type="password" class="form-control"
									name="confirm-new-password" id="confirm-new-password"
									placeholder="Confirmez le nouveau mot de passe" /> <i
									class="fal fa-key"></i>
							</div>
						</div>
						<div class="row">
							<div class="contact-feature style-border2 col-md-12"
								style="border-color: var(--theme-color)">
								<div class="contact-feature-icon icon-masking">
									<span class="mask-icon bg-mask"
										style="mask-image: url(&quot;assets/img/icon/contact-map-icon1.svg&quot;);"></span>
									<img src="assets/img/icon/contact-map-icon1.svg" alt="img">
								</div>
								<div class="media-body">
									<h4 class="box-title">Adresse:</h4>
									<textarea class="contact-feature_link" name="ShippingAddress"
										placeholder="${sessionScope.user.shippingAddress == null ? 'Pas d\'adresse renseignée' : sessionScope.user.shippingAddress }"></textarea>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="form-group col-md-6">
								<input type="password" class="form-control"
									name="currentPassword" id="password"
									placeholder="Mot de passe actuel" required
									style="border-color: var(--yellow-color)" /> <i
									class="fal fa-key"></i>


							</div>
							<div class="form-group col-md-6" style="text-align: center;">
								<button type="submit" form="edit-form" class="th-btn">
									Modifier !<i class="fa fa-edit" aria-hidden="true"></i>
								</button>
							</div>

						</div>

						<p class="form-messages mb-0 mt-3"></p>
					</form>
				</div>

			</div>
		</div>

	</section>
</c:if>