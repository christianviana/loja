package br.com.queroquero.loja.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class GreetingResource {

	@GET
	public String hello() {
		return "hello";
	}
}