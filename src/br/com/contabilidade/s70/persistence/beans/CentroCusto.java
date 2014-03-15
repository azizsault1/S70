package br.com.contabilidade.s70.persistence.beans;

import java.math.BigDecimal;

public interface CentroCusto {

	public enum Tipo {
		ESCRITORIO_SUPERINTENDENCIA("Escrit. e Superint.", 1), OBRA_ANDAMENTO("Obra em andamento", 2), CONTROLE_INTERNO("Controle Interno", 3), OBRA_ENCERRADA(
				"Obra encerrada", 9);

		private final String label;
		private final int value;

		private Tipo(final String label, final int value) {
			this.label = label;
			this.value = value;

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

		public int getValue() {
			return value;
		}
	}

	public long getCodigo();

	public Tipo getTipo();

	public BigDecimal getTaxa();

	public String getNome();

	public Superintendencia getSuperintendencia();

	public interface Superintendencia {
		public int getCodigo();

		public String getNome();
	}

}