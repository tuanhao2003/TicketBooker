(
    function () {
        document.addEventListener("DOMContentLoaded", function () {
            const total = document.getElementById("grandTotal").innerText.replace(",", "").replace("đ", "");
            const fullName = document.querySelector(".apiField[name='fullname']").value();
            const phone = document.querySelector(".apiField[name='phone']").value();
            const email = document.querySelector(".apiField[name='email']").value();
            const descript =  Date.now().toString(36) + Math.random().toString(36).substring(2)
            console.log(total);

            fetch("http://localhost:8080/payment/zalo-payment", {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({appUser: fullName, amount: total, description: descript})
            }).then(
                respone => respone.json()
            ).then(
                data => {
                    if (data.returnCode === 1) {
                        fetch("http://localhost:8080/payment/zalo-payment-status", {
                            method: "POST",
                            headers: {"Content-Type": "application/json"},
                            body: JSON.stringify(data)
                        }).then(async response => {
                            await response.json()
                        }).then(
                            data => {
                                console.log(data.returnMessage)
                        }).catch(e => console.log(e));
                        window.location.href = data.returnUrl;
                    }
                }
            ).catch(
                e => console.log(e)
            );
        });
    }
)();

