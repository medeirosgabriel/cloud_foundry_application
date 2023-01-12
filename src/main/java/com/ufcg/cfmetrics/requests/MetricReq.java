package com.ufcg.cfmetrics.requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.cloudfoundry.reactor.ConnectionContext;
import org.cloudfoundry.reactor.tokenprovider.PasswordGrantTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class MetricReq {
	
	@Autowired
	private PasswordGrantTokenProvider passwordGrantTokenProvider;
	@Autowired
	private ConnectionContext connectionContext;
	
	
	public String getPrometheusMetrics(String query, String start, String end, String step) throws IOException {
		query = query.replace(" ", "");
		String url = "https://metric-store.sausvtc01.pcf.dell.com/api/v1/query_range?query=%s&start=%s&end=%s&step=%s";
		url = String.format(url, query, start, end, step);
		String token = passwordGrantTokenProvider.getToken(connectionContext).block();
		URL obj = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Authorization", token);
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            } 
            in.close();
            return response.toString();
        } else {
        	return "Request not worked: " + responseCode;
        }
    }
	
}
