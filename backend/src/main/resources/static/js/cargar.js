$(document).ready(function() {
    var page = 0; // Página inicial
    var size = 10; // Tamaño de la página

    $('#loadMoreBtn').click(function(event) {
        console.log("Button clicked"); // Agregar esta línea para verificar si se activa el evento
        event.preventDefault();

        // Incrementar el número de página
        page++;

        // Realizar una solicitud AJAX para cargar más eventos
        $.ajax({
            type: 'GET',
            url: '/load-more-events',
            data: {
                page: page,
                size: size
            },
            success: function(data) {
                // Agregar eventos cargados al contenedor de eventos
                $('#eventsRow').append(data);
            },
            error: function(xhr, status, error) {
                console.error('Error al cargar más eventos:', error);
            }
        });
    });
});