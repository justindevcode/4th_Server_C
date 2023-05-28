package classproject.dearme.domain.schedule;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QToDo is a Querydsl query type for ToDo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QToDo extends EntityPathBase<ToDo> {

    private static final long serialVersionUID = 1937744362L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QToDo toDo = new QToDo("toDo");

    public final classproject.dearme.domain.base.QBaseEntity _super = new classproject.dearme.domain.base.QBaseEntity(this);

    public final BooleanPath checkTodo = createBoolean("checkTodo");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath endTime = createString("endTime");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModified = _super.lastModified;

    public final QSchedule schedule;

    public final StringPath startTime = createString("startTime");

    public QToDo(String variable) {
        this(ToDo.class, forVariable(variable), INITS);
    }

    public QToDo(Path<? extends ToDo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QToDo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QToDo(PathMetadata metadata, PathInits inits) {
        this(ToDo.class, metadata, inits);
    }

    public QToDo(Class<? extends ToDo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.schedule = inits.isInitialized("schedule") ? new QSchedule(forProperty("schedule"), inits.get("schedule")) : null;
    }

}

