//
//  CostType.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/09.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

enum CostType {
    case unspecified
    case u100000
    case u300000
    case u1000000
    case o10000000
    
    func toString() -> String {
        switch self {
        case .unspecified:
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
