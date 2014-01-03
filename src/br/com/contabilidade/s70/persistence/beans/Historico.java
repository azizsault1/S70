package br.com.contabilidade.s70.persistence.beans;

public interface Historico {

	public enum HistoricoComplemento {
		SIM('S'), NAO('N');

		private final char value;

		private HistoricoComplemento(final char value) {
			this.value = value;

		}

		public String getBancoValue() {
			return String.valueOf(value);
		}

		public static HistoricoComplemento valueBancoOf(final String value) {
			switch (value) {
			case "S":
				return SIM;
			default:
				return NAO;
			}
		}

	}

	public abstract long getId();

	public abstract String getDescricao();

	public abstract HistoricoComplemento hasComplemento();

}