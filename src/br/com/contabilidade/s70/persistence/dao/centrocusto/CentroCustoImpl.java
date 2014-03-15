package br.com.contabilidade.s70.persistence.dao.centrocusto;

import java.math.BigDecimal;

import br.com.contabilidade.s70.persistence.beans.CentroCusto;

final class CentroCustoImpl implements CentroCusto {

	private final long codigo;
	private final Tipo tipo;
	private final BigDecimal taxa;
	private final String nome;
	private final SuperintendenciaImpl superintendencia;

	public CentroCustoImpl(final S70t001 s70t001) {

		this.codigo = s70t001.getId();
		this.tipo = s70t001.getTipo();
		this.taxa = s70t001.getS70t01cp0521();
		this.nome = s70t001.getS70t01cp02();
		this.superintendencia = new SuperintendenciaImpl(s70t001);

	}

	@Override
	public long getCodigo() {
		return this.codigo;
	}

	@Override
	public Tipo getTipo() {
		return this.tipo;
	}

	@Override
	public BigDecimal getTaxa() {
		return this.taxa;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public Superintendencia getSuperintendencia() {
		return this.superintendencia;
	}

	class SuperintendenciaImpl implements Superintendencia {

		private final int codigo;
		private final String nome;

		public SuperintendenciaImpl(final S70t001 s70t001) {
			this.codigo = s70t001.getSuperintendencia();
			this.nome = s70t001.getNomeSuperintendencia();
		}

		@Override
		public int getCodigo() {
			return this.codigo;
		}

		@Override
		public String getNome() {
			return this.nome;
		}

	}

}
