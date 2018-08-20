package com.choice.sign.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
	 
    public static Map readExcel(String pathname){
    	Map<String,Map<String,String>> map = new HashMap<String,Map<String,String>>();
    	Map<Integer,String> titleMap = new HashMap<Integer,String>();
    	Map temp = null;
        try{
            //打开文件
            Workbook book = Workbook.getWorkbook(new File(pathname)) ;
            //取得第一个sheet
            Sheet sheet = book.getSheet(0);
            //取得行数
            int rows = sheet.getRows();
            for(int i = 0; i < rows; i++){
            	temp = new HashMap();
                Cell [] cell = sheet.getRow(i);
                if(i==0){//获取第一行数据，得到列头
	                for(int j=0; j<cell.length; j++){
	                    //getCell(列，行)
	                	String columnName = "";
	                	if("身份证".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "sfz";
	                	}else if("序号".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "xh";
	                	}else if("姓名".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "xm";
	                	}else if("养老保障参保情况".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "ybbzcbqk";
	                	}else if("联系电话".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "lxdh";
	                	}else if("享受状态".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "xszk";
	                	}else if("预约时间".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "yysj";
	                	}else if("家庭地址".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "jtzz";
	                	}else if("医保号".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "ybh";
	                	}else if("组号".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "zm";
	                	}else if("镇乡/街道".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "zxjd";
	                	}else if("村/社区".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "csq";
	                	}else{
	                		columnName = "columnName"+j;
	                	}
	                	titleMap.put(j,columnName);
	                    System.out.println("sheet.getCell(j, i).getContents():"+sheet.getCell(j, i).getContents());
	                }
                }else{
                	String cardId = "";
                	 for(int j=0; j<cell.length; j++){
                		 String aaa = titleMap.get(j);
                		 if("sfz".equals(aaa)){
                			 cardId = sheet.getCell(j, i).getContents().trim();
                		 }
                		temp.put(titleMap.get(j),sheet.getCell(j, i).getContents().trim());
 	                }
//                	 System.out.println("cardId!=''====="+cardId!="");
//                	 System.out.println("cardId.eqale(''):"+"".equals(cardId));
                	 if(cardId!=""){
                		 map.put(cardId, temp);
                	 }
                }
            }
            //关闭文件
            book.close();
        }catch(BiffException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return map;
    }
    
    /**
     * lis读取千麦返回的excel结果
     * @param pathname
     * @return
     */
    public static List readLisResultsExcel(String pathname){
    	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    	Map<Integer,String> titleMap = new HashMap<Integer,String>();
    	Map temp = null;
        try{
            //打开文件
            Workbook book = Workbook.getWorkbook(new File(pathname)) ;
            //取得第一个sheet
            Sheet sheet = book.getSheet(0);
            //取得行数
            int rows = sheet.getRows();
            for(int i = 0; i < rows; i++){
            	temp = new HashMap();
                Cell [] cell = sheet.getRow(i);
                if(i==0){//获取第一行数据，得到列头
	                for(int j=0; j<cell.length; j++){
	                    //getCell(列，行)
	                	String columnName = "";
	                	if("条形码".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "qmtxm";
	                	}else if("姓名".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "xm";
	                	}else if("性别".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "xb";
	                	}else if("检验者".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "jyz";
	                	}else if("接收时间".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "jssj";
	                	}else if("审核者".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "shz";
	                	}else if("审核时间".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "shsj";
	                	}else if("项目编号".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "xmbh";
	                	}else if("项目名称".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "xmmc";
	                	}else if("结果".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "jg";
	                	}else if("提示".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "ts";
	                	}else if("参考值".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "ckz";
	                	}else if("单位".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "dw";
	                	}else if("检查时间".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "jcsj";
	                	}else if("标本类型".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "yblx";
	                	}else if("病人年龄".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "brnl";
	                	}else if("临床诊断".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "lczd";
	                	}else if("医院条形码".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "yytxm";
	                	}else if("备注".equals(sheet.getCell(j, i).getContents().trim())){
	                		columnName = "bz";
	                	}else{
	                		columnName = "columnName"+j;
	                	}
	                	titleMap.put(j,columnName);
//	                    System.out.println("sheet.getCell(j, i).getContents():"+sheet.getCell(j, i).getContents());
	                }
                }else{
                	for(int j=0; j<cell.length; j++){
                		if("shsj".equals(titleMap.get(j))){
//                			System.out.println(sheet.getCell(j, i).getContents());
                		}
                		temp.put(titleMap.get(j),sheet.getCell(j, i).getContents().trim());
 	                }
            		 list.add(temp);
                }
            }
            //关闭文件
            book.close();
        }catch(BiffException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }
}
