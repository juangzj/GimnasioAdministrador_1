<%@include file="Header.jsp" %>
<!-- Imagen banner de gimnasio -->
<div >
    <img src="Imagenes/banner_gimnasio.png" class="img-fluid" alt="">   
</div>
<div class="container">
    <div class="row mt-5">
        <div class="col-md-6 mx-auto">
            <div class="card">
                <div class="card-header">
                    Ingreso al gimnasio
                </div>
                <div class="card-body">
                    <form action="SvResultadoIngreso" method="POST">
                        <div class="mb-3">
                            <label for="numeroIdentificacion" class="form-label">Ingrese su numero de identificación:</label>
                            <input type="number" class="form-control" id="numeroIdentificacion" name="cedulaIngreso" required>
                        </div>
                        <button type="submit" class="btn btn-primary ingresar">Ingresar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="Foother.jsp" %>
