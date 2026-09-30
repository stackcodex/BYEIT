document
    .getElementById("loginForm")
    .addEventListener("submit", function (event) {

        event.preventDefault();

        let email =
            document.getElementById("email").value;

        let password =
            document.getElementById("password").value;

        let message =
            document.getElementById("loginMessage");

        fetch("http://localhost:8081/api/auth/login", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                email: email,
                password: password
            })

        })

        .then(function (response) {
            return response.text();
        })

        .then(function (result) {

            message.innerText = result;

            if (result === "Login successful.") {

    message.style.color = "#5EE6A8";

    localStorage.setItem("byeItLoggedIn", "true");
    localStorage.setItem("byeItUserEmail", email);

    setTimeout(function () {
        window.location.href = "index.html";
    }, 800);

} else {

                message.style.color = "#FF6B7A";
            }

        })

        .catch(function () {

            message.innerText =
                "Unable to connect to server.";

            message.style.color = "#FF6B7A";

        });

    });