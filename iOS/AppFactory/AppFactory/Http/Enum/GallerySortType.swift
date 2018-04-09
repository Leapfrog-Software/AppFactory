//
//  GallerySortType.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/09.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

enum GallerySortType {
    case evaluate
    case cost
    
    func toString() -> String {
        switch self {
        case .evaluate:
            return "0"
        case .cost:
            return "1"
        }
    }
}
