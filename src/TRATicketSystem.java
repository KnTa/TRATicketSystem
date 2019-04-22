import SQL.SQLManager;

import java.sql.Statement;

public class TRATicketSystem {
    public static SQLManager sqlManager;

    public static void main(String[] args) {
        Statement statement = SQLManager.getStatment();
        //sqlManager = new SQLManager();
        System.out.println("Hello! World!");
    }
}
