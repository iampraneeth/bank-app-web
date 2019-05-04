package com.capgemini.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.bankapp.exception.AccountIdNotMatchException;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.service.impl.BankAccountServiceImpl;

@WebServlet(urlPatterns = { "*.do" }, loadOnStartup = 1)
public class BankAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankAccountService bankService;

	public BankAccountController() {
		bankService = new BankAccountServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String path = request.getServletPath();
		if (path.equals("/displayAllAccountDetails.do")) {
			List<BankAccount> bankAccounts = bankService.findAllBankAccount();
			request.setAttribute("accounts", bankAccounts);
			RequestDispatcher dispatcher = request.getRequestDispatcher("dad.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String path = request.getServletPath();
		System.out.println(path);
		if (path.equals("/addNewBankAccount.do")) {
			String accountHolderName = request.getParameter("customer_name");
			String accountType = request.getParameter("account_type");
			double accountBalance = Double.parseDouble(request.getParameter("account_balance"));
			BankAccount account = new BankAccount(accountHolderName, accountType, accountBalance);
			if (bankService.addNewBankAccount(account)) {
				out.println("<h2>bank account is created</h2>");
				out.close();
			}

		}
		if (path.equals("/withdrawals.do")) {
			long accountNumber = Long.parseLong(request.getParameter("number"));
			Double amount = Double.parseDouble(request.getParameter("amount"));
			try {
				double result = bankService.withdraw(accountNumber, amount);
				out.println("<h2>withdraw is successfull</h2>");
				out.print("balance remaining is " + result);
			} catch (LowBalanceException e) {
				e.printStackTrace();
			} catch (AccountIdNotMatchException e) {
				out.println("Account id not found");
				e.printStackTrace();
			}
		}
		if (path.equals("/deposits.do")) {
			long accountNumber = Long.parseLong(request.getParameter("number"));
			Double amount = Double.parseDouble(request.getParameter("amount"));
			try {
				double result = bankService.deposit(accountNumber, amount);
				out.println("<h2>deposit is successfull</h2>");
				out.println("balance remaining is " + result);
			} catch (AccountIdNotMatchException e) {
				out.println("Account id not found");
				e.printStackTrace();
			}

		}
		if (path.equals("/funds.do")) {
			long accountNumber = Long.parseLong(request.getParameter("number1"));
			long accountNumber2 = Long.parseLong(request.getParameter("number2"));
			Double amount = Double.parseDouble(request.getParameter("amount"));
			try {
				double result = bankService.fundTransfer(accountNumber, accountNumber2, amount);
				out.println("<h2>transaction is successfull</h2>");
				out.println("balance remaining is " + result);
			} catch (LowBalanceException e) {
				e.printStackTrace();
			} catch (AccountIdNotMatchException e) {
				out.println("Account id not found");
				e.printStackTrace();
			}
		}
		if (path.equals("/checkbalance.do")) {
			long accountNumber = Long.parseLong(request.getParameter("number"));
			try {
				double result = bankService.checkBalance(accountNumber);

				out.println("balance remaining is " + result);
			} catch (AccountIdNotMatchException e) {
				out.println("Account id not found");
				e.printStackTrace();
			}
		}
		if (path.equals("/searchAccount.do")) {
			long accountNumber = Long.parseLong(request.getParameter("number"));
			try {
				BankAccount bankAccount = bankService.searchBankAccount(accountNumber);
				request.setAttribute("searches", bankAccount);
				RequestDispatcher dispatcher = request.getRequestDispatcher("searchDisplay.jsp");
				dispatcher.forward(request, response);
			} catch (AccountIdNotMatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (path.equals("/updateAction.do")) {
			long accountNumber = Long.parseLong(request.getParameter("number"));
			try {
				System.out.println("uopdate page");
				BankAccount account = bankService.searchBankAccount(accountNumber);
				request.setAttribute("accounts", account);
				RequestDispatcher dispatcher = request.getRequestDispatcher("updateDetails.jsp");
				dispatcher.forward(request, response);

			} catch (AccountIdNotMatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (path.equals("/deleteaccount.do")) {
			long accountNumber = Long.parseLong(request.getParameter("number"));
			try {
				boolean result = bankService.deleteBankAccount(accountNumber);

				out.println("your account is deleted successfully" + result);
			} catch (AccountIdNotMatchException e) {
				out.println("Account id not found");
				e.printStackTrace();
			}
		}
		if (path.equals("/updateDetails.do")) {
			long accountNumber = Long.parseLong(request.getParameter("customer_id"));
			String accountHolderName = request.getParameter("customer_name");
			String accountType = request.getParameter("account_type");
			double accountBalance = Double.parseDouble(request.getParameter("account_balance"));
			BankAccount account = new BankAccount(accountNumber, accountHolderName, accountType, accountBalance);
			if(bankService.updateAccount(account)) {
				response.sendRedirect("displayAllAccountDetails.do");
			}
		
		}
	}

}
