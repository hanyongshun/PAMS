/***
 * quartz任务调度信息页面
 */
$(function(){	 
       var qrtzTable=$('#qrtzTable').DataTable({//利用id来找到对应的表格，并将数据填充进去
    	language: {
            "url": basepath+"/json/datatables_language.json"//国际化文件的文件资源
        },
        ordering: false, //禁止排序
        ajax: {
            url: basepath+'/qrtz/getJobDetails',
        },
        columns: [
                  { data: 'JOB_NAME' },//任务名
                  { data: 'JOB_GROUP'},//任务组
                  { data: 'DESCRIPTION'},//任务描述
                  { data: 'JOB_CLASS_NAME'},//任务类名
                  { data: 'CRON_EXPRESSION'},//表达式
                  { data: null,//操作部分
		         	  "render": function ( data, type, full, meta ) {            
			           		 var str = "";  
			           		 str+="<button class='btn table_btn btn-primary btn-sm edtiBtn'  target='_blank' >修改</button>";
			           		 str+="&nbsp;<button class='btn table_btn btn-danger btn-sm delBtn'  target='_blank'>删除</button>";			        
			           		 return str;
			           	}
			           }                
              ]
    });
       
       
       /***
	     * function 编辑表格行数据
	     */
		$("body").on("click",".edtiBtn",function(){	
			$(".modal-title").text("编辑调度任务");
		    var htm = $($('#testTemp').html());
			var _html='<div>'+htm[0].outerHTML+'</div>';			
			$("#seeMethodModal .modal-body").html(_html);	
			var $object=$(this).parent().parent();
	        var jobName=$object.find("td").eq(0).text();
	        showEditData(jobName);
			activateDatetimepicker($('.form_date'));
			$("#seeMethodModal").modal("show");		
	   }); 
	   
		/***
	     * function 新增调度任务
	     */
		$("body").on("click",".addBtn",function(){
		    var htm = $($('#testTemp').html());
		    $(".modal-title").text("新增调度任务");
			var _html='<div>'+htm[0].outerHTML+'</div>';			
			$("#seeMethodModal .modal-body").html(_html);	
			activateDatetimepicker($('.form_date'));
			$("#seeMethodModal").modal("show");
	   }); 
       
		/***
	     * function 删除调度任务
	     */
		$("body").on("click",".delBtn",function(){	
			layer.confirm('确定删除？', {
				  btn: ['是的','取消'] 
				}, function(){
				  layer.msg('的确很重要', {icon: 1});
				});
		
		}); 
		 /****
	     * function 激活时间输入框
	     */
	    function activateDatetimepicker($object){	    	
	    	$object.datetimepicker({
		         language:"zh-CN",
		         bootcssVer:3,
		         format: 'yyyy-mm-dd hh:ii:ss',  
		         autoclose:true,
		         todayHighlight: true,
		         minuteStep: 1,
		         startDate:new Date(),//过往时间不可以填
		         weekStart:1
		     }); 
	    }      	    
	    
	    /***
	     * function 编辑数据回显
	     */
	    function showEditData(jobName){
	    	 $.ajax({
		        	url:basepath+"/qrtz/getQuartzTaskByName",
		        	type:"post",
		        	data:{"jobName":jobName},
		        	dataType:"json",
		        	success:function(data){
		        		$("#jobName").val(data.record.JOB_NAME);	
		        		$("#jobGroup").val(data.record.JOB_GROUP);
		        		$("#triggerName").val(data.record.TRIGGER_NAME);
		        		$("#triggerGroup").val(data.record.TRIGGER_GROUP);
		        		$("#DateStr").val(data.record.DateStr);
		        		$("#description").val(data.record.DESCRIPTION);
		        	}
		        });
	    }
	   
	    /***
	     * function 提交表单数据
	     */
		$("body").on("click","#submitBtn",function(){
			$.ajax({
				url:basepath+"/qrtz/ChangeQuartzTaskTime",
				type:"post",
				dataType:"json",
				data:$("#testFrom").serialize(),
				success:function(){
					$("#seeMethodModal").modal("hide");
					qrtzTable.ajax.reload(null,false);//重新加载当前页表格数据
					/*if(msg.code==233){
						remindTable.ajax.reload(null,false);//重新加载当前页表格数据
					}else{
						alert("fail");
					}*/
				}					
			});
		
	    }); 
	    
	    
} );