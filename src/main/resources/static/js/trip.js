(function () {
    document.addEventListener("DOMContentLoaded", function () {
        // start
        initDateRestriction();
        deleteTripController();
        loadRouteController();
        formatDepartureTimeController();
        addTripController();
        editTripController();

        function initDateRestriction() {
            var today = new Date().toISOString().slice(0, 16);
            document.getElementById("departureTime").setAttribute("min", today);
        }

        function deleteTripController() {
            window.confirmDeleteTrip = function(button) {
                const tripContainer = button.closest('.trip-container');
                const tripIdElement = tripContainer.querySelector('.trip-id');
                const tripId = parseInt(tripIdElement.textContent);

                Swal.fire({
                    title: 'Xác nhận',
                    text: `Bạn có chắc chắn muốn xóa chuyến xe với ID ${tripId} không?`,
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: 'Có',
                    cancelButtonText: 'Không'
                }).then((result) => {
                    if (result.isConfirmed) {
                        deleteTrip(tripId, tripContainer);
                    }
                });
            }

            function deleteTrip(tripId, tripContainer) {
                fetch('/admin/trips', {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ tripId: tripId })
                })
                .then(response => {
                    if (response.ok) {
                        Swal.fire({
                            title: 'Đã xóa!',
                            text: `Chuyến xe với ID ${tripId} đã được xóa thành công.`,
                            icon: 'success',
                            confirmButtonText: 'OK'
                        });
                        tripContainer.remove();
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
        }

        function loadRouteController() {
            let routes = [];

            window.openAddTripModal = function() {
                document.getElementById('addTripModal').classList.remove('hidden');
            }
        }

        function formatDepartureTimeController() {
            const departureTimeElements = document.querySelectorAll('.departureTime');

            departureTimeElements.forEach((departureTimeElement) => {
                const departureTime = departureTimeElement.textContent.trim();

                console.log("Departure Time from HTML:", departureTime);

                if (departureTime) {
                    const [date, time] = departureTime.split('T');
                    const [year, month, day] = date.split('-');
                    const formattedDate = `${day}/${month}/${year}`;

                    const tripContainer = departureTimeElement.closest('.trip-container');
                    const departureDateElement = tripContainer.querySelector('#departureDate');
                    const departureHourElement = tripContainer.querySelector('#departureHour');

                    departureDateElement.textContent = `${formattedDate}`;
                    departureHourElement.textContent = ` ${time}`;
                } else {
                    console.warn('departureTime không có giá trị.');
                }
            });
        }

        function addTripController() {
            window.submitAddTripForm = function() {
                const routeId = document.getElementById('routeId').value;
                const busId = document.getElementById('busId').value;
                const driverId = document.getElementById('driverId').value;
                const departureStation = document.getElementById('departureStation').value;
                const arrivalStation = document.getElementById('arrivalStation').value;
                const departureTime = document.getElementById('departureTime').value;
                const price = document.getElementById('price').value;
                const availableSeats = document.getElementById('availableSeats').value;

                if (!routeId) {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Chưa chọn tuyến đường!',
                        text: 'Vui lòng chọn tuyến đường trước khi tạo chuyến xe.',
                        confirmButtonText: 'OK'
                    });
                } else if (!busId || !driverId || !departureStation || !arrivalStation || !departureTime || !price || !availableSeats) {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Thông tin không đầy đủ!',
                        text: 'Vui lòng điền tất cả các trường trước khi tạo chuyến xe.',
                        confirmButtonText: 'OK'
                    });
                } else {
                    Swal.fire({
                        icon: 'success',
                        title: 'Tạo chuyến xe thành công!',
                        text: 'Chuyến xe đã được tạo thành công.',
                        confirmButtonText: 'OK'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            document.getElementById('addTripForm').submit();
                        }
                    });
                }
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
                const departureStation = tripContainer.querySelector('.departure-location span').textContent.trim();
                const arrivalLocation = tripContainer.querySelector('.arrival-location').textContent.trim();
                const arrivalStation = tripContainer.querySelector('.arrival-location span').textContent.trim();
                const departureTime = tripContainer.querySelector('.departureTime').textContent.trim();
                const arrivalTime = tripContainer.querySelector('.arrivalTime').textContent.trim();
                const price = tripContainer.querySelector('.price span').textContent.trim();
                const availableSeats = tripContainer.querySelector('.availableSeats span').textContent.trim();

                console.log("Giá trị status là:", status);

                document.getElementById('updateTripModal').classList.remove('hidden');

                document.getElementById('edittripId').value = tripId;
                document.getElementById('editrouteId').value = routeId;
                document.getElementById('peditrouteId').textContent = routeId;
                document.getElementById('editbusId').value = busId;
                document.getElementById('editdriverId').value = driverId;
                document.getElementById('editdepartureStation').value = departureStation;
                document.getElementById('editarrivalStation').value = arrivalStation;
                document.getElementById('editdepartureTime').value = departureTime;
                document.getElementById('editarrivalTime').value = arrivalTime;
                document.getElementById('editprice').value = price;
                document.getElementById('editavailableSeats').value = availableSeats;

                document.getElementById('edittripStatus').value = status;
            }
        }
    });
})();