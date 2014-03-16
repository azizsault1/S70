<%@page import="br.com.contabilidade.s70.resources.ConstResources"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sun.corba.se.impl.orbutil.closure.Constant"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${it != null}">
	<c:if test="${it['ERRO'] != null}">
		<c:set var="erros" value="${it['ERRO']}"/>
		<div id="row">
			<div class="col-md-12 alert alert-danger"  <c:if test="${erros.isEmpty()}">hidden</c:if>>
				<c:forEach var="erro" items="${erros}">
					<c:out value="${erro}" />
					<br>
				</c:forEach>
			</div>
		</div>
	</c:if>
	
	<c:if test="${it['CUIDADO'] != null}">
		<c:set var="cuidados" value="${it['CUIDADO']}"/>
		<div id="row">
			<div class="col-md-12 alert alert-warning"  <c:if test="${cuidados.isEmpty()}">hidden</c:if>>
				<c:forEach var="cuidado" items="${cuidados}">
					<c:out value="${cuidado}" />
					<br>
				</c:forEach>
			</div>
		</div>
	</c:if>
	
		<c:if test="${it['SUCESSO'] != null}">
		<c:set var="sucessos" value="${it['SUCESSO']}"/>
		<div id="row">
			<div class="col-md-12 alert alert-success"  <c:if test="${sucessos.isEmpty()}">hidden</c:if>>
				<c:forEach var="sucesso" items="${sucessos}">
					<c:out value="${sucesso}" />
					<br>
				</c:forEach>
			</div>
		</div>
	</c:if>
	
</c:if>
