package main;

import dbms.DBMS;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class TestDBMS {

    public static void main(String[] args) throws SQLException, IOException {

        Scanner s = new Scanner(System.in).useDelimiter("(\\;)");

        String query;

        System.out.print("-> ");
        query = s.next();
        query = " " + query;
        String statementType[] = query.split("(\\s+)");

        while (!query.matches("(\\s*)exit(\\s*)")) {
            if (statementType[1].equalsIgnoreCase("select")) {
                Object[][] obj = null;
                try {
                    obj = DBMS.getInstance().executeRetrievalQuery(query);
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
                if (obj != null) {
                    showTable(obj);
                }
                System.out.println("");
            } else if (statementType[1].equalsIgnoreCase("create") || statementType[1].equalsIgnoreCase("drop")) {
                try {
                    DBMS.getInstance().executeStructureQuery(query);
                    System.out.println("QUERY ACCEPTED");
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
                System.out.println("");
            } else if (statementType[1].equalsIgnoreCase("delete") || statementType[1].equalsIgnoreCase("insert")) {
                try {
                    int x = DBMS.getInstance().executeUpdateQuery(query);
                    System.out.println("TABLE UPDATED : " + x + " row(s) affected");
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
                System.out.println("");
            } else {
                System.out.println("");
            }

            System.out.print("-> ");
            query = s.next();
            query = " " + query;
            statementType = query.split("(\\s+)");

        }

    }

    public static void showTable(Object[][] table) {

        System.out.println("");
        System.out.print("+");
        for (int i = 0; i < table[0].length; i++) {
            for (int j = 0; j < 23; j++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println("");
        for (int i = 0; i < table[0].length; i++) {
            System.out.print(String.format("|%-20s   ", table[0][i]));
        }
        System.out.print("|\n");
        System.out.print("+");
        for (int i = 0; i < table[0].length; i++) {
            for (int j = 0; j < 23; j++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println("");

        for (int i = 1; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(String.format("|  %-20s ", table[i][j]));
            }
            System.out.print("|\n");
        }
        System.out.print("+");
        for (int i = 0; i < table[0].length; i++) {
            for (int j = 0; j < 23; j++) {
                System.out.print("-");
            }
            System.out.print("+");
        }

        System.out.println("\n");
        System.out.println(table.length - 1 + " row(s)");
    }
}
