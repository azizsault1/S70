function preencherHistorico(codigo){
	getAjax('/historico/'+codigo, '#form');
}

function listarHistorico(){
	getAjax('/historico', '#list');
}

function remover(codigo){
	deleteAjax('/historico/'+codigo,'#center');
}