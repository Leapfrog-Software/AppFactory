//
//  Dialog.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/12.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

struct DialogAction {
    let title: String
    let action: (() -> ())?
}

class Dialog: UIView {
    
    @IBOutlet private weak var titleLabel: UILabel!
    @IBOutlet private weak var messageLabel: UILabel!
    @IBOutlet private weak var buttonsStackView: UIStackView!
    
    class func show(title: String, message: String, actions: [DialogAction]) {
        
        guard let window = UIApplication.shared.keyWindow else {
            return
        }
        
        let dialog = UINib(nibName: "Dialog", bundle: nil).instantiate(withOwner: nil, options: nil).first as! Dialog
        window.addSubview(dialog)
        dialog.translatesAutoresizingMaskIntoConstraints = false
        dialog.topAnchor.constraint(equalTo: window.topAnchor).isActive = true
        dialog.leadingAnchor.constraint(equalTo: window.leadingAnchor).isActive = true
        dialog.trailingAnchor.constraint(equalTo: window.trailingAnchor).isActive = true
        dialog.bottomAnchor.constraint(equalTo: window.bottomAnchor).isActive = true
        
        dialog.configure(title: title, message: message, actions: actions)
        
        dialog.alpha = 0
        UIView.animate(withDuration: 0.1) {
            dialog.alpha = 1.0
        }
    }
    
    private func configure(title: String, message: String, actions: [DialogAction]) {
        
        self.titleLabel.text = title
        self.messageLabel.text = message
        
        actions.forEach { action in
            let button = UINib(nibName: "DialogButton", bundle: nil).instantiate(withOwner: nil, options: nil).first as! DialogButton
            button.configure(title: action.title, didTap: {
                action.action?()
            })
            self.buttonsStackView.addArrangedSubview(button)
        }
    }
}

