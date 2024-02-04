package com.cos.person.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateReqDto {

  @NotBlank(message = "비밀번호를 입력하세요.")
  private String password;
  private String phone;
}
