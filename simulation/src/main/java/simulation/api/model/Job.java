package simulation.api.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "entityType", "fieldName", "text", "nlpFeatures", "workflowName" })
public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 808085401519531528L;

	@JsonProperty("id")
    @ApiModelProperty(notes = "Entity Id", example = "12345", required = true, position = 1)
	private String id;
	
	@JsonProperty("entityType")
	@ApiModelProperty(notes = "type of entity", example = "review", required = true, position = 2)
	private String entityType;
	
	@JsonProperty("fieldName")
	@ApiModelProperty(notes = "name of field", example = "comment", required = true, position = 3)
	private String fieldName;
	
	@JsonProperty("text")
	@ApiModelProperty(notes = "text", example = "Do I really need to provide an example?", required = true, position = 4)
	private String text;
	
	@JsonProperty("nlpFeatures")
	@ApiModelProperty(notes = "NLP features", example = "emotion", required = true, position = 5)
	private String nlpFeatures;
	
	@JsonProperty("workflowName")
	@ApiModelProperty(notes = "The name of workflow", example = "product_review_emotion", required = true, position = 6)
	private String workflowName;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("entityType")
	public String getEntityType() {
		return entityType;
	}

	@JsonProperty("entityType")
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@JsonProperty("fieldName")
	public String getFieldName() {
		return fieldName;
	}

	@JsonProperty("fieldName")
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@JsonProperty("text")
	public String getText() {
		return text;
	}

	@JsonProperty("text")
	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("nlpFeatures")
	public String getNlpFeatures() {
		return nlpFeatures;
	}

	@JsonProperty("nlpFeatures")
	public void setNlpFeatures(String nlpFeatures) {
		this.nlpFeatures = nlpFeatures;
	}

	@JsonProperty("workflowName")
	public String getWorkflowName() {
		return workflowName;
	}

	@JsonProperty("workflowName")
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", entityType=" + entityType + ", fieldName=" + fieldName + ", text=" + text + ", nlpFeatures=" + nlpFeatures + ", workflowName=" + workflowName + "]";
	}

}