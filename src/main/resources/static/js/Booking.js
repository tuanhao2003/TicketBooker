(function () {

    document.addEventListener("DOMContentLoaded", function () {
        booking();
        handlePayment();
        fetchTripDetails();
        let TICKET_PRICE; // Biến động để lưu giá vé
        let tripTotalPrice = 0; // Biến lưu giá vé tổng của chuyến

        async function fetchTripDetails() {
            try {
                const urlParams = new URLSearchParams(window.location.search);
                const tripId = urlParams.get('tripId');

                const response = await fetch(`/admin/trips/${tripId}`);
                if (response.ok) {
                    const data = await response.json();

                    document.getElementById('departureLocation').textContent = `${data.departureLocation} - ${data.arrivalLocation}`;
                    document.getElementById('departureTime').textContent = data.departureTime;

                    // Log giá trị của data.totalPrice
                    console.log('Total Price from API:', data.totalPrice);

                    // Lưu giá trị tổng giá vé của chuyến xe
                    TICKET_PRICE = data.totalPrice;

                    // Cập nhật hiển thị giá vé
                    updatePriceInfo();
                } else {
                    console.error('Không thể lấy thông tin chuyến xe');
                }
            } catch (error) {
                console.error('Lỗi khi lấy thông tin chuyến xe:', error);
            }
        }

        function booking() {
            let selectedSeats = [];
            let totalPrice = 0;

            const seats = document.querySelectorAll('.seat');
            const urlParams = new URLSearchParams(window.location.search);
            const tripId = urlParams.get('tripId');

            // Lấy danh sách các ghế đã được đặt từ backend khi trang được tải
            async function fetchBookedSeats() {
                try {
                    const response = await fetch(`/api/seats/${tripId}/booked`);
                    if (response.ok) {
                        const bookedSeats = await response.json();
                        markBookedSeats(bookedSeats);
                    }
                } catch (error) {
                    console.error('Lỗi khi lấy danh sách ghế đã đặt:', error);
                }
            }

            // Đánh dấu các ghế đã được đặt
            function markBookedSeats(bookedSeats) {
                seats.forEach(seat => {
                    if (bookedSeats.includes(seat.textContent.trim())) {
                        seat.classList.add('bg-gray-200', 'seat-booked');
                        seat.disabled = true; // Vô hiệu hóa ghế đã được đặt
                    }
                });
            }

            // Cập nhật thông tin giá vé
            function updatePriceInfo() {
                document.getElementById('seatCount').textContent = `${selectedSeats.length} Ghế`;
                document.getElementById('selectedSeats').textContent = selectedSeats.join(', ');
                totalPrice = selectedSeats.length * TICKET_PRICE; // Tính lại tổng giá
                document.getElementById('totalPrice').textContent = `${totalPrice.toLocaleString()}đ`;
                document.getElementById('ticketPrice').textContent = `${TICKET_PRICE.toLocaleString()}đ`;
                document.getElementById('grandTotal').textContent = `${totalPrice.toLocaleString()}đ`;
            }

            // Toggle chọn ghế
            function toggleSeat(button) {
                if (button.classList.contains('seat-booked')) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Ghế này đã được đặt!',
                        confirmButtonText: 'OK'
                    });
                    return;  // Nếu ghế đã được đặt, không cho phép chọn
                }

                if (button.classList.contains('seat-selected')) {
                    button.classList.remove('seat-selected');
                    button.classList.add('seat-available');
                    selectedSeats = selectedSeats.filter(s => s !== button.textContent);
                } else if (button.classList.contains('seat-available') && selectedSeats.length < 5) {
                    button.classList.remove('seat-available');
                    button.classList.add('seat-selected');
                    selectedSeats.push(button.textContent);
                } else if (selectedSeats.length >= 5) {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Không thể chọn quá 5 ghế!',
                        confirmButtonText: 'OK'
                    });
                }

                updatePriceInfo();
            }

            // Gọi API ngay lập tức khi trang được tải để lấy danh sách ghế đã đặt
            fetchBookedSeats();

            // Thêm sự kiện click vào tất cả các ghế
            seats.forEach(seat => {
                seat.addEventListener('click', function () {
                    toggleSeat(seat);
                });
            });
        }

