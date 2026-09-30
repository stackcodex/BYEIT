let cart = [];


// ================================
// LOAD CART
// ================================

let savedCart =
    localStorage.getItem("byeItCart");


if (savedCart) {

    cart = JSON.parse(savedCart);

}


// ================================
// DISPLAY ORDER
// ================================

function displayCheckout() {

    let checkoutItems =
        document.getElementById("checkoutItems");

    let subtotal = 0;


    if (cart.length === 0) {

        checkoutItems.innerHTML = `

            <p style="
                color: #C9BED3;
                font-size: 13px;
            ">
                Your cart is empty.
            </p>

        `;

        return;

    }


    checkoutItems.innerHTML = "";


    cart.forEach(function(item) {

        let itemTotal =
            item.price * item.quantity;


        subtotal =
            subtotal + itemTotal;


        checkoutItems.innerHTML += `

            <div class="checkout-item">

                <div class="checkout-item-info">

                    <div class="checkout-item-name">
                        ${item.name}
                    </div>

                    <div class="checkout-item-quantity">
                        Quantity: ${item.quantity}
                    </div>

                </div>

                <div class="checkout-item-price">
                    ₹${itemTotal}
                </div>

            </div>

        `;

    });


    document.getElementById("checkoutSubtotal").innerText =
        "₹" + subtotal;


    document.getElementById("checkoutTotal").innerText =
        "₹" + subtotal;

}


// ================================
// PLACE ORDER
// ================================

document
    .getElementById("checkoutForm")
    .addEventListener("submit", function(event) {

        event.preventDefault();


        let message =
            document.getElementById("checkoutMessage");


        if (cart.length === 0) {

            message.innerText =
                "Your cart is empty.";

            message.style.color =
                "#FF6B7A";

            return;

        }


        message.innerText =
            "Order placed successfully!";

        message.style.color =
            "#5EE6A8";


        localStorage.removeItem("byeItCart");


        setTimeout(function() {

            window.location.href =
                "index.html";

        }, 1200);

    });


// ================================
// START
// ================================

displayCheckout();