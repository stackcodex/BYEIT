let cart = [];


let savedCart =
    localStorage.getItem("byeItCart");


if (savedCart) {

    cart = JSON.parse(savedCart);

}


function displayCart() {

    let cartItems =
        document.getElementById("cartItems");

    let subtotal = 0;

    let itemCount = 0;


    if (cart.length === 0) {

        cartItems.innerHTML = `

            <div class="empty-cart">

                <div class="empty-cart-icon">
                    🛒
                </div>

                <h3>
                    Your cart is empty
                </h3>

                <p>
                    Looks like you haven't added
                    anything yet.
                </p>

                <a
                    href="index.html#products"
                    class="primary-button"
                >
                    Start Shopping
                </a>

            </div>

        `;


        document.getElementById("cartSubtotal").innerText =
            "₹0";

        document.getElementById("cartTotal").innerText =
            "₹0";

        document.getElementById("cartItemCount").innerText =
            "0 items";

        return;

    }


    cartItems.innerHTML = "";


    cart.forEach(function(item, index) {

        let itemTotal =
            item.price * item.quantity;


        subtotal = subtotal + itemTotal;

        itemCount = itemCount + item.quantity;


        cartItems.innerHTML += `

            <div class="cart-item">

                <div class="cart-item-image">

                    <div>
                        🛍️
                    </div>

                </div>


                <div class="cart-item-info">

                    <div class="cart-item-name">
                        ${item.name}
                    </div>

                    <div class="cart-item-category">
                        BYEIT Product
                    </div>

                    <div class="cart-item-price">
                        ₹${item.price}
                    </div>

                </div>


                <div class="cart-controls">

                    <div class="quantity-control">

                        <button
                            onclick="decreaseQuantity(${index})"
                        >
                            −
                        </button>

                        <span>
                            ${item.quantity}
                        </span>

                        <button
                            onclick="increaseQuantity(${index})"
                        >
                            +
                        </button>

                    </div>


                    <button
                        class="delete-button"
                        onclick="removeFromCart(${index})"
                        title="Remove item"
                    >
                        🗑
                    </button>

                </div>

            </div>

        `;

    });


    document.getElementById("cartSubtotal").innerText =
        "₹" + subtotal;


    document.getElementById("cartTotal").innerText =
        "₹" + subtotal;


    document.getElementById("cartItemCount").innerText =
        itemCount + (itemCount === 1 ? " item" : " items");

}


function increaseQuantity(index) {

    cart[index].quantity++;


    saveCart();


    displayCart();

}


function decreaseQuantity(index) {

    if (cart[index].quantity > 1) {

        cart[index].quantity--;

    } else {

        cart.splice(index, 1);

    }


    saveCart();


    displayCart();

}


function removeFromCart(index) {

    cart.splice(index, 1);


    saveCart();


    displayCart();

}


function saveCart() {

    localStorage.setItem(
        "byeItCart",
        JSON.stringify(cart)
    );

}


function checkout() {

    if (cart.length === 0) {

        alert("Your cart is empty.");

        return;

    }


    alert("Checkout page will be added next.");

}


displayCart();