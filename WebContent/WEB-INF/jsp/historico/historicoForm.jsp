<%@page import="br.com.contabilidade.s70.persistence.beans.Historico.HistoricoComplemento"%>
<%@page import="com.sun.tools.xjc.reader.xmlschema.ct.ComplexTypeBindingMode"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.Historico"%>
<%@page import="java.util.Map"%>
<%
	Map<String, Object> mps = (Map<String, Object>) request.getAttribute("it");
	Historico historico = (Historico) mps.get("historico");
%>


<div class="panel panel-primary">
	<div class="panel-body">
	<form role="form" >
		<div id="row">
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
				<label for="formDesc">Complemento</label>
				<input type="checkbox" id="formCompl" class="form-control" <%out.print(historico.hasComplemento() == HistoricoComplemento.SIM? "checked": "");%>> 
			</div>
		</div>
		<div id="row">
			<div class="col-md-4">
				<button type="button" class="btn btn-primary">Salvar&raquo;</button>
				&nbsp;
				<button type="reset" class="btn">Cancel</button>
			</div>
		</div>
	</form>
	</div>
</div>
