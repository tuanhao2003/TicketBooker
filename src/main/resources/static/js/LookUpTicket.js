tailwind.config = {
    theme: {
        extend: {
            colors: {
                primary: '#6B46C1',
                secondary: '#9F7AEA',
                accent: '#D6BCFA',
            }
        }
    }
}
function fetchTicketInfo() {
    // Simulate API call
    document.getElementById("searchSection").classList.add("hidden");
    document.getElementById("ticketInfo").classList.remove("hidden");
}

function resetForm() {
    document.getElementById("ticketId").value = "";
    document.getElementById("ticketInfo").classList.add("hidden");
    document.getElementById("searchSection").classList.remove("hidden");
}

document.addEventListener('DOMContentLoaded', function() {
    async function fetchTicketInfo() {
        const ticketId = document.getElementById("ticketId").value.trim();
        const customerPhone = document.getElementById("phoneNumber").value.trim();

        // Kiểm tra đầu vào
        if (!ticketId) {
            alert("Vui lòng nhập mã vé.");
            return;
        }

        if (!customerPhone) {
            alert("Vui lòng nhập số điện thoại.");
            return;
        }

        try {
            // Gửi request đến API
            const response = await fetch("http://localhost:8080/api/tickets/payment-infor", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ ticketId, customerPhone })
            });

            if (!response.ok) {
                throw new Error("Không tìm thấy thông tin vé hoặc có lỗi xảy ra.");
            }

            const ticketInfo = await response.json();

            // Hiển thị dữ liệu trên giao diện
            const elements = {
                "ticketIdValue": ticketInfo.id,
                "customerNameValue": ticketInfo.customerName,
                "customerPhoneValue": ticketInfo.customerPhone,
                "bookingDateValue": ticketInfo.paymentTime,
                "emailValue": ticketInfo.email,
                "totalPriceValue": `${ticketInfo.totalAmount} VND`
            };

            for (const [id, value] of Object.entries(elements)) {
                const element = document.getElementById(id);
                if (element) {
                    element.textContent = value;
                } else {
                    console.warn(`Element with id "${id}" not found`);
                }
            }

            // Hiển thị thông tin lộ trình
            const tripInfoElements = {
                ".tripInfo .departure .time": ticketInfo.departureTime,
                ".tripInfo .departure .location": ticketInfo.departureLocation,
                ".tripInfo .arrival .time": ticketInfo.arrivalTime,
                ".tripInfo .arrival .location": ticketInfo.arrivalLocation
            };

            for (const [selector, value] of Object.entries(tripInfoElements)) {
                const element = document.querySelector(selector);
                if (element) {
                    element.textContent = value;
                } else {
                    console.warn(`Element with selector "${selector}" not found`);
                }
            }

            // Ẩn modal tìm kiếm và hiện khối thông tin vé
            const searchSection = document.getElementById("searchSection");
            const ticketInfoElement = document.getElementById("ticketInfo");

            if (searchSection) {
                searchSection.classList.add("hidden");
            } else {
                console.warn('Element with id "searchSection" not found');
            }

            if (ticketInfoElement) {
                ticketInfoElement.classList.remove("hidden");
            } else {
                console.warn('Element with id "ticketInfo" not found');
            }

        } catch (error) {
            alert(error.message);
        }
    }

    function resetForm() {
        const elements = ["ticketId", "phoneNumber"];
        elements.forEach(id => {
            const element = document.getElementById(id);
            if (element) {
                element.value = "";
            } else {
                console.warn(`Element with id "${id}" not found`);
            }
        });

        const searchSection = document.getElementById("searchSection");
        const ticketInfoElement = document.getElementById("ticketInfo");

        if (searchSection) {
            searchSection.classList.remove("hidden");
        } else {
            console.warn('Element with id "searchSection" not found');
        }

        if (ticketInfoElement) {
            ticketInfoElement.classList.add("hidden");
        } else {
            console.warn('Element with id "ticketInfo" not found');
        }
    }

    // Gán các hàm cho các nút tương ứng
    const searchButton = document.querySelector('button[onclick="fetchTicketInfo()"]');
    if (searchButton) {
        searchButton.onclick = fetchTicketInfo;
    } else {
        console.warn('Search button not found');
    }

    const resetButton = document.querySelector('button[onclick="resetForm()"]');
    if (resetButton) {
        resetButton.onclick = resetForm;
    } else {
        console.warn('Reset button not found');
    }
});