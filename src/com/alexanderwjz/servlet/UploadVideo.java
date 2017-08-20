package com.alexanderwjz.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alexandewjz.util.Checkutil;
public class UploadVideo extends HttpServlet {

	public UploadVideo() {
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
		System.out.println("1234");
		response.setContentType("text/html;utf8");
		request.setCharacterEncoding("utf-8");
		//得到视频存放位置
		String videopath=this.getServletContext().getRealPath("WEB-INF/video");
		File file=new File(videopath);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(videopath + "目录不存在，需要创建");
			// 创建目录
			file.mkdir();
		}
		String message="";
		try{
			//创建文件上传工厂
			DiskFileItemFactory factory=new DiskFileItemFactory();
			//创建文件上传解析器
			ServletFileUpload upload= new ServletFileUpload(factory);
			//防止文件名乱码
			upload.setHeaderEncoding("utf-8");
			//判断是否是表单数据
			if(!ServletFileUpload.isMultipartContent(request)){
				return;
			}
			//使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				} else {// 如果fileitem中封装的是上传文件
					// 得到上传的文件名称，
					String filename = item.getName();
					System.out.println(filename);
					 /*if(filename==null || filename.trim().equals("")){
					 continue; 
					 }*/
	
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					if (Checkutil.checkfilename(filename)) {
						// 获取item中的上传文件的输入流
						InputStream in = item.getInputStream();
						// 创建一个文件输出流
						FileOutputStream out = new FileOutputStream(videopath+ "\\" + filename);
						// 创建一个缓冲区
						byte buffer[] = new byte[1024];
						// 判断输入流中的数据是否已经读完的标识
						int len = 0;
						// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
						while ((len = in.read(buffer)) > 0) {
							// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath +
							// "\\" + filename)当中
							out.write(buffer, 0, len);
						}
						// 关闭输入流
						in.close();
						// 关闭输出流
						out.close();
						// 删除处理文件上传时生成的临时文件
						item.delete();
						message = "文件上传成功！";
					}else{
						System.out.println("文件名不合法");
						message = "请上传mp4文件";
					}
					}
			}			
		}catch(Exception e){
			message="文件上传失败，请重新上传";
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/ListVideo?message="+message).forward(request, response);
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
