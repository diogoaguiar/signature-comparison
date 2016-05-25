package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-25T15:12:55.599Z")
public class InlineResponse2001   {
  
  private String queryKP = null;
  private String queryDescriptors = null;
  private Integer threshold = null;
  private String bestMatches = null;
  private byte[] image1 = null;
  private byte[] image2 = null;
  private byte[] resultImg = null;
  private byte[] queryImg = null;
  private String trainKP = null;
  private byte[] queryKPDraw = null;
  private Double score = null;
  private byte[] trainImg = null;
  private String trainDescriptors = null;
  private Integer minNumMatches = null;
  private Boolean verdict = null;
  private Double minMatchesPercent = null;
  private Double matchRate = null;
  private byte[] trainKPDraw = null;

  
  /**
   * Size of the matrix of keypoints for the query image
   **/
  public InlineResponse2001 queryKP(String queryKP) {
    this.queryKP = queryKP;
    return this;
  }

  
  @ApiModelProperty(value = "Size of the matrix of keypoints for the query image")
  @JsonProperty("queryKP")
  public String getQueryKP() {
    return queryKP;
  }
  public void setQueryKP(String queryKP) {
    this.queryKP = queryKP;
  }

  
  /**
   * Size of the matrix of descriptors for the query image
   **/
  public InlineResponse2001 queryDescriptors(String queryDescriptors) {
    this.queryDescriptors = queryDescriptors;
    return this;
  }

  
  @ApiModelProperty(value = "Size of the matrix of descriptors for the query image")
  @JsonProperty("queryDescriptors")
  public String getQueryDescriptors() {
    return queryDescriptors;
  }
  public void setQueryDescriptors(String queryDescriptors) {
    this.queryDescriptors = queryDescriptors;
  }

  
  /**
   * Defines the rigor applied to the selection of the bestMatches (lower number = more rigorous)
   **/
  public InlineResponse2001 threshold(Integer threshold) {
    this.threshold = threshold;
    return this;
  }

  
  @ApiModelProperty(value = "Defines the rigor applied to the selection of the bestMatches (lower number = more rigorous)")
  @JsonProperty("threshold")
  public Integer getThreshold() {
    return threshold;
  }
  public void setThreshold(Integer threshold) {
    this.threshold = threshold;
  }

  
  /**
   * Size of the matrix of matches found within the threshold
   **/
  public InlineResponse2001 bestMatches(String bestMatches) {
    this.bestMatches = bestMatches;
    return this;
  }

  
  @ApiModelProperty(value = "Size of the matrix of matches found within the threshold")
  @JsonProperty("bestMatches")
  public String getBestMatches() {
    return bestMatches;
  }
  public void setBestMatches(String bestMatches) {
    this.bestMatches = bestMatches;
  }

  
  /**
   * Base64 string of image1
   **/
  public InlineResponse2001 image1(byte[] image1) {
    this.image1 = image1;
    return this;
  }

  
  @ApiModelProperty(value = "Base64 string of image1")
  @JsonProperty("image1")
  public byte[] getImage1() {
    return image1;
  }
  public void setImage1(byte[] image1) {
    this.image1 = image1;
  }

  
  /**
   * Base64 string of image2
   **/
  public InlineResponse2001 image2(byte[] image2) {
    this.image2 = image2;
    return this;
  }

  
  @ApiModelProperty(value = "Base64 string of image2")
  @JsonProperty("image2")
  public byte[] getImage2() {
    return image2;
  }
  public void setImage2(byte[] image2) {
    this.image2 = image2;
  }

  
  /**
   * Base64 string of the comparison result image
   **/
  public InlineResponse2001 resultImg(byte[] resultImg) {
    this.resultImg = resultImg;
    return this;
  }

  
  @ApiModelProperty(value = "Base64 string of the comparison result image")
  @JsonProperty("resultImg")
  public byte[] getResultImg() {
    return resultImg;
  }
  public void setResultImg(byte[] resultImg) {
    this.resultImg = resultImg;
  }

  
  /**
   * Base64 string of query image
   **/
  public InlineResponse2001 queryImg(byte[] queryImg) {
    this.queryImg = queryImg;
    return this;
  }

  
  @ApiModelProperty(value = "Base64 string of query image")
  @JsonProperty("queryImg")
  public byte[] getQueryImg() {
    return queryImg;
  }
  public void setQueryImg(byte[] queryImg) {
    this.queryImg = queryImg;
  }

  
  /**
   * Size of the matrix of keypoints for the train image
   **/
  public InlineResponse2001 trainKP(String trainKP) {
    this.trainKP = trainKP;
    return this;
  }

  
  @ApiModelProperty(value = "Size of the matrix of keypoints for the train image")
  @JsonProperty("trainKP")
  public String getTrainKP() {
    return trainKP;
  }
  public void setTrainKP(String trainKP) {
    this.trainKP = trainKP;
  }

  
  /**
   * Base64 string of query image with keypoints
   **/
  public InlineResponse2001 queryKPDraw(byte[] queryKPDraw) {
    this.queryKPDraw = queryKPDraw;
    return this;
  }

  
  @ApiModelProperty(value = "Base64 string of query image with keypoints")
  @JsonProperty("queryKPDraw")
  public byte[] getQueryKPDraw() {
    return queryKPDraw;
  }
  public void setQueryKPDraw(byte[] queryKPDraw) {
    this.queryKPDraw = queryKPDraw;
  }

  
  /**
   * Matching score
   **/
  public InlineResponse2001 score(Double score) {
    this.score = score;
    return this;
  }

  
  @ApiModelProperty(value = "Matching score")
  @JsonProperty("score")
  public Double getScore() {
    return score;
  }
  public void setScore(Double score) {
    this.score = score;
  }

  
  /**
   * Base64 string of train image
   **/
  public InlineResponse2001 trainImg(byte[] trainImg) {
    this.trainImg = trainImg;
    return this;
  }

  
  @ApiModelProperty(value = "Base64 string of train image")
  @JsonProperty("trainImg")
  public byte[] getTrainImg() {
    return trainImg;
  }
  public void setTrainImg(byte[] trainImg) {
    this.trainImg = trainImg;
  }

  
  /**
   * Size of the matrix of descriptors for the train image
   **/
  public InlineResponse2001 trainDescriptors(String trainDescriptors) {
    this.trainDescriptors = trainDescriptors;
    return this;
  }

  
  @ApiModelProperty(value = "Size of the matrix of descriptors for the train image")
  @JsonProperty("trainDescriptors")
  public String getTrainDescriptors() {
    return trainDescriptors;
  }
  public void setTrainDescriptors(String trainDescriptors) {
    this.trainDescriptors = trainDescriptors;
  }

  
  /**
   * Minimum number of best matches needed to be considered a matching image
   **/
  public InlineResponse2001 minNumMatches(Integer minNumMatches) {
    this.minNumMatches = minNumMatches;
    return this;
  }

  
  @ApiModelProperty(value = "Minimum number of best matches needed to be considered a matching image")
  @JsonProperty("minNumMatches")
  public Integer getMinNumMatches() {
    return minNumMatches;
  }
  public void setMinNumMatches(Integer minNumMatches) {
    this.minNumMatches = minNumMatches;
  }

  
  /**
   * Comparison's verdict (true = match, false = no match)
   **/
  public InlineResponse2001 verdict(Boolean verdict) {
    this.verdict = verdict;
    return this;
  }

  
  @ApiModelProperty(value = "Comparison's verdict (true = match, false = no match)")
  @JsonProperty("verdict")
  public Boolean getVerdict() {
    return verdict;
  }
  public void setVerdict(Boolean verdict) {
    this.verdict = verdict;
  }

  
  /**
   * Percentage of success taht minNumMatches is equivalent to
   **/
  public InlineResponse2001 minMatchesPercent(Double minMatchesPercent) {
    this.minMatchesPercent = minMatchesPercent;
    return this;
  }

  
  @ApiModelProperty(value = "Percentage of success taht minNumMatches is equivalent to")
  @JsonProperty("minMatchesPercent")
  public Double getMinMatchesPercent() {
    return minMatchesPercent;
  }
  public void setMinMatchesPercent(Double minMatchesPercent) {
    this.minMatchesPercent = minMatchesPercent;
  }

  
  /**
   * Matched descriptors rate in percentage
   **/
  public InlineResponse2001 matchRate(Double matchRate) {
    this.matchRate = matchRate;
    return this;
  }

  
  @ApiModelProperty(value = "Matched descriptors rate in percentage")
  @JsonProperty("matchRate")
  public Double getMatchRate() {
    return matchRate;
  }
  public void setMatchRate(Double matchRate) {
    this.matchRate = matchRate;
  }

  
  /**
   * Base64 string of train image with keypoints
   **/
  public InlineResponse2001 trainKPDraw(byte[] trainKPDraw) {
    this.trainKPDraw = trainKPDraw;
    return this;
  }

  
  @ApiModelProperty(value = "Base64 string of train image with keypoints")
  @JsonProperty("trainKPDraw")
  public byte[] getTrainKPDraw() {
    return trainKPDraw;
  }
  public void setTrainKPDraw(byte[] trainKPDraw) {
    this.trainKPDraw = trainKPDraw;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2001 inlineResponse2001 = (InlineResponse2001) o;
    return Objects.equals(queryKP, inlineResponse2001.queryKP) &&
        Objects.equals(queryDescriptors, inlineResponse2001.queryDescriptors) &&
        Objects.equals(threshold, inlineResponse2001.threshold) &&
        Objects.equals(bestMatches, inlineResponse2001.bestMatches) &&
        Objects.equals(image1, inlineResponse2001.image1) &&
        Objects.equals(image2, inlineResponse2001.image2) &&
        Objects.equals(resultImg, inlineResponse2001.resultImg) &&
        Objects.equals(queryImg, inlineResponse2001.queryImg) &&
        Objects.equals(trainKP, inlineResponse2001.trainKP) &&
        Objects.equals(queryKPDraw, inlineResponse2001.queryKPDraw) &&
        Objects.equals(score, inlineResponse2001.score) &&
        Objects.equals(trainImg, inlineResponse2001.trainImg) &&
        Objects.equals(trainDescriptors, inlineResponse2001.trainDescriptors) &&
        Objects.equals(minNumMatches, inlineResponse2001.minNumMatches) &&
        Objects.equals(verdict, inlineResponse2001.verdict) &&
        Objects.equals(minMatchesPercent, inlineResponse2001.minMatchesPercent) &&
        Objects.equals(matchRate, inlineResponse2001.matchRate) &&
        Objects.equals(trainKPDraw, inlineResponse2001.trainKPDraw);
  }

