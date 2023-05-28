package classproject.dearme.repository.user;

import classproject.dearme.domain.user.Friend;
import classproject.dearme.dto.user.FriendInfoDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {

	List<Friend> findByFollower_Username(String username);
	List<Friend> findByFollowee_Username(String username);

	Friend findByFollower_UsernameAndFollowee_Username(String username, String op);

}
