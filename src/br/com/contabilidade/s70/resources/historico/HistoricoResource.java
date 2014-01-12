package br.com.contabilidade.s70.resources.historico;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.contabilidade.s70.bo.exceptions.ValidateException;
import br.com.contabilidade.s70.bo.historico.HistoricoBo;
import br.com.contabilidade.s70.persistence.HistoricoFacade.ReturnSaved;
import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;

import com.sun.jersey.api.view.Viewable;

@Path("/historico")
public class HistoricoResource {

	public static final String ERRO_INESPERADO = "Erro inesperado. Contate o administrador do sistema.";

	public enum ConstResources {
		SUCESSO, ERRO, CUIDADO, HISTORICO, HISTORICOS;
	}

	private final HistoricoBo bo;

	public HistoricoResource() {
		this(HistoricoBo.Factory.create());
	}

	public HistoricoResource(final HistoricoBo historico) {
		this.bo = historico;
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public Response save(final HistoricoToPersist historico) {
		final Map<String, Object> maps = new HashMap<String, Object>();
		final Collection<String> messages = new LinkedList<>();

		Historico hist = new HistoricoImpl();

		try {

			final ReturnSaved returned = this.bo.save(historico);
			messages.add(returned.getMessage());
			hist = returned.getHistorico();
			maps.put(ConstResources.SUCESSO.name(), messages);

		} catch (final PersistenceException e) {
			e.printStackTrace();
			messages.add(e.getMessage());
			maps.put(ConstResources.ERRO.name(), messages);

		} catch (final ValidateException e) {
			System.err.println("HistoricoResource.save(): " + e.getAllErrors());
			messages.addAll(e.getAllErrors());
			maps.put(ConstResources.ERRO.name(), messages);

		} catch (final Exception e) {
			System.err.println("HistoricoResource.save()" + e);
			messages.add(ERRO_INESPERADO);
			maps.put(ConstResources.ERRO.name(), messages);

		}

		maps.put(ConstResources.HISTORICO.name(), hist);
		return Response.ok(new Viewable("/historico/historicoForm.jsp", maps)).build();
	}

	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("{id}")
	public Response remove(@PathParam("id") final String id) {

		final Map<String, Object> maps = new HashMap<String, Object>();
		final Collection<String> menssagem = new LinkedList<>();

		try {
			final Long idHistorico = this.getId(id);
			this.bo.delete(idHistorico);

		} catch (final PersistenceException e) {
			e.printStackTrace();
			menssagem.add(e.getMessage());
			maps.put(ConstResources.ERRO.name(), menssagem);

		} catch (final Exception e) {
			e.printStackTrace();
			menssagem.add(ERRO_INESPERADO);
			maps.put(ConstResources.ERRO.name(), menssagem);

		}

		return Response.ok(new Viewable("/historico/historico.jsp", maps)).build();

	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("{id}")
	public Response get(@PathParam("id") final String id) {

		final Map<String, Object> maps = new HashMap<String, Object>();
		final Collection<String> menssagem = new LinkedList<>();

		Historico historico = new HistoricoImpl();

		try {
			historico = this.bo.get(this.getId(id));
		} catch (final NumberFormatException e) {
			// Caso em que eu não faço nada, o sistema so quer um historico.
		} catch (final PersistenceException e) {
			menssagem.add(e.getMessage());
			maps.put(ConstResources.ERRO.name(), menssagem);
		} catch (final Exception e) {
			menssagem.add("Erro inesperado. Contate o administrador do sistema.");
			maps.put(ConstResources.ERRO.name(), menssagem);
		}

		maps.put(ConstResources.HISTORICO.name(), historico);
		return Response.ok(new Viewable("/historico/historicoForm.jsp", maps)).build();
	}

	private List<Historico> todos() throws PersistenceException {
		return new LinkedList<>(this.bo.get());
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getAll() {
		final Map<String, Object> maps = new HashMap<String, Object>();
		final Collection<String> menssagem = new LinkedList<>();

		Collection<Historico> lista = Collections.emptyList();

		try {
			lista = this.todos();
		} catch (final PersistenceException e) {
			System.err.println(e.getMessage());
			menssagem.add(e.getMessage());
			maps.put(ConstResources.ERRO.name(), menssagem);

		} catch (final Exception e) {
			e.printStackTrace();
			menssagem.add(ERRO_INESPERADO);
			maps.put(ConstResources.ERRO.name(), menssagem);
			return Response.ok(new Viewable("/historico/historicoLista.jsp", maps)).build();

		}
		maps.put(ConstResources.HISTORICOS.name(), lista);
		return Response.ok(new Viewable("/historico/historicoLista.jsp", maps)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllJSon() {

		try {
			return Response.ok(this.todos()).build();
		} catch (final PersistenceException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} catch (final Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ERRO_INESPERADO).build();
		}

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

}
