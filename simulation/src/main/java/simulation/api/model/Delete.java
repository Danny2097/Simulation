package simulation.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class Delete {

	@JsonProperty("id")
	@ApiModelProperty(notes = "Entity Id", example = "12345", required = true, position = 1)
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
