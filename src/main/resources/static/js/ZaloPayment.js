(
    function () {
        document.addEventListener("DOMContentLoaded", function () {
            fetch("http://localhost:8080/payment/zalo-payment", {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({appUser: "hao", amount: 500000, description: "pickleball"})
            }).then(
                respone => respone.json()
            ).then(
                data => {
                    if(data.returnCode === 1){
                        fetch("http://localhost:8080/payment/zalo-payment-status", {
                            method: "POST",
                            headers: {"Content-Type": "application/json"},
                            body: JSON.stringify(data)
                        }).then( async response => {
                                window.location.href = data.returnUrl;
                                await response.json()
                            }
                        ).then(
                            data => {console.log(data.returnMessage)}
                        ).catch(e => console.log(e));
                    }
                }
            ).catch(
                e => console.log(e)
            );
        });
    }
)();

