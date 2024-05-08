// array de marcas con los nombres de las imágenes y sus textos alternativos
var brands = [
    { src: 'images/brands/audi.png', alt: 'Audi' },
    { src: 'images/brands/bmw.png', alt: 'BMW' },
    { src: 'images/brands/chevrolet.png', alt: 'Chevrolet' },
    { src: 'images/brands/byd.png', alt: 'BYD' },
    { src: 'images/brands/chery.png', alt: 'Chery' },
    { src: 'images/brands/citroen.png', alt: 'Citroen' },
    { src: 'images/brands/daewoo.png', alt: 'Daewoo' },
    { src: 'images/brands/dodge.png', alt: 'Dodge' },
    { src: 'images/brands/faw.png', alt: 'FAW' },
    { src: 'images/brands/fiat.png', alt: 'Fiat' },
    { src: 'images/brands/ford.png', alt: 'Ford' },
    { src: 'images/brands/geely.png', alt: 'Geely' },
    { src: 'images/brands/hino.png', alt: 'Hino' },
    { src: 'images/brands/honda.png', alt: 'Honda' },
    { src: 'images/brands/hyundai.png', alt: 'Hyundai' }
];

// array de audi con los nombres de las imágenes y sus textos alternativos
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

// array con los años del modelo a1 de audi
var a1 = [
    '2020', '2021', '2022', '2024', '2025'
];

// array con los cilindrajes del año 2020 del modelo a1 de audi
var year2020 = [
    '1.0L', '1.6L', '2.0L', '2.5L'
];

// array de mapeo entre nombre y array de las marcas
var arraysByBrand = {
    'audi': audi
    // Agregar más marcas y sus arrays asociados
};

// array de mapeo entre nombre y array de los modelos
var arraysByModel = {
    'a1': a1
    // Agregar más modelos y sus arrays asociados
};

// array de mapeo entre nombre y array de los años
var arraysByYear = {
    '2020': year2020
    // Agregar más años y sus arrays asociados
};

var lastBrandClicked = null;
var lastModelClicked = null;
var lastYearClicked = null;
var lastCylinderClicked = null;

// agregar una imagen al container con un ID único y evento de clic
function addImage(src, alt) {
    var idUnique = alt.toLowerCase(); // generar ID
    var rectangle = createRectangle(idUnique); // crear rectángulo
    var img = createImage(src, alt); // crear imagen
    rectangle.appendChild(img); // agregar imagen al rectángulo
    var container = document.getElementById('rectangle-grid'); // obtener container
    container.appendChild(rectangle); // agregar rectángulo al container
    addClickEvent(img, idUnique, rectangle); // agregar evento de clic a la imagen
}

// agregar un cuadro de texto al container con un ID único y evento de clic
function addText(content) {
    var idUnique = content.toLowerCase(); // generar ID
    var rectangle = createRectangle(idUnique); // crear rectángulo
    var text = createText(content); // crear cuadro de texto
    rectangle.appendChild(text); // agregar el cuadro de texto al rectángulo
    var container = document.getElementById('rectangle-grid'); // obtener container
    container.appendChild(rectangle); // agregar rectángulo al container
    addClickEvent(content, idUnique, rectangle); // agregar evento de clic a la imagen
}

// crear un rectángulo
function createRectangle(id) {
    var rectangle = document.createElement('div');
    rectangle.className = 'rectanglee';
    rectangle.id = id;
    return rectangle;
}

// crear una imagen
function createImage(src, alt) {
    var img = document.createElement('img');
    img.src = src;
    img.alt = alt;
    return img;
}

// crea un cuadro de texto
function createText(content) {
    var text = document.createElement('span');
    text.content = content;
    return text;
}

// crear un rectángulo que será la categoria
function createCategory(id) {
    var rectangle = document.createElement('button');
    rectangle.className = 'stage';
    rectangle.id = id;
    return rectangle;
}

