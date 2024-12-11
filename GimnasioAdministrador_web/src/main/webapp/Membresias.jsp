<%-- 
    Document   : Membresias
    Created on : 29/03/2024, 9:17:12 p. m.
    Author     : Juan Goyes
--%>

<%@page import="Modelo.Membresia"%>
<%@ page import="java.util.LinkedList" %>
<%@ page import="Modelo.Membresia" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.EOFException" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="Header.jsp" %>

<!-- Imagen banner de gimnasio -->
<div>
    <img src="Imagenes/banner_gimnasio.png" class="img-fluid" alt="">   
</div>

<div class="container col-md-4 my-3" >
  
</div>

<!-- Botón para hacer llamado al modal para agregar un nuevo usuario -->
<div class="container d-flex flex-row-reverse">
    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar-plus" viewBox="0 0 16 16">
        <path d="M8 7a.5.5 0 0 1 .5.5V9H10a.5.5 0 0 1 0 1H8.5v1.5a.5.5 0 0 1-1 0V10H6a.5.5 0 0 1 0-1h1.5V7.5A.5.5 0 0 1 8 7"/>
        <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"/>
        </svg> Agregar Membresia
    </button>
</div>

<!-- Tabla donde está la información de las membresias -->
<div class="container col-md-8 my-3">
    <table class="table table-bordered table-light my-3">
        <thead>
            <tr>
                <th>ID</th>
                <th>Membresia</th>
                <th>Descripcion</th>
                <th>Precio</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                Membresia membresiaCon = new Membresia();
                
                ServletContext context = getServletContext();
                
                LinkedList<Membresia> listaMembresias = membresiaCon.deserializarListaMembresias(context);
                
                if (listaMembresias != null) {
                    for (Membresia membresia : listaMembresias) {

            %>

            <tr>
                <td> <%= membresia.getId()%> </td>
                <td> <%= membresia.getNomMembresia()%> </td>
                <td> <%= membresia.getDescripcion()%> </td>
                <td> <%= membresia.getPrecio()%> </td>
                <td>
                    <!-- Iconos -->

                    <!-- Editar  -->
                    <a type="button" class="btn btn-info editar-btn"  data-id="<%= membresia.getId()%>" data-bs-toggle="modal" data-bs-target="#staticBackdropEditar">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                        <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                        </svg> 
                    </a>
                    <!-- Eliminar -->
                    <a type="button" class="btn btn-danger eliminar-btn" data-id="<%= membresia.getId()%>" data-bs-toggle="modal" data-bs-target="#staticBackdropEliminar">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                        <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"/>
                        </svg>
                    </a>
                </td>
            </tr>
            <%
                }
            } else {
            %>

            <tr>
                <td colspan="6">No hay membresías disponibles.</td>
            </tr>
            <%        
                }
            %>

        </tbody>
    </table>
</div>




<!-- Modal para agregar una membresia -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">Agregar Membresía</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="SvMembresia" method="POST">
                    <!-- Entrada para el nombre de la membresia -->
                    <h5>Nombre de la membresía</h5>
                    <input class="form-control form-control-sm" name="nomMembresia" type="text" placeholder="Membresia" aria-label=".form-control-sm example">
                    <!-- Entrada para la duracion de la membresia -->
                    <h5>Duración (Días)</h5>
                    <input class="form-control form-control-sm" name="duracion" type="number" placeholder="Duración (Días)" aria-label=".form-control-sm example" min="1" step="1" required>
                    <!-- Entrada para el precio de la membresia -->
                    <h5>Precio</h5>
                    <input class="form-control form-control-sm" name="precio" type="number" placeholder="Precio" aria-label=".form-control-sm example" min="0" step="0.01" required>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <button  type="submit" class="btn btn-primary">Agregar Membresia</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Modal para editar una membresia -->
<div class="modal fade" id="staticBackdropEditar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">Editar Membresía</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="SvEditarMembresia" method="POST">
                    <p>¿Estás seguro de que deseas editar la membresía con ID: <span id="membresiaIdEditar"></span>?</p>
                    <!-- Entrada para el nombre de la membresia -->
                    <h5>Nombre de la membresía</h5>
                    <input class="form-control form-control-sm" name="nomMembresiaEditar" type="text" placeholder="Membresia" aria-label=".form-control-sm example">
                    <!-- Entrada para la duracion de la membresia -->
                    <h5>Duración (Días)</h5>
                    <input class="form-control form-control-sm" name="duracionEditar" type="number" placeholder="Duración (Días)" aria-label=".form-control-sm example" min="1" step="1" required>
                    <!-- Entrada para el precio de la membresia -->
                    <h5>Precio</h5>
                    <input class="form-control form-control-sm" name="precioEditar" type="number" placeholder="Precio" aria-label=".form-control-sm example" min="0" step="0.01" required>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <button  type="submit" id="boton_editar" value="editarMembresia" class="btn btn-primary editarMembresia">Agregar Membresia</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Modal para Eliminar una membresia -->
<div class="modal fade" id="staticBackdropEliminar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">Agregar Membresía</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="SvEliminarMembresia" method="POST">
                    <p>¿Estás seguro de que deseas eliminar la membresía con ID: <span id="membresiaIdEliminar"></span>?</p>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <button  type="submit" id="boton_eliminar" value="eliminarMembresia" class="btn btn-primary eliminarMembresia">Eliminar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="Foother.jsp" %>
