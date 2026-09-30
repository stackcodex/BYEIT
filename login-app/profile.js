// =========================================
// LOAD USER INFORMATION
// =========================================

let userEmail =
    localStorage.getItem("byeItUserEmail");


let savedName =
    localStorage.getItem("byeItUserName");


let savedPhone =
    localStorage.getItem("byeItUserPhone");


let savedCity =
    localStorage.getItem("byeItUserCity");


let savedAddress =
    localStorage.getItem("byeItUserAddress");


// =========================================
// DISPLAY USER
// =========================================

if (userEmail) {

    document.getElementById("email").value =
        userEmail;

    document.getElementById("profileEmail").innerText =
        userEmail;

}


if (savedName) {

    document.getElementById("fullName").value =
        savedName;

    document.getElementById("profileName").innerText =
        savedName;

    document.getElementById("avatarLetter").innerText =
        savedName.charAt(0).toUpperCase();

}


// =========================================
// LOAD OTHER DETAILS
// =========================================

if (savedPhone) {

    document.getElementById("phone").value =
        savedPhone;

}


if (savedCity) {

    document.getElementById("city").value =
        savedCity;

}


if (savedAddress) {

    document.getElementById("address").value =
        savedAddress;

}


// =========================================
// SAVE PROFILE
// =========================================

document
    .getElementById("profileForm")
    .addEventListener("submit", function(event) {

        event.preventDefault();


        let name =
            document.getElementById("fullName").value;

        let phone =
            document.getElementById("phone").value;

        let city =
            document.getElementById("city").value;

        let address =
            document.getElementById("address").value;


        localStorage.setItem(
            "byeItUserName",
            name
        );


        localStorage.setItem(
            "byeItUserPhone",
            phone
        );


        localStorage.setItem(
            "byeItUserCity",
            city
        );


        localStorage.setItem(
            "byeItUserAddress",
            address
        );


        document.getElementById("profileName").innerText =
            name || "User";


        document.getElementById("avatarLetter").innerText =
            name
                ? name.charAt(0).toUpperCase()
                : "U";


        let message =
            document.getElementById("profileMessage");


        message.innerText =
            "Profile updated successfully.";

        message.style.color =
            "#5EE6A8";


    });


// =========================================
// LOGOUT
// =========================================

function logout() {

    localStorage.removeItem("byeItLoggedIn");

    localStorage.removeItem("byeItUserEmail");

    window.location.href =
        "login.html";

}