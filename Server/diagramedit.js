
var gSceneList = [
  ["ユーザー一覧", ["SNS", "マッチング", ""]],
  ["商品一覧", ["", ""]],
  ["ニュース一覧", ["", ""]],
  ["画像/文章一覧", ["", ""]],
  ["ユーザー詳細", ["", ""]],
  ["商品詳細", ["", ""]],
  ["ニュース詳細", ["", ""]],
  ["画像/文章投稿", ["", ""]],
  ["検索", ["", ""]],
  ["マイページ", ["", ""]],
  ["プロフィール編集", ["", ""]],
  ["ログイン", ["", ""]],
  ["新規登録", ["", ""]],
  ["同意画面", ["", ""]],
  ["スプラッシュ", ["", ""]],
  ["タブ画面", ["", ""]],
  ["メニュー", ["", ""]],
  ["チャット", ["", ""]],
  ["画像/動画再生", ["", ""]],
  ["画像/動画撮影", ["", ""]],
  ["地図", ["", ""]],
];

/*
画像
名前
性別
年齢
メッセージ
評価
*/

var gSelectedScene = 0;


function initialize() {

  var select = document.getElementById("genre-select");

  for (var i = 0; i < gSceneList.length; i++) {
    var option = document.createElement("option");
    option.setAttribute("value", i);
    option.innerHTML = gSceneList[i][0];
    select.appendChild(option);    
  }
}

function onClickCloseDetail() {

  document.getElementById("detail-base").style.visibility = "hidden";
  document.getElementById("detail").style.visibility = "hidden";

  alert(gSceneList[0][0]);
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
