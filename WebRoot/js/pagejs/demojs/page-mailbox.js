var source ;
var template;
$(function () {
	source = $("#appNotification").html();
	template = Handlebars.compile(source);
	loadMailBox(false);
    
});

//全选,设置checkbox name='all' tbody id=tb
$("input[name=all]").click(function () {
    if (this.checked) {
        $("#tb :checkbox").prop("checked", true);
    } else {
        $("#tb :checkbox").prop("checked", false);
    }
});


//单选 设置name=id
function userCheck(ths) {
    if (ths.checked == false) {
        $("input[name=all]:checkbox").prop('checked', false);
    }
    else {
        var count = $("input[name='itemBox']:checkbox:checked").length;
        if (count == $("input[name='itemBox']:checkbox").length) {
            $("input[name=all]:checkbox").prop("checked", true);
        }
    }
}

/****
 * 获取选中列
 * @returns {Array}
 */
  function queryCheckedValue(){
	var ids = [];	
	$("input:checkbox[name='itemBox']:checked").each(function(i) {
		ids.push($(this).val());
	});
	return ids;
	}


$("#delBtn").click(function(){
	    var ids = [];	   
	    ids=queryCheckedValue();	
		layer.confirm('您确定要删除吗？', {			
		    btn: ['确定', '取消'], //按钮
		    skin: 'btnClass',
		    icon: 2,
		    title: "提示",
	}, function () {
		 layer.closeAll('dialog');  
		  $.ajax({
			  url:basepath+"/demo/batchDelNotifications",
			  type:"post",
			  data:{"ids":ids},
		      dataType:"json",
		      success:function(data){	    	  
		    	  if(data.code==200){
		    		  loadMailBox(false);	
		    			toastrSuccess(data.msg,3000);
		    	  }else{
		    			toastrError(data.msg,3000);
		    	  }
		      }
		  });
		 
	})
});



/****
 * 刷新
 */
$("#refreshBtn").click(function(){
	loadMailBox(true);
});

	
	/***
	 * 加载邮箱列表
	 */
	function loadMailBox(showFlag){	
		var pageNumber=$("#pageNumberId").val();
		 $.ajax({
			  url:basepath+"/demo/loadNotifyList",
			  type:"post",		 
		      dataType:"json",
		      data:{"pageNumber":pageNumber},
		      success:function(data){	
		    	  if(data.code==200){  
		    		     //console.log(data.info);
		    		     var _html = template(data.info);	
		    		     $("#tb").empty();
		    			 $("#tb").html(_html);
		    			 $("#sizeId").empty();
		    			 $("#sizeId").html(data.size);		    			
		    			 $("#pageNumberId").val(data.pageNumber);	
		    			 $("#endPageNumberId").val(data.endPageNumber);
		    			 $("#pageId").empty();	
		    			 $("#pageId").html(data.pageNumber);
	                   if(showFlag){
	                		toastrSuccess("刷新成功",3000);
	                   }	    			 
		    	  }else{
		    		  if(!showFlag){
		    			  toastrError("刷新失败",3000);
	                   }	 
		    	  }
		      }
		  });				
	}
	
	/****
	 * 前一页
	 */
	$("#prevId").click(function(){
		var pageNumber=parseInt($("#pageNumberId").val())-1;
		//console.log("prevId:"+pageNumber);
		if(pageNumber>0){
			$("#pageNumberId").val(pageNumber);
			loadMailBox(false);
		}
		
	});
	
	/****
	 * 下一页
	 */
	$("#nextId").click(function(){
		var pageNumber=parseInt($("#pageNumberId").val())+1;
		var endpageNumber=$("#endPageNumberId").val();
		//console.log("nextId:"+pageNumber);
		if(pageNumber<=endpageNumber){
			$("#pageNumberId").val(pageNumber);
			loadMailBox(false);
		}
		
	});