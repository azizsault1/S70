package br.com.contabilidade.s70.bo.validators.centrocusto;

import br.com.contabilidade.s70.bo.validators.Validator;
import br.com.contabilidade.s70.persistence.beans.CentroCusto;

public interface CentroCustoValidator extends Validator<CentroCusto> {

	public class Factory {
		public static CentroCustoValidator create() {
			return new CentroCustoValidatorImpl();
		}
	}
}
