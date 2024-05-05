<!--==============================
	Header Area
==============================-->
<style>
.sub-menu {
	margin-top: -1.5em !important;
}
</style>

<!--[if lte IE 9]>
    	<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
  	<![endif]-->


<!--********************************
   		Code Start From Here 
	******************************** -->

<!--==============================
     Preloader
  ==============================-->
<div class="preloader ">
	<div class="preloader-inner">
		<span class="loader"></span>
	</div>
</div>
<%@ include file="../app/forms/inHeaderSearch.jsp"%>

<!--==============================-->
<!--==============================
    Mobile Menu
  ============================== -->
<div class="th-menu-wrapper">
	<div class="th-menu-area text-center">
		<button class="th-menu-toggle">
			<i class="fal fa-times"></i>
		</button>
		<div class="mobile-logo">
			<a href="landing"><img src="assets/img/logo_vapeur.png"
				alt="Vapeur" style="max-width: 150px;"></a>
		</div>
		<div class="th-mobile-menu">
			<ul>
				<li><a href="landing">Accueil</a></li>
				<li class="menu-item-has-children"><a href="store">Jeux</a>
					<ul class="sub-menu">
						<li><a href="store">Parcourir</a></li>
						<li><a href="search">Rechercher</a></li>
					</ul></li>

				<li><a href="library">Ma bibliothèque</a></li>
				<c:if test="${sessionScope.user != null }">
					<li><a href="profile">Profil</a></li>
				</c:if>
				<c:if test="${sessionScope.user == null }">
					<li><a href="login">Se connecter</a></li>
					<li><a href="signup" style="color: var(--theme-color)"
						class="panier">S'inscrire</a></li>
				</c:if>
				<c:if test="${sessionScope.user != null }">
					<li><a href="logout">Se déconnecter</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
<div class="color-scheme-wrap active">
	<button class="switchIcon">
		<i class="fa-solid fa-palette"></i>
	</button>
	<h4 class="color-scheme-wrap-title">
		<i class="far fa-palette"></i> Color Switcher
	</h4>
	<div class="color-switch-btns">
		<button data-color="#6240CF">
			<i class="fa-solid fa-droplet"></i>
		</button>
		<button data-color="#FFBE18">
			<i class="fa-solid fa-droplet"></i>
		</button>
		<button data-color="#24FFF2">
			<i class="fa-solid fa-droplet"></i>
		</button>
		<button data-color="#45F882">
			<i class="fa-solid fa-droplet"></i>
		</button>
		<button data-color="#FF7E02">
			<i class="fa-solid fa-droplet"></i>
		</button>
	</div>
</div>

<header class="th-header header-layout1">
	<div class="sticky-wrapper">
		<!-- Main Menu Area -->
		<div class="menu-area">
			<div class="container">
				<div class="row align-items-center justify-content-between">
					<div class="col-auto">
						<div class="header-logo">
							<a href="landing"><img src="assets/img/logo_vapeur.png"
								alt="Vapeur" style="max-width: 150px;"> </a>
						</div>
					</div>
					<div class="col-auto">
						<nav class="main-menu d-none d-lg-inline-block">
							<ul>
								<li><a href="landing">Accueil</a></li>
								<li class="menu-item-has-children"><a href="store">Jeux</a>
									<ul class="sub-menu">
										<li><a href="store">Parcourir</a></li>
										<li><a href="search">Rechercher</a></li>
									</ul></li>

								<li><a href="library">Ma bibliothèque</a></li>
								<c:if test="${sessionScope.user != null }">
									<li><a href="profile">Profil</a></li>
								</c:if>
								<c:if test="${sessionScope.user == null }">
									<li><a href="login">Se connecter</a></li>
									<li><a href="signup" style="color: var(--theme-color)"
										class="panier">S'inscrire</a></li>
								</c:if>
								<c:if test="${sessionScope.user != null }">
									<li><a href="logout">Se déconnecter</a></li>
								</c:if>
							</ul>
						</nav>
						<div class="header-button d-flex d-lg-none">
							<button type="button" class="th-menu-toggle">
								<span class="btn-border"></span><i class="far fa-bars"></i>
							</button>
						</div>
					</div>
					<div class="col-auto d-none d-xl-block">
						<div class="header-button">
							<button type="button" class="simple-icon searchBoxToggler">
								<i class="far fa-search"></i>
							</button>
							<a href="cart"><i class="fa-solid fa-cart-shopping"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="logo-bg"></div>
		</div>
	</div>
</header>