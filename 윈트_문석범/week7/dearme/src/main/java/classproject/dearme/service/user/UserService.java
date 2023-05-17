package classproject.dearme.service.user;

import static classproject.dearme.dto.user.UserInfoDto.toDto;

import classproject.dearme.domain.uploadfile.UploadFile;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.file.UploadFileDto;
import classproject.dearme.dto.user.UserCreateDto;
import classproject.dearme.dto.user.UserInfoDto;
import classproject.dearme.dto.user.UserLogin;
import classproject.dearme.dto.user.UserUpdateDto;
import classproject.dearme.repository.user.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//회원 로직
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	//회원등록
	@Transactional
	public User saveUser(UserCreateDto userCreateDto) {
		User user = User.getUser(userCreateDto);
		userRepository.save(user);
		return user;
	}

	//회원 로그인
	@Transactional
	public UserInfoDto login(UserLogin userLogin) {

		User findUser =  userRepository.findByUsername(userLogin.getUsername());
		log.info("User {}",userLogin.getPassword());
		log.info("findUser {}",findUser.getPassword());
		if (findUser.getPassword().equals(userLogin.getPassword()) ) {
			return toDto(findUser);
		} else {
			return null;
		}
	}

	//회원 마이페이지 정보 조회
	@Transactional
	public UserInfoDto getUserInfo(String name) {
		User findUser =  userRepository.findByUsername(name);
		return toDto(findUser);
	}


	//회원 정보 업데이트
	@Transactional
	public UserInfoDto updateUser(UserUpdateDto updateDto, UploadFileDto uploadFileDto, List<UploadFileDto> uploadFileDtos) {

		User findUser =  userRepository.findByUsername(updateDto.getUsername());
		log.info("content{}", updateDto.getContent());
		findUser.setContent(updateDto.getContent());
		if (UploadFile.getUploadFile(uploadFileDto) != null) {
			findUser.setImage(UploadFile.getUploadFile(uploadFileDto).getStoreFileName());
		}
		findUser.setAttachFile(UploadFile.getUploadFile(uploadFileDto));
		findUser.setImageFiles(UploadFile.getUploadFileList(uploadFileDtos));
		return toDto(findUser);
	}


}
