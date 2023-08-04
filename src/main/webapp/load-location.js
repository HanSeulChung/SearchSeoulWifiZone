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

    if (UserLocation.lat != -1.0 && UserLocation.lnt != -1.0) {
        sendDataToServer();
    }
}
function onGeoError(){
    alert("Can't find you. No weather for you.");
}

// AJAX 요청 보내기
function sendDataToServer() {
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
function getNearbyWifiInfo() {
    if (document.getElementById("LAT").value == 0.0 && document.getElementById("LNT").value == 0.0 ) {
        alert("위치 정보를 가져오거나 입력해주세요.");
    }

    if (!isNaN(lat) && !isNaN(lnt)) {
        // AJAX로 Java Servlet에 위치 정보 전송
        fetch(`/getWifiInfo?lat=${lat}&lnt=${lnt}`)
            .then(response => {
                if (response.ok) {
                    alert("가까운 Wi-Fi 정보 업데이트 완료!");
                } else {
                    alert("가까운 Wi-Fi 정보 업데이트 실패!");
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("가까운 Wi-Fi 정보 업데이트 실패!");
            });
    } else {
        alert("위치 정보를 가져오거나 입력해주세요.");
    }
}