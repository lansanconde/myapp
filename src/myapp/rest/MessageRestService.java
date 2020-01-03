package myapp.rest;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/ws")
public class MessageRestService {

    /* Premier exemple avec un GET */
   /* @GET()
    @Path("hello")
    public String hello() {
        return "Hello";
    }
    */

    @Path("hello")
    @GET()
    public String hello(@QueryParam("message") @DefaultValue("bye") String message) {
        if (message == null) {
            return "Hello";
        } else {
            return "Hello " + message.toUpperCase();
        }
    }
    

    @PostConstruct
    public void start() {
        System.err.println("start " + this);
    }

    @PreDestroy
    public void stop() {
        System.err.println("stop " + this);
    }
    @POST
    @Path("hello")
    public String helloPost() {
    	return "Form";
  
    }
    
    
 
    
    
    
}