package com.example.physiological.storage.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.HashMap;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * StorageRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-04T22:28:53.467019400+05:30[Asia/Colombo]", comments = "Generator version: 7.4.0")
public class StorageRequest {

  private String hospital;

  private String ward;

  @Valid
  private Map<String, String> data = new HashMap<>();

  public StorageRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public StorageRequest(String hospital, String ward, Map<String, String> data) {
    this.hospital = hospital;
    this.ward = ward;
    this.data = data;
  }

  public StorageRequest hospital(String hospital) {
    this.hospital = hospital;
    return this;
  }

  /**
   * Name of the hospital
   * @return hospital
  */
  @NotNull 
  @Schema(name = "hospital", description = "Name of the hospital", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hospital")
  public String getHospital() {
    return hospital;
  }

  public void setHospital(String hospital) {
    this.hospital = hospital;
  }

  public StorageRequest ward(String ward) {
    this.ward = ward;
    return this;
  }

  /**
   * Ward identifier
   * @return ward
  */
  @NotNull 
  @Schema(name = "ward", description = "Ward identifier", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ward")
  public String getWard() {
    return ward;
  }

  public void setWard(String ward) {
    this.ward = ward;
  }

  public StorageRequest data(Map<String, String> data) {
    this.data = data;
    return this;
  }

  public StorageRequest putDataItem(String key, String dataItem) {
    if (this.data == null) {
      this.data = new HashMap<>();
    }
    this.data.put(key, dataItem);
    return this;
  }

  /**
   * Key-value pairs of physiological data
   * @return data
  */
  @NotNull 
  @Schema(name = "data", description = "Key-value pairs of physiological data", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("data")
  public Map<String, String> getData() {
    return data;
  }

  public void setData(Map<String, String> data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StorageRequest storageRequest = (StorageRequest) o;
    return Objects.equals(this.hospital, storageRequest.hospital) &&
        Objects.equals(this.ward, storageRequest.ward) &&
        Objects.equals(this.data, storageRequest.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hospital, ward, data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StorageRequest {\n");
    sb.append("    hospital: ").append(toIndentedString(hospital)).append("\n");
    sb.append("    ward: ").append(toIndentedString(ward)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

