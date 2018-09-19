<?php

const STRUCTURE_ENGINEER_DATA_COUNT = 5;
const STRUCTURE_GALLERY_DATA_COUNT = 9;
const STRUCTURE_USER_DATA_COUNT = 4;
const ENGINEER_COUNT_PER_PAGE = 20;
const GALLERY_COUNT_PER_PAGE = 24;

const DATAKEY_ENGINEER_ID = "id";
const DATAKEY_ENGINEER_NAME = "name";
const DATAKEY_ENGINEER_AREA = "area";
const DATAKEY_ENGINEER_ORGANIZATION = "organization";
const DATAKEY_ENGINEER_PROFILE = "profile";

const DATAKEY_WORK_ID = "id";
const DATAKEY_WORK_NAME = "name";
const DATAKEY_WORK_ENGINEER_ID = "engineerId";
const DATAKEY_WORK_OS = "os";
const DATAKEY_WORK_LANGUAGE = "language";
const DATAKEY_WORK_FUNCTION = "function";
const DATAKEY_WORK_COST = "cost";
const DATAKEY_WORK_EVALUATE = "evaluate";
const DATAKEY_EXIST_SOURCE = "existSource";

const DATAKEY_USER_ID = "id";
const DATAKEY_USER_NAME = "name";
const DATAKEY_USER_EMAIL = "email";
const DATAKEY_USER_PASSWORD = "password";

const ERROR_NONE = 0;
const ERROR_UNAUTH = 1;
const ERROR_INVALID_CHAR = 2;
const ERROR_INVALID_LENGTH = 3;
const ERROR_EXIST = 4;

$command = $_GET["command"];

if (strcmp($command, "getengineers") == 0) {
	getEngineers();
} else if (strcmp($command, "getengineerdetail") == 0) {
	getEngineerDetail();
} else if (strcmp($command, "getgalleries") == 0) {
	getGalleries();
} else if (strcmp($command, "register") == 0) {
	register();
} else if (strcmp($command, "login") == 0) {
	login();
} else if (strcmp($command, "join") == 0) {
	joinMember();
} else if (strcmp($command, "sendmessage") == 0) {
	sendMessage();
} else if (strcmp($command, "estimate") == 0) {
	estimate();
} else if (strcmp($command, "creatediagram") == 0) {
	creatediagram();
} else {
  echo("unknown");
}

function getEngineers() {

	$page = intval($_GET["page"]);
  $keyword = $_GET["keyword"];
  $organization = $_GET["organization"];
  $cost = $_GET["cost"];
  $work = $_GET["work"];

  $engineerList = readEngineersFile();
	$filterred = array();

	$workList = readWorksFile();

	for ($i=0;$i<count($engineerList);$i++) {
		// キーワードでフィルタ
		$decodedKeyword = decodeBase64($keyword);
		if (strlen($decodedKeyword) > 0) {
			if ((strpos($engineerList[$i][DATAKEY_ENGINEER_NAME], $decodedKeyword) === false)
      && (strpos($engineerList[$i][DATAKEY_ENGINEER_PROFILE], $decodedKeyword) === false)) {
        continue;
      }
		}
		// 組織でフォルタ
		if ($organization != 0) {
			if ($engineerList[$i][DATAKEY_ENGINEER_ORGANIZATION] != $organization) {
				continue;
			}
		}

		$filterredWorks = Array();
		for ($j=0;$j<count($workList);$j++) {
			if (strcmp($workList[$j][DATAKEY_WORK_ENGINEER_ID], $engineerList[$i][DATAKEY_ENGINEER_ID]) == 0) {
				$filterredWorks[] = $workList[$j];
			}
		}
		// 予算でフィルタ
		if ($cost != 0) {
			$hit = false;
			for ($j=0;$j<count($filterredWorks);$j++) {
				$costData = $filterredWorks[$j][DATAKEY_WORK_COST];
				if (($cost == 1) && ($costData <= 100000)) {
					$hit = true;
					break;
				} else if (($cost == 2) && ($costData >= 100000) && ($costData <= 300000)) {
					$hit = true;
					break;
				} else if (($cost == 3) && ($costData >= 300000) && ($costData <= 1000000)) {
					$hit = true;
					break;
				} else if (($cost == 4) && ($costData >= 1000000)) {
					$hit = true;
					break;
				}
			}
			if ($hit == false) {
				continue;
			}
		}
		// 実績でフィルタ
		if ($work != 0) {
			$count = count($filterredWorks);
			if (($work == 1) && ($count < 10)) {
				continue;
			} else if (($work == 2) && ($count < 20)) {
				continue;
			} else if (($work == 3) && ($count < 30)) {
				continue;
			} else if (($work == 4) && ($count < 40)) {
				continue;
			} else if (($work == 5) && ($count < 50)) {
				continue;
			}
		}

		$filterred[] = $engineerList[$i];
	}
	// ページング
	$pagedList = Array();
	for ($i = $page * ENGINEER_COUNT_PER_PAGE; $i < ($page + 1) * ENGINEER_COUNT_PER_PAGE; $i++) {
		if (count($filterred) > $i) {
			$pagedList[] = $filterred[$i];
		}
	}

	$engineerObjs = Array();
	for ($i=0;$i<count($pagedList);$i++) {
		$works = Array();
		for ($j=0;$j<count($workList);$j++) {
			if (strcmp($workList[$j][DATAKEY_WORK_ENGINEER_ID], $pagedList[$i][DATAKEY_ENGINEER_ID]) == 0) {
				$works[] = $workList[$j];
			}
		}
		// 実績数
		$workCount = count($works);
		// 評価
		$evaluate = 0;
		if (count($works) > 0) {
			for ($j=0;$j<count($works);$j++) {
				$evaluate += $works[$j][DATAKEY_WORK_EVALUATE];
			}
			$evaluate = $evaluate / count($works);
		}

		$engineerObjs[] = Array(
			"id" => $pagedList[$i][DATAKEY_ENGINEER_ID],
			"name" => $pagedList[$i][DATAKEY_ENGINEER_NAME],
			"area" => $pagedList[$i][DATAKEY_ENGINEER_AREA],
			"organization" => $pagedList[$i][DATAKEY_ENGINEER_ORGANIZATION],
			"profile" => $pagedList[$i][DATAKEY_ENGINEER_PROFILE],
			"work" => $workCount,
			"evaluate" => $evaluate
		);
	}

	$retObj = Array(
		"page" => $page,
		"total" => count($filterred),
		"engineers" => $engineerObjs
	);
	echo(json_encode($retObj, JSON_UNESCAPED_UNICODE));
}

