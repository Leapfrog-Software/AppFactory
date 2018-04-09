//
//  OrganizationType.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/09.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

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
