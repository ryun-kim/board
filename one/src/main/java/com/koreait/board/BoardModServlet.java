package com.koreait.board;

import javax.servlet.RequestDispatcher;
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
        int iboard = Integer.parseInt(str);
        BoardVO param = new BoardVO();
        param.setIboard(iboard);
        BoardVO vo = BoardDAO.selBoard(param);
        req.setAttribute("data2",vo);

        String path = "/WEB-INF/jsp/mod.jsp";
        req.getRequestDispatcher(path).forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //TODO - 수정처리, 수정 완료되면 디테일 화면으로 이동
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");
        String writer = req.getParameter("writer");
        String str = req.getParameter("iboard");
        int iboard = Integer.parseInt(str);

        BoardVO vo = new BoardVO();
        vo.setIboard(iboard);
        vo.setTitle(title);
        vo.setCtnt(ctnt);
        vo.setWriter(writer);

        int result = BoardDAO.updBoard(vo);
        switch (result){
            case 1:
                res.sendRedirect("/detail?iboard="+ str);
                break;
        }
    }
}
