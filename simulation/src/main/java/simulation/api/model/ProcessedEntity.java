package simulation.api.model;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "entityType", "fieldName", "text", "nlpFeatures", "workflowName", "result" })
public class ProcessedEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6650584511910957426L;

	public ProcessedEntity(Process process) {
		this.id = process.getId();
		this.entityType = process.getEntityType();
		this.fieldName = process.getFieldName();
		this.nlpFeatures = process.getNlpFeatures();
		this.text = process.getText();
		this.workflowName = process.getWorkflowName();
		this.result = this.getRandomResult();
	}

	private String getRandomResult() {

		int randomNum = ThreadLocalRandom.current().nextInt(0, 4 + 1);
		String result = "";

		switch (randomNum) {
		case 0:
			result = "Monday";
			break;
		case 1:
			result = "Tuesday";
			break;
		case 2:
			result = "Wednesday";
			break;
		case 3:
			result = "Thursday";
			break;
		case 4:
			result = "Friday";
			break;

		default:
			result = "Nothing found";
			break;
		}

		return result;
	}

	@JsonProperty("id")
	@ApiModelProperty(notes = "Entity Id", example = "\"12345\"", required = true, position = 1)
	private String id;

	@JsonProperty("entityType")
	@ApiModelProperty(notes = "type of entity", example = "\"review\"", required = true, position = 2)
	private String entityType;

	@JsonProperty("fieldName")
	@ApiModelProperty(notes = "name of field", example = "\"comment\"", required = true, position = 3)
	private String fieldName;

	@JsonProperty("text")
	@ApiModelProperty(notes = "text", example = "\"Do I really need to provide an example?\"", required = true, position = 4)
	private String text;

	@JsonProperty("nlpFeatures")
	@ApiModelProperty(notes = "NLP features", example = "[\"emotion\"]", required = true, position = 5)
	private List<String> nlpFeatures;

	@JsonProperty("workflowName")
	@ApiModelProperty(notes = "The name of workflow", example = "\"product_review_emotion\"", required = true, position = 6)
	private String workflowName;

	@JsonProperty("result")
	@ApiModelProperty(notes = "\"Result from proceesing of the entity by the workflow. Note this has to be a String in Json format\"", example = "product_review_emotion", required = true, position = 7)
	private String result;

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
	public List<String> getNlpFeatures() {
		return nlpFeatures;
	}

	@JsonProperty("nlpFeatures")
	public void setNlpFeatures(List<String> nlpFeatures) {
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

	public String getResult() {
		return result;
	}

	@JsonProperty("result")
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Simple method which converts the object into a JSON representation of type
	 * String
	 * 
	 * @return String (Json Structure)
	 */
	public String toJson() {

		String nlpFeatures = "[";
		if (this.getNlpFeatures().size() > 0) {

			int size = this.getNlpFeatures().size();

			for (int i = 0; i < this.getNlpFeatures().size(); i++) {

				String temp = "\"";

				temp = temp + this.getNlpFeatures().get(i) + "\"";

				if (i < this.getNlpFeatures().size()-1) {
					temp = temp + ",";
				}

				nlpFeatures = nlpFeatures + temp;

			}
		}

		nlpFeatures = nlpFeatures + "]";

		String json = "{\n" + "\t\"id\" : \"" + id + "\",\n" + "\t\"entityType\" : \"" + entityType + "\",\n"
				+ "\t\"fieldName\" : \"" + fieldName + "\",\n" + "\t\"text\" : \"" + text + "\",\n"
				+ "\t\"nlpFeatures\" :" + nlpFeatures + ",\n" + "\t\"workflowName\" : \"" + workflowName + "\",\n"
				+ "\t\"result\" : \"" + result + "\"\n" + "}";

		return json;
	}

}
