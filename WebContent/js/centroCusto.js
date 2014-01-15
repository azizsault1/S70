function preencher(codigo){
	getAjax('/centroCusto/'+codigo, '#form');
}

function listar(){
	getAjax('/centroCusto', '#list');
}

function remover(codigo){
	deleteAjax('/centroCusto/'+codigo,'#center');
}

function save(){

	alert("Save");
}

