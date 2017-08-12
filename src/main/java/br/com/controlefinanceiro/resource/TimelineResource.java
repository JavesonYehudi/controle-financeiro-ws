package br.com.controlefinanceiro.resource;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.controlefinanceiro.service.TimelineService;

@Path(value = "/timeline")
public class TimelineResource {

	@Inject
	private TimelineService timelineService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(@QueryParam("date.start") String start, @QueryParam("date.end") String end) {
		try {
			return Response.status(Status.OK).entity(timelineService.buildTimelineItems(LocalDate.parse(start), LocalDate.parse(end))).build();
		} catch (Exception e) {
			String error = "{\"error\" : \"" + e.getMessage() + "\"}";
			return Response.status(Status.FORBIDDEN).entity(error).build();
		}
	}

}