function getEngineerDetail() {

	$engineerId = $_GET["id"];

	$engineerList = readEngineersFile();
	$engineerData = null;
	for ($i=0;$i<count($engineerList);$i++) {
		if (strcmp($engineerList[$i][DATAKEY_ENGINEER_ID], $engineerId) == 0) {
			$engineerData = $engineerList[$i];
			break;
		}
	}
	if ($engineerData == null) {
		echo("");
		exit;
	}

	$workList = readWorksFile();
	$works = Array();
	for ($i=0;$i<count($workList);$i++) {
		if (strcmp($workList[$i][DATAKEY_WORK_ENGINEER_ID], $engineerId) == 0) {
			$works[] = $workList[$i];
		}
	}
	$worksObjs = Array();
	for ($i=0;$i<count($works);$i++) {
		$worksObjs[] = Array(
			"id" => $works[$i][DATAKEY_WORK_ID],
			"name" => $works[$i][DATAKEY_WORK_NAME],
			"os" => $works[$i][DATAKEY_WORK_OS],
			"language" => $works[$i][DATAKEY_WORK_LANGUAGE],
			"function" => $works[$i][DATAKEY_WORK_FUNCTION],
			"cost" => $works[$i][DATAKEY_WORK_COST],
			"evaluate" => $works[$i][DATAKEY_WORK_EVALUATE],
			"source" => $works[$i][DATAKEY_EXIST_SOURCE]
		);
	}

	// 評価を算出
	$evaluate = 0;
	if (count($works) > 0) {
		for ($i=0;$i<count($works);$i++) {
			$evaluate += $works[$i][DATAKEY_WORK_EVALUATE];
		}
		$evaluate = $evaluate / count($works);
	}

	$retObj = Array(
		"id" => $engineerData[DATAKEY_ENGINEER_ID],
		"name" => $engineerData[DATAKEY_ENGINEER_NAME],
		"area" => $engineerData[DATAKEY_ENGINEER_AREA],
		"organization" => $engineerData[DATAKEY_ENGINEER_ORGANIZATION],
		"evaluate" => $evaluate,
		"profile" => $engineerData[DATAKEY_ENGINEER_PROFILE],
		"works" => $worksObjs
	);
	echo(json_encode($retObj, JSON_UNESCAPED_UNICODE));
}

