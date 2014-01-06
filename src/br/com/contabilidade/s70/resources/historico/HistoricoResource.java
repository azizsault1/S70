package br.com.contabilidade.s70.resources.historico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.contabilidade.s70.persistence.HistoricoFacade;
import br.com.contabilidade.s70.persistence.beans.Historico;

import com.sun.jersey.api.view.Viewable;

@Path("/historico")
public class HistoricoResource {

	private final HistoricoFacade facade;

	public HistoricoResource() {
		this.facade = HistoricoFacade.Factory.create();
	}

	@Path("/home")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response get() {
		return Response.ok(new Viewable("/historico/historico.jsp")).build();
	}

	private Long getId(final String id) {
		return Long.valueOf(id);

	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("{id}")
	public Response get(@PathParam("id") final String id) {

		final Map<String, Object> maps = new HashMap<String, Object>();

		Historico historico;

		try {
			historico = this.facade.get(this.getId(id));

		} catch (final Exception e) {
			historico = new HistoricoImpl();
		}
		System.out.println("HistoricoResource.get() Historico encontrado: " + historico);
		maps.put("historico", historico);
		return Response.ok(new Viewable("/historico/historicoForm.jsp", maps)).build();
	}

	private List<Historico> todos() {
		return new LinkedList<>(this.facade.get());
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getAll() {
		final Map<String, Object> maps = new HashMap<String, Object>();

		final Collection<Historico> lista = this.todos();

		maps.put("historicos", lista);

		return Response.ok(new Viewable("/historico/historicoLista.jsp", maps)).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllJSon() {
		return Response.ok(this.todos()).build();
	}

	class HistoricoImpl implements Historico {

		@Override
		public long getId() {
			return 0;
		}

		@Override
		public String getDescricao() {
			return "";
		}

		@Override
		public HistoricoComplemento hasComplemento() {
			return HistoricoComplemento.NAO;
		}

	}

	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("{id}")
	public Response remove(@PathParam("id") final String id) {
		try {
			final Long idHistorico = this.getId(id);
			this.facade.delete(idHistorico);
			return Response.ok(new Viewable("/historico/historico.jsp")).build();
		} catch (final Exception e) {
			e.printStackTrace();
			final Map<String, Collection<String>> mapErros = new HashMap<>();

			final Collection<String> listErros = new ArrayList<>();
			listErros.add("Erro Desconhecido");

			mapErros.put("Erros", listErros);

			return Response.serverError().entity(new Viewable("/historico/historico.jsp", mapErros)).build();
		}

	}
}
