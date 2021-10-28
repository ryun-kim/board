package com.koreait.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static void main(String[] args){
        StudentVO vo = new StudentVO();
        vo.setNm("륜");
        vo.setAge(50);
        vo.setAddr("반야월");

        insStudent(vo);
    }
    public static DbUtils dbUtils = DbUtils.getInstance();

    //메소드 insert 담당 메소드
    public static int insStudent(StudentVO vo){
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_student2" +
                "(nm,age,addr)" +
                "VALUES" +
                "(?, ?, ?)";
        try {
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNm());
            ps.setInt(2, vo.getAge());
            ps.setString(3, vo.getAddr());
            result = ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            dbUtils.close(con, ps);
        }

        return result;
    }

    public static List<StudentVO> selStudentList(){
        List<StudentVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT sno, nm FROM t_student2";

        try{
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) { //줄을 가르키게 한다. 다음 record에 값이 있으면 true, 없으면 false가 뜬다.
                StudentVO vo = new StudentVO();
                int sno = rs.getInt("sno");
                String nm = rs.getString("nm");
                vo.setSno(sno);
                vo.setNm(nm);
                list.add(vo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
                dbUtils.close(con, ps, rs);
        }
            return list;
    }

    public static StudentVO selStudent(StudentVO vo){
        StudentVO result = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELCT * FROM t_student2 WHERE sno = ?";

        try{
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,vo.getSno());
            rs= ps.executeQuery();

            if(rs.next()){//한줄받거나 0줄받을때에 따라서 값을 받기 위해 유연하게 if문 사용
                result = new StudentVO();
                result.setSno(rs.getInt("sno"));
                result.setNm(rs.getString("nm"));
                result.setAge(rs.getInt("age"));
                result.setAddr(rs.getString("addr"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            dbUtils.close(con, ps, rs);
        }
        return result;
    }

    public static int updStudent(StudentVO vo) {

        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE t_student2 SET nm=?, age=? , addr=? WHERE sno=?";
        try{
            con= dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNm());
            ps.setInt(2, vo.getAge());
            ps.setString(3, vo.getAddr());
            ps.setInt(4, vo.getSno());
            return ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            dbUtils.close(con, ps);
        }
        return 0;
    }

    public static int delStudent(StudentVO vo){

        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM t_student2 WHERE sno=?";
        try {
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, vo.getSno());
            return ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            dbUtils.close(con, ps);
        }
        return 0;
    }
}
