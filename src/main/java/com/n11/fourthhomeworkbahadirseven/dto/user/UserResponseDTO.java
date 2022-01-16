package com.n11.fourthhomeworkbahadirseven.dto.user;

import com.n11.fourthhomeworkbahadirseven.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDTO {
	private Long id;
	private String name;
	private String email;
	private String phone;

	public static UserResponseDTO fromUser(User user) {
		return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone());
	}
}
