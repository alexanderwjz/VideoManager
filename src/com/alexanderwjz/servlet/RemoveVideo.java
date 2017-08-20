package com.alexanderwjz.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveVideo extends HttpServlet {

	
	public RemoveVideo() {
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
		request.setCharacterEncoding("utf-8");
		String filename=request.getParameter("filename");
		filename=new String(filename.getBytes("iso8859-1"),"utf-8");
		String deletemessage;
		String savefilepath=this.getServletContext().getRealPath("/WEB-INF/video/"+filename);
		File file=new File(savefilepath);
		if(file.exists()){
			file.delete();
			deletemessage="文件删除成功";
			}else{
				deletemessage="文件删除失败，文件不存在";	
			}
		request.setAttribute("deletemessage",deletemessage);
		request.getRequestDispatcher("/ListVideo?deletemessage="+deletemessage).forward(request, response);;
		}			
	
	public void init() throws ServletException {
		// Put your code here
	}

}
