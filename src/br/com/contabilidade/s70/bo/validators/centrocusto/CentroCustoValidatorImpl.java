package br.com.contabilidade.s70.bo.validators.centrocusto;

import br.com.contabilidade.s70.bo.exceptions.ValidateException;
import br.com.contabilidade.s70.persistence.beans.CentroCusto;

class CentroCustoValidatorImpl implements CentroCustoValidator {

	private static final String ID_INVALIDO = "Código do histórico inválido. Deve estar entre: 1 e 99999";
	private static final String DESC_INVALIDO = "Descrição do histórico inválido.";
	private static final String DESC_VAZIO = "Descrição do histórico não pode ser vazio.";
	private static final String COMPL_INVALIDO = "Complemento nulo. Contate o administrador do sistema.";

	private static final String FIELD_ID = "id";
	private static final String FIELD_DESCRICAO = "descricao";
	private static final String FIELD_COMPLEMENTO = "complemento";

	@Override
	public void validate(final CentroCusto centroCusto) throws ValidateException {
		final ValidateException validator = new ValidateException();

		final long id = centroCusto.getS70t01cp011();
		if ((id <= 0) || (id > 99999)) {
			validator.addError(FIELD_ID, ID_INVALIDO);
		}

	}
}
