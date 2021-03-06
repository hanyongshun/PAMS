package com.DS.controller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.bean.CsvInfo;
import com.DS.common.model.User;
import com.DS.file.service.FileService;
import com.DS.file.service.impl.FileServiceImpl;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Inject;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
/****
 * 用于处理文件操作的控制器
 * @author jeff
 *
 */
public class FileController extends BaseController{
	@Inject(FileServiceImpl.class)
	private FileService fileService;
	 /****
	   * 文件下载
	   */
	  public void downfile() 
	   {		
		   // String path = getSession().getServletContext().getRealPath("/");	
			File file = new File(PathKit.getRootClassPath()+"/resources/excel/test.xls"); 
			  if (file.exists()) { //如果文件存在 
				  renderFile(file); 
			  } else  { 
		  	   renderJson("文件不存在");
		  	 } 
	  } 
	  
	  /****
	   * 上传文件资源到服务器（自定义位置）
	   */
	  public void uploadFile() 
	  {   		  
		  UploadFile uploadFile = this.getFile();//获取前台上传文件对象 
		  File file = uploadFile.getFile();//获取文件对象 
		  File myFile=fileService.uploadFile(file);
		  if(myFile!=null){
			  renderJson(ajaxDoneSuccess("文件上传成功"));
		  }else{
			  renderJson(ajaxDoneError("文件上传失败"));
		  }
			   
	   } 	  
	  
	  /***
	   * 上传文件资源到服务器（项目中）
	   */
	  public void uploadFileToProject(){		
		  UploadFile uf = getFile();
		  File f = uf.getFile();
		 /* String path = f.getPath();
		  System.out.println(path);*/
		  renderJson(ajaxDoneSuccess("文件上传成功"));
	  }
	  
	
	  /****
	   * 读取csv文件信息
	   */
	  public void readCSV(){
		  UploadFile uploadFile = this.getFile();//获取前台上传文件对象 
		  File file = uploadFile.getFile();//获取文件对象 
		  List<Record> data=fileService.readCSV(file,true);
		  Map<String,Object> map=new HashMap<String,Object>();
		  map.put("csv", data);
		  if(data!=null){
			 // setSessionAttr();
			  map.put("msg", "文件读取成功");
			  renderJson(ajaxDoneSuccess(map));
		  }else{
			  renderJson(ajaxDoneError("文件读取失败"));
		  }
	  }
	 
	  
	  /****
	   * 获取外部csv文件信息并读取
	   */
	  public void getExternalCsv(){
		  User nowUser = (User)getSession().getAttribute("user");
		  UploadFile uploadFile = this.getFile();
		  File file = uploadFile.getFile();//获取文件对象 
		  String newFilename=nowUser.getId()+nowUser.getAccount();
		  File myFile=fileService.uploadFile(file,newFilename);
		  CsvInfo  info=null;
		  try {
			  info=fileService.getCsvInfo(myFile);
		   } catch (IOException e) {
			   info=null;
			e.printStackTrace();
		  }		  
         if(info!=null){
        	  Map<String,Object> map=new HashMap<String,Object>();
   		      map.put("csv", info.getView());
   		      map.put("heads", info.getHeads());
   		      map.put("rows", info.getRows());
   		      map.put("msg", "文件读取成功");
   		      setSessionAttr(newFilename,info);
   		      renderJson(ajaxDoneSuccess(map)); 
         }else{
        	  renderJson(ajaxDoneError("文件读取失败"));
         }
	  }
	  
	  /*****
	   * 逻辑回归算法模型资源文件案例下载
	   */
	  public void downDemoCsv() 
	   {		
			 File file = new File(PathKit.getRootClassPath()+"/py/resources/grade.csv"); 
			  if (file.exists()) { //如果文件存在 
				  renderFile(file); 
			  } else  { 
		  	   renderJson("文件不存在");
		  	 } 
	  } 
}
