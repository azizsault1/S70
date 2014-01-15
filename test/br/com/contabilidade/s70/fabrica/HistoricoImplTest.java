/**
 * 
 */
package br.com.contabilidade.s70.fabrica;

import br.com.contabilidade.s70.persistence.beans.Historico;

/**
 * @author azizsault
 * 
 */
final class HistoricoImplTest implements Historico {

	private final long id;
	private final String descricao;
	private final HistoricoComplemento complemento;

	public HistoricoImplTest(final long id, final String descricao, final HistoricoComplemento complemento) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.complemento = complemento;
	}

	@Override
	public HistoricoComplemento hasComplemento() {
		return this.complemento;
	}

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

}
