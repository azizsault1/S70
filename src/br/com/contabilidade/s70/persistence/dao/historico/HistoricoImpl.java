package br.com.contabilidade.s70.persistence.dao.historico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import br.com.contabilidade.s70.persistence.beans.Historico;

/**
 * The persistent class for the s70t004 database table.
 * 
 */
@Entity(name = "S70T004")
@NamedQuery(name = "S70t004.findAll", query = "SELECT s FROM S70T004 s")
class HistoricoImpl implements Serializable, Historico {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="s70t04cp01")
	private long id;

	@Column(name="s70t04cp02")
	private String descricao;

	@Column(name="s70t04cp03")
	private String complemento;

	@Deprecated
	public HistoricoImpl() {}

	public HistoricoImpl(final long s70t04cp01, final String s70t04cp02, final String s70t04cp03) {
		super();
		this.id = s70t04cp01;
		this.descricao = s70t04cp02;
		this.complemento = s70t04cp03;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.persistence.dao.s70t004.S40T004#getS70t04cp01()
	 */
	@Override
	public long getId() {
		return this.id;
	}

	public void setId(final long s70t04cp01) {
		this.id = s70t04cp01;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.persistence.dao.s70t004.S40T004#getS70t04cp02()
	 */
	@Override
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(final String s70t04cp02) {
		this.descricao = s70t04cp02;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.persistence.dao.s70t004.S40T004#getS70t04cp03()
	 */
	@Override
	public HistoricoComplemento hasComplemento() {
		return HistoricoComplemento.valueBancoOf(this.complemento);
	}

	public void setComplemento(final String s70t04cp03) {
		this.complemento = s70t04cp03;
	}

	@Override
	public String toString() {
		return "Historico [id=" + this.id + ", descricao=" + this.descricao + ", complemento=" + this.complemento + "]";
	}

}