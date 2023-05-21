package classproject.dearme.domain.uploadfile;

import classproject.dearme.domain.base.BaseEntity;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.file.UploadFileDto;
import classproject.dearme.dto.user.UserCreateDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Getter
@Builder
@Setter
public class UploadFile extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "upload_file_id")
	private Long id; // PK로 활용하기 위한 id값

	private String uploadFileName;

	
	private String storeFileName;

	@ManyToOne
	private User userImages;

	@OneToOne(mappedBy = "attachFile")
	private User userImage;

	public UploadFile(String uploadFileName, String storeFileName) {
		this.uploadFileName = uploadFileName;
		this.storeFileName = storeFileName;
	}

	public static UploadFile getUploadFile(UploadFileDto uploadFileDto) {
		if (uploadFileDto == null) {
			return null;
		}
		return UploadFile.builder()
			.uploadFileName(uploadFileDto.getUploadFileName())
			.storeFileName(uploadFileDto.getStoreFileName())
			.build();
	}

	public static List<UploadFile> getUploadFileList(List<UploadFileDto> uploadFileDtoList) {
		if(uploadFileDtoList == null){
			return null;
		}
		List<UploadFile> entityList = new ArrayList<>();
		for (UploadFileDto uploadFileDto : uploadFileDtoList) {
			entityList.add(getUploadFile(uploadFileDto));
		}
		return entityList;
	}
}
