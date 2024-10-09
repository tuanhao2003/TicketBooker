const updateBtn = document.querySelectorAll(".updateBtn");
const deleteBtn = document.querySelectorAll(".deleteBtn");
const userId = document.querySelector(".userId");
const searchBox = document.querySelector(".searchBox");
const searchContainer = document.getElementById("searchCollapsed");
const seachShow = document.querySelector(".searchShow");
let timeout;
updateBtn.forEach(btn => {
    btn.addEventListener("click", function () {
        userId.setAttribute("value", btn.getAttribute("name"));
    });
});
deleteBtn.forEach(btn => {
    btn.addEventListener("click", function (e) {
        fetch("http://localhost:8080/admin/userManagement/delete", {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                userId: btn.getAttribute("name")
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Delete failed');
                }
                return response.json();
            })
            .then(response => {
                alert("Delete successfully");
                btn.closest(".userContainer").remove();
            })
            .catch(error => {
                alert("Delete failed: " + error);
            });
    });
});

searchBox.addEventListener("input", function () {
    clearTimeout(timeout);
    timeout = setTimeout(() => {
        if (!searchContainer.classList.contains("show") && searchBox.value !== "") {
            seachShow.click();
        } else if (searchContainer.classList.contains("show") && searchBox.value === "") {
            seachShow.click();
        }
        fetch("http://localhost:8080/admin/userManagement/search", {
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
                console.log(response.listUsers)
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