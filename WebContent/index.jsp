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
	<script src="<%=request.getContextPath()%>/js/index.js" charset="ISO-8859-1"></script>
	<script src="<%=request.getContextPath()%>/js/historico.js" charset="ISO-8859-1"></script>
	
	<script type="text/javascript">
		var homeSite = '<%=request.getContextPath()%>';
	</script>
  </head>
  <body>
    <div class="container">
		<div id="center">
			<p><a class="btn btn-primary btn-lg" role="button" onclick="acaoBtnHistoricoPadrao();">Cadastro de Histórico Padrão &raquo;</a></p>
			<p><a class="btn btn-primary btn-lg" role="button" onclick="acaoBtnCentroCusto();">Cadastro de Centro de Custo &raquo;</a></p>
		</div>
	</div>
  </body>
</html>
