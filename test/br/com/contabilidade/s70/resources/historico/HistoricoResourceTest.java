package br.com.contabilidade.s70.resources.historico;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.contabilidade.s70.bo.historico.HistoricoBo;
import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.beans.Historico.HistoricoComplemento;
import br.com.contabilidade.s70.persistence.exception.PersistenceException;
import br.com.contabilidade.s70.persistence.exception.PersistenceException.TypeError;
import br.com.contabilidade.s70.resources.ConstResources;

import com.sun.jersey.api.view.Viewable;

public class HistoricoResourceTest {

	private Mockery ctx;
	private HistoricoBo bo;
	private HistoricoResource resource;

	@Before
	public void setup() {
		this.ctx = new Mockery() {
			{
				this.setImposteriser(ClassImposteriser.INSTANCE);
				this.setThreadingPolicy(new Synchroniser());
			}
		};

		this.bo = this.ctx.mock(HistoricoBo.class);
		this.resource = new HistoricoResource(this.bo);
	}

	@After
	public void after() {
		this.ctx.assertIsSatisfied();
	}

	private HistoricoToPersist create(final int id, final HistoricoComplemento complemento) {
		final HistoricoToPersist historico = new HistoricoToPersist();
		historico.setId(id);
		historico.setDescricao(Long.valueOf(id).toString());
		historico.setComplemento(complemento.toSave());

		return historico;
	}

	private Collection<Historico> createList(final int quantidade, final HistoricoComplemento complemento) {

		final Collection<Historico> historicos = new LinkedList<>();

		for (int j = 1; j <= quantidade; j++) {
			historicos.add(this.create(j, complemento));
		}

		return historicos;
	}

	@Test
	public void testSave() {
		try {
			final HistoricoToPersist historico = this.create(1, HistoricoComplemento.SIM);

			this.ctx.checking(new Expectations() {
				{
					this.oneOf(HistoricoResourceTest.this.bo).save(historico);
				}
			});

			final Response response = this.resource.save(historico);
			Assert.assertEquals(200, response.getStatus());

			final List<String> result = this.getMessage(response, ConstResources.SUCESSO);

			Assert.assertFalse(result.isEmpty());
			Assert.assertEquals(1, result.size());
			Assert.assertEquals("Histórico salvo com sucesso.", result.get(0));

		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail("Erro inesperado.");
		}
	}

	private Object getObject(final Response response, final ConstResources chave) {
		final Object responseObject = response.getEntity();

		if (responseObject instanceof Viewable) {
			final Viewable view = (Viewable) responseObject;
			final Object model = view.getModel();

			if (model instanceof Map) {
				@SuppressWarnings("unchecked")
				final Map<String, List<String>> map = (Map<String, List<String>>) model;

				if (map.containsKey(chave.name())) {
					return map.get(chave.name());
				} else {
					throw new IllegalArgumentException("O Map não tinha a chave: " + chave);
				}
			} else {
				throw new IllegalArgumentException("Objeto não é um mapper é um: " + model);
			}

		} else {
			throw new IllegalArgumentException("Objeto não é um Vieable");
		}

	}

	@SuppressWarnings("unchecked")
	private List<String> getMessage(final Response response, final ConstResources chave) {
		return (List<String>) this.getObject(response, chave);
	}

	@Test
	public void testGetNull() {
		final Response response = this.resource.get(null);

		final Object historico = this.getObject(response, ConstResources.HISTORICO);
		Assert.assertNotNull(historico);

		try {
			this.getMessage(response, ConstResources.ERRO);
			Assert.fail("Não era para vir mensagem de erro.");
		} catch (final IllegalArgumentException e) {
			Assert.assertEquals("O Map não tinha a chave: " + ConstResources.ERRO, e.getMessage());
		}
	}

	@Test
	public void testGetNaoEncontrado() {
		try {
			this.ctx.checking(new Expectations() {
				{
					this.oneOf(HistoricoResourceTest.this.bo).get(this.with(Long.valueOf(1)));
					this.will(throwException(new PersistenceException(TypeError.CONSULTA, "Simulando erro do banco")));
				}
			});
		} catch (final PersistenceException e1) {
			Assert.fail("Não era para dar erro aqui.");
		}

		final Response response = this.resource.get("1");

		final Object historico = this.getObject(response, ConstResources.HISTORICO);
		Assert.assertNotNull(historico);

		final List<String> erros = this.getMessage(response, ConstResources.ERRO);
		Assert.assertNotNull(erros);
		Assert.assertEquals(1, erros.size());
		Assert.assertEquals("Simulando erro do banco", erros.get(0));
	}

	@Test
	public void testGetEncontrado() {
		final HistoricoToPersist historico = this.create(1, HistoricoComplemento.NAO);

		try {
			this.ctx.checking(new Expectations() {
				{
					this.oneOf(HistoricoResourceTest.this.bo).get(this.with(Long.valueOf(1)));
					this.will(returnValue(historico));
				}
			});
		} catch (final PersistenceException e1) {
			Assert.fail("Não era para dar erro aqui.");
		}

		final Response response = this.resource.get("1");

		final Object historicoBuscado = this.getObject(response, ConstResources.HISTORICO);
		Assert.assertNotNull(historicoBuscado);
		Assert.assertEquals(historico, historicoBuscado);

	}

	@Test
	public void testGetAll() {

		final Collection<Historico> historicos = this.createList(10, HistoricoComplemento.NAO);

		try {
			this.ctx.checking(new Expectations() {
				{
					this.oneOf(HistoricoResourceTest.this.bo).get();
					this.will(returnValue(historicos));
				}
			});
		} catch (final PersistenceException e1) {
			Assert.fail("Não era para dar erro aqui.");
		}

		final Response response = this.resource.getAll();

		@SuppressWarnings("unchecked")
		final Collection<Historico> historicoBuscado = (Collection<Historico>) this.getObject(response, ConstResources.HISTORICO);
		Assert.assertNotNull(historicoBuscado);

		Assert.assertEquals(10, historicos.size());
	}
}