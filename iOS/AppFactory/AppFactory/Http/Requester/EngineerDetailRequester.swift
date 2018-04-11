//
//  EngineerDetailRequester.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/09.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import Foundation

struct EngineerDetailWorkData {
    
    let id: String
    let name: String
    let os: String
    let language: String
    let function: String
    let cost: Int
    let evaluate: Int
    let source: Bool
    
    init?(data: Dictionary<String, Any>) {

        guard let id = data["id"] as? String else {
            return nil
        }
        self.id = id
        self.name = data["name"] as? String ?? ""
        self.os = data["os"] as? String ?? ""
        self.language = data["language"] as? String ?? ""
        self.function = data["function"] as? String ?? ""
        self.cost = data["cost"] as? Int ?? 0
        self.evaluate = data["evaluate"] as? Int ?? 0
        self.source = data["source"] as? Bool ?? false
    }
}

struct EngineerDetailResponseData {
    
    let id: String
    let name: String
    let area: String
    let organization: OrganizationType
    let evaluate: Int
    let profile: String
    let works: [EngineerDetailWorkData]
    
    init?(data: Dictionary<String, Any>) {

        guard let id = data["id"] as? String else {
            return nil
        }
        self.id = id
        
        self.name = data["name"] as? String ?? ""
        self.area = data["area"] as? String ?? ""
        self.organization = OrganizationType.create(with: data["organization"] as? Int ?? 0)
        self.evaluate = data["evaluate"] as? Int ?? 0
        self.profile = data["profile"] as? String ?? ""
        self.works = (data["works"] as? Array<Dictionary<String, Any>>)?.flatMap { EngineerDetailWorkData(data: $0) } ?? []
    }
}

class EngineerDetailRequester {
    
    class func get(id: String, completion: @escaping ((EngineerDetailResponseData?) -> ())) {
        
        var url = Constants.ServerRootUrl + Constants.ServerApiFile
        url += "?"
        url += ("command=getengineerdetail")
        url += "&"
        url += ("id=" + id)
        
        ApiManager.get(url: url, completion: { result, data in
            if result, let dic = data {
                completion(EngineerDetailResponseData(data: dic))
            } else {
                completion(nil)
            }
        })
    }
}
