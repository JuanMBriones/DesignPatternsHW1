package com.itesm.financial;

import java.util.List;

public class Client {
    private static final String CSV_FILENAME = "taxi-data.csv";

    public static void main(String[] args) throws Exception {
        System.out.println("Financial Report Generation");        

        List<Ride> result = FileManager.readFile(CSV_FILENAME);
        WebReport webReport = new WebReport();
        webReport.generateReport(result);

        System.out.println("-----------------------------------------------------");
        PrintReport printReport = new PrintReport();
        printReport.generateReport(result);
    }
}
