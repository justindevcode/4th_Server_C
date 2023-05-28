package classproject.dearme.domain.timeschedule;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QToDoSchedule is a Querydsl query type for ToDoSchedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QToDoSchedule extends EntityPathBase<ToDoSchedule> {

    private static final long serialVersionUID = 1765601140L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QToDoSchedule toDoSchedule = new QToDoSchedule("toDoSchedule");

    public final classproject.dearme.domain.base.QBaseEntity _super = new classproject.dearme.domain.base.QBaseEntity(this);

    public final BooleanPath checkTodo = createBoolean("checkTodo");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QDaySchedule daySchedule;

    public final StringPath endTime = createString("endTime");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModified = _super.lastModified;

    public final StringPath startTime = createString("startTime");

    public QToDoSchedule(String variable) {
        this(ToDoSchedule.class, forVariable(variable), INITS);
    }

    public QToDoSchedule(Path<? extends ToDoSchedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QToDoSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QToDoSchedule(PathMetadata metadata, PathInits inits) {
        this(ToDoSchedule.class, metadata, inits);
    }

    public QToDoSchedule(Class<? extends ToDoSchedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.daySchedule = inits.isInitialized("daySchedule") ? new QDaySchedule(forProperty("daySchedule"), inits.get("daySchedule")) : null;
    }

}

