package classproject.dearme.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = 858338488L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsers users = new QUsers("users");

    public final classproject.dearme.domain.base.QBaseEntity _super = new classproject.dearme.domain.base.QBaseEntity(this);

    public final classproject.dearme.domain.uploadfile.QUploadFile attachFile;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final ListPath<classproject.dearme.domain.uploadfile.UploadFile, classproject.dearme.domain.uploadfile.QUploadFile> imageFiles = this.<classproject.dearme.domain.uploadfile.UploadFile, classproject.dearme.domain.uploadfile.QUploadFile>createList("imageFiles", classproject.dearme.domain.uploadfile.UploadFile.class, classproject.dearme.domain.uploadfile.QUploadFile.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModified = _super.lastModified;

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final ListPath<String, StringPath> roles = this.<String, StringPath>createList("roles", String.class, StringPath.class, PathInits.DIRECT2);

    public final ListPath<classproject.dearme.domain.schedule.Schedule, classproject.dearme.domain.schedule.QSchedule> schedules = this.<classproject.dearme.domain.schedule.Schedule, classproject.dearme.domain.schedule.QSchedule>createList("schedules", classproject.dearme.domain.schedule.Schedule.class, classproject.dearme.domain.schedule.QSchedule.class, PathInits.DIRECT2);

    public final ListPath<classproject.dearme.domain.timecapsule.TimeCapsule, classproject.dearme.domain.timecapsule.QTimeCapsule> timeCapsules = this.<classproject.dearme.domain.timecapsule.TimeCapsule, classproject.dearme.domain.timecapsule.QTimeCapsule>createList("timeCapsules", classproject.dearme.domain.timecapsule.TimeCapsule.class, classproject.dearme.domain.timecapsule.QTimeCapsule.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QUsers(String variable) {
        this(Users.class, forVariable(variable), INITS);
    }

    public QUsers(Path<? extends Users> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUsers(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUsers(PathMetadata metadata, PathInits inits) {
        this(Users.class, metadata, inits);
    }

    public QUsers(Class<? extends Users> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attachFile = inits.isInitialized("attachFile") ? new classproject.dearme.domain.uploadfile.QUploadFile(forProperty("attachFile"), inits.get("attachFile")) : null;
    }

}

