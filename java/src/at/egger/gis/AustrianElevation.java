/*
 * Copyright Manfred Egger, 2018.
 */
package at.egger.gis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author maegger
 */
public class AustrianElevation {

    public static String url = "https://raw.githubusercontent.com/maegger/";

    /**
     * input: double x: x-coordinate (EPSG: 3857) 
     * double y: y-coordinate (EPSG: 3857)
     *
     * @author maegger
     */
    public static double getAustrianElevation(double x, double y) {
        String raster_x = "" + (int) (x - (x % 10));
        URL oracle = null;
        try {
            oracle = new URL(url + (int) (x - (int) (x % 20000)) + "/master/" + (int) (y - (y % 10)) + ".txt");
            BufferedReader in = null;
            in = new BufferedReader(new InputStreamReader(oracle.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] line = inputLine.split(" ");
                if (line[0].equals((String) raster_x)) {
                    in.close();
                    return Double.parseDouble(line[1]);
                }
            }
            in.close();
        } catch (IOException ex) {
            //Logger.getLogger(GetElevation.class.getName()).log(Level.SEVERE, null, ex);
            return -9999.0;
        }
        return 0.0;
    }

}
