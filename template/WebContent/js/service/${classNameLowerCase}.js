
$(function(){
	/**
	<#list table.columns as column>
		${column.columnNameLower} 
	</#list>
	**/
	$.ajax({
		type : 'POST',
	    url : ctx + '/',
	    dataType : 'json',
	    data : {
	    	
	    },
	    success:function(){
	    	
	    },
	    error: dunction(){
	    	
	    }
	});
	
})