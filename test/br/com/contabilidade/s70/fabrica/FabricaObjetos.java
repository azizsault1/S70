package br.com.contabilidade.s70.fabrica;

import br.com.contabilidade.s70.persistence.beans.Historico;
import br.com.contabilidade.s70.persistence.beans.Historico.HistoricoComplemento;

public interface FabricaObjetos {

	// ERROS PADRAO
	public abstract void erroNaoEsperado();

	public abstract void erroNaoEsperado(Exception e);

	public abstract Historico createHistorico(long id, String descricao, HistoricoComplemento complemento);

	public class Factory {
		public static FabricaObjetos create() {
			return new FabricaObjetosImpl();
		}
	}

}