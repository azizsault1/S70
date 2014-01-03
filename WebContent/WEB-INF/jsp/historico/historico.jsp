<%@page import="java.util.Map"%>
<%@page import="br.com.contabilidade.s70.persistence.beans.Historico"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>

<div class="row">
	<div class="col-12">
		<H1><legend>CADASTRO DE HISTÓRICO PADRÃO</legend></H1>
	</div>
</div>


<script type="text/javascript">
	listarHistorico();
	preencherHistorico(1);
</script>

<div id="form"></div>
<div id="list"></div>