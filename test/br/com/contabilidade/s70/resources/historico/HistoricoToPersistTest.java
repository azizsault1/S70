package br.com.contabilidade.s70.resources.historico;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;

public class HistoricoToPersistTest {

	@Test
	public void toObject() {

		final StringReader json = new StringReader("{\"codigo\":\"3\",\"descricao\":\"RECOLH.INSS -\",\"complemento\":\"S\"}");

		try {
			final JAXBContext jc = JAXBContext.newInstance(HistoricoToPersist.class);

			final Unmarshaller unmarshaller = jc.createUnmarshaller();
			final HistoricoToPersist historico = (HistoricoToPersist) unmarshaller.unmarshal(json);
			System.out.println("HistoricoToPersistTest.toObject() " + historico);
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail("Erro não esperado.");
		}
	}

	@Test
	public void toJson() {
		final HistoricoToPersist historico = new HistoricoToPersist();
		historico.setComplemento("N");
		historico.setId(3);
		historico.setDescricao("RECOLH.INSS -");

		try {
			final JAXBContext jc = JAXBContext.newInstance(HistoricoToPersist.class);

			final Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty("eclipselink.media-type", "application/json");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(historico, System.out);

		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail("Erro não esperado.");
		}

	}
}
