package com.dws.challenge.web.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequest {

  @NotNull(message = "Source account ID cannot be null")
  private Long accountFromId;

  @NotNull(message = "Destination account ID cannot be null")
  private Long accountToId;

  @NotNull(message = "Amount cannot be null")
  @Min(value = 1, message = "Transfer amount must be positive")
  private BigDecimal amount;
}