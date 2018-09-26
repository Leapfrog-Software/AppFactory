
var gGenreList = [
  ["スプラッシュ", ["標準", "オーバーレイ"]],
  ["一覧画面", ["ユーザー(リスト)", "ユーザー(コレクション)", "ユーザー(カード)", "ニュース(リスト)", "ニュース(カテゴリー)", "商品(リスト)", "商品(カテゴリー)", "商品(コレクション)"]],
  ["詳細画面", ["詳細①", "詳細②"]],
  ["投稿画面", ["文章", "文章＋画像"]],
  ["検索", ["検索①"]],
  ["マイページ", ["マイページ", "プロフィール編集"]],
//  ["ログイン", ["", ""]],
//  ["新規登録", ["", ""]],
//  ["同意画面", ["", ""]],
//  ["タブ画面", ["", ""]],
//  ["メニュー", ["", ""]],
//  ["チャット", ["", ""]],
//  ["画像/動画再生", ["", ""]],
//  ["画像/動画撮影", ["", ""]],
//  ["地図", ["", ""]],
];

var gSceneList = [];
var gCurrentSceneId = 0;
var gHtml = "";
var gMaxHIndex = 0;
var gMaxVIndex = 0;
var gTimer;

var kLeftMargin = 50;
var kTopMargin = 50;
var kSceneWidth = 120;
var kSceneHeight = 244;
var kSceneHorizontalMargin = 80;
var kSceneVerticalMargin = 60;
var kDialogWidth = 700;
var kDialogHeight = 600;

function initialize() {

  refresh(function(result) {});

  var genreSelect = document.getElementById("genre-select");

  for (var i = 0; i < gGenreList.length; i++) {
    var option = document.createElement("option");
    option.setAttribute("value", i);
    option.innerHTML = gGenreList[i][0];
    genreSelect.appendChild(option);
  }

  adjustWindow();

  window.addEventListener("resize", function(event) {
    if (gTimer !== false) {
      clearTimeout(gTimer);
    }
    gTimer = setTimeout(function() {
      adjustWindow();
    }, 50);
  });
}

function refresh(callback) {

  var diagramId = location.href.split("?")[1];
  var url = "srv.php?command=getdiagram&diagramId=" + diagramId;
  httpPost(url, null, function(data) {
    try {
      var parsed = JSON.parse(data);
      if ((parsed) && (parsed.result === "0") && (parsed.diagram)) {
        display(parsed.diagram);
        callback(true);
        return;
      }
    } catch(e) {
      alert(e.message);
    }
    callback(false);
  });
}

function display(diagram) {

  var splited = diagram.split("\n");
  if (splited.length == 0) {
    return;
  }

  var appName = base64Decode(splited[0]);
  document.getElementById("app-name").innerHTML = appName;

  gSceneList = [];

  for (var i = 1; i < splited.length; i++) {
    var sceneParams = splited[i].split(",");
    if (sceneParams.length == 10) {
      var scene = new Object();
      scene.id = sceneParams[0];
      scene.name = base64Decode(sceneParams[1]);
      scene.description = base64Decode(sceneParams[2]);
      scene.genre = sceneParams[3];
      scene.type = sceneParams[4];
      scene.transition1 = sceneParams[5];
      scene.transition2 = sceneParams[6];
      scene.transition3 = sceneParams[7];
      scene.transition4 = sceneParams[8];
      scene.transition5 = sceneParams[9];
      gSceneList.push(scene);
    }
  }

  // 遷移先の選択肢
  for (var i = 0; i < 5; i++) {
    var elementId = "transition" + (i + 1);
    var element = document.getElementById(elementId);

    for (var j = element.options.length - 1; j >= 0; j--) {
      element.removeChild(element.options[j]);
    }

    var option = document.createElement("option");
    option.setAttribute("value", "");
    option.innerHTML = "なし";
    element.appendChild(option);

    for (var j = 0; j < gSceneList.length; j++) {
      var option = document.createElement("option");
      option.setAttribute("value", gSceneList[j].id);
      option.innerHTML = gSceneList[j].name;
      element.appendChild(option);
    }
    // var createOption = document.createElement("option");
    // createOption.setAttribute("value", -1);
    // createOption.innerHTML = "(新規作成)";
    // element.appendChild(createOption);
  }

  // ルート画面
  var rootIndex = findSceneIndex(0);
  if (rootIndex == -1) {
    return;
  }
  var root = gSceneList[rootIndex];
  gHtml = "";
  gMaxHIndex = 0;
  gMaxVIndex = 0;
  displayBranch(root, 0, 0, [root]);

  var diagramElement = document.getElementById("diagram");
  diagramElement.innerHTML = gHtml;
  diagramElement.style.width = (2 * kLeftMargin + (gMaxHIndex + 1) * (kSceneWidth + kSceneHorizontalMargin)) + "px";
  diagramElement.style.height = (2 * kTopMargin + (gMaxVIndex + 1) * (kSceneHeight + kSceneVerticalMargin)) + "px";

  adjustWindow();
}

