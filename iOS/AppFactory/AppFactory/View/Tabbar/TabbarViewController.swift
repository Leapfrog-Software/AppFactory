//
//  TabbarViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/08.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class TabbarViewController: UIViewController {

    @IBOutlet private weak var container: UIView!
    @IBOutlet private weak var tab1ImageView: UIImageView!
    @IBOutlet private weak var tab2ImageView: UIImageView!
    @IBOutlet private weak var tab3ImageView: UIImageView!
    @IBOutlet private weak var tab4ImageView: UIImageView!
    @IBOutlet private weak var tab1Label: UILabel!
    @IBOutlet private weak var tab2Label: UILabel!
    @IBOutlet private weak var tab3Label: UILabel!
    @IBOutlet private weak var tab4Label: UILabel!
    
    private var engineerViewController: EngineerViewController!
    private var galleryViewController: GalleryViewController!
    private var progressViewController: ProgressViewController!
    private var otherViewController: OtherViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.initContents()
    }
    
    private func initContents() {
        
        self.engineerViewController = self.viewController(storyboard: "Engineer", identifier: "EngineerViewController") as! EngineerViewController
        self.addContents(self.engineerViewController, isHidden: false)
        self.galleryViewController = self.viewController(storyboard: "Gallery", identifier: "GalleryViewController") as! GalleryViewController
        self.addContents(self.galleryViewController, isHidden: true)
        self.progressViewController = self.viewController(storyboard: "Progress", identifier: "ProgressViewController") as! ProgressViewController
        self.addContents(self.progressViewController, isHidden: true)
        self.otherViewController = self.viewController(storyboard: "Other", identifier: "OtherViewController") as! OtherViewController
        self.addContents(self.otherViewController, isHidden: true)
        
        self.changeContents(index: 0)
    }
    
    private func addContents(_ viewController: UIViewController, isHidden: Bool) {
        
        self.container.addSubview(viewController.view)
        self.addChildViewController(viewController)
        viewController.didMove(toParentViewController: self)
        viewController.view.isHidden = isHidden
        
        viewController.view.translatesAutoresizingMaskIntoConstraints = false
        viewController.view.topAnchor.constraint(equalTo: self.container.topAnchor).isActive = true
        viewController.view.leadingAnchor.constraint(equalTo: self.container.leadingAnchor).isActive = true
        viewController.view.trailingAnchor.constraint(equalTo: self.container.trailingAnchor).isActive = true
        viewController.view.bottomAnchor.constraint(equalTo: self.container.bottomAnchor).isActive = true
    }
    
    func changeContents(index: Int) {
        
        self.engineerViewController.view.isHidden = (index != 0)
        self.galleryViewController.view.isHidden = (index != 1)
        self.progressViewController.view.isHidden = (index != 2)
        self.otherViewController.view.isHidden = (index != 3)
        
        self.tab1ImageView.image = UIImage(named: (index == 0) ? "tab1_on" : "tab1_off")
        self.tab2ImageView.image = UIImage(named: (index == 1) ? "tab2_on" : "tab2_off")
        self.tab3ImageView.image = UIImage(named: (index == 2) ? "tab3_on" : "tab3_off")
        self.tab4ImageView.image = UIImage(named: (index == 3) ? "tab4_on" : "tab4_off")
        
        self.tab1Label.textColor = (index == 0) ? .tabSelected : .tabUnselected
        self.tab2Label.textColor = (index == 1) ? .tabSelected : .tabUnselected
        self.tab3Label.textColor = (index == 2) ? .tabSelected : .tabUnselected
        self.tab4Label.textColor = (index == 3) ? .tabSelected : .tabUnselected
    }
    
    @IBAction func onTapTab1(_ sender: Any) {
        self.changeContents(index: 0)
    }
    
    @IBAction func onTapTab2(_ sender: Any) {
        self.changeContents(index: 1)
    }
    
    @IBAction func onTapTab3(_ sender: Any) {
        self.changeContents(index: 2)
    }
    
    @IBAction func onTapTab4(_ sender: Any) {
        self.changeContents(index: 3)
    }
}
