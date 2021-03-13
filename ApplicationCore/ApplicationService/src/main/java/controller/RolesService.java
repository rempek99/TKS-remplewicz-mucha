package controller;

import javax.faces.context.FacesContext;
import java.util.Arrays;

public class RolesService {
    public static boolean isUserInRoles(String[] roles) {
        for (String role : roles) {
            if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role)) {
                return true;
            }
        }

        throw new SecurityException("Access denied! Missing at least one of expected permissions: " + Arrays.toString(roles));
    }
}