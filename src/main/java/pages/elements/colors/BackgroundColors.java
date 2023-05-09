package pages.elements.colors;

/**
 * The enum Background colors.
 * Background colors это цвета для изменения фона трелло доски.
 */
public enum BackgroundColors {

    /**
     * Green background colors.
     */
    GREEN("rgb(81, 152, 57)",
            "Зеленый"),
    /**
     * Blue background colors.
     */
    BLUE("rgb(0, 121, 191)",
            "Синий"),
    /**
     * Orange background colors.
     */
    ORANGE("rgb(210, 144, 52)",
            "Оранжевый"),
    /**
     * Red background colors.
     */
    RED("rgb(176, 70, 50)",
            "Красный"),
    /**
     * Purple background colors.
     */
    PURPLE("rgb(137, 96, 158)",
            "Фиолетовый"),
    /**
     * Pink background colors.
     */
    PINK("rgb(205, 90, 145)",
            "Розовый");
    /**
     * rgb - строка, которая находится в атрибуте style у текущего веб элемента
     * и используется для идентификации цвета фона доски.
     */
    private String rgb;
    private String description;
    BackgroundColors(String rgb, String description){
        this.rgb = rgb;
        this.description = description;
    }

    /**
     * Get rgb string.
     *
     * @return Строка идентифицирующая цвет фона трелло доски.
     */
    public String getRgb(){
        return rgb;
    }

    public String getDescription(){
        return description;
    }
}
