//
//  EstimateRequester.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/08.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import Foundation

struct EstimateRequestData {
    let target: String
    let purpose: String
    let description: String
    let ios: Bool
    let android: Bool
    let chat: Bool
    let camera: Bool
    let movie: Bool
    let push: Bool
    let map: Bool
    let geofence: Bool
    let settle: Bool
    let credit: Bool
    let user: Bool
    let sns: Bool
    let notes: String
    let email: String
}

class EstimateRequester {
    
    class func send(requestData: EstimateRequestData, completion: @escaping ((Bool) -> ())) {
        
        var url = Constants.ServerUrl
        url += "?"
        url += ("command=estimate")
        url += "&"
        url += ("target=" + requestData.target)
        url += "&"
        url += ("purchase=" + (requestData.purpose.base64Encode() ?? ""))
        url += "&"
        url += ("description=" + (requestData.description.base64Encode() ?? ""))
        url += "&"
        url += ("ios=" + (requestData.ios ? "1" : "0"))
        url += "&"
        url += ("android=" + (requestData.android ? "1" : "0"))
        url += "&"
        url += ("chat=" + (requestData.chat ? "1" : "0"))
        url += "&"
        url += ("camera=" + (requestData.camera ? "1" : "0"))
        url += "&"
        url += ("movie=" + (requestData.movie ? "1" : "0"))
        url += "&"
        url += ("push=" + (requestData.push ? "1" : "0"))
        url += "&"
        url += ("map=" + (requestData.map ? "1" : "0"))
        url += "&"
        url += ("geofence=" + (requestData.geofence ? "1" : "0"))
        url += "&"
        url += ("settle=" + (requestData.settle ? "1" : "0"))
        url += "&"
        url += ("credit=" + (requestData.credit ? "1" : "0"))
        url += "&"
        url += ("user=" + (requestData.user ? "1" : "0"))
        url += "&"
        url += ("sns=" + (requestData.sns ? "1" : "0"))
        url += "&"
        url += ("notes=" + (requestData.notes.base64Encode() ?? ""))
        url += "&"
        url += ("email=" + (requestData.email.base64Encode() ?? ""))
        
        ApiManager.get(url: url, completion: { result, data in
            if result, let result = data?["result"] as? String, result == "0" {
                completion(true)
            } else {
                completion(false)
            }
        })
    }
}
