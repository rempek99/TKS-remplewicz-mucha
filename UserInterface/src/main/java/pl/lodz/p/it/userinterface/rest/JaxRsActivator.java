package pl.lodz.p.it.userinterface.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//@ApplicationScoped
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> resources = new java.util.HashSet<>();
//        resources.add(RestEndpoints.class);
//        return resources;
//    }
}