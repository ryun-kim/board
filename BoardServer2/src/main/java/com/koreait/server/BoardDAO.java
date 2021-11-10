package com.koreait.server;



import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.koreait.server.DbUtils.close;

public class BoardDAO {
    public static int insBoard(BoardVO param){


        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_board (title, writer, ctnt) VALUES (?, ?, ?)";
        //int result = 0;
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getWriter());
            ps.setString(3, param.getCtnt());
            //result = 1;
            //int result = ps.executeUpdate();
            return ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DbUtils.close(con, ps);
        }
        // return result;
        return 0;
    }
    public static BoardVO selBoardOne(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM t_board WHERE iboard = ?";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();

            if(rs.next()){
                BoardVO vo = new BoardVO();
                vo.setTitle(rs.getString("title"));
                vo.setWriter(rs.getString("writer"));
                vo.setIboard(rs.getInt("iboard"));
                vo.setRdt(rs.getString("rdt"));
                vo.setCtnt(rs.getString("ctnt"));
                return vo;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DbUtils.close(con,ps,rs);
        }
        return null;
    }


    public static List<BoardVO> selBoardList(){
        List<BoardVO> list = new ArrayList<>();

        Connection con = null; //터널
        PreparedStatement ps = null; //도로
        ResultSet rs = null; //select 때 쓰는거
        String sql = "SELECT iboard, title, writer, rdt FROM t_board ORDER BY iboard DESC";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                /*
                BoardVO vo = new BoardVO();
                list.add(vo);
                int iboard = rs.getInt("iboard");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                String rdt = rs.getString("rdt");
                vo.setIboard(iboard);
                vo.setTitle(title);
                vo.setWriter(writer);
                vo.setRdt(rdt);
                list.add(vo);
                 */
                BoardVO vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));
                list.add(vo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DbUtils.close(con, ps, rs);
        }

        return list;
    }

    public static BoardVO seldetail(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BoardVO vo = null;
        String dd = "SELECT * FROM t_board WHERE iboard =?";
        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(dd);
            rs =ps.executeQuery();
            ps.setInt(1, vo.getIboard());
            vo = new BoardVO();
            if(rs.next()){
                vo.setCtnt(rs.getString("ctnt"));
                vo.setIboard(rs.getInt("iboard"));
                vo.setWriter(rs.getString("writer"));
                vo.setTitle(rs.getString("title"));
                vo.setRdt(rs.getString("rdt"));
            }


        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }
        return vo ;
    }

    public static List<BoardVO> selboard(){
        List<BoardVO> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sq = "SELECT iboard, title, writer FROM t_board";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sq);
            rs = ps.executeQuery();
            if(rs.next()){
                BoardVO vo = new BoardVO();
                vo.setTitle(rs.getString("title"));
                vo.setWriter(rs.getString("write"));
                vo.setIboard(rs.getInt("iboard"));
                vo.setRdt(rs.getString("rdt"));
                vo.setCtnt(rs.getString("ctnt"));

            }
        }catch(Exception e ){
            e.printStackTrace();
        }finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }

}
