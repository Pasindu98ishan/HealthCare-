package com.example.physiological.storage.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * WardRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-04T22:28:53.467019400+05:30[Asia/Colombo]", comments = "Generator version: 7.4.0")
public class WardRequest {

  private String name;

  private String alias;

  private String hospitalName;

  public WardRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public WardRequest(String name, String hospitalName) {
    this.name = name;
    this.hospitalName = hospitalName;
  }

  public WardRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the ward
   * @return name
  */
  @NotNull @Size(min = 1) 
  @Schema(name = "name", description = "Name of the ward", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public WardRequest alias(String alias) {
    this.alias = alias;
    return this;
  }

  /**
   * Alias for the ward
   * @return alias
  */
  
  @Schema(name = "alias", description = "Alias for the ward", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("alias")
  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public WardRequest hospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
    return this;
  }

  /**
   * Name of the hospital the ward belongs to
   * @return hospitalName
  */
  @NotNull @Size(min = 1) 
  @Schema(name = "hospitalName", description = "Name of the hospital the ward belongs to", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hospitalName")
  public String getHospitalName() {
    return hospitalName;
  }

  public void setHospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WardRequest wardRequest = (WardRequest) o;
    return Objects.equals(this.name, wardRequest.name) &&
        Objects.equals(this.alias, wardRequest.alias) &&
        Objects.equals(this.hospitalName, wardRequest.hospitalName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, alias, hospitalName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WardRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    alias: ").append(toIndentedString(alias)).append("\n");
    sb.append("    hospitalName: ").append(toIndentedString(hospitalName)).append("\n");
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

