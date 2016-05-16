function loading(data){
	var loading = $("#image");
	var fundo = $("#fundo-loading");
	if(data.status == "begin"){
		loading.show();
		fundo.show();
	}
	else if(data.status == "success"){
		loading.hide();
		fundo.hide();
	}
}

$(function() {
	$(":text, :password").each(function(){
		if($(this).val() != ""){
			$(this).addClass("md-input-fixed");
		}else{
			$(this).removeClass("md-input-fixed");
		}
	})
	
	$("#uploadFoto").click(function(){
		$("#uploadFotoHidden").click();
		return false;
	})
	
	$("#uploadFotoHidden").change(function(){
		$("#srcUploadFoto").val($(this).val());
	})
})

