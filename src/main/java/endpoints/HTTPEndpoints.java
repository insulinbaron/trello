package endpoints;


/**
 * The type Http endpoints.
 * Класс хранит константы конечных точек для http запросов
 */
public final class HTTPEndpoints {
    /**
     * The constant BOARD_DEFAULT.
     */
    public static final String BOARD_DEFAULT = "1/boards";
    /**
     * The constant LISTS.
     */
    public static final String LISTS = "1/lists";
    /**
     * The constant LIST_PATH.
     */
    public static final String LIST_PATH = "1/lists/{id}";
    /**
     * The constant CARD_DEFAULT.
     */
    public static final String CARD_DEFAULT = "1/cards";
    /**
     * The constant CARD_PATH.
     */
    public static final String CARD_PATH = "1/cards/{id}/";
    /**
     * The constant ATTACHMENT_TO_CARD.
     */
    public static final String ATTACHMENT_TO_CARD = "1/cards/{id}/attachments";
    /**
     * The constant CHECKLIST_ON_CARD.
     */
    public static final String CHECKLIST_ON_CARD = "1/cards/{id}/checklists";
    /**
     * The constant CHECK_ITEM.
     */
    public static final String CHECK_ITEM = "1/checklists/{id}/checkItems";
    /**
     * The constant CHECKITEM_ON_CARD.
     */
    public static final String CHECKITEM_ON_CARD = "1/cards/{id}/checkItem/{idCheckItem}/";
    /**
     * The constant CARD_STICKER.
     */
    public static final String CARD_STICKER = "1/cards/{id}/stickers";
}
