var wsUri = "ws://" + document.location.host + document.location.pathname + "chat";
console.log(wsUri);
var websocket = new WebSocket(wsUri);

var username;
websocket.onopen = function(evt) { onOpen(evt) };
websocket.onmessage = function(evt) { onMessage(evt) };
websocket.onerror = function(evt) { onError(evt) };
var output = document.getElementById("output");

function join() {
    username = textField.value;
    websocket.send(username + " ::user");
}

function send_message() {
    websocket.send(username + ": " + textField.value);
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}

function onMessage(evt) {
    console.log("onMessage: " + evt.data);
    if (evt.data.indexOf("::user") != -1) {
        var html='<p><div class="dropdown">'
                  +'<a data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> '+evt.data.substring(0, evt.data.indexOf(" ::user"))+'</a>'
                  +'<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel" >'
                  +'<p style="margin-left: 5px"><a href="#" id="1" class="conversa" ><span class="glyphicon glyphicon-phone"></span> Iniciar Conversa</a></p>'
                  +'<p style="margin-left: 5px"><a href="#"  data-toggle="modal" data-target="#modal-info"><span class="glyphicon glyphicon-info-sign"></span> Informações</a></p>'
                  +'</ul></div></p>';
        tabs1.innerHTML += html;
    } else {
        chatlogField.innerHTML += evt.data + "\n";
    }
}

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}
//
//
////
//var wsUri;
//
//
//
//var username;
//var nome;
//var cidade;
//var nacimento;
//var frase;
//var apelido;
//var websocket;
//
//var output = document.getElementById("output");
//function join() {
//
//    wsUri = "ws://" + document.location.host + document.location.pathname + "chat/" + textField.value;
//    websocket = new WebSocket(wsUri);
//    websocket.onopen = function(username, evt) {
//
//        onOpen(evt)
//    };
//    websocket.onmessage = function(evt) {
//        onMessage(evt)
//    };
//    websocket.onerror = function(evt) {
//        onError(evt)
//    };
//    
////    nome = textNome.value;
////    cidade = textCidade.value;
////    nacimento = textNascimento.value;
////    frase = textFrase.value;
////    apelido = textApelido.value;
//
//    username = textField.value;
//    setTimeout(function(){
//        websocket.send(username + " joined");
//    },1000)
//    
//}
//
//function send_message() {
//    websocket.send("Data:" + new Date().toLocaleDateString() + " " + new Date().toLocaleTimeString() + " - " + username + ": " + textField.value);
//
//
//}
//
//function onOpen() {
//    writeToScreen("Connected to " + wsUri);
//}
//
//function onMessage(evt) {
//    console.log("onMessage: " + evt.data);
//    if (evt.data.indexOf("joined") != -1) {
//        userField.innerHTML += evt.data.substring(0, evt.data.indexOf(" joined")) + "\n";
//    } else {
//        chatlogField.innerHTML += evt.data + "\n";
//    }
//}
//
//function onError(evt) {
//    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
//}
//
//function writeToScreen(message) {
//    output.innerHTML += message + "<br>";
//}
//
//
