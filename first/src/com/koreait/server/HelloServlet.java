package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "hello", value = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("qwefefwef");
        String addr = req.getParameter("addr");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("gender");

        System.out.println("addr :" + addr);
        System.out.println("phone : " + phone);
        System.out.println("gender : " + gender);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("POST POST POST");
        String data = Utils.getJson(req);
        Gson gson = new Gson();
        TestVO vo = gson.fromJson(data, TestVO.class);
        System.out.println(vo.getName());
        System.out.println(vo.getAge());
    }
}
