package classproject.dearme.controller.user;

import classproject.dearme.dto.file.UploadFileDto;
import classproject.dearme.dto.user.UserCreateDto;
import classproject.dearme.dto.user.UserInfoDto;
import classproject.dearme.dto.user.UserLogin;
import classproject.dearme.dto.user.UserUpdateDto;
import classproject.dearme.file.FileStore;
import classproject.dearme.response.Response;
import classproject.dearme.service.file.FileService;
import classproject.dearme.service.user.UserService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

@Slf4j
@Api(tags = "유저")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final FileStore fileStore;

	private final FileService fileService;

	//회원가입


	@ApiOperation(
		value = "유저회원가입",
		notes = "회원가입시 사용하는 API")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Response saveUser(@RequestBody UserCreateDto userCreateDto) {
		return Response.success(userService.saveUser(userCreateDto));
	}

	//회원정보 조회
	@ApiOperation(
		value = "유저정보",
		notes = "마이페이지에서 데이터 불러오는 API")
	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)

	public Response getUserInfo(@PathVariable String username) {
		return Response.success(userService.getUserInfo(username));
	}

	//회원 정보수정 소개,사진
	@ApiOperation(
		value = "유저정보수정",
		notes = "마이페이지에서 유저정보를 수정하는 API")
	@PatchMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Response updateUserInfo(@ModelAttribute UserUpdateDto userUpdateDto) throws IOException {
		UploadFileDto attachFile = fileStore.storeFile(userUpdateDto.getAttachFile());
		List<UploadFileDto> storeFiles = fileStore.storeFiles(userUpdateDto.getImageFiles());
		UserInfoDto result = userService.updateUser(userUpdateDto, attachFile, storeFiles);
		fileService.save(attachFile);
		if (storeFiles != null) {
			for (UploadFileDto storeFile : storeFiles) {
				fileService.save(storeFile);
			}
		}
		return Response.success(result);
	}

	//회원 로그인
	@ApiOperation(
		value = "유저 로그인",
		notes = "로그인할때 사용하는 API")
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public Response login(@RequestBody UserLogin userLogin) {
		return Response.success(userService.login(userLogin));
	}

	//이미지 소스보기
	@ResponseBody
	@GetMapping("/images/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
		return new UrlResource("file:" + fileStore.getFullPath(filename));
	}

	//파일저장
	@GetMapping("/attach/{uploadFileId}")
	public ResponseEntity<Resource> downloadAttach(@PathVariable Long uploadFileId)
		throws MalformedURLException {
		UploadFileDto uploadFileDto = fileService.findUploadFile(uploadFileId);
		String storeFileName = uploadFileDto.getStoreFileName();
		String uploadFileName = uploadFileDto.getUploadFileName();

		UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

		log.info("uploadFileName={}", uploadFileName);

		String encodedUploadFileName = UriUtils.encode(uploadFileName,  StandardCharsets.UTF_8);
		String contentDisposition = "attachment; filename=\"" +
			encodedUploadFileName + "\"";
		return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
			.body(resource);
	}


}
