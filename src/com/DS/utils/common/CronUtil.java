package com.DS.utils.common;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.DS.bean.DateBean;
import com.DS.quartz.vo.QuartzTransferVo;
/***
 * 普通日期转Cron表达式
 */
public class CronUtil {

    private static final String TRANS_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String TRANS_CRON_FORMAT_ONCE = "ss mm HH dd MM ? yyyy";

    private static final String TRANS_CRON_FORMAT_DAY = "ss mm HH dd/ * ? *";

    private static final String TRANS_CRON_FORMAT_WEEK = "ss mm HH ? * -- *";

    private static final String TRANS_CRON_FORMAT_MONTH = "ss mm HH dd MM/1 ? *";

    public static void main(String[] args) throws Exception {
       // String result = getCron("day", "2018-08-11 12:11:00");
       String result = getCron("MON", "2018-12-12 21:14:15");
        // String result = getCronByOnce("2017-01-01 12:12:12");
//       String result = getCron("month", "2019-01-01 12:00:00");
        // String result = getCronToDate("12 12 12 01 01/1 ? 2018");

        System.out.println(result);
    }

    /**
     * 生成只执行一次的cron
     */
    public static String getCronByOnce(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(TRANS_DATE_FORMAT);
        Date parse = format.parse(dateStr);
        return fmtDateToStr(parse, TRANS_CRON_FORMAT_ONCE);
    }

    /**
     * 生成每月或每周或每天执行的cron
     */
    public static String getCron(String period, String startDateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(TRANS_DATE_FORMAT);
        Date startDate = format.parse(startDateStr);
        StringBuffer cronStringBuffer = new StringBuffer();
        if ("month".equals(period)) {
            String startDateCron = fmtDateToStr(startDate, TRANS_CRON_FORMAT_MONTH).trim();
            cronStringBuffer.append(startDateCron);
        } else if ("day".equals(period)) {
            String startDateCron = fmtDateToStr(startDate, TRANS_CRON_FORMAT_DAY).trim();
            // StringBuffer stringBuffer = new StringBuffer(str);
            // stringBuffer.insert(stringBuffer.lastIndexOf("/") + 1, period);
            // cron = stringBuffer.toString().trim();
            // createPeriod(arrStart, arrEnd, 4);
            cronStringBuffer.append(startDateCron).insert(cronStringBuffer.lastIndexOf("/") + 1, "1");
        } else {
            String startDateCron = fmtDateToStr(startDate, TRANS_CRON_FORMAT_WEEK).trim();
            cronStringBuffer.append(startDateCron.replaceAll("--", period));
        }
        return cronStringBuffer.toString();
    }


    public static String getCronToDate(String cron) {
        String format = null;
        StringBuffer stringBuffer = new StringBuffer(cron);
        int lastIndexOf = stringBuffer.lastIndexOf("/");
        stringBuffer.deleteCharAt(lastIndexOf);
        stringBuffer.deleteCharAt(lastIndexOf);
        String dateFormat = "ss mm HH dd MM ? yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            Date date = sdf.parse(stringBuffer.toString());
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format = sdf.format(date);
        } catch (Exception e) {
            return null;
        }
        return format;
    }

    /**
     * 格式转换 日期-字符串 
     */
    public static String fmtDateToStr(Date date, String dtFormat) {
        if (date == null)
            return "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public static String mappingComment(QuartzTransferVo paramVo){   	  
    	DateBean dateBean=TimeUtil.dateStrToDiv(paramVo.getDataStr());
    	if(dateBean==null){
    		return "";
    	}  	
    	if(paramVo.getPeriod()!=null&&!paramVo.getPeriod().equals("once")){
    		  if(paramVo.getPeriod().equals("week")){
    			  return mapKeyWorld(paramVo.getWeekType(),dateBean);
    		  }else{
    			  return mapKeyWorld(paramVo.getPeriod(),dateBean);
    		  }
    	}else{
    		return "只执行一次："+paramVo.getDataStr();
    	}     	
    }
    
   public static String mapKeyWorld(String key,DateBean dateBean){
	    String workTime=dateBean.getHour()+"点"+dateBean.getMinute()+"分执行";
	    String weekStr="每个星期的";
    	 String world="";
    	 if(key.equals("day")){
    		 world="每天"+workTime;
    	 }else if(key.equals("MON")){
    		 world=weekStr+"星期一"+workTime;
    	 }else if(key.equals("TUE")){
    		 world=weekStr+"星期二"+workTime;
    	 }else if(key.equals("WED")){
    		 world=weekStr+"星期三"+workTime;
    	 }else if(key.equals("THU")){
    		 world=weekStr+"星期四"+workTime;
    	 }else if(key.equals("FRI")){
    		 world=weekStr+"星期五"+workTime;
    	 }else if(key.equals("SAT")){
    		 world=weekStr+"星期六"+workTime;
    	 }else if(key.equals("SUN")){
    		 world=weekStr+"星期日"+workTime;
    	 }else if(key.equals("month")){
    		 world="每个月"+dateBean.getDay()+"号"+workTime;
    	 }else{
    		 world="参数错误";
    	 }
    	 
    	return world;
    }
  
}