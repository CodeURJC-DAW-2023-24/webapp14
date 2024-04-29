/*!
* Start Bootstrap - Agency v7.0.12 (https://startbootstrap.com/theme/agency)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-agency/blob/master/LICENSE)
*/
//
// Scripts
//
// el código para cargar más eventos
let page = 0; // Inicializar la variable de página a 0
const size = 10; // Tamaño de la página
// Función para cargar más eventos
function loadMoreEvents() {
    // Incrementar la página para la próxima solicitud
    page++;

    const csrfToken = document.querySelector('input[name="_csrf"]').value;

    // Realizar la solicitud AJAX
    $.ajax({
        type: 'GET',
        url: '/load-more-events',
        data: {
            page: page,
            size: size
        },
        success: function (response) {
            // Verificar si hay eventos adicionales
            if (response && response.events && response.events.length > 0) {
                // Agregar eventos al contenedor de eventos
                response.events.forEach(event => {
                    const eventHtml = `
                        <div class="col-lg-4 col-sm-6 mb-4">
                            <div class="portfolio-item">
                                <div class="portfolio-caption">
                                    <div class="portfolio-caption-heading">${event.title}</div>
                                    <div class="portfolio-caption-subheading text-muted">${event.description}</div>
                                    <a class="btn btn-primary btn-xl text-uppercase" href="event/${event.id}">Más información</a>
                                </div>
                            </div>
                        </div>`;
                    $('#eventsRow').append(eventHtml);
                });
            } else {
                // No hay más eventos, ocultar el botón de "cargar más"
                $('#loadMoreBtn').hide();
            }
        },
        error: function (error) {
            console.error('Error al cargar más eventos:', error);
        }
    });
}

window.addEventListener('DOMContentLoaded', event => {

    // Navbar shrink function
    var navbarShrink = function () {
        const navbarCollapsible = document.body.querySelector('#mainNav');
        if (!navbarCollapsible) {
            return;
        }
        if (window.scrollY === 0) {
            navbarCollapsible.classList.remove('navbar-shrink')
        } else {
            navbarCollapsible.classList.add('navbar-shrink')
        }

    };

    // Shrink the navbar 
    navbarShrink();

    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);

    //  Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            rootMargin: '0px 0px -40%',
        });
    };

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });

});
