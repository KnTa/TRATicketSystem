package SQL;

import java.sql.*;

public class SQLManager {
    private static Connection c;
    static {
        c =null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:./database/ticket_database.sqlite");
            c.setAutoCommit(false);
        }catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public static Statement getStatement(){
        try{
            return c.createStatement();
        }
        catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }

    public static void commit(){
        try{
            c.commit();
        }catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}