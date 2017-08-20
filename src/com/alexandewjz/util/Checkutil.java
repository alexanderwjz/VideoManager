package com.alexandewjz.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checkutil {
	/**
	 * 验证文件名是否是MP4格式
	 * @param filename
	 * @return
	 */
	public static boolean checkfilename(String filename){
		Pattern p=Pattern.compile(".*\\.mp4");
		Matcher m=p.matcher(filename);
		return m.matches();	
	}

}
