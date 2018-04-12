//
//  MessageViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/12.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class MessageViewController: UIViewController {
    
    @IBOutlet private weak var targetNameLabel: UILabel!
    @IBOutlet private weak var messageTextView: UITextView!
    @IBOutlet private weak var emailTextView: UITextField!
    @IBOutlet private weak var agreeTermsCheckBox: CheckBox!
    
    private var targetId = ""
    private var targetName = ""
    
    func set(targetId: String, targetName: String) {
        self.targetId = targetId
        self.targetName = targetName
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.targetNameLabel.text = self.targetName
    }
    
    private func showError(message: String) {
        let action = DialogAction(title: "OK", action: nil)
        Dialog.show(title: "エラー", message: message, actions: [action])
    }
    
    @IBAction func onTapSend(_ sender: Any) {
        
        guard let message = self.messageTextView.text,
            let email = self.emailTextView.text else {
                return
        }
        
        if message.count == 0 {
            self.showError(message: "メッセージが未入力です")
            return
        }
        if email.count == 0 {
            self.showError(message: "ご連絡先が未入力です")
            return
        }
        if self.agreeTermsCheckBox.getValue() == false {
            self.showError(message: "利用規約への同意が必要です")
            return
        }
        
        MessageRequester.send(sender: email, target: self.targetId, message: message, completion: { result in
            if result {
                
            } else {
                // TODO
            }
        })
    }
}
