<%@page import="java.util.Map"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.CentroCusto.Tipo"%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.CentroCusto"%>
<%@page import="br.com.contabilidade.s70.resources.ConstResources"%>
<%
	Map<String, Object> mps = (Map<String, Object>) request.getAttribute("it");
	CentroCusto centroCusto = (CentroCusto) mps.get(ConstResources.CENTRO_CUSTO.name());
%>

<div class="panel panel-default">
	<div class="panel-heading"><h4>SU Software - Cadastro de Centros de Custo</h4></div>		
	<div class="panel-body">
		<form>
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<label for="text">Centro de Custo</label>
						<input type="text" class="form-control" id="centroCusto" maxlength="5" value="<%out.print(centroCusto.getId()); %>">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="text">Denominação</label>
						<input type="text" class="form-control" id="Denominação" value="<%out.print(centroCusto.getS70t01cp02()); %>" maxlength="25">
					</div>
				</div>
				<div class="form-group col-md-6" >
					<label for="formDesc" style="width: 100%">Tipo</label>
					<label class="radio-inline">
						<input id="chkObraSup" name="optionsRadios" type="radio" value="Sup" 
						<%out.print(centroCusto.getTipo() == Tipo.ESCRITORIO_SUPERINTENDENCIA? "checked":"");%>
						onclick="checkType('chkObraSup')">Escrit. e Superint.
					</label>
					<label class="radio-inline">
						<input id="radioObra" name="optionsRadios" type="radio" value="Obra" 
						<%out.print(centroCusto.getTipo().isObra() ? "checked":"");%> 
						onclick="checkType('radioObra')">Obra
					</label>
					<label class="radio-inline">
						<input id="radioControleInterno" name="optionsRadios" type="radio" value="CtrInt"
						<%out.print(centroCusto.getTipo() == Tipo.CONTROLE_INTERNO? "checked":"");%>
						onclick="checkType('radioControleInterno')">Controle Interno
					</label>
				</div>
			</div>
			<div class="row" style="margin-bottom:20px">
				<div class="col-md-2">
					<label> Capitaliza </label>
					<div class="btn-group"> 
						<button type="button" class="btn btn-default">Sim</button > 
						<button type="button" class="btn btn-default">Não</button>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<label for="text">Percent. da Adm.</label>
						<input type="number" class="form-control" id="Percentual"  maxlength="5"  step="any">
					</div>
				</div>
				<div id="statusObra" class="form-group col-md-6" <% out.print((centroCusto.getTipo().isObra()? "":"hidden='true'"));%>>
					<label for="formDesc" style="width: 100%">Status</label>
						<label class="radio-inline">
							<input id="radioSim" name="optionsObra" type="radio" value="EmAnd" <%out.print(centroCusto.getTipo() == Tipo.OBRA_ANDAMENTO? "checked":"");%>>Em Andamento
						</label>
					<label class="radio-inline">
						<input id="radioNao" name="optionsObra" type="radio" value="Obra" <%out.print(centroCusto.getTipo() == Tipo.OBRA_ENCERRADA? "checked":"");%>>Encerrada
					</label>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<button type="button" class="btn btn-primary" onclick="save();">Salvar&raquo;</button>
					&nbsp;
					<button type="reset" class="btn">Cancel</button>
				</div>			
			</div>

		</form>
	</div>
</div>