function adjustWindow() {

  var dialogBase = document.getElementById("dialog-base");
  dialogBase.style.width = document.body.clientWidth + "px";
  var contentsHeight = (2 * kTopMargin + (gMaxVIndex + 1) * (kSceneHeight + kSceneVerticalMargin));
  if ((contentsHeight > kDialogHeight) && (contentsHeight > document.body.clientHeight)) {
    console.log("contentsHeight");
    dialogBase.style.height = contentsHeight + "px";
  } else if ((kDialogHeight > contentsHeight) && (kDialogHeight > document.body.clientHeight)) {
    console.log("kDialogHeight");
    dialogBase.style.height = kDialogHeight + "px";
  } else {
    console.log("clientHeight");
    dialogBase.style.height = document.body.clientHeight + "px";
  }

  var detailDialog = document.getElementById("detail");
  detailDialog.style.width = kDialogWidth + "px";
  detailDialog.style.height = kDialogHeight + "px";
  detailDialog.style.left = ((document.body.clientWidth - kDialogWidth) / 2) + "px";
  var detailDialogTop = (document.body.clientHeight - kDialogHeight) / 2;
  if (detailDialogTop < 0) {
    detailDialogTop = 0;
  }
  detailDialog.style.top = detailDialogTop + "px";
}

function displayBranch(scene, hIndex, vIndex, history) {

  if (hIndex > gMaxHIndex) { gMaxHIndex = hIndex; }
  if (vIndex > gMaxVIndex) { gMaxVIndex = vIndex; }

  var left = kLeftMargin + hIndex * (kSceneWidth + kSceneHorizontalMargin);
  var top = kTopMargin + vIndex * (kSceneHeight + kSceneVerticalMargin);

  var count = 0;
  for (var i = 0; i < history.length; i++) {
    if (history[i].id == scene.id) {
      count++;
    }
  }
  // 画面循環あり
  if (count >= 2) {
    gHtml += ("<div style='position:absolute;left:" + left + "px;top:" + top + "px;width:" + kSceneWidth + "px;height:" + kSceneHeight + "px;text-align:center;'>");
    gHtml += "<table style='width:100%;height:100%;margin:0 10px'><tr><td style='font-size:12px;color:#666'>";
    gHtml += ("\"" + scene.name + "\"<br>に戻る");
    gHtml += "</td></tr></table></div>";
    return vIndex + 1;
  }

  gHtml += ("<div style='position: absolute; left:" + left + "px;top:" + top + "px' onclick='javascript:onClickScene(\"" + scene.id + "\");'>");
  gHtml += ("<img src='img/diagram/scene" + scene.genre + "-" + scene.type + ".png' style='width:" + kSceneWidth + "px; height:" + kSceneHeight + "px'>");
  gHtml += ("<div style='width:" + kSceneWidth + "px;text-align:center;font-size:14px;margin-top:6px;color:#666'>");
  gHtml += scene.name;
  gHtml += "</div>";
  gHtml += "</div>";

  if ((scene.transition1.length == 0)
    && (scene.transition2.length == 0)
    && (scene.transition3.length == 0)
    && (scene.transition4.length == 0)
    && (scene.transition5.length == 0)) {
    return vIndex + 1;

  } else {
    var nextVIndex = vIndex;

    var trans1Index = findSceneIndex(scene.transition1);
    if (trans1Index != -1){
      displayArrow(hIndex, vIndex, nextVIndex - vIndex);
      var cpHistory = history.slice();
      cpHistory.push(gSceneList[trans1Index]);
      nextVIndex = displayBranch(gSceneList[trans1Index], hIndex + 1, nextVIndex, cpHistory);
    }
    var trans2Index = findSceneIndex(scene.transition2);
    if (trans2Index != -1) {
      displayArrow(hIndex, vIndex, nextVIndex - vIndex);
      var cpHistory = history.slice();
      cpHistory.push(gSceneList[trans2Index]);
      nextVIndex = displayBranch(gSceneList[trans2Index], hIndex + 1, nextVIndex, cpHistory);
    }
    var trans3Index = findSceneIndex(scene.transition3);
    if (trans3Index != -1) {
      displayArrow(hIndex, vIndex, nextVIndex - vIndex);
      var cpHistory = history.slice();
      cpHistory.push(gSceneList[trans3Index]);
      nextVIndex = displayBranch(gSceneList[trans3Index], hIndex + 1, nextVIndex, cpHistory);
    }
    var trans4Index = findSceneIndex(scene.transition4);
    if (trans4Index != -1) {
      displayArrow(hIndex, vIndex, nextVIndex - vIndex);
      var cpHistory = history.slice();
      cpHistory.push(gSceneList[trans4Index]);
      nextVIndex = displayBranch(gSceneList[trans4Index], hIndex + 1, nextVIndex, cpHistory);
    }
    var trans5Index = findSceneIndex(scene.transition5);
    if (trans5Index != -1) {
      displayArrow(hIndex, vIndex, nextVIndex - vIndex);
      var cpHistory = history.slice();
      cpHistory.push(gSceneList[trans5Index]);
      nextVIndex = displayBranch(gSceneList[trans5Index], hIndex + 1, nextVIndex, cpHistory);
    }
    return nextVIndex
  }
}

