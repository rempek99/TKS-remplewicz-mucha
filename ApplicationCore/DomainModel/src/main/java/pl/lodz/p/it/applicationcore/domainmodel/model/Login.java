package pl.lodz.p.it.applicationcore.domainmodel.model;

import pl.lodz.p.it.applicationcore.domainmodel.repositories.AccountRepo;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class Login implements Serializable {
    @Inject
    private AccountRepo accountRepo;

    private String login;
    private String password;

    private String role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private FacesContext facesContext;

    public void submit() throws IOException {

        switch (continueAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
                break;
            case SUCCESS:
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));
                externalContext.redirect(externalContext.getRequestContextPath() + "/whole/index.xhtml");
                break;
            case NOT_DONE:
        }
    }

    private AuthenticationStatus continueAuthentication() {
        return securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams()
                        .credential(new UsernamePasswordCredential(login, password))
        );
    }

    public boolean isAdminAllowedAccess() {
        return FacesContext.getCurrentInstance().getExternalContext().
                isUserInRole("ADMIN");
    }

    public boolean isAdminAndManagerAllowedAccess() {
        return FacesContext.getCurrentInstance().getExternalContext().
                isUserInRole("ADMIN") || FacesContext.getCurrentInstance().getExternalContext().
                isUserInRole("MANAGER");
    }

    public boolean isManagerOrUserAllowedAccess() {
        return FacesContext.getCurrentInstance().getExternalContext().
                isUserInRole("USER") || FacesContext.getCurrentInstance().getExternalContext().
                isUserInRole("MANAGER");
    }

    public boolean isAllUserAllowedAccess() {
        return FacesContext.getCurrentInstance().getExternalContext().
                isUserInRole("ADMIN") || FacesContext.getCurrentInstance().getExternalContext().
                isUserInRole("MANAGER") || FacesContext.getCurrentInstance().getExternalContext().
                isUserInRole("USER");
    }

    public String checkLoggedUserRole() {
        String role = "";
        if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADMIN")) {
            role = "ADMIN";
        }
        if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("MANAGER")) {
            role = "MANAGER";
        }
        if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USER")) {
            role = "USER";
        }
        return role;
    }
    
}
