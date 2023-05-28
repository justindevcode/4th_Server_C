package classproject.dearme.domain.timeschedule;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDaySchedule is a Querydsl query type for DaySchedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDaySchedule extends EntityPathBase<DaySchedule> {

    private static final long serialVersionUID = 921541916L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDaySchedule daySchedule = new QDaySchedule("daySchedule");

    public final classproject.dearme.domain.base.QBaseEntity _super = new classproject.dearme.domain.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath date = createString("date");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModified = _super.lastModified;

    public final StringPath today = createString("today");

    public final ListPath<ToDoSchedule, QToDoSchedule> toDoSchedules = this.<ToDoSchedule, QToDoSchedule>createList("toDoSchedules", ToDoSchedule.class, QToDoSchedule.class, PathInits.DIRECT2);

    public final StringPath tomorrow = createString("tomorrow");

    public final classproject.dearme.domain.user.QUsers users;

    public QDaySchedule(String variable) {
        this(DaySchedule.class, forVariable(variable), INITS);
    }

    public QDaySchedule(Path<? extends DaySchedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDaySchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDaySchedule(PathMetadata metadata, PathInits inits) {
        this(DaySchedule.class, metadata, inits);
    }

    public QDaySchedule(Class<? extends DaySchedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new classproject.dearme.domain.user.QUsers(forProperty("users"), inits.get("users")) : null;
    }

}

