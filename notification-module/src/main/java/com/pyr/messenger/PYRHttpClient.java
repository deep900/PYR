/**
 * 
 */
package com.pyr.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import sun.net.www.http.HttpClient;

/**
 * @author pradheep.p
 *
 */
public class PYRHttpClient {

	public String httpPost(String URL, String Parameters){
	StringBuffer finalStr = new StringBuffer();	
	org.apache.http.client.HttpClient client = HttpClientBuilder.create().build();
	HttpPost request = new HttpPost(URL+"?"+Parameters);
	HttpResponse response=null;
	try {
		response = client.execute(request);
	} catch (IOException e) {		
		e.printStackTrace();
	}

	// Get the response
	BufferedReader rd = null;
	try {
		rd = new BufferedReader
		    (new InputStreamReader(
		    response.getEntity().getContent()));
	} catch (UnsupportedOperationException | IOException e) {		
		e.printStackTrace();
	}

	String line = "";
	try {
		while ((line = rd.readLine()) != null) {
		    finalStr.append(line);
		}
	} catch (IOException e) {		
		e.printStackTrace();
	}
	return finalStr.toString();
	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {		

	}

}
