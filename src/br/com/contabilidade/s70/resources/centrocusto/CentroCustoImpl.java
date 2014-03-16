package br.com.contabilidade.s70.resources.centrocusto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.contabilidade.s70.persistence.beans.CentroCusto;
import br.com.contabilidade.s70.persistence.beans.CentroCusto.Superintendencia;

/*
 * codigo: "1" nome: "aaa" status: "EmAnd" taxa: "4" tipo: "Obra"
 */
@XmlRootElement(name = "centroCustoImpl")
class CentroCustoImpl implements CentroCusto {

	@XmlElement(name = "codigo")
	private final long codigo;
	@XmlElement(name = "tipo")
	private final String tipo;
	@XmlElement(name = "taxa")
	private final BigDecimal taxa;
	@XmlElement(name = "nome")
	private final String nome;
	private final SuperintendenciaPadrao superintendencia;
	@XmlElement(name = "status")
	private final String status;

	public CentroCustoImpl() {

		this.codigo = 0;
		this.tipo = "Obra";
		this.status = "EmAnd";
		this.taxa = new BigDecimal(4);
		this.nome = "";
		this.superintendencia = new SuperintendenciaPadrao();

	}

	@Override
	public long getCodigo() {
		return this.codigo;
	}

	@Override
	public Tipo getTipo() {

		if (this.tipo.equalsIgnoreCase("Obra")) {
			return this.status.equalsIgnoreCase("EmAnd") ? Tipo.OBRA_ANDAMENTO : Tipo.OBRA_ENCERRADA;
		}

		if (this.tipo.equalsIgnoreCase("Sup")) {
			return Tipo.ESCRITORIO_SUPERINTENDENCIA;
		}

		return Tipo.CONTROLE_INTERNO;
	}

	@Override
	public Superintendencia getSuperintendencia() {
		return this.superintendencia;
	}

	@Override
	public BigDecimal getTaxa() {
		return this.taxa;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	public String getStatus() {
		return this.status;
	}

	@Override
	public String toString() {
		return "CentroCustoImpl [codigo=" + this.codigo + ", tipo=" + this.getTipo() + ", taxa=" + this.taxa + ", nome=" + this.nome + ", superintendencia="
				+ this.superintendencia + ", status=" + this.status + "]";
	}

}

class SuperintendenciaPadrao implements Superintendencia {

	private final int codigo;
	private final String nome;

	public SuperintendenciaPadrao() {
		this.codigo = 500;
		this.nome = "SVC";
	}

	@Override
	public int getCodigo() {
		return this.codigo;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String toString() {
		return "SuperintendenciaPadrao [codigo=" + this.codigo + ", nome=" + this.nome + "]";
	}

}
