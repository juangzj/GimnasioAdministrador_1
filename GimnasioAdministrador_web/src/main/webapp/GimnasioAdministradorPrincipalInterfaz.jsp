<%-- 
    Document   : GimnasioAdministradorPrincipalInterfaz
    Created on : 29/03/2024, 9:24:40 a. m.
    Author     : Juan Goyes 
--%>

<%@page import="Modelo.Usuario"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Modelo.Membresia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="Header.jsp" %>
    <%
        //LLamado al metodo constructor de mebresia para acceder a sus metodos(Controlador)
        Membresia controladorMembresia = new Membresia();
        //LLamado al metodo contructor de usuario para acceder a sus metodos(Controlador)
        Usuario controladorUsuario = new Usuario();
        //Obtenemos el contexto del servlet
        ServletContext context = getServletContext();
        //LinkedList donde se guardaran las membresias exsitentes
        LinkedList<Membresia> listaMembresias = controladorMembresia.deserializarListaMembresias(context);
        //LinkedList donde se guardarala lista de usuarios existentes
        LinkedList<Usuario> listaUsuarios = controladorUsuario.deserializarListaUsuarios(context);

    %>
    <!-- Imagen banner de gimnasio -->
    <div >
        <img src="Imagenes/banner_gimnasio.png" class="img-fluid" alt="">   
    </div>

    <div class="container col-md-4 my-3">
        <!-- Barra de busqueda -->
        <div class="input-group my-5 ">


        </div>
    </div>
    <!-- Tabla donde esta la informacion de los usuarios -->
    <div class="container col-md-8 my-3">
        <table class="table table-bordered table-light my-3">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>No. Identificacion</th>
                    <th>Membresia</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Fin</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%                    if (listaUsuarios != null) {
                        for (Usuario usuario : listaUsuarios) {

                %>

                <tr>
                    <td> <%=usuario.getNomUsuario()%> </td>
                    <td> <%=usuario.getNumIdentificacion()%> </td>
                    <td> <%=controladorUsuario.buscarMembresiaDeUnUsuario(listaUsuarios, listaMembresias, usuario)%> </td>
                    <td> <%=usuario.getFechaInicio()%></td>
                    <td> <%=usuario.getFechaFIn()%></td>
                    <td> <%=usuario.isEstado()%> </td>
                    <td>
                        <!---------------------------Iconos----------------------------------------->

                        <!-- Editar  -->
                        <a type="button" class="btn btn-info "  data-id="<%=usuario.getNumIdentificacion()%>" data-bs-toggle="modal" data-bs-target="">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                            </svg> 
                        </a>


                        <!-- Eliminar -->
                        <a type="button" class="btn btn-danger eliminarUsuario-btn" data-id=" <%=usuario.getNumIdentificacion()%>" data-bs-toggle="modal" data-bs-target="#staticBackdropEliminarUsuario">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                            <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"/>
                            </svg>
                        </a>
                        <%
                            if (usuario.isEstado().equals("Vencida")) {
                        %>
                        <a class="btn btn-success eleccion-membresiaPagoUsuario" data-id=" <%=usuario.getNumIdentificacion()%>" type="button"  data-bs-toggle="modal" data-bs-target="#staticBackdropPagoMembresia" >
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-wallet2" viewBox="0 0 16 16">
                            <path d="M12.136.326A1.5 1.5 0 0 1 14 1.78V3h.5A1.5 1.5 0 0 1 16 4.5v9a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 13.5v-9a1.5 1.5 0 0 1 1.432-1.499zM5.562 3H13V1.78a.5.5 0 0 0-.621-.484zM1.5 4a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5z"/>
                            </svg>
                        </a>

                        <%
                            }
                        %>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>

    </div>

    <!-- Modal para agregar un usuario -->
    <div class="modal fade" id="staticBackdropUsuario" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Agregar Usuario</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="SvAniadirUsuario" method="POST">
                        <!-- Entrada para el nombre de la membresia -->
                        <h5>Nombre </h5>
                        <input class="form-control form-control-sm" name="nomUsuario" type="text" placeholder="Nombre del usuario" aria-label=".form-control-sm example">
                        <!-- Entrada para la duracion de la membresia -->
                        <h5>No. Identificación</h5>
                        <input class="form-control form-control-sm" name="identificacion" type="number" placeholder="No. identificación" aria-label=".form-control-sm example" min="1" step="1" required>
                        <!-- Entrada para el precio de la membresia -->
                        <h5>Membresia</h5>
                        <p>El ID de la membresia que se agregara al usuario es: <span id="membresiaIdUsuario"></span></p>
                        <div class="container col-md-8 my-3">   
                            <div style="overflow-x: auto; max-height: 400px;">
                                <table class="table table-bordered table-light my-3">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Membresia</th>
                                            <th>Selección</th>
                                        </tr>
                                    </thead>                            
                                    <tbody>
                                        <%
                                            if (listaMembresias != null) {
                                                for (Membresia membresia : listaMembresias) {
                                        %>
                                        <tr>
                                            <td><%= membresia.getId()%>  </td>
                                            <td> <%= membresia.getNomMembresia()%> </td>
                                            <td> 
                                                <a class=" btn eleccion-membresia" type="button" data-id="<%=membresia.getId()%>">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
                                                    <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"/>
                                                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"/>
                                                    </svg>

                                                </a>
                                            </td>
                                        </tr>
                                        <%
                                                }
                                            }
                                        %>

                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                            <button  type="submit" class="btn btn-primary">Agregar Usuario</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Script para recoger la id de la membresia que se va a asignar un usuario y despues enviarla por ajaxx al servlet -->
    <script>
        // Capturar clic en botón de de eleccion de membresia
        $('.eleccion-membresia').on('click', function () {
            // Obtener el ID de la membresía
            const memberId = $(this).data('id');
            // Mostrar el ID en el modal
            $('#membresiaIdUsuario').text(memberId);

            // Envío de ID al servlet a través de AJAX (método POST)
            $.ajax({
                url: 'SvAniadirUsuario', // Url donde se enviara los datos(en este caso el id)
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
    <!-- Modal para Eliminar un usuario -->
    <div class="modal fade" id="staticBackdropEliminarUsuario" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Eliminar Usuario</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="SvEliminarUsuario" method="POST">
                        <p>¿Estás seguro de que deseas eliminar el Usuario con Identificación: <span id="usuarioIdEliminar"></span>?</p>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                            <button  type="submit" id="boton_eliminarUsuario" value="eliminarUsuario" class="btn btn-primary eliminarUsuario">Eliminar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Script para recoger la id del usuario que se va a eliminar y despues enviarla por ajaxx al servlet -->
    <script>
        // Captur clic y mandar el numero de identificacion para la eliminacion del usuario
        $('.eliminarUsuario-btn').on('click', function () {
            // Obtener el ID de la membresía
            const memberId = $(this).data('id');
            // Mostrar el ID en el modal de eliminación
            $('#usuarioIdEliminar').text(memberId);

            // Envío de ID al servlet a través de AJAX (método POST)
            $.ajax({
                url: 'SvEliminarUsuario', // Url donde se enviara los datos(en este caso el id)
                method: 'POST', // Método de solicitud por donde llegara la información al servlet
                data: {identificacion: memberId}, // Datos a enviar (en este caso, el ID)
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
        $('.eliminarUsuario').on('click', function () {
            // Obtener la identificacion del usuario
            const eliminarUsuario = $('#boton_eliminarUsuario').val();

            // Envío de ID al servlet a través de AJAX (método POST)
            $.ajax({
                url: 'SvEliminarUsuario', // Url donde se enviara los datos(en este caso la verificacion de la eliminación del usuario)
                method: 'POST', // Método de solicitud
                data: {eliminarConfirmacion: eliminarUsuario}, // Datos a enviar (verificación para la eliminación del usuario)
                success: function (response) {
                    // Manejar la respuesta del servidor si es necesario
                },
                error: function (xhr, status, error) {
                    console.error('Error al enviar la solicitud:', error);
                }
            });
        });
    </script>
    <!-- Modal para realizar el pago de la membresia -->
    <div class="modal fade" id="staticBackdropPagoMembresia" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Pago Membresia</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>El ID del usuario que va a realizar el pago es: <span id="IdUsuarioPago"></span></p>
                    <p>El ID de la membresia que se agregara al usuario es: <span id="membresiaIdPago"></span></p>
                    <div class="container col-md-8 my-3">   
                        <div style="overflow-x: auto; max-height: 400px;">
                            <table class="table table-bordered table-light my-3">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Membresia</th>
                                        <th>Selección</th>
                                    </tr>
                                </thead>                            
                                <tbody>
                                    <%
                                        if (listaMembresias != null) {
                                            for (Membresia membresia : listaMembresias) {
                                    %>
                                    <tr>
                                        <td><%= membresia.getId()%>  </td>
                                        <td> <%= membresia.getNomMembresia()%> </td>
                                        <td> 
                                            <a class=" btn eleccion-membresiaPago" type="button" data-id="<%=membresia.getId()%>">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
                                                <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"/>
                                                <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4"/>
                                                </svg>

                                            </a>
                                        </td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>

                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="button" class="btn btn-primary">Realizar Pago</button>
                </div>
            </div>
        </div>
    </div>
    <%@include file="Foother.jsp" %>

    <!-- Script para recoger la id de la membresia que se va a asignar un usuario en la realizacion del pago  despues enviarla por ajaxx al servlet -->
    <script>
        // Capturar clic en botón de de eleccion de membresia
        $('.eleccion-membresiaPago').on('click', function () {
            // Obtener el ID de la membresía
            const memberIdPago = $(this).data('id');
            // Mostrar el ID en el modal
            $('#membresiaIdPago').text(memberIdPago);

            // Envío de ID al servlet a través de AJAX (método POST)
            $.ajax({
                url: 'SvPago', // Url donde se enviara los datos(en este caso el id)
                method: 'POST', // Método de solicitud por donde llegara la información al servlet
                data: {idMembresiaPago: memberIdPago}, // Datos a enviar (en este caso, el ID)
                success: function (response) {
                    // Manejar la respuesta del servidor si es necesario
                },
                error: function (xhr, status, error) {
                    console.error('Error al enviar la solicitud:', error);
                }
            });
        });
    </script>
    <!-- Script para recoger la id de la membresia que se va a asignar un usuario y despues enviarla por ajaxx al servlet -->
    <script>
        // Capturar clic en botón de de eleccion de membresia
        $('.eleccion-membresiaPagoUsuario').on('click', function () {
            // Obtener el ID de la membresía
            const memberIdPagoUsuario = $(this).data('id');
            // Mostrar el ID en el modal
            $('#IdUsuarioPago').text(memberIdPagoUsuario);

            // Envío de ID al servlet a través de AJAX (método POST)
            $.ajax({
                url: 'SvPago', // Url donde se enviara los datos(en este caso el id)
                method: 'POST', // Método de solicitud por donde llegara la información al servlet
                data: {idPagoUsuario: memberIdPagoUsuario}, // Datos a enviar (en este caso, el ID)
                success: function (response) {
                    // Manejar la respuesta del servidor si es necesario
                },
                error: function (xhr, status, error) {
                    console.error('Error al enviar la solicitud:', error);
                }
            });
        });
    </script>
