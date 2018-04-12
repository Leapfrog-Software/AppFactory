//
//  ProgressViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class ProgressViewController: UIViewController {

    @IBAction func onTapSearch(_ sender: Any) {
        self.tabbarViewController()?.changeContents(index: 0)
    }
}
