<%@page import="br.com.contabilidade.s70.resources.ConstResources"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sun.corba.se.impl.orbutil.closure.Constant"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	if (request != null && request.getAttribute("it") != null) {
		Map<String, Object> mps = (Map<String, Object>) request.getAttribute("it");

		if (mps.containsKey(ConstResources.ERRO.name())) {
			Collection<String> erros = (Collection<String>) mps.get(ConstResources.ERRO.name());
%>	
	<div id="row">
		<div class="col-md-12 alert alert-danger" <%out.print(erros.isEmpty()? "hidden" : "");%> >
		
<%
			for (String erro : erros) {
%>
					<p><%out.print(erro); %></p>
<%
			}
%>
		</div>
	</div>
<%
		} 

		if(mps.containsKey(ConstResources.CUIDADO.name())){
			Collection<String> cuidados = (Collection<String>) mps.get(ConstResources.CUIDADO.name());
	
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
	
		if (mps.containsKey(ConstResources.SUCESSO.name())) {
			Collection<String> sucessos = (Collection<String>) mps.get(ConstResources.SUCESSO.name());
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
	}
%>


