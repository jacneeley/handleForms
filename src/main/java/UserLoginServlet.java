

/* package net.codejava.servlet; */
 
import java.io.IOException;
import java.io.PrintWriter;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		//read forms
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username" + username);
		System.out.println("password" + password);
		
		//process form
		String languages[] = request.getParameterValues("language");
		String language = "";
		if (languages !=null ) {
			System.out.println("Languages are: ");
			for (String lang : languages) {
				System.out.println("\t" + lang);
				language += lang;
			}
		}
		
		String gender = request.getParameter("gender");
		System.out.println("Gender: " + gender);
		
		String bio = request.getParameter("bio");
		System.out.println("bio: " + bio);
		
		String occupation = request.getParameter("occupation");
		System.out.println("Occupation: " + occupation);
		
		//todo - add profile picture upload w/ upload field.
		
		//get response writer
		PrintWriter writer = response.getWriter();
		
		//html
		String htmlResponse = "<html><body>";
		htmlResponse += "<h1>User Info:</h1>";
		htmlResponse += "<h2>"+ username + "</h2>";
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
