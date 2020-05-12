package com.scheduler.demo.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.scheduler.demo.model.Note;

/**
 * 
 * @author Ravishankar.kumar
 *
 */
@Configuration
@EnableScheduling
public class ScheduledConfiguration {

	// External rest get api url
	final String uri = "http://localhost:8080/api/notes";

	// RestTemplate for getting object from api
	RestTemplate restTemplate = new RestTemplate();
	Note result[] = restTemplate.getForObject(uri, Note[].class);

	/**
	 *
	 * scheduled to run every 5 minutes but only during the 9-to-5 "business hours"
	 * on weekdays
	 *
	 */
	@Scheduled(cron = "${cron.expression}")
	public void executeTask() {

		ResponseEntity<String> response = getResponseEntity();

		if (response.getStatusCode().is2xxSuccessful()) {

			System.out.println(Arrays.toString(result));

		}

		System.out.println(Thread.currentThread().getName() + " The Task executed at " + new Date());

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// For getting a response status code value
	public ResponseEntity<String> getResponseEntity() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity request = new HttpEntity(result, headers);

		return restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
	}

}
