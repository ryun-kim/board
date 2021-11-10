package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ins")
public class InsBoardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //제목, 내용, 글쓴이 정보가 날아온다.
            String json =Utils.getJson(req);
            Gson gson = new Gson();
            BoardVO param = gson.fromJson(json, BoardVO.class);
            int result = BoardDAO.insBoard(param);
            /*
        ResultVO resultVo = new ResultVO();
        resultVo.setResult(result);

        String resultJson = gson.toJson(resultVo);
                PrintWriter out = res.getWriter();
        out.println(resultJson);
             */
        String resultJson = String.format("{\"result\":%d}", result);

        PrintWriter out = res.getWriter();
        out.println(resultJson);


    }
}
