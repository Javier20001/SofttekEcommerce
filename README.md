# SofttekEcommerce: Api Rest BackEnd 

## Descripción de SofttekEcommerce Api Rest BackEnd 

Este proyecto es una API REST desarrollada en Java con el framework Spring. Utiliza Maven para la gestión de dependencias, JDK 17 como versión del lenguaje, una base de datos MySQL, y está dockerizada para facilitar el despliegue. Además, se ha configurado para su despliegue en Kubernetes.

## Características Clave :key:

-   Registro e inicio de sesión de usuarios.
-   Búsqueda de productos por categoría y filtros.
-   Visualización detallada de productos.
-   Solicitud y confirmación de productos para añadir al carro de compras.
-   Pasarela de compra activa para la confirmación de la misma.
-   Funcionalidades para clientes y administradores.


## Requisitos Previos :bookmark_tabs:

Asegúrate de tener instalados los siguientes elementos:

-   Java Development Kit (JDK) 17
-   Maven
-   Docker
-   Kubernetes (kubectl)

## Pasos para Levantar la API Localmente :floppy_disk:

1. **Clonar el Repositorio:** `git clone https://github.com/Javier20001/SofttekEcommerce.git`
2. **Cambiar a la Rama Develop:** `git checkout develop`
3. **Compilar el Proyecto:** `mvn clean install `
4. **Configurar la Base de Datos:**
 -   Crea una base de datos MySQL llamada `eccomerce` (scheema).
-   Actualiza las credenciales de la base de datos en el archivo (username y password) `src/main/resources/application.properties`.
5. **La API estará disponible en: ** `http://localhost:8080`.

## Contribución :bulb:

¡Agradecemos las contribuciones a este proyecto! Si deseas contribuir, sigue estos pasos:

1.  Crea un fork del repositorio.
2.  Crea una nueva rama para tus cambios:  `git checkout -b feature/nueva-caracteristica`.
3.  Realiza los cambios necesarios en tu rama.
4.  Cuando hayas terminado, genera un pull request hacia la rama `develop`. Asegúrate de describir claramente tus cambios.
## Documentación Adicional :book:
- La documentación de la API en Swagger está disponible durante el desarrollo local.
   Puedes acceder a [Swagger Local](http://localhost:8080/swagger-ui/index.html) mientras trabajas en tu 		  máquina.
-  **Diseño de Clases:**  - [Diagrama](https://miro.com/welcomeonboard/UHZMb0RtVW93MWl0ZkE3SXQ1cjNnSVBFR1lOMlVNdno4TXBreENaNE1XblZVVVNMTDh0VWRoWlRYa25mT0pwT3wzNDU4NzY0NTczMDEzODUwMDE3fDI=?share_link_id=112213171260) 
-  **Modelado de Base de Datos:**  - [Modelado](https://lucid.app/lucidchart/7a2d70a9-71fa-4c97-8554-e284f6b7dc20/edit?viewport_loc=-227%2C349%2C2451%2C1099%2C0_0&invitationId=inv_f288e1e5-b355-4978-8226-43159b2cd5bf) 
-  **Diagrama de Casos de Uso:**  - [Descargar PDF](https://acrobat.adobe.com/id/urn:aaid:sc:VA6C2:6fc2caa8-5423-4b5c-96b9-7f653c24dcd2)
-  **Flujo de Trabajo:**  - [Flujo de trabajo](https://drive.google.com/file/d/1ssZh1zHrYLpVOFPao11BeDZl2MnO_UbC/view?usp=drive_link)
## Enlace a Trello Puedes seguir nuestro progreso y gestionar tareas en [Trello](https://trello.com/invite/b/1mmSN6hI/ATTI6f62e16fa0b388f07e231c07e3944b6a23011F61/tareas-para-sofftek).

## Tests Unitarios: :white_check_mark:

-  Los Tests Unitarios están configurados para ejecutarse únicamente en el entorno de desarrollo. 
-  Utilizamos JUnit y Mockito para escribir nuestros tests.
-  Asegúrate de ejecutar los tests antes de realizar cambios significativos y antes de crear una Pull Request. 	 (Generar tests en caso de necesitarlos para evaluar tus nuevos cambios).

## Equipo :construction_worker:
- Javier Kuznik 
- Martin Gimenez
- Alan Papillú
- Matias Perona
- Luciano Marti
- Juan Cruz Insua
- Gonzalo Ivan Vallone
- Lucio Dicuonzo
- Carlos iglesias

## Licencia :red_circle:

Este proyecto está bajo la Licencia MIT. Consulta el archivo  [LICENSE](https://github.com/Javier20001/SofttekEcommerce/tree/main/LICENSE)  para más detalles.
