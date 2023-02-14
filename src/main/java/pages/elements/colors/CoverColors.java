package pages.elements.colors;

/**
 * The enum Cover colors.
 * Cover colors цвета обложки для изменения обложки трелло карточки.
 */
public enum CoverColors {
    /**
     * Green  color.
     */
    GREEN("rgba(123, 200, 108, 1)"),
    /**
     * Yellow colors.
     */
    YELLOW("rgba(245, 221, 41, 1)"),
    /**
     * Orange cover colors.
     */
    ORANGE("rgba(255, 175, 63, 1)"),
    /**
     * Red cover colors.
     */
    RED("rgba(239, 117, 100, 1)"),
    /**
     * Purple cover colors.
     */
    PURPLE("rgba(205, 141, 229, 1)"),
    /**
     * Blue cover colors.
     */
    BLUE("rgba(91, 164, 207, 1)"),
    /**
     * Light blue cover colors.
     */
    LIGHT_BLUE("rgba(41, 204, 229, 1)"),
    /**
     * Mint cover colors.
     */
    MINT("rgba(109, 236, 169, 1)"),
    /**
     * Pink cover colors.
     */
    PINK("rgba(255, 142, 212, 1)"),
    /**
     * Black cover colors.
     */
    BLACK("rgba(23, 43, 77, 1)");
    /**
     * rgba - строка которая содержится в атрибуте class
     * и используется для идентификации цвета обложки на трелло карточке.
     */
    private String rgba;


    CoverColors(String rgba) {
        this.rgba = rgba;
    }

    /**
     * Get rgba string.
     *
     * @return Строка идентифицирующая цвет обложки на карточке трелло.
     */
    public String getRgba(){
        return rgba;
    }
}
