<%@page import="java.util.ArrayList"%>
<%@page import="com.sun.corba.se.impl.orbutil.closure.Constant"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="br.com.contabilidade.s70.resources.historico.HistoricoResource.ConstResources"%>
<%
	Map<String, Object> mps = (Map<String, Object>) request.getAttribute("it");

	if(mps.containsKey(ConstResources.ERRO)){
		List<String> erros = (List<String>) mps.get(ConstResources.ERRO);
%>	
	<div id="row">
		<div class="col-md-12 alert alert-danger" <%out.print(erros.isEmpty()? "hidden" : "");%> >
		
<%
				out.print("Teste de erro.");
				for(String erro : erros){%>
					<p><%out.print(erro); %></p>
<% 	
				}
%>
		</div>
	</div>
<%
	} 

	if(mps.containsKey(ConstResources.CUIDADO)){
		List<String> cuidados = (List<String>) mps.get(ConstResources.CUIDADO);
	
%>	
	<div id="row">
		<div class="col-md-12 alert alert-warning" <%out.print(cuidados.isEmpty()? "hidden" : "");%> >
		
<%
				for(String cuidado : cuidados){%>
					<p><%out.print(cuidado); %></p>
<% 	
				}
%>
		</div>
	</div>
<%
	} 
	
	if(mps.containsKey(ConstResources.SUCESSO)){
		List<String> sucessos = (List<String>) mps.get(ConstResources.SUCESSO);
	
%>		
	<div id="row">
		<div class="col-md-12 alert alert-success" <%out.print(sucessos.isEmpty()? "hidden" : "");%> >
		
<%
				for(String sucesso : sucessos){%>
					<p><%out.print(sucesso); %></p>
<% 	
				}
%>
		</div>
	</div>
<%
	} 
%>


