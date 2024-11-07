(
    function () {
        document.addEventListener("DOMContentLoaded", function () {
            sidebarController();
            navbarController();
            searchCollapseController();

            function navbarController() {
                window.addEventListener("load", pathShowControl());

                function pathShowControl() {
                    const pathShow = document.querySelector(".path-show");
                    let pathSplit = window.location.pathname.split("/");

                    pathShow.innerHTML = "";
                    pathSplit.forEach((pathComponent, index) => {
                        if (index > 0) {
                            if (index < pathSplit.length - 1) {
                                pathShow.innerHTML += `
                                <div>${pathComponent}</div>
                                <i class="fa-solid fa-angle-right h-fit"></i>
                                `;
                            } else {
                                pathShow.innerHTML += `
                                <div>${pathComponent}</div>
                                `;
                            }
                        }
                    });
                }
            }

            function sidebarController() {
                collapeControl();

                function collapeControl() {
                    const listCollapseToggle = document.querySelectorAll(".collapse-toggle");

                    listCollapseToggle.forEach(toggle => {
                        toggle.addEventListener("click", function () {
                            let targetId = toggle.getAttribute("data-fuba-target");
                            document.querySelector(targetId).classList.toggle("md:w-full")
                        });
                    });
                }
            }

            function searchCollapseController(){
                const contentSection = document.querySelector(".content-section");
                const searchContainer = document.getElementById("search-result-collapse");
                let isMouseHoveringForm = false;
                searchContainer.addEventListener("mouseenter", function () {
                    isMouseHoveringForm = true;
                });
                searchContainer.addEventListener("mouseleave", function () {
                    isMouseHoveringForm = false;
                });
                contentSection.addEventListener( "click", function checkMouseEnterFormZone() {
                    if (isMouseHoveringForm === false){
                        searchContainer.classList.add("collapse");
                    }
                })
            }

        });
    }
)();
