package classproject.dearme.dto.user;

import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "친구정보 DTO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class FriendInfoDto {

	private String UserName;

	private List<String> opponent;


}
