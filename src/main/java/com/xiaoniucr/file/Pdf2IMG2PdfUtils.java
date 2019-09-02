package com.xiaoniucr.file;

import java.io.File;
import java.util.Random;

public class Pdf2IMG2PdfUtils {


    //pdf文件路径
    private static String pdfPath = "D:\\pdf\\pdf\\pdf文字.pdf";

    //最终生成的pdf存储目录
    private static String dstPdf = "D:\\pdf\\pdf\\";

    //最终生成的pdf文件名称
    private static String outPdfName = "out.pdf";

    //dpi越大转换后越清晰，相对转换速度越慢,图片大小也越大
    private static int dpi = 150;


    private static Random random=new Random();

    public static void main(String[] args) {
        File file = new File(pdfPath);
        if(file.isFile()&&file.exists()) {
            pdf2Image2Pdf(pdfPath, dstPdf,outPdfName);
        }
    }

    /***
     * PDF文件转PNG图片
     * @param pdfFilePath pdf完整路径
     * @param dstPdfPath 最终生成的pdf存储目录
     * @param outPdfName 最终生成的pdf文件名称
     * @return
     */
    public static void pdf2Image2Pdf(String pdfFilePath, String dstPdfPath,String outPdfName) {
//        File file = new File(pdfFilePath);
//        PDDocument pdDocument;
//        try {
//            if (dstPdfPath.equals("")) {
//                dstPdfPath = file.getParent() ;
//            }
//
//            if (createDirectory(dstPdfPath)) {
//
//                pdDocument = PDDocument.load(file);
//                PDFRenderer renderer = new PDFRenderer(pdDocument);
//                int pages = pdDocument.getNumberOfPages();
//                Document docOut = new Document();
//                FileOutputStream os = new FileOutputStream(dstPdfPath+outPdfName);
//                PdfWriter.getInstance(docOut, os);
//                docOut.open();
//                for (int i = 0; i < pages; i++) {
//                    //第二个参数越大生成图片分辨率越高。
//                    BufferedImage image = renderer.renderImage(i, 2.5f);
//                    //加水印
////                    Graphics2D graphics = image.createGraphics();
////                    graphics.setColor(Color.PINK);
////                    //设置透明度
////                    graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.2f));
////                    //设置水印旋转角度
////                    graphics.rotate(Math.toRadians(30),
////                            (double) image.getWidth() / 2, (double) image
////                                    .getHeight() / 2);
////                    JLabel label = new JLabel("艾思欧");
////                    FontMetrics metrics = label.getFontMetrics(new Font("微软雅黑",Font.BOLD,1000));
////                    int width = metrics.stringWidth(label.getText());//文字水印的宽
////                    int rowsNumber = image.getHeight()/width+image.getHeight()%width;// 图片的高  除以  文字水印的宽  打印的行数(以文字水印的宽为间隔)
////                    int columnsNumber = image.getWidth()/width+image.getWidth()%width;//图片的宽 除以 文字水印的宽  每行打印的列数(以文字水印的宽为间隔)
////                    //防止图片太小而文字水印太长，所以至少打印一次
////                    if(rowsNumber < 1){
////                        rowsNumber = 1;
////                    }
////                    if(columnsNumber < 1){
////                        columnsNumber = 1;
////                    }
////                    for(int j=0;j<rowsNumber;j++){
////                        for(int k=0;k<columnsNumber;k++){
////                           graphics.drawString("艾思欧", i*width + j*width, -i*width + j*width);//画出水印,并设置水印位置
////                        }
////                    }
////                    graphics.dispose();
//                    ByteArrayOutputStream bb = new ByteArrayOutputStream();
//                    ImageIO.write(image, "png", bb);
//                    Image png = Image.getInstance(bb.toByteArray());
//                    float width = png.getWidth();
//                    float heigth = png.getHeight();
//                    png.setAbsolutePosition(0,0);
////                    png.scalePercent(100);
//                    png.scaleAbsolute(width,heigth);// 直接设定显示尺寸
//                    png.setAlignment(Image.ALIGN_MIDDLE);
//                    docOut.setPageSize(new Rectangle(width, heigth));
//                    docOut.newPage();
//                    docOut.add(png);
//                }
//                docOut.close();
//                System.out.println("success");
//            } else {
//                System.out.println("error:" + "creat" + dstPdfPath + "wrong");
//            }
//
//        } catch (Exception e) {
//            System.out.println("Exception");
//            e.printStackTrace();
//        }
    }




    private static boolean createDirectory(String folder) {
        File dir = new File(folder);
        if (dir.exists()) {
            return true;
        } else {
            return dir.mkdirs();
        }

    }
}