function getGalleries() {

	$page = intval($_GET["page"]);
	$sort = intval($_GET["sort"]);

	$galleryList = readWorksFile();

	$total = ceil(count($galleryList) / GALLERY_COUNT_PER_PAGE);

	// ソート
	if ($sort == 0) {
		$sortValue = Array();
		foreach ($galleryList as $key => $value) {
			$sortValue[$key] = $value[DATAKEY_WORK_EVALUATE];
		}
		array_multisort($sortValue, SORT_DESC, $galleryList);
	} else {
		$sortValue = Array();
		foreach ($galleryList as $key => $value) {
			$sortValue[$key] = $value[DATAKEY_WORK_COST];
		}
		array_multisort($sortValue, SORT_ASC, $galleryList);
	}

	$pagedList = Array();
	for ($i = $page * GALLERY_COUNT_PER_PAGE; $i < ($page + 1) * GALLERY_COUNT_PER_PAGE; $i++) {
		if (count($galleryList) > $i) {
			$pagedList[] = $galleryList[$i];
		}
	}

	$engineerList = readEngineersFile();

	$galleryObjs = Array();
	for ($i=0;$i<count($pagedList);$i++) {

		$engineerId = $pagedList[$i][DATAKEY_WORK_ENGINEER_ID];
		$engineerData = null;
		for ($j=0;$j<count($engineerList);$j++) {
			if (strcmp($engineerList[$j][DATAKEY_ENGINEER_ID], $engineerId) == 0) {
				$engineerData = $engineerList[$j];
				break;
			}
		}
		if ($engineerData == null) {
			continue;
		}

		$galleryObjs[] = Array(
			"id" => $pagedList[$i][DATAKEY_WORK_ID],
			"name" => $pagedList[$i][DATAKEY_WORK_NAME],
			"engineerId" => $engineerId,
			"engineerName" => $engineerData[DATAKEY_ENGINEER_NAME],
			"engineerArea" => $engineerData[DATAKEY_ENGINEER_AREA],
			"engineerOrganization" => $engineerData[DATAKEY_ENGINEER_ORGANIZATION],
			"cost" => $pagedList[$i][DATAKEY_WORK_COST],
			"evaluate" => $pagedList[$i][DATAKEY_WORK_EVALUATE]
		);
	}

	$retObj = Array(
		"page" => $page,
		"total" => $total,
		"galleryList" => $galleryObjs
	);

	echo(json_encode($retObj, JSON_UNESCAPED_UNICODE));
}

function register() {

	$email = decodeBase64($_GET["email"]);
	$password = decodeBase64($_GET["password"]);
	$name = decodeBase64($_GET["name"]);

	// 不正文字検出
	if ((strpos($email, ",") !== false)
	|| (strpos($email, "\n") !== false)
	|| (strpos($email, "\r") !== false)
	|| (strpos($password, ",") !== false)
	|| (strpos($password, "\n") !== false)
	|| (strpos($passwotd, "\r") !== false)
	|| (strpos($name, ",") !== false)
	|| (strpos($name, "\n") !== false)
	|| (strpos($name, "\r") !== false)) {
		$retObj = Array("result" => ERROR_INVALID_CHAR);
		echo(json_encode($retObj, JSON_UNESCAPED_UNICODE));
		exit;
	}

	// 文字数制限
	if ((strlen($email) == 0) || (strlen($email) > 255)
	|| (strlen($password) == 0) || (strlen($password) > 255)
	|| (strlen($name) == 0) || (strlen($name) > 255)) {
		$retObj = Array("result" => ERROR_INVALID_LENGTH);
		echo(json_encode($retObj, JSON_UNESCAPED_UNICODE));
		exit;
	}

	$userList = readUsersFile();
	$maxUserId = 0;
	for ($i=0;$i<count($userList);$i++) {
		if (strcmp($userList[$i][DATAKEY_USER_EMAIL], $email) == 0) {
			$retObj = Array("result" => ERROR_EXIST);
			echo(json_encode($retObj, JSON_UNESCAPED_UNICODE));
			exit;
		}
		if ($userList[$i][DATAKEY_USER_ID] > $maxUserId) {
			$maxUserId = $userList[$i][DATAKEY_USER_ID];
		}
	}

	$userId = $maxUserId + 1;
	$newUserData = (string)$userId . "," . $name . "," . $email . "," . $password . "\n";
	file_put_contents("data/users.txt", $newUserData, FILE_APPEND);

	$retObj = Array("result" => ERROR_NONE, "id" => $userId);
	echo(json_encode($retObj, JSON_UNESCAPED_UNICODE));

	// メール送信
	$subject = "【エンジニアナビ】新規登録完了のお知らせ";
	$message = $name . " 様\r\n\r\n";
	$message .= "新規登録完了のお知らせ\r\n\r\n";
	$message .= "この度はエンジニアナビへご登録頂き誠にありがとうございます。\r\n";
	$message .= "サービスへの登録が完了し、ご利用頂く準備が整いましたので、その旨をお知らせ致します。\r\n\r\n";
	$message .= "ご利用に関してご不明な点がありましたら、本メールへの返信にてご連絡ください\r\n\r\n";
	$message .= "今後ともエンジニアナビをよろしくお願い致します。\r\n\r\n";
	$message .= "株式会社Leapfrog\r\n";
	$headers = "From: info@lfg.co.jp" . "\r\n";
	sendMail($email, $subject, $message, $headers);
}

