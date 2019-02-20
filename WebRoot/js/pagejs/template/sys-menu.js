var dashboard="app-menu__icon fa fa-dashboard";
var laptop= "app-menu__icon fa fa-laptop";
var edit="app-menu__icon fa fa-edit";
var list="app-menu__icon fa fa-th-list";
var text="app-menu__icon fa fa-file-text";
var menuFlag=1;//0--静态资源  1--数据库资源
/*$(function(){
	param_bulidMenu();	
});*/


/*****
 * 构建参数测试
 */
function param_bulidMenu(){
	 if(JSON.parse(userMenu)!=null){		
		 //console.log(JSON.parse(userMenu));
		 bulidApp(JSON.parse(userMenu));
		 return;
	 }
	if(menuFlag==0){
		var context = {
	            "menuTree": [
	                     {               
	                         "url": "/go/goMenu",          
	                         "name":"首页",
	                         "icon":dashboard
	                    },
	                  {                  
	                      "url": "#",         
	                      "name":"数据表格",
	                      "icon":laptop,
	                      "subMenuList":[
		                                  {
		                                	  "name":"备忘提醒",
		                                	  "url":"/remind/goRemindList"
		                                  },
		                                  {
		                                	  "name":"调度任务",
		                                	  "url":"/qrtz/goQrtzTaskList"
		                                  }
	                                 ]
	                 },
	                 {             
	                     "url": "#",              
	                     "name":"测试区域",
	                     "icon":edit,
	                     "subMenuList":[
		                                  {
		                                	  "name":"测试页面",
		                                	  "url":"/test/goTestPage"
		                                  }
	                                ]
	                },
	                {           
	                    "url": "#",              
	                    "name":"案例页面",
	                    "icon":list,
	                    "subMenuList":[
		                                  {
		                                	  "name":"Ztree-bootstrap",
		                                	  "url":"/demo/goTreePage"
		                                  },
		                                  {
		                                	  "name":"ECharts",
		                                	  "url":"/demo/goEChartsPage"
		                                  }
	                               ]
	               },
	               {
	                   "url": "#",              
	                   "name":"任务模块",
	                   "icon":text,
	                   "subMenuList":[
		                                  {
		                                	  "name":"工程任务",
		                                	  "url":"/task/goTaskList"
		                                  }
	                              ]
	              },
	              ]
	         };		
		bulidApp(context);
	}else{
		$.ajax({
			url:basepath+"/menu/getTreeMenu",
			type:"post",
			dataType:"json",
			success:function(context){				
				console.log(context);
				bulidApp(context)				
			}
		});
	}	
}


/****
 * 构建菜单主体
 * @param data
 */
function bulidApp(data){
	var source = $("#appMenu").html();
	var template = Handlebars.compile(source);
	var _html = template(data);
	$(".app-menu").html(_html);
}

//处理子菜单的路径问题
$("body").on("click",".treeview-item",function(event){
	event.preventDefault();
	window.location.href=basepath+$(this).attr("href");
});

//处理单条菜单没有子菜单的url路径问题
$("body").on("click",".menuUrl",function(event){
	event.preventDefault();
	window.location.href=basepath+$(this).attr("href");
});