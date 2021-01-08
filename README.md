# H2O Water Coolers - Android App

## Consideraciones:
- Diseño SingletonMap
- Uso de base de datos (SQLite)
- Tener mecanismo de notificación (Toast)
- Tener ventanas de información de errores (Dialogs)
- Soporte multi-idioma (Español & Ingles)
- Utilización de un ArrayAdapter o similar
- Una adecuada abstracción, diseño y eficiencia

## Actividades:
0. BBDD -> ALAE
1. Lista de Clientes -> Tom
2. Lista de Productos -> Tom
3. Lista de Pedidos -> Tom
4. Añadir Cliente -> Alae
5. Editar Cliente -> Alae
6. Borrar Cliente -> Alae
7. Filtro -> Tom

MEMORIA H20 Android App:
-------------------------
Alcance de la aplicación desarrollada:
El principal proposito de la aplicación H2O es el desarrollo, diseño y lanzamiento de una aplicación para la gestión interna (estilo intranet) de la empresa H2O con sede en España. El cliente nos pidió la implementación de Gestión de Clientes, Gestión de Pedidos y Gestión de Productos. Así como una página de login para los empleados. Nos pidieron tambien que la aplicación fuese bilingüe (castellano e inglés) y que tenga notificaciones amigables para los empleados.

Casos de uso desarrollados:
De los casos de uso reunidos en la entrevista con el cliente el desarrollado en esta versión de la aplicación tenemos la Gestión de Clientes con su CRUD, Añadir Cliente, Borrar Cliente, Listar Cliente y Editar Cliente. Todas las operaciones del CRUD tienen su control de seguridad para evitar valores vacios, nulos o cualquier otro de incidencia en la BBDD. Se ha añadido una funcionalidad extra que es la de filtrar / buscar entre la lista de Clientes, que filtra por nombre.

Casos de uso propuestos para la siguiente versión de la aplicación:
Uno de los casos de uso propuestos a mejorar es el cargar mas elementor al hacer scroll en la aplicación, la ventana de la aplicación permite un máximo de X usuarios, dado que H2O es una empresa en constante crecimiento y auge, sería conveniente añadir la funcionalidad de que cargar al hacer scroll. Otra funcionalidad requerida por el cliente pero por falta de tiempo no se pudo llegar a concluir es la de añadir una actividad de Inicio de Sesión para controlar y restringir el acceso a los diferentes niveles de empleados que hay. Otra funcionalidad importante a añadir para poder implementar la Gestión de Productos y Gestión de Pedidos es hacer que el boton flotante para añadir cliente cambie conforme se cambie de pestaña (fragmento).

