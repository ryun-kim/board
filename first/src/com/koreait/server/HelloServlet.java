package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "hi", value = "/hi")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        System.out.println("name :" + name );
        System.out.println("age : " + age);

        PrintWriter out = res.getWriter();
        TestVO vo = new TestVO();
        vo.setName("ryun");
        vo.setAge(10);

        String result =String.format("{ \"name\": \"%s\", \"age\": %s}", vo.getName(), vo.getAge());
        out.print(result);

        Gson gson = new Gson();
        String result2 = gson.toJson(vo);
        TestVO vo2 = gson.fromJson(result2, TestVO.class);
        System.out.println("result2:" + result2);
        out.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String data = Utils.getJson(req);

        Gson gson = new Gson();
        TestVO vo = gson.fromJson(data, TestVO.class);
        System.out.println("name :" + vo.getName());
        System.out.println("age : " + vo.getAge());
        System.out.println(data);
    }
}
