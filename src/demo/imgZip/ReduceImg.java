package demo.imgZip;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ReduceImg {


    /**
     * 压缩图片 输出以png格式
     *
     * @param baseImgString
     * @param widthdist
     * @param heightdist
     */
    public void reduceImgOne(String baseImgString, int widthdist, int heightdist) {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(baseImgString);
            // 开始读取文件并压缩
            BufferedImage image = ImageIO.read(inputStream);
            // 构造一个类型为预定义图像类型之一的BufferedImage
            BufferedImage bufferedImage = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);
            //绘制图像
            bufferedImage.getGraphics().drawImage(image.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);

            // BufferedImage 转Base64
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            String base64String = Base64.getEncoder().encodeToString(baos.toByteArray());
            System.out.println(base64String);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩图片 输出以jpeg格式
     *
     * @param baseImgString
     * @param imgdist
     * @param widthdist
     * @param heightdist
     */
    public void reduceImgTwo(String baseImgString, String imgdist, int widthdist, int heightdist) {

        byte[] buffer = null;

        try {
            InputStream inputStream = new ByteArrayInputStream(baseImgString.getBytes(StandardCharsets.UTF_8));
            // InputStream inputStream = BaseToInputSteam(baseImgString);
            //InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(baseImgString);
            // 开始读取文件并进行压缩
            BufferedImage image = ImageIO.read(inputStream);
            // 构造一个类型为预定义图像类型之一的BufferedImage
            BufferedImage bufferedImage = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);
            // 绘制图像,getScaledInstance 表示创建此图像的缩放版本，返回一个新的缩放版本Image，按指定的width，height绘制图像
            // Image.SCALE_SMOOTH 选择图像平滑度比缩放速度具有更高优先级的图像缩放算法
            bufferedImage.getGraphics().drawImage(image.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);

            // 创建文件输出流
            FileOutputStream out = new FileOutputStream(imgdist);
            // 将图片以JPEG压缩，保存到out中
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(bufferedImage);
            // 关闭文件输出流
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按比例缩放
     *
     * @param baseImgString
     * @param imgdist
     * @param widthdist
     * @param heightdist
     */
    public void reduceImgTwo(String baseImgString, String imgdist, int widthdist, int heightdist, Float rate) {


        File srcFile = new File(baseImgString);
        // 检查图片文件是否存在
        if (!srcFile.exists()) {
            System.out.println("文件不存在");
        }
        // 如果比例为空则说明是按比例压缩
        if (rate != null && rate > 0) {
            // 获取源图片的宽高存入数组中
            int[] results = getImgWidthHeight(srcFile);

            if (results == null || results[0] == 0 || results[1] == 0) {
                return;
            } else {
                // 按比例缩放或扩大图片大小，将浮点型转为整型
                widthdist = (int) (results[0] * rate);
                heightdist = (int) (results[1] * rate);
                try {
                    // InputStream inputStream = BaseToInputSteam(baseImgString);
                    InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(baseImgString);
                    // 开始读取文件并进行压缩
                    BufferedImage image = ImageIO.read(inputStream);
                    // 构造一个类型为预定义图像类型之一的BufferedImage
                    BufferedImage bufferedImage = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);
                    // 绘制图像,getScaledInstance 表示创建此图像的缩放版本，返回一个新的缩放版本Image，按指定的width，height绘制图像
                    // Image.SCALE_SMOOTH 选择图像平滑度比缩放速度具有更高优先级的图像缩放算法
                    bufferedImage.getGraphics().drawImage(image.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);
                    // 创建文件输出流
                    FileOutputStream out = new FileOutputStream(imgdist);
                    // 将图片以JPEG压缩，保存到out中
                    //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                   // encoder.encode(bufferedImage);
                    // 关闭文件输出流
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 获取原图片的宽高
     *
     * @param srcFile
     * @return
     */
    private int[] getImgWidthHeight(File srcFile) {
        int[] imgWidthHeight = new int[2];
        try {
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(srcFile));
            imgWidthHeight[0] = sourceImg.getWidth();
            imgWidthHeight[1] = sourceImg.getHeight();
            return imgWidthHeight;
        } catch (IOException e) {
            System.out.println("获取图片宽高异常");
            e.printStackTrace();
        }
        return imgWidthHeight;
    }


}
