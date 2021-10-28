package com.koreait.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbUtils {
        public static DbUtils dbUtils;

        private DbUtils() {}

        public static DbUtils getInstance(){
            if(dbUtils ==null) {
                dbUtils = new DbUtils();
            }
            return dbUtils;
        }

        public Connection getCon() {
            final String URL = "jdbc:mysql://localhost:3308/test";
            final String USER_NAME = "root";
            final String USER_PW = "koreait";
            Connection con = null;
            try {
                con = DriverManager.getConnection(URL, USER_NAME, USER_PW);
                System.out.println("DB 연결 성공!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("DB연결 실패!");
            }
            //exception이 발생한다. 이때 try catch문으로도 해결할 수 있다. throws를 이용해서 던져줄수있다.

            return con;
        }

        public void close(Connection con, PreparedStatement ps){
            //insert delete update때 사용
            close(con, ps, null);
        }

        public void close(Connection con, PreparedStatement ps, ResultSet rs) {//connection 메소드는 메모리를 많이 먹기때문에 사용후 바로 반납해야한다.
            //select때만 사용
            if (rs != null) {
                try {rs.close();} //예외처리를 자동으로 해주지 않기 때문에 에러가 뜬다 throw로 해결
                catch (Exception e) {e.printStackTrace();}
            }
            if (ps != null) {
                try {ps.close();}
                catch (Exception e) {e.printStackTrace();}
            }

            if (con != null) {
                try {con.close();}
                catch (Exception e) {e.printStackTrace();}
            }
        }
}
