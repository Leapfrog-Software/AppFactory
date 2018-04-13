//
//  EngineerDetailWorkTableViewCell.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class EngineerDetailWorkTableViewCell: UITableViewCell {

    @IBOutlet private weak var workImageView: UIImageView!
    @IBOutlet private weak var workNameLabel: UILabel!
    @IBOutlet private weak var osLabel: UILabel!
    @IBOutlet private weak var languageLabel: UILabel!
    @IBOutlet private weak var functionLabel: UILabel!
    @IBOutlet private weak var costLabel: UILabel!
    @IBOutlet private weak var star1ImageView: UIImageView!
    @IBOutlet private weak var star2ImageView: UIImageView!
    @IBOutlet private weak var star3ImageView: UIImageView!
    @IBOutlet private weak var star4ImageView: UIImageView!
    @IBOutlet private weak var star5ImageView: UIImageView!
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        ImageStorage.shared.cancelRequest(imageView: self.workImageView)
    }
    
    func configure(workData: EngineerDetailWorkData){
        
        let imageUrl = Constants.ServerRootUrl + Constants.ServerWorkImageDirectory + workData.id
        ImageStorage.shared.fetch(url: imageUrl, imageView: self.workImageView)

        self.workNameLabel.text = workData.name
        self.osLabel.text = workData.os
        self.languageLabel.text = workData.language
        self.functionLabel.text = workData.function
        self.costLabel.text = CommonUtility.createCostText(workData.cost)
        
        let starImages = CommonUtility.createEvaluateImages(workData.evaluate)
        self.star1ImageView.image = starImages[0]
        self.star2ImageView.image = starImages[1]
        self.star3ImageView.image = starImages[2]
        self.star4ImageView.image = starImages[3]
        self.star5ImageView.image = starImages[4]
    }
}
