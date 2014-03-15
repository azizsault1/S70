package br.com.contabilidade.s70.bo.validators.centrocusto;

import java.math.BigDecimal;

import br.com.contabilidade.s70.bo.exceptions.ValidateException;
import br.com.contabilidade.s70.persistence.beans.CentroCusto;
import br.com.contabilidade.s70.persistence.beans.CentroCusto.Tipo;

class CentroCustoValidatorImpl implements CentroCustoValidator {

	private static final String ID_INVALIDO = "Código do Centro de Custo inválido. Deve estar entre: 1 e 99999";
	private static final String NOME_INVALIDO = "O nome do centro de custo não pode ser vazio.";
	private static final String TAXA_INVALIDA_OBRA_ANDAMENTO = "A taxa de administração para uma obra em andamento não pode ser zero.";
	private static final String TAXA_INVALIDA_OBRA_ENCERRADA = "A taxa de administração para uma obra em encerrada tem que ser zero.";

	private static final String FIELD_ID = "codigo";
	private static final String FIELD_NOME = "centroCustoNome";
	private static final String FIELD_TAXA = "percentId";

	@Override
	public void validate(final CentroCusto centroCusto) throws ValidateException {
		final ValidateException validator = new ValidateException();

		final long id = centroCusto.getCodigo();
		if ((id <= 0) || (id > 99999)) {
			validator.addError(FIELD_ID, ID_INVALIDO);
		}

		final String nome = centroCusto.getNome() == null ? "" : centroCusto.getNome();
		final boolean nomeIsEmpty = nome.isEmpty() || nome.trim().isEmpty();

		if (nomeIsEmpty) {
			validator.addError(FIELD_NOME, NOME_INVALIDO);
		}

		try {
			this.validarObra(centroCusto.getTipo(), centroCusto.getTaxa());
		} catch (final IllegalArgumentException e) {
			validator.addError(FIELD_TAXA, e.getMessage());
		}

		if (validator.hasErro()) {
			throw validator;
		}
	}

	private void validarObra(final Tipo tipo, final BigDecimal taxa) {

		final BigDecimal taxaZero = new BigDecimal(0);

		final boolean isObraEmAndamento = tipo == Tipo.OBRA_ANDAMENTO;
		final boolean isTaxaInvalidaObraAndamento = taxa.equals(taxaZero);

		if (isObraEmAndamento && isTaxaInvalidaObraAndamento) {
			throw new IllegalArgumentException(TAXA_INVALIDA_OBRA_ANDAMENTO);
		}

		final boolean isObraEncerrada = tipo == Tipo.OBRA_ENCERRADA;
		final boolean isTaxaInvalidaObraFinalizada = !taxa.equals(taxaZero);

		if (isObraEncerrada && isTaxaInvalidaObraFinalizada) {
			throw new IllegalArgumentException(TAXA_INVALIDA_OBRA_ENCERRADA);
		}

	}
}
