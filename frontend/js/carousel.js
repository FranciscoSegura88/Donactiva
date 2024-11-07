// js/carousel.js
document.addEventListener("DOMContentLoaded", () => {
    const carousel = document.getElementById("carousel");
    const slides = carousel.children;
    const totalSlides = slides.length;
    let currentIndex = 0;

    function showNextSlide() {
        currentIndex = (currentIndex + 1) % totalSlides;
        carousel.style.transform = `translateX(-${currentIndex * 100}%)`;
    }

    setInterval(showNextSlide, 3000);
});
