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
    @IBOutlet private weak var wordTextField: UITextField!
    @IBOutlet private weak var organization1ImageView: UIImageView!
    @IBOutlet private weak var organization2ImageView: UIImageView!
    @IBOutlet private weak var organization3ImageView: UIImageView!
    @IBOutlet private weak var organization1Label: UILabel!
    @IBOutlet private weak var organization2Label: UILabel!
    @IBOutlet private weak var organization3Label: UILabel!
    
    private var currentOrganization = OrganizationType.unspecified

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
    
    private func setOrganization() {
        
        let chackOnImage = UIImage(named: "check_on")
        let chackOffImage = UIImage(named: "check_off")
        
        self.organization1ImageView.image = (self.currentOrganization == .unspecified) ? chackOnImage : chackOffImage
        self.organization1Label.textColor = (self.currentOrganization == .unspecified) ? .activeGreen : .activeGray
        self.organization2ImageView.image = (self.currentOrganization == .corporation) ? chackOnImage : chackOffImage
        self.organization2Label.textColor = (self.currentOrganization == .corporation) ? .activeGreen : .activeGray
        self.organization3ImageView.image = (self.currentOrganization == .indivisual) ? chackOnImage : chackOffImage
        self.organization3Label.textColor = (self.currentOrganization == .indivisual) ? .activeGreen : .activeGray
    }
    
    private func close() {
        
        self.containerLeadingConstraint.constant = -self.containerWidthConstraint.constant
        UIView.animate(withDuration: 0.2, animations: {
            self.view.layoutIfNeeded()
        }, completion: { _ in
            self.pop(animationType: .none)
        })
    }
    
    @IBAction func onTapOrganization1(_ sender: Any) {
        self.currentOrganization = .unspecified
        self.setOrganization()
    }
    
    @IBAction func onTapOrganization2(_ sender: Any) {
        self.currentOrganization = .corporation
        self.setOrganization()
    }
    
    @IBAction func onTapOrganization3(_ sender: Any) {
        self.currentOrganization = .indivisual
        self.setOrganization()
    }
    
    @IBAction func onTapSearch(_ sender: Any) {
        self.close()
    }

    @IBAction func onTapClose(_ sender: Any) {
        self.close()
    }
}
