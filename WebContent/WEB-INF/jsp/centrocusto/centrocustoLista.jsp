<%@page import="br.com.contabilidade.s70.persistence.beans.CentroCusto"%>
<%@page import="br.com.contabilidade.s70.resources.ConstResources"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Map"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.Historico"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
				
		$('#dtCentroCusto').dataTable({
	        "oLanguage": {
	            "sLengthMenu": "Mostrar _MENU_ registros por pagina",
	            "sZeroRecords": "Não foi encontrado nenhum registro.",
	            "sInfo": "Mostrando _START_ de _END_ no total de _TOTAL_ registros",
	            "sInfoEmpty": "Mostrando 0 de 0 no total de 0 registros",
	            "sInfoFiltered": "(filtrados do total de _MAX_ registros)"
	        },
			"bJQueryUI": true,
			"sPaginationType": "full_numbers"
	    } );

	} );
</script>

<c:set var="lista" value="${it['CENTRO_CUSTOS']}"/>  

<table cellpadding="0" cellspacing="0" border="0"  id="dtCentroCusto" width="100%">
	<thead>
		<tr>
			<th>Código</th>
			<th>Descrição</th>
			<th>Status</th>	
			<th>Ações</th>			
		</tr>
	</thead>
	<tbody> 
	
		<c:forEach var="centrocusto" items="${lista}">
			<tr class="gradeC">
				<td style="width: 5%;" >
					<c:out value="${centrocusto.codigo}" />
				</td>
				<td style="width: 45%;" ><c:out value="${centrocusto.nome}" /></td>
				<td style="width: 20%;" ><c:out value="${centrocusto.tipo.label}" /></td>
				<td style="min-width: 40px;">
					<button type="button" class="btn btn-primary" onclick="preencher(${centrocusto.codigo});">Selecionar</button>
					<button type="button" class="btn btn-danger" onclick="remover(${centrocusto.codigo});">Remover</button>
				</td>			
			</tr> 
		</c:forEach>
	</tbody>

</table> 	

