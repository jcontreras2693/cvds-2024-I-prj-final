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
    // generar un ID único para el rectángulo
    var idUnico = 'brand-' + alt.toLowerCase();

    // crear el elemento de rectángulo
    var rectangle = document.createElement('div');
    rectangle.className = 'rectanglee';
    rectangle.id = idUnico; // Asignar el ID único al rectángulo

    // crear el elemento de imagen
    var img = document.createElement('img');
    img.src = src;
    img.alt = alt;

    // agregar la imagen al rectángulo
    rectangle.appendChild(img);

    // obtener el contenedor de rectángulos
    var contenedor = document.getElementById('rectangle-grid');

    // agregar el rectángulo al contenedor
    contenedor.appendChild(rectangle);

    img.addEventListener('click', function() { // evento de clic a la imagen
        // remover la clase de todos los rectángulos
        var rectangles = document.querySelectorAll('.rectanglee');
        rectangles.forEach(function(rectangle) {
            rectangle.classList.remove('active');
        });
        
        rectangle.classList.add('active'); // agregar la clase al rectángulo actual
    });
}

// agregar todas las imágenes del array al contenedor
for (var i = 0; i < imagenes.length; i++) {
    agregarImagen(imagenes[i].src, imagenes[i].alt);
}