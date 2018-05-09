package com.bascker.wechat.publicnumber.model;

import com.bascker.bsutil.JsonUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 用户实体
 *
 * @author bascker
 */
public class User {

    private String mOpenid;             // 用户的唯一标识
    private String mNickname;           // 用户昵称
    private int mSex;                   // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String mProvince;           // 用户个人资料填写的省份
    private String mCity;               // 普通用户个人资料填写的城市
    private String mCountry;            // 国家，如中国为CN
    private String mHeadimgurl;         // 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
    private List<String> mPrivilege;    // 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
    private String mUnionid;            // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段

    public User() {}

    public static User createFromJson (final String jsonStr) throws IOException {
        final Map<String, Object> map = JsonUtils.toMap(jsonStr);
        final User user = new User();
        user.setOpenid((String) map.get("openid"));
        user.setNickname((String) map.get("nickname"));
        user.setSex((Integer) map.get("sex"));
        user.setProvince((String) map.get("province"));
        user.setCity((String) map.get("city"));
        user.setCountry((String) map.get("country"));
        user.setHeadimgurl((String) map.get("headimgurl"));
        user.setPrivilege((List<String>) map.get("privilege"));
        user.setUnionid((String) map.get("unionid"));

        return user;
    }

    // ------------------------------------
    // Getter/Setter
    // ------------------------------------

    public String getOpenid() {
        return mOpenid;
    }

    public void setOpenid(final String openid) {
        mOpenid = openid;
    }

    public String getNickname() {
        return mNickname;
    }

    public void setNickname(final String nickname) {
        mNickname = nickname;
    }

    public String getSex() {
        if (mSex == 1) {
            return "男";
        } else if (mSex == 2) {
            return "女";
        }

        return "未知";
    }

    public void setSex(final int sex) {
        mSex = sex;
    }

    public String getProvince() {
        return mProvince;
    }

    public void setProvince(final String province) {
        mProvince = province;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(final String city) {
        mCity = city;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(final String country) {
        mCountry = country;
    }

    public String getHeadimgurl() {
        return mHeadimgurl;
    }

    public void setHeadimgurl(final String headimgurl) {
        mHeadimgurl = headimgurl;
    }

    public List<String> getPrivilege() {
        return mPrivilege;
    }

    public void setPrivilege(final List<String> privilege) {
        mPrivilege = privilege;
    }

    public String getUnionid() {
        return mUnionid;
    }

    public void setUnionid(final String unionid) {
        mUnionid = unionid;
    }

    @Override
    public String toString() {
        return "User{" +
                "mOpenid='" + mOpenid + '\'' +
                ", mNickname='" + mNickname + '\'' +
                ", mSex=" + mSex +
                ", mProvince='" + mProvince + '\'' +
                ", mCity='" + mCity + '\'' +
                ", mCountry='" + mCountry + '\'' +
                ", mHeadimgurl='" + mHeadimgurl + '\'' +
                ", mPrivilege=" + mPrivilege +
                ", mUnionid='" + mUnionid + '\'' +
                '}';
    }
}
