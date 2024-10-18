(
    function () {
        document.addEventListener("DOMContentLoaded", function () {
            const listCollapseToggle = document.querySelectorAll(".collapse-toggle");

            listCollapseToggle.forEach(toggle => {
                toggle.addEventListener("click", function () {
                    let targetId = toggle.getAttribute("data-fuba-target");
                    document.querySelector(targetId).classList.toggle("hidden")
                });
            });

        });
    }
)();
