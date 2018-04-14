//
//  UIColor+Original.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

extension UIColor {
    
    class var tabSelected: UIColor {
        return .black
    }
    
    class var tabUnselected: UIColor {
        return .lightGray
    }
    
    class var activeGreen: UIColor {
        return UIColor(red: 31 / 255, green: 203 / 255, blue: 169 / 255, alpha: 1.0)
    }
    
    class var activeGray: UIColor {
        return UIColor(red: 191 / 255, green: 191 / 255, blue: 191 / 255, alpha: 1.0)
    }
    
    class var placeholder: UIColor {
        return UIColor(red: 146 / 255, green: 146 / 255, blue: 146 / 255, alpha: 1)
    }
    
    class var dialogActionSuccess: UIColor {
        return UIColor(red: 123 / 255, green: 209 / 255, blue: 249 / 255, alpha: 1)
    }
    
    class var dialogActionError: UIColor {
        return UIColor(red: 230 / 255, green: 73 / 255, blue: 66 / 255, alpha: 1)
    }
}
