package com.neuedu.util;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
* @ClassName: GetImageUtil
* @Description: 获取图片的工具类
* @author neuedu_jnn
* @date 2019年8月17日 下午1:53:13
*
*/
    
public class GetImageUtil {
	
	
	
		//获取图片的方法
		public static Image getImg(String imgName) {
		    //反射
			URL resource = GetImageUtil.class.getClassLoader().getResource("com/neuedu/img/"+imgName);
			BufferedImage bufferedImage = null;
			try {
				bufferedImage = ImageIO.read(resource);		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bufferedImage;
		}

}
