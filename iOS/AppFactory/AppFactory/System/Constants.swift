//
//  Constants.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/08.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import Foundation

struct Constants {
    
    static let HttpTimeOutInterval = TimeInterval(10)
    static let ServerRootUrl = "http://appfac.net/"
    static let ServerApiFile = "srv.php"
    static let ServerEngineerImageDirectory = "data/img/user/"
    static let ServerWorkImageDirectory = "data/img/work/"
    static let StringEncoding = String.Encoding.utf8
    
    struct UserDefaultsKey {
        static let didShowTutorial = "didShowTutorial"
        static let pushSetting = "PushSetting"
    }
    
    struct WebPageUrl {
        static let Terms = "http://appfac.net/app/terms.html"
        static let Privacypolicy = "http://appfac.net/app/privacypolicy.html"
        static let Company = "http://lfg.co.jp"
    }
}
