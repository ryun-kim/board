package com.koreait.board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mod")
public class BoardModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String str = req.getParameter("iboard");
        int ib = Integer.parseInt(str);
        BoardVO vo = new BoardVO();
        vo.setIboard(ib);
        BoardVO vv = BoardDAO.selBoardDetail(vo);
        req.setAttribute("data",vv);

        String path = "/WEB-INF/jsp/mod.jsp";
        req.getRequestDispatcher(path).forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
