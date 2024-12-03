(
    function () {
        document.addEventListener("DOMContentLoaded", function () {
            loadSelectData();


            function loadSelectData() {
                getRoutes();

                function getRoutes() {
                    const fetchUrl = "http://localhost:8080/admin/routes/get-routes";
                    fetch(fetchUrl, {
                        method: "POST",
                        headers: {"Content-Type": "application/json"}
                    }).then(respone => {
                        if (respone.ok) {
                            return respone.json();
                        } else {
                            throw new Error(`HTTP error! Status: ${response.status}`);
                        }
                    }).then(data => {
                        preSetSelect(data.list);
                    }).catch(e => console.log(e));
                }

                function preSetSelect(data) {
                    const routeDeparture = document.querySelector(".selectFill.departure");
                    const routeArrival = document.querySelector(".selectFill.arrival");
                    routeDeparture.innerHTML = "";
                    routeArrival.innerHTML = "";
                    let departureNames = [];
                    let arrivalNames = [];
                    data.forEach(route => {
                        departureNames.push(route.departureLocation);
                        arrivalNames.push(route.arrivalLocation);
                    });
                    const listDepartures = [...new Set(departureNames)];
                    const listArrivals = [...new Set(arrivalNames)];
                    listDepartures.forEach(departureLocation => {
                        routeDeparture.innerHTML += `<option value="${departureLocation}">${departureLocation}</option>`;
                    });
                    listArrivals.forEach(arrivalLocation => {
                        routeArrival.innerHTML += `<option value="${arrivalLocation}">${arrivalLocation}</option>`;
                    });
                }
            }
        });
    }
)();
// Mobile menu toggle
const mobileMenuBtn = document.getElementById('mobileMenuBtn');
const mobileMenu = document.getElementById('mobileMenu');
mobileMenuBtn.addEventListener('click', () => {
    mobileMenu.classList.toggle('hidden');
});

// Login modal
const loginBtn = document.getElementById('loginBtn');
const loginModal = document.getElementById('loginModal');
const closeLoginModal = document.getElementById('closeLoginModal');

loginBtn.addEventListener('click', () => {
    loginModal.classList.remove('hidden');
    loginModal.classList.add('flex');
});

closeLoginModal.addEventListener('click', () => {
    loginModal.classList.remove('flex');
    loginModal.classList.add('hidden');
});

// Close modal when clicking outside
loginModal.addEventListener('click', (e) => {
    if (e.target === loginModal) {
        loginModal.classList.remove('flex');
        loginModal.classList.add('hidden');
    }
});

// Carousel functionality
const carousel = document.querySelector('.carousel');
const carouselItems = document.querySelectorAll('.carousel-item');
const carouselButtons = document.querySelectorAll('.carousel + div button');
let currentSlide = 0;

function updateCarousel() {
    carousel.style.transform = `translateX(-${currentSlide * 100}%)`;
    carouselButtons.forEach((button, index) => {
        button.classList.toggle('bg-white', index === currentSlide);
        button.classList.toggle('bg-gray-300', index !== currentSlide);
    });
}

carouselButtons.forEach((button, index) => {
    button.addEventListener('click', () => {
        currentSlide = index;
        updateCarousel();
    });
});

setInterval(() => {
    currentSlide = (currentSlide + 1) % carouselItems.length;
    updateCarousel();
}, 5000);

// Date input min date
const dateInput = document.querySelector('input[type="date"]');
dateInput.min = new Date().toISOString().split('T')[0];

// Animate elements on scroll
const animateOnScroll = (entries, observer) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.classList.add('animate-slide-in');
            observer.unobserve(entry.target);
        }
    });
};

const observer = new IntersectionObserver(animateOnScroll, {
    root: null,
    threshold: 0.1
});

document.querySelectorAll('.grid > div').forEach(el => observer.observe(el));

