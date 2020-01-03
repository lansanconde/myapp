package myapp.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myapp.beans.HelloManager;

@WebServlet(displayName = "My JEE 7 Servlet",
        description = "Ma servlet avec annotations",
        name = "hello",
        loadOnStartup = 10,
        urlPatterns = { "/Hello" })
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB(name = "helloManager")
    HelloManager hello;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String data = hello.sayHello();
        response.getWriter().printf("<p>%s</p>", data);
    }

    public HelloManager getHello() {
        return hello;
    }

    public void setHello(HelloManager hello) {
        this.hello = hello;
    }

}