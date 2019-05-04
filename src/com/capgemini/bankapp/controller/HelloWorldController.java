package com.capgemini.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "hellowworld", loadOnStartup = 1, urlPatterns = { "/hello", "/hellowworld", "/login" })
public class HelloWorldController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HelloWorldController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		System.out.println("helloworld");// this prints in server console

		PrintWriter out = response.getWriter();
		out.print("helloworld");
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		RequestDispatcher dispatcher = null;
//		PrintWriter out = response.getWriter();
		if (username.equals("root") && password.equals("root123")) {
			dispatcher = request.getRequestDispatcher("success.html");

		} else {
			dispatcher = request.getRequestDispatcher("error.html");
		}
		dispatcher.forward(request, response);
	}

}
