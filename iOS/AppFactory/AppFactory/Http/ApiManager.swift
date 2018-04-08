//
//  ApiManager.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/08.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import Foundation

class ApiManager {
    
    class func get(url: String, completion: @escaping ((Bool, Dictionary<String, Any>?) -> ())) {
        
        HttpManager.get(url: url) { result, data in
            if result, let data = data {
                do {
                    if let json = try JSONSerialization.jsonObject(with: data, options: JSONSerialization.ReadingOptions.allowFragments) as? Dictionary<String, Any> {
                        completion(true, json)
                        return
                    }
                } catch {}
            }
            completion(false, nil)
        }
    }
}
