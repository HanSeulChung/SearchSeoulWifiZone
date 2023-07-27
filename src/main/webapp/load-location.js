
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