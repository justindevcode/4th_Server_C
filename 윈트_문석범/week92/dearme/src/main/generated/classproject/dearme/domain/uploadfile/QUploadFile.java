package classproject.dearme.domain.uploadfile;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUploadFile is a Querydsl query type for UploadFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUploadFile extends EntityPathBase<UploadFile> {

    private static final long serialVersionUID = 148766267L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUploadFile uploadFile = new QUploadFile("uploadFile");

    public final classproject.dearme.domain.base.QBaseEntity _super = new classproject.dearme.domain.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModified = _super.lastModified;

    public final StringPath storeFileName = createString("storeFileName");

    public final StringPath uploadFileName = createString("uploadFileName");

    public final classproject.dearme.domain.user.QUsers usersImage;

    public final classproject.dearme.domain.user.QUsers usersImages;

    public QUploadFile(String variable) {
        this(UploadFile.class, forVariable(variable), INITS);
    }

    public QUploadFile(Path<? extends UploadFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUploadFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUploadFile(PathMetadata metadata, PathInits inits) {
        this(UploadFile.class, metadata, inits);
    }

    public QUploadFile(Class<? extends UploadFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.usersImage = inits.isInitialized("usersImage") ? new classproject.dearme.domain.user.QUsers(forProperty("usersImage"), inits.get("usersImage")) : null;
        this.usersImages = inits.isInitialized("usersImages") ? new classproject.dearme.domain.user.QUsers(forProperty("usersImages"), inits.get("usersImages")) : null;
    }

}

