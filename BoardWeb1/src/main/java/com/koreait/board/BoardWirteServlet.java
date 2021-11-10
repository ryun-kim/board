package com.koreait.board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/write")
public class BoardWirteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String jsp = "/WEB-INF/write.jsp";
        RequestDispatcher rd =  req.getRequestDispatcher(jsp);
        rd.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");
        String writer = req.getParameter("writer");

        BoardVO vo = new BoardVO();
        vo.setTitle(title);
        vo.setCtnt(ctnt);
        vo.setWriter(writer);

        int result = BoardDAO.insBoard(vo);
        switch(result){
            case 1:
                res.sendRedirect("/list");
                break;
            default:
                res.sendRedirect("/write");
                break;
        }

    }
}
