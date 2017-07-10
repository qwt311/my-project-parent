package com.project.common.util.excel;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.helpers.LogLog.error;

/**
 *  在实际开发中，由于业务需要，常常需要进行Excel导入导出操作。以前做一些简单的导入时，先准备一个模板，再进行导入，单有十几、
 *
 * 二十几个导入模板时，往往要做十几、二十几个模板。而且，当在模板中需要有下拉框，而且下拉数据要实时查询时，这样的方法就不太好了；
 *
 *现在，做成了动态生成导入模板，只要参数传对了，就可以生成任何我们想要的导入模板，而且随意设置下拉框的数目、位置。
 *
 * 1、需要用到的jar包：
 *
 *poi-3.10-FINAL.jar
 *
 * poi-ooxml-3.10-FINAL.jar
 *
 * poi-ooxml-schemas-3.10-FINAL.jar
 *
 * gradle引入：
 *
 * compile group: 'org.apache.poi', name: 'poi', version: '3.10'
 * compile group: 'org.apache.poi', name: 'poi-ooxml', version: '3.10-FINAL'
 *
 *
 *
 * Created by qiaowentao on 2017/1/18.
 */
public class ExcelTemplate {

    /**
     * @Title: createExcelTemplate
     * @Description: 生成Excel导入模板
     * @param fileName  Excel文件路径
     * @param handers   Excel列标题(数组)
     * @param downData  下拉框数据(数组)
     * @param downRows  下拉列的序号(数组,序号从0开始)
     * @param request
     *@param response
     *@return void
     *@throws
     */
    public static void createExcelTemplate(String fileName, String[] handers,
                                           List<String[]> downData, String[] downRows, HttpServletRequest request, HttpServletResponse response){

        HSSFWorkbook wb = new HSSFWorkbook();//创建工作薄

        //表头样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        //字体样式
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setFontName("微软雅黑");
        fontStyle.setFontHeightInPoints((short)12);
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(fontStyle);

        //新建sheet
        HSSFSheet sheet1 = wb.createSheet("Sheet1");
        HSSFSheet sheet2 = wb.createSheet("Sheet2");
        HSSFSheet sheet3 = wb.createSheet("Sheet3");

        //生成sheet1内容
        HSSFRow rowFirst = sheet1.createRow(0);//第一个sheet的第一行为标题
        //写标题
        for(int i=0;i<handers.length;i++){
            HSSFCell cell = rowFirst.createCell(i); //获取第一行的每个单元格
            sheet1.setColumnWidth(i, 8000); //设置每列的列宽
            cell.setCellStyle(style); //加样式
            cell.setCellValue(handers[i]); //往单元格里写数据
        }

        if(null != downRows && downRows.length > 0){
            //设置下拉框数据
            String[] arr = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
            int index = 0;
            HSSFRow row;

            for(int r=0;r<downRows.length;r++){
                String[] dlData = downData.get(r);//获取下拉对象
                int rownum = Integer.parseInt(downRows[r]);

                if(dlData.length<5){ //5以内的下拉
                    //5以内的下拉,参数分别是：作用的sheet、下拉内容数组、起始行、终止行、起始列、终止列
                    sheet1.addValidationData(setDataValidation(sheet1, dlData, 1, 50000, rownum ,rownum)); //超过255个报错
                } else { //5以上的下拉，即下拉列表元素很多的情况

                    //1、设置有效性
                    //String strFormula = "Sheet2!$A$1:$A$5000" ; //Sheet2第A1到A5000作为下拉列表来源数据
                    String strFormula = "Sheet2!$"+arr[index]+"$1:$"+arr[index]+"$5000"; //Sheet2第A1到A5000作为下拉列表来源数据
                    sheet2.setColumnWidth(r, 4000); //设置每列的列宽
                    //设置数据有效性加载在哪个单元格上,参数分别是：从sheet2获取A1到A5000作为一个下拉的数据、起始行、终止行、起始列、终止列
                    sheet1.addValidationData(SetDataValidation(strFormula, 1, 50000, rownum, rownum)); //下拉列表元素很多的情况

                    //2、生成sheet2内容
                    for(int j=0;j<dlData.length;j++){
                        if(index==0){ //第1个下拉选项，直接创建行、列
                            row = sheet2.createRow(j); //创建数据行
                            sheet2.setColumnWidth(j, 4000); //设置每列的列宽
                            row.createCell(0).setCellValue(dlData[j]); //设置对应单元格的值

                        } else { //非第1个下拉选项

                            int rowCount = sheet2.getLastRowNum();
                            //System.out.println("========== LastRowNum =========" + rowCount);
                            if(j<=rowCount){ //前面创建过的行，直接获取行，创建列
                                //获取行，创建列
                                sheet2.getRow(j).createCell(index).setCellValue(dlData[j]); //设置对应单元格的值

                            } else { //未创建过的行，直接创建行、创建列
                                sheet2.setColumnWidth(j, 4000); //设置每列的列宽
                                //创建行、创建列
                                sheet2.createRow(j).createCell(index).setCellValue(dlData[j]); //设置对应单元格的值
                            }
                        }
                    }
                    index++;
                }
            }
        }

        try {
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");

            //2.设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-disposition", "attachment;filename=\""+ new String((fileName).getBytes("GBK"),"ISO-8859-1") + "\"");    //这里的是要导出的文件显示的名字

            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: SetDataValidation
     * @Description: 下拉列表元素很多的情况 (5以上的下拉)
     * @param strFormula
     * @param firstRow
     * @param endRow
     * @param firstCol
     * @param endCol
     * @return
     */
    private static HSSFDataValidation SetDataValidation(String strFormula,int firstRow, int endRow, int firstCol, int endCol){

        //设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(strFormula);
        HSSFDataValidation dataValidation = new HSSFDataValidation(regions,constraint);

        dataValidation.createErrorBox("Error", "Error");
        dataValidation.createPromptBox("", null);

        return dataValidation;
    }

    /**
     * @Title: setDataValidation
     * @Description: 下拉列表元素不多的情况(5以内的下拉)
     * @param sheet
     * @param textList
     * @param firstRow
     * @param endRow
     * @param firstCol
     * @param endCol
     * @return
     */
    private static DataValidation setDataValidation(Sheet sheet, String[] textList, int firstRow, int endRow, int firstCol, int endCol){

        DataValidationHelper helper = sheet.getDataValidationHelper();
        //加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textList);
        //DVConstraint constraint = new DVConstraint();
        constraint.setExplicitListValues(textList);

        //设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol, (short) endCol);

        //数据有效性对象
        DataValidation data_validation = helper.createValidation(constraint, regions);
        //DataValidation data_validation = new DataValidation(regions, constraint);

        return data_validation;
    }

    /**
     * @Title: getExcel
     * @Description: 下载指定路径的Excel文件
     * @param url
     * @param fileName
     * @param response
     * @param request
     */
    public static void getExcel(String url, String fileName, HttpServletResponse response, HttpServletRequest request){

        try{
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");

            //2.设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-disposition", "attachment;filename=\""+ new String((fileName).getBytes("GBK"),"ISO-8859-1") + "\"");    //这里的是要导出的文件显示的名字
            //response.setHeader("Content-Disposition", "attachment;filename="
            //+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xls"); //中文文件名

            //通过文件路径获得File对象
            File file = new File(url);

            FileInputStream in = new FileInputStream(file);
            //3.通过response获取OutputStream对象(out)
            OutputStream out = new BufferedOutputStream(response.getOutputStream());

            int b = 0;
            byte[] buffer = new byte[2048];
            while ((b=in.read(buffer)) != -1){
                out.write(buffer,0,b);//4.写到输出流(out)中
            }

            in.close();
            out.flush();
            out.close();

        } catch(IOException e) {
            error("下载Excel模板异常", e);
        }

    }

    /**
     * @Title: encodeChineseDownloadFileName
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param request
     * @param pFileName
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String encodeChineseDownloadFileName(HttpServletRequest request, String pFileName)throws UnsupportedEncodingException {

        String filename;
        String agent = request.getHeader("USER-AGENT");
        //System.out.println("agent==========》"+agent);

        if (null != agent) {
            if (-1 != agent.indexOf("Firefox")) {//Firefox
                filename = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(pFileName.getBytes("UTF-8")))) + "?=";
            } else if (-1 != agent.indexOf("Chrome")) {//Chrome
                filename = new String(pFileName.getBytes(), "ISO8859-1");
            } else{//IE7
                filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
                filename = StringUtils.replace(filename, "+", "%20");//替换空格
            }
        } else {
            filename = pFileName;
        }

        return filename;

    }

    /**
     * @Title: delFile
     * @Description: 删除文件
     * @param @param filePath  文件路径
     * @return void
     * @throws
     */
    public static void delFile(String filePath) {
        java.io.File delFile = new java.io.File(filePath);
        delFile.delete();
    }

    public static void main(String[] args) {

        //调用的例子
        String fileName = "员工信息表"; //模板名称
        String[] handers = {"姓名","性别","证件类型","证件号码","服务结束时间","参保地","民族"}; //列标题

        //下拉框数据,如果没有下拉数据传入null即可
        List<String[]> downData = new ArrayList<>();
        String[] str1 = {"男","女","未知"};
        String[] str2 = {"北京","上海","广州","深圳","武汉","长沙","杭州"};
        String[] str3 = {"01-汉族","02-蒙古族","03-回族","04-藏族","05-维吾尔族","06-苗族","07-彝族","08-壮族","09-布依族","10-朝鲜族",
                "11-满族","12-侗族","13-瑶族","14-白族","15-土家族","16-哈尼族","17-哈萨克族","18-傣族","19-黎族","20-傈僳族",
                "21-佤族","22-畲族","23-高山族","24-拉祜族","25-水族","26-东乡族","27-纳西族","28-景颇族","29-柯尔克孜族","30-土族",
                "31-达斡尔族","32-仫佬族","33-羌族","34-布朗族","35-撒拉族","36-毛难族","37-仡佬族","38-锡伯族","39-阿昌族","40-普米族",
                "41-塔吉克族","42-怒族","43-乌孜别克族","44-俄罗斯族","45-鄂温克族","46-德昂族","47-保安族","48-裕固族","49-京族","50-塔塔尔族",
                "51-独龙族","52-鄂伦春族","53-赫哲族","54-门巴族","55-珞巴族","56-基诺族","98-外国血统","99-其他"};
        downData.add(str1);
        downData.add(str2);
        downData.add(str3);
        String [] downRows = {"1","5","6"}; //下拉的列序号数组(序号从0开始)


        try {
            //request、response 是 controller 中传入的参数
            HttpServletRequest request = null;
            HttpServletResponse response = null;
            createExcelTemplate(fileName, handers, downData, downRows, request, response);

        } catch (Exception e) {
            System.out.println("导出模板异常：" + e.getMessage());
        }

    }

}
