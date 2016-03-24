/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.carrocompras.mb;

import co.edu.udistrital.carro.compras.entity.Usuario;
import co.edu.udistrital.carro.compras.session.UsuarioFacadeLocal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.jboss.logging.Logger;

/**
 *
 * @author Leonardo
 */
@ManagedBean
@SessionScoped
public class IndexMB {
    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    // /////////////////////////////////////////////////////////////////////////
    // Logger de la clase
    // /////////////////////////////////////////////////////////////////////////
    private static Logger _logger = Logger.getLogger(IndexMB.class);
    
    private Boolean menuVisible;
    private String email;//usuario de logueo
    private String contrasenia;

    /**
     * Creates a new instance of IndexMB
     */
    public IndexMB() {
    }
    
    @PostConstruct
    public void inint() {
        
    }
    
    public void iniciarSesion() {
        try {
            Usuario usuario = usuarioFacade.usuarioByEmailYPass(email, getMD5(contrasenia));
            System.out.println(""+usuario);
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR, ocurrió un error", ""));
        }
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

    public Boolean getMenuVisible() {
        return menuVisible;
    }

    public void setMenuVisible(Boolean menuVisible) {
        this.menuVisible = menuVisible;
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
