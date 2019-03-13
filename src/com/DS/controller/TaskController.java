package com.DS.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.common.model.Project;
import com.DS.common.model.ProjectTree;
import com.DS.task.service.TaskService;
import com.DS.task.service.impl.TaskServiceImpl;
import com.DS.task.vo.TaskVo;
import com.DS.utils.common.ObjectUtil;
import com.DS.web.base.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
/****
 * 
 * @author jeff
 *  任务控制器
 */
public class TaskController extends BaseController{
	@Inject(TaskServiceImpl.class)
	private TaskService taskService;
	
	  /***
	   *  跳转到任务列表
	   */
       public void goTargetList(){   	 
    	  render("targetList.jsp");
       }
       
       /****
        * 获取任务列表详情
        */
       public void getTargetList(){	
   		DivPageCondition=getDivPageCondition();
   		DivPageCondition.put("startDates", getPara("startDates"));
   		DivPageCondition.put("endDates", getPara("endDates"));
   		DivPageCondition.put("taskName", getPara("taskName"));				
        renderJson(taskService.getTargetTaskList(DivPageCondition));
   		
   	}
       
     /****
   	 * 删除目标任务
   	 */
   	public void delTarget(){
   		Map<String,Object> paramMap=new HashMap<String,Object>();
   		paramMap.put("taskId", getPara("taskId"));
   		SqlPara delsql=Db.getSqlPara("task.delById",paramMap);
   		int result=Db.update(delsql);
   		if(result>0){
   			renderJson(ajaxDoneSuccess("删除成功"));
   		}else{
   			renderJson(ajaxDoneError("删除失败"));
   		}
   	}
   	
   	/**
	 *  跳转到创建新的调度器任务页面
	 */
	public void goCreateTarget(){
		render("createTarget.jsp");
	}	
	
	
	public void goProjectTree(){
		String projectId=getPara("projectId");
		setAttr("projectId",projectId);
		render("projectTree.jsp");
	}	
	
	/**
	 *  跳转到目标任务详情页面
	 */
	public void goTargetDetail(String taskId){
		 Record record=Db.findFirst("select * from task where taskId="+getPara("taskId"));
		 setAttr("vo",record);
		 render("targetDetail.jsp");
	}	
	
	
	/***
	 * 创建目标任务
	 */
	 public void createTarget(){		
		  TaskVo vo=getBean(TaskVo.class,"");
		  Map<String,Object> paramMap=ObjectUtil.convertBeanToMap(vo);		
		  paramMap.put("addTime",new Date());
		  SqlPara insertSql=Db.getSqlPara("task.insertData",paramMap);
		  int result=Db.update(insertSql);
		  if(result>0){
				renderJson(ajaxDoneSuccess("操作成功"));
			}else{
				renderJson(ajaxDoneError("操作失败"));
			}		 	   						 		   
	 }
	 
	 /*****
	  * 获取用户的工程任务甘特图
	  */
     public void getProjectGantt(){
    	 Record user = (Record)getSession().getAttribute("user");
    	 JSONArray r=taskService.getProjectGantt(user.get("id")+"");
    	 renderJson(r);
     }
     
     
     
     public void goProjectList(){
    	 String projectId=getPara("projectId");
    	 setAttr("projectId",projectId);
      	  render("projectList.jsp");
        }
     
     /****
      * 创建新的工程
      */
     public void createProject(){   	 
    	 Record user = (Record)getSession().getAttribute("user");
    	 Project project=getModel(Project.class,"");
    	 project.setUserId(user.getInt("id"));
    	 project.setUserName(user.getStr("account"));
    	 int id=taskService.createProject(project);
    	 setAttr("projectId",id);
    	 render("newProjectTree.jsp");
     }
     
     /****
      * 提交工程任务数据
      */
     public void submitProject(){	
		  String pTaskJson=getPara("pTaskJson");
		  List<ProjectTree> pTaskList= JSON.parseArray(pTaskJson,ProjectTree.class); 
		  ProjectTree projectTree=pTaskList.get(0);
		  Map<String, List<ProjectTree>> map=new HashMap<String, List<ProjectTree>>();
		  map.put("cond", pTaskList);
		  SqlPara updateSql=Db.getSqlPara("projectTree.insertDataBatch", map);	
		  Map<String,Object> delMap=new HashMap<String,Object>();
		  delMap.put("projectId",projectTree.getProjectId());
		  SqlPara deleteSql=Db.getSqlPara("projectTree.deleteAll", delMap);	
		  Db.tx(() -> {
			  Db.update(deleteSql);
			  Db.update(updateSql);			
			  return true;
			}); 
		  renderJson(ajaxDoneSuccess("操作成功"));  
   }
     
     /****
      * 获取工程任务列表数据
      */
     public void getProjectList(){
    	 Record user = (Record)getSession().getAttribute("user");
    	 DivPageCondition=getDivPageCondition();
    	 DivPageCondition.put("userId", user.get("id"));
    	 DivPageCondition.put("projectName", getPara("projectName")); 
    	 DivPageCondition.put("startDates", getPara("startDates"));
 		 DivPageCondition.put("endDates", getPara("endDates"));
		 renderJson(taskService.getProjectList(DivPageCondition));
     }
     
     public void getProjectTree(){
    	 String projectId=getPara("projectId");
    	 Map<String,Object> paramMap=new HashMap<String,Object>();
    	 paramMap.put("projectId", projectId);
    	 SqlPara sql=Db.getSqlPara("projectTree.getProjectById",paramMap);
 		 List<Record> projectTree= Db.find(sql);		
 		 renderJson(projectTree);   	 
     }
     
     /****
      * 删除工程任务
      */
     public  void delProject(){
    	 String projectId=getPara("projectId");
    	 if(taskService.deleteProject(projectId)>0){
    		 renderJson(ajaxDoneSuccess("删除成功"));
    	 }else{
    		 renderJson(ajaxDoneError("删除失败"));
    	 }
     }
}
