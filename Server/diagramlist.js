
function initialize() {

  var savedDiagramNos = getCookie("diagram");
  if (savedDiagramNos.length == 0) {
    document.getElementById("div_nodata").style.display = "block";
  } else {
    document.getElementById("div_nodata").style.display = "none";
    fetch();
  }
}

function fetch() {

}

function onClickCreate() {
  document.getElementById("div-create-base").style.visibility = "visible";
  document.getElementById("div-create").style.visibility = "visible";
  document.getElementById("div-no-name").style.visibility = "hidden";
}

function onClickDetailBase() {
  document.getElementById("div-create-base").style.visibility = "hidden";
  document.getElementById("div-create").style.visibility = "hidden";
  document.getElementById("div-no-name").style.visibility = "hidden";
}

function onClickNext() {

  var appName = document.getElementById("app-name").value;
  if (appName.length == 0) {
    document.getElementById("div-no-name").style.visibility = "visible";
    return;
  }

  var url = "srv.php?command=creatediagram";
  httpPost(url, null, function(data) {
    var parsed = null;
    try {
      parsed = JSON.parse(data);
      if (!parsed) {
        return;
      }
    } catch(e) {
      return;
    }
    if (parsed.result == null) {
      return;
    }
    if (parsed.result === "0") {
      location.href = "diagramedit.html?" + parsed.id;
    }
  });
}
