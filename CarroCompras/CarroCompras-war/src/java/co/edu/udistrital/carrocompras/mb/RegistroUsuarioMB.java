/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.carrocompras.mb;

import co.edu.udistrital.carro.compras.entity.Rol;
import co.edu.udistrital.carro.compras.entity.Usuario;
import co.edu.udistrital.carro.compras.session.UsuarioFacadeLocal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.jboss.logging.Logger;

/**
 *
 * @author lreyes
 */
@ManagedBean
@RequestScoped
public class RegistroUsuarioMB {

    // /////////////////////////////////////////////////////////////////////////
    // Logger de la clase
    // /////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    private static Logger _logger = Logger.getLogger(RegistroUsuarioMB.class);

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private String nombres;
    private String apellidos;
    private String email;
    private String contrasenia;

    /**
     * Creates a new instance of RegistroUsuario
     */
    public RegistroUsuarioMB() {
    }

    /**
     * Metodo que inicializa el backing bean
     */
    @PostConstruct
    public void init() {

    }

    public String guardarUsuario() {
        try {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsrNombres(nombres);
            nuevoUsuario.setUsrApellidos(apellidos);
            nuevoUsuario.setUsrEmail(email);
            nuevoUsuario.setUsrContrasenia(getMD5(contrasenia));
            //Asociar rol
            Rol rol = new Rol();
            rol.setRolId(2);
            nuevoUsuario.setRolId(rol);
            usuarioFacade.create(nuevoUsuario);
            _logger.info("Usuario creado");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", ""));
            return "guardar";
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR, no pudo ser guardado el registro", ""));
            return "error";
        }
    }

    public void salir() {

    }

    private String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}
