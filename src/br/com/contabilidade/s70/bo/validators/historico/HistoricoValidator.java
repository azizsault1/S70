package br.com.contabilidade.s70.bo.validators.historico;

import br.com.contabilidade.s70.bo.validators.Validator;
import br.com.contabilidade.s70.persistence.beans.Historico;

public interface HistoricoValidator extends Validator<Historico> {

	public class Factory{
		public static HistoricoValidator create(){
			return new HistoricoValidatorImpl();
		}
	}
}
