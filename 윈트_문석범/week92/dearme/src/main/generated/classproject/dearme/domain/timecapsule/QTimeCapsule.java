package classproject.dearme.domain.timecapsule;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTimeCapsule is a Querydsl query type for TimeCapsule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimeCapsule extends EntityPathBase<TimeCapsule> {

    private static final long serialVersionUID = -1417843585L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTimeCapsule timeCapsule = new QTimeCapsule("timeCapsule");

    public final classproject.dearme.domain.base.QBaseEntity _super = new classproject.dearme.domain.base.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModified = _super.lastModified;

    public final StringPath nextDay = createString("nextDay");

    public final StringPath toDay = createString("toDay");

    public final classproject.dearme.domain.user.QUsers users;

    public QTimeCapsule(String variable) {
        this(TimeCapsule.class, forVariable(variable), INITS);
    }

    public QTimeCapsule(Path<? extends TimeCapsule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTimeCapsule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTimeCapsule(PathMetadata metadata, PathInits inits) {
        this(TimeCapsule.class, metadata, inits);
    }

    public QTimeCapsule(Class<? extends TimeCapsule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new classproject.dearme.domain.user.QUsers(forProperty("users"), inits.get("users")) : null;
    }

}

