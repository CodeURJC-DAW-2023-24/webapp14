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


## ESTRUCTURA DEL PROYECTO

Vamos a tener diversas pantallas, para nuestra web:

#### PANTALLA DE INICIO

En esta pantalla se muestran las operaciones principales que se pueden realizar, y se muestran algunos eventos. En función de que ussuario seas se te mostraran tambien distintas opciones.

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/2d98b31b-de90-4388-81cb-5fdd89648f50)

#### PANTALLA DE LOGIN

En esta pantalla que te permite iniciar sesión si ya estas registrado o registrarte.

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/0a7ae3bd-8db4-49e9-8e05-4e8ab9f54980)

#### PANTALLA DE REGISTRO

En esta pantalla te permite registrarte añadiendo tus datos.

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/c39e9706-0db6-4056-8c44-dfdbe68770ed)

#### PANTALLA DE MI PERFIL

En esta pantalla se muestra la información del usuario que ha iniciado sesión

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/2682e329-e404-417a-a8c5-c72b24dbf45a)

#### PANTALLA DE EVENTOS RECIENTES

En esta pantalla puedes ver distintos eventos que se van a realizar y un botón de más eventos que al pulsarlo aparecerán más.

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/6e560f70-6970-41ef-9792-646afc2c34c8)

#### PANTALLA DE EVENTOS 

En esta pantalla puedes ver toda la información del evento en el que has pinchado en la pantalla de eventos. Desde esta pantalla podrás inscribirte al evento.

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/ac70a8fb-2ff3-4545-b563-5dd90373868f)

#### PANTALLA DE INSCRIPCIÓN

En esta pantalla podrás rellenar todos los datos para inscribirte a un evento

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/451a1fbd-f013-4e53-ba41-cff3e31c77f0)

#### PANTALLA DEL PANEL DE ADMINISTRACIÓN

En esta pantalla se muestra información base sobre la plataforma, número total de usuarios y eventos, y otra información como visitas a la web, o cuando hay más inscripciones a un evento.

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/0511a745-84a5-40e8-a939-da53a1cabf55)

#### PANTALLA DE GESTIÓN DE USUARIOS

En esta pantalla se muestran los usuarios, y permite realizar y acceder varias acciones sobre los usuarios.

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/85990da3-8ebf-4657-a3a2-f1a6e3d50f01)

#### PANTALLA DE GESTIÓN DE EVENTOS

En esta pantalla se muestran los eventos, y permite realizar y acceder varias acciones sobre los eventos

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/e754761d-e783-4b15-9a9a-ba45706ee353)

#### PANTALLA DE AÑADIR EVENTOS

En esta pantalla se muestran el formulario que hay que rellenar para añadir la información de un evento

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/47766a2b-7187-4d87-8eb0-15b7d48e27f5)

## DIAGRAMA DE NAVEGACIÓN DEL PROYECTO

Esta es la manera en la cual interactuan nuestras pantallas, en el diagrama: flecha rosa: usuario administrador, flecha morada usuario editor, flecha azul turquesa: usuario no registrado, flecha azul oscura: usuario registrado

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/e33ceee6-ab4a-436d-b865-2e195566971f)


#FASE 2

Tendras que crear un esquema en la base de datos, que sea ticketeventaplicacion. Los datos de accesos por contraseña, para admin, o user, predeterminado se encuentran en DatabaseUsersLoader.java. Para ejecutar nuestra aplicación tienes que darle a “Run as SpringBootApplication” en TicketEventApplication.


En la fase 2, se han realizado los siguientes cambios:

## PARTICIPACIÓN

### Alba Velasco Marqués 

Durante esta fase mis tareas principales han sido:
  -	Poder editar los eventos y la gestión de estos datos editados.
  -	Poder editar la información del perfil y la gestión de estos datos editados.
Los 5 commits más significativos han sido:
  -	Conseguido que se muestren los elementos por id
  -	Guardar en base de datos la edicion de perfil y formulario editEvent
  -	Mostrar bien PDF
  -	Prueba editar-evento 2
  -	Conseguido que se borren los eventos por id
Los 5 ficheros en los que más he participado han sido:
  -	TicketEventControllerGeneral
  -	EditForm.html
  -	EditEvent.html
  -	Eventos
  -	User

### Almudena López Rodríguez

Tareas:
  -	Ayudar con moustache
  -	Gestionar rol de editor
  -	Configurar la seguridad
Los 5 commits más significativos han sido:
  -	pdf en proceso
  -	Gráficos de contadores funcionan
  -	Mostrar Eventos Ajax
  -	Arreglamos paginación
  -	Funciona el gestionar Permiso de edición
Los 5 ficheros en los que más he participado han sido:
  -	TicketEventControllerGeneral
  -	SecurityConfiguration
  -	showEvent.html
  -	Index.html
  -	permisosUsuarios.html

### Andrés Carretero Jiménez 

Tareas:
  -	Registro de usuarios
  -	Creación de eventos
  -	Creación de entradas
  -	Mostrar la información del usuario en el perfil
Los 5 commits más significativos han sido:
  -	Ya funciona el registro
  -	Se crea evento correctamente
  -	Se crea el ticket
  -	Se muestra tu información en el perfil correctamente
  -	Repositorios metidos en el controller
Los 5 ficheros en los que más he participado han sido:
  -	TicketEventControllerGeneral
  -	registrar.html
  -	inscripción.html
  -	Event
  -	profile.html

### Irene Pérez Santiago

Tareas:
-	Configuración de la base de datos 
-	Primera version de seguridad
-	Gestión comments
-	Primera integración de los modelos de usuarios y eventos
Los 5 commits más significativos han sido:
  - funciona, puto ID de los huevos
  - integracion del user
  - update del maven
  - config del pom
  - gestion para meter eventos en base de datos
Los 5 ficheros en los que más he participado han sido:
  - SecurityConfiguration.java
  - EventService.java
  - CommentService.java
  - Event.java
  - Comment.java
  - TicketEventControllerGeneral.java


## DIAGRAMAS

### DIAGRAMA DE NAVEGACIÓN

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/af22c70e-c3dc-4428-8a8a-88f16abe0a5d)

### DIAGRAMA DE ENTIDAD - RELACION

Esto es la primera versión que se ha realizado:

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/4f1707a9-0a6b-491a-9260-3efdd31d900f)

Este es el resultado final:

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/442bdf6c-ff59-4425-a9b1-71770ea2cafa)

### DIAGRAMA DE CLASES

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/7bdb5360-5c50-435b-87c4-312d2f27f447)




