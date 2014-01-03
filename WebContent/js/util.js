//AJAX
function ajax(method,url, containerAlvo, dados, funcao, funcaoErro){
	$.ajax({
		type: method,
		url: homeSite + url,
		cache: false,
		processData: true,
		dataType: "html",
		contentTypeString: 'application/x-www-form-urlencoded; charset=ISO-8859-1',
		data: dados,
		beforeSend: function() {
			aguarde(containerAlvo);
		},
		success: function(data, status, request) {
			$(containerAlvo).html(request.responseText);

			if (funcao){
				eval(funcao);
			}
		},
		error: function(request, status, thrown) {
			if (funcaoErro){
				eval(funcaoErro);
			} else {
				//mensagemErro(request.status);
			}
		}
	});
}

function postAjax(url, containerAlvo, dados, funcao, funcaoErro){
	ajax('POST',url, containerAlvo, dados, funcao, funcaoErro);
} 

function getAjax(url, containerAlvo, dados, funcao, funcaoErro){
	ajax('GET', url, containerAlvo, dados, funcao, funcaoErro);
}

function deleteAjax(url, containerAlvo, dados, funcao, funcaoErro){
	ajax('DELETE', url, containerAlvo, dados, funcao, funcaoErro);
}

function aguarde(containerAlvo){

	$(containerAlvo).html("Aguarde...");
}