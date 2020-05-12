package simulation.api.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.ApiOperation;
import simulation.api.model.Delete;
import simulation.api.model.Process;
import simulation.api.model.ProcessedEntity;
import simulation.api.model.Query;
import simulation.api.utils.IndexingUtils;

@RestController
public class Endpoint {

	protected Logger logger = LogManager.getLogger(Endpoint.class);
	@Autowired
	RestHighLevelClient RESTclient;

	static class Config {

		protected Logger logger = LogManager.getLogger(Config.class);

		@Value("${elasticsearch.host}")
		private String elasticsearchHost;

		@Value("${elasticsearch.port}")
		private int elasticsearchPort;

		
		@Bean(destroyMethod = "close")
		RestHighLevelClient client() {

			//logger.info(elasticsearchHost + ":" + elasticsearchPort);
			logger.info("Attempting to create a High Level Rest Client");

			ClientConfiguration clientConfiguration = ClientConfiguration.builder()
					.connectedTo(elasticsearchHost + ":" + elasticsearchPort).build();

			RestHighLevelClient client = RestClients.create(clientConfiguration).rest();

			try {
				Boolean ping = client.ping();
				if (ping) {
					logger.info("Client has successfully connected to Elasticsearch");
				}

			} catch (Exception e) {
				logger.error("Error connecting to Elasticsearch, please check your configurations");
				// System.exit(0);
			}

			return RestClients.create(clientConfiguration).rest();
		}

	}

	final String jobIndex = "jobs";

	@PostMapping("/processText")
	@Async
	@ApiOperation("Prepares entity for Natural Language Processing")
	public void process(@RequestBody Process process) {

		ProcessedEntity ProcessedEntity = new ProcessedEntity(process);

		IndexRequest indexRequest = new IndexRequest();
		indexRequest.index(jobIndex).id(ProcessedEntity.getId()).type("job").source(ProcessedEntity.toJson(),
				XContentType.JSON);

		ActionListener<IndexResponse> listener;
		listener = new ActionListener<IndexResponse>() {

			@Override
			public void onResponse(IndexResponse response) {
				logger.info("Document id: " + response.getId() + " has been " + response.getResult());

			}

			@Override
			public void onFailure(Exception e) {
				logger.error(e);

			}

		};

		RESTclient.indexAsync(indexRequest, listener, IndexingUtils.writeHeaders);
	}

	@GetMapping("/queryTextAnalytics")
	@ApiOperation("Retrieve entities from Text Analytics Backend")
	@Async
	public Set<String> query(@RequestBody Query query) {
		//TODO Modify this code to get Processed things
		
		
//				// Bool Query
//				BoolQueryBuilder boolQuery = new BoolQueryBuilder();
//				boolQuery.must(new MatchQueryBuilder("entityType", query.getEntityType()));
//				boolQuery.must(new MatchQueryBuilder("workflowName", query.getFieldName()));
//
//				// Search Source
//				SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//				searchSourceBuilder.query(boolQuery);
//
//				// Search Request
//				SearchRequest searchRequest = new SearchRequest("jobs");
//				searchRequest.source(searchSourceBuilder);
//				SearchResponse response = null;
//
//				try {
//
//					response = client.search(searchRequest);
//
//				} catch (IOException e) {
//
//					e.printStackTrace();
//
//				}
//
//				SearchHits hits = response.getHits();
//
//				List<Job> jobs = new ArrayList<Job>();
//
//				for (SearchHit x : hits) {
//
//					try {
//
//						jobs.add(objectMapper.readValue(x.getSourceAsString(), Job.class));
//
//					} catch (JsonProcessingException e) {
//
//						logger.error(e);
//					}
//
//				}
//
//				return jobs;
//			}
//		
		
		
		
		Set<String> x = new HashSet<>();
		return x;
	}

	@PostMapping("/deleteDocument")
	@ApiOperation("Deletes document from Text Analytics Backend")
	@Async
	public void delete(@RequestBody Delete delete) {

		// Bool Query
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		boolQuery.must(new MatchQueryBuilder("id", delete.getId()));

		// Delete by Query
		DeleteByQueryRequest request = new DeleteByQueryRequest(jobIndex);
		request.setQuery(boolQuery);

		ActionListener<BulkByScrollResponse> listener;
		listener = new ActionListener<BulkByScrollResponse>() {
			@Override
			public void onResponse(BulkByScrollResponse bulkResponse) {
				logger.info("deleted sucessfully");
			}

			@Override
			public void onFailure(Exception e) {
				logger.error("error deleting");
			}
		};

		RESTclient.deleteByQueryAsync(request, RequestOptions.DEFAULT, listener);

	}

}
