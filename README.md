# TicketEvent

                Proyecto de web insipirado en la página de eventos EVENTOS URJC (https://eventos.urjc.es)

## MIEMBROS DEL EQUIPO:

El equipo está compuesto por:

| Nombre | Correo| Cuenta GitHub |
|----------|----------|----------|
| Andrés Carretero Jiménez  | a.carreteroj.2021@alumnos.urjc.es  | andrescrrt |
| Almudena López Rodríguez	    | a.lopezro.2021@alumnos.urjc.es   | almuLR|
| Irene Pérez Santiago   | i.perezs.2020@alumnos.urjc.es | iireeneeps012 |
|  Alba Velasco Marqués        |    a.velascom.2021@alumnos.urjc.es      |    AlbitaVM      |

## TRELLO

Se va a usar un espacio de trabajo, con un tablero por fase para organizar el trabajo: (https://trello.com/w/daw174)

## REQUISITOS DE LA APLICACIÓN

### ENTIDADES

- **Entidades**:
  -   Usuario
  -   Evento
  -   Entrada
  -   Comentario - Me gusta
    
-   **Relación**: Un usuario puede inscribirse a cualquier evento y dejar comentarios y me gusta en ellos.  Y se le genera su entrada para el evento. 

 ### USUARIOS y TIPO DE PERMISOS:

 - **Usuario Anónimo**: Este usuario puede acceder a la web, realizar búsquedas, consultar la información de un evento, registrarse y mirar el grafico de pantalla de inicio con eventos más destacados.

 - **Usuario Registrado**: Este usuario puede hacer las mismas funcionalidades de un usuario anónimo y, además, puede inscribirse a eventos, consultar su información, ver eventos anteriores en los que participó o desinscribirse de un evento. La información para su registro será del nombre, apellidos, si pertenece a la comunidad universitaria, correo electrónico (en caso de que sea de la universidad tendrá que escribir el de la universidad, si no el suyo propio) y contraseña.
 - **Ususario Editor**: Es un ususario registrado que se le otorga permiso para gestionar un evento o varios.
   
 - **Usuario Administrador**: Puede hacer todas las funcionalidades de los usuarios anteriores incluido crear un evento, modificarlo, editarlo, poder crear usuarios, eliminarlos, modificarlos y consultar gráficos de los eventos.

### IMÁGENES

La web de eventos permite que cualquier tipo de usuarios pueda subir imágenes desde el navegador web para su avatar y, además, los administradores podrán subir imágenes como contenido adicional del evento como el lugar donde se va a realizar.

### GRÁFICOS

A nivel de gráficos se va a optar por dos gráficos, un gráfico que la información se muestre de cara pública que consista en mostrar de tipo de eventos tienen una mayor aceptación o son los más inscritos. Y luego a nivel de administración se mostrará por cada evento un porcentaje de si al evento se han unido más personas de fuera de la universidad (usuario que no su correo no se @urjc.es) o de la universidad.

### TECNOLOGÍA COMPLEMENTARIA

A nivel de tecnología complementaria, vamos a necesitar el uso, de dos tecnologías para gestionar las entradas, una de ellas será el envío de correos a usuarios, con la información del evento al que se han apuntado y luego que se genere un pdf con la entrada.

### ALGORITMO

Como algoritmo, se quiere implementar un algoritmo que recomiende a cada usuario registrado en la pantalla de inicio, aquellos eventos que le puedan interesar basado en los eventos que ha asistido.

 




	

