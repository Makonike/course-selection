package com.zxd.www.po;

/**
 * 学院实体类
 * @author Makonike
 * @date 2021-09-13 20:03
 **/
public class Institute {


    private Integer instituteId;
    /**
     * 学院名称
     */
    private String instituteName;
    /**
     * 学院详情
     */
    private String instituteDesc;


    public Institute() {
    }

    public Institute(Integer instituteId, String instituteName, String instituteDesc) {
        this.instituteId = instituteId;
        this.instituteName = instituteName;
        this.instituteDesc = instituteDesc;
    }

    public Integer getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getInstituteDesc() {
        return instituteDesc;
    }

    public void setInstituteDesc(String instituteDesc) {
        this.instituteDesc = instituteDesc;
    }

    @Override
    public String toString() {
        return "Institute{" +
                "instituteId=" + instituteId +
                ", instituteName='" + instituteName + '\'' +
                ", instituteDesc='" + instituteDesc + '\'' +
                '}';
    }
}