// evento de clic a una opción
function addClickEvent(option, id, rectangle) {
    option.addEventListener('click', function() {
        var rectangles = document.querySelectorAll('.rectanglee');
        rectangles.forEach(function(rect) {
            rect.classList.remove('active');
        });
        rectangle.classList.add('active');
        if (containsIdBrands(id)) {
            chooseBrand(id);
        } else if(containsIdAudi(id)) {
            chooseModel(id);
        } else if (containsIdA1(id)) {
            chooseYear(id);
        } else {
            chooseCylinder(id);
        }
        console.log(containsIdAudi(id));
        alert('Has clickeado: ' + id);
    });
}

// Carga la página inicial
window.onload = function() {
    document.getElementById('brand').classList.add('active');
    createImagesGrid(brands);
}

// Acción de escoger una marca
function chooseBrand(choice) {
    lastBrandClicked = choice;
    unselectAllCategories();
    document.getElementById('model').classList.add('active');
    clearGrid();
    createImagesGrid(arraysByBrand[choice]);
}

// Acción de escoger un modelo
function chooseModel(choice) {
    lastModelClicked = choice;
    unselectAllCategories();
    document.getElementById('year').classList.add('active');
    clearGrid();
    createTextGrid(arraysByModel[choice]);
}

// Acción de escoger un año
function chooseYear(choice) {
    lastYearClicked = choice;
    unselectAllCategories();
    document.getElementById('cylinder').classList.add('active');
    clearGrid();
    createTextGrid(arraysByYear[choice]);
}

// Acción de escoger un cilindraje
function chooseCylinder(choice) {
    lastCylinderClickedClicked = choice;
}

// Configura la página para la opción marca
function clickBrand() {
    unselectAllCategories();
    document.getElementById('model').classList.add('active');
    clearGrid();
    createImagesGrid(brands);
}

// Configura la página para la opción modelo
function clickModel() {
    unselectAllCategories();
    document.getElementById('model').classList.add('active');
    clearGrid();
    createImagesGrid(arraysByBrand[lastBrandClicked]);
}

// Configura la página para la opción año
function clickYear() {
    unselectAllCategories();
    document.getElementById('year').classList.add('active');
    clearGrid();
    createTextGrid(arraysByModel[lastModelClicked]);
}

// Configura la página para la opción cilindraje
function clickCylinder() {
    unselectAllCategories();
    document.getElementById('cylinder').classList.add('active');
    clearGrid();
    createTextGrid(arraysByYear[lastYearClicked]);
}

// Crea la cuadrícula de imagenes según la opción seleccionada
function createImagesGrid(choice) {
        for (var i = 0; i < choice.length; i++) {
            addImage(choice[i].src, choice[i].alt);
        }
}

// Crea la cuadrícula con texto según la opción seleccionada
function createTextGrid(choice) {
        for (var i = 0; i < choice.length; i++) {
            addText(choice[i]);
        }
}

//Borra los containeres de la cuadrícula de imagenes
function clearGrid() {
    var container = document.getElementById('rectangle-grid');
    while (container.firstChild) {
        container.removeChild(container.firstChild);
    }
}

// Deselecciona todas las categorias
function unselectAllCategories() {
    document.getElementById('brand').classList.remove('active');
    document.getElementById('model').classList.remove('active');
    document.getElementById('year').classList.remove('active');
    document.getElementById('cylinder').classList.remove('active');
}

// Halla si un id se encuentra en brands
function containsIdBrands(id) {
    for (var i = 0; i < brands.length; i++) {
        if (brands[i].alt.toLowerCase() === id) {
            return true;
        }
    }
    return false;
}

// Halla si un id se encuentra en audi
function containsIdAudi(id) {
    for (var i = 0; i < audi.length; i++) {
        if (audi[i].alt.toLowerCase() === id) {
            return true;
        }
    }
    return false;
}

// Halla si un id se encuentra en a1
function containsIdA1(id) {
    for (var i = 0; i < a1.length; i++) {
        if (a1[i].content.toLowerCase() === id) {
            return true;
        }
    }
    return false;
}

// Halla si un id se encuentra en year2020
function containsIdYear2020(id) {
    for (var i = 0; i < year2020.length; i++) {
        if (year2020[i].content.toLowerCase() === id) {
            return true;
        }
    }
    return false;
}