package com.itesm.financial;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GenerateReport implements IGenerateReport {
    private final String START_SEPARATOR;
    private final String END_SEPARATOR;
    private final String OPEN_CLOSURE;
    private final String CLOSE_CLOSURE;
    private final String OPEN_ROW;
    private final String CLOSE_ROW;
    private final String START_TITLE;
    private final String END_TITLE;
    private final String START_SEPARATOR_HEADER;
    private final String END_SEPARATOR_HEADER;

    public GenerateReport(String START_SEPARATOR, String END_SEPARATOR, String OPEN_CLOSURE, String CLOSE_CLOSURE, String OPEN_ROW, String CLOSE_ROW, String START_TITLE, String END_TITLE, String START_SEPARATOR_HEADER, String END_SEPARATOR_HEADER) {
        this.START_SEPARATOR = START_SEPARATOR;
        this.END_SEPARATOR = END_SEPARATOR;
        this.OPEN_CLOSURE = OPEN_CLOSURE;
        this.CLOSE_CLOSURE = CLOSE_CLOSURE;
        this.OPEN_ROW = OPEN_ROW;
        this.CLOSE_ROW = CLOSE_ROW;
        this.START_TITLE = START_TITLE;
        this.END_TITLE = END_TITLE;
        this.START_SEPARATOR_HEADER = START_SEPARATOR_HEADER;
        this.END_SEPARATOR_HEADER = END_SEPARATOR_HEADER;
    }
    
    @Override
    public String formatAmount(double amount) {
        if(amount < 0) {
            return "<span style='color:red'>" + amount + "</span>";
        }
        return Double.toString(amount);
    }

    @Override
    public void generateReport(List<Ride> result) throws IOException {
        String htmlReport = createContent(result);
        System.out.println(htmlReport);
        FileManager.createFile(htmlReport);
    }

    @Override
    public String createContent(List<Ride> rides) {
        StringBuilder builder = new StringBuilder();
        builder.append(createHeaders("Taxi Report"));
        builder.append(createTableHeaders());
        rides.forEach( ride -> {
            builder.append(addRide(ride));
        });
        builder.append(closeTableHeaders());

        return builder.toString();
    }
    

    private String createHeaders(String title){
        return START_TITLE + title + END_TITLE;
    }

    private String generateBlock(List<String> values, String openClosure, String closeClosure, String separatorStart, String separatorEnd, String startRow, String endRow) {

        StringBuilder builder = new StringBuilder();
        builder.append(openClosure);
        builder.append(startRow);
        values.forEach( header -> {
            builder.append(separatorStart + header + separatorEnd);
        });
        builder.append(endRow);
        return builder.toString();
    }

    private String createTableHeaders() {
        List<String> headers = Arrays.asList(
            "Pick Up Time", 
            "Drop Off Time", 
            "Passenger Count", 
            "Trip Distance", 
            "Tolls Amount"
        );

        return generateBlock(headers, OPEN_CLOSURE, CLOSE_CLOSURE,  START_SEPARATOR_HEADER, END_SEPARATOR_HEADER, OPEN_ROW, CLOSE_ROW);
    }

    private String closeTableHeaders() {
        return CLOSE_CLOSURE;
    }

    private String addRide(Ride ride) {        
        List<String> values = Arrays.asList(
            Long.toString(ride.getTaxiId()),
            ride.getPickUpTime().toString(),
            ride.getDropOffTime().toString(),
            Integer.toString(ride.getPassengerCount()),
            Double.toString(ride.getTripDistance()),
            formatAmount(ride.getTollsAmount())
        );

        return generateBlock(values, "", CLOSE_CLOSURE,  START_SEPARATOR, END_SEPARATOR, OPEN_ROW, CLOSE_ROW);
    }

}
