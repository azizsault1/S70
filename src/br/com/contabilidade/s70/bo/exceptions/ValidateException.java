package br.com.contabilidade.s70.bo.exceptions;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidateException extends Exception {

	private static final long serialVersionUID = 1L;

	private final Map<String, List<String>> erros;
	private final Map<String, List<String>> warnings;

	private boolean erro;
	private boolean warning;

	public ValidateException() {
		this.erros = new HashMap<>();
		this.warnings = new HashMap<>();
	}

	public boolean hasErro() {
		return this.erro;
	}

	public boolean hasWarning() {
		return this.warning;
	}

	public Collection<String> getErrors(final String campo) {
		return Collections.unmodifiableCollection(this.search(campo, this.erros));
	}

	public Collection<String> getWarnings(final String campo) {
		return this.search(campo, this.warnings);
	}

	public void addError(final String campo, final String erro) {
		this.putMessage(campo, erro, this.erros);
		this.erro = Boolean.TRUE;
	}

	public void addWarning(final String campo, final String mensagem) {
		this.putMessage(campo, mensagem, this.warnings);
		this.warning = Boolean.TRUE;
	}

	private void putMessage(final String campo, final String message, final Map<String, List<String>> map) {
		final List<String> mensagens = this.search(campo, map);
		mensagens.add(message);
		map.put(campo, mensagens);
	}

	private List<String> search(final String key, final Map<String, List<String>> map) {
		if (map.containsKey(key)) {
			return map.get(key);
		} else {
			return new LinkedList<>();
		}
	}

	public boolean hasMessage() {
		return this.hasErro() || this.hasWarning();
	}

	private Collection<String> getAll(final Map<String, List<String>> map) {
		final List<String> allErros = new LinkedList<>();

		for (final List<String> errorList : map.values()) {
			allErros.addAll(errorList);
		}
		return Collections.unmodifiableCollection(allErros);

	}

	public Collection<String> getAllErrors() {
		final Collection<String> allErros = this.getAll(this.erros);
		return Collections.unmodifiableCollection(allErros);

	}

	public Collection<String> getAllWarnings() {
		final Collection<String> allErros = this.getAll(this.warnings);
		return Collections.unmodifiableCollection(allErros);

	}

	private Collection<String> getFields(final Map<String, List<String>> map) {

		final Set<String> fields = new HashSet<>();

		for (final String item : map.keySet()) {
			fields.add(item);
		}

		return fields;
	}

	public Collection<String> getAllFieldsErro() {
		return this.getFields(this.erros);
	}

	public Collection<String> getAllFieldsWarning() {
		return this.getFields(this.warnings);
	}

	@Override
	public String getMessage() {
		final StringBuilder str = new StringBuilder();
		for (final String message : this.getAllErrors()) {
			str.append(message);
			str.append(System.getProperty("line.separator"));
		}

		return str.toString();
	}

}