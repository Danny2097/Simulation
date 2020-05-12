package simulation.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class Query {

	
	@JsonProperty("entityType")
	@ApiModelProperty(notes = "type of entity", example = "\"review\"", required = true, position = 1)
	private String entityType;
	
	@JsonProperty("fieldName")
	@ApiModelProperty(notes = "name of field", example = "\"comment\"", required = true, position = 2)
	private String fieldName;
	
	@ApiModelProperty(notes = "this needs to be defined", example = "\"needs defintion\"", required = true, position = 3)
	protected NlpExpression nlpExpression;

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

	public NlpExpression getNlpExpression() {
		return nlpExpression;
	}

	public void setNlpExpression(NlpExpression nlpExpression) {
		this.nlpExpression = nlpExpression;
	}

}
