package com.alexanderwjz.service;

import java.util.Map;
/**
 * 命令组装器接口
 * @author alexanderwjz
 * @since jdk1.7
 * @version 2017年7月29日
 */
public interface CommandAssembly {
	/**
	 * 将参数转为ffmpeg命令
	 * @param paramMap
	 * @return
	 */
	public String assembly(Map<String, String> paramMap);
}