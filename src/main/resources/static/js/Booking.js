(function () {

    document.addEventListener("DOMContentLoaded", function () {
        booking();
        handlePayment();

        function booking() {
            const TICKET_PRICE = 460000; // Giá vé mỗi ghế
            let selectedSeats = [];
            let totalPrice = 0;

            const seats = document.querySelectorAll('.seat');

            seats.forEach(seat => {
                seat.addEventListener('click', function () {
                    toggleSeat(seat);
                });
            });

            function updatePriceInfo() {
                document.getElementById('seatCount').textContent = `${selectedSeats.length} Ghế`;
                document.getElementById('selectedSeats').textContent = selectedSeats.join(', ');
                document.getElementById('totalPrice').textContent = `${totalPrice.toLocaleString()}đ`;
                document.getElementById('ticketPrice').textContent = `${totalPrice.toLocaleString()}đ`;
                document.getElementById('grandTotal').textContent = `${totalPrice.toLocaleString()}đ`;
            }

            function toggleSeat(button) {
                if (button.classList.contains('seat-selected')) {
                    button.classList.remove('seat-selected');
                    button.classList.add('seat-available');
                    selectedSeats = selectedSeats.filter(s => s !== button.textContent);
                    totalPrice -= TICKET_PRICE;
                } else if (button.classList.contains('seat-available') && selectedSeats.length < 5) {
                    button.classList.remove('seat-available');
                    button.classList.add('seat-selected');
                    selectedSeats.push(button.textContent);
                    totalPrice += TICKET_PRICE;
                } else if (selectedSeats.length >= 5) {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Không thể chọn quá 5 ghế!',
                        confirmButtonText: 'OK'
                    });
                }

                updatePriceInfo();
            }
        }

        function handlePayment() {
            const btnPay = document.getElementById('btnPay');
            btnPay.addEventListener("click", function () {
                console.log("handlePayment đã được gọi!");

                try {
                    var tripId = document.getElementById('tripId')?.value || "";
                    var selectedSeats = document.getElementById('selectedSeats')?.innerText || "";
                    var grandTotal = document.getElementById('totalPrice')?.innerText || "0";
                    var customerName = document.querySelector('[name="customerName"]')?.value || "";
                    var customerPhone = document.querySelector('[name="customerPhone"]')?.value || "";
                    var email = document.querySelector('[name="email"]')?.value || "";
                    if (!selectedSeats || !customerName || !customerPhone || !email) {
                        alert("Vui lòng điền đầy đủ thông tin!");
                        return;
                    }

                    // Tìm phương thức thanh toán đã chọn
                    const selectedPayment = document.querySelector('input[name="payment"]:checked');
                    if (selectedPayment) {
                        const paymentMethod = selectedPayment.closest('div').querySelector('span').textContent.trim();
                        if(paymentMethod === "VNPay"){
                            console.log("Phương thức thanh toán đã chọn:", paymentMethod);

                            document.cookie = `tripId=${encodeURIComponent(tripId)}; path=/`;
                            document.cookie = `selectedSeats=${encodeURIComponent(selectedSeats)}; path=/`;
                            document.cookie = `grandTotal=${encodeURIComponent(grandTotal)}; path=/`;
                            document.cookie = `customerName=${encodeURIComponent(customerName)}; path=/`;
                            document.cookie = `customerPhone=${encodeURIComponent(customerPhone)}; path=/`;
                            document.cookie = `email=${encodeURIComponent(email)}; path=/`;

                            window.location.href = '/vnpay';
                        } else{
                            console.log("Phương thức thanh toán đã chọn:", paymentMethod);

                            document.cookie = `tripId=${encodeURIComponent(tripId)}; path=/`;
                            document.cookie = `selectedSeats=${encodeURIComponent(selectedSeats)}; path=/`;
                            document.cookie = `grandTotal=${encodeURIComponent(grandTotal)}; path=/`;
                            document.cookie = `customerName=${encodeURIComponent(customerName)}; path=/`;
                            document.cookie = `customerPhone=${encodeURIComponent(customerPhone)}; path=/`;
                            document.cookie = `email=${encodeURIComponent(email)}; path=/`;

                            window.location.href = '/zalopay';
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