package carrot.backend.domain.post.entity;

import lombok.Getter;

@Getter
public enum ItemCategory {
    ELECTRONICS("디지털기기"),
    BOOK("도서"),
    TICKET("티켓/교환권"),
    ETC("기타 중고물품");

    private final String name;

    ItemCategory(String name) {
        this.name = name;
    }

    public static ItemCategory nameOf(String name) {
        for (ItemCategory itemCategory : ItemCategory.values()) {
            if (itemCategory.getName().equals(name)) {
                return itemCategory;
            }
        }
        return null;
    }
}
