package classproject.dearme.dto.user;

import classproject.dearme.domain.user.Users;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "유저정보 DTO content=자기소개 image=image=사진(현재는텍스트)")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserInfoDto {

	private String username;

	private String email;

	private String phone;

	private String content;

	private String image;


	public static UserInfoDto toDto(Users users) {
		return UserInfoDto.builder()
			.username(users.getUsername())
			.email(users.getEmail())
			.phone(users.getPhone())
			.content(users.getContent())
			.image(users.getImage())
			.build();
	}

}
