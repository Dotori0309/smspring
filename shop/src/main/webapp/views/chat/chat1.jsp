<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
<style>
  body {
    font-family: 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
    background-color: #f0f2f5; /* Light gray background */
    color: #333;
  }
  .col-sm-10 {
    margin-top: 20px;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  }
  h2 {
    color: #007bff; /* Primary blue for headings */
    margin-bottom: 20px;
    border-bottom: 2px solid #007bff;
    padding-bottom: 10px;
  }
  h1#user_id2, h1#status2 { /* Adapted for chat1 IDs */
    font-size: 1.5rem;
    color: #555;
    margin-bottom: 10px;
  }
  button {
    background-color: #28a745; /* Green for connect/send */
    color: white;
    border: none;
    padding: 8px 15px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-right: 5px;
  }
  button:hover {
    background-color: #218838;
  }
  #disconnect2 { /* Adapted for chat1 IDs */
    background-color: #dc3545; /* Red for disconnect */
  }
  #disconnect2:hover {
    background-color: #c82333;
  }
  input[type="text"], input[type="password"] {
    border: 1px solid #ced4da;
    padding: 8px 10px;
    border-radius: 5px;
    margin-right: 5px;
    width: calc(100% - 100px); /* Adjust width for button */
    max-width: 250px;
  }
  div#all2, div#me2, div#to2 { /* Adapted for chat1 IDs */
    background-color: #e9ecef; /* Light background for chat areas */
    border: 1px solid #dee2e6;
    border-radius: 5px;
    padding: 10px;
    margin-top: 10px;
    height: 150px; /* Slightly reduced height for better fit */
    overflow-y: auto;
  }
  div#all2 h4, div#me2 h4, div#to2 h4 { /* Adapted for chat1 IDs */
    margin: 5px 0;
    padding: 5px;
    background-color: #f8f9fa;
    border-radius: 3px;
    border-left: 3px solid #007bff;
  }
  .card-body {
    padding: 0; /* Remove default card-body padding if col-sm-10 handles it */
  }
  .table-responsive {
    border: none; /* Remove default table-responsive border */
  }
</style>

<script>
  let chat1 = {
    id: '',
    stompClient: null,
    init: function () {
      this.id = $('#user_id2').text();
      this.setConnected(false); // Initially disable send buttons

      $('#connect2').click(() => {
        this.connect();
      });
      $('#disconnect2').click(() => {
        this.disconnect();
      });
      $('#sendall2').click(() => {
        let msg = JSON.stringify({
          'sendid': this.id,
          'content1': $("#alltext2").val()
        });
        this.stompClient.send("/receiveall", {}, msg);
        $("#alltext2").val('');
      });
      $('#sendme2').click(() => {
        let msg = JSON.stringify({
          'sendid': this.id,
          'content1': $("#metext2").val()
        });
        this.stompClient.send("/receiveme", {}, msg);
        $("#metext2").val('');
      });
      $('#sendto2').click(() => {
        var msg = JSON.stringify({
          'sendid': this.id,
          'receiveid': $('#target2').val(),
          'content1': $('#totext2').val()
        });
        this.stompClient.send('/receiveto', {}, msg);
        $('#totext2').val('');
      });
    },
    connect: function () {
      let sid = this.id;
      let socket = new SockJS('/chat');
      this.stompClient = Stomp.over(socket);

      // Use arrow function to preserve 'this' context
      this.stompClient.connect({}, (frame) => {
        console.log('Connected: ' + frame);
        this.setConnected(true); // Set connected status inside the callback

        this.stompClient.subscribe('/send', (msg) => {
          $("#all2").prepend(
                  "<h4>" + JSON.parse(msg.body).sendid + ":" +
                  JSON.parse(msg.body).content1 +
                  "</h4>");
        });
        this.stompClient.subscribe('/send/' + sid, (msg) => {
          $("#me2").prepend(
                  "<h4>" + JSON.parse(msg.body).sendid + ":" +
                  JSON.parse(msg.body).content1 + "</h4>");
        });
        this.stompClient.subscribe('/send/to/' + sid, (msg) => {
          $("#to2").prepend(
                  "<h4>" + JSON.parse(msg.body).sendid + ":" +
                  JSON.parse(msg.body).content1 +
                  "</h4>");
        });
      });
    },
    disconnect: function () {
      if (this.stompClient !== null) {
        this.stompClient.disconnect();
      }
      this.setConnected(false);
      console.log("Disconnected");
    },
    setConnected: function (connected) {
      // Control UI elements based on connection status
      $("#connect2").prop("disabled", connected);
      $("#disconnect2").prop("disabled", !connected);
      $("#sendall2, #sendme2, #sendto2").prop("disabled", !connected);
      $("#alltext2, #metext2, #target2, #totext2").prop("disabled", !connected);

      if (connected) {
        $("#status2").text("Connected");
      } else {
        $("#status2").text("Disconnected");
        $("#all2, #me2, #to2").empty(); // Clear chat windows on disconnect
      }
    }
  }
  $(() => {
    chat1.init();
  })
</script>


<div class="col-sm-10">
  <h2>chat1 Center</h2> <!-- Changed title -->
  <div class="card-body">
    <div class="table-responsive">
      <div class="col-sm-5">
        <h1 id="user_id2">${sessionScope.cust.custId}</h1> <!-- Changed ID for chat1 -->
        <H1 id="status2">Status</H1> <!-- Changed ID for chat1 -->
        <button id="connect2">Connect</button> <!-- Changed ID for chat1 -->
        <button id="disconnect2">Disconnect</button> <!-- Changed ID for chat1 -->

        <h3>All</h3>
        <input type="text" id="alltext2"><button id="sendall2">Send</button> <!-- Changed ID for chat1 -->
        <div id="all2"></div> <!-- Changed ID for chat1 -->

        <h3>Me</h3>
        <input type="text" id="metext2"><button id="sendme2">Send</button> <!-- Changed ID for chat1 -->
        <div id="me2"></div> <!-- Changed ID for chat1 -->

        <h3>To</h3>
        <input type="text" id="target2"> <!-- Changed ID for chat1 -->
        <input type="text" id="totext2"><button id="sendto2">Send</button> <!-- Changed ID for chat1 -->
        <div id="to2"></div> <!-- Changed ID for chat1 -->

      </div>
    </div>

  </div>
</div>