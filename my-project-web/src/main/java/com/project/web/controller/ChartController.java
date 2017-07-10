package com.project.web.controller;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 生成图表
 *
 * Created by qiaowentao on 2016/10/25.
 */
@Controller
@RequestMapping(value="/chart")
public class ChartController {

    private final static Logger logger = LoggerFactory.getLogger(ChartController.class);

    @RequestMapping(value="/toChartIndex")
    public ModelAndView toChartIndex(ModelAndView model, HttpServletRequest request, HttpServletResponse response){

        model.setViewName("/chart/chartIndex");
        return model;
    }

    @RequestMapping(value="/getLineChart")
    public ModelAndView getLineChart(ModelAndView model, HttpServletRequest request, HttpServletResponse response){
        CategoryDataset dataset = getDataSetLine();

        //如果把createLineChart改为createLineChart3D就变为了3D效果的折线图
        JFreeChart chart = ChartFactory.createLineChart("图表标题", "X轴标题", "Y轴标题", dataset,
                PlotOrientation.VERTICAL, // 绘制方向
                true, // 显示图例
                true, // 采用标准生成器
                false // 是否生成超链接
        );
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20)); // 设置标题字体
        chart.getLegend().setItemFont(new Font("宋体",Font.ITALIC,10));// 设置图例类别字体
        chart.setBackgroundPaint(ChartColor.WHITE);// 设置背景色
        //获取绘图区对象
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY); // 设置绘图区背景色
        plot.setRangeGridlinePaint(Color.WHITE); // 设置水平方向背景线颜色
        plot.setRangeGridlinesVisible(true);// 设置是否显示水平方向背景线,默认值为true
        plot.setDomainGridlinePaint(Color.WHITE); // 设置垂直方向背景线颜色
        plot.setDomainGridlinesVisible(true); // 设置是否显示垂直方向背景线,默认值为false


        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("宋体",Font.BOLD,15));  //设置Y轴坐标上标题的文字
        domainAxis.setTickLabelFont(new Font("sans-serif",Font.BOLD,12));// 设置坐标轴标尺值字体
        domainAxis.setLowerMargin(0.01);// 左边距 边框距离
        domainAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。
        domainAxis.setMaximumCategoryLabelLines(2);

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("宋体",Font.BOLD,22));
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//Y轴显示整数
        rangeAxis.setAutoRangeMinimumSize(1);   //最小跨度
        rangeAxis.setUpperMargin(0.18);//上边距,防止最大的一个数据靠近了坐标轴。
        rangeAxis.setLowerBound(0);   //最小值显示0
        rangeAxis.setAutoRange(false);   //不自动分配Y轴数据
        rangeAxis.setTickMarkStroke(new BasicStroke(1.6f));     // 设置坐标标记大小
        rangeAxis.setTickMarkPaint(Color.BLACK);     // 设置坐标标记颜色



        // 获取折线对象
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        BasicStroke realLine = new BasicStroke(1.8f); // 设置实线
        // 设置虚线
        float dashes[] = { 5.0f };
        BasicStroke brokenLine = new BasicStroke(2.2f, // 线条粗细
                BasicStroke.CAP_ROUND, // 端点风格
                BasicStroke.JOIN_ROUND, // 折点风格
                8f, dashes, 0.6f);
        for (int i = 0; i < dataset.getRowCount(); i++) {
            if (i % 2 == 0)
                renderer.setSeriesStroke(i, realLine); // 利用实线绘制
            else
                renderer.setSeriesStroke(i, brokenLine); // 利用虚线绘制
        }

        plot.setNoDataMessage("无对应的数据，请重新查询。");
        //plot.setNoDataMessageFont(titleFont);//字体的大小
        plot.setNoDataMessagePaint(Color.RED);//字体颜色

        try {
            String jpgName = ServletUtilities.saveChartAsJPEG(chart,1000,800,request.getSession());
            String chartURL=request.getContextPath() + "/chart?filename="+jpgName;
            model.addObject("chartURL", chartURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }

    private static CategoryDataset getDataSetLine() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(40, "", "普通动物学");
        dataset.addValue(50, "", "生物学");
        dataset.addValue(60, "", "动物解剖学");
        dataset.addValue(70, "", "生物理论课");
        dataset.addValue(80, "", "动物理论课");
        return dataset;
    }

    @RequestMapping(value="/getPieChart")
    public ModelAndView getPieChart(ModelAndView model, HttpServletRequest request, HttpServletResponse response){

        JFreeChart chart= ChartFactory.createPieChart("某公司人员组织数据图",getDatasetPie(),true,true,false);
        chart.setTitle(new TextTitle("某公司组织结构图",new Font("宋体", Font.BOLD+Font.ITALIC,20)));

        LegendTitle legend=chart.getLegend(0);//设置Legend
        legend.setItemFont(new Font("宋体",Font.BOLD,14));
        PiePlot plot=(PiePlot) chart.getPlot();//设置Plot
        plot.setLabelFont(new Font("隶书",Font.BOLD,16));

        try {
            /*OutputStream os = new FileOutputStream("C:/Users/qiaowentao/Desktop/company.jpeg");//图片是文件格式的，故要用到FileOutputStream用来输出。
            ChartUtilities.writeChartAsJPEG(os, chart, 1000, 800);*/
            String jpgName = ServletUtilities.saveChartAsJPEG(chart,1000,800,request.getSession());
            String chartURL=request.getContextPath() + "/chart?filename="+jpgName;
            model.addObject("chartPieURL", chartURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //使用一个面向application的工具类，将chart转换成JPEG格式的图片。第3个参数是宽度，第4个参数是高度。

        return model;
    }

    public static DefaultPieDataset getDatasetPie()
    {
        DefaultPieDataset dpd=new DefaultPieDataset(); //建立一个默认的饼图
        dpd.setValue("管理人员", 25);  //输入数据
        dpd.setValue("市场人员", 25);
        dpd.setValue("开发人员", 35);
        dpd.setValue("其他人员", 15);
        return dpd;
    }

    @RequestMapping(value="/getBarChart")
    public ModelAndView getBarChart(ModelAndView model, HttpServletRequest request, HttpServletResponse response){

//1. 获得数据集合
        CategoryDataset dataset = getDataSetBar();
        //2. 创建柱状图
        JFreeChart chart = ChartFactory.createBarChart3D("学生对教师授课满意度", // 图表标题
                "课程名", // 目录轴的显示标签
                "百分比", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                false, // 是否显示图例(对于简单的柱状图必须是false)
                false, // 是否生成工具
                false // 是否生成URL链接
        );
        //3. 设置整个柱状图的颜色和文字（char对象的设置是针对整个图形的设置）
        chart.setBackgroundPaint(ChartColor.WHITE); // 设置总的背景颜色

        //4. 获得图形对象，并通过此对象对图形的颜色文字进行设置
        CategoryPlot p = chart.getCategoryPlot();// 获得图表对象
        p.setBackgroundPaint(ChartColor.lightGray);//图形背景颜色
        p.setRangeGridlinePaint(ChartColor.WHITE);//图形表格颜色

        //5. 设置柱子宽度
        BarRenderer renderer = (BarRenderer)p.getRenderer();
        renderer.setMaximumBarWidth(0.06);

        //解决乱码问题
        getChartByFont(chart);

        //6. 将图形转换为图片，传到前台
        String fileName = null;
        try {
            fileName = ServletUtilities.saveChartAsJPEG(chart, 1000, 800, null, request.getSession());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String chartURL=request.getContextPath() + "/chart?filename="+fileName;
        model.addObject("chartBarURL", chartURL);

        return model;
    }

    //设置文字样式
    private static void getChartByFont(JFreeChart chart) {
        //1. 图形标题文字设置
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("宋体",Font.BOLD,20));

        //2. 图形X轴坐标文字的设置
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setLabelFont(new Font("宋体",Font.BOLD,22));  //设置X轴坐标上标题的文字
        axis.setTickLabelFont(new Font("宋体",Font.BOLD,15));  //设置X轴坐标上的文字

        //2. 图形Y轴坐标文字的设置
        ValueAxis valueAxis = plot.getRangeAxis();
        valueAxis.setLabelFont(new Font("宋体",Font.BOLD,15));  //设置Y轴坐标上标题的文字
        valueAxis.setTickLabelFont(new Font("sans-serif",Font.BOLD,12));//设置Y轴坐标上的文字
    }

    // 获取一个演示用的组合数据集对象
    private static CategoryDataset getDataSetBar() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(40, "", "语文");
        dataset.addValue(50, "", "物理");
        dataset.addValue(60, "", "化学");
        dataset.addValue(70, "", "生物");
        dataset.addValue(80, "", "地理");
        return dataset;
    }

}
