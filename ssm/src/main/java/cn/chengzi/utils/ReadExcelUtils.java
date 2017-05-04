package cn.chengzi.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class ReadExcelUtils {
	  private Logger logger = LoggerFactory.getLogger(ReadExcelUtils.class);  
	  private Workbook wb;  
	  private Sheet sheet;  
	  private Row row;  
	  /**
	   * 检查文件是够否和格式
	   * <P>ReadExcelUtils.readExcelUtils()<P>;
	   * @param filePath
	   * @param response
	   */
	  public Workbook readExcelUtils(String filePath){
		  if (filePath == null) {
			  return null;
		  }
		  String endName = filePath.substring(filePath.lastIndexOf("."));
		  try {  
	            InputStream is = new FileInputStream(filePath);  
	            if(".xls".equals(endName)){  
	                wb = new HSSFWorkbook(is);  
	            }else if(".xlsx".equals(endName)){  
	                wb = new XSSFWorkbook(is);  
	            }else{  
	                wb=null;  
	            }  
	        } catch (FileNotFoundException e) {  
	            logger.error("FileNotFoundException", e);  
	        } catch (IOException e) {  
	            logger.error("IOException", e);  
	        }
		return wb;  
	  }
	  /**
	   * 获取列表头
	   * <P>ReadExcelUtils.readExcelTitle()<P>;
	   * @return
	   * @throws Exception
	   */
	  public String[] readExcelTitle() throws Exception{  
	        if(wb==null){  
	            throw new Exception("Workbook对象为空！");  
	        }  
	        sheet = wb.getSheetAt(0);  
	        row = sheet.getRow(0);  
	        // 标题总列数  
	        int colNum = row.getPhysicalNumberOfCells();  
	        
	        String[] title = new String[colNum];  
	        for (int i = 0; i < colNum; i++) {  
	            //title[i] = row.getCell(i).getCellFormula();  
	            title[i] = row.getCell(i).getStringCellValue().trim();   
	           
	        }  
	        return title;  
	    }  
	  /**
	   * 获取内容
	   * <P>ReadExcelUtils.readExcelContent()<P>;
	   * @return
	   * @throws Exception
	   */
	  public Map<Integer, Map<Integer,Object>> readExcelContent() throws Exception{  
	        if(wb==null){  
	            throw new Exception("Workbook对象为空！");  
	        }  
	        Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();  
	          
	        sheet = wb.getSheetAt(0);  
	        // 得到总行数  
	        int rowNum = sheet.getLastRowNum();  
	        row = sheet.getRow(0);  
	        //获取总列数
	        int colNum = row.getPhysicalNumberOfCells();  
	        // 正文内容应该从第二行开始,第一行为表头的标题  
	        for (int i = 1; i <= rowNum; i++) {  
	            row = sheet.getRow(i);  
	            int j = 0;  
	            Map<Integer,Object> cellValue = new HashMap<Integer, Object>();  
	            while (j < colNum) {  
	                Object obj = getCellFormatValue(row.getCell(j));  
	                cellValue.put(j, obj);  
	                j++;  
	            }  
	            content.put(i, cellValue);  
	        }  
	        return content;  
	    }  
	  
	  private Object getCellFormatValue(Cell cell) {  
	        Object cellvalue = "";  
	        if (cell != null) {  
	            // 判断当前Cell的Type  
	            switch (cell.getCellType()) {  
	            case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC  
	            case Cell.CELL_TYPE_FORMULA: {  
	                // 判断当前的cell是否为Date  
	                if (DateUtil.isCellDateFormatted(cell)) {  
	                    // 如果是Date类型则，转化为Data格式  
	                    // data格式是带时分秒的：2013-7-10 0:00:00  
	                    // cellvalue = cell.getDateCellValue().toLocaleString();  
	                    // data格式是不带带时分秒的：2013-7-10  
	                    Date date = cell.getDateCellValue();  
	                    cellvalue = date;  
	                } else {// 如果是纯数字  
	  
	                    // 取得当前Cell的数值  
	                    cellvalue = String.valueOf(cell.getNumericCellValue());  
	                }  
	                break;  
	            }  
	            case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING  
	                // 取得当前的Cell字符串  
	                cellvalue = cell.getRichStringCellValue().getString();  
	                break;  
	            default:// 默认的Cell值  
	                cellvalue = "";  
	            }  
	        } else {  
	            cellvalue = "";  
	        }  
	        return cellvalue;  
	    }  
	  
	private void responseSendMsgForImg(HttpServletResponse response, String strMsg) {
			PrintWriter out = null;
			try {
				response.setContentType("text/html;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				out = response.getWriter();
				out.write(strMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(out != null){
					out.flush();
					out.close();
				}
			}
		}
	public static void main(String[] args) throws Exception {
		String filePath = "E:\\冻品开发任务划分表.xlsx";
		ReadExcelUtils excelUtils = new ReadExcelUtils();
		Workbook workbook = excelUtils.readExcelUtils(filePath);
		if (workbook==null) {
			System.out.println("check");
			return ;
		}
		 String[] title = excelUtils.readExcelTitle();
		for (String string2 : title) {
			System.out.println(string2);
		}
		 Map<Integer, Map<Integer,Object>> map = excelUtils.readExcelContent();  
         System.out.println("获得Excel表格的内容:");  
         for (int i = 1; i <= map.size(); i++) {
        	 Map<Integer, Object> map2 = map.get(i);
        	 for (Map.Entry<Integer,Object> qq : map2.entrySet()) {
        		 System.out.print(qq.getKey().toString()+qq.getValue());
				
			}
         }  
	}
}
