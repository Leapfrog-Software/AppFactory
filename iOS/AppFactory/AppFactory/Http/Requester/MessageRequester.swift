//
//  MessageRequester.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/08.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import Foundation

class MessageRequester {
    
    class func send(sender: String, target: String, message: String, completion: @escaping ((Bool) -> ())) {
        
        var url = Constants.ServerUrl
        url += "?"
        url += ("command=sendmessage")
        url += "&"
        url += ("sender=" + sender)
        url += "&"
        url += ("target=" + target)
        url += "&"
        url += ("message=" + (message.base64Encode() ?? ""))
        
        ApiManager.get(url: url, completion: { result, data in
            if result, let result = data?["result"] as? String, result == "0" {
                completion(true)
            } else {
                completion(false)
            }
        })
    }
}
