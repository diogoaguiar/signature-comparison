package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * Mazximum size of the images to be compared
 **/

@ApiModel(description = "Mazximum size of the images to be compared")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class InlineResponse2002NormMaxSize   {
  
  private Integer width = null;
  private Integer height = null;

  
  /**
   **/
  public InlineResponse2002NormMaxSize width(Integer width) {
    this.width = width;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("width")
  public Integer getWidth() {
    return width;
  }
  public void setWidth(Integer width) {
    this.width = width;
  }

  
  /**
   **/
  public InlineResponse2002NormMaxSize height(Integer height) {
    this.height = height;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("height")
  public Integer getHeight() {
    return height;
  }
  public void setHeight(Integer height) {
    this.height = height;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2002NormMaxSize inlineResponse2002NormMaxSize = (InlineResponse2002NormMaxSize) o;
    return Objects.equals(width, inlineResponse2002NormMaxSize.width) &&
        Objects.equals(height, inlineResponse2002NormMaxSize.height);
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2002NormMaxSize {\n");
    
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

