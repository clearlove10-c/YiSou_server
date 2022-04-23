package com.smlz.yisounews.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author smlz
 * @since 2022-04-15
 */
@TableName("user_info")
@ApiModel(value = "UserInfo对象", description = "")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPwd;

    private String userSex;

    private LocalDate userBirthday;

    private String userSignature;

    private byte[] userPic;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
    public LocalDate getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(LocalDate userBirthday) {
        this.userBirthday = userBirthday;
    }
    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }
    public byte[] getUserPic() {
        return userPic;
    }

    public void setUserPic(byte[] userPic) {
        this.userPic = userPic;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
            "userId=" + userId +
            ", userName=" + userName +
            ", userPwd=" + userPwd +
            ", userSex=" + userSex +
            ", userBirthday=" + userBirthday +
            ", userSignature=" + userSignature +
            ", userPic=" + userPic +
        "}";
    }

    public UserInfo(Integer userId, String userName, String userPwd, String userSex, LocalDate userBirthday, String userSignature, byte[] userPic) {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.userSignature = userSignature;
        this.userPic = userPic;
    }

    public UserInfo() {
    }
}
