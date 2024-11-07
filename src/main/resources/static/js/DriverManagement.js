(function () {
    document.addEventListener("DOMContentLoaded", function () {
        // start
        modalController();
        deleteController();
        getDetailsController();
        searchController();

        function deleteController() {
            const deleteBtn = document.querySelectorAll(".delete-btn");
            deleteBtn.forEach(btn => {
                btn.addEventListener("click", function () {
                    console.log(btn.dataset.id);
                    fetch("http://localhost:8080/admin/routes/delete", {
                        method: "DELETE",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify({
                            driverId: btn.dataset.id
                        })
                    })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Delete failed');
                            }
                            return response.json();
                        })
                        .then(data => {
                            if (data === true) {
                                alert("Delete successfully");
                                btn.closest("li").remove();
                            } else {
                                alert("Some error while deleting");
                            }
                        })
                        .catch(error => {
                            alert("Delete failed: " + error);
                        });
                });
            });
        }

        function getDetailsController() {
            const detailsBtn = document.querySelectorAll(".update-btn");
            detailsBtn.forEach(btn => {
                btn.addEventListener("click", function () {
                    window.location.href = "/admin/drivers/details/" + btn.dataset.id;
                });
            });
        }

        function searchController() {
            const searchBox = document.querySelector(".search-box");
            const searchContainer = document.getElementById("search-result-collapse");
            let timeout;

            searchBox.addEventListener("input", function () {
                clearTimeout(timeout);
                timeout = setTimeout(() => {
                    console.log(searchBox.value);
                    if(searchBox.value) {
                        fetch("http://localhost:8080/admin/drivers/search", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json"
                            },
                            body: JSON.stringify({
                                searchTerm: searchBox.value
                            })
                        })
                        .then(response => response.json())
                        .then(response => {
                            searchContainer.classList.remove("collapse");
                            let searchResult = "";
                            if (response.listDriver && response.driverCount > 0) {
                                response.listDriver.forEach(driver => {
                                    searchResult += `
                                    <div class="search-record rounded-sm hover:shadow hover:shadow-zinc-400 py-2 px-4">
                                        <div>Họ tên: ${driver.name}</div>
                                        <div>Số điện thoại: ${driver.phone}</div>
                                    </div>
                                    `;
                                });
                            } else {
                                searchResult = '<div class = "search-record rounded-sm hover:shadow hover:shadow-zinc-400 py-2 px-4">No drivers found</div>';
                            }
                            searchContainer.innerHTML = searchResult;
                        })
                        .catch(error => {
                            alert("Request failed: " + error);
                        });
                    }else{
                        searchContainer.innerHTML = '<div class = "search-record rounded-sm hover:shadow hover:shadow-zinc-400 py-2 px-4">No drivers found</div>';;
                    }
                }, 800);
            });
        }

        function modalController() {
            const addUserOpenBtn = document.querySelector(".add-user-open");
            const addUserCloseBtn = document.querySelector(".add-user-close");
            const modalContainer = document.querySelector(".modal-container");
            const modal = modalContainer.querySelector(".modal");
            const addUserForm = document.querySelector("#add-user-form");
            const formSubmitBtn = modal.querySelector(".add-user-submit");
            let isMouseHoveringForm = false;

            checkMouseEnterFormZone();
            toggleModal();
            submitForm();

            function toggleModal() {
                addUserOpenBtn.addEventListener("click", function () {
                    modalContainer.classList.remove("hidden");
                    modal.classList.remove("-bottom-full");
                });
                addUserCloseBtn.addEventListener("click", function () {
                    modalContainer.classList.add("hidden");
                    modal.classList.add("-bottom-full");
                    addUserForm.querySelectorAll("input[type='text']").forEach(input => { input.value = "" });
                });
                modalContainer.addEventListener("click", function () {
                    if (isMouseHoveringForm === false) {
                        modalContainer.classList.add("hidden");
                        modal.classList.add("-bottom-full");
                    }
                });
            }

            function checkMouseEnterFormZone() {
                modal.addEventListener("mouseenter", function () {
                    isMouseHoveringForm = true;
                });
                modal.addEventListener("mouseleave", function () {
                    isMouseHoveringForm = false;
                });
            }
            function submitForm() {
                formSubmitBtn.addEventListener("click", function () {
                    addUserForm.submit();
                    modalContainer.classList.add("hidden");
                    modal.classList.add("-bottom-full");
                    addUserForm.querySelectorAll("input[type='text']").forEach(input => { input.value = "" });
                });
            }
        }
        // end
    });
})();
