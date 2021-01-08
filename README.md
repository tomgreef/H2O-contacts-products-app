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

MEMORIA H20 Anddroid App:
-------------------------
0. Introducción
  - Interfaz multilingüe (Español / Inglés)
  - Notificacion amigables
  
1. Gestión Clientes (añade un cliente a la base de datos de H20):
  - Paginación de lista de clientes (futuro)
  - Añadir a cada cliente una foto de perfil (futuro)
  - Implementar un buscador / filtro al listar los Clientes que hay
  - Medidas seguridad al eliminar un Cliente
  - Medidas para evitar la inserción de valores nulos al añadir un Cliente
  - Si no se ha modificado ningun campo no se modifica la fila
