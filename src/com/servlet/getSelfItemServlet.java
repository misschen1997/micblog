package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.getItemDAO;
import com.DAO.itemSortDAO;
import com.bean.Item;
import com.bean.User;

public class getSelfItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public getSelfItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();//取得当前session
		User user = (User)session.getAttribute("user");
		getItemDAO getItem = new getItemDAO();
		ArrayList<Item> items =  getItem.getItemByUser(user);
		
		//ArrayList结果按时间排序
		itemSortDAO sortDAO = new itemSortDAO();
		Collections.sort(items, sortDAO);
		session.setAttribute("goalUser", user);
		session.setAttribute("items", items);
		response.sendRedirect("showUserBlog.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
