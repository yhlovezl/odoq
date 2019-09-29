//package com.xiaoniucr.test.file;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.poi.hslf.model.Slide;
//import org.apache.poi.hslf.model.TextRun;
//import org.apache.poi.hslf.usermodel.RichTextRun;
//import org.apache.poi.hslf.usermodel.SlideShow;
//import org.apache.poi.xslf.usermodel.XMLSlideShow;
//import org.apache.poi.xslf.usermodel.XSLFSlide;
//import org.apache.xmlbeans.XmlException;
//import org.openxmlformats.schemas.drawingml.x2006.main.*;
//import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
//import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;
//import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.geom.Rectangle2D;
//import java.awt.image.BufferedImage;
//import java.io.*;
//
//public class PPT2IMGUtils {
//
//    private static final String EXTENSION_PPT = ".ppt";
//    private static final String EXTENSION_PPTX = ".pptx";
//    private static Log log = LogFactory.getLog(PPT2IMGUtils.class);
//    /*默认转换的图片格式*/
//    private static String imgtype = "jpg";
//    /*保存图片路径*/
//    private static String imgpath = "D:\\pdf\\ppt2img";
//
//    private static String pptpath = "D:\\pdf\\ppt\\test.ppt";
//
//
//
//
//    /**
//     * convertPPT2Image(将ppt转换为图片)
//     *
//     * @param pptpath   ppt文件路径
//     * @param imgpath   转换后图片的输出路径
//     * @param imgformat 转换的图片格式
//     * @return Integer  返回转换的图片数量
//     */
//    public static Integer convertPPT2Image(String pptpath, String imgpath, String imgformat) {
//        try {
//            if (pptpath.endsWith(EXTENSION_PPT)) {
//                return ppt2003(pptpath, imgpath, imgformat);
//            } else if (pptpath.endsWith(EXTENSION_PPTX)) {
//                return ppt2007(pptpath, imgpath, imgformat);
//            } else {
//                log.error("PPT2Img将ppt转换为图片错误:传入的文件不是ppt文件," + pptpath);
//                return -1;
//            }
//        } catch (Exception e) {
//            log.error("PPT2Img将ppt转换为图片错误", e);
//            return -1;
//        }
//    }
//
//    @SuppressWarnings("deprecation")
//    private static Integer ppt2007(String pptpath, String imgpath, String imgformat) throws IOException, XmlException {
//        //检查传入的ppt文件地址是否正确
//        String filename = preReadCheck(pptpath);
//
//        FileInputStream orignalPPTFileInputStream = null;
//        FileOutputStream orignalPPTFileOutputStream = null;
//
//        XMLSlideShow oneSlideShow = null;
//        Integer imgnum = 0;
//        try {
//            orignalPPTFileInputStream = new FileInputStream(new File(pptpath));
//            oneSlideShow = new XMLSlideShow(orignalPPTFileInputStream);
//            /*获得PPT每页的尺寸大小（宽和高度）*/
//            Dimension onePPTPageSize = oneSlideShow.getPageSize();
//            /*获得PPT文件中的所有的PPT页面，并转换为一张张的播放片*/
//            XSLFSlide[] pptPageXSLFSlideArray = oneSlideShow.getSlides();
//            /*下面的XML配置文件定义转换后的图片内的文字字体，否则将会出现转换后 的图片内的中文为乱码 */
//            String xmlFontFormat1 = "<xml-fragment xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:p=\"http://schemas.openxmlformats.org/presentationml/2006/main\">";
//            String xmlFontFormat2 = "<a:rPr lang=\"zh-CN\" altLang=\"en-US\" dirty=\"0\" smtClean=\"0\">";
//            String xmlFontFormat3 = "<a:latin typeface=\"微软雅黑\" pitchFamily=\"34\" charset=\"-122\"/>";
//            String xmlFontFormat31 = "<a:ea typeface=\"微软雅黑\" pitchFamily=\"34\" charset=\"-122\"/>";
//            String xmlFontFormat4 = "</a:rPr>";
//            String xmlFontFormat5 = "</xml-fragment>";
//            StringBuffer xmlFontFormatStringBuffer = new StringBuffer();
//            xmlFontFormatStringBuffer.append(xmlFontFormat1);
//            xmlFontFormatStringBuffer.append(xmlFontFormat2);
//            xmlFontFormatStringBuffer.append(xmlFontFormat3);
//            xmlFontFormatStringBuffer.append(xmlFontFormat31);
//            xmlFontFormatStringBuffer.append(xmlFontFormat4);
//            xmlFontFormatStringBuffer.append(xmlFontFormat5);
//
//            imgnum = pptPageXSLFSlideArray.length;
//            for (int pptPageXSLFSlideIndex = 0; pptPageXSLFSlideIndex < pptPageXSLFSlideArray.length; pptPageXSLFSlideIndex++) {
//                /*设置字体为宋体，解决中文乱码问题*/
//                CTSlide oneCTSlide = pptPageXSLFSlideArray[pptPageXSLFSlideIndex].getXmlObject();
//                CTGroupShape oneCTGroupShape = oneCTSlide.getCSld().getSpTree();
//                CTShape[] oneCTShapeArray = oneCTGroupShape.getSpArray();
//                for (CTShape oneCTShape : oneCTShapeArray) {
//                    CTTextBody oneCTTextBody = oneCTShape.getTxBody();
//                    if (null == oneCTTextBody) {
//                        continue;
//                    }
//                    CTTextParagraph[] oneCTTextParagraph = oneCTTextBody.getPArray();
//                    CTTextFont oneCTTextFont = null;
//
//                    oneCTTextFont = CTTextFont.Factory.parse(xmlFontFormatStringBuffer.toString());
//
//                    for (CTTextParagraph textParagraph : oneCTTextParagraph) {
//                        CTRegularTextRun[] oneCTRegularTextRunArray = textParagraph.getRArray();
//                        for (CTRegularTextRun oneCTRegularTextRun : oneCTRegularTextRunArray) {
//                            CTTextCharacterProperties oneCTTextCharacterProperties = oneCTRegularTextRun.getRPr();
//                            oneCTTextCharacterProperties.setLatin(oneCTTextFont);
//                        }
//                    }
//                }
//                /*创建BufferedImage对象，图象的尺寸为原来PPT的每页的尺寸*/
//                BufferedImage oneBufferedImage = new BufferedImage(onePPTPageSize.width, onePPTPageSize.height, BufferedImage.TYPE_INT_RGB);
//                Graphics2D oneGraphics2D = oneBufferedImage.createGraphics();
//                oneGraphics2D.setPaint(Color.white);
//                oneGraphics2D.fill(new Rectangle2D.Float(0, 0, onePPTPageSize.width, onePPTPageSize.height));
//                /*将PPT文件中的每个页面中的相关内容画到转换后的图片中*/
//                pptPageXSLFSlideArray[pptPageXSLFSlideIndex].draw(oneGraphics2D);
//                /*设置图片的存放路径和图片的格式，注意生成的文件路径为绝对路径，最 终获得各个图像文件所对应的输出流对象 */
//                orignalPPTFileOutputStream = new FileOutputStream(imgpath + "/" + filename + "_" + pptPageXSLFSlideIndex + "." + imgformat);
//                ImageIO.write(oneBufferedImage, imgformat, orignalPPTFileOutputStream);
//            }
//        } finally {
//            try {
//                if (orignalPPTFileInputStream != null) {
//                    orignalPPTFileInputStream.close();
//                }
//                if (orignalPPTFileOutputStream != null) {
//                    orignalPPTFileOutputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return imgnum;
//    }
//
//    /**
//     * ppt2003(ppt2003转换为图片)
//     *
//     * @param pptpath   ppt文件路径
//     * @param imgpath   转换后图片的输出路径
//     * @param imgformat 转换的图片格式
//     * @return Integer  返回转换的图片数量
//     */
//    private static Integer ppt2003(String pptpath, String imgpath, String imgformat) throws IOException {
////检查传入的ppt文件地址是否正确
//        String filename = preReadCheck(pptpath);
//        FileInputStream orignalPPTFileInputStream = null;
//        FileOutputStream orignalPPTFileOutputStream = null;
//
//        SlideShow oneSlideShow = null;
//        Integer imgnum = 0;
//        try {
//            orignalPPTFileInputStream = new FileInputStream(pptpath);
//            oneSlideShow = new SlideShow(orignalPPTFileInputStream);
//
//            /** *获得PPT每页的尺寸大小（宽和高度）*/
//            Dimension onePPTPageSize = oneSlideShow.getPageSize();
//            /** *获得PPT文件中的所有的PPT页面（获得每一张幻灯片），并转换为一张张 的播放片 */
//            Slide[] pptPageSlideArray = oneSlideShow.getSlides();
//            /** *下面的循环的主要功能是实现对PPT文件中的每一张幻灯片进行转换和操 作。 */
//            imgnum = pptPageSlideArray.length;
//            for (int pptPageSlideIndex = 0; pptPageSlideIndex < pptPageSlideArray.length; pptPageSlideIndex++) {
//                TextRun[] textRunsArray = pptPageSlideArray[pptPageSlideIndex].getTextRuns();
//                for (int textRunsArrayIndex = 0; textRunsArrayIndex < textRunsArray.length; textRunsArrayIndex++) {
//                    RichTextRun[] pptRichTextRunsArray = textRunsArray[textRunsArrayIndex].getRichTextRuns();
//                    for (int pptRichTextRunsArrayIndex = 0; pptRichTextRunsArrayIndex < pptRichTextRunsArray.length; pptRichTextRunsArrayIndex++) {
//                        pptRichTextRunsArray[pptRichTextRunsArrayIndex].setFontIndex(1);
//                        pptRichTextRunsArray[pptRichTextRunsArrayIndex].setFontName("宋体");
//                        /**
//                        但如果 PPT 文件在 WPS 中保存过，则 pptRichTextRunsArray[ pptRichTextRunsArrayIndex].getFontSize() 的值可能为0或者26040。
//                        因此首先识别当前文本框内的字体尺寸是否为0或者大于26040，则 设置默认的字体尺寸。
//                         */
//                        int currentFontSize = pptRichTextRunsArray[pptRichTextRunsArrayIndex].getFontSize();
//                        if ((currentFontSize <= 0) || (currentFontSize >= 26040)) {
//                            pptRichTextRunsArray[pptRichTextRunsArrayIndex].setFontSize(30);
//                        }
//                    }
//                }
//
//                /** *创建BufferedImage对象，图象的尺寸为原来PPT的每页的尺寸*/
//                BufferedImage oneBufferedImage = new BufferedImage(onePPTPageSize.width, onePPTPageSize.height, BufferedImage.TYPE_INT_RGB);
//                Graphics2D oneGraphics2D = oneBufferedImage.createGraphics();
//                oneGraphics2D.setPaint(Color.white);
//                oneGraphics2D.fill(new Rectangle2D.Float(0, 0, onePPTPageSize.width, onePPTPageSize.height));
//                pptPageSlideArray[pptPageSlideIndex].draw(oneGraphics2D);
//
//                orignalPPTFileOutputStream = new FileOutputStream(imgpath + "/" + filename + "_" + pptPageSlideIndex + "." + imgformat);
//
//                ImageIO.write(oneBufferedImage, imgformat, orignalPPTFileOutputStream);
//
//            }
//        } finally {
//            try {
//                if (orignalPPTFileInputStream != null) {
//                    orignalPPTFileInputStream.close();
//                }
//                if (orignalPPTFileOutputStream != null) {
//                    orignalPPTFileOutputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return imgnum;
//    }
//
//    //文件检查,如果存在则返回文件名
//    private static String preReadCheck(String path) throws FileNotFoundException {
//        File file = new File(path);
//        if (!file.exists()) {
//            throw new FileNotFoundException("传入的文件不存在：" + path);
//        }
//        String filename = file.getName();
//        return filename.substring(0, filename.lastIndexOf("."));
//    }
//
//    public static void main(String[] args) {
//
//        try {
//            ppt2003(pptpath,imgpath,imgtype);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//}
