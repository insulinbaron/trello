package pages.elements.colors;

/**
 * The enum Background colors.
 * Background colors это цвета для изменения фона трелло доски.
 */
public enum BackgroundColors {

    /**
     * Green background colors.
     */
    GREEN("rgb(81, 152, 57)"),
    /**
     * Blue background colors.
     */
    BLUE("rgb(0, 121, 191)"),
    /**
     * Orange background colors.
     */
    ORANGE("rgb(210, 144, 52)"),
    /**
     * Red background colors.
     */
    RED("rgb(176, 70, 50)"),
    /**
     * Purple background colors.
     */
    PURPLE("rgb(137, 96, 158)"),
    /**
     * Pink background colors.
     */
    PINK("rgb(205, 90, 145)");
    /**
     * rgb - строка, которая находится в атрибуте style у текущего веб элемента
     * и используется для идентификации цвета фона доски.
     */
    private String rgb;
    BackgroundColors(String rgb){
        this.rgb = rgb;
    }

    /**
     * Get rgb string.
     *
     * @return Строка идентифицирующая цвет фона трелло доски.
     */
    public String getRgb(){
        return rgb;
    }
}
