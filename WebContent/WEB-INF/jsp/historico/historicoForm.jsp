<%@page import="br.com.contabilidade.s70.resources.ConstResources"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.Historico.HistoricoComplemento"%>
<%@page import="com.sun.tools.xjc.reader.xmlschema.ct.ComplexTypeBindingMode"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.Historico"%>
<%@page import="java.util.Map"%>


<%
	Map<String, Object> mps = (Map<String, Object>) request.getAttribute("it");
	Historico historico = (Historico) mps.get(ConstResources.HISTORICO.name());
%>

<div class="panel panel-primary">
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
					placeholder="Código" value="<%out.print(historico.getId());%>"
					min="1" size="5">
			</div>
			<div class="form-group col-md-8" >
				<label for="formDesc">Descrição</label> 
				<input type="text" class="form-control" id="formDesc" placeholder="Descrição" size="20" value="<%out.print(historico.getDescricao());%>">
			</div>
			<div class="form-group col-md-2">
				<label for="formDesc" style="width: 100%">Complemento</label>
					<label class="radio-inline">
						<input id="radioSim" name="optionsRadios" type="radio" id="checkSim" value="Sim" <%out.print(historico.hasComplemento() == HistoricoComplemento.SIM? "checked": "");%> >Sim
					</label>
					<label class="radio-inline">
						<input id="radioNao" name="optionsRadios" type="radio" id="checkNao" value="Nao" <%out.print(historico.hasComplemento() == HistoricoComplemento.NAO? "checked": "");%> >Não
					</label>
				<!-- input type="checkbox" id="formCompl" class="form-control" --> 
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
