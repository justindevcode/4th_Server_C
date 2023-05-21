package classproject.dearme.repository.user;

import classproject.dearme.domain.user.QUser;
import classproject.dearme.domain.user.User;
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

	public List<User> findSearchAll(String word) {
		return query.select(QUser.user)
			.from(QUser.user)
			.where(likeUserName(word))
			.fetch();
	}

	private BooleanExpression likeUserName(String userName) {
		if (StringUtils.hasText(userName)) {
			return QUser.user.username.like("%" + userName + "%");
		}
		return null;
	}
}
