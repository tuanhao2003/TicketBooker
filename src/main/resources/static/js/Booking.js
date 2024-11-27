const TICKET_PRICE = 460000; // Giá vé mỗi ghế
let selectedSeats = [];
let totalPrice = 0;

function updatePriceInfo() {
    document.getElementById('seatCount').textContent = `${selectedSeats.length} Ghế`;
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

// Khởi tạo thông tin giá tiền và ghế
updatePriceInfo();