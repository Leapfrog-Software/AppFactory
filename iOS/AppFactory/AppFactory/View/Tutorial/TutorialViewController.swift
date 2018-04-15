//
//  TutorialViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/15.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class TutorialViewController: UIViewController {
    
    @IBOutlet private weak var scrollView: UIScrollView!
    @IBOutlet private weak var progress1View: UIView!
    @IBOutlet private weak var progress2View: UIView!
    @IBOutlet private weak var progress3View: UIView!
    @IBOutlet private weak var progress4View: UIView!
    @IBOutlet private weak var progress5View: UIView!
    @IBOutlet private weak var contentsViewVerticalConstraint: NSLayoutConstraint!
    @IBOutlet private weak var startButtonTopConstraint: NSLayoutConstraint!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.initContents()
    }
    
    private func initContents() {
        
        let viewTypes = TutorialView.ViewType.allValues()
        
        viewTypes.forEach { viewType in
            let tutorialView = UINib(nibName: "TutorialView", bundle: nil).instantiate(withOwner: nil, options: nil).first as! TutorialView
            tutorialView.set(viewType: viewType)
            tutorialView.frame = CGRect(x: self.scrollView.frame.size.width * CGFloat(viewType.rawValue),
                                        y: 0,
                                        width: self.scrollView.frame.size.width,
                                        height: self.scrollView.frame.size.height)
            self.scrollView.addSubview(tutorialView)
        }
        
        self.scrollView.contentSize = CGSize(width: CGFloat(viewTypes.count) * self.scrollView.frame.size.width,
                                             height: self.scrollView.frame.size.height)
        
        self.setProgress(page: 0)
    }
    
    private func setProgress(page: Int) {
        
        self.progress1View.backgroundColor = (page == 0) ? .mainBlack : .passiveGray
        self.progress2View.backgroundColor = (page == 1) ? .mainBlack : .passiveGray
        self.progress3View.backgroundColor = (page == 2) ? .mainBlack : .passiveGray
        self.progress4View.backgroundColor = (page == 3) ? .mainBlack : .passiveGray
        self.progress5View.backgroundColor = (page == 4) ? .mainBlack : .passiveGray
    }
    
    @IBAction func onTapStart(_ sender: Any) {
        
        let saveData = SaveData.shared
        saveData.didShowTutorial = true
        saveData.save()
        
        self.contentsViewVerticalConstraint.constant = UIScreen.main.bounds.size.height
        UIView.animate(withDuration: 0.15, animations: {
            self.view.layoutIfNeeded()
        }, completion: { _ in
            self.pop(animationType: .none)
        })
    }
}

extension TutorialViewController: UIScrollViewDelegate {

    func scrollViewDidEndDecelerating(_ scrollView: UIScrollView) {
        
        let page = Int(self.scrollView.contentOffset.x / self.scrollView.frame.size.width)
        if page < 0 || page >= TutorialView.ViewType.allValues().count {
            return
        }
        self.setProgress(page: page)
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {

        let page = Int(self.scrollView.contentOffset.x / self.scrollView.frame.size.width)
        if page >= 4 {
            self.startButtonTopConstraint.constant = 8
            return
        }
        if page == 3 {
            let offset = Int(self.scrollView.contentOffset.x) % Int(self.scrollView.frame.size.width)
            let rate = CGFloat(offset) / self.scrollView.frame.size.width
            self.startButtonTopConstraint.constant = 67 * (1 - rate) + 8
        } else {
            self.startButtonTopConstraint.constant = 75
        }
    }
}
