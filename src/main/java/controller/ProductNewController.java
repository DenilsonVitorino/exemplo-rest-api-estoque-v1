package controller;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("productnew")
public class ProductNewController {
	
	
	@GET
	@Produces("application/text")
	public String teste() {
		return "Olá mundo!";
	}

}
