//
//  DialogButton.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/12.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class DialogButton: UIView {
    
    @IBOutlet private weak var titleLabel: UILabel!
    
    private var didTap: (() -> ())?
    
    func configure(title: String, didTap: @escaping (() -> ())) {
        self.titleLabel.text = title
        self.didTap = didTap
    }
    
    @IBAction func onTap(_ sender: Any) {
        self.didTap?()
    }
}

