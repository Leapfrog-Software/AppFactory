//
//  WorksType.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/09.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

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