// Gọi fetchTripDetails khi trang được tải
        document.addEventListener('DOMContentLoaded', function () {
            fetchTripDetails();
            booking();
        });


        function handlePayment() {
            const btnPay = document.getElementById('btnPay');
            btnPay.addEventListener("click", async function () {
                console.log("handlePayment đã được gọi!");

                try {
                    var tripId = new URLSearchParams(window.location.search).get('tripId') || "";
                    var selectedSeats = document.getElementById('selectedSeats')?.innerText || "";
                    var grandTotal = document.getElementById('totalPrice')?.innerText || "0";
                    var customerName = document.querySelector('[name="customerName"]')?.value || "";
                    var customerPhone = document.querySelector('[name="customerPhone"]')?.value || "";
                    var email = document.querySelector('[name="email"]')?.value || "";

                    grandTotal = grandTotal.replace(/[^0-9]/g, ""); // "100000"
                    grandTotal = parseInt(grandTotal, 10);
                    if (!selectedSeats || !customerName || !customerPhone || !email) {
                        alert("Vui lòng điền đầy đủ thông tin!");
                        return;
                    }

                    // Lưu dữ liệu cần thiết vào cookie
                    document.cookie = `tripId=${encodeURIComponent(tripId)}; path=/`;
                    document.cookie = `selectedSeats=${encodeURIComponent(selectedSeats)}; path=/`;
                    document.cookie = `grandTotal=${encodeURIComponent(grandTotal)}; path=/`;
                    document.cookie = `customerName=${encodeURIComponent(customerName)}; path=/`;
                    document.cookie = `customerPhone=${encodeURIComponent(customerPhone)}; path=/`;
                    document.cookie = `email=${encodeURIComponent(email)}; path=/`;

                    // Gọi API prebooking-seat
                    const preBookingResponse = await fetch('/api/seats/prebooking-seat', {
                        method: 'POST',
                        credentials: 'include' // Gửi kèm cookie
                    });

                    if (!preBookingResponse.ok) {
                        alert("Đặt trước ghế thất bại. Vui lòng thử lại!");
                        return;
                    }

//                    const seatIds = await preBookingResponse.json(); // Lấy danh sách seatIds trả về
//                    console.log("Ghế được đặt trước thành công:", seatIds);

                    // Điều hướng đến trang thanh toán
                    const selectedPayment = document.querySelector('input[name="payment"]:checked');
                    if (selectedPayment) {
                        const paymentMethod = selectedPayment.closest('div').querySelector('span').textContent.trim();
                        console.log("Phương thức thanh toán đã chọn:", paymentMethod);

                        if (paymentMethod === "VNPay") {
                            window.location.href = '/vnpay';
                        } else {
                            (
                                function () {
                                    const total = document.getElementById("totalPrice").innerText.replace(",", "").replace("đ", "");
                                    const fullName = document.querySelector(".apiField[name='customerName']").value;
                                    const phone = document.querySelector(".apiField[name='customerPhone']").value;
                                    const email = document.querySelector(".apiField[name='email']").value;
                                    const descript = Date.now().toString(36) + Math.random().toString(36).substring(2)
                                    console.log(total);

                                    fetch("http://localhost:8080/payment/zalo-payment", {
                                        method: "POST",
                                        headers: {"Content-Type": "application/json"},
                                        body: JSON.stringify({appUser: fullName, amount: total, description: descript})
                                    }).then(
                                        respone => respone.json()
                                    ).then(
                                        data => {
                                            if (data.returnCode === 1) {
                                                fetch("http://localhost:8080/payment/zalo-payment-status", {
                                                    method: "POST",
                                                    headers: {"Content-Type": "application/json"},
                                                    body: JSON.stringify(data)
                                                }).then(async response => {
                                                    await response.json()
                                                }).then(
                                                    data => {
                                                        console.log(data.returnMessage)
                                                    }).catch(e => console.log(e));
                                                window.location.href = data.returnUrl;
                                            }
                                        }
                                    ).catch(
                                        e => console.log(e)
                                    );
                                }
                            )();
                        }
                    } else {
                        console.log("Không có phương thức thanh toán nào được chọn.");
                    }

                } catch (error) {
                    console.error("Đã xảy ra lỗi khi xử lý thanh toán:", error);
                    alert("Đã xảy ra lỗi. Vui lòng thử lại.");
                }
            });
        }
    });
})();