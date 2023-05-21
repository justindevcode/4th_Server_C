package classproject.dearme.service.timecapsule;

import classproject.dearme.domain.timecapsule.TimeCapsule;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.timecapsule.TimeCapsuleInfoDto;
import classproject.dearme.repository.timecapsule.TimeCapsuleRepository;
import classproject.dearme.repository.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeCapsuleService {

	private final TimeCapsuleRepository timeCapsuleRepository;
	private final UserRepository userRepository;

	@Transactional
	public TimeCapsuleInfoDto save(TimeCapsuleInfoDto timeCapsuleInfoDto) {
		User user = userRepository.findByUsername(timeCapsuleInfoDto.getUserName());
		TimeCapsule timeCapsule = TimeCapsule.getTimeCapsule(timeCapsuleInfoDto, user);
		timeCapsuleRepository.save(timeCapsule);
		return TimeCapsuleInfoDto.toDto(timeCapsule);
	}

	@Transactional
	public List<TimeCapsuleInfoDto> findAll(String username) {
		User user = userRepository.findByUsername(username);
		List<TimeCapsule> resultDomain = timeCapsuleRepository.findByUser(user);
		List<TimeCapsuleInfoDto> result = new ArrayList<>();
		for (TimeCapsule timeCapsule : resultDomain) {
			result.add(TimeCapsuleInfoDto.toDto(timeCapsule));
		}
		return result;

	}

	@Transactional
	public String delete(Long id) {
		timeCapsuleRepository.deleteById(id);
		return id + "deleteSuccess";
	}
}
