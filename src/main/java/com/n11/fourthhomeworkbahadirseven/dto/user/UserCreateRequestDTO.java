package com.n11.fourthhomeworkbahadirseven.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreateRequestDTO {
	private String name;
	private String email;
	private String phone;
}
