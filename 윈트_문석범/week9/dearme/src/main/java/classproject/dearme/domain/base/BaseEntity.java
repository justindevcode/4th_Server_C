package classproject.dearme.domain.base;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Entity가 생성된 시기, 수정된 시기가 필요한 로직들이 존재하기 때문에 한꺼번에 이를 관리하기 위한 BaseEntity 클래스 설계
// BaseEntity를 상속받음으로써, Entity 생성, 수정과 관련된 정보를 사용할 수 있음.
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity {

	@CreatedDate
	@Column(updatable = false) // Entity가 생성된 시간을 수정하면 안되기 때문에 @Column 어노테이션을 통하여 수정 차단
	private LocalDateTime createdDate; // Entity가 처음으로 생성된 시간

	@LastModifiedDate
	private LocalDateTime lastModified; // Entity가 마지막으로 수정된 시간
}