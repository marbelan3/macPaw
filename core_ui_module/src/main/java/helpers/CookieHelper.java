package helpers;

import com.codeborne.selenide.WebDriverRunner;

import java.util.Objects;

public class CookieHelper {

    public static String getCookie(String cookieName) {
        return String.valueOf(WebDriverRunner.getWebDriver().manage().getCookieNamed(cookieName).getValue());
    }

    public CookieHelper() {
        this.ho_sid = getCookie("ho_sid");
        this.slang = getCookie("slang");
        this.uid = getCookie("uid");
        this.ab_xl = getCookie("ab_xl");
        this.ab_xl_uid = getCookie("ab_xl_uid");
        this.sections_test = getCookie("sections_test");
        this.login = getCookie("login");
        this.sid = getCookie("sid");
        this.gauid = getCookie("gauid");
    }

    private String ho_sid;
    private String slang;
    private String uid;
    private String ab_xl;
    private String ab_xl_uid;
    private String sections_test;
    private String login;
    private String sid;
    private String gauid;


    public String getHo_sid() {
        return ho_sid;
    }

    public void setHo_sid(String ho_sid) {
        this.ho_sid = ho_sid;
    }

    public String getSlang() {
        return slang;
    }

    public void setSlang(String slang) {
        this.slang = slang;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAb_xl() {
        return ab_xl;
    }

    public void setAb_xl(String ab_xl) {
        this.ab_xl = ab_xl;
    }

    public String getAb_xl_uid() {
        return ab_xl_uid;
    }

    public void setAb_xl_uid(String ab_xl_uid) {
        this.ab_xl_uid = ab_xl_uid;
    }

    public String getSections_test() {
        return sections_test;
    }

    public void setSections_test(String sections_test) {
        this.sections_test = sections_test;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getGauid() {
        return gauid;
    }

    public void setGauid(String gauid) {
        this.gauid = gauid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookieHelper that = (CookieHelper) o;
        return Objects.equals(ho_sid, that.ho_sid) &&
                Objects.equals(slang, that.slang) &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(ab_xl, that.ab_xl) &&
                Objects.equals(ab_xl_uid, that.ab_xl_uid) &&
                Objects.equals(sections_test, that.sections_test) &&
                Objects.equals(login, that.login) &&
                Objects.equals(sid, that.sid) &&
                Objects.equals(gauid, that.gauid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ho_sid, slang, uid, ab_xl, ab_xl_uid, sections_test, login, sid, gauid);
    }

    @Override
    public String toString() {
        return "CookieHelper{" +
                "ho_sid='" + ho_sid + '\'' +
                ", slang='" + slang + '\'' +
                ", uid='" + uid + '\'' +
                ", ab_xl='" + ab_xl + '\'' +
                ", ab_xl_uid='" + ab_xl_uid + '\'' +
                ", sections_test='" + sections_test + '\'' +
                ", login='" + login + '\'' +
                ", sid='" + sid + '\'' +
                ", gauid='" + gauid + '\'' +
                '}';
    }

}
