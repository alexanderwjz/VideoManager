package com.alexanderwjz.service;

import java.util.Map;

/**
 * 命令组装器实现
 * 
 * @author alexanderwjz
 * @since jdk1.7
 * @version 2017年7月29日
 */
public class CommandAssemblyImpl implements CommandAssembly {
	/**
	 * 
	 * @param map
	 *            -要组装的map
	 * @param id
	 *            -返回参数：id
	 * @param id
	 *            -返回参数：组装好的命令
	 * @return
	 */
	public String assembly(Map<String, String> paramMap) {
		try {
			if (paramMap.containsKey("ffmpegPath")) {
				String ffmpegPath = (String) paramMap.get("ffmpegPath");
				// -i：输入流地址或者文件绝对地址
				//-threads 2 -re -fflags +genpts -stream_loop -1循环推流
				StringBuilder comm = new StringBuilder(ffmpegPath
						+ " -threads 2 -re -fflags +genpts -stream_loop -1"
						+ " -i ");
				// 是否有必输项：输入地址，输出地址，应用名，twoPart：0-推一个元码流；1-推一个自定义推流；2-推两个流（一个是自定义，一个是元码）
				if (paramMap.containsKey("input")
						&& paramMap.containsKey("output")
						&& paramMap.containsKey("appName")
						&& paramMap.containsKey("twoPart")) {
					String input = (String) paramMap.get("input");
					String output = (String) paramMap.get("output");
					String appName = (String) paramMap.get("appName");
					String twoPart = (String) paramMap.get("twoPart");
					String codec = (String) paramMap.get("codec");
					comm.append(input);
					if ("2".equals(twoPart)) {
						comm.append(" -vcodec copy -acodec copy -f flv ")
								.append(output);
					}
					return comm.toString();
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}
