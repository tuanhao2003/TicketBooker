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
    fetch('/admin/trips', {
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

    // Hàm để mở modal và tải vị trí
    window.openAddTripModal = function () {
        let departureOptions = '<option value="">Chọn điểm khởi hành</option>';
        document.querySelector('#addTripModal').classList.toggle('hidden');
        fetch('/admin/routes/getDepartureLocation')
            .then(response => response.json())
            .then (data => {
                data.forEach(value => {
                    departureOptions +="<option value='"+value+"'>"+value+"</option>";
                })
                document.querySelector('#departureLocation').innerHTML = departureOptions;
            })
            .catch(error => {
                console.error('Đã xảy ra lỗi:', error);
                Swal.fire({
                    title: 'Lỗi',
                    text: 'Có lỗi.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            });

        //Hàm load Tài xế
        let driverOptions = '<option value="">Chọn tài xế</option>';

        fetch('/api/drivers/getAll')
            .then(response => response.json())
            .then (data => {
                console.log(data)
                data.listDriver.forEach(value => {
                    driverOptions +="<option value='"+value.driverId+"'>"+value.name+"</option>";
                })
                document.querySelector('#driverName').innerHTML = driverOptions;
            })
            .catch(error => {
                console.error('Đã xảy ra lỗi:', error);
                Swal.fire({
                    title: 'Lỗi',
                    text: 'Có lỗi.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            });
    }
    // Hàm load Arrival Location
    document.querySelector("#departureLocation").addEventListener('change',function (){
        let arrivalLocation = '';
        let url = '/admin/routes/getArrivalLocation?departureLocation=' + this.value;
        fetch(url)
            .then(response =>response.json())
            .then(data => {
                data.forEach(value => {
                    arrivalLocation += "<option value='"+value.routeId+"'>"+value.arrivalLocation+"</option>";
                })
                document.querySelector('#arrivalLocation').innerHTML = arrivalLocation;
                const arrival = document.getElementById("arrivalLocation").value;
                document.getElementById("routeId").value = arrival;
            })
            .catch(error => {
                console.error('Đã xảy ra lỗi:', error);
                Swal.fire({
                    title: 'Lỗi',
                    text: 'Có lỗi.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            });
    })
});

function submitAddTripForm() {
//    const busId = document.getElementById('busId').value;
//    const driverId = document.getElementById('driverId').value;
    const departureStation = document.getElementById('departureStation').value;
    const arrivalStation = document.getElementById('arrivalStation').value;
    const departureTime = document.getElementById('departureTime').value;
    const price = document.getElementById('price').value;
    const availableSeats = document.getElementById('availableSeats').value;

    if (!departureStation || !arrivalStation || !departureTime || !price || !availableSeats) {
        Swal.fire({
            icon: 'warning',
            title: 'Thông tin không đầy đủ!',
            text: 'Vui lòng điền tất cả các trường trước khi tạo chuyến xe.',
            confirmButtonText: 'OK'
        });
    } else {
        // Nếu đã có routeId, hiển thị thông báo thành công trước
        Swal.fire({
            icon: 'success',
            title: 'Tạo chuyến xe thành công!',
            text: 'Chuyến xe đã được tạo thành công.',
            confirmButtonText: 'OK'
        }).then((result) => {
            // Kiểm tra xem người dùng đã bấm OK chưa
            if (result.isConfirmed) {
                // Sau khi bấm OK, tiến hành gửi dữ liệu form
                document.getElementById('addTripForm').submit();
            }
        });
    }
}

function editTripController() {
    window.editTrip = function(button) {
        const tripContainer = button.closest('.trip-container');

        const tripId = tripContainer.querySelector('.trip-id').textContent.trim();
        const routeId = tripContainer.querySelector('.route-id').textContent.trim();
        const busId = tripContainer.querySelector('.bus-id').textContent.trim();
        const driverId = tripContainer.querySelector('.driver-id').textContent.trim();
        const status = tripContainer.querySelector('.status').textContent.trim();
        const departureLocation = tripContainer.querySelector('.departure-location').textContent.trim();
        const departureStation = tripContainer.querySelector('.departure-station').textContent.trim();
        const arrivalLocation = tripContainer.querySelector('.arrival-location').textContent.trim();
        const arrivalStation = tripContainer.querySelector('.arrival-station').textContent.trim();
        const departureTime = tripContainer.querySelector('.departureTime').textContent.trim();
        const arrivalTime = tripContainer.querySelector('.arrivalTime').textContent.trim();
        const price = tripContainer.querySelector('.price span').textContent.trim();
        const availableSeats = tripContainer.querySelector('.availableSeats span').textContent.trim();

        console.log("Giá trị status là:", status);

        document.getElementById('updateTripModal').classList.remove('hidden');

        document.getElementById('edittripId').value = tripId;
        document.getElementById('editrouteId').value = routeId;
        document.getElementById('editbusId').value = busId;
        document.getElementById('editdriverId').value = driverId;
        document.getElementById('editdepartureStation').value = departureStation;
        document.getElementById('editarrivalStation').value = arrivalStation;
        document.getElementById('editdepartureTime').value = departureTime;
        document.getElementById('editarrivalTime').value = arrivalTime;
        document.getElementById('editprice').value = price;
        document.getElementById('editavailableSeats').value = availableSeats;

        document.getElementById('edittripStatus').value = status;
    };
}

// Gọi hàm để khởi tạo
document.addEventListener('DOMContentLoaded', function() {
    editTripController();
});

document.getElementById("arrivalLocation").addEventListener("change", function() {
    const arrival = document.getElementById("arrivalLocation").value;
    document.getElementById("routeId").value = arrival;
});

//document.getElementById("driverName").addEventListener("change", function() {
//    const driver = document.getElementById("driverName").value;
//    document.getElementById("driverId").value = driver;
//});
//
//document.getElementById("license").addEventListener("change", function() {
//    const driver = document.getElementById("license").value;
//    document.getElementById("busId").value = driver;
//});
