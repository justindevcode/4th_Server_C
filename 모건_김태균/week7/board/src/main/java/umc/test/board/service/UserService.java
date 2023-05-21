package umc.test.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.test.board.domain.User;
import umc.test.board.dto.user.UserCreateRequestDto;
import umc.test.board.dto.user.UserListResponseDto;
import umc.test.board.dto.user.UserResponseDto;
import umc.test.board.dto.user.UserUpdateRequestDto;
import umc.test.board.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserCreateRequestDto requestDto){
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자는 없습니다."));
        user.update(requestDto.getName(), requestDto.getAge());

        return id;
    }

    @Transactional
    public UserResponseDto searchById(Long id){
        User user = userRepository. findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
        return new UserResponseDto(user);
    }

    @Transactional
    public List<UserListResponseDto> searchAllDesc(){
        return userRepository.findAll().stream()
                .map(UserListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자는 존재하지 않습니다."));
        userRepository.delete(user);
    }
}
