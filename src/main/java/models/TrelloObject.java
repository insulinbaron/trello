package models;

import io.restassured.path.json.JsonPath;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import paths.JSONPaths;

/**
 * The type Trello object.
 * Объектом трелло могут быть "доска трелло", "карточка трелло", "колонка трелло" и тд.
 */
public class TrelloObject {
    /**
     * name - имя трелло объекта.
     * id - id трелло объекта.
     * idBoard - id доски трелло на которой располагается трелло объект.
     * idList - id колонки на которой располагается трелло объект.
     * idChecklist - id контрольного списка на котором располагается трелло объект.
     * idCard - id карточки на которой располагается трелло объект.
     */

    private static String getName(JsonPath response){
        return response.getString(JSONPaths.NAME);
    }
    private static String getId(JsonPath response){
        return response.getString(JSONPaths.ID);
    }

    private static String getIdBoard(JsonPath response){
        return response.getString(JSONPaths.ID_BOARD);
    }

    private static String getIdList(JsonPath response){
        return response.getString(JSONPaths.ID_LIST);
    }

    private static String getIdCheckList(JsonPath response){
        return response.getString(JSONPaths.ID_CHECKLIST);
    }

    private static String getIdCard(JsonPath response){
        return response.getString(JSONPaths.ID_CARD);
    }


    @Data
    public static class Board{
        private String id;
        private String name;
        public Board(JsonPath response){
            try {
                id = TrelloObject.getId(response);
                name = TrelloObject.getName(response);
            } catch (Exception e){
                e.printStackTrace();
            }
        }


        @Override
        public String toString(){
            return String.format("Доска: \n id = \"%s\", \n name = \"%s\"", id, name);
        }
    }

    @Data
    public static class List{
        private String id;
        private String name;
        private String idBoard;

        public List(JsonPath response) {
            try {
                id = TrelloObject.getId(response);
                name = TrelloObject.getName(response);
                idBoard = TrelloObject.getIdBoard(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Override
        public String toString(){
            return String.format("Колонка: \n id = \"%s\", \n name = \"%s\", \n idBoard = \"%s\"", id, name, idBoard);
        }
    }

    @Data
    public static class CheckList{
        private String id;
        private String name;
        private String idBoard;
        private String idCard;
        public CheckList(JsonPath response){
            try{
                id = TrelloObject.getId(response);
                name = TrelloObject.getName(response);
                idBoard = TrelloObject.getIdBoard(response);
                idCard = TrelloObject.getIdCard(response);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public String toString(){
            return String.format("Чеклист: \n id = \"%s\", \n name = \"%s\", \n idBoard = \"%s\", \n idCard = \"%s\"", id, name, idBoard, idCard);
        }
    }

    @Data
    public static class Card{
        private String id;
        private String name;
        private String idBoard;
        private String idList;
        public Card(JsonPath response){
            try{
                id = TrelloObject.getId(response);
                name = TrelloObject.getName(response);
                idBoard = TrelloObject.getIdBoard(response);
                idList = TrelloObject.getIdList(response);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public String toString(){
            return String.format("Карточка: \n id = \"%s\", \n name = \"%s\", \n idBoard = \"%s\", idList = \"%s\"", id, name, idBoard, idList);
        }
    }

    @Data
    public static class Attachment{
        private String id;
        private String name;
        public Attachment(JsonPath response){
            try{
                id = TrelloObject.getId(response);
                name = TrelloObject.getName(response);
            } catch (Exception e){
                e.printStackTrace();
            }
        }


        @Override
        public String toString(){
            return String.format("Вложение: \n id = \"%s\", \n name = \"%s\"", id, name);
        }
    }

    @Data
    public static class CheckItem {
        private String id;
        private String name;
        private String idChecklist;
        public CheckItem(JsonPath response){
            try{
                id = TrelloObject.getId(response);
                name = TrelloObject.getName(response);
                idChecklist = TrelloObject.getIdCheckList(response);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public String toString(){
            return String.format("Колонка: \n id = \"%s\", \n name = \"%s\", \n idChecklist = \"%s\"", id, name, idChecklist);
        }
    }
}
