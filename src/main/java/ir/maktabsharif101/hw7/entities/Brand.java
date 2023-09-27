package ir.maktabsharif101.hw7.entities;
@SuppressWarnings("unused")
public class Brand {
    private Integer id;
    private String brandName;
    private String website;
    private String description;
    private Integer[] shareHolderIds;

    public Brand() {
    }

    public Brand(Integer id, String brandName, String website, String description, Integer[] shareHolderIds) {
        this.id = id;
        this.brandName = brandName;
        this.website = website;
        this.description = description;
        this.shareHolderIds = shareHolderIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer[] getShareHolderIds() {
        return shareHolderIds;
    }

    public void setShareHolderIds(Integer[] shareHolderIds) {
        this.shareHolderIds = shareHolderIds;
    }
}
