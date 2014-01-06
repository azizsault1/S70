package br.com.contabilidade.s70.resources.historico;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.contabilidade.s70.persistence.beans.Historico;

@XmlRootElement(name = "historico")
class HistoricoToPersist implements Historico {

	private long id;
	private String descricao;
	private String complemento;

	public void setComplemento(final String complemento) {
		this.complemento = complemento;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public void setId(final long id) {
		this.id = id;
	}

	@Override
	@XmlElement(name = "id")
	public long getId() {
		return this.id;
	}

	@Override
	@XmlElement(name = "descricao")
	public String getDescricao() {
		return this.descricao;
	}

	@Override
	@XmlElement(name = "complemento")
	public HistoricoComplemento hasComplemento() {
		return HistoricoComplemento.SIM;
	}

}
