package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.InlineResponse2002NormMaxSize;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-26T14:01:41.973Z")
public class InlineResponse2002   {
  
  private Integer minNumMatches = null;
  private InlineResponse2002NormMaxSize normMaxSize = null;
  private Integer threshold = null;
  private Double minMatchesPercent = null;

  
  /**
   * Number of matches needed to be a matching image
   **/
  public InlineResponse2002 minNumMatches(Integer minNumMatches) {
    this.minNumMatches = minNumMatches;
    return this;
  }

  
  @ApiModelProperty(value = "Number of matches needed to be a matching image")
  @JsonProperty("minNumMatches")
  public Integer getMinNumMatches() {
    return minNumMatches;
  }
  public void setMinNumMatches(Integer minNumMatches) {
    this.minNumMatches = minNumMatches;
  }

  
  /**
   **/
  public InlineResponse2002 normMaxSize(InlineResponse2002NormMaxSize normMaxSize) {
    this.normMaxSize = normMaxSize;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("normMaxSize")
  public InlineResponse2002NormMaxSize getNormMaxSize() {
    return normMaxSize;
  }
  public void setNormMaxSize(InlineResponse2002NormMaxSize normMaxSize) {
    this.normMaxSize = normMaxSize;
  }

  
  /**
   * Threshold for image comparison
   **/
  public InlineResponse2002 threshold(Integer threshold) {
    this.threshold = threshold;
    return this;
  }

  
  @ApiModelProperty(value = "Threshold for image comparison")
  @JsonProperty("threshold")
  public Integer getThreshold() {
    return threshold;
  }
  public void setThreshold(Integer threshold) {
    this.threshold = threshold;
  }

  
  /**
   * Percentage of success that minNumMatches is equivalent to
   **/
  public InlineResponse2002 minMatchesPercent(Double minMatchesPercent) {
    this.minMatchesPercent = minMatchesPercent;
    return this;
  }

  
  @ApiModelProperty(value = "Percentage of success that minNumMatches is equivalent to")
  @JsonProperty("minMatchesPercent")
  public Double getMinMatchesPercent() {
    return minMatchesPercent;
  }
  public void setMinMatchesPercent(Double minMatchesPercent) {
    this.minMatchesPercent = minMatchesPercent;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2002 inlineResponse2002 = (InlineResponse2002) o;
    return Objects.equals(minNumMatches, inlineResponse2002.minNumMatches) &&
        Objects.equals(normMaxSize, inlineResponse2002.normMaxSize) &&
        Objects.equals(threshold, inlineResponse2002.threshold) &&
        Objects.equals(minMatchesPercent, inlineResponse2002.minMatchesPercent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(minNumMatches, normMaxSize, threshold, minMatchesPercent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2002 {\n");
    
    sb.append("    minNumMatches: ").append(toIndentedString(minNumMatches)).append("\n");
    sb.append("    normMaxSize: ").append(toIndentedString(normMaxSize)).append("\n");
    sb.append("    threshold: ").append(toIndentedString(threshold)).append("\n");
    sb.append("    minMatchesPercent: ").append(toIndentedString(minMatchesPercent)).append("\n");
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

