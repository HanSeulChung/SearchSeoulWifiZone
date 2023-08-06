class UserLocation {
    static lat = -1.0;
    static lnt = -1.0;
}
function loadGeo() {
    navigator.geolocation.getCurrentPosition(onGeoOk,onGeoError);
}
function onGeoOk(position){
    UserLocation.lat = position.coords.latitude;
    UserLocation.lnt = position.coords.longitude;
    document.getElementById("LAT").value = UserLocation.lat;
    document.getElementById("LNT").value = UserLocation.lnt;
}
function onGeoError(){
    alert("Can't find you. No weather for you.");
}

// AJAX 요청 보내기
function sendDatatoServer() {
    // HistoryAddServlet에 보내는 fucntion 호출 : 사용자의 위치 history db 저장
    sendHistoryAddServlet();

    // WifiServlet에 보내는 function 호출 : 사용자의 위치에서 가장 가까운 20개의 데이터를 거리값을 null에서 값으로 update해준다.
    sendWifiServlet();
}

function sendHistoryAddServlet() {
    const xhr = new XMLHttpRequest();
    const url = "/History";
    const currentTime = new Date().toISOString();
    const data = {
        lat: UserLocation.lat.toString(),
        lnt: UserLocation.lnt.toString(),
        inquiryDate: currentTime
    };
    console.log(UserLocation.lat);
    console.log(UserLocation.lnt);
    console.log(data);
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                console.log("데이터가 성공적으로 추가되었습니다.");
            } else {
                console.error("데이터 추가에 실패하였습니다.");
            }
        }
    };

    xhr.send(JSON.stringify(data)); // 데이터를 서버로 전송
}

function sendWifiServlet() {
    const xhr = new XMLHttpRequest();
    const url = "/Wifi";
    const currentTime = new Date().toISOString();
    const data = {
        lat: UserLocation.lat.toString(),
        lnt: UserLocation.lnt.toString()
    };
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                console.log("데이터가 성공적으로 추가되었습니다.");
            } else {
                console.error("데이터 추가에 실패하였습니다.");
            }
        }
    };

    xhr.send(JSON.stringify(data)); // 데이터를 서버로 전송
}
