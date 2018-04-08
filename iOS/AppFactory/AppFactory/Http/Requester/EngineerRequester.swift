//
//  EngineerRequester.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/08.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import Foundation

enum OrganizationType {
    case unspecified
    case corporation
    case indivisual
    
    static func create(with: String) -> OrganizationType {
        switch with {
        case "1":
            return .corporation
        case "2":
            return .indivisual
        default:
            return .unspecified
        }
    }
    
    func toString() -> String {
        switch self {
        case .unspecified:
            return "0"
        case .corporation:
            return "1"
        case .indivisual:
            return "2"
        }
    }
}

enum CostType {
    case unsecified
    case u100000
    case u300000
    case u1000000
    case o10000000
    
    func toString() -> String {
        switch self {
        case .unsecified:
            return "0"
        case .u100000:
            return "1"
        case .u300000:
            return "2"
        case .u1000000:
            return "3"
        case .o10000000:
            return "4"
        }
    }
}

enum WorksType {
    case unspecified
    case o10
    case o20
    case o30
    case o40
    case o50
    
    func toString() -> String {
        switch self {
        case .unspecified:
            return "0"
        case .o10:
            return "1"
        case .o20:
            return "2"
        case .o30:
            return "3"
        case .o40:
            return "4"
        case .o50:
            return "5"
        }
    }
}

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
        self.organization = OrganizationType.create(with: data["organization"] as? String ?? "")
        self.profile = data["profile"] as? String ?? ""
        self.works = data["works"] as? Int ?? 0
        self.evaluate = data["evaluate"] as? Int ?? 0
    }
}

struct EngineerResponseData {
    let page: Int
    let total: Int
    let engineers: [EngineerData]
}

class EngineerRequester {
    
    class func getEngineer(page: Int, keyword: String, organization: OrganizationType, cost: CostType, works: WorksType, completion: @escaping ((Bool, EngineerResponseData?) -> ())) {
        
        var url = Constants.ServerUrl
        url += "?"
        url += ("command=getengineers")
        url += "&"
        url += ("page=" + "\(page)")
        url += "&"
        url += ("keyword=" + keyword)
        url += "&"
        url += ("organization=" + organization.toString())
        url += "&"
        url += ("cost=" + cost.toString())
        url += "&"
        url += ("work=" + works.toString())
        
        ApiManager.get(url: url, completion: { result, data in
            if result, let dic = data {
                if let page = dic["page"] as? Int, let total = dic["total"] as? Int, let engineers = dic["engineers"] as? Array<Dictionary<String, Any>> {
                    let engineerDataList = engineers.flatMap { EngineerData(data: $0) }
                    let response = EngineerResponseData(page: page, total: total, engineers: engineerDataList)
                    completion(true, response)
                    return
                }
            }
            completion(false, nil)
        })
    }
}
