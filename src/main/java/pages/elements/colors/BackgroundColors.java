package pages.elements.colors;

public enum BackgroundColors {
    GREEN("rgb(81, 152, 57)");
    private String rgb;
    BackgroundColors(String rgb){
        this.rgb = rgb;
    }
    public String getRgb(){
        return rgb;
    }
}
