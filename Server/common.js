
function httpPost(url, param, callback){

    var xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Pragma', 'no-cache');
    xhr.setRequestHeader('Cache-Control', 'no-cache');
    xhr.setRequestHeader('If-Modified-Since', 'Thu, 01 Jun 1970 00:00:00 GMT');
    xhr.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded' );
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            //受信OK
            if (xhr.status == 200) {
                var rData = xhr.responseText;
                callback(rData);
            }
            //通信エラー
            else {
                callback(null);
            }
        }
    }
    xhr.send(param);
}


function setCookie(key, val){

  var cookies = '';
  cookies += key + '=' + encodeURIComponent(val) + '; ';

  var expire = new Date();
  expire.setTime( expire.getTime() + 1 * 1000 * 3600 * 24);
  expire.toUTCString();

  cookies += 'expires=' + expire+';';

  document.cookie = cookies;
}

function getCookie(key){

  var cookies = document.cookie.split(";");
  for(var i=0;i<cookies.length;i++){
    var keyVal = cookies[i].split("=");
    if(keyVal.length >= 2){
      var cookieKey = keyVal[0].replace(/ /g, "");
      if(cookieKey == key){
        return keyVal[1];
      }
    }
  }
  return "";
}

function organizationWord(type) {

    if (type == 1) {
      return "法人";
    } else if (type == 2) {
      return "個人";
    } else {
      return "-"
    }
}

function evaluateValue(data) {
  var val = String(data / 10);
  if (val.length >= 3) {
    return val.substr(0, 3);
  } else if (val.length == 2) {
    return val + "0";
  } else if (val.length == 1) {
    return val + ".0";
  } else {
    return "0.0";
  }
}

function base64Encode(str) {
  return btoa(unescape(encodeURIComponent(str)));
}

function base64Decode(str) {
  return decodeURIComponent(escape(atob(str)));
}
