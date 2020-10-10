package com.test.wah.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.Collections;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping(value="/consumer")
@RestController
@CrossOrigin
public class ConsumerController {

	@GetMapping(value="/HttpURLConnection.")
	public ResponseEntity<String> getConsume(){
		//Note: it is not working as there is no certificate added.
		
		try{
			//Create a URL Object
			URL url = new URL ("https://reqres.in/api/users");
			
			//Create a URL Object
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			
			//Set the Request Method
			con.setRequestMethod("POST");

			//Set the Request Content-Type Header Parameter
			con.setRequestProperty("Content-Type", "application/json; utf-8");

			
			//Set Response Format Type
			con.setRequestProperty("Accept", "application/json");

			//Ensure the Connection Will Be Used to Send Content
			con.setDoOutput(true);

			//Create the Request Body
			String jsonInputString = "{" +"'name':"+ "'Upendra' ,"+ "'job' :"+ "'Programmer'}";
			//String jsonInputString = "{}";
			
			//We'd need to write it:
			try(OutputStream os = con.getOutputStream()) {
			    byte[] input = jsonInputString.getBytes("utf-8");
			    os.write(input, 0, input.length);			
			}
			
			//Read the Response from Input Stream
			try(BufferedReader br = new BufferedReader(
					  new InputStreamReader(con.getInputStream(), "utf-8"))) {
					    StringBuilder response = new StringBuilder();
					    String responseLine = null;
					    while ((responseLine = br.readLine()) != null) {
					        response.append(responseLine.trim());
					    }
					    System.out.println(response.toString());
					}
			//If the response is in JSON format, use any third-party JSON parsers such as Jackson library, Gson, or org.json to parse the response.
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@GetMapping(value="/HttpPost")
	public ResponseEntity<String> getConsumer2(){
		
		//NOte: working , HttpPost, HttpClient used with SSL .
		
		System.setProperty("javax.net.ssl.trustStore","C:/Users/john/Downloads/CloudflareIncECCCA.jks");
		System.setProperty("javax.net.ssl.trustStorePassword","Hello4");
		java.security.Security.setProperty("ssl.SocketFactory.provider", "sun.security.ssl.SSLSocketFactoryImpl");
		java.security.Security.setProperty("ssl.ServerSocketFocatory.provider", "sun.security.ssl.SSLSocketFactoryImpl");
		
		try{
			char[] passphrase="Hello4".toCharArray();
			KeyStore keystore=KeyStore.getInstance("JKS");
			FileInputStream fisN=new FileInputStream("C:/Users/john/Downloads/CloudflareIncECCCA.jks");
			keystore.load(fisN,passphrase);
			
			TrustManagerFactory tmf=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(keystore);
			SSLContext context=SSLContext.getInstance("SSL");
			TrustManager[] trustmanagers=tmf.getTrustManagers();
			context.init(null, trustmanagers, null);
			
			
			//Jsonob json=new JSONObject();
			HttpPost postRequest=new HttpPost("https://reqres.in/api/users");
			
			postRequest.addHeader("content-type","application/x-www-form-urlencoded");
			postRequest.addHeader("Accept","application/json");
			String jsonInputString = "{" +"'name':"+ "'Upendra' ,"+ "'job' :"+ "'Programmer'}";
			StringEntity json=new StringEntity(jsonInputString);
			postRequest.setEntity(json);
			
			HttpClient client=HttpClients.custom().setSSLContext(context).build();
			
			HttpResponse response=client.execute(postRequest);
			
			System.out.println(response);
			
			InputStream in=response.getEntity().getContent();
			BufferedReader reader=new BufferedReader(new InputStreamReader(in));
			String line;
			String output="";
			while((line=reader.readLine())!=null){
				output=line;
			}
			System.out.println(output);
			
			
			return new ResponseEntity<String>(output,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<String>("success",HttpStatus.OK);
		
	}
	@GetMapping(value="/HttpGet")
	public ResponseEntity<String> getConsumer3(){
		
		//Note: it working , with SSL added to the HttpClient and get method
		
		System.setProperty("javax.net.ssl.trustStore","C:/Users/john/Downloads/CloudflareIncECCCA.jks");
		System.setProperty("javax.net.ssl.trustStorePassword","Hello4");
		java.security.Security.setProperty("ssl.SocketFactory.provider", "sun.security.ssl.SSLSocketFactoryImpl");
		java.security.Security.setProperty("ssl.ServerSocketFocatory.provider", "sun.security.ssl.SSLSocketFactoryImpl");
		
		try{
			char[] passphrase="Hello4".toCharArray();
			KeyStore keystore=KeyStore.getInstance("JKS");
			FileInputStream fisN=new FileInputStream("C:/Users/john/Downloads/CloudflareIncECCCA.jks");
			keystore.load(fisN,passphrase);
			
			TrustManagerFactory tmf=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(keystore);
			SSLContext context=SSLContext.getInstance("SSL");
			TrustManager[] trustmanagers=tmf.getTrustManagers();
			context.init(null, trustmanagers, null);
			
			
			//Jsonob json=new JSONObject();
			HttpGet postRequest = new HttpGet("https://reqres.in/api/users"); 
			//HttpGet postRequest=new HttpGet("https://reqres.in/api/users");
			postRequest.addHeader("content-type","application/x-www-form-urlencoded");
			postRequest.addHeader("Accept","application/json");
			String jsonInputString = "{" +"'name':"+ "'Upendra' ,"+ "'job' :"+ "'Programmer'}";
			StringEntity json=new StringEntity(jsonInputString);
			//postRequest.setEntity(json);
			
			HttpClient client=HttpClients.custom().setSSLContext(context).build();
			
			HttpResponse response=client.execute(postRequest);
			
			System.out.println(response);
			
			InputStream in=response.getEntity().getContent();
			BufferedReader reader=new BufferedReader(new InputStreamReader(in));
			String line;
			String output="";
			while((line=reader.readLine())!=null){
				output=line;
			}
			System.out.println(output);
			
			
			return new ResponseEntity<String>(output,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<String>("success",HttpStatus.OK);
		
	}
	@GetMapping(value="/SSLRestTemplateGet")
	public ResponseEntity<String> getConsumer4(){
		//NOte: working properly with SSL added to the RestTemplate, call get method on RestTemplate.
		
		try{
			RestTemplate restTemplate=this.getSSLRestTemplate();
			
			String response = restTemplate.getForObject("https://reqres.in/api/users", String.class);
			//String response = restTemplate.getForObject("https://google.com/search?q=java", String.class);
			//String response = restTemplate.getForObject("https://google.com/search?q={q}", String.class,"java");
			System.out.println(response);
			
			return new ResponseEntity<String>(response,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<String>("success",HttpStatus.OK);
		
	}
	@GetMapping(value="/SSLRestTemplatePOST")
	public ResponseEntity<String> getConsumer5(){
		
		//NOte: working propertly with certificate added to the RestTemplate, calling as post mehtod on RestTemplate.
		
		String url = "https://reqres.in/api/users";

		// create an instance of RestTemplate
		RestTemplate restTemplate = this.getSSLRestTemplate();;

		// create headers
		HttpHeaders headers = new HttpHeaders();

		// set `Content-Type` and `Accept` headers
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// example of custom header
		//headers.set("X-Request-Source", "Desktop");
		String jsonInputString = "{" +"'name':"+ "'Upendra' ,"+ "'job' :"+ "'Programmer'}";
		// build the request
		HttpEntity request = new HttpEntity(headers);

		// make an HTTP GET request with headers
		ResponseEntity<String> response = restTemplate.exchange(
		        url,
		        HttpMethod.GET,
		        request,
		        String.class,
		        jsonInputString
		);

		// check response
		if (response.getStatusCode() == HttpStatus.OK) {
		    System.out.println("Request Successful.");
		    System.out.println(response.getBody());
		} else {
		    System.out.println("Request Failed");
		    System.out.println(response.getStatusCode());
		}
		return new ResponseEntity<String>(response.toString(),HttpStatus.OK);
	}
	
	public RestTemplate getSSLRestTemplate(){
		System.setProperty("javax.net.ssl.trustStore","C:/Users/john/Downloads/CloudflareIncECCCA.jks");
		System.setProperty("javax.net.ssl.trustStorePassword","Hello4");
		java.security.Security.setProperty("ssl.SocketFactory.provider", "sun.security.ssl.SSLSocketFactoryImpl");
		java.security.Security.setProperty("ssl.ServerSocketFocatory.provider", "sun.security.ssl.SSLSocketFactoryImpl");
		
		try{
			
			char[] passphrase="Hello4".toCharArray();
			KeyStore keystore=KeyStore.getInstance("JKS");
			FileInputStream fisN=new FileInputStream("C:/Users/john/Downloads/CloudflareIncECCCA.jks");
			keystore.load(fisN,passphrase);
			
			TrustManagerFactory tmf=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(keystore);
			SSLContext context=SSLContext.getInstance("SSL");
			TrustManager[] trustmanagers=tmf.getTrustManagers();
			context.init(null, trustmanagers, null);
			
			HttpClient client=HttpClients.custom().setSSLContext(context).build();
			
		    HttpComponentsClientHttpRequestFactory requestFactory=new HttpComponentsClientHttpRequestFactory();
		    
		    requestFactory.setHttpClient(client);
		    
		    RestTemplate resttemplate=new RestTemplate(requestFactory);
			
			return resttemplate;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
} 
