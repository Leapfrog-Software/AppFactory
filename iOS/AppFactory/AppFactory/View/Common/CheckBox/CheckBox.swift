//
//  CheckBox.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/12.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class CheckBox: UIView {
    
    @IBOutlet private weak var checkImageView: UIImageView!
    
    private var isOn = false
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.setImage()
    }
    
    func setValue(isOn: Bool) {
        self.isOn = isOn
        self.setImage()
    }
    
    func getValue() -> Bool {
        return self.isOn
    }
    
    private func setImage() {
        let imageName = self.isOn ? "" : ""
        self.checkImageView.image = UIImage(named: imageName)
    }
    
    @IBAction func onTap(_ sender: Any) {
        self.isOn = !self.isOn
        self.setImage()
    }
}

