package pages.elements.colors;

/**
 * The enum Cover colors.
 * Cover colors цвета обложки для изменения обложки трелло карточки.
 */
public enum CoverColors {
    /**
     * Green  color.
     */
    GREEN("rgba(123, 200, 108, 1)",
            "green",
            "Зеленый"),
    /**
     * Yellow colors.
     */
    YELLOW("rgba(245, 221, 41, 1)",
            "yellow",
            "Желтый"),
    /**
     * Orange cover colors.
     */
    ORANGE("rgba(255, 175, 63, 1)",
            "orange",
            "Оранжевый"),
    /**
     * Red cover colors.
     */
    RED("rgba(239, 117, 100, 1)",
            "red",
            "Красный"),
    /**
     * Purple cover colors.
     */
    PURPLE("rgba(205, 141, 229, 1)",
            "purple",
            "Фиолетовый"),
    /**
     * Blue cover colors.
     */
    BLUE("rgba(91, 164, 207, 1)",
            "blue",
            "Синий"),
    /**
     * Light blue cover colors.
     */
    SKY("rgba(41, 204, 229, 1)",
            "sky",
            "Голубой"),
    /**
     * Mint cover colors.
     */
    LIME("rgba(109, 236, 169, 1)",
            "lime",
            "Мятный"),
    /**
     * Pink cover colors.
     */
    PINK("rgba(255, 142, 212, 1)",
            "pink,",
            "Розовый"),
    /**
     * Black cover colors.
     */
    BLACK("rgba(23, 43, 77, 1)",
            "black",
            "Черный");
    /**
     * rgba - строка которая содержится в атрибуте class
     * и используется для идентификации цвета обложки на трелло карточке.
     */
    private String rgba;
    private String description;
    private String classValue;


    CoverColors(String rgba, String classValue, String description) {
        this.rgba = rgba;
        this.classValue = classValue;
        this.description = description;
    }

    /**
     * Get rgba string.
     *
     * @return Строка идентифицирующая цвет обложки на карточке трелло.
     */
    public String getRgba(){
        return rgba;
    }

    public String getDescription() {
        return description;
    }
    public String getClassValue() {return classValue;}
}
