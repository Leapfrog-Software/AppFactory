//
//  Loading.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/15.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class Loading: UIView {
    
    @IBOutlet private weak var loading1ImageView: UIImageView!
    @IBOutlet private weak var loading2ImageView: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.animate()
    }
    
    private func animate() {
        
        let animation1 = CABasicAnimation(keyPath: "transform.rotation.z")
        animation1.toValue = NSNumber(value: 2 * Double.pi)
        animation1.duration = 1.2
        animation1.repeatCount = .infinity
        loading1ImageView.layer.add(animation1, forKey: "rotate")

        let animation2 = CABasicAnimation(keyPath: "transform.rotation.z")
        animation2.toValue = NSNumber(value: -2 * Double.pi)
        animation2.duration = 1.6
        animation2.repeatCount = .infinity
        loading2ImageView.layer.add(animation2, forKey: "rotate")
    }
    
    class func start() {
        
        guard let window = UIApplication.shared.keyWindow else {
            return
        }
        if !(window.subviews.flatMap { $0 as? Loading }).isEmpty {
            return
        }
        guard let loadingView = UINib(nibName: "Loading", bundle: nil).instantiate(withOwner: self, options: nil).first as? Loading else {
            return
        }
        window.addSubview(loadingView)
        loadingView.frame = CGRect(origin: .zero, size: window.frame.size)
    }
    
    class func stop() {
        
        guard let window = UIApplication.shared.keyWindow else {
            return
        }
        let loadingViews = window.subviews.flatMap { $0 as? Loading }
        loadingViews.forEach { $0.removeFromSuperview() }
    }
}

