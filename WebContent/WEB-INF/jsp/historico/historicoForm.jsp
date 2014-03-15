<%@page import="br.com.contabilidade.s70.resources.ConstResources"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.Historico.HistoricoComplemento"%>
<%@page import="com.sun.tools.xjc.reader.xmlschema.ct.ComplexTypeBindingMode"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.Historico"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="historico" value="${it['HISTORICO']}"/>

<div class="panel panel-default">
	<div  class="panel-heading">
		<h4>SU Software - Cadastro de Historicos</h4>
	</div>
	<div class="panel-body">
	<form role="form" >
		<div id="row">
			<jsp:include page="../message.jsp" />
			<div class="form-group col-md-2">
				<label for="formCodigo">Código</label> <input
					type="number" class="form-control" id="formCodigo"
					placeholder="Código" value="${historico.id}"
					min="1" size="5">
			</div>
			<div class="form-group col-md-8" >
				<label for="formDesc">Descrição</label> 
				<input type="text" class="form-control" id="formDesc" placeholder="Descrição" size="20" value="${historico.descricao}">
			</div>
			<div class="form-group col-md-2">
				<label for="formDesc" style="width: 100%">Complemento</label>
					<label class="radio-inline">
						<input id="radioSim" name="optionsRadios" type="radio" id="checkSim" value="Sim" <c:if test="${historico.hasComplemento() == 'SIM'}">checked</c:if> >Sim
					</label>
					<label class="radio-inline">
						<input id="radioNao" name="optionsRadios" type="radio" id="checkNao" value="Nao" <c:if test="${historico.hasComplemento() == 'NAO'}">checked</c:if> >Não
					</label>
			</div>
		</div>
		<div id="row">
			<div class="col-md-4">
				<button type="button" class="btn btn-primary" onclick="save();">Salvar&raquo;</button>
				&nbsp;
				<button type="reset" class="btn">Cancel</button>
			</div>
		</div>
	</form>
	</div>
</div>
