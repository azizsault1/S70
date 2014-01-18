package br.com.contabilidade.s70.persistence.dao.centrocusto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.contabilidade.s70.persistence.beans.CentroCusto;

/**
 * The persistent class for the s70t001 database table.
 * 
 */
@Entity
@Table(name = "s70t001")
@NamedQuery(name = "CentroCusto.findAll", query = "SELECT c FROM CentroCustoImpl c")
class CentroCustoImpl implements Serializable, CentroCusto {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "S70T01CP01_1")
	private long s70t01cp011;

	private String a;

	@Column(name = "b")
	private int tipoObra;

	@Column(name = "S70T01CP01_2")
	private int s70t01cp012;

	private String s70t01cp02;

	private String s70t01cp03;

	@Column(name = "S70T01CP051_1")
	private BigDecimal s70t01cp0511;

	@Column(name = "S70T01CP052_1")
	private BigDecimal s70t01cp0521;

	public CentroCustoImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.persistence.dao.centrocusto.CentroCusto#getS70t01cp011()
	 */
	@Override
	public long getId() {
		return this.s70t01cp011;
	}

	public void setS70t01cp011(final long s70t01cp011) {
		this.s70t01cp011 = s70t01cp011;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.persistence.dao.centrocusto.CentroCusto#getA()
	 */
	@Override
	public String isCapitaliza() {
		return this.a;
	}

	public void setA(final String a) {
		this.a = a;
	}

	@Override
	public Tipo getTipo() {
		return Tipo.valueOf(this.tipoObra);
	}

	public void setB(final int b) {
		this.tipoObra = b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.persistence.dao.centrocusto.CentroCusto#getS70t01cp012()
	 */
	@Override
	public int getSuperintendencia() {
		return this.s70t01cp012;
	}

	public void setS70t01cp012(final int s70t01cp012) {
		this.s70t01cp012 = s70t01cp012;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.persistence.dao.centrocusto.CentroCusto#getS70t01cp02()
	 */
	@Override
	public String getS70t01cp02() {
		return this.s70t01cp02;
	}

	public void setS70t01cp02(final String s70t01cp02) {
		this.s70t01cp02 = s70t01cp02;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.persistence.dao.centrocusto.CentroCusto#getS70t01cp03()
	 */
	@Override
	public String getNomeSuperintendencia() {
		return this.s70t01cp03;
	}

	public void setS70t01cp03(final String s70t01cp03) {
		this.s70t01cp03 = s70t01cp03;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.persistence.dao.centrocusto.CentroCusto#getS70t01cp0511()
	 */
	@Override
	public BigDecimal getS70t01cp0511() {
		return this.s70t01cp0511;
	}

	public void setS70t01cp0511(final BigDecimal s70t01cp0511) {
		this.s70t01cp0511 = s70t01cp0511;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.contabilidade.s70.persistence.dao.centrocusto.CentroCusto#getS70t01cp0521()
	 */
	@Override
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
		final CentroCustoImpl other = (CentroCustoImpl) obj;
		if (this.s70t01cp011 != other.s70t01cp011) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CentroCustoImpl [s70t01cp011=" + this.s70t01cp011 + ", a=" + this.a + ", b=" + this.tipoObra + ", s70t01cp012=" + this.s70t01cp012
				+ ", s70t01cp02=" + this.s70t01cp02 + ", s70t01cp03=" + this.s70t01cp03 + ", s70t01cp0511=" + this.s70t01cp0511 + ", s70t01cp0521="
				+ this.s70t01cp0521 + "]";
	}

}