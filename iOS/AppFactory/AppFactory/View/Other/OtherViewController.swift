//
//  OtherViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class OtherViewController: UIViewController {

    private func showWebView(webPageType: WebPageType) {
        
        let webViewController = self.viewController(storyboard: "Common", identifier: "WebViewController") as! WebViewController
        webViewController.set(webPageType: webPageType)
        self.tabbarViewController()?.stack(viewController: webViewController, animationType: .horizontal)
    }

    @IBAction func onTapTerms(_ sender: Any) {
        self.showWebView(webPageType: .terms)
        
    }
    
    @IBAction func onTapEndedProject(_ sender: Any) {
        let endedProject = self.viewController(storyboard: "Other", identifier: "EndedProjectViewController") as! EndedProjectViewController
        self.tabbarViewController()?.stack(viewController: endedProject, animationType: .horizontal)
    }
}
