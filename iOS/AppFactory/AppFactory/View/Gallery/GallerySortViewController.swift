//
//  GallerySortViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/15.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class GallerySortViewController: UIViewController {

    @IBOutlet private var evaluateImageView: UIImageView!
    @IBOutlet private var costImageView: UIImageView!
    
    private var defaultSortType: GallerySortType!
    private var completion: ((GallerySortType) -> ())!
    
    func set(defaultType: GallerySortType, completion: @escaping ((GallerySortType) -> ())) {
        self.defaultSortType = defaultType
        self.completion = completion
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.evaluateImageView.isHidden = (self.defaultSortType != .evaluate)
        self.costImageView.isHidden = (self.defaultSortType != .cost)
    }
    
    private func close(delay: Bool) {
        
        UIView.animate(withDuration: 0.15, delay: delay ? 0.3 : 0, animations: {
            self.view.alpha = 0
        }, completion: { _ in
            self.pop(animationType: .none)
        })
    }
    
    @IBAction func onTapEvaluate(_ sender: Any) {
        self.evaluateImageView.isHidden = false
        self.costImageView.isHidden = true
        self.completion(.evaluate)
        self.close(delay: true)
    }
    
    @IBAction func onTapCost(_ sender: Any) {
        self.evaluateImageView.isHidden = true
        self.costImageView.isHidden = false
        self.completion(.cost)
        self.close(delay: true)
    }
    
    @IBAction func onTapClose(_ sender: Any) {
        self.close(delay: false)
    }
}
