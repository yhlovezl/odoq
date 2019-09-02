package com.xiaoniucr.file;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class PdfWatermarkUtils {



    public static void main(String[] args) {



        OutputStream os = null;
        File file = new File("D:\\pdf\\样本\\GBT19001—2015质量管理体系 要求（正式版） (1).pdf");
        try {

            Long beginTime = System.currentTimeMillis();
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            Document docOut = new Document();
            FileOutputStream ost = new FileOutputStream("D:\\pdf\\输出\\out.pdf");
            PdfWriter.getInstance(docOut, ost);
            docOut.open();
           Image mark = ImageIO.read(new File("D:\\pdf\\样本\\water.png"));
            for (int i = 0; i < pageCount; i++) {

                //设置pdf的清晰度，值越大pdf图片越清晰，转换越慢，转换出来的pdf文件越大
                BufferedImage image = renderer.renderImage(i, 2.5f);
                ByteArrayOutputStream bb = new ByteArrayOutputStream();
                ImageIO.write(image, "png", bb);
                Image srcImg = ImageIO.read(new ByteArrayInputStream(bb.toByteArray()));
                int imgWidth = srcImg.getWidth(null);
                int imgHeight = srcImg.getHeight(null);
                // 得到画笔对象
                Graphics2D g = image.createGraphics();
                g.drawImage(srcImg, 0, 0, image.getWidth(), image.getHeight(), null);
                // g.rotate(Math.toRadians(-45)); //水印旋转
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.2f)); //设置水印透明度

                int waterWidth = mark.getWidth(null);
                int waterHight = mark.getHeight(null);
                //平铺
                if(WatermarkUtils.WATERMARK_STYLE_TILED.equals(20)) {
                    for (int y = 0; y < imgHeight; y += waterHight * 2) {
                        for (int x = 0; x < imgWidth; x += waterWidth) {
                            g.drawImage(mark, x + y, y-x, null);
                        }
                    }
                }else {//居中
                    int x = (imgWidth - waterWidth/2)/2;
                    int y = (imgHeight - waterHight*3)/2;
                    g.drawImage(mark, imgWidth/8, imgHeight/4, 6*imgWidth/8,imgHeight/2,null);
                }
                g.dispose();
                ByteArrayOutputStream cc = new ByteArrayOutputStream();
                // 生成图片
                ImageIO.write(image, "JPG", cc);
                com.itextpdf.text.Image png = com.itextpdf.text.Image.getInstance(cc.toByteArray());
                //设置图片在pdf中的位置，页面左下角有坐标原点，如果不设置默认居中
                png.setAbsolutePosition(0,0);
                //设置对其
                png.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
                //直接设置图片大小
//                png.scaleAbsolute(png.getWidth()-50,png.getHeight()-80);
                docOut.setPageSize(new Rectangle(png.getWidth(), png.getHeight()));
                docOut.newPage();
                docOut.add(png);
            }
            docOut.close();
            Long endTime = System.currentTimeMillis();
            System.out.println("PDF转换耗时====>"+((endTime - beginTime)/1000)+"秒");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