<%
    /**
     * Mostrar un mensaje de alerta seguna el resultado de la eliminacion de una
     * membresia
     */
    String alertaEliminacionMem = (String) request.getSession().getAttribute("alertaEliminacionMem");
    //System.out.println("La alerta para eliminar una membresia es: " + alertaEliminacionMem);
    if (alertaEliminacionMem != null && !alertaEliminacionMem.isEmpty()) {
        if (alertaEliminacionMem.equals("true")) {
%>
<script>
    Swal.fire({
        title: "Se eliminó la membresia",
        text: "Se eliminó la membresia exitosamente ",
        icon: "success"
    });
</script>

<%        
    } else {

%>
<script>
    Swal.fire({
        title: "Error",
        text: "No se puede eliminar la membresia porque existen usuarios que estan utilizando esta membresia",
        icon: "error"
    });
</script>

<%        }
    }
//removemos el atributo de la alerta de membresiaz
    request.getSession().removeAttribute("alertaEliminacionMem");

%>



<%    /**
     * Mostar un mensaje de alerta que muestre que se agregó la membresia de
     * forma exitosa o no se agrego la membresia
     */
    String alertaMembresia = (String) request.getSession().getAttribute("alertaMembresia");
    //System.out.println("La alerta para agregar una nueva Membresia es: " + alertaMembresia);

    if (alertaMembresia != null && !alertaMembresia.isEmpty()) {
        if (alertaMembresia.equals("true")) {
%>

<script>
    Swal.fire({
        title: "Nueva Membresia",
        text: "Se agregó la membresia exitosamente ",
        icon: "success"
    });
</script>

<%            
        }
        
    }
    //removemos el atributo de la alerta de membresia
    request.getSession().removeAttribute("alertaMembresia");
%>

<!-- Script para recoger la id de la membresia que se va a eliminar y despues enviarla por ajaxx al servlet -->
<script>
    // Capturar clic en botón de eliminar
    $('.eliminar-btn').on('click', function () {
        // Obtener el ID de la membresía
        const memberId = $(this).data('id');
        // Mostrar el ID en el modal de eliminación
        $('#membresiaIdEliminar').text(memberId);
        
        // Envío de ID al servlet a través de AJAX (método POST)
        $.ajax({
            url: 'SvEliminarMembresia', // Url donde se enviara los datos(en este caso el id)
            method: 'POST', // Método de solicitud por donde llegara la información al servlet
            data: {id: memberId}, // Datos a enviar (en este caso, el ID)
            success: function (response) {
                // Manejar la respuesta del servidor si es necesario
            },
            error: function (xhr, status, error) {
                console.error('Error al enviar la solicitud:', error);
            }
        });
    });
</script>
<!-- Script para enviar la confimacion de la eliminación de la membresia -->
<script>
    $('.eliminarMembresia').on('click', function () {
        // Obtener el ID de la membresía
        const eliminarMembresia = $('#boton_eliminar').val();
        
        // Envío de ID al servlet a través de AJAX (método POST)
        $.ajax({
            url: 'SvEliminarMembresia', // Url donde se enviara los datos(en este caso la verificacion de la eliminación de la memrbesia)
            method: 'POST', // Método de solicitud
            data: {boton_eliminar: eliminarMembresia}, // Datos a enviar (verificación para la eliminación de la memrbesia)
            success: function (response) {
                // Manejar la respuesta del servidor si es necesario
            },
            error: function (xhr, status, error) {
                console.error('Error al enviar la solicitud:', error);
            }
        });
    });
</script>
<!-- Script para recoger la id de la membresia que se va a editar y despues enviarla por ajaxx al servlet -->
<script>
    // Capturar clic en botón de editar
    $('.editar-btn').on('click', function () {
        // Obtener el ID de la membresía
        const memberIdE = $(this).data('id');
        // Mostrar el ID en el modal de editar
        $('#membresiaIdEditar').text(memberIdE);
        
        // Envío de ID al servlet a través de AJAX (método POST)
        $.ajax({
            url: 'SvEditarMembresia', // Url donde se enviara los datos(en este caso el id)
            method: 'POST', // Método de solicitud por donde llegara la información al servlet
            data: {id: memberIdE}, // Datos a enviar (en este caso, el ID)
            success: function (response) {
                // Manejar la respuesta del servidor si es necesario
            },
            error: function (xhr, status, error) {
                console.error('Error al enviar la solicitud:', error);
            }
        });
    });
</script>
<!-- Script para enviar la confimacion de la edición de la membresia -->
<script>
    $('.editarMembresia').on('click', function () {
        // Obtener el ID de la membresía
        const editarMembresia = $('#boton_editar').val();
        
        // Envío de ID al servlet a través de AJAX (método POST)
        $.ajax({
            url: 'SvEditarMembresia', // Url donde se enviara los datos(en este caso la verificacion de la eliminación de la memrbesia)
            method: 'POST', // Método de solicitud
            data: {boton_editar: editarMembresia}, // Datos a enviar (verificación de la confrimacion para la edicion de la memrbesia)
            success: function (response) {
                // Manejar la respuesta del servidor si es necesario
            },
            error: function (xhr, status, error) {
                console.error('Error al enviar la solicitud:', error);
            }
        });
    });
</script>





