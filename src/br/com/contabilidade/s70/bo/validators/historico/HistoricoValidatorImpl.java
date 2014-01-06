package br.com.contabilidade.s70.bo.validators.historico;

import br.com.contabilidade.s70.bo.exceptions.ValidateException;
import br.com.contabilidade.s70.persistence.beans.Historico;

class HistoricoValidatorImpl implements HistoricoValidator {

	private static final String ID_INVALIDO = "Código do histórico inválido. Deve estar entre: 1 e 99999";
	private static final String DESC_INVALIDO = "Descrição do histórico inválido.";
	private static final String DESC_VAZIO = "Descrição do histórico não pode ser vazio.";
	private static final String COMPL_INVALIDO = "Complemento nulo. Contate o administrador do sistema.";

	private static final String FIELD_ID = "id";
	private static final String FIELD_DESCRICAO = "descricao";
	private static final String FIELD_COMPLEMENTO = "complemento";

	@Override
	public void validate(final Historico historico) throws ValidateException {
		final ValidateException validator = new ValidateException();

		final long id = historico.getId();
		if ((id <= 0) || (id > 99999)) {
			validator.addError(FIELD_ID, ID_INVALIDO);
		}

		final String descricao = historico.getDescricao();

		final boolean descricaoNulo = descricao == null;

		if (descricaoNulo) {
			validator.addError(FIELD_DESCRICAO, DESC_INVALIDO);
			throw validator;
		}

		final String descTrim = descricao.trim();

		if (descTrim.isEmpty()) {
			validator.addError(FIELD_DESCRICAO, DESC_VAZIO);
		}

		if (historico.hasComplemento() == null) {
			validator.addError(FIELD_COMPLEMENTO, COMPL_INVALIDO);
		}

		if (validator.hasMessage()) {
			throw validator;
		}
	}
}
