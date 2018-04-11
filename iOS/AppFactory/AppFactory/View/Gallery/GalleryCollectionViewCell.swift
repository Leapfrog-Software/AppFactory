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
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        ImageStorage.shared.cancelRequest(imageView: self.appImageView)
    }
    
    func configuer(galleryData: GalleryData) {
        
        let imageUrl = Constants.ServerRootUrl + Constants.ServerWorkImageDirectory + galleryData.id + ".png"
        ImageStorage.shared.fetch(url: imageUrl, imageView: self.appImageView)
    }
}