function displayArrow(hIndex, vIndex, height) {

  if (height == 0) {
    var left = kLeftMargin + hIndex * (kSceneWidth + kSceneHorizontalMargin) + kSceneWidth;
    var width = kSceneHorizontalMargin;
    var top = kTopMargin + vIndex * (kSceneHeight + kSceneVerticalMargin) + kSceneHeight / 2;
    gHtml += ("<div style='position:absolute;left:" + left + "px;top:" + top + "px;width:" + width + "px'>");
    gHtml += "<div class='arrow-right'></div>";
    gHtml += "</div>";
  } else {
    var lineLeft = kLeftMargin + hIndex * (kSceneWidth + kSceneHorizontalMargin) + kSceneWidth + kSceneHorizontalMargin / 2;
    var lineTop = kTopMargin + vIndex * (kSceneHeight + kSceneVerticalMargin) + kSceneHeight / 2;
    var lineHeight = height * (kSceneHeight + kSceneVerticalMargin);
    gHtml += ("<div style='position:absolute;left:" + lineLeft + ";top:" + lineTop + "px;height:" + lineHeight + "px;'>");
    gHtml += "<div class='line-vertical'></div>";
    gHtml += "</div>";

    var arrowLeft = kLeftMargin + hIndex * (kSceneWidth + kSceneHorizontalMargin) + kSceneWidth + kSceneHorizontalMargin / 2;
    var arrowWidth = kSceneHorizontalMargin / 2;
    var arrowTop = kTopMargin + (vIndex + height) * (kSceneHeight + kSceneVerticalMargin) + kSceneHeight / 2;
    gHtml += ("<div style='position:absolute;left:" + arrowLeft + "px;top:" + arrowTop + "px;width:" + arrowWidth + "px'>");
    gHtml += "<div class='arrow-right'></div>";
    gHtml += "</div>";
  }
}

function findSceneIndex(id) {

  for (var i = 0; i < gSceneList.length; i++) {
    if (gSceneList[i].id == id) {
      return i;
    }
  }
  return -1;
}

function findScene(id) {

  for (var i = 0; i < gSceneList.length; i++) {
    if (gSceneList[i].id == id) {
      return gSceneList[i];
    }
  }
  return null;
}

