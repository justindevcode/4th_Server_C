package carrot.backend.domain.post.entity;

import lombok.Getter;

@Getter
public enum Status {
    SALE("판매중"),
    COMPLETE("완료");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public static Status nameOf(String name) {
        for (Status status : Status.values()) {
            if (status.getName().equals(name)) {
                return status;
            }
        }
        return null;
    }
}
