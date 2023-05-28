package classproject.dearme.service.user;


import classproject.dearme.domain.user.Friend;
import classproject.dearme.domain.user.Users;
import classproject.dearme.dto.user.FriendInfoDto;
import classproject.dearme.repository.user.FriendRepository;
import classproject.dearme.repository.user.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendService {

	private final FriendRepository friendRepository;
	private final UsersRepository usersRepository;

	@Transactional
	public FriendInfoDto getHisFollower(String userName) {
		List<Friend> friends = friendRepository.findByFollower_Username(userName);
		FriendInfoDto friendInfoDto = new FriendInfoDto();
		friendInfoDto.setUserName(userName);
		List<String> ops = new ArrayList<>();
		if (friends == null) {
			return friendInfoDto;
		}
		for (Friend friend : friends) {
			ops.add(friend.getFollowee().getUsername());
		}
		friendInfoDto.setOpponent(ops);
		return friendInfoDto;
	}

	@Transactional
	public FriendInfoDto getHisFollowee(String userName) {
		List<Friend> friends = friendRepository.findByFollowee_Username(userName);
		FriendInfoDto friendInfoDto = new FriendInfoDto();
		friendInfoDto.setUserName(userName);
		List<String> ops = new ArrayList<>();
		if (friends == null) {
			return friendInfoDto;
		}
		for (Friend friend : friends) {
			ops.add(friend.getFollower().getUsername());
		}
		friendInfoDto.setOpponent(ops);
		return friendInfoDto;
	}

	@Transactional
	public FriendInfoDto getFriendYet(String userName) {
		List<Friend> friendsFollower = friendRepository.findByFollower_Username(userName);
		List<Users> friendsFollowersUsers = new ArrayList<>();
		for (Friend friend : friendsFollower) {
			friendsFollowersUsers.add(friend.getFollowee());
		}
		List<Friend> friendsFollowee = friendRepository.findByFollowee_Username(userName);
		List<Friend> result = friendsFollowee.stream()
			.filter(element -> !friendsFollowersUsers.contains(element.getFollower()))
			.collect(Collectors.toList());
		FriendInfoDto friendInfoDto = new FriendInfoDto();
		friendInfoDto.setUserName(userName);
		List<String> ops = new ArrayList<>();
		if (result.isEmpty()) {
			return friendInfoDto;
		}
		for (Friend friend : result) {
			log.info("not null result {},{}", friend,friend.getFollower().getUsername());
			ops.add(friend.getFollower().getUsername());
		}
		friendInfoDto.setOpponent(ops);
		return friendInfoDto;
	}

	@Transactional
	public FriendInfoDto crateFriend(String userName, String opName) {
		Users users = usersRepository.findByUsername(userName).orElse(null);
		Users opUsers = usersRepository.findByUsername(opName).orElse(null);
		if (users == null || opUsers == null) {
			throw  new IllegalArgumentException("not found users");
		}
		Friend friend = Friend.getFriendList(users, opUsers);
		friendRepository.save(friend);
//		users.setFollowing(friend);
//		opUsers.setFollowers(friend);
		List<String> myList = new ArrayList<>();
		myList.add(opName);
		return new FriendInfoDto(userName, myList);
	}

	@Transactional
	public String deleteFriend(String userName, String opName) {
		Friend friend = friendRepository.findByFollower_UsernameAndFollowee_Username(userName, opName);
		friendRepository.delete(friend);
		return "Success " + userName + "'s friend";
	}

	@Transactional
	public String deleteAll() {
		friendRepository.deleteAll();
		return "delete All friend";
	}

}
