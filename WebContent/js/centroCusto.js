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

	alert("Save");
}

function checkType(idRadio){
	
	if(idRadio == 'radioObra'){
		$('#statusObra').show();
	} else {
		$('#statusObra').hide();
	}
}

