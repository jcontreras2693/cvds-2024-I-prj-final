// array con los nombres de las imágenes y sus textos alternativos
var imagenes = [
    { src: 'images/audi.png', alt: 'Audi' },
    { src: 'images/bmw.png', alt: 'BMW' },
    { src: 'images/chevrolet.png', alt: 'Chevrolet' },
    { src: 'images/byd.png', alt: 'BYD' },
    { src: 'images/chery.png', alt: 'Chery' },
    { src: 'images/citroen.png', alt: 'Citroen' },
    { src: 'images/daewoo.png', alt: 'Daewoo' },
    { src: 'images/dodge.png', alt: 'Dodge' },
    { src: 'images/faw.png', alt: 'FAW' },
    { src: 'images/fiat.png', alt: 'Fiat' },
    { src: 'images/ford.png', alt: 'Ford' },
    { src: 'images/geely.png', alt: 'Geely' },
    { src: 'images/hino.png', alt: 'Hino' },
    { src: 'images/honda.png', alt: 'Honda' },
    { src: 'images/hyundai.png', alt: 'Hyundai' }
];

// función para agregar una imagen al contenedor con un ID único y evento de clic
function agregarImagen(src, alt) {
    // generar un ID único para la imagen
    var idUnico = 'imagen-' + alt.toLowerCase();

    // crear el elemento de imagen
    var img = document.createElement('img');
    img.src = src;
    img.alt = alt;
    img.id = idUnico; // Asignar el ID único a la imagen

    // obtener el contenedor de imágenes
    var contenedor = document.getElementById('image-grid');

    // agregar la imagen al contenedor
    contenedor.appendChild(img);

    // agregar evento de clic a la imagen
    img.addEventListener('click', function() {
        alert('¡Hiciste clic en la imagen de ' + alt + '!');
    });
}

// agregar todas las imágenes del array al contenedor
for (var i = 0; i < imagenes.length; i++) {
    agregarImagen(imagenes[i].src, imagenes[i].alt);
}

