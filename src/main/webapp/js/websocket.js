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
    frase = textFrase.value;

    name = textNome.value;
    $.ajax({
        type: "POST",
        url: "./cadastro",
        data: {nome: textNome.value, apelido: textApelido.value, email: textEmail.value, cidade: textCidade.value, data: textData.value, frase: textFrase.value},
        success: function(data) {
            titulo.innerHTML = "<div style='border-radius:50%;-moz-border-radius:50%;-webkit-border-radius:50%;'></div>" + name + " esta online! </br> " + frase;
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

    console.log(name);
    var data = new Date();
    websocket.send(data.toLocaleString() + " " + name + ": " + textField.value);
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}
function infUser(name) {

    $.ajax({
        type: "POST",
        url: "./informacao",
        data: {nome: name},
        success: function(result) {
            $("#dialogInf").dialog("open");
            console.log(result);
            infNome.innerHTML = result['nome'];
            infApelido.innerHTML = result['apelido'];
            infCidade.innerHTML = result['cidade'];
            infData.innerHTML = result['data'];
            infFrase.innerHTML = result['frase'];
            infEmail.innerHTML = result['email'];

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
                dialog += '<div id="dialog' + users[x].toLowerCase().trim().replace(" ", "_") + '"  title="Chat - ' + users[x] + '" >'
                        + '<div class="panel panel-default" style="width: 370px">'
                        + '<div class="panel-heading">'
                        + '<a href="#"  data-toggle="modal" onclick="infUser(\'' + users[x].trim() + '\')" data-target="#modal-info"><span class="glyphicon glyphicon-info-sign"></span> Informações</a>'
                        + '</div>'
                        + '<div class="panel-body" id="painel' + x + '" style="height: 200px;overflow-y: scroll">'
                        + '</div>'
                        + '<div class="panel-footer">'
                        + '<div class="row">'
                        + '<div class="col-xs-10">'
                        + '<textarea class="form-control" rows="3" style="resize: none;width: 250px"></textarea>'
                        + '</div>'
                        + '<div class="col-xs-2">'
                        + '<p><button type="button" class="btn btn-primary btn-lg" onclick="infUser(\'' + users[x].trim() + '\')" style="float: right">Enviar</button></p>'
                        + '<p><button type="button" class="btn btn-default btn-lg" onclick="$(\'#dialog' + users[x].toLowerCase().trim().replace(" ", "_") + '\').dialog(\'close\')" style="float: right;margin-top: 5px;width: 78px;">Sair</button></p>'
                        + '</div>'
                        + '</div>'
                        + '</div>'
                        + '</div>'
                        + '</div>';


                html += '<p><div class="dropdown">'
                        + '<a data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> ' + users[x].trim() + '</a>'
                        + '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel" >'
                        + '<p style="margin-left: 5px"><a href="#" class="conversa" onclick="$(\'#dialog' + users[x].toLowerCase().trim().replace(" ", "_") + '\').dialog(\'open\');$(\'#dialog' + users[x].toLowerCase().trim().replace(" ", "_") + '\').dialog({width: 405});" ><span class="glyphicon glyphicon-phone"></span> Iniciar Conversa</a></p>'
                        + '<p style="margin-left: 5px"><a href="#"  data-toggle="modal" onclick="infUser(\'' + users[x].trim() + '\')" data-target="#modal-info"><span class="glyphicon glyphicon-info-sign"></span> Informações</a></p>'
                        + '</ul></div></p>';

            }


        }
        userTab.innerHTML = html;
        dialogsUser.innerHTML = dialog;
        historico.innerHTML = html;

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
