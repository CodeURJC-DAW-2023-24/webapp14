# TicketEvent

        Proyecto de web insipirado en la página de eventos EVENTOS URJC (https://eventos.urjc.es)
## MIEMBROS DEL EQUIPO:

El equipo está compuesto por:


| Nombre                     | Correo                            | Cuenta GitHub |
| -------------------------- | --------------------------------- | ------------- |
| Andrés Carretero Jiménez | a.carreteroj.2021@alumnos.urjc.es | andrescrrt    |
| Almudena López Rodríguez | a.lopezro.2021@alumnos.urjc.es    | almuLR        |
| Irene Pérez Santiago      | i.perezs.2020@alumnos.urjc.es     | iireeneeps012 |
| Alba Velasco Marqués      | a.velascom.2021@alumnos.urjc.es   | AlbitaVM      |

## TRELLO

Se va a usar un espacio de trabajo, con un tablero por fase para organizar el trabajo: (https://trello.com/w/daw174)

## REQUISITOS DE LA APLICACIÓN

### ENTIDADES

- **Entidades**:

  - Usuario
  - Evento
  - Entrada
  - Comentario - Me gusta
- **Relación**: Un usuario puede inscribirse a cualquier evento y dejar comentarios y me gusta en ellos.  Y se le genera su entrada para el evento.

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

# FASE 2

Tendras que crear un esquema en la base de datos, que sea ticketeventaplicacion. Los datos de accesos por contraseña, para admin, o user, predeterminado se encuentran en DatabaseUsersLoader.java. Para ejecutar nuestra aplicación tienes que darle a “Run as SpringBootApplication” en TicketEventApplication.

En la fase 2, se han realizado los siguientes cambios:

## PARTICIPACIÓN


| Nombre                     | Participación |
| -------------------------- | -------------- |
| Andrés Carretero Jiménez | 100%           |
| Almudena López Rodríguez | 100%           |
| Irene Pérez Santiago      | 100%           |
| Alba Velasco Marqués      | 100%           |

### Alba Velasco Marqués

Durante esta fase mis tareas principales han sido:

- Poder editar los eventos y la gestión de estos datos editados.
- Poder editar la información del perfil y la gestión de estos datos editados.

Los 5 commits más significativos han sido:


| Commit                                                               | Enlace                                                                                           |
| -------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------ |
| Conseguido que se muestren los elementos por id                      | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/74002419c7c926445ab9dad2aa0f7fb0f32094b0 |
| Guardar en base de datos la edicion de perfil y formulario editEvent | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/66de93f3c15089f88823500ccdae27eedcb12196 |
| Mostrar bien PDF                                                     | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/ce2e118bc82f19c5057763eaafc9ec6548159c71 |
| Prueba editar-evento 2                                               | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/094c8f2820c9d3fe8e3b4ea3ab3fbd8e03f8f0dc |
| Conseguido que se borren los eventos por id                          | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/99f3ac4b68da400f5743cce20eb69b1bd2f92820 |

Los 5 ficheros en los que más he participado han sido:


| Fichero                           | Enlace                                                                                                                                  |
| --------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------- |
| TicketEventControllerGeneral.java | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Controllers/TicketEventControllerGeneral.java |
| EditForm.html                     | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/resources/templates/editForm.html                                   |
| EditEvent.html                    | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/resources/templates/editEvent.html                                  |
| Eventos.html                      | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/resources/templates/eventos.html                                    |
| User.java                         | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Model/User.java                               |

### Almudena López Rodríguez

Tareas:

- Ayudar con moustache
- Gestionar rol de editor
- Configurar la seguridad

Los 5 commits más significativos han sido:


| Commit                                    | Enlace                                                                                           |
| ----------------------------------------- | ------------------------------------------------------------------------------------------------ |
| pdf en proceso                            | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/cf8965d9a12fadef7ec341bcc37d08a4fe54225a |
| Gráficos de contadores funcionan         | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/b985fae3b98db5736cfd822bb30b81c3698c62a1 |
| Mostrar Eventos Ajax                      | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/a1c989c337acf0c28f872fdeeecec7f807ebae92 |
| Arreglamos paginación                    | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/559b8d9cea5de93effc5db3d83ca8b6a2903123a |
| Funciona el gestionar Permiso de edición | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/3c5fa9cccf20333693b5e3f38b00b572f23d0c2c |

Los 5 ficheros en los que más he participado han sido:


| Fichero                           | Enlace                                                                                                                                  |
| --------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------- |
| TicketEventControllerGeneral.java | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Controllers/TicketEventControllerGeneral.java |
| SecurityConfiguration.java        | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/security/SecurityConfiguration.java           |
| showEvent.html                    | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/resources/templates/showEvent.html                                  |
| Index.html                        | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/resources/templates/index.html                                      |
| permisosUsuarios.html             | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/resources/templates/permisosUsuarios.html                           |

### Andrés Carretero Jiménez

Tareas:

- Registro de usuarios
- Creación de eventos
- Creación de entradas
- Mostrar la información del usuario en el perfil

Los 5 commits más significativos han sido:


| Commit                                                | Enlace                                                                                         |
| ----------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| Ya funciona el registro                               | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/01d45c37be3604677a1a3920954f2f9cbed88471 |
| Se crea evento correctamente                          | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/1cea32702524ae619e7a4cc66fc699693a2c2dca |
| Se crea el ticket                                     | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/b61c3fa0739865d739781950e9a51c46645bf9e8 |
| Se muestra tu información en el perfil correctamente | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/62f8e2aad7ef5a6153a34ede9b28fe4e2d78cd84 |
| Repositorios metidos en el controller                 | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/569634a6c347c96303b4294308adc3afcff6513b |

Los 5 ficheros en los que más he participado han sido:


| Fichero                           | Enlace                                                                                                                                  |
| --------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------- |
| TicketEventControllerGeneral.java | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Controllers/TicketEventControllerGeneral.java |
| registrar.html                    | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/resources/templates/registrar.html                                  |
| inscripcion.html                  | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/resources/templates/inscripcion.html                                |
| event.html                        | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Model/Event.java                              |
| profile.html                      | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/resources/templates/profile.html                                    |

### Irene Pérez Santiago

Tareas:

- Configuración de la base de datos
- Primera version de seguridad
- Gestión comments
- Primera integración de los modelos de usuarios y eventos

Los 5 commits más significativos han sido:


| Commit                                                      | Enlace                                                                                         |
| ----------------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| funciona, puto ID de los huevos                             | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/55863ec2cffff2e0bb376ec11d114be5f337ad9c |
| se arregla el pom                                           | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/773b1096bf3b7054447a7b0ee28242a7ce5617c6 |
| configurado para eventos, pero no funciona el html          | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/3ea5b1004a589f14e6a185e117a46d71f668924c |
| arreglado el css                                            | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/b3265fe7a405c8de0ed2b67abe33d83dd046df87 |
| actualizacion de las clases con notacion para base de datos | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/c7742b535c969253014478da634d00edef0c5142 |

Los 5 ficheros en los que más he participado han sido:


| Fichero                    | Enlace                                                                                                                        |
| -------------------------- | ----------------------------------------------------------------------------------------------------------------------------- |
| SecurityConfiguration.java | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/security/SecurityConfiguration.java |
| EventService.java          | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Service/EventService.java           |
| CommentService.java        | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Service/CommentService.java         |
| Event.java                 | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Model/Event.java                    |
| Comment.java               | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Model/Comment.java                  |

## DIAGRAMAS

### DIAGRAMA DE NAVEGACIÓN

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/af22c70e-c3dc-4428-8a8a-88f16abe0a5d)

### DIAGRAMA DE ENTIDAD - RELACION

Esto es la primera versión que se ha realizado:

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/4f1707a9-0a6b-491a-9260-3efdd31d900f)

