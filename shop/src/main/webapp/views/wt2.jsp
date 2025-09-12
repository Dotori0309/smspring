<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  #map2{
    width:100%;
    height:400px;
    border: 2px solid red;
  }
  #weather-info{
    width:100%;
    height:400px;
    border: 2px solid blue;
    overflow: auto;
  }
</style>
<script>
  let wt2 = {
    map:null,
    init:function(){
      this.makeMap();
    },
    makeMap: function(){
      let mapContainer = document.getElementById('map2');
      let mapOption = {
        center: new kakao.maps.LatLng(37.538453, 127.053110),
        level: 5
      }
      this.map = new kakao.maps.Map(mapContainer, mapOption);
      let mapTypeControl = new kakao.maps.MapTypeControl();
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
      let zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position)=>{
          let lat = position.coords.latitude;
          let lon = position.coords.longitude;
          let locPosition = new kakao.maps.LatLng(lat, lon);
          this.map.panTo(locPosition);
          this.getWeather(lat, lon);
        });
      } else {
        alert('Geolocation is not supported by this browser.');
      }

      kakao.maps.event.addListener(this.map, 'click', (mouseEvent)=>{
        let latlng = mouseEvent.latLng;
        let lat = latlng.getLat();
        let lon = latlng.getLng();
        this.getWeather(lat, lon);
      });

      kakao.maps.event.addListener(this.map, 'dragend', ()=> {
        let center = this.map.getCenter();
        let lat = center.getLat();
        let lon = center.getLng();
        this.getWeather(lat, lon);
      });
    },
    getWeather: function(lat, lon){
      $.ajax({
        url: '<c:url value="/getwt2"/>',
        data: {'lat': lat, 'lon': lon},
        success: (data)=>{
          this.displayWeather(data);
        }
      });
    },
    displayWeather: function(data){
      let weather = data.weather[0];
      let temp = data.main.temp;
      let icon = weather.icon;
      let iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";

      let content = '<div>' +
              '  <img src="' + iconUrl + '" alt="weather icon">' +
              '  <p>Temperature: ' + temp + '°C</p>' +
              '  <p>Weather: ' + weather.main + '</p>' +
              '</div>';

      $('#weather-info').html(content);
    }
  }
  $(function(){
    wt2.init();
  });
</script>

<div class="col-sm-10">
  <div class="row">
    <div class="col-sm-8">
      <h2>Weather 2 Page</h2>
      <div id="map2"></div>
    </div>
    <div class="col-sm-2">
      <h2>Information</h2>
      <div id="weather-info"></div>
    </div>
  </div>
</div>
