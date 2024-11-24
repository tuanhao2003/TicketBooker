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

    // Initial update
    updateDateContainerLayout();
});