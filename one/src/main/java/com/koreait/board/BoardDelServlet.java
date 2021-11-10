package com.koreait.board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del")
public class BoardDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String strIboard = req.getParameter("iboard");
        System.out.println("strIboard:" + strIboard);
        int ib = Integer.parseInt(strIboard);
        BoardVO vo = new BoardVO();
        vo.setIboard(ib);
        int result=BoardDAO.delBoard(vo);

        switch(result){
            case 1:
                res.sendRedirect("/list");
                break;
            case 2:
                break;
        }


    }
}
