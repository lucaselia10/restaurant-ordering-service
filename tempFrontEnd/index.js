setup();

function setup() {
    updateCartTotal(); //

    let menuQuantityInputs = document.getElementsByClassName('qty-input')
    for (let i = 0; i < menuQuantityInputs.length; i++) {
        let input = menuQuantityInputs[i]
        input.addEventListener('change', quantityChanged);
    }

    let addToCartButtons = document.getElementsByClassName('add-btn');
    for (let i = 0; i < addToCartButtons.length; i++) {
        let button = addToCartButtons[i];
        button.addEventListener('click', addToCartClick)
    }

    let cartQuantitySelect = document.getElementsByClassName('cart-quantity');
    for (let i = 0; i < cartQuantitySelect.length; i++) {
        let selection = cartQuantitySelect[i]

        selection.addEventListener('change', function(event) {
            if(selection.selectedIndex == "0"){
                let selectionMade = event.target;
                selectionMade.parentElement.remove();
            }

            updateCartTotal();
        })
    }

}
function quantityAdd(event) {
    //TODO incremenet menuQuantity
}
function quantitySub(event) {
    //TODO decrement menuQuantity
}

function quantityChanged(event) {
    var input = event.target;
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 1;
    }

    updateCartTotal();
}

function addToCartClick(event) {
    let button = event.target;
    let hoveritem = button.parentElement;
    let icon = hoveritem.getElementsByClassName('item-icon')[0].innerText;
    let name = hoveritem.getElementsByClassName('item-name')[0].innerText;
    let price = hoveritem.getElementsByClassName('item-price')[0].innerText;
    let qty = hoveritem.getElementsByClassName('qty-input')[0].value;
    addItemToCart(icon, name, price, qty);
    updateCartTotal();
}

function addItemToCart(icon, name, price, qty){
    let newItem = document.createElement('div');
    let hoveritems = document.getElementsByClassName('cart-item')[0];
    let hoveritemsNames = hoveritems.getElementsByClassName('item-name');
    let hoveritemsQty = hoveritems.getElementsByClassName('cart-quantity');
    let adjustedPrice = price * qty;

    for (var i = 0; i < hoveritemsNames.length; i++) {
        if (hoveritemsNames[i].innerText == name) {
            //TODO - refactor for easier qty++
            return;
        }
    }
    let newItemContents = `
        <div class="hover-item cart-item">
            <span class="item-icon">${icon}</span>
            <span class="item-name">${name}</span>
            &lt;&gt;
            <span class="item-price" data-price="${price}">${adjustedPrice}</span>
            <select class="cart-quantity">
                <option value="remove">remove</option>
                <option value="${qty}" selected="selected">${qty}</option>
                <option value="1" >1</option>
                <option value="2">2</option>
                <option value="20">20</option>
                <option value="99">99</option>
            </select>
        </div>`

    newItem.innerHTML = newItemContents;
    hoveritems.append(newItem);
    let selection = newItem.getElementsByClassName('cart-quantity')[0];
    selection.addEventListener('change',
                                function(event) {
                                   if(selection.selectedIndex == "0"){
                                       let selectionMade = event.target;
                                       selectionMade.parentElement.remove();
                                   }
                                   updateCartTotal();
                                })

}

function updateCartTotal() {
    let cartContainer = document.getElementsByClassName('cart-section')[0];
    let cartItems = cartContainer.getElementsByClassName('hover-item');
    let checkoutBtn = document.getElementById('checkout-button');
    let total = 0;

    for (let i = 0; i < cartItems.length; i++) {
        let cartItem = cartItems[i];
        let cartItemPrice = cartItem.getElementsByClassName('item-price')[0];
        let cartItemQty = cartItem.getElementsByClassName('cart-quantity')[0];
        let price = parseFloat(cartItemPrice.dataset.price.replace('$', ''));
        let quantity = cartItemQty.options[cartItemQty.selectedIndex].text;
        cartItemPrice.textContent = "$" + (price * quantity).toFixed(2);
        total = total + (price * quantity);
    }

    checkoutBtn.textContent = "Checkout $" + total.toFixed(2);
}

function checkoutClicked() {
    //TODO send JSON then while cart has items, removeChild
}