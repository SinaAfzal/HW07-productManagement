package ir.maktabsharif101.hw7.entities;
@SuppressWarnings("unused")
public class ShareHolder {
    private Integer id;
    private String shareHolderName;
    private String phoneNumber;
    private String nationalCode;

    private int[] brandIds;

    public ShareHolder() {
    }

    public ShareHolder(Integer id, String shareHolderName, String phoneNumber, String nationalCode, int[] brandIds) {
        this.id = id;
        this.shareHolderName = shareHolderName;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
        this.brandIds = brandIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShareHolderName() {
        return shareHolderName;
    }

    public void setShareHolderName(String shareHolderName) {
        this.shareHolderName = shareHolderName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public int[] getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(int[] brandIds) {
        this.brandIds = brandIds;
    }
}
