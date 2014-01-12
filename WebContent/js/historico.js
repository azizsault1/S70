function preencherHistorico(codigo){
	getAjax('/historico/'+codigo, '#form');
}

function listarHistorico(){
	getAjax('/historico', '#list');
}

function remover(codigo){
	deleteAjax('/historico/'+codigo,'#center');
}

function save(){
	
	var codigo = $( "#formCodigo" ).val();
	var desc = $( "#formDesc" ).val();
	
	var compl = $("#radioSim").is(":checked")? "S": "N";
	
	var historico = new Historico(codigo, desc, compl);

	postAjax('/historico','#form',JSON.stringify(historico), 'listarHistorico()');
}

function Historico(codigo,desc,compl){
	this.codigo = codigo;
	this.descricao = desc;
	this.complemento = compl;
	
}