<div class="marquee-area-1 bg-repeat overflow-hidden background-image" style="background-image: url(&quot;assets/img/bg/jiji-bg.png&quot;);">
        <div class="container-fluid">
            <div class="swiper th-slider swiper-initialized swiper-horizontal swiper-backface-hidden" id="marqueeSlider1" data-slider-options="{&quot;breakpoints&quot;:{&quot;0&quot;:{&quot;slidesPerView&quot;:&quot;auto&quot;}},&quot;autoplay&quot;:{&quot;delay&quot;:1500,&quot;disableOnInteraction&quot;:false},&quot;spaceBetween&quot;:50}">
                <div class="swiper-wrapper" id="swiper-wrapper-0b382ce4ace298ac" aria-live="off" style="transition-duration: 1000ms; transform: translate3d(-1157.72px, 0px, 0px);">
                    
					<c:forEach begin="1" end="2" var="i">
	                	<div class="marquee-item swiper-slide" style="margin-right: 50px;" role="group" aria-label="4 / 8" data-swiper-slide-index="3">
	                        <div class="marquee_icon">
	                            <img src="assets/img/normal/star.png" alt="Icon">
	                        </div>
	                        
	                        <h3 class="marquee-title"><a href="game?id=${list[0].id }"><c:out value="${list[0].title }"/></a></h3>
	                    </div>
	                    <div class="marquee-item swiper-slide" style="margin-right: 50px;" role="group" aria-label="4 / 8" data-swiper-slide-index="3">
	                        <div class="marquee_icon">
	                            <img src="assets/img/normal/star.png" alt="Icon">
	                        </div>
	                        
	                        <h3 class="marquee-title"><a href="store?genres=${list[1].id }"><c:out value="${list[1].name }"/></a></h3>
	                    </div>
	                    <div class="marquee-item swiper-slide" style="margin-right: 50px;" role="group" aria-label="4 / 8" data-swiper-slide-index="3">
	                        <div class="marquee_icon">
	                            <img src="assets/img/normal/star.png" alt="Icon">
	                        </div>
	                        
	                        <h3 class="marquee-title"><a href="store?developers=${list[2].id }"><c:out value="${list[2].name }"/></a></h3>
	                    </div>
	                    <div class="marquee-item swiper-slide" style="margin-right: 50px;" role="group" aria-label="4 / 8" data-swiper-slide-index="3">
	                        <div class="marquee_icon">
	                            <img src="assets/img/normal/star.png" alt="Icon">
	                        </div>
	                        
	                        <h3 class="marquee-title"><a href="store?modes=${list[3].id }"><c:out value="${list[3].name }"/></a></h3>
	                    </div>
                    </c:forEach>
                   </div>
            <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
        </div>
    </div>