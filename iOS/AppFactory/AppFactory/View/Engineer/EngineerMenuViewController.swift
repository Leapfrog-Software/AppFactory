//
//  EngineerMenuViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class EngineerMenuViewController: UIViewController {

    @IBOutlet private weak var containerLeadingConstraint: NSLayoutConstraint!
    @IBOutlet private weak var containerWidthConstraint: NSLayoutConstraint!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.containerLeadingConstraint.constant = -self.containerWidthConstraint.constant
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        self.containerLeadingConstraint.constant = 0
        UIView.animate(withDuration: 0.2) {
            self.view.layoutIfNeeded()
        }
    }
    
    private func close() {
        
        self.containerLeadingConstraint.constant = -self.containerWidthConstraint.constant
        UIView.animate(withDuration: 0.2, animations: {
            self.view.layoutIfNeeded()
        }, completion: { _ in
            self.pop(animationType: .none)
        })
    }
    
    @IBAction func onTapSearch(_ sender: Any) {
        self.close()
    }

    @IBAction func onTapClose(_ sender: Any) {
        self.close()
    }
}
