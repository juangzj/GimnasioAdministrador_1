<%-- 
    Document   : index
    Created on : 29/03/2024, 9:47:19 p. m.
    Author     : Usuario 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="Scripts/sweetAlert.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>
        <!-- **************************************** -->        
        <section class="vh-100" style="background-color: #F9FCFF;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col col-xl-10">
                        <div class="card" style="border-radius: 1rem;">
                            <div class="row g-0">
                                <div class="col-md-6 col-lg-5 d-none d-md-block">
                                    <img src= "Imagenes/gimnasio_interfaz_web.png"
                                         class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
                                </div>
                                <div class="col-md-6 col-lg-7 d-flex align-items-center">
                                    <div class="card-body p-4 p-lg-5 text-black">

                                        <form action="SvUsuarioAdministrador" method="POST">

                                            <div class="d-flex align-items-center mb-3 pb-1">
                                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                                <span class="h1 fw-bold mb-0">Gimnasio Adsministrador</span>
                                            </div>

                                            <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Ingrese a su cuenta</h5>
                                            <!-- Entrada para el nombre de usuario -->
                                            <div class="form-outline mb-4">
                                                <input type="text" name="nomUsuario" class="form-control form-control-lg" required/>
                                                <label class="form-label" >Usuario</label>
                                            </div>
                                            <!-- Entrada para la contraseña -->
                                            <div class="form-outline mb-4">
                                                <input type="password" name="contrasenia" class="form-control form-control-lg" />
                                                <label class="form-label" for="form2Example27" required>Contraseña</label>
                                            </div>
                                            <!-- Boton para enviar los datos al servlet -->
                                            <div class="pt-1 mb-4">
                                                <button class="btn btn-dark btn-lg btn-block" type="submit">Ingresar</button>
                                            </div>

                                            <a class="small text-muted" href="#!">¿Olvidó su contraseña?</a>
                                            <a href="#!" class="small text-muted">Terminos de uso.</a>
                                            <a href="#!" class="small text-muted">Privacidad y politicas</a>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>  
     
        <%@include file="Foother.jsp" %>

        <%

            String alertaInicioSesion = (String) request.getSession().getAttribute("alertaInicioSesion");
            System.out.println("la alerta es " + alertaInicioSesion);

            if (alertaInicioSesion != null && !alertaInicioSesion.isEmpty()) {

                if (alertaInicioSesion.equals("false")) {


        %>        
        <script>
            Swal.fire({
                title: "Error",
                text: "No pudo ingresar a la platafroma, por favor intente de nuevo ",
                icon: "error"
            });
        </script>

        <%            }

            }

            request.getSession().removeAttribute("alertaInicioSesion");
        %>

