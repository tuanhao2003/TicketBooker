document.addEventListener('DOMContentLoaded', function() {
    const tripTypeRadios = document.querySelectorAll('input[name="tripType"]');
    const dateContainer = document.getElementById('dateContainer');
    const returnDateContainer = document.getElementById('returnDateContainer');

    function updateDateContainerLayout() {
        const selectedTripType = document.querySelector('input[name="tripType"]:checked').value;
        if (selectedTripType === 'oneWay') {
            returnDateContainer.classList.add('hidden');
        } else {
            returnDateContainer.classList.remove('hidden');
        }
    }

    tripTypeRadios.forEach(radio => {
        radio.addEventListener('change', updateDateContainerLayout);
    });

    updateDateContainerLayout();
});

document.addEventListener('DOMContentLoaded', function() {
  const seatButtons = document.querySelectorAll('button:nth-child(1)');

  seatButtons.forEach(button => {
    button.addEventListener('click', function() {
      const card = this.closest('.bg-white');
      const seatSelection = card.querySelector('.seat-selection');

      if (seatSelection.classList.contains('hidden')) {
        // Nếu đang ẩn, hiển thị giao diện chọn ghế
        seatSelection.classList.remove('hidden');
        seatSelection.innerHTML = `
          <div class="mt-4 p-4 border border-gray-200 rounded-lg">
            <h3 class="text-lg font-semibold mb-2">Chọn ghế</h3>
            <div class="grid grid-cols-3 gap-2">
              <div class="text-center">
                <div class="w-8 h-8 bg-gray-200 rounded mx-auto mb-1"></div>
                <span class="text-xs">Đã bán</span>
              </div>
              <div class="text-center">
                <div class="w-8 h-8 bg-blue-200 rounded mx-auto mb-1"></div>
                <span class="text-xs">Còn trống</span>
              </div>
              <div class="text-center">
                <div class="w-8 h-8 bg-orange-200 rounded mx-auto mb-1"></div>
                <span class="text-xs">Đang chọn</span>
              </div>
            </div>
            <div class="grid grid-cols-2 gap-2">
                <div class="mt-4 text-center">
                  <h4 class="font-medium mb-2">Tầng dưới</h4>
                  <div class="grid grid-cols-3 gap-2 justify-center">
                    ${generateSeats('A', 17)}
                  </div>
                </div>
                <div class="mt-4 text-center">
                  <h4 class="font-medium mb-2">Tầng trên</h4>
                  <div class="grid grid-cols-3 gap-2 justify-center">
                    ${generateSeats('B', 17)}
                  </div>
                </div>
            </div>
          </div>
        `;
      } else {
        // Nếu đang hiển thị, ẩn giao diện chọn ghế
        seatSelection.classList.add('hidden');
      }
    });
  });

  function generateSeats(prefix, count) {
    let seats = '';
    for (let i = 1; i <= count; i++) {
      const seatNumber = i.toString().padStart(2, '0');
      seats += `<button class="w-8 h-8 bg-blue-200 rounded text-xs mx-auto">${prefix}${seatNumber}</button>`;
    }
    return seats;
  }
});