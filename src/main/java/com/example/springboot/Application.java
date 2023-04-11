package com.example.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.security.GeneralSecurityException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;



@SpringBootApplication
public class Application {
	@Bean
	public RestClient restClient() {
		RestClientBuilder builder = RestClient.builder(HttpHost.create("http://localhost:9200"))
				.setHttpClientConfigCallback(httpClientBuilder -> {
							CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
							credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("admin", "admin"));
							httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
							try {
								httpClientBuilder
										.setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
										.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
							} catch (GeneralSecurityException e) {
								throw new IllegalStateException("could not configure http client to accept all certificates", e);
							}
							return httpClientBuilder;
						}
				);
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Started Application");
		};
	}

}