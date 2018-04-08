//
//  SplashViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/08.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class SplashViewController: UIViewController {


    override func viewDidLoad() {
        super.viewDidLoad()
        
        EngineerRequester.getEngineer(page: 0, keyword: "", organization: .unspecified, cost: .unsecified, works: .unspecified, completion: { _, _ in
            
        })
    }
}
