//
//  WebViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/11.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit
import WebKit

enum WebPageType {
    case terms
    
    func toUrl() -> URL {

        let urlString: String
        
        switch self {
        case .terms:
            urlString = Constants.WebPageUrl.Terms
        }
        
        return URL(string: urlString)!
    }
}

class WebViewController: UIViewController {

    @IBOutlet private weak var containerView: UIView!

    private var webPageType: WebPageType!
    
    func set(webPageType: WebPageType) {
        self.webPageType = webPageType
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let webView = WKWebView()
        self.containerView.addSubview(webView)
        webView.translatesAutoresizingMaskIntoConstraints = false
        webView.topAnchor.constraint(equalTo: self.containerView.topAnchor).isActive = true
        webView.leadingAnchor.constraint(equalTo: self.containerView.leadingAnchor).isActive = true
        webView.trailingAnchor.constraint(equalTo: self.containerView.trailingAnchor).isActive = true
        webView.bottomAnchor.constraint(equalTo: self.containerView.bottomAnchor).isActive = true
        
        let request = URLRequest(url: self.webPageType.toUrl(), cachePolicy: .reloadIgnoringLocalAndRemoteCacheData, timeoutInterval: Constants.HttpTimeOutInterval)
        webView.load(request)
    }
    
    
    @IBAction func onTapBack(_ sender: Any) {
        self.pop(animationType: .horizontal)
    }
}
