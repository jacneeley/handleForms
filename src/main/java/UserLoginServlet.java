

/* package net.codejava.servlet; */
 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/login")
@MultipartConfig(
	fileSizeThreshold= 1024 * 1024 * 2, //2mb
	maxFileSize = 1024 * 1024 * 10,    //10mb
	maxRequestSize = 1024 * 1024 * 50 //50mb
)

public class UserLoginServlet extends HttpServlet {
	
	private static String SAVE_DIR = "uploadedFiles";
	
	private String extractFileName(Part part) {
		String content = part.getHeader("content-disposition");
		System.out.println(content);
		String[] strItems = content.split(";");
		for (String s : strItems) {
			if(s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() -1 );
			}
		}
		return "";
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		//read forms
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		
		//process form
		String languages[] = request.getParameterValues("language");
		String language = "";
		if (languages !=null ) {
			System.out.println("Languages are: ");
			for (String lang : languages) {
				System.out.println("\t" + lang);
				language += "\t" + lang;
			}
		}
		
		String gender = request.getParameter("gender");
		System.out.println("Gender: " + gender);
		
		String bio = request.getParameter("bio");
		System.out.println("bio: " + bio);
		
		String occupation = request.getParameter("occupation");
		System.out.println("Occupation: " + occupation);
		
		//add profile picture upload w/ upload field.
		File f = new File("eclipse-workspace/QuickHtmlForm/src/main/webapp");
		String appPath = f.getAbsolutePath();
		String savePath = appPath + File.separator + SAVE_DIR;
		
		File fileSaveDir = new File(savePath);
		if(!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		
		Part filePart = request.getPart("file");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		
		try {
			System.out.println("Upload -> " + fileSaveDir);
			filePart.write(fileSaveDir + File.separator + extractFileName(filePart));
			System.out.println("Upload Complete");		
		} catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println(fileName);
		
		//get response writer
		PrintWriter writer = response.getWriter();
		
		//html
		String htmlResponse = "<html><link rel=\"stylesheet\" type=\"text/css\" href=\"./style.css\"><body>";
		htmlResponse += "<h1>User Info:</h1>";
		htmlResponse += "<img src='./uploadedFiles" + fileName + "' />";
		htmlResponse += "<h2>username: "+ username + "</h2>";
		htmlResponse += "<h2>password: " + password + "</h2>";
		htmlResponse += "<h2>language: " + language + "</h2>";
		htmlResponse += "<h2>gender: " + gender + "</h2>";
		htmlResponse += "<h2>occupation: " + occupation + "</h2>";
		htmlResponse += "<h2>bio: " + bio + "</h2>";
		htmlResponse += "</body></html>";
		
		//return response
		writer.println(htmlResponse);
	}

}
