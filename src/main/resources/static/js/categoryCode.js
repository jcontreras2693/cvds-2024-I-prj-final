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


// Función para formatear el texto del nombre del servicio
function formatServiceName(name) {
    // Convertir a minúsculas y reemplazar espacios con guiones
    return name.toLowerCase().replace(/\s+/g, '-').normalize("NFD").replace(/[\u0300-\u036f]/g, "");
}

// Obtener todos los elementos de imagen y actualizar sus rutas
document.querySelectorAll('[id^="img-"]').forEach(function(img) {
    var serviceName = img.getAttribute('id').substring(4); // Obtener el nombre del servicio
    var formattedServiceName = formatServiceName(serviceName); // Formatear el nombre del servicio
    var categoryName = img.getAttribute('data-category'); // Obtener el nombre de la categoría desde el atributo data-
    var formatCategoryName= formatServiceName(categoryName);
    img.setAttribute('src', '/static/images/servicios/' + formattedServiceName + '.png'); // Actualizar la ruta de la imagen
});

