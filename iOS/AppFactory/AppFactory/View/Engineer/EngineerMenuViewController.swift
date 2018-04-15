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
    @IBOutlet private weak var cost1ImageView: UIImageView!
    @IBOutlet private weak var cost2ImageView: UIImageView!
    @IBOutlet private weak var cost3ImageView: UIImageView!
    @IBOutlet private weak var cost4ImageView: UIImageView!
    @IBOutlet private weak var cost5ImageView: UIImageView!
    @IBOutlet private weak var cost1Label: UILabel!
    @IBOutlet private weak var cost2Label: UILabel!
    @IBOutlet private weak var cost3Label: UILabel!
    @IBOutlet private weak var cost4Label: UILabel!
    @IBOutlet private weak var cost5Label: UILabel!
    @IBOutlet private weak var work1ImageView: UIImageView!
    @IBOutlet private weak var work2ImageView: UIImageView!
    @IBOutlet private weak var work3ImageView: UIImageView!
    @IBOutlet private weak var work4ImageView: UIImageView!
    @IBOutlet private weak var work5ImageView: UIImageView!
    @IBOutlet private weak var work6ImageView: UIImageView!
    @IBOutlet private weak var work1Label: UILabel!
    @IBOutlet private weak var work2Label: UILabel!
    @IBOutlet private weak var work3Label: UILabel!
    @IBOutlet private weak var work4Label: UILabel!
    @IBOutlet private weak var work5Label: UILabel!
    @IBOutlet private weak var work6Label: UILabel!
    
    private var defaultWord = ""
    private var currentOrganization = OrganizationType.unspecified
    private var currentCost = CostType.unspecified
    private var currentWork = WorksType.unspecified
    
    private var completion: ((String, OrganizationType, CostType, WorksType) -> ())?
    
    private var checkOnImage: UIImage? {
        return UIImage(named: "check_on")
    }
    private var checkOffImage: UIImage? {
        return UIImage(named: "check_off")
    }
    
    func setDefault(word: String, organization: OrganizationType, cost: CostType, work: WorksType) {
        self.defaultWord = word
        self.currentOrganization = organization
        self.currentCost = cost
        self.currentWork = work
    }
    
    func setCompletion(_ completion: @escaping ((String, OrganizationType, CostType, WorksType) -> ())) {
        self.completion = completion
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.containerLeadingConstraint.constant = -self.containerWidthConstraint.constant
        
        self.wordTextField.text = self.defaultWord
        self.setOrganization()
        self.setCost()
        self.setWork()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        self.containerLeadingConstraint.constant = 0
        UIView.animate(withDuration: 0.2) {
            self.view.layoutIfNeeded()
        }
    }
    
    private func setOrganization() {
        
        self.organization1ImageView.image = (self.currentOrganization == .unspecified) ? self.checkOnImage : self.checkOffImage
        self.organization1Label.textColor = (self.currentOrganization == .unspecified) ? .activeGreen : .activeGray
        self.organization2ImageView.image = (self.currentOrganization == .corporation) ? self.checkOnImage : self.checkOffImage
        self.organization2Label.textColor = (self.currentOrganization == .corporation) ? .activeGreen : .activeGray
        self.organization3ImageView.image = (self.currentOrganization == .indivisual) ? self.checkOnImage : self.checkOffImage
        self.organization3Label.textColor = (self.currentOrganization == .indivisual) ? .activeGreen : .activeGray
    }
    
    private func setCost() {
        
        self.cost1ImageView.image = (self.currentCost == .unspecified) ? self.checkOnImage : self.checkOffImage
        self.cost1Label.textColor = (self.currentCost == .unspecified) ? .activeGreen : .activeGray
        self.cost2ImageView.image = (self.currentCost == .u100000) ? self.checkOnImage : self.checkOffImage
        self.cost2Label.textColor = (self.currentCost == .u100000) ? .activeGreen : .activeGray
        self.cost3ImageView.image = (self.currentCost == .u300000) ? self.checkOnImage : self.checkOffImage
        self.cost3Label.textColor = (self.currentCost == .u300000) ? .activeGreen : .activeGray
        self.cost4ImageView.image = (self.currentCost == .u1000000) ? self.checkOnImage : self.checkOffImage
        self.cost4Label.textColor = (self.currentCost == .u1000000) ? .activeGreen : .activeGray
        self.cost5ImageView.image = (self.currentCost == .o10000000) ? self.checkOnImage : self.checkOffImage
        self.cost5Label.textColor = (self.currentCost == .o10000000) ? .activeGreen : .activeGray
    }
    
    private func setWork() {
        
        self.work1ImageView.image = (self.currentWork == .unspecified) ? self.checkOnImage : self.checkOffImage
        self.work1Label.textColor = (self.currentWork == .unspecified) ? .activeGreen : .activeGray
        self.work2ImageView.image = (self.currentWork == .o10) ? self.checkOnImage : self.checkOffImage
        self.work2Label.textColor = (self.currentWork == .o10) ? .activeGreen : .activeGray
        self.work3ImageView.image = (self.currentWork == .o20) ? self.checkOnImage : self.checkOffImage
        self.work3Label.textColor = (self.currentWork == .o20) ? .activeGreen : .activeGray
        self.work4ImageView.image = (self.currentWork == .o30) ? self.checkOnImage : self.checkOffImage
        self.work4Label.textColor = (self.currentWork == .o30) ? .activeGreen : .activeGray
        self.work5ImageView.image = (self.currentWork == .o40) ? self.checkOnImage : self.checkOffImage
        self.work5Label.textColor = (self.currentWork == .o40) ? .activeGreen : .activeGray
        self.work6ImageView.image = (self.currentWork == .o50) ? self.checkOnImage : self.checkOffImage
        self.work6Label.textColor = (self.currentWork == .o50) ? .activeGreen : .activeGray
    }
    
    private func close() {
        
        self.containerLeadingConstraint.constant = -self.containerWidthConstraint.constant
        UIView.animate(withDuration: 0.2, animations: {
            self.view.layoutIfNeeded()
        }, completion: { _ in
            self.pop(animationType: .none)
        })
    }
    
    @IBAction func didExitWord(_ sender: Any) {
        self.view.endEditing(true)
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
    
    @IBAction func onTapCost1(_ sender: Any) {
        self.currentCost = .unspecified
        self.setCost()
    }
    
    @IBAction func onTapCost2(_ sender: Any) {
        self.currentCost = .u100000
        self.setCost()
    }

    @IBAction func onTapCost3(_ sender: Any) {
        self.currentCost = .u300000
        self.setCost()
    }
    
    @IBAction func onTapCost4(_ sender: Any) {
        self.currentCost = .u1000000
        self.setCost()
    }
    
    @IBAction func onTapCost5(_ sender: Any) {
        self.currentCost = .o10000000
        self.setCost()
    }
    
    @IBAction func onTapWork1(_ sender: Any) {
        self.currentWork = .unspecified
        self.setWork()
    }
    
    @IBAction func onTapWork2(_ sender: Any) {
        self.currentWork = .o10
        self.setWork()
    }
    
    @IBAction func onTapWork3(_ sender: Any) {
        self.currentWork = .o20
        self.setWork()
    }
    
    @IBAction func onTapWork4(_ sender: Any) {
        self.currentWork = .o30
        self.setWork()
    }
    
    @IBAction func onTapWork5(_ sender: Any) {
        self.currentWork = .o40
        self.setWork()
    }
    
    @IBAction func onTapWork6(_ sender: Any) {
        self.currentWork = .o50
        self.setWork()
    }
    
    @IBAction func onTapSearch(_ sender: Any) {
        self.completion?(self.wordTextField.text ?? "", self.currentOrganization, self.currentCost, self.currentWork)
        self.close()
    }

    @IBAction func onTapClose(_ sender: Any) {
        self.close()
    }
}
