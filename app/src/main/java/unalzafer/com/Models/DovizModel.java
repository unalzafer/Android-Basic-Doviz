package unalzafer.com.Models;

public class DovizModel {
    private String Code;
    private String Unit;
    private String Name;
    private String Arrow;
    private String Buying;
    private String Selling;
    private String Percentage;
    private String UpdateDate;
    private String DetailLink;
    private String ImageLink;

    public DovizModel(String code, String unit, String name, String arrow, String buying, String selling, String percentage, String updateDate, String detailLink, String imageLink) {
        Code = code;
        Unit = unit;
        Name = name;
        Arrow = arrow;
        Buying = buying;
        Selling = selling;
        Percentage = percentage;
        UpdateDate = updateDate;
        DetailLink = detailLink;
        ImageLink = imageLink;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getArrow() {
        return Arrow;
    }

    public void setArrow(String arrow) {
        Arrow = arrow;
    }

    public String getBuying() {
        return Buying;
    }

    public void setBuying(String buying) {
        Buying = buying;
    }

    public String getSelling() {
        return Selling;
    }

    public void setSelling(String selling) {
        Selling = selling;
    }

    public String getPercentage() {
        return Percentage;
    }

    public void setPercentage(String percentage) {
        Percentage = percentage;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }

    public String getDetailLink() {
        return DetailLink;
    }

    public void setDetailLink(String detailLink) {
        DetailLink = detailLink;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }
}
