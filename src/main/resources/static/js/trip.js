// Hàm khóa chọn ngày
document.addEventListener("DOMContentLoaded", function () {
    var today = new Date().toISOString().slice(0, 16);  // Lấy ngày hiện tại theo định dạng yyyy-MM-ddThh:mm
    document.getElementById("departureTime").setAttribute("min", today);
//    document.getElementById("arrivalTime").setAttribute("min", today);
});

// Hàm xóa chuyến xe
function confirmDeleteTrip(button) {
    // Tìm container chứa chuyến xe
    const tripContainer = button.closest('.trip-container');

    // Lấy giá trị tripId từ phần tử trong tripContainer
    const tripIdElement = tripContainer.querySelector('.trip-id');
    const tripId = parseInt(tripIdElement.textContent);

    // Hiển thị thông báo xác nhận xóa bằng SweetAlert
    Swal.fire({
        title: 'Xác nhận',
        text: `Bạn có chắc chắn muốn xóa chuyến xe với ID ${tripId} không?`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Có',
        cancelButtonText: 'Không'
    }).then((result) => {
        if (result.isConfirmed) {
            deleteTrip(tripId, tripContainer); // Gọi hàm xóa chuyến xe và truyền tripContainer
        }
    });
}

// Hàm thực hiện fetch để xóa chuyến xe
function deleteTrip(tripId, tripContainer) {
    fetch('/admin/tripManagement', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ tripId: tripId })  // Gửi tripId trong body
    })
    .then(response => {
        if (response.ok) {
            Swal.fire({
                title: 'Đã xóa!',
                text: `Chuyến xe với ID ${tripId} đã được xóa thành công.`,
                icon: 'success',
                confirmButtonText: 'OK'
            });

            // Ngay lập tức xóa container của chuyến xe khỏi giao diện
            tripContainer.remove();  // Xóa container ngay lập tức
        } else {
            Swal.fire({
                title: 'Lỗi',
                text: `Xóa chuyến xe thất bại. Vui lòng thử lại.`,
                icon: 'error',
                confirmButtonText: 'OK'
            });
        }
    })
    .catch(error => {
        console.error('Đã xảy ra lỗi:', error);
        Swal.fire({
            title: 'Lỗi',
            text: 'Có lỗi trong quá trình xóa chuyến xe.',
            icon: 'error',
            confirmButtonText: 'OK'
        });
    });
}

// Hàm load route
document.addEventListener('DOMContentLoaded', function() {
    let routes = [];

    // Hàm để mở modal và tải vị trí
    window.openAddTripModal = function() {
        document.getElementById('addTripModal').classList.remove('hidden');
    }
});