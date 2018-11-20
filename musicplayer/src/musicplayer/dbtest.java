package musicplayer;
import java.sql.*;

public class dbtest {
   public static void main(String[] args){
      try{
          String dbFile = "jdbc:ucanaccess://C:\\Users\\정현정\\eclipse-workspace\\musicplayer\\src\\database\\signdata.mdb";
          Connection conn = DriverManager.getConnection(dbFile);
          Statement st = conn.createStatement();
          PreparedStatement pstmt;
          ResultSet rs = st.executeQuery("SELECT Name from 테이블1");


          while(rs.next()){
             String ID= rs.getString("Name");
             System.out.println(ID);
          }

          rs.close();
          conn.close();
    }
   catch (SQLException e) {System.out.println(e);}
   } //main End
}//Class End
