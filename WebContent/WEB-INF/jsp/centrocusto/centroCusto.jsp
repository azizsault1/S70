<%@page import="java.util.Map"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.Historico"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Sistema de ....</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    

    <script src="https://code.jquery.com/jquery.js"></script>   
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/util.js" charset="ISO-8859-1"></script>
	<script src="<%=request.getContextPath()%>/js/centroCusto.js" charset="ISO-8859-1"></script>
	
	<script type="text/javascript">
		var homeSite = '<%=request.getContextPath()%>';
	</script>
  </head>
	<body>
		<div class="container">
			<div id="center">
				<script type="text/javascript">
					//listar();
					//preencher();
				</script>
				<div id="form"></div>
				<div id="list"></div>
			</div>
		</div>
	</body>
</html>