<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  #map5{
    width:auto;
    height:400px;
    border:2px solid red;
  }
</style>
<script>
  let map5 = {
    map:null,
    init: function(){
      this.makeMap();
      this.showMarkers();
    },
    showMarkers:function (){
      let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      let imageSize = new kakao.maps.Size(24, 35);
      let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
      let positions = [
        {
          title: '메가커피 아산탕정점',
          content: '<div>메가커피 아산탕정점</div>',
          latlng: new kakao.maps.LatLng(36.809632, 127.084150)
        },
        {
          title: '메가MGC커피 선문대점',
          content: '<div>메가MGC커피 선문대점</div>',
          latlng: new kakao.maps.LatLng(36.803140, 127.069829)
        },
        {
          title: '메가커피 아산 지중해마을점',
          content: '<div>메가커피 아산 지중해마을점</div>',
          latlng: new kakao.maps.LatLng(36.799514, 127.061615)
        },
        {
          title: '메가커피 장재점',
          content: '<div>메가커피 장재점</div>',
          latlng: new kakao.maps.LatLng(36.792853, 127.086993)
        },
        {
          title: '메가커피 천안 프라디움시티점',
          content: '<div>메가커피 천안 프라디움시티점</div>',
          latlng: new kakao.maps.LatLng(36.797238, 127.100262)
        },
        {
          title: '메가커피 배방점',
          content: '<div>메가커피 배방점</div>',
          latlng: new kakao.maps.LatLng(36.774720, 127.078885)
        },
      ];

      for (let i = 0; i < positions.length; i ++) {
        let marker = new kakao.maps.Marker({
          map: this.map,
          position: positions[i].latlng,
          title : positions[i].title,
          image : markerImage
        });

        let infowindow = new kakao.maps.InfoWindow({
          content: positions[i].content
        });

        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(this.map, marker, infowindow));
        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
      }
    },
    makeMap:function(){
      let mapContainer = document.getElementById('map5');
      let mapOption = {
        center: new kakao.maps.LatLng(36.803140, 127.069829), // 메가MGC커피 선문대점 기준 중심
        level: 7
      }
      this.map = new kakao.maps.Map(mapContainer, mapOption);
      let mapTypeControl = new kakao.maps.MapTypeControl();
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
      let zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    }
  };

  function makeOverListener(map, marker, infowindow) {
    return function() {
      infowindow.open(map, marker);
    };
  }

  function makeOutListener(infowindow) {
    return function() {
      infowindow.close();
    };
  }

  $(function(){
    map5.init()
  });
</script>

<div class="col-sm-10">
  <h2>Map5 메가커피 찾기</h2>
  <div id="map5"></div>
</div>
