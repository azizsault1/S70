package br.com.contabilidade.s70.resources.centrocusto;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;

import br.com.contabilidade.s70.persistence.beans.CentroCusto.Tipo;

public class CentroCustoImplTest {

	@Test
	public void toXmlObraAndamento() {

		try {
			final StringWriter sw = new StringWriter();
			final CentroCustoImpl centro = new CentroCustoImpl();
			// create JAXB context and instantiate marshaller
			final JAXBContext context = JAXBContext.newInstance(CentroCustoImpl.class);
			final Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out
			m.marshal(centro, sw);

			//@formatter:off
			final String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + 
										"<centroCustoImpl>\n" + 
										"    <codigo>0</codigo>\n"+ 
										"    <nome></nome>\n" + 
										"    <taxa>4</taxa>\n" + 
										"    <tipo>OBRA_ANDAMENTO</tipo>\n" + 
										"</centroCustoImpl>\n";

			Assert.assertEquals(expected, sw.toString());

		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
	
	
	@Test
	public void toObject(){
		
		try {
			final StringReader xml = new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + 
					"<centroCustoImpl>\n" + 
					"    <codigo>11</codigo>\n"+ 
					"    <nome>Teste</nome>\n" + 
					"    <taxa>5</taxa>\n" + 
					"    <tipo>CtrInt</tipo>\n" + 
					"</centroCustoImpl>\n");
			
			final JAXBContext context = JAXBContext.newInstance(CentroCustoImpl.class);
			final Unmarshaller um = context.createUnmarshaller();
			final CentroCustoImpl centro = (CentroCustoImpl) um.unmarshal(xml);
			Assert.assertEquals("Teste", centro.getNome());
			Assert.assertEquals(Long.valueOf(11).longValue(), centro.getCodigo());
			Assert.assertEquals(new BigDecimal(5), centro.getTaxa());
			Assert.assertEquals(Tipo.CONTROLE_INTERNO, centro.getTipo());
			
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}
	
	@Test
	public void toObjectObraEmAndamento(){
		
		try {
			final StringReader xml = new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + 
					"<centroCustoImpl>\n" + 
					"    <codigo>11</codigo>\n"+ 
					"    <nome>Teste</nome>\n" + 
					"    <taxa>5</taxa>\n" + 
					"    <tipo>Obra</tipo>\n" + 
					"    <status>EmAnd</status>\n" +
					"</centroCustoImpl>\n");
			
			final JAXBContext context = JAXBContext.newInstance(CentroCustoImpl.class);
			final Unmarshaller um = context.createUnmarshaller();
			final CentroCustoImpl centro = (CentroCustoImpl) um.unmarshal(xml);
			Assert.assertEquals("Teste", centro.getNome());
			Assert.assertEquals(Long.valueOf(11).longValue(), centro.getCodigo());
			Assert.assertEquals(new BigDecimal(5), centro.getTaxa());
			Assert.assertEquals(Tipo.OBRA_ANDAMENTO, centro.getTipo());
			
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}
	
	@Test
	public void toObjectObraEncerrada(){
		
		try {
			final StringReader xml = new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + 
					"<centroCustoImpl>\n" + 
					"    <codigo>11</codigo>\n"+ 
					"    <nome>Teste</nome>\n" + 
					"    <taxa>5</taxa>\n" + 
					"    <tipo>Obra</tipo>\n" + 
					"    <status>Encerrada</status>\n" +
					"</centroCustoImpl>\n");
			
			final JAXBContext context = JAXBContext.newInstance(CentroCustoImpl.class);
			final Unmarshaller um = context.createUnmarshaller();
			final CentroCustoImpl centro = (CentroCustoImpl) um.unmarshal(xml);
			Assert.assertEquals("Teste", centro.getNome());
			Assert.assertEquals(Long.valueOf(11).longValue(), centro.getCodigo());
			Assert.assertEquals(new BigDecimal(5), centro.getTaxa());
			Assert.assertEquals(Tipo.OBRA_ENCERRADA, centro.getTipo());
			
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}
	
	@Test
	public void toObjectSuperintendencia(){
		
		try {
			final StringReader xml = new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + 
					"<centroCustoImpl>\n" + 
					"    <codigo>11</codigo>\n"+ 
					"    <nome>Teste</nome>\n" + 
					"    <taxa>5</taxa>\n" + 
					"    <tipo>Sup</tipo>\n" + 
					"</centroCustoImpl>\n");
			
			final JAXBContext context = JAXBContext.newInstance(CentroCustoImpl.class);
			final Unmarshaller um = context.createUnmarshaller();
			final CentroCustoImpl centro = (CentroCustoImpl) um.unmarshal(xml);
			Assert.assertEquals("Teste", centro.getNome());
			Assert.assertEquals(Long.valueOf(11).longValue(), centro.getCodigo());
			Assert.assertEquals(new BigDecimal(5), centro.getTaxa());
			Assert.assertEquals(Tipo.ESCRITORIO_SUPERINTENDENCIA, centro.getTipo());
			
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}
}
