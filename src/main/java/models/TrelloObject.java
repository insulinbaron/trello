package models;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Trello object.
 * Объектом трелло могут быть "доска трелло", "карточка трелло", "колонка трелло" и тд.
 */
@Data
@NoArgsConstructor
public class TrelloObject {
    //TO DO переделать DTO
    // add readme
    // add logger
    // add jenkins file
    // add screen
    /**
     * name - имя трелло объекта.
     * id - id трелло объекта.
     * idBoard - id доски трелло на которой располагается трелло объект.
     * idList - id колонки на которой располагается трелло объект.
     * idChecklist - id контрольного списка на котором располагается трелло объект.
     * idCard - id карточки на которой располагается трелло объект.
     */
    private String name;
    private String id;
    private String idBoard;
    private String idList;
    private String idChecklist;
    private String idCard;
}
