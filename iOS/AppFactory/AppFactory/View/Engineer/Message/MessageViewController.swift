//
//  MessageViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/12.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class MessageViewController: KeyboardRespondableViewController {
    
    @IBOutlet private weak var engineerImageView: UIImageView!
    @IBOutlet private weak var targetNameLabel: UILabel!
    @IBOutlet private weak var messageTextView: UITextView!
    @IBOutlet private weak var emailTextView: UITextField!
    @IBOutlet private weak var agreeTermsImageView: UIImageView!
    @IBOutlet private weak var scrollViewBottomConstraint: NSLayoutConstraint!
    
    private var targetId = ""
    private var targetName = ""
    private var isAgree = false
    
    func set(targetId: String, targetName: String) {
        self.targetId = targetId
        self.targetName = targetName
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.initContents()
    }
    
    private func initContents() {
        
        let imageUrl = Constants.ServerRootUrl + Constants.ServerEngineerImageDirectory + self.targetId
        ImageStorage.shared.fetch(url: imageUrl, imageView: self.engineerImageView)

        self.targetNameLabel.text = self.targetName
    }
    
    override func keyboardDidChange(with: KeyboardAnimation) {

        self.scrollViewBottomConstraint.constant = with.height
        UIView.animate(withDuration: with.duration, delay: 0, options: with.curve, animations: {
            self.view.layoutIfNeeded()
        })
    }
    
    private func showError(message: String) {
        let action = DialogAction(title: "OK", action: nil)
        Dialog.show(style: .error, title: "エラー", message: message, actions: [action])
    }
    
    @IBAction func didExitEmail(_ sender: Any) {
        self.view.endEditing(true)
    }
    
    @IBAction func onTapAgree(_ sender: Any) {
        self.isAgree = !self.isAgree
        self.agreeTermsImageView.image = self.isAgree ? UIImage(named: "check_on") : UIImage(named: "check_off")
    }
    
    @IBAction func onTapTerms(_ sender: Any) {
        let webView = self.viewController(storyboard: "Common", identifier: "WebViewController") as! WebViewController
        webView.set(webPageType: .terms)
        self.stack(viewController: webView, animationType: .horizontal)
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
        if !self.isAgree {
            self.showError(message: "利用規約への同意が必要です")
            return
        }
        
        Loading.start()
        
        MessageRequester.send(sender: email, target: self.targetId, message: message, completion: { result in
            
            Loading.stop()
            
            if result {
                let action = DialogAction(title: "OK", action: {
                    if let engineerDetail = self.parent as? EngineerDetailViewController {
                        engineerDetail.pop(animationType: .horizontal)
                    }
                })
                Dialog.show(style: .success, title: "完了", message: "メッセージを送信しました", actions: [action])
            } else {
                self.showError(message: "通信に失敗しました")
            }
        })
    }
    
    @IBAction func onTapBack(_ sender: Any) {
        self.pop(animationType: .horizontal)
    }
}

extension MessageViewController: UITextViewDelegate {
    
    func textViewDidEndEditing(_ textView: UITextView) {
        self.view.endEditing(true)
    }
}

extension MessageViewController: UIScrollViewDelegate {
    
    func scrollViewWillBeginDragging(_ scrollView: UIScrollView) {
        self.view.endEditing(true)
    }
}
