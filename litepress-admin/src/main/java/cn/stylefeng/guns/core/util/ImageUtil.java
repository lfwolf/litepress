package cn.stylefeng.guns.core.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	
	/**
     * 判断是否为纯色
     * @param imgPath 图片源
     * @param percent 纯色百分比，即大于此百分比为同一种颜色则判定为纯色,范围[0-1]
     * @return
     * @throws IOException
     */
    public static boolean isSimpleColorImg(String imgPath,float percent) throws IOException{
        BufferedImage src=ImageIO.read(new File(imgPath));
        int height=src.getHeight();
        int width=src.getWidth();
        int count=0,pixTemp=0,pixel=0;
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                pixel=src.getRGB(i, j);
                if(pixel==pixTemp) //如果上一个像素点和这个像素点颜色一样的话，就判定为同一种颜色
                    count++;
                else
                    count=0;
                if((float)count/(height*width)>=percent) //如果连续相同的像素点大于设定的百分比的话，就判定为是纯色的图片 
                    return true;
                pixTemp=pixel;
            }
        }
        return false;
    }

}
