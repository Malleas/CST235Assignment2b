package business;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/verses")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class VersesService {
	
	@Inject VersesDataService versesDataService;
	
	@GET
	@Path("/getverse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerse(
			@QueryParam("inputString")String inputString) throws Exception {
		return versesDataService.getVerse(inputString);
	}
	
	
	@GET
	@Path("/test")
	public Response getTest(
			@QueryParam("test")String test) {
			System.out.println(test);
		
		return Response
				.status(200)
				.entity("getTest is called, input: " + test)
				.build();
		
	}
	

}
