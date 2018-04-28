//
//  EngineerRequester.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/08.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import Foundation

struct EngineerData {
    
    let id: String
    let name: String
    let area: String
    let organization: OrganizationType
    let profile: String
    let works: Int
    let evaluate: Int
    
    init?(data: Dictionary<String, Any>) {

        guard let id = data["id"] as? String else {
            return nil
        }
        self.id = id
        self.name = data["name"] as? String ?? ""
        self.area = data["area"] as? String ?? ""
        self.organization = OrganizationType.create(with: data["organization"] as? Int ?? 0)
        self.profile = (data["profile"] as? String ?? "").replacingOccurrences(of: "<br>", with: "\n")
        self.works = data["work"] as? Int ?? 0
        self.evaluate = data["evaluate"] as? Int ?? 0
    }
}

struct EngineerResponseData {
    let page: Int
    let total: Int
    let engineerList: [EngineerData]
    
    init?(data: Dictionary<String, Any>) {
        
        guard let page = data["page"] as? Int else {
            return nil
        }
        self.page = page
        
        guard let total = data["total"] as? Int else {
            return nil
        }
        self.total = total
        
        self.engineerList = (data["engineers"] as? Array<Dictionary<String, Any>>)?.flatMap { EngineerData(data: $0) } ?? []
    }
}

class EngineerRequester {
    
    class func get(page: Int, keyword: String, organization: OrganizationType, cost: CostType, works: WorksType, completion: @escaping ((EngineerResponseData?) -> ())) {
        
        var url = Constants.ServerRootUrl + Constants.ServerApiFile
        url += "?"
        url += ("command=getengineers")
        url += "&"
        url += ("page=" + "\(page)")
        url += "&"
        url += ("keyword=" + keyword)
        url += "&"
        url += ("organization=" + organization.toValue())
        url += "&"
        url += ("cost=" + cost.toString())
        url += "&"
        url += ("work=" + works.toString())
        
        ApiManager.get(url: url, completion: { result, data in
            if result, let dic = data {
                completion(EngineerResponseData(data: dic))
            } else {
                completion(nil)
            }
        })
    }
}
