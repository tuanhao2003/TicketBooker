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
                btn.addEventListener("click", function (e) {
                    fetch("http://localhost:8080/admin/users/delete", {
                        method: "DELETE",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify({
                            userId: btn.dataset.id
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
            const detailsBtn = document.querySelectorAll(".details-btn");
            detailsBtn.forEach(btn => {
                btn.addEventListener("click", function () {
                    window.location.href = "/admin/users/details/" + btn.dataset.id;
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
                    if (!searchContainer.classList.contains("show") && searchBox.value !== "") {
                        searchContainer.classList.remove("hidden");
                    } else if (searchContainer.classList.contains("show") && searchBox.value === "") {
                        searchContainer.classList.remove("hidden");
                    }
                    fetch("http://localhost:8080/admin/users/search", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify({
                            name: searchBox.value
                        })
                    })
                        .then(response => response.json())
                        .then(response => {
                            if (response.listUsers && response.listUsers.length > 0) {
                                response.listUsers.forEach(user => {
                                    searchContainer.innerHTML += `
                                <div class="user-card">
                                    <img src="${user.profilePhoto}" alt="Profile Photo">
                                    <div>User Name: ${user.firstName} ${user.lastName}</div>
                                    <div>Address: ${user.address}</div>
                                    <div>Date of Birth: ${user.dateOfBirth}</div>
                                    <div>Gender: ${user.gender}</div>
                                </div>
                            `;
                                });
                            } else {
                                searchContainer.innerHTML = '<div>No users found</div>';
                            }
                        })
                        .catch(error => {
                            alert("Request failed: " + error);
                        });
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
