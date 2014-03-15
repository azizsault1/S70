package br.com.contabilidade.s70.persistence.dao.centrocusto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.contabilidade.s70.persistence.beans.CentroCusto;
import br.com.contabilidade.s70.persistence.beans.CentroCusto.Tipo;

/**
 * The persistent class for the s70t001 database table.
 * 
 */
@Entity
@Table(name = "s70t001")
@NamedQuery(name = "CentroCusto.findAll", query = "SELECT c FROM S70t001 c")
class S70t001 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "S70T01CP01_1")
	private long s70t01cp011;

	@Column(name = "a")
	private final String capitaliza;

	@Column(name = "b")
	private int tipoObra;

	@Column(name = "S70T01CP01_2")
	private int s70t01cp012;

	private String s70t01cp02;

	private String s70t01cp03;

	@Column(name = "S70T01CP051_1")
	private int s70t01cp0511;

	@Column(name = "S70T01CP052_1")
	private BigDecimal s70t01cp0521;

	@Deprecated
	/**
	 * Utilizado apenas pelo JPA
	 */
	public S70t001() {
		this.s70t01cp011 = 0;
		this.s70t01cp02 = "";
		this.s70t01cp0521 = new BigDecimal(0);
		this.capitaliza = "S";
		this.tipoObra = Tipo.OBRA_ANDAMENTO.getValue();

		this.s70t01cp012 = 0;
		this.s70t01cp03 = "";
		this.s70t01cp0511 = 0;

	}

	public S70t001(final CentroCusto centroCusto) {

		this.s70t01cp011 = centroCusto.getCodigo();
		this.s70t01cp02 = centroCusto.getNome();
		this.s70t01cp0521 = centroCusto.getTaxa();
		this.capitaliza = centroCusto.getTipo() == Tipo.OBRA_ENCERRADA ? "N" : "S";
		this.tipoObra = centroCusto.getTipo().getValue();

		this.s70t01cp012 = centroCusto.getSuperintendencia().getCodigo();
		this.s70t01cp03 = centroCusto.getSuperintendencia().getNome();
		this.s70t01cp0511 = centroCusto.getSuperintendencia().getCodigo();

	}

	public long getId() {
		return this.s70t01cp011;
	}

	public void setS70t01cp011(final long s70t01cp011) {
		this.s70t01cp011 = s70t01cp011;
	}

	public Tipo getTipo() {
		return Tipo.valueOf(this.tipoObra);
	}

	public void setB(final int b) {
		this.tipoObra = b;
	}

	public int getSuperintendencia() {
		return this.s70t01cp012;
	}

	public void setS70t01cp012(final int s70t01cp012) {
		this.s70t01cp012 = s70t01cp012;
	}

	public String getS70t01cp02() {
		return this.s70t01cp02;
	}

	public void setS70t01cp02(final String s70t01cp02) {
		this.s70t01cp02 = s70t01cp02;
	}

	public String getNomeSuperintendencia() {
		return this.s70t01cp03;
	}

	public void setS70t01cp03(final String s70t01cp03) {
		this.s70t01cp03 = s70t01cp03;
	}

	public int getS70t01cp0511() {
		return this.s70t01cp0511;
	}

	public void setS70t01cp0511(final int s70t01cp0511) {
		this.s70t01cp0511 = s70t01cp0511;
	}

	public BigDecimal getS70t01cp0521() {
		return this.s70t01cp0521;
	}

	public void setS70t01cp0521(final BigDecimal s70t01cp0521) {
		this.s70t01cp0521 = s70t01cp0521;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + (int) (this.s70t01cp011 ^ (this.s70t01cp011 >>> 32));
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
		final S70t001 other = (S70t001) obj;
		if (this.s70t01cp011 != other.s70t01cp011) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CentroCustoImpl [s70t01cp011=" + this.s70t01cp011 + ", a=" + this.capitaliza + ", b=" + this.tipoObra + ", s70t01cp012=" + this.s70t01cp012
				+ ", s70t01cp02=" + this.s70t01cp02 + ", s70t01cp03=" + this.s70t01cp03 + ", s70t01cp0511=" + this.s70t01cp0511 + ", s70t01cp0521="
				+ this.s70t01cp0521 + "]";
	}

}