  @Override
  public int hashCode() {
    return Objects.hash(queryKP, queryDescriptors, threshold, bestMatches, image1, image2, resultImg, queryImg, trainKP, queryKPDraw, score, trainImg, trainDescriptors, minNumMatches, verdict, minMatchesPercent, matchRate, trainKPDraw);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2001 {\n");
    
    sb.append("    queryKP: ").append(toIndentedString(queryKP)).append("\n");
    sb.append("    queryDescriptors: ").append(toIndentedString(queryDescriptors)).append("\n");
    sb.append("    threshold: ").append(toIndentedString(threshold)).append("\n");
    sb.append("    bestMatches: ").append(toIndentedString(bestMatches)).append("\n");
    sb.append("    image1: ").append(toIndentedString(image1)).append("\n");
    sb.append("    image2: ").append(toIndentedString(image2)).append("\n");
    sb.append("    resultImg: ").append(toIndentedString(resultImg)).append("\n");
    sb.append("    queryImg: ").append(toIndentedString(queryImg)).append("\n");
    sb.append("    trainKP: ").append(toIndentedString(trainKP)).append("\n");
    sb.append("    queryKPDraw: ").append(toIndentedString(queryKPDraw)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
    sb.append("    trainImg: ").append(toIndentedString(trainImg)).append("\n");
    sb.append("    trainDescriptors: ").append(toIndentedString(trainDescriptors)).append("\n");
    sb.append("    minNumMatches: ").append(toIndentedString(minNumMatches)).append("\n");
    sb.append("    verdict: ").append(toIndentedString(verdict)).append("\n");
    sb.append("    minMatchesPercent: ").append(toIndentedString(minMatchesPercent)).append("\n");
    sb.append("    matchRate: ").append(toIndentedString(matchRate)).append("\n");
    sb.append("    trainKPDraw: ").append(toIndentedString(trainKPDraw)).append("\n");
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

