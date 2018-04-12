//
//  GalleryCollectionViewCell.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class GalleryCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet private weak var appImageView: UIImageView!
    @IBOutlet private weak var appNameLabel: UILabel!
    @IBOutlet private weak var engineerNameLabel: UILabel!
    @IBOutlet private weak var areaLabel: UILabel!
    @IBOutlet private weak var organizationLabel: UILabel!
    @IBOutlet private weak var costLabel: UILabel!
    @IBOutlet private weak var evaluateLabel: UILabel!
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        ImageStorage.shared.cancelRequest(imageView: self.appImageView)
    }
    
    func configuer(galleryData: GalleryData) {
        
        let imageUrl = Constants.ServerRootUrl + Constants.ServerWorkImageDirectory + galleryData.id + ".png"
        ImageStorage.shared.fetch(url: imageUrl, imageView: self.appImageView)
        
        self.appNameLabel.set(text: galleryData.name, lineHeight: 22)
        self.engineerNameLabel.text = galleryData.engineerName
        self.areaLabel.text = galleryData.engineerArea
        self.organizationLabel.text = galleryData.engineerOrganization.toText()
        self.costLabel.text = CommonUtility.createCostText(galleryData.cost)
        self.evaluateLabel.text = CommonUtility.createEvaluateText(galleryData.evaluate)
    }
}