Este es el resultado final:

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/442bdf6c-ff59-4425-a9b1-71770ea2cafa)

### DIAGRAMA DE CLASES

<img width="496" alt="image" src="https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/89156022/76d2d62d-51f6-4868-9ffe-a02e44f1e52b">

# FASE 3

## COMO EJECUTAR LA APLICACIÓN - DESDE FUERA DE LA MAQUINA DE LA UNIVERSIDAD

Para ejecutar la aplicación en cualquiera de los siguientes casos, debes cumplir los siguientes requisitos:

1. Tener instalado docker, y docker compose (en caso de no tenerlo, consulta https://docs.docker.com/engine/install/ y https://docs.docker.com/compose/)
2. Tener git instalado en la maquina, u ordenador donde vayas a ejecutar la aplicación (https://git-scm.com)
3. Clona nuestra repositorio (https://github.com/CodeURJC-DAW-2023-24/webapp14.git)

### Aplicación Dockerizada

Para ejecutar al aplicación a través de la versión dockerizada,na vez que tenemos esos requisitos cubiertos:

1. Accede a la carpeta donde has clonado el repositorio
2. Ejecuta *docker compose -f docker/docker-compose.yml up* , en algunos casos si no has iniciado sesión deberás primero realizar un docker login (RECOMENDABLE: ejecutar el comando con sudo delante)
3. Accede a la web a través de tu navegador de confianza, [https://localhost:8443/](https://localhost:8443/) o https://IPMAQUINA:8443/

### Ejecutar el fichero create_image.sh

Para ejecutar el fichero create_image.sh, una vez que tenemos los requisitos cubiertos:

1. Accede a la carpeta donde has clonado el repositorio
2. Muevete a la carpeta docker/
3. Da permisos de ejecución al fichero chmod +x create_image.sh (en windows, te resultará más sencillo hacerlo desde el explorador de archivos, sino sabes como aqui te dejamos los enlaces https://www.anujvarma.com/ssh-on-windows-permissions-for-private-key-too-open/) (RECOMENDABLE: ejecutar el comando con sudo delante)
4. Ejecuta el fichero ./create_image.sh, te pedirá logearte y después de ahi ya estaría todo (RECOMENDABLE: ejecutar el comando con sudo delante)

### Generar una imagen dockerizada

Para generar una imagen dockerizada, una vez que tenemos los requisitos cubiertos tienes que:

1. Accede a la carpeta donde has clonado el repositorio
2. Con el comando sudo delante, docker build -t iireenees012/webapp -f docker/Dockerfile .

## COMO EJECUTAR LA APLICACIÓN - DESDE LA MAQUINA DE LA UNIVERSIDAD

Si eres estudiante del Curso 2023 - 2024 de la Asignatura Desarrollo de Aplicaciones Web, puedes optar por ejecutar la aplicación desde la máquina que te han asignado los profesores:

1. Hay que conectarse a la red de la Universidad:
   1. A través de Eduroam, es decir, presencial en la Universidad
   2. A través de MyApps, ya bien sea a través del Escritorio de Desarrollo o el Escritorio de Ubuntu
   3. A través de la VPN de la Universidad, para saber como configurarla consulta el Manual de la VPN (https://www.urjc.es/principal-intranet/documentos/general/82-configuracion-vpn-urjc/file)
2. Despliega y configura tu maquina:
   1. Para conectarte deberas acceder por SSH a través de la clave remota, a través de los datos proporcionados por  los profesores (te dan la IP y el fichero de la clave ssh), la clave deberás descargarla y guardarlar
   2. Abre la terminal de tu ordenador o del escritorio de desarrollo, accede a la carpeta donde has guardado la clave y ejecutra este comando *ssh -i *prAppWeb*XX.key vmuser@IP\_MAQUINA* (No olvides que tienes que sustituir los datos)
   3. Si no tienes docker y docker compose hay que instalarlo (en caso de no tenerlo, consulta https://docs.docker.com/engine/install/ y https://docs.docker.com/compose/)
3. Despliegue de la aplicación:
   1. Clona nuestra repositorio (https://github.com/CodeURJC-DAW-2023-24/webapp14.git)
   2. Accede a él y ya puedes experimentar lo que necesites, recomendado usar sudo para las siguientes operaciones.

## PROBAR EL POSTMAN

Si quieres probar el postman, tienes que hacer lo siguiente:

1. Tener descargado postman en tu ordenador (https://www.postman.com/downloads/)
2. Inicia Sesión o Registrate
3. Importa el fichero api.postman_collection.json (lo puedes encontrar en el github)
4. Prueba las distintas opciones

RECUERDA: Debes tener la aplicación levantada para probrarlo

## PARTICIPACIÓN


| Nombre                     | Participación |
| -------------------------- | -------------- |
| Andrés Carretero Jiménez | 100%           |
| Almudena López Rodríguez | 100%           |
| Irene Pérez Santiago      | 100%           |
| Alba Velasco Marqués      | 100%           |

### Alba Velasco Marqués

Durante esta fase mis tareas principales han sido:

- Hacer el algortimo.
- Mostrar en el perfil solo los eventos a los que se ha inscrito el usuario al que pertenece ese perfil.
- Guardar y mostrar los comentarios en la misma pagina del evento en donde se han escrito.
- Division de controladores.
- Arreglar el estilo de la pagina de cada evento.
- Arreglar que un editor no pueda borrar un evento.
- Hacer el TicketRestController y el CommentRestController
- Operaciones en la EventsRestController sobre las peticiones rest de los eventos recomendados.

Los 5 commits más significativos han sido:


| Commit                                                                         | Enlace                                                                                           |
| ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------ |
| Finalizado algoritmo                                                           | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/a4356cc07b8d39609f0dc7ba374786e5f5bd2b5b |
| Conseguido que se muestren en el perfil los comenarios del propio usuario solo | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/9b64f97fcd84919bd81c68b6c99acdd555031c83 |
| Conseguido comentarios que se guarden y se muestren solo en el evento          | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/d3991f30ff05ce35748a0193aa331819e2843573 |
| ApirestControllers completos                                                   | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/a90ab238f5721cc2e4e35fd0a799c1065112d91e |
| Hecho operacion de consultar eventos recomendados                              | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/40c66be4676a86536f087c69dd843ced85663b10 |

Los 5 ficheros en los que más he participado han sido:


| Fichero               | Enlace                                                                                                                                |
| --------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| CommentRestController | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Controllers/Rest/CommentRestController.java |
| TicketRestController  | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Controllers/Rest/TicketRestController.java  |
| EventRestController   | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Controllers/Rest/UserRestController.java    |
| showEvent.html        | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/resources/templates/showEvent.html                                |
| UserController        | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Controllers/UserController.java             |

### Almudena López Rodríguez

Tareas:

- Establecer la base de la API Rest
- Crear la generación automática de la documentación de la API
- Configurar la seguridad
- Crear la colección de objetos JSON
- Crear los gráficos con sus correspondientes operaciones de la API
- Hacer las peticiones Rest de los eventos y del login
- Añadir las imágenes a la base de datos

Los 5 commits más significativos han sido:


| Commit                                                                           | Enlace                                                                                           |
| -------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------ |
| Operacion API Especial Datos Grafico                                             | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/ddd88cbe986adfa76d68f8a6f02bf50f57408061 |
| Generación de la documentación automatica de la API                            | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/d6ff7a1acc7c8f2d26b5ec89c6367b62d1007762 |
| Creacion de la nueva configuración de seguridad y programación del RESTEVENTOS | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/1b08f273167d83296bec8b68d16c973af455553b |
| Merge branch 'GraficosAdmin' into Imagenes                                       | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/1b4a2c7e288ae22a485b4309b134e52c5567b991 |
| Se guarda el evento al que se registran y el ususario                            | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/fe797f664dbfca78cefa4c4606a2185f59bb8a67 |

Los 5 ficheros en los que más he participado han sido:


| Fichero                     | Enlace                                                                                                                                 |
| --------------------------- | -------------------------------------------------------------------------------------------------------------------------------------- |
| EventRestController         | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/pruebaB/src/main/java/com/codeUrjc/daw/Controllers/Rest/EventRestController.java |
| LoginController             | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/pruebaB/src/main/java/com/codeUrjc/daw/Controllers/auth/LoginController.java     |
| UserController              | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/pruebaB/src/main/java/com/codeUrjc/daw/Controllers/UserController.java           |
| pom.xml                     | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/pom.xml                                                                     |
| api.postman_collection.json | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/pruebaB/api.postman_collection.json                                              |

### Andrés Carretero Jiménez

Tareas:

- Se muestran los eventos a los que se ha apuntado un usuario
- Hacer el UserRestController
- Que las imágenes de los eventos se puedan guardar y editar
- Peticiones del User y de las imágenes
- Diagrama de clases y templates

Los 5 commits más significativos han sido:


| Commit                                                                  | Enlace                                                                                           |
| ----------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------ |
| Configuración API Rest del User                                        | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/f3504170e13ccd1d4414f517438d41ed351ce1e0 |
| Documentación API Rest del User                                        | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/49d3da1b890e1bf6e0a8ecf9e5f8fda4f52fcd1b |
| Se guardan y se muestran correctamente las imagenes al crear un evento. | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/6544c40a42be3c163232b294b1c1366fa21bf48c |
| Arreglado el editar evento. Se editan las imagenes correctamente.       | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/c13c62999d99544a4254d92563821828ee25901e |
| Api rest actualizada con las imagenes funcionando.                      | https://github.com/CodeURJC-DAW-2023-24/webapp14/commit/d56fe17f357209fa4d3b8718bd72f66196b0beb2 |

Los 5 ficheros en los que más he participado han sido:


| Fichero                  | Enlace                                                                                                                                 |
| ------------------------ | -------------------------------------------------------------------------------------------------------------------------------------- |
| UserRestController.java  | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/pruebaB/src/main/java/com/codeUrjc/daw/Controllers/Rest/UserRestController.java  |
| EventController.java     | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/pruebaB/src/main/java/com/codeUrjc/daw/Controllers/EventController.java          |
| EventRestController.java | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/pruebaB/src/main/java/com/codeUrjc/daw/Controllers/Rest/EventRestController.java |
| editEvent.html           | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/pruebaB/src/main/resources/templates/editEvent.html                              |
| showEvent.html           | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/pruebaB/src/main/resources/templates/showEvent.html                              |

### Irene Pérez Santiago

Tareas:

- Configuración de la maquina de la Universidad
- Configuración del proyecto por carpetas
- Configuración del dockerfile y docker compose
- Generar documentación de conexión
- Comprobar la docker en la máquina

Los 5 commits más significativos han sido:


| Commit                                                      | Enlace                                                                                         |
| ----------------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| funciona, puto ID de los huevos                             | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/55863ec2cffff2e0bb376ec11d114be5f337ad9c |
| se arregla el pom                                           | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/773b1096bf3b7054447a7b0ee28242a7ce5617c6 |
| configurado para eventos, pero no funciona el html          | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/3ea5b1004a589f14e6a185e117a46d71f668924c |
| arreglado el css                                            | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/b3265fe7a405c8de0ed2b67abe33d83dd046df87 |
| actualizacion de las clases con notacion para base de datos | https://github.com/CodeURJC-DAW-2023-24/webapp14/tree/c7742b535c969253014478da634d00edef0c5142 |

Los 5 ficheros en los que más he participado han sido:


| Fichero                    | Enlace                                                                                                                        |
| -------------------------- | ----------------------------------------------------------------------------------------------------------------------------- |
| SecurityConfiguration.java | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/security/SecurityConfiguration.java |
| EventService.java          | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Service/EventService.java           |
| CommentService.java        | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Service/CommentService.java         |
| Event.java                 | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Model/Event.java                    |
| Comment.java               | https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/main/src/main/java/com/codeUrjc/daw/Model/Comment.java                  |

### Documentación de la API REST:

Especificación OpenAPI: https://github.com/CodeURJC-DAW-2023-24/webapp14/blob/Imagenes/api-docs/api-docs.yaml

Documentación HTML: https://rawcdn.githack.com/CodeURJC-DAW-2023-24/webapp14/2d27a247c7cc05efa489acf0fa83acbf3d8f6ba7/api-docs/api-docs.html

### Diagrama de clases y templates

![image](https://github.com/CodeURJC-DAW-2023-24/webapp14/assets/118294636/55d2012e-f54f-46e0-ab78-4549d2f7b296)
