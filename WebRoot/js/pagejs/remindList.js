/****
 * 备忘提醒页面的js
 */
$(function(){
	    var remindTable=$('#remindTable').DataTable({
			language: {
		     "url": basepath+"/json/datatables_language.json"//国际化文件的文件资源
		 },
		 bFilter: false, //去掉默认搜索框
		 bLengthChange: false, //去掉显示总页数
		 ordering: false, // 禁止排序
		 //bStateSave:true,
		 ajax: {
		     url: basepath+'/remind/getRemindDetails',
		 },
		 columns: [
		           { data: 'remindName' },//事项简称	     
		           { data: null,
		         	  "render": function ( data, type, full, meta ) {        
		           		 var str = setPopverStyle(full.remindName,full.remindText,"详情");  	           		
		           		 return str;
		           	}
		           },
		           { data: 'remindTime'},//提醒时间
		           { data: 'mail'},//提醒邮箱
		           { data: null,//操作部分
		         	  "render": function ( data, type, full, meta ) {            
		           		 var str = "<span id="+full.id+">";  
		           		 str+="<button class='btn table_btn btn-primary btn-sm edtiBtn'  target='_blank' >修改</button>";
		           		 str+="&nbsp;<button class='btn table_btn btn-danger btn-sm delBtn'  target='_blank'>删除</button>";
		           		 str+="</span>";
		           		 return str;
		           	}
		           }
		       ]
		});
		
	   /***
	    * function 表格绘画成功后激活弹出框
	    */
	    remindTable.on( 'draw', function () {	    
	    	 $("[data-toggle='popover']").popover();
		} );
	    
	    /***
	     * function 编辑表格行数据
	     */
		$("body").on("click",".edtiBtn",function(){
			var $operatingArea=$(this).parent();
			var id=$operatingArea.attr("id");				
			$(".modal-title").text("备忘编辑");
		    var htm = $($('#testTemp').html());
			var _html='<div>'+htm[0].outerHTML+'</div>';			
			$("#seeMethodModal .modal-body").html(_html);	
			$("#recordId").val(id);
			showEditData($operatingArea.parent().parent());
			activateDatetimepicker($('.form_date'));
			$("#seeMethodModal").modal("show");
		
	   }); 
	   
		/***
	     * function 新增备忘事务
	     */
		$("body").on("click",".addBtn",function(){
		    var htm = $($('#testTemp').html());
		    $(".modal-title").text("新增备忘");
			var _html='<div>'+htm[0].outerHTML+'</div>';			
			$("#seeMethodModal .modal-body").html(_html);	
			activateDatetimepicker($('.form_date'));
			$("#seeMethodModal").modal("show");
	   }); 
		
		/***
	     * function 提交表单数据
	     */
		$("body").on("click","#submitBtn",function(){	
			if($("#testFrom").validationEngine('validate')){		
				$.ajax({
					url:basepath+"/remind/addRemindDetail",
					type:"post",
					dataType:"json",
					data:$("#testFrom").serialize(),
					success:function(msg){
						$("#seeMethodModal").modal("hide");
						if(msg.code==233){
							remindTable.ajax.reload(null,false);//重新加载当前页表格数据
						}else{
							alert("fail");
						}
					}					
				});
			}
	    }); 
			
	    /****
	     *  function 设置气泡弹出框
	     *  content 文本内容
	     *  title 文本内容主题
	     *  textStr 点击文本语
	     */
	    function setPopverStyle(title,content,textStr){
	    	var str="<a tabindex='0'  data-toggle='popover' data-trigger='focus' title="+title+" data-content="+content+">"+textStr+"</a>";
	    	return str;	    	
	    }
	    
	    /****
	     * function 激活时间输入框
	     */
	    function activateDatetimepicker($object){	    	
	    	$object.datetimepicker({
		         language:"zh-CN",
		         bootcssVer:3,
		         format: 'yyyy-mm-dd HH:mm:ss',  
		         autoclose:true,
		         todayHighlight: true,
		         minView:2,
		         startDate:new Date(),//过往时间不可以填
		         weekStart:1
		     }); 
	    }
	    
	    /***
	     * function 编辑数据回显
	     */
	    function showEditData($object){
	    	$("#remindName").val($object.find("td").eq(0).text());
	    	$("#remindText").val($object.find("td").eq(1).text());
	    	$("#remindTime").val($object.find("td").eq(2).text());
	    	$("#mail").val($object.find("td").eq(3).text());	    	
	    }
	    
	    /***
	     * function 删除备忘事务
	     */
		$("body").on("click",".delBtn",function(){
			var id=$(this).parent().attr("id");
			var info={"id":id}
			layer.confirm('确定删除？', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					$.ajax({
						url:basepath+"/remind/delRemindDetail",
						type:"post",
						data:info,
						dataType:"json",
						success:function(msg){
							if(msg.code==233){
								remindTable.ajax.reload(null,false);
								$.scojs_message('删除成功！', $.scojs_message.TYPE_OK);								
							}else{
								$.scojs_message('删除失败！', $.scojs_message.TYPE_ERROR);
							}
							
						}
					});					 
					layer.closeAll();
				});
	   }); 
	 	    
} );