function onClickCloseDetail() {

  document.getElementById("dialog-base").style.visibility = "hidden";
  document.getElementById("detail").style.visibility = "hidden";
}


function onClickSave() {

  var diagramId = location.href.split("?")[1];

  var url = "srv.php?command=editdiagram";
  url += ("&diagramId=" + diagramId);
  url += ("&sceneId=" + gCurrentSceneId);
  url += ("&name=" + base64Encode(document.getElementById("scene-name").value));
  url += ("&description=" + base64Encode(document.getElementById("scene-description").value));
  url += ("&genre=" + document.getElementById("genre-select").selectedIndex);
  url += ("&type=" + document.getElementById("type-select").selectedIndex);
  url += ("&transition1=" + document.getElementById("transition1").value);
  url += ("&transition2=" + document.getElementById("transition2").value);
  url += ("&transition3=" + document.getElementById("transition3").value);
  url += ("&transition4=" + document.getElementById("transition4").value);
  url += ("&transition5=" + document.getElementById("transition5").value);

  httpPost(url, null, function(data) {
    try {
      var parsed = JSON.parse(data);
      if ((parsed) && (parsed.result === "0")) {
        refresh(function(result) {
          if (result) {
            document.getElementById("dialog-base").style.visibility = "hidden";
            document.getElementById("detail").style.visibility = "hidden";
          } else {
            alert("エラー");
          }
        });
      }
    } catch(e) {}
  });
}

function onClickScene(id) {

  // 新規作成
  if (id == -1) {
    var maxSceneId = -1;
    for (var i = 0; i < gSceneList.length; i++) {
      if (gSceneList[i].id > maxSceneId) {
        maxSceneId = gSceneList[i].id;
      }
    }
    gCurrentSceneId = maxSceneId + 1;
  }
  // 編集
  else {
    gCurrentSceneId = id;
  }

  document.getElementById("dialog-base").style.visibility = "visible";
  document.getElementById("detail").style.visibility = "visible";

  var sceneIndex = findSceneIndex(id);
  if (sceneIndex == -1) {
    document.getElementById("scene-name").value = "";
    document.getElementById("scene-description").value = "";
    document.getElementById("genre-select").selectedIndex = 0;
    ocument.getElementById("type-select").selectedIndex = 0;
    return;
  }
  var scene = gSceneList[sceneIndex];
  document.getElementById("scene-name").value = scene.name;
  document.getElementById("scene-description").value = scene.description;
  document.getElementById("genre-select").selectedIndex = scene.genre;
  document.getElementById("type-select").selectedIndex = scene.type;

  for (var i = 0; i < 5; i++) {
    var transIndex;
    if (i == 0) transIndex = findSceneIndex(scene.transition1);
    else if (i == 1) transIndex = findSceneIndex(scene.transition2);
    else if (i == 2) transIndex = findSceneIndex(scene.transition3);
    else if (i == 3) transIndex = findSceneIndex(scene.transition4);
    else transIndex = findSceneIndex(scene.transition5);

    var elementId = "transition" + (i + 1);
    if (transIndex == -1) {
      document.getElementById(elementId).selectedIndex = 0;
    } else {
      document.getElementById(elementId).selectedIndex = 1 + transIndex;
    }
  }
}

function onChangeGenre() {

  var genreIndex = document.getElementById("genre-select").selectedIndex;
  var typeSelect = document.getElementById("type-select");

  for (var i = typeSelect.options.length - 1; i >= 0; i--) {
    typeSelect.removeChild(typeSelect.options[i]);
  }

  var typeList = gGenreList[genreIndex][1];
  for (var i = 0; i < typeList.length; i++) {
    var option = document.createElement("option");
    option.setAttribute("value", i);
    option.innerHTML = typeList[i];
    typeSelect.appendChild(option);
  }
  typeSelect.selectedIndex = 0;

  changeDetailImage(genreIndex, 0);
}

function onChangeType() {

  var genreIndex = document.getElementById("genre-select").selectedIndex;
  var typeIndex = document.getElementById("type-select").selectedIndex;

  changeDetailImage(genreIndex, typeIndex);
}

function changeDetailImage(genre, type) {

  document.getElementById("detail-img").src = "img/diagram/scene" + genre + "-" + type + ".png";
}
