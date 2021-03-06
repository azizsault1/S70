package br.com.contabilidade.s70.resources.centrocusto;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
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

import br.com.contabilidade.s70.bo.centrocusto.CentroCustoBo;
import br.com.contabilidade.s70.bo.exceptions.ValidateException;
import br.com.contabilidade.s70.persistence.beans.CentroCusto;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.facade.centrocusto.CentroCustoFacade.ReturnSaved;
import br.com.contabilidade.s70.resources.ConstResources;

import com.sun.jersey.api.view.Viewable;

@Path("/centroCusto")
public class CentroCustoResource {

	public static final String ERRO_INESPERADO = "Erro inesperado. Contate o administrador do sistema.";

	private final CentroCustoBo bo;

	public CentroCustoResource() {
		this(CentroCustoBo.Factory.create());
	}

	public CentroCustoResource(final CentroCustoBo bo) {
		this.bo = bo;
	}

	@Path("/home")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response get() {
		return Response.ok(new Viewable("/centrocusto/centrocusto.jsp")).build();
	}

	private Long getId(final String id) {
		return Long.valueOf(id);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public Response save(final CentroCustoImpl centroCusto) {

		System.out.println("CentroCustoResource.save() centroCusto: " + centroCusto);
		final Map<String, Object> maps = new HashMap<String, Object>();
		final Collection<String> messages = new LinkedList<>();

		CentroCusto ccusto = centroCusto == null ? new CentroCustoImpl() : centroCusto;

		try {

			final ReturnSaved returned = this.bo.save(ccusto);
			messages.add(returned.getMessage());
			ccusto = returned.getCentroCusto();
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

		maps.put(ConstResources.CENTRO_CUSTO.name(), ccusto);
		return Response.ok(new Viewable("/centrocusto/centrocustoForm.jsp", maps)).build();
	}

	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("{id}")
	public Response remove(@PathParam("id") final String id) {
		System.out.println("CentroCustoResource.remove() " + id);
		final Map<String, Object> maps = new HashMap<String, Object>();

		final Collection<String> menssagem = new LinkedList<>();

		try {
			final Long idCentroCusto = this.getId(id);
			this.bo.delete(idCentroCusto);

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
	public Response getCentro(@PathParam("id") final String id) {

		final Map<String, Object> maps = new HashMap<String, Object>();
		final Collection<String> menssagem = new LinkedList<>();

		CentroCusto centroCusto = new CentroCustoImpl();

		try {
			centroCusto = this.bo.get(this.getId(id));
		} catch (final NumberFormatException e) {
			// Caso em que eu não faço nada, o sistema so quer um historico.
		} catch (final PersistenceException e) {
			menssagem.add(e.getMessage());
			maps.put(ConstResources.ERRO.name(), menssagem);
		} catch (final Exception e) {
			menssagem.add("Erro inesperado. Contate o administrador do sistema.");
			maps.put(ConstResources.ERRO.name(), menssagem);
		}

		maps.put(ConstResources.CENTRO_CUSTO.name(), centroCusto);
		return Response.ok(new Viewable("/centrocusto/centrocustoForm.jsp", maps)).build();
	}

	private Collection<CentroCusto> todos() throws PersistenceException {
		return this.bo.get();
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getAll() {
		final Map<String, Object> maps = new HashMap<String, Object>();
		final Collection<String> menssagem = new LinkedList<>();

		Collection<CentroCusto> lista = Collections.emptyList();

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
			return Response.ok(new Viewable("/centrocusto/centrocustoLista.jsp", maps)).build();

		}
		maps.put(ConstResources.CENTRO_CUSTOS.name(), lista);
		return Response.ok(new Viewable("/centrocusto/centrocustoLista.jsp", maps)).build();
	}

}
