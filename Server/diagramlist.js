
function initialize() {

  var savedDiagramIds = getCookie("diagram");
  if (savedDiagramIds.length == 0) {
    document.getElementById("div_nodata").style.display = "block";
  } else {
    document.getElementById("div_nodata").style.display = "none";
    fetch(savedDiagramIds);
  }
}

function fetch(savedDiagramIds) {

  var url = "srv.php?command=getdiagramname&ids=" + savedDiagramIds;
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
      var names = parsed.names;

      var html = "<table>";
      for (id in names) {
        html += "<tr><td><a href='javascript:onClickDiagram(\"" + id + "\")'>";
        html += base64Decode(names[id]);
        html += "</a></td></tr>";
      }
      html += "</table>";
    }
    document.getElementById("contents").innerHTML = html;
  });
}

function onClickDiagram(id) {
  location.href = "diagramedit.html?" + id;
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

  var url = "srv.php?command=creatediagram&appName=" + base64Encode(appName);
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
      var diagramId = parsed.id;
      var savedId = getCookie("diagram");
      if (savedId.length > 0) {
        savedId += "-";
      }
      savedId += diagramId;
      setCookie("diagram", savedId);

      location.href = "diagramedit.html?" + diagramId;
    }
  });
}
