package classproject.dearme.dto.user;

import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@ApiModel(value = "유저 정보 변경 DTO username=유저이름 content=자기소개 image=사진(현재는텍스트)")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class UserUpdateDto {

	private String username;

	private String content;

	private String image;

	private MultipartFile attachFile;

	private List<MultipartFile> imageFiles;

	private String email;

	private String phone;
}
