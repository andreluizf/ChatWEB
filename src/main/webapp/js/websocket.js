var websocket;
var name;
var apelido;
var data;
var email;
var cidade;
var frase;
var output;
var wsUri;
function init(user) {
    wsUri = "ws://" + document.location.host + document.location.pathname + "chat/" + user;
    console.log(wsUri);
    websocket = new WebSocket(wsUri);

    websocket.onopen = function(evt) {
        onOpen(evt)
    };
    websocket.onmessage = function(evt) {
        onMessage(evt)
    };
    websocket.onerror = function(evt) {
        onError(evt)
    };
    output = document.getElementById("output");
}
function join() {
//    username = textField.value;

    name = textNome.value;
    $.ajax({
        type: "POST",
        url: "./cadastro",
        data: {nome: textNome.value, apelido: textApelido.value, email: textEmail.value, cidade: textCidade.value, data: textData.value, frase: textFrase.value},
        success: function(data) {
            alert("sucess");
        },
        error: function(data) {
            alert("data.code");
        }
    });

    init(name);
    setTimeout(function() {
        websocket.send(name + " ::user");
    }, 500);

}

function send_message() {
    websocket.send(name + ": " + textField.value);
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}
function infUser(nome) {
    alert("aki")
    $.ajax({
        type: "POST",
        url: "./informacao",
        data: {nome: nome},
        success: function(data) {
            alert("sucess");
            console.log(data);
        },
        error: function(data) {
            alert("data.code");
        }
    });
}

function onMessage(evt) {
    if (evt.data.indexOf("]") != -1) {
        var data = evt.data.replace("[", "").replace("]", "");
        var users = data.split(",");
        var dialog = '';
        var dialogInf = '';
        var html = '';
        for (var x = 0; x < users.length; x++)
        {
            if (users[x].trim() != name.trim()) {
                console.log(users[x]);
                console.log(name);
                dialog += '<div id="dialog' + users[x].toLowerCase().trim() + '" title="Usuario : ' + users[x].trim() + '" >'
                        + '<div class="panel panel-default" style="width: 370px">'
                        + '<div class="panel-body" id="painel' + x + '" style="height: 200px;overflow-y: scroll">'
                        + '</div>'
                        + '<div class="panel-footer">'
                        + '<div class="row">'
                        + '<div class="col-xs-10">'
                        + '<textarea class="form-control" rows="3" style="resize: none;width: 250px"></textarea>'
                        + '</div>'
                        + '<div class="col-xs-2">'
                        + '<button type="button" class="btn btn-primary btn-lg" style="float: right">Enviar</button>'
                        + '</div>'
                        + '</div>'
                        + '</div>'
                        + '</div>'
                        + '</div>';


                html += '<p><div class="dropdown">'
                        + '<a data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> ' + users[x].trim() + '</a>'
                        + '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel" >'
                        + '<p style="margin-left: 5px"><a href="#" class="conversa" onclick="$(\'#dialog' + users[x].toLowerCase().trim() + '\').dialog(\'open\');$(\'#dialog' + users[x].toLowerCase().trim() + '\').dialog({width: 405});" ><span class="glyphicon glyphicon-phone"></span> Iniciar Conversa</a></p>'
                        + '<p style="margin-left: 5px"><a href="#"  data-toggle="modal" onclick="infUser()" data-target="#modal-info"><span class="glyphicon glyphicon-info-sign"></span> Informações</a></p>'
                        + '</ul></div></p>';

            }


        }
        tabs1.innerHTML = html;
        dialogsUser.innerHTML = dialog;


        for (var x = 0; x < users.length; x++)
        {

            $("#dialog" + users[x].toLowerCase().trim()).dialog({autoOpen: false});
        }
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
