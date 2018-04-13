//
//  EngineerDetailProfileTableViewCell.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class EngineerDetailProfileTableViewCell: UITableViewCell {
    
    @IBOutlet private weak var userImageView: UIImageView!
    @IBOutlet private weak var nameLabel: UILabel!
    @IBOutlet private weak var areaLabel: UILabel!
    @IBOutlet private weak var organizationLabel: UILabel!
    @IBOutlet private weak var star1ImageView: UIImageView!
    @IBOutlet private weak var star2ImageView: UIImageView!
    @IBOutlet private weak var star3ImageView: UIImageView!
    @IBOutlet private weak var star4ImageView: UIImageView!
    @IBOutlet private weak var star5ImageView: UIImageView!
    @IBOutlet private weak var evaluateLabel: UILabel!
    @IBOutlet private weak var profileLabel: UILabel!
    @IBOutlet private weak var workNoLabel: UILabel!
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        ImageStorage.shared.cancelRequest(imageView: self.userImageView)
    }
    
    func configure(engineerDetailData: EngineerDetailResponseData) {
        
        let imageUrl = Constants.ServerRootUrl + Constants.ServerEngineerImageDirectory + engineerDetailData.id
        ImageStorage.shared.fetch(url: imageUrl, imageView: self.userImageView)
        
        self.nameLabel.text = engineerDetailData.name
        self.areaLabel.text = engineerDetailData.area
        self.organizationLabel.text = engineerDetailData.organization.toText()
        
        let evaluateImages = CommonUtility.createEvaluateImages(engineerDetailData.evaluate)
        self.star1ImageView.image = evaluateImages[0]
        self.star2ImageView.image = evaluateImages[1]
        self.star3ImageView.image = evaluateImages[2]
        self.star4ImageView.image = evaluateImages[3]
        self.star5ImageView.image = evaluateImages[4]
        
        self.evaluateLabel.text = CommonUtility.createEvaluateText(engineerDetailData.evaluate)
        
        self.profileLabel.text = engineerDetailData.profile
        
        let workCount = engineerDetailData.works.count
        self.workNoLabel.text = "(\(workCount)件)"
    }
}
