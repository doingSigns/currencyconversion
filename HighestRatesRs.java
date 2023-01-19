/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myRESTWS;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import myRS.CurCodesRS;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author N1032570
 */
@Path("higherRates")
public class HighestRatesRs {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HighestRatesRs
     */
    public HighestRatesRs() {
    }

    /**
     * Retrieves representation of an instance of myRESTWS.HighestRatesRs
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
  public String getJson(@QueryParam("Cur")String Cur) {
        JSONArray ja = new JSONArray();
        for(CurCodesRS.ExRate exr : CurCodesRS.ExRate.values()){
            JSONObject job = new JSONObject();
            if(exr.rateInGBP() > CurCodesRS.ExRate.valueOf(Cur).rateInGBP() && !exr.equals(CurCodesRS.ExRate.GBP))
            {
                job.put("name", exr.curName());
                job.put("code", exr.name());
                job.put("rate", exr.rateInGBP());
                ja.put(job);
            }
        }
        JSONObject rootJobj = new JSONObject();

        rootJobj.put("HigherRate", ja);

        return rootJobj.toString();
    }
    /**
     * PUT method for updating or creating an instance of HighestRatesRs
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
