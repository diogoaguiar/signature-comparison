package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-11T22:40:50.677Z")
public class ComparisonResponse   {
  
  private Integer verdict = null;
  private Integer score = null;

  
  /**
   * Code of the comparison's veredict (match or no match)
   **/
  public ComparisonResponse verdict(Integer verdict) {
    this.verdict = verdict;
    return this;
  }

  
  @ApiModelProperty(value = "Code of the comparison's veredict (match or no match)")
  @JsonProperty("verdict")
  public Integer getVerdict() {
    return verdict;
  }
  public void setVerdict(Integer verdict) {
    this.verdict = verdict;
  }

  
  /**
   * Score of the comparison
   **/
  public ComparisonResponse score(Integer score) {
    this.score = score;
    return this;
  }

  
  @ApiModelProperty(value = "Score of the comparison")
  @JsonProperty("score")
  public Integer getScore() {
    return score;
  }
  public void setScore(Integer score) {
    this.score = score;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComparisonResponse comparisonResponse = (ComparisonResponse) o;
    return Objects.equals(verdict, comparisonResponse.verdict) &&
        Objects.equals(score, comparisonResponse.score);
  }

  @Override
  public int hashCode() {
    return Objects.hash(verdict, score);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComparisonResponse {\n");
    
    sb.append("    verdict: ").append(toIndentedString(verdict)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
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

