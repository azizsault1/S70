package br.com.contabilidade.s70.persistence.dao.historico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.com.contabilidade.s70.persistence.beans.Historico;

/**
 * The persistent class for the s70t004 database table.
 * 
 */
@Entity(name = "S70T004")
@NamedQueries({
		//
		@NamedQuery(name = "S70t004.findAll", query = "SELECT s FROM S70T004 s order by s.id"),
		@NamedQuery(name = "S70t004.remove", query = " DELETE FROM S70T004 WHERE id = :id") })
class HistoricoImpl implements Serializable, Historico {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "s70t04cp01")
	private long id;

	@Column(name = "s70t04cp02")
	private String descricao;

	@Column(name = "s70t04cp03")
	private String complemento;

	@Deprecated
	public HistoricoImpl() {
	}

	public HistoricoImpl(final long s70t04cp01, final String s70t04cp02, final String s70t04cp03) {
		super();
		this.id = s70t04cp01;
		this.descricao = s70t04cp02;
		this.complemento = s70t04cp03;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public void setId(final long s70t04cp01) {
		this.id = s70t04cp01;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(final String s70t04cp02) {
		this.descricao = s70t04cp02;
	}

	@Override
	public HistoricoComplemento hasComplemento() {
		return HistoricoComplemento.toLoad(this.complemento);
	}

	public void setComplemento(final String s70t04cp03) {
		this.complemento = s70t04cp03;
	}

	@Override
	public String toString() {
		return "Historico [id=" + this.id + ", descricao=" + this.descricao + ", complemento=" + this.complemento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + (int) (this.id ^ (this.id >>> 32));
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final HistoricoImpl other = (HistoricoImpl) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

}