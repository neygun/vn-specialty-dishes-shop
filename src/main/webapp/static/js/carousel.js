const multipleItemCarousel1 = document.querySelector("#carouselExampleControls1");

if (window.matchMedia("(min-width: 576px)").matches) {
  const carousel1 = new bootstrap.Carousel(multipleItemCarousel1, {
    interval: false,
  });
  let carouselWidth = $(".carousel-inner1")[0].scrollWidth;
  let cardWidth = $(".carousel-item1").width();
  let scrollPosition = 0;
  
  $(".carousel-control-next1").on("click", function() {
    if (scrollPosition < (carouselWidth - (cardWidth * 5))) {
      scrollPosition += cardWidth;
      $(".carousel-inner1").animate({ scrollLeft: scrollPosition }, 200);
    }
  });
  
  $(".carousel-control-prev1").on("click", function() {
    if (scrollPosition > 0) {
      scrollPosition -= cardWidth;
      $(".carousel-inner1").animate({ scrollLeft: scrollPosition }, 200);
    }
  });
} else {
  $(multipleItemCarousel1).addClass("slide");
}

const multipleItemCarousel2 = document.querySelector("#carouselExampleControls2");

if (window.matchMedia("(min-width: 576px)").matches) {
  const carousel2 = new bootstrap.Carousel(multipleItemCarousel2, {
    interval: false,
  });
  let carouselWidth = $(".carousel-inner2")[0].scrollWidth;
  let cardWidth = $(".carousel-item2").width();
  let scrollPosition = 0;
  
  $(".carousel-control-next2").on("click", function() {
    if (scrollPosition < (carouselWidth - (cardWidth * 5))) {
      scrollPosition += cardWidth;
      $(".carousel-inner2").animate({ scrollLeft: scrollPosition }, 200);
    }
  });
  
  $(".carousel-control-prev2").on("click", function() {
    if (scrollPosition > 0) {
      scrollPosition -= cardWidth;
      $(".carousel-inner2").animate({ scrollLeft: scrollPosition }, 200);
    }
  });
} else {
  $(multipleItemCarousel2).addClass("slide");
}

const multipleItemCarousel3 = document.querySelector("#carouselExampleControls3");

if (window.matchMedia("(min-width: 576px)").matches) {
  const carousel3 = new bootstrap.Carousel(multipleItemCarousel3, {
    interval: false,
  });
  let carouselWidth = $(".carousel-inner3")[0].scrollWidth;
  let cardWidth = $(".carousel-item3").width();
  let scrollPosition = 0;
  
  $(".carousel-control-next3").on("click", function() {
    if (scrollPosition < (carouselWidth - (cardWidth * 5))) {
      scrollPosition += cardWidth;
      $(".carousel-inner3").animate({ scrollLeft: scrollPosition }, 200);
    }
  });
  
  $(".carousel-control-prev3").on("click", function() {
    if (scrollPosition > 0) {
      scrollPosition -= cardWidth;
      $(".carousel-inner3").animate({ scrollLeft: scrollPosition }, 200);
    }
  });
} else {
  $(multipleItemCarousel3).addClass("slide");
}
