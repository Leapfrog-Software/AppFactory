//
//  GalleryRequester.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/08.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import Foundation

struct GalleryData {
    
    let id: String
    let name: String
    let engineerId: String
    let engineerArea: String
    let engineerOrganization: OrganizationType
    let cost: Int
    let evaluate: Int
    
    init?(data: Dictionary<String, Any>) {

        guard let id = data["id"] as? String else {
            return nil
        }
        self.id = id
        
        self.name = data["name"] as? String ?? ""
        self.engineerId = data["engineerId"] as? String ?? ""
        self.engineerArea = data["engineerArea"] as? String ?? ""
        self.engineerOrganization = OrganizationType.create(with: data["engineerOrganization"] as? Int ?? 0)
        self.cost = data["cost"] as? Int ?? 0
        self.evaluate = data["evaluate"] as? Int ?? 0
    }
}

struct GalleryResponseData {
    let page: Int
    let total: Int
    let galleryList: [GalleryData]
    
    init?(data: Dictionary<String, Any>) {
        
        guard let page = data["page"] as? Int else {
            return nil
        }
        self.page = page
        
        guard let total = data["total"] as? Int else {
            return nil
        }
        self.total = total
        
        self.galleryList = (data["galleryList"] as? Array<Dictionary<String, Any>>)?.flatMap { GalleryData(data: $0) } ?? []
    }
}

class GalleryRequester {
    
    class func get(sort: GallerySortType, page: Int, completion: @escaping ((GalleryResponseData?) -> ())) {
        
        var url = Constants.ServerRootUrl + Constants.ServerApiFile
        url += "?"
        url += ("command=getgalleries")
        url += "&"
        url += ("sort=" + sort.toString())
        url += "&"
        url += ("page=" + "\(page)")
        
        ApiManager.get(url: url, completion: { result, data in
            if result, let dic = data {
                completion(GalleryResponseData(data: dic))
            } else {
                completion(nil)
            }
        })
    }
}
