
function loadGeo() {
    navigator.geolocation.getCurrentPosition(onGeoOk,onGeoError);
}
function onGeoOk(position){
    const lat = position.coords.latitude;
    const lng = position.coords.longitude;
    document.getElementById("LAT").value = lat;
    document.getElementById("LNT").value = lng;
    console.log(lat);
    console.log(lng);
}
function onGeoError(){
    alert("Can't find you. No weather for you.");
}

function calDistance() {
    const lat = document.getElementById("LAT").value;
    const lng = document.getElementById("LNT").value;

    // 서버에 위치 정보를 전달하는 AJAX 요청
    $.ajax({
        type: "POST",
        url: "/getNearbyWifi",
        data: {
            lat: lat,
            lng: lng,
        },
        success: function (response) {
            // 서버로부터 받은 결과를 처리하는 로직
            // 예를 들어, 테이블에 결과 데이터를 동적으로 추가하는 등의 동작을 수행

            // 예시로 가까운 WiFi 정보를 테이블에 추가하는 코드
            const table = document.getElementById("wifiInfos");
            table.innerHTML = ""; // 테이블 초기화

            response.forEach(function (wifi) {
                const row = table.insertRow();
                row.insertCell().innerText = wifi.distance;
                row.insertCell().innerText = wifi.manageNo;
                row.insertCell().innerText = wifi.borough;
                // ... 나머지 WiFi 정보를 셀에 추가
            });
        },
        error: function (error) {
            alert("Failed to get nearby WiFi data.");
        }
    });
}