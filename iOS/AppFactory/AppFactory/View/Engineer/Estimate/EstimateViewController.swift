//
//  EstimateViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/11.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class EstimateViewController: UIViewController {
    
    @IBOutlet private weak var engineerNameLabel: UILabel!
    @IBOutlet private weak var purposeTextView: UITextView!
    @IBOutlet private weak var descriptionTextView: UITextView!
    @IBOutlet private weak var iosCheckBox: CheckBox!
    @IBOutlet private weak var androidCheckBox: CheckBox!
    @IBOutlet private weak var chatCheckBox: CheckBox!
    @IBOutlet private weak var cameraCheckBox: CheckBox!
    @IBOutlet private weak var movieCheckBox: CheckBox!
    @IBOutlet private weak var pushCheckBox: CheckBox!
    @IBOutlet private weak var mapCheckBox: CheckBox!
    @IBOutlet private weak var geofenceCheckBox: CheckBox!
    @IBOutlet private weak var settleCheckBox: CheckBox!
    @IBOutlet private weak var creditCheckBox: CheckBox!
    @IBOutlet private weak var userCheckBox: CheckBox!
    @IBOutlet private weak var snsCheckBox: CheckBox!
    @IBOutlet private weak var notesTextView: UITextView!
    @IBOutlet private weak var emailTextField: UITextField!
    @IBOutlet private weak var agreeTermsCheckBox: CheckBox!
    
    private var targetEngineerId = ""
    private var targetEngineerName = ""
    
    func set(targetId: String, targetName: String) {
        self.targetEngineerId = targetId
        self.targetEngineerName = targetName
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.initContents()
    }
    
    private func initContents() {
        self.engineerNameLabel.text = self.targetEngineerName
    }
    
    private func showError(message: String) {
        let action = DialogAction(title: "OK", action: nil)
        Dialog.show(style: .error, title: "エラー", message: message, actions: [action])
    }
    
    @IBAction func onTapSend(_ sender: Any) {
        
        guard let purpose = self.purposeTextView.text,
            let description = self.descriptionTextView.text,
            let notes = self.notesTextView.text,
            let email = self.emailTextField.text else {
                return
        }
        
        if purpose.count == 0 {
            self.showError(message: "アプリの目的が未入力です")
            return
        }
        if description.count == 0 {
            self.showError(message: "アプリの概要が未入力です")
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
        
        let requestData = EstimateRequestData(target: self.targetEngineerId,
                                              purpose: purpose,
                                              description: description,
                                              ios: self.iosCheckBox.getValue(),
                                              android: self.androidCheckBox.getValue(),
                                              chat: self.chatCheckBox.getValue(),
                                              camera: self.cameraCheckBox.getValue(),
                                              movie: self.movieCheckBox.getValue(),
                                              push: self.pushCheckBox.getValue(),
                                              map: self.mapCheckBox.getValue(),
                                              geofence: self.geofenceCheckBox.getValue(),
                                              settle: self.settleCheckBox.getValue(),
                                              credit: self.creditCheckBox.getValue(),
                                              user: self.userCheckBox.getValue(),
                                              sns: self.snsCheckBox.getValue(),
                                              notes: notes,
                                              email: email)
        EstimateRequester.send(requestData: requestData, completion: { [weak self] result in
            if result {
                let complete = self?.viewController(storyboard: "Engineer", identifier: "EstimateCompleteViewController") as! EstimateCompleteViewController
                self?.stack(viewController: complete, animationType: .horizontal)
            } else {
                // TODO
            }
        })
    }
    
    @IBAction func onTapBack(_ sender: Any) {
        self.pop(animationType: .horizontal)
    }
}

