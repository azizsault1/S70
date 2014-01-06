package br.com.contabilidade.s70.bo.validators;

import br.com.contabilidade.s70.bo.exceptions.ValidateException;

public interface Validator<Interface> {

	public void validate(Interface entidade) throws ValidateException;

}
