<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>备忘提醒</title>
<style type="text/css">
	a{
	text-decoration:none;
	}
	a:hover{
		text-decoration:none;
	}
</style>
</head>
<body>
	<div class="panel-body">
	 <h2 align="center" id="h2text">备忘提醒</h2>	
	
    <a tabindex="0"   data-toggle="popover" data-trigger="focus" title="页面介绍" data-content="用于邮件提醒备忘事务的功能页面">页面介绍</a>
    <button type="button" class="btn btn-info testBtn" style="float:right;margin-right:20px;margin-bottom:40px;">测试</button>
	<button type="button" class="btn btn-info addBtn" style="float:right;margin-right:20px;margin-bottom:40px;">新增</button>
	<table id="remindTable" class="display">
    <thead>
        <tr>
            <th>事项简称</th>
            <th>具体事项</th>
            <th>截止时间</th>
            <th>提醒邮箱</th>
            <th>操作</th>            
        </tr>
    </thead>
    <tbody>      
    </tbody>
</table>
</div>
<!--弹出框基本框架开始-->
				<div class="modal" id="seeMethodModal">
						<div class="modal-dialog modal-lg ptn" style="width:600px;"><!--修改这个width可以改变整个模态框的宽度-->
						    <div class="modal-content" >
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						        <h4 class="modal-title">备忘修改</h4>
						      </div>
						      <div class="modal-body" style="height:350px;overflow:hidden;"><!--改变这个高度可以改变整个模态框的高度，但是内部元素可能撑破-->
						      </div>	
						      <div class="modal-footer">
                				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
               					 <button type="button" class="btn btn-primary" id="submitBtn">保存</button>
            				</div>     
					    </div>
					  </div>
				</div>
<!--弹出框主体框架结束-->

<!--弹出框内容模板-->
<template id="testTemp">
    <div class="panel mbn" style="height:550px;overflow-y:auto;">
       <div class="panel-body" >
       <form id="testFrom">
		<table class="table table-bordered table-hover">
          <tbody>
          <!--第0行-->
            <tr>
				<td class="va-m" width="30%"><span>事项简称</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input id="username" class="form-control validate[required]" placeholder="" maxlength="20" type="text">      
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr>
            <!--第1行-->
			 <tr>
				<td class="va-m" width="30%"><span>具体事项</span></td>
              	<td>
	              	<div class="row">
	              	<div class="col-sm-12">
	              		  <textarea class="form-control" rows="3"></textarea>    
	              	</div>
                      	
	                 </div>	  
              	</td>              	
            </tr>
            <!--第2行-->
            <tr>
				<td class="va-m" width="30%"><span>截止时间</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="prePad" class="form-control validate[funcCall[rangeInputIsRight]]" placeholder="" maxlength="20" type="text">      
	                  	</div>	                  	
	                 </div>	  
              	</td>              	
            </tr>
            <!--第3行-->
             <tr>
				<td class="va-m" width="30%"><span>提醒邮箱</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="repad" class="form-control validate[funcCall[rangeInputIsRight]]" placeholder="" maxlength="20" type="text">      
	                  	</div>	                  	
	                 </div>	  
              	</td>              	
            </tr>	         		
          </tbody>
        </table>
        </form>
      </div>
    </div>
</template>
<!--弹出框内容模板-->
<script src="${BASE_PATH}/js/pagejs/remindList.js"></script>
</body>
</html>