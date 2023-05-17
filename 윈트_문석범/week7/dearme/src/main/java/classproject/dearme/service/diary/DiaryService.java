package classproject.dearme.service.diary;

import static classproject.dearme.dto.user.UserInfoDto.toDto;

import classproject.dearme.domain.diary.Diary;
import classproject.dearme.domain.timecapsule.TimeCapsule;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.diary.DiaryInfoDto;
import classproject.dearme.dto.timecapsule.TimeCapsuleInfoDto;
import classproject.dearme.repository.diary.DiaryRepository;
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
public class DiaryService {

	private final DiaryRepository diaryRepository;
	private final UserRepository userRepository;

	@Transactional
	public DiaryInfoDto save(DiaryInfoDto diaryInfoDto) {
		User user = userRepository.findByUsername(diaryInfoDto.getUsername());
		Diary diary = Diary.getDiary(diaryInfoDto, user);
		diaryRepository.save(diary);
		return DiaryInfoDto.toDto(diary);
	}

	@Transactional
	public DiaryInfoDto update(DiaryInfoDto diaryInfoDto) {
		Diary findDiary = diaryRepository.getReferenceById(diaryInfoDto.getId());
		findDiary.setCoordinateX(diaryInfoDto.getCoordinateX());
		findDiary.setCoordinateY(diaryInfoDto.getCoordinateY());
		findDiary.setImageType(diaryInfoDto.getImageType());
		return DiaryInfoDto.toDto(findDiary);
	}

	@Transactional
	public List<DiaryInfoDto> findAll(String username) {
		User user = userRepository.findByUsername(username);
		List<Diary> resultDomain = diaryRepository.findByUser(user);
		List<DiaryInfoDto> result = new ArrayList<>();
		for (Diary diary : resultDomain) {
			result.add(DiaryInfoDto.toDto(diary));
		}
		return result;

	}

}
