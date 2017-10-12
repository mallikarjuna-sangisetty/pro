package com.project.traffic.constants;


public class QueryConstatns {

    public static final String PROVIDERS_VS_SEATS_PIECHART = "SELECT distinct(t.provider),count(t.seats) count FROM transportaiondetails t group by provider"; 
    public static final String PROVIDERS_VS_BOOKED = "select distinct(provider),sum(total) booked  from transportaiondetails t,bookticket b where t.transport_id = b.transport_id group by provider";
    public static final String VALUED_CUSTOMERS = "SELECT distinct(u.userName),sum(total) tickets FROM user u,bookticket b where u.user_id = b.user_id group by username order by tickets desc";
    public static final String MOST_TRAVELLED_DESTINATIONS = "select distinct(destination),sum(total) counter from transportaiondetails t,bookticket b where t.transport_id = b.transport_id group by destination order by counter desc";
    public static final String MOST_TRAFFIC_SOURCES = "select distinct(source),sum(total) counter from transportaiondetails t,bookticket b where t.transport_id = b.transport_id group by source order by counter desc";
    public static String YEAR_WISE_TRAVELLED = "SELECT YEAR(STR_TO_DATE(bookedDate, '%m/%d/%Y')) year,sum(total) travelled FROM trafficanalysis.bookticket "
                                                + "where YEAR(STR_TO_DATE(bookedDate, '%m/%d/%Y')) in(?) "
                                                + "group by YEAR(STR_TO_DATE(bookedDate, '%m/%d/%Y')) ;";
    public static String YEAR_WISE_REVENUE = "";
    //some comment
}
