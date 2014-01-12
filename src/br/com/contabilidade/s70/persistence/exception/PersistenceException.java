package br.com.contabilidade.s70.persistence.exception;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = 1L;
	private final TypeError type;

	public enum TypeError {
		DATA_CREATE, REMOVE, SALVAR, ALTERACAO, CONSULTA, CONSULTA_VAZIA, TRANSACAO, CHAVE_DUPLICADA;
	}

	public PersistenceException(final TypeError type, final Throwable ex) {
		super(ex);
		this.type = type;
	}

	public PersistenceException(final TypeError type, final String message) {
		super(message);
		this.type = type;
	}

	public PersistenceException(final TypeError type, final String message, final Exception e) {
		super(message, e);
		this.type = type;
	}

	public TypeError getType() {
		return this.type;
	}

}
