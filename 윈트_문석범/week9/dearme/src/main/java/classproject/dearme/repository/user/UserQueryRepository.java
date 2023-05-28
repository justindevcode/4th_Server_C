package classproject.dearme.repository.user;

import classproject.dearme.domain.user.QUsers;
import classproject.dearme.domain.user.Users;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class UserQueryRepository {

	private final JPAQueryFactory query;

	public UserQueryRepository(EntityManager entityManager) {
		this.query = new JPAQueryFactory(entityManager);
	}

	public List<Users> findSearchAll(String word) {
		return query.select(QUsers.users)
			.from(QUsers.users)
			.where(likeUserName(word))
			.fetch();
	}

	private BooleanExpression likeUserName(String userName) {
		if (StringUtils.hasText(userName)) {
			return QUsers.users.username.like("%" + userName + "%");
		}
		return null;
	}
}
