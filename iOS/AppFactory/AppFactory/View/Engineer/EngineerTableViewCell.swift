//
//  EngineerTableViewCell.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class EngineerTableViewCell: UITableViewCell {
    
    @IBOutlet private weak var userImageView: UIImageView!
    @IBOutlet private weak var nameLabel: UILabel!
    @IBOutlet private weak var areaLabel: UILabel!
    @IBOutlet private weak var organizationLabel: UILabel!
    @IBOutlet private weak var star1ImageView: UIImageView!
    @IBOutlet private weak var star2ImageView: UIImageView!
    @IBOutlet private weak var star3ImageView: UIImageView!
    @IBOutlet private weak var star4ImageView: UIImageView!
    @IBOutlet private weak var star5ImageView: UIImageView!
    @IBOutlet private weak var profileLabel: UILabel!
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        ImageStorage.shared.cancelRequest(imageView: self.userImageView)
    }

    func configure(engineerData: EngineerData) {
        
        let userImageUrl = Constants.ServerRootUrl + Constants.ServerEngineerImageDirectory + engineerData.id + ".png"
        ImageStorage.shared.fetch(url: userImageUrl, imageView: self.userImageView)
        
        self.nameLabel.text = engineerData.name
        self.areaLabel.text = engineerData.area
        self.organizationLabel.text = engineerData.organization.toText()

        let evaluateImages = CommonUtility.createEvaluateImages(engineerData.evaluate)
        self.star1ImageView.image = evaluateImages[0]
        self.star2ImageView.image = evaluateImages[1]
        self.star3ImageView.image = evaluateImages[2]
        self.star4ImageView.image = evaluateImages[3]
        self.star5ImageView.image = evaluateImages[4]
        
        self.profileLabel.set(text: engineerData.profile, lineHeight: 22)
    }
}
