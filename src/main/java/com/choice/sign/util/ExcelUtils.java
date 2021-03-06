package com.choice.sign.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtils {

	private WritableCellFormat titleWcf;//excel标题
	
	private WritableCellFormat legendWcf;//excel表头
	
	private WritableCellFormat contentWcf;//excel正文
	
	public WritableCellFormat getTitleWcf() {
		return titleWcf;
	}

	public void setTitleWcf(WritableCellFormat titleWcf) {
		this.titleWcf = titleWcf;
	}

	public WritableCellFormat getLegendWcf() {
		return legendWcf;
	}

	public void setLegendWcf(WritableCellFormat legendWcf) {
		this.legendWcf = legendWcf;
	}

	public WritableCellFormat getContentWcf() {
		return contentWcf;
	}

	public void setContentWcf(WritableCellFormat contentWcf) {
		this.contentWcf = contentWcf;
	}

	public ExcelUtils() throws WriteException {
		init();
	}
	
	/**
	 * 初始化标题字体、表头字体、内容字体的样式
	 * @throws WriteException
	 */
	private void init() throws WriteException{
		// 设置标题字体
		WritableFont titleWf = new WritableFont(WritableFont.ARIAL, 18, WritableFont.BOLD,
				false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		this.titleWcf = new WritableCellFormat(titleWf);
		this.titleWcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
		this.titleWcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.NONE, Colour.BLACK);// 设置细边框
		
		// 设置表头字体
		WritableFont legendWf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD,
				false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		this.legendWcf = new WritableCellFormat(legendWf);
		this.legendWcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置水平对齐方式
		this.legendWcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); // 设置垂直对齐方式
		this.legendWcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, Colour.BLACK);// 设置细边框
		this.legendWcf.setWrap(true);
		
		// 设置正文字体
		WritableFont contentWf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
				false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		this.contentWcf = new WritableCellFormat(contentWf);
		this.contentWcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
		contentWcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); // 设置垂直对齐方式
		contentWcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, Colour.BLACK);// 设置细边框
	}
	
	/**
	 * 创建表头
	 * @param wsheet
	 * @param x
	 * @param y
	 * @param legends
	 * @param sizes
	 * @return 表头所在的行数
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public int buildLegend(WritableSheet wsheet, int x, int y, String[] legends, int[] sizes) throws RowsExceededException, WriteException{
		for(int i=0; i<legends.length; i++){
			Label wlabel = new Label(x+i, y, legends[i], this.legendWcf);
			wsheet.setColumnView(x+i, sizes[i]);
			wsheet.addCell(wlabel);
		}
		return 1;
	}
	
	/**
	 * 创建额外内容
	 * @param wsheet
	 * @param x
	 * @param y
	 */
	public void buildExtraContent(WritableSheet wsheet, int x, int y){
	}
	
	public void export(OutputStream os, List<Object[]> datas) throws BiffException, IOException, RowsExceededException, WriteException {
		this.export(null ,os, null, null, null, datas, 0, 0);
	}
	
	public void export(OutputStream os, String[] legends, int[] sizes, List<Object[]> datas) throws BiffException, IOException, RowsExceededException, WriteException {
		this.export(null ,os, null, legends, sizes, datas, 0, 0);
	}
	
	public void export(OutputStream os, String title, String[] legends, int[] sizes, List<Object[]> datas) throws BiffException, IOException, RowsExceededException, WriteException {
		this.export(null ,os, title, legends, sizes, datas, 0, 0);
	}
	
	public void export(OutputStream os, String title, String[] legends, int[] sizes, List<Object[]> datas, int offsetX, int offsetY) throws BiffException, IOException, RowsExceededException, WriteException {
		this.export(null ,os, title, legends, sizes, datas, offsetX, offsetY);
	}
	public void export(OutputStream os, String title, String[] legends, int[] sizes, List<Object[]> datas, String sheetname) throws BiffException, IOException, RowsExceededException, WriteException {
		this.export(null ,os, title, legends, sizes, datas,sheetname, 0, 0);
	}
	/**
	 * 将已有数据写入Excel
	 * @param templateFile 模板文件
	 * @param os 数据流，如果是写本地文件的话，可以是FileOutputStream;如果是写Web下载的话，可以是ServletOupputStream
	 * @param title 工作簿的标题,如果不用的话,可以写null或者""
	 * @param legends 表头名称
	 * @param sizes 设定每一列的宽度
	 * @param datas 数据集
	 * @param offsetX 单元格横向偏移量
	 * @param offsetY 单元格纵向偏移量
	 * @throws BiffException
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public void export(File templateFile, OutputStream os, String title, String[] legends, int[] sizes, List<Object[]> datas, int offsetX, int offsetY) throws BiffException, IOException, RowsExceededException, WriteException {
		WritableWorkbook wbook = null;
		WritableSheet wsheet = null;
		if(templateFile!=null){
			Workbook wb = Workbook.getWorkbook(templateFile);
			wbook = Workbook.createWorkbook(os, wb);
			wsheet = wbook.getSheet(0);
		}else{
			wbook = Workbook.createWorkbook(os); // 建立excel文件
			wsheet = wbook.createSheet("第一页", 0); // sheet名称
		}

		if (title != null && !title.trim().equals("")) {// 添加标题
			wsheet.mergeCells(offsetX, offsetY, offsetX+datas.get(0).length-1, offsetY); // 合并单元格
			Label wlabel = new Label(offsetX, offsetY, title, this.titleWcf);
			wsheet.addCell(wlabel);
		}

		// 如果有标题的话，要设置一下偏移
		int rowIndex = 1;
		if (title == null || title.trim().equals("")) {
			rowIndex = 0;
		}

		//创建表头
		if(legends!=null && sizes!=null){
			rowIndex = rowIndex + this.buildLegend(wsheet, offsetX, offsetY+rowIndex, legends, sizes);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		WritableCell wcell = null;//单元格
		//往Excel输出数据
		for (int i=0;i<datas.size();i++) {
			for(int j=0;j<datas.get(i).length;j++){
				Object value = datas.get(i)[j];
				if(value instanceof java.lang.Number) {
					wcell = new Number(offsetX+j, offsetY+rowIndex, ((java.lang.Number)value).doubleValue(), this.contentWcf);
				}else if(value instanceof java.util.Date) {
					wcell = new Label(offsetX+j, offsetY+rowIndex, sdf.format((java.util.Date)value), this.contentWcf);
				}else{
					wcell = new Label(offsetX+j, offsetY+rowIndex, (java.lang.String)value, this.contentWcf);
				}
				wsheet.addCell(wcell);
			}
			rowIndex++;
		}
		
		//创建额外内容
		buildExtraContent(wsheet, offsetX, offsetY+rowIndex);
		wbook.write(); // 写入文件
		wbook.close();
		os.flush();
		os.close();
	}
	public void export(File templateFile, OutputStream os, String title, String[] legends, int[] sizes, List<Object[]> datas,String sheetname, int offsetX, int offsetY) throws BiffException, IOException, RowsExceededException, WriteException {
		WritableWorkbook wbook = null;
		WritableSheet wsheet = null;
		if(templateFile!=null){
			Workbook wb = Workbook.getWorkbook(templateFile);
			wbook = Workbook.createWorkbook(os, wb);
			wsheet = wbook.getSheet(0);
		}else{
			wbook = Workbook.createWorkbook(os); // 建立excel文件
			if(sheetname!=null&&!"".equals(sheetname)){
				wsheet = wbook.createSheet(sheetname, 0); // sheet名称
			}else{
				wsheet = wbook.createSheet("第一页", 0); // sheet名称
			}
		}

		if (title != null && !title.trim().equals("")) {// 添加标题
			wsheet.mergeCells(offsetX, offsetY, offsetX+datas.get(0).length-1, offsetY); // 合并单元格
			Label wlabel = new Label(offsetX, offsetY, title, this.titleWcf);
			wsheet.addCell(wlabel);
		}

		// 如果有标题的话，要设置一下偏移
		int rowIndex = 1;
		if (title == null || title.trim().equals("")) {
			rowIndex = 0;
		}

		//创建表头
		if(legends!=null && sizes!=null){
			rowIndex = rowIndex + this.buildLegend(wsheet, offsetX, offsetY+rowIndex, legends, sizes);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		WritableCell wcell = null;//单元格
		//往Excel输出数据
		for (int i=0;i<datas.size();i++) {
			if((i+1)==datas.size()){
				wsheet.mergeCells(0, offsetY+rowIndex, 0, offsetY+rowIndex+1); // 合并单元格
				wsheet.mergeCells(1, offsetY+rowIndex, 1, offsetY+rowIndex+1); // 合并单元格
				wsheet.mergeCells(3, offsetY+rowIndex, 3, offsetY+rowIndex+1); // 合并单元格
				wsheet.mergeCells(4, offsetY+rowIndex, 4, offsetY+rowIndex+1); // 合并单元格
				wsheet.mergeCells(5, offsetY+rowIndex, 5, offsetY+rowIndex+1); // 合并单元格
			}
			for(int j=0;j<datas.get(i).length;j++){
				Object value = datas.get(i)[j];
				if(value instanceof java.lang.Number) {
					wcell = new Number(offsetX+j, offsetY+rowIndex, ((java.lang.Number)value).doubleValue(), this.contentWcf);
				}else if(value instanceof java.util.Date) {
					wcell = new Label(offsetX+j, offsetY+rowIndex, sdf.format((java.util.Date)value), this.contentWcf);
				}else{
					wcell = new Label(offsetX+j, offsetY+rowIndex, (java.lang.String)value, this.contentWcf);
				}
				wsheet.addCell(wcell);
			}
			rowIndex++;
		}
		//创建额外内容
		buildExtraContent(wsheet, offsetX, offsetY+rowIndex);
		wbook.write(); // 写入文件
		wbook.close();
		os.flush();
		os.close();
	}
	/**
	 * 将已有数据写入Excel,运用本地文件修改前2行为标题得到
	 * @param templateFile 模板文件
	 * @param os 数据流，如果是写本地文件的话，可以是FileOutputStream;如果是写Web下载的话，可以是ServletOupputStream
	 * @param title 工作簿的标题,必须的
	 * @param datas 数据集
	 * @param offsetX 单元格横向偏移量
	 * @param offsetY 单元格纵向偏移量
	 */
	public void exportWithTitle(File templateFile, OutputStream os, String title, List<Object[]> datas, int offsetX, int offsetY) throws BiffException, IOException, RowsExceededException, WriteException {
		WritableWorkbook wbook = null;
		WritableSheet wsheet = null;
		if(templateFile!=null){
			Workbook wb = Workbook.getWorkbook(templateFile);
			wbook = Workbook.createWorkbook(os, wb);
			wsheet = wbook.getSheet(0);
		}else{
			wbook = Workbook.createWorkbook(os); // 建立excel文件
			wsheet = wbook.createSheet("第一页", 0); // sheet名称
		}

		if (title != null && !title.trim().equals("")) {// 添加标题
			
			WritableCell cell = wsheet.getWritableCell(0,0);
			if(cell.getType() == CellType.LABEL){
				Label lable = (Label)cell;
				lable.setString(title);
			}
		}


		int rowIndex = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		WritableCell wcell = null;//单元格
		//往Excel输出数据
		for (int i=0;i<datas.size();i++) {
			for(int j=0;j<datas.get(i).length;j++){
				Object value = datas.get(i)[j];
				if(value instanceof java.lang.Number) {
					wcell = new Number(offsetX+j, offsetY+rowIndex, ((java.lang.Number)value).doubleValue(), this.contentWcf);
				}else if(value instanceof java.util.Date) {
					wcell = new Label(offsetX+j, offsetY+rowIndex, sdf.format((java.util.Date)value), this.contentWcf);
				}else{
					wcell = new Label(offsetX+j, offsetY+rowIndex, (java.lang.String)value, this.contentWcf);
				}
				wsheet.addCell(wcell);
			}
			rowIndex++;
		}
		
		//创建额外内容
		buildExtraContent(wsheet, offsetX, offsetY+rowIndex);
		
		wbook.write(); // 写入文件
		wbook.close();
		os.flush();
		os.close();
	}

	/**
	 * 从Excel中读数据
	 * @param is
	 * @param offsetX 单元格横向偏移量
	 * @param offsetY 单元格纵向偏移量
	 * @return
	 */
	public static String[][][] loadExcelData(InputStream is, int offsetX, int offsetY) {
		try {
			Workbook wb = Workbook.getWorkbook(is);// 得到工作薄
			Sheet[] sheets = wb.getSheets();// 得到工作薄中的工作表
			String[][][] sheetsConent = new String[sheets.length][][];
			for (int i = 0; i < sheets.length; i++) {
				//System.out.println("length:"+sheets.length);
				//System.out.println("rows:"+sheets[i].getRows());
				//System.out.println("columns:"+sheets[i].getColumns());
				if((sheets[i].getRows()-offsetY) <=0 || (sheets[i].getColumns()-offsetX)<=0){
					continue;
				}
				sheetsConent[i] = new String[sheets[i].getRows() - offsetY][sheets[i].getColumns() - offsetX];
				for (int j = 0; j < sheets[i].getRows() - offsetY; j++) {
					for (int k = 0; k < sheets[i].getColumns() - offsetX; k++) {
						Cell cell = sheets[i].getCell(k + offsetX, j + offsetY);// 得到工作表的第一个单元格,即A1
						sheetsConent[i][j][k] = cell.getContents();// getContents()将Cell中的字符转为字符串
					}
				}
			}
			wb.close();// 关闭工作薄
			return sheetsConent;
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public void test1() throws FileNotFoundException{
		File file = new File("D:/text.xls");
		InputStream is = new FileInputStream(file);
		String[][][] sheets = ExcelUtils.loadExcelData(is, 0, 2);
		for(String[][] sheet:sheets){
			for(String[] cells:sheet){
				for(String cell:cells){
					System.out.println(cell);
				}
			}
		}
	}
	
	public void test2() throws Exception{
		//File file = new File("D:/text.xls");
		//OutputStream os = new FileOutputStream(file);
		
		//List<String[]> datas = new ArrayList<String[]>();
		//datas.add(Arrays.asList(new String[]{"1","2","3"}));
		//datas.add(new String[]{"4","5","6"});
		//ExcelUtils.export(os, "测试", null, datas, 0, 0);
		
	}
	
	/**
	 * 导出单行标题的Excel
	 * @param response
	 * @param listMaps  数据集合
	 * @param titles	标题列表
	 * @param length    单元格宽度
	 * @param newName   新建EXCEL 名称
	 */
	public static void exportExcel(HttpServletResponse response,List<Map<String,Object>> listMaps,String[] titles,int[] length,String newName) throws Exception, Exception, Exception, Exception{
		response.setContentType("application/x-msdownload;charset=gbk");
		response.setCharacterEncoding("UTF-8");
		String fileNameTemp = URLEncoder.encode(newName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileNameTemp.getBytes("utf-8"), "gbk"));
		OutputStream os = response.getOutputStream();
		ExcelUtils eu = new ExcelUtils();
		List<Object[]> listObjects = new ArrayList<Object[]>();
		if(listMaps != null){
			//listObjects = DbUtils.ListMapToListObject(listMaps);
		}
		eu.export(os, titles ,length,listObjects);
		os.flush();
		os.close();
	}
	
	
	
}
