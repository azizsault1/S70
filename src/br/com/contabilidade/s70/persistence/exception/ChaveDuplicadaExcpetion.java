package br.com.contabilidade.s70.persistence.exception;

import org.eclipse.persistence.exceptions.DatabaseException;

public class ChaveDuplicadaExcpetion extends Exception {

	private static final long serialVersionUID = 1L;

	public ChaveDuplicadaExcpetion(String mensagem) {
		super(mensagem);
	}

	public ChaveDuplicadaExcpetion(final DatabaseException ex) {
		super(ex);
	}

}
