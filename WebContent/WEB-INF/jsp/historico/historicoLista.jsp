<%@page import="br.com.contabilidade.s70.resources.ConstResources"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Map"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.Historico"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>

<div class="panel panel-default">
<div class="panel-body">

<%
	Map<String, Object> mps = (Map<String, Object>) request.getAttribute("it");
	Collection<Historico> historicos = (Collection<Historico>) mps.get(ConstResources.HISTORICOS.name());

	if(historicos != null && !historicos.isEmpty()){
%>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Código</th>
				<th>Descrição</th>
				<th>Complemento</th>
				<th>Ações</th>		
			</tr>
		</thead>
		<tbody>
<%
	for(Historico historico : historicos){
%>
			<tr>
				<td><%
					out.print(historico.getId());
				%></td>
				<td style="width: 500px;"><%
					out.print(historico.getDescricao());
				%></td>
				<td><%
					out.print(historico.hasComplemento());
				%></td>
				<td>
					<button type="button" class="btn btn-primary" onclick="preencherHistorico(<%out.print(historico.getId());%>);">Selecionar</button>
					<button type="button" class="btn btn-danger" onclick="remover(<%out.print(historico.getId());%>);">Remover</button>
				</td>			
			</tr>
<% 		} %>
		</tbody>
	
	</table>
<%		
	} else { 
%>		
		<div class="liberacao-tit">
				<b>Não foi possível encontrar os históricos</b>
		</div>
<%
	}
%>
</div>
</div>
