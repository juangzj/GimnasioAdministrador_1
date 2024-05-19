<%@page import="Modelo.Usuario"%>
<%@include file="Header.jsp" %>
<div >
    <img src="Imagenes/banner_gimnasio.png" class="img-fluid" alt="">   
</div>
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-12">
                <h2>Detalle Ingreso</h2>

                <div class="row">
                </div>

                <%
                    //LLamado al metodo constructor de usuario para acceder a sus metodos
                    Usuario controladorUsuario = new Usuario();

                    Usuario usuarioInfo = (Usuario) request.getSession().getAttribute("usuarioInfo");
                    if (usuarioInfo != null) {

                %>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="nombre" class="form-label">Nombre:</label>
                        <span id="nombre" class="form-control"><%= usuarioInfo.getNomUsuario()%></span>  </div>
                    <div class="col-md-6 mb-3">
                        <label for="numeroIdentificacion" class="form-label">Número de Identificación:</label>
                        <span id="nombreMembresia" class="form-control"><%= usuarioInfo.getNumIdentificacion()%> </span> </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="nombreMembresia" class="form-label">Nombre Membresía:</label>
                        <span id="nombreMembresia" class="form-control"><%= usuarioInfo.getMembresia().getNomMembresia()%> </span>  </div>
                    <div class="col-md-3 mb-3">
                        <label for="precioMembresia" class="form-label">Precio Membresía:</label>
                        <span id="precioMembresia" class="form-control"><%= usuarioInfo.getMembresia().getPrecio()%></span>  </div>
                    <div class="col-md-3 mb-3">
                        <label for="duracionMembresia" class="form-label">Duración Membresía (Dias):</label>
                        <span id="duracionMembresia" class="form-control"><%= usuarioInfo.getMembresia().getDuracion()%></span>   </div>
                </div>
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="fechaActual" class="form-label">Fecha Actual:</label>
                        <span id="fechaActual" class="form-control"><%= usuarioInfo.getFechaActual()%></span> </div>
                    <div class="col-md-4 mb-3">
                        <label for="fechaInicioMembresia" class="form-label">Fecha Inicio Membresía:</label>
                        <span id="fechaInicioMembresia" class="form-control"><%= usuarioInfo.getFechaInicio()%></span>  </div>
                    <div class="col-md-4 mb-3">
                        <label for="fechaFinalizacionMembresia" class="form-label">Fecha Finalización Membresía:</label>
                        <span id="fechaFinalizacionMembresia" class="form-control"><%= usuarioInfo.getFechaFIn()%></span>  </div>
                </div>
                <div class="row">

                    <label for="estadoMembresia" class="form-label">Estado de Membresía:</label>
                    <span id="estadoMembresia" class="form-control"><%= usuarioInfo.isEstado()%></span>  </div>
                <div class="col-md-4 mb-3">
                </div>
            </div>
            <%
                if (usuarioInfo.isEstado().equals("Activa")) {

            %>
            <h2 style="color: green;">**El usuario puede acceder al gimnasio**</h2>
            <%                } else {
            %>

            <h2 style="color: red;">**El usuario NO puede acceder al gimnasio, Su membresía ha expirado**</h2>

            <%
                }
            } else {
            %>
            <br>
            <h2>No se encontro ningun usuario registrador con el numero de identifiación ingresado</h2>
            <br>
            <%
                }

            %>

        </div>
    </div>
    <br>


    <a href="javascript:history.back()" class="btn btn-secondary mb-3">Regresar</a>


    <%@include file="Foother.jsp" %>
