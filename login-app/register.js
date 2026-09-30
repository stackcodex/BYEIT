document
    .getElementById("registerForm")
    .addEventListener("submit", function (event) {

        event.preventDefault();

        let fullName =
            document.getElementById("fullName").value;

        let email =
            document.getElementById("email").value;

        let password =
            document.getElementById("password").value;

        let confirmPassword =
            document.getElementById("confirmPassword").value;

        let message =
            document.getElementById("registerMessage");

        if (password !== confirmPassword) {

            message.innerText =
                "Passwords do not match.";

            message.style.color = "#FF6B7A";

            return;
        }

        fetch("http://localhost:8081/api/auth/register", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                fullName: fullName,
                email: email,
                password: password
            })

        })

        .then(function (response) {
            return response.text();
        })

        .then(function (result) {

            message.innerText = result;

            if (result === "Registration successful.") {

                message.style.color = "#5EE6A8";

                document
                    .getElementById("registerForm")
                    .reset();

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