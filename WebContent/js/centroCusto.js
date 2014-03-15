function preencher(id){
	getAjax('/centroCusto/'+id, '#form');
}

function listar(){
	getAjax('/centroCusto', '#list');
}

function remover(codigo){
	deleteAjax('/centroCusto/'+codigo,'#center');
}

function save(){
	var codigo = $("#centroCustoId").val();
	var nome = $("#centroCustoNome").val();
	var tipo = $("input:checked[name=optionsRadios]").val();
	
	var taxa;
	var status;
	
	if(tipo == 'Obra'){
		taxa = $("#percentId").val();
		status = $("input:checked[name=optionsObra]").val();
	}
	
	alert("taxa: "+taxa + " status: "+ status );
	
	var centroCusto = new CentroCusto(codigo, nome, tipo, taxa, status);
	
	postAjax('/centroCusto','#form',JSON.stringify(centroCusto), 'listar()');
}

function checkType(idRadio){
	
	if(idRadio == 'radioObra'){
		$('#statusObra').show();
		$('#percentAdm').show();
	} else {
		$('#statusObra').hide();
		$('#percentAdm').hide();
	}
}

function CentroCusto(codigo, nome, tipo, taxa, status){
	
	this.codigo = codigo;
	this.nome = nome;
	this.tipo = tipo;
	this.taxa = taxa;
	this.status = status;
	
}

