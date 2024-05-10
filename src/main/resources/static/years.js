// Agregar años a los botones
function addYears(years) {
    // Encontrar todos los botones en la cuadrícula
    var buttons = document.querySelectorAll('.rectangle-grid button');

    // Iterar sobre cada botón
    buttons.forEach(function(button) {
        // Limpiar cualquier contenido previo del botón
        button.innerHTML = '';

        // Obtener el año correspondiente a este botón
        var year = years.shift(); // Toma y elimina el primer año del array

        // Crear un elemento de texto para el año y agregarlo al botón
        var yearText = document.createTextNode(year);
        button.appendChild(yearText);
    });
    return false;
}
