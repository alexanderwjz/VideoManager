package com.alexanderwjz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alexanderwjz.entity.TaskEntity;
import com.alexanderwjz.impl.FFmpegManager;
import com.alexanderwjz.impl.FFmpegManagerImpl;

public class PlayAndPauseVideo extends HttpServlet {
	FFmpegManager manager;
	public PlayAndPauseVideo() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("utf-8");
		String filename=request.getParameter("filename");
		if(filename.equals("null")){
			filename=null;
		}
		String task=request.getParameter("task");
		System.out.println("获取到的文件名是"+filename);
		if(filename!=null&&task.equals("play")){
		filename=new String(filename.getBytes("iso8859-1"),"utf-8");
		String videoSavePath=this.getServletContext().getRealPath("/WEB-INF/video/"+filename);
		Map map = new HashMap();
		map.put("appName", "test");
		map.put("input", videoSavePath);
		map.put("output", "rtmp://101.200.56.154/hls/mystream");
		map.put("twoPart", "2");
		manager.start(map);
		out.println("播放成功");
		}else if(filename!=null&&task.equals("stop")){
			Collection<TaskEntity> infoList = manager.queryAll();
			System.out.println(infoList);
			manager.stopAll();
			out.print("停止成功");
		}
		else if(filename==null&&task.equals("play")){
			out.print("请选择要播放的文件");
		}
	}
	public void init() throws ServletException {
		manager=new FFmpegManagerImpl();
		// Put your code here
	}

}
