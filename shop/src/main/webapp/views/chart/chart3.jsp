<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  #container1{
    width: 300px;
    border: 1px solid black;
  }
  #container2{
    width: 300px;
    border: 1px solid yellow;
  }
</style>
<script>
  let chart3App = {
    init: function() {
      this.getData1();
      this.getData2();
    },
    getData1: function() {
      $.ajax({
        url: '<c:url value="/chart3_1"/>',
        success: (data) => {
          this.drawTotalChart(data);
        }
      });
    },
    getData2: function() {
      $.ajax({
        url: '<c:url value="/chart3_2"/>',
        success: (data) => {
          this.drawAvgChart(data);
        }
      })
    },

    drawTotalChart: function(data) {
      const categories = data.map(d => d.customerId);
      const seriesData = data.map(d => d.orderPrice);

      Highcharts.chart('container1', {
        chart: { type: 'column' },
        title: { text: '월별 매출 합계' },
        xAxis: { categories: categories, title: { text: '월' } },
        yAxis: { title: { text: '매출액 (원)' } },
        series: [{
          name: '월 매출',
          data: seriesData
        }]
      });
    },

    drawAvgChart: function(data) {
      const categories = data.map(d => d.customerId);
      const seriesData = data.map(d => d.orderId);

      Highcharts.chart('container2', {
        chart: { type: 'line' },
        title: { text: '월별 평균 매출' },
        xAxis: { categories: categories, title: { text: '월' } },
        yAxis: { title: { text: '평균 매출액 (원)' } },
        series: [{
          name: '평균 매출',
          data: seriesData
        }]
      });
    }
  }

  $(function() {
    chart3App.init();
  });
</script>

<div class="col-sm-10">
  <h2>월별 매출 현황</h2>
  <div class="row">
    <div class="col-md-6" id="container1"></div>
    <div class="col-md-6" id="container2"></div>
  </div>
</div>
