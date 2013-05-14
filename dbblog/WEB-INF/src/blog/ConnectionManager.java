package blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * シンプルなコネクション管理クラス
 * 
 * @author Shinji Miyamoto
 */
public class ConnectionManager {

    /**
     * JDBCドライバーのクラス名
     */
    final static String DRIVER = "com.mysql.jdbc.Driver";

    /**
     * データベースのURL
     */
    final static String URL = "jdbc:mysql://localhost/mydb";

    
    /**
     * データベースのユーザ
     */
    final static String USER = "root";

    /**
     * データベースのパスワード
     */
    final static String PASS = "hoge";

    /**
     * Conncectionを取得します。
     */
    public static Connection getConnection()
            throws SQLException {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException(
                    "fail to class load : "
                            + e.getMessage());
        }

        Connection con = DriverManager.getConnection(URL,
                USER, PASS);

        return con;
    }

    
    
    
    /**
     * 接続確認テスト
     */
    public static void main(String[] args)
            throws SQLException {

        Connection con = getConnection();
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("select * from account");
        while(rs.next()){
        	String s = "NAME=" + rs.getString("NAME")
        		+ ",MONEY=" + rs.getString("MONEY")
        		;
        	System.out.println(s);
        }
        smt.close();
        con.close();
        System.out.println("END");
    }
}