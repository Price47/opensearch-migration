package com.example.springboot;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senacor.elasticsearch.evolution.core.ElasticsearchEvolution;


@RestController
public class MigrationController {

	@GetMapping("/migrate")
	public String index() {
		// first create a Elastic RestClient
		RestClient restclient = RestClient.builder(HttpHost.create("http://localhost:9200")).build();
		// then create a ElasticsearchEvolution configuration and create a instance of ElasticsearchEvolution with that configuration
		ElasticsearchEvolution elasticsearchEvolution = ElasticsearchEvolution.configure().load(restclient);
		int migrationsRun = elasticsearchEvolution.migrate();
		
		return String.format("%d Migrations Run", migrationsRun);
	}
}