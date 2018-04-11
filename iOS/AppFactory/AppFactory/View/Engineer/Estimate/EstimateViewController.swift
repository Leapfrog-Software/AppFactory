//
//  EstimateViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/11.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class EstimateViewController: UIViewController {

    private var isTargetAll = false
    
    func set(isTargetAll: Bool) {
        self.isTargetAll = isTargetAll
    }

    @IBAction func onTapSend(_ sender: Any) {
        
        // TODO
        let requestData = EstimateRequestData(target: "", purpose: "", description: "", ios: true, android: true, chat: true, camera: true, movie: true, push: true, map: true, geofence: true, settle: true, credit: true, user: true, sns: true, notes: "", email: "")
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
