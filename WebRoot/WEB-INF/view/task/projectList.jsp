<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>工程任务</title>
</head>
<body>
<div class="tile">
	<form  class="form-inline" id="filterForm">	
						<div class="form-group">
					        <label>工程名称</label>
					        <input type="text" class="form-control keepPlace" name="projeName" id="projectNameKey">
					    </div>						    				       
		               <div class="input-daterange input-group keepPlace">
		               		<label>时间范围 &nbsp;&nbsp;&nbsp;</label>			                	       			                                
		                  	<span class="input-group-addon keepPlace"><i class="fa fa-calendar"></i></span>
						    <input type="text" id="datetimepicker1" class="form-control quaryTime" name="startDates" style="width:180px;">
						    <span class="input-group-addon">to</span>
						    <input type="text" id="datetimepicker2" class="form-control quaryTime" name="endDates" style="width:180px;">
						    	 <button type="button" id="querys" class="btn btn-info mt5 mr3 keepPlace" data-step="3" data-intro="点击查询按钮，按查询条件查出数据列表！">查询</button>	
						    	 <button type="button" id="reset" class="btn btn-default mt5 keepPlace" data-step="4" data-intro="点击重置将查询条件还原成默认查询状态！">重置</button>	
						    	 <button type="button" class="btn btn-default mt5 keepPlace" id="addBtn" data-step="4">新增</button>				    	    
					  	  </div>					  
	</form>	
</div>
<div class="tile">
	<table id="projectTable" class="display" style="width:100%">
    <thead>
        <tr>
            <th>工程任务</th>
            <th>计划开始时间</th>
            <th>计划结束时间</th>
            <th>操作</th>            
        </tr>
    </thead>
    <tbody>      
    </tbody>
</table>
</div>
				<div class="modal" id="seeMethodModal">
						<div class="modal-dialog modal-lg ptn" style="width:600px;">
						    <div class="modal-content" >
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						        <h4 class="modal-title"></h4>
						      </div>
						      <div class="modal-body" >
						      </div>	
						      <div class="modal-footer">
                				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
               					 <button type="button" class="btn btn-primary" id="submitBtn">保存</button>
            				</div>     
					    </div>
					  </div>
				</div>

<template id="template">
    <div class="panel mbn">
       <div class="panel-body" >
       <form id="projectForm" style="margin-top:20px;">
		<table class="table table-bordered table-hover">
          <tbody>       
            <tr>
				<td class="va-m" width="30%"><span>工程任务</span><input id="projectId" type="text" value="" style="display:none;" name="id" ></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input name="subject" id="projectName" class="form-control validate[required]" placeholder="" maxlength="20" type="text">      
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr>       			   
            <tr>
				<td class="va-m" width="30%"><span>开始时间</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input  id="startDate" class="form-control validate[required] form_date" placeholder="" maxlength="20" type="text" readonly>      
	                  	</div>	                  	
	                 </div>	  
              	</td>              	
            </tr>        
             <tr>
				<td class="va-m" width="30%"><span>结束时间</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                             <input   id="finshDate" class="form-control validate[required] form_date" placeholder="" maxlength="20" type="text" readonly>      
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
<script src="${BASE_PATH}/js/pagejs/task/projectList.js"></script>
<script src="${BASE_PATH}/js/validation/lang/jquery.validationEngine-zh_CN.js"></script>
<script src="${BASE_PATH}/js/validation/jquery.validationEngine.min.js"></script>	
</body>
</html>