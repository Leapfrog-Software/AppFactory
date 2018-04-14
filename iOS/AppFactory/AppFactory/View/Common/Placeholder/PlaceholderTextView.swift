//
//  PlaceholderTextView.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/14.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class PlaceholderTextView: UITextView {
    
    @IBInspectable var placeholder: String = ""
    
    private weak var placeholderLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        let placeholderLabel = UILabel(frame: CGRect(x: 8, y: 10, width: self.frame.size.width - 20, height: self.font?.pointSize ?? 0))
        placeholderLabel.text = self.placeholder
        placeholderLabel.textColor = .placeholder
        placeholderLabel.font = self.font
        placeholderLabel.isUserInteractionEnabled = false
        self.addSubview(placeholderLabel)
        self.placeholderLabel = placeholderLabel
        
        NotificationCenter.default.addObserver(self, selector: #selector(textDidChange), name: Notification.Name.UITextViewTextDidChange, object: nil)
    }
    
    @objc func textDidChange(notification: Notification) {
        self.placeholderLabel.isHidden = self.text.count > 0
    }
    
    deinit {
        NotificationCenter.default.removeObserver(self, name: Notification.Name.UITextViewTextDidChange, object: nil)
    }
}
