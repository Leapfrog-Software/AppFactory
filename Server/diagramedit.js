
function onClickCloseDetail() {

  document.getElementById("detail-base").style.visibility = "hidden";
  document.getElementById("detail").style.visibility = "hidden";
}

function onClickSave() {

	document.getElementById("detail-base").style.visibility = "hidden";
  document.getElementById("detail").style.visibility = "hidden";
}

function onClickScene(id) {

  document.getElementById("detail-base").style.visibility = "visible";
  document.getElementById("detail").style.visibility = "visible";
}

function onChangeGenre() {

  var index = document.getElementById("genre-select").selectedIndex;

  if (index == 0) {

  }
}

function onChangeType() {

  var index = document.getElementById("type-select").selectedIndex;

  if (index == 0) {
    
  }
}
