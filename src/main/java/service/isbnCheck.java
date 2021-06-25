package service;

import javax.ws.rs.*;

@Path("/isbnCheck")
public class isbnCheck {
    @GET
    @Produces("text/plain")
    public String check (@QueryParam("isbn") String isbn) {
        if (isbn.length() != 13) {
            return "{ \"check\": false }";
        }
        int a = 0;
        for (int i = 0; i < 12; i = i + 2) {
            a = a + isbn.charAt(i) - '0';
        }
        for (int i = 1; i < 12; i = i + 2) {
            a = a + 3 * (isbn.charAt(i) - '0');
        }
        a = 10 - a % 10;
        if (a == isbn.charAt(12) - '0') {
            return "{ \"check\": true }";
        }
        return "{ \"check\": false }";
    }
}