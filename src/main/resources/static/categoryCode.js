

// boton del carrito, el contenido del carrito y el boton de cerrar
const cartButton = document.querySelector('.cart');
const cartContent = document.querySelector('.cart-content');
const closeButton = document.querySelector('#close-button');

cartButton.addEventListener('click', function() {
    cartContent.classList.toggle('show');
});

closeButton.addEventListener('click', function() {
    cartContent.classList.add('hide');
    setTimeout(() => {
        cartContent.classList.remove('show');
    }, 500);
});

