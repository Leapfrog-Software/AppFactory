//
//  OtherViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class OtherViewController: UIViewController {
    
    @IBOutlet private weak var pushSettingSwitch: UISwitch!
    @IBOutlet private weak var versionLabel: UILabel!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let data = Bundle.main.infoDictionary! as Dictionary
        self.versionLabel.text = "アプリバージョン : " + (data["CFBundleShortVersionString"] as! String)
        
        self.pushSettingSwitch.isOn = SaveData.shared.pushSetting
    }
    
    private func showWebView(webPageType: WebPageType) {
        let webViewController = self.viewController(storyboard: "Common", identifier: "WebViewController") as! WebViewController
        webViewController.set(webPageType: webPageType)
        self.tabbarViewController()?.stack(viewController: webViewController, animationType: .horizontal)
    }
    
    @IBAction func onChangePush(_ sender: Any) {
        let saveData = SaveData.shared
        saveData.pushSetting = !saveData.pushSetting
        saveData.save()
    }

    @IBAction func onTapEndedProject(_ sender: Any) {
        let endedProject = self.viewController(storyboard: "Other", identifier: "EndedProjectViewController") as! EndedProjectViewController
        self.tabbarViewController()?.stack(viewController: endedProject, animationType: .horizontal)
    }
    
    @IBAction func onTapHowToUse(_ sender: Any) {
        let tutorial = self.viewController(storyboard: "Main", identifier: "TutorialViewController") as! TutorialViewController
        self.tabbarViewController()?.stack(viewController: tutorial, animationType: .none)
    }
    
    @IBAction func onTapTerms(_ sender: Any) {
        self.showWebView(webPageType: .terms)
    }
    
    @IBAction func onTapPrivacypolicy(_ sender: Any) {
        self.showWebView(webPageType: .privacypolicy)
    }
    
    @IBAction func onTapCompany(_ sender: Any) {
        self.showWebView(webPageType: .company)
    }
}
