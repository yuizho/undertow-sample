package io.github.yuizho.undertow;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Path("/hello")
public class HelloResource {
    @Context
    ServletContext servletContext;
    @Context
    HttpServletRequest servletRequest;
    @Context
    HttpServletResponse servletResponse;

    @GET
    @Produces("text/html")
    public String hello() {
        System.out.println("ThreadId: " + Thread.currentThread().getId());
        System.out.println("HashCode: " + System.identityHashCode(this));

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/"); 
        
        templateResolver.setSuffix(".html"); 
        templateResolver.setTemplateMode(TemplateMode.HTML);

        IContext ctx = new WebContext(servletRequest, servletResponse, servletContext);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine.process("hello", ctx);
    }
}
