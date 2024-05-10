const cartButton = document.querySelector('.cart');
const cartContent = document.querySelector('.cart-content');
const closeButton = document.querySelector('#close-button');
let isCartOpen = false; // rastrear el estado del contenido del carrito

// alternar la visibilidad del contenido del carrito
function toggleCartContent() {
    if (!isCartOpen) { // Si el carrito está cerrado, abrirlo
        
        cartContent.classList.add('show');
        cartContent.classList.remove('hide');
    

        isCartOpen = true;
    } else { // si el carrito esta abierto, cerrarlo
        cartContent.classList.add('hide');
        cartContent.classList.remove('show');
        isCartOpen = false;
    }
}

//controlar el evento click del boton del carrito
cartButton.addEventListener('click', toggleCartContent);

// controlar el evento click del boton de cerrar
closeButton.addEventListener('click', function() {
    cartContent.classList.remove('show');
    cartContent.classList.add('hide');
    isCartOpen = false;
});
