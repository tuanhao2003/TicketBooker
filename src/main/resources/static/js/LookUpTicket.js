tailwind.config = {
    theme: {
        extend: {
            colors: {
                primary: '#6B46C1',
                secondary: '#9F7AEA',
                accent: '#D6BCFA',
            }
        }
    }
}
function fetchTicketInfo() {
    // Simulate API call
    document.getElementById("searchSection").classList.add("hidden");
    document.getElementById("ticketInfo").classList.remove("hidden");
}

function resetForm() {
    document.getElementById("ticketId").value = "";
    document.getElementById("ticketInfo").classList.add("hidden");
    document.getElementById("searchSection").classList.remove("hidden");
}