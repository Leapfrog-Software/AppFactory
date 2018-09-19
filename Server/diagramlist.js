
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
  document.getElementById("detail-base").style.visibility = "visible";
  document.getElementById("detail").style.visibility = "visible";
}
