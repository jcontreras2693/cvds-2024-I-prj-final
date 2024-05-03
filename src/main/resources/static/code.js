// array con los nombres de las imágenes y sus textos alternativos
var marcas = [
    { src: 'images/marcas/audi.png', alt: 'Audi' },
    { src: 'images/marcas/bmw.png', alt: 'BMW' },
    { src: 'images/marcas/chevrolet.png', alt: 'Chevrolet' },
    { src: 'images/marcas/byd.png', alt: 'BYD' },
    { src: 'images/marcas/chery.png', alt: 'Chery' },
    { src: 'images/marcas/citroen.png', alt: 'Citroen' },
    { src: 'images/marcas/daewoo.png', alt: 'Daewoo' },
    { src: 'images/marcas/dodge.png', alt: 'Dodge' },
    { src: 'images/marcas/faw.png', alt: 'FAW' },
    { src: 'images/marcas/fiat.png', alt: 'Fiat' },
    { src: 'images/marcas/ford.png', alt: 'Ford' },
    { src: 'images/marcas/geely.png', alt: 'Geely' },
    { src: 'images/marcas/hino.png', alt: 'Hino' },
    { src: 'images/marcas/honda.png', alt: 'Honda' },
    { src: 'images/marcas/hyundai.png', alt: 'Hyundai' }
];

var audi = [
    { src: 'images/audi/A1.png', alt: 'a1' },
    { src: 'images/audi/A1_TDI.png', alt: 'a1_tdi' },
    { src: 'images/audi/A3.png', alt: 'a3' },
    { src: 'images/audi/A3_COUPE.png', alt: 'a3_coupe' },
    { src: 'images/audi/A4.png', alt: 'a4' },
    { src: 'images/audi/A4_TFSI.png', alt: 'a4_tfsi' },
    { src: 'images/audi/A5.png', alt: 'a5' },
    { src: 'images/audi/Q2.png', alt: 'q2' },
    { src: 'images/audi/Q3.png', alt: 'q3' },
    { src: 'images/audi/Q3_TFSI.png', alt: 'q3_tfsi' },
    { src: 'images/audi/Q5.png', alt: 'q5' },
    { src: 'images/audi/Q7.png', alt: 'q7' },
    { src: 'images/audi/Q7_TFSI.png', alt: 'q7_tfsi' },
    { src: 'images/audi/S3_Performance.png', alt: 's3_performance' }
];


var lastBrandClicked = null;

// agregar una imagen al contenedor con un ID único y evento de clic
function agregarImagen(src, alt) {
    document.getElementById('marca').classList.add('active');

    var idUnique = alt.toLowerCase(); // generar ID
    
    var rectangle = crearRectangulo(idUnique); // crear rectángulo
    var img = crearImagen(src, alt); // crear imagen
    
    rectangle.appendChild(img); // agregar imagen al rectángulo
    
    var contenedor = document.getElementById('rectangle-grid'); // obtener contenedor
    contenedor.appendChild(rectangle); // agregar rectángulo al contenedor
    
    agregarEventoClic(img, idUnique, rectangle); // agregar evento de clic a la imagen
}

// crear un rectángulo
function crearRectangulo(id) {
    var rectangle = document.createElement('div');
    rectangle.className = 'rectanglee';
    rectangle.id = id;
    return rectangle;
}

// crear una imagen
function crearImagen(src, alt) {
    var img = document.createElement('img');
    img.src = src;
    img.alt = alt;
    return img;
}

// evento de clic a una imagen
function agregarEventoClic(img, id, rectangle) {
    img.addEventListener('click', function() {
        var rectangles = document.querySelectorAll('.rectanglee');
        rectangles.forEach(function(rect) {
            rect.classList.remove('active');
        });
        rectangle.classList.add('active');
        lastBrandClicked = seleccionarMarca();
    });
}

// cargar las imágenes al iniciar la página
window.onload = function() {
    for (var i = 0; i < marcas.length; i++) {
        agregarImagen(marcas[i].src, marcas[i].alt);
    }
}

function seleccionarMarca() {
    document.getElementById('marca').classList.remove('active');
    document.getElementById('modelo').classList.add('active');
}



