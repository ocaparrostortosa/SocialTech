# SocialTech

![Bandera españa](http://www.stiftsgymnasium-melk.org/nawi/Physik/Team-Physik/files/stacks-image-3e7032f.png) Español 
==================


Proyecto escolar basado en una aplicación Android utilizada para guardar contactos, tanto de personas como de empresas, a partir de un usuario registrado soportado por una base de datos Firebase.

Creada por Óscar Caparrós para el módulo de 'Desarrollo de Interfazes' del C.F.G.S. de Desarrollo de
 Aplicaciones Multiplataforma del instituto I.E.S. Virgen del Carmen.
 
 La aplicación fue diseñada como proyecto del primer trimestre. Dicho proyecto está basado en la tecnología SCRUM, por lo que estaba dividido en diferentes "Sprints" o "Tareas". Dichos "Sprints" fueron creados para que el alumno desarrollara una aplicación Android desde cero con distintas tareas de refactorización y extensión de los mismos sprints.

El propósito final de la aplicación consistía en disponer de varios fragmentos para crear, listar y seleccionar distintos contactos tanto de personas como de empresas. Dichos contactos deberán de estar guardados en un proyecto de Firebase y controlados previamente por una autenticación de e-mail y contraseña (propia de Firebase).
 
 Primeras impresiones
---------------------
A la hora de ejecutar la aplicación en nuestro dispositivo Android observamos que se abre una ventana de logueo donde no podremos iniciar sesión hasta que no nos registremos. Una vez registrados de forma exitosa podremos proceder al logueo, y si éste es correcto, se nos abrirá una pestaña donde, en caso de que existan, se nos mostrarán los usuarios y un menú
donde poder crear otros usuarios o acceder a la ventana de empresas donde podremos realizar las mismas actividades. 

La lista tanto de personas como de empresas dispone de una función que elimina un contacto pulsando de forma prolongada en dicho contacto o abre una segunda ventana donde se nos muestra información adicional y extendida del propio contacto.

Para realizar un rápido vistazo a la aplicación y a sus funciones principales he creado un usuario predeterminado con un par de contactos:
- Usuario: ocaparrostortosa@github.com
- Contraseña: 123456
 
 El uso básico de la aplicación es:
 
 - Registrarse: Permite crear un nuevo usuario en la base de datos para poder iniciar sesión con dicho usuario.
 - Loguearse: Permite iniciar sesión dependiendo de un e-mail y una contraseña.
 - Guardar contacto: Te permite guardar un contacto de una persona (Nombre, apellidos, telefono, email, sexo, titulos, provincia y edad), o de una empresa(Nombre, dirección, localidad, provincia, teléfono corporativo,
email corporativo, observaciones y contacto de persona asociado.). Dichos contactos serán almacenados en un apartado de la base de datos de Firebase dependiendo id del usuario logueado.
 - Lista contactos: Te permite, dependiendo del usuario logueado, obtener todos los contactos guardados tanto de personas como de empresas.
 - Otras acciones: Como por ejemplo la de poder llamar al contacto haciendo click sobre la imagen en la descripción de dicho contacto; o la de poder ocultar y mostrar la contraseña a la hora de iniciar sesión.
 

 Problemas conocidos
---------------------

Varios de los problemas conocidos son derivados de la falta de tiempo a la hora de hacer el proyecto, igualmente serán corregidos cuando tenga tiempo suficiente.

- Errores puntuales e inusuales a la hora de realizar la transición entre diferentes activities y fragmentos que conllevan a la caída de la aplicación.
- Arreglar el idioma en la aplicación ya que hay una mezcla entre Inglés y Español.
- Encontrar y arreglar el error generado en el menú del activity de las Empresas.

 Extensión del proyecto
---------------------

Como parte de una futura extensión el proyecto me gustaría acabar y llevar a cabo algunas de las siguientes funciones:

- Añadir de forma automática un listado en forma de AutoComplete para poder elegir la Localidad en la que se encuentra la empresa dependiendo de la Provincia.
- Realizar una transición limpia mediante un efecto o progress bar entre las activities de personas y empresas.
- Implementar un Navigation Drawler e intercambiarlo por el menú actual de la aplicación.

 Aplicaciones y servicios usados
================================
 ![Android Studio](https://3.bp.blogspot.com/-BVQ36vhFc0I/VsOpgnJmD-I/AAAAAAAAAFk/Z4BOOKmbxJ4/s1600/banner.PNG)  ![Firebase](https://media.licdn.com/mpr/mpr/AAEAAQAAAAAAAAuEAAAAJDllZmUxNmM0LTZiMWEtNGFiNi04ZTUwLTI5ZTcxOGFjZWNhMA.png)
 
 Agradecimientos a la comunidad de:
 
 ![Stackoverflow](https://upload.wikimedia.org/wikipedia/ro/f/f7/Stack_Overflow_logo.png)
 
 ---------------
 
 ![Bandera inglaterra](http://www.jabarprov.go.id/assets_front/images/english.png) English
==================
 
 
 School proyect based in an Android application used to save contacts (people and business). These contacts are saved from a user previously registered supported by a Firebase database.
 
 Created by Óscar Caparrós for the subject of 'Interface development' of the higher education training cycle 'Development of Multiplatform Applications' in the highschool 'Virgen del carmen'.
 
 The application was developed like a school proyect. This proyect is based in the SCRUM technology and its divided in diferents Sprints created to let the students develop an Android application right from the start with differents tasks to refactor and extend these sprints.
 
 The final purpose of the application was to have several fragments to create, list and select different contacts. These contacts must be saved in a Firebase project and previously controlled by an e-mail and password authentication (own Firebase).
 
 First impressions
---------------------
At the time of running the application on our Android device we observed a login window where we can't log in until we register. Once registered successfully we can proceed to login, and if this is correct, we will open a tab where will be shown our users and a menu
where you can create other users or access the window of companies where we can perform the same activities.

The list of both (people and companies) has a function that eliminates a contact by pressing a long time in that contact or opens a second window where an additional and extended information of the contact  will be shown.

To make a quick look of the application I have created a default user with a couple of contacts:
- User: ocaparrostortosa@github.com
- Password: 123456


The basic use of the application is:

- Register: Creates a new user in the database to be able to log in.
- Log in: Allows you to log in depending on an e-mail and a password.
- Save contact: It allows you to save a contact of a person (Name, surnames, phone, email, sex, titles, province and age), or a contact of a company (Name, address, town, province, corporate phone,
corporate email, observations and contact of associated person.). These contacts will be stored in a section of the Firebase database depending on the logged user.
- Contacts list: It allows you, depending on the logged user, to obtain all the saved contacts (people and companies).
- Other actions: As for example the ability to call the contact by clicking on the image in the description of that contact; or the ability to hide and show the password when you are trying to log in.

Known problems
---------------------

Several of the known problems are derived from the lack of time at the time of doing the project, they will also be corrected.

- Punctual and unusual errors when making the transition between different activities and fragments that lead to the fall of the application.
- Fix the language in the application because it has a mixture between English and Spanish.
- Find and fix the error generated in the menu of the activity of the Companies.

 
Extension of the project
---------------------

As part of a future extension of the project I would like to finish and carry out some of the following functions:

- Automatically add a list in the form of AutoComplete to choose the location where the company is located on depending on the province.
- Make a clean transition through an effect or progress bar between the activities of people and companies.
- Implement a Navigation Drawler and exchange it with the current menu.

 
Used Apps and Services
 -------------------------------------------
 ![Android Studio](https://3.bp.blogspot.com/-BVQ36vhFc0I/VsOpgnJmD-I/AAAAAAAAAFk/Z4BOOKmbxJ4/s1600/banner.PNG) ![Firebase](https://media.licdn.com/mpr/mpr/AAEAAQAAAAAAAAuEAAAAJDllZmUxNmM0LTZiMWEtNGFiNi04ZTUwLTI5ZTcxOGFjZWNhMA.png)
 
Acknowledgments to the community of:
 
 ![Stackoverflow](https://upload.wikimedia.org/wikipedia/ro/f/f7/Stack_Overflow_logo.png)