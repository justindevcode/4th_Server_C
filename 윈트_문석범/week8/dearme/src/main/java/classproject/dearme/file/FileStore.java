package classproject.dearme.file;

import classproject.dearme.domain.uploadfile.UploadFile;
import classproject.dearme.dto.file.UploadFileDto;
import classproject.dearme.service.file.FileService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileStore {


	private final FileService fileService;

	@Value("${file.dir}")
	private String fileDir;

	public String thisPath = System.getProperty("user.dir");



	public String getFullPath(String fileName) {
		log.info("thisPath {} / filedir {} / fileName {}", thisPath, fileDir, fileName);
		log.info("pathaws {}", thisPath + "/" + fileDir + fileName);
		//절대경로는 배포상태에 따라 바꿔받고 프로젝트 내부의 경로는 상대경로로 사이에 "\\"로 연결
		return thisPath + "/" +  fileDir + fileName;
	}

	public List<UploadFileDto> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
		if (multipartFiles == null) {
			return null;
		}
		List<UploadFileDto> storeFileResult = new ArrayList<>();
		for (MultipartFile multipartFile : multipartFiles) {
			if (!multipartFile.isEmpty()) {
				storeFileResult.add(storeFile(multipartFile));
			}
		}
		return storeFileResult;
	}
	@Transactional
	public UploadFileDto storeFile(MultipartFile multipartFile) throws IOException {
		if (multipartFile == null) {
			return null;
		}

		String originalFilename = multipartFile.getOriginalFilename();
		log.info("originalFilename = {}", originalFilename);

		//서버에 저장하는 파일명
		String storeFileName = createStoreFileName(originalFilename);
		log.info("storeFileName = {}", storeFileName);
		log.info("{}", thisPath);
		multipartFile.transferTo(new File(getFullPath(storeFileName)));
		return new UploadFileDto(null,originalFilename, storeFileName);
	}

	private String createStoreFileName(String originalFilename) {
		String uuid = UUID.randomUUID().toString();
		String ext = extractExt(originalFilename);
		return uuid + "." + ext;
	}

	private String extractExt(String originalFilename) {
		int pos = originalFilename.lastIndexOf(".");
		return originalFilename.substring(pos + 1);
	}
}
