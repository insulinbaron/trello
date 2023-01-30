public enum Paths {
    ID("id"),
    ID_BOARD("idBoard"),
    NAME("name"),
    ID_LIST("idList"),
    IS_UPLOAD("isUpload"),
    DUE("due"),
    DESC("desc"),
    ID_CARD("idCard"),
    ID_CHECKLIST("idChecklist"),
    STATE("state"),
    IMAGE("image");

    private String path;
    Paths(String path){
        this.path = path;
    }
    public String getPath(){
        return path;
    }

}
