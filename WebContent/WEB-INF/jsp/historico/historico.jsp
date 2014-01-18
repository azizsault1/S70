<%@page import="java.util.Map"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.Historico"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Sistema de ....</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
	<!-- link href="<%=request.getContextPath()%>/css/demo_page.css" rel="stylesheet">-->
	<link href="<%=request.getContextPath()%>/css/jquery.dataTables_themeroller.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.4.custom.css" rel="stylesheet">
	
	<script src="https://code.jquery.com/jquery.js" ></script> 
	<script src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/util.js" charset="ISO-8859-1"></script>
	<script src="<%=request.getContextPath()%>/js/historico.js" charset="ISO-8859-1"></script>

	<script type="text/javascript" charset="utf-8">
		var homeSite = '<%=request.getContextPath()%>';
	</script>
	
  </head>
	<body>
		<div class="container">
			<div id="center">
				<script type="text/javascript">
					lista();
					preencher();
				</script>
				<div id="form"></div>
				<div id="list"></div>
			</div>
		</div>
	</body>
</html>