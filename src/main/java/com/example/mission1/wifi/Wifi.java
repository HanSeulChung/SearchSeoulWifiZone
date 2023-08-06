package com.example.mission1.wifi;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wifi {

    private double distance; // 거리(Km)
    @SerializedName("X_SWIFI_MGR_NO")
    private String manageNo; // 관리번호 : private key
    @SerializedName("X_SWIFI_WRDOFC")
    private String borough; // 자치구
    @SerializedName("X_SWIFI_MAIN_NM")
    private String wifiName; // 와이파이명
    @SerializedName("X_SWIFI_ADRES1")
    private String roadAddr; // 도로명주소
    @SerializedName("X_SWIFI_ADRES2")
    private String detailAddr; // 상세주소
    @SerializedName("X_SWIFI_INSTL_FLOOR")
    private String intallLoc; // 설치장소
    @SerializedName("X_SWIFI_INSTL_TY")
    private String installType; // 설치타입
    @SerializedName("X_SWIFI_INSTL_MBY")
    private String installAgency; // 설치기관
    @SerializedName("X_SWIFI_SVC_SE")
    private String serviceClassify; // 서비스구분
    @SerializedName("X_SWIFI_CMCWR")
    private String netType; // 망종류
    @SerializedName("X_SWIFI_CNSTC_YEAR")
    private int installYear; // 설치년도
    @SerializedName("X_SWIFI_INOUT_DOOR")
    private String inOrout; //실내외구분
    @SerializedName("X_SWIFI_REMARS3")
    private String wifiConEnv; //설치환경
    @SerializedName("LNT")
    private double lat; // 위도
    @SerializedName("LAT")
    private double lnt; // 경도
    @SerializedName("WORK_DTTM")
    private String workDate; // 설치날짜
}