function login() {

	$email = decodeBase64($_GET["email"]);
	$password = decodeBase64($_GET["password"]);

	$userList = readUsersFile();
	for ($i=0;$i<count($userList);$i++) {
		if (strcmp($userList[$i][DATAKEY_USER_EMAIL], $email) == 0) {
			if (strcmp($userList[$i][DATAKEY_USER_PASSWORD], $password) == 0) {
				$userId = $userList[$i][DATAKEY_USER_ID];
				$userName = $userList[$i][DATAKEY_USER_NAME];
				$retObj = Array("result" => ERROR_NONE, "id" => $userId, "name" => $userName);
				echo(json_encode($retObj, JSON_UNESCAPED_UNICODE));
				exit;
			}
		}
	}
	$retObj = Array("result" => ERROR_UNAUTH);
	echo(json_encode($retObj, JSON_UNESCAPED_UNICODE));
}

function joinMember() {

	$email = decodeBase64($_GET["email"]);
	$message = decodeBase64($_GET["message"]);

	// メール送信
	$subject = "開発者登録のメッセージが届きました";
	$body = "";
	$body .= ("メールアドレス: " . $email . "\r\n");
	$body .= ("メッセージ: " . $message . "\r\n");
	$headers = "From: info@lfg.co.jp" . "\r\n";

	sendMail("leapfrog.noreck@gmail.com", $subject, $body, $headers);
	sendMail("sfujita@lfg.co.jp", $subject, $body, $headers);

	$retObj = Array("result" => "0");
	echo(json_encode($retObj, JSON_UNESCAPED_UNICODE));
}

function sendMessage() {

		$sender = $_GET["sender"];
		$target = $_GET["target"];
		$message = $_GET["message"];

		$messageData = $sender . "," . $target . "," . $message . "\n";
		file_put_contents("data/message.txt", $messageData, FILE_APPEND);

		$subject = "アプリファクトリーからメッセージが届きました";
		$body = "";
		$body .= ("送信者: " . $sender . "\n");
		$body .= ("受信者: " . $target . "\n");
		$body .= ("メッセージ: " . decodeBase64($message) . "\n");
		$headers = "From: info@lfg.co.jp" . "\r\n";
		sendMail("leapfrog.noreck@gmail.com", $subject, $body, $headers);
		sendMail("sfujita@lfg.co.jp", $subject, $body, $headers);

		$retObj = Array("result" => "0");
		echo(json_encode($retObj, JSON_UNESCAPED_UNICODE));
}

function estimate() {

	$target = $_GET["target"];
	$purpose = decodeBase64($_GET["purpose"]);
	$description = decodeBase64($_GET["description"]);
	$ios = $_GET["ios"];
	$android = $_GET["android"];
	$chat = $_GET["chat"];
	$camera = $_GET["camera"];
	$movie = $_GET["movie"];
	$push = $_GET["push"];
	$map = $_GET["map"];
	$geofence = $_GET["geofence"];
	$settle = $_GET["settle"];
	$credit = $_GET["credit"];
	$user = $_GET["user"];
	$sns = $_GET["sns"];
	$notes = decodeBase64($_GET["notes"]);
	$email = decodeBase64($_GET["email"]);

	$fileData = "";
	$fileData .= ($target . ",");
	$fileData .= ($purpose . ",");
	$fileData .= ($description . ",");
	$fileData .= ($ios . ",");
	$fileData .= ($android . ",");
	$fileData .= ($chat . ",");
	$fileData .= ($camera . ",");
	$fileData .= ($movie . ",");
	$fileData .= ($push . ",");
	$fileData .= ($map . ",");
	$fileData .= ($geofence . ",");
	$fileData .= ($settle . ",");
	$fileData .= ($credit . ",");
	$fileData .= ($user . ",");
	$fileData .= ($sns . ",");
	$fileData .= ($notes . ",");
	$fileData .= ($email . ",");
	$fileData .= "\n";

	file_put_contents("data/estimate.txt", $fileData, FILE_APPEND);

	$subject = "アプリファクトリーから見積もり依頼が届きました";
	$body = "";
	$body .= ("送信者: " . $email . "\n");
	$body .= ("受信者: " . $target . "\n");
	$body .= ("メッセージ: " . $fileData . "\n");
	$headers = "From: info@lfg.co.jp" . "\r\n";
	sendMail("leapfrog.noreck@gmail.com", $subject, $body, $headers);
	sendMail("sfujita@lfg.co.jp", $subject, $body, $headers);

	echo(json_encode(Array("result" => "0")));
}

