function onCopy(obj) {
	//if(!obj) alert('没标识数据源');
	var tar = obj || '#copy';
	var content = $(tar).html() || $(tar).val(); //input的时候取value
	//textarea缓存要复制的内容
	$('#tmp').val(content);
	$('#tmp').select();
	try {
		document.execCommand("Copy"); //内容复制到剪切板
		alert('复制成功');
	} catch(e) {
		alert('浏览器不支持快捷复制，请选中内容后，CTRL+C');
	}
}