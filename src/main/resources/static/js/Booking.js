
(function () {

    document.addEventListener("DOMContentLoaded", function () {
        booking();
        handlePayment();

        function booking(){
            const TICKET_PRICE = 460000; // Giá vé mỗi ghế
            let selectedSeats = [];
            let totalPrice = 0;

            const seats = document.querySelectorAll('.seat');

            // Gắn sự kiện click cho từng nút
            seats.forEach(seat => {
                seat.addEventListener('click', function () {
                    toggleSeat(seat);
                });
            });
            function updatePriceInfo() {
                document.getElementById('selectedSeats').textContent = selectedSeats.join(', ');
                document.getElementById('totalPrice').textContent = `${totalPrice.toLocaleString()}đ`;
                document.getElementById('ticketPrice').textContent = `${totalPrice.toLocaleString()}đ`;
                document.getElementById('grandTotal').textContent = `${totalPrice.toLocaleString()}đ`;
            }

            function toggleSeat(button) {
                // Nếu ghế đang ở trạng thái "đang chọn", chuyển về trạng thái "còn trống" và trừ tiền
                if (button.classList.contains('seat-selected')) {
                    button.classList.remove('seat-selected');
                    button.classList.add('seat-available');
                    // Loại bỏ ghế khỏi danh sách ghế được chọn
                    selectedSeats = selectedSeats.filter(s => s !== button.textContent);
                    // Trừ giá tiền
                    totalPrice -= TICKET_PRICE;
                }
                // Nếu ghế đang ở trạng thái "còn trống", chuyển sang trạng thái "đang chọn" và cộng tiền
                else if (button.classList.contains('seat-available')) {
                    button.classList.remove('seat-available');
                    button.classList.add('seat-selected');
                    // Thêm ghế vào danh sách ghế được chọn
                    selectedSeats.push(button.textContent);
                    // Cộng giá tiền
                    totalPrice += TICKET_PRICE;
                }

                // Cập nhật thông tin hiển thị
                updatePriceInfo();
            }
        }
        function handlePayment() {

            const btnPay = document.getElementById('btnPay');
            btnPay.addEventListener("click", function(){
                console.log("handlePayment đã được gọi!");
                try {
                    // Lấy thông tin từ các input và biến
                    var tripId = 123; // Cần lấy tripId từ thông tin chuyến đi
                    var selectedSeats = document.getElementById('selectedSeats')?.innerText || ""; // Số ghế đã chọn
                    var grandTotal = document.getElementById('totalPrice')?.innerText || "0"; // Tổng tiền
                    var customerName = document.querySelector('[name="customerName"]')?.value || ""; // Tên khách hàng
                    var customerPhone = document.querySelector('[name="customerPhone"]')?.value || ""; // Số điện thoại
                    var email = document.querySelector('[name="email"]')?.value || ""; // Email

                    console.log({
                        tripId,
                        selectedSeats,
                        grandTotal,
                        customerName,
                        customerPhone,
                        email,
                    });

                    // Kiểm tra dữ liệu nhập vào
                    if (!selectedSeats || !customerName || !customerPhone || !email) {
                        alert("Vui lòng điền đầy đủ thông tin!");
                        return;
                    }

                    // Lưu thông tin vào sessionStorage
                    sessionStorage.setItem('tripId', tripId);
                    sessionStorage.setItem('seatSelect', selectedSeats);
                    sessionStorage.setItem('grandTotal', grandTotal);
                    sessionStorage.setItem('customerName', customerName);
                    sessionStorage.setItem('customerPhone', customerPhone);
                    sessionStorage.setItem('email', email);

                    // Chuyển hướng đến URL /vnpay
                    window.location.href = '/vnpay';
                } catch (error) {
                    console.error("Đã xảy ra lỗi khi xử lý thanh toán:", error);
                    alert("Đã xảy ra lỗi. Vui lòng thử lại.");
                }
            })
        }
    });
})();