package classproject.dearme.service.user;

import static classproject.dearme.dto.user.UserInfoDto.toDto;

import classproject.dearme.domain.uploadfile.UploadFile;
import classproject.dearme.domain.user.Users;
import classproject.dearme.dto.file.UploadFileDto;
import classproject.dearme.dto.user.UserCreateDto;
import classproject.dearme.dto.user.UserInfoDto;
import classproject.dearme.dto.user.UserLogin;
import classproject.dearme.dto.user.UserUpdateDto;
import classproject.dearme.repository.user.UserQueryRepository;
import classproject.dearme.repository.user.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//회원 로직
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UsersRepository usersRepository;
	private final UserQueryRepository userQueryRepository;

	//회원등록
	@Transactional
	public Users saveUser(UserCreateDto userCreateDto) {
		Users users = Users.getUser(userCreateDto);
		usersRepository.save(users);
		return users;
	}

	//회원 로그인
	@Transactional
	public UserInfoDto login(UserLogin userLogin) {

		Users findUsers =  usersRepository.findByUsername(userLogin.getUsername()).orElse(null);
		log.info("Users {}",userLogin.getPassword());
		log.info("findUsers {}", findUsers.getPassword());
		if (findUsers.getPassword().equals(userLogin.getPassword()) ) {
			return toDto(findUsers);
		} else {
			return null;
		}
	}

	//회원 마이페이지 정보 조회
	@Transactional
	public UserInfoDto getUserInfo(String name) {
		Users findUsers =  usersRepository.findByUsername(name).orElse(null);
		return toDto(findUsers);
	}


	//회원 정보 업데이트
	@Transactional
	public UserInfoDto updateUser(UserUpdateDto updateDto, UploadFileDto uploadFileDto, List<UploadFileDto> uploadFileDtos) {

		Users findUsers =  usersRepository.findByUsername(updateDto.getUsername()).orElse(null);
		log.info("content{}", updateDto.getContent());
		findUsers.setContent(updateDto.getContent());
		findUsers.setEmail(updateDto.getEmail());
		findUsers.setPhone(updateDto.getPhone());
		if (uploadFileDto == null) {
			findUsers.setImage(null);
		} else {
			findUsers.setImage(uploadFileDto.getStoreFileName());

		}
		findUsers.setAttachFile(UploadFile.getUploadFile(uploadFileDto));
		findUsers.setImageFiles(UploadFile.getUploadFileList(uploadFileDtos));
		return toDto(findUsers);
	}

	@Transactional
	public void deleteAll() {
		usersRepository.deleteAll();
	}

	@Transactional
	public List<UserInfoDto> getUserSearchAll(String word) {
		List<UserInfoDto> userInfoDtos = new ArrayList<>();
		List<Users> findSearchUsers = userQueryRepository.findSearchAll(word);
		for (Users users : findSearchUsers) {
			userInfoDtos.add(toDto(users));
		}
		return userInfoDtos;
	}



}
