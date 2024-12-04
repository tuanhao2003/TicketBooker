    document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector('form');
    const oldPasswordInput = document.querySelector('input[name="oldPassword"]');
    const newPasswordInput = document.querySelector('input[name="newPassword"]');
    const confirmPasswordInput = document.querySelector('input[name="confirmPassword"]');
    const statusMessage = document.createElement('p'); // Element to display status messages

    // Append statusMessage to the form (can be styled as needed)
    form.appendChild(statusMessage);

    form.addEventListener('submit', async function (event) {
    event.preventDefault(); // Prevent default form submission

    // Reset any previous status messages
    resetStatusMessage();

    // Validate inputs
    if (!validateForm()) {
    return;
}

    // Prepare data to send
    const formData = new URLSearchParams();
    formData.append('oldPassword', document.querySelector('input[name="oldPassword"]').value);
    formData.append('newPassword', document.querySelector('input[name="newPassword"]').value);

        try {
    // Send request using Fetch API
    const response = await fetch('/profile/change-password', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
},
    body: formData.toString()
});

    // Handle response
    if (response.ok) {
    displaySuccess("Password updated successfully!");
    form.reset();
} else if (response.status === 400) {
    const errorData = await response.text();
    displayError(`Error: ${errorData}`);
} else if (response.status === 401) {
    displayError("You are not authorized to change the password.");
} else {
    displayError("An unexpected error occurred. Please try again later.");
}
} catch (error) {
    // Handle network or other errors
    displayError("Failed to connect to the server. Please check your connection.");
}
});

    // Function to validate form inputs
    function validateForm() {
    resetStatusMessage();

    if (!oldPasswordInput.value.trim()) {
    displayError("Old password is required.");
    return false;
}

    if (!validatePasswordStrength(newPasswordInput.value)) {
    displayError("New password must have at least 8 characters, including uppercase, lowercase, numbers, and special characters.");
    return false;
}

    if (newPasswordInput.value !== confirmPasswordInput.value) {
    displayError("Passwords do not match.");
    return false;
}

    return true;
}

    // Function to validate password strength
    function validatePasswordStrength(password) {
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return passwordRegex.test(password);
}

    // Function to display error messages
    function displayError(message) {
    statusMessage.textContent = message;
    statusMessage.className = 'text-red-500 text-sm mt-2';
}

    // Function to display success messages
    function displaySuccess(message) {
    statusMessage.textContent = message;
    statusMessage.className = 'text-green-500 text-sm mt-2';
}

    // Function to reset status messages
    function resetStatusMessage() {
    statusMessage.textContent = '';
    statusMessage.className = '';
}
});
