package com.xiaoniucr.file;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * pdf转图片pdf加水印
 */
public class PdfConvertUtils {

    /**水印格式：居中*/
    public static final Integer WATERMARK_STYLE_CENTER = 0;

    /**水印格式：平铺*/
    public static final Integer WATERMARK_STYLE_TILED = 1;


    /**
     * pdf转pdf图片加水印
     * @param srcPdfPath        pdf源文件地址
     * @param tarPdfPath        输出的图片pdf文件地址
     * @param markImgPath       水印图片地址
     * @param watermarkStyle    水印方式：0居中，1平铺
     * @return
     */
    public static String pdfConvert(String srcPdfPath,String tarPdfPath,String markImgPath,Integer watermarkStyle){

        try {
            Long beginTime = System.currentTimeMillis();
            // 读取源pdf文件
            PDDocument doc = PDDocument.load(new File(srcPdfPath));
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            // 获取水印图片
            Image mark = ImageIO.read(new File(markImgPath));
            Document pdfOut = new Document();
            FileOutputStream outputStream = new FileOutputStream(tarPdfPath);
            PdfWriter.getInstance(pdfOut, outputStream);
            pdfOut.open();
            // 逐页转换
            for (int i = 0; i < pageCount; i++) {
                // 设置pdf的清晰度，值越大pdf图片越清晰，转换越慢，转换出来的pdf文件越大
                BufferedImage image = renderer.renderImage(i, 2.5f);
                ByteArrayOutputStream bb = new ByteArrayOutputStream();
                ImageIO.write(image, "JPG", bb);
                Image srcImg = ImageIO.read(new ByteArrayInputStream(bb.toByteArray()));
                // 图片大小
                int imgWidth = srcImg.getWidth(null);
                int imgHeight = srcImg.getHeight(null);
                // 得到画笔对象
                Graphics2D g = image.createGraphics();
                g.drawImage(srcImg, 0, 0, image.getWidth(), image.getHeight(), null);
                // g.rotate(Math.toRadians(-45)); //水印旋转
                // 设置透明度，值越大水印图片显示越清楚，也即越不透明
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.2f)); //设置水印透明度
                // 水印图片大小
                int waterWidth = mark.getWidth(null);
                int waterHight = mark.getHeight(null);
                // 居中
                if(PdfConvertUtils.WATERMARK_STYLE_CENTER.equals(watermarkStyle)) {

//                    int x = (imgWidth - waterWidth/2)/2;
//                    int y = (imgHeight - waterHight*3)/2;
                    g.drawImage(mark, imgWidth/8, imgHeight/4, 6*imgWidth/8,imgHeight/2,null);
                }else {
                    // 平铺
//                    for (int y = 0; y < imgHeight; y += waterHight * 2) {
//                        for (int x = 0; x < imgWidth; x += waterWidth) {
//                            g.drawImage(mark, x + y, y-x, null);
//                        }
//                    }
                    g.drawImage(mark,imgWidth/8,imgHeight/8,6*imgWidth/8,3*imgHeight/8,null);
                    g.drawImage(mark,imgWidth/8,5*imgHeight/8,6*imgWidth/8,3*imgHeight/8,null);
                }
                g.dispose();
                ByteArrayOutputStream cc = new ByteArrayOutputStream();
                // 生成图片
                ImageIO.write(image, "JPG", cc);
                com.itextpdf.text.Image png = com.itextpdf.text.Image.getInstance(cc.toByteArray());
                // 设置图片在pdf中的位置，页面左下角有坐标原点，如果不设置默认居中
                png.setAbsolutePosition(0,0);
                // 设置对其方式
                png.setAlignment(com.itextpdf.text.Image.ALIGN_MIDDLE);
                // 直接设置图片大小
                // png.scaleAbsolute(png.getWidth()-50,png.getHeight()-80);
                pdfOut.setPageSize(new com.itextpdf.text.Rectangle(png.getWidth(), png.getHeight()));
                pdfOut.newPage();
                pdfOut.add(png);
            }
            pdfOut.close();
            Long endTime = System.currentTimeMillis();
            System.out.println("PDF转换耗时====>"+((endTime - beginTime)/1000)+"秒");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    public static void main(String[] args) {

        pdfConvert("D:\\pdf\\样本\\GBT19001—2015质量管理体系 要求（正式版） (1).pdf","D:\\pdf\\输出\\out.pdf","D:\\pdf\\样本\\water.png",1);
    }

}
