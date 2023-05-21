package classproject.dearme.repository.file;

import classproject.dearme.domain.uploadfile.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<UploadFile, Long> {

}
