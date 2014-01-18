<%@page import="br.com.contabilidade.s70.resources.ConstResources"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Map"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.Historico"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>

<%
	Map<String, Object> mps = (Map<String, Object>) request.getAttribute("it");
	Collection<Historico> historicos = (Collection<Historico>) mps.get(ConstResources.HISTORICOS.name());

	if(historicos != null && !historicos.isEmpty()){
%>
	<table cellpadding="0" cellspacing="0" border="0" class="display dataTable" id="dtHistoricodtHistorico" width="100%">
		<thead>
			<tr>
				<th>C�digo</th>
				<th>Descri��o</th>
				<th>Complemento</th>
				<th>A��es</th>		
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
					<button type="button" class="btn btn-primary" onclick="preencher(<%out.print(historico.getId());%>);">Selecionar</button>
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
			<b>N�o foi poss�vel encontrar os hist�ricos</b>
	</div>
<%
	}
%>

<script type="text/javascript" charset="utf-8">
		$('#dtHistoricodtHistorico').dataTable({
	        "oLanguage": {
	            "sLengthMenu": "Mostrar _MENU_ registros por pagina",
	            "sZeroRecords": "N�o foi encontrado nenhum registro.",
	            "sInfo": "Mostrando _START_ de _END_ no total de _TOTAL_ registros",
	            "sInfoEmpty": "Mostrando 0 de 0 no total de 0 registros",
	            "sInfoFiltered": "(filtrados do total de _MAX_ registros)"
	        },
			"bJQueryUI": true,
			"sPaginationType": "full_numbers"
	    } );

</script>

