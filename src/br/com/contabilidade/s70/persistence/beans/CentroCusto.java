package br.com.contabilidade.s70.persistence.beans;

import java.math.BigDecimal;

public interface CentroCusto {

	public enum Tipo {
		ESCRITORIO_SUPERINTENDENCIA("Escrit. e Superint."), OBRA_ANDAMENTO("Obra em andamento"), CONTROLE_INTERNO("Controle Interno"), OBRA_ENCERRADA(
				"Obra encerrada");

		private final String label;

		private Tipo(final String label) {
			this.label = label;

		}

		public String getLabel() {
			return label;
		}

		public boolean isObra() {
			if ((this == OBRA_ANDAMENTO) || (this == OBRA_ENCERRADA)) {
				return Boolean.TRUE;
			}

			return Boolean.FALSE;
		}

		public static Tipo valueOf(final int valor) {
			switch (valor) {
			case 1:
				return ESCRITORIO_SUPERINTENDENCIA;
			case 3:
				return CONTROLE_INTERNO;
			case 2:
				return OBRA_ANDAMENTO;
			default:
				return OBRA_ENCERRADA;
			}
		}
	}

	public abstract long getId();

	public abstract String isCapitaliza();

	public abstract Tipo getTipo();

	public abstract int getSuperintendencia();

	public abstract String getS70t01cp02();

	public abstract String getNomeSuperintendencia();

	public abstract BigDecimal getS70t01cp0511();

	public abstract BigDecimal getS70t01cp0521();

}