package simulation.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({
		"id", 
		"entityType", 
		"fieldName", 
		"text",
		"nlpFeatures"	
})

public class Process {
	
	@JsonProperty("id")
	@ApiModelProperty(notes = "Entity Id", example = "12345", required = true, position = 1)
	protected String id;
	
	@JsonProperty("entityType")
	@ApiModelProperty(notes = "type of entity", example = "review", required = true, position = 2)
	protected String entityType;
	
	@JsonProperty("fieldName")
	@ApiModelProperty(notes = "name of field", example = "comment", required = true, position = 3)
	protected String fieldName;
	
	@JsonProperty("text")
	@ApiModelProperty(notes = "text", example = "Do I really need to provide an example?", required = true, position = 4)
	protected String text;
	
	@JsonProperty("nlpFeatures")
	@ApiModelProperty(notes = "NLP features", example = "[\"emotion\"]", required = true, position = 5)
	protected List<String> nlpFeatures;
	
	@JsonProperty("workflowName")
	@ApiModelProperty(notes = "The name of workflow", example = "product_review_emotion", required = true, position = 6)
	private String workflowName;

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName.toLowerCase();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<String> getNlpFeatures() {
		return nlpFeatures;
	}

	public void setNlpFeatures(List<String> nlpFeatures) {
		this.nlpFeatures = nlpFeatures;
	}


}
