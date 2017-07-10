package com.project.web.util;

//import com.project.web.controller.CopyText;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by qiaowentao on 2016/10/8.
 */
public class ZipUtil {

    static byte[] buf=new byte[2048];

    public static String createZip(Integer reportType){
        //Zip文件夹 系统临时文件夹
        String folder=System.getProperty("java.io.tmpdir");
        String zipName = folder+System.currentTimeMillis()+"_导出证书"+".zip";
        ZipOutputStream out = null;
        BufferedOutputStream bos = null;
        File pdfFile = null;
        int b;
        try{
            out = new ZipOutputStream(new FileOutputStream(zipName));
            bos = new BufferedOutputStream(out);
            out.putNextEntry(new ZipEntry("高思杯证书评级规则.docx"));
            InputStream ins = ZipUtil.class.getResourceAsStream("/report/gaosibei.docx");
            while ((b = ins.read(buf)) != -1) {
                bos.write(buf,0,b); // 将字节流写入当前zip目录
            }
            bos.flush();
            out.closeEntry();
            ins.close(); // 输入流关闭
            List<Map<String,Object>> dataList = new ArrayList();

            Map<String,Object> jasperMap=new HashMap<>();
            String gradeId = "6";
            //获取背景图片
            String jpgName = "/report/"+Integer.valueOf(gradeId)+"/"+reportType+".jpg";
            InputStream path = ZipUtil.class.getResourceAsStream(jpgName);
            String studentName = "刘玉强";
            String gradeName = "六年级";
            String phone = "15876532479";
            String testNumber = "110";
            String level = "A";
            jasperMap.put("studentName",studentName);
            jasperMap.put("gradeName",gradeName.substring(0,1));
            jasperMap.put("level",level);
            jasperMap.put("path",path);
            dataList.add(jasperMap);
            String pdfName = studentName+"_"+phone+"_"+testNumber+"_"+"证书.pdf";
            //动态的根据年级选择模板
            String sourceFileName = ZipUtil.class.getResource("/report/pdf.jasper").getPath();
            //Jasper 生成PDF
            JRBeanCollectionDataSource beanColDataSource =
                    new JRBeanCollectionDataSource(dataList);
            File jasperFile = new File(sourceFileName);
            //JasperReport jasperReport= (JasperReport) JRLoader.loadObject(jasperFile);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperFile.getPath(),null,beanColDataSource);
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING,"UTF-8");
            exporter.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME,pdfName);
            exporter.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY,Boolean.TRUE);
            exporter.exportReport();
            //动态的根据不同的年级进去不同的文件夹
            String base = "证书" +"/"+ gradeName +"/"+ pdfName;
            // 创建zip压缩进入点base
            out.putNextEntry(new ZipEntry(base));
            FileInputStream fis = new FileInputStream(pdfName);
            BufferedInputStream bis = new BufferedInputStream(fis);
            while ((b = bis.read(buf)) != -1) {
                bos.write(buf,0,b); // 将字节流写入当前zip目录
            }
            bos.flush();
            out.closeEntry();
            bis.close();
            fis.close(); // 输入流关闭
            pdfFile = new File(pdfName);
            if (pdfFile.exists()){
                pdfFile.delete();
            }
        }catch (JRException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return zipName;
    }

    public static void main(String[] args) {
       /* String zipName = createZip(0);
        CopyText copyText = new CopyText();
        copyText.Copy(new File(zipName),new File("D:/export/刘玉强.Zip"));*/
        /*InputStream inputStream = ZipUtil.class.getResourceAsStream("/report/6/0.jpg");
        FileOutputStream fos = null;
        byte[] byt = new byte[1024];
        try {
            fos = new FileOutputStream(new File("C:/Users/qiaowentao/Desktop/a.jpg"));
            while((inputStream.read(byt)) != -1){
                fos.write(byt,0,byt.length);
            }
            System.out.println("完成");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

}
