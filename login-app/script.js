let cart = [];

let savedCart =
    localStorage.getItem("byeItCart");


if (savedCart) {

    cart = JSON.parse(savedCart);

}
// ================================
// ADD PRODUCT TO CART
// ================================

function addToCart(name, price) {

    let existingItem = cart.find(function(item) {

        return item.name === name;

    });


    if (existingItem) {

        existingItem.quantity++;

    } else {

        cart.push({

            name: name,
            price: price,
            quantity: 1

        });

    }


    localStorage.setItem(
        "byeItCart",
        JSON.stringify(cart)
    );


    updateCart();


    document.getElementById("cartCount").innerText =
        getCartItemCount();

}


// ================================
// GET TOTAL CART QUANTITY
// ================================

function getCartItemCount() {

    let count = 0;


    cart.forEach(function(item) {

        count = count + item.quantity;

    });


    return count;

}


// ================================
// UPDATE CART
// ================================

function updateCart() {

    let cartItems =
        document.getElementById("cartItems");

    let total = 0;


    // CART EMPTY

    if (cart.length === 0) {

        cartItems.innerHTML = `
            <p class="empty-cart">
                Your cart is empty.
            </p>
        `;


        document.getElementById("cartTotal").innerText =
            "₹0";


        return;

    }


    cartItems.innerHTML = "";


    // DISPLAY CART ITEMS

    cart.forEach(function(item, index) {

        let itemTotal =
            item.price * item.quantity;


        total = total + itemTotal;


        cartItems.innerHTML += `

            <div class="cart-item">

                <div class="cart-item-info">

                    <strong>
                        ${item.name}
                    </strong>

                    <span>
                        ₹${item.price} each
                    </span>

                </div>


                <div class="cart-item-actions">

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


                    <button
                        class="remove-button"
                        onclick="removeFromCart(${index})"
                        title="Remove item"
                    >
                        🗑
                    </button>

                </div>


                <strong class="cart-item-price">
                    ₹${itemTotal}
                </strong>

            </div>

        `;

    });


    document.getElementById("cartTotal").innerText =
        "₹" + total;

}


// ================================
// INCREASE QUANTITY
// ================================

function increaseQuantity(index) {

    cart[index].quantity++;


    localStorage.setItem(
        "byeItCart",
        JSON.stringify(cart)
    );


    updateCart();


    document.getElementById("cartCount").innerText =
        getCartItemCount();

}


// ================================
// DECREASE QUANTITY
// ================================

function decreaseQuantity(index) {

    if (cart[index].quantity > 1) {

        cart[index].quantity--;

    } else {

        cart.splice(index, 1);

    }


    localStorage.setItem(
        "byeItCart",
        JSON.stringify(cart)
    );


    updateCart();


    document.getElementById("cartCount").innerText =
        getCartItemCount();

}


// ================================
// REMOVE PRODUCT
// ================================

function removeFromCart(index) {

    cart.splice(index, 1);


    localStorage.setItem(
        "byeItCart",
        JSON.stringify(cart)
    );


    updateCart();


    document.getElementById("cartCount").innerText =
        getCartItemCount();

}


// ================================
// OPEN CART
// ================================

function openCart() {

    document
        .getElementById("cartOverlay")
        .classList.add("show");

}


// ================================
// CLOSE CART
// ================================

function closeCart() {

    document
        .getElementById("cartOverlay")
        .classList.remove("show");

}


// ================================
// CHECKOUT
// ================================

function checkout() {

    if (cart.length === 0) {

        alert("Your cart is empty.");

        return;

    }


    alert("Checkout feature will be added soon.");

}


// ================================
// FILTER PRODUCTS
// ================================

function filterProducts(category, button) {

    let products =
        document.querySelectorAll(".product-card");


    let filters =
        document.querySelectorAll(".filter");


    filters.forEach(function(filter) {

        filter.classList.remove("active");

    });


    button.classList.add("active");


    products.forEach(function(product) {

        if (
            category === "all" ||
            product.dataset.category === category
        ) {

            product.style.display = "block";

        } else {

            product.style.display = "none";

        }

    });

}


// ================================
// SCROLL TO PRODUCTS
// ================================

function scrollToProducts() {

    document
        .getElementById("products")
        .scrollIntoView({

            behavior: "smooth"

        });

}

// ================================
// PROFILE BUTTON
// ================================

function openProfile() {

    let loggedIn =
        localStorage.getItem("byeItLoggedIn");

    if (loggedIn === "true") {

        window.location.href =
            "profile.html";

    } else {

        window.location.href =
            "login.html";

    }

}


// ================================
// UPDATE PROFILE BUTTON
// ================================

let profileButton =
    document.getElementById("profileButton");


if (profileButton) {

    let loggedIn =
        localStorage.getItem("byeItLoggedIn");


    if (loggedIn === "true") {

        profileButton.innerText =
            "Profile";

    }

}