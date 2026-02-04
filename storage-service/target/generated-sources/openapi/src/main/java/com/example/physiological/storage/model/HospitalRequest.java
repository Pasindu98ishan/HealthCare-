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
 * HospitalRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-04T22:28:53.467019400+05:30[Asia/Colombo]", comments = "Generator version: 7.4.0")
public class HospitalRequest {

  private String name;

  private String address;

  public HospitalRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public HospitalRequest(String name) {
    this.name = name;
  }

  public HospitalRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the hospital
   * @return name
  */
  @NotNull @Size(min = 1) 
  @Schema(name = "name", description = "Name of the hospital", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public HospitalRequest address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Address of the hospital
   * @return address
  */
  
  @Schema(name = "address", description = "Address of the hospital", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HospitalRequest hospitalRequest = (HospitalRequest) o;
    return Objects.equals(this.name, hospitalRequest.name) &&
        Objects.equals(this.address, hospitalRequest.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HospitalRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
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

