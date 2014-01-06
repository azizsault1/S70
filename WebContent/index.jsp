<!DOCTYPE html>
<html>
  <head>
    <title>Sistema de ....</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/util.js" charset="ISO-8859-1"></script>
	<script src="<%=request.getContextPath()%>/js/index.js" charset="ISO-8859-1"></script>
	<script src="<%=request.getContextPath()%>/js/historico.js" charset="ISO-8859-1"></script>
	
	<script type="text/javascript">
		var homeSite = '<%=request.getContextPath()%>';
	</script>
  </head>
  <body>
    <div class="container">
		<div id="center">
			<p><a class="btn btn-primary btn-lg" role="button" onclick="acaoBtnHistoricoPadrao();">Cadastro de Hist�rico Padr�o &raquo;</a></p>
		</div>
	</div>
  </body>
</html>