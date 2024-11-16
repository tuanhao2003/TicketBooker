// file: searchInvoices.js
async function searchInvoices() {
    const totalAmountInput = document.getElementById('totalAmount').value;
    const paymentStatusSelect = document.getElementById('paymentStatus').value;
    const paymentMethodSelect = document.getElementById('paymentMethod').value;

    // Tạo object request
    const requestDTO = {
        totalAmount: totalAmountInput ? parseInt(totalAmountInput) : null,
        paymentStatus: paymentStatusSelect || null,
        paymentMethod: paymentMethodSelect || null
    };

    try {
        // Gửi request tới API
        const response = await fetch('/api/invoices/search', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestDTO)
        });

        if (response.ok) {
            const responseDTO = await response.json();
            updateTable(responseDTO.listInvoices);
        } else {
            console.error('Error fetching data:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

function updateTable(invoices) {
    const tableBody = document.getElementById('invoiceTableBody');
    tableBody.innerHTML = ''; // Xóa nội dung cũ của bảng

    invoices.forEach(invoice => {
        const row = `
            <tr class="border-b border-gray-200 hover:bg-purple-100 transition-all duration-300 ease-in-out hover:shadow-md transition-transform hover:scale-105">
                <td class="py-3 px-6">${invoice.id}</td>
                <td class="py-3 px-6">${new Intl.NumberFormat().format(invoice.totalAmount)} ₫</td>
                <td class="py-3 px-6">
                    <span class="${invoice.paymentStatus === 'PAID' ? 'bg-green-200 rounded text-green-600 px-4 py-2 font-semibold' :
                        invoice.paymentStatus === 'PENDING' ? 'bg-yellow-200 text-yellow-600 px-4 py-2 rounded font-semibold' :
                        'bg-gray-200 text-gray-600 font-semibold rounded px-4 py-2'}">
                        ${invoice.paymentStatus}
                    </span>
                </td>
                <td class="py-3 px-6">${new Date(invoice.paymentTime).toLocaleString('vi-VN')}</td>
                <td class="py-3 px-6">${invoice.paymentMethod}</td>
            </tr>
        `;
        tableBody.insertAdjacentHTML('beforeend', row);
    });
}

document.addEventListener('DOMContentLoaded', () => {
    searchInvoices();
});