function createDiagram() {
	echo(json_encode(Array("result" => "0", "id" => "3")));
}

function readEngineersFile() {

  $rawData = file(__DIR__ . '/data/engineers.txt');

  $ret = array();
  for($i=1;$i<count($rawData);$i++){
    $exploded = explode(",", rtrim($rawData[$i], "\n"));
    if (count($exploded) == STRUCTURE_ENGINEER_DATA_COUNT) {
      $idData = $exploded[0];
      $nameData = $exploded[1];
      $areaData = $exploded[2];
      $organizationData = intval($exploded[3]);
      $profileData = $exploded[4];

      $ret[] = Array(
        DATAKEY_ENGINEER_ID => $idData,
        DATAKEY_ENGINEER_NAME => $nameData,
        DATAKEY_ENGINEER_AREA => $areaData,
        DATAKEY_ENGINEER_ORGANIZATION => $organizationData,
        DATAKEY_ENGINEER_PROFILE => $profileData
      );
    }
  }
  return $ret;
}

function readWorksFile() {

  $rawData = file(__DIR__ . '/data/works.txt');

  $ret = array();
  for($i=1;$i<count($rawData);$i++){
    $exploded = explode(",", rtrim($rawData[$i], "\n"));
    if (count($exploded) == STRUCTURE_GALLERY_DATA_COUNT) {
      $idData = $exploded[0];
      $nameData = $exploded[1];
      $engineerIdData = $exploded[2];
			$osData = $exploded[3];
			$languageData = $exploded[4];
			$functionData = $exploded[5];
      $costData = intval($exploded[6]);
      $evaluateData = intval($exploded[7]);
			$existSource = intval($exploded[8]);

      $ret[] = Array(
        DATAKEY_WORK_ID => $idData,
        DATAKEY_WORK_NAME => $nameData,
        DATAKEY_WORK_ENGINEER_ID => $engineerIdData,
				DATAKEY_WORK_OS => $osData,
				DATAKEY_WORK_LANGUAGE => $languageData,
				DATAKEY_WORK_FUNCTION => $functionData,
        DATAKEY_WORK_COST => $costData,
        DATAKEY_WORK_EVALUATE => $evaluateData,
				DATAKEY_EXIST_SOURCE => $existSource
      );
		}
  }
  return $ret;
}

function readUsersFile() {

  $rawData = file(__DIR__ . '/data/users.txt');

  $ret = array();
  for($i=1;$i<count($rawData);$i++){
    $exploded = explode(",", rtrim($rawData[$i], "\n"));
    if (count($exploded) == STRUCTURE_USER_DATA_COUNT) {
      $idData = intval($exploded[0]);
      $nameData = $exploded[1];
      $emailData = $exploded[2];
      $passwordData = $exploded[3];

      $ret[] = Array(
        DATAKEY_USER_ID => $idData,
        DATAKEY_USER_NAME => $nameData,
        DATAKEY_USER_EMAIL => $emailData,
        DATAKEY_USER_PASSWORD => $passwordData
      );
    }
  }
  return $ret;
}

function decodeBase64($str) {

	$val = str_replace(array("_", "-", " "), array("+", "/", "+"), $str);
	return base64_decode($val);
}

function sendMail($to, $subject, $message, $headers) {

	mb_language("Japanese");
	mb_internal_encoding("UTF-8");
	mb_send_mail($to, $subject, $message, $headers);
}

function debugSave($str) {

	date_default_timezone_set('Asia/Tokyo');

	$date = date('Y-m-d H:i:s');
	$text = $date . " " . $str . "\n";
	file_put_contents("debug.txt", $text, FILE_APPEND);
}

?>
