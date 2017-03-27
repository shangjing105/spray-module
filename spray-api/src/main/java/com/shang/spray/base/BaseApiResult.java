package com.shang.spray.base;

/**
 * info:api返回信息
 * Created by shang on 16/6/29.
 */
public class BaseApiResult {
    /**
     * 请求返回状态
     */
    private int status;
    /**
     * 请求返回信息
     */
    private String msg;
    /**
     * 请求凭证
     */
    private int rsid;
    /**
     * 系统类型
     */
    private String os;
    /**
     * 系统编号
     */
    private String osVersion;
    /**
     * 系统版本号
     */
    private String version;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 签名
     */
    private String sign;



    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRsid() {
        return rsid;
    }

    public void setRsid(int rsid) {
        this.rsid = rsid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(BaseApiCode baseApiCode) {
        this.status=baseApiCode.getStatus();
        this.msg=baseApiCode.getMsg();
    }
}
