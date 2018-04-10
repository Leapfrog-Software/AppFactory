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
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
            let tabbarViewController = self.viewController(storyboard: "Main", identifier: "TabbarViewController") as! TabbarViewController
            self.stack(viewController: tabbarViewController, animationType: .none)
        }
    }
}
