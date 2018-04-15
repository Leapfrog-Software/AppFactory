//
//  CheckBox.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/12.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class CheckBoxRoowView: UIView {
    
    @IBInspectable var title: String = ""
    
    private weak var checkBox: CheckBox?
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        let checkBox = UINib(nibName: "CheckBox", bundle: nil).instantiate(withOwner: nil, options: nil).first as! CheckBox
        checkBox.configure(title: self.title)
        self.addSubview(checkBox)
        checkBox.translatesAutoresizingMaskIntoConstraints = false
        checkBox.topAnchor.constraint(equalTo: self.topAnchor).isActive = true
        checkBox.leadingAnchor.constraint(equalTo: self.leadingAnchor).isActive = true
        checkBox.trailingAnchor.constraint(equalTo: self.trailingAnchor).isActive = true
        checkBox.bottomAnchor.constraint(equalTo: self.bottomAnchor).isActive = true
        
        self.checkBox = checkBox
    }
    
    func setValue(isOn: Bool) {
        self.checkBox?.setValue(isOn: isOn)
    }
    
    func getValue() -> Bool {
        return self.checkBox?.getValue() ?? false
    }
}

class CheckBox: UIView {    
    
    @IBOutlet private weak var checkImageView: UIImageView!
    @IBOutlet private weak var titleLabel: UILabel!
    
    private var isOn = false
    
    func configure(title: String) {
        self.titleLabel.text = title
    }
    
    fileprivate func setValue(isOn: Bool) {
        self.isOn = isOn
        self.setImage()
    }
    
    fileprivate func getValue() -> Bool {
        return self.isOn
    }
    
    private func setImage() {
        let imageName = self.isOn ? "check_gray_on" : "check_gray_off"
        self.checkImageView.image = UIImage(named: imageName)
    }
    
    @IBAction func onTap(_ sender: Any) {
        self.isOn = !self.isOn
        self.setImage()
    }
}

