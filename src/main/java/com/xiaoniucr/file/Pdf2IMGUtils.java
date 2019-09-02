package com.xiaoniucr.file;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;


/**
 * PDF转图片
 */
public class Pdf2IMGUtils {


    //pdf文件路径
    private static String pdfPath = "D:\\pdf\\pdf\\pdf文字.pdf";

    //pdf转换出的图片存储路径
    private static String dstImgFolder = "D:\\pdf\\img";

    //dpi越大转换后越清晰，相对转换速度越慢,图片大小也越大
    private static int dpi = 150;

    //需要转换的页数：0表示全部
    private static int page = 5;

    private static Random random=new Random();

    public static void main(String[] args) {
        File file = new File(pdfPath);
        if(file.isFile()&&file.exists()) {
            pdf2Image(pdfPath, dstImgFolder, dpi,page);
        }
    }

    /***
     * PDF文件转PNG图片
     * @param PdfFilePath pdf完整路径
     * @param dstImgFolder 图片存放的文件夹
     * @param dpi dpi越大转换后越清晰，相对转换速度越慢
     * @param flag 页数 为0则转换全部页数
     * @return
     */
    public static void pdf2Image(String PdfFilePath, String dstImgFolder, int dpi,int flag) {
        File file = new File(PdfFilePath);
        PDDocument pdDocument;
        try {
            String imgPDFPath = file.getParent();
            int dot = file.getName().lastIndexOf('.');
            String imagePDFName = file.getName().substring(0, dot);
            String imgFolderPath = null;
            if (dstImgFolder.equals("")) {
                imgFolderPath = imgPDFPath ;
            } else {
                imgFolderPath = dstImgFolder;
            }

            if (createDirectory(imgFolderPath)) {
                pdDocument = PDDocument.load(file);

                PDFRenderer renderer = new PDFRenderer(pdDocument);
                int pages = pdDocument.getNumberOfPages();
                if(flag > 0) {//大于0则打印具体页数
                    if(flag<pages) {
                        pages = flag;
                    }
                }

                StringBuffer imgFilePath = null;
                for (int i = 0; i < pages; i++) {
                    String imgFilePathPrefix = imgFolderPath+File.separator + imagePDFName;
                    imgFilePath = new StringBuffer();
                    imgFilePath.append(imgFilePathPrefix);
                    imgFilePath.append("-");
                    imgFilePath.append(String.valueOf(i + 1));
                    imgFilePath.append(".png");
                    File dstFile = new File(imgFilePath.toString());
                    BufferedImage image = renderer.renderImage(i, 4f);

                    //加水印
//                    Graphics graphics = image.getGraphics();
//                    graphics.setColor(Color.PINK);
//                    int heigh=image.getHeight();
//                    int width=image.getWidth();
//                    int x=random.nextInt(width);
//                    int y=random.nextInt(heigh);
//                    graphics.setFont(new Font("微软雅黑",Font.BOLD,20));
//                    graphics.drawString("艾思欧", x, y);
                    ImageIO.write(image, "png", dstFile);
                }
                System.out.println("success");
            } else {
                System.out.println("error:" + "creat" + imgFolderPath + "wrong");
            }
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
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
