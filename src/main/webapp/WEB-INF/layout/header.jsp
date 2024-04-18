<!--==============================
	Header Area
==============================-->
	<style>
	  .sub-menu {
	    margin-top:-1.5em!important;
	  }
	</style>
    <header class="th-header header-layout1">
        <div class="sticky-wrapper">
            <!-- Main Menu Area -->
            <div class="menu-area">
                <div class="container">
                    <div class="row align-items-center justify-content-between">
                        <div class="col-auto">
                            <div class="header-logo">
                                <a href="landing">
                                    <span data-mask-src="assets/img/logo.svg" class="logo-mask"></span>
                                    <img src="assets/img/logo.svg" alt="Bame">
                                </a>
                            </div>
                        </div>
                        <div class="col-auto">
                            <nav class="main-menu d-none d-lg-inline-block">
                                <ul>
                                    <li>
                                        <a href="landing">Accueil</a>
                                    </li>
                                    <li class="menu-item-has-children">
                                        <a href="store">Jeux</a>
                                        <ul class="sub-menu">
                                            <li><a href="store">Parcourir</a></li>
                                            <li><a href="search">Rechercher</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="library">Ma bibliothèque</a></li>
                                    <c:if test="${sessionScope.user != null }">
                                    	<li><a href="profile">Profil</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.user == null }">
                                    	<li><a href="login">Se connecter</a></li>
                                    	<li><a href="signup" style="color:var(--theme-color)" class="panier">S'inscrire</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.user != null }">
                                    	<li><a href="logout">Se déconnecter</a></li>
                                    </c:if>
                                </ul>
                            </nav>
                            <div class="header-button d-flex d-lg-none">
                                <button type="button" class="th-menu-toggle"><span class="btn-border"></span><i class="far fa-bars"></i></button>
                            </div>
                        </div>
                        <div class="col-auto d-none d-xl-block">
                            <div class="header-button">
                                <button type="button" class="simple-icon searchBoxToggler"><i class="far fa-search"></i></button>
                                <a href="cart"><i class="fa-solid fa-cart-shopping"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="logo-bg"></div>
            </div>
        </div>
    </header>