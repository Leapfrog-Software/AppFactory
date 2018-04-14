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
    @IBOutlet private weak var iosCheckBox: CheckBoxRoowView!
    @IBOutlet private weak var androidCheckBox: CheckBoxRoowView!
    @IBOutlet private weak var chatCheckBox: CheckBoxRoowView!
    @IBOutlet private weak var cameraCheckBox: CheckBoxRoowView!
    @IBOutlet private weak var movieCheckBox: CheckBoxRoowView!
    @IBOutlet private weak var pushCheckBox: CheckBoxRoowView!
    @IBOutlet private weak var mapCheckBox: CheckBoxRoowView!
    @IBOutlet private weak var geofenceCheckBox: CheckBoxRoowView!
    @IBOutlet private weak var settleCheckBox: CheckBoxRoowView!
    @IBOutlet private weak var creditCheckBox: CheckBoxRoowView!
    @IBOutlet private weak var userCheckBox: CheckBoxRoowView!
    @IBOutlet private weak var snsCheckBox: CheckBoxRoowView!
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
        let name = (self.targetEngineerName.count > 0) ? self.targetEngineerName : "登録中の開発者"
        self.engineerNameLabel.text = name + "様に見積もり依頼を送信します。"
    }
    
    private func showError(message: String) {
        let action = DialogAction(title: "OK", action: nil)
        Dialog.show(style: .error, title: "エラー", message: message, actions: [action])
    }
    
    @IBAction func onTapSend(_ sender: Any) {
        /*
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

            } else {
                // TODO
            }
        })
 */
    }
    
    @IBAction func onTapBack(_ sender: Any) {
        self.pop(animationType: .horizontal)
    }
}

