package com.koreait.board;

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
        String str = req.getParameter("iboard");
        int iboard = Integer.parseInt(str);
        BoardVO vo = new BoardVO();
        vo.setIboard(iboard);
        int result = BoardDAO.delBoard(vo);

        switch(result){
            case 1:
                res.sendRedirect("/list");
                break;
            default:
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
