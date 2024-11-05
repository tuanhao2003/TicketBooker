(function () {
    document.addEventListener("DOMContentLoaded", function () {
        // start
        modalController();
        deleteController();
        getDetailsController();
        searchController();
        updateController();

        function updateController() {
            const fileInput = document.querySelector(".avatar-input");
            const avatarShow = fileInput.closest("img");

            fileInput.addEventListener('change', function (event) {
                const file = event.target.files[0];

                if (file) {
                    const reader = new FileReader();

                    reader.onload = function (e) {
                        avatarShow.src = e.target.result;
                    };

                    fileInput.value = reader.readAsDataURL(file);
                }
            });
        }
        function deleteController() {
            const deleteBtn = document.querySelectorAll(".delete-btn");
            deleteBtn.forEach(btn => {
                btn.addEventListener("click", function (e) {
                    fetch("http://localhost:8080/api/users/delete", {
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
                    if (searchBox.value !== "") {
                        searchContainer.classList.remove("h-0");
                        searchContainer.classList.add("h-fit");
                    } else if (searchBox.value === "") {
                        searchContainer.classList.add("h-0");
                        searchContainer.classList.remove("h-fit");
                    }
                    fetch("http://localhost:8080/api/users/search", {
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
                                    searchContainer.innerHTML +=
                                        '<div class="shadow hover:bg-gray-100 flex justify-start w-5/6 items-center space-x-10 search-data" th:attr="data-id=${user.id}>' +
                                        '<img th:if="${user.profilePhoto != null}" th:src="${user.profilePhoto}" class="rounded-full shadow-inner h-full aspect-square border shadow-gray-500">' +
                                        '<img th:if="${user.profilePhoto == null and user.gender == T(com.example.ticketbooker.Util.Enum.Gender).MALE}" th:src="@{/components/noavt_male.png}" class="rounded-full shadow-inner h-full aspect-square border shadow-gray-500" >' +
                                        '<img th:if="${user.profilePhoto == null and user.gender == T(com.example.ticketbooker.Util.Enum.Gender).FEMALE}" th:src="@{/components/noavt_female.png}" class="rounded-full shadow-inner h-full aspect-square border shadow-gray-500">' +
                                        '<img th:if="${user.profilePhoto == null and user.gender == T(com.example.ticketbooker.Util.Enum.Gender).OTHER}" th:src="@{/components/noavt_other.png}" class="rounded-full shadow-inner h-full aspect-square border shadow-gray-500">' +
                                        '<div class="flex flex-col items-start justify-center">' +
                                        '<div th:text="${user.fullName}"> </div>' +
                                        '<div th:text="${user.account != null ? user.account.email : ' + "'No email'" + '}"></div>' +
                                        '</div>' +
                                        '</div>';
                                });
                                let searchData = searchContainer.querySelectorAll(".search-data");
                                searchData.forEach(data => {
                                    data.addEventListener("click", function () {
                                        window.location.href = "/admin/users/details/" + data.dataset.id;
                                    });
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
