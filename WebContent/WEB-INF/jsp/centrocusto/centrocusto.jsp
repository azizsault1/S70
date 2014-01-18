<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8;" />
		
		<title>S70 - Sistema de ...</title>
		<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/docs.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/jquery.dataTables_themeroller.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.4.custom.css" rel="stylesheet">
		
		<script src="https://code.jquery.com/jquery.js" ></script> 
		<script src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/util.js" charset="ISO-8859-1"></script>
		<script src="<%=request.getContextPath()%>/js/centroCusto.js" charset="ISO-8859-1"></script>
		<script type="text/javascript" charset="utf-8">
			var homeSite = '<%=request.getContextPath()%>';
		</script>
	</head>
	<body>
		<div class="container">
			<div id="center">
				<script type="text/javascript">
					listar();
					preencher();
				</script>
				<div id="form"></div>
				<div id="list"></div>
			</div>
		</div>
	</body>
</html>