//
//  SaveData.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/15.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import Foundation

class SaveData {
    
    static let shared = SaveData()
    
    var didShowTutorial = false
    var pushSetting = true
    
    init() {
        let userDefaults = UserDefaults()
        self.didShowTutorial = userDefaults.bool(forKey: Constants.UserDefaultsKey.didShowTutorial)
        self.pushSetting = userDefaults.bool(forKey: Constants.UserDefaultsKey.pushSetting)
    }
    
    func save() {
        
        let userDefaults = UserDefaults()
        
        userDefaults.set(self.didShowTutorial, forKey: Constants.UserDefaultsKey.didShowTutorial)
        userDefaults.set(self.pushSetting, forKey: Constants.UserDefaultsKey.pushSetting)
        
        userDefaults.synchronize()
    }
}
