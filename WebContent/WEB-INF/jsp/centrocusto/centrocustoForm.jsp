<%@page import="java.util.Map"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.CentroCusto.Tipo"%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.CentroCusto"%>
<%@page import="br.com.contabilidade.s70.resources.ConstResources"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ccusto" value="${it['CENTRO_CUSTO']}"/> 

<div class="panel panel-default">
	<div class="panel-heading"><h4>SU Software - Cadastro de Centros de Custo</h4></div>		
	<div class="panel-body">
		<form>
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<label for="text">Centro de Custo</label>
						<input type="text" class="form-control" id="centroCustoId" maxlength="5" value="${ccusto.codigo}">
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="text">Nome</label>
						<input type="text" class="form-control" id="centroCustoNome" value="${ccusto.nome}" maxlength="25">
					</div>
				</div>
				<div class="form-group col-md-6" >
					<label for="formDesc" style="width: 100%">Tipo</label>
					
					<label class="radio-inline">
						<input id="chkObraSup" name="optionsRadios" type="radio" value="Sup" 
						<c:if test="${ccusto.tipo ==  'ESCRITORIO_SUPERINTENDENCIA'}">checked</c:if>
						onclick="checkType('chkObraSup')">Escrit. e Superint.
					</label>
					<label class="radio-inline">
						<input id="radioObra" name="optionsRadios" type="radio" value="Obra"
						<c:if test="${ccusto.tipo.obra}">checked</c:if>
						onclick="checkType('radioObra')">Obra
					</label>
					<label class="radio-inline">
						<input id="radioControleInterno" name="optionsRadios" type="radio" value="CtrInt"
						<c:if test="${ccusto.tipo ==  'CONTROLE_INTERNO'}">checked</c:if>
						onclick="checkType('radioControleInterno')">Controle Interno
					</label>
				</div>
			</div>
			<div class="row" style="margin-bottom:20px">
				<div class="col-md-2">
					<div class="form-group" id="percentAdm" <c:if test="${!ccusto.tipo.obra}">hidden='true'</c:if>>
						<label for="number">Percent. da Adm.</label>
						<input type="number" class="form-control" id="percentId"  maxlength="5"  step="any" value="${ccusto.taxa }">
					</div>
				</div>
				<div id="statusObra" class="form-group col-md-6" <c:if test="${!ccusto.tipo.obra}">hidden='true'</c:if>>
					<label for="formDesc" style="width: 100%">Status</label>
						<label class="radio-inline">
							<input id="radioSim" name="optionsObra" type="radio" value="EmAnd" <c:if test="${ccusto.tipo == 'OBRA_ANDAMENTO'}">checked</c:if>>Em Andamento
						</label>
					<label class="radio-inline">
						<input id="radioNao" name="optionsObra" type="radio" value="Encerrada" <c:if test="${ccusto.tipo == 'OBRA_ENCERRADA'}">checked</c:if>>Encerrada
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